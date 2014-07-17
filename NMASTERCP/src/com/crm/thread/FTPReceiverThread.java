/**
 * 
 */
package com.crm.thread;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import com.crm.kernel.io.FileUtil;
import com.crm.thread.util.ThreadUtil;
import com.fss.util.AppException;

/**
 * @author hungdt
 * 
 */
public class FTPReceiverThread extends FileThread
{
	public int			ftpUse				= 0;
	public String		ftpAddress			= "";
	public int			ftpPort				= 0;
	public String		ftpUser				= "";
	public String		ftpPass				= "";
	public String		ftpRemoteDir		= "";
	public String		ftpBackupDir		= "";
	public String		ftpTempDir			= "";
	public String		ftpPreFile			= "charggw";
	public String		ftpExtFile			= "";
	public String		ftpCollumnSeparate	= ",";

	public FTPClient	ftp					= null;

	// //////////////////////////////////////////////////////
	// Override
	// //////////////////////////////////////////////////////
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Vector getFileDefinition()
	{
		Vector vtReturn = new Vector();

		vtReturn.addElement(ThreadUtil.createTextParameter("ftpUse", 400, "The billing runing ftp mode, that must put cdr file in remote server"));
		vtReturn.addElement(ThreadUtil.createTextParameter("ftpAddress", 400, ""));
		vtReturn.addElement(ThreadUtil.createIntegerParameter("ftpPort", ""));
		vtReturn.addElement(ThreadUtil.createTextParameter("ftpUser", 400, ""));
		vtReturn.addElement(ThreadUtil.createTextParameter("ftpPass", 400, ""));
		vtReturn.addElement(ThreadUtil.createTextParameter("ftpRemoteDir", 400, ""));
		vtReturn.addElement(ThreadUtil.createTextParameter("ftpBackupDir", 400, ""));
		vtReturn.addElement(ThreadUtil.createTextParameter("ftpTempDir", 400, ""));

		vtReturn.addElement(ThreadUtil.createTextParameter("importDir", 300, ""));
		vtReturn.addElement(ThreadUtil.createTextParameter("tempDir", 300, ""));

		vtReturn.addElement(ThreadUtil.createTextParameter("wildcard", 300, ""));

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
		wildcard = ThreadUtil.getString(this, "wildcard", true, "*.*");

		importDir = loadDirectory("importDir", true, false);
		exportDir = loadDirectory("exportDir", true, false);
		tempDir = loadDirectory("tempDir", true, false);
		errorDir = loadDirectory("errorDir", true, false);
		rejectDir = loadDirectory("rejectDir", true, false);
		backupDir = loadDirectory("backupDir", true, false);

		ftpUse = ThreadUtil.getInt(this, "ftpUse", 3000);
		ftpAddress = ThreadUtil.getString(this, "ftpAddress", true, "");
		ftpPort = ThreadUtil.getInt(this, "ftpPort", 3000);
		ftpUser = ThreadUtil.getString(this, "ftpUser", true, "");
		ftpPass = ThreadUtil.getString(this, "ftpPass", true, "");
		ftpRemoteDir = ThreadUtil.getString(this, "ftpRemoteDir", true, "");
		ftpBackupDir = ThreadUtil.getString(this, "ftpBackupDir", false, "");
		ftpTempDir = ThreadUtil.getString(this, "ftpTempDir", false, "");
		ftpPreFile = ThreadUtil.getString(this, "ftpPreFile", false, "");
		ftpExtFile = ThreadUtil.getString(this, "ftpExtFile", false, "");
	}

	public void beforeProcessSession() throws Exception
	{
		super.beforeProcessSession();

		try
		{
			ftp = new FTPClient();
			ftp.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
			int reply;

			ftp.connect(ftpAddress);
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply))
			{
				ftp.disconnect();
			}
			ftp.login(ftpUser, ftpPass);
			ftp.setFileType(FTP.BINARY_FILE_TYPE);
			ftp.enterLocalPassiveMode();

			logMonitor("Connect to server success!");
		}
		catch (Exception e)
		{
			throw e;
		}

	}

	public void afterProcessSession() throws Exception
	{
		try
		{
			if (ftp.isConnected())
			{
				ftp.logout();
				ftp.disconnect();
			}
		}
		catch (Exception e)
		{
			logMonitor(e);
		}
		finally
		{
			super.afterProcessSession();
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
		List<FileEntry> list = new ArrayList<FileEntry>();

		FTPFile[] files = ftp.listFiles(ftpRemoteDir + workingDir + "/" + wildcard);
		String[] fileNames = new String[files.length];

		for (int j = 0; j < fileNames.length; j++)
		{
			fileNames[j] = files[j].getName();
		}

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

		return list;
	}

	// //////////////////////////////////////////////////////
	// after process file
	// Author : ThangPV
	// Created Date : 16/09/2004
	// //////////////////////////////////////////////////////
	protected void afterProcessFile(FileEntry fileEntry, boolean success) throws Exception
	{
		String fileName = fileEntry.getFileName();

		try
		{
			if (success)
			{
				if (!FileUtil.renameFile(tempDir + fileName, importDir + fileName, true))
				{
					throw new AppException("Can not move file to directory " + exportDir);
				}

				if ((errorBuffer != null) && (errorBuffer.length() > 0))
				{
					updateErrorFile(fileName);
				}
			}
			else
			{
				FileUtil.deleteFile(tempDir + fileName);
			}
		}
		catch (Exception e)
		{
			FileUtil.deleteFile(tempDir + fileName);

			throw e;
		}
	}

	public void processFile(FileEntry fileEntry) throws Exception
	{
		File localFile = null;
		FileOutputStream fileStream = null;
		OutputStream bufferStream = null;

		try
		{
			String fileName = fileEntry.getFileName();
			String remoteFile = ftpRemoteDir + workingDir + "/" + fileName;

			localFile = new File(tempDir + fileName);

			if (localFile.exists())
			{
				if (!localFile.delete())
				{
					throw new AppException("File " + fileName + " is existed in local directory");
				}
			}

			fileStream = new FileOutputStream(localFile);
			bufferStream = new BufferedOutputStream(fileStream);

			if (!ftp.retrieveFile(remoteFile, bufferStream))
			{
				logMonitor("Unable to download file from FTP server : " + fileName);
			}
			else if (!ftpBackupDir.equals(""))
			{
				if (!ftp.rename(remoteFile, ftpBackupDir + "/" + fileName))
				{
					throw new AppException("Can not backup remmote file to " + ftpBackupDir + "/" + fileName);
				}
			}
			else
			{
				ftp.deleteFile(remoteFile);
			}

			logMonitor("Download file " + fileName + " success from ");
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			FileUtil.safeClose(bufferStream);
			FileUtil.safeClose(fileStream);
			FileUtil.safeClose(localFile);
		}
	}
}
