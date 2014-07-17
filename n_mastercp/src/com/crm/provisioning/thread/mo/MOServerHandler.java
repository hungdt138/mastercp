package com.crm.provisioning.thread.mo;

import java.util.concurrent.TimeoutException;

import com.crm.kernel.message.Constants;
import com.crm.kernel.queue.QueueFactory;
import com.crm.provisioning.message.CommandMessage;
import com.crm.provisioning.thread.MOListenerThread;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.QueryStringDecoder;
import io.netty.util.CharsetUtil;

import static io.netty.handler.codec.http.HttpHeaders.*;
import static io.netty.handler.codec.http.HttpResponseStatus.*;
import static io.netty.handler.codec.http.HttpVersion.*;

public class MOServerHandler extends SimpleChannelInboundHandler<Object>
{
	private MOServerListener	moServer	= null;
	private MOListenerThread	dispatcher	= null;

	public MOServerHandler(MOServerListener moServer)
	{
		super();

		this.moServer = moServer;
		dispatcher = (MOListenerThread) this.moServer.getDispatcher();
	}

	public void debugMonitor(Object message)
	{
		moServer.debugMonitor(message);
	}

	public void logMonitor(Object message)
	{
		moServer.logMonitor(message);
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception
	{
		ctx.flush();
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Object msg)
	{
		if (msg instanceof HttpRequest)
		{
			HttpRequest request = (HttpRequest) msg;
			if (is100ContinueExpected(request))
			{
				send100Continue(ctx);
			}
			int code = getResponse(request.getUri());

			FullHttpResponse response = new DefaultFullHttpResponse(request.getProtocolVersion(),
					HttpResponseStatus.OK, Unpooled.copiedBuffer("" + code, CharsetUtil.US_ASCII));

			ctx.write(response);
			ctx.flush();
			ctx.close();
		}
	}

	private static void send100Continue(ChannelHandlerContext ctx)
	{
		FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_0, CONTINUE);
		ctx.write(response);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception
	{
		cause.printStackTrace();
		ctx.close();
	}

	private int getResponse(String uri)
	{
		int status = 202; // success but not send MT
		
		logMonitor("REQ: " + uri);
		
		QueryStringDecoder decoder = new QueryStringDecoder(uri);

		String username = "";
		String password = "";
		String shortcode = "";
		String isdn = "";
		String requestId = "";
		String requestDate = "";
		String productCode = "";
		String cmdCode = "";
		String msgBody = "";
		double amount = 0;

		try
		{
			username = decoder.parameters().get("Username").get(0);
			password = decoder.parameters().get("Password").get(0);
			shortcode = decoder.parameters().get("shortcode").get(0);
			isdn = decoder.parameters().get("MSISDN").get(0);
			requestId = decoder.parameters().get("requestId").get(0);
			requestDate = decoder.parameters().get("requestDate").get(0);
			productCode = decoder.parameters().get("productCode").get(0);
			cmdCode = decoder.parameters().get("cmdcode").get(0);
			msgBody = decoder.parameters().get("msgbody").get(0);
			amount = Double.parseDouble(decoder.parameters().get("amount").get(0));
		}
		catch (Exception e)
		{
			status = 400; // parameter error
			
			logMonitor("RESP: requestId=" + requestId + ",isdn=" + isdn + "," + status);
			
			return status;
		}

		CommandMessage message = new CommandMessage();
		message.setIsdn(isdn);
		message.setKeyword(msgBody);
		message.setServiceAddress(shortcode);
		message.setAmount(amount);
		message.setChannel(Constants.CHANNEL_WEB);
		message.setSubscriberType(Constants.PREPAID_SUB_TYPE);
		message.setUserName(username);
		message.setRequestValue("username", username);
		message.setRequestValue("password", password);
		message.setRequestValue("requestId", requestId);
		message.setRequestValue("requestDate", requestDate);
		message.setRequestValue("productCode", productCode);
		message.setRequestValue("cmdCode", cmdCode);
		message.setCorrelationID(username + requestId);
		try
		{
			Object waitObject = new Object();
			CommandMessage resp = null;
			synchronized (waitObject)
			{
				QueueFactory.callbackListerner.put(message.getCorrelationID(), waitObject);
				QueueFactory.attachLocal(dispatcher.queueLocalName, message);

				waitObject.wait(dispatcher.timeout);

				resp = (CommandMessage) QueueFactory.callbackOrder.get(message.getCorrelationID());

			}

			if (resp == null)
			{
				throw new TimeoutException();
			}

			debugMonitor(resp.toLogString());

			if (resp.getCause().equals(Constants.ERROR_INVALID_SYNTAX))
			{
				status = 205;
			}
			else if (resp.getCause().equals(Constants.ERROR_REGISTERED))
			{
				status = 206;
			}
			else if (!resp.getCause().equals(Constants.SUCCESS))
			{
				status = 204;
			}
		}
		catch (Exception e)
		{
			debugMonitor(e);
			status = 204;// system error
		}

		logMonitor("RESP: requestId=" + requestId + ",isdn=" + isdn + "," + status);

		return status;
	}
}
