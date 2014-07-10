/**
 * 
 */
package com.crm.provisioning.impl;

import java.io.IOException;
import java.net.ConnectException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.axis.AxisFault;

import com.crm.product.cache.ProductFactory;
import com.crm.product.cache.ProductEntry;
import com.crm.product.cache.ProductRoute;
import com.crm.provisioning.cache.CommandAction;
import com.crm.provisioning.cache.CommandEntry;
import com.crm.provisioning.cache.ProvisioningCommand;
import com.crm.provisioning.cache.ProvisioningConnection;
import com.crm.provisioning.cache.ProvisioningEntry;
import com.crm.provisioning.cache.ProvisioningFactory;
import com.crm.provisioning.cache.ProvisioningMessage;
import com.crm.provisioning.message.CommandMessage;
import com.crm.provisioning.message.VNMMessage;
import com.crm.provisioning.thread.CommandInstance;
import com.crm.provisioning.thread.ProvisioningThread;
import com.crm.provisioning.util.CommandUtil;
import com.crm.provisioning.util.ResponseUtil;
import com.crm.subscriber.bean.SubscriberProduct;
import com.crm.subscriber.impl.IDDServiceImpl;
import com.crm.subscriber.impl.MGMServiceImpl;
import com.crm.subscriber.impl.SubscriberGroupImpl;
import com.crm.subscriber.impl.SubscriberProductImpl;
import com.crm.util.GeneratorSeq;
import com.crm.util.StringUtil;
import com.crm.kernel.index.ExecuteImpl;
import com.crm.kernel.message.Constants;
import com.crm.kernel.sql.Database;

import com.fss.util.AppException;

/**
 * @author ThangPV
 * 
 */
public class CommandImpl extends ExecuteImpl
{
	public ProvisioningConnection getProvisioningConnection(
			CommandInstance instance) throws Exception
	{
		return instance.getProvisioningConnection();
	}

	public String getErrorCode(CommandInstance instance,
			CommandMessage request, Exception error)
	{
		String errorCode = Constants.ERROR;
		try
		{
			instance.logMonitor(error);

			if (error != null)
			{
				if (error instanceof AppException)
				{
					errorCode = ((AppException) error).getMessage();
				}
				else
				{
					ProvisioningEntry provisioning = ProvisioningFactory
							.getCache().getProvisioning(
									request.getProvisioningId());

					ProvisioningMessage message = provisioning.getMessage(error
							.getMessage());

					if (message != null)
					{
						errorCode = message.getCause();
					}
				}
			}
		}
		catch (Exception e)
		{
			instance.getLog().error(e);
		}

		return errorCode;
	}

	public void processError(CommandInstance instance, ProvisioningCommand provisioningCommand, CommandMessage request,
			Exception error) throws Exception
	{
		String errorCode = "";

		try
		{
			if (error instanceof AppException)
			{
				errorCode = ((AppException) error).getMessage();
			}
			else if (error instanceof AxisFault)
			{
				AxisFault axisFault = (AxisFault) error;

				if (axisFault.detail instanceof ConnectException)
				{
					errorCode = "connection-time-out";
				}
				else
				{
					errorCode = getErrorCode(instance, request, error);
				}
			}
			else if (error instanceof IOException)
			{
				throw new AppException(Constants.ERROR_CONNECTION);
			}
			else
			{
				errorCode = getErrorCode(instance, request, error);
			}

			request.setStatus(Constants.ORDER_STATUS_DENIED);
			request.setCause(errorCode);

			CommandUtil.processError(instance, request, errorCode);
		}
		finally
		{
			// rollback(instance, provisioningCommand, request);
		}
	}

	public CommandMessage simulation(CommandInstance instance,
			ProvisioningCommand provisioningCommand, CommandMessage request)
			throws Exception
	{
		try
		{
			long simulationExecuteTime = ((ProvisioningThread) instance.getDispatcher()).simulationTime;
			String cause = ((ProvisioningThread) instance.getDispatcher()).simulationCause;
			instance.debugMonitor("Simulation execute time: " + simulationExecuteTime + "ms");
			Thread.sleep(simulationExecuteTime);
			request.setCause(cause);
		}
		catch (Exception e)
		{
			throw e;
		}

		return request;
	}

	/**
	 * Edited by NamTA<br>
	 * Modified Date: 17/05/2012
	 * 
	 * @param instance
	 * @param provisioningCommand
	 * @param request
	 * @return
	 * @throws Exception
	 */
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

//			if (result.getActionType().equals(Constants.ACTION_UPGRADE))
//				includeCurrentDay = false;

			SubscriberProduct subProduct = SubscriberProductImpl.register(
					result.getUserId(), result.getUserName(),
					result.getSubscriberId(), result.getIsdn(),
					result.getSubscriberType(), result.getProductId(),
					result.getCampaignId(), result.getLanguageId(),
					includeCurrentDay, result.getMerchantId(), result.getSessionId(),0);
			if (subProduct.getExpirationDate() != null)
			{
				result.setResponseValue(ResponseUtil.SERVICE_EXPIRE_DATE,
						StringUtil.format(subProduct.getExpirationDate(),
								"dd/MM/yyyy"));
			}
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

			SubscriberProductImpl.unregister(result.getUserId(),
					result.getUserName(), result.getSubProductId(),
					result.getProductId());
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

			SubscriberProductImpl.subscription(result.getUserId(),
					result.getUserName(), result.getSubProductId(),
					result.isFullOfCharge(), result.getQuantity());
		}
		catch (Exception error)
		{
			processError(instance, provisioningCommand, request, error);
		}

		return result;
	}

	public CommandMessage barringBySupplier(CommandInstance instance,
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

			SubscriberProductImpl.barringBySupplier(result.getUserId(),
					result.getUserName(), result.getSubProductId());
		}
		catch (Exception error)
		{
			processError(instance, provisioningCommand, request, error);
		}

		return result;
	}

	/**
	 * Created by NamTA<br>
	 * Created Date: 16/05/2012
	 * 
	 * @param instance
	 * @param provisioningCommand
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public CommandMessage extendExpirationDate(CommandInstance instance,
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

			boolean includeCurrentDay = result.getParameters().getBoolean("includeCurrentDay", false);
			SubscriberProduct subProduct = SubscriberProductImpl
					.extendExpirationDate(result.getUserId(),
							result.getUserName(), result.getSubProductId(),
							result.getCampaignId(), includeCurrentDay);

			/**
			 * Add response value
			 */
			result.setResponseValue(ResponseUtil.SERVICE_EXPIRE_DATE,
					StringUtil.format(subProduct.getExpirationDate(),
							"dd/MM/yyyy"));
		}
		catch (Exception error)
		{
			processError(instance, provisioningCommand, request, error);
		}

		return result;
	}

	public CommandMessage unbarringBySupplier(CommandInstance instance,
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

			SubscriberProductImpl.unbarringBySupplier(result.getUserId(),
					result.getUserName(), result.getSubProductId());
		}
		catch (Exception error)
		{
			processError(instance, provisioningCommand, request, error);
		}

		return result;
	}

	public CommandMessage addMember(CommandInstance instance,
			ProvisioningCommand provisioningCommand, CommandMessage request)
			throws Exception
	{
		Connection connection = null;

		CommandMessage result = request;

		try
		{
			ProductEntry product = ProductFactory.getCache().getProduct(
					request.getProductId());

			connection = Database.getConnection();

			connection.setAutoCommit(false);

			// add member into group
			String verifyCode = result.getParameters().getString(
					"member.verifyCode", "");

			SubscriberGroupImpl.addMember(connection, result.getUserId(),
					result.getUserName(), result.getIsdn(), result.getShipTo(),
					result.getProductId(), product.getMemberType(), verifyCode,
					result.getOrderDate(), Constants.SUPPLIER_ACTIVE_STATUS);

			if (product.isSubscription())
			{
				int totalMember = SubscriberGroupImpl.countMember(connection,
						result.getIsdn(), result.getProductId(),
						product.getMemberType(), true);

				if (totalMember == 1 && request.getProductId() != 12110)
				{
					boolean includeCurrentDay = result.getParameters().getBoolean("includeCurrentDay", false);
					
					SubscriberProductImpl.register(connection,
							result.getUserId(), result.getUserName(),
							result.getSubscriberId(), result.getIsdn(),
							result.getSubscriberType(), result.getProductId(),
							result.getCampaignId(), result.getLanguageId(),
							includeCurrentDay, request.getMerchantId(), result.getSessionId(),0);
				}
			}

			connection.commit();
		}
		catch (Exception error)
		{
			Database.rollback(connection);

			processError(instance, provisioningCommand, request, error);
		}
		finally
		{
			Database.closeObject(connection);
		}

		return result;
	}

	public CommandMessage addMemberF5(CommandInstance instance,
			ProvisioningCommand provisioningCommand, CommandMessage request)
			throws Exception
	{
		Connection connection = null;

		CommandMessage result = request;

		try
		{
			ProductEntry product = ProductFactory.getCache().getProduct(
					request.getProductId());

			connection = Database.getConnection();

			connection.setAutoCommit(false);

			// add member into group
			String verifyCode = result.getParameters().getString(
					"member.verifyCode", "");
			String[] phoneBookList = result.getRequestValue("f5-modify-member", "").split(",");

			for (int i = 0; i < phoneBookList.length; i++)
			{
				SubscriberGroupImpl.addMember(connection, result.getUserId(),
						result.getUserName(), result.getIsdn(),
						phoneBookList[i], result.getProductId(),
						product.getMemberType(), verifyCode,
						result.getOrderDate(), Constants.SUPPLIER_ACTIVE_STATUS);
			}
			connection.commit();
		}
		catch (Exception error)
		{
			Database.rollback(connection);

			processError(instance, provisioningCommand, request, error);
		}
		finally
		{
			Database.closeObject(connection);
		}

		return result;
	}

	public CommandMessage removeMember(CommandInstance instance,
			ProvisioningCommand provisioningCommand, CommandMessage request)
			throws Exception
	{
		Connection connection = null;

		CommandMessage result = request;

		try
		{
			ProductEntry product = ProductFactory.getCache().getProduct(
					request.getProductId());

			connection = Database.getConnection();

			connection.setAutoCommit(false);

			// add member into group
			SubscriberGroupImpl.removeMember(connection, result.getIsdn(),
					result.getShipTo(), result.getProductId(),
					product.getMemberType(), Constants.SUPPLIER_CANCEL_STATUS);

			if (product.isSubscription())
			{
				int totalMember = SubscriberGroupImpl.countMember(connection,
						result.getIsdn(), result.getProductId(),
						product.getMemberType(), true);

				if (totalMember == 0)
				{
					SubscriberProductImpl.unregister(connection,
							result.getUserId(), result.getUserName(),
							result.getSubProductId(), result.getProductId());
				}
			}

			connection.commit();
		}
		catch (Exception error)
		{
			Database.rollback(connection);

			processError(instance, provisioningCommand, request, error);
		}
		finally
		{
			Database.closeObject(connection);
		}

		return result;
	}

	public CommandMessage removeMemberF5(CommandInstance instance,
			ProvisioningCommand provisioningCommand, CommandMessage request)
			throws Exception
	{
		Connection connection = null;

		CommandMessage result = request;

		try
		{
			ProductEntry product = ProductFactory.getCache().getProduct(
					request.getProductId());

			connection = Database.getConnection();

			connection.setAutoCommit(false);

			// add member into group
			String[] phoneBookList = result.getRequestValue("f5-modify-member", "").split(",");

			for (int i = 0; i < phoneBookList.length; i++)
			{
				SubscriberGroupImpl.removeMember(connection, result.getIsdn(),
						phoneBookList[i], result.getProductId(),
						product.getMemberType(), Constants.SUPPLIER_BARRING_STATUS);
			}

			connection.commit();
		}
		catch (Exception error)
		{
			Database.rollback(connection);

			processError(instance, provisioningCommand, request, error);
		}
		finally
		{
			Database.closeObject(connection);
		}

		return result;
	}

	public CommandMessage removeGroup(CommandInstance instance,
			ProvisioningCommand provisioningCommand, CommandMessage request)
			throws Exception
	{
		Connection connection = null;

		CommandMessage result = request;

		try
		{
			ProductEntry product = ProductFactory.getCache().getProduct(
					request.getProductId());

			connection = Database.getConnection();

			connection.setAutoCommit(false);

			// add member into group
			SubscriberGroupImpl.removeGroup(connection, result.getIsdn(),
					result.getProductId(), product.getMemberType());

			if (product.isSubscription())
			{
				SubscriberProductImpl.unregister(connection,
						result.getUserId(), result.getUserName(),
						result.getSubProductId(), result.getProductId());
			}

			connection.commit();
		}
		catch (Exception error)
		{
			Database.rollback(connection);

			processError(instance, provisioningCommand, request, error);
		}
		finally
		{
			Database.closeObject(connection);
		}

		return result;
	}

	public CommandMessage rollback(CommandInstance instance,
			ProvisioningCommand provisioningCommand, CommandMessage request)
			throws Exception
	{
		instance.rollback(request);
		return request;
	}

	public CommandMessage nextCommand(CommandAction action,
			CommandMessage message) throws Exception
	{
		CommandMessage transform = ((CommandMessage) message).clone();

		transform.setCommandId(action.getNextCommandId());

		// transform.setCommandRequest(transform.getCommandResponse());
		transform.setResponse("");

		return transform;
	}

	/**
	 * withdraw money for loyalty balance.
	 * 
	 * @param instance
	 * @param provisioningCommand
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public CommandMessage withDraw(CommandInstance instance,
			ProvisioningCommand provisioningCommand, CommandMessage request)
			throws Exception
	{
		CommandMessage result = request;
		try
		{

			SubscriberProductImpl.withdraw(request.getUserId(),
					request.getUserName(), request.getSubscriberId(),
					request.getIsdn(), "LOYALTY", request.getQuantity());

			result.setCause(Constants.SUCCESS);
		}
		catch (Exception e)
		{
			result.setCause("error-withdraw-loyalty");
			processError(instance, provisioningCommand, result, e);
		}
		return result;
	}

	public VNMMessage registerLuckySimProduct(CommandInstance instance,
			ProvisioningCommand provisioningCommand, CommandMessage request)
			throws Exception
	{
		long userId = request.getUserId();
		String userName = request.getUserName();
		Date createDate = new Date();
		Date modifiedDate = new Date();
		String subscriberId = "0";
		long productId = request.getProductId();
		String isdn = request.getIsdn();
		int subscriberType = Constants.PREPAID_SUB_TYPE;
		int supplierStatus = Constants.SUPPLIER_ACTIVE_STATUS;
		Date registerDate = new Date();
		String languageId = request.getLanguageId();
		long campaignId = request.getCampaignId();

		String strSQL =
				"insert into SubscriberProduct(subproductid,userid,username," +
						"createdate,modifieddate,subscriberid,productid,isdn,subscribertype," +
						"supplierstatus,registerdate,languageid,campaignid) " +
						"values(sub_product_seq.nextval,?,?,?,?,?,?,?,?,?,?,?,?)";

		VNMMessage result = CommandUtil.createVNMMessage(request);

		Connection connection = null;
		PreparedStatement stmt = null;
		try
		{
			connection = com.crm.kernel.sql.Database.getConnection();
			stmt = connection.prepareStatement(strSQL);
			stmt.setLong(1, userId);
			stmt.setString(2, userName);
			stmt.setTimestamp(3, new Timestamp(createDate.getTime()));
			stmt.setTimestamp(4, new Timestamp(modifiedDate.getTime()));
			stmt.setString(5, subscriberId);
			stmt.setLong(6, productId);
			stmt.setString(7, isdn);
			stmt.setInt(8, subscriberType);
			stmt.setInt(9, supplierStatus);
			stmt.setTimestamp(10, new Timestamp(registerDate.getTime()));
			stmt.setString(11, languageId);
			stmt.setLong(12, campaignId);
			stmt.executeUpdate();
		}
		catch (Exception ex)
		{
			result.setCause(Constants.ERROR);
			result.setDescription(ex.getMessage());
			throw ex;
		}
		finally
		{
			Database.closeObject(stmt);
			Database.closeObject(connection);
		}
		return result;
	}

	public CommandMessage recover(CommandInstance instance,
			ProvisioningCommand provisioningCommand, CommandMessage request)
			throws Exception
	{
		CommandMessage result = request;
		try
		{
			// Change function to recover money when error
			SubscriberProductImpl.withdraw(request.getUserId(),
					request.getUserName(), request.getSubscriberId(),
					request.getIsdn(), "LOYALTY", -1 * request.getQuantity());
			result.setCause(Constants.SUCCESS);
		}
		catch (Exception e)
		{
			result.setCause("error-withdraw-loyalty");
			processError(instance, provisioningCommand, result, e);
		}
		return result;
	}

	// 2012-09-19 MinhDT ADD_START: add for product VB600 and VB220
	public CommandMessage confirmRegister(CommandInstance instance,
			ProvisioningCommand provisioningCommand, CommandMessage request)
			throws Exception
	{
		CommandMessage result = request;

		String responseCode = "";
		try
		{
			IDDServiceImpl.confirmRegister(result.getIsdn(),
					result.getProductId());
			responseCode = Constants.SUCCESS;
			result.setCause(responseCode);
			result.setResponse(responseCode);
			result.setStatus(Constants.ORDER_STATUS_APPROVED);
		}
		catch (Exception error)
		{
			processError(instance, provisioningCommand, result, error);
		}

		return result;
	}

	public CommandMessage registerIDD(CommandInstance instance,
			ProvisioningCommand provisioningCommand, CommandMessage request)
			throws Exception
	{
		try
		{
			boolean includeCurrentDay = request.getParameters().getBoolean(
					"includeCurrentDay");

			if (request.getActionType().equals(Constants.ACTION_REGISTER)
					|| request.getActionType().equals(Constants.ACTION_UPGRADE))
				includeCurrentDay = false;

			SubscriberProduct subProduct = SubscriberProductImpl.register(
					request.getUserId(), request.getUserName(),
					request.getSubscriberId(), request.getIsdn(),
					request.getSubscriberType(), request.getProductId(),
					request.getCampaignId(), request.getLanguageId(),
					includeCurrentDay, request.getMerchantId(), request.getSessionId(),0);

			if (request.getActionType().equals(
					Constants.ACTION_SUPPLIER_REACTIVE)
					|| request.getParameters()
							.getProperty("PropertiesRenew", "false")
							.equals("true"))
			{
				IDDServiceImpl.updateProperties(request.getIsdn(),
						request.getProductId(), "renew");
				IDDServiceImpl.removeExtendIDDBuffet(request.getIsdn(),
						request.getProductId());

				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				Calendar cal = Calendar.getInstance();
				cal.add(cal.HOUR_OF_DAY, 14);

				request.setCause("renew.success");
				request.setResponseValue(ResponseUtil.SERVICE_START_DATE, cal
						.getTime().getHours());
				request.setResponseValue(ResponseUtil.SERVICE_ACTIVE_DATE,
						sdf.format(cal.getTime()));

				ProductRoute orderRoute = ProductFactory.getCache().getProductRoute(request.getRouteId());
				boolean allowSendSMS = Boolean.parseBoolean(orderRoute.getParameter("AllowSendSMS", "false"));

				if (allowSendSMS)
				{
					CommandMessage object = request.clone();
					object.setCause("renew.success");
					object.setChannel(Constants.CHANNEL_SMS);

					ResponseUtil.notifyOwner(instance, orderRoute, object);
				}
			}

			IDDServiceImpl.removeConfirm(request.getIsdn(),
					request.getProductId());
		}
		catch (Exception error)
		{
			processError(instance, provisioningCommand, request, error);
		}

		return request;
	}

	public CommandMessage unregisterIDD(CommandInstance instance,
			ProvisioningCommand provisioningCommand, CommandMessage request)
			throws Exception
	{
		try
		{
			SubscriberProductImpl.unregister(request.getUserId(),
					request.getUserName(), request.getSubProductId(),
					request.getProductId());

			IDDServiceImpl.removeConfirm(request.getIsdn(),
					request.getProductId());

			IDDServiceImpl.removeExtendIDDBuffet(request.getIsdn(),
					request.getProductId());

			ProductRoute orderRoute = ProductFactory.getCache().getProductRoute(request.getRouteId());
			boolean allowSendSMS = Boolean.parseBoolean(orderRoute.getParameter("AllowSendSMS", "false"));

			if (allowSendSMS)
			{
				CommandMessage object = request.clone();
				object.setChannel(Constants.CHANNEL_SMS);

				ResponseUtil.notifyOwner(instance, orderRoute, object);
			}
		}
		catch (Exception error)
		{
			processError(instance, provisioningCommand, request, error);
		}

		return request;
	}

	public CommandMessage registerMGM(CommandInstance instance,
			ProvisioningCommand provisioningCommand, CommandMessage request)
			throws Exception
	{
		ProductEntry product = ProductFactory.getCache().getProduct(
				request.getProductId());

		String introducer = request.getIsdn();

		String responseCode = "";

		try
		{
			String referral = request.getKeyword().toUpperCase();

			referral = referral.substring(referral.indexOf(" "),
					referral.length()).trim();

			// Check country code
			if (!referral.equals("")
					&& referral.startsWith(Constants.DOMESTIC_CODE))
			{
				referral = referral.substring(Constants.DOMESTIC_CODE.length());
				referral = Constants.COUNTRY_CODE + referral;
			}

			// Check max referral
			int intNumOfCc = MGMServiceImpl.getNumOfCC(introducer);
			String CCGroupName = product.getParameter("CCGroupName", "CG");
			CCGroupName = CCGroupName + (intNumOfCc + 1);
			int AlcoTime = Integer.valueOf(product.getParameter("AlcoTime",
					"34"));

			Calendar serviceStart = Calendar.getInstance();
			Calendar serviceEnd = Calendar.getInstance();
			serviceEnd.add(Calendar.DATE, AlcoTime);

			boolean checkAddDaily = Boolean.parseBoolean(request
					.getParameters().getProperty("AddDaily", "true"));
			int addDaily = 1;

			if (!checkAddDaily)
			{
				addDaily = 2;
				AlcoTime = Integer.valueOf(product.getParameter("AlcoTime_1M",
						"30"));
			}

			// Insert into table.
			if (MGMServiceImpl.insertNewMGM(introducer, referral, introducer
					+ "_" + CCGroupName, serviceStart, serviceEnd, AlcoTime,
					addDaily))
			{
				if (!checkAddDaily)
				{
					responseCode = Constants.SUCCESS + "_1M";
				}
				else
				{
					responseCode = Constants.SUCCESS;
				}
				request.setCause(responseCode);
				request.setShipTo(referral);
				request.setResponseValue(ResponseUtil.REFERAL, referral);
				request.setResponseValue(ResponseUtil.SERVICE_EXPIRE_DATE,
						new SimpleDateFormat("dd/MM/yyyy").format(serviceEnd
								.getTime()));
			}
			else
			{
				throw new AppException("insert-fail");
			}
		}
		catch (Exception e)
		{
			processError(instance, provisioningCommand, request, e);
		}

		return request;
	}

	public CommandMessage updateDailyMGM(CommandInstance instance,
			ProvisioningCommand provisioningCommand, CommandMessage request)
			throws Exception
	{
		String responseCode = "";
		String isdn = request.getIsdn();
		try
		{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			if (MGMServiceImpl.updateAddBalance(isdn, sdf.format(new Date())))
			{
				responseCode = Constants.SUCCESS;
				request.setCause(responseCode);
				request.setStatus(Constants.SERVICE_STATUS_APPROVED);
			}
			else
			{
				throw new AppException("update-fail");
			}
		}
		catch (Exception e)
		{
			processError(instance, provisioningCommand, request, e);
		}

		return request;
	}

	/**
	 * Sets request and log, auto-generate sessionId
	 * 
	 * @param instance
	 * @param request
	 * @param requestString
	 * @return
	 * @throws Exception
	 */
	public long setRequest(CommandInstance instance, CommandMessage request,
			String requestString) throws Exception
	{
		long sessionId = 0;
		try
		{
			if (request.getOrderId() != Constants.DEFAULT_ID)
				sessionId = request.getOrderId();
			sessionId = GeneratorSeq.getNextSeq();
		}
		catch (Exception e)
		{
		}

		setRequest(instance, request, requestString, sessionId);

		return sessionId;
	}

	/**
	 * Sets request and log, use existing sessionId
	 * 
	 * @param instance
	 * @param request
	 * @param requestString
	 * @param sessionId
	 * @throws Exception
	 */
	public void setRequest(CommandInstance instance, CommandMessage request,
			String requestString, long sessionId) throws Exception
	{

		requestString = "ID=" + sessionId + "| " + requestString;

		instance.logMonitor("SEND: " + requestString);
		// request.setRequest(requestString);
		request.setRequestTime(new Date());
	}

	/**
	 * Sets response and log
	 * 
	 * @param instance
	 * @param request
	 * @param responseString
	 * @param sessionId
	 * @throws Exception
	 */
	public void setResponse(CommandInstance instance, CommandMessage request,
			String responseString, long sessionId) throws Exception
	{
		request.setResponseTime(new Date());

		long costTime = CommandUtil.calculateCostTime(request.getRequestTime(), request.getResponseTime());

		responseString = "ID=" + sessionId + "| " + responseString + "| costTime=" + costTime;
		instance.logMonitor("RECEIVE: " + responseString);
		// request.setResponse(responseString);
	}
	
	

	public void setRequestMO(CommandInstance instance, CommandMessage request,
			String requestString, long sessionId) throws Exception
	{

		requestString = "ID=" + sessionId + " | " + requestString;

		instance.logMonitor("FW: " + requestString);
		request.setRequest(requestString);
		// request.setRequestTime(new Date());
	}
	
	public void setDelivery(CommandInstance instance, CommandMessage request,
			String requestString, long sessionId) throws Exception
	{

		requestString = "ID=" + sessionId + " | " + requestString;

		instance.logMonitor("DELIVERY: " + requestString);
		request.setRequest(requestString);
		// request.setRequestTime(new Date());
	}
	
	public String getLogRequest(String funcionName, String isdn)
	{
		String log = funcionName + "{" + isdn + "}";
		return log;
	}
	
	public String getLogRequest(String funcionName, String isdn, long merchantId, String keyword, long productId)
	{
		String log = funcionName + "{isdn=" + isdn + " | cpid=" + merchantId + " | cmd=" +keyword+  " | productid=" +productId+ "}" ;
		return log;
	}
	
	public String getLogRequest(String funcionName, String isdn, long merchantId,long productId, int numSMS)
	{
		String log = funcionName + "{isdn=" + isdn + " | cpid=" + merchantId +  " | productid=" +productId+ " | numsms=" +numSMS+ "}" ;
		return log;
	}
	
	public String getLogRequest(String functionName, String isdn, long merchantId, long productId, String otp)
	{
		String log = functionName + "{isdn=" + isdn + " | cpid=" + merchantId +  " | productid=" +productId+ " | otp=" +otp+ "}" ;
		return log;
	}

}
