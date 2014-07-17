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

import java.text.SimpleDateFormat;

import vms.org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.NonceGenerator;
import vnm.Mtcharging_wsStub;
import vnm.Mtcharging_wsStub.SendSMS;
import vnm.Mtcharging_wsStub.SendSMSRequest;
import vnm.Mtcharging_wsStub.SendSMSResponse;
import vnm.Mtcharging_wsStub.SendSMSResponseE;
import vnm.Mtcharging_wsStub.SubscriptionReq;
import vnm.Mtcharging_wsStub.SubscriptionResp;

import com.crm.provisioning.cache.ProvisioningConnection;
import com.crm.provisioning.message.CommandMessage;
import com.crm.thread.DispatcherInstance;
import com.crm.util.AppProperties;

/**
 * @author HungDT
 * 
 */
public class VNMConnection extends ProvisioningConnection
{
	public int	agentId		= 54020;
	public int	merchantId	= 3022;

	public VNMConnection() {
		super();
	}

	public boolean openConnection() throws Exception
	{
		return super.openConnection();
	}

	public void setParameters(AppProperties parameters) throws Exception
	{
		super.setParameters(parameters);
		agentId = getParameters().getInt("agentId");
		merchantId = getParameters().getInt("merchantId");
	}

	/**
	 * 19/03.2014, Update new version sendSMS Vietnamobile
	 * @throws Exception 
	 */
	
	public VNMResponse sendSMS(DispatcherInstance instance,
			CommandMessage message) throws Exception
	{
		VNMResponse resp = new VNMResponse();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		
		SendSMSRequest req = null;
		try
		{
			String password = NonceGenerator.getInstance()
					.getNonce(merchantId + getPassword() + df.format(message.getOrderDate()));
			
			req = new SendSMSRequest();
			req.setAgentId(agentId);
			req.setCpId(merchantId);
			req.setDescription(message.getDescription());
			req.setIsdn(message.getIsdn());
			req.setMessage(message.getContent());
			req.setPassword(password);
			req.setProduct(message.getVnmproduct());
			req.setRequestDate(df.format(message.getOrderDate()));
			req.setRequestId(message.getOrderId());
			req.setShortCode("8926");
			
			Mtcharging_wsStub stub = new Mtcharging_wsStub(getHost());
			
			SendSMS send = new SendSMS();
			send.setIn0(req);
			
			SendSMSResponseE respE = stub.sendSMS(send);
			SendSMSResponse resp1 = respE.getOut();
			
			resp.setResult(resp1.getResult());
			resp.setDesc(resp1.getResultDescription());
			
		}
		catch (Exception e)
		{
			throw e;
		}
		
		
		return resp;
	}
	
	/**
	 * 19/03/2014, Update new version Subscription Vietnamobile
	 */
	
	public VNMResponse subscription(DispatcherInstance instance,
			CommandMessage message) throws Exception
	{
		VNMResponse response = new VNMResponse();
		
		
		SubscriptionReq req = null;
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		try
		{
			String password = NonceGenerator.getInstance()
					.getNonce(merchantId + getPassword() + df.format(message.getOrderDate()));
			
			req = new SubscriptionReq();
			req.setAgentId(agentId);
			req.setCpId(merchantId);
			req.setIsdn(message.getIsdn());
			req.setDescription(message.getDescription());
			req.setPassword(password);
			req.setProduct(message.getVnmproduct());
			req.setRequestDate(df.format(message.getOrderDate()));
			req.setRequestId(message.getOrderId());
			
			Mtcharging_wsStub stub = new Mtcharging_wsStub(getHost());
			
			vnm.Mtcharging_wsStub.Subscription sub = new vnm.Mtcharging_wsStub.Subscription();
			sub.setIn0(req);
			
			
			SubscriptionResp r = stub.subscription(sub).getOut();
			
			response.setAmount(r.getAmount());
			response.setDesc(r.getResultDescription());
			response.setResult(r.getResult());
			
		}
		catch (Exception e)
		{
			throw e;
		}

		return response;
	}
}