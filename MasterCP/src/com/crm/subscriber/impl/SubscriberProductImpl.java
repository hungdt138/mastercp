package com.crm.subscriber.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.crm.marketing.cache.CampaignEntry;
import com.crm.marketing.cache.CampaignFactory;
import com.crm.product.cache.ProductEntry;
import com.crm.product.cache.ProductFactory;
import com.crm.provisioning.cache.ProvisioningCommand;
import com.crm.provisioning.message.CommandMessage;
import com.crm.provisioning.message.VNMMessage;
import com.crm.provisioning.thread.CommandInstance;
import com.crm.provisioning.util.CommandUtil;
import com.crm.kernel.message.Constants;
import com.crm.subscriber.bean.SubscriberProduct;
import com.crm.kernel.sql.Database;
import com.crm.util.DateUtil;
import com.fss.util.AppException;

/**
 * 
 * @author ?? <br>
 *         Modified by NamTA Modified Date: 07/06/2012
 * 
 */
public class SubscriberProductImpl
{
	public final static String	CONDITION_ACTIVE		= " (supplierStatus = " + Constants.SUPPLIER_ACTIVE_STATUS + ") ";

	public final static String	CONDITION_BARRING		= " (supplierStatus = " + Constants.SUPPLIER_BARRING_STATUS + ") ";

	public final static String	CONDITION_TERMINATED	= " (supplierStatus = " + Constants.SUPPLIER_CANCEL_STATUS + ") ";

	public final static String	CONDITION_UNTERMINATED	= " (supplierStatus != " + Constants.SUPPLIER_CANCEL_STATUS + ") ";

	private static long			DEFAULT_ID				= 0;

	/**
	 * TODO: Performance test
	 */
	// private static long sleepTime = 1000L;

	public static Date calculateExpirationDate(Date startDate, String subscriptionType, int period, int quantity)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		if (period <= 0)
		{
			return null;
		}

		if (subscriptionType.equalsIgnoreCase("monthly") || subscriptionType.equalsIgnoreCase("month"))
		{
			calendar.add(Calendar.DATE, 30 * period * quantity);
		}
		else if (subscriptionType.equalsIgnoreCase("weekly") || subscriptionType.equalsIgnoreCase("week"))
		{
			calendar.add(Calendar.DATE, 7 * period * quantity);
		}
		else if (subscriptionType.equalsIgnoreCase("daily") || subscriptionType.equalsIgnoreCase("day"))
		{
			calendar.add(Calendar.DATE, 1 * period * quantity);
		}

		// calendar.add(Calendar.DATE, -1);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);

		return calendar.getTime();
	}

	public static Date calculateGraceDate(Date startDate, String graceDateUnit, int graceDatePeriod) throws Exception
	{
		Date graceDate = null;
		if (graceDatePeriod > 0)
		{
			graceDate = DateUtil.addDate(startDate, graceDateUnit, graceDatePeriod);
		}

		return graceDate;
	}

	public static SubscriberProduct getProduct(ResultSet rsProduct) throws Exception
	{
		SubscriberProduct result = new SubscriberProduct();

		try
		{
			result.setUserId(rsProduct.getLong("userId"));
			result.setUserName(Database.getString(rsProduct, "userName"));

			result.setSubscriberId(rsProduct.getLong("subscriberId"));
			result.setSubProductId(rsProduct.getLong("subProductId"));
			result.setProductId(rsProduct.getLong("productId"));

			result.setSubscriberType(rsProduct.getInt("subscriberType"));
			result.setIsdn(Database.getString(rsProduct, "isdn"));
			result.setLanguageId(Database.getString(rsProduct, "languageId"));

			result.setRegisterDate(rsProduct.getTimestamp("registerDate"));
			result.setUnregisterDate(rsProduct.getTimestamp("unregisterDate"));
			result.setTermDate(rsProduct.getTimestamp("termDate"));
			result.setExpirationDate(rsProduct.getTimestamp("expirationDate"));
			result.setGraceDate(rsProduct.getTimestamp("graceDate"));

			result.setBarringStatus(rsProduct.getInt("barringStatus"));
			result.setSupplierStatus(rsProduct.getInt("supplierStatus"));
			result.setSessionId(rsProduct.getLong("sessionId"));
			result.setScore(rsProduct.getInt("score"));
			result.setQuantity(rsProduct.getInt("quantity"));
		}
		catch (Exception e)
		{
			throw e;
		}

		return result;
	}

	/**
	 * Only use for searching on table subscriberorder to get information about
	 * service.
	 * 
	 * @param rsProduct
	 * @param temp
	 * @return
	 * @throws Exception
	 */
	public static SubscriberProduct getProduct(ResultSet rsProduct, long temp) throws Exception
	{
		SubscriberProduct result = new SubscriberProduct();

		try
		{
			result.setUserId(rsProduct.getLong("userId"));
			result.setUserName(Database.getString(rsProduct, "userName"));

			result.setSubscriberId(rsProduct.getLong("subscriberId"));
			result.setSubProductId(rsProduct.getLong("subProductId"));
			result.setProductId(rsProduct.getLong("productId"));

			result.setSubscriberType(rsProduct.getInt("subscriberType"));
			result.setIsdn(Database.getString(rsProduct, "isdn"));

			result.setRegisterDate(rsProduct.getTimestamp("createdate"));

		}
		catch (Exception e)
		{
			throw e;
		}

		return result;
	}

	public static SubscriberProduct getProduct(Connection connection, long subProductId) throws Exception
	{
		PreparedStatement stmtProduct = null;
		ResultSet rsProduct = null;

		SubscriberProduct result = null;

		try
		{
			String SQL = "Select * From SubscriberProduct Where subProductId = ?";

			stmtProduct = connection.prepareStatement(SQL);
			stmtProduct.setLong(1, subProductId);

			rsProduct = stmtProduct.executeQuery();

			if (rsProduct.next())
			{
				result = getProduct(rsProduct);
			}
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			Database.closeObject(rsProduct);
			Database.closeObject(stmtProduct);
		}

		return result;
	}

	public static SubscriberProduct getProduct(long subProductId) throws Exception
	{
		Connection connection = null;

		try
		{
			connection = Database.getConnection();

			return getProduct(connection, subProductId);
		}
		finally
		{
			Database.closeObject(connection);
		}
	}

	/**
	 * Created by NamTA<br>
	 * Created Date: 07/06/2012
	 * 
	 * @param isdn
	 * @param productId
	 * @return
	 * @throws Exception
	 */
	public static SubscriberProduct getUnterminated(String isdn, long productId) throws Exception
	{
		Connection connection = null;

		try
		{
			/**
			 * TODO: performanceTest
			 */

			// Thread.sleep(sleepTime);
			// return null;

			connection = Database.getConnection();

			return getUnterminated(connection, isdn, productId);
		}
		finally
		{
			Database.closeObject(connection);
		}
	}

	/**
	 * Created by NamTA<br>
	 * Created Date: 07/06/2012
	 * 
	 * @param connection
	 * @param isdn
	 * @param productId
	 * @return
	 * @throws Exception
	 */
	public static SubscriberProduct getUnterminated(Connection connection, String isdn, long productId) throws Exception
	{
		PreparedStatement stmtActive = null;
		ResultSet rsActive = null;

		SubscriberProduct result = null;

		try
		{
			String SQL = "Select * "
					+ "From SubscriberProduct "
					+ "Where isdn = ? and productId = ? and " + CONDITION_UNTERMINATED
					+ "Order by registerDate desc";

			stmtActive = connection.prepareStatement(SQL);
			stmtActive.setString(1, isdn);
			stmtActive.setLong(2, productId);

			rsActive = stmtActive.executeQuery();

			if (rsActive.next())
			{
				result = getProduct(rsActive);
			}
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			Database.closeObject(rsActive);
			Database.closeObject(stmtActive);
		}

		return result;
	}

	/**
	 * Created by NamTA<br>
	 * Created Date: 07/06/2012
	 * 
	 * @param connection
	 * @param isdn
	 * @param productId
	 * @return
	 * @throws Exception
	 */
	public static SubscriberProduct getBarring(Connection connection, String isdn, long productId) throws Exception
	{
		PreparedStatement stmtActive = null;
		ResultSet rsActive = null;

		SubscriberProduct result = null;

		try
		{
			String SQL = "Select * "
					+ "From SubscriberProduct "
					+ "Where isdn = ? and productId = ? and " + CONDITION_BARRING
					+ "Order by registerDate desc";

			stmtActive = connection.prepareStatement(SQL);
			stmtActive.setString(1, isdn);
			stmtActive.setLong(2, productId);

			rsActive = stmtActive.executeQuery();

			if (rsActive.next())
			{
				result = getProduct(rsActive);
			}
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			Database.closeObject(rsActive);
			Database.closeObject(stmtActive);
		}

		return result;
	}

	/**
	 * Created by NamTA<br>
	 * Created Date: 07/06/2012
	 * 
	 * @param isdn
	 * @param productId
	 * @return
	 * @throws Exception
	 */
	public static SubscriberProduct getBarring(String isdn, long productId) throws Exception
	{
		Connection connection = null;

		try
		{
			connection = Database.getConnection();

			return getBarring(connection, isdn, productId);
		}
		finally
		{
			Database.closeObject(connection);
		}
	}

	public static SubscriberProduct getActive(Connection connection, String isdn, long productId) throws Exception
	{
		PreparedStatement stmtActive = null;
		ResultSet rsActive = null;

		SubscriberProduct result = null;

		try
		{
			String SQL = "Select * "
					+ "From SubscriberProduct "
					+ "Where isdn = ? and productId = ? and " + CONDITION_ACTIVE
					+ "Order by registerDate desc";

			stmtActive = connection.prepareStatement(SQL);
			stmtActive.setString(1, isdn);
			stmtActive.setLong(2, productId);

			rsActive = stmtActive.executeQuery();

			if (rsActive.next())
			{
				result = getProduct(rsActive);
			}
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			Database.closeObject(rsActive);
			Database.closeObject(stmtActive);
		}

		return result;
	}

	public static SubscriberProduct getActive(String isdn, long productId) throws Exception
	{
		Connection connection = null;

		try
		{
			connection = Database.getConnection();

			return getActive(connection, isdn, productId);
		}
		finally
		{
			Database.closeObject(connection);
		}
	}

	public static SubscriberProduct getActive(Connection connection, long subscriberId, long productId) throws Exception
	{
		PreparedStatement stmtActive = null;
		ResultSet rsActive = null;

		SubscriberProduct result = null;

		try
		{
			String SQL = "Select * "
					+ "From SubscriberProduct "
					+ "Where subscriberId = ? and productId = ? and " + CONDITION_ACTIVE
					+ "Order by registerDate desc";

			stmtActive = connection.prepareStatement(SQL);
			stmtActive.setLong(1, subscriberId);
			stmtActive.setLong(2, productId);

			rsActive = stmtActive.executeQuery();

			if (rsActive.next())
			{
				result = getProduct(rsActive);
			}
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			Database.closeObject(rsActive);
			Database.closeObject(stmtActive);
		}

		return result;
	}

	public static SubscriberProduct getActive(long subscriberId, long productId) throws Exception
	{
		Connection connection = null;

		try
		{
			connection = Database.getConnection();

			return getActive(connection, subscriberId, productId);
		}
		finally
		{
			Database.closeObject(connection);
		}
	}

	public static SubscriberProduct getActive(Connection connection, long subscriberId, String isdn, long productId)
			throws Exception
	{
		if (subscriberId != DEFAULT_ID)
		{
			return getActive(connection, subscriberId, productId);
		}
		else
		{
			return getActive(connection, isdn, productId);
		}
	}

	public static SubscriberProduct getActiveX(String isdn, long productId, Date orderDate) throws Exception
	{
		Connection connection = null;

		try
		{
			connection = Database.getConnection();

			return getActiveX(connection, isdn, productId, orderDate);
		}
		finally
		{
			Database.closeObject(connection);
		}
	}

	public static SubscriberProduct getActiveX(Connection connection, String isdn, long productId, Date orderDate)
			throws Exception
	{
		PreparedStatement stmtActive = null;
		ResultSet rsActive = null;

		SubscriberProduct result = null;

		try
		{
			String SQL = "Select * "
					+ "From SubscriberOrder "
					+ "Where isdn = ? and productId = ? and status = ? "
					+ "and orderDate >= trunc(sysdate) and orderDate < (trunc(sysdate) + 1)"
					+ "Order by createdate desc";

			stmtActive = connection.prepareStatement(SQL);
			stmtActive.setString(1, isdn);
			stmtActive.setLong(2, productId);
			stmtActive.setLong(3, Constants.ORDER_STATUS_APPROVED);

			rsActive = stmtActive.executeQuery();

			if (rsActive.next())
			{
				result = getProduct(rsActive, 1);
			}
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			Database.closeObject(rsActive);
			Database.closeObject(stmtActive);
		}

		return result;

	}

	public static List<SubscriberProduct> getActive(Connection connection, long subscriberId, String isdn) throws Exception
	{
		PreparedStatement stmtActive = null;
		ResultSet rsActive = null;

		ArrayList<SubscriberProduct> result = new ArrayList<SubscriberProduct>();

		try
		{
			if (subscriberId != Constants.DEFAULT_ID)
			{
				String SQL = "Select * From SubscriberProduct Where isdn = ? and " + CONDITION_ACTIVE;

				stmtActive = connection.prepareStatement(SQL);
				stmtActive.setString(1, isdn);
			}
			else
			{
				String SQL = "Select * From SubscriberProduct Where subscriberId = ? and " + CONDITION_ACTIVE;

				stmtActive = connection.prepareStatement(SQL);
				stmtActive.setLong(1, subscriberId);
			}

			rsActive = stmtActive.executeQuery();

			if (rsActive.next())
			{
				result.add(getProduct(rsActive));
			}
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			Database.closeObject(rsActive);
			Database.closeObject(stmtActive);
		}

		return result;
	}

	/**
	 * Edited by NamTA<br>
	 * Modified Date: 17/05/2012
	 * 
	 * @param connection
	 * @param userId
	 * @param userName
	 * @param subscriberId
	 * @param isdn
	 * @param subscriberType
	 * @param productId
	 * @param campaignId
	 * @param languageId
	 * @param includeCurrentDay
	 * @return
	 * @throws Exception
	 */
	public static SubscriberProduct register(
			Connection connection, long userId, String userName, long subscriberId, String isdn, int subscriberType
			, long productId, long campaignId, String languageId, boolean includeCurrentDay, long merchantId, long sessionId, int score)
			throws Exception
	{
		SubscriberProduct subscriberProduct = null;

		PreparedStatement stmtRegister = null;

		try
		{
			Date now = new Date();

			ProductEntry product = ProductFactory.getCache().getProduct(productId);

			// calculate term of use date
			Date termDate = null;

			if (product.getTermPeriod() > 0)
			{
				termDate = DateUtil.addDate(now, product.getTermUnit(), product.getTermPeriod());
			}

			// calculate expire date
			Date expirationDate = null;
			Date graceDate = null;

			if (product.isSubscription())
			{
				int quantity = 1;

				int expirationPeriod = product.getSubscriptionPeriod();

				String expirationUnit = product.getSubscriptionUnit();

				if (campaignId != DEFAULT_ID)
				{
					CampaignEntry campaign = CampaignFactory.getCache().getCampaign(campaignId);

					if ((campaign != null))
					{
						boolean isBonus = Boolean.parseBoolean(campaign.getParameter("BonusEnable", "false"));
						if (isBonus)
						{
							expirationPeriod = Integer.parseInt(campaign.getParameter("PromotionPeriod", "7"));

							expirationUnit = campaign.getParameter("PromotionUnit", "day");
						}
					}
				}

				expirationDate = calculateExpirationDate(now, expirationUnit, expirationPeriod, quantity);

				/**
				 * remove 1 day if expiration time includes current day
				 */
				if (includeCurrentDay)
				{
					Calendar expiration = Calendar.getInstance();
					expiration.setTime(expirationDate);
					expiration.add(Calendar.DATE, -1);

					expirationDate = expiration.getTime();
				}
				graceDate = calculateGraceDate(expirationDate, product.getGraceUnit(), product.getGracePeriod());
			}

			// check product are registered or not
			subscriberProduct = getActive(connection, subscriberId, isdn, productId);

			if (subscriberProduct != null)
			{
				//throw new AppException(Constants.ERROR_REGISTERED);
			}

			// register product for subscriber
			String sql = "Insert into SubscriberProduct " +
					"     (subProductId, userId, userName, createDate, modifiedDate " +
					"     , subscriberId, isdn, subscriberType, productId, languageId " +
					"     , registerDate, termDate, expirationDate, graceDate, barringStatus, supplierStatus, CampaignId, merchantId, sessionId, score) " +
					"Values " +
					"     (?, ?, ?, sysDate, sysDate " +
					"     , ?, ?, ?, ?, ? " +
					"     , ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			stmtRegister = connection.prepareStatement(sql);

			long subProductId = Database.getSequence(connection, "sub_product_seq");

			int barringStatus = Constants.USER_ACTIVE_STATUS;
			int supplierStatus = Constants.SUPPLIER_ACTIVE_STATUS;

			stmtRegister.setLong(1, subProductId);
			stmtRegister.setLong(2, userId);
			stmtRegister.setString(3, userName);

			stmtRegister.setLong(4, subscriberId);
			stmtRegister.setString(5, isdn);
			stmtRegister.setInt(6, subscriberType);
			stmtRegister.setLong(7, productId);
			stmtRegister.setString(8, languageId);

			stmtRegister.setTimestamp(9, DateUtil.getTimestampSQL(now));
			stmtRegister.setTimestamp(10, DateUtil.getTimestampSQL(termDate));
			stmtRegister.setTimestamp(11, DateUtil.getTimestampSQL(expirationDate));
			stmtRegister.setTimestamp(12, DateUtil.getTimestampSQL(graceDate));

			stmtRegister.setInt(13, barringStatus);
			stmtRegister.setInt(14, supplierStatus);
			stmtRegister.setLong(15, campaignId);
			stmtRegister.setLong(16, merchantId);
			stmtRegister.setLong(17, sessionId);
			stmtRegister.setInt(18, score);

			stmtRegister.execute();

			// bind return
			// bind order
			subscriberProduct = new SubscriberProduct();

			subscriberProduct.setUserId(userId);
			subscriberProduct.setUserName(userName);

			subscriberProduct.setSubscriberId(subscriberId);
			subscriberProduct.setSubProductId(subProductId);
			subscriberProduct.setProductId(productId);

			subscriberProduct.setSubscriberType(subscriberType);
			subscriberProduct.setIsdn(isdn);

			subscriberProduct.setRegisterDate(now);
			subscriberProduct.setTermDate(termDate);
			subscriberProduct.setExpirationDate(expirationDate);
			subscriberProduct.setGraceDate(graceDate);

			subscriberProduct.setBarringStatus(barringStatus);
			subscriberProduct.setSupplierStatus(supplierStatus);
			subscriberProduct.setMerchantId(merchantId);

			if (product.isAuditEnable())
			{
				SubscriberActivateImpl.addActivate(
						connection, userId, userName, subscriberId, isdn, subProductId, subscriberProduct.getRegisterDate()
						, subscriberProduct.getBarringStatus(), subscriberProduct.getSupplierStatus(), "");
			}
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			Database.closeObject(stmtRegister);
		}

		return subscriberProduct;
	}

	/**
	 * Edited by NamTA<br>
	 * Modified Date: 17/05/2012
	 * 
	 * @param userId
	 * @param userName
	 * @param subscriberId
	 * @param isdn
	 * @param subscriberType
	 * @param productId
	 * @param campaignId
	 * @param languageId
	 * @param includeCurrentDay
	 * @return
	 * @throws Exception
	 */
	public static SubscriberProduct register(
			long userId, String userName, long subscriberId, String isdn, int subscriberType
			, long productId, long campaignId, String languageId, boolean includeCurrentDay, long merchantId, long sessionId, int score)
			throws Exception
	{
		Connection connection = null;

		try
		{
			/**
			 * TODO; PerformanceTest
			 */

			// Thread.sleep(sleepTime);
			// SubscriberProduct subPro = new SubscriberProduct();
			// subPro.setExpirationDate(new Date());
			//
			// return subPro;

			connection = Database.getConnection();

			return register(connection, userId, userName, subscriberId, isdn, subscriberType, productId, campaignId, languageId,
					includeCurrentDay, merchantId, sessionId, score);
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			Database.closeObject(connection);
		}
	}

	public static void unregister(Connection connection, long userId, String userName, long subProductId, long productId)
			throws Exception
	{
		SubscriberProduct subscriberProduct = null;

		PreparedStatement stmtSubscription = null;

		try
		{
			String sql = "Update SubscriberProduct "
					+ "   Set 	userId = ?, userName = ?, modifiedDate = sysDate "
					+ "   		, unregisterDate = sysDate, barringStatus = ?, supplierStatus = ? "
					+ "	  Where subProductId = ? and " + CONDITION_UNTERMINATED;

			stmtSubscription = connection.prepareStatement(sql);

			stmtSubscription.setLong(1, userId);
			stmtSubscription.setString(2, userName);
			stmtSubscription.setInt(3, Constants.USER_CANCEL_STATUS);
			stmtSubscription.setInt(4, Constants.SUPPLIER_CANCEL_STATUS);
			stmtSubscription.setLong(5, subProductId);

			stmtSubscription.execute();

			if (stmtSubscription.getUpdateCount() == 0)
			{
				throw new AppException(Constants.ERROR_UNREGISTERED);
			}

			ProductEntry product = ProductFactory.getCache().getProduct(productId);

			if (product.isAuditEnable())
			{
				subscriberProduct = getProduct(connection, subProductId);

				SubscriberActivateImpl.unregister(
						connection, userId, userName, subscriberProduct.getSubscriberId()
						, subscriberProduct.getIsdn(), subscriberProduct.getProductId(), subscriberProduct.getUnregisterDate(),
						"");
			}
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			Database.closeObject(stmtSubscription);
		}
	}

	public static void unregister(long userId, String userName, long subProductId, long productId) throws Exception
	{
		Connection connection = null;

		try
		{
			connection = Database.getConnection();

			unregister(connection, userId, userName, subProductId, productId);
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			Database.closeObject(connection);
		}
	}

	public static void barringBySupplier(Connection connection, long userId, String userName, long subProductId)
			throws Exception
	{
		SubscriberProduct subscriberProduct = null;

		PreparedStatement stmtSubscription = null;

		try
		{
			subscriberProduct = getProduct(connection, subProductId);

			if (subscriberProduct == null)
			{
				throw new AppException(Constants.ERROR_UNREGISTERED);
			}
			else if (subscriberProduct.getSupplierStatus() == Constants.SUPPLIER_BARRING_STATUS)
			{
				return;
			}

			ProductEntry product = ProductFactory.getCache().getProduct(subscriberProduct.getProductId());

			String sql = "Update SubscriberProduct " +
					"     Set    userId = ?, userName = ?, modifiedDate = sysDate, supplierStatus = ? " +
					"     Where  subProductId = ? and unregisterDate is null ";

			stmtSubscription = connection.prepareStatement(sql);

			stmtSubscription.setLong(1, userId);
			stmtSubscription.setString(2, userName);
			stmtSubscription.setInt(3, Constants.SUPPLIER_BARRING_STATUS);
			stmtSubscription.setLong(4, subProductId);

			stmtSubscription.execute();

			subscriberProduct.setSupplierStatus(Constants.SUPPLIER_BARRING_STATUS);

			if (product.isAuditEnable())
			{
				SubscriberActivateImpl.updateActivate(
						connection, userId, userName, subscriberProduct.getSubscriberId()
						, subscriberProduct.getIsdn(), subscriberProduct.getProductId()
						, new Date(), subscriberProduct.getBarringStatus(), subscriberProduct.getSupplierStatus(), "");
			}
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			Database.closeObject(stmtSubscription);
		}
	}

	public static void barringBySupplier(long userId, String userName, long subProductId)
			throws Exception
	{
		Connection connection = null;

		try
		{
			connection = Database.getConnection();

			barringBySupplier(connection, userId, userName, subProductId);
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			Database.closeObject(connection);
		}
	}

	public static SubscriberProduct subscription(
			Connection connection, long userId, String userName, long subProductId, boolean fullOfCharge, int quantity)
			throws Exception
	{
		SubscriberProduct subscriberProduct = null;

		PreparedStatement stmtSubscription = null;

		try
		{
			subscriberProduct = getProduct(connection, subProductId);

			if (subscriberProduct == null)
			{
				throw new AppException(Constants.ERROR_UNREGISTERED);
			}

			ProductEntry product = ProductFactory.getCache().getProduct(subscriberProduct.getProductId());

			// if (!product.isSubscription())
			// {
			// throw new AppException(Constants.ERROR_SUBSCRIPTION_NOT_FOUND);
			// }

			String subscriptionUnit = fullOfCharge ? product.getSubscriptionUnit() : "daily";
			int subscriptionPeriod = fullOfCharge ? product.getSubscriptionPeriod() : quantity;

			int gracePeriod = product.getGracePeriod();
			String graceUnit = product.getGraceUnit();

			// extend subscription date
			Date expirationDate = DateUtil.addDate(subscriberProduct.getExpirationDate(), subscriptionUnit, subscriptionPeriod);
			Date graceDate = DateUtil.addDate(expirationDate, graceUnit, gracePeriod);

			// prepare SQL
			String SQL = "Update SubscriberProduct "
					+ "   Set userId = ?, userName = ?, modifiedDate = sysDate "
					+ "       , supplierStatus = ?, expirationDate = ?, graceDate = ? "
					+ "   Where subProductId = ? and unregisterDate is null ";

			stmtSubscription = connection.prepareStatement(SQL);

			stmtSubscription.setLong(1, userId);
			stmtSubscription.setString(2, userName);
			stmtSubscription.setInt(3, Constants.SUPPLIER_ACTIVE_STATUS);
			stmtSubscription.setTimestamp(4, DateUtil.getTimestampSQL(expirationDate));
			stmtSubscription.setTimestamp(5, DateUtil.getTimestampSQL(graceDate));
			stmtSubscription.setLong(6, subProductId);

			stmtSubscription.execute();

			if (stmtSubscription.getUpdateCount() == 0)
			{
				throw new AppException(Constants.ERROR_UNREGISTERED);
			}

			if (product.isAuditEnable()
					&& (subscriberProduct.getSupplierStatus() != Constants.SUPPLIER_ACTIVE_STATUS))
			{
				SubscriberActivateImpl.updateActivate(
						connection, userId, userName, subscriberProduct.getSubscriberId()
						, subscriberProduct.getIsdn(), subscriberProduct.getProductId()
						, new Date(), subscriberProduct.getBarringStatus(), subscriberProduct.getSupplierStatus(), "");
			}

			subscriberProduct.setExpirationDate(expirationDate);
			subscriberProduct.setGraceDate(graceDate);
			subscriberProduct.setSupplierStatus(Constants.SUPPLIER_ACTIVE_STATUS);
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			Database.closeObject(stmtSubscription);
		}

		return subscriberProduct;
	}

	public static SubscriberProduct subscription(
			long userId, String userName, long subProductId, boolean fullOfCharge, int quantity)
			throws Exception
	{
		Connection connection = null;

		try
		{
			connection = Database.getConnection();

			return subscription(connection, userId, userName, subProductId, fullOfCharge, quantity);
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			Database.closeObject(connection);
		}
	}

	public static void changeLanguage(Connection connection, long userId, String userName, long subProductId, String languageId)
			throws Exception
	{
		PreparedStatement stmtProduct = null;

		try
		{
			// prepare SQL
			String SQL = "Update 	SubscriberProduct "
					+ "   Set 		userId = ?, userName = ?, modifiedDate = sysDate, languageId = ? "
					+ "   Where 	subProductId = ? and unregisterDate is null ";

			stmtProduct = connection.prepareStatement(SQL);

			// update
			stmtProduct.setLong(1, userId);
			stmtProduct.setString(2, userName);
			stmtProduct.setString(3, languageId);
			stmtProduct.setLong(4, subProductId);

			if (stmtProduct.executeUpdate() == 0)
			{
				throw new AppException("unregistered");
			}
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			Database.closeObject(stmtProduct);
		}
	}

	public static void changeLanguage(long userId, String userName, long subProductId, String languageId) throws Exception
	{
		Connection connection = null;

		try
		{
			connection = Database.getConnection();

			changeLanguage(connection, userId, userName, subProductId, languageId);
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			Database.closeObject(connection);
		}
	}

	public static void unbarringBySupplier(Connection connection, long userId, String userName, long subProductId)
			throws Exception
	{
		SubscriberProduct subscriberProduct = null;

		PreparedStatement stmtSubscription = null;

		try
		{
			subscriberProduct = getProduct(connection, subProductId);

			if (subscriberProduct == null)
			{
				throw new AppException(Constants.ERROR_UNREGISTERED);
			}
			else if (subscriberProduct.getSupplierStatus() == Constants.SUPPLIER_ACTIVE_STATUS)
			{
				return;
			}

			ProductEntry product = ProductFactory.getCache().getProduct(subscriberProduct.getProductId());

			String sql = "Update SubscriberProduct " +
					"     Set    userId = ?, userName = ?, modifiedDate = sysDate, supplierStatus = ? " +
					"     Where  subProductId = ? and unregisterDate is null ";

			stmtSubscription = connection.prepareStatement(sql);

			stmtSubscription.setLong(1, userId);
			stmtSubscription.setString(2, userName);
			stmtSubscription.setInt(3, Constants.SUPPLIER_ACTIVE_STATUS);
			stmtSubscription.setLong(4, subProductId);

			stmtSubscription.execute();

			subscriberProduct.setSupplierStatus(Constants.SUPPLIER_ACTIVE_STATUS);

			if (product.isAuditEnable())
			{
				SubscriberActivateImpl.updateActivate(
						connection, userId, userName, subscriberProduct.getSubscriberId()
						, subscriberProduct.getIsdn(), subscriberProduct.getProductId()
						, new Date(), subscriberProduct.getBarringStatus(), subscriberProduct.getSupplierStatus(), "");
			}
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			Database.closeObject(stmtSubscription);
		}
	}

	public static void unbarringBySupplier(long userId, String userName, long subProductId)
			throws Exception
	{
		Connection connection = null;

		try
		{
			connection = Database.getConnection();

			unbarringBySupplier(connection, userId, userName, subProductId);
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			Database.closeObject(connection);
		}
	}

	/**
	 * 
	 * Created by NamTA <br>
	 * Created Date: 16/05/2012
	 * 
	 * @param connection
	 * @param userId
	 * @param userName
	 * @param subProductId
	 * @return
	 * @throws Exception
	 */
	public static SubscriberProduct extendExpirationDate(Connection connection, long userId, String userName, long subProductId,
			long campaignId, boolean includeCurrentDay)
			throws Exception
	{
		SubscriberProduct subscriberProduct = null;

		PreparedStatement stmtSubscription = null;

		try
		{
			subscriberProduct = getProduct(connection, subProductId);

			if (subscriberProduct == null)
			{
				throw new AppException(Constants.ERROR_UNREGISTERED);
			}

			ProductEntry product = ProductFactory.getCache().getProduct(subscriberProduct.getProductId());

			Date now = new Date();
			Date expirationDate = subscriberProduct.getExpirationDate();
			Date graceDate = null;
			if (expirationDate.before(now))
				expirationDate = now;

			if (product.isSubscription())
			{
				int quantity = 1;

				int expirationPeriod = product.getSubscriptionPeriod();

				String expirationUnit = product.getSubscriptionUnit();

				if (campaignId != DEFAULT_ID)
				{
					CampaignEntry campaign = CampaignFactory.getCache().getCampaign(campaignId);

					if ((campaign != null) && campaign.isBonusEnable())
					{
						expirationPeriod = campaign.getBonusPeriod();

						expirationUnit = campaign.getBonusUnit();
					}
				}

				expirationDate = calculateExpirationDate(expirationDate, expirationUnit, expirationPeriod, quantity);

				if (includeCurrentDay)
				{
					/**
					 * remove 1 day if expiration time includes current day
					 */
					if (includeCurrentDay)
					{
						Calendar expiration = Calendar.getInstance();
						expiration.setTime(expirationDate);
						expiration.add(Calendar.DATE, -1);

						expirationDate = expiration.getTime();
					}
				}

				graceDate = DateUtil.addDate(expirationDate, product.getGraceUnit(), product.getGracePeriod());
			}

			String sql = "Update SubscriberProduct " +
					"     Set    userId = ?, userName = ?, modifiedDate = sysDate, supplierStatus = ?, " +
					"     expirationDate = ?, graceDate = ? " +
					"     Where  subProductId = ? and unregisterDate is null ";

			stmtSubscription = connection.prepareStatement(sql);

			stmtSubscription.setLong(1, userId);
			stmtSubscription.setString(2, userName);
			stmtSubscription.setInt(3, Constants.SUPPLIER_ACTIVE_STATUS);
			stmtSubscription.setTimestamp(4, DateUtil.getTimestampSQL(expirationDate));
			stmtSubscription.setTimestamp(5, DateUtil.getTimestampSQL(graceDate));
			stmtSubscription.setLong(6, subProductId);

			stmtSubscription.execute();

			subscriberProduct.setSupplierStatus(Constants.SUPPLIER_ACTIVE_STATUS);
			subscriberProduct.setExpirationDate(expirationDate);
			subscriberProduct.setGraceDate(graceDate);

			if (product.isAuditEnable())
			{
				SubscriberActivateImpl.updateActivate(
						connection, userId, userName, subscriberProduct.getSubscriberId()
						, subscriberProduct.getIsdn(), subscriberProduct.getProductId()
						, new Date(), subscriberProduct.getBarringStatus(), subscriberProduct.getSupplierStatus(), "");
			}

			return subscriberProduct;
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			Database.closeObject(stmtSubscription);
		}
	}

	/**
	 * 
	 * Created by NamTA<br>
	 * Created Date: 16/05/2012
	 * 
	 * @param userId
	 * @param userName
	 * @param subProductId
	 * @return
	 * @throws Exception
	 */
	public static SubscriberProduct extendExpirationDate(long userId, String userName, long subProductId,
			long campaignId, boolean includeCurrentDay)
			throws Exception
	{
		Connection connection = null;

		try
		{
			connection = Database.getConnection();

			return extendExpirationDate(connection, userId, userName, subProductId, campaignId, includeCurrentDay);
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			Database.closeObject(connection);
		}
	}

	public static String getMemberList(Connection connection, String isdn, long productId, boolean includeSuspended)
				throws Exception
	{
		String phoneBookList = "";
		PreparedStatement stmtSubscription = null;

		ResultSet resultSet = null;

		try
		{
			String sql = "";
			if (includeSuspended)
			{
				sql = "Select * from subscribergroup " +
							"Where  isdn = ? and productid = ? " +
							" and unregisterdate is null order by createdate";
			}
			else
			{
				sql = "Select * from subscribergroup " +
						"Where  isdn = ? and productid = ? and status = ? " +
						" and unregisterdate is null order by createdate";
			}

			stmtSubscription = connection.prepareStatement(sql);

			stmtSubscription.setString(1, isdn);
			stmtSubscription.setLong(2, productId);
			if (!includeSuspended)
			{
				stmtSubscription.setInt(3, Constants.SUPPLIER_ACTIVE_STATUS);
			}
			resultSet = stmtSubscription.executeQuery();

			if (resultSet.next())
			{
				phoneBookList = com.fss.util.StringUtil.nvl(resultSet.getString("REFERALISDN"), "");
			}

			while (resultSet.next())
			{
				phoneBookList = phoneBookList + "," + com.fss.util.StringUtil.nvl(resultSet.getString("REFERALISDN"), "");
			}
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			Database.closeObject(stmtSubscription);
		}
		return phoneBookList;
	}

	public static String getMemberList(String isdn, String userName, long productId, boolean includeSuspended)
					throws Exception
	{
		Connection connection = null;
		String result;
		try
		{
			connection = Database.getConnection();

			result = getMemberList(connection, isdn, productId, includeSuspended);
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			Database.closeObject(connection);
		}
		return result;
	}

	public static boolean withdraw(long userId, String userName, long subscriberId, String isdn, String balanceType, double amount)
			throws Exception
	{
		Connection connection = Database.getConnection();
		connection.setAutoCommit(false);

		PreparedStatement stmtBalance = null;

		try
		{
			String SQL = "Update SubscriberBalance "
					+ "Set userId = ?, userName = ?, modifiedDate = sysDate, balanceAmount = nvl(balanceAmount, 0) - ? "
					+ "Where subscriberId = ? and balanceType = ? and nvl(balanceAmount, 0) >= ? ";

			stmtBalance = connection.prepareStatement(SQL);

			stmtBalance.setLong(1, userId);
			stmtBalance.setString(2, userName);
			stmtBalance.setDouble(3, amount);
			stmtBalance.setString(4, isdn);
			stmtBalance.setString(5, balanceType);
			stmtBalance.setDouble(6, amount);

			stmtBalance.execute();

			if (stmtBalance.getUpdateCount() == 0)
			{
				throw new AppException("not-enough-money");
			}

			connection.commit();
		}
		catch (Exception e)
		{
			Database.rollback(connection);

			throw e;
		}
		finally
		{
			Database.closeObject(stmtBalance);
			Database.closeObject(connection);
		}

		return true;
	}

	/**
	 * Create by Do Tien Hung
	 * 
	 * @param connection
	 * @param userId
	 * @param userName
	 * @param subscriberId
	 * @param isdn
	 * @param subscriberType
	 * @param productId
	 * @param campaignId
	 * @param languageId
	 * @param includeCurrentDay
	 * @return
	 * @throws Exception
	 */

	public static SubscriberProduct registerProductBypassExisted(Connection connection, long userId, String userName,
			long subscriberId, String isdn, int subscriberType
			, long productId, long campaignId, String languageId, boolean includeCurrentDay) throws Exception
	{
		SubscriberProduct subscriberProduct = null;

		PreparedStatement stmtRegister = null;

		try
		{
			Date now = new Date();

			ProductEntry product = ProductFactory.getCache().getProduct(productId);

			// calculate term of use date
			Date termDate = null;

			if (product.getTermPeriod() > 0)
			{
				termDate = DateUtil.addDate(now, product.getTermUnit(), product.getTermPeriod());
			}

			// calculate expire date
			Date expirationDate = null;
			Date graceDate = null;

			if (product.isSubscription())
			{
				int quantity = 1;

				int expirationPeriod = product.getSubscriptionPeriod();

				String expirationUnit = product.getSubscriptionUnit();

				if (campaignId != DEFAULT_ID)
				{
					CampaignEntry campaign = CampaignFactory.getCache().getCampaign(campaignId);

					if ((campaign != null) && campaign.isBonusEnable())
					{
						expirationPeriod = campaign.getBonusPeriod();

						expirationUnit = campaign.getBonusUnit();
					}
				}

				expirationDate = calculateExpirationDate(now, expirationUnit, expirationPeriod, quantity);

				/**
				 * remove 1 day if expiration time includes current day
				 */
				if (includeCurrentDay)
				{
					Calendar expiration = Calendar.getInstance();
					expiration.setTime(expirationDate);
					expiration.add(Calendar.DATE, -1);

					expirationDate = expiration.getTime();
				}

				graceDate = DateUtil.addDate(expirationDate, product.getGraceUnit(), product.getGracePeriod());
			}

			// check product are registered or not
			String[] listProductId = product.getParameter("listProductId", "").split(",");
			for (int i = 0; i < listProductId.length; i++)
			{
				subscriberProduct = getActive(connection, subscriberId, isdn, Long.parseLong(listProductId[i]));
				if (subscriberProduct != null)
				{
					break;
				}
			}

			if (subscriberProduct != null)
			{
				long subProductId = subscriberProduct.getSubProductId();
				unregister(connection, userId, userName, subProductId, productId);
			}

			// register product for subscriber
			String sql = "Insert into SubscriberProduct " +
					"     (subProductId, userId, userName, createDate, modifiedDate " +
					"     , subscriberId, isdn, subscriberType, productId, languageId " +
					"     , registerDate, termDate, expirationDate, graceDate, barringStatus, supplierStatus) " +
					"Values " +
					"     (?, ?, ?, sysDate, sysDate " +
					"     , ?, ?, ?, ?, ? " +
					"     , ?, ?, ?, ?, ?, ?)";

			stmtRegister = connection.prepareStatement(sql);

			long subProductId = Database.getSequence(connection, "sub_product_seq");

			int barringStatus = Constants.USER_ACTIVE_STATUS;
			int supplierStatus = Constants.SUPPLIER_ACTIVE_STATUS;

			stmtRegister.setLong(1, subProductId);
			stmtRegister.setLong(2, userId);
			stmtRegister.setString(3, userName);

			stmtRegister.setLong(4, subscriberId);
			stmtRegister.setString(5, isdn);
			stmtRegister.setInt(6, subscriberType);
			stmtRegister.setLong(7, productId);
			stmtRegister.setString(8, languageId);

			stmtRegister.setTimestamp(9, DateUtil.getTimestampSQL(now));
			stmtRegister.setTimestamp(10, DateUtil.getTimestampSQL(termDate));
			stmtRegister.setTimestamp(11, DateUtil.getTimestampSQL(expirationDate));
			stmtRegister.setTimestamp(12, DateUtil.getTimestampSQL(graceDate));

			stmtRegister.setInt(13, barringStatus);
			stmtRegister.setInt(14, supplierStatus);

			stmtRegister.execute();

			// bind return
			// bind order
			subscriberProduct = new SubscriberProduct();

			subscriberProduct.setUserId(userId);
			subscriberProduct.setUserName(userName);

			subscriberProduct.setSubscriberId(subscriberId);
			subscriberProduct.setSubProductId(subProductId);
			subscriberProduct.setProductId(productId);

			subscriberProduct.setSubscriberType(subscriberType);
			subscriberProduct.setIsdn(isdn);

			subscriberProduct.setRegisterDate(now);
			subscriberProduct.setTermDate(termDate);
			subscriberProduct.setExpirationDate(expirationDate);
			subscriberProduct.setGraceDate(graceDate);

			subscriberProduct.setBarringStatus(barringStatus);
			subscriberProduct.setSupplierStatus(supplierStatus);

			if (product.isAuditEnable())
			{
				SubscriberActivateImpl.addActivate(
						connection, userId, userName, subscriberId, isdn, subProductId, subscriberProduct.getRegisterDate()
						, subscriberProduct.getBarringStatus(), subscriberProduct.getSupplierStatus(), "");
			}
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			Database.closeObject(stmtRegister);
		}

		return subscriberProduct;
	}

	public static SubscriberProduct registerProductBypassExisted(
			long userId, String userName, long subscriberId, String isdn, int subscriberType
			, long productId, long campaignId, String languageId, boolean includeCurrentDay)
			throws Exception
	{
		Connection connection = null;

		try
		{
			connection = Database.getConnection();

			return registerProductBypassExisted(connection, userId, userName, subscriberId, isdn, subscriberType, productId,
					campaignId, languageId,
					includeCurrentDay);
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			Database.closeObject(connection);
		}
	}

	/*
	 * Cáº­p nháº­t Ä‘iá»ƒm cho trÃ² chÆ¡i cÃ¢u há»�i (Viettel)
	 */
	public static int updateScore(long userId, String userName, long subscriberId, int score) throws Exception
	{
		int totalScore = 0;
		Connection connection = null;
		PreparedStatement scoreStmt = null;
		ResultSet rsScrore = null;
		try
		{
			connection = Database.getConnection();
			// get total score
			String sqlGet = "select score from subscriberProduct where subProductId = ?";
			scoreStmt = connection.prepareStatement(sqlGet);
			scoreStmt.setLong(1, subscriberId);
			rsScrore = scoreStmt.executeQuery();
			if (rsScrore.next())
			{
				totalScore = rsScrore.getInt("score");
			}

			totalScore = totalScore + score;

			// update score
			String sqlUpdate = "update SubscriberProduct set userId = ?, userName = ?, modifiedDate = sysdate, score = ? where subProductId = ?";

			scoreStmt = connection.prepareStatement(sqlUpdate);
			scoreStmt.setLong(1, userId);
			scoreStmt.setString(2, userName);
			scoreStmt.setInt(3, totalScore);
			scoreStmt.setLong(4, subscriberId);

			scoreStmt.executeUpdate();

		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			Database.closeObject(scoreStmt);
			Database.closeObject(connection);
			Database.closeObject(rsScrore);
		}

		return totalScore;
	}

	public static int getScoreByProductId(String isdn, long productId) throws Exception
	{
		int totalScore = 0;
		Connection connection = null;
		PreparedStatement scoreStmt = null;
		ResultSet rsScrore = null;
		try
		{
			connection = Database.getConnection();
			String sqlGet = "select * from (select score from subscriberProduct where isdn = ? and productId = ? order by registerDate desc) where rownum = 1";
			scoreStmt = connection.prepareStatement(sqlGet);
			scoreStmt.setString(1, isdn);
			scoreStmt.setLong(2, productId);
			rsScrore = scoreStmt.executeQuery();
			if (rsScrore.next())
			{
				totalScore = rsScrore.getInt("score");
			}
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			Database.closeObject(scoreStmt);
			Database.closeObject(connection);
			Database.closeObject(rsScrore);
		}
		return totalScore;
	}
	
	/*
	 * 
	 * Cap nhan so luong gui tin hang ngay
	 */
	
	public static void updateQuantity(long userId, String userName, long subscriberId, int quantity) throws Exception
	{
		int quantityTotal = 0;
		Connection connection = null;
		PreparedStatement scoreStmt = null;
		ResultSet rsScrore = null;
		try
		{
			connection = Database.getConnection();
			// get total score
			String sqlGet = "select quantity from subscriberProduct where subProductId = ?";
			scoreStmt = connection.prepareStatement(sqlGet);
			scoreStmt.setLong(1, subscriberId);
			rsScrore = scoreStmt.executeQuery();
			if (rsScrore.next())
			{
				quantityTotal = rsScrore.getInt("quantity");
			}

			quantityTotal = quantityTotal + quantity;

			// update score
			String sqlUpdate = "update SubscriberProduct set userId = ?, userName = ?, modifiedDate = sysdate, quantity = ? where subProductId = ?";

			scoreStmt = connection.prepareStatement(sqlUpdate);
			scoreStmt.setLong(1, userId);
			scoreStmt.setString(2, userName);
			scoreStmt.setInt(3, quantityTotal);
			scoreStmt.setLong(4, subscriberId);

			scoreStmt.executeUpdate();

		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			Database.closeObject(scoreStmt);
			Database.closeObject(connection);
			Database.closeObject(rsScrore);
		}
	}
	
	/**
	 * Reset so quantity khi tra loi cau hoi
	 */
	
	public static void updateQuantityToZero(long userId, String userName, long subscriberId) throws Exception
	{
		Connection connection = null;
		PreparedStatement scoreStmt = null;
		ResultSet rsScrore = null;
		try
		{
			connection = Database.getConnection();
			
			// update score
			String sqlUpdate = "update SubscriberProduct set userId = ?, userName = ?, modifiedDate = sysdate, quantity = ? where subProductId = ?";

			scoreStmt = connection.prepareStatement(sqlUpdate);
			scoreStmt.setLong(1, userId);
			scoreStmt.setString(2, userName);
			scoreStmt.setInt(3, 0);
			scoreStmt.setLong(4, subscriberId);

			scoreStmt.executeUpdate();

		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			Database.closeObject(scoreStmt);
			Database.closeObject(connection);
			Database.closeObject(rsScrore);
		}
	}
}
