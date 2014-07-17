/**
 * ----------------------------------------------------------------- 
 * @ Copyright(c) 2013 Vietnamobile. JSC. All Rights Reserved.
 * ----------------------------------------------------------------- 
 * Date 	Author 		Version
 * ------------------------------------- 
 * Oct 3, 2013 hungdt  v1.0
 * -------------------------------------
 */
package com.crm.provisioning.impl.mobifone;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axis2.databinding.types.URI;

import vms.org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.NonceGenerator;
import vms.org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.SendSmsServiceStub;
import vms.org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.SendSmsServiceStub.ChargingInformation;
import vms.org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.SendSmsServiceStub.GetSmsDeliveryStatus;
import vms.org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.SendSmsServiceStub.GetSmsDeliveryStatusE;
import vms.org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.SendSmsServiceStub.GetSmsDeliveryStatusResponseE;
import vms.org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.SendSmsServiceStub.SendSms;
import vms.org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.SendSmsServiceStub.SendSmsE;
import vms.org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.SendSmsServiceStub.SendSmsResponseE;
import vms.org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.SendSmsServiceStub.SimpleReference;
import vms.org.csapi.www.wsdl.parlayx.wap_push.send.v1_0.service.SendPushMessageServiceStub;
import vms.org.csapi.www.wsdl.parlayx.wap_push.send.v1_0.service.SendPushMessageServiceStub.GetPushMessageDeliveryStatus;
import vms.org.csapi.www.wsdl.parlayx.wap_push.send.v1_0.service.SendPushMessageServiceStub.GetPushMessageDeliveryStatusE;
import vms.org.csapi.www.wsdl.parlayx.wap_push.send.v1_0.service.SendPushMessageServiceStub.GetPushMessageDeliveryStatusResponseE;
import vms.org.csapi.www.wsdl.parlayx.wap_push.send.v1_0.service.SendPushMessageServiceStub.SendPushMessage;
import vms.org.csapi.www.wsdl.parlayx.wap_push.send.v1_0.service.SendPushMessageServiceStub.SendPushMessageE;
import vms.org.csapi.www.wsdl.parlayx.wap_push.send.v1_0.service.SendPushMessageServiceStub.SendPushMessageResponseE;
import vms.cn.com.huawei.www.schema.common.v2_1.RequestSOAPHeader;
import vms.cn.com.huawei.www.schema.common.v2_1.RequestSOAPHeaderE;

import com.crm.provisioning.cache.ProvisioningConnection;
import com.crm.provisioning.message.CommandMessage;
import com.crm.util.AppProperties;

/**
 * @author hungdt
 * 
 */
public class SdpVmsConnection extends ProvisioningConnection
{
	public String		serviceId	= "";
	public String		productId	= "";
	public static int	count		= 0;

	public SdpVmsConnection()
	{
		super();
	}

	public boolean openConnection() throws Exception
	{
		return super.openConnection();
	}

	/**
	 * 
	 */
	public void setParameters(AppProperties parameters) throws Exception
	{
		super.setParameters(parameters);
	}

	/**
	 * 
	 * hungdt
	 * 
	 * @param request
	 * @return
	 */
	public RequestSOAPHeaderE createHeader(CommandMessage request)
	{
		RequestSOAPHeaderE requestHeaderE = new RequestSOAPHeaderE();
		RequestSOAPHeader requestHeader = new RequestSOAPHeader();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		String created = sdf.format(Calendar.getInstance().getTime());

		serviceId = request.getParameters().getString("serviceid", "");
		productId = request.getParameters().getString("productid", "");

		String password = NonceGenerator.getInstance()
				.getNonce(getUserName() + getPassword() + created);
		requestHeader.setSpId(getUserName());
		requestHeader.setSpPassword(password);
		requestHeader.setServiceId(serviceId);
		requestHeader.setTimeStamp(created);
		requestHeader.setOA(request.getIsdn());
		requestHeader.setFA(request.getIsdn());
		requestHeaderE.setRequestSOAPHeader(requestHeader);

		return requestHeaderE;
	}

	/**
	 * 
	 * hungdt
	 * 
	 * @param message
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	public SendSmsE createBodySmS(CommandMessage message) throws Exception
	{

		try
		{
			URI address = new URI(message.getIsdn());
			URI endpoint = new URI(getHost());

			// set charging infomation

			ChargingInformation charging = new ChargingInformation();
			charging.setAmount(new BigDecimal(1));
			charging.setCode("111");
			charging.setCurrency("VND");
			charging.setDescription(message.getDescription());

			// set parameter
			// String msg = "http://goo.gl/cJB4W1";

			SimpleReference sim = new SimpleReference();
			sim.setCorrelator("123456");
			sim.setEndpoint(endpoint);
			sim.setInterfaceName("SmsNotification");
			SendSms param = new SendSms();
			param.addAddresses(address);
			param.setCharging(charging);

			// if (!msg.equals("") &&
			// message.getParameters().getString("serviceId").equals("1303042000071764"))
			// {
			// param.setMessage(msg);
			// }
			// else
			// {
			param.setMessage(message.getContent());
			// }

			// param.setReceiptRequest(sim);
			param.setSenderName(message.getServiceAddress());

			SendSmsE sendSms = new SendSmsE();
			sendSms.setSendSms(param);
			return sendSms;
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			if (message.getParameters().getString("serviceId").equals("1303042000071764"))
			{
				count++;
			}
		}
	}

	/**
	 * 
	 * hungdt
	 * 
	 * @param message
	 * @return
	 * @throws Exception
	 */
	public SendPushMessageE createBodyWap(CommandMessage message)
			throws Exception
	{
		try
		{
			URI address = new URI(message.getIsdn());
			URI endpoint = new URI(getHost());
			vms.org.csapi.www.wsdl.parlayx.wap_push.send.v1_0.service.SendPushMessageServiceStub.ChargingInformation charging = new vms.org.csapi.www.wsdl.parlayx.wap_push.send.v1_0.service.SendPushMessageServiceStub.ChargingInformation();
			charging.setAmount(new BigDecimal(1));
			charging.setCode("111");
			charging.setCurrency("VND");
			charging.setDescription(message.getDescription());
			vms.org.csapi.www.wsdl.parlayx.wap_push.send.v1_0.service.SendPushMessageServiceStub.SimpleReference sim = new vms.org.csapi.www.wsdl.parlayx.wap_push.send.v1_0.service.SendPushMessageServiceStub.SimpleReference();
			sim.setCorrelator("123456");
			sim.setEndpoint(endpoint);
			sim.setInterfaceName("WapNotification");
			SendPushMessage param = new SendPushMessage();
			param.addAddresses(address);
			param.setCharging(charging);

			param.setSubject(message.getDeliveryWapTitle());
			// param.setReceiptRequest(sim);
			param.setSenderAddress(message.getServiceAddress());
			URI targetURL = new URI(message.getDeliveryWapHref());
			param.setTargetURL(targetURL);
			// param.setSourceport(123);

			SendPushMessageE sendE = new SendPushMessageE();
			sendE.setSendPushMessage(param);
			return sendE;

		}
		catch (Exception e)
		{
			throw e;
		}
	}

	/**
	 * 
	 * hungdt
	 * 
	 * @param header
	 * @param body
	 * @param endpoinVMS
	 * @return
	 * @throws org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.PolicyException
	 * @throws org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.ServiceException
	 */
	public SendSmsResponseE sendSMS(RequestSOAPHeaderE header, SendSmsE body)
			throws vms.org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.PolicyException,
			vms.org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.ServiceException
	{
		SendSmsResponseE response = null;
		try
		{
			SendSmsServiceStub stub = new SendSmsServiceStub(getHost());
			stub._getServiceClient()
					.addHeader(header.getOMElement(RequestSOAPHeaderE.MY_QNAME, OMAbstractFactory
							.getSOAP11Factory()));
			response = stub.sendSms(body);
			// System.out.println(response.getOMElement(SendSmsResponseE.MY_QNAME,
			// OMAbstractFactory.getSOAP11Factory()));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return response;
	}

	/**
	 * 
	 * hungdt
	 * 
	 * @param header
	 * @param body
	 * @param endpoinVMS
	 * @return
	 */
	public SendPushMessageResponseE sendWap(RequestSOAPHeaderE header,
			SendPushMessageE body)
	{
		SendPushMessageResponseE response = null;
		try
		{
			SendPushMessageServiceStub stub = new SendPushMessageServiceStub(getHost());
			stub._getServiceClient()
					.addHeader(header.getOMElement(RequestSOAPHeaderE.MY_QNAME, OMAbstractFactory
							.getSOAP11Factory()));
			response = stub.sendPushMessage(body);
			System.out
					.println(response
							.getOMElement(SendPushMessageResponseE.MY_QNAME, OMAbstractFactory
									.getSOAP11Factory()));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return response;
	}

	/**
	 * 
	 * hungdt
	 * 
	 * @param reqIndentifier
	 * @return
	 * @throws Exception
	 */
	public GetPushMessageDeliveryStatusE getPushDelivery(
			String reqIndentifier) throws Exception
	{
		GetPushMessageDeliveryStatusE getWapdelivery = null;
		try
		{
			getWapdelivery = new GetPushMessageDeliveryStatusE();
			GetPushMessageDeliveryStatus request = new GetPushMessageDeliveryStatus();
			request.setRequestIdentifier(reqIndentifier);
			getWapdelivery.setGetPushMessageDeliveryStatus(request);

		}
		catch (Exception e)
		{
			throw e;
		}

		return getWapdelivery;
	}

	/**
	 * 
	 * hungdt
	 * 
	 * @param reqIndentifier
	 * @return
	 * @throws Exception
	 */
	public GetSmsDeliveryStatusE createBody(String reqIndentifier)
			throws Exception
	{
		GetSmsDeliveryStatusE getSmsDeliveryStatusRequset = null;
		try
		{
			getSmsDeliveryStatusRequset = new GetSmsDeliveryStatusE();
			GetSmsDeliveryStatus request = new GetSmsDeliveryStatus();
			request.setRequestIdentifier(reqIndentifier);
			getSmsDeliveryStatusRequset.setGetSmsDeliveryStatus(request);
		}
		catch (Exception e)
		{
			throw e;
		}

		return getSmsDeliveryStatusRequset;
	}

	/**
	 * 
	 * hungdt
	 * 
	 * @param header
	 * @param body
	 * @param endpointVMS
	 * @return
	 * @throws Exception
	 */
	public GetSmsDeliveryStatusResponseE getDeliverySMS(
			RequestSOAPHeaderE header, GetSmsDeliveryStatusE body) throws Exception
	{
		GetSmsDeliveryStatusResponseE response = null;
		try
		{
			SendSmsServiceStub stub = new SendSmsServiceStub(getHost());
			stub._getServiceClient()
					.addHeader(header.getOMElement(RequestSOAPHeaderE.MY_QNAME, OMAbstractFactory
							.getSOAP11Factory()));
			response = stub.getSmsDeliveryStatus(body);
			// System.out.println(response.getOMElement(GetSmsDeliveryStatusResponseE.MY_QNAME,
			// OMAbstractFactory.getSOAP11Factory()));
		}
		catch (Exception e)
		{
			throw e;
		}
		return response;
	}

	/**
	 * 
	 * @author hungdt
	 * @param header
	 * @param body
	 * @param endpointVMS
	 * @return
	 * @throws Exception
	 */
	public GetPushMessageDeliveryStatusResponseE getDeliveryWap(
			RequestSOAPHeaderE header, GetPushMessageDeliveryStatusE body) throws Exception
	{
		GetPushMessageDeliveryStatusResponseE response = null;
		try
		{
			SendPushMessageServiceStub stub = new SendPushMessageServiceStub(getHost());
			stub._getServiceClient()
					.addHeader(header.getOMElement(RequestSOAPHeaderE.MY_QNAME, OMAbstractFactory
							.getSOAP11Factory()));
			response = stub.getPushMessageDeliveryStatus(body);
			// System.out.println(response.getOMElement(GetPushMessageDeliveryStatusResponseE.MY_QNAME,
			// OMAbstractFactory.getSOAP11Factory()));
		}
		catch (Exception e)
		{
			throw e;
		}
		return response;
	}
}
