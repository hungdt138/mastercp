package com.crm.subscriber.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.crm.kernel.sql.Database;

public class FunStoryServiceImpl
{
	public static boolean sendFunStory(Connection connection, String isdn, String serviceAddress,
			String productCode, int totalMT, String delayTime, long productId) throws Exception
	{
		boolean success = false;
		PreparedStatement stmtConfig = null;
		try
		{
			String sql = "INSERT INTO SEND_SMS  (ID, SOURCE_NUMBER, TARGET_NUMBER, PRODUCT_CODE, CONTENT, SENDDATE, PRODUCTID) "
						+"SELECT send_sms_seq.nextval, ?, ?, ?, storyContent, sysdate + " + delayTime + ", ? from funstory where trunc(createdate) = trunc(sysdate) and rownum < ?";
			stmtConfig = connection.prepareStatement(sql);
			stmtConfig.setString(1, serviceAddress);
			stmtConfig.setString(2, isdn);
			stmtConfig.setString(3, productCode);
			stmtConfig.setLong(4, productId);
			stmtConfig.setInt(5, totalMT + 1);
			
			stmtConfig.execute();
			
			success = true;
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			Database.closeObject(stmtConfig);
		}
		
		return success;
	}
	
	public static boolean sendFunStory(String isdn, String serviceAddress, String productCode,
			int totalMT, String delayTime, long productId) throws Exception
	{
		Connection connection = null;

		try
		{
			connection = Database.getConnection();

			return sendFunStory(connection, isdn, serviceAddress, productCode,
										totalMT, delayTime, productId);
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
}
