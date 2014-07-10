package com.crm.provisioning.thread;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.crm.util.StringUtil;

public class CDR
{
	public static CDR createCDRFromFileString(String cdrContent) throws Exception
	{
		if (cdrContent == null)
		{
			return null;
		}

		String[] contentElements = cdrContent.split("\\|");

		CDR cdr = new CDR();

		try
		{
			cdr.setStreamNo(contentElements[0]);
			cdr.setTimeStamp(contentElements[1]);
			cdr.setChargeResult(contentElements[2]);
			cdr.setMsIsdn(contentElements[3]);
			cdr.setSpID(contentElements[4]);
			cdr.setServiceID(contentElements[5]);
			cdr.setProductID_telco(contentElements[6]);
			cdr.setChargeMode(contentElements[7]);
			cdr.setBeginTime(contentElements[8]);
			cdr.setEndTime(contentElements[9]);
			cdr.setPayType(contentElements[11]);
			cdr.setCost(contentElements[17]);
			cdr.setB_Isdn(contentElements[25]);
			cdr.setProductId(CDRImpl.getProductId(contentElements[5]));
			cdr.setThirdParty(contentElements[26]);
			cdr.setTimes(Integer.parseInt(contentElements[10]));
			cdr.setUplinkVolume(Integer.parseInt(contentElements[14]));
			cdr.setDownlinkVolume(Integer.parseInt(contentElements[15]));
			cdr.setPreDiscountFee(Integer.parseInt(contentElements[16]));
			cdr.setSpBenifitRate(Integer.parseInt(contentElements[18]));
			cdr.setsPBenifitFee(Integer.parseInt(contentElements[19]));
			cdr.setApn(contentElements[20]);
			cdr.setGgsnId(contentElements[21]);
			cdr.setpKgSpId(contentElements[22]);
			cdr.setpKgServiceId(contentElements[23]);
			cdr.setpKgProductId(contentElements[24]);
			cdr.setServiceCategory(contentElements[27]);
			cdr.setContentProvision(contentElements[28]);
		}
		catch (Exception ep)
		{
			ep.getStackTrace();
		}
		return cdr;
	}

	public String getStreamNo()
	{
		return streamNo;
	}

	public void setStreamNo(String streamNo)
	{
		this.streamNo = streamNo;
	}

	public String getTimeStamp()
	{
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp)
	{
		this.timeStamp = timeStamp;
	}

	public String getChargeResult()
	{
		return chargeResult;
	}

	public void setChargeResult(String chargeResult)
	{
		this.chargeResult = chargeResult;
	}

	public String getMsIsdn()
	{
		return msIsdn;
	}

	public void setMsIsdn(String msIsdn)
	{
		this.msIsdn = msIsdn;
	}

	public String getSpID()
	{
		return spID;
	}

	public void setSpID(String spID)
	{
		this.spID = spID;
	}

	public String getServiceID()
	{
		return serviceID;
	}

	public void setServiceID(String serviceID)
	{
		this.serviceID = serviceID;
	}

	public String getProductID_telco()
	{
		return productID_telco;
	}

	public void setProductID_telco(String productID_telco)
	{
		this.productID_telco = productID_telco;
	}

	public String getChargeMode()
	{
		return chargeMode;
	}

	public void setChargeMode(String chargeMode)
	{
		this.chargeMode = chargeMode;
	}

	public String getBeginTime()
	{
		return beginTime;
	}

	public void setBeginTime(String beginTime)
	{
		this.beginTime = beginTime;
	}

	public String getEndTime()
	{
		return endTime;
	}

	public void setEndTime(String endTime)
	{
		this.endTime = endTime;
	}

	public String getPayType()
	{
		return payType;
	}

	public void setPayType(String payType)
	{
		this.payType = payType;
	}

	public String getDuration()
	{
		return duration;
	}

	public void setDuration(String duration)
	{
		this.duration = duration;
	}

	public String getVolume()
	{
		return volume;
	}

	public void setVolume(String volume)
	{
		this.volume = volume;
	}

	public String getCost()
	{
		return cost;
	}

	public void setCost(String cost)
	{
		this.cost = cost;
	}

	public String getB_Isdn()
	{
		return b_Isdn;
	}

	public void setB_Isdn(String b_Isdn)
	{
		this.b_Isdn = b_Isdn;
	}

	public long getCDR_ID()
	{
		return CDR_ID;
	}

	public void setCDR_ID(long cDR_ID)
	{
		CDR_ID = cDR_ID;
	}

	public String getProductId()
	{
		return productId;
	}

	public void setProductId(String productId)
	{
		this.productId = productId;
	}

	public String getThirdParty()
	{
		return thirdParty;
	}

	public void setThirdParty(String thirdParty)
	{
		this.thirdParty = thirdParty;
	}

	public int getTimes()
	{
		return times;
	}

	public void setTimes(int times)
	{
		this.times = times;
	}

	public int getUplinkVolume()
	{
		return uplinkVolume;
	}

	public void setUplinkVolume(int uplinkVolume)
	{
		this.uplinkVolume = uplinkVolume;
	}

	public int getDownlinkVolume()
	{
		return downlinkVolume;
	}

	public void setDownlinkVolume(int downlinkVolume)
	{
		this.downlinkVolume = downlinkVolume;
	}

	public int getPreDiscountFee()
	{
		return preDiscountFee;
	}

	public void setPreDiscountFee(int preDiscountFee)
	{
		this.preDiscountFee = preDiscountFee;
	}

	public int getSpBenifitRate()
	{
		return spBenifitRate;
	}

	public void setSpBenifitRate(int spBenifitRate)
	{
		this.spBenifitRate = spBenifitRate;
	}

	public int getsPBenifitFee()
	{
		return sPBenifitFee;
	}

	public void setsPBenifitFee(int sPBenifitFee)
	{
		this.sPBenifitFee = sPBenifitFee;
	}

	public String getApn()
	{
		return apn;
	}

	public void setApn(String apn)
	{
		this.apn = apn;
	}

	public String getGgsnId()
	{
		return ggsnId;
	}

	public void setGgsnId(String ggsnId)
	{
		this.ggsnId = ggsnId;
	}

	public String getpKgSpId()
	{
		return pKgSpId;
	}

	public void setpKgSpId(String pKgSpId)
	{
		this.pKgSpId = pKgSpId;
	}

	public String getpKgServiceId()
	{
		return pKgServiceId;
	}

	public void setpKgServiceId(String pKgServiceId)
	{
		this.pKgServiceId = pKgServiceId;
	}

	public String getpKgProductId()
	{
		return pKgProductId;
	}

	public void setpKgProductId(String pKgProductId)
	{
		this.pKgProductId = pKgProductId;
	}

	public String getServiceCategory()
	{
		return serviceCategory;
	}

	public void setServiceCategory(String serviceCategory)
	{
		this.serviceCategory = serviceCategory;
	}

	public String getContentProvision()
	{
		return contentProvision;
	}

	public void setContentProvision(String contentProvision)
	{
		this.contentProvision = contentProvision;
	}

	private long	CDR_ID				= 0l;
	private String	streamNo			= "";
	private String	timeStamp			= "";
	private String	chargeResult		= "";
	private String	msIsdn				= "";
	private String	spID				= "";
	private String	serviceID			= "";
	private String	productID_telco		= "";
	private String	chargeMode			= "";
	private String	beginTime			= "";
	private String	endTime				= "";
	private String	payType				= "";
	private String	duration			= "";
	private String	volume				= "";
	private String	cost				= "";
	private String	b_Isdn				= "";
	private String	productId			= "";
	private String	thirdParty			= "";
	// add new
	private int		times				= 0;
	private int		uplinkVolume		= 0;
	private int		downlinkVolume		= 0;
	private int		preDiscountFee		= 0;
	private int		spBenifitRate		= 0;
	private int		sPBenifitFee		= 0;
	private String	apn					= "";
	private String	ggsnId				= "";
	private String	pKgSpId				= "";
	private String	pKgServiceId		= "";
	private String	pKgProductId		= "";
	private String	serviceCategory		= "";
	private String	contentProvision	= "";

	public String toString()
	{
		Class<? extends CDR> type = this.getClass();
		Method[] methods = type.getMethods();
		String returnString = "";
		for (int i = 0; i < methods.length; i++)
		{
			if (!methods[i].getName().startsWith("get"))
			{
				continue;
			}
			String member = "";
			try
			{
				member = methods[i].getName().substring(3) + "=";
				Object value = methods[i].invoke(this, new Object[] {});
				if (value instanceof Date || value instanceof Calendar)
				{
					member += (new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")).format(value);
				}
				else
				{
					member += value.toString();
				}
				member += " | ";
			}
			catch (Exception e)
			{
				member = "";
			}
			returnString += member;
		}
		return returnString.trim();
	}
}
