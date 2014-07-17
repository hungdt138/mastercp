package com.crm.football.sql.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.crm.football.bean.FootballInfo;
import com.crm.football.bean.FootballSubEntry;
import com.crm.kernel.sql.Database;

public class FootballImpl
{
	/**
	 * Get teamId
	 * 
	 * @param keyword
	 * @return
	 * @throws Exception
	 */
	public static FootballInfo validTeamName(String keyword) throws Exception
	{
		Connection connection = null;
		PreparedStatement stmt = null;
		FootballInfo info = null;

		try
		{
			connection = Database.getConnection();

			String sqlQuery = "SELECT keyId, teamCode "
								+ " FROM VasSoccerKeyword "
								+ " WHERE teamCode is not null and ("
								+ " availableKeyword like '%,'||?||',%' "
								+ " OR availableKeyword like '%,'||? "
								+ " OR availableKeyword like ?||',%' "
								+ " OR availableKeyword = ? "
								+ ")";

			stmt = connection.prepareStatement(sqlQuery);
			stmt.setString(1, keyword);
			stmt.setString(2, keyword);
			stmt.setString(3, keyword);
			stmt.setString(4, keyword);
			ResultSet rs = stmt.executeQuery();

			if (rs.next())
			{
				info = new FootballInfo();
				info.setId(rs.getLong("keyId"));
				info.setName(rs.getString("teamCode"));
				info.setCode(keyword);
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

		return info;
	}

	public static FootballInfo validLeagueName(String keyword) throws Exception
	{
		Connection connection = null;
		PreparedStatement stmt = null;
		FootballInfo info = null;

		try
		{
			connection = Database.getConnection();

			String sqlQuery = "SELECT keyId, leagueCode "
					+ " FROM VasSoccerKeyword "
					+ " WHERE leagueCode is not null and ("
					+ " availableKeyword like '%,'||?||',%' "
					+ " OR availableKeyword like '%,'||? "
					+ " OR availableKeyword like ?||',%' "
					+ " OR availableKeyword = ? "
					+ ")";

			stmt = connection.prepareStatement(sqlQuery);
			stmt.setString(1, keyword);
			stmt.setString(2, keyword);
			stmt.setString(3, keyword);
			stmt.setString(4, keyword);
			ResultSet rs = stmt.executeQuery();

			if (rs.next())
			{
				info = new FootballInfo();
				info.setId(rs.getLong("keyId"));
				info.setName(rs.getString("leagueCode"));
				info.setCode(keyword);
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

		return info;
	}

	public static long register(String isdn, long keyId, String serviceAddress, long productId, boolean isCharged)
			throws Exception
	{
		Connection connection = null;
		PreparedStatement subIdstmt = null;
		PreparedStatement stmt = null;

		long subsId = 0;
		try
		{

			connection = Database.getConnection();
			String strGetSubId = " SELECT SUB_PRODUCT_SEQ.nextVal FROM DUAL ";
			subIdstmt = connection.prepareStatement(strGetSubId);
			ResultSet rsId = subIdstmt.executeQuery();
			if (rsId.next())
			{
				subsId = rsId.getLong(1);
				if (subsId == 0)
					return subsId;
			}
			else
			{
				return subsId;
			}

			String sqlQuery = " INSERT INTO VasSoccerSubs (subsId, isdn, keyId, serviceAddress, "
							+ " productId, createDate, modifiedDate, "
							+ " lastChargingDate, lastSuccessDate, status) "
							+ " VALUES (?, ?, ?, ?, "
							+ " ?, sysdate, sysdate, sysdate, sysdate, 1)";
			if (!isCharged)
			{
				sqlQuery = " INSERT INTO VasSoccerSubs (subsId, isdn, keyId, serviceAddress, "
						+ " productId, createDate, modifiedDate, "
						+ " lastChargingDate, lastSuccessDate, status) "
						+ " VALUES (?, ?, ?, ?, "
						+ " ?, sysdate, sysdate, null, null, 1)";
			}

			stmt = connection.prepareStatement(sqlQuery);
			stmt.setLong(1, subsId);
			stmt.setString(2, isdn);
			stmt.setLong(3, keyId);
			stmt.setString(4, serviceAddress);
			stmt.setLong(5, productId);
			stmt.execute();
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			Database.closeObject(subIdstmt);
			Database.closeObject(stmt);
			Database.closeObject(connection);
		}

		return subsId;
	}

	public static void unregister(String isdn, long productId) throws Exception
	{
		Connection connection = null;
		PreparedStatement stmt = null;

		try
		{
			connection = Database.getConnection();

			String sqlQuery = " UPDATE VasSoccerSubs SET "
							+ " unregisterDate = sysdate, "
							+ " status = 0 "
							+ " WHERE isdn = ? AND productId = ? ";

			stmt = connection.prepareStatement(sqlQuery);
			stmt.setString(1, isdn);
			stmt.setLong(2, productId);
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

	public static void renew(long subsId) throws Exception
	{
		Connection connection = null;
		PreparedStatement stmt = null;

		try
		{
			connection = Database.getConnection();

			String sqlQuery = " UPDATE VasSoccerSubs SET "
							+ " modifiedDate = sysdate, "
							+ " lastSuccessDate = sysdate, "
							+ " status = 1 "
							+ " WHERE subsId = ? ";

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

	public static void addQueueWait(long subsId, String isdn, long productId, String serviceAddress, long keyId, boolean isNew)
			throws Exception
	{
		Connection connection = null;
		PreparedStatement stmt = null;

		try
		{
			connection = Database.getConnection();

			String sqlQuery = "INSERT INTO VASSOCCERWAIT(subsId, isdn, serviceAddress, keyId, productId, createDate, isNew ) "
						+ " VALUES ( ?, ?, ?, ?, ?, trunc(sysdate), ?) ";

			stmt = connection.prepareStatement(sqlQuery);

			stmt.setLong(1, subsId);
			stmt.setString(2, isdn);
			stmt.setString(3, serviceAddress);
			stmt.setLong(4, keyId);
			stmt.setLong(5, productId);
			stmt.setBoolean(6, isNew);

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

	public static FootballSubEntry getSubsription(String isdn, long productId, long keyId) throws Exception
	{
		FootballSubEntry sub = null;
		Connection connection = null;
		PreparedStatement stmt = null;

		try
		{
			connection = Database.getConnection();

			String sqlQuery = "Select subsId, isdn, serviceAddress, keyId, createDate, lastChargingDate, status "
							+ " from VASSOCCERSUBS "
							+ " where isdn = ? and productId = ? and keyId = ? and unregisterDate is null ";

			stmt = connection.prepareStatement(sqlQuery);
			stmt.setString(1, isdn);
			stmt.setLong(2, productId);
			stmt.setLong(3, keyId);
			ResultSet rs = stmt.executeQuery();

			if (rs.next())
			{
				sub = new FootballSubEntry();
				sub.setSubsId(rs.getLong("subsId"));
				sub.setIsdn(rs.getString("isdn"));
				sub.setServiceAddress(rs.getString("serviceAddress"));
				sub.setKeyId(keyId);
				sub.setProductId(productId);
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

	public static List<FootballSubEntry> getSubsriptions(String isdn, long productId) throws Exception
	{
		Connection connection = null;
		PreparedStatement stmt = null;

		List<FootballSubEntry> subs = new ArrayList<FootballSubEntry>();

		try
		{
			connection = Database.getConnection();

			String sqlQuery = "Select subsId, isdn, serviceAddress, keyId, createDate, lastChargingDate, status "
							+ " from VASSOCCERSUBS "
							+ " where isdn = ? and productId = ? and unregisterDate is null ";

			stmt = connection.prepareStatement(sqlQuery);
			stmt.setString(1, isdn);
			stmt.setLong(2, productId);
			ResultSet rs = stmt.executeQuery();

			while (rs.next())
			{
				FootballSubEntry sub = new FootballSubEntry();
				sub.setSubsId(rs.getLong("subsId"));
				sub.setIsdn(rs.getString("isdn"));
				sub.setServiceAddress(rs.getString("serviceAddress"));
				sub.setKeyId(rs.getLong("keyId"));
				sub.setProductId(productId);
				sub.setCreateDate(rs.getTimestamp("createDate"));
				sub.setLastChargingDate(rs.getTimestamp("lastChargingDate"));
				sub.setActive(rs.getBoolean("status"));
				subs.add(sub);
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

		return subs;
	}
}
