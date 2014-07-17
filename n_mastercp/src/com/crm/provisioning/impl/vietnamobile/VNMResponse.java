/**
 * ----------------------------------------------------------------- 
 * @ Copyright(c) 2013 Vietnamobile. JSC. All Rights Reserved.
 * ----------------------------------------------------------------- 
 * Date 	Author 		Version
 * ------------------------------------- 
 * Oct 25, 2013 HungDT  v1.0
 * -------------------------------------
 */
package com.crm.provisioning.impl.vietnamobile;

/**
 * @author HungDT
 *
 */
public class VNMResponse
{
	private String result = "";
	private int numSMS = 0;
	private double amount = 0;
	private String cause = "";
	private String isdn = "";
	private String desc = "";
	private long orderId	= 0;
	
	
	public String getResult()
	{
		return result;
	}
	public void setResult(String result)
	{
		this.result = result;
	}
	public int getNumSMS()
	{
		return numSMS;
	}
	public void setNumSMS(int numSMS)
	{
		this.numSMS = numSMS;
	}
	public double getAmount()
	{
		return amount;
	}
	public void setAmount(double amount)
	{
		this.amount = amount;
	}
	public String getCause()
	{
		return cause;
	}
	public void setCause(String cause)
	{
		this.cause = cause;
	}
	public String getIsdn()
	{
		return isdn;
	}
	public void setIsdn(String isdn)
	{
		this.isdn = isdn;
	}
	public String getDesc()
	{
		return desc;
	}
	public void setDesc(String desc)
	{
		this.desc = desc;
	}
	public long getOrderId()
	{
		return orderId;
	}
	public void setOrderId(long orderId)
	{
		this.orderId = orderId;
	}
	
	
}
