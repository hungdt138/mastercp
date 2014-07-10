package com.gateway.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.crm.util.StringUtil;

public class ServiceRequest
{
	private String	username;
	private String	password;

	private long	cpId;
	private long	agentId;

	private long	reqId;
	private String	reqDate;

	private String	isdn;
	private String	product;

	public String toString()
	{
		return StringUtil.toDebugString(this);
	}

	public String toOrderString() throws ParseException
	{
		StringBuffer result = new StringBuffer();

		result.append("requestId = ");
		result.append(getReqId());
		result.append(" | requestDate = ");
		result.append((new SimpleDateFormat("yyyyMMddHHmmss")).parse(getReqDate()));
		result.append(" | userMame = ");
		result.append(getUsername());
		result.append(" | password = ");
		result.append(getPassword());
		result.append(" | cpId = ");
		result.append(getCpId());
		result.append(" | agentId = ");
		result.append(getAgentId());
		result.append(" | isdn = ");
		result.append(getIsdn());
		result.append(" | product = ");
		result.append(getProduct());
		return result.toString();
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	

	public long getCpId()
	{
		return cpId;
	}

	public void setCpId(long cpId)
	{
		this.cpId = cpId;
	}

	public long getAgentId()
	{
		return agentId;
	}

	public void setAgentId(long agentId)
	{
		this.agentId = agentId;
	}

	

	public long getReqId()
	{
		return reqId;
	}

	public void setReqId(long reqId)
	{
		this.reqId = reqId;
	}

	public String getReqDate()
	{
		return reqDate;
	}

	public void setReqDate(String reqDate)
	{
		this.reqDate = reqDate;
	}

	public String getIsdn()
	{
		return isdn;
	}

	public void setIsdn(String isdn)
	{
		this.isdn = isdn;
	}
	
	public String getProduct()
	{
		return product;
	}

	public void setProduct(String product)
	{
		this.product = product;
	}
}
