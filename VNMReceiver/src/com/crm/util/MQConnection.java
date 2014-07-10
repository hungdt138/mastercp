package com.crm.util;

import java.io.Serializable;

import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueSession;
import javax.jms.Session;

import com.crm.provisioning.cache.PoolableObject;
import com.crm.provisioning.message.CommandMessage;

public class MQConnection implements PoolableObject, ExceptionListener
{
	private QueueConnection		queueConnection	= null;
	private QueueSession		queueSession	= null;
	private Queue				queueWorking	= null;

	private boolean				hasError		= false;

	private long				poolId			= 0L;
	
	public long getPoolId()
	{
		return poolId;
	}

	public MQConnection(long poolId) throws Exception
	{
		this.poolId = poolId;
		queueConnection = WSQueueFactory.createQueueConnection();
		queueConnection.setExceptionListener(this);
		queueSession = queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
	}

	@Override
	public void activate()
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void destroy()
	{
		try
		{
			if (queueSession != null)
			{
				try
				{
					queueSession.close();
				}
				catch (Exception e)
				{
				}
			}
			if (queueConnection != null)
			{

				try
				{
					queueConnection.stop();
				}
				finally
				{
					queueConnection.close();
				}
			}
		}
		catch (Exception e)
		{
			debugMonitor(e);
		}
		finally
		{
			queueConnection = null;
			queueSession = null;
			queueWorking = null;
		}
	}

	@Override
	public void passivate()
	{
		// TODO Auto-generated method stub
	}

	@Override
	public boolean validate()
	{
		if (!hasError)
		{
			return true;
		}
		return false;
	}
	
	public void markError()
	{
		hasError = true;
	}

	public void debugMonitor(Object message)
	{
	}

//	public MessageConsumer createTempConsumer(String queueName) throws Exception
//	{
//		return createTempConsumer(queueName, null);
//	}

//	public MessageConsumer createTempConsumer(Queue queue) throws JMSException
//	{
//		return createTempConsumer(queue, null);
//	}
//
//	public MessageConsumer createTempConsumer(String queueName, String selector) throws Exception
//	{
//		Queue queue = QueueFactory.getQueue(queueName);
//
//		return createTempConsumer(queue, selector);
//	}
	
	public QueueSession getSession() throws JMSException
	{
		return queueSession;
	}
	
	public QueueSession createSession() throws JMSException
	{
		return queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
	}

//	public MessageConsumer createTempConsumer(Queue queue, String selector) throws JMSException
//	{
//		if (selector != null && !"".equals(selector))
//		{
//			return queueSession.createConsumer(queue, selector);
//		}
//		else
//		{
//			return queueSession.createConsumer(queue);
//		}
//	}

	public Message sendMessage(CommandMessage request, Queue queue, long timeout, boolean isPersistent) throws Exception
	{
		return sendMessage(request, request.getCorrelationID(), request.getTimeout(), queue, timeout, isPersistent);
	}

	public Message sendMessage(CommandMessage request, String queueName, long timeout, boolean isPersistent) throws Exception
	{
		Queue queue = WSQueueFactory.getQueue(queueName);
		return sendMessage(request, request.getCorrelationID(), request.getTimeout(), queue, timeout, isPersistent);
	}

	public Message sendMessage(Serializable request, String queueName, long timeout, boolean isPersistent) throws Exception
	{
		Queue queue = WSQueueFactory.getQueue(queueName);
		return sendMessage(request, "", 0, queue, timeout, null, null, isPersistent);
	}

	public Message sendMessage(Serializable request, String correlationId, String queueName, long timeout, boolean isPersistent) throws Exception
	{
		Queue queue = WSQueueFactory.getQueue(queueName);
		return sendMessage(request, correlationId, 0, queue, timeout, null, null, isPersistent);
	}

	public Message sendMessage(Serializable request, long expiration, String queueName, long timeout, boolean isPersistent) throws Exception
	{
		Queue queue = WSQueueFactory.getQueue(queueName);
		return sendMessage(request, "", expiration, queue, timeout, null, null, isPersistent);
	}

	public Message sendMessage(Serializable request, String correlationId, long expiration, String queueName, long timeout, boolean isPersistent)
			throws Exception
	{
		Queue queue = WSQueueFactory.getQueue(queueName);
		return sendMessage(request, correlationId, expiration, queue, timeout, null, null, isPersistent);
	}

	public Message sendMessage(Serializable request, String correlationId, long expiration, String queueName,
			long timeout, String[] propsName, Object[] propsValue, boolean isPersistent) throws Exception
	{
		Queue queue = WSQueueFactory.getQueue(queueName);
		return sendMessage(request, correlationId, expiration, queue, timeout, propsName, propsValue, isPersistent);
	}

	public Message sendMessage(Serializable request, Queue queue, long timeout, boolean isPersistent) throws Exception
	{
		return sendMessage(request, "", 0, queue, timeout, null, null, isPersistent);
	}

	public Message sendMessage(Serializable request, String correlationId, Queue queue, long timeout, boolean isPersistent) throws Exception
	{
		return sendMessage(request, correlationId, 0, queue, timeout, null, null, isPersistent);
	}

	public Message sendMessage(Serializable request, long expiration, Queue queue, long timeout, boolean isPersistent) throws Exception
	{
		return sendMessage(request, "", expiration, queue, timeout, null, null, isPersistent);
	}

	public Message sendMessage(Serializable request, String correlationId, long expiration, Queue queue, long timeout, boolean isPersistent)
			throws Exception
	{
		return sendMessage(request, correlationId, expiration, queue, timeout, null, null, isPersistent);
	}

	public Message sendMessage(Serializable request, String correlationId, long expiration, Queue queue,
			long timeout, String[] propsName, Object[] propsValue, boolean isPersistent) throws Exception
	{
		Message message = queueSession.createObjectMessage(request);
		if (expiration > 0)
			message.setJMSExpiration(expiration);

		if (!correlationId.equals(""))
			message.setJMSCorrelationID(correlationId);

		if (propsName != null && propsValue != null)
		{
			if (propsName.length > 0)
			{
				for (int i = 0; i < propsName.length; i++)
				{
					if (propsValue[i] instanceof String)
					{
						message.setStringProperty(propsName[i], (String) propsValue[i]);
					}
					else if (propsValue[i] instanceof Byte)
					{
						message.setByteProperty(propsName[i], ((Byte) propsValue[i]).byteValue());
					}
					else if (propsValue[i] instanceof Integer)
					{
						message.setIntProperty(propsName[i], ((Integer) propsValue[i]).intValue());
					}
					else if (propsValue[i] instanceof Boolean)
					{
						message.setBooleanProperty(propsName[i], ((Boolean) propsValue[i]).booleanValue());
					}
					else if (propsValue[i] instanceof Short)
					{
						message.setShortProperty(propsName[i], ((Short) propsValue[i]).shortValue());
					}
					else if (propsValue[i] instanceof Long)
					{
						message.setLongProperty(propsName[i], ((Long) propsValue[i]).longValue());
					}
					else if (propsValue[i] instanceof Double)
					{
						message.setDoubleProperty(propsName[i], ((Double) propsValue[i]).doubleValue());
					}
					else if (propsValue[i] instanceof Float)
					{
						message.setFloatProperty(propsName[i], ((Float) propsValue[i]).floatValue());
					}
					else
					{
						message.setObjectProperty(propsName[i], propsValue[i]);
					}
				}
			}
		}

		return sendMessage(message, queue, timeout, isPersistent);
	}

	public Message sendMessage(Message message, Queue queue, boolean isPersistent) throws Exception
	{
		try
		{
			WSQueueFactory.sendMessage(queueSession, queue, message, isPersistent);
		}
		catch (Exception e)
		{
			hasError = true;
			throw e;
		}
		return message;
	}

	public Message sendMessage(Message message, Queue queue, long timeout, boolean isPersistent) throws Exception
	{
		try
		{
			WSQueueFactory.sendMessage(queueSession, queue, message, timeout, isPersistent);
		}
		catch (Exception e)
		{
			hasError = true;
			throw e;
		}
		return message;
	}

	public Message sendMessage(Message message, String queueName, boolean isPersistent) throws Exception
	{
		try
		{
			WSQueueFactory.sendMessage(queueSession, queueName, message, isPersistent);
		}
		catch (Exception e)
		{
			hasError = true;
			throw e;
		}

		return message;
	}

	public Message sendMessage(Message message, String queueName, long timeout, boolean isPersistent) throws Exception
	{
		try
		{
			Queue queue = WSQueueFactory.getQueue(queueName);
			WSQueueFactory.sendMessage(queueSession, queue, message, timeout, isPersistent);
		}
		catch (Exception e)
		{
			hasError = true;
			throw e;
		}

		return message;
	}

	public int getQueueSize(String queueName) throws Exception
	{
		try
		{
			return WSQueueFactory.getQueueSize(queueSession, queueName);
		}
		catch (Exception e)
		{
			hasError = true;
			throw e;
		}
	}

	public int getQueueSize(Queue queue) throws Exception
	{
		try
		{
			return WSQueueFactory.getQueueSize(queueSession, queue);
		}
		catch (Exception e)
		{
			hasError = true;
			throw e;
		}
	}

	public Queue createTempQueue(String queueName) throws Exception
	{
		try
		{
			return WSQueueFactory.createQueue(queueSession, queueName);
		}
		catch (Exception e)
		{
			hasError = true;
			throw e;
		}
	}
	
	@Override
	public void onException(JMSException arg0)
	{
		debugMonitor(arg0);
		hasError = true;
	}

}
