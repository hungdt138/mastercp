/**
 * 
 */
package com.crm.kernel.message;

import java.io.Serializable;
import java.util.Date;
import java.util.logging.Level;

import com.crm.kernel.index.BaseMessage;

/**
 * @author ThangPV
 * 
 */
public class MonitorMessage extends BaseMessage implements Serializable
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	private String				host				= "";
	private int					port				= 0;
	private String				threadId			= "";
	private String				logFile				= "";
	private String				logClass			= "";

	private Date				logTime				= new Date();
	private Level				logLevel			= Level.INFO;
	private boolean				sendToUser			= false;
	private String				content				= "";
	private Exception			exception			= null;

	private Date				sendTime			= null;
	private int					status				= 0;
	private Date				statusDate			= null;

	private StringBuilder		buffer				= new StringBuilder();

	public MonitorMessage()
	{

	}

	public MonitorMessage(String threadId, String logFile, String logClass)
	{
		this();

		setThreadId(threadId);
		setLogFile(logFile);
		setLogClass(logClass);
	}

	public void setContent(String content, Exception exception, boolean sendToUser, Level logLevel)
	{
		logTime = new Date();

		setContent(content);
		setException(exception);
		setSendToUser(sendToUser);
		setLogLevel(logLevel);
	}

	public String getHost()
	{
		return host;
	}

	public void setHost(String host)
	{
		this.host = host;
	}

	public int getPort()
	{
		return port;
	}

	public void setPort(int port)
	{
		this.port = port;
	}

	public String getThreadId()
	{
		return threadId;
	}

	public void setThreadId(String threadId)
	{
		this.threadId = threadId;
	}

	public String getLogFile()
	{
		return logFile;
	}

	public void setLogFile(String logFile)
	{
		this.logFile = logFile;
	}

	public String getLogClass()
	{
		return logClass;
	}

	public void setLogClass(String logClass)
	{
		this.logClass = logClass;
	}

	public Date getLogTime()
	{
		return logTime;
	}

	public void setLogTime(Date logTime)
	{
		this.logTime = logTime;
	}

	public Level getLogLevel()
	{
		return logLevel;
	}

	public void setLogLevel(Level logLevel)
	{
		this.logLevel = logLevel;
	}

	public boolean isSendToUser()
	{
		return sendToUser;
	}

	public void setSendToUser(boolean sendToUser)
	{
		this.sendToUser = sendToUser;
	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	public Exception getException()
	{
		return exception;
	}

	public void setException(Exception exception)
	{
		this.exception = exception;
	}

	public Date getSendTime()
	{
		return sendTime;
	}

	public void setSendTime(Date sendTime)
	{
		this.sendTime = sendTime;
	}

	public int getStatus()
	{
		return status;
	}

	public void setStatus(int status)
	{
		this.status = status;
	}

	public Date getStatusDate()
	{
		return statusDate;
	}

	public void setStatusDate(Date statusDate)
	{
		this.statusDate = statusDate;
	}

	public StringBuilder getBuffer()
	{
		return buffer;
	}

	public void setBuffer(StringBuilder buffer)
	{
		this.buffer = buffer;
	}
}
