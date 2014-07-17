package com.crm.product.impl;


import java.util.Date;

import com.crm.kernel.message.Constants;
import com.crm.product.cache.ProductEntry;
import com.crm.product.cache.ProductFactory;
import com.crm.product.cache.ProductPrice;
import com.crm.product.cache.ProductRoute;
//import com.crm.provisioning.impl.nmschargingapi.NMSCharingConnection;
import com.crm.provisioning.message.CommandMessage;
import com.crm.provisioning.message.VNMMessage;
import com.crm.provisioning.thread.OrderRoutingInstance;
import com.crm.provisioning.util.CommandUtil;
import com.crm.subscriber.bean.SubscriberGoService;
import com.crm.subscriber.bean.SubscriberProduct;
import com.crm.subscriber.impl.SubscriberProductImpl;
import com.crm.util.DateUtil;
import com.crm.util.GeneratorSeq;
import com.fss.util.AppException;

public class GoRoutingImpl  extends OrderRoutingImpl 
{
//	// Get number of answere on subscriber product
//	public int countAnswereInDay(String isdn, long productid, Date orderdate)
//	{
//		int result = 0;
//		
//		return result;
//	}
//	// Return: have valid question for subs
//	public boolean checkValidQuestion(String isdn, long productid, Date orderdate)
//	{
//		boolean isValid = false;
//		
//		return isValid;
//	}
//	// Radom question 
//	public long diceQuestions(String isdn, long productid)
//	{
//		long questionId = 0;
//		
//		return questionId;
//	}
//	public void updateSubscriberProduct(String isdn, long productId)
//	{
//		
//	}
//	public boolean isFirstRegist(String isdn, long productId)
//	{
//		boolean first = false;
//		
//		return first;
//	}
	// //////////////////////////////////////////////////////
	// check class of service and compare with list of denied COS
	// Author : ThangPV
	// Created Date : 16/09/2004
	// //////////////////////////////////////////////////////
	@Override
	public String getSubscriberState(OrderRoutingInstance instance, ProductRoute orderRoute, ProductEntry product,
			CommandMessage request) throws Exception
	{
		// TODO Auto-generated method stub
		if (request instanceof VNMMessage)
			return ((VNMMessage)request).getSubscriberEntity() == null ? "" : ((VNMMessage)request).getSubscriberEntity().getCurrentState();
		else
			return super.getSubscriberState(instance, orderRoute, product, request);
	}

	// //////////////////////////////////////////////////////
	// check class of service and compare with list of denied COS
	// Author : ThangPV
	// Created Date : 16/09/2004
	// //////////////////////////////////////////////////////
	@Override
	public String getSubscriberCOS(OrderRoutingInstance instance, ProductRoute orderRoute, ProductEntry product,
			CommandMessage request) throws Exception
	{
		if (request instanceof VNMMessage)
			return ((VNMMessage)request).getSubscriberEntity() == null ? "" : ((VNMMessage)request).getSubscriberEntity().getCOSName();
		else
			return super.getSubscriberCOS(instance, orderRoute, product, request);
	}

	// //////////////////////////////////////////////////////
	// check class of service and compare with list of denied COS
	// Author : ThangPV
	// Created Date : 16/09/2004
	// //////////////////////////////////////////////////////
	public void validateBalance(
			OrderRoutingInstance instance, ProductRoute orderRoute, ProductEntry product, CommandMessage vnmMessage, long balanceAmount)
			throws Exception
	{
		SubscriberGoService subscriberGoService = null;
		try
		{
			if (vnmMessage.getActionType().equals(Constants.ACTION_CONFIRM) || 
				vnmMessage.getActionType().equals(Constants.ACTION_BET))
			{
				subscriberGoService = SubscriberProductImpl.getGoServiceData(vnmMessage.getIsdn(), product.getProductId());
				// Check valid question
				if (subscriberGoService == null)
				{
					throw new AppException(Constants.ERROR_INVALID_CONFIRM);
				}
				else if (subscriberGoService.getNumOfQuestion() >= Integer.valueOf(product.getParameter("MaxQuestion", "10")))
				{
					throw new AppException(Constants.ERROR_OVER_CONFIRM);
				}
				else if (!SubscriberProductImpl.isSendQuestion(vnmMessage.getIsdn(), product.getProductId()))
				{
					throw new AppException(Constants.ERROR_INVALID_CONFIRM);
				}
				else
				{
					// Set quanlity
					vnmMessage.setQuantity(subscriberGoService.getNumOfQuestion() + 1);
					vnmMessage.getParameters().setLong("QuestionId",subscriberGoService.getLastQuestionId());
					vnmMessage.getParameters().setLong("Score",subscriberGoService.getScore());
					vnmMessage.getParameters().setLong("NumerOfQuestion",subscriberGoService.getNumOfQuestion() + 1);
					if (vnmMessage.getActionType().equals(Constants.ACTION_BET))
					{
						vnmMessage.getParameters().setLong("BetRate", Long.valueOf(product.getParameter("BetRate", "1")));
					}
					else
					{
						vnmMessage.getParameters().setLong("BetRate", 1);
					}
					vnmMessage.getParameters().setLong("RewardPoint", Long.valueOf(product.getParameter("RewardPoint", "100")));
				}
			}

			// set default price
			vnmMessage.setOfferPrice(product.getPrice());

			ProductPrice productPrice =
					product.getProductPrice(
							vnmMessage.getChannel(), vnmMessage.getActionType(), vnmMessage.getSegmentId()
							, vnmMessage.getAssociateProductId(), vnmMessage.getQuantity(), vnmMessage.getOrderDate());

			int quantity = 1;
			double fullOfCharge = product.getPrice();
			double baseOfCharge = product.getPrice();

			if (productPrice != null)
			{
				fullOfCharge = productPrice.getFullOfCharge();
				baseOfCharge = productPrice.getBaseOfCharge();
			}
			if (vnmMessage.getParameters().getString("FreeForFirstTime").equals("true")) // Duymb Add GO 20140401
			{
				vnmMessage.setPrice(0);
			}
			else if (balanceAmount >= fullOfCharge)
			{
				vnmMessage.setPrice(fullOfCharge);
				vnmMessage.setFullOfCharge(true);
			}
			else if (balanceAmount < fullOfCharge && balanceAmount >= baseOfCharge && vnmMessage.getActionType().equals(Constants.ACTION_BET))
			{
				vnmMessage.setPrice(baseOfCharge);
				vnmMessage.getParameters().setLong("BetRate", 1);
			}
			else if (balanceAmount < baseOfCharge)
			{
				throw new AppException(Constants.ERROR_NOT_ENOUGH_MONEY);
			}
			else if (orderRoute.isBaseChargeEnable())
			{
				vnmMessage.setFullOfCharge(false);
				vnmMessage.setPrice(baseOfCharge);

				quantity = (int) (balanceAmount / vnmMessage.getPrice());

				if (quantity == 0)
				{
					throw new AppException(Constants.ERROR_NOT_ENOUGH_MONEY);
				}
			}
			else
			{
				throw new AppException(Constants.ERROR_NOT_ENOUGH_MONEY);
			}
			
			vnmMessage.setQuantity(quantity);
			vnmMessage.setAmount(vnmMessage.getPrice() * vnmMessage.getQuantity());
			
			if (vnmMessage.getAmount()== product.getPrice())
			{
				vnmMessage.getParameters().setString("BonusPoint","50");
				vnmMessage.getParameters().setString("BonusSms","5");				
			}
			if (vnmMessage.isPaid())
			{
				vnmMessage.getParameters().setString("BonusPoint","0");
				vnmMessage.setQuantity(0);
				vnmMessage.setAmount(0);
			}
		}
		catch (Exception e)
		{
			throw e;
		}
	}

	public CommandMessage checkBalance(OrderRoutingInstance instance, ProductRoute orderRoute, CommandMessage order)
			throws Exception
	{
		ProductEntry product = null;

		//CCWSConnection connection = null;
//		NMSCharingConnection connection = null;
//		SubscriberRetrieve subscriberRetrieve = null;

//		SubscriberEntity subscriberEntity = null;
		long balanceAmount = -1;
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

//				connection = (NMSCharingConnection) instance.getProvisioningConnection();

				// get subscriber information in CCWS
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
					//instance.debugMonitor("SEND:" + strRequest + ". ID="							+ sessionId);
//					balanceAmount = connection.getBalance(order.getIsdn());
					if (balanceAmount > 0)
					{						
						vnmMessage.setSubscriberType(Constants.PREPAID_SUB_TYPE); // DuyMB fixbug add 20130108
//						instance.debugMonitor("RECEIVE:" + strResponse + ". ID=" + sessionId + ". costTime=" + costTime);
					}
					else 
					{
						// treat as postpaid or case not enough money?
						balanceAmount = Integer.MAX_VALUE;
						vnmMessage.setSubscriberType(Constants.PREPAID_SUB_TYPE); // DuyMB fixbug add 20130108
					}
				}
				catch (Exception e)
				{
					//vnmMessage.setSubscriberType(SubscriberEntryImpl.getSubscriberType(vnmMessage.getIsdn()));
					//vnmMessage.setSubscriberType(Constants.POSTPAID_SUB_TYPE);
					vnmMessage.setSubscriberType(Constants.UNKNOW_SUB_TYPE);
				}
				finally
				{
//					instance.closeProvisioningConnection(connection);
				}

				if (balanceAmount > 0)
				{
//					vnmMessage.setSubscriberRetrieve(subscriberRetrieve);
					vnmMessage.setSubscriberType(Constants.PREPAID_SUB_TYPE);
					

//					Calendar currentActiveDate = subscriberEntity.getDateEnterActive();
//
//					Calendar minActiveDate = Calendar.getInstance();
//					minActiveDate.set(1900, 1, 1);
//					Calendar maxActiveDate = Calendar.getInstance();
//					maxActiveDate.set(2100, 1, 1);
//					try
//					{
//						Date actDate = product.getParameters().getDate(vnmMessage.getActionType() + ".minActiveDate", "dd/MM/yyyy");
//						
//						if (actDate != null)
//							minActiveDate.setTime(actDate);
//					}
//					catch (Exception e)
//					{
//					}
//					
//					try
//					{
//						Date actDate = product.getParameters().getDate(vnmMessage.getActionType() + ".maxActiveDate", "dd/MM/yyyy");
//						if (actDate != null)
//							maxActiveDate.setTime(actDate);
//					}
//					catch (Exception e)
//					{
//					}
//					
//					if (minActiveDate.after(currentActiveDate) || maxActiveDate.before(currentActiveDate))
//					{
//						throw new AppException(Constants.ERROR_INVALID_ACTIVE_DATE);
//					}
//
//					// Add balance info in response
//					BalanceEntity[] balances = subscriberRetrieve.getSubscriberData().getBalances().getBalance();
//
//					for (BalanceEntity balance : balances)
//					{
//						vnmMessage.setResponseValue(balance.getBalanceName() + ".amount",
//								StringUtil.format(balance.getBalance(), "#"));
//						vnmMessage.setResponseValue(balance.getBalanceName() + ".expireDate",
//								StringUtil.format(balance.getAccountExpiration().getTime(), "dd/MM/yyyy HH:mm:ss"));
//					}
//
//					vnmMessage.setResponseValue(ResponseUtil.SERVICE_PRICE, StringUtil.format(product.getPrice(), "#"));
//					// End edited

					validateCOS(instance, orderRoute, product, vnmMessage);

					validateState(instance, orderRoute, product, vnmMessage);

					validateBalance(instance, orderRoute, product, vnmMessage,balanceAmount);
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
//				instance.closeProvisioningConnection(connection);
			}
		}

		return (vnmMessage == null) ? order : vnmMessage;
	}

	public void checkActionType(OrderRoutingInstance instance,ProductRoute orderRoute, ProductEntry product,
								CommandMessage order, SubscriberProduct subscriberProduct)throws Exception
	{
		if (subscriberProduct != null)
		{
			if (order.getActionType().equals(Constants.ACTION_REGISTER) || order.getActionType().equals(Constants.ACTION_SUBSCRIPTION))
			{
				Date now = new Date();
				if(DateUtil.compareDate(
						subscriberProduct.getExpirationDate(), now) >= 0)
				{
					throw new AppException(Constants.ERROR_REGISTERED);
				}
			}
			order.setSubProductId(subscriberProduct.getSubProductId());			
		}
		
		// Mien phi cho lan dang ky dau tien.
		if (order.getActionType().equals(Constants.ACTION_REGISTER) 
			&& SubscriberProductImpl.isFirstTimeRegister(order.getIsdn(), product.getProductId()))
		{
			order.getParameters().setString("FreeForFirstTime", "true");
			order.getParameters().setString("BonusPoint","0");			
		}
		else if (order.getActionType().equals(Constants.ACTION_REGISTER) && 
				SubscriberProductImpl.isSecondTimeInDay(order.getIsdn(), product.getProductId()))
		{
			order.getParameters().setString("BonusPoint","50");
			order.getParameters().setString("UseredQuestion","0");
		}
//		else if((order.getActionType().equals(Constants.ACTION_REGISTER) || order.getActionType().equals(Constants.ACTION_SUBSCRIPTION))
//				&& subscriberProduct != null)
//		{
//			Date now = new Date();
//			if(DateUtil.compareDate(
//					subscriberProduct.getExpirationDate(), now) >= 0)
//			{
//				throw new AppException(Constants.ERROR_REGISTERED);
//			}
//		}
		else if (order.getActionType().equals(Constants.ACTION_REGISTER) && 
				 SubscriberProductImpl.isUnregisterInDay(order.getIsdn(), product.getProductId()) &&
				 subscriberProduct == null)
		{
			order.setPaid(true);
			order.getParameters().setString("UseredQuestion", "5");
			
		}
		else if(order.getActionType().equals(Constants.ACTION_UNREGISTER) && subscriberProduct == null)
		{
			throw new AppException(Constants.ERROR_UNREGISTERED);
		}
			
	}
}
