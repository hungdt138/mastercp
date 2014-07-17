/**
 * 
 */
package com.crm.product.impl;

import java.util.Calendar;
import java.util.Date;

import com.comverse_in.prepaid.ccws.BalanceEntity;
import com.comverse_in.prepaid.ccws.SubscriberEntity;
import com.comverse_in.prepaid.ccws.SubscriberRetrieve;
import com.crm.kernel.message.Constants;
import com.crm.product.cache.ProductEntry;
import com.crm.product.cache.ProductFactory;
import com.crm.product.cache.ProductPrice;
import com.crm.product.cache.ProductRoute;
import com.crm.provisioning.impl.ccws.CCWSCommandImpl;
import com.crm.provisioning.impl.ccws.CCWSConnection;
import com.crm.provisioning.message.CommandMessage;
import com.crm.provisioning.message.VNMMessage;
import com.crm.provisioning.thread.OrderRoutingInstance;
import com.crm.provisioning.util.CommandUtil;
import com.crm.provisioning.util.ResponseUtil;
import com.crm.subscriber.bean.SubscriberProduct;
import com.crm.subscriber.impl.SubscriberEntryImpl;
import com.crm.subscriber.impl.SubscriberProductImpl;
import com.crm.util.GeneratorSeq;
import com.crm.util.StringUtil;
import com.fss.util.AppException;

/**
 * @author hungdt
 * 
 */
public class ChargingOrderRoutingImpl extends VNMOrderRoutingImpl
{

	public void validateBalance(OrderRoutingInstance instance,
			ProductRoute orderRoute, ProductEntry product, VNMMessage vnmMessage)
			throws Exception
	{
		SubscriberEntity subscriberEntity = null;

		try
		{
			subscriberEntity = vnmMessage.getSubscriberEntity();

			BalanceEntity balance = CCWSConnection.getBalance(subscriberEntity,
					CCWSConnection.CORE_BALANCE);

			vnmMessage.setAvailableBalance(balance.getAvailableBalance());

			if (balance.getAvailableBalance() < product.getMinBalance())
			{

				// throw new AppException(Constants.ERROR_NOT_ENOUGH_MONEY);
			}
			else if ((product.getMaxBalance() > 0)
					&& (balance.getAvailableBalance() > product.getMaxBalance()))
			{
				throw new AppException(Constants.ERROR_BALANCE_TOO_LARGE);
			}
			else
			{
				Calendar calendar = Calendar.getInstance();

				calendar.setTime(new Date());

				calendar.add(Calendar.DATE, product.getMaxExpirationDays());
				boolean checkAccountBalance;
				checkAccountBalance = product.getParameter("checkCoreExpireDate", "false").equals("true");

				if (calendar.after(balance.getAccountExpiration())
						&& checkAccountBalance)
				{
					throw new AppException(Constants.ERROR_EXPIRE_TOO_LARGE);
				}
			}

			// set default price
			vnmMessage.setOfferPrice(product.getPrice());

			ProductPrice productPrice = product.getProductPrice(
					vnmMessage.getChannel(), vnmMessage.getActionType(),
					vnmMessage.getSegmentId(),
					vnmMessage.getAssociateProductId(),
					vnmMessage.getQuantity(), vnmMessage.getOrderDate());

			int quantity = 1;
			double fullOfCharge = product.getPrice();
			double baseOfCharge = product.getPrice();

			if (productPrice != null)
			{
				fullOfCharge = productPrice.getFullOfCharge();
				baseOfCharge = productPrice.getBaseOfCharge();
			}

			if (balance.getAvailableBalance() >= fullOfCharge)
			{
				vnmMessage.setPrice(fullOfCharge);
				vnmMessage.setFullOfCharge(true);
			}
			else if (balance.getAvailableBalance() < baseOfCharge)
			{
				// throw new AppException(Constants.ERROR_NOT_ENOUGH_MONEY);
			}
			else if (balance.getAvailableBalance() < fullOfCharge)
			{
				vnmMessage.setPrice(fullOfCharge);
				vnmMessage.setFullOfCharge(true);

				if (orderRoute.getParameter("charggw.chargtype", "").equals(Constants.CGW_ACTION_OFFLINE) &&
						!vnmMessage.getParameters().getString("fromreq", "").equals("FTP"))
				{
					throw new AppException(Constants.ERROR_NOT_ENOUGH_MONEY);
				}

				if (vnmMessage.getParameters().getString("fromreq", "").equals("FTP") && vnmMessage.getCgwStatus().equals("D")
						&& orderRoute.getParameter("charggw.chargtype", "").equals(Constants.CGW_ACTION_OFFLINE))
				{
					throw new AppException(Constants.ERROR_NOT_ENOUGH_MONEY);
				}
			}
			else if (orderRoute.isBaseChargeEnable())
			{
				vnmMessage.setFullOfCharge(false);
				vnmMessage.setPrice(baseOfCharge);

				quantity = (int) (balance.getAvailableBalance() / vnmMessage
						.getPrice());

				if (quantity == 0)
				{
					// throw new AppException(Constants.ERROR_NOT_ENOUGH_MONEY);
				}
			}
			else
			{

			}
			if (vnmMessage.getParameters().getString("balance.charging.core.type").equals("deduct"))
			{
				if (balance.getAvailableBalance() < vnmMessage.getParameters().getDouble("balance.charging.core.amount"))
				{
					throw new AppException(Constants.ERROR_NOT_ENOUGH_MONEY);
				}

			}
			if (vnmMessage.getSubmodifyBalance() != null && vnmMessage.getSubmodifyBalance().toLowerCase().contains("core"))
			{
				String[] balances = vnmMessage.getSubmodifyBalance().split(",");
				String[] subAmount = vnmMessage.getSubmodifyAmount().split(",");
				String _tempAmount = "";
				for (int i = 0; i < balances.length; i++)
				{
					if (balances[i].toLowerCase().equals("core"))
					{
						_tempAmount = subAmount[i];
						if (_tempAmount.startsWith("-"))
						{
							if (balance.getAvailableBalance() < Integer.parseInt(_tempAmount.substring(1)))
							{
								throw new AppException(Constants.ERROR_NOT_ENOUGH_MONEY);
							}

						}
					}
				}
			}

			vnmMessage.setQuantity(quantity);
			vnmMessage.setAmount(vnmMessage.getPrice()
					* vnmMessage.getQuantity());
		}
		catch (Exception e)
		{
			throw e;
		}
	}

	public CommandMessage checkBalance(OrderRoutingInstance instance,
			ProductRoute orderRoute, CommandMessage order) throws Exception
	{
		ProductEntry product = null;

		CCWSConnection connection = null;

		SubscriberRetrieve subscriberRetrieve = null;

		SubscriberEntity subscriberEntity = null;

		VNMMessage vnmMessage = CommandUtil.createVNMMessage(order);

		if ((instance.getDebugMode().equals("depend")))
		{
			simulation(instance, orderRoute, vnmMessage);
		}
		else
		{
			try
			{
				long productId = vnmMessage.getProductId();

				product = ProductFactory.getCache().getProduct(productId);

				connection = (CCWSConnection) instance
						.getProvisioningConnection();

				// get subscriber information in CCWS
				int queryLevel = orderRoute.getParameters().getInteger(
						"prepaid.queryLevel", 1);

				try
				{
					int sessionId = 0;
					try
					{
						sessionId = GeneratorSeq.getNextSeq();
					}
					catch (Exception e)
					{
					}
					String strRequest = (new CCWSCommandImpl())
							.getLogRequest(
									"com.comverse_in.prepaid.ccws.ServiceSoapStub.retrieveSubscriberWithIdentityNoHistory",
									vnmMessage.getIsdn());
					instance.debugMonitor("SEND:" + strRequest + ". ID="
							+ sessionId);
					Date startTime = new Date();
					subscriberRetrieve = connection.getSubscriber(
							vnmMessage.getIsdn(), queryLevel);
					Date endTime = new Date();
					String costTime = CommandUtil.calculateCostTime(startTime,
							endTime);
					if (subscriberRetrieve != null)
					{
						subscriberEntity = subscriberRetrieve
								.getSubscriberData();
						String strResponse = (new CCWSCommandImpl())
								.getLogResponse(subscriberEntity,
										vnmMessage.getIsdn());

						vnmMessage
								.setSubscriberType(Constants.PREPAID_SUB_TYPE); // DuyMB
																				// fixbug
																				// add
																				// 20130108

						instance.debugMonitor("RECEIVE:" + strResponse
								+ ". ID=" + sessionId + ". costTime="
								+ costTime);
					}
				}
				catch (Exception e)
				{
					// vnmMessage.setSubscriberType(SubscriberEntryImpl.getSubscriberType(vnmMessage.getIsdn()));
					// vnmMessage.setSubscriberType(Constants.POSTPAID_SUB_TYPE);
					vnmMessage.setSubscriberType(Constants.UNKNOW_SUB_TYPE);
				}
				finally
				{
					instance.closeProvisioningConnection(connection);
				}

				if (subscriberEntity == null)
				{
					if (vnmMessage.getSubscriberType() == Constants.PREPAID_SUB_TYPE)
					{
						throw new AppException(Constants.ERROR);
					}
				}
				else
				{
					vnmMessage.setSubscriberRetrieve(subscriberRetrieve);
					vnmMessage.setSubscriberType(Constants.PREPAID_SUB_TYPE);

					// Add balance info in response
					BalanceEntity[] balances = subscriberRetrieve
							.getSubscriberData().getBalances().getBalance();

					for (BalanceEntity balance : balances)
					{
						vnmMessage.setResponseValue(balance.getBalanceName()
								+ ".amount",
								StringUtil.format(balance.getBalance(), "#"));
						vnmMessage.setResponseValue(balance.getBalanceName()
								+ ".expireDate", StringUtil.format(balance
								.getAccountExpiration().getTime(),
								"dd/MM/yyyy HH:mm:ss"));
					}

					vnmMessage.setResponseValue(ResponseUtil.SERVICE_PRICE,
							StringUtil.format(product.getPrice(), "#"));
					// End edited

					validateCOS(instance, orderRoute, product, vnmMessage);

					validateState(instance, orderRoute, product, vnmMessage);

					validateBalance(instance, orderRoute, product, vnmMessage);
				}
			}
			catch (AppException e)
			{
				vnmMessage.setCause(e.getMessage());
				vnmMessage.setDescription(e.getContext());
				vnmMessage.setStatus(Constants.ORDER_STATUS_DENIED);
			}
			catch (Exception e)
			{
				throw e;
			}
			finally
			{
				if (vnmMessage != null)
				{
					vnmMessage.setSubscriberRetrieve(subscriberRetrieve);
				}

				instance.closeProvisioningConnection(connection);
			}
		}

		return (vnmMessage == null) ? order : vnmMessage;
	}

	public CommandMessage parser(OrderRoutingInstance instance,
			ProductRoute orderRoute, CommandMessage order) throws Exception
	{
		Exception error = null;

		ProductEntry product = null;

		SubscriberProduct subscriberProduct = null;

		try
		{
			// check SMS syntax
			Date startTime = new Date();
			Date endTime = new Date();

			/**
			 * Should check for both SMS & web.
			 */
			smsParser(instance, orderRoute, order);

			// check duplicate request
			if (orderRoute.getDuplicateScan() > 0)
			{
				startTime = new Date();
				checkDuplicate(instance, orderRoute, order);

				endTime = new Date();
				instance.debugMonitor("Check duplicate(" + order.getIsdn()
						+ ") cost time: "
						+ (endTime.getTime() - startTime.getTime()) + "ms");
			}

			if (orderRoute.getMaxRegisterDaily() > 0)
			{
				startTime = new Date();
				checkMaxRegister(instance, orderRoute, order);

				endTime = new Date();
				instance.debugMonitor("Check maxregisterdaily("
						+ order.getIsdn() + ")  cost time: "
						+ (endTime.getTime() - startTime.getTime()) + "ms");
			}
			// check promotion
			if (orderRoute.isCheckPromotion())
			{
				checkPromotion(instance, orderRoute, order);
			}

			// check product in available list
			product = ProductFactory.getCache()
					.getProduct(order.getProductId());

			// get current subscriber product
			if (!product.isSubscription())
			{

			}
			else if (order.getSubProductId() == Constants.DEFAULT_ID)
			{
				/**
				 * Edited: replaced getActive by getUnterminated (for barring
				 * subscription case)
				 */
				subscriberProduct = SubscriberProductImpl.getUnterminated(
						order.getIsdn(), order.getProductId());
			}
			else
			{
				subscriberProduct = SubscriberProductImpl.getProduct(order
						.getSubProductId());
			}

			// check action type
			checkActionType(instance, orderRoute, product, order,
					subscriberProduct);

			// validate
			if (orderRoute.isCheckBalance())
			{
				order = checkBalance(instance, orderRoute, order);
			}
			else
			{
				order.setAmount(order.getQuantity() * order.getPrice());
			}

			// Set subscriber type
			if (order.getSubscriberType() == Constants.UNKNOW_SUB_TYPE)
			{
				order.setSubscriberType(SubscriberEntryImpl
						.getSubscriberType(order.getIsdn()));
			}

			/**
			 * Check if sub type is supported or not
			 */
			if (order.getSubscriberType() == Constants.PREPAID_SUB_TYPE)
			{
				String unsupported = orderRoute.getParameter(
						"unsupport.prepaid", "false");
				if (unsupported.trim().toLowerCase().equals("true"))
					throw new AppException(Constants.ERROR_UNSUPPORT_PREPAID);
			}
			else if (order.getSubscriberType() == Constants.POSTPAID_SUB_TYPE)
			{
				ProductPrice productPrice = product.getProductPrice(
						order.getChannel(), order.getActionType(),
						order.getSegmentId(), order.getAssociateProductId(),
						order.getQuantity(), order.getOrderDate());

				int quantity = 1;

				order.setQuantity(quantity);
				if (productPrice != null)
				{
					order.setAmount(productPrice.getFullOfCharge() * order.getQuantity());
				}
				String unsupported = orderRoute.getParameter(
						"unsupport.postpaid", "false");
				if (unsupported.trim().toLowerCase().equals("true"))
					throw new AppException(Constants.ERROR_UNSUPPORT_POSTPAID);
			}

			// check for cgw
			String cgw_action = orderRoute
					.getParameter("charggw.chargtype", "");
			if (cgw_action.equals(Constants.CGW_ACTION_ONLINE))
			{

				if (order.getCgwStatus().equals(Constants.CGW_STATUS_D))
				{
					if (order.getSubscriberType() == 1)
					{
						order.setActionType("online-prepaid-d");
					}
					else if (order.getSubscriberType() == 2)
					{
						order.setActionType("online-postpaid-d");
					}

				}
				else if (order.getCgwStatus().equals(Constants.CGW_STATUS_U))
				{
					if (order.getSubscriberType() == 2)
					{
						order.setActionType("online-postpaid-u");
					}
					else if (order.getSubscriberType() == 1)
					{
						order.setActionType("online-prepaid-u");
					}
				}
			}
			else if (cgw_action.equals(Constants.CGW_ACTION_OFFLINE))
			{
				if (order.getCgwStatus().equals(Constants.CGW_STATUS_D))
				{
					if (order.getSubscriberType() == 1)
					{
						order.setActionType("offline-prepaid-d");
					}
					else if (order.getSubscriberType() == 2)
					{
						order.setActionType("offline-postpaid-d");
					}
				}
				else if (order.getCgwStatus().equals(Constants.CGW_STATUS_U))
				{
					if (order.getSubscriberType() == 1)
					{
						order.setActionType("offline-prepaid-u");
					}
					else if (order.getSubscriberType() == 2)
					{
						order.setActionType("offline-postpaid-u");
					}
				}
			}

			String balances = order.getParameters().getString("balances", "");
			if (balances.equals(""))
			{
				balances = order.getSubmodifyBalance();
			}

			if (balances != null && balances.toUpperCase().equals("CORE") && !order.getKeyword().contains("EXPIREDATE"))
			{
				order.setActionType("charge-core-balance");
			}

			// DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date currentDate = new Date();

			if ((order.getStatus() == Constants.ORDER_STATUS_DENIED)
					&& order.getCause()
							.equals(Constants.ERROR_NOT_ENOUGH_MONEY))
			{
				if (order.getActionType().equals(Constants.ACTION_TOPUP))
				{
					if (order.getRequestValue("first-action-type", "").equals(
							Constants.ACTION_SUBSCRIPTION))
					{
						order.setActionType(Constants.ACTION_SUBSCRIPTION);
					}
				}

				if (order.getActionType().equals(Constants.ACTION_SUBSCRIPTION))
				{
					if (subscriberProduct.getExpirationDate().before(
							currentDate))
					{
						if (subscriberProduct.getSupplierStatus() == Constants.SUPPLIER_ACTIVE_STATUS)
						{
							order.setActionType(Constants.ACTION_SUPPLIER_DEACTIVE);

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

			if (order.getActionType().equals(Constants.ACTION_SUBSCRIPTION)
					|| order.getActionType().equals(
							Constants.ACTION_SUPPLIER_DEACTIVE))
			{
				if (subscriberProduct == null)
					throw new AppException(
							Constants.ERROR_SUBSCRIPTION_NOT_FOUND);

				/**
				 *
				 */
				if ((subscriberProduct.getGraceDate() == null && order
						.getActionType().equals(
								Constants.ACTION_SUPPLIER_DEACTIVE))
						|| (subscriberProduct.getGraceDate() != null
								&& subscriberProduct.getGraceDate().before(
										currentDate) && (order.getActionType()
								.equals(Constants.ACTION_SUPPLIER_DEACTIVE) || subscriberProduct
								.isBarring())))
				{
					order.setActionType(Constants.ACTION_CANCEL);

					order.setCause("");

					order.setStatus(Constants.ORDER_STATUS_PENDING);
				}
				else if (subscriberProduct.isBarring()
						&& !orderRoute.isTopupEnable()
						&& order.getActionType().equals(
								Constants.ACTION_SUBSCRIPTION))
				{
					order.setCause(Constants.ERROR_REGISTERED);

					order.setStatus(Constants.ORDER_STATUS_DENIED);
				}
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

		/**
		 * In case of subscription <br/>
		 * Unregister subscription if subscriber is Retired(S3) <br />
		 * Or if subscriber can not validate and current date > grace date
		 */
		if (order.getActionType().equals(Constants.ACTION_SUBSCRIPTION))
		{
			if (subscriberProduct == null)
			{
				order.setStatus(Constants.ORDER_STATUS_DENIED);
				order.setCause(Constants.ERROR_SUBSCRIPTION_NOT_FOUND);
			}

			String currentState = "";
			Date currentDate = new Date();
			try
			{
				currentState = getSubscriberState(instance, orderRoute,
						product, order);
			}
			catch (Exception e)
			{

			}

			if (currentState.equals(Constants.BALANCE_STATE_RETIRED_S3)
					|| order.getSubscriberType() == Constants.UNKNOW_SUB_TYPE
					|| ((subscriberProduct.getGraceDate() == null || (subscriberProduct
							.getGraceDate() != null && subscriberProduct
							.getGraceDate().before(currentDate))) && order
							.getStatus() == Constants.ORDER_STATUS_DENIED))
			{
				order.setActionType(Constants.ACTION_UNREGISTER);
				order.setDescription(order.getCause());
				order.setCause("");
				/**
				 * unregister for all subtype = prepaid subtype
				 */
				order.setSubscriberType(Constants.PREPAID_SUB_TYPE);
				order.setStatus(Constants.ORDER_STATUS_PENDING);
			}
		}

		if ((error != null) && !(error instanceof AppException))
		{
			throw error;
		}

		return order;
	}
}
