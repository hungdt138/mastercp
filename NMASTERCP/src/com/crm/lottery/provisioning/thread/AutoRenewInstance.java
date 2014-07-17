package com.crm.lottery.provisioning.thread;

import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.QueueSession;

import com.crm.kernel.message.Constants;
import com.crm.kernel.queue.QueueFactory;
import com.crm.provisioning.message.CommandMessage;
import com.crm.thread.DispatcherInstance;

public class AutoRenewInstance extends DispatcherInstance
{

	public AutoRenewInstance() throws Exception
	{
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public AutoRenewThread getDispatcher()
	{
		// TODO Auto-generated method stub
		return (AutoRenewThread) super.getDispatcher();
	}

	public int processMessage(QueueSession session, MessageProducer producer, Object request) throws Exception
	{
		if (request == null)
		{
			return Constants.BIND_ACTION_NONE;
		}

		if (request instanceof CommandMessage)
		{
			CommandMessage msg = (CommandMessage) request;
			try
			{
				String debugContent = "SEND SUBS: ISDN=" + msg.getIsdn() + ",ADDR=" + msg.getServiceAddress()
						+ ",KEY=" + msg.getKeyword() + ",CODE=" + msg.getRequestValue("lottery.regionCode", "");
				debugContent = dispatcher.displayDebug ? debugContent : "";

				if (getDispatcher().queueDispatcherEnable)
				{
					Message message = QueueFactory.createObjectMessage(session, request);

					producer.send(message);
				}
				else
				{
					QueueFactory.attachLocal(getDispatcher().queueName, request);
				}
				

				long subsId = Long.parseLong(msg.getRequestValue("lottery.subsId", "0"));
				getDispatcher().updateSubs(subsId);

				if (dispatcher.displayDebug)
				{
					debugMonitor(debugContent);
				}
			}
			catch (Exception e)
			{
				throw e;
			}
		}
		return Constants.BIND_ACTION_SUCCESS;
	}

	@Override
	public void doProcessSession() throws Exception
	{
		Queue sendQueue = null;
		QueueSession session = null;
		MessageProducer producer = null;

		try
		{
			if (dispatcher.queueDispatcherEnable)
			{
				sendQueue = QueueFactory.getQueue(getDispatcher().queueName);
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
						processMessage(session, producer, request);
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
