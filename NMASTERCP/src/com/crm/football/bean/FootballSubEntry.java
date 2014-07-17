package com.crm.football.bean;

import java.util.Date;

public class FootballSubEntry
{
	private long	subsId				= 0;
	private String	isdn				= "";
	private String	serviceAddress		= "";
	private long	keyId				= 0;
	private long	productId			= 0;
	private Date	createDate			= null;
	private Date	lastChargingDate	= null;
	private boolean	active				= true;
	
	public long getSubsId()
	{
		return subsId;
	}
	public void setSubsId(long subsId)
	{
		this.subsId = subsId;
	}
	public String getIsdn()
	{
		return isdn;
	}
	public void setIsdn(String isdn)
	{
		this.isdn = isdn;
	}
	public String getServiceAddress()
	{
		return serviceAddress;
	}
	public void setServiceAddress(String serviceAddress)
	{
		this.serviceAddress = serviceAddress;
	}
	public long getKeyId()
	{
		return keyId;
	}
	public void setKeyId(long keyId)
	{
		this.keyId = keyId;
	}
	public long getProductId()
	{
		return productId;
	}
	public void setProductId(long productId)
	{
		this.productId = productId;
	}
	public Date getCreateDate()
	{
		return createDate;
	}
	public void setCreateDate(Date createDate)
	{
		this.createDate = createDate;
	}
	public Date getLastChargingDate()
	{
		return lastChargingDate;
	}
	public void setLastChargingDate(Date lastChargingDate)
	{
		this.lastChargingDate = lastChargingDate;
	}
	public boolean isActive()
	{
		return active;
	}
	public void setActive(boolean active)
	{
		this.active = active;
	}
	
	
}
