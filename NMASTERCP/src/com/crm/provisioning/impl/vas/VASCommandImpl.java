package com.crm.provisioning.impl.vas;

import com.crm.kernel.message.Constants;
import com.crm.product.cache.ProductEntry;
import com.crm.product.cache.ProductFactory;
import com.crm.product.cache.ProductRoute;
import com.crm.provisioning.cache.ProvisioningCommand;
import com.crm.provisioning.impl.CommandImpl;
import com.crm.provisioning.message.CommandMessage;
import com.crm.provisioning.thread.CommandInstance;
import com.crm.provisioning.util.CommandUtil;
import com.crm.util.StringUtil;

public class VASCommandImpl extends CommandImpl
{

	/**
	 * Get provisioning from VASGATE <br>
	 * 
	 * Author: NamTA <br>
	 * Create Date: 08/05/2012
	 * 
	 * @param instance
	 * @param provisioningCommand
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public CommandMessage getProvisioning(
			CommandInstance instance, ProvisioningCommand provisioningCommand, CommandMessage request)
			throws Exception
	{
		VASConnection connection = null;
		ProductRoute productRoute = null;
		ProductEntry productEntry = null;
		if (instance.getDebugMode().equals("depend"))
		{
			simulation(instance, provisioningCommand, request);
		}
		else
		{
			try
			{
				productEntry = ProductFactory.getCache().getProduct(request.getProductId());
				productRoute = ProductFactory.getCache().getProductRoute(request.getRouteId());
				connection = (VASConnection) instance.getProvisioningConnection();
				connection.provisioning(request);

				String strActionType = request.getActionType();
				String strCauseValue = StringUtil.nvl(request.getCauseValue(), "0");

				String messageResponseKey = strActionType + "-" + strCauseValue;
				String messageResponseValue = productRoute.getParameter(messageResponseKey, "");
				if (productRoute.getChannel().equals(Constants.CHANNEL_SMS)
						&& !messageResponseValue.equals(""))
				{
					CommandUtil.sendSMS(instance, request, messageResponseValue);
				}
				else if (productRoute.getChannel().equals(Constants.CHANNEL_SMS))
				{
					messageResponseValue = productEntry.getParameter(request.getCause(),
							"Xin loi, he thong dang ban. Xin quy khach vui long nhan tin lai sau.");
					CommandUtil.sendSMS(instance, request, messageResponseValue);
				}
			}
			catch (Exception e)
			{
				CommandUtil.sendSMS(instance, request, "Xin loi, he thong dang ban. Xin quy khach vui long nhan tin lai sau.");
				processError(instance, provisioningCommand, request, e);
			}
			finally
			{
				instance.closeProvisioningConnection(connection);
			}
		}
		return request;
	}

	public CommandMessage getActivationStatus(CommandInstance instance, ProvisioningCommand provisioningCommand,
			CommandMessage request)
			throws Exception
	{
		VASConnection connection = null;
		try
		{
			connection = (VASConnection) instance.getProvisioningConnection();
			connection.checkAllStatus(request);
		}
		catch (Exception e)
		{
			processError(instance, provisioningCommand, request, e);
		}
		finally
		{
			instance.closeProvisioningConnection(connection);
		}

		return request;
	}


}
