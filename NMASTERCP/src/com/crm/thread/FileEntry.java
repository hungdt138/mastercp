/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.crm.thread;

import com.crm.util.AppProperties;

public class FileEntry
{
	private long			fileId		= 0;

	private long			masterId	= 0;
	private String			masterCode	= "";
	private String			masterTitle	= "";

	private AppProperties	properties	= new AppProperties();

	private String			folder		= "";
	private String			fileName	= "";
	private boolean			success		= false;

	public long getFileId()
	{
		return fileId;
	}

	public void setFileId(long fileId)
	{
		this.fileId = fileId;
	}

	public long getMasterId()
	{
		return masterId;
	}

	public void setMasterId(long masterId)
	{
		this.masterId = masterId;
	}

	public String getMasterCode()
	{
		return masterCode;
	}

	public void setMasterCode(String masterCode)
	{
		this.masterCode = masterCode;
	}

	public String getMasterTitle()
	{
		return masterTitle;
	}

	public void setMasterTitle(String masterTitle)
	{
		this.masterTitle = masterTitle;
	}

	public AppProperties getProperties()
	{
		return properties;
	}

	public void setProperties(AppProperties properties)
	{
		this.properties = properties;
	}

	public String getFolder()
	{
		return folder;
	}

	public void setFolder(String folder)
	{
		this.folder = folder;
	}

	public String getFileName()
	{
		return fileName;
	}

	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}

	public boolean isSuccess()
	{
		return success;
	}

	public void setSuccess(boolean success)
	{
		this.success = success;
	}
}