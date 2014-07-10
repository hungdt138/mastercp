/**
 * ----------------------------------------------------------------- 
 * @ Copyright(c) 2013 Vietnamobile. JSC. All Rights Reserved.
 * ----------------------------------------------------------------- 
 * Date 	Author 		Version
 * ------------------------------------- 
 * Oct 2, 2013 hungdt  v1.0
 * -------------------------------------
 */
package com.gateway.service;

/**
 * @author hungdt
 *
 */
public class WapRequest extends ServiceRequest
{
	private String url = "";
	private String subject = "";
	public String getUrl()
	{
		return url;
	}
	public void setUrl(String url)
	{
		this.url = url;
	}
	public String getSubject()
	{
		return subject;
	}
	public void setSubject(String subject)
	{
		this.subject = subject;
	}
	
	
}
