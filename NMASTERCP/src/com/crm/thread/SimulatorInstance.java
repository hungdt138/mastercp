package com.crm.thread;

import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.QueueSession;

import com.crm.kernel.queue.QueueFactory;
import com.crm.provisioning.message.CommandMessage;
import com.crm.thread.DispatcherInstance;

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

public class SimulatorInstance extends DispatcherInstance
{
	// //////////////////////////////////////////////////////
	// Queue variables
	// //////////////////////////////////////////////////////
	public SimulatorInstance() throws Exception
	{
		super();
	}

	public SimulatorThread getDispatcher()
	{
		return (SimulatorThread) dispatcher;
	}

	public boolean isOverload()
	{
		if ((getDispatcher().maxLocalPending > 0)
				&& (QueueFactory.getTotalLocalPending() >= getDispatcher().maxLocalPending))
		{
			return true;
		}
		else if ((getDispatcher().maxServerPending > 0)
				&& (getDispatcher().getTotalServerPending() >= getDispatcher().maxServerPending))
		{
			return true;
		}

		return false;
	}

	// //////////////////////////////////////////////////////
	// process session
	// Author : ThangPV
	// Created Date : 16/09/2004
	// //////////////////////////////////////////////////////
	public void processOverload() throws Exception
	{
		// logMonitor("sleeping ..." + getDispatcher().getTotalServerPending());

		Thread.sleep(10);
	}

	// //////////////////////////////////////////////////////
	// process session
	// Author : ThangPV
	// Created Date : 16/09/2004
	// //////////////////////////////////////////////////////
	public void doProcessSession() throws Exception
	{
		Queue sendQueue = null;
		Queue responseQueue = null;

		QueueSession session = null;
		MessageProducer producer = null;
		MessageConsumer consumer = null;

		try
		{
			if (QueueFactory.queueServerEnable && dispatcher.queueDispatcherEnable)
			{
				sendQueue = QueueFactory.getQueue(getDispatcher().queueName);

				if (!getDispatcher().queueCallback.equals(""))
				{
					responseQueue = QueueFactory.getQueue(getDispatcher().queueCallback);
				}

				session = dispatcher.getQueueSession();
				producer = QueueFactory.createProducer(session, sendQueue);
			}

			while (isAvailable())
			{
				if (isOverload())
				{
					processOverload();

					continue;
				}
				else
				{
					CommandMessage request = (CommandMessage) detachMessage();

					if (request == null)
					{
						continue;
					}

					long costTime = 0;
					long startTime = System.currentTimeMillis();

					if (dispatcher.queueDispatcherEnable)
					{
						Message message = QueueFactory.createObjectMessage(session, request);

						producer.send(message);

						costTime = System.currentTimeMillis() - startTime;

						if (costTime > 1500)
						{
							logMonitor("long time for sending request: " + costTime);
						}

						synchronized (dispatcher.mutex)
						{
							getDispatcher().totalServerPending++;
						}
					}
					else
					{
						QueueFactory.attachLocal(dispatcher.queueLocalName, request);
					}

					if (responseQueue != null)
					{
						startTime = System.currentTimeMillis();

						Object response = null;
						String correlationId = request.getCorrelationID();

						QueueFactory.callbackListerner.put(correlationId, request);

						if (getDispatcher().asynchronous)
						{
							try
							{
								synchronized (request)
								{
									request.wait(getDispatcher().orderTimeout);
								}
							}
							catch (Exception e)
							{
								logMonitor(e);
							}
							finally
							{
								response = QueueFactory.callbackOrder.remove(correlationId);

								QueueFactory.callbackListerner.remove(request.getCorrelationID());
							}
						}
						else
						{
							try
							{
								consumer = session.createConsumer(responseQueue, "JMSCorrelationID = '" + correlationId + "'");

								response = consumer.receive(getDispatcher().orderTimeout);

								if (response == null)
								{

								}
							}
							finally
							{
								QueueFactory.closeQueue(consumer);
							}
						}

						costTime = (System.currentTimeMillis() - startTime);

						if (response == null)
						{
							logMonitor("order timeout:" + correlationId);
						}
						else
						{
							logMonitor("execute time: " + costTime + ", correlationId = " + correlationId);
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
			QueueFactory.closeQueue(consumer);
			QueueFactory.closeQueue(producer);
			QueueFactory.closeQueue(session);
		}
	}

}
