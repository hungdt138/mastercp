package com.crm.provisioning.impl.vim;

import java.util.Date;

import com.crm.kernel.message.Constants;
import com.crm.provisioning.cache.ProvisioningCommand;
import com.crm.provisioning.impl.CommandImpl;
import com.crm.provisioning.message.CommandMessage;
import com.crm.provisioning.thread.CommandInstance;
import com.crm.util.GeneratorSeq;

public class VIMCommandImpl extends CommandImpl
{
	public CommandMessage registerService(CommandInstance instance, ProvisioningCommand provisioningCommand,
			CommandMessage request)
			throws Exception
	{
		// int result = UNKNOW_RESPONSE;
		String responseCode = "";

		if (instance.getDebugMode().equals("depend"))
		{
			simulation(instance, provisioningCommand, request);
		}
		else
		{
			try
			{
				int subscriberType;
				if (request.isPostpaid())
				{
					subscriberType = 501;
				}
				else
				{
					subscriberType = 500;
				}
	
				int packageType = provisioningCommand.getParameters().getInteger("PackageType", 101);
	
				VIMConnection connection = null;
				try
				{
					connection = (VIMConnection) instance.getProvisioningConnection();
					int sessionId = setRequestLog(instance, request, "REGISTER(" + request.getIsdn() + ")");
					responseCode = connection.register(request, subscriberType,
							packageType, sessionId);
					setResponse(instance, request, responseCode, sessionId);
				}
				finally
				{
					instance.closeProvisioningConnection(connection);
				}
	
				if (responseCode.equals(provisioningCommand.getParameter("expectedResult", "")))
				{
					request.setCause(Constants.SUCCESS);
				}
				else
				{
					request.setCause(Constants.ERROR);
				}
			}
			catch (Exception e)
			{
				processError(instance, provisioningCommand, request, e);
			}
		}
		
		return request;
	}

	public CommandMessage unregisterService(CommandInstance instance, ProvisioningCommand provisioningCommand,
			CommandMessage request)
			throws Exception
	{
		String responseCode = "";

		if (instance.getDebugMode().equals("depend"))
		{
			simulation(instance, provisioningCommand, request);
		}
		else
		{
			try
			{
				VIMConnection connection = null;
				try
				{
					connection = (VIMConnection) instance.getProvisioningConnection();
					int sessionId = setRequestLog(instance, request, "UNREGISTER(" + request.getIsdn() + ")");
					responseCode = connection.unregister(request, sessionId);
	
					setResponse(instance, request, responseCode, sessionId);
				}
				finally
				{
					instance.closeProvisioningConnection(connection);
				}
	
				if (responseCode.equals(provisioningCommand.getParameter("expectedResult", "")))
				{
					request.setCause(Constants.SUCCESS);
				}
				else
				{
					request.setCause(Constants.ERROR);
				}
			}
			catch (Exception e)
			{
				processError(instance, provisioningCommand, request, e);
			}
		}

		return request;
	}

	public CommandMessage reactiveService(CommandInstance instance, ProvisioningCommand provisioningCommand,
			CommandMessage request)
			throws Exception
	{
		String responseCode = "";

		if (instance.getDebugMode().equals("depend"))
		{
			simulation(instance, provisioningCommand, request);
		}
		else
		{
			try
			{
				VIMConnection connection = null;
				try
				{
					connection = (VIMConnection) instance.getProvisioningConnection();
					int sessionId = setRequestLog(instance, request, "REACTIVE(" + request.getIsdn() + ")");
					responseCode = connection.reactive(request, sessionId);
	
					setResponse(instance, request, responseCode, sessionId);
				}
				finally
				{
					instance.closeProvisioningConnection(connection);
				}
	
				if (responseCode.equals(provisioningCommand.getParameter("expectedResult", "")))
				{
					request.setCause(Constants.SUCCESS);
				}
				else
				{
					request.setCause(Constants.ERROR);
				}
			}
			catch (Exception e)
			{
				processError(instance, provisioningCommand, request, e);
			}
		}

		return request;
	}

	public CommandMessage deactiveService(CommandInstance instance, ProvisioningCommand provisioningCommand,
			CommandMessage request)
			throws Exception
	{
		String responseCode = "";

		if (instance.getDebugMode().equals("depend"))
		{
			simulation(instance, provisioningCommand, request);
		}
		else
		{
			try
			{
				VIMConnection connection = null;
				try
				{
					connection = (VIMConnection) instance.getProvisioningConnection();
					int sessionId = setRequestLog(instance, request, "DEACTIVE(" + request.getIsdn() + ")");
					responseCode = connection.renewal(request, 600, sessionId);
					setResponse(instance, request, responseCode, sessionId);
				}
				finally
				{
					instance.closeProvisioningConnection(connection);
				}
	
				if (responseCode.equals(provisioningCommand.getParameter("expectedResult", "")))
				{
					request.setCause(Constants.SUCCESS);
				}
				else
				{
					request.setCause(Constants.ERROR);
				}
			}
			catch (Exception e)
			{
				processError(instance, provisioningCommand, request, e);
			}
		}

		return request;
	}
	
	public int setRequestLog(CommandInstance instance, CommandMessage request, String requestString) throws Exception
	{
		request.setRequestTime(new Date());
		long sessionId = setRequest(instance, request, requestString);
		if (sessionId > (long)Integer.MAX_VALUE)
			return (int)(sessionId % (long)Integer.MAX_VALUE);
		else
			return (int) sessionId;
	}
	
}
