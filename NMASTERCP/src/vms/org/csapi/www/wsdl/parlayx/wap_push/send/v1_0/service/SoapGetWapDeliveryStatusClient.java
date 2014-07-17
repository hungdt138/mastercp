package vms.org.csapi.www.wsdl.parlayx.wap_push.send.v1_0.service;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axis2.AxisFault;
import org.apache.axis2.databinding.ADBException;
import vms.org.csapi.www.wsdl.parlayx.wap_push.send.v1_0.service.SendPushMessageServiceStub.GetPushMessageDeliveryStatus;
import vms.org.csapi.www.wsdl.parlayx.wap_push.send.v1_0.service.SendPushMessageServiceStub.GetPushMessageDeliveryStatusE;
import vms.org.csapi.www.wsdl.parlayx.wap_push.send.v1_0.service.SendPushMessageServiceStub.GetPushMessageDeliveryStatusResponseE;
import vms.org.csapi.www.wsdl.parlayx.wap_push.send.v1_0.service.SendPushMessageServiceStub.SendPushMessageResponseE;

import vms.cn.com.huawei.www.schema.common.v2_1.RequestSOAPHeader;
import vms.cn.com.huawei.www.schema.common.v2_1.RequestSOAPHeaderE;

public class SoapGetWapDeliveryStatusClient {
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
	
	public static GetPushMessageDeliveryStatusE createBody()
	{
		GetPushMessageDeliveryStatusE getWapdelivery = new GetPushMessageDeliveryStatusE();
		GetPushMessageDeliveryStatus request = new GetPushMessageDeliveryStatus();
		request.setRequestIdentifier("100001200501130410080910854511");
		getWapdelivery.setGetPushMessageDeliveryStatus(request);
		return getWapdelivery;
	}
	
	public static void getPushDeliveryStatus(RequestSOAPHeaderE header, GetPushMessageDeliveryStatusE body)
	{
		try {
			SendPushMessageServiceStub stub = new SendPushMessageServiceStub(
					"http://113.187.31.2:8080/osg/services/SendPushMessageService");
			stub._getServiceClient().addHeader(
					header.getOMElement(RequestSOAPHeaderE.MY_QNAME,
							OMAbstractFactory.getSOAP11Factory()));
			GetPushMessageDeliveryStatusResponseE response = stub.getPushMessageDeliveryStatus(body);
			System.out.println(response.getOMElement(GetPushMessageDeliveryStatusResponseE.MY_QNAME,
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
	

	public static void main(String[] args)
	{
		getPushDeliveryStatus(createHeader(), createBody());
	}

}
