/**
 *-----------------------------------------------------------------------------
 * @ Copyright(c) 2012  Vietnamobile. JSC. All Rights Reserved.
 *-----------------------------------------------------------------------------
 * FILE  NAME             : Merchantcheck.java
 * DESCRIPTION            :
 * PRINCIPAL AUTHOR       : hungdt
 * SYSTEM NAME            : PGW
 * MODULE NAME            : Webservice MerchantAccount
 * LANGUAGE               : Java
 * DATE OF FIRST RELEASE  : 
 *-----------------------------------------------------------------------------
 * @ Date Aug 21, 2012
 * @ Release 1.0.0.0
 * @ Version 1.0
 * -----------------------------------------------------------------------------------
 * Date	            Author	      Version        Description
 * -----------------------------------------------------------------------------------
 * Aug 21, 2012       hungdt        1.0 	       Initial Create
 * -----------------------------------------------------------------------------------
 */
package com.gateway.service.impl;

import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;

import com.crm.provisioning.message.CommandMessage;
import com.crm.util.AppProperties;
import com.gateway.model.DeliveryRequest;
import com.gateway.model.DeliveryResponse;
import com.gateway.model.OTPRequest;
import com.gateway.model.OTPResponse;
import com.gateway.model.SMSRequest;
import com.gateway.model.ServiceRequest;
import com.gateway.model.ServiceResponse;
import com.gateway.model.ServiceStatus;
import com.gateway.model.SubRequest;
import com.gateway.model.SubResponse;
import com.gateway.model.WapRequest;
import com.gateway.security.AgentEntry;
import com.gateway.security.Authentication;
import com.gateway.service.ChargingService;
import com.gateway.service.WebseviceBase;
import com.gateway.util.WSResponseUtil;
import com.gateway.util.WSExeption;

/**
 * @author hungdt
 * 
 */
public class ChargingImpl extends WebseviceBase implements ChargingService
{
	SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
	

	@Override
	public DeliveryResponse sendSMS(SMSRequest request) throws Exception
	{
		DeliveryResponse response = new DeliveryResponse();
		
		try
		{
			Authentication.authenticate(request, response, false);
			
			if (response.getResult() != WSExeption.SUCCESS)
			{ 
				return response;
			}
			
			AgentEntry agent = Authentication.getAgent(request);
			
			
			CommandMessage message = new CommandMessage();
			
			message.setChannel("web");
			message.setServiceAddress(agent.getServiceAddress());
			message.setKeyword("DELIVERY_" + request.getProduct());
			message.setIsdn(request.getIsdn());
			//message.setSubProductId(request.getrequestId());
			message.setOrderDate(df.parse(request.getReqDate()));
			message.setMerchantId(request.getCpId());
			message.setAgentId(request.getAgentId());
			message.setMsgContent(request.getDeliveryContent());
			
			processRequest(request, message, response, logger.isDebugEnabled());
		}
		catch (Exception e)
		{
			logger.error(e, e);
			WSResponseUtil.updateResponse(response, "error");
		}

		return response;
	}
	
	
	@Override
	public ServiceResponse syncSubInfo(ServiceRequest req) throws Exception
	{
		return null;
	}

	@Override
	public OTPResponse OTPreq(OTPRequest req) throws Exception
	{
		OTPResponse resp = new OTPResponse();
		try
		{
			Authentication.authenticate(req, resp, false);
			
			if (resp.getResult() != WSExeption.SUCCESS)
			{ 
				return resp;
			}
			
			AgentEntry agent = Authentication.getAgent(req);
			
			AppProperties app = new AppProperties();
			app.setString("request.action", req.getAction());
			CommandMessage message = new CommandMessage();
			
			message.setChannel("web");
			message.setServiceAddress(agent.getServiceAddress());
			if(req.getAction().equals("sub"))
			{
				message.setKeyword("OTPREQ_" + req.getProduct());
			}
			else
			{
				message.setKeyword("UNREQOTP_" + req.getProduct());
			}
			
			message.setIsdn(req.getIsdn());
			message.setRequestId(req.getReqId());
			message.setOrderDate(df.parse(req.getReqDate()));
			message.setMerchantId(req.getCpId());
			message.setAgentId(req.getAgentId());
			
			message.setParameters(app);
			
			processRequest(req, message, resp, logger.isDebugEnabled());
		}
		catch (Exception e)
		{
			logger.error(e, e);
			WSResponseUtil.updateResponse(resp, "error");
		}
		
		return resp;
	}

	@Override
	public SubResponse subRequest(SubRequest req) throws Exception
	{
		SubResponse resp = new SubResponse();
		try
		{
			Authentication.authenticate(req, resp, false);
			
			if (resp.getResult() != WSExeption.SUCCESS)
			{ 
				return resp;
			}
			
			String orderType = Authentication.getAction(req.getIsdn(), req.getReqId());
			
			AgentEntry agent = Authentication.getAgent(req);
			
			AppProperties app = new AppProperties();
			app.setString("request.otp", req.getOtp());
			CommandMessage message = new CommandMessage();
			
			message.setChannel("web");
			message.setServiceAddress(agent.getServiceAddress());
			if(orderType.equals("vms-otpreq"))
			{
				message.setKeyword("SUBREQ_" + req.getProduct());
			}
			else
			{
				message.setKeyword("UNREQSUB_" + req.getProduct());
			}
			
			message.setIsdn(req.getIsdn());
			message.setRequestId(req.getReqId());
			message.setOrderDate(df.parse(req.getReqDate()));
			message.setMerchantId(req.getCpId());
			message.setAgentId(req.getAgentId());
			
			message.setParameters(app);
			
			processRequest(req, message, resp, logger.isDebugEnabled());
		}
		catch (Exception e)
		{
			logger.error(e, e);
			WSResponseUtil.updateResponse(resp, "error");
		}
		
		return resp;
	}

	
	@Override
	public DeliveryResponse sendWAP(WapRequest req) throws Exception
	{
		DeliveryResponse response = new DeliveryResponse();
		
		try
		{
			Authentication.authenticate(req, response, false);
			
			if (response.getResult() != WSExeption.SUCCESS)
			{ 
				return response;
			}
			
			AgentEntry agent = Authentication.getAgent(req);
			
			CommandMessage message = new CommandMessage();
			
			message.setChannel("web");
			message.setServiceAddress(agent.getServiceAddress());
			message.setKeyword("DELIVERY_" + req.getProduct());
			message.setIsdn(req.getIsdn());
			message.setOrderDate(df.parse(req.getReqDate()));
			message.setMerchantId(req.getCpId());
			message.setAgentId(req.getAgentId());
			message.setSubject(req.getSubject());
			message.setUrl(req.getUrl());
			
			
			processRequest(req, message, response, logger.isDebugEnabled());
		}
		catch (Exception e)
		{
			logger.error(e, e);
			WSResponseUtil.updateResponse(response, "error");
		}

		return response;
	}
	
//	public static void main(String[] args) throws Exception
//	{
//		OTPRequest req = new OTPRequest();
//		req.setAction("sub");
//		req.setAgentId(54001);
//		req.setCpId(3001);
//		req.setIsdn("84906130890");
//		req.setPassword("sammedia123");
//		req.setProduct("MSPSAM800004");
//		req.setReqDate("20131007172800");
//		req.setReqId(123455678);
//		req.setUsername("sam_media");
//		
//		ChargingImpl charge = new ChargingImpl();
//		OTPResponse resp = charge.OTPreq(req);
//		
//		System.out.println(resp.getIsdn());
//		System.out.println(resp.getResult());
//		System.out.println(resp.getStatus());
//	}
	
	private static Logger	logger	= Logger.getLogger(WebseviceBase.class);
}
