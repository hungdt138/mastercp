/**
 * ----------------------------------------------------------------- 
 * @ Copyright(c) 2013 Vietnamobile. JSC. All Rights Reserved.
 * ----------------------------------------------------------------- 
 * Date 	Author 		Version
 * ------------------------------------- 
 * Nov 24, 2013 hungdt  v1.0
 * -------------------------------------
 */
package com.crm.provisioning.thread;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import com.crm.kernel.sql.Database;
import com.crm.provisioning.cache.MQConnection;
import com.crm.provisioning.message.CommandMessage;
import com.crm.thread.DispatcherThread;
import com.crm.thread.util.ThreadUtil;
import com.fss.util.AppException;

/**
 * @author hungdt
 *
 */
public class SubscriptionVNMRequestThread extends DispatcherThread
{
	public String						sqlQuery			= "";
	public int							requestTimeout		= 1200;

	private PreparedStatement			stmtGetRequest		= null;
	private PreparedStatement			stmtRemoveRequest	= null;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Vector getParameterDefinition()
	{
		Vector vtReturn = new Vector();
		// vtReturn.add(ThreadUtil.createTextParameter("subscriptionProductsList",
		// 400, "List of products need to subscribe, separate by comma (,)."));
		// vtReturn.add(ThreadUtil.createTextParameter("subscriptionChannel",
		// 400,
		// "Channel of subscription request will send to, \"SMS\" or \"web\"."));
		vtReturn.add(ThreadUtil.createTextParameter("sqlQuery", 400, "Query from subscriberproduct table for subscription info."));
		vtReturn.add(ThreadUtil.createIntegerParameter("requestTimeout", "Life time of subscription request, in second."));
		vtReturn.addAll(super.getParameterDefinition());

		return vtReturn;
	}

	@Override
	public void fillDispatcherParameter() throws AppException
	{
		// TODO Auto-generated method stub
		super.fillDispatcherParameter();

		sqlQuery = ThreadUtil.getString(this, "sqlQuery", false, "");
		requestTimeout = ThreadUtil.getInt(this, "requestTimeout", 1200);
	}
	
	@Override
	public void beforeProcessSession() throws Exception
	{
		super.beforeProcessSession();

		try
		{
			stmtGetRequest = getConnection().prepareStatement(sqlQuery);

			String strSQL = "Delete CommandRequest Where requestId = ?";
			stmtRemoveRequest = getConnection().prepareStatement(strSQL);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
	
	@Override
	public void afterProcessSession() throws Exception
	{
		try
		{
			Database.closeObject(stmtGetRequest);
			Database.closeObject(stmtRemoveRequest);
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
	
	@Override
	public void doProcessSession() throws Exception
	{
		ResultSet rsQueue = stmtGetRequest.executeQuery();
		int count = 0;

		while (rsQueue.next() && isAvailable())
		{
			
			
		}
		
		logMonitor("Processed " + count + " records.");
	}
}
