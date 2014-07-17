package com.crm.provisioning.impl.vas;

import java.net.URL;

import com.crm.kernel.message.Constants;
import com.crm.product.cache.ProductEntry;
import com.crm.product.cache.ProductFactory;
import com.crm.provisioning.cache.ProvisioningConnection;
import com.crm.provisioning.message.CommandMessage;
import com.crm.service.elcom.vasman.CheckAllVasStatusReq;
import com.crm.service.elcom.vasman.CheckAllVasStatusResp;
import com.crm.service.elcom.vasman.ProvisioningReq;
import com.crm.service.elcom.vasman.ProvisioningResp;
import com.crm.service.elcom.vasman.WSVasmanagerLocator;
import com.crm.service.elcom.vasman.WSVasmanagerPortType;
import com.crm.util.StringUtil;
import com.fss.util.AppException;

public class VASConnection extends ProvisioningConnection
{
	// private static String portlet = "SDP-ext";
	// private static String serviceName =
	// "Portlet_Activation_ActivationStatusService";
	// private ActivationStatusServiceSoap serviceSoap = null;
	//
	// // New vasman parameters

	public VASConnection()
	{
		super();
	}

	// private URL getURL(String host, String port, String user, String
	// password)
	// throws Exception
	// {
	// // Unathenticated url
	//
	// String url = "http://" + host + "/" + portlet + "/axis/" + serviceName;
	//
	// // Authenticated url
	//
	// if (true)
	// {
	// url = "http://" + user + ":" + password
	// + "@" + host + ":" + port + "/" + portlet + "/secure/axis/" +
	// serviceName;
	// }
	//
	// return new URL(url);
	// }
	//
	// @Override
	// public boolean openConnection() throws Exception
	// {
	// ActivationStatusServiceSoapServiceLocator serviceLocator = new
	// ActivationStatusServiceSoapServiceLocator();
	// URL url = getURL(getHost(), getPort() + "", getUserName(),
	// getPassword());
	//
	// serviceSoap =
	// serviceLocator.getPortlet_Activation_ActivationStatusService(url);
	//
	// return super.openConnection();
	// }
	//
	// public CommandMessage provisioning(CommandMessage request)
	// throws Exception
	// {
	// try
	// {
	// String keyword = StringUtil.nvl(request.getKeyword(), "");
	// String[] argParams = StringUtil.toStringArray(keyword, " ");
	// if (argParams.length < 2)
	// {
	// throw new AppException(Constants.ERROR_INVALID_SYNTAX);
	// }
	// String productName = StringUtil.nvl(argParams[1], "");
	// ProductEntry entry =
	// ProductFactory.getCache().getProduct(request.getProductId());
	// String sku = entry.getParameter(productName, "");
	// if (sku.equals(""))
	// {
	// throw new AppException(Constants.ERROR_INVALID_SYNTAX);
	// }
	// String sourceAddress = StringUtil.nvl(request.getIsdn(), "");
	// int commandId = 1;
	// if (request.getActionType().equals(Constants.ACTION_REGISTER))
	// {
	// commandId = 1;
	// }
	// else if (request.getActionType().equals(Constants.ACTION_UNREGISTER))
	// {
	// commandId = 3;
	// }
	// ActivationStatusSoap res =
	// serviceSoap.provisioning(sourceAddress, sku, commandId);
	// int returnCode = res.getReturnCode();
	// String responseDetail = res.getResponseDetail();
	// request.setCause(res.getResponseCode());
	// }
	// catch (Exception e)
	// {
	// throw e;
	// }
	// return request;
	// }
	//
	// public CommandMessage checkAllStatus(CommandMessage request) throws
	// Exception
	// {
	// try
	// {
	// String isdn = request.getIsdn();
	//
	// ActivationStatusSoap[] activationStatusSoaps =
	// serviceSoap.checkAllStatus(isdn);
	//
	// if (activationStatusSoaps == null)
	// {
	//
	// }
	// else
	// {
	// // SupplierStatus
	// // 1 - active
	// // 3 - deactive
	// // 2 - suspend
	// // vas name = productId
	// // vas id = sku
	// String vasList = "";
	// for (ActivationStatusSoap activationStatusSoap : activationStatusSoaps)
	// {
	// String vasName = activationStatusSoap.getProductId();
	// vasList += vasName + StringPool.COMMA;
	// request.setResponseValue(ResponseUtil.VAS + "." + vasName + ".id",
	// activationStatusSoap.getSku());
	// request.setResponseValue(ResponseUtil.VAS + "." + vasName + ".status",
	// activationStatusSoap.getSupplierStatus());
	// request.setResponseValue(ResponseUtil.VAS + "." + vasName +
	// ".description", "");
	// }
	//
	// request.setResponseValue(ResponseUtil.VAS, vasList);
	// }
	// }
	// catch (Exception e)
	// {
	// throw e;
	// }
	//
	// return request;
	// }
	//
	// public Trigger checkStatus(CommandInstance instance, Trigger trigger)
	// throws Exception
	// {
	// String isdn = trigger.getIsdn();
	// ActivationStatusSoap activationStatusSoaps[] =
	// serviceSoap.checkAllStatus(isdn);
	// if (activationStatusSoaps == null || activationStatusSoaps.length == 0)
	// {
	// trigger.setDescription("No registration service");
	// instance.logMonitor(trigger.toLogString());
	// }
	// else
	// {
	// StringBuilder stringBuilder = new StringBuilder();
	// int j = activationStatusSoaps.length;
	//
	// for (int i = 0; i < j; i++)
	// {
	// ActivationStatusSoap activationStatusSoap = activationStatusSoaps[i];
	// String status = activationStatusSoap.getSupplierStatus();
	// String sku = activationStatusSoap.getSku();
	// String productId = activationStatusSoap.getProductId();
	// if (status.equals(String.valueOf(Constants.SUPPLIER_BARRING_STATUS)))
	// {
	// String responseCode = serviceSoap.provisioning(isdn, sku,
	// 1).getResponseCode();
	// stringBuilder.append((new
	// StringBuilder("[ProductId: ")).append(productId).append(", CurrentStatus: ")
	// .append(status).append(", ResponseCode: ").append(responseCode).append("] ").toString());
	// }
	// else
	// {
	// stringBuilder.append((new
	// StringBuilder("[ProductId: ")).append(productId).append(", CurrentStatus: ")
	// .append(status).append(", ResponseCode: ").append("] ").toString());
	// }
	// }
	// trigger.setDescription(stringBuilder.toString());
	// }
	// return trigger;
	// }

	private String					address		= "/WSVasManager/services/WSVasmanager";

	private WSVasmanagerPortType	serviceSoap	= null;

	private URL getURL(String host, String port) throws Exception
	{
		address = "http://" + host + ":" + port + address;
		return new URL(address);
	}

	@Override
	public boolean openConnection() throws Exception
	{
		WSVasmanagerLocator serviceLocator = new WSVasmanagerLocator();
		URL url = getURL(getHost(), String.valueOf(getPort()));

		serviceSoap = serviceLocator.getWSVasmanagerHttpPort(url);

		return super.openConnection();
	}

	public CommandMessage provisioning(CommandMessage request)
			throws Exception
	{
		try
		{
			String keyword = StringUtil.nvl(request.getKeyword(), "");
			String[] argParams = StringUtil.toStringArray(keyword, " ");
			if (argParams.length < 2)
			{
				throw new AppException(Constants.ERROR_INVALID_SYNTAX);
			}
			String productName = StringUtil.nvl(argParams[1], "");
			ProductEntry entry = ProductFactory.getCache().getProduct(request.getProductId());
			String sku = entry.getParameter(productName, "");
			if (sku.equals(""))
			{
				throw new AppException(Constants.ERROR_INVALID_SYNTAX);
			}
			String sourceAddress = StringUtil.nvl(request.getIsdn(), "");
			int commandId = 1;
			if (request.getActionType().equals(Constants.ACTION_REGISTER))
			{
				commandId = 1;
			}
			else if (request.getActionType().equals(Constants.ACTION_UNREGISTER))
			{
				commandId = 3;
			}

			ProvisioningReq provisioningRequest = new ProvisioningReq();
			provisioningRequest.setMdn(sourceAddress);
			provisioningRequest.setNVasID(String.valueOf(sku));
			provisioningRequest.setNCmdID(commandId);
			provisioningRequest.setSDescription("Active service from ASCS System");
			provisioningRequest.setPass(getPassword());
			provisioningRequest.setUser(getUserName());

			ProvisioningResp provisioningResponse = serviceSoap.provisioning(provisioningRequest);
			int returnCode = Integer.parseInt(provisioningResponse.getErrorCode());
			request.setCauseValue(returnCode);
			request.setCause(provisioningResponse.getErrorDetail());
		}
		catch (Exception e)
		{
			throw e;
		}
		return request;
	}

	public CommandMessage checkAllStatus(CommandMessage request) throws Exception
	{
		try
		{
			String isdn = request.getIsdn();
			
			CheckAllVasStatusReq req = new CheckAllVasStatusReq(request.getIsdn(), getPassword(), getUserName());
			
			CheckAllVasStatusResp resp = serviceSoap.checkAllVasStatus(req);
			
			

//			ActivationStatusSoap[] activationStatusSoaps =
//					serviceSoap.checkAllStatus(isdn);
//
//			if (activationStatusSoaps == null)
//			{
//
//			}
//			else
//			{
//				// SupplierStatus
//				// 1 - active
//				// 3 - deactive
//				// 2 - suspend
//				// vas name = productId
//				// vas id = sku
//				String vasList = "";
//				for (ActivationStatusSoap activationStatusSoap : activationStatusSoaps)
//				{
//					String vasName = activationStatusSoap.getProductId();
//					vasList += vasName + StringPool.COMMA;
//					request.setResponseValue(ResponseUtil.VAS + "." + vasName + ".id",
//							activationStatusSoap.getSku());
//					request.setResponseValue(ResponseUtil.VAS + "." + vasName +
//							".status",
//							activationStatusSoap.getSupplierStatus());
//					request.setResponseValue(ResponseUtil.VAS + "." + vasName +
//							".description", "");
//				}
//
//				request.setResponseValue(ResponseUtil.VAS, vasList);
//			}
		}
		catch (Exception e)
		{
			throw e;
		}

		return request;
	}

}
