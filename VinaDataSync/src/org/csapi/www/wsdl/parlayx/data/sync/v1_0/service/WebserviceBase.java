/**
 * 
 */
package org.csapi.www.wsdl.parlayx.data.sync.v1_0.service;

import java.util.Properties;

import org.apache.log4j.Logger;

import com.crm.kernel.message.Constants;
import com.crm.provisioning.message.CommandMessage;

import com.crm.util.WSConfiguration;
import com.crm.util.WSQueueFactory;




/**
 * @author hungdt
 *
 */
public class WebserviceBase
{
	private static Logger						logger		= Logger.getLogger(WebserviceBase.class);
	
	
	protected CommandMessage sendMessageToQueue(CommandMessage message, int timeout) throws Exception
	{
		if (timeout <= 0)
		{
			timeout = 60000;
		}

		return WSQueueFactory.sendMessageToQueue(message, timeout);
	}

	
	protected CommandMessage sendToQueue(CommandMessage message, int timeout) throws Exception
	{
		logger.debug(message.toLogString());
		if (timeout <= 0)
		{
			timeout = 40000;
		}

		if (message.getChannel().equals(""))
		{
			message.setChannel(Constants.CHANNEL_WEB);
		}

		CommandMessage result = sendMessageToQueue(message, timeout);

		logger.debug(result.toLogString());
		return result;
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
