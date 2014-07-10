package com.gateway.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;


import com.crm.kernel.message.Constants;
import com.crm.kernel.queue.QueueFactory;
import com.crm.kernel.sql.Database;
import com.crm.provisioning.message.CommandMessage;
import com.crm.provisioning.util.ResponseConstants;

import com.crm.util.AppProperties;
import com.crm.util.GeneratorSeq;
import com.crm.util.StringUtil;
import com.crm.util.WSConfiguration;
import com.gateway.model.DeliveryRequest;
import com.gateway.model.DeliveryResponse;
import com.gateway.model.ServiceRequest;
import com.gateway.model.ServiceResponse;
import com.gateway.model.ServiceStatus;
import com.gateway.security.AgentEntry;
import com.gateway.security.Authentication;
import com.gateway.util.WSResponseUtil;
import com.gateway.util.WSExeption;

public class WebseviceBase
{
	private static Logger						loggerger		= Logger.getLogger(WebseviceBase.class);

	protected ConcurrentHashMap<String, Long>	cMap		= new ConcurrentHashMap<String, Long>();
	protected ConcurrentHashMap<String, Long>	tpsMap		= new ConcurrentHashMap<String, Long>();
	protected Calendar							starttime	= Calendar.getInstance();

	protected ServiceResponse authenticate(ServiceRequest request) throws Exception
	{
		return authenticate(request);
	}

	protected void updateStatistic(ServiceRequest request)
	{
		try
		{
			AgentEntry agent = Authentication.getAgent(request);

			if (agent != null)
			{
				agent.decreement();
			}
		}
		catch (Exception e)
		{

		}
	}

	public ServiceStatus getStatus(ServiceRequest request) throws Exception
	{
		
		ServiceStatus response = new ServiceStatus();

		Connection connection = null;

		PreparedStatement stmtSubscription = null;

		ResultSet rsSubscription = null;

		try
		{
			Authentication.authenticate(request, response, false);

			if (response.getResult() != WSExeption.SUCCESS)
			{
				return response;
			}

			connection = Database.getConnection();

			String sql = "Select A.* From SubscriberProduct A, ProductEntry B "
					+ "Where A.productId = B.productId and isdn = ? and B.code = ? and (unregisterDate is not null) ";

			stmtSubscription = connection.prepareStatement(sql);

			stmtSubscription.setString(1, request.getIsdn());
			stmtSubscription.setString(2, request.getProduct());

			rsSubscription = stmtSubscription.executeQuery();
			
			
			if (rsSubscription.next())
			{
				//response.setStatus(rsSubscription.getInt("supplierStatus"));
				response.setRegisterDate(rsSubscription.getDate("registerDate"));
				response.setExpirationDate(rsSubscription.getDate("expirationDate"));
			}
			else
			{
				WSResponseUtil.updateResponse(response, "unregistered");
			}
		}
		catch (Exception e)
		{
			loggerger.error(e, e);
			
			WSResponseUtil.updateResponse(response, "error");
		}
		finally
		{
			Database.closeObject(rsSubscription);
			Database.closeObject(stmtSubscription);

			Database.closeObject(connection);

			updateStatistic(request);
		}

		return response;
	}


//	protected CommandMessage sendMessageToQueue(CommandMessage message, ServiceRequest request, int timeout)
//	{
//		timeout = WSConfiguration.getConfiguration().getTimeout();
//		
//		if (timeout <= 0)
//		{
//			timeout = 40000;
//		}
//
//		message.setUserId(request.getAgentId());
//		message.setUserName(request.getUsername());
//
//		if (message.getChannel().equals(""))
//		{
//			message.setChannel(Constants.CHANNEL_WEB);
//		}
//
//		CommandMessage result = sendMessageToQueue(message, timeout);
//
//		return result;
//	}

	protected String getCommand(String service, String action)
	{
		return WSConfiguration.getConfiguration().getCommand(service, action);
	}

	protected String getShortCode(String service, String action)
	{
		return WSConfiguration.getConfiguration().getShortCode(service, action);
	}

	protected Properties getProperties(String service)
	{
		return WSConfiguration.getConfiguration().getProperties(service);
	}

//	public ServiceResponse processRequest(
//			ServiceRequest request, CommandMessage message, ServiceResponse response, boolean debug) throws Exception
//	{
//		String debugRequest = request.toOrderString();
//
//
//		try
//		{
//			if (debug)
//			{
//				loggerger.debug("SubCoreReq: " + debugRequest);
//			}
//
//			CommandMessage result = sendMessageToQueue(message, request, 0);
//		
//			if (debug)
//			{
//				loggerger.debug("SubCoreResp: " + result.toOrderString());
//			}
//			
//			if (result != null)
//			{
//				response = WSResponseUtil.updateResponse(response, result);
//			}
//			else
//			{
//				response = WSResponseUtil.updateResponse(response, "timeout");
//			}
//			
//			if (debug)
//			{
//				loggerger.debug("SubResp: " + response.toOrderString());
//			}
//		}
//		catch (Exception e)
//		{
//			loggerger.error(e, e);
//
//			response = WSResponseUtil.updateResponse(response, "error");
//		}
//		finally
//		{
//			updateStatistic(request);
//		}
//
//		return response;
//	}

	public void processRequest(ServiceRequest request, CommandMessage message, ServiceResponse response) throws Exception
	{
		processRequest(request, message, response, loggerger.isDebugEnabled());
	}
	
	public DeliveryResponse processRequest(
			DeliveryRequest request, CommandMessage message, DeliveryResponse response, boolean debug) throws Exception
	{
		String debugRequest = request.toOrderString();

		
		try
		{
			if (debug)
			{
				loggerger.debug("CoreReq: " + debugRequest);
			}

			CommandMessage result = sendMessageToQueue(message, request, 0);
			  
			if (debug)
			{
				loggerger.debug("CoreResp: " + result.toOrderString());
			}

			if (result != null)
			{
				response = WSResponseUtil.updateResponse(response,result);
			}
			else
			{
				response = WSResponseUtil.updateResponse(response, "timeout");
			}
			
			if (debug)
			{
				loggerger.debug("DeliveryResp: " + response.toOrderString());
			}
		}
		catch (Exception e)
		{
			loggerger.error(e, e);

			response = WSResponseUtil.updateResponse(response, "error");
		}
		finally
		{
			updateStatistic(request);
		}

		return response;
	}
	
	protected String getSessionId(boolean hexa)
	{
		int sequence = GeneratorSeq.getNextSeq();

		if (hexa)
		{
			String hex = Integer.toHexString(sequence).toUpperCase();

			while (hex.length() < 8)
			{
				hex = "0" + hex;
			}

			hex = "0x" + hex;

			return hex;
		}
		else
		{
			return String.valueOf(sequence);
		}
	}

	public CommandMessage sendOrder(Object request, String correlationId, long timeout)
	{
		if (timeout <= 0)
		{
			timeout = 30000;
		}

		long startTime = System.currentTimeMillis();

		QueueSession session = null;
		MessageProducer producer = null;
		MessageConsumer consumer = null;
		Message message = null;

		CommandMessage response = null;

		if (request instanceof CommandMessage)
		{
			response = (CommandMessage) request;
		}
		else
		{
			response = new CommandMessage();
		}

		try
		{
			Queue sendQueue = QueueFactory.getQueue(QueueFactory.ORDER_REQUEST_QUEUE);
			Queue waitQueue = QueueFactory.getQueue(QueueFactory.ORDER_RESPONSE_QUEUE);

			session = QueueFactory.getSession();
			message = QueueFactory.createObjectMessage(session, request);
			producer = session.createProducer(sendQueue);

			if (!correlationId.equals(""))
			{
				consumer = session.createConsumer(waitQueue, "JMSCorrelationID = '" + correlationId + "'");
			}

			producer.send(message);

			if (!correlationId.equals(""))
			{
				startTime = System.currentTimeMillis();
				message = consumer.receive(timeout);
			}

			Object content = QueueFactory.getContentMessage(message);

			if (content != null)
			{
				if (content instanceof CommandMessage)
				{
					response = (CommandMessage) content;
				}
				else
				{
					response.setStatus(Constants.ORDER_STATUS_DENIED);
					response.setCause(Constants.ERROR);
				}
			}
			else
			{
				response.setStatus(Constants.ORDER_STATUS_DENIED);
				response.setCause(Constants.ERROR_TIMEOUT);
			}

			// debugMonitor("cost time: " + (System.currentTimeMillis() -
			// startTime) + " for sessionId = " + correlationId);
			logger.info("cost time: " + (System.currentTimeMillis() - startTime) + " for sessionId = " + correlationId);
		}
		catch (JMSException e)
		{
			debugMonitor(e);

			response.setStatus(Constants.ORDER_STATUS_DENIED);
			response.setCause(Constants.ERROR_CONNECTION);
		}
		catch (AppException e)
		{
			debugMonitor(e);

			response.setStatus(Constants.ORDER_STATUS_DENIED);
			response.setCause(e.getMessage());
		}
		catch (Exception e)
		{
			debugMonitor(e);

			response.setStatus(Constants.ORDER_STATUS_DENIED);
			response.setCause(Constants.ERROR_RESOURCE_BUSY);
		}
		finally
		{
			QueueFactory.closeQueue(consumer);
			QueueFactory.closeQueue(producer);
			QueueFactory.closeQueue(session);
		}

		if ((response != null) && (response.getStatus() == Constants.ORDER_STATUS_DENIED))
		{
			logger.error("sessionId = " + correlationId + ", timeout = " + timeout + " : " + response);
		}

		return response;
	}

	protected CommandMessage sendOrder(CommandMessage message, RequestBase request)
	{
		CommandMessage result = null;

		String sessionId = message.getCorrelationID();

		try
		{
			message.setUserId(0);
			message.setUserName(request.getUserName());
			message.setChannel(Constants.CHANNEL_WEB);

			String requestContent = getRequest(sessionId, request);

			message.setRequestValue(ResponseConstants.SESSION_ID, sessionId);
			message.setRequestValue(ResponseConstants.VALUE, requestContent);

			result = sendOrder(message, sessionId, request.getTimeout());
		}
		catch (Exception e)
		{
			logger.error(e);
		}

		if (result != null)
		{
			if (logger.isDebugEnabled())
			{
				// logger.debug("response: " + result.toString());
			}
		}
		else
		{
			logger.error("request with sessionId = " + sessionId + " has response is null");
		}

		return result;
	}

}
