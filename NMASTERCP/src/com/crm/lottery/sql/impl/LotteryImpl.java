package com.crm.lottery.sql.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import com.crm.kernel.sql.Database;
import com.crm.lottery.bean.LotteryEntry;
import com.crm.lottery.bean.LotterySubEntry;
import com.crm.util.DateUtil;

public class LotteryImpl
{
	public static int getRegionCode(String regionCode) throws Exception
	{
		PreparedStatement stmt = null;
		ResultSet result = null;
		Connection connection = null;
		try
		{
			connection = Database.getConnection();

			String SQL = "Select count(*) Total From VASLOTTERYREGION Where RegionCode = ? ";

			stmt = connection.prepareStatement(SQL);
			stmt.setString(1, regionCode);
			;

			result = stmt.executeQuery();

			if (result.next())
			{
				return result.getInt("Total");
			}
			return 0;
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

	public static ArrayList<LotteryEntry> getLottery(String regionCode, Date lotteryDate) throws Exception
	{
		LotteryEntry lottery = null;
		Connection connection = null;
		PreparedStatement stmt = null;
		ArrayList<LotteryEntry> lotteries = new ArrayList<LotteryEntry>();
		try
		{
			connection = Database.getConnection();

			String sqlQuery = "Select lotteryDate, prize, isLast, regionCode "
							+ " from VASLOTTERY "
							+ " where LotteryDate = trunc(?) and regionCode in "
							+ " (select regionCode from VASLOTTERYREGION where majorRegion in ("
							+ " select majorRegion from VASLOTTERYREGION where regionCode = ? "
							+ " )) ";

			stmt = connection.prepareStatement(sqlQuery);
			stmt.setTimestamp(1, DateUtil.getTimestampSQL(lotteryDate));
			stmt.setString(2, regionCode);
			ResultSet rs = stmt.executeQuery();

			while (rs.next())
			{
				lottery = new LotteryEntry();
				lottery.setLotteryDate(rs.getTimestamp("lotteryDate"));
				lottery.setRegionCode(rs.getString("regionCode"));
				lottery.setPrize(rs.getString("prize"));
				lottery.setLast(rs.getBoolean("isLast"));

				lotteries.add(lottery);
			}
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

		return lotteries;
	}

	public static String register(String isdn, long productId, String serviceAddress, String regionCode) throws Exception
	{
		Connection connection = null;
		PreparedStatement stmtRegion = null;
		PreparedStatement stmt = null;

		try
		{
			connection = Database.getConnection();

			String sqlQuery = "Select majorRegion from VASLOTTERYREGION where regionCode = ?";
			stmtRegion = connection.prepareStatement(sqlQuery);
			stmtRegion.setString(1, regionCode);

			ResultSet rs = stmtRegion.executeQuery();
			String majorRegion = "";
			if (rs.next())
			{
				majorRegion = rs.getString("majorRegion");
			}

			sqlQuery = "insert into VASLOTTERYSUBS(subsId, isdn, productId, serviceAddress, regionCode, "
							+ " majorRegion, createDate, lastChargingDate, lastSuccessDate, status ) "
							+ " values ( SUB_PRODUCT_SEQ.nextVal, ?, ?, ?, ?, "
							+ " ?, sysdate, sysdate, sysdate, 1) ";

			stmt = connection.prepareStatement(sqlQuery);

			stmt.setString(1, isdn);
			stmt.setLong(2, productId);
			stmt.setString(3, serviceAddress);
			stmt.setString(4, regionCode);
			stmt.setString(5, majorRegion);

			stmt.execute();
			return majorRegion;
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			Database.closeObject(stmtRegion);
			Database.closeObject(stmt);
			Database.closeObject(connection);
		}
	}

	public static void renew(long subsId) throws Exception
	{
		Connection connection = null;
		PreparedStatement stmt = null;

		try
		{
			connection = Database.getConnection();

			String sqlQuery = "update VASLOTTERYSUBS set "
							+ " lastSuccessDate = sysdate, "
							+ " status = 1 "
							+ " where subsId = ? ";

			stmt = connection.prepareStatement(sqlQuery);

			stmt.setLong(1, subsId);

			stmt.executeUpdate();
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

	public static void unregister(String isdn, String majorRegion) throws Exception
	{
		Connection connection = null;
		PreparedStatement stmt = null;

		try
		{
			connection = Database.getConnection();

			String sqlQuery = "update VASLOTTERYSUBS set "
							+ " unregisterDate = sysdate, "
							+ " status = 0 "
							+ " where isdn = ? and unregisterDate is null and majorRegion = ?";

			stmt = connection.prepareStatement(sqlQuery);

			stmt.setString(1, isdn);
			stmt.setString(2, majorRegion);

			stmt.executeUpdate();
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

	public static LotterySubEntry getSubsription(String isdn, String regionCode) throws Exception
	{
		LotterySubEntry sub = null;
		Connection connection = null;
		PreparedStatement stmt = null;

		try
		{
			connection = Database.getConnection();

			String sqlQuery = "Select subsId, isdn, serviceAddress, regionCode, majorRegion, createDate, lastChargingDate, status "
							+ " from VASLOTTERYSUBS "
							+ " where isdn = ? and regionCode = ? and unregisterDate is null ";

			stmt = connection.prepareStatement(sqlQuery);
			stmt.setString(1, isdn);
			stmt.setString(2, regionCode);
			ResultSet rs = stmt.executeQuery();

			if (rs.next())
			{
				sub = new LotterySubEntry();
				sub.setIsdn(rs.getString("isdn"));
				sub.setServiceAddress(rs.getString("serviceAddress"));
				sub.setRegionCode(rs.getString("regionCode"));
				sub.setMajorRegion(rs.getString("majorRegion"));
				sub.setCreateDate(rs.getTimestamp("createDate"));
				sub.setLastChargingDate(rs.getTimestamp("lastChargingDate"));
				sub.setActive(rs.getBoolean("status"));
				return sub;
			}
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

		return sub;
	}

	public static void addQueueWait(String isdn, long productId, String serviceAddress, String regionCode, String majorRegion,
			String sentRegion)
			throws Exception
	{
		Connection connection = null;
		PreparedStatement stmt = null;

		try
		{
			connection = Database.getConnection();

			String sqlQuery = "insert into VASLOTTERYWAIT(waitId, isdn, productId, serviceAddress, regionCode, majorRegion, sentRegion, receiveDate ) "
						+ " values ( LOTTERY_SEQ.nextVal, ?, ?, ?, ?, ?, ?, trunc(sysdate)) ";

			stmt = connection.prepareStatement(sqlQuery);

			stmt.setString(1, isdn);
			stmt.setLong(2, productId);
			stmt.setString(3, serviceAddress);
			stmt.setString(4, regionCode);
			stmt.setString(5, majorRegion);
			stmt.setString(6, sentRegion);

			stmt.execute();
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

	public static void sendSMS(String serviceAddress, String isdn, String productCode, String content, long productId)
			throws Exception
	{
		Connection connection = null;
		PreparedStatement stmt = null;

		try
		{
			connection = Database.getConnection();

			String sqlQuery = "INSERT INTO Send_SMS (ID, SOURCE_NUMBER, TARGET_NUMBER, PRODUCT_CODE, CONTENT, SENDDATE, PRODUCTID, SENDFLAG)"
							+ " VALUES(send_sms_seq.nextval, ?, ?, ?, ?, sysdate, ?, 0) ";

			stmt = connection.prepareStatement(sqlQuery);

			stmt.setString(1, serviceAddress);
			stmt.setString(2, isdn);
			stmt.setString(3, productCode);
			stmt.setString(4, content);
			stmt.setLong(5, productId);

			stmt.execute();
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
