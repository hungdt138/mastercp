/**
 * 
 */
package com.crm.provisioning.thread;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPConnectionClosedException;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.net.ftp.FTPClient;

import com.crm.thread.DispatcherThread;
import com.crm.thread.util.ThreadUtil;
import com.fss.util.AppException;

/**
 * @author HungDT
 * 
 */
public class VmsgetcdrThread extends DispatcherThread
{

	public FTPClient	ftp					= null;
	protected Calendar	startTime			= null;

	public int			ftpUse				= 0;
	public String		ftpAddress			= "";
	public int			ftpPort				= 0;
	public String		ftpUser				= "";
	public String		ftpPass				= "";
	public String		ftpServFolder		= "";
	public String		ftpClientFolder		= "";
	public int			ftpDownloadInterval	= 0;
	public String		ftpPreFile			= "";
	public String		ftpTimetorun		= "";

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Vector getParameterDefinition()
	{
		Vector vtReturn = new Vector();

		vtReturn.add(ThreadUtil.createTextParameter("ftpUse", 400, "The billing runing ftp mode, that must put cdr file in remote server"));
		vtReturn.add(ThreadUtil.createTextParameter("ftpAddress", 400, ""));
		vtReturn.add(ThreadUtil.createIntegerParameter("ftpPort", ""));
		vtReturn.add(ThreadUtil.createTextParameter("ftpUser", 400, ""));
		vtReturn.add(ThreadUtil.createTextParameter("ftpPass", 400, ""));
		vtReturn.add(ThreadUtil.createTextParameter("ftpServFolder", 400, ""));
		vtReturn.add(ThreadUtil.createTextParameter("ftpClientFolder", 400, ""));
		vtReturn.add(ThreadUtil.createIntegerParameter("ftpDownloadInterval", ""));
		vtReturn.add(ThreadUtil.createTextParameter("ftpPreFile", 400, ""));
		vtReturn.add(ThreadUtil.createTextParameter("ftpTimetorun", 400, ""));
		vtReturn.addAll(super.getParameterDefinition());
		return vtReturn;
	}

	public void fillDispatcherParameter() throws AppException
	{
		ftpUse = ThreadUtil.getInt(this, "ftpUse", 3000);
		ftpAddress = ThreadUtil.getString(this, "ftpAddress", true, "");
		ftpPort = ThreadUtil.getInt(this, "ftpPort", 3000);
		ftpUser = ThreadUtil.getString(this, "ftpUser", true, "");
		ftpPass = ThreadUtil.getString(this, "ftpPass", true, "");
		ftpServFolder = ThreadUtil.getString(this, "ftpServFolder", true, "");
		ftpClientFolder = ThreadUtil.getString(this, "ftpClientFolder", true, "");
		ftpDownloadInterval = ThreadUtil.getInt(this, "ftpDownloadInterval", 3000);
		ftpPreFile = ThreadUtil.getString(this, "ftpPreFile", true, "");
		ftpTimetorun = ThreadUtil.getString(this, "ftpTimetorun", false, "");
		super.fillDispatcherParameter();
	}

	public void beforeProcessSession() throws Exception
	{
		super.beforeProcessSession();
		try
		{
			startTime = Calendar.getInstance();
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
			debugMonitor("Connect to server success!");
		}
		catch (Exception e)
		{
			debugMonitor(e);
		}

	}

	public void afterProcessSession() throws Exception
	{
		super.beforeProcessSession();
		if (this.ftp.isConnected())
		{
			try
			{
				this.ftp.logout();
				this.ftp.disconnect();
			}
			catch (IOException f)
			{
				debugMonitor(f);
			}
		}
	}

	public void doProcessSession() throws ParseException
	{
		Calendar now = Calendar.getInstance();

		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");

		try
		{
			if (!ftp.isConnected())
			{
				debugMonitor("Reconnect to server!!!");
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
				debugMonitor("Connect to server success!");
			}

			String remoteFile = "";
			String localFile = "";
			int count = 0;
			Calendar timeToRun = Calendar.getInstance();

			if (!ftpTimetorun.equals(""))
			{
				timeToRun.setTime(df.parse(ftpTimetorun));

			}

			timeToRun.add(Calendar.DATE, -1);

			String folder = df.format(timeToRun.getTime());
			remoteFile = ftpServFolder + "/" + folder;
			// r1emoteFile = ftpServFolder + "/20140401";
			localFile = ftpClientFolder + "/" + folder;
			File localF = new File(localFile);
			if (!localF.exists())
			{
				logMonitor("Creating directory: " + localFile);
				if (localF.mkdirs())
				{
					logMonitor("Folder is create!!");
				}
			}
			FTPFile[] files = ftp.listFiles(remoteFile);
			logMonitor("Download file from " + remoteFile);
			if (files.length == 0)
			{
				logMonitor("folder " + remoteFile + " is empty");
			}

			for (FTPFile file : files)
			{
				String filePath = localFile + "/" + file.getName();
				File filePathLocal = new File(filePath);
				if (!filePathLocal.exists())
				{
					if (file.getName().startsWith(ftpPreFile))
					{
						String remoteFile1 = file.getName();
						File downloadFile = new File(localFile + "/" + file.getName());

						OutputStream outputStream1 = new BufferedOutputStream(new FileOutputStream(downloadFile));

						if (!ftp.retrieveFile(remoteFile + "/" + remoteFile1, outputStream1))
						{
							logMonitor("Unable to download file from FTP server : " + file.getName());
							File tmp = null;
							try
							{
								outputStream1.close();
							}
							catch (FTPConnectionClosedException e)
							{
								logMonitor("Connection to FTP server  is closed ");

							}
							catch (Exception e1)
							{
								logMonitor("Unable to delete corrupt file from local directory : " + tmp.getAbsolutePath());
							}
						}
						else
						{
							ftp.deleteFile(remoteFile + "/" +remoteFile1);
							outputStream1.close();
							count++;
							logMonitor("Download file " + file.getName() + " success from " + remoteFile + " to " + localFile);

						}
					}
				}
				else
				{
					logMonitor("File " + file.getName() + " exist in " + localFile);
				}

			}

			logMonitor("Download total " + count + " file!!");

		}
		catch (Exception e)
		{
			logMonitor(e);
		}
		finally
		{
			if (this.ftp.isConnected())
			{
				try
				{
					this.ftp.logout();
					this.ftp.disconnect();
					debugMonitor("Connection closed!!");
				}
				catch (IOException f)
				{
					debugMonitor(f);
				}
			}
		}
	}

}
