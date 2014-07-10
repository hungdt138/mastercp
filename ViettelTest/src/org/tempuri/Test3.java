/**
 * 
 */
package org.tempuri;

/**
 * @author Hung
 *
 */
public class Test3
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		String a = "a";
		String[] b = a.split(",");
		
		if(b.length > 0 && a.contains(","))
		{
			System.out.println("ok");
		}
		
		System.out.println(b[0]);

	}

}
