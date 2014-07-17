package com.crm.provisioning.impl.mtcharging;

import java.util.Date;

import com.crm.kernel.message.Constants;
import com.crm.product.cache.ProductEntry;
import com.crm.product.cache.ProductFactory;
import com.crm.provisioning.cache.ProvisioningCommand;
import com.crm.provisioning.impl.CommandImpl;
import com.crm.provisioning.message.CommandMessage;
import com.crm.provisioning.thread.CommandInstance;
import com.nms.iwebservice.SubscriptionResp;

public class MTChargingCommandImpl extends CommandImpl
{
	public CommandMessage subscription(CommandInstance instance,
			ProvisioningCommand provisioningCommand, CommandMessage request)
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

				ProductEntry product = ProductFactory.getCache().getProduct(
						request.getProductId());

				String productCode = request.getRequestValue("productCode", "");

				if ("".equals(productCode))
				{
					productCode = product.getParameter("MTProductCode", "");
				}

				MTChargingConnection connection = null;
				try
				{
					String description = request.getParameters().getString("Description", "");
					
					connection = (MTChargingConnection) instance
							.getProvisioningConnection();
					int sessionId = setRequestLog( instance, request,
							product.getAlias() + " - Subscription(" + 
							request.getIsdn() + ", " + request.getProductId() + ", " + description + ")");
					
					SubscriptionResp response = connection.subscription(
							sessionId, request.getIsdn(), productCode, description);
					
					responseCode = response.getResult();
					request.setAmount(response.getAmount());
					setResponse(instance, request, request.getIsdn() + ","
							+ responseCode + "," + response.getAmount(),
							sessionId);
				}
				finally
				{
					instance.closeProvisioningConnection(connection);
				}

				if (responseCode.equals(provisioningCommand.getParameter(
						"expectedResult", "SVC0000")))
				{
					request.setCause(Constants.SUCCESS);
				}
				else if (responseCode.equals(provisioningCommand.getParameter(
						"NotEnoughMoney", "SVC0202")))
				{
					request.setCause(Constants.ERROR_NOT_ENOUGH_MONEY);
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

	public CommandMessage sendSMS(CommandInstance instance,
			ProvisioningCommand provisioningCommand, CommandMessage request)
			throws Exception
	{
		String responseCode = "";

		if (instance.getDebugMode().equals("depend"))
		{
			instance.debugMonitor("Message: " + request.getRequest());
			simulation(instance, provisioningCommand, request);
		}
		else
		{
			try
			{
				String productCode = request.getRequestValue("productCode", "");

				if ("".equals(productCode))
				{
					ProductEntry product = ProductFactory.getCache()
							.getProduct(request.getProductId());
					productCode = product.getParameter("MTProductCode", "");
				}

				MTChargingConnection connection = null;
				try
				{
					if (!request.getRequest().equals(""))
					{
						connection = (MTChargingConnection) instance
								.getProvisioningConnection();
						int sessionId = setRequestLog(
								instance,
								request,
								"SendMT(" + request.getServiceAddress() + ", "
										+ productCode + ", "
										+ request.getIsdn() + ", "
										+ request.getRequest() + ")");
						responseCode = connection.sendSMS(sessionId,
								request.getIsdn(), request.getRequest(),
								productCode, request.getServiceAddress());
						setResponse(instance, request, responseCode, sessionId);
					}
				}
				finally
				{
					instance.closeProvisioningConnection(connection);
				}

				if (responseCode.equals(provisioningCommand.getParameter(
						"expectedResult", "SVC0000")))
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

	public int setRequestLog(CommandInstance instance, CommandMessage request,
			String requestString) throws Exception
	{
		request.setRequestTime(new Date());
		long sessionId = setRequest(instance, request, requestString);
		if (sessionId > (long) Integer.MAX_VALUE)
			return (int) (sessionId % (long) Integer.MAX_VALUE);
		else
			return (int) sessionId;
	}
}
