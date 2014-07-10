package com.crm.product.impl;

import java.util.Calendar;
import java.util.Date;

import com.comverse_in.prepaid.ccws.BalanceEntity;
import com.comverse_in.prepaid.ccws.SubscriberEntity;
import com.crm.kernel.message.Constants;
import com.crm.product.cache.ProductEntry;
import com.crm.product.cache.ProductPrice;
import com.crm.product.cache.ProductRoute;
import com.crm.provisioning.impl.ccws.CCWSCommandImpl;
import com.crm.provisioning.impl.ccws.CCWSConnection;
import com.crm.provisioning.message.CommandMessage;
import com.crm.provisioning.message.VNMMessage;
import com.crm.provisioning.thread.OrderRoutingInstance;
import com.crm.provisioning.util.CommandUtil;
import com.crm.subscriber.bean.SubscriberProduct;
import com.crm.subscriber.impl.SubscriberGroupImpl;
import com.crm.subscriber.impl.SubscriberProductImpl;
import com.fss.util.AppException;

public class F5OrderRoutingImpl extends Biz30OrderRoutingImpl
{
	@Override
	public void smsParser(OrderRoutingInstance instance, ProductRoute orderRoute, CommandMessage order) throws Exception
	{
		// TODO Auto-generated method stub
		super.smsParser(instance, orderRoute, order);

		/**
		 * Get current member.
		 */
		String currentMemberlist = SubscriberProductImpl
				.getMemberList(order.getIsdn(), "", orderRoute.getProductId(), false);

		order.setRequestValue("f5-current-member", currentMemberlist);

		/**
		 * Make modifying member list more clear: add country code, force
		 * separate char is comma ...
		 */
		if (order.getActionType().equals(Constants.ACTION_ADD_MEMBER) ||
				order.getActionType().equals(Constants.ACTION_REMOVE_MEMBER))
		{
			String modifiedMembers = order.getKeyword().replaceFirst(
					orderRoute.getKeyword(), "").trim();
			String[] arrayOfModifiedMembers = modifiedMembers.split("[,.;\\s]");
			String modifiedMemberList = "";
			for (int i = 0; i < arrayOfModifiedMembers.length; i++)
			{
				if (arrayOfModifiedMembers[i].trim().equals(""))
				{
					continue;
				}

				modifiedMemberList += "," + CommandUtil.addCountryCode(arrayOfModifiedMembers[i].trim());
			}
			while (modifiedMemberList.startsWith(","))
				modifiedMemberList = modifiedMemberList.substring(1);
			if (modifiedMemberList.equals(""))
				throw new AppException(Constants.ERROR_INVALID_SYNTAX);

			if (!CCWSCommandImpl.isValidateSub(modifiedMemberList.split(",")))
			{
				throw new AppException(Constants.ERROR_SUBSCRIBER_NOT_FOUND);
			}

			order.setRequestValue("f5-modify-member", modifiedMemberList);

			if (modifiedMemberList.contains(order.getIsdn()))
			{
				throw new AppException(Constants.ERROR_INVALID_DELIVER);
			}
		}

		if (order.getActionType().equals(Constants.ACTION_REMOVE_MEMBER))
		{
			String currentPhoneBookList = order.getRequestValue("f5-current-member", "");

			String removeMembers = order.getRequestValue("f5-modify-member", "");

			String[] arrayOfRemoveMembers = removeMembers.split(",");
			/**
			 * throw existed-member error if there is any remove member not in
			 * current members
			 */
			for (String removeMember : arrayOfRemoveMembers)
			{
				if (!currentPhoneBookList.contains(removeMember))
				{
					throw new AppException(Constants.ERROR_EXSITED_MEMBER);
				}
				else
				{
					currentPhoneBookList = currentPhoneBookList.replaceAll(removeMember, "");
				}
			}

			order.setRequestValue("f5-member", currentPhoneBookList);
		}
	}

	@Override
	public void checkActionType(OrderRoutingInstance instance, ProductRoute orderRoute, ProductEntry product,
			CommandMessage order, SubscriberProduct subscriberProduct) throws Exception
	{
		if (order.getActionType().equals(Constants.ACTION_ADD_MEMBER)
				|| order.getActionType().equals(Constants.ACTION_REMOVE_MEMBER))
		{
			if (subscriberProduct == null)
			{
				throw new AppException(Constants.ERROR_SUBSCRIPTION_NOT_FOUND);
			}

			if (!subscriberProduct.isActive() || subscriberProduct.isExpired())
			{
				throw new AppException(Constants.ERROR_SUBSCRIPTION_NOT_FOUND);
			}
		}

		super.checkActionType(instance, orderRoute, product, order, subscriberProduct);
	}

	@Override
	public void validateBalance(OrderRoutingInstance instance, ProductRoute orderRoute, ProductEntry product,
			VNMMessage vnmMessage) throws Exception
	{
		SubscriberEntity subscriberEntity = null;

		try
		{
			subscriberEntity = vnmMessage.getSubscriberEntity();

			BalanceEntity balance = CCWSConnection.getBalance(subscriberEntity, CCWSConnection.CORE_BALANCE);

			if (balance.getAvailableBalance() < product.getMinBalance())
			{
				throw new AppException(Constants.ERROR_NOT_ENOUGH_MONEY);
			}
			else if ((product.getMaxBalance() > 0) && (balance.getAvailableBalance() > product.getMaxBalance()))
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

				if (calendar.after(balance.getAccountExpiration()) && checkAccountBalance)
				{
					throw new AppException(Constants.ERROR_EXPIRE_TOO_LARGE);
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

			if (vnmMessage.getActionType().equals(Constants.ACTION_ADD_MEMBER))
			{
				int totalMemberAdded = SubscriberGroupImpl.countMember(vnmMessage.getIsdn(), product.getProductId(),
						product.getMemberType(), true);

				String addingMembers = vnmMessage.getRequestValue("f5-modify-member", "");

				String[] arrayOfAddingMembers = addingMembers.split(",");

				String currentPhoneBookList = vnmMessage.getRequestValue("f5-current-member", "");

				String[] arrayOfCurrentPhoneBookList = currentPhoneBookList.split(",");

				int currentMemberCount = arrayOfCurrentPhoneBookList.length;

				if (arrayOfAddingMembers.length + currentMemberCount > product.getMaxMembers())
				{
					throw new AppException(Constants.ERROR_OVER_MEMBER_LIMITATION);
				}

				for (int i = 0; i < arrayOfAddingMembers.length; i++)
				{
					if (currentPhoneBookList.contains(arrayOfAddingMembers[i]))
					{
						throw new AppException(Constants.ERROR_EXSITED_MEMBER);
					}
				}

				quantity = (totalMemberAdded + arrayOfAddingMembers.length) - product.getMaxMembers();
				if (quantity < 0)
					quantity = 0;
				if (quantity > arrayOfAddingMembers.length)
					quantity = arrayOfAddingMembers.length;

				// vnmMessage.setQuantity(quantity);
				vnmMessage.setPrice(fullOfCharge);

				if (balance.getAvailableBalance() < (quantity * fullOfCharge))
				{
					throw new AppException(Constants.ERROR_NOT_ENOUGH_MONEY);
				}

				String[] arrayOfPhoneBookList = new String[arrayOfCurrentPhoneBookList.length + arrayOfAddingMembers.length];

				System.arraycopy(arrayOfCurrentPhoneBookList, 0, arrayOfPhoneBookList, 0, arrayOfCurrentPhoneBookList.length);
				System.arraycopy(arrayOfAddingMembers, 0, arrayOfPhoneBookList, arrayOfCurrentPhoneBookList.length,
						arrayOfAddingMembers.length);

				String phoneBookList = "";
				if (arrayOfPhoneBookList.length > 0)
					phoneBookList = arrayOfPhoneBookList[0];
				for (int i = 1; i < arrayOfPhoneBookList.length; i++)
				{
					phoneBookList = phoneBookList + "," + arrayOfPhoneBookList[i];
				}

				// vnmMessage.setRequestValue("f5-new-member", addingMembers);
				vnmMessage.setRequestValue("f5-member", phoneBookList);
			}
			else
			{
				if (vnmMessage.getActionType().equals(Constants.ACTION_TOPUP))
				{
					String currentPhoneBookList = SubscriberProductImpl
							.getMemberList(vnmMessage.getIsdn(), "", orderRoute.getProductId(), false);
					vnmMessage.setRequestValue("f5-member", currentPhoneBookList);
				}

				if (balance.getAvailableBalance() >= fullOfCharge)
				{
					vnmMessage.setPrice(fullOfCharge);
					vnmMessage.setFullOfCharge(true);
				}
				else if (balance.getAvailableBalance() < baseOfCharge)
				{
					throw new AppException(Constants.ERROR_NOT_ENOUGH_MONEY);
				}
				else if (orderRoute.isBaseChargeEnable())
				{
					vnmMessage.setFullOfCharge(false);
					vnmMessage.setPrice(baseOfCharge);

					quantity = (int) (balance.getAvailableBalance() / vnmMessage.getPrice());

					if (quantity == 0)
					{
						throw new AppException(Constants.ERROR_NOT_ENOUGH_MONEY);
					}
				}
				else
				{
					throw new AppException(Constants.ERROR_NOT_ENOUGH_MONEY);
				}
			}

			vnmMessage.setQuantity(quantity);
			vnmMessage.setAmount(vnmMessage.getPrice() * vnmMessage.getQuantity());
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}
