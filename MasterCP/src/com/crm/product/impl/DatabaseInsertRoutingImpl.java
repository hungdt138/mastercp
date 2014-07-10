package com.crm.product.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;
import com.crm.kernel.message.Constants;
import com.crm.kernel.sql.Database;
import com.crm.product.cache.ProductRoute;
import com.crm.provisioning.message.CommandMessage;
import com.crm.provisioning.thread.OrderRoutingInstance;
import com.crm.subscriber.impl.SubscriberEntryImpl;
import com.fss.util.AppException;
import com.fss.util.StringUtil;
import java.sql.Timestamp;

public class DatabaseInsertRoutingImpl extends OrderRoutingImpl
{

	// parameters input
	private static final String	KEYWORD		= "KEYWORD";
	private static final String	MDN			= "MDN";
	private static final String	SHORT_CODE	= "SHORTCODE";
	private static final String	DELIVERTIME	= "DELIVERTIME";
	private static final String	INSERTTIME	= "INSERTTIME";
	private static final String	CODE		= "CODE";
	private static final String	STATUS		= "STATUS";
	private static final String	RAWDATA		= "RAWDATA";
	private static final String	NEWDATA		= "NEWDATA";

	public CommandMessage parser(OrderRoutingInstance instance,
			ProductRoute orderRoute, CommandMessage order) throws Exception
	{
		// Check subscriber type
		order.setSubscriberType(SubscriberEntryImpl.getSubscriberType(order
				.getIsdn()));

		// Get Subscriber information
		String strISDN = StringUtil.nvl(order.getIsdn(), "");
		String strShortCode = StringUtil.nvl(orderRoute.getServiceAddress(), "");
		String strKeyWord = StringUtil.nvl(order.getKeyword(), "");
		Date dtRequestDate = order.getRequestTime();

		// Get properties
		String strSuccessMessage = orderRoute.getParameter("SuccessMessage",
				"He thong dang ban, vui long nhan tin lai sau");
		String strErrorMessage = orderRoute.getParameter("ErrorMessage",
				"He thong dang ban, vui long nhan tin lai sau");
		String strCode = orderRoute.getParameter("Code", "0");
		String strSeqence = orderRoute.getParameter("Sequence", "REQUEST_SEQ");
		String strStatus = orderRoute.getParameter("Status", "0");
		String strNewData = orderRoute.getParameter("NewData", order.getKeyword());
		String strRawData = orderRoute.getParameter("RawData", order.getKeyword());
		String strTableNames = orderRoute.getParameter("TableName", "Request");
		String[] argTableName = StringUtil.toStringArray(strTableNames, ",");

		String strSQL = orderRoute
				.getParameter(
						"SqlCommand",
						"Insert into [TableName](id,mdn,newdata,rawdata,shortcode,delivertime,inserttime,code,status) values([id],[mdn],[newdata],[rawdata],[shortcode],[delivertime],[inserttime],[code],[status])");

		String strParams = strSQL.substring(strSQL.indexOf("values"));
		strParams = strParams.substring(strParams.indexOf("(") + 1,
				strParams.length() - 1).trim();

		String[] argParams = StringUtil.toStringArray(strParams, ",");

		// replace parameters in SQL as char '?'
		for (String paramIndex : argParams)
		{
			strSQL = strSQL.replace(paramIndex, "?");
		}

		// Innit Connection and statement
		Connection connection = null;
		PreparedStatement stmt = null;

		try
		{
			// Check flag verify keyword
			if (orderRoute.getParameter("VerifyKeyword", "true").equals(
					Constants.VERIFY_KEYWORD))
			{
				smsParser(instance, orderRoute, order);
			}

			connection = Database.getConnection();
			connection.setAutoCommit(false);

			int iCount = 1;

			for (String tableNameIndex : argTableName)
			{
				// Create table
				createTable(tableNameIndex);
				String strSQLModified = strSQL.replace("[TableName]", tableNameIndex);
				stmt = connection.prepareStatement(strSQLModified);
				for (String paramIndex : argParams)
				{
					String strID = Database.getSequenceValue(connection, strSeqence);
					paramIndex = paramIndex.trim()
							.substring(1, paramIndex.length() - 1).toUpperCase()
							.trim();
					if (paramIndex.equals(KEYWORD))
					{
						stmt.setString(iCount, strKeyWord);
					}
					else if (paramIndex.equals(MDN))
					{
						stmt.setString(iCount, strISDN);
					}
					else if (paramIndex.equals(SHORT_CODE))
					{
						stmt.setString(iCount, strShortCode);
					}
					else if (paramIndex.equals(DELIVERTIME))
					{
						stmt.setTimestamp(iCount,
								new Timestamp(dtRequestDate.getTime()));
					}
					else if (paramIndex.equals(INSERTTIME))
					{
						stmt.setTimestamp(iCount,
								new Timestamp((new Date()).getTime()));
					}
					else if (paramIndex.equals(STATUS))
					{
						stmt.setString(iCount, strStatus);
					}
					else if (paramIndex.equals(CODE))
					{
						stmt.setString(iCount, strCode);
					}
					else if (paramIndex.equals(RAWDATA))
					{
						stmt.setString(iCount, strRawData);
					}
					else if (paramIndex.equals(NEWDATA))
					{
						stmt.setString(iCount, strNewData);
					}
					else if (paramIndex.equals("ID"))
					{
						stmt.setString(iCount, strID);
					}
					iCount++;
				}
				stmt.executeUpdate();
				iCount = 1;
			}

			connection.commit();
			order.setRequest(strSuccessMessage);
		}
		catch (Exception ex)
		{
			connection.rollback();
			order.setRequest(strErrorMessage);
			throw new AppException(Constants.ERROR_CREATE_ORDER_FAIL);
		}
		finally
		{
			Database.closeObject(stmt);
			connection.setAutoCommit(true);
			Database.closeObject(connection);
		}
		return order;
	}

	protected void createTable(String tableName)
	{
		String strSQL = "CREATE TABLE [TableName] ( "
					+ " ID             NUMBER PRIMARY KEY, "
					+ " MDN            VARCHAR2(15) NOT NULL, "
					+ " NEWDATA        NVARCHAR2(100) not null,  "
					+ " RAWDATA        NVARCHAR2(500) not null,  "
					+ " SHORTCODE      NUMBER not null, "
					+ " DELIVERTIME    DATE not null, "
					+ " INSERTTIME     DATE not null,  "
					+ " CODE           NUMBER not null , "
					+ " STATUS         NUMBER not null )";
		strSQL = strSQL.replace("[TableName]", tableName);
		Connection connection = null;
		PreparedStatement stmt = null;
		try
		{
			connection = Database.getConnection();
			stmt = connection.prepareStatement(strSQL);
			stmt.execute();
		}
		catch (Exception ex)
		{

		}
		finally
		{
			Database.closeObject(stmt);
			Database.closeObject(connection);
		}
	}

	public CommandMessage nextCommand(OrderRoutingInstance instance,
			ProductRoute orderRoute, CommandMessage order) throws Exception
	{
		return order;
	}
}
