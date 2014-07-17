package com.crm.provisioning.impl.epos;

import java.sql.Connection;

import org.apache.log4j.Logger;

import com.crm.kernel.message.Constants;
import com.crm.provisioning.cache.ProvisioningCommand;
import com.crm.provisioning.impl.CommandImpl;
import com.crm.provisioning.message.CommandMessage;
import com.crm.provisioning.message.VNMMessage;
import com.crm.provisioning.thread.CommandInstance;
import com.crm.provisioning.util.CommandUtil;

public class EposCommandImpl extends CommandImpl {

	Logger logger = Logger.getLogger(EposCommandImpl.class);
	
	public VNMMessage changeCOS(CommandInstance instance, ProvisioningCommand provisioningCommand, CommandMessage request) throws Exception
	{
		VNMMessage result = CommandUtil.createVNMMessage(request);
		EposConnection connection = null;
		
		if (instance.getDebugMode().equals("depend"))
		{
			simulation(instance, provisioningCommand, request);
		}
		else
		{
			try
			{
				String strPackageType = provisioningCommand.getParameter("PackageType", "");
				String isdn = request.getIsdn();
				String strOption = provisioningCommand.getParameter("Option", "");
				connection = (EposConnection) instance.getProvisioningConnection();
				
				String response = "";
				if (request.isPostpaid())
				{
					response = connection.changeCOS(isdn, strOption, strPackageType, true);
				}
				else
				{
					response = connection.changeCOS(isdn, strOption, strPackageType, false);
				}
				if (response.equals(Constants.SUCCESS) || 
					response.equals(Constants.EPOS_COS_CHANGED) || 
					response.equals(Constants.EPOS_COS_CANCELED))
				{
					result.setCause(Constants.SUCCESS);
				}
				else
				{
					result.setCause(Constants.ERROR);
				}
			}
			catch(Exception ex)
			{
				logger.error(ex.getMessage());
			}
			finally
			{
				connection.closeConnection();
			}
		}
		
		return result;
	}
}
