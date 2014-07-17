
package com.crm.provisioning.impl.mca;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import com.crm.provisioning.cache.ProvisioningConnection;
import com.fss.util.StringUtil;

public class MCAConnection extends ProvisioningConnection
{

	/**
	 * socket
	 */
	private Socket				socket	= null;

	/**
	 * Buffer, use for storing result.
	 */
	private BufferedReader		reader	= null;

	/**
	 * Buffer, use for storing command.
	 */
	private BufferedWriter		writer	= null;

	public final static String	SUCCESS	= "success";
	public final static String	FAILURE	= "fail";

	public MCAConnection() throws Exception 
	{
		super();
		
	}
	public boolean openConnection() throws Exception
	{
		try{
			socket = new Socket(host, port);
			socket.setSoTimeout(30000);
			getReader();
			getWriter();
		}
		catch (Exception e)
		{
			throw e;
		}
		return (socket != null);
	}

	/**
	 * Purpose: Constructor. Author: DuyMB Date : 04/03/2011
	 * 
	 * @param address
	 *            ,port
	 */
	public MCAConnection(String address, int port, int timeout) throws Exception 
	{
		try
		{
			socket = new Socket(address, port);
			socket.setSoTimeout(timeout);
			getReader();
			getWriter();
		}
		catch (Exception e)
		{
			throw e;
		}
	}

	/**
	 * Purpose: process command and receive message from SMI. Author: DuyMB Date
	 * : 04/03/2011
	 * 
	 * @param data
	 * @return String message.
	 * @throws Exception 
	 */
	public String processCommmand(String data) throws Exception
	{
		try
		{
			writeData(StringUtil.nvl(data,""));
			
			return StringUtil.nvl(readData(),"");
		}
		catch (Exception e)
		{
			throw e;
		}
	}

	/**
	 * Purpose: Author: DuyMB Date : 04/03/2011
	 */
	private void getReader() throws Exception
	{
		try
		{
			if (reader != null)
			{
				reader.close();
				reader = null;
			}
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Purpose: Author: DuyMB Date : 04/03/2011
	 */
	private void getWriter() throws Exception
	{
		try
		{
			if (writer != null)
			{
				writer.close();
				writer = null;
			}
			writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Purpose: write command into buffer. Author: DuyMB Date : 04/03/2011
	 */
	private void writeData(String data) throws Exception
	{
		try
		{
			writer.write(data, 0, data.length());
			writer.newLine();
			writer.flush();
		}
		catch (Exception ex)
		{
			throw ex;
		}
	}

	/**
	 * Purpose: Read information from buffer. Author: DuyMB Date : 04/03/2011
	 */
	private String readData() throws Exception
	{
		try
		{
			return reader.readLine();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			try
			{
				getReader();
				return null;
			}
			catch (Exception ex)
			{
				throw ex;
			}
		}
	}

	/**
	 * Purpose: Check command is success or failure. Author: DuyMB Date :
	 * 04/03/2011
	 * 
	 * @param result
	 * @return
	 */
	public String isSuccessCommand(String result)
	{
		result = result.toUpperCase();
		
		if ("".equals(result))
			return FAILURE;
		if (result.contains("ADDED") || result.contains("UPDATED") || result.contains("DELETED"))
		{
			return SUCCESS;
		}
		else if (!result.contains("FAILED"))
		{
			return SUCCESS;
		}

		return FAILURE;
	}

	/**
	 * Purpose: Disconnect socket and clear buffer.
	 */
	public void disconnect()
	{
		try
		{
			socket.close();
			reader.close();
			writer.close();
		}
		catch (Exception e)
		{

		}
	}
	@Override
	public boolean validate() throws Exception 
	{
		try
		{

			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}	
	
}
