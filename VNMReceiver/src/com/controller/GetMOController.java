/**
 * 
 */
package com.controller;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.crm.kernel.message.Constants;
import com.crm.provisioning.message.CommandMessage;
import com.crm.util.GeneratorSeq;
import com.crm.util.StringUtil;
import com.services.WebserviceBase;

/**
 * @author hungdt
 * 
 */
public class GetMOController extends WebserviceBase implements Controller
{

	private static Logger	logger	= Logger.getLogger(GetMOController.class);

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		ModelAndView mv = new ModelAndView("receiver");
		long seqId = GeneratorSeq.getNextSeq();

		String resp = "";

		String username = "acom";
		String password = "acomvnm123";

		String userReq = request.getParameter("Username") == null ? "" : request
				.getParameter("Username").toString();
		String passReq = request.getParameter("Password") == null ? "" : request
				.getParameter("Password").toString();
		String shortCode = request.getParameter("shortcode") == null ? "" : request
				.getParameter("shortcode").toString();
		String isdn = request.getParameter("MSISDN") == null ? "" : request
				.getParameter("MSISDN").toString();
		String reqId = request.getParameter("requestId") == null ? "" : request
				.getParameter("requestId").toString();
		String reqDate = request.getParameter("requestDate") == null ? "" : request
				.getParameter("requestDate").toString();
		String cmdCode = request.getParameter("cmdcode") == null ? "" : request
				.getParameter("cmdcode").toString();
		String msgBody = request.getParameter("msgbody") == null ? "" : request
				.getParameter("msgbody").toString();
		String productCode = request.getParameter("productCode") == null ? "" : request
				.getParameter("productCode").toString();
		String amount = request.getParameter("amount") == null ? "" : request
				.getParameter("amount").toString();

		if (StringUtil.isBlank(userReq) || StringUtil.isBlank(passReq) || StringUtil
				.isBlank(shortCode) || StringUtil.isBlank(isdn) || StringUtil
				.isBlank(reqId) || StringUtil.isBlank(reqDate) || StringUtil
				.isBlank(cmdCode) || StringUtil.isBlank(msgBody) || StringUtil.isBlank(productCode))
		{
			resp = "400";
		}

		else
		{
			if (!username.equals(userReq) || !password.equals(passReq))
			{
				resp = "400";
			}
			else
			{
				logger.debug("Receiver from VNM: " + isdn + "," + reqId + "," + reqDate + "," + cmdCode + "," + msgBody + "," + shortCode + "," + userReq + "," + passReq + "," + productCode + "," + amount + "|| ID=" + seqId);
				System.out.println("Receiver from VNM: " + isdn + "," + reqId + "," + reqDate + "," + cmdCode + "," + msgBody + "," + shortCode + "," + userReq + "," + passReq + "," + productCode + "," + amount);
				SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
				CommandMessage message = new CommandMessage();
				String command = getCommand(productCode, cmdCode.trim());

				String sc = getShortCode(productCode, cmdCode.trim());

				message.setKeyword(command);
				message.setServiceAddress(sc);
				message.setIsdn(isdn);
				message.setOrderDate(df.parse(reqDate));
				message.setChannel(Constants.CHANNEL_WEB);
				message.setSessionId(Long.parseLong(reqId));
				try
				{
					logger.debug("Request core " +message.toLogString());
					
					CommandMessage result = sendMessageToQueue(message, 0);

					logger.debug(result.toLogString());
					System.out.println(result.toLogString());
					if (result.getCause().equals("success"))
					{
						resp = "200";
					}
					else
					{
						resp = "204";
					}
					
				}
				catch (Exception e)
				{
					logger.error("Error: " + e.toString());
					throw e;
				}

			}
		}
		
		logger.debug("Resp: " +resp + "| ID=" + seqId);
		
		mv.addObject("resp", resp);
		return mv;
	}

}
