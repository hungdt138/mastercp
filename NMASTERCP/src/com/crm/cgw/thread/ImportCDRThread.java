/**
 * 
 */
package com.crm.cgw.thread;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import com.crm.thread.FileEntry;
import com.crm.thread.FileThread;
import com.crm.thread.util.ThreadUtil;
import com.crm.util.StringUtil;
import com.crm.kernel.sql.Database;

import com.fss.util.AppException;

/**
 * @author hungdt
 * 
 */
public class ImportCDRThread extends FileThread
{
	public String						delimiter			= "";
	public String						dateFormat			= "";

	public String						channel				= "";
	public String						isdnPrefix			= "";
	public String						keywordPrefix		= "";
	public String						chargingDescription	= "";

	protected SimpleDateFormat			checkDate			= null;
	protected HashMap<String, String>	merchants			= new HashMap<String, String>();
	protected PreparedStatement			stmtImport			= null;

	public ImportCDRThread() throws Exception
	{
		super();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Vector getDispatcherDefinition()
	{
		Vector vtReturn = new Vector();

		vtReturn.addElement(ThreadUtil.createComboParameter("channel", "web,sms", ""));
		vtReturn.addElement(ThreadUtil.createTextParameter("isdnPrefix", 300, ""));
		vtReturn.addElement(ThreadUtil.createTextParameter("keywordPrefix", 300, ""));
		vtReturn.addElement(ThreadUtil.createTextParameter("chargingDescription", 300, ""));

		vtReturn.addElement(ThreadUtil.createTextParameter("importDir", 300, ""));
		vtReturn.addElement(ThreadUtil.createTextParameter("wildcard", 300, ""));
		vtReturn.addElement(ThreadUtil.createTextParameter("dateFormat", 300, ""));
		vtReturn.addElement(ThreadUtil.createTextParameter("delimiter", 300, ""));
		vtReturn.addElement(ThreadUtil.createIntegerParameter("batchSize", ""));
		vtReturn.addElement(ThreadUtil.createBooleanParameter("batchCommit", "commit at end of batch"));
		
		vtReturn.addElement(ThreadUtil.createTextParameter("exportDir", 300, ""));
		vtReturn.addElement(ThreadUtil.createTextParameter("tempDir", 300, ""));
		vtReturn.addElement(ThreadUtil.createTextParameter("errorDir", 300, ""));
		vtReturn.addElement(ThreadUtil.createTextParameter("rejectDir", 300, ""));
		vtReturn.addElement(ThreadUtil.createTextParameter("backupDir", 300, ""));

		vtReturn.addAll(ThreadUtil.createLogParameter(this));

		return vtReturn;
	}

	public void fillFileParameter() throws Exception
	{
		super.fillFileParameter();

		channel = ThreadUtil.getString(this, "channel", true, "");
		isdnPrefix = ThreadUtil.getString(this, "isdnPrefix", true, "");
		keywordPrefix = ThreadUtil.getString(this, "keywordPrefix", true, "");
		chargingDescription = ThreadUtil.getString(this, "chargingDescription", true, "");

		delimiter = ThreadUtil.getString(this, "delimiter", true, ";");
		dateFormat = ThreadUtil.getString(this, "dateFormat", true, ";");

		checkDate = new SimpleDateFormat(dateFormat);

		batchSize = ThreadUtil.getInt(this, "batchSize", 100);
		batchCommit = ThreadUtil.getBoolean(this, "batchCommit", false);
	}

	public void beforeProcessSession() throws Exception
	{
		//super.beforeProcessSession();

		PreparedStatement stmtMerchant = null;
		ResultSet rsMerchant = null;
		
		try
		{
			mcnMain = Database.getConnection();

			String strSQL = "Insert into ChargingCDR "
					+ "		(requestId, userName, createDate, channel, requestDate, orderDate "
					+ "		, merchantId, serviceAddress, serviceName, isdn "
					+ "		, keyword, contentType, status, description) "
					+ " values "
					+ "		(command_seq.nextval, ?, sysDate, ?, ?, ? "
					+ "		, ?, ?, ?, ?, ?, ?, ?, ?) ";

			stmtImport = mcnMain.prepareStatement(strSQL);
			
			merchants.clear();
			
			stmtMerchant = mcnMain.prepareStatement("Select * From MerchantEntry");
			rsMerchant = stmtMerchant.executeQuery();
			
			while (rsMerchant.next())
			{
				merchants.put(rsMerchant.getString("merchantid"), rsMerchant.getString("title"));
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
	}

	public void afterProcessSession() throws Exception
	{
		try
		{
			mcnMain.commit();
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			Database.closeObject(stmtImport);
			Database.closeObject(mcnMain);

			super.afterProcessSession();
		}
	}

	public void updateBatch() throws Exception
	{
		stmtImport.executeBatch();
		
		if (batchCommit)
		{
			mcnMain.commit();
		}
	}

	public boolean isDate(String value)
	{
		if ((value == null) || value.equals(""))
		{
			return true;
		}

		try
		{
			checkDate.parse(value.trim());
		}
		catch (Exception e)
		{
			return false;
		}

		return true;
	}
	// Get all subs directory.
	public List<String> getSubDirs() throws Exception
	{ 
		List<String> list = new ArrayList<String>();
		list.add("");

		return list;
	}
	
//	public List<FileEntry> getFileList() throws Exception
//	{
//		PreparedStatement stmtMerchant = null;
//		ResultSet rsMerchant = null;
//
//		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddhhmmss");
//
//		List<FileEntry> list = new ArrayList<FileEntry>();
//
//		try
//		{
//			stmtMerchant = mcnMain.prepareStatement("Select * From MerchantEntry");
//			rsMerchant = stmtMerchant.executeQuery();
//
//			while (rsMerchant.next())
//			{
//				FileEntry fileEntry = new FileEntry();
//
//				fileEntry.setMasterId(rsMerchant.getLong("merchantId"));
//				fileEntry.setMasterCode(rsMerchant.getString("alias_"));
//				fileEntry.setMasterTitle(rsMerchant.getString("title"));
//
//				fileEntry.setFileName(rsMerchant.getString("alias_") + wildcard);
//
//				list.add(fileEntry);
//			}
//		}
//		catch (Exception e)
//		{
//			throw e;
//		}
//		finally
//		{
//			Database.closeObject(rsMerchant);
//			Database.closeObject(stmtMerchant);
//		}
//
//		return list;
//	}
	public void processLine(String line) throws Exception
	{
		String[] fields = line.split("\t");

		try
		{
			String merchantId = fields[5].trim();
			String serviceAddress = fields[0].trim();
			String serviceName = fields[7].trim();
			String isdn = fields[1].trim();
			String status = fields[3].trim();
			String contentType = fields[6].trim();

			Date requestDate = null;
			Date orderDate = null;

			if (serviceAddress.equals(""))
			{
				throw new AppException("missing-service-address");
			}
			if (isdn.equals(""))
			{
				throw new AppException("missing-isdn");
			}
			if (merchants.get(merchantId) == null)
			{
				throw new AppException("invalid-merchant-id");
			}
			if (isDate(fields[2].trim()))
			{
				requestDate = checkDate.parse(fields[2].trim());
			}
			else
			{
				throw new AppException("invalid-request-date");
			}
			if (isDate(fields[4].trim()))
			{
				orderDate = checkDate.parse(fields[4].trim());
			}
			else
			{
				throw new AppException("invalid-order-date");
			}

			if (!isdnPrefix.equals(""))
			{
				boolean found = false;
				
				String[] prefix = StringUtil.split(isdnPrefix, ";");

				for (int j = 0; !found & (j < prefix.length); j++)
				{
					if (!prefix[j].equals("") && isdn.startsWith(prefix[j]))
					{
						found = true;
					}
				}
				
				if (!found)
				{
					throw new AppException("invalid-isdn");
				}
			}

			stmtImport.setString(1, "system");
			stmtImport.setString(2, channel);
			stmtImport.setTimestamp(3, (requestDate == null) ? null : new java.sql.Timestamp(requestDate.getTime()));
			stmtImport.setTimestamp(4, (orderDate == null) ? null : new java.sql.Timestamp(orderDate.getTime()));
			stmtImport.setString(5, merchantId);
			stmtImport.setString(6, serviceAddress);
			stmtImport.setString(7, serviceName);
			stmtImport.setString(8, isdn);
			stmtImport.setString(9, keywordPrefix + serviceAddress);
			stmtImport.setString(10, contentType);
			stmtImport.setString(11, status);
			stmtImport.setString(12, chargingDescription);

			stmtImport.addBatch();
		}
		catch (AppException e)
		{
			throw e;
		}
		catch (Exception e)
		{
			throw new AppException("invalid-structure: " + e.getMessage());
		}
	}
}
