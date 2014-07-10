/**
 * 
 */
package com.crm.provisioning.thread;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.crm.kernel.index.BinaryIndex;
import com.crm.kernel.message.Constants;
import com.crm.kernel.sql.Database;
import com.crm.provisioning.cache.ProvisioningRoute;
import com.crm.thread.DatasourceThread;

import com.fss.util.AppException;

/**
 * @author ThangPV
 * 
 */
public class CommandRoutingThread extends DatasourceThread
{
	public BinaryIndex	routes		= new BinaryIndex();

	public CommandRoutingThread()
	{
		super();
	}

	public void loadCache() throws Exception
	{
		Connection connection = null;
		PreparedStatement stmtConfig = null;
		ResultSet rsConfig = null;

		try
		{
			getLog().debug("Loading route table ...");

			routes.clear();

			connection = Database.getConnection();

			// //////////////////////////////////////////////////////
			// Connection parameters
			// //////////////////////////////////////////////////////
			String SQL = "Select * From	ProvisioningRoute Order by provisioningType desc, routeType desc "
					+ ", decode(substr(routeKey,length(routeKey)), '%', 0, 1) desc, routeKey desc";

			stmtConfig = connection.prepareStatement(SQL);
			rsConfig = stmtConfig.executeQuery();

			while (rsConfig.next())
			{
				ProvisioningRoute entry = new ProvisioningRoute();

				entry.setProvisioningType(Database.getString(rsConfig, "provisioningType"));
				entry.setRouteType(Database.getString(rsConfig, "routeType"));
				entry.setIndexKey(Database.getString(rsConfig, "routeKey"));

				entry.setProvisioningId(rsConfig.getLong("provisioningId"));

				routes.add(entry);
			}

			Database.closeObject(rsConfig);
			Database.closeObject(stmtConfig);

			getLog().debug("Routing table are loaded");
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			Database.closeObject(rsConfig);
			Database.closeObject(stmtConfig);

			Database.closeObject(connection);
		}
	}

	public ProvisioningRoute getRoute(String provisioningType, String routeType, String routeKey) throws Exception
	{
		ProvisioningRoute lookup = new ProvisioningRoute();

		lookup.setProvisioningType(provisioningType);
		lookup.setRouteType(routeType);
		lookup.setRouteKey(routeKey);

		lookup = (ProvisioningRoute) routes.get(lookup);

		if (lookup == null)
		{
			debugMonitor("getRoute fail!!");
			throw new AppException(Constants.ERROR_ROUTE_NOT_FOUND);
		}

		return lookup;
	}
}
