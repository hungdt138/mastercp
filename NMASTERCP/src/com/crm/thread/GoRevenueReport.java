package com.crm.thread;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import com.crm.kernel.message.Constants;
import com.crm.kernel.report.GoReportData;
import com.crm.kernel.sql.Database;
import com.crm.thread.util.ThreadUtil;
import com.crm.util.DateUtil;
import com.fss.thread.ParameterType;
import com.fss.util.AppException;
import com.lowagie.text.pdf.ArabicLigaturizer;

public class GoRevenueReport extends MailThread
{
	private String sqlCountActiveSubs = "";
	private String sqlSelectOldData = "";
	private String sqlSelectRevenue = "";
	private String sqlSelectNewSubs = "";
	
	private String mailFormat = "";
	private String nextRunDate = "";
	private String timeStartToCharge = "090000";
	{

	}

	// //////////////////////////////////////////////////////
	// Override
	// //////////////////////////////////////////////////////
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Vector getParameterDefinition()
	{
		Vector vtReturn = new Vector();

		vtReturn.addElement(createParameterDefinition("SqlCountActiveSubs", "",
				ParameterType.PARAM_TEXTAREA_MAX, "5000", ""));
		vtReturn.addElement(createParameterDefinition("sqlSelectOldData", "",
				ParameterType.PARAM_TEXTAREA_MAX, "5000", ""));
		vtReturn.addElement(createParameterDefinition("sqlSelectRevenue", "",
				ParameterType.PARAM_TEXTAREA_MAX, "5000", ""));
		vtReturn.addElement(createParameterDefinition("sqlSelectNewSubs", "",
				ParameterType.PARAM_TEXTAREA_MAX, "5000", ""));
		
		vtReturn.addElement(createParameterDefinition("MailFormat", "",
				ParameterType.PARAM_TEXTAREA_MAX, "100", ""));
		vtReturn.addElement(ThreadUtil.createTextParameter("TimeToStart", 400,
				"Time to begin starting charge in day, for mat 'HHmmss', default '090000'."));
		vtReturn.addElement(createParameterDefinition("NextRunDate", "",
				ParameterType.PARAM_TEXTAREA_MAX, "100", ""));
		vtReturn.addElement(ThreadUtil.createBooleanParameter("neverExpire", ""));
		
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
			setSqlCountActiveSubs(loadMandatory("SqlCountActiveSubs"));
			setSqlSelectOldData(loadMandatory("sqlSelectOldData"));
			setSqlSelectRevenue(loadMandatory("sqlSelectRevenue"));
			setSqlSelectNewSubs(loadMandatory("sqlSelectNewSubs"));
			// Fill parameter
			
			setMailFormat(loadMandatory("MailFormat"));
			timeStartToCharge = ThreadUtil.getString(this, "TimeToStart", false, "090000");
			setNextRunDate(loadMandatory("NextRunDate"));
			
			if (!timeStartToCharge.matches("([01][0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]$"))
			{
				throw new Exception("Invalid input format for TimeToStart.");
			}
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
	}

	// //////////////////////////////////////////////////////
	// after process session
	// Author : ThangPV
	// Created Date : 16/09/2004
	// //////////////////////////////////////////////////////
	public void afterProcessSession() throws Exception
	{
		super.afterProcessSession();
	}
	
	public void doProcessSession() throws Exception
	{
		Calendar checkTime = Calendar.getInstance();
		try
		{

			Calendar timeRun = Calendar.getInstance();
			timeRun.setTime(new SimpleDateFormat("yyyyMMddHHmmss").parse(getNextRunDate() + timeStartToCharge));
			if (checkTime.before(timeRun))
			{
				return;
			}
			List<GoReportData> arrayList = getData();
			logMonitor("Start send mail report");
			
			StringBuilder body = new StringBuilder();
			
			int count = 0;
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			GoReportData data =  null;
			long totalRevenueSubscription = 0;
			long totalRevenueAddQuestion = 0;
			long totalRevenue = 0;
			for (int i = arrayList.size(); i >0; i--)
			{
				data = arrayList.get(i-1);
				body.append("<tr>");
				body.append("<td style=\"text-align: center;\">" + df.format(data.getReportdate())+ "</td>");
				body.append("<td>" + data.getTotalSubsActive() + "</td>");
				body.append("<td style=\"text-align: right;\">" + data.getTotalSubsRegisterPerDay() + "</td>");
				body.append("<td style=\"text-align: right;\">" + data.getTotalSubsUnregisterPerDay() + "</td>");
				body.append("<td style=\"text-align: right;\">" + data.getTotalRevenueSubscription() + "</td>");
				body.append("<td style=\"text-align: right;\">" + data.getTotalRevenueBuyQuestion() + "</td>");
				body.append("<td style=\"text-align: right;\">" + data.getTotalRevenue() + "</td>");
				body.append("</tr>");
				count ++;
				totalRevenueSubscription = totalRevenueSubscription + data.getTotalRevenueSubscription();
				totalRevenueAddQuestion = totalRevenueAddQuestion + data.getTotalRevenueBuyQuestion();
				totalRevenue = totalRevenue + data.getTotalRevenue();
			}
			
			body.append("<tr>");
			body.append("<td>&nbsp;</td>");
			body.append("<td>&nbsp;</td>");
			body.append("<td>&nbsp;</td>");
			body.append("<td>&nbsp;</td>");
			body.append("<td style=\"text-align: right;\">" + totalRevenueSubscription + "</td>");
			body.append("<td style=\"text-align: right;\">" + totalRevenueAddQuestion + "</td>");
			body.append("<td style=\"text-align: right;\">" + totalRevenue + "</td>");
			body.append("</tr>");
			String mailContent = getMailFormat().replaceAll("<=%Content=%>", body.toString());
			
			Date date = new Date();			
			if (count > 0)
			{
				initMailSession();
				try
				{
					sendEmail(getSubject() + df.format(date), mailContent, null);
				}
				finally
				{
					detroyMailSessioin();
					storeDataReport(arrayList.get(arrayList.size()-1));
				}
			}
			else
			{
				logMonitor("No data to report");
			}
			logMonitor("Finish send mail report");
			
			checkTime.add(Calendar.DATE, 1);
			setNextRunDate(new SimpleDateFormat("yyyyMMdd").format(checkTime.getTime()));
			mprtParam.setProperty("NextRunDate", getNextRunDate());
			storeConfig();			
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			logMonitor(ex.getMessage());
		}
		finally
		{
		}
	}
	private List<GoReportData> getData()
	{
		List<GoReportData> result = new ArrayList<GoReportData>() ;

		
		Connection connection = null;
		ResultSet resultSetOldDate = null;
		ResultSet resultSetRevenue = null;
		ResultSet resultSetNewSubs = null;
		ResultSet resultTotalActive = null;
		
		PreparedStatement stmtSelectOldDate = null;
		PreparedStatement stmtSelectRevenue = null;
		PreparedStatement stmtSelectNewSubs = null;
		PreparedStatement stmtTotalActive = null;
		
		try
		{
			connection = Database.getConnection();
			stmtSelectOldDate = connection.prepareStatement(sqlSelectOldData);
			stmtSelectRevenue = connection.prepareStatement(sqlSelectRevenue);
			stmtSelectNewSubs = connection.prepareStatement(sqlSelectNewSubs);
			stmtTotalActive = connection.prepareStatement(sqlCountActiveSubs);
			
			resultSetOldDate = stmtSelectOldDate.executeQuery();
			resultSetRevenue = stmtSelectRevenue.executeQuery();
			resultSetNewSubs = stmtSelectNewSubs.executeQuery();
			resultTotalActive = stmtTotalActive.executeQuery();
			GoReportData data = null;
			while (resultSetOldDate.next())
			{
				data = new GoReportData();
				data.setReportdate(resultSetOldDate.getDate("reportdate"));
				data.setTotalSubsActive(resultSetOldDate.getLong("totalSubActive"));
				data.setTotalSubsRegisterPerDay(resultSetOldDate.getLong("totalNewRegister"));
				data.setTotalSubsUnregisterPerDay(resultSetOldDate.getLong("totalUnregister"));
				data.setTotalRevenueSubscription(resultSetOldDate.getLong("subscriptionRevenue"));
				data.setTotalRevenueBuyQuestion(resultSetOldDate.getLong("addQuestionRevenue"));
				data.setTotalRevenue(resultSetOldDate.getLong("revenue"));
				result.add(data);
			}
			
			data = new GoReportData();
			while (resultSetNewSubs.next())
			{
				long values = 0;
				String actionType = "";
				values = resultSetNewSubs.getLong("x");
				actionType = resultSetNewSubs.getString("ordertype");
				if (actionType.equals(Constants.ACTION_REGISTER))
				{
					data.setTotalSubsRegisterPerDay(values);
				}
				else if (actionType.equals(Constants.ACTION_UNREGISTER))
				{
					data.setTotalSubsUnregisterPerDay(values);
				}
			}
			while (resultSetRevenue.next())
			{
				long count = 0;
				String actionType = "";
				count = resultSetRevenue.getLong("x");
				actionType = resultSetRevenue.getString("ordertype");
				long revenue = resultSetRevenue.getLong("totalMoney");
				if (actionType.equals(Constants.ACTION_SUBSCRIPTION))
				{
					data.setTotalRevenueSubscription(revenue);
				}
				else if (actionType.equals(Constants.ACTION_CONFIRM))
				{
					data.setTotalRevenueBuyQuestion(revenue);
				}
				else if (actionType.equals(Constants.ACTION_REGISTER))
				{
					data.setTotalRevenueRegister(revenue);
				}
			}
			while(resultTotalActive.next())
			{
				data.setTotalSubsActive(resultTotalActive.getLong("totalSubs"));
				data.setTotalRevenue(data.getTotalRevenueBuyQuestion() + data.getTotalRevenueRegister() + data.getTotalRevenueSubscription());
			}
			result.add(data);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			Database.closeObject(resultSetOldDate);
			Database.closeObject(resultSetRevenue);
			Database.closeObject(resultSetNewSubs);
			Database.closeObject(resultTotalActive);
			
			Database.closeObject(stmtSelectNewSubs);
			Database.closeObject(stmtSelectOldDate);
			Database.closeObject(stmtSelectRevenue);
			Database.closeObject(stmtTotalActive);
			
			Database.closeObject(connection);
		}
		return result;
		
	}
	private void storeDataReport(GoReportData data)
	{
		Connection connection = null;
		String sqlInsert = "insert into GoRevenueReport " +
				"(reportdate,totalSubActive,totalNewRegister,totalUnregister,subscriptionRevenue,addQuestionRevenue,revenue) " +
				"values (?,?,?,?,?,?,?)";
		
		PreparedStatement stmtInsert = null;
		try
		{
			connection = Database.getConnection();
			stmtInsert = connection.prepareStatement(sqlInsert);
			stmtInsert.setTimestamp(1, DateUtil.getTimestampSQL(data.getReportdate()));
			stmtInsert.setLong(2,data.getTotalSubsActive());
			stmtInsert.setLong(3,data.getTotalSubsRegisterPerDay());
			stmtInsert.setLong(4,data.getTotalSubsUnregisterPerDay());
			stmtInsert.setLong(5,data.getTotalRevenueSubscription());
			stmtInsert.setLong(6,data.getTotalRevenueBuyQuestion());
			stmtInsert.setLong(7,data.getTotalRevenue());
			
			stmtInsert.execute();
		}
		catch ( Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			Database.closeObject(stmtInsert);
			Database.closeObject(connection);
		}
	}

	public String getMailFormat()
	{
		return mailFormat;
	}

	public void setMailFormat(String mailFormat)
	{
		this.mailFormat = mailFormat;
	}

	public String getTimeStartToCharge()
	{
		return timeStartToCharge;
	}

	public void setTimeStartToCharge(String timeStartToCharge)
	{
		this.timeStartToCharge = timeStartToCharge;
	}
	
	public void setNextRunDate(String nextRunDate)
	{
		this.nextRunDate = nextRunDate;
	}

	public String getNextRunDate()
	{
		return nextRunDate;
	}

	public String getSqlCountActiveSubs()
	{
		return sqlCountActiveSubs;
	}

	public void setSqlCountActiveSubs(String sqlCountActiveSubs)
	{
		this.sqlCountActiveSubs = sqlCountActiveSubs;
	}

	public String getSqlSelectOldData()
	{
		return sqlSelectOldData;
	}

	public void setSqlSelectOldData(String sqlSelectOldData)
	{
		this.sqlSelectOldData = sqlSelectOldData;
	}

	public String getSqlSelectRevenue()
	{
		return sqlSelectRevenue;
	}

	public void setSqlSelectRevenue(String sqlSelectRevenue)
	{
		this.sqlSelectRevenue = sqlSelectRevenue;
	}

	public String getSqlSelectNewSubs()
	{
		return sqlSelectNewSubs;
	}

	public void setSqlSelectNewSubs(String sqlSelectNewSubs)
	{
		this.sqlSelectNewSubs = sqlSelectNewSubs;
	}
}

