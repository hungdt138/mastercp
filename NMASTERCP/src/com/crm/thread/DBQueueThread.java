package com.crm.thread;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

import com.crm.kernel.queue.QueueFactory;
import com.crm.kernel.sql.Database;
import com.crm.provisioning.message.CommandMessage;
import com.crm.thread.util.ThreadUtil;
import com.crm.util.AppProperties;

import com.fss.util.AppException;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright:
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author: HungPQ
 * @version 1.0
 */

public class DBQueueThread extends DispatcherThread
{
	public PreparedStatement					stmtQueue			= null;
	public ResultSet							rsQueue				= null;

	public String								selectSQL			= "";
	public String								updateSQL			= "";
	public String								queueTable			= "";
	public String								primaryKey			= "";
	public String								keywordPrefix		= "";

	public int									pendingMaxSize		= 0;
	public String								pendingQueueList	= "";

	public int									totalServerPending	= 0;
	public ConcurrentHashMap<String, Boolean>	indexes				= new ConcurrentHashMap<String, Boolean>();

	// //////////////////////////////////////////////////////
	// Override
	// //////////////////////////////////////////////////////
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Vector getDispatcherDefinition()
	{
		Vector vtReturn = new Vector();

		vtReturn.addElement(ThreadUtil.createTextParameter("selectSQL", 4000, ""));
		vtReturn.addElement(ThreadUtil.createTextParameter("updateSQL", 4000, ""));
		vtReturn.addElement(ThreadUtil.createTextParameter("queueTable", 50, ""));
		vtReturn.addElement(ThreadUtil.createTextParameter("primaryKey", 50, ""));
		vtReturn.addElement(ThreadUtil.createIntegerParameter("pendingMaxSize", "max request is waitting for process"));
		vtReturn.addElement(ThreadUtil.createIntegerParameter("overloadWaitTime", "wait in seconds when system is overloading"));

		if (QueueFactory.queueServerEnable)
		{
			vtReturn.addElement(
					ThreadUtil.createBooleanParameter("queueDispatcherEnable", "init queue connection when start dispatcher"));
			vtReturn.addElement(
					ThreadUtil.createTextParameter("pendingQueueList", 4000, "list of queue are used to check pending size"));
		}
		vtReturn.addElement(ThreadUtil.createTextParameter("queueName", 100, "jndi queue name"));
		vtReturn.addElement(ThreadUtil.createTextParameter("queueLocalName", 100, "temporary queue name"));
		vtReturn.addElement(ThreadUtil.createIntegerParameter("queueLocalSize", "Max local queue size"));
		vtReturn.addElement(ThreadUtil.createBooleanParameter("neverExpire", "Loop or not"));
		
		vtReturn.addAll(ThreadUtil.createInstanceParameter(this));

		vtReturn.addAll(ThreadUtil.createLogParameter(this));
//		vtReturn.addALl(super.getDispatcherDefinition());
//		super.getDispatcherDefinition();
		return vtReturn;
	}

	// //////////////////////////////////////////////////////
	// Override
	// //////////////////////////////////////////////////////
	public void fillDispatcherParameter() throws AppException
	{
		try
		{
			super.fillDispatcherParameter();


			selectSQL = ThreadUtil.getString(this, "selectSQL", true, "");
			queueTable = ThreadUtil.getString(this, "queueTable", true, "");
			primaryKey = ThreadUtil.getString(this, "primaryKey", true, "");

			pendingMaxSize = ThreadUtil.getInt(this, "pendingMaxSize", 1000);
		}
		catch (AppException e)
		{
			logMonitor(e);

			throw e;
		}
		catch (Exception e)
		{
			logMonitor(e);

			throw new AppException(e.getMessage());
		}
		finally
		{
		}
	}

	// //////////////////////////////////////////////////////
	// process session
	// Author : ThangPV
	// Created Date : 16/09/2004
	// //////////////////////////////////////////////////////
	public Object detachMessage() throws Exception
	{
		CommandMessage request = new CommandMessage();

		request.setRequestTime(new Date());
		request.setRequestId(rsQueue.getLong("requestId"));
		request.setChannel(rsQueue.getString("channel"));
		request.setServiceAddress(rsQueue.getString("serviceAddress"));
		request.setIsdn(rsQueue.getString("isdn"));
		request.setKeyword(rsQueue.getString("keyword"));
		request.setSubProductId(rsQueue.getLong("subProductId"));
		request.getParameters().setString("Description", rsQueue.getString("description"));
		return request;
	}

	// /////////////////////////////////////////////////////////////////////////
	// Prepare data target
	// Author: ThangPV
	// Modify DateTime: 19/02/2003
	// /////////////////////////////////////////////////////////////////////////
	public boolean isOverload()
	{
		if (QueueFactory.getLocalQueue(queueName).isOverload())
		{
			return true;
		}
		else if (QueueFactory.getLocalQueue(queueLocalName).isOverload())
		{
			return true;
		}
		else if (pendingMaxSize > 0)
		{
			int totalPending = QueueFactory.getTotalLocalPending() + totalServerPending;

			return (totalPending > pendingMaxSize);
		}

		return false;
	}

	// //////////////////////////////////////////////////////
	/**
	 * Event raised when session prepare to run
	 * 
	 * @throws java.lang.Exception
	 * @author Phan Viet Thang
	 */
	// //////////////////////////////////////////////////////
	public void beforeProcessSession() throws Exception
	{
		super.beforeProcessSession();

		try
		{
			mcnMain = Database.getConnection();

			QueueFactory.getLocalQueue(queueLocalName).setCheckPending(false);

			if (!selectSQL.equals(""))
			{
				stmtQueue = mcnMain.prepareStatement(selectSQL);
			}
			else if (!queueTable.equals(""))
			{
				selectSQL = "Select * From " + queueTable;
			}

			indexes.clear();
		}
		catch (Exception e)
		{
			throw e;
		}
	}

	// //////////////////////////////////////////////////////
	/**
	 * Event raised when session prepare to run
	 * 
	 * @throws java.lang.Exception
	 * @author Phan Viet Thang
	 */
	// //////////////////////////////////////////////////////
	public void afterProcessSession() throws Exception
	{
		try
		{
			if (totalCount > 0)
			{
				StringBuilder sbLog = new StringBuilder();

				sbLog.append(" End of process session ");
				sbLog.append("\n      Total record    : ");
				sbLog.append(totalCount);
				sbLog.append("\n      Success record  : ");
				sbLog.append(successCount);
				sbLog.append("\n      Bypass record   : ");
				sbLog.append(bypassCount);
				sbLog.append("\n      Error record    : ");
				sbLog.append(errorCount);

				logMonitor(sbLog.toString());
			}
		}
		finally
		{
			Database.closeObject(stmtQueue);
			Database.closeObject(rsQueue);
			Database.closeObject(mcnMain);

			super.afterProcessSession();
		}
	}

	public void updateSnapshot() throws Exception
	{
		totalServerPending = pendingMaxSize;
		totalServerPending = QueueFactory.getSnapshotSize(pendingQueueList);
	}

	public void updateStatistic() throws Exception
	{
		if ((lastStatistic > 0) && (totalCount > 0))
		{
			StringBuilder sbLog = new StringBuilder();

			sbLog.append(" Total ");
			sbLog.append(totalCount);
			sbLog.append(" record are processed.");
			sbLog.append("\n      Success record  : ");
			sbLog.append(successCount);
			sbLog.append("\n      Bypass record   : ");
			sbLog.append(bypassCount);
			sbLog.append("\n      Error record    : ");
			sbLog.append(errorCount);

			logMonitor(sbLog.toString());
			synchronized (mutex)
			{
				totalCount = 0;
				successCount = 0;
				bypassCount = 0;
				errorCount = 0;
			}
		}
	}

	// //////////////////////////////////////////////////////
	// process session
	// Author : ThangPV
	// Created Date : 16/09/2004
	// //////////////////////////////////////////////////////
	public void doProcessSession() throws Exception
	{
		try
		{
			boolean EOF = false;

			rsQueue = stmtQueue.executeQuery();

			while (isAvailable() && !EOF)
			{
				checkInstance();

				if ((System.currentTimeMillis() - lastSnapshot) > snapshotInterval)
				{
					updateSnapshot();
					lastSnapshot = System.currentTimeMillis();
				}
				if ((System.currentTimeMillis() - lastStatistic) > statisticInterval)
				{
					updateStatistic();
					lastStatistic = System.currentTimeMillis();
				}

				if (isOverload())
				{
					processOverload(null);
				}
				else if (rsQueue.next())
				{
					Object request = detachMessage();

					String requestId = rsQueue.getString(primaryKey);

					if (indexes.get(requestId) == null)
					{
						QueueFactory.attachLocal(queueLocalName, request);

						indexes.put(requestId, Boolean.TRUE);
					}
					else if (displayDebug)
					{
						debugMonitor("Request " + requestId + " is loaded");
					}
				}
				else
				{
					EOF = true;
				}

				Thread.sleep(1);
			}
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}
