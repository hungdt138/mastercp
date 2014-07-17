package com.crm.horoscope.sql.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Date;

import com.crm.horoscope.bean.HoroscopeEntity;
import com.crm.kernel.message.Constants;
import com.crm.kernel.sql.Database;
import com.crm.marketing.cache.CampaignEntry;
import com.crm.marketing.cache.CampaignFactory;
import com.crm.product.cache.ProductEntry;
import com.crm.product.cache.ProductFactory;
import com.crm.subscriber.bean.SubscriberProduct;
import com.crm.util.DateUtil;
import com.fss.util.AppException;

public class HoroscopeImpl
{
	public final static String	CONDITION_ACTIVE		= " (supplierStatus = " + Constants.SUPPLIER_ACTIVE_STATUS + ") ";

	public final static String	CONDITION_BARRING		= " (supplierStatus = " + Constants.SUPPLIER_BARRING_STATUS + ") ";

	public final static String	CONDITION_TERMINATED	= " (supplierStatus = " + Constants.SUPPLIER_CANCEL_STATUS + ") ";

	public final static String	CONDITION_UNTERMINATED	= " (supplierStatus != " + Constants.SUPPLIER_CANCEL_STATUS + ") ";

	private static long			DEFAULT_ID				= 0;
	
	public static SubscriberProduct register(Connection connection,
			long userId, String userName, long subscriberId, String isdn,
			int subscriberType, long productId, long campaignId,
			String languageId, String keyword, String birthday,
			boolean includeCurrentDay) throws Exception
	{
		SubscriberProduct subscriberProduct = null;

		PreparedStatement stmtRegister = null;

		try
		{
			Date now = new Date();

			ProductEntry product = ProductFactory.getCache().getProduct(
					productId);

			// calculate term of use date
			Date termDate = null;

			if (product.getTermPeriod() > 0)
			{
				termDate = DateUtil.addDate(now, product.getTermUnit(),
						product.getTermPeriod());
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
						expirationPeriod = campaign.getSchedulePeriod();
						expirationUnit = campaign.getScheduleUnit();
					}
				}

				expirationDate = calculateExpirationDate(now, expirationUnit,
						expirationPeriod, quantity);

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
				graceDate = calculateGraceDate(expirationDate,
						product.getGraceUnit(), product.getGracePeriod());
			}

			// check product are registered or not
			subscriberProduct = getActive(connection, subscriberId, isdn,
					productId, birthday);

			if (subscriberProduct != null)
			{
				throw new AppException(Constants.ERROR_REGISTERED);
			}

			// register product for subscriber
			String sql = "Insert into SubscriberProduct "
					+ "     (subProductId, userId, userName, createDate, modifiedDate "
					+ "     , subscriberId, isdn, subscriberType, productId, languageId "
					+ "     , registerDate, termDate, expirationDate, graceDate, barringStatus"
					+ "		, supplierStatus, CampaignId, description, birthday) "
					+ "Values " + "     (?, ?, ?, sysDate, sysDate "
					+ "     , ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			stmtRegister = connection.prepareStatement(sql);

			long subProductId = Database.getSequence(connection,
					"sub_product_seq");

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
			stmtRegister.setTimestamp(11,
					DateUtil.getTimestampSQL(expirationDate));
			stmtRegister.setTimestamp(12, DateUtil.getTimestampSQL(graceDate));

			stmtRegister.setInt(13, barringStatus);
			stmtRegister.setInt(14, supplierStatus);
			stmtRegister.setLong(15, campaignId);
			stmtRegister.setString(16, keyword);
			stmtRegister.setString(17, birthday);

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

	public static SubscriberProduct register(long userId, String userName,
			long subscriberId, String isdn, int subscriberType, long productId,
			long campaignId, String languageId, String keyword,
			String birthday, boolean includeCurrentDay) throws Exception
	{
		Connection connection = null;

		try
		{
			connection = Database.getConnection();

			return register(connection, userId, userName, subscriberId, isdn,
					subscriberType, productId, campaignId, languageId, keyword,
					birthday, includeCurrentDay);
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
	
	public static void unregister(Connection connection, long userId, String userName, String isdn, long productId)
			throws Exception
	{
		PreparedStatement stmtSubscription = null;

		try
		{
			String sql = "Update SubscriberProduct "
					+ "   Set 	userId = ?, userName = ?, modifiedDate = sysDate "
					+ "   		, unregisterDate = sysDate, barringStatus = ?, supplierStatus = ? "
					+ "	  Where isdn = ? and productid = ? and " + CONDITION_UNTERMINATED;

			stmtSubscription = connection.prepareStatement(sql);

			stmtSubscription.setLong(1, userId);
			stmtSubscription.setString(2, userName);
			stmtSubscription.setInt(3, Constants.USER_CANCEL_STATUS);
			stmtSubscription.setInt(4, Constants.SUPPLIER_CANCEL_STATUS);
			stmtSubscription.setString(5, isdn);
			stmtSubscription.setLong(6, productId);

			stmtSubscription.execute();

			if (stmtSubscription.getUpdateCount() == 0)
			{
				throw new AppException(Constants.ERROR_UNREGISTERED);
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

	public static void unregister(long userId, String userName, String isdn, long productId) throws Exception
	{
		Connection connection = null;

		try
		{
			connection = Database.getConnection();

			unregister(connection, userId, userName, isdn, productId);
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
	
	public static Date calculateExpirationDate(Date startDate, String subscriptionType, int period, int quantity)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);

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
	
	public static SubscriberProduct getUnterminated(String isdn, long productId, String birthday) throws Exception
	{
		Connection connection = null;

		try
		{
			connection = Database.getConnection();

			return getUnterminated(connection, isdn, productId, birthday);
		}
		finally
		{
			Database.closeObject(connection);
		}
	}
	
	public static SubscriberProduct getUnterminated(Connection connection, String isdn, long productId, String birthday) throws Exception
	{
		PreparedStatement stmtActive = null;
		ResultSet rsActive = null;

		SubscriberProduct result = null;

		try
		{
			StringBuilder SQL = new StringBuilder();
			SQL.append(" Select * From SubscriberProduct");
			SQL.append(" Where isdn = ? and productId = ?");
			SQL.append(" and " + CONDITION_UNTERMINATED);
			SQL.append(" and (select horoscope");
			SQL.append(" from horoscope");
			SQL.append(" where (to_date(startdate, 'dd/mm') <= to_date(birthday, 'dd/mm')");
			SQL.append(" and to_date(endate, 'dd/mm') >= to_date(birthday, 'dd/mm'))");
			SQL.append(" or (to_date(startdate, 'dd/mm') > to_date('1/12', 'dd/mm') and");
			SQL.append(" ((to_date(startdate, 'dd/mm') <=");
			SQL.append(" add_months(to_date(birthday, 'dd/mm'), 12) and");
			SQL.append(" to_date(endate, 'dd/mm') >= to_date(birthday, 'dd/mm')) or");
			SQL.append(" (to_date(startdate, 'dd/mm') <= to_date(birthday, 'dd/mm') and");
			SQL.append(" add_months(to_date(endate, 'dd/mm'), 12) >=");
			SQL.append(" to_date(birthday, 'dd/mm')))))");
			SQL.append(" =");
			SQL.append(" (select horoscope");
			SQL.append(" from horoscope");
			SQL.append(" where (to_date(startdate, 'dd/mm') <= to_date(?, 'dd/mm')");
			SQL.append(" and to_date(endate, 'dd/mm') >= to_date(?, 'dd/mm'))");
			SQL.append(" or (to_date(startdate, 'dd/mm') > to_date('1/12', 'dd/mm') and");
			SQL.append(" ((to_date(startdate, 'dd/mm') <=");
			SQL.append(" add_months(to_date(?, 'dd/mm'), 12) and");
			SQL.append(" to_date(endate, 'dd/mm') >= to_date(?, 'dd/mm')) or");
			SQL.append(" (to_date(startdate, 'dd/mm') <= to_date(?, 'dd/mm') and");
			SQL.append(" add_months(to_date(endate, 'dd/mm'), 12) >=");
			SQL.append(" to_date(?, 'dd/mm')))))");
			SQL.append(" Order by registerDate desc");

			stmtActive = connection.prepareStatement(SQL.toString());
			stmtActive.setString(1, isdn);
			stmtActive.setLong(2, productId);
			stmtActive.setString(3, birthday);
			stmtActive.setString(4, birthday);
			stmtActive.setString(5, birthday);
			stmtActive.setString(6, birthday);
			stmtActive.setString(7, birthday);
			stmtActive.setString(8, birthday);

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
		}
		catch (Exception e)
		{
			throw e;
		}

		return result;
	}
	
	public static SubscriberProduct getActive(Connection connection, long subscriberId, long productId, String birthday) throws Exception
	{
		PreparedStatement stmtActive = null;
		ResultSet rsActive = null;

		SubscriberProduct result = null;

		try
		{
			StringBuilder SQL = new StringBuilder();
			SQL.append(" Select /* + SUBS_SUBPCRIBERID_INX2 */ * From SubscriberProduct");
			SQL.append(" Where subscriberId = ? and productId = ? and " + CONDITION_ACTIVE);
			SQL.append(" and (select horoscope");
			SQL.append(" from horoscope");
			SQL.append(" where (to_date(startdate, 'dd/mm') <= to_date(birthday, 'dd/mm')");
			SQL.append(" and to_date(endate, 'dd/mm') >= to_date(birthday, 'dd/mm'))");
			SQL.append(" or (to_date(startdate, 'dd/mm') > to_date('1/12', 'dd/mm') and");
			SQL.append(" ((to_date(startdate, 'dd/mm') <=");
			SQL.append(" add_months(to_date(birthday, 'dd/mm'), 12) and");
			SQL.append(" to_date(endate, 'dd/mm') >= to_date(birthday, 'dd/mm')) or");
			SQL.append(" (to_date(startdate, 'dd/mm') <= to_date(birthday, 'dd/mm') and");
			SQL.append(" add_months(to_date(endate, 'dd/mm'), 12) >=");
			SQL.append(" to_date(birthday, 'dd/mm')))))");
			SQL.append(" =");
			SQL.append(" (select horoscope");
			SQL.append(" from horoscope");
			SQL.append(" where (to_date(startdate, 'dd/mm') <= to_date(?, 'dd/mm')");
			SQL.append(" and to_date(endate, 'dd/mm') >= to_date(?, 'dd/mm'))");
			SQL.append(" or (to_date(startdate, 'dd/mm') > to_date('1/12', 'dd/mm') and");
			SQL.append(" ((to_date(startdate, 'dd/mm') <=");
			SQL.append(" add_months(to_date(?, 'dd/mm'), 12) and");
			SQL.append(" to_date(endate, 'dd/mm') >= to_date(?, 'dd/mm')) or");
			SQL.append(" (to_date(startdate, 'dd/mm') <= to_date(?, 'dd/mm') and");
			SQL.append(" add_months(to_date(endate, 'dd/mm'), 12) >=");
			SQL.append(" to_date(?, 'dd/mm')))))");
			SQL.append(" Order by registerDate desc");

			stmtActive = connection.prepareStatement(SQL.toString());
			stmtActive.setLong(1, subscriberId);
			stmtActive.setLong(2, productId);
			stmtActive.setString(3, birthday);
			stmtActive.setString(4, birthday);
			stmtActive.setString(5, birthday);
			stmtActive.setString(6, birthday);
			stmtActive.setString(7, birthday);
			stmtActive.setString(8, birthday);
			
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
	
	public static SubscriberProduct getActive(Connection connection, String isdn, long productId, String birthday) throws Exception
	{
		PreparedStatement stmtActive = null;
		ResultSet rsActive = null;

		SubscriberProduct result = null;

		try
		{
			StringBuilder SQL = new StringBuilder();
			SQL.append(" Select * From SubscriberProduct");
			SQL.append(" Where isdn = ? and productId = ? and " + CONDITION_ACTIVE);
			SQL.append(" and (select horoscope");
			SQL.append(" from horoscope");
			SQL.append(" where (to_date(startdate, 'dd/mm') <= to_date(birthday, 'dd/mm')");
			SQL.append(" and to_date(endate, 'dd/mm') >= to_date(birthday, 'dd/mm'))");
			SQL.append(" or (to_date(startdate, 'dd/mm') > to_date('1/12', 'dd/mm') and");
			SQL.append(" ((to_date(startdate, 'dd/mm') <=");
			SQL.append(" add_months(to_date(birthday, 'dd/mm'), 12) and");
			SQL.append(" to_date(endate, 'dd/mm') >= to_date(birthday, 'dd/mm')) or");
			SQL.append(" (to_date(startdate, 'dd/mm') <= to_date(birthday, 'dd/mm') and");
			SQL.append(" add_months(to_date(endate, 'dd/mm'), 12) >=");
			SQL.append(" to_date(birthday, 'dd/mm')))))");
			SQL.append(" =");
			SQL.append(" (select horoscope");
			SQL.append(" from horoscope");
			SQL.append(" where (to_date(startdate, 'dd/mm') <= to_date(?, 'dd/mm')");
			SQL.append(" and to_date(endate, 'dd/mm') >= to_date(?, 'dd/mm'))");
			SQL.append(" or (to_date(startdate, 'dd/mm') > to_date('1/12', 'dd/mm') and");
			SQL.append(" ((to_date(startdate, 'dd/mm') <=");
			SQL.append(" add_months(to_date(?, 'dd/mm'), 12) and");
			SQL.append(" to_date(endate, 'dd/mm') >= to_date(?, 'dd/mm')) or");
			SQL.append(" (to_date(startdate, 'dd/mm') <= to_date(?, 'dd/mm') and");
			SQL.append(" add_months(to_date(endate, 'dd/mm'), 12) >=");
			SQL.append(" to_date(?, 'dd/mm')))))");
			SQL.append(" Order by registerDate desc");

			stmtActive = connection.prepareStatement(SQL.toString());
			stmtActive.setString(1, isdn);
			stmtActive.setLong(2, productId);
			stmtActive.setString(3, birthday);
			stmtActive.setString(4, birthday);
			stmtActive.setString(5, birthday);
			stmtActive.setString(6, birthday);
			stmtActive.setString(7, birthday);
			stmtActive.setString(8, birthday);

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

	public static SubscriberProduct getActive(Connection connection, long subscriberId, String isdn, long productId, String birthday)
			throws Exception
	{
		if (subscriberId != DEFAULT_ID)
		{
			return getActive(connection, subscriberId, productId, birthday);
		}
		else
		{
			return getActive(connection, isdn, productId, birthday);
		}
	}
	
	public static void insertHoroscope(HoroscopeEntity[] hs) throws Exception
	{
		if (hs == null)
			return;
		Connection connection = null;
		PreparedStatement stmt = null;

		try
		{
			connection = Database.getConnection();
			connection.setAutoCommit(false);

			String sqlQuery = "INSERT INTO VasHoroscope (CreateDate, Horoscope, StartDate, EndDate, Detail)"
							+ " VALUES(trunc(?), ?, ?, ?, ?) ";

			stmt = connection.prepareStatement(sqlQuery);
			for (HoroscopeEntity h : hs)
			{

				stmt.setTimestamp(1, DateUtil.getTimestampSQL(h.getCreateDate()));
				stmt.setString(2, h.getHoroscope());
				stmt.setString(3, h.getStartDate());
				stmt.setString(4, h.getEndDate());
				stmt.setString(5, h.getDetail());
				stmt.addBatch();
			}

			stmt.executeBatch();

			connection.commit();
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			Database.closeObject(stmt);
			Database.closeObject(connection);
		}
	}

}
