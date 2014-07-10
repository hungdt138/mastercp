/**
 * ----------------------------------------------------------------- 
 * @ Copyright(c) 2013 Vietnamobile. JSC. All Rights Reserved.
 * ----------------------------------------------------------------- 
 * Date 	Author 		Version
 * ------------------------------------- 
 * Dec 18, 2013 hungdt  v1.0
 * -------------------------------------
 */
package com.mastercp.iwebservices;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author hungdt
 *
 */
public class ResponseBase
{
	public static final int	SUCCESS						= 0;

	public static final int	UNKNOWN						= 1;
	
	public static final int	FAILED						= 2; 

	public static final int	SYSTEM_OVERLOAD				= 3;

	public static final int	TIMEOUT						= 4;	// : Timeout

	public static final int	CAN_NOT_AUTHENTICATE		= 5;

	// Define Error code
	public static final int	NOT_HAS_PERMISSION			= 6;

	public static final int	WEBSERVICE_ERROR			= 7;
	
	public static final int	INVALID_SYNTAX				= 8;
	
	public static final int	WEBSERVICE_NOT_INITIALIZED	= 9;

	public static final int	RULE_NOT_FOUND				= 10;

	public static final int	NOT_CONNECT_DATABASE		= 11;

	public static final int	SYSTEM_BUSY					= 12;
	
	String					msisdn						= "";

	String					detail						= "";	// detail
																// description
																// of the error

	int						result						= -1;	// Successfully
																// or not
																// successfully

	public String getMsisdn()
	{
		return msisdn;
	}

	public void setMsisdn(String msisdn)
	{
		this.msisdn = msisdn;
	}

	public String getDetail()
	{
		return detail;
	}

	public void setDetail(String detail)
	{
		this.detail = detail;
	}

	public int getResult()
	{
		return result;
	}

	public void setResult(int result)
	{
		this.result = result;
	}
	
	
	public String toString()
	{
		Class<? extends ResponseBase> type = this.getClass();
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
