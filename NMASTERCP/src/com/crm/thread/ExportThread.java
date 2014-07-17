package com.crm.thread;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Vector;

import com.crm.kernel.io.FileUtil;
import com.crm.kernel.sql.Database;
import com.crm.thread.util.ThreadUtil;
import com.crm.util.StringUtil;

import com.fss.util.AppException;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright:
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author: HungPQ
 * @version 1.0
 */

public class ExportThread extends FileThread
{
	public PreparedStatement	stmtExport		= null;
	public PreparedStatement	stmtUpdate		= null;
	public ResultSet			rsExport		= null;

	public String				selectSQL		= "";
	public String				updateSQL		= "";
	public String				primaryKeys		= "";
	public String				exportFields	= "";
	public String				header			= "";
	public String				delimiter		= "";
	public String				dateFormat		= "";
	
	public FileWriter			fileExport		= null;
	public BufferedWriter		fileBuffer		= null;
	
	public int 					createFileInteval = 1;

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
		vtReturn.addElement(ThreadUtil.createTextParameter("header", 4000, ""));
		vtReturn.addElement(ThreadUtil.createTextParameter("delimiter", 10, ""));
		vtReturn.addElement(ThreadUtil.createTextParameter("dateFormat", 30, ""));

		return vtReturn;
	}

	// //////////////////////////////////////////////////////
	// Override
	// //////////////////////////////////////////////////////
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Vector getDispatcherDefinition()
	{
		Vector vtReturn = new Vector();

		vtReturn.addAll(getFileDefinition());
		vtReturn.addAll(ThreadUtil.createLogParameter(this));

		return vtReturn;
	}

	// //////////////////////////////////////////////////////
	// Override
	// //////////////////////////////////////////////////////
	public void fillFileParameter() throws Exception
	{
		try
		{
			exportDir = loadDirectory("exportDir", true, true);
			tempDir = loadDirectory("tempDir", true, true);
			errorDir = loadDirectory("errorDir", true, false);
			backupDir = loadDirectory("backupDir", true, true);

			selectSQL = ThreadUtil.getString(this, "selectSQL", true, "");
			updateSQL = ThreadUtil.getString(this, "updateSQL", false, "");
			primaryKeys = ThreadUtil.getString(this, "primaryKeys", false, "");
			exportFields = ThreadUtil.getString(this, "exportFields", false, "");

			header = ThreadUtil.getString(this, "header", false, "");
			delimiter = ThreadUtil.getString(this, "delimiter", false, ";");
			dateFormat = ThreadUtil.getString(this, "dateFormat", false, "yyyy/MM/dd hh:mm:ss");
			
			createFileInteval = ThreadUtil.getInt(this, "DelayTime", 10000);
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
		}
	}

	// //////////////////////////////////////////////////////
	/**
	 * Event raised when session prepare to run
	 * 
	 * @throws java.lang.Exception
	 * @author Phan Viet Thang
	 */
	// //////////////////////////////////////////////////////
	public void beforeProcessSession() throws Exception
	{
		//super.beforeProcessSession();

		try
		{
			mcnMain = Database.getConnection();
			mcnMain.setAutoCommit(false);

			if (!selectSQL.equals(""))
			{
				stmtExport = mcnMain.prepareStatement(selectSQL);
			}
			if (!updateSQL.equals(""))
			{
				stmtUpdate = mcnMain.prepareStatement(updateSQL);
			}
		}
		catch (Exception e)
		{
			throw e;
		}
	}

	// //////////////////////////////////////////////////////
	/**
	 * Event raised when session prepare to run
	 * 
	 * @throws java.lang.Exception
	 * @author Phan Viet Thang
	 */
	// //////////////////////////////////////////////////////
	public void afterProcessSession() throws Exception
	{
		try
		{
			Database.closeObject(rsExport);
			Database.closeObject(stmtExport);
			Database.closeObject(stmtUpdate);
			Database.closeObject(mcnMain);

			FileUtil.safeClose(fileBuffer);
			FileUtil.safeClose(fileExport);
		}
		finally
		{
			super.afterProcessSession();
		}
	}

	// //////////////////////////////////////////////////////
	// before process file
	// Author : ThangPV
	// Created Date : 16/09/2004
	// //////////////////////////////////////////////////////
	protected void beforeProcessFile(FileEntry fileEntry ) throws Exception
	{
		super.beforeProcessFile(fileEntry);
	}

	// //////////////////////////////////////////////////////
	// after process file
	// Author : ThangPV
	// Created Date : 16/09/2004
	// //////////////////////////////////////////////////////
	protected void afterProcessFile(FileEntry fileEntry, boolean success) throws Exception
	{
		String fileName = fileEntry.getFileName();
		File file = null;
		try
		{
			//fileBuffer.flush();
			FileUtil.safeClose(fileBuffer);
			FileUtil.safeClose(fileExport);
			file = new File (tempDir + fileName);
			if (success)
			{
				if (file.length() > 0 && !FileUtil.copyFile(tempDir + fileName, exportDir + fileName))
				{
					throw new AppException("Can not move file" + fileName + "to directory " + exportDir);
				}
				else
				{
					if (file.length() > 0 && !FileUtil.copyFile(tempDir + fileName, backupDir + fileName))
					{
						throw new AppException("Can not move file" + fileName + "to directory " + backupDir);
					}
				}
					

				if ((errorBuffer != null) && (errorBuffer.length() > 0))
				{
					updateErrorFile(fileName);
				}

				mcnMain.commit();
			}
			else
			{
				Database.rollback(mcnMain);
				
				FileUtil.deleteFile(tempDir + fileName);
			}
		}
		catch (Exception e)
		{
			Database.rollback(mcnMain);
			FileUtil.deleteFile(tempDir + fileName);
			throw e;
		}
		finally
		{
			Database.closeObject(rsExport);

			updateStatistic();
			logMonitor("End of process file " + fileName);
			file = null;
		}
	}

	public void updateStatistic() throws Exception
	{
		if ((lastStatistic > 0) && (totalCount > 0))
		{
			StringBuilder sbLog = new StringBuilder();

			sbLog.append("Total ");
			sbLog.append(totalCount);
			sbLog.append(" record are processed.");
			sbLog.append("\n      Success record  : ");
			sbLog.append(successCount);
			sbLog.append("\n      Bypass record   : ");
			sbLog.append(bypassCount);
			sbLog.append("\n      Error record    : ");
			sbLog.append(errorCount);

			logMonitor(sbLog.toString());

			lastStatistic = System.currentTimeMillis();
		}
	}

	public void exportLine() throws Exception
	{
		SimpleDateFormat format = new SimpleDateFormat(dateFormat);

		if (!exportFields.equals(""))
		{
			StringBuilder line = new StringBuilder();

			for (String field : StringUtil.split(exportFields, ","))
			{
				if (!field.equals(""))
				{
					Object value = rsExport.getObject(field);

					if (value instanceof Timestamp)
					{
						line.append(format.format((Timestamp) value));
					}
					else if (value instanceof Date)
					{
						value = rsExport.getTimestamp(field);

						line.append(format.format((Timestamp) value));
					}
					else if (value != null)
					{
						line.append(value.toString());
					}
					else
					{
						line.append("");
					}
				}

				if (!delimiter.equals(""))
				{
					line.append(delimiter);
				}
			}

			if ((stmtUpdate != null) && !primaryKeys.equals(""))
			{
				int j = 1;

				for (String field : StringUtil.split(primaryKeys, ","))
				{
					Object value = rsExport.getObject(field);

					if ((value instanceof Timestamp) || (value instanceof Date))
					{
						value = rsExport.getTimestamp(field);
						
						stmtUpdate.setTimestamp(j, (Timestamp) value);
					}
					else if (value != null)
					{
						stmtUpdate.setString(j, rsExport.getString(field));
					}
					else
					{
						stmtUpdate.setString(j, "");
					}

					j++;
				}

				stmtUpdate.execute();
			}

			if (!delimiter.equals(""))
			{
				fileBuffer.write(line.substring(0, line.length() - delimiter.length()));
			}
			else
			{
				fileBuffer.write(line.toString());
			}
			fileBuffer.write("\r\n");
		}

		totalCount++;
		successCount++;
	}

	public void errorLine(Exception e) throws Exception
	{
		logMonitor("Error occur when export file: " + e.getMessage());
	}

	public void processFile(FileEntry fileEntry) throws Exception
	{
		String fileName = fileEntry.getFileName();
		
		try
		{
			batchCount = 0;

			fileExport = new FileWriter(tempDir + fileName);
			fileBuffer = new BufferedWriter(fileExport);

			rsExport = stmtExport.executeQuery();

			while (rsExport.next() && isAvailable())
			{
				try
				{
					exportLine();
				}
				catch (AppException e)
				{
					errorLine(e);

					errorCount++;
				}
				catch (Exception e)
				{
					throw e;
				}

				batchCount++;

				if (batchCount > batchSize)
				{
					updateBatch();

					batchCount = 0;
				}
			}

			if (!isAvailable() && rsExport.next())
			{
				throw new AppException("Aborted by user when processing file");
			}
			fileEntry.setSuccess(isAvailable());
			
			if (batchCount > 0)
			{
				updateBatch();
			}
			
		}
		catch (Exception e)
		{
			throw e;
		}		
	}
	public void doProcessSession() throws Exception
	{
		super.doProcessSession();
		Thread.sleep(createFileInteval * 1000);
	}
}
