package com.crm.util;

import javax.naming.NamingException;

import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.log4j.Logger;
import com.crm.provisioning.cache.ObjectPool;

public class MQConnectionPool extends ObjectPool
{
	private int					maxActive		= 10;
	private long				maxWait			= 10;
	private int					maxIdle			= 10;
	private long				poolId	= 0L;

	public MQConnectionPool(int maxActive, int maxWait, int maxIdle)
	{
		this.maxActive = maxActive;
		this.maxWait = maxWait;
		this.maxIdle = maxIdle;
	}

	@Override
	public void debugMonitor(Object message)
	{
	}

	@Override
	public GenericObjectPool initPool() throws Exception
	{
		poolId = System.currentTimeMillis();
		
		/**
		 * This pool must be FIFO (LIFO = false) be cause in case of connection has consumer, 
		 * all connection must be get to read in sequence to detach message from consumer batch.
		 */
		GenericObjectPool objectPool = new GenericObjectPool(this, maxActive, GenericObjectPool.WHEN_EXHAUSTED_BLOCK
				, maxWait, maxIdle, maxIdle, true, true, 0, 0, 0, false, 0, false);
		return objectPool;
	}

	@Override
	protected Object createObject() throws Exception
	{
		try
		{
			return new MQConnection(poolId);
		}
		catch (NamingException ne)
		{
			WSQueueFactory.resetContext();

			throw ne;
		}
	}

	public MQConnection getConnection() throws Exception
	{
		return (MQConnection) getObject();
	}

	public void returnConnection(MQConnection connection)
	{
		if (connection == null)
			return;
		
		/**
		 * Destroy connection on return if this is old pool connection (in case closed then re-opened pool)
		 */
		if (connection.getPoolId() != poolId)
		{
			try
			{
				destroyObject(connection);
			}
			catch (Exception e)
			{
				debugMonitor(e);
			}
		}
		else
			returnObject(connection);
	}
}
