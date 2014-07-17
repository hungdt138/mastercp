package com.crm.provisioning.impl.mtcharging;

import java.net.URL;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.crm.provisioning.cache.ProvisioningConnection;
import com.nms.iwebservice.SendSMSRequest;
import com.nms.iwebservice.SendSMSResponse;
import com.nms.iwebservice.SubscriptionReq;
import com.nms.iwebservice.SubscriptionResp;
import com.nms.iwebservices.Mtcharging_wsHttpBindingStub;
import com.nms.iwebservices.Mtcharging_wsLocator;

public class MTChargingConnection extends ProvisioningConnection
{
	private Mtcharging_wsHttpBindingStub binding = null;
	
	
	public MTChargingConnection()
	{
	}

	private URL getURL(String host, int port) throws Exception
	{
		String strUrl = "http://" + host + ":" + port + "/mtcharging/services/mtcharging_ws";

		URL url = new URL(strUrl);
		return url;
	}

	@Override
	public boolean openConnection() throws Exception
	{
		URL url = getURL(getHost(), getPort());

		Mtcharging_wsLocator locator = new Mtcharging_wsLocator();
		binding = new Mtcharging_wsHttpBindingStub(url, locator);
		
		return super.openConnection();
	}
	
	public String sendSMS(long requestID, String isdn, String message, String productCode, String shortCode) throws Exception
	{
		SendSMSRequest request = new SendSMSRequest();
		request.setAgentId(getParameters().getLong("AgentId"));
		request.setCpId(getParameters().getLong("CPId"));
		request.setDescription("SendMT");
		request.setIsdn(isdn);
		request.setMessage(message);
		request.setProduct(productCode);
		request.setShortCode(shortCode);
		
		String requestDate = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		request.setPassword(getNonce(getParameters().getLong("CPId")
									+ getPassword()
									+ requestDate));
		request.setRequestDate(requestDate);
		request.setRequestId(requestID);
		
		SendSMSResponse response = binding.sendSMS(request);
		
		return response.getResult();
	}
	
	public SubscriptionResp subscription(long requestID, String isdn, String productCode, String description) throws Exception
	{
		SubscriptionReq request = new SubscriptionReq();
		request.setAgentId(getParameters().getLong("AgentId"));
		request.setCpId(getParameters().getLong("CPId"));
		request.setDescription(description);
		request.setIsdn(isdn);
		request.setProduct(productCode);
		
		String requestDate = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		request.setPassword(getNonce(getParameters().getLong("CPId")
									+ getPassword()
									+ requestDate));
		request.setRequestDate(requestDate);
		request.setRequestId(requestID);
		
		return binding.subscription(request);
	}
	
	public String getNonce(String s)
	{
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
							'A', 'B', 'C', 'D', 'E', 'F' };
		try
		{
			byte[] strTemp = s.getBytes();
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.update(strTemp);
			byte[] md = messageDigest.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++)
			{
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return String.valueOf(str);
		}
		catch (Exception e)
		{
			return null;
		}
	}
}
