package com.crm.provisioning.impl.loyalty;

import com.crm.loyalty.cache.RankEntry;
import com.crm.loyalty.cache.RankFactory;
import com.crm.kernel.sql.Database;
import com.crm.product.cache.ProductFactory;
import com.crm.provisioning.cache.CommandAction;
import com.crm.provisioning.cache.ProvisioningCommand;
import com.crm.provisioning.impl.CommandImpl;
import com.crm.provisioning.message.CommandMessage;
import com.crm.provisioning.thread.CommandInstance;
import com.crm.subscriber.impl.SubscriberBalanceImpl;

import com.fss.util.AppException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

public class LoyaltyImpl extends CommandImpl
{
	public static String	NOT_REGISTERED	= "not-registered";

	public boolean getBalanceQuery(CommandInstance instance, ProvisioningCommand provisioningCommand, CommandMessage request) throws Exception
	{
		Connection connection = Database.getConnection();

		PreparedStatement stmtUsage = null;
		ResultSet rsUsage = null;

		PreparedStatement stmtBalance = null;
		ResultSet rsBalance = null;
		try
		{
			request.setCause("success");

			double balanceAmount = 0.0D;

			String SQL = "Select * From SubscriberBalance Where sourceAddress = ? and balanceType = 'LOYALTY' ";

			stmtBalance = connection.prepareStatement(SQL);
			stmtBalance.setString(1, request.getIsdn());

			rsBalance = stmtBalance.executeQuery();

			if (rsBalance.next())
			{
				balanceAmount = rsBalance.getDouble("balanceAmount");
			}
			else
			{
				throw new AppException(NOT_REGISTERED);
			}

			SQL = "Select * From SubscriberRank Where cycleDate = ? and sourceAddress = ? and balanceType = 'LOYALTY'";

			stmtUsage = connection.prepareStatement(SQL);

			stmtUsage.setDate(1, new java.sql.Date(request.getCycleDate().getTime()));
			stmtUsage.setString(2, request.getIsdn());

			rsUsage = stmtUsage.executeQuery();

			String response = "";

			double totalAmount = 0.0D;

			RankEntry rankRule = null;

			String rankStartDate = "";
			String rankExpirationDate = "";

			if (rsUsage.next())
			{
				request.setCause("success");

				if (rsUsage.getDate("expirationDate") == null)
				{
					request.setCause("not-exist");
				}
				else
				{
					rankRule = RankFactory.getCache().getRank(rsUsage.getLong("rankId"));
				}

				totalAmount = rsUsage.getDouble("totalAmount");

				SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
				if (rsUsage.getDate("startDate") != null)
				{
					rankStartDate = formatDate.format(rsUsage.getDate("startDate"));
				}
				if (rsUsage.getDate("expirationDate") != null)
				{
					rankExpirationDate = formatDate.format(rsUsage.getDate("expirationDate"));
				}
			}
			else
			{
				request.setCause(NOT_REGISTERED);
			}

			//response = ProductFactory.getCache().getProductMessage(request, "vni");

			if (rankRule != null)
			{
				response = response.replaceAll("<totalAmount>", String.valueOf(new Double(totalAmount).longValue()));
				response = response.replaceAll("<balanceAmount>", String.valueOf(new Double(balanceAmount).longValue()));
				response = response.replaceAll("<fromDate>", rankStartDate);
				response = response.replaceAll("<toDate>", rankExpirationDate);
				response = response.replaceAll("<rank>", rankRule.getTitle());
			}

			//instance.getDispatcher().sendSMS(request, request.getServiceAddress(), request.getIsdn(), response);

			request.setStatus(0);
		}
		catch (AppException e)
		{
			request.setCause(e.getMessage());
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			Database.closeObject(rsUsage);
			Database.closeObject(stmtUsage);
			Database.closeObject(rsBalance);
			Database.closeObject(stmtBalance);

			Database.closeObject(connection);
		}
		return true;
	}

	public boolean sendRedeemSMS(CommandAction instance, CommandMessage request) throws Exception
	{
		return true;
	}

	public boolean redeem(CommandAction instance, CommandMessage request) throws Exception
	{
		// return SubscriberBalanceImpl.withdraw(request);
		return false;
	}
}