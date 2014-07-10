package com.crm.provisioning.thread;

import java.util.Date;

import javax.jms.JMSException;
import javax.jms.Message;

import com.crm.product.cache.ProductEntry;
import com.crm.product.cache.ProductFactory;
import com.crm.product.cache.ProductRoute;
import com.crm.provisioning.cache.CommandEntry;
import com.crm.provisioning.cache.ProvisioningCommand;
import com.crm.provisioning.cache.ProvisioningEntry;
import com.crm.provisioning.cache.ProvisioningFactory;
import com.crm.provisioning.impl.CommandImpl;
import com.crm.provisioning.message.CommandMessage;
import com.crm.provisioning.util.CommandUtil;
import com.crm.util.StringUtil;
import com.crm.kernel.message.Constants;
import com.crm.kernel.queue.QueueFactory;

import com.fss.util.AppException;
import com.sun.corba.ee.impl.orbutil.closure.Constant;

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

public class CommandInstance extends ProvisioningInstance
{
	public CommandInstance() throws Exception
	{
		super();
	}

	public int processMessage(Message message) throws Exception
	{
		CommandMessage request = (CommandMessage) QueueFactory.getContentMessage(message);

		CommandMessage result = request;

		CommandEntry command = null;

		ProductRoute productRoute = null;

		ProvisioningCommand action = null;

		Exception error = null;

		try
		{
			result.setRequestTime(new Date());
			result.setResponseTime(null);

			productRoute = ProductFactory.getCache().getProductRoute(result.getRouteId());

			if ((productRoute != null) && CommandUtil.isTimeout(result, productRoute.getTimeout()))
			{
				throw new AppException(Constants.ERROR_TIMEOUT);
			}

			ProvisioningEntry provisioning = ProvisioningFactory.getCache().getProvisioning(request.getProvisioningId());

			if (provisioning.getStatus() == Constants.SERVICE_STATUS_DENIED)
			{
				throw new AppException(Constants.UPGRADING);
			}

			command = ProvisioningFactory.getCache().getCommand(request.getCommandId());

			action = provisioning.getAction(command.getCommandId());

			if (action.getStatus() == Constants.SERVICE_STATUS_DENIED)
			{
				throw new AppException(Constants.UPGRADING);
			}
			else
			{
				result = (CommandMessage) action.getExecuteMethod().invoke(action.getExecuteImpl(), this, action, request);

				if (result == null)
				{
					result = request;
				}

				if (result.getResponseTime() == null)
					result.setResponseTime(new Date());
			}
		}
		catch (AppException e)
		{
			result.setCause(e.getMessage());
			result.setDescription(e.getContext());

			error = e;
		}
		catch (Exception e)
		{
			result.setCause(Constants.ERROR);
			result.setDescription(e.getMessage());

			error = e;
		}
		finally
		{
			if ((action == null) || action.isLogEnable())
			{
				sendCommandLog(result);
			}
		}

		// get next command if available
		try
		{
			String actionCause = result.getCause();

			if (error != null)
			{
				result.setStatus(Constants.ORDER_STATUS_DENIED);
			}

			if (result.getStatus() == Constants.ORDER_STATUS_DENIED)
			{
				if (actionCause.equals(""))
					actionCause = Constants.ERROR;
			}
			else if (actionCause.equals(""))
			{
				actionCause = Constants.SUCCESS;
			}

			if (!result.getActionType().equals(Constants.ACTION_ROLLBACK)
					&& actionCause.equals(Constants.SUCCESS)
					&& command != null)
				
			result.getCompletedCommands().add(command.getAlias());

			int nextCounter = 0;
			try
			{
				if (!request.getActionType().equals(Constants.ACTION_ROLLBACK))
				{
					if (isTimeout(request))
						actionCause = Constants.ERROR_TIMEOUT;
				}

				result.setCause(actionCause);
				if (!result.getActionType().equals(Constants.ACTION_ROLLBACK)
						&& !actionCause.equals(Constants.SUCCESS)
						&& command != null)
				{
					result.setStatus(Constants.ORDER_STATUS_DENIED);
					rollback(result);
				}

				nextCounter = sendNextCommand(productRoute, result, command, result.getActionType(), actionCause);
			}
			catch (JMSException jme)
			{
				result.setCause(Constants.ERROR_RESOURCE_BUSY);
				result.setStatus(Constants.ORDER_STATUS_DENIED);
				rollback(result);
			}

			// reply to sender
			if (!result.getActionType().equals(Constants.ACTION_ROLLBACK) && ((nextCounter == 0) || (nextCounter > 1)))
			{
				if (result.getStatus() == Constants.ORDER_STATUS_PENDING)
				{
					result.setStatus(Constants.ORDER_STATUS_APPROVED);
				}

				try
				{
						sendOrderResponse(productRoute, result);	
				}
				catch (Exception e)
				{
					logMonitor(e);
					result.setCause(Constants.ERROR_RESOURCE_BUSY);
					result.setStatus(Constants.ORDER_STATUS_DENIED);
					rollback(result);
				}
			}

			if ((error != null) && !(error instanceof AppException))
			{
				throw error;
			}
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			// debugMonitor(result.toString());
			debugMonitor(result.toLogString());
		}

		return Constants.BIND_ACTION_SUCCESS;
	}

	public void rollback(CommandMessage request) throws Exception
	{
		CommandMessage rollback = null;
		for (int i = 0; i < request.getCompletedCommands().size(); i++)
		{
			String commandAlias = request.getCompletedCommands().get(i);

			try
			{
				CommandEntry completedCommand = null;
				try
				{
					completedCommand = ProvisioningFactory.getCache().getCommand(commandAlias);
				}
				catch (Exception e)
				{
					debugMonitor("Error: rollback command [" + completedCommand + "]not found.");
				}

				String rollbackCommandAlias = completedCommand.getParameter("rollback", "");

				if (!"".equals(rollbackCommandAlias))
				{
					CommandEntry commandEntry = null;
					try
					{
						commandEntry = ProvisioningFactory.getCache().getCommand(rollbackCommandAlias);
					}
					catch (Exception e)
					{

					}
					if (commandEntry != null)
					{
						rollback = request.clone();
						rollback.setProvisioningType(commandEntry.getProvisioningType());
						rollback.setCommandId(commandEntry.getCommandId());

						rollback.setActionType(Constants.ACTION_ROLLBACK);
						// rollback.setParameter("ignoreDBUpdate", "true");
						rollback.getParameters().setProperty("ignoreError", "true");
						rollback.setStatus(Constants.SERVICE_STATUS_DENIED);

						rollback.setRequest("");
						rollback.setResponse("");

						// Send to command routing queue.
						try
						{
							sendCommandRouting(rollback);
						}
						catch (Exception e)
						{
							debugMonitor("Error: rollback error [" + rollbackCommandAlias + "].");
						}
					}
					else
					{
						// Rollback command not found.
						debugMonitor("Error: rollback command [" + rollbackCommandAlias + "]not found.");
					}
				}
			}
			catch (Exception e)
			{
				debugMonitor(e);
			}
		}
	}
}
