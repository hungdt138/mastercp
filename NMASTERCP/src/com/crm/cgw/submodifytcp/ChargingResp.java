/**
 * 
 */
package com.crm.cgw.submodifytcp;

import javax.jms.Message;
import javax.jms.ObjectMessage;

import com.crm.provisioning.message.CommandMessage;

/**
 * @author hungdt
 * 
 */
public class ChargingResp implements INetData
{

	int		m_iSequence;
	String	m_sMDN;
	String	m_sExpiredDate;
	String	m_sErrorCode;
	String	m_sDetailCode;
	String	m_sDetail;

	String	m_sSessionStatus;

	public ChargingResp()
	{
	}

	public ChargingResp(String strMessage)
	{
		String[] strArr = strMessage.split(",");
		
		this.m_iSequence = Integer.parseInt(strArr[0]);
		this.m_sMDN = strArr[1];
		this.m_sExpiredDate = strArr[2];
		this.m_sErrorCode = strArr[3];
		this.m_sSessionStatus = strArr[4];
	}

	public ChargingResp(ChargingResp strMessage)
	{
		this.m_iSequence = strMessage.getM_iSequence();
		this.m_sDetail = strMessage.getM_sDetail();
		this.m_sDetailCode = strMessage.getM_sDetailCode();
		this.m_sErrorCode = strMessage.getM_sErrorCode();
		this.m_sExpiredDate = strMessage.getM_sExpiredDate();
		this.m_sMDN = strMessage.getM_sMDN();
		this.m_sSessionStatus = strMessage.getM_sSessionStatus();
	}

	public String getM_sSessionStatus()
	{
		return m_sSessionStatus;
	}

	public void setM_sSessionStatus(String m_sSessionStatus)
	{
		this.m_sSessionStatus = m_sSessionStatus;
	}

	public int getM_iSequence()
	{
		return m_iSequence;
	}

	public void setM_iSequence(int m_iSequence)
	{
		this.m_iSequence = m_iSequence;
	}

	public String getM_sMDN()
	{
		return m_sMDN;
	}

	public void setM_sMDN(String m_sMDN)
	{
		this.m_sMDN = m_sMDN;
	}

	public String getM_sExpiredDate()
	{
		return m_sExpiredDate;
	}

	public void setM_sExpiredDate(String m_sExpiredDate)
	{
		this.m_sExpiredDate = m_sExpiredDate;
	}

	public String getM_sErrorCode()
	{
		return m_sErrorCode;
	}

	public void setM_sErrorCode(String m_sErrorCode)
	{
		this.m_sErrorCode = m_sErrorCode;
	}

	public String getM_sDetailCode()
	{
		return m_sDetailCode;
	}

	public void setM_sDetailCode(String m_sDetailCode)
	{
		this.m_sDetailCode = m_sDetailCode;
	}

	public String getM_sDetail()
	{
		return m_sDetail;
	}

	public void setM_sDetail(String m_sDetail)
	{
		this.m_sDetail = m_sDetail;
	}

	public String toString()
	{
		StringBuilder sbLog = new StringBuilder();
		
		sbLog.append(getM_iSequence());
		sbLog.append(",");
		sbLog.append(getM_sMDN());
		sbLog.append(",");
		sbLog.append(getM_sExpiredDate());
		sbLog.append(",");
		sbLog.append(getM_sErrorCode());
		sbLog.append(",");
		sbLog.append(getM_sSessionStatus());
		
		return sbLog.toString();
	}

	@Override
	public byte[] getData()
	{
		return getContent().getBytes();
	}

	@Override
	public String getContent()
	{
		return toString();
	}

	public static CommandMessage getFromMQMessage(Message message) throws Exception
	{
		if (message instanceof ObjectMessage)
		{
			Object content = ((ObjectMessage) message).getObject();
			
			if (content instanceof CommandMessage)
			{
				return (CommandMessage) content;
			}
			else
			{
				return null;
			}
		}
		else
		{
			return null;
		}
	}

}
