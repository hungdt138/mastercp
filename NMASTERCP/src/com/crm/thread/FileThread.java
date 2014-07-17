package com.crm.thread;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import com.crm.kernel.io.FileUtil;
import com.crm.kernel.io.WildcardFilter;
import com.crm.kernel.sql.Database;
import com.crm.thread.util.ThreadUtil;

import com.fss.util.AppException;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2003
 * </p>
 * <p>
 * Company: FPT
 * </p>
 * 
 * @author ThangPV
 * @version 1.0 Purpose : Base class for other threads
 */

public class FileThread extends DispatcherThread
{
	public String			importDir			= "";
	public String			exportDir			= "";
	public String			tempDir				= "";
	public String			rejectDir			= "";
	public String			errorDir			= "";
	public String			backupDir			= "";
	public String			workingDir			= "";
	public String			wildcard			= "";
	public boolean			continueWhenError	= true;

	public StringBuilder	errorBuffer			= new StringBuilder();

	// //////////////////////////////////////////////////////
	// Override
	// //////////////////////////////////////////////////////
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Vector getFileDefinition()
	{
		Vector vtReturn = new Vector();

		vtReturn.addElement(ThreadUtil.createTextParameter("wildcard", 300, ""));
		vtReturn.addElement(ThreadUtil.createTextParameter("importDir", 300, ""));
		vtReturn.addElement(ThreadUtil.createTextParameter("exportDir", 300, ""));
		vtReturn.addElement(ThreadUtil.createTextParameter("tempDir", 300, ""));
		vtReturn.addElement(ThreadUtil.createTextParameter("errorDir", 300, ""));
		vtReturn.addElement(ThreadUtil.createTextParameter("rejectDir", 300, ""));
		vtReturn.addElement(ThreadUtil.createTextParameter("backupDir", 300, ""));

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
		vtReturn.addAll(super.getDispatcherDefinition());

		return vtReturn;
	}

	// //////////////////////////////////////////////////////
	// Override
	// //////////////////////////////////////////////////////
	public void fillFileParameter() throws Exception
	{
		wildcard = ThreadUtil.getString(this, "wildcard", true, "*.*");

		importDir = loadDirectory("importDir", true, false);
		exportDir = loadDirectory("exportDir", true, false);
		tempDir = loadDirectory("tempDir", true, false);
		errorDir = loadDirectory("errorDir", true, false);
		rejectDir = loadDirectory("rejectDir", true, false);
		backupDir = loadDirectory("backupDir", true, false);
	}

	// //////////////////////////////////////////////////////
	// Override
	// //////////////////////////////////////////////////////
	public void fillDispatcherParameter() throws Exception
	{
		try
		{
			super.fillDispatcherParameter();

			fillFileParameter();
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

	// /////////////////////////////////////////////////////////////////////////
	// Get Call_ID
	// Author: ThangPV
	// Modify DateTime: 19/02/2003
	// /////////////////////////////////////////////////////////////////////////
	public long getFileID() throws Exception
	{
		return Database.getSequence("FileSeq");
	}

	public void updateStatistic() throws Exception
	{
		if ((lastStatistic > 0) && (totalCount > 0))
		{
			StringBuilder sbLog = new StringBuilder();

			sbLog.append(" Total ");
			sbLog.append(totalCount);
			sbLog.append(" record are processed.");
			sbLog.append("\n      Success record  : ");
			sbLog.append(successCount);
			sbLog.append("\n      Bypass record   : ");
			sbLog.append(bypassCount);
			sbLog.append("\n      Error record    : ");
			sbLog.append(errorCount);

			logMonitor(sbLog.toString());
		}
	}

	public boolean backupFile(String fileName) throws Exception
	{
		if (backupDir.equals(""))
		{
			logMonitor("backupDir is null. File will be delete !");

			return FileUtil.deleteFile(importDir + fileName);
		}
		else
		{
			return FileUtil.renameFile(importDir + fileName, backupDir + fileName, true);
		}
	}

	public boolean rejectFile(String fileName) throws Exception
	{
		if (rejectDir.equals(""))
		{
			logMonitor("rejectDir is null. File will be delete !");

			return false;
		}

		return FileUtil.renameFile(importDir + fileName, rejectDir + fileName, true);
	}

	public boolean updateErrorFile(String fileName) throws Exception
	{
		RandomAccessFile fl = null;

		try
		{
			fl = new RandomAccessFile(tempDir + fileName, "rw");

			fl.seek(fl.length());
			fl.write(errorBuffer.toString().getBytes());
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			FileUtil.safeClose(fl);
		}

		return FileUtil.renameFile(tempDir + fileName, errorDir + fileName, true);
	}

	// //////////////////////////////////////////////////////
	// before process file
	// Author : ThangPV
	// Created Date : 16/09/2004
	// //////////////////////////////////////////////////////
	protected void beforeProcessFile(FileEntry fileEntry) throws Exception
	{
		logMonitor("Start of processing file " + fileEntry.getFileName());

		resetCounter();

		if (fileEntry != null)
		{
			fileEntry.setSuccess(false);
		}
	}

	// //////////////////////////////////////////////////////
	// after process file
	// Author : ThangPV
	// Created Date : 16/09/2004
	// //////////////////////////////////////////////////////
	protected void afterProcessFile(FileEntry fileEntry, boolean success) throws Exception
	{
		String fileName = fileEntry.getFileName();

		if (fileName == null)
		{
			boolean moved = false;

			if (success)
			{
				moved = backupFile(fileName);

				if ((errorBuffer != null) || (errorBuffer.length() > 0))
				{
					updateErrorFile(fileName);
				}
			}
			else
			{
				moved = rejectFile(fileName);
			}

			if (!moved)
			{
				logMonitor("Can not move file to relate folder. File will be delete");
			}
		}
	}

	// //////////////////////////////////////////////////////
	// after process file
	// Author : ThangPV
	// Created Date : 16/09/2004
	// //////////////////////////////////////////////////////
	protected void afterProcessFile(FileEntry fileEntry) throws Exception
	{
		String fileName = fileEntry.getFileName();

		try
		{
			afterProcessFile(fileEntry, fileEntry.isSuccess());
		}
		finally
		{
			updateStatistic();

			logMonitor("End of process file " + fileName);
		}
	}

	public void updateBatch() throws Exception
	{
	}

	public void processLine(String line) throws Exception
	{
	}

	public void errorLine(String line) throws Exception
	{
		errorBuffer.append(line);

		if (!line.endsWith("\r\n"))
		{
			errorBuffer.append("\r\n");
		}
	}

	public void processFile(FileEntry fileEntry) throws Exception
	{
		FileReader fileReader = null;
		BufferedReader bufferReader = null;

		try
		{
			batchCount = 0;

			fileReader = new FileReader(importDir + fileEntry.getFileName());
			bufferReader = new BufferedReader(fileReader);

			String line = bufferReader.readLine();

			while (line != null)
			{
				line = line.trim();

				if (!line.equals(""))
				{
					try
					{
						processLine(line);
					}
					catch (AppException e)
					{
						errorCount++;

						errorLine(line);
					}
					catch (Exception e)
					{
						throw e;
					}
				}
				else
				{
					bypassCount++;
				}

				line = bufferReader.readLine();

				batchCount++;

				if (batchCount > batchSize)
				{
					updateBatch();

					batchCount = 0;
				}
			}

			if (batchCount > 0)
			{
				updateBatch();
			}
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			FileUtil.safeClose(bufferReader);
			FileUtil.safeClose(fileReader);
		}
	}

	// /////////////////////////////////////////////////////////////////////////
	// validate file before call convert
	// Author: HiepTH
	// Modify DateTime: 09/07/2003
	// /////////////////////////////////////////////////////////////////////////
	protected boolean validateFile(String fileName) throws Exception
	{
		return true;
	}

	// //////////////////////////////////////////////////////
	/**
	 * Session process
	 * 
	 * @throws java.lang.Exception
	 * @author Phan Viet Thang
	 */
	// //////////////////////////////////////////////////////
	public List<String> getSubDirs() throws Exception
	{ 
		List<String> list = new ArrayList<String>();
		list.add("");

		return list;
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
		List<FileEntry> list = new ArrayList<FileEntry>();

		if (!importDir.equals(""))
		{
			String listedDir = importDir + workingDir;

			File flDir = new File(listedDir);
			String fileNames[] = flDir.list(new WildcardFilter(wildcard));

			if (fileNames != null && fileNames.length > 0)
			{
				Arrays.sort(fileNames);

				for (int j = 0; j < fileNames.length; j++)
				{
					if (validateFile(fileNames[j]))
					{
						FileEntry fileEntry = new FileEntry();
						fileEntry.setFileName(fileNames[j]);

						list.add(fileEntry);
					}
				}
			}
		}

		return list;
	}

	// //////////////////////////////////////////////////////
	/**
	 * Session process
	 * 
	 * @throws java.lang.Exception
	 * @author Phan Viet Thang
	 */
	// //////////////////////////////////////////////////////
	public void doProcessSession() throws Exception
	{
		try
		{
			List<String> subDirs = getSubDirs();

			if ((subDirs == null) || subDirs.isEmpty())
			{
				return;
			}

			for (String subDir : subDirs)
			{
				workingDir = subDir;

				List<FileEntry> fileList = getFileList();

				for (int j = 0; isAvailable() && (j < fileList.size()); j++)
				{
					FileEntry fileEntry = fileList.get(j);

					if (fileEntry == null)
					{
						continue;
					}

					try
					{
						beforeProcessFile(fileEntry);
						processFile(fileEntry);
					}
					catch (AppException e)
					{
						logMonitor(e.getMessage());

						fileEntry.setSuccess(false);
					}
					catch (Exception e)
					{
						fileEntry.setSuccess(false);

						throw e;
					}
					finally
					{
						afterProcessFile(fileEntry);
					}

					if (!fileEntry.isSuccess() && !continueWhenError)
					{
						break;
					}
				}
			}
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}
