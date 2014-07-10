package com.gateway.service;

import com.gateway.model.OTPRequest;
import com.gateway.model.OTPResponse;
import com.gateway.model.SMSRequest;
import com.gateway.model.ServiceRequest;
import com.gateway.model.ServiceResponse;
import com.gateway.model.SubRequest;
import com.gateway.model.SubResponse;
import com.gateway.model.WapRequest;

public abstract interface ChargingService
{
	//public abstract ServiceResponse getStatus(ServiceRequest req) throws Exception;
	
	public abstract ServiceResponse sendSMS(SMSRequest req) throws Exception;
	
	public abstract ServiceResponse sendWAP(WapRequest req) throws Exception;
	
	public abstract ServiceResponse syncSubInfo(ServiceRequest req) throws Exception;
	
	public abstract OTPResponse OTPreq(OTPRequest req) throws Exception;
	
	public abstract SubResponse subRequest(SubRequest req) throws Exception;
}
