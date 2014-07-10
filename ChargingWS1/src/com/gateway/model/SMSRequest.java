/**
 * ----------------------------------------------------------------- 
 * @ Copyright(c) 2013 Vietnamobile. JSC. All Rights Reserved.
 * ----------------------------------------------------------------- 
 * Date 	Author 		Version
 * ------------------------------------- 
 * Oct 2, 2013 hungdt  v1.0
 * -------------------------------------
 */
package com.gateway.model;

/**
 * @author hungdt
 *
 */
public class SMSRequest extends ServiceRequest
{
	private String			deliveryContent	= "";

	public String getDeliveryContent()
	{
		return deliveryContent;
	}

	public void setDeliveryContent(String deliveryContent)
	{
		this.deliveryContent = deliveryContent;
	}
	
	
}
