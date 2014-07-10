package org.csapi.www.wsdl.parlayx.sms.send.v2_2.service;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axis2.AxisFault;
import org.apache.axis2.databinding.ADBException;
import org.apache.axis2.databinding.types.URI;
import org.apache.axis2.databinding.types.URI.MalformedURIException;
import org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.PolicyException;
import org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.SendSmsServiceStub;
import org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.ServiceException;
import org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.SendSmsServiceStub.ChargingInformation;
import org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.SendSmsServiceStub.SendSms;
import org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.SendSmsServiceStub.SendSmsE;
import org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.SendSmsServiceStub.SendSmsResponseE;
import org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.SendSmsServiceStub.SimpleReference;
import cn.com.huawei.www.schema.common.v2_1.RequestSOAPHeader;
import cn.com.huawei.www.schema.common.v2_1.RequestSOAPHeaderE;

public class SoapSendSmsClient {

	public static RequestSOAPHeaderE createHeader() {
		RequestSOAPHeaderE requestHeaderE = new RequestSOAPHeaderE();
		RequestSOAPHeader requestHeader = new RequestSOAPHeader();
		String spId = "130304";
		String serviceId = "1303042000043464";
		String spPassword = "Acom123123";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		String created = sdf.format(Calendar.getInstance().getTime());
		String password = NonceGenerator.getInstance().getNonce(
				spId + spPassword + created);
		requestHeader.setSpId(spId);
		requestHeader.setSpPassword(password);
		requestHeader.setServiceId(serviceId);
		requestHeader.setTimeStamp(created);
		requestHeader.setOA("84904811633");
		requestHeader.setFA("84904811633");
		requestHeaderE.setRequestSOAPHeader(requestHeader);
		return requestHeaderE;
	}

	public static SendSmsE createBody() {
		try {
			URI address = new URI("84904811633");
			URI endpoint = new URI("http://183.91.14.218:8080/axis2/services/SmsNotificationService");
			
			
			ChargingInformation charging = new ChargingInformation();
			charging.setAmount(new BigDecimal(200));
			charging.setCode("111");
			charging.setCurrency("RMB");
			charging.setDescription("description");
			
			SimpleReference sim = new SimpleReference();
			sim.setCorrelator("123456");
			sim.setEndpoint(endpoint);
			sim.setInterfaceName("SmsNotification");
			SendSms param = new SendSms();
			param.addAddresses(address);
			param.setCharging(charging);
//			param.setData_coding(0);
//			param.setDestinationport(0);
//			param.setEncode("utf-8");
//			param.setEsm_class(1);
			param.setMessage("http://sp.teebik.com/download/Mobile_Security_6.8.02.32.apk");
			//param.setReceiptRequest(sim);
			param.setSenderName("8926");
//			param.setSourceport(123);
			SendSmsE sendSms = new SendSmsE();
			sendSms.setSendSms(param);
			return sendSms;
		} catch (MalformedURIException e) {
			return null;
		}
	}

	public static void sendSms(RequestSOAPHeaderE header, SendSmsE body) {
		try {
			SendSmsServiceStub stub = new SendSmsServiceStub(
					"http://113.187.31.2:8080/SendSmsService/services/SendSms");
			stub._getServiceClient().addHeader(
					header.getOMElement(RequestSOAPHeaderE.MY_QNAME,
							OMAbstractFactory.getSOAP11Factory()));
			SendSmsResponseE response = stub.sendSms(body);
			System.out.println(response.getOMElement(SendSmsResponseE.MY_QNAME,
					OMAbstractFactory.getSOAP11Factory()));
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PolicyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ADBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		sendSms(createHeader(), createBody());
	}

}
