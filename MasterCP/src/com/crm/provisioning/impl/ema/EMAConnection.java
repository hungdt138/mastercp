package com.crm.provisioning.impl.ema;

import com.crm.provisioning.cache.ProvisioningConnection;

public class EMAConnection extends ProvisioningConnection
{
	private TelnetWrapper	telnet;

	private String			mstrPrompt				= "\nEnter command: ";
	private String			mstrAdditionalCommand	= ";\n";

	public boolean			isOpen					= false;
	public boolean			isLogin					= false;
	public boolean			isSending				= false;

	public EMAConnection()
	{
		super();
	}

	public boolean openConnection() throws Exception
	{
		try
		{
			// Open connection to telnet server
			isOpen = false;
			isLogin = false;
			telnet = new TelnetWrapper(host, port);
			isOpen = true;

			try
			{
				telnet.setSoTimeout((int)timeout);
				telnet.send("LOGIN:" + userName + ":" + password + ";\r\n");
				telnet.receiveUntil(";");
			}
			catch (Exception ex)
			{
				throw ex;
			}

			isLogin = true;
		}
		catch (Exception ex)
		{
			try
			{
				if (isOpen)
				{
					telnet.disconnect();

					isOpen = false;
				}
			}
			catch (Exception e1)
			{
				dispatcher.logMonitor(e1);
			}

			telnet = null;
		}

		setClosed(!isOpen);

		return (telnet != null);
	}

	public boolean closeConnection()
	{
		try
		{
			if (isLogin)
			{
				try
				{
					telnet.send("LOGOUT;\n");
				}
				catch (Exception ex)
				{
					dispatcher.logMonitor(ex);
				}
			}
		}
		catch (Exception e)
		{
			dispatcher.logMonitor(e);
		}

		try
		{
			if (isOpen)
			{
				telnet.disconnect();
				isOpen = false;
			}
		}
		catch (Exception e)
		{
			dispatcher.logMonitor(e);
		}
		finally
		{
			telnet = null;
		}

		return true;
	}
	
	@Override
	public boolean validate() throws Exception
	{
		try
		{
			//System.out.println("checking ema connection");
			telnet.send("TEST;\r\n");
			telnet.receiveUntil(";");
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}

	public String executeCommand(String strCommand) throws Exception
	{
		// Clear buffer
		if (telnet == null)
		{
			throw new Exception("Connection to server closed");
		}
		String str = telnet.receive();

		// Send command
		String strSend = strCommand + mstrAdditionalCommand;
		telnet.send(strSend);
		str = telnet.receiveUntilEx(mstrPrompt, timeout);

		// Remove prompt & correct response
		str = str.substring(0, str.length() - mstrPrompt.length());

		return str;
	}

	public String executeCommand(String template, String isdn) throws Exception
	{
		try
		{
			String command = template.replaceAll("<ISDN>", isdn);

			return executeCommand(command);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
	
	public static void main(String[] params)
	{
		EMAConnection connection = new EMAConnection();
		connection.setHost("192.168.194.4");
		connection.setPort(3300);
		connection.setUserName("selfcare");
		connection.setPassword("ematest");
		connection.setTimeout(1000);
		
		try
		{
			connection.openConnection();
			
			for (int i = 0; i < 10; i++)
			{
				System.out.println("Check connection =" + connection.validate());
				Thread.sleep(1000);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{
			connection.closeConnection();
		}
		
	}
}
