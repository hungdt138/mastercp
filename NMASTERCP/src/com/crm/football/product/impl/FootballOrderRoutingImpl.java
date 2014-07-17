package com.crm.football.product.impl;

import java.util.List;

import com.crm.football.bean.FootballInfo;
import com.crm.football.bean.FootballSubEntry;
import com.crm.football.sql.impl.FootballImpl;
import com.crm.kernel.message.Constants;
import com.crm.product.cache.ProductEntry;
import com.crm.product.cache.ProductRoute;
import com.crm.product.impl.OrderRoutingImpl;
import com.crm.provisioning.message.CommandMessage;
import com.crm.provisioning.thread.OrderRoutingInstance;
import com.crm.subscriber.bean.SubscriberProduct;
import com.fss.util.AppException;

public class FootballOrderRoutingImpl extends OrderRoutingImpl
{
	@Override
	public void smsParser(OrderRoutingInstance instance, ProductRoute orderRoute, CommandMessage order) throws Exception
	{
		// TODO Auto-generated method stub
		super.smsParser(instance, orderRoute, order);

		try
		{
			String code = order.getParameters().getString("sms.params[0]", "");
			if (code != null && !"".equals(code))
				order.setRequestValue("football.code", code);

			FootballInfo info = validateKeyword(order);
			if (info != null)
			{
				order.setRequestValue("football.code", info.getCode());
				order.setRequestValue("football.id", info.getId());
				order.setRequestValue("football.name", info.getName());
			}
		}
		catch (Exception e)
		{
			throw e;
		}
	}

	public FootballInfo validateKeyword(CommandMessage request) throws Exception
	{
		return null;
	}

	@Override
	public void checkActionType(OrderRoutingInstance instance, ProductRoute orderRoute, ProductEntry product,
			CommandMessage order, SubscriberProduct subscriberProduct) throws Exception
	{
		// TODO Auto-generated method stub
		super.checkActionType(instance, orderRoute, product, order, subscriberProduct);

		if (order.getActionType().equals(Constants.ACTION_REGISTER))
		{
			FootballSubEntry sub = FootballImpl.getSubsription(order.getIsdn(), order.getProductId(),
					Long.parseLong(order.getRequestValue("football.id", "0")));
			if (sub != null)
			{
				throw new AppException(Constants.ERROR_REGISTERED);
			}
		}
		else if (order.getActionType().equals(Constants.ACTION_UNREGISTER))
		{
			List<FootballSubEntry> subs = FootballImpl.getSubsriptions(order.getIsdn(), order.getProductId());
			if (subs.size() == 0)
			{
				throw new AppException(Constants.ERROR_UNREGISTERED);
			}
		}
	}
}
