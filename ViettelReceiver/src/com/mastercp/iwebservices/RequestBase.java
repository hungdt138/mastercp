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
public class RequestBase
{
	String	username	= "";
	String	password	= "";
	int		timeout;

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public int getTimeout()
	{
		return timeout;
	}

	public void setTimeout(int timeout)
	{
		this.timeout = timeout;
	}
	
	public String toString()
	{
		Class<? extends RequestBase> type = this.getClass();
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
