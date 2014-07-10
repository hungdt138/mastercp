/**
 * 
 */
package com.crm.provisioning.impl.quizquestion;

import java.util.Date;

import com.crm.provisioning.cache.ProvisioningCommand;
import com.crm.provisioning.impl.CommandImpl;
import com.crm.provisioning.message.CommandMessage;
import com.crm.provisioning.thread.CommandInstance;
import com.fss.util.AppException;

/**
 * @author Hung
 * 
 */
public class Viettel8x26Impl extends CommandImpl
{
	public void sendSMS(CommandInstance instance, ProvisioningCommand provisioningCommand, CommandMessage request) throws Exception
	{
		Viettel8x26Connection connection = null;
		//CommandMessage result = request;
		try
		{
			connection = (Viettel8x26Connection) instance.getProvisioningConnection();

			instance.logMonitor("submit request (RequestId: "+request.getRequestId()+") (MoId: "+request.getSessionId()+") (SrcNum: " + request.getServiceAddress() + ")" +
					" (DstNum: " + request.getIsdn() + ") (MsgContent: "+request.getRequest()+")");
			
			String cmdCode = request.getParameters().getString("vt8x26.commandcode", "");
			int isCdr = request.getParameters().getInteger("vt8x26.iscdr",0);
			
			String resultStr = connection.sendSMS(request.getSessionId(), request.getServiceAddress(), request.getIsdn(), cmdCode, "", 
					1, request.getRequest(), 1, 0, isCdr);
			
			instance.logMonitor("response received (MTResult: "+resultStr+")");
			
			instance.debugMonitor(request.toLogString());
			
//			if(!resultStr.equals("Ok"))
//			{
//				throw new AppException("error");
//			}
		}
		catch (Exception e)
		{
			processError(instance, provisioningCommand, request, e);
		}
		finally
		{
			instance.closeProvisioningConnection(connection);
		}
		//return result;
	}

}
