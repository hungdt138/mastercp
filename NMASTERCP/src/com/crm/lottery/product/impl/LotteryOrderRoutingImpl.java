package com.crm.lottery.product.impl;

import com.crm.kernel.message.Constants;
import com.crm.lottery.sql.impl.LotteryImpl;
import com.crm.product.cache.ProductRoute;
import com.crm.product.impl.OrderRoutingImpl;
import com.crm.provisioning.message.CommandMessage;
import com.crm.provisioning.thread.OrderRoutingInstance;
import com.fss.util.AppException;

public class LotteryOrderRoutingImpl extends OrderRoutingImpl
{
	@Override
	public CommandMessage parser(OrderRoutingInstance instance, ProductRoute orderRoute, CommandMessage order) throws Exception
	{
		// TODO Auto-generated method stub
		return super.parser(instance, orderRoute, order);
	}

	@Override
	public void smsParser(OrderRoutingInstance instance, ProductRoute orderRoute, CommandMessage order) throws Exception
	{
		// TODO Auto-generated method stub
		super.smsParser(instance, orderRoute, order);

		validateRegion(order);
	}

	private void validateRegion(CommandMessage order) throws Exception
	{
		if (order.getActionType().equals(Constants.ACTION_REGISTER))
		{
			String regionCode = "";
			try
			{
				String key = order.getKeyword();
				if (key.contains("MB"))
					regionCode = "XSMB";
				else if (key.contains("MT"))
					regionCode = "XSMT";
				else if (key.contains("MN"))
					regionCode = "XSMN";

			}
			catch (Exception e)
			{

			}
			
			if ("".equals(regionCode))
				throw new AppException(Constants.ERROR_INVALID_SYNTAX);
			try
			{
				int count = LotteryImpl.getRegionCode(regionCode);
				if (count == 0)
					throw new AppException(Constants.ERROR_INVALID_PARAMETER);

				order.setRequestValue("lottery.regionCode", regionCode);
			}
			catch (Exception e)
			{
				throw e;
			}
		}
		else if (order.getActionType().equals(Constants.ACTION_UNREGISTER))
		{
			String majorRegion = "";
			try
			{
				majorRegion = order.getKeyword().split(" ")[1];
			}
			catch (Exception e)
			{

			}

			if ("".equals(majorRegion))
				throw new AppException(Constants.ERROR_INVALID_SYNTAX);
			if (majorRegion.contains("MB"))
				majorRegion = "MB";
			else if (majorRegion.contains("MT"))
				majorRegion = "MT";
			else
				majorRegion = "MN";

			order.setRequestValue("lottery.majorRegion", majorRegion);
		}
	}
}
