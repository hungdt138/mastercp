/**
 * 
 */
package com.crm.provisioning.thread;

import java.util.Vector;

import com.crm.thread.util.ThreadUtil;
import com.crm.util.AppProperties;
import com.fss.util.AppException;

/**
 * @author Hung
 *
 */
public class Viettel8x26Thread extends CommandThread
{
	protected String operator = "";
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Vector getParameterDefinition()
	{
		Vector vtReturn = new Vector();
		vtReturn.add(ThreadUtil.createTextParameter("operator", 500, "Operator"));
		vtReturn.addAll(super.getParameterDefinition());
		return vtReturn;
	}
	
	public void fillDispatcherParameter() throws AppException
	{
		// TODO Auto-generated method stub
		super.fillDispatcherParameter();
		operator = ThreadUtil.getString(this, "operator", false, "");
	}
	
	public void initProvisioningParameters() throws Exception
	{
		try
		{
			super.initProvisioningParameters();
			
			AppProperties parameters = new AppProperties();
			parameters.setString("operator", operator);
			
			provisioningPool.setParameters(parameters);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}
