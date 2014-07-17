/**
 * 
 */
package com.crm.provisioning.thread.sdpvms;

import java.util.Vector;

import com.crm.provisioning.thread.CommandThread;
import com.crm.thread.util.ThreadUtil;
import com.crm.util.AppProperties;
import com.fss.util.AppException;

/**
 * @author hungdt
 *
 */
public class SDPSendThread extends CommandThread
{
	public String	OA				= "84906130890";
	public String	FA				= "84906130890";

	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Vector getDispatcherDefinition()
	{
		Vector vtReturn = new Vector();


		vtReturn.addElement(ThreadUtil.createTextParameter("OA", 100, ""));

		vtReturn.addElement(ThreadUtil.createTextParameter("FA", 100, ""));


		vtReturn.addAll(super.getDispatcherDefinition());
		return vtReturn;
	}
	
	// //////////////////////////////////////////////////////
	// Override
	// //////////////////////////////////////////////////////
	public void fillParameter() throws AppException
	{
		try
		{
			super.fillParameter();

			
			OA = ThreadUtil.getString(this, "OA", true, "");

			FA = ThreadUtil.getString(this, "FA", true, "");
		
		}
		catch (AppException e)
		{
			logMonitor(e);

			throw e;
		}
		catch (Exception e)
		{
			logMonitor(e);

			throw new AppException(e.getMessage());
		}
	}
	
	public void initProvisioningParameters() throws Exception
	{
		try
		{
			super.initProvisioningParameters();

			AppProperties parameters = new AppProperties();

			

			parameters.setString("oa", OA);
			parameters.setString("fa", FA);
			
			

			provisioningPool.setParameters(parameters);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}
