package com.crm.thread;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import com.crm.kernel.sql.Database;
import com.crm.thread.util.ThreadUtil;
import com.fss.thread.ParameterType;
import com.fss.util.AppException;

public class RevenueReport extends MailThread
{
	private String SQL = "";
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

		vtReturn.addElement(createParameterDefinition("SQL", "",
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
			
			// Fill parameter
			setSQL(loadMandatory("SQL"));
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
		PreparedStatement _stmtSelect = null;
		Connection connection = null;
		ResultSet rs = null;
		
		try
		{
			Calendar checkTime = Calendar.getInstance();
			Calendar timeRun = Calendar.getInstance();
			timeRun.setTime(new SimpleDateFormat("yyyyMMddHHmmss").parse(getNextRunDate() + timeStartToCharge));
			if (checkTime.before(timeRun))
			{
				return;
			}
			
			logMonitor("Start send mail report");
			
			connection = Database.getConnection();
			_stmtSelect = connection.prepareStatement(SQL);
			
			rs = _stmtSelect.executeQuery();
			StringBuilder body = new StringBuilder();
			
			int count = 0;
			while (rs.next())
			{
				body.append("<tr>");
				body.append("<td style=\"text-align: center;\">" + rs.getString("productid") + "</td>");
				body.append("<td>" + rs.getString("alias_") + "</td>");
				body.append("<td style=\"text-align: right;\">" + rs.getString("TotalRegisterFree") + "</td>");
				body.append("<td style=\"text-align: right;\">" + rs.getString("TotalRegister") + "</td>");
				body.append("<td style=\"text-align: right;\">" + rs.getString("TotalSubscription") + "</td>");
				body.append("<td style=\"text-align: right;\">" + rs.getString("TotalUnregister") + "</td>");
				body.append("<td style=\"text-align: right;\">" + rs.getString("TotalSub") + "</td>");
				body.append("<td style=\"text-align: right;\">" + rs.getString("TotalRevenue") + "</td>");
				body.append("<td style=\"text-align: right;\">" + rs.getString("TotalMT") + "</td>");
				body.append("</tr>");
				
				count++;
			}
			
			body.append("<tr>");
			body.append("<td>&nbsp;</td>");
			body.append("<td>&nbsp;</td>");
			body.append("<td>&nbsp;</td>");
			body.append("<td>&nbsp;</td>");
			body.append("<td>&nbsp;</td>");
			body.append("<td>&nbsp;</td>");
			body.append("<td>&nbsp;</td>");
			body.append("<td>&nbsp;</td>");
			body.append("<td>&nbsp;</td>");
			body.append("</tr>");
			
			String mailContent = getMailFormat().replaceAll("<=%Content=%>", body.toString());
			
			Date date = new Date();
			DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH");
			
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
			Database.closeObject(rs);
			Database.closeObject(_stmtSelect);
			Database.closeObject(connection);
		}
	}
	
	public String getSQL()
	{
		return SQL;
	}

	public void setSQL(String sQL)
	{
		SQL = sQL;
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
}
