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
import com.crm.provisioning.cache.CommandEntry;
import com.crm.provisioning.cache.ProvisioningFactory;
import com.crm.provisioning.message.CommandMessage;
import com.crm.thread.DispatcherThread;
import com.crm.thread.util.ThreadUtil;
import com.fss.util.AppException;

public class SendMessageThread extends DispatcherThread
{
	private String	lastRunDate		= "";

	private int		loadDBInterval	= 60;

	private long	lastLoad		= 0;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Vector getParameterDefinition()
	{
		Vector vtReturn = new Vector();

		vtReturn.addElement(ThreadUtil.createIntegerParameter("LoadDBInterval",
				"Load new message interval by second, default 60."));
		vtReturn.addElement(ThreadUtil.createTextParameter("ProvisioningAlias", 400, "Send to this provisioning."));
		vtReturn.addElement(ThreadUtil.createTextParameter("CommandAlias", 400, "To do this command."));
		vtReturn.addElement(ThreadUtil.createTextParameter("LastRunDate", 400, "SQL query to get subscription."));

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

			loadDBInterval = ThreadUtil.getInt(this, "LoadDBInterval", 60);
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

			while (isAvailable())
			{
				if (lastLoad + loadDBInterval * 1000 < System.currentTimeMillis())
				{
					loadDatabase();
				}

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
			}
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
		ResultSet rsMessage = null;

		Connection connection = null;
		PreparedStatement stmtGetMessage = null;

		try
		{
			connection = Database.getConnection();

			CommandEntry command = ProvisioningFactory.getCache().getCommand(Constants.COMMAND_SEND_MT);

			String strSQL = "SELECT prize, lotteryDate, regionCode FROM VasLottery "
								+ " WHERE isSent = 0 ";
			stmtGetMessage = connection.prepareStatement(strSQL);

			String strSQLUpdate = "UPDATE VasLottery SET isSent = 1 WHERE LotteryDate = ? AND regionCode = ? ";

			rsMessage = stmtGetMessage.executeQuery();

			int total = 0;
			while (rsMessage.next())
			{

				PreparedStatement stmtUpdate = null;
				PreparedStatement stmtGetQueue = null;
				ResultSet rsQueueWait = null;
				try
				{
					String regionCode = rsMessage.getString("regionCode");
					strSQL = "SELECT isdn, productId, serviceAddress, regionCode, majorRegion, sentRegion FROM VasLotteryWait "
							+ " WHERE trunc(receiveDate) = trunc(?) AND majorRegion IN "
							+ " (SELECT majorRegion from VasLotteryRegion WHERE regionCode = ? )";
					stmtGetQueue = connection.prepareStatement(strSQL);
					stmtGetQueue.setTimestamp(1, rsMessage.getTimestamp("lotteryDate"));
					stmtGetQueue.setString(2, regionCode);

					rsQueueWait = stmtGetQueue.executeQuery();

					int count = 0;
					int byPassCount = 0;
					while (rsQueueWait.next())
					{
						String sentRegion = rsQueueWait.getString("sentRegion");
						if (sentRegion == null)
							sentRegion = "";

						if (sentRegion.contains(regionCode))
						{
							byPassCount++;
						}
						else
						{
							count++;
							CommandMessage message = new CommandMessage();
							message.setIsdn(rsQueueWait.getString("isdn"));
							message.setProductId(rsQueueWait.getLong("productId"));
							message.setServiceAddress(rsQueueWait.getString("serviceAddress"));
							message.setRequestValue("lottery.regionCode", regionCode);
							message.setRequestValue("lottery.majorRegion", rsQueueWait.getString("majorRegion"));
							message.setRequest(rsMessage.getString("prize"));
							message.setCommandId(command.getCommandId());
							message.setProvisioningType(command.getProvisioningType());

							QueueFactory.attachCommandRouting(message);
						}
					}

					stmtUpdate = connection.prepareStatement(strSQLUpdate);
					stmtUpdate.setTimestamp(1, rsMessage.getTimestamp("lotteryDate"));
					stmtUpdate.setString(2, regionCode);

					stmtUpdate.executeUpdate();

					debugMonitor("Get " + count + "/" + (count + byPassCount)
							+ " waiter of " + regionCode + ", by pass "
							+ byPassCount + " due to sent when register.");
				}
				catch (Exception e)
				{
					throw e;
				}
				finally
				{
					Database.closeObject(rsQueueWait);
					Database.closeObject(stmtGetQueue);
					Database.closeObject(stmtUpdate);
				}

			}

			logMonitor("Total record is browsed: " + total);

			setLastRunDate(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));

			// mprtParam.setProperty("LastRunDate", getLastRunDate());
			storeConfig();
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			Database.closeObject(stmtGetMessage);
			Database.closeObject(connection);
			lastLoad = System.currentTimeMillis();
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
		System.out.println(Calendar.SATURDAY);
	}
}
