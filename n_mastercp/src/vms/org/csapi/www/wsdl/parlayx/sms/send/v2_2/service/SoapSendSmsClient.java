package vms.org.csapi.www.wsdl.parlayx.sms.send.v2_2.service;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axis2.AxisFault;
import org.apache.axis2.databinding.ADBException;
import org.apache.axis2.databinding.types.URI;
import org.apache.axis2.databinding.types.URI.MalformedURIException;
import vms.org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.PolicyException;
import vms.org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.SendSmsServiceStub;
import vms.org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.ServiceException;
import vms.org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.SendSmsServiceStub.ChargingInformation;
import vms.org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.SendSmsServiceStub.SendSms;
import vms.org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.SendSmsServiceStub.SendSmsE;
import vms.org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.SendSmsServiceStub.SendSmsResponseE;
import vms.org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.SendSmsServiceStub.SimpleReference;
import vms.cn.com.huawei.www.schema.common.v2_1.RequestSOAPHeader;
import vms.cn.com.huawei.www.schema.common.v2_1.RequestSOAPHeaderE;

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
			URI endpoint = new URI("http://113.187.31.2:8080/SendSmsService/services/SendSms");
			ChargingInformation charging = new ChargingInformation();
			charging.setAmount(new BigDecimal(1));
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
			param.setMessage("Link tai game http://corellinet.beezzi.com:8080/sms/vndl.php?id=4877543");
			param.setReceiptRequest(sim);
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
			
//			System.out.println(response.getOMElement(SendSmsResponseE.MY_QNAME,
//					OMAbstractFactory.getSOAP11Factory()));
			System.out.println(response.getSendSmsResponse().getResult());
			
			
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
