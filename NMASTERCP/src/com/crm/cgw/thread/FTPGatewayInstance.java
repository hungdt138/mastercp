/**
 * 
 */
package com.crm.cgw.thread;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Calendar;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPConnectionClosedException;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import com.crm.provisioning.thread.CommandStatisticThread;
import com.crm.thread.DispatcherInstance;

/**
 * @author hungdt
 *
 */
public class FTPGatewayInstance extends DispatcherInstance
{
	public FTPClient	ftp					= null;
	protected Calendar									startTime			= null;


	public FTPGatewayInstance() throws Exception
	{
		super();
	}
	
	public FTPGatewayThread getDispatcher()
	{
		return (FTPGatewayThread) dispatcher;
	}
	
	public void beforeProcessSession() throws Exception {
		try {
			startTime = Calendar.getInstance();
			ftp = new FTPClient();
			ftp.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
			int reply;
			
			ftp.connect(getDispatcher().ftpAddress);
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
			}
			ftp.login(getDispatcher().ftpUser, getDispatcher().ftpPass);
			ftp.setFileType(FTP.BINARY_FILE_TYPE);
			ftp.enterLocalPassiveMode();
			debugMonitor("Connect to server success!");
		}
		catch (Exception e) {
			debugMonitor(e);
		}

	}

	public void afterProcessSession() throws Exception {
		if (this.ftp.isConnected()) {
			try {
				this.ftp.logout();
				this.ftp.disconnect();
			}
			catch (IOException f) {
				debugMonitor(f);
			}
		}
	}
	
	
	
	public void doProcessSession()
	{
		Calendar now = Calendar.getInstance();
		if (now.getTimeInMillis() - startTime.getTimeInMillis() > 1000 * (getDispatcher()).ftpDownloadInterval)
		{
			try
			{
				if(!ftp.isConnected())
				{
					debugMonitor("Reconnect to server!!!");
					ftp = new FTPClient();
					ftp.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
					int reply;
					
					ftp.connect(getDispatcher().ftpAddress);
					reply = ftp.getReplyCode();
					if (!FTPReply.isPositiveCompletion(reply)) {
						ftp.disconnect();
					}
					ftp.login(getDispatcher().ftpUser, getDispatcher().ftpPass);
					ftp.setFileType(FTP.BINARY_FILE_TYPE);
					ftp.enterLocalPassiveMode();
					debugMonitor("Connect to server success!");
				}
					
				String[] cpName = getDispatcher().ftpCPNameDownload.split(getDispatcher().ftpCollumnSeparate);
				String remoteFile = "";
				String localFile = "";
				int count = 0;
				for(String cp : cpName)
				{
					remoteFile = getDispatcher().ftpServFolder + "/" + cp;
					localFile = getDispatcher().ftpClientFolder + "/" + cp + "/";
					File localF = new File(localFile);
					if(!localF.exists())
					{
						logMonitor("Creating directory: " + localFile);
						if(localF.mkdirs())
						{
							logMonitor("Folder is create!!");
						}
					}
					FTPFile[] files = ftp.listFiles(remoteFile);
					logMonitor("Download file from " + cp);
					if(files.length == 0)
					{
							logMonitor("folder " +cp + " is empty");
						
					}
					for(FTPFile file : files)
					{
						if(file.getName().startsWith(getDispatcher().ftpPreFile) && file.getName().contains(getDispatcher().ftpExtFile))
						{   
							String remoteFile1 = file.getName();
							File downloadFile = new File(localFile+file.getName());
						
							OutputStream outputStream1 = new BufferedOutputStream(new FileOutputStream(downloadFile));
							
							if (!ftp.retrieveFile(remoteFile+"/"+remoteFile1, outputStream1)) {
			                    logMonitor("Unable to download file from FTP server : " + file.getName());
			                    File tmp = null;
			                    try {
			                    	outputStream1.close();
			                    } catch (FTPConnectionClosedException e) {
			                        logMonitor("Connection to FTP server  is closed ");
			                      
			                    } catch (Exception e1) {
			                        logMonitor("Unable to delete corrupt file from local directory : " + tmp.getAbsolutePath());
			                    }
							}
							else
							{
								ftp.deleteFile(remoteFile+"/"+remoteFile1);
								outputStream1.close();
								count++;
								logMonitor("Download file " + file.getName() + " success from " + cp);
								
							}
						}
						
					}
					logMonitor("Download total " +count+ " file!!");
				}
				startTime = Calendar.getInstance();
			}
			catch (Exception e)
			{
				logMonitor(e);
			} finally
			{
				if (this.ftp.isConnected()) {
					try {
						this.ftp.logout();
						this.ftp.disconnect();
						debugMonitor("Connection closed!!");
					}
					catch (IOException f) {
						debugMonitor(f);
					}
				}
			}
		}
		
		
	}
}
