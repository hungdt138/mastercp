/**
 * ----------------------------------------------------------------- 
 * @ Copyright(c) 2013 Vietnamobile. JSC. All Rights Reserved.
 * ----------------------------------------------------------------- 
 * Date 	Author 		Version
 * ------------------------------------- 
 * Dec 18, 2013 hungdt  v1.0
 * -------------------------------------
 */
package com.mastercp.iwebservices.impl;

import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;

import com.crm.kernel.message.Constants;
import com.crm.provisioning.message.CommandMessage;
import com.mastercp.iwebservices.ErrorCode;
import com.mastercp.iwebservices.ResponseBase;
import com.mastercp.iwebservices.ViettelReq;
import com.mastercp.iwebservices.ViettelResp;
import com.mastercp.iwebservices.ViettelServices;
import com.mastercp.iwebservices.WebserviceBase;

/**
 * @author hungdt
 *
 */
public class ViettelServicesImpl extends WebserviceBase
{
	protected static Logger	log	= Logger.getLogger(ViettelServicesImpl.class);
	
	protected ResponseBase updateResponse(ResponseBase response, CommandMessage result)
	{
		if (result != null)
		{
			int cause = getErrorCode(result.getStatus(), result.getCause());

			response.setResult(cause);
			response.setDetail(getErrorDetail(cause, result.getCause()));
		}

		return response;
	}
	
	@Override
	public ViettelResp syncOrderRelation(ViettelReq req) throws Exception
	{
		String service = "SyncOrderRelation";
		String sessionId = getSessionId(true);
		log.info("REQ: sessionId = " + sessionId + " | " + req.toString());
		
		ViettelResp resp = new ViettelResp();
		
		try
		{
			if (!authenticate(req.getUsername(), req.getPassword(), service))
			{
				resp.setResult(ErrorCode.E_INVALID_USERPASS);
				resp.setDetail(getErrorDetail(resp.getResult(), ""));

				return resp;
			}
			
			if(req.getCmdCode() == null || req.getCmdCode().equals(""))
			{
				resp.setResult(ErrorCode.E_INVALID_CONDITION);
				resp.setDetail(getErrorDetail(resp.getResult(), ""));

				return resp;
			}
			
			if(req.getShortCode() == null || req.getShortCode().equals(""))
			{
				resp.setResult(ErrorCode.E_INVALID_CONDITION);
				resp.setDetail(getErrorDetail(resp.getResult(), ""));

				return resp;
			}
			
//			if(req.getOpId() == )
//			{
//				resp.setResult(ErrorCode.E_INVALID_CONDITION);
//				resp.setDetail(getErrorDetail(resp.getResult(), ""));
//
//				return resp;
//			}
			
			String command = getCommand(service, req.getMsgContent().toUpperCase().replace(" ", ""));
			
			SimpleDateFormat df  = new SimpleDateFormat("yyyyMMddHHmmss");
			CommandMessage message = new CommandMessage();
			message.setKeyword(command);
			message.setServiceAddress(req.getShortCode());
			message.setIsdn(req.getIsdn());
			message.setOrderDate(df.parse(req.getReqDate()));
			message.setChannel(Constants.CHANNEL_WEB);
			message.setCorrelationID(sessionId);
			message.setSessionId(req.getMoId());
			
			CommandMessage result = sendOrder(message, req);
			
			log.info(result.toOrderString());
			
			updateResponse(resp, result);

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
