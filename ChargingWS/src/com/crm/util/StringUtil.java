/**
 * 
 */
package com.crm.util;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * @author ThangPV
 * 
 */
public class StringUtil
{
	public static ArrayList<String> toList(String source, String separator)
	{
		ArrayList<String> result = new ArrayList<String>();

		if (source.equals(""))
		{
			return result;
		}
		else
		{
			int index = 0;
			int last = 0;

			while ((index = source.indexOf(separator, last)) >= 0)
			{
				String element = source.substring(last, index).trim();

				if (!element.equals(""))
				{
					result.add(source.substring(last, index).trim());
				}

				last = index + separator.length();
			}

			if (last <= source.length())
			{
				result.add(source.substring(last, source.length()).trim());
			}

			return result;
		}
	}
	public static String toDebugString(Object debugObject)
	{
		Class<?> type = debugObject.getClass();

		Method[] methods = type.getMethods();

		StringBuffer returnString = new StringBuffer();

		for (int i = 0; i < methods.length; i++)
		{
			if (!methods[i].getName().startsWith("get"))
			{
				continue;
			}

			try
			{
				returnString.append(methods[i].getName().substring(3));
				returnString.append(" = ");

				Object value = methods[i].invoke(debugObject, new Object[] {});

				if ((value instanceof Date) || (value instanceof Calendar))
				{
					returnString.append((new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")).format(value));
				}
				else
				{
					returnString.append(value.toString());
				}

				returnString.append(" | ");
			}
			catch (Exception e)
			{

			}
		}

		return returnString.toString();
	}
}
