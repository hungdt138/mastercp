/**
 * 
 */
package com.crm.provisioning.impl.mt;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import com.crm.kernel.message.AlarmMessage;
import com.crm.kernel.message.Constants;
import com.crm.kernel.sql.Database;
import com.crm.merchant.cache.MerchantEntry;
import com.crm.merchant.cache.MerchantFactory;
import com.crm.product.cache.ProductCache;
import com.crm.product.cache.ProductEntry;
import com.crm.product.cache.ProductFactory;
import com.crm.product.cache.ProductRoute;
import com.crm.provisioning.cache.ProvisioningCommand;
import com.crm.provisioning.message.CommandMessage;

import com.crm.provisioning.thread.CommandInstance;
import com.crm.provisioning.util.CommandUtil;
import com.crm.provisioning.util.ResponseUtil;
import com.crm.subscriber.bean.SubscriberProduct;
import com.crm.subscriber.impl.SubscriberOrderImpl;
import com.crm.subscriber.impl.SubscriberProductImpl;
import com.crm.util.AppProperties;
import com.crm.util.HttpRequest;
import com.crm.util.StringPool;
import com.crm.util.StringUtil;
import com.crm.provisioning.impl.CommandImpl;
import com.crm.provisioning.impl.smpp.SMPPConnection;
import com.fss.util.AppException;

/**
 * @author hungdt
 * 
 */
public class ChargingCommandImpl extends CommandImpl
{

	private static Logger	log		= Logger.getLogger(ChargingCommandImpl.class);
	private static int		count	= 0;

	public CommandMessage sendMOToCP(CommandInstance instance,
			ProvisioningCommand provisioningCommand, CommandMessage request) throws Exception
	{

		CommandMessage result = request;
		MerchantEntry merchant = MerchantFactory.getCache().getMerchant(request.getMerchantId());

		ProductRoute productRoute = ProductFactory.getCache().getProductRoute(request.getRouteId());
		ProductEntry product = ProductFactory.getCache().getProduct(request.getProductId());

		String productCode = product.getAlias();
		String cmdCode = productRoute.getKeyword().replace("%", StringPool.BLANK).replace(" ", StringPool.BLANK);
		String msgBody = request.getKeyword().replace(" ", StringPool.URL_SPACE);

		if (request.getChannel().equals(Constants.CHANNEL_WEB))
		{
			productCode = product.getAlias();
			cmdCode = productRoute.getParameters().getString("mastercp.code", "").replace("%", StringPool.BLANK).replace(" ", StringPool.BLANK);
			msgBody = productRoute.getParameters().getString("mastercp.msg", "").replace(" ", StringPool.URL_SPACE);
		}

		if (cmdCode.equals("") || cmdCode == null)
		{
			productCode = product.getAlias();
			cmdCode = product.getParameters().getString("vt8x26.commandcode", "").replace("%", StringPool.BLANK).replace(" ", StringPool.BLANK);
			msgBody = product.getParameters().getString("vt8x26.commandcode", "").replace(" ", StringPool.URL_SPACE);
		}

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

		Connection connection = null;
		try
		{
			String url = product.getHost();
			int telcoId = product.getOpId();
			if (url == null)
			{
				url = product.getParameters().getString("urlmo", "http://corellinet.beezzi.com:8080/sms/mo/acom/group/apac/subscription");
				telcoId = product.getParameters().getInteger("telcoId", 1);
			}
			StringBuffer cpUrl = new StringBuffer();
			cpUrl.append(url);
			cpUrl.append("?username=");
			cpUrl.append(merchant.getUsername());
			cpUrl.append("&password=");
			cpUrl.append(merchant.getPassword());
			cpUrl.append("&dest=");
			cpUrl.append(request.getServiceAddress());
			cpUrl.append("&isdn=");
			cpUrl.append(request.getIsdn());
			cpUrl.append("&reqid=");
			cpUrl.append(request.getOrderId());
			cpUrl.append("&requestDate=");
			cpUrl.append(dateFormat.format(request.getOrderDate()));
			cpUrl.append("&productCode=");
			cpUrl.append(productCode);
			cpUrl.append("&cmdcode=");
			// cpUrl.append(productRoute.getKeyword().replace("%",
			// StringPool.BLANK).replace(" ", StringPool.BLANK));
			cpUrl.append(cmdCode);
			cpUrl.append("&msgbody=");
			// cpUrl.append(request.getKeyword().replace(" ",
			// StringPool.URL_SPACE));
			cpUrl.append(msgBody);
			cpUrl.append("&opid=");
			cpUrl.append(telcoId);

			connection = Database.getConnection();
			SubscriberOrderImpl.updateDesc(connection, request.getOrderId(), cpUrl.toString(), request.getOrderDate());

			long sessionId = setRequest(
					instance,
					request,
					getLogRequest("com.crm.provisioning.impl.mt.ChargingCommandImpl.sendMOToCP ", result.getIsdn(), result.getMerchantId(),
							result.getKeyword(), result.getProductId()));

			String response = HttpRequest.callURL(cpUrl.toString()).trim();

			if (response.equalsIgnoreCase("200"))
			{
				result.setCause(Constants.SUCCESS);
				result.setResponse(response);
			}
			else if (response.equalsIgnoreCase("202"))
			{
				result.setCause(Constants.SUCCESS);
				result.setResponse(response);
			}
			else
			{
				result.setCause(Constants.ERROR);
				setResponse(instance, request, result.getCause() + ", cpResponse=" + response, sessionId);
				throw new AppException(Constants.ERROR);
			}
			setResponse(instance, request, result.getCause() + ", cpResponse=" + response, sessionId);

		}
		catch (Exception e)
		{
			processError(instance, provisioningCommand, request, e);
		}
		finally
		{

			Database.closeObject(connection);
		}

		return result;
	}

	public CommandMessage deliveryMT(CommandInstance instance, ProvisioningCommand provisioningCommand, CommandMessage request) throws Exception
	{
		CommandMessage result = request;

		Connection connection = null;
		// get description
		String prefix = "";

		String description = "ContentSubcription";

		try
		{

			connection = Database.getConnection();
			String deliveryContent = request.getParameters().getProperty(Constants.REQUEST_PREFIX + "deliveryContent");

			int deliveryCouter = SMPPConnection.countSMS(deliveryContent);

			SubscriberOrderImpl.updateDeliveryCouter(connection, request.getOrderId(), deliveryCouter, request.getOrderDate(), request.getAgentId());

			request.setChannel(Constants.CHANNEL_SMS);

			result.setNumSMS(deliveryCouter);

			long sessionId = setRequest(
					instance,
					request,
					getLogRequest("com.crm.provisioning.impl.mt.ChargingCommandImpl.deliveryMT ", result.getIsdn(), result.getMerchantId(),
							result.getProductId(), deliveryCouter));

			CommandUtil.sendSMS(instance, request, deliveryContent);

			result.setChannel(Constants.CHANNEL_WEB);
			result.setDescription(description);
			result.setCause(Constants.SUCCESS);

			setResponse(instance, request, "success", sessionId);

		}
		catch (Exception e)
		{
			processError(instance, provisioningCommand, request, e);
		}
		finally
		{
			Database.closeObject(connection);
		}

		return result;
	}

	public CommandMessage register(CommandInstance instance,
			ProvisioningCommand provisioningCommand, CommandMessage request)
			throws Exception
	{
		CommandMessage result = request;

		try
		{
			ProductEntry product = ProductFactory.getCache().getProduct(
					request.getProductId());
			
			

			if (!product.isSubscription())
			{
				throw new AppException(Constants.ERROR_INVALID_REQUEST);
			}

			boolean includeCurrentDay = result.getParameters().getBoolean(
					"includeCurrentDay");

			// if (result.getActionType().equals(Constants.ACTION_REGISTER)
			// & result.getActionType().equals(Constants.ACTION_UPGRADE))
			// includeCurrentDay = false;

			long sessionId = setRequest(
					instance,
					request,
					getLogRequest("com.crm.provisioning.impl.mt.ChargingCommandImpl.register {merchantId:" + request.getMerchantId() + "} "
							+ "{alias:" + product.getAlias() + "}", request.getIsdn()));

			// Get score trong chu ki choi

			Date endDate = product.getParameters().getDate("nonsub.enddate", "dd/MM/yyyy hh:mm:ss");
			Date sysDate = Calendar.getInstance().getTime();
			int score = 0;

			if (endDate != null && sysDate.compareTo(endDate) < 0)
			{
				score = SubscriberProductImpl.getScoreByProductId(result.getIsdn(), result.getProductId());
			}

			SubscriberProduct subProduct = SubscriberProductImpl.register(
					result.getUserId(), result.getUserName(),
					result.getSubscriberId(), result.getIsdn(),
					result.getSubscriberType(), result.getProductId(),
					result.getCampaignId(), result.getLanguageId(),
					includeCurrentDay, result.getMerchantId(), result.getSessionId(), score);

			setResponse(instance, request, "success", sessionId);

			/**
			 * Add response value
			 */
			result.setResponseValue(ResponseUtil.SERVICE_EXPIRE_DATE,
					StringUtil.format(subProduct.getExpirationDate(),
							"dd/MM/yyyy"));
			result.setSubProductId(subProduct.getSubProductId());

		}
		catch (Exception error)
		{
			processError(instance, provisioningCommand, request, error);
		}

		return result;
	}

	public CommandMessage unregister(CommandInstance instance,
			ProvisioningCommand provisioningCommand, CommandMessage request)
			throws Exception
	{
		CommandMessage result = request;

		try
		{
			ProductEntry product = ProductFactory.getCache().getProduct(
					request.getProductId());

			if (!product.isSubscription())
			{
				throw new AppException(Constants.ERROR_INVALID_REQUEST);
			}
			long sessionId = setRequest(
					instance,
					request,
					getLogRequest("com.crm.provisioning.impl.mt.ChargingCommandImpl.unregister {merchantId:" + request.getMerchantId() + "} "
							+ "{alias:" + product.getAlias() + "}", request.getIsdn()));
			SubscriberProductImpl.unregister(result.getUserId(),
					result.getUserName(), result.getSubProductId(),
					result.getProductId());
			setResponse(instance, request, "success", sessionId);
		}
		catch (Exception error)
		{
			processError(instance, provisioningCommand, request, error);
		}

		return result;
	}

	public CommandMessage subscription(CommandInstance instance,
			ProvisioningCommand provisioningCommand, CommandMessage request)
			throws Exception
	{
		CommandMessage result = request;

		try
		{
			ProductEntry product = ProductFactory.getCache().getProduct(
					request.getProductId());

			if (!product.isSubscription())
			{
				throw new AppException(Constants.ERROR_INVALID_REQUEST);
			}
			long sessionId = setRequest(
					instance,
					request,
					getLogRequest("com.crm.provisioning.impl.mt.ChargingCommandImpl.subscription {merchantId:" + request.getMerchantId() + "} "
							+ "{alias:" + product.getAlias() + "}", request.getIsdn()));
			SubscriberProductImpl.subscription(result.getUserId(),
					result.getUserName(), result.getSubProductId(),
					result.isFullOfCharge(), result.getQuantity());
			setResponse(instance, request, "success", sessionId);

			// result.setCause(Constants.SUCCESS);
		}
		catch (Exception error)
		{
			processError(instance, provisioningCommand, request, error);
		}

		return result;
	}

}
