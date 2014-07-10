/**
 * ----------------------------------------------------------------- 
 * @ Copyright(c) 2013 Vietnamobile. JSC. All Rights Reserved.
 * ----------------------------------------------------------------- 
 * Date 	Author 		Version
 * ------------------------------------- 
 * Oct 25, 2013 HungDT  v1.0
 * -------------------------------------
 */
package com.crm.provisioning.thread.vnm;

import java.util.Vector;

import com.crm.provisioning.thread.CommandThread;
import com.crm.thread.DispatcherThread;
import com.crm.thread.util.ThreadUtil;
import com.crm.util.AppProperties;
import com.fss.util.AppException;

/**
 * @author HungDT
 * 
 */
public class VNMSendSMSThread extends CommandThread
{
	protected int	agentId		= 0;
	protected int	merchantId	= 0;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Vector getParameterDefinition()
	{
		Vector vtReturn = new Vector();
		vtReturn.add(ThreadUtil.createIntegerParameter("agentId", "agent id"));
		vtReturn.add(ThreadUtil
				.createIntegerParameter("merchantId", "merchantId id"));
		vtReturn.addAll(super.getParameterDefinition());
		return vtReturn;
	}

	public void fillDispatcherParameter() throws AppException
	{
		// TODO Auto-generated method stub
		super.fillDispatcherParameter();
		agentId = ThreadUtil.getInt(this, "agentId", 0);
		merchantId = ThreadUtil.getInt(this, "merchantId", 0);
	}

	public void initProvisioningParameters() throws Exception
	{
		try
		{
			super.initProvisioningParameters();

			AppProperties parameters = new AppProperties();
			parameters.setInteger("merchantId", merchantId);
			parameters.setInteger("agentId", agentId);

			provisioningPool.setParameters(parameters);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}
