package com.crm.product.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.crm.kernel.message.Constants;
import com.crm.product.cache.ProductEntry;
import com.crm.product.cache.ProductFactory;
import com.crm.product.cache.ProductRoute;
import com.crm.provisioning.message.CommandMessage;
import com.crm.provisioning.thread.OrderRoutingInstance;
import com.crm.subscriber.bean.SubscriberProduct;
import com.crm.subscriber.impl.SubscriberEntryImpl;
import com.crm.subscriber.impl.SubscriberProductImpl;
import com.crm.util.DateUtil;
import com.fss.util.AppException;

public class DataOrderRoutingImpl extends VNMOrderRoutingImpl {
	
	
	
	public void checkActionType(
			OrderRoutingInstance instance, ProductRoute orderRoute, ProductEntry product
			, CommandMessage order, SubscriberProduct subscriberProduct)
			throws Exception
	{
		Date now = new Date();

		try
		{
			if (subscriberProduct != null)
			{
				int remainDays = DateUtil.getDateDiff(now, subscriberProduct.getExpirationDate());

				if (remainDays < 0)
				{
					remainDays = 0;
				}

				order.setResponseValue("service.activeDays", remainDays);
				order.setResponseValue("service.activeDate", subscriberProduct.getExpirationDate());
			}

			if (subscriberProduct != null)
			{
				order.setSubProductId(subscriberProduct.getSubProductId());
			}

			String actionType = order.getActionType();

			if (actionType.equals(Constants.ACTION_REGISTER) && (subscriberProduct != null))
			{
				if (orderRoute.isTopupEnable())
				{
					actionType = Constants.ACTION_TOPUP;
				}
				else
				{
					actionType = Constants.ACTION_REGISTER;
				}
			}

			if (product.isSubscription())
			{
				if ((subscriberProduct == null) || subscriberProduct.isCancel())
				{
					if (actionType.equals(Constants.ACTION_SUBSCRIPTION))
					{
						throw new AppException(Constants.ERROR_SUBSCRIPTION_NOT_FOUND);
					}
					else if (actionType.equals(Constants.ACTION_UNREGISTER))
					{
						throw new AppException(Constants.ERROR_SUBSCRIPTION_NOT_FOUND);
					}
					else if (actionType.equals(Constants.ACTION_CANCEL))
					{
						throw new AppException(Constants.ERROR_SUBSCRIPTION_NOT_FOUND);
					}
					else if (actionType.equals(Constants.ACTION_TOPUP))
					{
						actionType = Constants.ACTION_REGISTER;
					}
				}
				else if (subscriberProduct != null)
				{
					if (orderRoute.isTopupEnable())
					{
						actionType = Constants.ACTION_TOPUP;
					}
					else if (actionType.equals(Constants.ACTION_SUBSCRIPTION))
					{
						if (DateUtil.compareDate(subscriberProduct.getExpirationDate(), now) >= 0)
						{
							actionType = Constants.ACTION_SUBSCRIPTION;
						}
					}
				}
			}

			// get associate product
			if (actionType.equals(Constants.ACTION_REGISTER) || actionType.equals(Constants.ACTION_UPGRADE))
			{
				checkBlacklist(instance, product, order);

				checkUpgrade(instance, product, order);

				if (order.getAssociateProductId() != Constants.DEFAULT_ID)
				{
					actionType = Constants.ACTION_UPGRADE;
				}
			}

			order.setActionType(actionType);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
	
	public CommandMessage parser(OrderRoutingInstance instance, ProductRoute orderRoute, CommandMessage order) throws Exception
	{
		Exception error = null;

		ProductEntry product = null;

		SubscriberProduct subscriberProduct = null;

		try
		{
			// check SMS syntax
			if (order.getChannel().equals("SMS"))
			{
				smsParser(instance, orderRoute, order);
			}

			// check duplicate request
			if (orderRoute.getDuplicateScan() > 0)
			{
				checkDuplicate(instance, orderRoute, order);
			}

			if (orderRoute.getMaxRegisterDaily() > 0)
			{
				checkMaxRegister(instance, orderRoute, order);
			}
			// check promotion
			if (orderRoute.isCheckPromotion())
			{
				checkPromotion(instance, orderRoute, order);
			}

			// check product in available list
			product = ProductFactory.getCache().getProduct(order.getProductId());

			// get current subscriber product
			if (!product.isSubscription())
			{

			}
			else if (order.getSubProductId() == Constants.DEFAULT_ID)
			{
				subscriberProduct = SubscriberProductImpl.getActive(order.getIsdn(), order.getProductId());
			}
			else
			{
				subscriberProduct = SubscriberProductImpl.getProduct(order.getSubProductId());
			}

			// check action type
			checkActionType(instance, orderRoute, product, order, subscriberProduct);

			// validate
			if (orderRoute.isCheckBalance())
			{
				order = checkBalance(instance, orderRoute, order);
			}
			else
			{
				if (order.getSubscriberType() == Constants.UNKNOW_SUB_TYPE)
				{
					order.setSubscriberType(SubscriberEntryImpl.getSubscriberType(order.getIsdn()));
				}

				order.setAmount(order.getQuantity() * order.getPrice());
			}

			if ((order.getStatus() == Constants.ORDER_STATUS_DENIED) && order.getCause().equals(Constants.ERROR_NOT_ENOUGH_MONEY))
			{
				if (order.getActionType().equals(Constants.ACTION_SUBSCRIPTION))
				{
					order.setActionType(Constants.ACTION_CANCEL);

					order.setCause("");

					order.setStatus(Constants.ORDER_STATUS_PENDING);
				}
			}
			else
			{
				checkSubscriberType(instance, product, order);
			}

			if (order.getStatus() != Constants.ORDER_STATUS_DENIED)
			{
				validate(instance, orderRoute, order);
			}
		}
		catch (Exception e)
		{
			error = e;
		}

		if (error != null)
		{
			order.setStatus(Constants.ORDER_STATUS_DENIED);

			if (error instanceof AppException)
			{
				order.setCause(error.getMessage());
			}
			else
			{
				order.setDescription(error.getMessage());
			}
		}

		if ((error != null) && !(error instanceof AppException))
		{
			throw error;
		}
		
		order.getParameters().setBoolean("includeCurrentDay", true);

		return order;
	}
}
