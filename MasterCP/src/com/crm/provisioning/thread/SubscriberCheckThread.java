/**
 * 
 */
package com.crm.provisioning.thread;

import java.sql.PreparedStatement;
import java.util.Vector;

import com.crm.thread.DispatcherThread;
import com.crm.thread.util.ThreadUtil;
import com.fss.sql.Database;
import com.fss.util.AppException;

/**
 * @author hungdt
 *
 */
public class SubscriberCheckThread extends DispatcherThread
{
	private PreparedStatement	_stmtQueue			= null;
	private PreparedStatement	_stmtAddQueue		= null;
	private PreparedStatement	_stmtSubscribers	= null;
	private String				_sqlCommand			= "";
	private String				_channel			= "";
	private String				_lastRunDate		= "";
	
	// //////////////////////////////////////////////////////
	// Override
	// //////////////////////////////////////////////////////
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Vector getParameterDefinition()
	{
		Vector vtReturn = new Vector();

		vtReturn.addElement(ThreadUtil.createTextParameter("SQLCommand", 400, "SQL query to get subscription."));

		vtReturn.addElement(ThreadUtil.createTextParameter("LastRunDate", 400, "SQL query to get subscription."));
		
		vtReturn.addElement(ThreadUtil.createTextParameter("channel", 400, "Subscription channel \"web\" or \"SMS\"."));

		vtReturn.addAll(super.getParameterDefinition());

		return vtReturn;
	}

	// //////////////////////////////////////////////////////
	// Override
	// //////////////////////////////////////////////////////
	public void fillParameter() throws AppException
	{
		try
		{
			super.fillParameter();

			setSQLCommand(loadMandatory("SQLCommand"));
			setLastRunDate(loadMandatory("LastRunDate"));
			
			_channel = ThreadUtil.getString(this, "channel", false, "web");
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

	// //////////////////////////////////////////////////////
	// after process session
	// Author : ThangPV
	// Created Date : 16/09/2004
	// //////////////////////////////////////////////////////
	public void beforeProcessSession() throws Exception
	{
		super.beforeProcessSession();

		try
		{
			neverExpire = false;

			String strSQL = getSQLCommand();
			_stmtQueue = getConnection().prepareStatement(strSQL);

			strSQL = "Update SubscriberProduct Set lastRunDate = SysDate Where subProductId = ?";
			_stmtSubscribers = getConnection().prepareStatement(strSQL);

			strSQL = "insert into CommandRequest "
					+ "		(requestId, userName, createDate, requestDate "
					+ "		, channel, serviceAddress, isdn, keyword) "
					+ " values "
					+ "		(command_seq.nextval, ?, SysDate, SysDate"
					+ "		, ?, ?, ?, ?) ";
			_stmtAddQueue = getConnection().prepareStatement(strSQL);
		}
		catch (Exception e)
		{
			throw e;
		}
	}

	// //////////////////////////////////////////////////////
	// after process session
	// Author : ThangPV
	// Created Date : 16/09/2004
	// //////////////////////////////////////////////////////
	public void afterProcessSession() throws Exception
	{
		try
		{
			Database.closeObject(_stmtQueue);
			Database.closeObject(_stmtSubscribers);
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
	
	
	public void setLastRunDate(String _lastRunDate)
	{
		this._lastRunDate = _lastRunDate;
	}

	public String getLastRunDate()
	{
		return _lastRunDate;
	}

	public void setSQLCommand(String _sqlCommand)
	{
		this._sqlCommand = _sqlCommand;
	}

	public String getSQLCommand()
	{
		return _sqlCommand;
	}
}
