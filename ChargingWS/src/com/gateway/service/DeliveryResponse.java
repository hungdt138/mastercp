/**
 *-----------------------------------------------------------------------------
 * @ Copyright(c) 2012  Vietnamobile. JSC. All Rights Reserved.
 *-----------------------------------------------------------------------------
 * FILE  NAME             : TopupReq.java
 * DESCRIPTION            :
 * PRINCIPAL AUTHOR       : hungdt
 * SYSTEM NAME            : Payment Gateway
 * MODULE NAME            : Webservice Merchant
 * LANGUAGE               : Java
 * DATE OF FIRST RELEASE  : 
 *-----------------------------------------------------------------------------
 * @ Datetime Oct 25, 2012 4:13:32 PM
 * @ Release 1.0.0.0
 * @ Version 1.0
 * -----------------------------------------------------------------------------------
 * Date	            Author	      Version        Description
 * -----------------------------------------------------------------------------------
 * Oct 25, 2012       hungdt        1.0 	       Initial Create
 * -----------------------------------------------------------------------------------
 */
package com.gateway.service;

import java.text.ParseException;

/**
 * @author hungdt
 * 
 */
public class DeliveryResponse extends ServiceResponse
{
	private int	numOfSms	= 0;
	private double 	amount;

	public String toOrderString() throws ParseException
	{
		StringBuffer result = new StringBuffer();
		result.append("orderId = ");
		result.append(" | isdn = ");
		result.append(getIsdn());
		result.append(" | result = ");
		result.append(getResult());
		result.append(" | status = ");
		result.append(getStatus());
		result.append(" | amount = ");
		result.append(getAmount());
		result.append(" | numOfSms = ");
		result.append(getNumOfSms());
		return result.toString();
	}
	
	public int getNumOfSms()
	{
		return numOfSms;
	}

	public void setNumOfSms(int numOfSms)
	{
		this.numOfSms = numOfSms;
	}
	
	public double getAmount()
	{
		return amount;
	}
	
	public void setAmount(double amount)
	{
		this.amount = amount;
	}
}
