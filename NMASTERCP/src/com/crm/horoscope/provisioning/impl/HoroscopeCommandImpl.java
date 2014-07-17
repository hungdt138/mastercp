package com.crm.horoscope.provisioning.impl;

import com.crm.horoscope.sql.impl.HoroscopeImpl;
import com.crm.kernel.message.Constants;
import com.crm.product.cache.ProductEntry;
import com.crm.product.cache.ProductFactory;
import com.crm.provisioning.cache.ProvisioningCommand;
import com.crm.provisioning.impl.CommandImpl;
import com.crm.provisioning.message.CommandMessage;
import com.crm.provisioning.thread.CommandInstance;
import com.crm.provisioning.util.ResponseUtil;
import com.crm.subscriber.bean.SubscriberProduct;
import com.crm.util.StringUtil;
import com.fss.util.AppException;

public class HoroscopeCommandImpl extends CommandImpl
{
	public CommandMessage register(CommandInstance instance,
			ProvisioningCommand provisioningCommand, CommandMessage request)
			throws Exception
	{
		CommandMessage result = request;

		try
		{
			ProductEntry product = ProductFactory.getCache().getProduct(
					request.getProductId());

			if (!product.isSubscription())
			{
				throw new AppException(Constants.ERROR_INVALID_REQUEST);
			}

			boolean includeCurrentDay = result.getParameters().getBoolean(
					"includeCurrentDay");

			SubscriberProduct subProduct = HoroscopeImpl.register(
					result.getUserId(), result.getUserName(),
					result.getSubscriberId(), result.getIsdn(),
					result.getSubscriberType(), result.getProductId(),
					result.getCampaignId(), result.getLanguageId(),
					result.getKeyword().trim(), result.getParameters().getString("BirthdayOfSub"),
					includeCurrentDay);
			if (subProduct.getExpirationDate() != null)
			{
				result.setResponseValue(ResponseUtil.SERVICE_EXPIRE_DATE,
						StringUtil.format(subProduct.getExpirationDate(),
								"dd/MM/yyyy"));
			}
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
			ProductEntry product = ProductFactory.getCache().getProduct(
					request.getProductId());

			if (!product.isSubscription())
			{
				throw new AppException(Constants.ERROR_INVALID_REQUEST);
			}

			HoroscopeImpl.unregister(result.getUserId(),
					result.getUserName(), result.getIsdn(),
					result.getProductId());
		}
		catch (Exception error)
		{
			processError(instance, provisioningCommand, request, error);
		}

		return result;
	}
}
