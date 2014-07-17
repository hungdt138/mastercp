package vms.org.csapi.www.wsdl.parlayx.wap_push.send.v1_0.service;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axis2.AxisFault;
import org.apache.axis2.databinding.ADBException;
import org.apache.axis2.databinding.types.URI;
import org.apache.axis2.databinding.types.URI.MalformedURIException;
import vms.org.csapi.www.wsdl.parlayx.wap_push.send.v1_0.service.SendPushMessageServiceStub.ChargingInformation;
import vms.org.csapi.www.wsdl.parlayx.wap_push.send.v1_0.service.SendPushMessageServiceStub.SendPushMessage;
import vms.org.csapi.www.wsdl.parlayx.wap_push.send.v1_0.service.SendPushMessageServiceStub.SendPushMessageE;
import vms.org.csapi.www.wsdl.parlayx.wap_push.send.v1_0.service.SendPushMessageServiceStub.SendPushMessageResponseE;
import vms.org.csapi.www.wsdl.parlayx.wap_push.send.v1_0.service.SendPushMessageServiceStub.SimpleReference;

import vms.cn.com.huawei.www.schema.common.v2_1.RequestSOAPHeader;
import vms.cn.com.huawei.www.schema.common.v2_1.RequestSOAPHeaderE;

public class SoapSendWapClient {
	public static RequestSOAPHeaderE createHeader() {
		RequestSOAPHeaderE requestHeaderE = new RequestSOAPHeaderE();
		RequestSOAPHeader requestHeader = new RequestSOAPHeader();
		String spId = "130304";
		String serviceId = "1303042000006304";
		String spPassword = "Acom123456";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		String created = sdf.format(Calendar.getInstance().getTime());
		String password = NonceGenerator.getInstance().getNonce(
				spId + spPassword + created);
		requestHeader.setSpId(spId);
		requestHeader.setSpPassword(password);
		requestHeader.setServiceId(serviceId);
		requestHeader.setTimeStamp(created);
		requestHeader.setOA("84906130890");
		requestHeader.setFA("84906130890");
		requestHeaderE.setRequestSOAPHeader(requestHeader);
		return requestHeaderE;
	}
	
	public static SendPushMessageE createBody()
	{
		try {
			URI address = new URI("84906130890");
			URI endpoint = new URI("http://113.187.31.2:8080/osg/services/SendPushMessageService");
			ChargingInformation charging = new ChargingInformation();
			charging.setAmount(new BigDecimal(1));
			charging.setCode("111");
			charging.setCurrency("RMB");
			charging.setDescription("description");
			SimpleReference sim = new SimpleReference();
			sim.setCorrelator("123456");
			sim.setEndpoint(endpoint);
			sim.setInterfaceName("SmsNotification");
			SendPushMessage param = new SendPushMessage();
			param.addAddresses(address);
			param.setCharging(charging);
//			param.setData_coding(0);
//			param.setDestinationport(0);
//			param.setEncode("utf-8");
//			param.setEsm_class(1);
			param.setSubject("Test Wappush");
			param.setReceiptRequest(sim);
			param.setSenderAddress("9001");
			 URI targetURL = new URI(
	            "http://sinhnhat.vietnamobile.com.vn");
			param.setTargetURL(targetURL);
//			param.setSourceport(123);
			
			SendPushMessageE sendE = new SendPushMessageE();
			sendE.setSendPushMessage(param);
			return sendE;
			
		}  catch (MalformedURIException e) {
			return null;
		}
	}
	
	public static void sendSWap(RequestSOAPHeaderE header, SendPushMessageE body) {
		try {
			SendPushMessageServiceStub stub = new SendPushMessageServiceStub(
					"http://113.187.31.2:8080/osg/services/SendPushMessageService");
			stub._getServiceClient().addHeader(
					header.getOMElement(RequestSOAPHeaderE.MY_QNAME,
							OMAbstractFactory.getSOAP11Factory()));
			SendPushMessageResponseE response = stub.sendPushMessage(body);
			System.out.println(response.getOMElement(SendPushMessageResponseE.MY_QNAME,
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
		sendSWap(createHeader(), createBody());
	}
}	
