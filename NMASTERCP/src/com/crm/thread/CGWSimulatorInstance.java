/**
 * 
 */
package com.crm.thread;

import javax.jms.Queue;

import com.crm.kernel.message.Constants;
import com.crm.provisioning.message.CommandMessage;
import com.crm.util.AppProperties;

/**
 * @author hungdt
 * 
 */
public class CGWSimulatorInstance extends SimulatorInstance
{
	// //////////////////////////////////////////////////////
	// Queue variables
	// //////////////////////////////////////////////////////
	public Queue	queueCallback	= null;

	public CGWSimulatorInstance() throws Exception
	{
		super();
	}

	public CGWSimulatorThread getDispatcher()
	{
		return (CGWSimulatorThread) dispatcher;
	}

	// //////////////////////////////////////////////////////
	// process session
	// Author : ThangPV
	// Created Date : 16/09/2004
	// //////////////////////////////////////////////////////
	public Object detachMessage() throws Exception
	{
		long currentIsdn = getDispatcher().getCurrentIsdn();

		if (currentIsdn <= 0)
		{
			return null;
		}

		CommandMessage order = new CommandMessage();

		order.setChannel(getDispatcher().channel);

		if (getDispatcher().channel.equals(Constants.CHANNEL_SMS))
		{
			order.setProvisioningType("SMSC");
		}

		order.setUserId(0);
		order.setUserName(getDispatcher().deliveryUser);

		if (order.getUserName().equals(""))
		{
			order.setUserName("system");
		}

		order.setServiceAddress(getDispatcher().serviceAddress);
		order.setIsdn(String.valueOf(currentIsdn));
		order.setShipTo(getDispatcher().shipTo);
		order.setTimeout(getDispatcher().orderTimeout * 1000);

		order.setKeyword(getDispatcher().keyword);

		order.setSubmodifyBalance(getDispatcher().balance);
		order.setSubmodifyAmount(getDispatcher().amount);
		order.setSubmodifyExpireDate(getDispatcher().expireDate);
		order.setDescription(getDispatcher().description);

		AppProperties app = new AppProperties();

		app.setString("fromReq", "SUBMODIFYTCP");
		app.setString("description", getDispatcher().description);
		app.setString("balance.charging.core.amount",getDispatcher().amount);
		app.setString("balance.charging.core.type",getDispatcher().actionType);

		order.setParameters(app);

		if (!getDispatcher().queueCallback.equals(""))
		{
			order.setCorrelationID(String.valueOf(currentIsdn));
		}
		
		return order;
	}
}
