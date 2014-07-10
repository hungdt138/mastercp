package com.crm.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.ObjectMessage;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.naming.InitialContext;

import org.apache.log4j.Logger;

import com.crm.kernel.message.Constants;
import com.crm.kernel.queue.QueueFactory;
import com.crm.provisioning.message.CommandMessage;
import com.fss.util.AppException;

/**
 * 
 * @author Nam <br>
 *         Last Modified Date: 29/06/2012
 * 
 */
public class WSQueueFactory extends QueueFactory
{
	public static Logger			log				= Logger.getLogger(WSQueueFactory.class);
	private static Destination		responseQueue	= null;
	public static QueueConnection	queueConnection	= null;
	public static boolean			IS_PERSISTENT	= true;

	public static synchronized void initContext() throws Exception
	{
		appQueues.clear();
		//InputStream serverConfig = null;
		InputStream jndiProperties = null;
		File file = null;
		String debugString = "";
		try
		{
			if (context == null)
			{
				// get server host
				//serverConfig = WSQueueFactory.class.getResourceAsStream("ServiceConfig.txt");

				AppProperties configProvider = WSConfiguration.getConfiguration();

				QUEUE_FACTORY = configProvider.getString("root.queue.factory", "jms/CCS");
				IS_PERSISTENT = configProvider.getBoolean("root.queue.persistent", true);

				ORDER_REQUEST_QUEUE = configProvider.getString("root.queue.orderRoute", "queue/OrderRoute");
				ORDER_RESPONSE_QUEUE = configProvider.getString("root.queue.orderResponse", "queue/OrderResponse");
				debugString = "OrderRouteQueue = " + ORDER_REQUEST_QUEUE
						+ " | OrderResponseQueue = " + ORDER_RESPONSE_QUEUE;
				ALARM_QUEUE = configProvider.getString("root.queue.alarm", "queue/Alarm");

				// COMMAND_ROUTE_QUEUE =
				// configProvider.getString("root.queue.commandRoute",
				// "queue/CommandRoute");
				COMMAND_LOG_QUEUE = configProvider.getString("root.queue.commandLog", "queue/CommandLog");
				COMMAND_CALLBACK = configProvider.getString("root.queue.commandCallback", "queue/CommandCallback");

				// connection mode
				String mode = configProvider.getString("root.queue.connection", "dedicated");

				if (mode.equalsIgnoreCase("dedicated"))
				{
					connectionMode = Constants.QUEUE_CONNECTION_DEDICATED;
				}
				else
				{
					connectionMode = Constants.QUEUE_CONNECTION_SHARING;
				}

				try
				{
					file = new File("vinaphone_service/jndi.properties");
					jndiProperties = new FileInputStream(file);
					debugMonitor("Load jndi properties from " + file.getAbsolutePath() + ".");
				}
				catch (Exception ex)
				{
					jndiProperties = WSQueueFactory.class.getResourceAsStream("jndi.properties");
					debugMonitor("File not found. Load jndi properties from ./applications/WSInterface/WEB-INF/classes/com/crm/util/jndi.properties.");
				}
				//jndiProperties = WSQueueFactory.class.getResourceAsStream("jndi.properties");
				
				// get context properties
				Properties properties = new Properties();
				
				properties.load(jndiProperties);
				
				debugString = "JndiHost = "  + properties.getProperty("org.omg.CORBA.ORBInitialHost")
							+ ":"  + properties.getProperty("org.omg.CORBA.ORBInitialPort") 
							+ " | " + debugString;
				System.setProperty("org.omg.CORBA.ORBInitialPort", properties.getProperty("org.omg.CORBA.ORBInitialPort"));
				System.setProperty("org.omg.CORBA.ORBInitialHost", properties.getProperty("org.omg.CORBA.ORBInitialHost"));

				context = new InitialContext(properties);
				

				debugMonitor("Load jndi properties success: " + (debugString != null ? debugString : ""));
			}

			// lookup the queue connection factory
			connectionFactory = (QueueConnectionFactory) context.lookup(QUEUE_FACTORY);

			if (connectionMode == Constants.QUEUE_CONNECTION_SHARING)
			{
				queueConnection = connectionFactory.createQueueConnection();

				queueConnection.start();
			}
		}
		catch (Exception e)
		{
			log.debug(e);
			context = null;
			connectionFactory = null;

			throw e;
		}
		finally
		{
			try
			{
				jndiProperties.close();
			}
			catch (Exception e2)
			{
				// TODO: handle exception
			}
		}
	}

	private synchronized static void debugMonitor(Object e)
	{
		log.debug(e);
	}

	public synchronized static QueueConnection createConnection() throws Exception
	{
		try
		{
			// initContext();
			//
			// if (connectionMode == Constants.QUEUE_CONNECTION_DEDICATED)
			// {
			// connection = connectionFactory.createQueueConnection();
			// connection.start();
			// }
			// else
			// {
			// connection = queueConnection;
			// }

			initContext();

			queueConnection = connectionFactory.createQueueConnection();
			queueConnection.start();
			responseQueue = WSQueueFactory.getQueue(WSQueueFactory.ORDER_RESPONSE_QUEUE);

		}
		catch (Exception e)
		{
			WSQueueFactory.closeQueue(queueConnection);
			WSQueueFactory.resetContext();
			throw e;
		}

		return queueConnection;
	}

	public static CommandMessage sendMessageToQueue(CommandMessage message, int timeout)
	{
		CommandMessage result = message;

		// QueueConnection connection = null;
		QueueSession queueSession = null;
		MessageConsumer responseConsumer = null;

		message.setTimeout(timeout);

		try
		{
			// connection = createConnection();

			if (queueConnection == null)
			{
				createConnection();
			}
			queueSession = queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);

			String requestId = WebserviceUtil.getSessionId();
			String callbackSelector = "JMSCorrelationID = '" + requestId + "'";

			message.setCorrelationID(String.valueOf(requestId));

			ObjectMessage objMessage = queueSession.createObjectMessage();
			objMessage.setObject(message);
			objMessage.setJMSCorrelationID(message.getCorrelationID());

			debugMonitor("SEND: JMSCorrelationID = " + requestId + ": " + message.toOrderString());

			WSQueueFactory.sendMessage(queueSession, WSQueueFactory.ORDER_REQUEST_QUEUE, objMessage, IS_PERSISTENT);

			responseConsumer = queueSession.createConsumer(responseQueue, callbackSelector);

			Message response = responseConsumer.receive(timeout);

			if (response == null)
			{
				debugMonitor("RECEIVE: null");
				result.setStatus(Constants.ORDER_STATUS_DENIED);
				result.setCause(Constants.ERROR_TIMEOUT);
			}
			else
			{
				Object responseContent = getContentMessage(response);

				if (responseContent != null & responseContent instanceof CommandMessage)
				{
					result = (CommandMessage) responseContent;
					debugMonitor("RECEIVE: CorelationId = " + result.getCorrelationID() + ": " + result.toOrderString());
				}
				else
				{
					debugMonitor("RECEIVE: " + response.toString());
					result.setStatus(Constants.ORDER_STATUS_DENIED);
					result.setCause(Constants.ERROR);
				}
			}
		}
		catch (JMSException jmex)
		{
			debugMonitor(jmex);
			result.setStatus(Constants.ORDER_STATUS_DENIED);
			result.setCause(Constants.ERROR_CONNECTION);
			WSQueueFactory.closeQueue(queueConnection);
		}
		catch (AppException e)
		{
			debugMonitor(e);
			result.setStatus(Constants.ORDER_STATUS_DENIED);
			result.setCause(e.getMessage());
		}
		catch (Exception ex)
		{
			debugMonitor(ex);
			result.setStatus(Constants.ORDER_STATUS_DENIED);
			result.setCause(Constants.ERROR_RESOURCE_BUSY);
		}
		finally
		{
			// WSQueueFactory.closeQueue(connection);
			try
			{
				WSQueueFactory.closeQueue(responseConsumer);
				WSQueueFactory.closeQueue(queueSession);
			}
			catch (Exception e)
			{

			}
		}

		if (result.getResponseTime() == null)
			result.setResponseTime(new Date());

		return result;
	}

	public static Object getContentMessage(Message message) throws JMSException
	{
		if (message instanceof ObjectMessage)
		{
			ObjectMessage objMessage = (ObjectMessage) message;
			return objMessage.getObject();
		}
		else
			return null;
	}
}
