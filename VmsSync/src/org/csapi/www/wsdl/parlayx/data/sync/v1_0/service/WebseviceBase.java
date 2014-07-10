package org.csapi.www.wsdl.parlayx.data.sync.v1_0.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import com.crm.provisioning.message.CommandMessage;

import com.crm.util.WSConfiguration;
import com.crm.util.WSQueueFactory;

public class WebseviceBase
{
	private static Logger						logger		= Logger.getLogger(WebseviceBase.class);

	protected ConcurrentHashMap<String, Long>	cMap		= new ConcurrentHashMap<String, Long>();
	protected ConcurrentHashMap<String, Long>	tpsMap		= new ConcurrentHashMap<String, Long>();
	protected Calendar							starttime	= Calendar.getInstance();

	

	protected CommandMessage sendMessageToQueue(CommandMessage message, int timeout)
	{
		//timeout = WSConfiguration.getConfiguration().getTimeout();
		if (timeout <= 0)
		{
			timeout = 40000;
		}

		return WSQueueFactory.sendMessageToQueue(message, timeout);
	}

	
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

}
