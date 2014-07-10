package com.crm.product.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.crm.kernel.message.Constants;
import com.crm.kernel.sql.Database;
import com.crm.product.cache.ProductRoute;
import com.crm.product.impl.OrderRoutingImpl;
import com.crm.provisioning.message.CommandMessage;
import com.crm.provisioning.thread.OrderRoutingInstance;
import com.crm.provisioning.util.CommandUtil;
import com.crm.subscriber.impl.SubscriberOrderImpl;
import com.fss.util.AppException;

public class QuizRoutingImpl extends OrderRoutingImpl {
	public CommandMessage parser(OrderRoutingInstance instance, ProductRoute orderRoute, CommandMessage order) throws Exception
	{
		Connection connection = null;

		PreparedStatement stmtQuizContent = null;
		ResultSet rsQuizContent = null;

		PreparedStatement stmtQuizRegister = null;
		PreparedStatement stmtQuizUnregister = null;
		PreparedStatement stmtQuizStatus = null;
		PreparedStatement stmtQuizScore = null;
		ResultSet rsQuizStatus = null;

		String actionType = "answer";
		String cause = "";
		String isdn = order.getIsdn();
		String quizContent = "";
		String answer = "";
		long productId = order.getProductId();
		long quizId = 0;
		long lastContentId = 0;
		long contentId = 0;
		int earnScore = 0;
		int totalScore = 0;

		try
		{
			super.smsParser(instance, orderRoute, order);

			connection = Database.getConnection();

			String SQL = "Select A.*, B.score answer_score "
					+ " From SubscriberQuiz A, ProductContent B "
					+ " Where (A.contentId = B.contentId) "
					+ " and isdn = ? and A.productId = ? and trunc(expireDate) >= trunc(sysdate) "
					+ " Order by expireDate desc";

			stmtQuizStatus = connection.prepareStatement(SQL);
			stmtQuizStatus.setString(1, isdn);
			stmtQuizStatus.setLong(2, productId);

			rsQuizStatus = stmtQuizStatus.executeQuery();

			if (rsQuizStatus.next())
			{
				quizId = rsQuizStatus.getLong("quizId");
				totalScore = rsQuizStatus.getInt("score");

				lastContentId = rsQuizStatus.getLong("contentId");
				contentId = lastContentId;

				if (rsQuizStatus.getInt("counter") > 15)
				{
					cause = "over-limit";
				}
				else
				{
					cause = "wrong";
					int paramCount = order.getParameters().getInt("sms.params.count");

					if (paramCount > 0)
					{
						for (int j = 0; j < paramCount; j++)
						{
							answer += (" " + order.getParameters().getString("sms.params[" + j + "]"));
						}
						answer = answer.trim();

						if (rsQuizStatus.getString("answer").equalsIgnoreCase(answer))
						{
							earnScore = rsQuizStatus.getInt("answer_score");
							totalScore = rsQuizStatus.getInt("score") + earnScore;

							// send next question

							contentId = 0;
							actionType = "next";
						}
					}
				}
			}
			else
			{
				actionType = "register";
			}

			if (contentId == 0)
			{
				SQL = "Select * From ProductContent Where contentId > ? "
						+ " Union "
						+ " Select * From ProductContent Where contentId < ?  ";

				stmtQuizContent = connection.prepareStatement(SQL);
				stmtQuizContent.setLong(1, lastContentId);
				stmtQuizContent.setLong(2, lastContentId);

				rsQuizContent = stmtQuizContent.executeQuery();

				if (rsQuizContent.next())
				{
					contentId = rsQuizContent.getLong("contentId");
					quizContent = rsQuizContent.getString("content");
					answer = rsQuizContent.getString("answer");
				}
			}
			else
			{
				SQL = "Select * From ProductContent Where contentId = ? ";

				stmtQuizContent = connection.prepareStatement(SQL);
				stmtQuizContent.setLong(1, contentId);

				rsQuizContent = stmtQuizContent.executeQuery();

				if (rsQuizContent.next())
				{
					quizContent = rsQuizContent.getString("content");
					answer = rsQuizContent.getString("answer");
				}
			}

			if (actionType.equals("register"))
			{
//				SubscriberOrderImpl.createOrder(
//						connection, order.getUserId(), order.getUserName(), order.getOrderDate(), order.getActionType()
//						, order.getSubscriberId(), order.getIsdn(), order.getSubscriberType()
//						, order.getSubProductId(), order.getProductId(), order.getMerchantId()
//						, order.getPrice(), order.getQuantity(), order.getDiscount(), order.getAmount(), order.getScore()
//						, order.getCause(), order.getStatus(), order.getChannel(), order.getContentType(), order.getContentCode(), order.getOpId(),
//						(int) order.getRequestId());

				SQL = "Insert Into SubscriberQuiz "
						+ " (quizId, userId, userName, createDate, modifiedDate"
						+ " , isdn, productId, contentId, answer, expireDate) "
						+ " Values "
						+ " (ORDER_SEQ.nextval, ?, ?, sysdate, sysdate, ?, ?, ?, ?, trunc(sysdate) + 1 - 1/86400) ";

				stmtQuizRegister = connection.prepareStatement(SQL);

				stmtQuizRegister.setLong(1, order.getUserId());
				stmtQuizRegister.setString(2, order.getUserName());
				stmtQuizRegister.setString(3, isdn);
				stmtQuizRegister.setLong(4, productId);
				stmtQuizRegister.setLong(5, contentId);
				stmtQuizRegister.setString(6, answer);

				stmtQuizRegister.execute();
			}
			else
			{
				SQL = "Update SubscriberQuiz Set contentId = ?, answer = ?, score = nvl(score, 0) + ? Where quizId = ? ";

				stmtQuizScore = connection.prepareStatement(SQL);
				stmtQuizScore.setLong(1, contentId);
				stmtQuizScore.setString(2, answer);
				stmtQuizScore.setInt(3, earnScore);
				stmtQuizScore.setLong(4, quizId);

				stmtQuizScore.execute();
			}

			if (actionType.equals("register"))
			{
				CommandUtil.sendSMS(instance, order, "Ban se nhan duoc noi dung dich vu va cau hoi mien phi de 'Tich diem trung iPod sanh dieu'. Tra loi dung duoc 10d, sai khong co diem. Ap dung theo T&C");
				CommandUtil.sendSMS(instance, order, "Link dich vu: : http://sp.teebik.com/download/Mobile_Security_6.8.02.32.apk");
			}
			else if (actionType.equals("next"))
			{
				quizContent = "Dung, ban co "+totalScore+"d. CH tiep: " + quizContent;
			}
			else if (actionType.equals("over-limit"))
			{
				quizContent = "Ban da vuot qua so lan duoc phep tham du trong ngay ";
			}
			else if (actionType.equals("unregister"))
			{
				CommandUtil.sendSMS(instance, order, "Dich vu da duoc khoa, cam on ban da tham gia. Ban co muon so huu iPod sanh dieu khong? Gui GET den 6619 ngay ban nhe!");
			}
			else
			{
				quizContent = "Sai, ban co "+totalScore+"d. Cau hoi la: " + quizContent;
			}
			
			
			if(quizContent != null)
			{
				CommandUtil.sendSMS(instance, order, quizContent);
			}
			
			
					
			
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			Database.closeObject(stmtQuizContent);
			Database.closeObject(rsQuizContent);

			Database.closeObject(stmtQuizRegister);
			Database.closeObject(stmtQuizUnregister);
			Database.closeObject(stmtQuizStatus);
			Database.closeObject(stmtQuizScore);
			Database.closeObject(rsQuizStatus);

			Database.closeObject(connection);
		}
		
		return order;
	}
}
