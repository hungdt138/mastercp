package com.crm.subscriber.bean;

import java.util.Date;

import com.crm.kernel.message.Constants;

public class SubscriberGoService
{
	private long	subProductId	= Constants.DEFAULT_ID;

	// Audit fields -->

	private long	userId			= Constants.DEFAULT_ID;
	private String	userName		= "";
	private Date	createDate		= null;
	private Date	modifiedDate	= null;

	// Other fields -->
	private long	score			= 0;
	private int		numOfQuestion	= 0;
	private long	lastQuestionId	= 0;

	private long	productId		= Constants.DEFAULT_ID;
	private String	isdn			= "";
	private String	listQuestion	= "";

	private int		subscriberType	= Constants.USER_CANCEL_STATUS;
	private int		barringStatus	= Constants.USER_CANCEL_STATUS;
	private int		supplierStatus	= Constants.SUPPLIER_CANCEL_STATUS;

	private Date	registerDate	= null;
	private Date	unregisterDate	= null;
	private Date	expirationDate	= null;

	private String	languageId		= "";

	public long getSubProductId()
	{
		return subProductId;
	}

	public void setSubProductId(long subProductId)
	{
		this.subProductId = subProductId;
	}

	public long getUserId()
	{
		return userId;
	}

	public void setUserId(long userId)
	{
		this.userId = userId;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public Date getCreateDate()
	{
		return createDate;
	}

	public void setCreateDate(Date createDate)
	{
		this.createDate = createDate;
	}

	public Date getModifiedDate()
	{
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate)
	{
		this.modifiedDate = modifiedDate;
	}

	public long getScore()
	{
		return score;
	}

	public void setScore(long score)
	{
		this.score = score;
	}

	public long getLastQuestionId()
	{
		return lastQuestionId;
	}

	public void setLastQuestionId(long lastQuestionId)
	{
		this.lastQuestionId = lastQuestionId;
	}

	public long getProductId()
	{
		return productId;
	}

	public void setProductId(long productId)
	{
		this.productId = productId;
	}

	public String getIsdn()
	{
		return isdn;
	}

	public void setIsdn(String isdn)
	{
		this.isdn = isdn;
	}

	public String getListQuestion()
	{
		return listQuestion;
	}

	public void setListQuestion(String listQuestion)
	{
		this.listQuestion = listQuestion;
	}

	public int getSubscriberType()
	{
		return subscriberType;
	}

	public void setSubscriberType(int subscriberType)
	{
		this.subscriberType = subscriberType;
	}

	public int getBarringStatus()
	{
		return barringStatus;
	}

	public void setBarringStatus(int barringStatus)
	{
		this.barringStatus = barringStatus;
	}

	public int getSupplierStatus()
	{
		return supplierStatus;
	}

	public void setSupplierStatus(int supplierStatus)
	{
		this.supplierStatus = supplierStatus;
	}

	public Date getRegisterDate()
	{
		return registerDate;
	}

	public void setRegisterDate(Date registerDate)
	{
		this.registerDate = registerDate;
	}

	public Date getUnregisterDate()
	{
		return unregisterDate;
	}

	public void setUnregisterDate(Date unregisterDate)
	{
		this.unregisterDate = unregisterDate;
	}

	public Date getExpirationDate()
	{
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate)
	{
		this.expirationDate = expirationDate;
	}

	public String getLanguageId()
	{
		return languageId;
	}

	public void setLanguageId(String languageId)
	{
		this.languageId = languageId;
	}

	public int getNumOfQuestion()
	{
		return numOfQuestion;
	}

	public void setNumOfQuestion(int numOfQuestion)
	{
		this.numOfQuestion = numOfQuestion;
	}
}
