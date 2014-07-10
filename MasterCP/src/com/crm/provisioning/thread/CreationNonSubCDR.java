/**
 * 
 */
package com.crm.provisioning.thread;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import org.apache.axiom.om.OMAbstractFactory;

import vms.cn.com.huawei.www.schema.common.v2_1.RequestSOAPHeader;
import vms.cn.com.huawei.www.schema.common.v2_1.RequestSOAPHeaderE;
import vms.org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.NonceGenerator;
import vms.org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.SendSmsServiceStub;
import vms.org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.SendSmsServiceStub.DeliveryInformation;
import vms.org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.SendSmsServiceStub.GetSmsDeliveryStatus;
import vms.org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.SendSmsServiceStub.GetSmsDeliveryStatusE;
import vms.org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.SendSmsServiceStub.GetSmsDeliveryStatusResponseE;

import com.crm.kernel.sql.Database;
import com.crm.subscriber.impl.SubscriberOrderImpl;
import com.crm.thread.DispatcherThread;
import com.crm.thread.util.ThreadUtil;
import com.fss.util.AppException;

/**
 * @author hungdt
 * 
 */
public class CreationNonSubCDR extends DispatcherThread
{

	private PreparedStatement	_stmtCDR		= null;
	private ResultSet			_rSet			= null;

	protected String			fileName		= "";
	protected String			backupDir		= "";
	protected String			serverDir		= "";
	protected String			serverIP		= "";
	protected String			serverUsername	= "";
	protected String			serverPassword	= "";
	protected String			_strSQL			= "";
	protected String			sdpHost			= "";
	protected String			sdpUsername		= "";
	protected String			sdpPassword		= "";
	protected Connection		connection		= null;
	private final static String	COMMA_SYMBOL	= ",";
	protected String			timetorun		= "";

	/**
	 * 
	 * Get parameters from ThreadManager.cfg file. Unchecked warning message for
	 * the method.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Vector getParameterDefinition()
	{

		Vector vtReturn = new Vector();
		vtReturn.addElement(ThreadUtil.createTextParameter("SQL", 100, ""));
		vtReturn.addElement(ThreadUtil.createTextParameter("fileName", 100, ""));
		vtReturn.addElement(ThreadUtil
				.createTextParameter("fileServerDir", 100, ""));
		vtReturn.addElement(ThreadUtil
				.createTextParameter("backupDir", 100, ""));
		vtReturn.addElement(ThreadUtil
				.createTextParameter("fileServerIP", 100, ""));
		vtReturn.addElement(ThreadUtil
				.createTextParameter("sdpHost", 100, ""));
		vtReturn.addElement(ThreadUtil
				.createTextParameter("sdpUsername", 100, ""));
		vtReturn.addElement(ThreadUtil
				.createTextParameter("sdpPassword", 100, ""));
		vtReturn.addElement(ThreadUtil
				.createTextParameter("timetorun", 100, ""));

		vtReturn.addAll(super.getParameterDefinition());

		return vtReturn;
	}

	/*
	 * Update value for parameters.
	 * 
	 * @see com.crm.thread.DispatcherThread#fillParameter()
	 */
	@Override
	public void fillParameter() throws AppException
	{
		super.fillParameter();

		_strSQL = ThreadUtil.getString(this, "SQL", false, "");
		fileName = ThreadUtil.getString(this, "fileName", false, "");
		serverDir = ThreadUtil.getString(this, "fileServerDir", false, "");
		backupDir = ThreadUtil.getString(this, "backupDir", false, "");
		serverIP = ThreadUtil.getString(this, "fileServerIP", false, "");
		sdpHost = ThreadUtil.getString(this, "sdpHost", false, "");
		sdpUsername = ThreadUtil.getString(this, "sdpUsername", false, "");
		sdpPassword = ThreadUtil.getString(this, "sdpPassword", false, "");
		timetorun = ThreadUtil.getString(this, "timetorun", false, "");
	}

	/*
	 * Get DB connection.
	 * 
	 * @see com.crm.thread.DispatcherThread#beforeProcessSession()
	 */
	@Override
	public void beforeProcessSession() throws Exception
	{
		super.beforeProcessSession();
		try
		{
			connection = Database.getConnection();
			_stmtCDR = connection.prepareStatement(_strSQL);
		}
		catch (Exception e)
		{
			throw e;
		}
	}

	/*
	 * Close DB connection.
	 * 
	 * @see com.crm.thread.DispatcherThread#afterProcessSession()
	 */
	@Override
	public void afterProcessSession() throws Exception
	{
		try
		{

			Database.closeObject(_stmtCDR);

			Database.closeObject(_rSet);

			Database.closeObject(connection);
		}
		finally
		{
			super.afterProcessSession();
		}
	}

	/*
	 * Create CDR file.
	 * 
	 * @see com.crm.thread.DispatcherThread#doProcessSession()
	 */
	@Override
	public void doProcessSession() throws Exception
	{

		try
		{

			Calendar cal = Calendar.getInstance();

			if (!timetorun.equals(""))
			{
				cal.setTime(new SimpleDateFormat("dd/MM/yyyy").parse(timetorun));
			}

			cal.add(Calendar.DATE, -1);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm:ss");

			String exportingFile = serverDir;
			String backupFile = backupDir;
			String fullSysTime = sdf.format(cal.getTime());
			String strFileName = fileName.replaceAll("%t%", fullSysTime);
			exportingFile = exportingFile + strFileName;
			backupFile = backupFile + strFileName;

			_stmtCDR.setString(1, new SimpleDateFormat("dd/MM/yyyy").format(cal.getTime()));
			_stmtCDR.setString(2, new SimpleDateFormat("dd/MM/yyyy").format(cal.getTime()));

			_rSet = _stmtCDR.executeQuery();

			// Get sysDate.
			String sysDate = "";

			// Get sysTime.
			String sysTime = "";

			// Gate way.
			String gateway_name = "Acom";

			// Service No
			String serviceNo = "8626";

			List<String> listDelivery = new ArrayList<String>();
			List<Long> listOrderIdInserted = new ArrayList<Long>();

			while (_rSet.next())
			{
				Long orderId = _rSet.getLong("orderId");
				// String orderNo = _rSet.getString("orderNo");
				String isdn = _rSet.getString("isdn");
				String description = _rSet.getString("orderType");
				String productId = _rSet.getString("code");
				String cause = _rSet.getString("cause");
				Date orderdate = _rSet.getTimestamp("orderDate");
				int opId = _rSet.getInt("telcoid");

				sysDate = sdfDate.format(orderdate);
				sysTime = sdfTime.format(orderdate);

				String status = "";
				if (cause.equals("success"))
				{
					status = "0";
				}

				logMonitor("Get status: isdn=" + isdn + ",productId=" + productId + ",status=" + status);

				// End

				String resultLine = formatResultLine(String.valueOf(orderId), isdn, serviceNo, sysDate, sysTime, description, gateway_name,
						productId, status, opId);
				listDelivery.add(resultLine);
				listOrderIdInserted.add(orderId);
			}

			// Executing create CDR file.
			createCDRFile(strFileName, listDelivery, listOrderIdInserted);

//			if (cal.getTime().before(Calendar.getInstance().getTime()))
//			{
//				Calendar timeToRun1 = Calendar.getInstance();
//				timeToRun1.setTime(new SimpleDateFormat("dd/MM/yyyy").parse(timetorun));
//				timeToRun1.add(Calendar.DATE, 1);
//				timetorun = new SimpleDateFormat("dd/MM/yyyy").format(timeToRun1.getTime());
//				mprtParam.setProperty("timetorun", timetorun);
//				storeConfig();
//			}

		}
		catch (SQLException e)
		{
			logMonitor("SQL invalid:" + e);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{

			_rSet.close();
		}
	}

	/**
	 * Get data to create CDR file, then update Delivery_status = 1.
	 * 
	 * @param strFileName
	 * @param listDelivery
	 */
	private synchronized void createCDRFile(final String strFileName,
			List<String> listDelivery, List<Long> listOrderIdInserted)
	{
		if (listDelivery.size() == 0)
		{
			logMonitor("All data was exported into the lastest CDR file.");
			return;
		}
		FileOutputStream fos = null;
		File cdrFile = null;
		String dataToWriteIntoCDRFile = "";

		// Crearte directory to store CDR file.
		File cdrDir = new File(serverDir);
		File cdrBackupDir = new File(backupDir);
		if (!cdrDir.exists())
		{
			cdrDir.mkdirs();
		}
		if (!cdrBackupDir.exists())
		{
			cdrBackupDir.mkdirs();
		}

		try
		{
			String filePath = serverDir + "/" + strFileName;
			String filePathBk = backupDir + "/" + strFileName;
			// String filePath = "C:/" + strFileName;
			// String filePathBk = "C:/A/" +"/" + strFileName;
			cdrFile = new File(filePath);
			fos = new FileOutputStream(cdrFile);
			for (String aLineInCDRFile : listDelivery)
			{
				dataToWriteIntoCDRFile += "\n" + aLineInCDRFile;
				dataToWriteIntoCDRFile = dataToWriteIntoCDRFile.trim();
			}
			byte[] contentinBytes = dataToWriteIntoCDRFile.getBytes();
			fos.write(contentinBytes);
			fos.flush();
			fos.close();

			// Backup file created.
			if (backupCDRFile(filePath, filePathBk) == true)
			{
				logMonitor("Creating CDR file " + strFileName + " completely. " + "Located: " + serverDir);
			}
			else
			{
				logMonitor("Creating CDR file " + strFileName + " completely. " + "Backup file ERROR.");
			}

		}
		catch (Exception e)
		{
			logMonitor("Creating CDR file false. Cause: " + e);
		}
		finally
		{
			// Update export status.
			try
			{
				for (Long orderId : listOrderIdInserted)
				{
					SubscriberOrderImpl.updateExportStatus(0, orderId);
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}

	}

	/**
	 * Create backup file.
	 * 
	 * @param filePath
	 *            String.
	 */
	private synchronized boolean backupCDRFile(String filePath,
			String filePathBk)
	{
		InputStream is = null;
		OutputStream os = null;
		try
		{
			File cdrFile = new File(filePath);
			File cdrFileBk = new File(filePathBk);

			is = new FileInputStream(cdrFile);
			os = new FileOutputStream(cdrFileBk);

			byte[] buffer = new byte[1024];
			int length;

			// copy the file content in bytes
			while ((length = is.read(buffer)) > 0)
			{
				os.write(buffer, 0, length);
			}
			os.flush();
			os.close();
			is.close();
			return true;

		}
		catch (FileNotFoundException e)
		{
			logMonitor("File not found. " + e);
		}
		catch (Exception e)
		{
			logMonitor("Error: " + e);
		}
		return false;

	}

	/**
	 * Data format: OrderNo, isdn, serviceNo(8926), sysDate, sysTime,
	 * description, gateway_name(Acom), productId, result.
	 */
	private String formatResultLine(final String orderNo, final String isdn,
			final String serviceNo, final String sysDate, final String sysTime,
			final String description, final String gateway_name,
			final String productId, final String result, final int opId)
	{

		return orderNo + COMMA_SYMBOL + isdn + COMMA_SYMBOL + serviceNo + COMMA_SYMBOL + sysDate + COMMA_SYMBOL + sysTime + COMMA_SYMBOL
				+ description + COMMA_SYMBOL + gateway_name + COMMA_SYMBOL + opId + COMMA_SYMBOL + productId + COMMA_SYMBOL + result;
	}

	/**
	 * 
	 * hungdt
	 * 
	 * @param request
	 * @return
	 */
	public static RequestSOAPHeaderE createHeader(String serviceId, String username, String strPassword, String isdn)
	{
		RequestSOAPHeaderE requestHeaderE = new RequestSOAPHeaderE();
		RequestSOAPHeader requestHeader = new RequestSOAPHeader();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		String created = sdf.format(Calendar.getInstance().getTime());

		String password = NonceGenerator.getInstance()
				.getNonce(username + strPassword + created);
		requestHeader.setSpId(username);
		requestHeader.setSpPassword(password);
		requestHeader.setServiceId(serviceId);
		requestHeader.setTimeStamp(created);
		requestHeader.setOA(isdn);
		requestHeader.setFA(isdn);
		requestHeaderE.setRequestSOAPHeader(requestHeader);

		return requestHeaderE;
	}

	/**
	 * 
	 * hungdt
	 * 
	 * @param reqIndentifier
	 * @return
	 * @throws Exception
	 */
	public static GetSmsDeliveryStatusE createBody(String reqIndentifier)
			throws Exception
	{
		GetSmsDeliveryStatusE getSmsDeliveryStatusRequset = null;
		try
		{
			getSmsDeliveryStatusRequset = new GetSmsDeliveryStatusE();
			GetSmsDeliveryStatus request = new GetSmsDeliveryStatus();
			request.setRequestIdentifier(reqIndentifier);
			getSmsDeliveryStatusRequset.setGetSmsDeliveryStatus(request);
		}
		catch (Exception e)
		{
			throw e;
		}

		return getSmsDeliveryStatusRequset;
	}

	/**
	 * 
	 * hungdt
	 * 
	 * @param header
	 * @param body
	 * @param endpointVMS
	 * @return
	 * @throws Exception
	 */
	public static GetSmsDeliveryStatusResponseE getDeliverySMS(
			RequestSOAPHeaderE header, GetSmsDeliveryStatusE body, String host) throws Exception
	{
		GetSmsDeliveryStatusResponseE response = null;
		try
		{
			SendSmsServiceStub stub = new SendSmsServiceStub(host);
			stub._getServiceClient()
					.addHeader(header.getOMElement(RequestSOAPHeaderE.MY_QNAME, OMAbstractFactory
							.getSOAP11Factory()));
			response = stub.getSmsDeliveryStatus(body);
		}
		catch (Exception e)
		{
			throw e;
		}
		return response;
	}

	public static int getDeliveryCDR(String isdn, long productId, String timestamp)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		int status = 0;
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try
		{
			String sql = "select * from telcocdr where isdn = ? and productId = ? and thirdparty = ? and timestamp like ?";
			connection = Database.getConnection();
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, isdn);
			stmt.setString(2, String.valueOf(productId));
			stmt.setString(3, isdn);
			stmt.setString(4, timestamp + "%");

			rs = stmt.executeQuery();
			if (rs.next())
			{

			}
		}
		catch (Exception e)
		{
			// TODO: handle exception
		}

		return status;
	}
}
