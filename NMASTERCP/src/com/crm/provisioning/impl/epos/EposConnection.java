package com.crm.provisioning.impl.epos; 

import java.net.URL;
import java.rmi.RemoteException;

import org.apache.axis.AxisFault;
import org.apache.log4j.Logger;

import com.crm.provisioning.cache.ProvisioningConnection;
import com.fss.SMSUtility.BasicInput;
import com.fss.SMSUtility.LoginOutput;
import com.fss.SMSUtility.MposFunctionSoapBindingStub;

public class EposConnection extends ProvisioningConnection 
{
	private String URL = "";
	private String User ="";
	private String Pass = "";
	private MposFunctionSoapBindingStub _connection = null;
	private LoginOutput login = null;
	private BasicInput In = null;
	
	public EposConnection()
	{
		super();
	}
	public boolean openConnection() throws Exception
	{
		loadService(host, userName, password);
		return super.openConnection();
	}
	public void loadService(String strUrl, String strUser, String strPassword) throws Exception
	{
		this.URL = strUrl;
		this.User = strUser;
		this.Pass = strPassword;

		this.In = new BasicInput();
		this.In.setPassword(strPassword);
		this.In.setUsername(strUser);
		
		try
		{
			_connection = new MposFunctionSoapBindingStub(new URL(this.URL),null);			
			this.login = _connection.login(this.In);
		}
		catch (AxisFault ex)
		{
			throw ex;
		}
		catch (RemoteException e)
		{
			throw e;
		}
	}
	/**
	 * Purpose: Change COS for 3G register/cancel.
	 * @param _instance
	 * @param command
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String  changeCOS (String isdn, String Option, String packageType,boolean isPostpaid) throws Exception
	{
		boolean  success = false;
		String responseCode = "";
		try
		{						
			if (isPostpaid)
			{
				success = _connection.changeCOS4POS(isdn, Option, packageType, this.In);
			}
			else
			{
				success = _connection.changeCos4Pre(isdn, Option, this.In);
			}
			if (success)
			{
				responseCode = "success";
			}
				
		}
		catch (Exception ex)
		{
			_logger.error(ex,ex);
			// Checking response of EPOS.
			responseCode = ePosErrorHandle(ex.getMessage());
		}
		finally
		{
		}
		
		return responseCode;
	}
	/**
	 * Purpose: Handle error from EPOS.
	 * @param errorDetail
	 * @return
	 */
	public String ePosErrorHandle(String errorDetail)
	{
		String response = "";
		
		if (errorDetail == null)
		{
			return response;
		}
		
		if (errorDetail.contains("OOM3-00015"))
		{
			response = "cust-is-not-found";
		}
		else if (errorDetail.contains("OOM3-10037"))
		{
			response = "cos-is-not-exist";
		}
		else if (errorDetail.contains("OOM3-10041"))
		{
			response = "parameter-is-not-correct";
		}
		else if (errorDetail.contains("OOM3-10042"))
		{
			response = "in-past-cos-registed";
		}
		else if (errorDetail.contains("OOM3-10043"))
		{
			response = "in-past-cos-canceled";
		}
		else
		{
			response = "error";
		}
			
		return response;
	}
	
	public String getUser()
	{
		return User;
	}
	public void setUser(String user)
	{
		User = user;
	}
	public String getPass()
	{
		return Pass;
	}
	public void setPass(String pass)
	{
		Pass = pass;
	}
	public MposFunctionSoapBindingStub get_connection()
	{
		return _connection;
	}
	public void set_connection(MposFunctionSoapBindingStub _connection)
	{
		this._connection = _connection;
	}
	public LoginOutput getLogin()
	{
		return login;
	}
	public void setLogin(LoginOutput login)
	{
		this.login = login;
	}	
	private Logger		_logger		= Logger.getLogger(EposConnection.class);
	public static void main (String args[])
	{
		EposConnection obj = null;
		try
		{
			String strUrl ="http://10.8.13.61:7865/eload/services/MposFunction?wsdl";
			String strUser ="USERTEST";
			String strPassword="PASSTEST";
			obj = new EposConnection();
			obj.loadService(strUrl, strUser, strPassword);
			String isdn = "0922000511";
			String Option= "REGISTER";
			
			System.out.println(obj.changeCOS(isdn, Option,"FlexiData", true));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				obj.closeConnection();
			}
			catch(Exception ex)
			{
				
			}
		}
	}	
}
