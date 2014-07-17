package com.crm.product.impl;

import java.util.Calendar;
import java.util.Date;

import com.crm.kernel.message.Constants;
import com.crm.marketing.cache.CampaignEntry;
import com.crm.marketing.cache.CampaignFactory;
import com.crm.product.cache.ProductEntry;
import com.crm.product.cache.ProductFactory;
import com.crm.product.cache.ProductRoute;
import com.crm.provisioning.message.CommandMessage;
import com.crm.provisioning.thread.OrderRoutingInstance;
import com.fss.util.AppException;

public class VasgateRoutingImpl extends OrderRoutingImpl
{
	@Override
	public void checkPromotion(OrderRoutingInstance instance,
			ProductRoute orderRoute, CommandMessage order) throws Exception
	{
		CampaignEntry campaign = null;

		try
		{
			ProductEntry product = ProductFactory.getCache().getProduct(order.getProductId());
			campaign = CampaignFactory.getCache().getCampaign(product.getAlias().toUpperCase(), order.getActionType());

			Date now = new Date();
			if (campaign != null && campaign.getStatus() == Constants.CAMPAIGN_STATUS_APPROVED 
					&& campaign.getStartDate() != null && campaign.getStartDate().before(now))
			{
				Calendar campExp = null;
				if (campaign.getExpirationDate() != null)
				{
					campExp = Calendar.getInstance();
					campExp.setTime(campaign.getExpirationDate());
					campExp.add(Calendar.DATE, 1);
				}
				
				if (campExp != null && campExp.getTime().before(now))
				{
				}
				else if (order.getAmount() == 0)
				{
					order.setCampaignId(campaign.getCampaignId());
				}
			}
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}
