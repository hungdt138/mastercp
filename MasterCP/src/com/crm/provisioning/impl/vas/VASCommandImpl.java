package com.crm.provisioning.impl.vas;

import java.net.URL;

import org.apache.axis.AxisFault;

import com.crm.kernel.message.CommandConst;
import com.crm.product.cache.ProductEntry;
import com.crm.product.cache.ProductFactory;
import com.crm.provisioning.cache.ProvisioningCommand;
import com.crm.provisioning.impl.CommandImpl;
import com.crm.provisioning.message.CommandMessage;
import com.crm.provisioning.thread.CommandInstance;
import com.sdp.portlet.activation.model.ActivationStatusSoap;
import com.sdp.portlet.activation.service.http.ActivationStatusServiceSoap;
import com.sdp.portlet.activation.service.http.ActivationStatusServiceSoapServiceLocator;
//import com.sdp.portlet.activation.service.http.ActivationStatusServiceSoap;
//import com.sdp.portlet.activation.service.http.ActivationStatusServiceSoapServiceLocator;
import com.sun.corba.ee.impl.orbutil.closure.Constant;

public class VASCommandImpl extends CommandImpl
{

	/**
	 * Get provisioning from VASGATE <br>
	 * 
	 * Author: NamTA <br>
	 * Create Date: 08/05/2012
	 * 
	 * @param instance
	 * @param provisioningCommand
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public CommandMessage getProvisioning(
			CommandInstance instance, ProvisioningCommand provisioningCommand, CommandMessage request)
			throws Exception
	{
		VASConnection connection = null;
		try
		{
			connection = (VASConnection) instance.getProvisioningConnection();
			connection.provisioning(request);
		}
		catch (Exception e)
		{
			processError(instance, provisioningCommand, request, e);
		}
		finally
		{
			instance.closeProvisioningConnection(connection);
		}

		return request;
	}

	public CommandMessage getActivationStatus(CommandInstance instance, ProvisioningCommand provisioningCommand,
			CommandMessage request)
			throws Exception
	{
		VASConnection connection = null;
		try
		{
			connection = (VASConnection) instance.getProvisioningConnection();
			connection.checkAllStatus(request);
		}
		catch (Exception e)
		{
			processError(instance, provisioningCommand, request, e);
		}
		finally
		{
			instance.closeProvisioningConnection(connection);
		}

		return request;
	}

}
