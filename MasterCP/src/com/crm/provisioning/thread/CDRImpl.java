package com.crm.provisioning.thread;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.crm.kernel.sql.Database;

public class CDRImpl
{
	public static void insertCDR(CDR[] cdrs) throws Exception
	{
		Connection connection = null;

		try
		{
			connection = Database.getConnection();
			insertCDR(connection, cdrs);

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

	public static void insertCDR(Connection connection, CDR[] cdrs) throws Exception
	{
		PreparedStatement stmtCDR = null;

		try
		{
			int count = 0;

			String SQL = "insert into TELCOCDR(id, streamno, createdate, timestamp, chargeresult, isdn, " +
					"spid, serviceid, productid_telco, productid, chargemode, begintime, endtime, " +
					"subtype, cost, b_isdn, status, telcoID, THIRDPARTY,times,uplinkvolume,downlinkvolume,preDiscountFee,spBenifitRate"
					+ ",sPBenifitFee,apn,ggsnId,pKgSpId,pKgServiceId,pKgProductId,serviceCategory,contentProvision)" +
					" VALUES(CDR_SEQ.nextVal,?,sysdate,?,?,?,?,?,?,?,?,?,?,?,?,?,1,1,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			stmtCDR = connection.prepareStatement(SQL);

			for (CDR cdr : cdrs)
			{
				stmtCDR.setString(1, cdr.getStreamNo());
				stmtCDR.setString(2, cdr.getTimeStamp());
				stmtCDR.setString(3, cdr.getChargeResult());
				stmtCDR.setString(4, cdr.getMsIsdn());
				stmtCDR.setString(5, cdr.getSpID());
				stmtCDR.setString(6, cdr.getSpID() + cdr.getServiceID());
				stmtCDR.setString(7, cdr.getProductID_telco());
				stmtCDR.setString(8, cdr.getProductId());
				stmtCDR.setString(9, cdr.getChargeMode());
				stmtCDR.setString(10, cdr.getBeginTime());
				stmtCDR.setString(11, cdr.getEndTime());
				stmtCDR.setString(12, cdr.getPayType());
				stmtCDR.setString(13, cdr.getCost());
				stmtCDR.setString(14, cdr.getB_Isdn());
				stmtCDR.setString(15, cdr.getThirdParty());
				stmtCDR.setInt(16, cdr.getTimes());
				stmtCDR.setInt(17, cdr.getUplinkVolume());
				stmtCDR.setInt(18, cdr.getDownlinkVolume());
				stmtCDR.setInt(19, cdr.getPreDiscountFee());
				stmtCDR.setInt(20, cdr.getSpBenifitRate());
				stmtCDR.setInt(21, cdr.getsPBenifitFee());
				stmtCDR.setString(22, cdr.getApn());
				stmtCDR.setString(23, cdr.getGgsnId());
				stmtCDR.setString(24, cdr.getpKgSpId());
				stmtCDR.setString(25, cdr.getpKgServiceId());
				stmtCDR.setString(26, cdr.getpKgProductId());
				stmtCDR.setString(27, cdr.getServiceCategory());
				stmtCDR.setString(28, cdr.getContentProvision());

				stmtCDR.addBatch();
				count++;

				if (count >= 50)
				{
					stmtCDR.executeBatch();
					count = 0;
				}
			}
			if (count > 0)
			{
				stmtCDR.executeBatch();
			}
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			Database.closeObject(stmtCDR);
		}

	}

	public static String getProductId(String serviceId) throws Exception
	{
		String productId = "0";

		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try
		{
			connection = Database.getConnection();
			String sql = "select code from productEntry where productId = (select productId from productConfig where properties like ?)";
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, "%" + serviceId + "%");
			rs = stmt.executeQuery();
			if (rs.next())
			{
				productId = rs.getString("code");
			}
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			Database.closeObject(connection);
			Database.closeObject(stmt);
			Database.closeObject(rs);
		}

		return productId;
	}

	public static void updateExportStatus(int status, long orderId)
			throws Exception
	{
		PreparedStatement stmtOrder = null;

		Connection connection = null;
		try
		{
			connection = Database.getConnection();

			String sql = "Update telcocdr Set status = ? Where id = ? ";

			stmtOrder = connection.prepareStatement(sql);

			stmtOrder.setInt(1, status);
			stmtOrder.setLong(2, orderId);

			stmtOrder.execute();
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			Database.closeObject(stmtOrder);
			Database.closeObject(connection);
		}
	}

}
