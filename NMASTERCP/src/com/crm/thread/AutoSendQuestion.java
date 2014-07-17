package com.crm.thread;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;


import com.crm.kernel.sql.Database;
import com.crm.provisioning.message.CommandMessage;
import com.crm.provisioning.util.CommandUtil;
import com.crm.thread.util.ThreadUtil;
import com.fss.util.AppException;

public class AutoSendQuestion extends DispatcherThread
{
	private String strSQL = ""; 
	
	private PreparedStatement stmtGetList = null;
	private PreparedStatement stmtUpdate = null;
	private Connection connection = null;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Vector getDispatcherDefinition()
	{
		Vector vtReturn = new Vector();
		
		vtReturn.addElement(ThreadUtil.createTextParameter("SQL", 1000, "SQL statement to get list subs"));
		vtReturn.addAll(ThreadUtil.createLogParameter(this));
		return vtReturn;
	}
	public void fillParameter() throws AppException
	{
		try
		{
			strSQL = loadMandatory("SQL");
			super.fillParameter();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	public void beforeProcessSession() throws Exception
	{
		logMonitor("Starting Send Go question.");
		connection = Database.getConnection();
		stmtGetList = connection.prepareStatement(strSQL);
		String strUpdateSQL = "Update send_sms_go set sendflag = 1 where id = ?";
		stmtUpdate = connection.prepareStatement(strUpdateSQL);
	}
	public void afterProcessSession() throws Exception
	{
		Database.closeObject(stmtGetList);
		Database.closeObject(stmtUpdate);
		Database.closeObject(connection);
	}
	
	public void doProcessSession() throws Exception
	{
		ResultSet rs = stmtGetList.executeQuery();
		CommandMessage message = new CommandMessage();
		String logString = "";
		int count = 0;
		try
		{
			while (rs.next())
			{
				logString = logString + rs.getString("ISDN") +":" + rs.getLong("questionId") +"\n\r";
				message.setIsdn(rs.getString("ISDN"));
				CommandUtil.sendSMS(null, message, rs.getString("ServiceAddress"), "", rs.getString("Content"));
				stmtUpdate.setLong(1, rs.getLong("ID"));
				stmtUpdate.addBatch();
				count = count + 1;
				if (count >= batchSize)
				{
					stmtUpdate.executeBatch();
					logMonitor(logString);
					count = 0;
					logString = "";
				}
			}
			if (count > 0)
			{
				stmtUpdate.executeBatch();
				logMonitor(logString);
				count = 0;
				logString = "";
			}
		}
		finally
		{
			Database.closeObject(rs);
		}
	}
}
