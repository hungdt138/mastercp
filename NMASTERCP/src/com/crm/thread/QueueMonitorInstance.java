/**
 * 
 */
package com.crm.thread;

import java.util.Enumeration;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;

import com.crm.kernel.message.Constants;
import com.crm.kernel.message.MonitorMessage;
import com.crm.kernel.queue.QueueFactory;
import com.crm.thread.DispatcherInstance;
import com.crm.thread.util.ThreadUtil;

/**
 * @author ThangPV
 * 
 */
public class QueueMonitorInstance extends DispatcherInstance
{
	public QueueMonitorInstance() throws Exception
	{
		super();
	}

	// //////////////////////////////////////////////////////
	// process session
	// Author : ThangPV
	// Created Date : 16/09/2004
	// //////////////////////////////////////////////////////
	public Object detachMessage() throws Exception
	{
		try
		{
			return QueueFactory.detachLocal(QueueFactory.MONITOR_QUEUE);
		}
		catch (Exception e)
		{
			throw e;
		}
	}

	// //////////////////////////////////////////////////////
	// process session
	// Author : ThangPV
	// Created Date : 16/09/2004
	// //////////////////////////////////////////////////////
	public void idle() throws Exception
	{
		Thread.sleep(1);
	}

	public int processMessage(Object message) throws Exception
	{
		if ((message == null) || !(message instanceof MonitorMessage))
		{
			return Constants.BIND_ACTION_NONE;
		}

		return processMessage((MonitorMessage) message);
		// return Constants.BIND_ACTION_NONE;
	}

	public int processMessage(MonitorMessage message) throws Exception
	{
		String content = message.getContent();
		
		if (content.equals(""))
		{
			return Constants.BIND_ACTION_NONE;
		}

		if (message.isSendToUser())
		{
			// dispatcher.logToUser(message.getContent());
			MonitorMessage entry = ThreadUtil.threadLogs.get(message.getThreadId());

			if (entry == null)
			{
				entry = message;
				
				ThreadUtil.threadLogs.put(message.getThreadId(), entry);
			}
			
			synchronized (entry)
			{
				entry.getBuffer().append(message.getContent());
			}			
		}

		if (!message.getLogClass().equals(""))
		{
			Logger log = Logger.getLogger(message.getLogClass());

			if (log != null)
			{
				if (error != null)
				{
					log.error(content, error);
				}
				else if (log.isDebugEnabled())
				{
					log.debug(content);
				}
			}
		}

		return Constants.BIND_ACTION_SUCCESS;
	}

	// //////////////////////////////////////////////////////
	// after process session
	// Author : ThangPV
	// Created Date : 16/09/2004
	// //////////////////////////////////////////////////////
	public void afterProcessSession() throws Exception
	{
		super.afterProcessSession();

		try
		{
			Enumeration<?> allAppenders = Logger.getRootLogger().getAllAppenders();

			while (allAppenders.hasMoreElements())
			{
				Object nextElement = allAppenders.nextElement();

				if (nextElement instanceof FileAppender)
				{
					FileAppender fileAppender = (FileAppender) nextElement;

					if (!fileAppender.getImmediateFlush())
					{
						fileAppender.setImmediateFlush(true);

						fileAppender.setImmediateFlush(false);
					}
				}
			}
		}
		catch (RuntimeException e)
		{
			e.printStackTrace();
		}
	}

}
