/**
 * 
 */
package com.gateway.security;

import java.util.Date;

import org.apache.log4j.Logger;

import com.crm.product.cache.ProductCache;
import com.crm.util.DateUtil;

/**
 * @author ThangPV
 * 
 */
public class AgentFactory
{
	private static AgentCache	cache		= null;
	private static Date			cacheDate	= null;

	public AgentFactory()
	{
		super();
	}

	public synchronized static void clear() throws Exception
	{
		if (cache != null)
		{
			cache.clear();
		}
		
		cacheDate	= null;
	}
	
	public synchronized static AgentCache loadCache(Date date) throws Exception
	{
		try
		{
			date = DateUtil.trunc(date);

			log.debug("Caching product information for date: " + date);

			if (cache != null)
			{
				cache.clear();
			}
			else
			{
				cache = new AgentCache();
			}
			cache.loadCache();

			cacheDate = date;

			log.debug("Cached product information for date: " + cacheDate);
		}
		catch (Exception e)
		{
			cache = null;
			cacheDate = null;

			throw e;
		}

		return cache;
	}

	public synchronized static AgentCache getCache(Date date) throws Exception
	{
		boolean reload = true;

		try
		{
			date = DateUtil.trunc(date);

			if (cache == null)
			{
				cache = new AgentCache();
			}
			else if ((cacheDate == null) || !cacheDate.equals(date))
			{
				cache.clear();
			}
			else
			{
				reload = false;
			}

			if (reload)
			{
				log.debug("Caching product information for date: " + date);

				cache.loadCache();

				cacheDate = date;

				log.debug("Cached product information for date: " + cacheDate);
			}
		}
		catch (Exception e)
		{
			cache = null;
			cacheDate = null;

			throw e;
		}

		return cache;
	}

	public static AgentCache getCache() throws Exception
	{
		return getCache(new Date());
	}

	private static Logger	log	= Logger.getLogger(AgentFactory.class);

}
