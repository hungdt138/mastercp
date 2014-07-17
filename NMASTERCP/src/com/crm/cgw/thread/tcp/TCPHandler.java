package com.crm.cgw.thread.tcp;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;

import com.crm.cgw.submodifytcp.ChangeBalance;
import com.crm.cgw.submodifytcp.ChangeState;
import com.crm.cgw.submodifytcp.Charging;
import com.crm.kernel.message.Constants;
import com.crm.kernel.queue.QueueFactory;
import com.crm.provisioning.message.CommandMessage;
import com.crm.util.AppProperties;

import com.fss.util.AppException;

public class TCPHandler implements Runnable
{
	public static final String	SEPARATE_CHAR		= ",";
	public static final String	DATE_FORMAT			= "dd/MM/yy HH:mm:ss";
	public static final String	TYPE_CHANGE_BALANCE	= "BALANCE";
	public static final String	TYPE_CHANGE_STATE	= "STATE";

	public static final String	accoutStartwith		= "ACCOUNT";

	protected TCPThread			dispatcher			= null;
	protected Socket			socket				= null;
	protected long				sessionId			= 0;
	protected String			lastData			= "";

	private boolean				stopped				= true;
	private Thread				thread				= null;
	private long				sleepTime			= 1000L;

	public TCPHandler(TCPThread dispatcher, Socket socket, long sessionId, long sleepTime)
	{
		this.dispatcher = dispatcher;
		this.socket = socket;
		this.sessionId = sessionId;
		this.sleepTime = sleepTime;

		start();
	}

	public void logMonitor(Object message)
	{
		if (dispatcher != null)
		{
			dispatcher.logMonitor(message);
		}
	}

	public void debugMonitor(Object message)
	{
		if (dispatcher != null)
		{
			dispatcher.debugMonitor(message);
		}
	}

	public TCPThread getDispatcher()
	{
		return dispatcher;
	}

	public InetAddress getAddress()
	{
		return (socket == null) ? null : socket.getInetAddress();
	}

	public int getPort()
	{
		return (socket == null) ? 0 : socket.getPort();
	}

	public boolean isConnected()
	{
		return (socket == null) ? false : socket.isConnected();
	}

	public boolean isRunning()
	{
		return !stopped;
	}

	public void start()
	{
		thread = new Thread(this);
		stopped = false;

		thread.start();

		logMonitor("NetThread #" + thread.getId() + "[" + thread.getName() + "] started.");
	}

	public void stop()
	{
		stopped = true;

		try
		{
			socket.close();
		}
		catch (Exception e)
		{
		}
		finally
		{
			socket = null;
		}

		try
		{
			thread.join();
		}
		catch (InterruptedException e)
		{
			thread.interrupt();
		}

		logMonitor("NetThread #" + thread.getId() + "[" + thread.getName() + "] stopped.");
	}

	public void stop(long timeout)
	{
		stopped = true;

		try
		{
			socket.close();
		}
		catch (Exception e)
		{
		}
		finally
		{
			socket = null;
		}

		try
		{
			thread.join(timeout);
		}
		catch (InterruptedException e)
		{
			thread.interrupt();
		}

		logMonitor("NetThread #" + thread.getId() + "[" + thread.getName() + "] stopped.");
	}

	public void destroy()
	{
		stopped = true;
		thread.interrupt();

		logMonitor("NetThread #" + thread.getId() + "[" + thread.getName() + "] destroyed.");
	}

	@Override
	public void run()
	{
		while (isRunning())
		{
			try
			{
				process();
			}
			catch (Exception e)
			{
				debugMonitor(e);
			}
			finally
			{
				sleep(sleepTime);
			}
		}

	}

	@SuppressWarnings("static-access")
	public void sleep(long millis)
	{
		try
		{
			thread.sleep(millis);
		}
		catch (InterruptedException e)
		{
			thread.interrupt();
		}
	}

	public byte[] getBytes(String hexStr)
	{
		if (hexStr.length() % 2 > 0)
		{
			return null;
		}

		byte[] ret = new byte[hexStr.length() / 2];
		for (int i = 0; i < ret.length; i++)
		{
			String s1 = hexStr.substring(i * 2, i * 2 + 2);
			int k = Integer.parseInt(s1, 16);
			ret[i] = (byte) k;
		}
		return ret;
	}

	public Object createRequest(String strContent) throws Exception
	{
		Charging charging = null;

		if (strContent.indexOf(TYPE_CHANGE_BALANCE) > 0)
		{
			charging = new ChangeBalance();
		}
		else if (strContent.indexOf(TYPE_CHANGE_STATE) > 0)
		{
			charging = new ChangeState();
		}
		else
		{
			throw new AppException(Constants.ERROR_INVALID_PARAMETER);
		}

		charging.setContent(strContent);
		charging.setCharg_seq(sessionId);

		CommandMessage message = new CommandMessage();

		message.setChannel(Constants.CHANNEL_WEB);
		message.setTimeout(getDispatcher().orderTimeout);

		AppProperties parameters = new AppProperties();

		parameters.setLong("sessionId", sessionId);
		parameters.setString("responseQueue", QueueFactory.TCP_RESPONSE_QUEUE);

		if (charging instanceof ChangeBalance)
		{
			parameters.setString("description", ((ChangeBalance) charging).getComment());

			message.setUserName(((ChangeBalance) charging).getAccount());
			message.setSubmodifyBalance(((ChangeBalance) charging).getBalance());
			message.setSubmodifyAmount(((ChangeBalance) charging).getAmount());
			message.setSubmodifyExpireDate(((ChangeBalance) charging).getExpireDate());
			message.setDescription(((ChangeBalance) charging).getComment());
			message.setKeyword(getDispatcher().keywordPrefix + "BALANCE");
			message.setServiceAddress("345");
			message.setIsdn(((ChangeBalance) charging).getMdn());
			message.setRequestId(((ChangeBalance) charging).getId());
		}
		else if (charging instanceof ChangeState)
		{
			parameters.setString("state", ((ChangeState) charging).getState());

			message.setKeyword(getDispatcher().keywordPrefix + "STATE");
			message.setServiceAddress("345");
			message.setDescription(((ChangeState) charging).getComment());
			message.setIsdn(((ChangeState) charging).getMdn());
			message.setRequestId(((ChangeState) charging).getId());
		}

		message.setParameters(parameters);

		return message;
	}

	public void createObject(Object data)
	{
		byte[] bytes = (byte[]) data;
		String receiveData = new String(bytes);
		String hexData = ""; // StringUtil.toHexString(bytes, 0, bytes.length);
		byte[] sufix = getBytes("EFEF");
		String sufixStr = new String(sufix);

		debugMonitor("RECEIVE from #" + sessionId + ": " + hexData + "[ASCII:" + receiveData + "]");

		synchronized (lastData)
		{
			receiveData = lastData + receiveData;

			int startIndex = 0;
			int endIndex = receiveData.indexOf(sufixStr);

			while (endIndex > 0)
			{
				String receive = receiveData.substring(startIndex, endIndex);

				try
				{
					boolean isOverload = false;
				
					Object request = createRequest(receive);

					if (getDispatcher().maxPendingSize > 0)
					{
						int totalPending = QueueFactory.getTotalLocalPending() 
								+ QueueFactory.getTotalRemotePending("cgw/OrderRoute;cgw/OSA;cgw/CCWS");
						
						if (totalPending > getDispatcher().maxPendingSize)
						{
							isOverload = true;
						}
					
					}
					
					if (isOverload)
					{
						String[] split = receive.split(",");

						CommandMessage response = new CommandMessage();

						try
						{
							response.setOrderId(Integer.parseInt(split[0]));
							response.setIsdn(split[2].split("=")[1]);
						}
						catch (Exception ex)
						{

						}

						response.setStatus(Constants.ORDER_STATUS_DENIED);
						response.setCause(Constants.ERROR_RESOURCE_BUSY);

						QueueFactory.attachLocal(QueueFactory.TCP_RESPONSE_QUEUE, response);
					}
					else
					{
						QueueFactory.attachLocal(QueueFactory.ORDER_REQUEST_QUEUE, request);

						debugMonitor(request);
					}
				}
				catch (Exception e)
				{
					logMonitor("Error when parsing request: " + e.getMessage() + ". Detail: \r\n" + receive + "\r\n");

					if (dispatcher != null)
					{
						dispatcher.getLog().error(e, e);
					}

					String[] split = receive.split(",");

					CommandMessage response = new CommandMessage();

					try
					{
						response.setOrderId(Integer.parseInt(split[0]));
						response.setIsdn(split[2].split("=")[1]);
					}
					catch (Exception ex)
					{

					}

					response.setCause(e.toString());
					response.setDescription(e.toString());

					try
					{
						QueueFactory.attachLocal(QueueFactory.TCP_RESPONSE_QUEUE, response);
					}
					catch (Exception e1)
					{
						e1.printStackTrace();
					}
				}

				startIndex = endIndex + sufixStr.length();

				endIndex = receiveData.indexOf(sufixStr, startIndex);
			}

			lastData = receiveData.substring(startIndex);
		}

	}

	public int readInputStream(byte[] receivedData) throws IOException
	{
		try
		{
			return socket.getInputStream().read(receivedData);
		}
		catch (SocketTimeoutException ste)
		{
			return 0;
		}
		catch (SocketException se)
		{
			return -1;
		}
	}

	public void response(byte[] data) throws IOException
	{
		OutputStream os = null;

		synchronized (this)
		{
			os = socket.getOutputStream();

			if (os == null)
			{
				throw new SocketException("Connection reset");
			}
		}

		try
		{
			os.write(data);
			os.flush();
		}
		catch (IOException e)
		{
			throw e;
		}
	}

	public void handle(byte[] data)
	{
		if (data != null)
		{
			createObject(data);
		}
		else
		{
			stop();
		}
	}

	public void process() throws Exception
	{
		byte[] buffer = new byte[0];
		byte[] receivedData = new byte[1024];

		int byteCount = readInputStream(receivedData);

		while (isRunning() && (byteCount > 0))
		{
			byte[] newBuffer = new byte[buffer.length + byteCount];

			System.arraycopy(buffer, 0, newBuffer, 0, buffer.length);
			System.arraycopy(receivedData, 0, newBuffer, buffer.length, byteCount);

			buffer = newBuffer;

			try
			{
				handle(buffer);

				buffer = new byte[0];
			}
			catch (Exception e)
			{
				throw e;
			}

			Thread.sleep(1);

			byteCount = readInputStream(receivedData);
		}

		if (isRunning())
		{
			if (buffer.length != 0)
			{
				try
				{
					handle(buffer);
				}
				catch (Exception e)
				{
					throw e;
				}
			}

			if (byteCount < 0)
			{
				handle(null);
			}
		}
		else
		{
			handle(null);
		}
	}

}
