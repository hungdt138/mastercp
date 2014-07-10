/**
 * 
 */
package com.gateway.service;

/**
 * @author Hung
 * 
 */
public abstract interface ChargingService
{
	public abstract ServiceResponse sendSMS(SMSRequest req) throws Exception;

	public abstract ServiceResponse sendWAP(WapRequest req) throws Exception;

	public abstract ServiceResponse syncSubInfo(ServiceRequest req) throws Exception;

	public abstract OTPResponse OTPreq(OTPRequest req) throws Exception;

	public abstract SubResponse subRequest(SubRequest req) throws Exception;
}
