/**
 * 
 */
package com.crm.provisioning.thread;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import com.crm.kernel.message.Constants;
import com.crm.kernel.sql.Database;
import com.crm.subscriber.impl.SubscriberOrderImpl;
import com.crm.thread.DispatcherThread;
import com.crm.thread.util.ThreadUtil;
import com.crm.util.DateUtil;
import com.fss.util.AppException;

/**
 * @author hungdt
 *
 */
public class ExportCdrThread extends DispatcherThread
{
	private PreparedStatement	_stmtCDR	= null;
	private ResultSet			_rSet		= null;
	
	protected String			fileName		= "";
	protected String			backupDir		= "";
	protected String			serverDir		= "";
	protected String			serverIP		= "";
	protected String			serverUsername	= "";
	protected String			serverPassword	= "";
	protected String 			_strSQL 		= "";
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Vector getParameterDefinition()
	{
		Vector vtReturn = new Vector();
		vtReturn.addElement(ThreadUtil.createTextParameter("SQL", 100, ""));
		vtReturn.addElement(ThreadUtil.createTextParameter("fileName", 100, ""));
		vtReturn.addElement(ThreadUtil.createTextParameter("backupDir", 100, ""));
		vtReturn.addElement(ThreadUtil.createTextParameter("fileServerDir", 100, ""));
		vtReturn.addElement(ThreadUtil.createTextParameter("fileServerIP", 100, ""));
		vtReturn.addElement(ThreadUtil.createTextParameter("fileServerUsername", 100, ""));
		vtReturn.addElement(ThreadUtil.createTextParameter("fileServerPassword", 100, ""));
		vtReturn.addElement(ThreadUtil.createTextParameter("mailContent", 1000, ""));
		
		vtReturn.addAll(super.getParameterDefinition());
		
		return vtReturn;
	}
	
	public void fillParameter() throws AppException
	{
		super.fillParameter();

		_strSQL = ThreadUtil.getString(this, "SQL", false, "");
		fileName = ThreadUtil.getString(this, "fileName", false, "");
		backupDir = ThreadUtil.getString(this, "backupDir", false, "");
		serverDir = ThreadUtil.getString(this, "fileServerDir", false, "");
		serverIP = ThreadUtil.getString(this, "fileServerIP", false, "");
		serverUsername = ThreadUtil.getString(this, "fileServerUsername", false, "");
		serverPassword = ThreadUtil.getString(this, "fileServerPassword", false, "");
	}
	
	public void beforeProcessSession() throws Exception
	{
		super.beforeProcessSession();
		try
		{
			_stmtCDR = getConnection().prepareStatement(_strSQL);
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
			Database.closeObject(_stmtCDR);
			super.afterProcessSession();
		}
		finally
		{
			super.afterProcessSession();
		}
	}
	
	public void doProcessSession() throws Exception
	{
		long orderId = 0;
		
		try
		{
			
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
			
			String exportingFile = serverDir;
			String backupFile	= backupDir;
			String strFileName = fileName.replaceAll("%t%", sdf.format(cal.getTime()));
			if (!exportingFile.endsWith("\\") && !exportingFile.endsWith("/"))
				exportingFile = exportingFile + "/";
			
			exportingFile = exportingFile + strFileName;
			backupFile = backupFile + strFileName;
			
			
			_rSet = _stmtCDR.executeQuery();
			
			StringBuffer strChargeInfo = new StringBuffer();
			while (_rSet.next())
			{
				orderId = _rSet.getLong(1);
				
				strChargeInfo.append(_rSet.getString(2) + "\n");
				
				SubscriberOrderImpl.updateExportStatus(Constants.EXPORT_STATUS_SUCCESS, orderId);
			}
			
			if(strChargeInfo.length() > 0)
			{
				
				File file = new File(exportingFile);
				file.setExecutable(true);
				file.setReadable(true);
				file.setWritable(true);
				BufferedWriter out = new BufferedWriter(new FileWriter(file));
				out.write(strChargeInfo.toString());
				out.close();
				//backup file
				File file1 = new File(backupFile);
				BufferedWriter out1 = new BufferedWriter(new FileWriter(file1));
				file.setExecutable(true);
				file.setReadable(true);
				file.setWritable(true);
				out1.write(strChargeInfo.toString());
				out1.close();
				
				logMonitor("CREATE FILE " + strFileName + " DONE.");
			}
			else
			{
				logMonitor("NO DATA EXPORT.");
			}
			
		}
		catch (Exception e)
		{
			SubscriberOrderImpl.updateExportStatus(Constants.EXPORT_STATUS_ERROR, orderId);
			e.printStackTrace();
		}
		finally
		{
			_rSet.close();
		}
	}
	
	
	
}
