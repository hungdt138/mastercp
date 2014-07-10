/**
 * ----------------------------------------------------------------- 
 * @ Copyright(c) 2013 Vietnamobile. JSC. All Rights Reserved.
 * ----------------------------------------------------------------- 
 * Date 	Author 		Version
 * ------------------------------------- 
 * Dec 18, 2013 hungdt  v1.0
 * -------------------------------------
 */
package com.mastercp.iwebservices;

/**
 * @author hungdt
 * 
 */
public class ViettelReq extends RequestBase
{
	private int		opId		= 0;
	private long	moId		= 0;
	private String	shortCode	= "";
	private String	isdn		= "";
	private String	cmdCode		= "";
	private String	msgContent	= "";
	private String	reqDate		= "";	// yyyymmddHHmmss

	public int getOpId()
	{
		return opId;
	}

	public void setOpId(int opId)
	{
		this.opId = opId;
	}

	public long getMoId()
	{
		return moId;
	}

	public void setMoId(long moId)
	{
		this.moId = moId;
	}

	public String getShortCode()
	{
		return shortCode;
	}

	public void setShortCode(String shortCode)
	{
		this.shortCode = shortCode;
	}

	public String getIsdn()
	{
		return isdn;
	}

	public void setIsdn(String isdn)
	{
		this.isdn = isdn;
	}

	public String getCmdCode()
	{
		return cmdCode;
	}

	public void setCmdCode(String cmdCode)
	{
		this.cmdCode = cmdCode;
	}

	public String getMsgContent()
	{
		return msgContent;
	}

	public void setMsgContent(String msgContent)
	{
		this.msgContent = msgContent;
	}

	public String getReqDate()
	{
		return reqDate;
	}

	public void setReqDate(String reqDate)
	{
		this.reqDate = reqDate;
	}

}
