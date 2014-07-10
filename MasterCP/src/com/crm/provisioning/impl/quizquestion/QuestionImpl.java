/**
 * 
 */
package com.crm.provisioning.impl.quizquestion;

import java.util.List;

import org.apache.log4j.Logger;

import com.crm.kernel.message.Constants;
import com.crm.product.cache.ProductEntry;
import com.crm.product.cache.ProductFactory;
import com.crm.provisioning.cache.ProvisioningCommand;
import com.crm.provisioning.impl.CommandImpl;
import com.crm.provisioning.impl.mt.ChargingCommandImpl;
import com.crm.provisioning.message.CommandMessage;
import com.crm.provisioning.thread.CommandInstance;
import com.crm.provisioning.util.CommandUtil;
import com.crm.provisioning.util.ResponseUtil;
import com.crm.question.bean.ContentQuestion;
import com.crm.question.impl.ContentQuestionImpl;
import com.crm.subscriber.bean.SubscriberProduct;
import com.crm.subscriber.impl.SubscriberOrderImpl;
import com.crm.subscriber.impl.SubscriberProductImpl;
import com.fss.util.AppException;

/**
 * @author HungDT
 * 
 */
public class QuestionImpl extends CommandImpl
{

	private static Logger	log	= Logger.getLogger(ChargingCommandImpl.class);

	public CommandMessage checkAnswers(CommandInstance instance,
			ProvisioningCommand provisioningCommand, CommandMessage request)
			throws Exception
	{
		CommandMessage result = request;
		ProductEntry product = ProductFactory.getCache().getProduct(result.getProductId());

		String answer = "";
//		int paramCount = result.getParameters().getInt("sms.params.count");
//		if (paramCount > 0)
//		{
//			for (int j = 0; j < paramCount; j++)
//			{
//				answer += (" " + result.getParameters().getString(
//						"sms.params[" + j + "]"));
//			}
//			answer = answer.trim();
//		}
		
		String[] split = request.getKeyword().split("_");
		answer = split[1];

		try
		{
			long qId = SubscriberOrderImpl.getQuestionId(result.getIsdn(), result.getProductId());
			SubscriberProduct subscriberProduct = null;

			// get current subscriber product
			if (!product.isSubscription())
			{

			}
			else if (result.getSubProductId() == Constants.DEFAULT_ID)
			{
				subscriberProduct = SubscriberProductImpl.getActive(result
						.getIsdn(), result.getProductId());
			}
			else
			{
				subscriberProduct = SubscriberProductImpl.getProduct(result
						.getSubProductId());
			}
			
			if(subscriberProduct == null)
			{
				throw new AppException(Constants.ERROR_REGISTERED);
			}

			if (ContentQuestionImpl.checkQuestion(qId, answer))
			{
				result.setActionType(Constants.ACTION_TRUE_ANSWER);
				// Tra loi dung, cong diem cho khach hang
				int score = SubscriberProductImpl.updateScore(result.getUserId(), result.getUserName(), subscriberProduct.getSubProductId(), product
						.getParameters().getInteger("quiz.score", 10));
				result.setScore(score);

			}
			else
			{
				// Tra loi sai khong cong diem
				result.setActionType(Constants.ACTION_WRONG_ANSWER);
				int score = SubscriberProductImpl.updateScore(result.getUserId(), result.getUserName(), subscriberProduct.getSubProductId(), 0);
				result.setScore(score);
			}

			// check so luong cau hoi da choi trong 1 ngay
			List<ContentQuestion> lstQId = SubscriberOrderImpl.getListQuestionIdFromOrder(result.getIsdn());
			if (lstQId.size() >= 15)
			{
				result.getParameters().setBoolean("isover", true);

				if (result.getActionType().equals(Constants.ACTION_TRUE_ANSWER))
				{
					result.getParameters().setString("answer", "Dung. ");
				}
				else
				{
					result.getParameters().setString("answer", "Sai. ");
				}

				result.setActionType(Constants.ACTION_ORVER_QUESTION_DAY);
			}

			// Neu chua choi qua 15 cau hoi thi lay tiep cau hoi gui cho khach hang
			ContentQuestion content = ContentQuestionImpl.getContentQuestion(result.getIsdn(), result.getProductId());

			// Neu content = null tuc la het cau hoi, thong bao cho khach hang da
			// tra loi het so cau hoi.
			if (content == null)
			{
				result.setActionType(Constants.ACTION_ORVER_QUESTION);
				result.setCause(Constants.SUCCESS);
				result.setStatus(0);
			}
			result.getParameters().setString("quiz.content", content.getContent());
			result.getParameters().setLong("quiz.id", content.getContentId());
			
			if(result.getSessionId() == 0)
			{
				SubscriberProduct subProduct = SubscriberProductImpl.getProduct(result
						.getSubProductId());
				
				result.setSessionId(subProduct.getSessionId());
			}
			
			SubscriberOrderImpl.updateQuestionId(request.getOrderId(), content.getContentId());
		}
		catch (Exception e)
		{
			throw e;
		}
		
		return result;
	}

	/**
	 * Truong hop khach hang dang ki, doi voi cac dich vu can send content.
	 * 
	 * @param instance
	 * @param provisioningCommand
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public CommandMessage sendFirstContent(CommandInstance instance,
			ProvisioningCommand provisioningCommand, CommandMessage request)
			throws Exception
	{

		CommandMessage result = request;
		ContentQuestion content = ContentQuestionImpl.getContentQuestion(
				result.getIsdn(), result.getProductId());
		ContentQuestion contentFirst = ContentQuestionImpl.getContent(result.getIsdn(), result.getProductId());
		ProductEntry product = null;
		try
		{
			try
			{
				product = ProductFactory.getCache().getProduct(
						result.getProductId());
			}
			catch (Exception e)
			{
				instance.logMonitor(e);
			}
			String strContent = content.getContent();
			strContent = ResponseUtil.formatResponse(instance, product, result,
					result.getActionType(), strContent);

			result.getParameters().setBoolean("quiz.isfirst", true);
			result.getParameters().setString("quiz.content1", content.getContent());
			
			
			result.getParameters().setInteger("vt8x26.iscdr", 0);
			
			
			if(result.getSessionId() == 0)
			{
				SubscriberProduct subProduct = SubscriberProductImpl.getProduct(result
						.getSubProductId());
				
				result.setSessionId(subProduct.getSessionId());
			}
			
			if(contentFirst != null)
			{
				result.getParameters().setString("quiz.content2", contentFirst.getContent());
			}
			
			
			
			SubscriberOrderImpl.updateQuestionId(request.getOrderId(), content.getContentId());
		}
		catch (Exception e)
		{
			instance.logMonitor(e);
		}

		return result;
	}

	/**
	 * Truong hop khach hang dang ki lai, send cau hoi moi cho khach hang
	 * 
	 * @param instance
	 * @param provisioningCommand
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public CommandMessage sendContent(CommandInstance instance,
			ProvisioningCommand provisioningCommand, CommandMessage request)
			throws Exception
	{

		CommandMessage result = request;
		ContentQuestion content = ContentQuestionImpl.getContentQuestion(
				result.getIsdn(), result.getProductId());
		ProductEntry product = null;
		try
		{
			try
			{
				product = ProductFactory.getCache().getProduct(
						result.getProductId());
			}
			catch (Exception e)
			{
				instance.logMonitor(e);
			}
			String strContent = content.getContent();
			strContent = ResponseUtil.formatResponse(instance, product, result,
					result.getActionType(), strContent);

			result.getParameters().setBoolean("quiz.isregistered", true);
			result.getParameters().setString("quiz.content1", content.getContent());
			result.getParameters().setLong("quiz.id", content.getContentId());

			
			if(result.getSessionId() == 0)
			{
				SubscriberProduct subProduct = SubscriberProductImpl.getProduct(result
						.getSubProductId());
				
				result.setSessionId(subProduct.getSessionId());
			}
			
			SubscriberOrderImpl.updateQuestionId(request.getOrderId(), content.getContentId());
		}
		catch (Exception e)
		{
			instance.logMonitor(e);
		}

		return result;
	}

	public CommandMessage sendCommandRequest(CommandInstance instance,
			ProvisioningCommand provisioningCommand, CommandMessage request)
			throws Exception
	{
		CommandMessage result = request;
		try
		{
			if (!ContentQuestionImpl.checkExistInCommand(request.getIsdn(), request.getChannel(), request.getServiceAddress(), request.getKeyword()))
			{
				ContentQuestionImpl.insertCommandRequest(request.getUserName(), request.getChannel(),
						request.getServiceAddress(), request.getIsdn(), request.getKeyword());
				instance.logMonitor("Add {" + request.getIsdn() + "|" + request.getKeyword() + "} to queue.");
			}

		}
		catch (Exception e)
		{
			instance.logMonitor(e);
		}

		return result;
	}
	
	public CommandMessage sendDailyContent(CommandInstance instance,
			ProvisioningCommand provisioningCommand, CommandMessage request)
			throws Exception
	{
		CommandMessage result = request;
		ContentQuestion content = ContentQuestionImpl.getContentQuestion(
				result.getIsdn(), result.getProductId());
		ProductEntry product = null;
		try
		{
			try
			{
				product = ProductFactory.getCache().getProduct(
						result.getProductId());
			}
			catch (Exception e)
			{
				instance.logMonitor(e);
			}
			String strContent = "Cau hoi trung iPod hom nay: " +content.getContent();
			strContent = ResponseUtil.formatResponse(instance, product, result,
					result.getActionType(), strContent);
			
			instance.logMonitor("{"+result.getIsdn()+"} " + strContent);
			

			if(result.getSessionId() == 0)
			{
				SubscriberProduct subProduct = SubscriberProductImpl.getProduct(result
						.getSubProductId());
				
				result.setSessionId(subProduct.getSessionId());
			}
			
			CommandUtil.sendSMS(instance, request, strContent);
			
			SubscriberOrderImpl.updateQuestionId(request.getOrderId(), content.getContentId());
			
		}
		catch (Exception e)
		{
			instance.logMonitor(e);
		}

		return result;
	}
}
