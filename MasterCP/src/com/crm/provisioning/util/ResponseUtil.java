/**
 * 
 */
package com.crm.provisioning.util;

import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.crm.kernel.domain.DomainFactory;
import com.crm.kernel.message.Constants;
import com.crm.product.cache.ProductEntry;
import com.crm.product.cache.ProductFactory;
import com.crm.product.cache.ProductMessage;
import com.crm.product.cache.ProductRoute;
import com.crm.provisioning.message.CommandMessage;
import com.crm.question.bean.ContentQuestion;
import com.crm.question.impl.ContentQuestionImpl;
import com.crm.subscriber.impl.SubscriberOrderImpl;
import com.crm.thread.DispatcherInstance;
import com.crm.util.StringUtil;
import com.fss.util.AppException;

/**
 * @author ThangPV
 * 
 */
public class ResponseUtil
{
	public static String	COS_CURRENT			= "cos.current";
	public static String	COS_NEW				= "cos.new";
	public static String	SERVICE_BALANCE		= "service.balance";
	public static String	SERVICE_AMOUNT		= "service.amount";
	public static String	SERVICE_PRICE		= "service.price";
	public static String	SERVICE_DAYS		= "service.days";
	public static String	SERVICE_ACTIVE_DAYS	= "service.activeDays";
	public static String	SERVICE_START_DATE	= "service.startDate";
	public static String	SERVICE_EXPIRE_DATE	= "service.expireDate";
	public static String	SERVICE_ACTIVE_DATE	= "service.activeDate";
	public static String	LEADER				= "leader";
	public static String	REFERAL				= "referal";
	public static String	PHONEBOOK			= "phonebook";
	public static String	FRIEND_OLD			= "friend.old";
	public static String	FRIEND_NEW			= "friend.new";
	public static String	MEMBER				= "member";
	public static String	MEMBER_FREE			= "member.free";

	/**
	 * add static fields.
	 * 
	 * Edited by NamTA
	 */
	public static String	ACCOUNT_STATE		= "account.state";
	public static String	BALANCES			= "balances";
	public static String	TIMEOUT				= "timeout";
	public static String	SESSION_ID			= "sessionId";
	public static String	VALUE				= "Value";

	public static String	AMOUNT_UNBILL		= "unbillAmount";
	public static String	AMOUNT_OUTSTANDING	= "outstandingAmount";
	public static String	AMOUNT_LAST_PAYMENT	= "lastPaymentAmount";

	public static String	VAS					= "VAS";
	public static String	SMS_HREF			= "smsHref";
	public static String	SMS_TEXT			= "smsText";
	public static String	SMS_TYPE			= "smsType";
	public static String	SMS_CMD_CHECK		= "smsCmdCheck";

	public static String formatResponse(
			DispatcherInstance instance, ProductEntry product, CommandMessage request, String actionType, String template)
	{
		String content = template;

		try
		{
			if (product != null)
			{
				content = content.replaceAll("~PRODUCT_ALIAS~", product.getIndexKey());
				content = content.replaceAll("~PRODUCT_TITLE~", product.getTitle());
				content = content.replaceAll("~PRODUCT_START_DATE~", String.format(Constants.DATE_FORMAT, product.getStartDate()));
				content = content.replaceAll("~PRODUCT_EXPIRE_DATE~", String.format(Constants.DATE_FORMAT, product.getExpirationDate()));
			}

			Calendar now = Calendar.getInstance();
			content = content.replaceAll("~NOW~", String.format(Constants.DATE_FORMAT, now.getTime()));
			now.add(Calendar.DAY_OF_MONTH, 1);
			content = content.replaceAll("~NEXT_DATE~", String.format(Constants.DATE_FORMAT, now.getTime()));

			content = content.replaceAll("~ISDN~", request.getIsdn());
			content = content.replaceAll("~SHIP_TO~", request.getShipTo());
			content = content.replaceAll("~PRICE~", String.valueOf(request.getPrice()));
			content = content.replaceAll("~QUANTITY~", String.valueOf(request.getQuantity()));
			content = content.replaceAll("~CHARGING_AMOUNT~", String.valueOf(request.getAmount()));
			content = content.replaceAll("~DIEM~", String.valueOf((int) request.getScore()));
			
			

			// content = content.replaceAll("~CUR_BALANCE_AMOUNT~",
			// request.getResponseValue("balance.amount"));
			// content = content.replaceAll("~CUR_BALANCE_EXPIRE~",
			// request.getResponseValue("balance.expireDate"));

			/**
			 * Begin balance info replacement
			 * 
			 * Created by NamTA
			 */

			Pattern balancePattern = Pattern.compile("~CUR_BALANCE_([a-zA-Z0-9_]+)~");
			Matcher balanceMatcher = balancePattern.matcher(content);
			while (balanceMatcher.find())
			{
				String matchedString = balanceMatcher.group().replace("~", "");

				String property = matchedString.replace("CUR_BALANCE_", "");

				if (matchedString.endsWith("EXPIRE"))
				{
					property = property.replace("_EXPIRE", "") + ".expireDate";
				}
				else if (matchedString.endsWith("START"))
				{
					property = property.replace("_START", "") + ".startDate";
				}
				else if (matchedString.endsWith("AMOUNT"))
				{
					property = property.replace("_AMOUNT", "") + ".amount";
				}
				else
				{
					continue;
				}
				content = content.replaceAll(balanceMatcher.group(), request.getResponseValue(property));
			}

			/**
			 * End
			 */

			content = content.replaceAll("~CURRENT_COS~", request.getResponseValue(COS_CURRENT));
			content = content.replaceAll("~NEW_COS~", request.getResponseValue(COS_NEW));

			content = content.replaceAll("~SERVICE_BALANCE~", request.getResponseValue(SERVICE_BALANCE));
			content = content.replaceAll("~SERVICE_AMOUNT~", request.getResponseValue(SERVICE_AMOUNT));

			content = content.replaceAll("~SERVICE_PRICE~", request.getResponseValue(SERVICE_PRICE));

			content = content.replaceAll("~SERVICE_DAYS~", request.getResponseValue(SERVICE_DAYS));
			content = content.replaceAll("~SERVICE_START_DATE~", request.getResponseValue(SERVICE_START_DATE));
			content = content.replaceAll("~SERVICE_EXPIRE_DATE~", request.getResponseValue(SERVICE_EXPIRE_DATE));
			content = content.replaceAll("~SERVICE_ACTIVE_DATE~", request.getResponseValue(SERVICE_ACTIVE_DATE));

			// subscription
			content = content.replaceAll("~SUBSCRIPTION_REMAIN_DAYS~", request.getResponseValue(SERVICE_ACTIVE_DAYS));
			content = content.replaceAll("~SUBSCRIPTION_EXPIRE_DATE~", request.getResponseValue(SERVICE_EXPIRE_DATE));

			// phone book list
			content = content.replaceAll("~LEADER~", request.getResponseValue(LEADER));
			content = content.replaceAll("~REFERAL~", request.getResponseValue(REFERAL));
			content = content.replaceAll("~PHONE_BOOK~", request.getResponseValue(PHONEBOOK));

			// friend
			content = content.replaceAll("~OLD_FRIEND~", request.getResponseValue(FRIEND_OLD));
			content = content.replaceAll("~NEW_FRIEND~", request.getResponseValue(FRIEND_NEW));

			// member
			content = content.replaceAll("~MEMBER~", request.getResponseValue(MEMBER));
			content = content.replaceAll("~FREE_MEMBER~", request.getResponseValue(MEMBER_FREE));
		}
		catch (Exception e)
		{
			instance.logMonitor(e);
			instance.logMonitor(request);
		}

		return content;
	}

	public static void sendResponse(
			DispatcherInstance instance, ProductRoute orderRoute, CommandMessage request
			, String actionType, String cause, String prefix, String postfix, String isdn) throws AppException
	{
		if ((request == null) || isdn.equals(""))
		{
			return;
		}
		
		String content = "";
		
		if (request.getChannel().equals(Constants.CHANNEL_WEB) && !request.getParameters().getBoolean("isvt"))
		{
			if (!request.getCause().equals("")
					&& !request.getCause().equals(Constants.SUCCESS))
				return;
			
			if (!request.getActionType().equals(Constants.ACTION_SUBSCRIPTION)
					&& !request.getActionType().equals(Constants.ACTION_SUPPLIER_DEACTIVE)
					&& !request.getActionType().equals(Constants.ACTION_SUPPLIER_REACTIVE)
					&& !request.getActionType().equals(Constants.ACTION_TOPUP))
				return;
		}

		cause = CommandUtil.getCause(request);

		if (!prefix.equals(""))
		{
			cause = prefix + "." + cause;
		}

		if (!postfix.equals(""))
		{
			cause = cause + "." + postfix;
		}

		ProductEntry product = null;

		try
		{
			try
			{
				product = ProductFactory.getCache().getProduct(request.getProductId());
			}
			catch (Exception e)
			{
				instance.logMonitor(e);
			}

			if (actionType.equals(""))
			{
				actionType = request.getActionType();
			}

			content = getResponseTemplate(instance, orderRoute, request, actionType, cause);
			//instance.debugMonitor("Response " +content);
//			if ("".equals(content) || content == null)
//			{
//				cause = Constants.ERROR;
//				if (!prefix.equals(""))
//				{
//					cause = prefix + "." + cause;
//				}
//
//				if (!postfix.equals(""))
//				{
//					cause = cause + "." + postfix;
//				}
//				content = getResponseTemplate(instance, orderRoute, request, actionType, cause);
//			}

			
			
			content = formatResponse(instance, product, request, actionType, content);
			
			//get cau hoi tu properties
			String strContent = request.getParameters().getString("quiz.content");
			
			//replace cau hoi
			content = content.replaceAll("~QUESTION~", strContent);
			
			//Truong hop vuot qua gioi han trong 1 ngay
			if(request.getParameters().getBoolean("isover"))
			{
				content = request.getParameters().getString("answer") + content;
			}
			
//			String serviceAddress = (product != null) ? product.getServiceAddress() : request.getServiceAddress();
			
			// instance.dispatcher
			if (!content.equals("") && !request.getUserName().equals("system"))
			{
					CommandUtil.sendSMS(instance, request, content);
					instance.logMonitor("sendNotify: " + request.getIsdn() + " - " + content);
			}
			
			
			
			//Send first content register viettel non-sub
			if(request.getParameters().getBoolean("quiz.isfirst"))
			{
				Thread.sleep(1000);
				//send first content
				String content2 = request.getParameters().getString("quiz.content2");
				CommandUtil.sendSMS(instance, request,  content2);
				instance.logMonitor("sendNotify: " + request.getIsdn() + " - " + content2);
				
				Thread.sleep(1000);
				//send question
				String content1 = request.getParameters().getString("quiz.content1");
				CommandUtil.sendSMS(instance, request,  content1);
				instance.logMonitor("sendNotify: " + request.getIsdn() + " - " + content1);
			}
			
			//tin nhan send khi khach hang dang ki roi ma dang ki lai
			if(request.getParameters().getBoolean("quiz.isregistered"))
			{	
				Thread.sleep(1000);
				//send question
				String content1 = request.getParameters().getString("quiz.content1");
				CommandUtil.sendSMS(instance, request,  content1);
				instance.logMonitor("sendNotify: " + request.getIsdn() + " - " + content1);
			}
			
		}
		catch (Exception e)
		{
			instance.logMonitor(e);
		}
	}

	public static void notifyOwner(DispatcherInstance instance, ProductRoute orderRoute, CommandMessage request) throws AppException
	{
		sendResponse(instance, orderRoute, request, "", "", "", "", request.getIsdn());
	}

	public static void notifyDeliver(DispatcherInstance instance, ProductRoute orderRoute, CommandMessage request) throws AppException
	{
		sendResponse(instance, orderRoute, request, "", "", "", "deliver", request.getShipTo());
	}

	public static void notifyAdvertising(DispatcherInstance instance, ProductRoute orderRoute, CommandMessage request) throws AppException
	{
		sendResponse(instance, orderRoute, request, Constants.ACTION_ADVERTISING, "", "", "", request.getShipTo());
	}

	public static String getResponseTemplate(
			DispatcherInstance instance, ProductRoute orderRoute, CommandMessage request, String actionType, String cause)
			throws Exception
	{
		String content = "";

		ProductEntry product = null;

		ProductMessage productMessage = null;

		try
		{
			if (cause.equals(""))
			{
				cause = request.getCause();
			}

			try
			{
				product = ProductFactory.getCache().getProduct(request.getProductId());
			}
			catch (Exception e)
			{
				instance.logMonitor(e);
			}

			if (product != null)
			{
				productMessage = product.getProductMessage(
						actionType, request.getCampaignId(), request.getLanguageId(), request.getChannel(), cause);
			}

			if (productMessage != null)
			{
				content = productMessage.getContent();

				request.setCauseValue(productMessage.getCauseValue());
			}
			else
			{
				content = DomainFactory.getCache().getDomain("RESPONSE_MESSAGE", actionType + "." + cause);
				
				if (content.equals(""))
				{
					content = DomainFactory.getCache().getDomain("RESPONSE_MESSAGE", request.getServiceAddress() + "." + cause);
				}
				
				if (content.equals(""))
				{
					content = DomainFactory.getCache().getDomain("RESPONSE_MESSAGE", cause);
				}

				if (content.equals(""))
				{
					content = cause;
					//throw new AppException(cause);
				}
			}
		}
		catch (Exception e)
		{
			//instance.logMonitor(e);
			//instance.logMonitor(request);
		}

		return content;
	}

	public static String replaceByValue(CommandMessage request, String content, String tag, String value) throws Exception
	{
		return content.replaceAll("~" + tag + "~", value);
	}

	public static String replaceByValue(CommandMessage request, String content, String tag, int value) throws Exception
	{
		return content.replaceAll("~" + tag + "~", String.valueOf(value));
	}

	public static String replaceByValue(CommandMessage request, String content, String tag, double value) throws Exception
	{
		return content.replaceAll("~" + tag + "~", String.valueOf(value));
	}

	public static String replaceByValue(CommandMessage request, String content, String tag, long value) throws Exception
	{
		return content.replaceAll("~" + tag + "~", String.valueOf(value));
	}

	public static String replaceByParameter(CommandMessage request, String content, String tag, String parameter)
			throws Exception
	{
		return content.replaceAll("~" + tag + "~", request.getParameters().getString("response." + parameter));
	}
}
