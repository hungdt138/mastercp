package com.crm.lottery.provisioning.impl;

import java.util.ArrayList;
import java.util.Date;

import com.crm.kernel.message.Constants;
import com.crm.lottery.bean.LotteryEntry;
import com.crm.lottery.bean.LotterySubEntry;
import com.crm.lottery.sql.impl.LotteryImpl;
import com.crm.product.cache.ProductEntry;
import com.crm.product.cache.ProductFactory;
import com.crm.provisioning.cache.ProvisioningCommand;
import com.crm.provisioning.impl.CommandImpl;
import com.crm.provisioning.message.CommandMessage;
import com.crm.provisioning.thread.CommandInstance;
import com.fss.util.AppException;

public class LotteryCommandImpl extends CommandImpl
{
	public CommandMessage register(CommandInstance instance,
			ProvisioningCommand provisioningCommand, CommandMessage request)
			throws Exception
	{
		CommandMessage result = request;

		String regionCode = result.getRequestValue("lottery.regionCode");
		String majorRegion = "";
		try
		{
			LotterySubEntry sub = LotteryImpl.getSubsription(result.getIsdn(), regionCode);
			if (sub == null)
			{
				majorRegion = LotteryImpl.register(result.getIsdn(), result.getProductId(),
						result.getServiceAddress(), regionCode);

				result.setRequestValue("lottery.majorRegion", majorRegion);
			}
			else
			{
				result.setRequestValue("lottery.majorRegion", sub.getMajorRegion());
			}

			result.setCause(Constants.SUCCESS);
		}
		catch (Exception e)
		{
			processError(instance, provisioningCommand, result, e);
		}

		instance.debugMonitor("REGISTER: " + result.getIsdn() + "," + majorRegion + "," + result.getCause());
		return result;
	}

	public CommandMessage unregister(CommandInstance instance,
			ProvisioningCommand provisioningCommand, CommandMessage request)
			throws Exception
	{
		CommandMessage result = request;

		String majorRegion = result.getRequestValue("lottery.majorRegion", "");
		try
		{
			if ("".equals(majorRegion))
				throw new AppException(Constants.ERROR_INVALID_SYNTAX);
			LotteryImpl.unregister(result.getIsdn(), majorRegion);
			result.setCause(Constants.SUCCESS);
		}
		catch (Exception e)
		{
			processError(instance, provisioningCommand, result, e);
		}
		instance.debugMonitor("UNREGISTER: " + result.getIsdn() + "," + majorRegion + "," + result.getCause());
		return result;
	}

	public CommandMessage renew(CommandInstance instance,
			ProvisioningCommand provisioningCommand, CommandMessage request)
			throws Exception
	{
		CommandMessage result = request;
		String regionCode = "";
		String majorRegion = "";
		long subsId = 0;
		try
		{
			subsId = Long.parseLong(result.getRequestValue("lottery.subsId", "0"));
			if (subsId > 0)
			{
				regionCode = result.getRequestValue("lottery.regionCode", "");
				majorRegion = result.getRequestValue("lottery.majorRegion", "");
				LotteryImpl.renew(subsId);
				LotteryImpl.addQueueWait(result.getIsdn(), result.getProductId(),
						result.getServiceAddress(), regionCode, majorRegion, "");

				result.setCause(Constants.SUCCESS);
			}
			else
			{
				throw new AppException(Constants.ERROR_SUBSCRIPTION_NOT_FOUND);
			}
		}
		catch (Exception e)
		{
			processError(instance, provisioningCommand, result, e);
		}
		instance.debugMonitor("RENEW: " + subsId + "," + result.getIsdn() + "," + majorRegion + "," + result.getCause());
		return result;
	}

	public CommandMessage getLottery(CommandInstance instance,
			ProvisioningCommand provisioningCommand, CommandMessage request)
			throws Exception
	{
		CommandMessage result = request;
		try
		{
			String regionCode = result.getRequestValue("lottery.regionCode", "");
			String majorRegion = result.getRequestValue("lottery.majorRegion", "");

			ArrayList<LotteryEntry> lotteries = LotteryImpl.getLottery(regionCode, new Date());


//			try
//			{
//				ProductEntry product = ProductFactory.getCache().getProduct(result.getProductId());
//				/**
//				 * The f**ker MT charging cause this sh*t op
//				 */
//				Thread.sleep(product.getParameters().getLong("MTSendDelay", 10000));
//			}
//			catch (Exception e)
//			{
//				//
//			}

			
			String sentRegion = "";
			if (lotteries != null && lotteries.size() > 0)
			{
				sentRegion = sentLotteryResult(instance, provisioningCommand, result, lotteries);
			}

			instance.debugMonitor("SEND: " + result.getIsdn() + sentRegion);
			LotteryImpl.addQueueWait(result.getIsdn(), result.getProductId(),
						result.getServiceAddress(), regionCode, majorRegion, sentRegion);
		}
		catch (Exception e)
		{
			processError(instance, provisioningCommand, result, e);
		}
		return result;
	}

	private String sentLotteryResult(CommandInstance instance,
			ProvisioningCommand provisioningCommand, CommandMessage request, ArrayList<LotteryEntry> lotteries) throws Exception
	{
		String sentRegion = "";
		String productCode = "";
		productCode = request.getRequestValue("productCode", "");
		if ("".equals(productCode))
		{
			ProductEntry product = ProductFactory.getCache().getProduct(request.getProductId());
			productCode = product.getParameter("MTProductCode", "");
		}

		for (LotteryEntry lottery : lotteries)
		{
			if (lottery.isLast())
			{
				LotteryImpl.sendSMS(request.getServiceAddress(), request.getIsdn(), productCode, lottery.getPrize(),
						request.getProductId());

				sentRegion += "," + lottery.getRegionCode();
			}
		}

		return sentRegion;
	}
}
