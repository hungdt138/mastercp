/**
 * 
 */
package com.crm.provisioning.impl.data;

import com.crm.kernel.message.Constants;
import com.crm.product.cache.ProductEntry;
import com.crm.product.cache.ProductFactory;
import com.crm.provisioning.cache.ProvisioningCommand;
import com.crm.provisioning.impl.CommandImpl;
import com.crm.provisioning.message.CommandMessage;
import com.crm.provisioning.thread.CommandInstance;
import com.crm.provisioning.util.ResponseUtil;
import com.crm.subscriber.bean.SubscriberProduct;
import com.crm.subscriber.impl.SubscriberProductImpl;
import com.crm.util.StringUtil;
import com.fss.util.AppException;

/**
 * @author hungdt
 * 
 */
public class DataCommandImpl extends CommandImpl
{

	public CommandMessage registerServiceByPassExisted(CommandInstance instance,
			ProvisioningCommand provisioningCommand, CommandMessage request)
			throws Exception
	{
		CommandMessage result = request;
		try
		{

			boolean includeCurrentDay = result.getParameters().getBoolean(
					"includeCurrentDay");

			if (result.getActionType().equals(Constants.ACTION_UPGRADE))
				includeCurrentDay = false;

			SubscriberProduct subProduct = SubscriberProductImpl.registerProductBypassExisted(
					result.getUserId(), result.getUserName(),
					result.getSubscriberId(), result.getIsdn(),
					result.getSubscriberType(), result.getProductId(),
					result.getCampaignId(), result.getLanguageId(),
					includeCurrentDay);

			/**
			 * Add response value
			 */
			result.setResponseValue(ResponseUtil.SERVICE_EXPIRE_DATE,
					StringUtil.format(subProduct.getExpirationDate(),
							"dd/MM/yyyy"));
		}
		catch (Exception error)
		{
			processError(instance, provisioningCommand, request, error);
		}
		return result;
	}

	public CommandMessage unregister(CommandInstance instance,
			ProvisioningCommand provisioningCommand, CommandMessage request)
			throws Exception
	{
		CommandMessage result = request;

		try
		{
			SubscriberProductImpl.unregister(result.getUserId(),
					result.getUserName(), result.getSubProductId(),
					result.getProductId());
		}
		catch (Exception error)
		{
			processError(instance, provisioningCommand, request, error);
		}

		return result;
	}

	public CommandMessage subscription(CommandInstance instance,
			ProvisioningCommand provisioningCommand, CommandMessage request)
			throws Exception
	{
		CommandMessage result = request;
		try
		{
			SubscriberProductImpl.subscription(result.getUserId(),
					result.getUserName(), result.getSubProductId(),
					result.isFullOfCharge(), result.getQuantity());
		}
		catch (Exception error)
		{
			processError(instance, provisioningCommand, request, error);
		}

		return result;
	}

}
