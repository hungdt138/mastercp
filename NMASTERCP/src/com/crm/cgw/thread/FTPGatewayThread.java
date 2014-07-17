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
import java.util.Vector;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import com.crm.thread.DispatcherThread;
import com.crm.thread.util.ThreadUtil;
import com.fss.util.AppException;

/**
 * @author hungdt
 * 
 */
public class FTPGatewayThread extends DispatcherThread
{
	public int		ftpUse				= 0;
	public String	ftpAddress			= "";
	public int		ftpPort				= 0;
	public String	ftpUser				= "";
	public String	ftpPass				= "";
	public String	ftpServFolder		= "";
	public String	ftpClientFolder		= "";
	public String	ftpCPNameDownload	= "";
	public int		ftpDownloadInterval	= 0;
	public String	ftpPreFile			= "charggw";
	public String	ftpExtFile			= "";
	public String	ftpCollumnSeparate	= ",";

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
		vtReturn.add(ThreadUtil.createTextParameter("ftpCPNameDownload", 400, ""));
		vtReturn.add(ThreadUtil.createIntegerParameter("ftpDownloadInterval", ""));
		vtReturn.add(ThreadUtil.createTextParameter("ftpPreFile", 400, ""));
		vtReturn.add(ThreadUtil.createTextParameter("ftpExtFile", 400, ""));
		vtReturn.add(ThreadUtil.createTextParameter("ftpCollumnSeparate", 400, ""));

		vtReturn.addAll(super.getParameterDefinition());
		return vtReturn;
	}

	public void fillDispatcherParameter() throws Exception
	{
		ftpUse = ThreadUtil.getInt(this, "ftpUse", 3000);
		ftpAddress = ThreadUtil.getString(this, "ftpAddress", true, "");
		ftpPort = ThreadUtil.getInt(this, "ftpPort", 3000);
		ftpUser = ThreadUtil.getString(this, "ftpUser", true, "");
		ftpPass = ThreadUtil.getString(this, "ftpPass", true, "");
		ftpServFolder = ThreadUtil.getString(this, "ftpServFolder", true, "");
		ftpClientFolder =
				ThreadUtil.getString(this, "ftpClientFolder", true, "");
		ftpCPNameDownload =
				ThreadUtil.getString(this, "ftpCPNameDownload", true, "");
		ftpDownloadInterval =
				ThreadUtil.getInt(this, "ftpDownloadInterval", 3000);
		ftpPreFile = ThreadUtil.getString(this, "ftpPreFile", true, "");
		ftpExtFile = ThreadUtil.getString(this, "ftpExtFile", true, "");
		ftpCollumnSeparate =
				ThreadUtil.getString(this, "ftpCollumnSeparate", true, "");
		super.fillDispatcherParameter();
	}

}
