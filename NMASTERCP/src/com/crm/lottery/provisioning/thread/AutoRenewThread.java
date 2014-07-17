package com.crm.lottery.provisioning.thread;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import javax.jms.MessageConsumer;
import javax.jms.QueueSession;

import com.crm.kernel.message.Constants;
import com.crm.kernel.queue.QueueFactory;
import com.crm.kernel.sql.Database;
import com.crm.provisioning.message.CommandMessage;
import com.crm.thread.DispatcherThread;
import com.crm.thread.util.ThreadUtil;
import com.crm.util.StringUtil;
import com.fss.util.AppException;

public class AutoRenewThread extends DispatcherThread
{
	private PreparedStatement	stmtSubscription		= null;
	private PreparedStatement	stmtSubscriptionUpdate	= null;
	private Connection			connection				= null;
	private String				keyword					= "";
	private String				lastRunDate				= "";
	private String				checkChargeQuery		= "";

	private int					batchSize				= 1000;
	private int					counter					= 0;
	private int					checkChargeInterval		= 3600;
	private String				timeStartToCharge		= "01:00:00";
	private String				timeEndCharging			= "12:00:00";
	private long				lastCheck				= 0;

	public synchronized void addCounter()
	{
		counter++;
	}

	public synchronized void removeCounter()
	{
		counter--;
	}

	public synchronized int getCounter()
	{
		return counter;
	}

	// //////////////////////////////////////////////////////
	// Override
	// //////////////////////////////////////////////////////
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Vector getParameterDefinition()
	{
		Vector vtReturn = new Vector();

		vtReturn.addElement(ThreadUtil.createTextParameter("TimeToStart", 400,
				"Time to begin starting charge in day, for mat 'HH:mm:ss', default '01:00:00'."));

		vtReturn.addElement(ThreadUtil.createTextParameter("TimeToEnd", 400,
				"Time to end charging in day, for mat 'HH:mm:ss', default '12:00:00'."));

		vtReturn.addElement(ThreadUtil.createTextParameter("CheckChargeQuery", 400,
				"Query SQL to get subs to charge."));

		vtReturn.addElement(ThreadUtil.createTextParameter("CheckChargeInterval", 400,
				"Scan db interval, by second, default 3600."));

		vtReturn.addElement(ThreadUtil.createTextParameter("LastRunDate", 400, "SQL query to get subscription yyyyMMddHHmmss."));

		vtReturn.addElement(ThreadUtil.createTextParameter("Keyword", 400, "Subscription Keyword."));

		vtReturn.addElement(ThreadUtil.createIntegerParameter("BatchSize",
				"Update subscription status by batch size, default 300."));

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
			setLastRunDate(loadMandatory("LastRunDate"));
			timeStartToCharge = ThreadUtil.getString(this, "TimeToStart", false, "01:00:00");
			timeEndCharging = ThreadUtil.getString(this, "TimeToEnd", false, "12:00:00");
			keyword = ThreadUtil.getString(this, "Keyword", false, "");
			batchSize = ThreadUtil.getInt(this, "BatchSize", 300);
			checkChargeInterval = ThreadUtil.getInt(this, "CheckChargeInterval", 3600);

			checkChargeQuery = loadMandatory("CheckChargeQuery");

			if (!timeStartToCharge.matches("([01][0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]$") ||
					!timeEndCharging.matches("([01][0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]$"))
			{
				debugMonitor("Invalid input format for time parameter.");
				throw new AppException(Constants.ERROR_INVALID_PARAMETER);
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

	// //////////////////////////////////////////////////////
	// after process session
	// Author : ThangPV
	// Created Date : 16/09/2004
	// //////////////////////////////////////////////////////
	public void beforeProcessSession() throws Exception
	{
		super.beforeProcessSession();

	}

	private int	batchCounter	= 0;

	public synchronized void updateSubs(long subsId)
	{
		try
		{
			stmtSubscriptionUpdate.setLong(1, subsId);
			stmtSubscriptionUpdate.addBatch();
			batchCounter++;
			if (batchCounter >= batchSize)
			{
				batchCounter = 0;
				updateToDB();
			}
			removeCounter();
		}
		catch (Exception e)
		{
			debugMonitor(e);
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
			if (connection != null && !connection.isClosed())
			{
				updateToDB();

				Database.closeObject(stmtSubscription);
				Database.closeObject(stmtSubscriptionUpdate);
				Database.closeObject(connection);
			}
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			QueueFactory.getLocalQueue(queueLocalName).empty();
			super.afterProcessSession();
		}
	}

	// //////////////////////////////////////////////////////
	// process session
	// Author : ThangPV
	// Created Date : 16/09/2004
	// //////////////////////////////////////////////////////
	public void doProcessSession() throws Exception
	{
		QueueSession session = null;
		MessageConsumer consumer = null;

		try
		{
			if (QueueFactory.queueServerEnable && queueDispatcherEnable && (queueMode == Constants.QUEUE_MODE_CONSUMER))
			{
				session = getQueueSession();
				consumer = session.createConsumer(QueueFactory.getQueue(queueName));
			}

			loadDatabase();

			while (isAvailable())
			{

				checkInstance();

				if ((System.currentTimeMillis() - lastSnapshot) > snapshotInterval)
				{
					updateSnapshot();
					lastSnapshot = System.currentTimeMillis();
				}
				if ((System.currentTimeMillis() - lastStatistic) > statisticInterval)
				{
					updateStatistic();
					lastStatistic = System.currentTimeMillis();
				}

				if (isOverload())
				{
					processOverload(consumer);
				}
				else
				{
					Object message = detachMessage(consumer);
					if (message != null)
					{
						QueueFactory.attachLocal(queueName, message);
					}
					else
					{
						Thread.sleep(5);
					}
				}

				if (getCounter() != 0)
				{
					Thread.sleep(1000);
				}
				else
				{
					break;
				}
			}

			closeDatabase();
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			QueueFactory.closeQueue(consumer);
			QueueFactory.closeQueue(session);
		}

	}

	public void loadDatabase() throws Exception
	{
		ResultSet rs = null;

		try
		{
			/**
			 * Check charge interval
			 */
			lastCheck = (new SimpleDateFormat("yyyyMMddHHmmss")).parse(getLastRunDate()).getTime();
			if (lastCheck + checkChargeInterval * 1000 > System.currentTimeMillis())
			{
				return;
			}

			//lastCheck = System.currentTimeMillis();
			/**
			 * Check time to start charge
			 */
			Calendar checkTime = Calendar.getInstance();
			String strCheckTime = StringUtil.format(checkTime.getTime(), "yyyyMMdd");
			String strCheckTimeStart = strCheckTime + timeStartToCharge;
			String strCheckTimeEnd = strCheckTime + timeEndCharging;
			String strNow = StringUtil.format(checkTime.getTime(), "yyyyMMddHH:mm:ss");
			if (strNow.compareTo(strCheckTimeStart) < 0 || strNow.compareTo(strCheckTimeEnd) > 0)
			{
				return;
			}

			connection = Database.getConnection();

			String strSQL = "SELECT subsId, isdn, serviceAddress, regionCode, majorRegion "
								+ "FROM VasLotterySubs "
								+ " WHERE (lastChargingDate + 1/24) < sysdate "
								+ " AND unregisterDate is null "
								+ " AND (lastSuccessDate is null OR trunc(lastSuccessDate) < trunc(sysdate)) "
								+ " AND status = 1 ";
			stmtSubscription = connection.prepareStatement(checkChargeQuery);

			strSQL = "Update VasLotterySubs Set lastChargingDate = sysdate Where subsId = ?";
			stmtSubscriptionUpdate = connection.prepareStatement(strSQL);

			rs = stmtSubscription.executeQuery();

			int total = 0;
			while (rs.next())
			{
				addCounter();
				total++;

				CommandMessage message = new CommandMessage();
				message.setRequestValue("lottery.subsId", rs.getLong("subsId"));
				message.setIsdn(rs.getString("isdn"));
				message.setServiceAddress(rs.getString("serviceAddress"));
				message.setRequestValue("lottery.regionCode", rs.getString("regionCode"));
				message.setRequestValue("lottery.majorRegion", rs.getString("majorRegion"));
				message.setChannel(Constants.CHANNEL_WEB);
				String strKeyword = keyword.replace("<majorRegion>", rs.getString("majorRegion"))
								.replace("<regionCode>", rs.getString("regionCode"));
				message.setKeyword(strKeyword);

				QueueFactory.attachLocal(queueLocalName, message);
			}

			logMonitor("Total record is browsed: " + total);

			setLastRunDate(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));

			mprtParam.setProperty("LastRunDate", getLastRunDate());
			storeConfig();
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			Database.closeObject(rs);
		}
	}

	public void updateToDB() throws Exception
	{
		if (stmtSubscriptionUpdate != null)
		{
			stmtSubscriptionUpdate.executeBatch();
			debugMonitor("Update batch to DB");
		}

	}

	public void closeDatabase() throws Exception
	{

		try
		{
			try
			{
				updateToDB();
			}
			finally
			{
				if (connection != null)
				{
					connection.commit();
					debugMonitor("Commited to DB");
				}
			}
			Database.closeObject(stmtSubscription);
			Database.closeObject(stmtSubscriptionUpdate);
			Database.closeObject(connection);
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			stmtSubscription = null;
			stmtSubscriptionUpdate = null;
			connection = null;
			QueueFactory.getLocalQueue(queueLocalName).empty();
		}
	}

	public void setLastRunDate(String _lastRunDate)
	{
		this.lastRunDate = _lastRunDate;
	}

	public String getLastRunDate()
	{
		return lastRunDate;
	}

	public static void main(String[] args)
	{
		String timeStartToCharge = "31:00:00";
		if (!timeStartToCharge.matches("([01][0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]$"))
		{
			System.out.println("not matches.");
		}
		else
		{
			System.out.println("matches.");
		}
	}
}
