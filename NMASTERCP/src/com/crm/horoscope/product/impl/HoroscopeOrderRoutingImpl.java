package com.crm.horoscope.product.impl;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.crm.horoscope.sql.impl.HoroscopeImpl;
import com.crm.kernel.message.Constants;
import com.crm.product.cache.ProductEntry;
import com.crm.product.cache.ProductFactory;
import com.crm.product.cache.ProductRoute;
import com.crm.product.impl.VasgateRoutingImpl;
import com.crm.provisioning.message.CommandMessage;
import com.crm.provisioning.thread.OrderRoutingInstance;
import com.crm.subscriber.bean.SubscriberProduct;
import com.crm.subscriber.impl.SubscriberEntryImpl;
import com.crm.subscriber.impl.SubscriberProductImpl;
import com.fss.util.AppException;

public class HoroscopeOrderRoutingImpl extends VasgateRoutingImpl
{
	@Override
	public void smsParser(OrderRoutingInstance instance,
			ProductRoute orderRoute, CommandMessage order) throws Exception
	{
		// TODO Auto-generated method stub
		super.smsParser(instance, orderRoute, order);
		
		if (order.getActionType().equals(Constants.ACTION_REGISTER))
		{
			validateBirthday(order);
		}
	}
	
	public void validateBirthday(CommandMessage order) throws Exception
	{
		String dateValidatePattern = "\\s((3[0-1])|(0?[1-9])|(([1-2]?)([0-9])))(\\D)((1[0-2])|(0?[1-9]))$";
		
		Pattern p = Pattern.compile(dateValidatePattern);
		Matcher matcher = p.matcher(order.getKeyword().trim());
		
		String date = "";
		String month = "";
		
		if (matcher.find())
		{
			date = matcher.group(1);
			month = matcher.group(8);
			
			if (date.equals("") || month.equals(""))
			{
				throw new AppException(Constants.ERROR_INVALID_SYNTAX);
			}
			else
			{
				date = formatDateMonth(date);
				month = formatDateMonth(month);
				
				if ((date.equals("30") || date.equals("31")) && month.equals("02"))
				{
					throw new AppException(Constants.ERROR_INVALID_SYNTAX);
				}
				
				if (date.equals("31") && (month.equals("04") || month.equals("06") || month.equals("09") || month.equals("11")))
				{
					throw new AppException(Constants.ERROR_INVALID_SYNTAX);
				}
				
				if (date.equals("29") || date.equals("30") || date.equals("31"))
				{
					date = "28";
				}
				
				order.getParameters().setString("BirthdayOfSub", date + "/" + month);
			}
		}
		else
		{
			throw new AppException(Constants.ERROR_INVALID_SYNTAX);
		}
	}
	
	@Override
	public CommandMessage parser(OrderRoutingInstance instance,
			ProductRoute orderRoute, CommandMessage order) throws Exception
	{
		Exception error = null;

		ProductEntry product = null;

		SubscriberProduct subscriberProduct = null;

		try
		{
			/**
			 * Should check for both SMS & web.
			 */
			smsParser(instance, orderRoute, order);

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
			product = ProductFactory.getCache()
					.getProduct(order.getProductId());
			
			order.getParameters().setBoolean("includeCurrentDay", product.getParameters().getBoolean("includeCurrentDay", true));

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
				if (order.getActionType().equals(Constants.ACTION_REGISTER))
				{
					subscriberProduct = HoroscopeImpl.getUnterminated(
							order.getIsdn(), order.getProductId(), order.getParameters().getString("BirthdayOfSub"));
				}
				else
				{
					subscriberProduct = SubscriberProductImpl.getUnterminated(order.getIsdn(), order.getProductId());
				}
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
//			else
//			{
//				order.setAmount(order.getQuantity() * order.getPrice());
//			}

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
				String unsupported = orderRoute.getParameter("unsupport.prepaid", "false");
				if (unsupported.trim().toLowerCase().equals("true"))
					throw new AppException(Constants.ERROR_UNSUPPORT_PREPAID);
			}
			else if (order.getSubscriberType() == Constants.POSTPAID_SUB_TYPE)
			{
				String unsupported = orderRoute.getParameter("unsupport.postpaid", "false");
				if (unsupported.trim().toLowerCase().equals("true"))
					throw new AppException(Constants.ERROR_UNSUPPORT_POSTPAID);
			}

			// DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date currentDate = new Date();

			if ((order.getStatus() == Constants.ORDER_STATUS_DENIED)
					&& order.getCause()
							.equals(Constants.ERROR_NOT_ENOUGH_MONEY))
			{
				if (order.getActionType().equals(Constants.ACTION_TOPUP))
				{
					if (order.getRequestValue("first-action-type", "").equals(Constants.ACTION_SUBSCRIPTION))
					{
						order.setActionType(Constants.ACTION_SUBSCRIPTION);
					}
				}
				// Duymb add Autorenew for MAXI 24
				if (order.getActionType().equals(Constants.ACTION_AUTORENEW))
				{
					order.setActionType(Constants.ACTION_UNRENEW);
					order.setCause("");
					order.setStatus(Constants.ORDER_STATUS_PENDING);					
				}
				// DuyMB add end.
				if (order.getActionType().equals(Constants.ACTION_SUBSCRIPTION))
				{
					if (subscriberProduct
							.getExpirationDate().before(currentDate))
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
					|| order.getActionType().equals(Constants.ACTION_SUPPLIER_DEACTIVE))
			{
				if (subscriberProduct == null)
					throw new AppException(Constants.ERROR_SUBSCRIPTION_NOT_FOUND);

				boolean subscriptionNeverExpire = product.getParameters().getBoolean("subscription.neverExpire", false);

				/**
				 * <code>
				 * if subscription.neverExpire=true
				 * {
				 * 		case graceDate != null && graceDate < currentDate && actionType is de-active
				 * 			cancel subscription;
				 * 		case graceDate != null && graceDate < currentDate && is barring
				 * 			cancel subscription;
				 * 		case graceDate == null && actionType is de-active
				 * 			cancel subscription;
				 * }
				 * </code>
				 */
				if (!subscriptionNeverExpire && ((subscriberProduct.getGraceDate() == null
						&& order.getActionType().equals(Constants.ACTION_SUPPLIER_DEACTIVE))
						|| (subscriberProduct.getGraceDate() != null
								&& subscriberProduct.getGraceDate().before(currentDate)
								&& (order.getActionType().equals(Constants.ACTION_SUPPLIER_DEACTIVE)
										|| subscriberProduct.isBarring())
										)))
				{
					order.setActionType(Constants.ACTION_CANCEL);

					order.setCause("");

					order.setStatus(Constants.ORDER_STATUS_PENDING);
				}
				else if (subscriberProduct.isBarring() && !orderRoute.isTopupEnable()
						&& order.getActionType().equals(Constants.ACTION_SUBSCRIPTION))
				{
					order.setCause(Constants.ERROR_REGISTERED);

					order.setStatus(Constants.ORDER_STATUS_DENIED);
				}
			}
			
			if (order.getActionType().equals(Constants.ACTION_SUPPLIER_DEACTIVE)
					&& subscriberProduct.isBarring())
			{
				order.setCause(Constants.ERROR_SUBSCRIPTION_NOT_FOUND);
				order.setDescription("Is already suspended.");
				order.setStatus(Constants.ORDER_STATUS_DENIED);
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
		 * In case of subscription (or de-active) <br/>
		 * Unregister subscription if subscriber is Retired(S3) <br />
		 */
		if (order.getActionType().equals(Constants.ACTION_SUBSCRIPTION)
				|| order.getActionType().equals(Constants.ACTION_SUPPLIER_DEACTIVE))
		{
			String currentState = "";
			try
			{
				currentState = getSubscriberState(instance, orderRoute, product, order);
			}
			catch (Exception e)
			{

			}

			if (currentState.equals(Constants.BALANCE_STATE_RETIRED_S3))
			{
				order.setActionType(Constants.ACTION_CANCEL);
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
	
	public String formatDateMonth(String dateMonth)
	{
		String output = dateMonth;
		if (dateMonth.length() == 1)
			output = "0" + dateMonth;
		
		return output;
	}
}
