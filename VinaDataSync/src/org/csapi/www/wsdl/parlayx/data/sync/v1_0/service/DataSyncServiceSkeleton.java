/**
 * DataSyncServiceSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.4  Built on : Apr 26, 2008 (06:24:30 EDT)
 */
package org.csapi.www.wsdl.parlayx.data.sync.v1_0.service;

import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;
import org.csapi.www.schema.parlayx.data.sync.v1_0.local.SyncOrderRelation;
import org.csapi.www.schema.parlayx.data.sync.v1_0.local.SyncOrderRelationResponse;
import org.csapi.www.schema.parlayx.data.sync.v1_0.local.SyncOrderRelationResponseE;
import org.csapi.www.schema.parlayx.data.v1_0.UserID;

import com.crm.provisioning.message.CommandMessage;
import com.crm.util.AppProperties;
import com.crm.provisioning.message.CommandMessage;
import com.crm.util.AppProperties;

/**
 * DataSyncServiceSkeleton java skeleton for the axisService
 */
public class DataSyncServiceSkeleton extends WebserviceBase
{
	private static Logger			logger				= Logger.getLogger(DataSyncServiceSkeleton.class);
	/**
	 * Auto generated method signature
	 * 
	 * @param syncMSISDNChange
	 */

	public org.csapi.www.schema.parlayx.data.sync.v1_0.local.SyncMSISDNChangeResponseE syncMSISDNChange(
			org.csapi.www.schema.parlayx.data.sync.v1_0.local.SyncMSISDNChangeE syncMSISDNChange)
	{
		// TODO : fill this with the necessary business logic
		throw new java.lang.UnsupportedOperationException("Please implement " + this
				.getClass().getName() + "#syncMSISDNChange");
	}

	/**
	 * Auto generated method signature
	 * 
	 * @param changeMSISDN
	 */

	public org.csapi.www.schema.parlayx.data.sync.v1_0.local.ChangeMSISDNResponseE changeMSISDN(
			org.csapi.www.schema.parlayx.data.sync.v1_0.local.ChangeMSISDNE changeMSISDN)
	{
		// TODO : fill this with the necessary business logic
		throw new java.lang.UnsupportedOperationException("Please implement " + this
				.getClass().getName() + "#changeMSISDN");
	}

	/**
	 * Auto generated method signature
	 * 
	 * @param syncSubscriptionData
	 */

	public org.csapi.www.schema.parlayx.data.sync.v1_0.local.SyncSubscriptionDataResponseE syncSubscriptionData(
			org.csapi.www.schema.parlayx.data.sync.v1_0.local.SyncSubscriptionDataE syncSubscriptionData)
	{
		// TODO : fill this with the necessary business logic
		throw new java.lang.UnsupportedOperationException("Please implement " + this
				.getClass().getName() + "#syncSubscriptionData");
	}

	/**
	 * Auto generated method signature
	 * 
	 * @param syncOrderRelation
	 * @throws Exception 
	 */

	public org.csapi.www.schema.parlayx.data.sync.v1_0.local.SyncOrderRelationResponseE syncOrderRelation(
			org.csapi.www.schema.parlayx.data.sync.v1_0.local.SyncOrderRelationE syncOrderRelation) throws Exception
	{
		SyncOrderRelationResponseE responseE = new SyncOrderRelationResponseE();
		SyncOrderRelationResponse response = new SyncOrderRelationResponse();
		
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		//MessageContext msgContext = MessageContext.getCurrentMessageContext();
		
		//SoapHeaderUtil.printSoapHeader(msgContext);
		SyncOrderRelation sync = syncOrderRelation.getSyncOrderRelation();
		UserID user = sync.getUserID();
		System.out.println("SDP Sync MO "+user.getID()+ " = " + toDebugString(sync));
		logger.debug("SDP Sync MO "+user.getID()+ " = " + toDebugString(sync));
		
		CommandMessage message = new CommandMessage();
		String command = getCommand(sync.getProductID(), sync.getUpdateDesc());
		String shortCode = getShortCode(sync.getProductID(), sync.getUpdateDesc());
		
		message.setKeyword(command);
		message.setServiceAddress(shortCode);
		message.setIsdn(user.getID());
		message.setOrderDate(df.parse(sync.getEffectiveTime()));
		message.setChannel("web");
	
		try
		{
			
			CommandMessage result = sendMessageToQueue(message, 0);
			
			response.setResultDescription(result.getCause());
			response.setResult(result.getStatus());
			responseE.setSyncOrderRelationResponse(response);
			
			System.out.println(result.toString());
			System.out.println(response.toString());
			//logger.debug("Result all  "+user.getID()+ " : " + result.toLogString());
			logger.debug("Result "+user.getID()+ " = " + toDebugString(result));
			
		}
		catch (Exception e)
		{
			logger.error(e.toString());
			System.out.println("Error: "+e.toString());
			throw e;
		}

		
		System.out.println("return");
		return responseE;
	}
	
	public static String toDebugString(Object debugObject)
	{
		Class<?> type = debugObject.getClass();

		Method[] methods = type.getMethods();

		StringBuffer returnString = new StringBuffer();

		for (int i = 0; i < methods.length; i++)
		{
			if (!methods[i].getName().startsWith("get"))
			{
				continue;
			}

			try
			{
				returnString.append(methods[i].getName().substring(3));
				returnString.append(" = ");

				Object value = methods[i].invoke(debugObject, new Object[] {});

				if ((value instanceof Date) || (value instanceof Calendar))
				{
					returnString.append((new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")).format(value));
				}
				else
				{
					returnString.append(value.toString());
				}

				returnString.append(" | ");
			}
			catch (Exception e)
			{

			}
		}

		return returnString.toString();
	}

}
