package com.crm.product.impl;

import com.crm.product.cache.ProductRoute;
import com.crm.provisioning.message.CommandMessage;
import com.crm.provisioning.thread.OrderRoutingInstance;

public class LotteryOrderRoutingImpl extends OrderRoutingImpl
{
	@Override
	public CommandMessage parser(OrderRoutingInstance instance, ProductRoute orderRoute, CommandMessage order) throws Exception
	{
		// TODO Auto-generated method stub
		return super.parser(instance, orderRoute, order);
	}
}
