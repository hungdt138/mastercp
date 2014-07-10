/**
 * 
 */
package com.crm.product.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.comverse_in.prepaid.ccws.SubscriberEntity;
import com.comverse_in.prepaid.ccws.SubscriberRetrieve;
import com.crm.kernel.message.Constants;
import com.crm.product.cache.ProductEntry;
import com.crm.product.cache.ProductFactory;
import com.crm.product.cache.ProductRoute;
import com.crm.provisioning.impl.CommandImpl;
import com.crm.provisioning.impl.ccws.CCWSConnection;
import com.crm.provisioning.message.CommandMessage;
import com.crm.provisioning.thread.OrderRoutingInstance;
import com.crm.provisioning.util.CommandUtil;
import com.crm.subscriber.bean.SubscriberGroup;
import com.crm.subscriber.bean.SubscriberProduct;
import com.crm.subscriber.impl.SubscriberEntryImpl;
import com.crm.subscriber.impl.SubscriberGroupImpl;
import com.crm.subscriber.impl.SubscriberProductImpl;

import com.fss.util.AppException;

/**
 * @author ThangPV
 * 
 */
public class CoupleOrderRoutingImpl extends Biz30OrderRoutingImpl
{
	public void smsParser(OrderRoutingInstance instance, ProductRoute orderRoute, CommandMessage order) throws Exception
	{
		try
		{
			super.smsParser(instance, orderRoute, order);

			// Check member of couple.
			String	memberList = SubscriberProductImpl.getMemberList(order.getIsdn(), order.getUserName(), order.getProductId(), false);

			if (!"".equals(memberList))
			{
				order.setShipTo(CommandUtil.addCountryCode(memberList));
				order.setResponseValue("leader", order.getIsdn());
			}
			else
			{

				order.setShipTo(CommandUtil.addCountryCode(order.getParameters().getString("sms.params[0]", "")));
			}

			if (order.getActionType().equals(Constants.ACTION_REGISTER) && order.getShipTo().equals(""))
			{
				throw new AppException(Constants.ERROR_INVALID_SYNTAX);
			}
		}
		catch (Exception e)
		{
			throw e;
		}

	}

	public boolean validate(OrderRoutingInstance instance, ProductRoute orderRoute, CommandMessage order) throws Exception
	{
		CCWSConnection connection = null;

		if (order.getActionType().equals(Constants.ACTION_TOPUP))
		{
			String memberList = SubscriberProductImpl.getMemberList(order.getIsdn(), order.getUserName(), order.getProductId(), false);
			if (!"".equals(memberList))
			{
				order.setShipTo(CommandUtil.addCountryCode(memberList));
			}
		}

		if (!order.getShipTo().equals(""))
		{
			if (order.getIsdn().equals(order.getShipTo()))
			{
				throw new AppException(Constants.ERROR_INVALID_DELIVER);
			}

			try
			{
				connection = (CCWSConnection) instance.getProvisioningConnection();

				SubscriberRetrieve subRetrieve = connection.getSubscriber(order.getShipTo(), 1);

				if (!subRetrieve.getSubscriberData().getCurrentState().equals(Constants.BALANCE_STATE_ACTIVE))
				{
					throw new AppException(Constants.ERROR_DENIED_STATUS);
				}
			}
			catch (Exception e)
			{
				throw new AppException(Constants.ERROR_INVALID_DELIVER);
			}
			finally
			{
				instance.closeProvisioningConnection(connection);
			}
		}

		if (order.getActionType().equals(Constants.ACTION_REGISTER))
		{
			ProductEntry product = ProductFactory.getCache().getProduct(order.getProductId());

			if (SubscriberGroupImpl.isOwner(order.getIsdn(), order.getProductId(), product.getMemberType()))
			{
				throw new AppException(Constants.ERROR_INVALID_OWNER);
			}
		}
		else if (order.getActionType().equals(Constants.ACTION_UNREGISTER))
		{
			ProductEntry product = ProductFactory.getCache().getProduct(order.getProductId());

			if (!SubscriberGroupImpl.isOwner(order.getIsdn(), order.getProductId(), product.getMemberType())) // DuyMB
																												// fix
																												// 20120510
																												// them
																												// !
			{
				throw new AppException(Constants.ERROR_INVALID_OWNER);
				// throw new AppException(Constants.ERROR_OWNER_ONLY);
			}
		}

		return true;
	}

	/**
	 * Created by NamTA<br>
	 * Created Date: 07/06/2012
	 * 
	 * @param instance
	 * @param orderRoute
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public CommandMessage unregisterParser(OrderRoutingInstance instance, ProductRoute orderRoute, CommandMessage order)
			throws Exception
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
				subscriberProduct = SubscriberProductImpl.getUnterminated(order.getIsdn(), order.getProductId());
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
					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

					String expirationDate = dateFormat.format(subscriberProduct.getExpirationDate());
					String currentDate = dateFormat.format(new Date());

					if (expirationDate.compareTo(currentDate) < 0)
					{
						if (subscriberProduct.getSupplierStatus() == Constants.SUPPLIER_ACTIVE_STATUS)
						{
							order.setActionType(Constants.ACTION_SUPPLIER_DEACTIVE);

							order.setCause("");

							order.setStatus(Constants.ORDER_STATUS_PENDING);
						}
					}
					else
					{
						String graceDate = dateFormat.format(subscriberProduct.getGraceDate());

						if (graceDate.compareTo(currentDate) < 0)
						{
							order.setActionType(Constants.ACTION_CANCEL);

							order.setCause("");

							order.setStatus(Constants.ORDER_STATUS_PENDING);
						}
					}
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

		return order;
	}

}
