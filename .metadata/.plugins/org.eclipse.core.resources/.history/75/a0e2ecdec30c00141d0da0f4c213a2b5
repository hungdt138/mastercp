package com.crm.provisioning.thread;

import java.util.Vector;

import com.crm.kernel.queue.QueueFactory;
import com.crm.provisioning.thread.mo.MOServerListener;
import com.crm.thread.DispatcherThread;
import com.crm.thread.util.ThreadUtil;
import com.fss.util.AppException;

public class MOListenerThread extends DispatcherThread
{
	public int				listenPort	= 5000;
	public int				backLogSize	= 1000;
	public int				numThreads	= 50;
	public int				timeout		= 60000;
	public MOServerListener	moServer	= null;

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Vector getDispatcherDefinition()
	{
		Vector vtReturn = new Vector();

		vtReturn.addElement(ThreadUtil.createIntegerParameter("listenPort", ""));
		vtReturn.addElement(ThreadUtil.createIntegerParameter("backLogSize", "Backlog size."));
		vtReturn.addElement(ThreadUtil.createIntegerParameter("numThreads", "Number of event loop group"));
		vtReturn.addElement(ThreadUtil.createIntegerParameter("timeout", "time to live of result before expired (ms)."));

		vtReturn.addAll(ThreadUtil.createQueueParameter(this));
		vtReturn.addAll(ThreadUtil.createInstanceParameter(this));
		vtReturn.addAll(ThreadUtil.createLogParameter(this));

		return vtReturn;
	}

	@Override
	public void fillParameter() throws AppException
	{
		try
		{
			super.fillParameter();

			listenPort = ThreadUtil.getInt(this, "listenPort", 5000);
			backLogSize = ThreadUtil.getInt(this, "backLogSize", 1000);
			numThreads = ThreadUtil.getInt(this, "numThreads", 50);
			timeout = ThreadUtil.getInt(this, "timeout", 60000);
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

	@Override
	public void beforeProcessSession() throws Exception
	{
		super.beforeProcessSession();

		moServer = new MOServerListener(this, listenPort, backLogSize, numThreads);
		moServer.start();

	}

	@Override
	public void afterProcessSession() throws Exception
	{
		try
		{
			if (moServer != null)
			{
				moServer.shutdown();
			}
		}
		finally
		{
			super.afterProcessSession();
		}
	}
}
