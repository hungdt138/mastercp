/**
 * 
 */
package com.crm.provisioning.thread;

import javax.jms.Message;

import com.crm.kernel.message.Constants;
import com.crm.kernel.queue.QueueFactory;
import com.crm.provisioning.impl.quizquestion.Viettel8x26Connection;
import com.crm.provisioning.impl.smpp.SMPPConnection;
import com.crm.provisioning.message.CommandMessage;
import com.crm.provisioning.util.ResponseUtil;
import com.crm.util.GeneratorSeq;

/**
 * @author Hung
 *
 */
public class Viettel8x26Instance extends CommandInstance
{

	public Viettel8x26Instance() throws Exception
	{
		super();
	}
	
	public int processMessage(Message message) throws Exception
	{
		boolean checkCommand = false;

		CommandMessage sms = (CommandMessage) QueueFactory.getContentMessage(message);
		
		String strCmdCheck = sms.getRequestValue(ResponseUtil.SMS_CMD_CHECK, "true");
		
		if (strCmdCheck.toUpperCase().equals("TRUE"))
		{
			checkCommand = true;
		}

		if (checkCommand)
		{
			return super.processMessage(message);
		}
		else
		{
			Viettel8x26Connection connection = null;
			
			try
			{
				// debugMonitor(message.toString());

				connection = (Viettel8x26Connection) getProvisioningConnection();

				try
				{
					
					sms.setProvisioningType("VT8X26");
					
					String cmdCode = sms.getParameters().getString("vt8x26.commandcode", "");
					int isCdr = sms.getParameters().getInt("vt8x26.iscdr");
					
					logMonitor("submit request (RequestId: "+sms.getOrderId()+") (MoId: "+sms.getSessionId()+") (SrcNum: " + sms.getServiceAddress() + ")" +
						" (DstNum: " + sms.getIsdn() + ") (MsgContent: "+sms.getRequest()+")");
					
					String resultStr = connection.sendSMS(sms.getSessionId(), sms.getServiceAddress(), sms.getIsdn(), cmdCode, "", 
							1, sms.getRequest(), 1, 0, isCdr);
					
					logMonitor("response received (RequestId: "+sms.getOrderId()+") (MTResult: "+resultStr+")");
					
					// debugMonitor(sms);
				}
				catch (Exception e)
				{
					sms.setStatus(Constants.ORDER_STATUS_DENIED);

					throw e;
				}
				finally
				{
					closeProvisioningConnection(connection);
				}
			}
			catch (Exception e)
			{
				throw e;
			}

			return Constants.BIND_ACTION_SUCCESS;
		}
	}
}
