/**
 * 
 */
package com.gateway.service;

import java.text.ParseException;

/**
 * @author hungdt
 *
 */
public class ReceiveSubInfoReq extends ServiceRequest
{
	private String dest;

	public String getDest()
	{
		return dest;
	}

	public void setDest(String dest)
	{
		this.dest = dest;
	}
	
	public String toOrderString() throws ParseException
	{
		StringBuffer result = new StringBuffer();
		result.append(super.toOrderString());
		result.append(" | dest = ");
		result.append(getDest());
		return result.toString();
	}
}
