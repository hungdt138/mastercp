/**
 * 
 */
package com.crm.question.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.crm.kernel.message.Constants;
import com.crm.kernel.sql.Database;
import com.crm.question.bean.ContentQuestion;
import com.crm.subscriber.impl.SubscriberOrderImpl;
import com.fss.util.AppException;

/**
 * @author HungDT
 * 
 */
public class ContentQuestionImpl
{

	public static ContentQuestion getContentQuestion(String isdn, long productId)
			throws Exception
	{
		ContentQuestion content = null;
		Connection connection = null;
		PreparedStatement stmtConetnt = null;
		ResultSet rsContent = null;
		List<ContentQuestion> lst = new ArrayList<ContentQuestion>();
		try
		{
			String sql = "select * from productContent where productid = ? and type = 'quiz' and contentId not in (select B.questionId from SubscriberOrder B where  B.isdn = ? and (B.ordertype = 'register' or B.ordertype = 'answer' or B.ordertype = 'registered' or B.ordertype = 'advertising') and B.productId = ? and B.questionId is not null and B.questionId <> 0)order by contentId asc";

			connection = Database.getConnection();
			stmtConetnt = connection.prepareStatement(sql);
			stmtConetnt.setLong(1, productId);
			stmtConetnt.setString(2, isdn);
			stmtConetnt.setLong(3, productId);

			rsContent = stmtConetnt.executeQuery();
			while (rsContent.next())
			{
				content = new ContentQuestion();

				content.setContentId(rsContent.getLong("contentId"));
				content.setContent(rsContent.getString("content"));
				content.setAnswer(rsContent.getString("answer"));
				lst.add(content);
			}

			if (lst.size() == 0)
			{
				throw new AppException("question-not-found");
			}

			content = (ContentQuestion) lst.get(0);
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			Database.closeObject(stmtConetnt);
			Database.closeObject(connection);
			Database.closeObject(rsContent);
		}

		return content;
	}

	public static ContentQuestion getContent(String isdn, long productId)
			throws Exception
	{
		ContentQuestion content = null;
		Connection connection = null;
		PreparedStatement stmtConetnt = null;
		ResultSet rsContent = null;
		try
		{
			String sql = "select * from productContent where productid = ? and type = 'content'";
			connection = Database.getConnection();
			stmtConetnt = connection.prepareStatement(sql);
			stmtConetnt.setLong(1, productId);

			rsContent = stmtConetnt.executeQuery();
			while (rsContent.next())
			{
				content = new ContentQuestion();

				content.setContentId(rsContent.getLong("contentId"));
				content.setContent(rsContent.getString("content"));
				content.setAnswer(rsContent.getString("answer"));
			}

		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			Database.closeObject(stmtConetnt);
			Database.closeObject(connection);
			Database.closeObject(rsContent);
		}

		return content;
	}

	public static ContentQuestion getContentById(long contentId)
			throws Exception
	{
		ContentQuestion content = null;
		Connection connection = null;
		PreparedStatement stmtConetnt = null;
		ResultSet rsContent = null;

		try
		{
			String sql = "select * from productContent where contentId = ? and type = 'quiz'";
			connection = Database.getConnection();
			stmtConetnt = connection.prepareStatement(sql);
			stmtConetnt.setLong(1, contentId);

			rsContent = stmtConetnt.executeQuery();
			if (rsContent.next())
			{
				content = new ContentQuestion();

				content.setContentId(rsContent.getLong("contentId"));
				content.setContent(rsContent.getString("content"));
				content.setAnswer(rsContent.getString("answer"));
			}

		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			Database.closeObject(stmtConetnt);
			Database.closeObject(connection);
			Database.closeObject(rsContent);
		}

		return content;
	}

	public static boolean checkQuestion(long questionId, String answers)
			throws Exception
	{
		boolean check = false;
		Connection connection = null;
		PreparedStatement stmtConetnt = null;
		ResultSet rsContent = null;
		try
		{
			String sql = "select * from productContent where contentId = ? and type = 'quiz'";
			connection = Database.getConnection();
			stmtConetnt = connection.prepareStatement(sql);
			stmtConetnt.setLong(1, questionId);
			rsContent = stmtConetnt.executeQuery();
			if (rsContent.next())
			{
				String trueAnswers = rsContent.getString("answer");
				if (trueAnswers.equalsIgnoreCase(answers))
				{
					check = true;
				}
			}
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			Database.closeObject(stmtConetnt);
			Database.closeObject(connection);
			Database.closeObject(rsContent);
		}
		return check;
	}

	public static void insertCommandRequest(String username, String channel, String serviceAddress, String isdn, String keyword) throws Exception
	{
		Connection connection = null;
		PreparedStatement stmtContent = null;
		try
		{
			String sql = "insert into commandRequest(requestId, username, createdate, requestDate, channel, serviceAddress, isdn, keyword) " +
							"values(command_seq.nextval,?,sysdate,sysdate, ?, ?, ?, ?)";
			connection = Database.getConnection();
			stmtContent = connection.prepareStatement(sql);
			stmtContent.setString(1, username);
			stmtContent.setString(2, channel);
			stmtContent.setString(3, serviceAddress);
			stmtContent.setString(4, isdn);
			stmtContent.setString(5, keyword);

			stmtContent.execute();
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			Database.closeObject(stmtContent);
			Database.closeObject(connection);
		}
	}

	public static boolean checkExistInCommand(String isdn, String channel, String serviceAddress, String keyword) throws Exception
	{
		boolean check = false;
		Connection connection = null;
		PreparedStatement stmtContent = null;
		ResultSet rsContent = null;
		try
		{
			String sql = "select count(*) from commandRequest where isdn = ? and channel=? and serviceAddress = ? and keyword = ?";
			connection = Database.getConnection();
			stmtContent = connection.prepareStatement(sql);
			stmtContent.setString(1, isdn);
			stmtContent.setString(2, channel);
			stmtContent.setString(3, serviceAddress);
			stmtContent.setString(4, keyword);
			rsContent = stmtContent.executeQuery();
			if (rsContent.next())
			{
				int count = rsContent.getInt(1);
				if (count > 0)
				{
					check = true;
				}
			}
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			Database.closeObject(stmtContent);
			Database.closeObject(connection);
		}
		return check;
	}

	public static void main(String[] args) throws Exception
	{
		String isdn = "84967289990";
		long productId = 1110;
		ContentQuestion content = ContentQuestionImpl.getContentQuestion(isdn, productId);
	}
}
