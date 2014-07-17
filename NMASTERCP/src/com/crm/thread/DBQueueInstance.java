package com.crm.thread;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.QueueSession;

import com.crm.kernel.message.Constants;
import com.crm.kernel.queue.QueueFactory;
import com.crm.kernel.sql.Database;
import com.crm.provisioning.message.CommandMessage;

public class DBQueueInstance extends DispatcherInstance
{
	protected PreparedStatement	stmtRemove	= null;
	protected Connection		connection	= null;

	public DBQueueInstance() throws Exception
	{
		super();
	}

	public DBQueueThread getDispatcher()
	{
		return (DBQueueThread) super.getDispatcher();
	}

	public void beforeProcessSession() throws Exception
	{
		super.beforeProcessSession();

		try
		{
			connection = Database.getConnection();

			if (!getDispatcher().queueTable.equals("") && !getDispatcher().primaryKey.equals(""))
			{
				String SQL = "Delete " + getDispatcher().queueTable + " Where " + getDispatcher().primaryKey + " = ?";

				stmtRemove = connection.prepareStatement(SQL);
			}
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
			Database.closeObject(stmtRemove);
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

	public int processMessage(QueueSession session, MessageProducer producer, Object request) throws Exception
	{
		if (request == null)
		{
			return Constants.BIND_ACTION_NONE;
		}

		try
		{
			String debugContent = dispatcher.displayDebug ? request.toString() : "";

			if (dispatcher.queueDispatcherEnable)
			{
				Message message = QueueFactory.createObjectMessage(session, request);

				producer.send(message);

				synchronized (dispatcher.mutex)
				{
					getDispatcher().totalServerPending++;
				}
			}
			else
			{
				QueueFactory.attachLocal(dispatcher.queueName, request);
			}

			long requestId = 0;

			if (request instanceof CommandMessage)
			{
				requestId = ((CommandMessage) request).getRequestId();
			}

			if (requestId != Constants.DEFAULT_ID)
			{
				if (!getDispatcher().primaryKey.equals(""))
				{
					stmtRemove.setLong(1, requestId);
					stmtRemove.execute();

					connection.commit();
				}

				getDispatcher().indexes.remove(requestId);
			}

			if (dispatcher.displayDebug)
			{
				debugMonitor(debugContent);
			}
		}
		catch (Exception e)
		{
			Database.rollback(connection);

			throw e;
		}

		return Constants.BIND_ACTION_SUCCESS;
	}

	// //////////////////////////////////////////////////////
	// process session
	// Author : ThangPV
	// Created Date : 16/09/2004
	// //////////////////////////////////////////////////////
	public void doProcessSession() throws Exception
	{
		Queue sendQueue = null;
		QueueSession session = null;
		MessageProducer producer = null;

		try
		{
			if (dispatcher.queueDispatcherEnable)
			{
				sendQueue = QueueFactory.getQueue(dispatcher.queueName);
				session = dispatcher.getQueueSession();
				producer = QueueFactory.createProducer(session, sendQueue);
			}

			while (isAvailable())
			{
				if (isOverload())
				{
					Thread.sleep(getDispatcher().overloadWaitTime);
				}
				else
				{
					Object request = detachMessage();

					if (request != null)
					{
						int action = processMessage(session, producer, request);

						if (action == Constants.BIND_ACTION_SUCCESS)
						{
							synchronized (dispatcher.mutex)
							{
								dispatcher.successCount++;
							}
						}
						else if (action == Constants.BIND_ACTION_ERROR)
						{
							synchronized (dispatcher.mutex)
							{
								dispatcher.errorCount++;
							}
						}
						else if (action == Constants.BIND_ACTION_BYPASS)
						{
							synchronized (dispatcher.mutex)
							{
								dispatcher.bypassCount++;
							}
						}

						synchronized (dispatcher.mutex)
						{
							dispatcher.totalCount++;
						}
					}
				}

				Thread.sleep(1);
			}
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			QueueFactory.closeQueue(session);
		}
	}
}
