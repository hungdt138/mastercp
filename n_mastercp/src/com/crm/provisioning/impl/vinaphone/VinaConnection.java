/**
 * ----------------------------------------------------------------- 
 * @ Copyright(c) 2013 Vietnamobile. JSC. All Rights Reserved.
 * ----------------------------------------------------------------- 
 * Date 	Author 		Version
 * ------------------------------------- 
 * Oct 6, 2013 hungdt  v1.0
 * -------------------------------------
 */
package com.crm.provisioning.impl.vinaphone;

import java.security.MessageDigest;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axiom.soap.SOAPFactory;
import org.apache.axiom.soap.SOAPHeaderBlock;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.databinding.types.URI;

import vinaphone.org.csapi.www.schema.parlayx.common.v2_1.SimpleReference;
import vinaphone.org.csapi.www.schema.parlayx.sms.send.v2_2.local.SendSms;
import vinaphone.org.csapi.www.schema.parlayx.sms.send.v2_2.local.SendSmsE;
import vinaphone.org.csapi.www.schema.parlayx.sms.send.v2_2.local.SendSmsResponseE;
import vinaphone.org.csapi.www.schema.parlayx.wap_push.send.v1_0.local.SendPushMessage;
import vinaphone.org.csapi.www.schema.parlayx.wap_push.send.v1_0.local.SendPushMessageE;
import vinaphone.org.csapi.www.schema.parlayx.wap_push.send.v1_0.local.SendPushMessageResponseE;
import vinaphone.org.csapi.www.schema.parlayx.wap_push.v1_0.MessagePriority;
import vinaphone.org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.PolicyException;
import vinaphone.org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.SendSmsServiceStub;
import vinaphone.org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.ServiceException;
import vinaphone.org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.SoapHeaderUtil;
import vinaphone.org.csapi.www.wsdl.parlayx.wap_push.send.v1_0.service.SendPushMessageServiceStub;

import com.crm.provisioning.cache.ProvisioningConnection;
import com.crm.provisioning.message.CommandMessage;
import com.crm.thread.DispatcherInstance;
import com.crm.util.AppProperties;
import com.logica.smpp.pdu.Request;

/**
 * @author hungdt
 * 
 */
public class VinaConnection extends ProvisioningConnection
{

	/*
	 * SoapHeader'label Name.
	 */
	/*
	 * spId
	 */
	protected static final String	SOAPHEADER_SPID					= "spId";

	/*
	 * spPassword
	 */
	protected static final String	SOAPHEADER_SPPASSWORD			= "spPassword";

	/*
	 * serviceId
	 */
	protected static final String	SOAPHEADER_SERVICEID			= "serviceId";

	/*
	 * timeStamp
	 */
	protected static final String	SOAPHEADER_TIMESTAMP			= "timeStamp";

	/*
	 * OA
	 */
	protected static final String	SOAPHEADER_OA					= "OA";

	/*
	 * FA
	 */
	protected static final String	SOAPHEADER_FA					= "FA";

	/*
	 * Token
	 */
	protected static final String	SOAPHEADER_TOKEN				= "token";

	/*
	 * NameSpace for SOAPMessage
	 */
	protected static final String	nameSpace						= "http://www.huawei.com/schema/osg/common/v2_1";

	/*
	 * Request SOAPHeader for Sending Message to SDP
	 */
	protected static final String	SOAPHEADER_RequestSOAPHeader	= "RequestSOAPHeader";

	public VinaConnection()
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

	public static SOAPHeaderBlock getAOMTSOAPHeaderBlock(String isdn, String spId, String pass, String serviceId)
	{

		/*
		 * SP's SOAPHeader Info,when sending message to SDP, following
		 * SoapHeader fields may be taken to SDP.
		 * SpID,spPassword,serviceID,timeStamp,OA,FA,token, this can reference
		 * to API document. SP can get spID£¬ password, serviceID on SDP when
		 * issuing service on SDP.
		 */
		/*
		 * SpID
		 */
		String spIDStr = spId;

		/*
		 * password,it is unencrypted,when sending to SDP,it should be encrypted
		 * by MD5 when needing authenticated by password. spPassword = MD5(spId
		 * + password + timeStamp)
		 */
		String passwordStr = pass;

		/*
		 * serviceID
		 */
		String serviceIDStr = serviceId;

		/*
		 * current timestamp
		 */
		String timeStampStr = Long.toString(System.currentTimeMillis());

		/*
		 * Service subscription address. Do not fill in it in case of group
		 * transmission.
		 */
		String oaStr = isdn;

		/*
		 * Payment address. Optional
		 */
		String faStr = isdn;

		/*
		 * token is optional. When there is token in SOAPHeader,it means that SP
		 * will be authenticated by token. SP can get token using TokenManage
		 * interface when SP needs to authenticate by token.
		 */
		String tokenStr = "";

		// set SoapHeader
		OMFactory fac = OMAbstractFactory.getOMFactory();
		SOAPFactory sfac = OMAbstractFactory.getSOAP11Factory();

		// the namespace Object of request Header,when sending Message to SDP,it
		// is mandatory
		OMNamespace omNs = fac.createOMNamespace(nameSpace, "tns");

		// the SOAPHeaderBlock of request,when sending Message to SDP,it is
		// mandatory and must be "RequestSOAPHeader"
		SOAPHeaderBlock soapHeadB = sfac.createSOAPHeaderBlock(SOAPHEADER_RequestSOAPHeader, omNs);

		// sp id
		SOAPHeaderBlock spID = sfac.createSOAPHeaderBlock(SOAPHEADER_SPID, omNs);
		spID.addChild(sfac.createOMText(spIDStr));

		/*
		 * password,encrypted by md5 spPassword = MD5(spId + password +
		 * timeStamp)
		 */

		String spPassword = md5(spIDStr + passwordStr + timeStampStr);
		SOAPHeaderBlock password = sfac.createSOAPHeaderBlock(SOAPHEADER_SPPASSWORD, omNs);
		password.addChild(sfac.createOMText(spPassword));

		// timestamp
		SOAPHeaderBlock timestamp = sfac.createSOAPHeaderBlock(SOAPHEADER_TIMESTAMP, omNs);
		timestamp.addChild(sfac.createOMText(timeStampStr));

		// service id
		SOAPHeaderBlock serviceID = sfac.createSOAPHeaderBlock(SOAPHEADER_SERVICEID, omNs);
		serviceID.addChild(sfac.createOMText(serviceIDStr));

		// oa
		SOAPHeaderBlock oa = sfac.createSOAPHeaderBlock(SOAPHEADER_OA, omNs);
		oa.addChild(sfac.createOMText(oaStr));

		// fa
		SOAPHeaderBlock fa = sfac.createSOAPHeaderBlock(SOAPHEADER_FA, omNs);
		fa.addChild(sfac.createOMText(faStr));

		// token
		SOAPHeaderBlock token = sfac.createSOAPHeaderBlock(SOAPHEADER_TOKEN, omNs);
		token.addChild(sfac.createOMText(tokenStr));

		soapHeadB.setMustUnderstand(false);

		/*
		 * Add soapHeader block to RequestSoapHeader
		 */
		soapHeadB.addChild(spID);
		soapHeadB.addChild(password);
		soapHeadB.addChild(timestamp);
		soapHeadB.addChild(serviceID);
		soapHeadB.addChild(oa);
		soapHeadB.addChild(fa);
		soapHeadB.addChild(token);

		return soapHeadB;
	}

	/**
	 * md5 arithmetic for encrypting String
	 * 
	 * @param s
	 *            the param you want to encrypt
	 * @return encrypted String
	 */
	public static String md5(String s)
	{
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'A', 'B', 'C', 'D', 'E', 'F' };
		try
		{
			byte[] strTemp = s.getBytes();
			MessageDigest MD = MessageDigest.getInstance("MD5");
			MD.update(strTemp);
			byte[] md = MD.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++)
			{
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return String.valueOf(str);
		}
		catch (Exception e)
		{
			return null;
		}
	}

	public String sendSms(DispatcherInstance instance, CommandMessage message)
			throws Exception
	{

		String identifier = "";
		try
		{
			/*
			 * the sendSms service URI of SDP System
			 */
			// String sendSmsURI =
			// "http://127.0.0.1:8888/osg/services/SendSmsService";

			String sendSmsURI = getHost();
			/*
			 * Generate SendSmsServiceStub for sending SMS
			 */
			SendSmsServiceStub stub = new SendSmsServiceStub(sendSmsURI);

			/*
			 * generate necessary SoapHeader
			 */
			ServiceClient client = stub._getServiceClient();
			client.addHeader(getAOMTSOAPHeaderBlock(message.getIsdn(), getUserName(), getPassword(), message.getParameters().getString("serviceid")));

			/*
			 * Create SoapBody
			 */
			SendSmsE sendSmsE = new SendSmsE();
			SendSms sendSms = new SendSms();

			/*
			 * Set SendSMS's Each field to SoapBody, including
			 * Address,Message,SenderName, SimpleReference and
			 * ChargingInformation.
			 */
			/*
			 * Set Address. the destination addresses of message it's element
			 * type is URI and schema must be tel. Address format:country code +
			 * mobile phone number of a subscriber Example: 8699907550001 (which
			 * can be prefixed by +, 0, or both) Address is an URI array,when
			 * SMS is sent to multi-users, it will be multi-URIs.
			 */

			URI[] addr = new URI[1];
			addr[0] = new URI("tel:" + message.getIsdn());
			sendSms.setAddresses(addr);

			sendSms.setMessage(message.getContent());

			/*
			 * Set SenderName. the Name of SP,it will display on User's
			 * terminal. it may be sp's center accessCode,it is obtained when
			 * issuing service.
			 */
			sendSms.setSenderName(message.getServiceAddress());

			/*
			 * Set SimpleReference. When SP fill this filed, it means the mode
			 * to obtain status reports is notify,otherwise,it means the mode is
			 * get.
			 */
			SimpleReference ref = new SimpleReference();
			// the identifier of SimpleReference
			ref.setCorrelator("123456");
			// endpoint,the webservice which SP used to receive SMS status.
			ref.setEndpoint(new URI("http://183.91.14.218:8089/axis2/services/SmsNotificationService"));
			// SimpleReference's interfacename
			ref.setInterfaceName("SmsNotificationService");
			sendSms.setReceiptRequest(ref);

			sendSmsE.setSendSms(sendSms);

			/*
			 * Invoke Stub's sendSms Operation for Sending SMS to SDP.
			 */
			SendSmsResponseE rsp = stub.sendSms(sendSmsE);

			identifier = rsp.getSendSmsResponse().getResult();

		}
		catch (PolicyException e)
		{
			throw e;
		}
		catch (ServiceException e)
		{
			e.printStackTrace();
			throw e;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}

		return identifier;
	}

	public String sendWap(DispatcherInstance instance, CommandMessage message)
			throws Exception
	{

		String identifier = "";

		try
		{
			/*
			 * the sendPushMessage service URI of SDP System
			 */
			String sendPushMessageURL = getHost();

			/*
			 * Generate SendPushMessageServiceStub for sending push message
			 */
			SendPushMessageServiceStub stub = new SendPushMessageServiceStub(sendPushMessageURL);

			/*
			 * generate necessery SoapHeader
			 */
			ServiceClient client = stub._getServiceClient();
			client.addHeader(getAOMTSOAPHeaderBlock(message.getIsdn(), getUserName(), getPassword(), message.getParameters().getString("serviceid")));

			/*
			 * Create SoapBody
			 */
			SendPushMessageE sendPushMessageE = new SendPushMessageE();
			SendPushMessage sendPushMessage = new SendPushMessage();

			/*
			 * Set SendPushMessage's Each field to SoapBody, including
			 * Addresses,TargetURL,SenderAddress,
			 * Subject,Priority,SimpleReference and ChargingInformation.
			 */
			/*
			 * Set Address. the destination addresses of message it's element
			 * type is URI and schema must be tel. Address format:country code +
			 * mobile phone number of a subscriber Example: 8613507550001 (which
			 * can be prefixed by +, 0, or both)
			 * 
			 * Address is an URI array,when WAP is sent to multi-users, it will
			 * be multi-URIs.
			 */
			URI[] addr = new URI[1];
			addr[0] = new URI(message.getIsdn());
			sendPushMessage.setAddresses(addr);

			/*
			 * Set TargetURL. It indicates the URL connection of a push.
			 */
			URI targetURL = new URI(message.getDeliveryWapHref());
			sendPushMessage.setTargetURL(targetURL);

			/*
			 * Set SenderAddress.the Name of SP. It indicates the source
			 * address,it will display on User's terminal. it may be sp's center
			 * accessCode.
			 */
			sendPushMessage.setSenderAddress(message.getServiceAddress());

			/*
			 * Set Subject It indicates the subject of a push message.
			 */
			sendPushMessage.setSubject(message.getDeliveryWapTitle());

			/*
			 * Set Priority It indicates the priority of a push message.
			 */
			sendPushMessage.setPriority(MessagePriority.Default);

			/*
			 * Set SimpleReference. When SP fill this filed, it means the mode
			 * to obtain status reports is notify,otherwise,it means the mode is
			 * get.
			 */
			SimpleReference ref = new SimpleReference();
			// the identifier of SimpleReference
			ref.setCorrelator("123456");
			// endpoint,the webservice which SP used to receive WAP status.
			ref
					.setEndpoint(new URI(message.getParameters().getString("endpoint")));
			// SimpleReference's interfacename
			ref.setInterfaceName("PushMessageNotificationService");
			sendPushMessage.setReceiptRequest(ref);

			sendPushMessageE.setSendPushMessage(sendPushMessage);

			/*
			 * Invoke Stub's sendPushMessage Operation for Sending Push Message
			 * to SDP.
			 */
			SendPushMessageResponseE rsp = stub.sendPushMessage(sendPushMessageE);

			identifier = rsp.getSendPushMessageResponse().getRequestIdentifier();
		}
		catch (Exception e)
		{
			instance.debugMonitor(e.toString());
			throw e;
		}

		return identifier;
	}

}
