package com.crm.provisioning.thread;

import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.QueueSession;

import com.crm.kernel.message.Constants;
import com.crm.kernel.queue.QueueFactory;
import com.crm.provisioning.cache.MQConnection;
import com.crm.thread.DispatcherInstance;

public class MOListenerInstance extends DispatcherInstance
{
	public MOListenerInstance() throws Exception
	{
		super();
	}

	public MOListenerThread getDispatcher()
	{
		return (MOListenerThread) super.getDispatcher();
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

//			if (getDispatcher().queueDispatcherEnable)
//			{
				Message message = QueueFactory.createObjectMessage(
						session, request);

				producer.send(message);
//			}
//			else
//			{
//				QueueFactory.attachLocal(getDispatcher().queueName, request);
//			}
			
			if (dispatcher.displayDebug)
			{
				debugMonitor(debugContent);
			}
		}
		catch (Exception e)
		{
			throw e;
		}

		return Constants.BIND_ACTION_SUCCESS;
	}
	
	public void doProcessSession() throws Exception
	{
		MQConnection mqConnection = null;
		QueueSession queueSession = null;
		MessageProducer producer = null;
		Queue sendQueue = null;

		try
		{
			if (dispatcher.queueDispatcherEnable)
			{
				mqConnection = getMQConnection();
				sendQueue = QueueFactory.getQueue(getDispatcher().queueName);
				queueSession = mqConnection.createSession();
				producer = QueueFactory.createQueueProducer(queueSession,
						sendQueue, getDispatcher().timeout,
						getDispatcher().queuePersistent);
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
						int action = processMessage(queueSession, producer, request);
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
			QueueFactory.closeQueue(queueSession);
		}
	}
}
