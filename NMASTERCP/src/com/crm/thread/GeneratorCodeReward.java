package com.crm.thread;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import org.apache.commons.lang3.RandomStringUtils;

import com.crm.kernel.sql.Database;
import com.crm.thread.util.ThreadUtil;
import com.crm.util.DateUtil;
import com.fss.thread.ParameterType;
import com.fss.util.AppException;

public class GeneratorCodeReward extends DispatcherThread
{
	private boolean bNumberStyle = true;
	private boolean bCharacterStyle = true;
	private int intPointPerCode = 50;
	private int intLengthOfCode = 12;
	private String strSQL = ""; 
	
	private String timeToStart= "010000";
	private String nextRunDate = "";

	private PreparedStatement stmtGetList = null;
	private PreparedStatement stmtInsert = null;
	private Connection connection = null;
	
	private char[] arrayOfChar = {'0','1','2','3','4','5','6','7','8','9',
			'A','a','B','b','C','c','D','d','E','e','F','f','G','g','H','h','I','i','J','j','K','k','L','l','M','m','N',
			'n','O','o','P','p','Q','q','R','r','S','s','T','t','U','u','V','v','W','w','X','x','Y','y','Z','z'};
	
	public Vector getDispatcherDefinition()
	{
		Vector vtReturn = new Vector();
		
		vtReturn.addElement(ThreadUtil.createTextParameter("SQL", 1000, "SQL statement to get list subs"));
		vtReturn.addElement(ThreadUtil.createBooleanParameter("NumberStyle","Reward code contain number or not"));
		vtReturn.addElement(ThreadUtil.createBooleanParameter("CharacterStyle","Reward code contain character or not"));		
		vtReturn.addElement(ThreadUtil.createIntegerParameter("Product","Productid of product"));
		vtReturn.addElement(ThreadUtil.createIntegerParameter("PointPerCode","How many point for one code"));
		vtReturn.addElement(ThreadUtil.createIntegerParameter("LengthOfCode","Length of code"));
		vtReturn.addElement(ThreadUtil.createTextParameter("TimeToStart", 400,
				"Time to begin starting in day, for mat 'HHmmss', default '090000'."));
		vtReturn.addElement(createParameterDefinition("NextRunDate", "",
				ParameterType.PARAM_TEXTAREA_MAX, "100", ""));
		
		vtReturn.addAll(ThreadUtil.createLogParameter(this));
		return vtReturn;
	}
	public void fillParameter() throws AppException
	{
		bNumberStyle = ThreadUtil.getBoolean(this, "NumberStyle", true);
		bCharacterStyle = ThreadUtil.getBoolean(this, "CharacterStyle", true);
		intPointPerCode = ThreadUtil.getInt(this, "PointPerCode", 50);
		intLengthOfCode = ThreadUtil.getInt(this, "LengthOfCode", 50);
		strSQL = ThreadUtil.getString(this, "SQL", true, "");
		
		timeToStart = ThreadUtil.getString(this, "TimeToStart", false, "090000");
		setNextRunDate(loadMandatory("NextRunDate"));
		
		if (!timeToStart.matches("([01][0-9]|2[0-3])[0-5][0-9][0-5][0-9]$"))
		{
			throw new AppException("Invalid input format for TimeToStart.");
		}		
		super.fillParameter();
	}
	public void beforeProcessSession() throws Exception
	{
		logMonitor("Starting generate Reward code.");
		connection = Database.getConnection();
		stmtGetList = connection.prepareStatement(strSQL);
		String strInsertSQL = "insert into CodeRewardGo (id,productid,createdate,isdn,codereward,bingo,expirecode) values (?,?,?,?,?,?,?)";
		stmtInsert = connection.prepareStatement(strInsertSQL);
	}
	public void afterProcessSession() throws Exception
	{
		Database.closeObject(stmtGetList);
		Database.closeObject(stmtInsert);
		Database.closeObject(connection);
	}
	public String generatorRewardCode(int lengthOfCode, boolean numberStyle, boolean characterStyle)
	{
		String code = "";
		code = RandomStringUtils.random(lengthOfCode, 0, arrayOfChar.length -1,characterStyle, numberStyle, arrayOfChar);
		return code;
	}
	
	public void doProcessSession() throws Exception
	{
		ResultSet rs = stmtGetList.executeQuery();
		String logCode = "";
		String code = "";
		int count = 0;
		Calendar checkTime = Calendar.getInstance();
		Calendar timeRun = Calendar.getInstance();
		timeRun.setTime(new SimpleDateFormat("yyyyMMddHHmmss").parse(getNextRunDate() + timeToStart));
//		logMonitor(new SimpleDateFormat("yyyyMMddHHmmss").format(checkTime.getTime()));
//		logMonitor(new SimpleDateFormat("yyyyMMddHHmmss").format(timeRun.getTime()));
		if (checkTime.before(timeRun))
		{
			return;
		}
		try
		{
			while (rs.next())
			{
				logCode = rs.getString("isdn") + ":";
				int numberOfCode = rs.getInt("SCORE") / intPointPerCode;
				for (int i=0; i < numberOfCode; i++)
				{
					stmtInsert.setLong(1, Database.getSequence(connection,"CODEREWARDGO_SEQ"));
					stmtInsert.setLong(2, rs.getLong("productid"));
					stmtInsert.setTimestamp(3, DateUtil.getTimestampSQL(new Date()));
					stmtInsert.setString(4, rs.getString("isdn"));
					code = generatorRewardCode(intLengthOfCode,bNumberStyle,bCharacterStyle);
					stmtInsert.setString(5, code);
					stmtInsert.setInt(6, 0);
					stmtInsert.setInt(7, 0);
					stmtInsert.addBatch();
					count =  count + 1;
					logCode = logCode + code + " ";
				}
				if (count > 0)
				{
					int[] result = stmtInsert.executeBatch();
					count = 0;
					logMonitor(logCode);
				}
			}
			

			Calendar nextDate = Calendar.getInstance();
			nextDate.add(Calendar.DAY_OF_MONTH, 1);
			setNextRunDate( new SimpleDateFormat("yyyyMMdd").format(nextDate.getTime()));	
			mprtParam.setProperty("NextRunDate", getNextRunDate());
			storeConfig();	
						
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			Database.closeObject(rs);

		}
	}
	public String getNextRunDate()
	{
		return nextRunDate;
	}
	public void setNextRunDate(String nextRunDate)
	{
		this.nextRunDate = nextRunDate;
	}
	
}
