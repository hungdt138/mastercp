package com.crm.lottery.bean;

import java.util.Date;

public class LotterySubEntry
{
	private long	subsId				= 0;
	private String	isdn				= "";
	private String	serviceAddress		= "";
	private String	regionCode			= "";
	private String	majorRegion			= "";
	private Date	createDate			= null;
	private Date	lastChargingDate	= null;
	private Date	unregisterDate		= null;
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

	public void setServiceAddress(String serviceAddress)
	{
		this.serviceAddress = serviceAddress;
	}

	public String getServiceAddress()
	{
		return serviceAddress;
	}

	public String getRegionCode()
	{
		return regionCode;
	}

	public void setRegionCode(String regionCode)
	{
		this.regionCode = regionCode;
	}

	public void setMajorRegion(String majorRegion)
	{
		this.majorRegion = majorRegion;
	}

	public String getMajorRegion()
	{
		return majorRegion;
	}

	public void setCreateDate(Date createDate)
	{
		this.createDate = createDate;
	}

	public Date getCreateDate()
	{
		return createDate;
	}

	public Date getLastChargingDate()
	{
		return lastChargingDate;
	}

	public void setLastChargingDate(Date lastChargingDate)
	{
		this.lastChargingDate = lastChargingDate;
	}

	public Date getUnregisterDate()
	{
		return unregisterDate;
	}

	public void setUnregisterDate(Date unregisterDate)
	{
		this.unregisterDate = unregisterDate;
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
