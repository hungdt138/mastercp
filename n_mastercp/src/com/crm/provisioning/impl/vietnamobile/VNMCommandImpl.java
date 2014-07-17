/**
 * ----------------------------------------------------------------- 
 * @ Copyright(c) 2013 Vietnamobile. JSC. All Rights Reserved.
 * ----------------------------------------------------------------- 
 * Date 	Author 		Version
 * ------------------------------------- 
 * Oct 25, 2013 HungDT  v1.0
 * -------------------------------------
 */
package com.crm.provisioning.impl.vietnamobile;

import com.crm.product.cache.ProductEntry;
import com.crm.product.cache.ProductFactory;

import com.crm.provisioning.cache.ProvisioningCommand;

import com.crm.provisioning.impl.CommandImpl;

import com.crm.provisioning.message.CommandMessage;
import com.crm.provisioning.thread.CommandInstance;

import com.fss.util.AppException;

/**
 * @author HungDT
 *
 */
public class VNMCommandImpl extends CommandImpl
{
	public CommandMessage sendSms(CommandInstance instance,
			ProvisioningCommand provisioningCommand, CommandMessage request)
			throws Exception
	{
		VNMConnection connection = null;
		CommandMessage result = request;
		try
		{
			ProductEntry product = ProductFactory.getCache().getProduct(result.getProductId());

			result.setVnmproduct(product.getParameters().getString("vnm.productcode"));

			String descKey = "sdp.description." + result.getActionType();

			String description = product.getParameter(descKey, "");

			connection = (VNMConnection) instance.getProvisioningConnection();

			String requestStr = "com.crm.provisioning.impl.vietnamobile.sendSms{spid= " + product.getParameters().getString("vnm.cpid") + ",sid= "
					+ product.getParameters().getString("vnm.productcode") +
					",isdn= " + result.getIsdn() + ",description= " + description + ",content= " + result.getContent() + "}";

			long sessionId = setRequest(instance, result, requestStr);

			VNMResponse resp = connection.sendSMS(instance, result);

			setResponse(instance, request, "orderId=" + resp.getOrderId() + ", result=" + result + ", desc=" + resp.getDesc(), sessionId);

			if (!resp.getResult().equals("SVC0000"))
			{
				throw new AppException("error");
			}

		}
		catch (Exception e)
		{
			processError(instance, provisioningCommand, result, e);
		}

		finally
		{
			instance.closeProvisioningConnection(connection);
		}

		return result;
	}

	public CommandMessage subscription(CommandInstance instance,
			ProvisioningCommand provisioningCommand, CommandMessage request)
			throws Exception
	{
		VNMConnection connection = null;
		CommandMessage result = request;
		try
		{
			ProductEntry product = ProductFactory.getCache().getProduct(result.getProductId());

			result.setVnmproduct(product.getParameters().getString("vnm.productcode"));

			String descKey = "sdp.description." + result.getActionType();

			String description = product.getParameter(descKey, "");

			connection = (VNMConnection) instance.getProvisioningConnection();

			String requestStr = "com.crm.provisioning.impl.vietnamobile.subscription{spid= " + product.getParameters().getString("vnm.cpid")
					+ ",sid= "
					+ product.getParameters().getString("vnm.productcode") +
					",isdn= " + result.getIsdn() + ",description= " + description + "}";

			long sessionId = setRequest(instance, result, requestStr);

			VNMResponse resp = connection.subscription(instance, result);

			setResponse(instance, request, "orderId=" + resp.getOrderId() + ", result=" + result + ", desc=" + resp.getDesc(), sessionId);

			if (!resp.getResult().equals("SVC0000"))
			{
				throw new AppException("error");
			}

		}
		catch (Exception e)
		{
			processError(instance, provisioningCommand, result, e);
		}

		finally
		{
			instance.closeProvisioningConnection(connection);
		}

		return result;
	}

}
