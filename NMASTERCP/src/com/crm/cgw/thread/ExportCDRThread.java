/**
 * 
 */
package com.crm.cgw.thread;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import com.crm.kernel.sql.Database;

import com.crm.thread.ExportThread;
import com.crm.thread.FileEntry;
import com.crm.thread.util.ThreadUtil;
import com.fss.util.AppException;

/**
 * @author hungdt
 * 
 */
public class ExportCDRThread extends ExportThread
{
	public String	filePrefix	= "";
	public String	fileExt		= "";

	// //////////////////////////////////////////////////////
	// Override
	// //////////////////////////////////////////////////////
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Vector getFileDefinition()
	{
		Vector vtReturn = new Vector();

		vtReturn.addElement(ThreadUtil.createTextParameter("selectSQL", 4000, ""));
		vtReturn.addElement(ThreadUtil.createTextParameter("updateSQL", 4000, ""));
		vtReturn.addElement(ThreadUtil.createTextParameter("primaryKeys", 4000, "fields will be fill in update SQL, separator by comma character"));
		vtReturn.addElement(ThreadUtil.createTextParameter("exportFields", 4000, "fields will be exported, separator by comma character"));

		vtReturn.addElement(ThreadUtil.createTextParameter("exportDir", 300, "export file to directory"));
		vtReturn.addElement(ThreadUtil.createTextParameter("backupDir", 300, "backup file to directory"));		
		vtReturn.addElement(ThreadUtil.createTextParameter("tempDir", 300, "temporary directory"));
		vtReturn.addElement(ThreadUtil.createTextParameter("errorDir", 300, ""));
		vtReturn.addElement(ThreadUtil.createTextParameter("filePrefix", 30, ""));
		vtReturn.addElement(ThreadUtil.createTextParameter("fileExt", 30, ""));
		vtReturn.addElement(ThreadUtil.createTextParameter("header", 4000, ""));
		vtReturn.addElement(ThreadUtil.createTextParameter("delimiter", 10, ""));
		vtReturn.addElement(ThreadUtil.createTextParameter("dateFormat", 30, ""));

		return vtReturn;
	}

	// //////////////////////////////////////////////////////
	// Override
	// //////////////////////////////////////////////////////
	public void fillFileParameter() throws AppException
	{
		try
		{
			super.fillFileParameter();
			
			filePrefix = ThreadUtil.getString(this, "filePrefix", false, "");
			fileExt = ThreadUtil.getString(this, "fileExt", false, "csv");
		}
		catch (AppException e)
		{
			logMonitor(e);

			throw e;
		}
		catch (Exception e)
		{
			logMonitor(e);

			throw new AppException(e.getMessage());
		}
		finally
		{
		}
	}

	// //////////////////////////////////////////////////////
	/**
	 * Session process
	 * 
	 * @throws java.lang.Exception
	 * @author Phan Viet Thang
	 */
	// //////////////////////////////////////////////////////
	public List<FileEntry> getFileList() throws Exception
	{
		PreparedStatement stmtMerchant = null;
		ResultSet rsMerchant = null;

		SimpleDateFormat format = new SimpleDateFormat("yyMMddHHmmss");

		List<FileEntry> list = new ArrayList<FileEntry>();

		try
		{
			stmtMerchant = mcnMain.prepareStatement("Select * From MerchantEntry");
			rsMerchant = stmtMerchant.executeQuery();

			while (rsMerchant.next())
			{
				FileEntry fileEntry = new FileEntry();

				fileEntry.setMasterId(rsMerchant.getLong("merchantId"));
				fileEntry.setMasterCode(rsMerchant.getString("alias_"));
				fileEntry.setMasterTitle(rsMerchant.getString("title"));

				fileEntry.setFileName(filePrefix + fileEntry.getMasterCode().toUpperCase()+ "_" + format.format(new Date()) + fileExt);

				list.add(fileEntry);
			}
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			Database.closeObject(rsMerchant);
			Database.closeObject(stmtMerchant);
		}

		return list;
	}

	// //////////////////////////////////////////////////////
	// before process file
	// Author : ThangPV
	// Created Date : 16/09/2004
	// //////////////////////////////////////////////////////
	protected void beforeProcessFile(FileEntry fileEntry) throws Exception
	{
		super.beforeProcessFile(fileEntry);

		stmtExport.setLong(1, fileEntry.getMasterId());
	}

}
