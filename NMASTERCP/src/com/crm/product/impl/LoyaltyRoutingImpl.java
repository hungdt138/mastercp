package com.crm.product.impl;

import com.crm.kernel.message.Constants;
import com.crm.product.cache.ProductEntry;
import com.crm.product.cache.ProductFactory;
import com.crm.product.cache.ProductRoute;
import com.crm.provisioning.message.VNMMessage;
import com.crm.provisioning.thread.OrderRoutingInstance;
import com.fss.util.AppException;

//trungnq
public class LoyaltyRoutingImpl extends VNMOrderRoutingImpl
{

	public VNMMessage validateNumber(OrderRoutingInstance instance, ProductRoute orderRoute, ProductEntry product, VNMMessage vnmMessage)
	{
		String strPoints = vnmMessage.getParameters().getString("sms.params[" + 0 + "]");

		try
		{
			Integer.parseInt(strPoints);
		}
		catch (NumberFormatException e)
		{
			vnmMessage.setCause(e.toString());

			return vnmMessage;
		}

		return vnmMessage;
	}

	public void validateBalance(OrderRoutingInstance instance,
			ProductRoute orderRoute, ProductEntry product,
			VNMMessage vnmMessage, String balanceType, boolean chargeMulti)
			throws Exception
	{
		// TODO Auto-generated method stub
		// super.validateBalance(instance, orderRoute, product, vnmMessage,
		// balanceType,
		// chargeMulti);

		String strPoints = vnmMessage.getParameters().getString("sms.params[" + 0 + "]");

		try
		{

			ProductEntry productEntry = ProductFactory.getCache().getProduct(vnmMessage.getProductId());

			int factor = productEntry.getParameters().getInteger("factor", 10);

			int redeemPoints = Integer.parseInt(strPoints);

			vnmMessage.getParameters().setDouble("Amount", redeemPoints * factor);

			double amount = vnmMessage.getParameters().getDouble("Amount");

			vnmMessage.setAmount(amount);
		}
		catch (Throwable e)
		{
			vnmMessage.setCause(Constants.ERROR_INVALID_SYNTAX);
		}

	}

}
