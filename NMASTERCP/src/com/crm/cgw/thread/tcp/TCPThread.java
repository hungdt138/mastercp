package com.crm.cgw.thread.tcp;

import java.net.ServerSocket;
import java.util.Iterator;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

import com.crm.thread.DispatcherThread;
import com.crm.thread.util.ThreadUtil;

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

public class TCPThread extends DispatcherThread
{
	public ServerSocket								server				= null;

	public int										listenPort			= 3000;
	public int										backLogSize			= 100;
	public int										socketBufferSize	= 65536;

	public int										maxPendingSize		= 100;
	public int										orderTimeout		= 60000;
	public String									keywordPrefix		= "";

	public ConcurrentHashMap<Integer, TCPHandler>	handlers			= new ConcurrentHashMap<Integer, TCPHandler>();

	// //////////////////////////////////////////////////////
	// Override
	// //////////////////////////////////////////////////////
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Vector getDispatcherDefinition()
	{
		Vector vtReturn = new Vector();

		vtReturn.addElement(ThreadUtil.createIntegerParameter("listenPort", "Listen port."));
		vtReturn.addElement(
				ThreadUtil.createIntegerParameter("maxConnection", "Maximum connection the server can handle."));
		vtReturn.add(
				ThreadUtil.createIntegerParameter("socketBufferSize", "Socket buffer size, in KB. Default 1500KB."));

		vtReturn.addElement(
				ThreadUtil.createIntegerParameter("maxPendingSize", "max pending request in local queue, default is 100"));
		vtReturn.addElement(ThreadUtil.createTextParameter("keywordPrefix", 400, "keywordPrefix"));
		vtReturn.addElement(ThreadUtil.createIntegerParameter("orderTimeout", "request timeout in milisecond"));

		vtReturn.addAll(ThreadUtil.createInstanceParameter(this));
		vtReturn.addAll(ThreadUtil.createLogParameter(this));

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

			listenPort = ThreadUtil.getInt(this, "listenPort", 3000);
			backLogSize = ThreadUtil.getInt(this, "backLogSize", 1000);
			socketBufferSize = ThreadUtil.getInt(this, "socketBufferLength", 1500) * 1024;

			maxPendingSize = ThreadUtil.getInt(this, "maxPendingSize", 100);
			orderTimeout = ThreadUtil.getInt(this, "orderTimeout", 120000);
			keywordPrefix = ThreadUtil.getString(this, "keywordPrefix", true, "");
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
	}

	// //////////////////////////////////////////////////////
	// after process session
	// Author : ThangPV
	// Created Date : 16/09/2004
	// //////////////////////////////////////////////////////
	public void afterProcessSession() throws Exception
	{
		try
		{
			if (server != null)
			{
				try
				{
					server.close();
				}
				catch (Exception e)
				{
					logMonitor("close server error: " + e.getMessage());
				}
				finally
				{
					server = null;
				}
			}

			Iterator<Integer> sessionIds = handlers.keySet().iterator();

			while (sessionIds.hasNext())
			{
				try
				{
					TCPHandler handler = handlers.get(sessionIds.next());

					if (handler != null)
					{
						handler.stop(1000);
					}
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}

			handlers.clear();
		}
		finally
		{
			super.afterProcessSession();
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
			checkInstance();

			if ((server == null) || server.isClosed())
			{
				server = new ServerSocket(listenPort, backLogSize);
			}

			TCPServer callbackServer = new TCPServer(server, this);

			callbackServer.start();

			while (isAvailable())
			{
				Thread.sleep(1000);
			}
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}
