/**
 * 
 */
package com.crm.util;

import java.util.ArrayList;

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
}
