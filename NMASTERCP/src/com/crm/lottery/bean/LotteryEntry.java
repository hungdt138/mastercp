package com.crm.lottery.bean;

import java.util.Date;

public class LotteryEntry
{
	private String regionCode = "";
	private Date lotteryDate = null;
	private String prize = "";
	private boolean isLast = false;
	public String getRegionCode()
	{
		return regionCode;
	}
	public void setRegionCode(String regionCode)
	{
		this.regionCode = regionCode;
	}
	public Date getLotteryDate()
	{
		return lotteryDate;
	}
	public void setLotteryDate(Date lotteryDate)
	{
		this.lotteryDate = lotteryDate;
	}
	public String getPrize()
	{
		return prize;
	}
	public void setPrize(String prize)
	{
		this.prize = prize;
	}
	public boolean isLast()
	{
		return isLast;
	}
	public void setLast(boolean isLast)
	{
		this.isLast = isLast;
	}
}
