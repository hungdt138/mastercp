/**
 * 
 */
package com.crm.provisioning.thread;

import javax.jms.Message;

import com.crm.kernel.message.Constants;
import com.crm.kernel.queue.QueueFactory;
import com.crm.provisioning.cache.CommandEntry;
import com.crm.provisioning.cache.MQConnection;
import com.crm.provisioning.cache.ProvisioningEntry;
import com.crm.provisioning.cache.ProvisioningFactory;
import com.crm.provisioning.cache.ProvisioningRoute;
import com.crm.provisioning.message.CommandMessage;
import com.crm.thread.DatasourceInstance;

import com.fss.util.AppException;

/**
 * @author ThangPV
 * 
 */
public class CommandRoutingInstance extends DatasourceInstance
{
	public CommandRoutingInstance() throws Exception
	{
		super();
	}

	public int processMessage(Message message) throws Exception
	{
		CommandMessage request = (CommandMessage) QueueFactory.getContentMessage(message);

		Exception error = null;
		ProvisioningEntry provisioning = null;
		CommandEntry command = null;
		try
		{
			
			ProvisioningRoute route =
					((CommandRoutingThread) dispatcher).getRoute(request.getProvisioningType(), "ISDN", request.getIsdn());

			if (route == null)
			{
				debugMonitor("processMessage faill");
				throw new AppException(Constants.ERROR_ROUTE_NOT_FOUND);
			}

			// forward request to related provisioning queue
			request.setProvisioningId(route.getProvisioningId());

			provisioning = ProvisioningFactory.getCache().getProvisioning(route.getProvisioningId());
			command = ProvisioningFactory.getCache().getCommand(request.getCommandId());
			if (provisioning == null)
			{
				throw new AppException(Constants.ERROR_PROVISIONING_NOT_FOUND);
			}
			else
			{
				String queueName = provisioning.getQueueName();

				if (queueName.equals(""))
				{
					queueName = command.getParameter("queue", "");
				}
				
				if (queueName.equals(""))
				{
					queueName = ((CommandRoutingThread) dispatcher).queuePrefix + "/" + provisioning.getIndexKey();
				}

				MQConnection connection = null;
				try
				{
					connection = getMQConnection();
					connection.sendMessage(request, queueName, 0);
				}
				finally
				{
					returnMQConnection(connection);
				}
			}
		}
		catch (Exception e)
		{
			debugMonitor(e);
			error = e;
		}

		if (error != null)
		{
			request.setStatus(Constants.ORDER_STATUS_DENIED);

			if (error instanceof AppException)
			{
				request.setCause(error.getMessage());
				request.setDescription(((AppException) error).getContext());

				logMonitor(request);
			}
			else
			{
				request.setCause(Constants.ERROR);
				request.setDescription(error.getMessage());

				logMonitor(request);

				throw error;
			}
		}
		else
		{
			/**
			 * Add log ISDN: PROVISIONING_ALIAS - COMMAND_ALIAS<br>
			 * NamTA<br>
			 * 21/08/2012
			 */
			if (provisioning != null & command != null)
				debugMonitor(request.getIsdn() + ": " + provisioning.getAlias() + " - " + command.getAlias());

			// debugMonitor(request.toShortString());
		}

		return Constants.BIND_ACTION_SUCCESS;
	}
}
