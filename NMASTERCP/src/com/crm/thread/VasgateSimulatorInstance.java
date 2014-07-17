package com.crm.thread;

import com.crm.provisioning.message.CommandMessage;

public class VasgateSimulatorInstance extends SimulatorInstance
{

	public VasgateSimulatorInstance() throws Exception 
	{
		super();
	}
	//Overide detach
	public Object detachMessage() throws Exception
	{
		CommandMessage order = new CommandMessage();
		order.setKeyword(getDispatcher().keyword);
		order.setIsdn(getDispatcher().isdn);
		order.setServiceAddress(getDispatcher().serviceAddress);
		order.setChannel(getDispatcher().channel);
		return order;
	}
}
