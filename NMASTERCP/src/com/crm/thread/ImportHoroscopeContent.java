package com.crm.thread;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import com.crm.kernel.sql.Database;
import com.crm.thread.util.ThreadUtil;
import com.crm.util.DateUtil;
import com.fss.thread.ParameterType;
import com.fss.util.AppException;

public class ImportHoroscopeContent extends DispatcherThread
{
	private String timeToStart= "010000";
	private String nextRunDate = "";
	private String directoryPath = "";
	private String start = null;
	private String end = null;

	private PreparedStatement stmtInsert = null;
	private Connection connection = null;
	
	private String [] horoscope = {"Song Ngu","Bao Binh","Kim Nguu","Bach Duong","Song Tu","Nhan Ma","Ma Ket","Cu Giai","Bo Cap","Su Tu","Xu Nu","Thien Binh"};
	
	public Vector getDispatcherDefinition()
	{
		Vector vtReturn = new Vector();
		
		vtReturn.addElement(ThreadUtil.createTextParameter("DirectoryPath",400, "Path where store content"));
		vtReturn.addElement(ThreadUtil.createTextParameter("TimeToStart", 400,
				"Time to begin starting in day, for mat 'HHmmss', default '090000'."));
		vtReturn.addElement(createParameterDefinition("NextRunDate", "",
				ParameterType.PARAM_TEXTAREA_MAX, "100", ""));
		
		vtReturn.addAll(ThreadUtil.createLogParameter(this));
		return vtReturn;
	}
	public void fillParameter() throws AppException

	{
		directoryPath = ThreadUtil.getString(this, "DirectoryPath", true, "");		
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
		logMonitor("Starting Import Horoscope Content");
		connection = Database.getConnection();
		String strInsertSQL = "insert into vashoroscope (createdate,horoscope,startdate,enddate,detail) values (?,?,?,?,?)";
		stmtInsert = connection.prepareStatement(strInsertSQL);
	}
	public void afterProcessSession() throws Exception
	{
		Database.closeObject(stmtInsert);
		Database.closeObject(connection);
	}
	
	public void doProcessSession() throws Exception
	{
		BufferedReader buffReader = null;
		
		Calendar checkTime = Calendar.getInstance();
		Calendar timeRun = Calendar.getInstance();
		timeRun.setTime(new SimpleDateFormat("yyyyMMddHHmmss").parse(getNextRunDate() + timeToStart));
		if (checkTime.before(timeRun))
		{
			return;
		}
		try
		{
			buffReader = new BufferedReader(readFile(directoryPath));
			String line = buffReader.readLine();
			while (line != null)
			{
				for (int i = 0; i < horoscope.length; i ++)
				{
					if (line.substring(0, horoscope[i].length()).equals(horoscope[i]))
					{
						String content = line.substring(horoscope[i].length() + 1);
						getStartEnd(horoscope[i], start, end);
						stmtInsert.setTimestamp(1, DateUtil.getTimestampSQL(new Date()));
						stmtInsert.setString(2, horoscope[i]);
						stmtInsert.setString(3, start);
						stmtInsert.setString(4, end);
						stmtInsert.setString(5, content);
						stmtInsert.execute();
					}
				}
				line = buffReader.readLine();
			}
			
			Calendar nextDate = Calendar.getInstance();
			nextDate.add(Calendar.DAY_OF_MONTH, 1);
			
			setNextRunDate( new SimpleDateFormat("yyyyMMdd").format(nextDate.getTime()));
			storeConfig();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			buffReader.close();
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
	public FileReader  readFile(String directoryPath) throws IOException
	{
		FileReader inputFile = null;
		// Check file name
		SimpleDateFormat format = new SimpleDateFormat("ddMMyyyy");
		String fileName = "TV_"+ format.format(new Date())+ ".csv";
		try
		{
			inputFile = new FileReader(directoryPath + "/" + fileName);
		}
		finally
		{
		}		
		return inputFile;
	}
	public void getStartEnd(String cung, String Start, String End)
	{
		if (cung.equals("Song Ngu"))
		{
			Start = "19/2";
			End ="20/3";
		}
		else if (cung.equals("Bao Binh"))
		{
			Start = "20/1";
			End ="18/2";			
		}
		else if (cung.equals("Kim Nguu"))
		{
			Start = "20/4";
			End ="20/5";						
		}
		else if (cung.equals("Bach Duong"))
		{
			Start = "21/3";
			End ="19/4";									
		}
		else if (cung.equals("Song Tu"))
		{
			Start = "21/5";
			End ="21/6";												
		}
		else if(cung.equals("Nhan Ma"))
		{
			Start = "22/11";
			End ="21/12";															
		}
		else if(cung.equals("Ma Ket"))
		{
			Start = "22/12";
			End ="19/1";																		
		}
		else if(cung.equals("Cu Giai"))
		{
			Start = "22/6";
			End ="21/7";				
		}
		else if(cung.equals("Bo Cap"))
		{
			Start = "23/10";
			End ="21/11";							
		}
		else if(cung.equals("Su Tu"))
		{
			Start = "23/7";
			End ="22/8";	
		}
		else if(cung.equals("Xu Nu"))
		{
			Start = "23/8";
			End ="22/9";				
		}
		else if(cung.equals("Thien Binh"))
		{
			Start = "23/9";
			End ="22/10";			
		}
		start = Start;
		end = End;
		//"Song Ngu","Bao Binh","Kim Nguu","Duong Cuu","Song Sinh","Nhan Ma","Ma Ket","Cu Giai","Bo Cap","Su Tu","Xu Nu","Thien Binh"
	}
}

