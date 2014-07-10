/**
 * 
 */
package com.crm.provisioning.thread;

import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Vector;

import org.apache.log4j.Logger;

import jxl.CellView;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Formula;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import com.crm.kernel.message.Constants;
import com.crm.kernel.queue.QueueFactory;
import com.crm.provisioning.cache.CommandEntry;
import com.crm.provisioning.cache.MQConnection;
import com.crm.provisioning.cache.ProvisioningFactory;
import com.crm.provisioning.message.CommandMessage;
import com.crm.provisioning.util.ResponseUtil;
import com.crm.thread.MailThread;
import com.crm.thread.util.ThreadUtil;
import com.crm.util.DateUtil;
import com.crm.util.StringPool;
import com.fss.sql.Database;
import com.fss.thread.ParameterType;
import com.fss.util.AppException;

/**
 * @author hungdt
 * 
 */
public class SendMailReportCS extends MailThread
{
	protected String			folderPath;
	protected String			SQLHourly		= "";
	protected String			SQLDaily		= "";
	protected String			_timerun		= "";

	protected String			lastUpdateTime	= null;
	private String				isdnList		= "";

	protected PreparedStatement	_stmtHourly		= null;
	protected PreparedStatement	_stmtDaily		= null;

	private WritableCellFormat	captionReport;
	private WritableCellFormat	captionReport1;
	private WritableCellFormat	captionReport2;
	private WritableCellFormat	times;
	{

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Vector getParameterDefinition()
	{
		Vector vtReturn = new Vector();

		vtReturn.addElement(createParameterDefinition("FolderPath", "", ParameterType.PARAM_TEXTAREA_MAX, "100", ""));
		vtReturn.addElement(createParameterDefinition("SQLHourly", "", ParameterType.PARAM_TEXTAREA_MAX, "100", ""));
		vtReturn.addElement(createParameterDefinition("SQLDaily", "", ParameterType.PARAM_TEXTAREA_MAX, "100", ""));
		vtReturn.addElement(createParameterDefinition("TimeRun", "", ParameterType.PARAM_TEXTBOX_MAX, "100"));
		vtReturn.addElement(createParameterDefinition("LastUpdateTime", "", ParameterType.PARAM_TEXTBOX_MAX, "100"));
		vtReturn.add(ThreadUtil
				.createTextParameter("isdnList", 400, "List of destination isdn."));

		vtReturn.addAll(super.getParameterDefinition());
		return vtReturn;
	}

	// //////////////////////////////////////////////////////
	// Override
	// //////////////////////////////////////////////////////
	public void fillParameter() throws AppException
	{
		try
		{
			super.fillParameter();

			// Fill parameter
			folderPath = loadMandatory("FolderPath");
			SQLHourly = loadMandatory("SQLHourly");
			SQLDaily = loadMandatory("SQLDaily");
			lastUpdateTime = loadMandatory("LastUpdateTime");
			_timerun = loadMandatory("TimeRun");
			setIsdnList(ThreadUtil.getString(this, "isdnList", false, ""));
		}
		catch (AppException e)
		{
			throw e;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void beforeProcessSession() throws Exception
	{
		super.beforeProcessSession();

		try
		{
			String strSQL = SQLHourly;
			_stmtHourly = getConnection().prepareStatement(strSQL);

			strSQL = SQLDaily;
			_stmtDaily = getConnection().prepareStatement(strSQL);

		}
		catch (Exception e)
		{
			throw e;
		}
	}

	// //////////////////////////////////////////////////////
	// after process session
	// Author : ThangPV
	// Created Date : 16/09/2004
	// //////////////////////////////////////////////////////
	public void afterProcessSession() throws Exception
	{
		try
		{
			Database.closeObject(_stmtHourly);
			Database.closeObject(_stmtDaily);
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			super.afterProcessSession();
		}
	}

	public void doProcessSession() throws Exception
	{
		try
		{
			Date cur = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			SimpleDateFormat sdfHourly = new SimpleDateFormat("yyyyMMddHHmmss");
			SimpleDateFormat sdfDaily = new SimpleDateFormat("yyyyMMdd");
			Calendar timeRun = Calendar.getInstance();
			timeRun.setTime(sdf.parse(getTimeRun()));

			Calendar lastUpdate = Calendar.getInstance();
			lastUpdate.setTime(sdf.parse(getLastUpdateTime()));

			Calendar now = Calendar.getInstance();

			Calendar toTime = Calendar.getInstance();
			String hourlyPath = "";
			String dailyPath = "";
			if (!cur.before(timeRun.getTime()))
			{
				if (now.getTimeInMillis() - lastUpdate.getTimeInMillis() >= 1000 * 60 * 60)
				{
					File file = new File(folderPath);
					if (!file.exists())
					{
						file.mkdirs();
					}

					if (folderPath.endsWith("/"))
					{
						hourlyPath = folderPath + "CSReport" + sdfHourly
								.format(lastUpdate.getTime()) + ".xls";
					}
					else
					{
						hourlyPath = folderPath + "/CSReport" + sdfHourly
								.format(lastUpdate.getTime()) + ".xls";
					}
					toTime.setTimeInMillis(lastUpdate.getTimeInMillis() + 1000 * 60 * 60);
					writeExcelFile(hourlyPath, "Hourly Report", "Content Subscription Hourly Report", "", _stmtHourly, sdf
							.format(toTime.getTime()));

					// Send mail
					DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

					String strFileName = hourlyPath;
					String content = "Content subscription hourly report Vietnamobile.";
					sendEmail(getSubject().replace("<title>", "hourly") + " " + df.format(lastUpdate
							.getTime()), content, strFileName);

					setLastUpdateTime(sdf.format(toTime.getTime()));
					mprtParam
							.setProperty("LastUpdateTime", getLastUpdateTime());
					storeConfig();
				}

				if (now.getTimeInMillis() - timeRun.getTimeInMillis() >= 1000 * 60 * 60 * 24)
				{
					File file = new File(folderPath);
					if (!file.exists())
					{
						file.mkdirs();
					}

					if (folderPath.endsWith("/"))
					{
						dailyPath = folderPath + "CSReport" + sdfDaily
								.format(timeRun.getTime()) + ".xls";
					}
					else
					{
						dailyPath = folderPath + "/CSReport" + sdfDaily
								.format(timeRun.getTime()) + ".xls";
					}
					toTime.setTimeInMillis(timeRun.getTimeInMillis() + 1000 * 60 * 60 * 24);
					writeExcelFileDaily(dailyPath, "Daily Report", "Content Subscription Daily Report", "", _stmtDaily, sdf
							.format(toTime.getTime()));

					// Send mail
					DateFormat df = new SimpleDateFormat("dd-MM-yyyy");

					String strFileName = dailyPath;
					String content = "Content subscription daily report Vietnamobile.";
					sendEmail(getSubject().replace("<title>", "daily") + " " + df.format(timeRun.getTime()), content, strFileName);

					setTimeRun(sdf.format(toTime.getTime()));
					mprtParam.setProperty("TimeRun", getTimeRun());
					storeConfig();
				}
			}
			else
			{
				debugMonitor("Not time to run...");
			}

		}
		catch (Exception e)
		{
			debugMonitor("Error: " + e.toString());
			throw e;
		}
	}

	public void writeExcelFile(String outputFile, String sheetName,
			String strHeader, String strSubHeader, PreparedStatement obj,
			String toTime) throws IOException, WriteException
	{
		try
		{
			File file = new File(outputFile);
			WorkbookSettings wbSettings = new WorkbookSettings();

			wbSettings.setLocale(new Locale("en", "EN"));

			WritableWorkbook workbook = Workbook
					.createWorkbook(file, wbSettings);
			workbook.createSheet(sheetName, 0);
			WritableSheet excelSheet = workbook.getSheet(0);
			createLabel(excelSheet, strHeader);
			createContent(excelSheet, 5, obj, toTime);

			workbook.write();
			workbook.close();
		}
		catch (Exception ex)
		{
			debugMonitor("Error create file hourly:" + ex.getMessage());
			_logger.error("SendReportCS:" + ex.getMessage());
		}
	}

	public void writeExcelFileDaily(String outputFile, String sheetName,
			String strHeader, String strSubHeader, PreparedStatement obj,
			String toTime) throws IOException, WriteException
	{
		try
		{
			File file = new File(outputFile);
			WorkbookSettings wbSettings = new WorkbookSettings();

			wbSettings.setLocale(new Locale("en", "EN"));

			WritableWorkbook workbook = Workbook
					.createWorkbook(file, wbSettings);
			workbook.createSheet(sheetName, 0);
			WritableSheet excelSheet = workbook.getSheet(0);
			createLabel(excelSheet, strHeader);
			createContentDaily(excelSheet, 5, obj, toTime);

			workbook.write();
			workbook.close();
		}
		catch (Exception ex)
		{
			debugMonitor("Error create file daily:" + ex.getMessage());
			_logger.error("SendReportCS:" + ex.getMessage());
		}
	}

	private void createLabel(WritableSheet sheet, String strSubHeader)
			throws WriteException
	{
		// Lets create a times font
		WritableFont times11pt = new WritableFont(WritableFont.TAHOMA, 10, WritableFont.BOLD);
		// Define the cell format
		times = new WritableCellFormat(times11pt);
		// Lets automatically wrap the cells

		// Create create a bold font with unterlines
		WritableFont times11ptBoldUnderline = new WritableFont(WritableFont.TAHOMA, 11, WritableFont.BOLD, false);
		captionReport = new WritableCellFormat(times11ptBoldUnderline);
		// Lets automatically wrap the cells
		captionReport.setWrap(true);
		captionReport.setVerticalAlignment(VerticalAlignment.CENTRE);
		captionReport.setAlignment(Alignment.CENTRE);

		// create
		WritableFont times11ptCenter = new WritableFont(WritableFont.TAHOMA, 10, WritableFont.BOLD, false);
		captionReport1 = new WritableCellFormat(times11ptCenter);
		// Lets automatically wrap the cells
		captionReport1.setWrap(true);
		captionReport1.setVerticalAlignment(VerticalAlignment.CENTRE);
		captionReport1.setAlignment(Alignment.CENTRE);
		captionReport1.setBorder(Border.ALL, BorderLineStyle.DASHED);

		// create
		WritableFont times11pt1 = new WritableFont(WritableFont.TAHOMA, 10);
		captionReport2 = new WritableCellFormat(times11pt1);
		// Lets automatically wrap the cells
		captionReport2.setWrap(true);
		captionReport2.setVerticalAlignment(VerticalAlignment.CENTRE);
		captionReport2.setAlignment(Alignment.CENTRE);
		captionReport2.setBorder(Border.ALL, BorderLineStyle.DASHED);

		CellView cv = new CellView();
		cv.setFormat(times);
		cv.setFormat(captionReport);
		cv.setFormat(captionReport1);
		cv.setFormat(captionReport2);
		cv.setAutosize(true);

		// Write a few headers
		Label label1 = new Label(0, 0, strSubHeader, captionReport);
		sheet.addCell(label1);
		sheet.mergeCells(0, 0, 7, 0);

		addCaption(sheet, 2, 1, "From", times);
		addCaption(sheet, 4, 1, "To", times);

		Label label4 = new Label(0, 3, "No.", captionReport1);
		sheet.addCell(label4);
		sheet.mergeCells(0, 3, 0, 4);

		Label label5 = new Label(1, 3, "CP", captionReport1);
		sheet.addCell(label5);
		sheet.mergeCells(1, 3, 1, 4);

		Label label6 = new Label(2, 3, "MO", captionReport1);
		sheet.addCell(label6);
		sheet.mergeCells(2, 3, 4, 3);

		Label label7 = new Label(5, 3, "MT", captionReport1);
		sheet.addCell(label7);
		sheet.mergeCells(5, 3, 7, 3);

		addCaption(sheet, 2, 4, "Sum", captionReport1);
		addCaption(sheet, 3, 4, "Success", captionReport1);
		addCaption(sheet, 4, 4, "Failed", captionReport1);
		
		addCaption(sheet, 5, 4, "Sum", captionReport1);
		addCaption(sheet, 6, 4, "Success", captionReport1);
		addCaption(sheet, 7, 4, "Failed", captionReport1);

	}

	private void createContent(WritableSheet sheet, int StartRow,
			PreparedStatement obj, String toTime) throws WriteException,
			RowsExceededException
	{
		int counter = 1;
		int row = StartRow;
		ResultSet result = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		try
		{
			addLabel1(sheet, 2, 2, getLastUpdateTime());
			addLabel1(sheet, 4, 2, toTime);

			obj.setString(1, getLastUpdateTime());
			obj.setString(2, toTime);
			result = obj.executeQuery();
			while (result.next())
			{
				addNumber(sheet, 0, row, counter);

				addNumber(sheet, 1, row, result.getInt("merchantId"));
				Formula sumMo = new Formula(2, row, "SUM(D" + (row + 1) + ":E" + (row + 1) + ")", captionReport2);
				sheet.addCell(sumMo);
				addNumber(sheet, 3, row, result.getInt("totalmosuccess"));

				addNumber(sheet, 4, row, result.getInt("totalmofailure"));
				Formula sumMt = new Formula(5, row, "SUM(G" + (row + 1) + ":H" + (row + 1) + ")", captionReport2);
				sheet.addCell(sumMt);
				addNumber(sheet, 6, row, result.getInt("totalmtsuccess"));
				addNumber(sheet, 7, row, result.getInt("totalmtfailure"));

				counter++;
				row++;
			}
			if (counter > 1)
			{
				getConnection().commit();
			}

		}
		catch (Exception ex)
		{
			debugMonitor("Error create file hourly:" + ex.getMessage());
			_logger.error("SendReportCS: " + ex.getMessage());
		}
		finally
		{

			try
			{
				result.close();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
	}

	private void createContentDaily(WritableSheet sheet, int StartRow,
			PreparedStatement obj, String toTime) throws WriteException,
			RowsExceededException
	{
		int counter = 1;
		int row = StartRow;
		ResultSet result = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

		try
		{
			addLabel1(sheet, 2, 2, getTimeRun());
			addLabel1(sheet, 4, 2, toTime);

			obj.setDate(1, DateUtil.getDateSQL(sdf.parse(getTimeRun())));
			result = obj.executeQuery();
			while (result.next())
			{
				addNumber(sheet, 0, row, counter);

				addNumber(sheet, 1, row, result.getInt("merchantId"));
				Formula sumMo = new Formula(2, row, "SUM(D" + (row + 1) + ":E" + (row + 1) + ")", captionReport2);
				sheet.addCell(sumMo);
				addNumber(sheet, 3, row, result.getInt("totalmosuccess"));

				addNumber(sheet, 4, row, result.getInt("totalmofailure"));
				Formula sumMt = new Formula(5, row, "SUM(G" + (row + 1) + ":H" + (row + 1) + ")", captionReport2);
				sheet.addCell(sumMt);
				addNumber(sheet, 6, row, result.getInt("totalmtsuccess"));
				addNumber(sheet, 7, row, result.getInt("totalmtfailure"));

				counter++;
				row++;
			}
			if (counter > 1)
			{
				getConnection().commit();
			}

		}
		catch (Exception ex)
		{
			debugMonitor("Error create file daily:" + ex.getMessage());
			_logger.error("SendReportCS: " + ex.getMessage());
		}
		finally
		{

			try
			{
				result.close();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
	}

	private void addCaption(WritableSheet sheet, int column, int row, String s,
			WritableCellFormat format) throws RowsExceededException,
			WriteException
	{
		Label label;
		label = new Label(column, row, s, format);
		sheet.addCell(label);
	}

	private void addNumber(WritableSheet sheet, int column, int row,
			Integer integer) throws WriteException, RowsExceededException
	{
		Number number;
		number = new Number(column, row, integer, captionReport2);
		sheet.addCell(number);
	}

	private void addLabel(WritableSheet sheet, int column, int row, String s)
			throws WriteException, RowsExceededException
	{
		Label label;
		label = new Label(column, row, s, captionReport2);
		sheet.addCell(label);
	}

	private void addLabel1(WritableSheet sheet, int column, int row, String s)
			throws WriteException, RowsExceededException
	{
		Label label;
		label = new Label(column, row, s);
		sheet.addCell(label);
		sheet.mergeCells(column, row, column + 1, row);
	}

	protected void sendReportSMS(String content) throws Exception
	{
		String[] isdns = getIsdnList().split(StringPool.COMMA);
		String sentAlarmIsdn = "";
		try
		{
			CommandEntry command = ProvisioningFactory.getCache()
					.getCommand(Constants.COMMAND_SEND_SMS);

			for (String isdn : isdns)
			{
				if (isdn.equals(""))
				{
					continue;
				}
				sentAlarmIsdn += isdn + ",";
				CommandMessage request = new CommandMessage();
				request.setChannel(Constants.CHANNEL_SMS);
				request.setUserId(0);
				request.setUserName("system");

				request.setServiceAddress("123");
				request.setShipTo(isdn);
				request.setIsdn(isdn);
				request.setRequest(content);

				request.setKeyword("ALARM");

				request.setProvisioningType(Constants.PROVISIONING_SMSC);
				request.setCommandId(command.getCommandId());
				request.setRequestValue(ResponseUtil.SMS_CMD_CHECK, "false");

				// QueueFactory.sendObjectMessage(this.queueSession,
				// QueueFactory.COMMAND_ROUTE_QUEUE, request);

				MQConnection connection = null;
				try
				{
					connection = getMQConnection();
					connection
							.sendMessage(request, QueueFactory.COMMAND_ROUTE_QUEUE, 0);
				}
				finally
				{
					returnMQConnection(connection);
				}
			}

			if (!sentAlarmIsdn.equals(""))
				debugMonitor("Sent alarm SMS to: " + sentAlarmIsdn);
		}
		catch (Exception e)
		{
			throw e;
		}
	}

	public String getTimeRun()
	{
		return _timerun;
	}

	public void setTimeRun(String _timeRun)
	{
		this._timerun = _timeRun;
	}

	public String getLastUpdateTime()
	{
		return lastUpdateTime;
	}

	public void setLastUpdateTime(String lastUpdateTime)
	{
		this.lastUpdateTime = lastUpdateTime;
	}

	public void setIsdnList(String isdnList)
	{
		this.isdnList = isdnList;
	}

	public String getIsdnList()
	{
		return isdnList;
	}

	private static Logger	_logger	= Logger.getLogger(SendMailReportCS.class);
}
