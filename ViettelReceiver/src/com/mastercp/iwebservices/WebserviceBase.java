/**
 * ----------------------------------------------------------------- 
 * @ Copyright(c) 2013 Vietnamobile. JSC. All Rights Reserved.
 * ----------------------------------------------------------------- 
 * Date 	Author 		Version
 * ------------------------------------- 
 * Dec 18, 2013 hungdt  v1.0
 * -------------------------------------
 */
package com.mastercp.iwebservices;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.QueueSession;

import org.apache.log4j.Logger;

import com.crm.kernel.message.Constants;
import com.crm.kernel.queue.QueueFactory;
import com.crm.kernel.sql.Database;
import com.crm.provisioning.impl.charging.ChargingConstants;
import com.crm.provisioning.message.CommandMessage;
import com.crm.provisioning.util.ResponseConstants;
import com.crm.util.GeneratorSeq;
import com.crm.util.StringPool;
import com.crm.util.StringUtil;
import com.crm.util.User;
import com.crm.util.WSConfiguration;
import com.crm.util.WSSecurity;

import com.fss.util.AppException;

/**
 * @author hungdt
 * 
 */
public abstract class WebserviceBase implements ViettelServices
{

	protected static Logger	log	= Logger.getLogger(WebserviceBase.class);

	public WebserviceBase() {
		try
		{
			WSConfiguration.getConfiguration();
		}
		catch (Exception e)
		{
			log.error(e, e);
		}
	}

	protected void debugMonitor(Object e)
	{
		if (e instanceof Exception)
		{
			log.error(e, (Exception) e);
		}
		else
		{
			log.debug(e);
		}
	}

	protected void infoMonitor(Object e)
	{
		if (e instanceof Exception)
		{
			log.error(e, (Exception) e);
		}
		else
		{
			log.info(e);
		}
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

	public CommandMessage sendOrder(Object request, String correlationId,
			long timeout)
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
			Queue sendQueue = QueueFactory
					.getQueue(QueueFactory.ORDER_REQUEST_QUEUE);
			Queue waitQueue = QueueFactory
					.getQueue(QueueFactory.ORDER_RESPONSE_QUEUE);

			session = QueueFactory.getSession();
			message = QueueFactory.createObjectMessage(session, request);
			producer = session.createProducer(sendQueue);

			if (!correlationId.equals(""))
			{
				consumer = session
						.createConsumer(waitQueue, "JMSCorrelationID = '" + correlationId + "'");
			}
			try
			{
				long beforeSend = System.currentTimeMillis();
				producer.send(message);
				long afterSend = System.currentTimeMillis();
				log.info("Costime send:" + (afterSend - beforeSend) + ((afterSend - beforeSend) > 20 ? " high " : " low ") + "for sessionId = '" + correlationId);
			}
			catch (Exception ex)
			{
				log.info("Send message error");
			}
			finally
			{
				QueueFactory.closeQueue(producer);
			}
			if (!correlationId.equals(""))
			{
				startTime = System.currentTimeMillis();
				long beforeReceive = System.currentTimeMillis();
				message = consumer.receive(timeout);
				long afterReceive = System.currentTimeMillis();

				log.info("Costime Receive:" + (afterReceive - beforeReceive) + ((afterReceive - beforeReceive) > 1150 ? " high " : " low ") + "for sessionId = '" + correlationId);
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
			log.info("cost time: " + (System.currentTimeMillis() - startTime) + " for sessionId = " + correlationId);
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
			// QueueFactory.closeQueue(producer);
			QueueFactory.closeQueue(session);
		}

		if ((response != null) && (response.getStatus() == Constants.ORDER_STATUS_DENIED))
		{
			log.error("sessionId = " + correlationId + ", timeout = " + timeout + " : " + response);
		}

		return response;
	}

	protected CommandMessage sendOrder(CommandMessage message,
			RequestBase request)
	{
		CommandMessage result = null;

		String sessionId = message.getCorrelationID();
		
		int timeout = request.getTimeout();
		
		if(timeout == 0)
		{
			timeout = 30000;
		}
		
		try
		{
			message.setUserId(0);
			message.setUserName(request.getUsername());
			message.setChannel(Constants.CHANNEL_WEB);

			// String requestContent = getRequest(sessionId, request);

			message.setRequestValue(ResponseConstants.SESSION_ID, sessionId);
			// message.setRequestValue(ResponseConstants.VALUE, requestContent);

			result = sendOrder(message, sessionId, request.getTimeout());
			// result = new CommandMessage();
			// result.setStatus(Constants.ORDER_STATUS_APPROVED);
			// result.setCause(Constants.SUCCESS);
			// Thread.sleep(1200);

		}
		catch (Exception e)
		{
			log.error(e);
		}

		if (result != null)
		{
			if (log.isDebugEnabled())
			{
				// log.debug("response: " + result.toString());
			}
		}
		else
		{
			log.error("request with sessionId = " + sessionId + " has response is null");
		}

		return result;
	}

	protected boolean authenticate(String username, String password,
			String action)
	{
		WSSecurity security = WSSecurity.getSecuriry();

		User user = security.authenticate(username, password);

		if (user == null)
		{
			return false;
		}
		if (!user.hasPermission(action))
		{
			return false;
		}

		return true;
	}

	protected String getCommand(String service)
	{
		return WSConfiguration.getConfiguration().getCommand(service);
	}
	
	protected String getCommand(String service, String keyword)
	{
		return WSConfiguration.getConfiguration().getCommand(service, keyword);
	}

	protected String getShortCode(String service)
	{
		return WSConfiguration.getConfiguration().getShortCode(service);
	}

	protected Properties getProperties(String service)
	{
		return WSConfiguration.getConfiguration().getProperties(service);
	}

	protected String getErrorDetail(int code, String cause)
	{
		if (code != ErrorCode.E_FAILSE)
		{
			return (new ErrorCode()).getErrorDetail(code);
		}
		else
		{
			return cause;
		}
	}

	protected int getErrorCode(int status, String cause)
	{
		int code = ErrorCode.E_OK;

		if (status != Constants.ORDER_STATUS_APPROVED)
		{
			if (cause.equals(Constants.ERROR_TIMEOUT))
			{
				code = ErrorCode.E_SYSTEM_BUSY;
			}
			else if (cause.equals(Constants.ERROR_DENIED_STATUS))
			{
				code = ErrorCode.E_FAIL_TO_DEDUCT;
			}
			else if (cause.equals(Constants.ERROR_RESOURCE_BUSY))
			{
				code = ErrorCode.E_SYSTEM_BUSY;
			}
			else if (cause.equals(Constants.ERROR_CONNECTION))
			{
				code = ErrorCode.E_NOTCONNECT;
			}
			else if (cause.equals(Constants.ERROR_DENIED_SUBSCRIBER_TYPE))
			{
				code = ErrorCode.E_SUBCRIBER_NOT_FOUND;
			}
			else if (cause.equals(Constants.ERROR_INVALID_SYNTAX))
			{
				code = ErrorCode.E_INVALID_SYNTAX;
			}
			else if (cause.equals(Constants.ERROR_NOT_ENOUGH_MONEY))
			{
				code = ErrorCode.E_NOT_ENOUGH_MONEY;
			}
			else if (cause.equals(Constants.BALANCE_STATE_SUSPEND_S1))
			{
				code = ErrorCode.E_FAIL_DEBIT_S1;
			}
			else if (cause.equals(Constants.BALANCE_STATE_SUSPEND_S2))
			{
				code = ErrorCode.E_FAIL_DEBIT_S2;
			}
			else if (cause.equals(Constants.BALANCE_STATE_RETIRED_S3))
			{
				code = ErrorCode.E_FAIL_DEBIT_S3;
			}
			else if (cause.equals(Constants.BALANCE_STATE_IDLE))
			{
				code = ErrorCode.E_FAIL_TO_DEDUCT;
			}
			else if (cause.toLowerCase().equals("fraud lockout"))
			{
				code = ErrorCode.E_FRAUD_LOCKOUT;
			}
			else if (!cause.equals(Constants.SUCCESS))
			{
				code = ErrorCode.E_FAILSE;
			}
			else if (cause.equals(Constants.ERROR_COMMAND_NOT_FOUND))
			{
				code = ErrorCode.E_UNKNOW_COMMAND;
			}
			else if (cause.toLowerCase().equals("invalid amount"))
			{
				code = ErrorCode.E_INVALID_AMOUNT;
			}
		}

		return code;
	}
}
