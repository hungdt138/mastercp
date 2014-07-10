package com.gateway.security;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.codehaus.xfire.transport.http.XFireServletController;

import com.crm.kernel.message.Constants;
import com.crm.kernel.sql.Database;
import com.crm.util.GeneratorSeq;
import com.crm.util.StringUtil;
import com.fss.util.AppException;
import com.gateway.model.DeliveryRequest;
import com.gateway.model.ServiceRequest;
import com.gateway.model.ServiceResponse;
import com.gateway.util.WSExeption;
import com.gateway.util.WSResponseUtil;

public class Authentication
{

	private static Logger logger = Logger.getLogger(Authentication.class);

	protected static ConcurrentHashMap<String, AgentEntry> cMap = new ConcurrentHashMap<String, AgentEntry>();

	protected static void debugMonitor(Object e)
	{
		logger.debug(e);
	}

	protected static void infoMonitor(Object e)
	{
		logger.info(e);
	}

	public static AgentEntry getAgent(ServiceRequest request)
	{
		return cMap.get(request.getUsername().toLowerCase());
	}


	protected static void checkMaximumAmoutPerDay(Connection connection,
			long merchantId, int quantity, float amount, double maxAmount,
			ServiceResponse response) throws Exception
	{
		PreparedStatement stmtStatistic = null;

		ResultSet rsStatistic = null;

		java.sql.Date now = new java.sql.Date((new Date()).getTime());

		try
		{
			String SQL = "Select merchantId, type, sum(quantity), sum(amount) "
					+ " From MerchantStatistic "
					+ " Where merchantId = ? and sumDate >= trunc(?) and (sumDate < trunc(?) + 1) and status = 0 "
					+ " Group by merchantId, type";

			stmtStatistic = connection.prepareStatement(SQL);
			stmtStatistic.setLong(1, merchantId);
			stmtStatistic.setDate(2, now);
			stmtStatistic.setDate(3, now);

			rsStatistic = stmtStatistic.executeQuery();

			if (rsStatistic.next())
			{

			}
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			Database.closeObject(rsStatistic);
			Database.closeObject(stmtStatistic);
		}
	}

	protected static void checkMaximumAmoutPerDay(Connection connection,
			AgentEntry agent, int quantity, double amount) throws Exception
	{
		PreparedStatement stmtStatistic = null;

		ResultSet rsStatistic = null;

		java.sql.Date now = new java.sql.Date((new Date()).getTime());

		try
		{
			String SQL = "Select merchantId, type, sum(quantity), sum(amount) "
					+ " From MerchantStatistic "
					+ " Where merchantId = ? and sumDate >= trunc(?) and (sumDate < trunc(?) + 1) and status = 0 "
					+ " Group by merchantId, type";

			stmtStatistic = connection.prepareStatement(SQL);
			stmtStatistic.setLong(1, agent.getAgentId());
			stmtStatistic.setDate(2, now);
			stmtStatistic.setDate(3, now);

			rsStatistic = stmtStatistic.executeQuery();

			if (rsStatistic.next())
			{

			}
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			Database.closeObject(rsStatistic);
			Database.closeObject(stmtStatistic);
		}
	}

	protected static boolean validateOrderId(long orderId, String isdn, Connection connection) throws Exception
	{
		PreparedStatement stmtOrder = null;

		ResultSet rsOrder = null;

		try
		{
			String sql = "select 1 from subscriberOrder where orderId = ? and status = 0 and isdn = ?";
			stmtOrder = connection.prepareStatement(sql);
			stmtOrder.setLong(1, orderId);
			stmtOrder.setString(2, isdn);
			rsOrder = stmtOrder.executeQuery();

			if (rsOrder.next())
			{
				return true;
			}

		}
		catch (Exception e)
		{
			throw e;
		}
		return false;
	}
	
	
	public static String getAction(String isdn, long reqId) throws Exception
	{
		PreparedStatement stmtOrder = null;

		ResultSet rsOrder = null;

		Connection connection = null;
		
		String orderType = "";
		try
		{
			connection = Database.getConnection();
			String sql = "select orderType from subscriberOrder where orderNo = ? and isdn = ?";
			stmtOrder = connection.prepareStatement(sql);
			stmtOrder.setLong(1, reqId);
			stmtOrder.setString(2, isdn);
			rsOrder = stmtOrder.executeQuery();

			if (rsOrder.next())
			{
				orderType = rsOrder.getString("orderType");
			}

		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			Database.closeObject(connection);
			Database.closeObject(rsOrder);
			Database.closeObject(stmtOrder);
		}
		return orderType;
	}
	

	public static void checkIP(String s, String startIP, String endIP)
			throws AppException
	{

		if (startIP.equals("0") || endIP.equals("0"))
		{

		}
		else
		{
			String s3 = s.substring(0, s.lastIndexOf("."));
			String startIP3 = startIP.substring(0, startIP.lastIndexOf("."));
			String endIP3 = endIP.substring(0, endIP.lastIndexOf("."));

			String s4 = s.substring(s.lastIndexOf(".") + 1);
			String startIP4 = startIP.substring(startIP.lastIndexOf(".") + 1);
			String endIP4 = endIP.substring(endIP.lastIndexOf(".") + 1);

			int is = Integer.parseInt(s4);
			int istartIP = Integer.parseInt(startIP4);
			int iendIP = Integer.parseInt(endIP4);

			if (s3.equals(startIP3) && s3.equals(endIP3))
			{
				if (is <= iendIP)
				{
					if (is >= istartIP)
					{
						
					}
				}
				
			}
			else
			{
				throw new AppException(WSExeption.SVC_IP_REJECT);
			}
		}

	}
	
	public static void validateParameter(ServiceRequest request) throws AppException
	{
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		
		try
		{
			 df.parse(request.getReqDate());
		}
		catch (Exception e)
		{
			throw new AppException(WSExeption.SVC_PARAMETER_ERROR);
		}
		
	}

	public static synchronized void authenticate(ServiceRequest request,
			ServiceResponse response, boolean check) throws Exception
	{
		logger.debug("AuthenticatingReq = "+ StringUtil.toDebugString(request));

		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		
		//validate parameter
		validateParameter(request);
		
		if (response == null)
		{
			//response = new ServiceResponse(request.getrequestId(),df.parse(request.getrequestDate()));
		}

		Connection connection = Database.getConnection();

		PreparedStatement stmtAgent = null;

		ResultSet rsAgent = null;

		AgentEntry agent = null;

		String userName = request.getUsername().toLowerCase();

		try
		{
			request.setUsername(request.getUsername().toLowerCase());

			agent = getAgent(request);

			if (agent == null)
			{
				agent = new AgentEntry();

				stmtAgent = connection
						.prepareStatement("Select * From MerchantAgent A, MerchantEntry B Where A.screenName = ? and A.agentId = ? and"
								+ " A.merchantId = B.merchantId");

				stmtAgent.setString(1, request.getUsername());
				stmtAgent.setLong(2, request.getAgentId());

				rsAgent = stmtAgent.executeQuery();

				if (rsAgent.next())
				{
					agent.setMerchantId(rsAgent.getLong("merchantId"));
					agent.setAgentId(rsAgent.getLong("agentId"));
					agent.setCode(rsAgent.getString("code"));
					agent.setServiceAddress(rsAgent.getString("serviceAddress"));
					agent.setScreenName(request.getUsername());
					agent.setPassword(rsAgent.getString("password_"));
					agent.setStartIP(rsAgent.getString("startIP"));
					agent.setEndIP(rsAgent.getString("endIP"));
					agent.setMaxTps(rsAgent.getInt("maxTPS"));
					agent.setMaxConnection(rsAgent.getInt("maxsession"));

				}
				else
				{
					throw new AppException(WSExeption.SVC_ACCESS_AUTHENTICATION_ERROR);
				}

				cMap.put(userName, agent);
			}
			

			// check ip & spam
			HttpServletRequest req = XFireServletController.getRequest();
			checkIP(req.getRemoteAddr().toString(), agent.getStartIP(),
					agent.getEndIP());

			// check concurrent connections
			if (agent.getMaxConnection() == 0)
			{
				
			}
			else if (agent.getConnectionCounter() < agent.getMaxConnection())
			{
				agent.increement();
			}
			else
			{
				throw new AppException(WSExeption.SVC_CONNECTION_LIMIT);
			}

			// check max tps
			if (agent.getMaxTps() == 0)
			{

			}
			else if (agent.getTpsCounter() > agent.getMaxTps())
			{
				throw new AppException(WSExeption.SVC_TPS_LIMIT);
			}

			// check username & password
			if (request.getUsername().equals(agent.getScreenName()) && request.getPassword().equals(agent.getPassword()))
			{

			}
			else if (!request.getUsername().equals(agent.getScreenName()) || !request.getPassword().equals(agent.getPassword()))
			{
				throw new AppException(WSExeption.SVC_ACCESS_AUTHENTICATION_ERROR);
			}

			if (check)
			{
				// validate OrderId

				if (validateOrderId(request.getReqId(), request.getIsdn(), connection))
				{
					
				}
				else
				{
					throw new AppException(Constants.ERROR_INVALID_REQUEST);
				}
			}

			// check total amount
			// checkMaximumAmoutPerDay(connection, agent, 1, 0);

			logger.debug("AuthenticatingResp = " + response.toOrderString());
		}
		catch (Exception e)
		{
			logger.debug("Authenticating Exception = " + e);
			WSResponseUtil.updateResponse(response, e.toString());
		}
		finally
		{
			Database.closeObject(rsAgent);
			Database.closeObject(stmtAgent);
			Database.closeObject(connection);

			if (agent != null)
			{
				agent.decreement();

				cMap.replace(request.getUsername(), agent);
			}
		}
	}
}
