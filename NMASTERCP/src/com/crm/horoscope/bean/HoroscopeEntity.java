package com.crm.horoscope.bean;

import java.util.Date;

public class HoroscopeEntity
{
	private Date	createDate	= new Date();
	private String	horoscope	= "";
	private String	startDate	= "";
	private String	endDate		= "";
	private String	detail		= "";

	public Date getCreateDate()
	{
		return createDate;
	}

	public void setCreateDate(Date createDate)
	{
		this.createDate = createDate;
	}

	public String getHoroscope()
	{
		return horoscope;
	}

	public void setHoroscope(String horoscope)
	{
		this.horoscope = horoscope;
	}

	public void setStartDate(String startDate)
	{
		this.startDate = startDate;
	}

	public String getStartDate()
	{
		return startDate;
	}

	public void setEndDate(String endDate)
	{
		this.endDate = endDate;
	}

	public String getEndDate()
	{
		return endDate;
	}

	public String getDetail()
	{
		return detail;
	}

	public void setDetail(String detail)
	{
		this.detail = detail;
	}

}
