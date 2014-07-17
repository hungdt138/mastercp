package com.crm.cgw.thread.tcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.crm.util.GeneratorSeq;

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

public class TCPServer implements Runnable
{
	protected TCPThread		dispatcher	= null;

	protected ServerSocket	server		= null;

	protected int			port		= 5000;

	protected Thread		thread		= null;

	public TCPServer(ServerSocket server, TCPThread dispatcher)
	{
		this.dispatcher = dispatcher;
		this.server = server;
	}

	// //////////////////////////////////////////////////////
	/**
	 * Start thread
	 * 
	 * @author Phan Viet Thang
	 */
	// //////////////////////////////////////////////////////
	public void start()
	{
		// Destroy previous if it's constructed
		destroy();

		// Start new thread
		Thread thread = new Thread(this);

		thread.start();
	}

	// //////////////////////////////////////////////////////
	/**
	 * Start thread
	 * 
	 * @author Phan Viet Thang
	 */
	// //////////////////////////////////////////////////////
	public void stop()
	{
		destroy();
	}

	// //////////////////////////////////////////////////////
	/**
	 * Destroy thread
	 * 
	 * @author Phan Viet Thang
	 */
	// //////////////////////////////////////////////////////
	public void destroy()
	{
		try
		{
			Thread.sleep(100);

			if ((thread != null) && !thread.isInterrupted())
			{
				Thread tmpThread = thread;
				thread = null;

				if (tmpThread != null)
				{
					tmpThread.interrupt();
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void run()
	{
		int sessionId = 0;

		try
		{
			while (!server.isClosed() && ((dispatcher == null) || dispatcher.isAvailable()))
			{
				// The server do a loop here to accept all connection initiated
				// by the client application.

				try
				{
					Socket socket = server.accept();

					sessionId = GeneratorSeq.getNextSeq();
					
					socket.setSoLinger(true, 1);
					socket.setTcpNoDelay(true);
					socket.setReceiveBufferSize(1* 1024 * 1024);
					socket.setSendBufferSize(1* 1024 * 1024);

					TCPHandler handler = new TCPHandler(dispatcher, socket, sessionId, dispatcher.getDelayTime());
					
					dispatcher.handlers.put(sessionId, handler);
				}
				catch (Exception e)
				{
					e.printStackTrace();
					
					throw e;
				}
				finally
				{
					Thread.sleep(10);
				}
			}
		}
		catch (Exception e)
		{
			dispatcher.logMonitor(e);
		}
		finally
		{
			if ((server != null) && server.isClosed())
			{
				try
				{
					server.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
	}
}
