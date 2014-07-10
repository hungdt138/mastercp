package com.crm.subscriber.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.crm.kernel.message.Constants;
import com.crm.question.bean.ContentQuestion;
import com.crm.subscriber.bean.SubscriberOrder;
import com.crm.kernel.sql.Database;
import com.crm.util.DateUtil;
import com.fss.util.AppException;

public class SubscriberOrderImpl {
	/**
	 * TODO performance Test
	 * 
	 */
	// private static long sleepTime = 1000L;

	public static SubscriberOrder getOrder(ResultSet rsOrder) throws Exception {
		SubscriberOrder result = new SubscriberOrder();

		try {
			result.setUserId(rsOrder.getLong("userId"));
			result.setUserName(Database.getString(rsOrder, "userName"));

			result.setSubscriberId(rsOrder.getLong("subscriberId"));
			result.setSubProductId(rsOrder.getLong("subProductId"));
			result.setProductId(rsOrder.getLong("productId"));

			result.setSubscriberType(rsOrder.getInt("subscriberType"));
			result.setIsdn(Database.getString(rsOrder, "isdn"));
			result.setShipTo(Database.getString(rsOrder, "shippingTo"));

			result.setOrderDate(rsOrder.getTime("orderDate"));
			result.setOrderId(rsOrder.getLong("orderId"));
			result.setOrderNo(rsOrder.getString("orderNo"));
			result.setCycleDate(rsOrder.getTimestamp("cycleDate"));

			result.setStatus(rsOrder.getInt("status"));
		} catch (Exception e) {
			throw e;
		}

		return result;
	}

	public static SubscriberOrder getOrder(Connection connection, long orderId,
			Date orderDate) throws Exception {
		PreparedStatement stmtOrder = null;
		ResultSet rsOrder = null;

		SubscriberOrder result = null;

		try {
			String SQL = "Select * From SubscriberOrder Where orderId = ? and orderDate >= trunc(?) and orderDate < (trunc(?) + 1) ";

			stmtOrder = connection.prepareStatement(SQL);
			stmtOrder.setLong(1, orderId);
			stmtOrder.setTimestamp(2, DateUtil.getTimestampSQL(orderDate));
			stmtOrder.setTimestamp(3, DateUtil.getTimestampSQL(orderDate));

			rsOrder = stmtOrder.executeQuery();

			if (rsOrder.next()) {
				result = getOrder(rsOrder);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			Database.closeObject(rsOrder);
			Database.closeObject(stmtOrder);
		}

		return result;
	}

	public static SubscriberOrder getPendingOrder(Connection connection,
			String isdn, long productId, Date orderDate, int duplicateScan)
			throws Exception {
		SubscriberOrder order = null;

		PreparedStatement stmtOrder = null;
		ResultSet rsOrder = null;

		if (duplicateScan <= 0) {
			return null;
		}

		try {
			String SQL = "Select * " + "   From 	SubscriberOrder "
					+ "   Where	isdn = ? and productId = ? and status = ? "
					+ "      	and orderDate >= ? and orderDate <= ? ";

			stmtOrder = connection.prepareStatement(SQL);

			stmtOrder.setString(1, isdn);
			stmtOrder.setLong(2, productId);
			stmtOrder.setInt(3, Constants.ORDER_STATUS_PENDING);

			// stmtOrder.setInt(4, delta);
			stmtOrder.setTimestamp(4, DateUtil.getTimestampSQL(DateUtil
					.addSecond(orderDate, -duplicateScan)));
			Date systemDate = new Date();
			stmtOrder.setTimestamp(5, DateUtil.getTimestampSQL(systemDate));

			rsOrder = stmtOrder.executeQuery();

			if (rsOrder.next()) {
				order = getOrder(rsOrder);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			Database.closeObject(rsOrder);
			Database.closeObject(stmtOrder);
		}

		return order;
	}

	public static boolean isDuplicatedOrder(Connection connection, String isdn,
			long productId, Date orderDate, int duplicateScan) throws Exception {
		if (duplicateScan <= 0) {
			return false;
		} else {
			SubscriberOrder order = getPendingOrder(connection, isdn,
					productId, orderDate, duplicateScan);

			return (order != null);
		}
	}

	public static boolean isDuplicatedOrder(String isdn, long productId,
			Date orderDate, int duplicateScan) throws Exception {
		Connection connection = null;

		try {
			/**
			 * TODO: performance test
			 */
			// Thread.sleep(sleepTime);
			// return false;

			if (duplicateScan <= 0) {
				return false;
			}

			connection = Database.getConnection();

			return isDuplicatedOrder(connection, isdn, productId, orderDate,
					duplicateScan);
		} finally {
			Database.closeObject(connection);
		}
	}

	public static int getRegisteredOrder(Connection connection, String isdn,
			long productId, Date orderDate) throws Exception {
		PreparedStatement stmtOrder = null;
		ResultSet rsOrder = null;

		int total = 0;

		try {
			String SQL = "Select count(*) total "
					+ "   From 	SubscriberOrder "
					+ "   Where	isdn = ? and productId = ? and status = ? and orderType in (?, ?, ?) "
					+ "      	and orderDate >= trunc(?) and orderDate < (trunc(?) + 1) ";

			// String SQL = "Select count(*) total "
			// + "   From 	SubscriberOrder "
			// +
			// "   Where	isdn = ? and productId = ? and status = ? and orderType in (?, ?, ?) "
			// +
			// "      	and orderDate >= trunc(to_date(?, 'DD/MM/SYYYY HH24:MI:SS')) "
			// +
			// " 		and orderDate < (trunc(to_date(?, 'DD/MM/SYYYY HH24:MI:SS')) + 1) ";

			stmtOrder = connection.prepareStatement(SQL);

			// SimpleDateFormat sdf = new
			// SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			stmtOrder.setString(1, isdn);
			stmtOrder.setLong(2, productId);
			stmtOrder.setInt(3, Constants.ORDER_STATUS_APPROVED);
			stmtOrder.setString(4, Constants.ACTION_REGISTER);
			stmtOrder.setString(5, Constants.ACTION_TOPUP);
			stmtOrder.setString(6, Constants.ACTION_UPGRADE);
			// stmtOrder.setString(7, sdf.format(orderDate));
			// stmtOrder.setString(8, sdf.format(orderDate));
			stmtOrder.setTimestamp(7, DateUtil.getTimestampSQL(orderDate));
			stmtOrder.setTimestamp(8, DateUtil.getTimestampSQL(orderDate));

			rsOrder = stmtOrder.executeQuery();

			if (rsOrder.next()) {
				total = rsOrder.getInt("total");
			}
		} catch (Exception e) {
			throw e;
		} finally {
			Database.closeObject(rsOrder);
			Database.closeObject(stmtOrder);
		}

		return total;
	}

	public static int getRegisteredOrder(String isdn, long productId,
			Date orderDate) throws Exception {
		Connection connection = null;

		try {
			/**
			 * TODO: For performance test
			 */
			// Thread.sleep(sleepTime);
			// return 0;
			connection = Database.getConnection();

			return getRegisteredOrder(connection, isdn, productId, orderDate);
		} finally {
			Database.closeObject(connection);
		}
	}

	public static SubscriberOrder createOrder(Connection connection,
			long userId, String userName, Date orderDate, String orderType,
			long subscriberId, String isdn, int subscriberType,
			long subProductId, long productId, long merchantId, double price,
			int quantity, double discount, double amount, double score,
			String cause, int status, String channel, int contentType,
			int contentCode, int opId, int orderNo, String content, long agentId)
			throws Exception {
		SubscriberOrder order = null;

		PreparedStatement stmtOrder = null;

		try {
			Date now = new Date();
			Date cycleDate = SubscriberEntryImpl.getCycleDate(now);

			long orderId = Database.getSequence(connection, "order_seq");

			String SQL = "Insert into SubscriberOrder "
					+ "		(orderId, userId, userName, createDate, modifiedDate, orderType, orderDate, cycleDate "
					+ "		, subscriberId, subProductId, productId, isdn, subscriberType "
					+ "		, offerPrice, price, quantity, discount, amount, score, status, cause, channel, merchantId, contentType, contentCode, telcoid, orderNo, shippingto, agentId, exportstatus) "
					+ " Values " + "		(?, ?, ?, sysDate, sysDate, ?, ?, ? "
					+ "		, ?, ?, ?, ?, ? "
					+ "		, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?,1) ";

			stmtOrder = connection.prepareStatement(SQL);

			stmtOrder.setLong(1, orderId);
			stmtOrder.setLong(2, userId);
			stmtOrder.setString(3, userName);

			stmtOrder.setString(4, orderType);
			stmtOrder.setTime(5, DateUtil.getTimeSQL(orderDate));
			stmtOrder.setTimestamp(6, DateUtil.getTimestampSQL(cycleDate));

			stmtOrder.setLong(7, subscriberId);
			stmtOrder.setLong(8, subProductId);
			stmtOrder.setLong(9, productId);
			stmtOrder.setString(10, isdn);
			stmtOrder.setInt(11, subscriberType);

			stmtOrder.setDouble(12, 0);
			stmtOrder.setDouble(13, price);
			stmtOrder.setDouble(14, quantity);
			stmtOrder.setDouble(15, discount);
			stmtOrder.setDouble(16, amount);
			stmtOrder.setDouble(17, score);

			stmtOrder.setInt(18, status);
			stmtOrder.setString(19, cause);
			stmtOrder.setString(20, channel);
			stmtOrder.setLong(21, merchantId);
			stmtOrder.setInt(22, contentType);
			stmtOrder.setInt(23, contentCode);
			stmtOrder.setInt(24, opId);
			stmtOrder.setInt(25, orderNo);
			stmtOrder.setString(26, content);
			stmtOrder.setLong(27, agentId);
			stmtOrder.execute();

			// bind order
			order = new SubscriberOrder();

			order.setUserId(userId);
			order.setUserName(userName);

			order.setSubscriberId(subscriberId);
			order.setSubProductId(subProductId);
			order.setProductId(productId);

			order.setSubscriberType(subscriberType);
			order.setIsdn(isdn);
			// order.setShipTo(shipTo);

			order.setOrderDate(now);
			order.setOrderId(orderId);
			// order.setOrderNo(orderNo);
			order.setCycleDate(cycleDate);

			order.setStatus(status);
		} catch (Exception e) {
			throw e;
		} finally {
			Database.closeObject(stmtOrder);
		}

		return order;
	}

	public static SubscriberOrder createOrder(long userId, String userName,
			Date orderDate, String orderType, long subscriberId, String isdn,
			int subscriberType, long subProductId, long productId,
			long merchantId, double price, int quantity, double discount,
			double amount, double score, String cause, int status,
			int contentType, int contentCode, int opId, int orderNo,
			String content, long agentId) throws Exception {
		return createOrder(userId, userName, orderDate, orderType,
				subscriberId, isdn, subscriberType, subProductId, productId,
				merchantId, price, quantity, discount, amount, score, cause,
				status, "", contentType, contentCode, opId, orderNo, content, agentId);

	}

	public static SubscriberOrder createOrder(long userId, String userName,
			Date orderDate, String orderType, long subscriberId, String isdn,
			int subscriberType, long subProductId, long productId,
			long merchantId, double price, int quantity, double discount,
			double amount, double score, String cause, int status,
			String channel, int contentType, int contentCode, int opId,
			int orderNo, String content, long agentId) throws Exception {
		Connection connection = null;

		try {
			/**
			 * TODO: performance test
			 */

			// Thread.sleep(sleepTime);
			// SubscriberOrder order = new SubscriberOrder();
			// order.setOrderId(Constants.DEFAULT_ID);
			// order.setOrderDate(new Date());
			// return order;

			connection = Database.getConnection();

			return createOrder(connection, userId, userName, orderDate,
					orderType, subscriberId, isdn, subscriberType,
					subProductId, productId, merchantId, price, quantity,
					discount, amount, score, cause, status, channel,
					contentType, contentCode, opId, orderNo, content, agentId);
		} finally {
			Database.closeObject(connection);
		}
	}

	public static void updateOrder(Connection connection, long orderId,
			Date orderDate, long subscriberId, long subProductId, double price,
			int quantity, double discount, double amount, double score,
			int status, String cause, String description) throws Exception {
		PreparedStatement stmtOrder = null;

		try {
			String sql = "Update SubscriberOrder "
					+ "   Set 	modifiedDate = sysDate, subscriberId = ?, subProductId = ? "
					+ "   		, price = ?, quantity = ?, discount = ?, amount = ?, score = ? "
					+ "   		, status = ?, cause = ?, description = ? "
					+ "Where orderDate >= trunc(?) and orderDate < (trunc(?) + 1) and orderId = ? ";

			stmtOrder = connection.prepareStatement(sql);

			stmtOrder.setLong(1, subscriberId);
			stmtOrder.setLong(2, subProductId);

			stmtOrder.setDouble(3, price);
			stmtOrder.setInt(4, quantity);
			stmtOrder.setDouble(5, discount);
			stmtOrder.setDouble(6, amount);
			stmtOrder.setDouble(7, score);

			stmtOrder.setInt(8, status);
			stmtOrder.setString(9, cause);
			stmtOrder.setString(10, description);

			stmtOrder.setTimestamp(11, DateUtil.getTimestampSQL(orderDate));
			stmtOrder.setTimestamp(12, DateUtil.getTimestampSQL(orderDate));
			stmtOrder.setLong(13, orderId);

			stmtOrder.execute();

			if (stmtOrder.getUpdateCount() == 0) {
				throw new AppException(Constants.ERROR_ORDER_NOT_FOUND);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			Database.closeObject(stmtOrder);
		}
	}

	public static void updateStatus(Connection connection, long orderId,
			Date orderDate, int status, String cause, String description)
			throws Exception {
		PreparedStatement stmtOrder = null;

		try {
			String sql = "Update SubscriberOrder "
					+ "   Set 	modifiedDate = sysDate, status = ?, cause = ?, description = ? "
					+ "Where orderDate >= trunc(?) and orderDate < (trunc(?) + 1) and orderId = ? ";

			stmtOrder = connection.prepareStatement(sql);

			stmtOrder.setInt(1, status);
			stmtOrder.setString(2, cause);
			stmtOrder.setString(3, description);

			stmtOrder.setTimestamp(4, DateUtil.getTimestampSQL(orderDate));
			stmtOrder.setTimestamp(5, DateUtil.getTimestampSQL(orderDate));
			stmtOrder.setLong(6, orderId);

			stmtOrder.execute();
		} catch (Exception e) {
			throw e;
		} finally {
			Database.closeObject(stmtOrder);
		}
	}

	public static void updateStatus(long orderId, Date orderDate, int status,
			String cause, String description) throws Exception {
		Connection connection = null;

		try {
			/**
			 * TODO: performance test
			 */

			// Thread.sleep(sleepTime);

			connection = Database.getConnection();

			updateStatus(connection, orderId, orderDate, status, cause,
					description);
		} catch (Exception e) {
			throw e;
		} finally {
			Database.closeObject(connection);
		}
	}

	// HungDT

	public static void updateDesc(Connection connection, long orderId,
			String desc, Date orderDate) throws Exception {
		PreparedStatement stmtOrder = null;

		try {
			String sql = "Update SubscriberOrder "
					+ "   Set cpurlrequest = ? "
					+ "Where orderDate >= trunc(?) and orderDate < (trunc(?) + 1) and orderId = ? ";

			stmtOrder = connection.prepareStatement(sql);

			stmtOrder.setString(1, desc);
			stmtOrder.setTimestamp(2, DateUtil.getTimestampSQL(orderDate));
			stmtOrder.setTimestamp(3, DateUtil.getTimestampSQL(orderDate));
			stmtOrder.setLong(4, orderId);

			stmtOrder.execute();
		} catch (Exception e) {
			throw e;
		} finally {
			Database.closeObject(stmtOrder);
		}
	}

	public static void updateExportStatus(int status, long orderId)
			throws Exception {
		PreparedStatement stmtOrder = null;

		Connection connection = null;
		try {
			connection = Database.getConnection();

			String sql = "Update SubscriberOrder Set exportStatus = ? Where orderId = ? ";

			stmtOrder = connection.prepareStatement(sql);

			stmtOrder.setInt(1, status);
			stmtOrder.setLong(2, orderId);

			stmtOrder.execute();
		} catch (Exception e) {
			throw e;
		} finally {
			Database.closeObject(stmtOrder);
			Database.closeObject(connection);
		}
	}

	public static void updateDeliveryCouter(Connection connection,
			long orderId, int deliveryCouter, Date orderDate, long agentId)
			throws Exception {
		PreparedStatement stmtOrder = null;

		try {
			String sql = "Update SubscriberOrder "
					+ "   Set deliveryCounter = ?, agentId = ?"
					+ "Where orderDate >= trunc(?) and orderDate < (trunc(?) + 1) and orderId = ? ";

			stmtOrder = connection.prepareStatement(sql);

			stmtOrder.setInt(1, deliveryCouter);
			stmtOrder.setLong(2, agentId);
			stmtOrder.setTimestamp(3, DateUtil.getTimestampSQL(orderDate));
			stmtOrder.setTimestamp(4, DateUtil.getTimestampSQL(orderDate));
			stmtOrder.setLong(5, orderId);

			stmtOrder.execute();
		} catch (Exception e) {
			throw e;
		} finally {
			Database.closeObject(stmtOrder);
		}
	}

	/**
	 * Cáº­p nháº­t ID cáº©u há»�i
	 * 
	 * @param orderId
	 * @param questionId
	 * @throws Exception
	 */
	public static void updateQuestionId(long orderId, long questionId)
			throws Exception {
		PreparedStatement stmtOrder = null;
		Connection connection = null;
		try {

			String sql = "Update SubscriberOrder set questionId = ?, MODIFIEDDATE = sysdate where orderId = ?";
			connection = Database.getConnection();
			stmtOrder = connection.prepareStatement(sql);
			stmtOrder.setLong(1, questionId);
			stmtOrder.setLong(2, orderId);

			stmtOrder.executeUpdate();

		} catch (Exception e) {
			throw e;
		} finally {
			Database.closeObject(stmtOrder);
			Database.closeObject(connection);
		}
	}

	public static List<ContentQuestion> getListQuestionIdFromOrder(String isdn,
			long productId) throws Exception {
		ContentQuestion content = null;
		PreparedStatement stmtOrder = null;
		ResultSet rsOrder = null;
		Connection connection = null;
		List<ContentQuestion> lst = new ArrayList<ContentQuestion>();
		try {
			String sql = "select A.* from productContent A, SubscriberOrder B where A.contentId = B.questionId and B.isdn = ? and (B.ordertype = ? "
					+ "or B.ordertype = ?) and B.productId = ? and B.questionId is not null and B.questionId <> 0"
					+ " order by B.orderDate desc";
			connection = Database.getConnection();
			stmtOrder = connection.prepareStatement(sql);
			stmtOrder.setString(1, isdn);
			stmtOrder.setString(2, Constants.ACTION_REGISTER);
			stmtOrder.setString(3, Constants.ACTION_ANSWER);
			stmtOrder.setLong(4, productId);
			rsOrder = stmtOrder.executeQuery();

			while (rsOrder.next()) {
				content = new ContentQuestion();
				content.setContentId(rsOrder.getLong("contentId"));
				content.setContent(rsOrder.getString("content"));
				content.setAnswer(rsOrder.getString("answer"));
				lst.add(content);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			Database.closeObject(stmtOrder);
			Database.closeObject(connection);
			Database.closeObject(rsOrder);
		}

		return lst;
	}
	
	//check 15th MO
	public static List<ContentQuestion> getListQuestionIdFromOrder(String isdn) throws Exception {
		ContentQuestion content = null;
		PreparedStatement stmtOrder = null;
		ResultSet rsOrder = null;
		Connection connection = null;
		List<ContentQuestion> lst = new ArrayList<ContentQuestion>();
		try {
			String sql = "select A.* from productContent A, SubscriberOrder B where A.contentId = B.questionId and B.isdn = ? and (B.ordertype = ? "
					+ "or B.ordertype = ?) and B.questionId is not null and B.questionId <> 0 and B.createDate >= trunc(sysdate)"
					+ "order by B.orderDate desc";
			connection = Database.getConnection();
			stmtOrder = connection.prepareStatement(sql);
			stmtOrder.setString(1, isdn);
			stmtOrder.setString(2, Constants.ACTION_REGISTER);
			stmtOrder.setString(3, Constants.ACTION_ANSWER);
			
			rsOrder = stmtOrder.executeQuery();

			while (rsOrder.next()) {
				content = new ContentQuestion();
				content.setContentId(rsOrder.getLong("contentId"));
				content.setContent(rsOrder.getString("content"));
				content.setAnswer(rsOrder.getString("answer"));
				lst.add(content);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			Database.closeObject(stmtOrder);
			Database.closeObject(connection);
			Database.closeObject(rsOrder);
		}

		return lst;
	}

	public static int getTotalQuestion(String isdn, long productId)
			throws Exception {
		int count = 0;
		PreparedStatement stmtOrder = null;
		ResultSet rsOrder = null;
		Connection connection = null;
		try {
			String sql = "select count(*) from SubscriberOrder where isdn = ? and (ordertype = ? or ordertype = ?) and productId = ? and status = 0 and orderDate = trunc(sysdate)";
			connection = Database.getConnection();
			stmtOrder = connection.prepareStatement(sql);
			stmtOrder.setString(1, isdn);
			stmtOrder.setString(2, Constants.ACTION_ANSWER);
			stmtOrder.setString(3, Constants.ACTION_REGISTER);
			stmtOrder.setLong(4, productId);

			rsOrder = stmtOrder.executeQuery();

			if (rsOrder.next()) {
				count = rsOrder.getInt(1);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			Database.closeObject(stmtOrder);
			Database.closeObject(connection);
			Database.closeObject(rsOrder);
		}

		return count;
	}

	public static long getQuestionId(String isdn, long productId)
			throws Exception {
		long qId = 0;
		PreparedStatement stmtOrder = null;
		ResultSet rsOrder = null;
		Connection connection = null;
		try {
			String sql = "select * from (select * from SubscriberOrder where isdn = ? and (ordertype = ? or ordertype = ?)"
					+ " and productId = ? and questionId is not null and questionId <> 0 order by MODIFIEDDATE desc) where rownum = 1";
			connection = Database.getConnection();
			stmtOrder = connection.prepareStatement(sql);
			stmtOrder.setString(1, isdn);
			stmtOrder.setString(2, Constants.ACTION_ANSWER);
			stmtOrder.setString(3, Constants.ACTION_REGISTER);
			stmtOrder.setLong(4, productId);
			rsOrder = stmtOrder.executeQuery();

			while (rsOrder.next()) {
				qId = rsOrder.getLong("questionId");

			}
		} catch (Exception e) {
			throw e;
		} finally {
			Database.closeObject(stmtOrder);
			Database.closeObject(connection);
			Database.closeObject(rsOrder);
		}

		return qId;
	}

	public static void updateDeliveryStatus(long orderId, String status, String sId)
			throws Exception {
		PreparedStatement stmtOrder = null;
		Connection connection = null;
		try {

			String sql = "Update SubscriberOrder set delivery_status = ?, TELCOSSERVICEID = ?, MODIFIEDDATE = sysdate, EXPORTSTATUS = 1 where orderId = ?";
			connection = Database.getConnection();
			stmtOrder = connection.prepareStatement(sql);
			stmtOrder.setString(1, status);
			stmtOrder.setString(2, sId);
			stmtOrder.setLong(3, orderId);

			stmtOrder.executeUpdate();

		} catch (Exception e) {
			throw e;
		} finally {
			Database.closeObject(stmtOrder);
			Database.closeObject(connection);
		}
	}

}
