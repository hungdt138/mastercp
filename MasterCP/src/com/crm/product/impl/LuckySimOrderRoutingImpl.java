package com.crm.product.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Date;

import com.comverse_in.prepaid.ccws.BalanceEntity;
import com.comverse_in.prepaid.ccws.SubscriberEntity;
import com.crm.kernel.message.Constants;
import com.crm.kernel.sql.Database;
import com.crm.product.cache.ProductEntry;
import com.crm.product.cache.ProductRoute;
import com.crm.provisioning.cache.ProvisioningCommand;
import com.crm.provisioning.impl.ccws.CCWSConnection;
import com.crm.provisioning.message.CommandMessage;
import com.crm.provisioning.message.VNMMessage;
import com.crm.provisioning.thread.CommandInstance;
import com.crm.provisioning.thread.OrderRoutingInstance;
import com.crm.provisioning.util.CommandUtil;
import com.crm.subscriber.bean.SubscriberProduct;
import com.fss.util.AppException;
import com.fss.util.StringUtil;

public class LuckySimOrderRoutingImpl extends VNMOrderRoutingImpl
{

	private static final String	FACE_VALUE			= "faceValue";
	private static final String	SUBTRACTING_DAY		= "subtractingDay";
	private static final String	AMOUNT_CONDITION	= "amountCondition";
	private static final String	CAUSE				= "cause";

	@Override
	public void checkActionType(OrderRoutingInstance instance,
			ProductRoute orderRoute, ProductEntry product,
			CommandMessage order, SubscriberProduct subscriberProduct)
			throws Exception
	{

	}

	@Override
	public void validateBalance(OrderRoutingInstance instance,
			ProductRoute orderRoute, ProductEntry product, VNMMessage vnmMessage)
			throws Exception
	{

		// get parameters
		String strFaceValue = product.getParameter(FACE_VALUE, "");
		String strSubtractingDay = product.getParameter(SUBTRACTING_DAY, "0");
		String strAmountCondition = product.getParameter(AMOUNT_CONDITION, "0");
		String strISDN = vnmMessage.getIsdn();
		String strDateCondition = "";

		String strCreateDate = checkSubscriberProductExist(strISDN, vnmMessage.getProductId());

		// neu thue bao chua tung su dung dich vu truoc do
		if (strCreateDate.equals(""))
		{
			strDateCondition = StringUtil
					.format(com.fss.util.DateUtil.addDay(new Date(),
							-Integer.parseInt(strSubtractingDay)), "dd/MM/yyyy");
		}
		// truong hop nguoc lai da tung su dung dich vu truoc doi
		else
		{
			strDateCondition = strCreateDate;
		}

		// Kiem tra lich su nap tien
		boolean bCheckRecharge = checkRechargeHistory(strISDN, strFaceValue,
				strDateCondition);

		// Neu khong thoa man dieu kien nap tien
		if (!bCheckRecharge)
		{
			if (strCreateDate.equals(""))
			{
				throw new AppException(Constants.INVALID_RECHARGE_NOT_USED);
			}
			else
			{
				throw new AppException(Constants.INVALID_RECHARGE_USED);
			}
		}
		else
		{
			SubscriberEntity subscriberEntity = vnmMessage
					.getSubscriberEntity();
			BalanceEntity balanceCore = CCWSConnection.getBalance(
					subscriberEntity, CCWSConnection.CORE_BALANCE);
			double dAvailableBalacen = balanceCore.getAvailableBalance();

			// Neu tai khoan core lon hon hoac bang dieu kien dich vu
			if (dAvailableBalacen >= Double.parseDouble(strAmountCondition))
			{
				vnmMessage.setAmount(Double.parseDouble(strAmountCondition));
			}
			else
			{
				vnmMessage.setAmount(0);
			}
		}
	}

	protected boolean checkRechargeHistory(String isdn, String faceValue,
			String dateCondition) throws Exception
	{
		String strSQL = "select 'exist'"
				+ " from recharge_trigger a"
				+ " where 1 = 1 "
				+ "       and face_value > ? "
				+ "       and mdn = ? "
				+ "       and (recharge_date <= sysdate"
				+ "       		and recharge_date >= to_date(?,'dd/mm/yyyy'))";

		// String strSQL =
		// " select /*+ INDEX ( A RECHARGE_TRIGGER_MDN_IDX) */ * "
		// + " from ascs.recharge_trigger a "
		// + " where a.mdn = ? "
		// + " and a.face_value >= 20000 "
		// + " and a.recharge_date > to_date(?,'YYYY-MM-DD HH24:MI:SS') ";

		PreparedStatement stmt = null;
		Connection connection = null;
		ResultSet rs = null;
		try
		{
			connection = com.crm.kernel.sql.Database.getConnection();
			stmt = connection.prepareStatement(strSQL);
			stmt.setString(1, faceValue);
			stmt.setString(2, isdn);
			stmt.setString(3, dateCondition);
			rs = stmt.executeQuery();
			if (rs.next())
			{
				return true;
			}
		}
		finally
		{
			Database.closeObject(stmt);
			Database.closeObject(rs);
			Database.closeObject(connection);
		}
		return false;
	}

	protected String checkSubscriberProductExist(String iSDN, long productid) throws Exception
	{
		String strSQL = "	select to_char(createdate,'dd/mm/yyyy') "
						+ " from subscriberproduct"
						+ " where 1 = 1"
						+ " 		and isdn = ?"
						+ " 		and productid = ?"
						+ " 		order by createdate desc";
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try
		{
			connection = com.crm.kernel.sql.Database.getConnection();
			stmt = connection.prepareStatement(strSQL);
			stmt.setString(1, iSDN);
			stmt.setLong(2, productid);
			rs = stmt.executeQuery();
			if (rs.next())
			{
				return StringUtil.nvl(rs.getString(1), "");
			}
		}
		finally
		{
			Database.closeObject(stmt);
			Database.closeObject(rs);
			Database.closeObject(connection);
		}
		return "";
	}
}
