package com.crm.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.ObjectMessage;
import javax.jms.QueueSession;
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
	private static Destination		responseQueue		= null;
	// private static QueueConnection connection = null;
	private static MQConnectionPool	mqConnectionPool	= null;

	public static void resetContext() throws Exception
	{
		try
		{
			if (mqConnectionPool != null)
			{
				mqConnectionPool.destroyPool();
			}
		}
		catch (Exception e)
		{
			// TODO: handle exception
		}
	}

	public static synchronized void initContext(InputStream serverConfig,
			InputStream jndiProperties) throws Exception
	{
		appQueues.clear();
		try
		{
			if (mqConnectionPool == null || mqConnectionPool.isClosed())
			{
				// get server host
				AppProperties configProvider = new AppProperties();

				configProvider.load(serverConfig);

				QUEUE_FACTORY = configProvider.getString(
						"root.queue.factory", "jms/CCS");

				ORDER_REQUEST_QUEUE = configProvider.getString(
						"root.queue.orderRoute", "queue/OrderRoute");
				ORDER_RESPONSE_QUEUE = configProvider
						.getString("root.queue.orderResponse",
								"queue/OrderResponse");

				ALARM_QUEUE = configProvider.getString("root.queue.alarm",
						"queue/Alarm");

				COMMAND_ROUTE_QUEUE = configProvider.getString(
						"root.queue.commandRoute", "queue/CommandRoute");
				COMMAND_LOG_QUEUE = configProvider.getString(
						"root.queue.commandLog", "queue/CommandLog");
				COMMAND_CALLBACK = configProvider.getString(
						"root.queue.commandCallback",
						"queue/CommandCallback");

				// connection mode
				String mode = configProvider.getString(
						"root.queue.connection", "dedicated");

				if (mode.equalsIgnoreCase("dedicated"))
				{
					connectionMode = Constants.QUEUE_CONNECTION_DEDICATED;
				}
				else
				{
					connectionMode = Constants.QUEUE_CONNECTION_SHARING;
				}

				createConnectionFactory(configProvider);

				int maxActive = 10;
				int maxWait = 60000;
				int maxIdle = 10;

				if (mqConnectionPool == null)
				{
					mqConnectionPool = new MQConnectionPool(maxActive,
							maxWait, maxIdle);
				}
				mqConnectionPool.open();

				// get context properties
				Properties properties = new Properties();

				properties.load(jndiProperties);

				System.setProperty(
						"org.omg.CORBA.ORBInitialPort",
						properties.getProperty("org.omg.CORBA.ORBInitialPort"));
				System.setProperty(
						"org.omg.CORBA.ORBInitialHost",
						properties.getProperty("org.omg.CORBA.ORBInitialHost"));

				context = new InitialContext(properties);
			}

			// lookup the queue connection factory
			// connectionFactory = (QueueConnectionFactory) context
			// .lookup(QUEUE_FACTORY);

			if (connectionMode == Constants.QUEUE_CONNECTION_SHARING)
			{
				queueConnection = (com.sun.messaging.jms.QueueConnection) connectionFactory
						.createQueueConnection();

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
	}

	private synchronized static void debugMonitor(Object e)
	{
		log.debug(e);
	}

	public synchronized static void createConnection() throws Exception
	{
		InputStream serverConfig = null;
		InputStream jndiProperties = null;
		try
		{
			serverConfig = WSQueueFactory.class.getResourceAsStream("ServerConfig.txt");
			jndiProperties = WSQueueFactory.class.getResourceAsStream("jndi.properties");
			
			
			
			initContext(serverConfig, jndiProperties);

			// connection = WSQueueFactory.createQueueConnection();

			responseQueue = WSQueueFactory
					.getQueue(WSQueueFactory.ORDER_RESPONSE_QUEUE);
		}
		catch (Exception e)
		{
			// WSQueueFactory.closeQueue(connection);
			try
			{
				// connection.close();
			}
			catch (Exception ex)
			{

			}
			WSQueueFactory.resetContext();
			throw e;
		}
		finally
		{
			try
			{
				serverConfig.close();
				jndiProperties.close();
			}
			catch (IOException ioe)
			{
			}
		}
	}

	public static CommandMessage sendMessageToQueue(CommandMessage message,
			int timeout)
	{
		CommandMessage result = message;
		result.setRequestTime(message.getRequestTime());

		QueueSession queueSession = null;
		MessageConsumer responseConsumer = null;

		try
		{
			if (mqConnectionPool == null || mqConnectionPool.isClosed())
			{
				long createConn = System.currentTimeMillis();
				createConnection();
				long endCreateConn = System.currentTimeMillis();
				
//				log.debug("Create connection: " + (endCreateConn - createConn) + "ms");
			}

			MQConnection connection = null;
			try
			{
				long getConn = System.currentTimeMillis();
				connection = mqConnectionPool.getConnection();

				queueSession = connection.createSession();
				long endGetConn = System.currentTimeMillis();
				
//				log.debug("Get connection & session: " + (endGetConn - getConn) + "ms");
			}
			finally
			{
				mqConnectionPool.returnConnection(connection);
			}

			String requestId = WebserviceUtil.getSessionId();
			String callbackSelector = "JMSCorrelationID = '" + requestId
					+ "'";

			message.setCorrelationID(String.valueOf(requestId));

			ObjectMessage objMessage = queueSession.createObjectMessage();
			objMessage.setObject(message);
			objMessage.setJMSCorrelationID(message.getCorrelationID());

			long startSend =  System.currentTimeMillis();
			WSQueueFactory.sendMessage(queueSession,
					WSQueueFactory.ORDER_REQUEST_QUEUE, objMessage, false);

			long endSend = System.currentTimeMillis();
			
//			log.debug("Send to queue: " + (endSend - startSend) + "ms");
			responseConsumer = queueSession.createConsumer(responseQueue,
					callbackSelector);

			long startReceive =  System.currentTimeMillis();
			
			Message response = responseConsumer.receive(timeout);
			
			long endReceive =  System.currentTimeMillis();
			
//			log.debug("Receive from queue: " + (endReceive - startReceive) + "ms");
			if (response == null)
			{
				result.setStatus(Constants.ORDER_STATUS_DENIED);
				result.setCause(Constants.ERROR_TIMEOUT);
			}
			else
			{
				long startGetContent =  System.currentTimeMillis();
				Object responseContent = getContentMessage(response);

				if ((responseContent != null)
						& (responseContent instanceof CommandMessage))
				{
					result = (CommandMessage) responseContent;
				}
				else
				{
					result.setStatus(Constants.ORDER_STATUS_DENIED);
					result.setCause(Constants.ERROR);
				}
				
				long endGetContent =  System.currentTimeMillis();
				
//				log.debug("Get content from queue: " + (endGetContent - startGetContent) + "ms");
			}
		}
		catch (AppException e)
		{
			debugMonitor(e);
			result.setStatus(Constants.ORDER_STATUS_DENIED);
			result.setCause(Constants.ERROR);
		}
		catch (Exception ex)
		{
			debugMonitor(ex);
			result.setStatus(Constants.ORDER_STATUS_DENIED);
			result.setCause(Constants.ERROR);
		}
		finally
		{
			try
			{
				long startCloseConnection =  System.currentTimeMillis();
				queueSession.close();
				responseConsumer.close();
				long endCloseConnection =  System.currentTimeMillis();
//			log.debug("Close connection queue: " + (endCloseConnection - startCloseConnection) + "ms");
				
			}
			catch (JMSException e)
			{
			}
		}

		if (result.getResponseTime() == null)
			result.setResponseTime(new Date());

		return result;
	}

	public static Object getContentMessage(Message message)
			throws JMSException
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
