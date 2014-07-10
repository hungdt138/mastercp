package com.gateway.model;

import java.text.ParseException;
import java.util.Date;

import com.crm.util.StringUtil;

public class ServiceResponse
{
	
	private String	isdn;
	private int		result;
	private String	status;
	
	

	public String toOrderString() throws ParseException
	{
		StringBuffer result = new StringBuffer();
		result.append(" | isdn = ");
		result.append(getIsdn());
		result.append(" | result = ");
		result.append(getResult());
		result.append(" | status = ");
		result.append(getStatus());
		return result.toString();
	}
	
	public ServiceResponse()
	{
	}

	

	public ServiceResponse(String msisdn, int result, String cause, String description)
	{
		super();

		setIsdn(isdn);
		setResult(result);
		setStatus(status);
	}

	public String toString()
	{
		return StringUtil.toDebugString(this);
	}

	public String getIsdn()
	{
		return isdn;
	}

	public void setIsdn(String isdn)
	{
		this.isdn = isdn;
	}

	public int getResult()
	{
		return result;
	}

	public void setResult(int result)
	{
		this.result = result;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	
	
}
