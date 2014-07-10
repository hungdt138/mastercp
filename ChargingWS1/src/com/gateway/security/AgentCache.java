/**
 * 
 */
package com.gateway.security;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import org.apache.log4j.Logger;

import com.crm.kernel.index.BinaryIndex;
import com.crm.kernel.index.IndexNode;
import com.crm.kernel.message.Constants;
import com.crm.kernel.sql.Database;
import com.crm.util.DateUtil;

import com.fss.util.AppException;

/**
 * @author ThangPV
 * 
 */
public class AgentCache
{
	// cache object
	private BinaryIndex	agents		= new BinaryIndex();

	private Date		cacheDate	= null;

	public synchronized void loadCache() throws Exception
	{
		Connection connection = null;

		try
		{
			clear();

			log.debug("Caching agent ...");

			connection = Database.getConnection();

			loadAgents(connection);

			setCacheDate(DateUtil.trunc());

			log.debug("Agent is cached");
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			Database.closeObject(connection);
		}
	}

	public void clear()
	{
		agents.clear();
	}

	protected void loadAgents(Connection connection) throws Exception
	{
		PreparedStatement stmtConfig = null;
		ResultSet rsConfig = null;

		try
		{
			log.debug("Loading agent rule ...");

			stmtConfig = connection.prepareStatement("Select * From merchantAgent A, merchantEntry B where A.merchantId = B.merchantId  " +
					"Order by agentId desc");
			rsConfig = stmtConfig.executeQuery();

			while (rsConfig.next())
			{
				AgentEntry agent = new AgentEntry(rsConfig.getLong("merchantId"), rsConfig.getLong("agentId"), rsConfig.getString("code"));

				agent.setStatus(rsConfig.getInt("status"));
				agent.setServiceAddress(rsConfig.getString("serviceAddress"));
				agent.setScreenName(rsConfig.getString("screenName"));
				agent.setPassword(rsConfig.getString("password_"));
				agents.add(agent.getAgentId(), agent.getIndexKey(), agent);
				
			}

			log.debug("Agent rule are loaded");
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			Database.closeObject(rsConfig);
			Database.closeObject(stmtConfig);
		}
	}

	public void setCacheDate(Date cacheDate)
	{
		this.cacheDate = DateUtil.trunc(cacheDate);
	}

	public Date getCacheDate()
	{
		return cacheDate;
	}

	public BinaryIndex getAgents()
	{
		return agents;
	}

	public AgentEntry getAgent(long agentId) throws Exception
	{
		if (agentId == Constants.DEFAULT_ID)
		{
			return null;
		}

		IndexNode node = agents.getById(agentId);

		if (node == null)
		{
			throw new AppException(Constants.ERROR_PRODUCT_NOT_FOUND);
		}

		return (AgentEntry) node;
	}

	public AgentEntry getAgent(String alias) throws Exception
	{
		if (alias.equals(""))
		{
			return null;
		}

		AgentEntry result = (AgentEntry) agents.getByKey(alias);

		if (result == null)
		{
			throw new AppException(Constants.ERROR_PRODUCT_NOT_FOUND);
		}

		return result;
	}

	private static Logger	log	= Logger.getLogger(AgentCache.class);

}
