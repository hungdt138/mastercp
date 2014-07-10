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
package com.gateway.model;

import java.util.Date;

/**
 * @author hungdt
 * 
 */
public class ServiceStatus extends ServiceResponse
{
	public final static int	ACTIVE			= 1;
	public final static int	BARRING			= 0;
	public final static int	SUSPEND			= 2;
	public final static int	CANCEL			= 3;
	
	
	private Date			registerDate	= null;
	private Date			expirationDate	= null;
	private Date			graceDate		= null;

	

	public Date getRegisterDate()
	{
		return registerDate;
	}

	public void setRegisterDate(Date registerDate)
	{
		this.registerDate = registerDate;
	}

	public Date getExpirationDate()
	{
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate)
	{
		this.expirationDate = expirationDate;
	}

	public Date getGraceDate()
	{
		return graceDate;
	}

	public void setGraceDate(Date graceDate)
	{
		this.graceDate = graceDate;
	}
}
