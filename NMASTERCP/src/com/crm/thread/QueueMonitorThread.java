package com.crm.thread;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.RandomAccessFile;
import java.text.NumberFormat;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

import javax.jms.Queue;
import javax.jms.QueueSession;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.crm.kernel.message.AlarmMessage;
import com.crm.kernel.message.MonitorMessage;
import com.crm.kernel.queue.LocalQueue;
import com.crm.kernel.queue.QueueFactory;
import com.crm.kernel.sql.Database;
import com.crm.thread.config.ThreadConstant;
import com.crm.thread.util.ThreadUtil;

import com.fss.ddtp.DDTP;
import com.fss.thread.ManageableThread;
import com.fss.thread.ThreadManager;
import com.fss.util.AppException;
import com.fss.util.FileUtil;
import com.fss.util.StringUtil;

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

public class QueueMonitorThread extends DispatcherThread
{
	protected String[]		externalQueues		= new String[0];

	protected String		applicationName		= "";
	protected String		monitorURL			= "";	
	protected int			maxSize				= 10000;
	protected int			warningSize			= 1000;
	protected String		warningDiskPath		= "";
	protected int			warningDiskPercent	= 10;

	protected int			sendLogInterval		= 1000;
	protected int			sysInfoInterval		= 1000;

	protected long			lastSend			= System.currentTimeMillis();
	protected long			lastSystemInfo		= System.currentTimeMillis();

	protected StringBuilder	systemDump			= new StringBuilder();
	public CloseableHttpClient	httpClient		= HttpClients.createDefault();

	// //////////////////////////////////////////////////////
	// Override
	// //////////////////////////////////////////////////////
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Vector getDispatcherDefinition()
	{
		Vector vtReturn = new Vector();
		
		vtReturn.addElement(ThreadUtil.createTextParameter("appName", 200, "Application name."));
		vtReturn.addElement(ThreadUtil.createTextParameter("monitorURL", 400, "Monitor URL."));

		// data source connection
		vtReturn.addElement(ThreadUtil.createIntegerParameter("queueMaxPending", ""));

		if (QueueFactory.queueServerEnable)
		{
			vtReturn.addElement(
					ThreadUtil.createBooleanParameter(
							"queueDispatcherEnable", "init queue connection when start dispatcher"));
			vtReturn.addElement(ThreadUtil.createTextParameter("queuePrefix", 100, ""));
			vtReturn.addElement(
					ThreadUtil.createTextParameter("queueList", 4000, "list of external queue"));
		}
		vtReturn.addElement(ThreadUtil.createTextParameter("queueLocalName", 100, "jndi queue name"));
		vtReturn.addElement(ThreadUtil.createIntegerParameter("queueLocalSize", "Max local queue size"));

		vtReturn.addElement(
				ThreadUtil.createIntegerParameter("warningSize", "send alert when queue size is over this value"));
		vtReturn.addElement(
				ThreadUtil.createIntegerParameter("maxSize", "send alert when queue size is over this value"));

		vtReturn.addElement(
				ThreadUtil.createTextParameter("diskPath", 4000, "Path of warning disk"));
		vtReturn.addElement(
				ThreadUtil.createIntegerParameter(
						"warningDiskPercent", "Percent of in used disk space need to warning if reach."));

		vtReturn.addElement(
				ThreadUtil.createIntegerParameter(
						"sendLogInterval", "interval for send log to monitor client (ms)"));
		vtReturn.addElement(
				ThreadUtil.createIntegerParameter(
						"sysInfoInterval", "interval for dummy system status (ms)"));

		vtReturn.addElement(ThreadUtil.createBooleanParameter("alarmEnable", "send to alarm thread"));

		vtReturn.addAll(ThreadUtil.createInstanceParameter(this));
		vtReturn.addAll(ThreadUtil.createLogParameter(this));

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

			neverExpire = true;
			applicationName = ThreadUtil.getString(this, "appName", false, "");
			monitorURL = ThreadUtil.getString(this, "monitorURL", false, "");
			
			sendLogInterval = ThreadUtil.getInt(this, "sendLogInterval", 1000);
			sysInfoInterval = ThreadUtil.getInt(this, "sysInfoInterval", 1000);

			maxSize = ThreadUtil.getInt(this, "maxSize", 10000);
			warningSize = ThreadUtil.getInt(this, "warningSize", 1000);

			externalQueues = StringUtil.toStringArray(ThreadUtil.getString(this, "queueList", false, ""), ";");

			warningDiskPath = ThreadUtil.getString(this, "diskPath", false, "/");
			warningDiskPercent = ThreadUtil.getInt(this, "warningDiskPercent", 10);
		}
		catch (AppException e)
		{
			throw e;
		}
		catch (Exception e)
		{
			throw new AppException(e.getMessage());
		}
	}

	// //////////////////////////////////////////////////////
	/**
	 * Write log to file and show to user (with date in prefix)
	 * 
	 * @param strLog
	 *            Log content
	 * @author Thai Hoang Hiep
	 */
	// //////////////////////////////////////////////////////
	public void logMonitor(String strLog)
	{
		logMonitor(strLog, false);
	}

	// //////////////////////////////////////////////////////
	public void logMonitor(String strLog, boolean bSendMail)
	{
		if (bSendMail)
		{
			alertByMail(strLog);
		}

		final java.text.SimpleDateFormat fmt = new java.text.SimpleDateFormat("dd/MM HH:mm:ss:SSS");
		strLog = fmt.format(new java.util.Date()) + " " + strLog + "\r\n";

		log(strLog);
	}

	// //////////////////////////////////////////////////////
	/**
	 * Write log to file and show to user (without date in prefix)
	 * 
	 * @param strLog
	 *            Log content
	 * @author Thai Hoang Hiep
	 */
	// //////////////////////////////////////////////////////
	public void log(String strLog)
	{
		if (!strLog.endsWith("\n"))
		{
			strLog += "\n";
		}

		logToUser(strLog);
		logToFile(strLog);
	}

	// //////////////////////////////////////////////////////
	// process session
	// Author : ThangPV
	// Created Date : 16/09/2004
	// Modified by NamTA
	// Modified Date 27/08/2012
	// //////////////////////////////////////////////////////
	protected String queueWarning(Queue checkQueue, String name, int size) throws Exception
	{
		if (size > warningSize)
		{
			String queueWarning = "";

			if (size >= maxSize)
			{
				queueWarning = "FATAL: queue " + name + " is reach to limitation (" + size + "/" + maxSize + ")\r\n";
			}
			else
			{
				queueWarning = "WARNING: queue " + name + " may be reach to limitation (" + size + "/" + maxSize + ")\r\n";
			}

			logMonitor(queueWarning);
			// alarmMessage += warningMessage;

			return queueWarning;
		}
		return "";
	}

	// //////////////////////////////////////////////////////
	// process session
	// Author : ThangPV
	// Created Date : 16/09/2004
	// Modified by NamTA
	// Modified Date 27/08/2012
	// //////////////////////////////////////////////////////
	protected boolean memoryLogging() throws Exception
	{
		Runtime runtime = Runtime.getRuntime();

		// write log
		NumberFormat format = NumberFormat.getInstance();

		long maxMemory = runtime.maxMemory();
		long allocatedMemory = runtime.totalMemory();
		long freeMemory = runtime.freeMemory();

		File file = new File(warningDiskPath);

		long totalDiskSize = file.getTotalSpace();
		long usableDiskSize = file.getUsableSpace();
		long usedDiskSize = totalDiskSize - usableDiskSize;
		long percentUsedDisk = 100 * (usedDiskSize) / totalDiskSize;

		systemDump.append("\r\n");
		systemDump.append("Memory information:\r\n");
		systemDump.append("\t :: Free memory            : ");
		systemDump.append(format.format(freeMemory / 1024 / 1024));
		systemDump.append(" MB\r\n");
		systemDump.append("\t :: Allocated memory       : ");
		systemDump.append(format.format(allocatedMemory / 1024 / 1024));
		systemDump.append(" MB\r\n");
		systemDump.append("\t :: Max memory             : ");
		systemDump.append(format.format(maxMemory / 1024 / 1024));
		systemDump.append(" MB\r\n");
		systemDump.append("\t :: Total free memory      : ");
		systemDump.append(format.format((freeMemory + (maxMemory - allocatedMemory)) / 1024 / 1024));
		systemDump.append(" MB\r\n");
		systemDump.append("\t :: Total free memory      : ");
		systemDump.append(format.format((100 * (freeMemory + (maxMemory - allocatedMemory)) / maxMemory)));
		systemDump.append(" (%)\r\n");
		systemDump.append("\t :: Disk in used           : ");
		systemDump.append(format.format(usedDiskSize / 1024 / 1024));
		systemDump.append("/");
		systemDump.append(format.format(totalDiskSize / 1024 / 1024));
		systemDump.append(" MB, Used ");
		systemDump.append(format.format(percentUsedDisk));
		systemDump.append(" (%) (");
		systemDump.append(warningDiskPath);
		systemDump.append(")\r\n");
		systemDump.append("\t :: Total running thread   : ");
		systemDump.append(Thread.activeCount());
		systemDump.append("\r\n");

		return (percentUsedDisk >= warningDiskPercent);
	}

	// //////////////////////////////////////////////////////
	// process session
	// Author : ThangPV
	// Created Date : 16/09/2004
	// Modified by NamTA
	// Modified Date 27/08/2012
	// //////////////////////////////////////////////////////
	public void getSystemStatus() throws Exception
	{
		systemDump.delete(0, systemDump.length());

		boolean needAlarm = memoryLogging();

		if (Database.appDatasource != null)
		{
			systemDump.append("\r\n");
			systemDump.append("Database connection status: \r\n");
			systemDump.append("\t Number database connection\t\t\t: ");
			systemDump.append(Database.appDatasource.getNumConnections());
			systemDump.append("\r\n");
			systemDump.append("\t Number busy database connection\t: ");
			systemDump.append(Database.appDatasource.getNumBusyConnections());
			systemDump.append("\r\n");
			systemDump.append("\t Number idle database connection\t: ");
			systemDump.append(Database.appDatasource.getNumIdleConnections());
			systemDump.append("\r\n");
			systemDump.append("\t Number idle database connection\t: ");
			systemDump.append(Database.appDatasource.getNumUnclosedOrphanedConnections());
			systemDump.append("\r\n");
		}

		String queueWarningMessage = "";

		systemDump.append("\r\n");
		systemDump.append("Local queue status: \r\n");

		for (String key : QueueFactory.localQueues.keySet())
		{
			LocalQueue localQueue = QueueFactory.getLocalQueue(key);

			systemDump.append("Local queue (");
			systemDump.append(key);
			systemDump.append("): ");
			systemDump.append(localQueue.getSize());

			if (localQueue.getMaxSize() > 0)
			{
				systemDump.append("/");
				systemDump.append(localQueue.getMaxSize());
			}

			systemDump.append("\r\n");
		}
		if (QueueFactory.getTotalLocalPending() > 0)
		{
			systemDump.append("Total pending counter : ");
			systemDump.append(QueueFactory.getTotalLocalPending());
			systemDump.append("\r\n");
		}

		if (queueDispatcherEnable)
		{
			systemDump.append("\r\n");
			systemDump.append("Remote queue status: \r\n");

			QueueSession session = null;

			try
			{
				session = getQueueSession();

				for (int j = 0; j < externalQueues.length; j++)
				{
					if (externalQueues[j].equals(""))
					{
						continue;
					}

					String queueName = externalQueues[j];

					try
					{
						Queue checkQueue = QueueFactory.getQueue(queueName);

						int size = QueueFactory.getQueueSize(session, checkQueue);

						QueueFactory.queueSnapshot.put(queueName, new Integer(size));

						systemDump.append("Total command request for ");
						systemDump.append(queueName);
						systemDump.append(" : ");
						systemDump.append(size);
						systemDump.append("\r\n");

						queueWarningMessage += queueWarning(checkQueue, queueName, size);
					}
					catch (Exception e)
					{
						systemDump.append("Error occur when get size of queue ");
						systemDump.append(queueName);
						systemDump.append(": ");
						systemDump.append(e.getMessage());

						logMonitor(e);
					}
				}
			}
			catch (Exception e)
			{
				systemDump.append("Can not get remote queue size: ");
				systemDump.append(e.getMessage());
				systemDump.append("\r\n");

				logMonitor(e);
			}
			finally
			{
				QueueFactory.closeQueue(session);
			}
		}

		if (needAlarm)
		{
			systemDump.append("WARNING: Disk space is running low");
		}
		if (!queueWarningMessage.equals(""))
		{
			needAlarm = true;
			systemDump.append(queueWarningMessage);
		}

		logMonitor(systemDump);

		if (needAlarm)
		{
			AlarmMessage message = new AlarmMessage();

			message.setContent(systemDump.toString());
			message.setDescription("System resource is running low.");
			message.setCause("system-resouce");
			message.setImmediately(true);

			sendAlarmMessage(message);
		}
	}

	public void updateLogFile() throws Exception
	{
		ThreadManager threadManager = getThreadManager();

		Set<String> keys = ThreadUtil.threadLogs.keySet();

		Iterator<String> iterator = keys.iterator();

		while (iterator.hasNext())
		{
			String threadId = iterator.next();

			MonitorMessage entry = ThreadUtil.threadLogs.get(threadId);

			if (entry.getBuffer().length() == 0)
			{
				continue;
			}

			try
			{
				if (entry.getLogFile().length() != 0)
				{
					RandomAccessFile fl = null;

					try
					{
						//fl = new RandomAccessFile(entry.getLogFile()+ StringUtil.format(new java.util.Date(),"yyyyMMdd") + ".log", "rw");
					    String filePath = StringUtil.format(new java.util.Date(),"yyyyMMdd") + ".log";
					    fl = new RandomAccessFile(entry.getLogFile() + filePath, "rw");

						fl.seek(fl.length());
						fl.write(entry.getBuffer().toString().getBytes());
					}
					catch (Exception e)
					{
					}
					finally
					{
						FileUtil.safeClose(fl);
					}
				}

				ManageableThread sendingDispatcher = threadManager.getThread(entry.getThreadId());

				if (sendingDispatcher != null)
				{
					entry.setStatus(sendingDispatcher.getThreadStatus());
				}
				else
				{
					entry.setStatus(ThreadConstant.THREAD_STOPPED);
				}

				String logThreadId = entry.getThreadId();
				String logText = entry.getBuffer().toString();
				String logStatus = String.valueOf(entry.getStatus());
				
				DDTP request = new DDTP();

				request.setString("ThreadID", logThreadId);
				request.setString("LogResult", logText);
				request.setString("ThreadStatus",logStatus);

				try
				{
					threadManager.sendRequestToAll(request, "logMonitor", "MonitorProcessor");
				}
				catch (Exception e)
				{
				}
				finally
				{
					sendToMonitorServer(logThreadId, logText, logStatus);
				}				
			}
			finally
			{
			}

			synchronized (mutex)
			{
				entry.getBuffer().delete(0, entry.getBuffer().length());
			}
		}
	}

	public void doProcessSession() throws Exception
	{
		while (isAvailable())
		{
			checkInstance();

			if ((System.currentTimeMillis() - lastSystemInfo) > sysInfoInterval)
			{
				try
				{
					getSystemStatus();

					lastSystemInfo = System.currentTimeMillis();
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}

			if ((System.currentTimeMillis() - lastSend) > sendLogInterval)
			{
				updateLogFile();

				lastSend = System.currentTimeMillis();
			}

			Thread.sleep(1);
		}
	}
	public void sendToMonitorServer(String threadId, String log, String status)
	{
		if ("".equals(monitorURL))
			return;
		HttpPost post = null;

		try
		{
			String userAgent = applicationName + "," + threadId + "," + status;

			post = new HttpPost(monitorURL);
			post.setHeader("User-Agent", userAgent);
			post.setHeader("Connection", "close");

			ByteArrayInputStream stream = new ByteArrayInputStream(log.getBytes());

			post.setEntity(new InputStreamEntity(stream, log.length()));
			httpClient.execute(post);
		}
		catch (Exception e)
		{
			debugMonitor(e);
		}
		finally
		{
			if (post != null)
				post.releaseConnection();
		}
	}	
}
