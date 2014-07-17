package com.crm.provisioning.thread;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import com.crm.kernel.message.Constants;
import com.crm.kernel.queue.QueueFactory;
import com.crm.kernel.sql.Database;
import com.crm.provisioning.cache.CommandEntry;
import com.crm.provisioning.cache.ProvisioningFactory;
import com.crm.provisioning.message.CommandMessage;
import com.crm.provisioning.util.ResponseUtil;
import com.crm.thread.DispatcherThread;
import com.fss.thread.ParameterType;
import com.fss.util.AppException;

public class SendSMS extends DispatcherThread
{
	protected PreparedStatement	_stmtQueue			= null;
	protected PreparedStatement	_stmtUpdate			= null;
	protected String			_sqlCommand			= "";
	protected int				_batchNumber		= 1000;
	protected Connection 		connection			= null;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Vector getParameterDefinition()
	{
		Vector vtReturn = new Vector();

		vtReturn.addElement(createParameterDefinition("SQLCommand", "",
				ParameterType.PARAM_TEXTBOX_MAX, "100"));
		vtReturn.addElement(createParameterDefinition("BatchNumber", "",
				ParameterType.PARAM_TEXTBOX_MAX, "100"));

		vtReturn.addAll(super.getParameterDefinition());

		return vtReturn;
	}
	
	public void fillParameter() throws AppException
	{
		try
		{
			super.fillParameter();

			// Fill parameter
			setSQLCommand(loadMandatory("SQLCommand"));
			setBatchNumber(loadInteger("BatchNumber"));
		}
		catch (AppException e)
		{
			throw e;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void beforeProcessSession() throws Exception
	{
		super.beforeProcessSession();

		try
		{
			connection = Database.getConnection();
			String strSQL = getSQLCommand();
			if (strSQL.toLowerCase().contains("where"))
			{
				strSQL = strSQL + " and rownum <= " + getBatchNumber();
			}
			else
			{
				strSQL = strSQL + " where rownum <= " + getBatchNumber();
			}
			_stmtQueue = connection.prepareStatement(strSQL);
			
			strSQL = "Update send_sms Set sendflag = 1 where Id = ?";
			_stmtUpdate = connection.prepareStatement(strSQL);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
	
	public void afterProcessSession() throws Exception
	{
		try
		{
			Database.closeObject(_stmtQueue);
			Database.closeObject(_stmtUpdate);
			Database.closeObject(connection);
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			super.afterProcessSession();
		}
	}
	
	public void doProcessSession() throws Exception
	{
		ResultSet rsQueue = null;
		CommandMessage order = null;
		
		String isdn = "";
		String shotCode = "";
		String content = "";
		String productCode = "";

		int counter = 0;
		try
		{
			rsQueue = _stmtQueue.executeQuery();
			while (rsQueue.next() && isAvailable())
			{
				isdn = rsQueue.getString("target_number");
				shotCode = rsQueue.getString("source_number");
				content = rsQueue.getString("content");
				productCode = rsQueue.getString("product_Code");
				
				debugMonitor("Isdn: " + isdn + ", SC: " + shotCode + ", Content: " + content);
				
				order = pushOrder(isdn, shotCode, content, productCode);
				
				QueueFactory.attachCommandRouting(order);
				
				_stmtUpdate.setLong(1, rsQueue.getLong("ID"));
				_stmtUpdate.addBatch();
				
				counter++;
			}
			
			if (counter > 0)
			{
				_stmtUpdate.executeBatch();
			}
		}
		catch (Exception ex)
		{
			logMonitor("Error: " + ex.getMessage());
	
		}
		finally
		{
			rsQueue.close();
			connection.commit();
		}
	}
	
	public CommandMessage pushOrder(String isdn, String serviceAddress, String content, String productCode) throws Exception
	{
		CommandMessage order = new CommandMessage();

		try
		{
			CommandEntry command = ProvisioningFactory.getCache().getCommand(Constants.COMMAND_SEND_MT);
			
			order.setProvisioningType(Constants.PROVISIONING_MT);
			order.setCommandId(command.getCommandId());
			order.setServiceAddress(serviceAddress);
			order.setIsdn(isdn);
			
			order.setRequest(content);
			order.setRequestValue("productCode", productCode);
		}
		catch (Exception e)
		{
			throw e;
		}
		return order;
	}
	
	public void setSQLCommand(String _sqlCommand)
	{
		this._sqlCommand = _sqlCommand;
	}

	public String getSQLCommand()
	{
		return _sqlCommand;
	}
	
	public void setBatchNumber(int _batchNumber)
	{
		this._batchNumber = _batchNumber;
	}

	public int getBatchNumber()
	{
		return _batchNumber;
	}
}
