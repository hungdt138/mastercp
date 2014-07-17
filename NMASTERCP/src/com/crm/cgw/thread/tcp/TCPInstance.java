package com.crm.cgw.thread.tcp;

import javax.jms.Queue;

import com.crm.cgw.submodifytcp.ChargingResp;
import com.crm.kernel.message.Constants;
import com.crm.kernel.queue.QueueFactory;
import com.crm.provisioning.message.CommandMessage;
import com.crm.thread.DispatcherInstance;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright:
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author: HungPQ
 * @version 1.0
 */

public class TCPInstance extends DispatcherInstance
{
	// //////////////////////////////////////////////////////
	// Queue variables
	// //////////////////////////////////////////////////////
	public Queue	queueCallback	= null;

	public TCPInstance() throws Exception
	{
		super();
	}

	public TCPThread getDispatcher()
	{
		return (TCPThread) dispatcher;
	}

	// //////////////////////////////////////////////////////
	// process session
	// Author : ThangPV
	// Created Date : 16/09/2004
	// //////////////////////////////////////////////////////
	public Object detachMessage() throws Exception
	{
		Object message = QueueFactory.detachLocal(QueueFactory.TCP_RESPONSE_QUEUE);
		// Object message =
		// QueueFactory.detachLocal(QueueFactory.ORDER_RESPONSE_QUEUE);

		return message;
	}

	// //////////////////////////////////////////////////////
	// process session
	// Author : ThangPV
	// Created Date : 16/09/2004
	// //////////////////////////////////////////////////////
	public int processMessage(CommandMessage message) throws Exception
	{
		if (message == null)
		{
			return Constants.BIND_ACTION_NONE;
		}

		String correlationId = message.getCorrelationID();

		if (correlationId.equals(""))
		{
			return Constants.BIND_ACTION_NONE;
		}

		long sessionId = 0;

		try
		{
			sessionId = message.getParameters().getLong("sessionId", 0);

			ChargingResp response = new ChargingResp();

			response.setM_iSequence((int) message.getRequestId());
			response.setM_sMDN(message.getIsdn());
			response.setM_sErrorCode(message.getCause());
			response.setM_sDetail(message.getDescription());

			TCPHandler handler = getDispatcher().handlers.get(sessionId);

			if (handler != null)
			{
				if (handler.isConnected())
				{
					handler.response(response.getData());

					if (message.getStatus() == Constants.ORDER_STATUS_DENIED)
					{
						logMonitor(message.toString());
					}
					else
					{
						debugMonitor("Response client: #" + sessionId + response.getContent());
					}
				}
				else
				{
					logMonitor("Connection #" + sessionId + " is disconnected: " + message.toString());
				}
			}
		}
		catch (Exception e)
		{
			debugMonitor("Response client: " + e.toString());
		}

		if (dispatcher.displayDebug && (message.getOrderDate() != null))
		{
			StringBuilder sbLog = new StringBuilder();

			sbLog.append(message.getIsdn());
			sbLog.append(",");
			sbLog.append(message.getOrderDate());
			sbLog.append(",");
			sbLog.append(message.getResponseTime());
			sbLog.append(",");
			sbLog.append(System.currentTimeMillis() - message.getOrderDate().getTime());

			debugMonitor(sbLog.toString());
		}

		return Constants.BIND_ACTION_SUCCESS;
	}
}
