package com.crm.kernel.report;

import java.util.Date;


public class GoReportData
{
	private Date reportdate = new Date();
	private long totalSubsActive = 0;
	private long totalSubsRegister = 0;
	private long totalSubsUnregister = 0;
	private long totalSubsRegisterPerDay = 0;
	private long totalSubsUnregisterPerDay = 0;
	private long totalSubsSubscriptionPerDay = 0;

	private long totalRevenueSubscription = 0;
	private long totalRevenueBuyQuestion = 0;
	private long totalRevenueRegister = 0;
	private long totalRevenue = 0;
	public Date getReportdate()
	{
		return reportdate;
	}
	public void setReportdate(Date reportdate)
	{
		this.reportdate = reportdate;
	}
	public long getTotalSubsActive()
	{
		return totalSubsActive;
	}
	public void setTotalSubsActive(long totalSubsActive)
	{
		this.totalSubsActive = totalSubsActive;
	}
	public long getTotalSubsRegister()
	{
		return totalSubsRegister;
	}
	public void setTotalSubsRegister(long totalSubsRegister)
	{
		this.totalSubsRegister = totalSubsRegister;
	}
	public long getTotalSubsUnregister()
	{
		return totalSubsUnregister;
	}
	public void setTotalSubsUnregister(long totalSubsUnregister)
	{
		this.totalSubsUnregister = totalSubsUnregister;
	}
	public long getTotalSubsRegisterPerDay()
	{
		return totalSubsRegisterPerDay;
	}
	public void setTotalSubsRegisterPerDay(long totalSubsRegisterPerDay)
	{
		this.totalSubsRegisterPerDay = totalSubsRegisterPerDay;
	}
	public long getTotalSubsUnregisterPerDay()
	{
		return totalSubsUnregisterPerDay;
	}
	public void setTotalSubsUnregisterPerDay(long totalSubsUnregisterPerDay)
	{
		this.totalSubsUnregisterPerDay = totalSubsUnregisterPerDay;
	}
	public long getTotalRevenueSubscription()
	{
		return totalRevenueSubscription;
	}
	public void setTotalRevenueSubscription(long totalRevenueSubscription)
	{
		this.totalRevenueSubscription = totalRevenueSubscription;
	}
	public long getTotalRevenueBuyQuestion()
	{
		return totalRevenueBuyQuestion;
	}
	public void setTotalRevenueBuyQuestion(long totalRevenueBuyQuestion)
	{
		this.totalRevenueBuyQuestion = totalRevenueBuyQuestion;
	}
	public long getTotalRevenue()
	{
		return totalRevenue;
	}
	public void setTotalRevenue(long totalRevenue)
	{
		this.totalRevenue = totalRevenue;
	}
	public long getTotalRevenueRegister()
	{
		return totalRevenueRegister;
	}
	public void setTotalRevenueRegister(long totalRevenueRegister)
	{
		this.totalRevenueRegister = totalRevenueRegister;
	}
}
