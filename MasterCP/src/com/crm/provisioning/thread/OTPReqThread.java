/**
 * ----------------------------------------------------------------- 
 * @ Copyright(c) 2013 Vietnamobile. JSC. All Rights Reserved.
 * ----------------------------------------------------------------- 
 * Date 	Author 		Version
 * ------------------------------------- 
 * Oct 6, 2013 hungdt  v1.0
 * -------------------------------------
 */
package com.crm.provisioning.thread;

import java.util.Vector;

import com.crm.thread.util.ThreadUtil;
import com.crm.util.AppProperties;
import com.fss.util.AppException;

/**
 * @author hungdt
 *
 */
public class OTPReqThread extends CommandThread
{
	protected String otpLink = "";
	protected String username = "";
	protected String salt = "";
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Vector getParameterDefinition()
	{
		Vector vtReturn = new Vector();
		vtReturn.add(ThreadUtil.createTextParameter("otpLink", 500, "Link request OTP"));
		vtReturn.add(ThreadUtil.createTextParameter("username", 100, "Username"));
		vtReturn.add(ThreadUtil.createTextParameter("salt", 100, "Username"));
		vtReturn.addAll(super.getParameterDefinition());
		return vtReturn;
	}
	
	public void fillDispatcherParameter() throws AppException
	{
		// TODO Auto-generated method stub
		super.fillDispatcherParameter();
		otpLink = ThreadUtil.getString(this, "otpLink", false, "");
		username = ThreadUtil.getString(this, "username", false, "");
		salt = ThreadUtil.getString(this, "salt", false, "");
	}
	
	public void initProvisioningParameters() throws Exception
	{
		try
		{
			super.initProvisioningParameters();
			
			AppProperties parameters = new AppProperties();
			parameters.setString("otpLink", otpLink);
			parameters.setString("user", username);
			parameters.setString("password", salt);
			
			provisioningPool.setParameters(parameters);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}
