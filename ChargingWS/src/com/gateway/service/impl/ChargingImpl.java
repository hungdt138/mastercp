/**
 * 
 */
package com.gateway.service.impl;

import java.text.SimpleDateFormat;

import com.crm.provisioning.message.CommandMessage;
import com.crm.util.AppProperties;
import com.gateway.service.ErrorCode;
import com.gateway.service.OTPRequest;
import com.gateway.service.OTPResponse;
import com.gateway.service.SMSRequest;
import com.gateway.service.ServiceRequest;
import com.gateway.service.ServiceResponse;
import com.gateway.service.SubRequest;
import com.gateway.service.SubResponse;
import com.gateway.service.WapRequest;
import com.gateway.service.WebseviceBase;

/**
 * @author Hung
 * 
 */
public class ChargingImpl extends WebseviceBase
{
	SimpleDateFormat	df	= new SimpleDateFormat("yyyyMMddHHmmss");

	@Override
	public ServiceResponse sendSMS(SMSRequest req) throws Exception
	{
		String service = "sendSMS";
		String sessionId = getSessionId(true);
		log.info("REQ: sessionId = " + sessionId + " | " + req.toString());
		ServiceResponse resp = new ServiceResponse();

		try
		{
			if (!authenticate(req.getUsername(), req.getPassword(), service))
			{
				resp.setStatus(ErrorCode.SVC_ACCESS_AUTHENTICATION_ERROR);
				resp.setResult(ErrorCode.ERROR);
				resp.setIsdn(req.getIsdn());
				return resp;
			}

			if (req.getIsdn() == null || req.getIsdn().equals(""))
			{
				resp.setStatus(ErrorCode.SVC_PARAMETER_ERROR);
				resp.setResult(ErrorCode.ERROR);
				resp.setIsdn(req.getIsdn());
				return resp;
			}

			if (req.getProduct() == null || req.getProduct().equals(""))
			{
				resp.setStatus(ErrorCode.SVC_PARAMETER_ERROR);
				resp.setResult(ErrorCode.ERROR);
				resp.setIsdn(req.getIsdn());
				return resp;
			}

			if (req.getDeliveryContent() == null || req.getDeliveryContent().equals(""))
			{
				resp.setStatus(ErrorCode.SVC_PARAMETER_ERROR);
				resp.setResult(ErrorCode.ERROR);
				resp.setIsdn(req.getIsdn());
				return resp;
			}

			CommandMessage message = new CommandMessage();

			message.setChannel("web");
			message.setServiceAddress("8926");
			message.setKeyword("DELIVERY_" + req.getProduct());
			message.setIsdn(req.getIsdn());
			// message.setSubProductId(request.getrequestId());
			message.setOrderDate(df.parse(req.getReqDate()));
			message.setMerchantId(req.getCpId());
			message.setAgentId(req.getAgentId());
			message.setMsgContent(req.getDeliveryContent());

			CommandMessage result = sendOrder(message, req);

			log.info(result.toOrderString());

			resp.setStatus(ErrorCode.getErrorByCause(result.getCause()));
			resp.setResult(ErrorCode.getResultByCause(result.getCause()));
			resp.setIsdn(req.getIsdn());

		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			log.info("RESP: sessionId = " + sessionId + " | " + resp.toString());
		}

		return resp;
	}

	@Override
	public ServiceResponse sendWAP(WapRequest req) throws Exception
	{
		String service = "sendWAP";
		String sessionId = getSessionId(true);
		log.info("REQ: sessionId = " + sessionId + " | " + req.toString());
		ServiceResponse resp = new ServiceResponse();

		try
		{
			if (!authenticate(req.getUsername(), req.getPassword(), service))
			{
				resp.setStatus(ErrorCode.SVC_ACCESS_AUTHENTICATION_ERROR);
				resp.setResult(ErrorCode.ERROR);
				resp.setIsdn(req.getIsdn());
				return resp;
			}

			if (req.getIsdn() == null || req.getIsdn().equals(""))
			{
				resp.setStatus(ErrorCode.SVC_PARAMETER_ERROR);
				resp.setResult(ErrorCode.ERROR);
				resp.setIsdn(req.getIsdn());
				return resp;
			}

			if (req.getProduct() == null || req.getProduct().equals(""))
			{
				resp.setStatus(ErrorCode.SVC_PARAMETER_ERROR);
				resp.setResult(ErrorCode.ERROR);
				resp.setIsdn(req.getIsdn());
				return resp;
			}

			CommandMessage message = new CommandMessage();

			message.setChannel("web");
			message.setServiceAddress("8926");
			message.setKeyword("DELIVERY_" + req.getProduct());
			message.setIsdn(req.getIsdn());
			message.setOrderDate(df.parse(req.getReqDate()));
			message.setMerchantId(req.getCpId());
			message.setAgentId(req.getAgentId());
			message.setSubject(req.getSubject());
			message.setUrl(req.getUrl());

			CommandMessage result = sendOrder(message, req);

			log.info(result.toOrderString());

			resp.setStatus(ErrorCode.getErrorByCause(result.getCause()));
			resp.setResult(ErrorCode.getResultByCause(result.getCause()));
			resp.setIsdn(req.getIsdn());

		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			log.info("RESP: sessionId = " + sessionId + " | " + resp.toString());
		}

		return resp;
	}

	@Override
	public ServiceResponse syncSubInfo(ServiceRequest req) throws Exception
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OTPResponse OTPreq(OTPRequest req) throws Exception
	{
		String service = "OTPreq";
		String sessionId = getSessionId(true);
		log.info("REQ: sessionId = " + sessionId + " | " + req.toString());
		OTPResponse resp = new OTPResponse();

		try
		{
			if (!authenticate(req.getUsername(), req.getPassword(), service))
			{
				resp.setStatus(ErrorCode.SVC_ACCESS_AUTHENTICATION_ERROR);
				resp.setResult(ErrorCode.ERROR);
				resp.setIsdn(req.getIsdn());
				return resp;
			}

			if (req.getIsdn() == null || req.getIsdn().equals(""))
			{
				resp.setStatus(ErrorCode.SVC_PARAMETER_ERROR);
				resp.setResult(ErrorCode.ERROR);
				resp.setIsdn(req.getIsdn());
				return resp;
			}

			if (req.getProduct() == null || req.getProduct().equals(""))
			{
				resp.setStatus(ErrorCode.SVC_PARAMETER_ERROR);
				resp.setResult(ErrorCode.ERROR);
				resp.setIsdn(req.getIsdn());
				return resp;
			}

			AppProperties app = new AppProperties();
			app.setString("request.action", req.getAction());
			CommandMessage message = new CommandMessage();

			message.setChannel("web");
			message.setServiceAddress("8926");
			if (req.getAction().equals("sub"))
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

			CommandMessage result = sendOrder(message, req);

			log.info(result.toOrderString());

			resp.setStatus(ErrorCode.getErrorByCause(result.getCause()));
			resp.setResult(ErrorCode.getResultByCause(result.getCause()));
			resp.setIsdn(req.getIsdn());

		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			log.info("RESP: sessionId = " + sessionId + " | " + resp.toString());
		}

		return resp;
	}

	@Override
	public SubResponse subRequest(SubRequest req) throws Exception
	{
		String service = "subRequest";
		String sessionId = getSessionId(true);
		log.info("REQ: sessionId = " + sessionId + " | " + req.toString());
		SubResponse resp = new SubResponse();

		try
		{
			if (!authenticate(req.getUsername(), req.getPassword(), service))
			{
				resp.setStatus(ErrorCode.SVC_ACCESS_AUTHENTICATION_ERROR);
				resp.setResult(ErrorCode.ERROR);
				resp.setIsdn(req.getIsdn());
				return resp;
			}

			if (req.getIsdn() == null || req.getIsdn().equals(""))
			{
				resp.setStatus(ErrorCode.SVC_PARAMETER_ERROR);
				resp.setResult(ErrorCode.ERROR);
				resp.setIsdn(req.getIsdn());
				return resp;
			}

			if (req.getProduct() == null || req.getProduct().equals(""))
			{
				resp.setStatus(ErrorCode.SVC_PARAMETER_ERROR);
				resp.setResult(ErrorCode.ERROR);
				resp.setIsdn(req.getIsdn());
				return resp;
			}

			String orderType = getAction(req.getIsdn(), req.getReqId());

			AppProperties app = new AppProperties();
			app.setString("request.otp", req.getOtp());
			CommandMessage message = new CommandMessage();

			message.setChannel("web");
			message.setServiceAddress("8926");
			if (orderType.equals("vms-otpreq"))
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

			CommandMessage result = sendOrder(message, req);

			log.info(result.toOrderString());

			resp.setStatus(ErrorCode.getErrorByCause(result.getCause()));
			resp.setResult(ErrorCode.getResultByCause(result.getCause()));
			resp.setIsdn(req.getIsdn());

		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			log.info("RESP: sessionId = " + sessionId + " | " + resp.toString());
		}

		return resp;
	}

}
