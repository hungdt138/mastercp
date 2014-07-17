/**
 * 
 */
package com.crm.thread;

import java.util.Vector;

import com.crm.thread.util.ThreadUtil;

import com.fss.util.AppException;

/**
 * @author hungdt
 * 
 */
public class CGWSimulatorThread extends SimulatorThread
{
	protected String	balance			= "";
	protected String	amount			= "";
	protected String	expireDate		= "";
	protected String	description		= "";
	
	protected String	actionType		= "";	

	public CGWSimulatorThread()
	{
		super();
	}

	// //////////////////////////////////////////////////////
	// Override
	// //////////////////////////////////////////////////////
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Vector getSimulatorDefinition()
	{
		Vector vtReturn = new Vector();

		vtReturn.addElement(ThreadUtil.createTextParameter("deliveryUser", 30, ""));
		vtReturn.addElement(ThreadUtil.createComboParameter("channel", "SMS,web", ""));
		vtReturn.addElement(ThreadUtil.createTextParameter("serviceAddress", 30, ""));
		vtReturn.addElement(ThreadUtil.createTextParameter("keyword", 30, ""));

		vtReturn.addElement(ThreadUtil.createTextParameter("isdn", 30, ""));
		vtReturn.addElement(ThreadUtil.createTextParameter("endIsdn", 30,	
				"If parameter is set, the simulator send order of subscribers range from isdn to endIsdn."));
		vtReturn.addElement(ThreadUtil.createTextParameter("shipTo", 30, ""));
		vtReturn.addElement(ThreadUtil.createIntegerParameter("orderTimeout", ""));
		
		vtReturn.addElement(ThreadUtil.createTextParameter("balance", 30, ""));
		vtReturn.addElement(ThreadUtil.createTextParameter("amount", 30, ""));
		vtReturn.addElement(ThreadUtil.createTextParameter("expireDate", 30, ""));
		vtReturn.addElement(ThreadUtil.createTextParameter("description", 30, ""));
		vtReturn.addElement(ThreadUtil.createTextParameter("actiontype", 30, ""));

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

			// //////////////////////////////////////////////////////
			// Fill extent parameter
			// //////////////////////////////////////////////////////

			balance = ThreadUtil.getString(this, "balance", false, "");
			amount = ThreadUtil.getString(this, "amount", false, "");
			expireDate = ThreadUtil.getString(this, "expireDate", false, "");
			description = ThreadUtil.getString(this, "description", false, "");
			actionType = ThreadUtil.getString(this, "actiontype", false, "");
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
}
