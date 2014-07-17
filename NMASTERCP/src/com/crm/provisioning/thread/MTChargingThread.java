package com.crm.provisioning.thread;

import java.util.Vector;

import com.crm.thread.util.ThreadUtil;
import com.crm.util.AppProperties;
import com.fss.util.AppException;

public class MTChargingThread extends CommandThread
{
	private long agentId	= 0L;
	private long cpId		= 0L;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Vector getDispatcherDefinition()
	{
		Vector vtReturn = new Vector();

		vtReturn.addElement(ThreadUtil.createLongParameter("AgentId", "Mã subCP"));
		vtReturn.addElement(ThreadUtil.createLongParameter("CPId", "Mã CP"));
		
		vtReturn.addAll(ThreadUtil.createProvisioningParameter(this));

		vtReturn.add(ThreadUtil.createBooleanParameter("simulationMode", "Use simulation or not"));
		vtReturn.add(ThreadUtil.createLongParameter("simulationExecuteTime", "Simulation time in millisecond."));
		vtReturn.add(ThreadUtil.createTextParameter("simulationCause", 400, "Response cause after using simulation."));

		vtReturn.addAll(ThreadUtil.createQueueParameter(this));
		vtReturn.addAll(ThreadUtil.createInstanceParameter(this));
		vtReturn.addAll(ThreadUtil.createLogParameter(this));
		
		return vtReturn;
	}
	
	public void fillParameter() throws AppException
	{
		try
		{
			super.fillParameter();

			agentId = ThreadUtil.getLong(this, "AgentId", 0);
			cpId = ThreadUtil.getLong(this, "CPId", 0);
		}
		catch (AppException e)
		{
			throw e;
		}
		catch (Exception e)
		{
			throw new AppException(e.getMessage());
		}
	}
	
	public void initProvisioningParameters() throws Exception
	{
		try
		{
			super.initProvisioningParameters();

			AppProperties parameters = new AppProperties();

			parameters.setLong("AgentId", agentId);
			parameters.setLong("CPId", cpId);
			
			provisioningPool.setParameters(parameters);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}
