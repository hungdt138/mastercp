/**
 * 
 */
package org.csapi.www.wsdl.parlayx.sms.send.v2_2.service;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axis2.AxisFault;
import org.apache.axis2.databinding.ADBException;
import org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.PolicyException;
import org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.SendSmsServiceStub;
import org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.ServiceException;
import org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.SendSmsServiceStub.GetSmsDeliveryStatus;
import org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.SendSmsServiceStub.GetSmsDeliveryStatusE;
import org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.SendSmsServiceStub.GetSmsDeliveryStatusResponseE;
import cn.com.huawei.www.schema.common.v2_1.RequestSOAPHeader;
import cn.com.huawei.www.schema.common.v2_1.RequestSOAPHeaderE; 


/**
 * @author hungdt
 *
 */
public class SoapGetSmsDeliveryStatusClient {
	
	
	public static RequestSOAPHeaderE createHeader() {
		RequestSOAPHeaderE requestHeaderE = new RequestSOAPHeaderE();
		RequestSOAPHeader requestHeader = new RequestSOAPHeader();
		String spId = "130304";
		String serviceId = "1303042000006010";
		String spPassword = "Acom123123";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		String created = sdf.format(Calendar.getInstance().getTime());
		String password = NonceGenerator.getInstance().getNonce(
				spId + spPassword + created);
		requestHeader.setSpId(spId);
		requestHeader.setSpPassword(password);
		requestHeader.setServiceId(serviceId);
		requestHeader.setTimeStamp(created);
		requestHeader.setOA("841205332233");
		requestHeader.setFA("841205332233");
		requestHeaderE.setRequestSOAPHeader(requestHeader);
		return requestHeaderE;
	}
	
	public static GetSmsDeliveryStatusE createBody()
	{
	    GetSmsDeliveryStatusE getSmsDeliveryStatusRequset = new GetSmsDeliveryStatusE();
	    GetSmsDeliveryStatus request = new GetSmsDeliveryStatus();
	    request.setRequestIdentifier("100001200101130623170327413181");
	    getSmsDeliveryStatusRequset.setGetSmsDeliveryStatus(request);
	    return getSmsDeliveryStatusRequset;
	}
	
	public static void getSmsDeliveryStatus(RequestSOAPHeaderE header, GetSmsDeliveryStatusE body)
	{
	    try
	    {
	        SendSmsServiceStub stub = new SendSmsServiceStub("http://113.187.31.2:8080/SendSmsService/services/SendSms");
	        stub._getServiceClient().addHeader(header.getOMElement(RequestSOAPHeaderE.MY_QNAME,
	            OMAbstractFactory.getSOAP11Factory()));
	        GetSmsDeliveryStatusResponseE response = stub.getSmsDeliveryStatus(body);
	        System.out.println(response.getOMElement(GetSmsDeliveryStatusResponseE.MY_QNAME,
	            OMAbstractFactory.getSOAP11Factory()));
	        System.out.println(response.getGetSmsDeliveryStatusResponse().getResult());
	    }
	    catch (AxisFault e)
	    {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	    catch (RemoteException e)
	    {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	    catch (PolicyException e)
	    {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	    catch (ServiceException e)
	    {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	    catch (ADBException e)
	    {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	}

	public static void main(String[] args)
	{
	    getSmsDeliveryStatus(createHeader(), createBody());
	}


}
