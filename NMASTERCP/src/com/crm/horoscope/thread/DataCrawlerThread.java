package com.crm.horoscope.thread;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringEscapeUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.crm.horoscope.bean.HoroscopeEntity;
import com.crm.horoscope.sql.impl.HoroscopeImpl;
import com.crm.thread.DispatcherThread;
import com.crm.thread.util.ThreadUtil;
import com.fss.util.AppException;

public class DataCrawlerThread extends DispatcherThread
{
	public String	parentUrl		= "http://ngoisao.net/tin-tuc/phong-cach/trac-nghiem";

	public Calendar	lastParsingTime	= null;

	public int		checkInterval	= 3600;
	public long		lastCheck		= 0;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Vector getParameterDefinition()
	{
		Vector vtReturn = new Vector();
		vtReturn.add(ThreadUtil.createTextParameter("newsUrl", 100,
				"Url of getting page."));
		vtReturn.add(ThreadUtil.createTextParameter("lastParsingTime", 100,
				"The last report time, format yyyyMMdd"));
		vtReturn.add(ThreadUtil.createIntegerParameter("checkInterval",
				"Check to get data interval, by second, default 3600."));
		vtReturn.addAll(super.getParameterDefinition());
		return vtReturn;
	}

	@Override
	public void fillParameter() throws AppException
	{
		// TODO Auto-generated method stub
		super.fillParameter();

		String lastTime = ThreadUtil.getString(this, "lastParsingTime", false, "");
		parentUrl = ThreadUtil.getString(this, "newsUrl", false, "");
		checkInterval = ThreadUtil.getInt(this, "checkInterval", 3600);
		lastParsingTime = Calendar.getInstance();
		if ("".equals(lastTime) || lastTime == null)
		{
			lastParsingTime.add(Calendar.DAY_OF_MONTH, -1);
		}
		else
		{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			try
			{
				Date lastReportDate = sdf.parse(lastTime);

				lastParsingTime.setTime(lastReportDate);
			}
			catch (ParseException e)
			{
				throw new AppException("Last report time parsing error.");
			}
		}
	}

	@Override
	public void doProcessSession() throws Exception
	{
		if (lastCheck + checkInterval * 1000 < System.currentTimeMillis())
		{
			lastCheck = System.currentTimeMillis();
			parseData();
		}
	}

	private void parseData()
	{
		try
		{

			debugMonitor("Try to connect: " + parentUrl);
			Document doc = Jsoup.connect(parentUrl).get();

			Elements elements = null;
			String url = "";

			debugMonitor("Try to parse: div.sp-phong-cach/div.news/a.ptw[href]");
			elements = doc.select("div.sp-phong-cach").select("div.news").select("a.ptw");
			url = elements.attr("href");

			debugMonitor("Try to connect: " + url);
			doc = Jsoup.connect(url).get();

			debugMonitor("Try to parse: div.detailCT/div.topDetail/span");
			Elements eTime = doc.select("div.detailCT").select("div.topDetail").select("span");
			String time = getHtmlText(eTime.text());
			debugMonitor("Get time string: " + time);
			Calendar foundDate = Calendar.getInstance();
			try
			{
				String timePattern = "(\\d{1}|\\d{2})/(\\d{1}|\\d{2})/\\d{4}";

				Pattern r = Pattern.compile(timePattern);
				Matcher matcher = r.matcher(time);
				if (matcher.find())
				{
					String[] foundDateComponent = matcher.group().split("/");
					foundDate.set(Integer.parseInt(foundDateComponent[2]),
									Integer.parseInt(foundDateComponent[1]) - 1,
									Integer.parseInt(foundDateComponent[0]));
					// month value is 0 - based, LMAO
				}
			}
			catch (Exception e)
			{
				debugMonitor(e);
				throw new Exception("Can not parse time.");
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

			if (sdf.format(foundDate.getTime()).compareTo(sdf.format(lastParsingTime.getTime())) <= 0)
			{
				debugMonitor("Has already fetched topic of " + sdf.format(foundDate.getTime()));
				return;
			}

			debugMonitor("Try to parse: div.detailCT/div.fck_detail");
			Elements eDetail = doc.select("div.detailCT").select("div.fck_detail");
			Elements eContent = eDetail.select("p.Normal");
			int count = 0;
			Iterator<Element> itr = eContent.iterator();
			HoroscopeEntity[] horoscopes = new HoroscopeEntity[12];
			String scope = "";
			String startDate = "";
			String endDate = "";
			String content = "";
			while (itr.hasNext())
			{
				Element e = itr.next();
				Elements eScope = e.select("strong");
				if (!eScope.isEmpty())
				{
					if (!"".equals(scope))
					{
						horoscopes[count] = new HoroscopeEntity();
						horoscopes[count].setCreateDate(foundDate.getTime());
						horoscopes[count].setHoroscope(scope.trim());
						horoscopes[count].setStartDate(startDate.trim());
						horoscopes[count].setEndDate(endDate.trim());
						horoscopes[count].setDetail(content.trim());
						debugMonitor("Add: " + scope + " | " + startDate + "-" + endDate + " | " + content);
						startDate = "";
						endDate = "";
						content = "";
						scope = "";
						count++;
						if (count > 12)
							break;
					}

					scope = getHtmlText(eScope.text());
					String dateRange = getHtmlText(e.text());
					String dateRangePattern = "[0-9]+/[0-9]+";

					Pattern p = Pattern.compile(dateRangePattern);
					Matcher matcher = p.matcher(dateRange);
					if (matcher.find())
					{
						startDate = matcher.group();
					}
					if (matcher.find())
					{
						endDate = matcher.group();
					}

				}
				else
				{
					if ("".equals(content))
					{
						content = getHtmlText(e.text());
					}
					else
					{
						content = content + " " + getHtmlText(e.text());
					}
				}
			}

			HoroscopeImpl.insertHoroscope(horoscopes);
			debugMonitor("Added to DB.");
			lastParsingTime = foundDate;
			mprtParam.setProperty("lastParsingTime", sdf.format(lastParsingTime.getTime()));
			storeConfig();
		}
		catch (Exception e)
		{
			debugMonitor(e);
		}
	}

	public static String getHtmlText(String input)
	{
		String ret = StringEscapeUtils.unescapeHtml4(input);
		ret = unsignsCharConverted(ret);
		return ret;
	}

	public static String unsignsCharConverted(String input)
	{
		String ret = input;
		String strChar = "aAeEoOuUiIdDyY";
		for (int count = 0; count < 14; count++)
		{
			String strTemp = "";
			switch (count)
			{
			case 0:
				strTemp = "áàạảãâấầậẩẫăắằặẳẵ";
				break;
			case 1:
				strTemp = "ÁÀẠẢÃÂẤẦẬẨẪĂẮẰẶẲẴ";
				break;
			case 2:
				strTemp = "éèẹẻẽêếềệểễeeeeee";
				break;
			case 3:
				strTemp = "ÉÈẸẺẼÊẾỀỆỂỄEEEEEE";
				break;
			case 4:
				strTemp = "óòọỏõôốồộổỗơớờợởỡ";
				break;
			case 5:
				strTemp = "ÓÒỌỎÕÔỐỒỘỔỖƠỚỜỢỞỠ";
				break;
			case 6:
				strTemp = "úùụủũưứừựửữuuuuuu";
				break;
			case 7:
				strTemp = "ÚÙỤỦŨƯỨỪỰỬỮUUUUUU";
				break;
			case 8:
				strTemp = "íìịỉĩiiiiiiiiiiii";
				break;
			case 9:
				strTemp = "ÍÌỊỈĨIIIIIIIIIIII";
				break;
			case 10:
				strTemp = "đdddddddddddddddd";
				break;
			case 11:
				strTemp = "ĐDDDDDDDDDDDDDDDD";
				break;
			case 12:
				strTemp = "ýỳỵỷỹyyyyyyyyyyyy";
				break;
			case 13:
				strTemp = "ÝỲỴỶỸYYYYYYYYYYYY";
				break;
			default:
				break;
			} 
			for (int j = 0; j < strTemp.length(); j++)
			{
				ret = ret.replace(strTemp.substring(j, j + 1), strChar.substring(count, count + 1));
			}
		}
		return ret;
	}
}
