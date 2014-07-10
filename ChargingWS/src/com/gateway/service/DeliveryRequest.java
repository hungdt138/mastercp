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
import java.text.SimpleDateFormat;

/**
 * @author hungdt
 * 
 */
public class DeliveryRequest extends ServiceRequest
{
	public final static int	TEXT_SMS		= 0;
	public final static int	WAP_SMS			= 1;
 
	public final static int	FREE_OF_CHARGE	= 0;
	public final static int	CHARGE			= 1;

	private int				deliveryMode	= TEXT_SMS;
	private String			deliveryContent	= "";
	private String			deliveryWapTitle= "";
	private String			deliveryWapHref	= "";
 
	private int				chargeMode		= CHARGE;
	

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
		result.append(" | deliveryContent = ");
		result.append(getDeliveryContent());
		
		
		return result.toString();
	}
	
	public int getDeliveryMode()
	{
		return deliveryMode;
	}

	public void setDeliveryMode(int deliveryMode)
	{
		this.deliveryMode = deliveryMode;
	}

	public String getDeliveryContent()
	{
		return deliveryContent;
	}

	public void setDeliveryContent(String deliveryContent)
	{
		this.deliveryContent = deliveryContent;
	}

	public int getChargeMode()
	{
		return chargeMode;
	}

	public void setChargeMode(int chargeMode)
	{
		this.chargeMode = chargeMode;
	}

	public String getDeliveryWapTitle()
	{
		return deliveryWapTitle;
	}

	public void setDeliveryWapTitle(String deliveryWapTitle)
	{
		this.deliveryWapTitle = deliveryWapTitle;
	}

	public String getDeliveryWapHref()
	{
		return deliveryWapHref;
	}

	public void setDeliveryWapHref(String deliveryWapHref)
	{
		this.deliveryWapHref = deliveryWapHref;
	}

	
	
	
	
}
