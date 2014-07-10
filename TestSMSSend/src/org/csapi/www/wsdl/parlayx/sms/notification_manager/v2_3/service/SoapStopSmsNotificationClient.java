/**
 * 
 */
package org.csapi.www.wsdl.parlayx.sms.notification_manager.v2_3.service;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axis2.AxisFault;
import org.apache.axis2.databinding.ADBException;
import org.csapi.www.wsdl.parlayx.sms.notification_manager.v2_3.service.PolicyException;
import org.csapi.www.wsdl.parlayx.sms.notification_manager.v2_3.service.ServiceException;
import org.csapi.www.wsdl.parlayx.sms.notification_manager.v2_3.service.SmsNotificationManagerServiceStub;
import org.csapi.www.wsdl.parlayx.sms.notification_manager.v2_3.service.SmsNotificationManagerServiceStub.StartSmsNotificationResponseE;
import org.csapi.www.wsdl.parlayx.sms.notification_manager.v2_3.service.SmsNotificationManagerServiceStub.StopSmsNotification;
import org.csapi.www.wsdl.parlayx.sms.notification_manager.v2_3.service.SmsNotificationManagerServiceStub.StopSmsNotificationE;
import org.csapi.www.wsdl.parlayx.sms.notification_manager.v2_3.service.SmsNotificationManagerServiceStub.StopSmsNotificationResponseE;
import org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.NonceGenerator;

import cn.com.huawei.www.schema.common.v2_1.RequestSOAPHeader;
import cn.com.huawei.www.schema.common.v2_1.RequestSOAPHeaderE;

/**
 * @author hungdt
 * 
 */
public class SoapStopSmsNotificationClient
{
	public static RequestSOAPHeaderE createHeader()
	{
		RequestSOAPHeaderE requestHeaderE = new RequestSOAPHeaderE();
		RequestSOAPHeader requestHeader = new RequestSOAPHeader();
		String spId = "130304";
		String serviceId = "1303042000006010";
		String spPassword = "Acom123123";

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		String created = sdf.format(Calendar.getInstance().getTime());
		String password = NonceGenerator.getInstance()
				.getNonce(spId + spPassword + created);
		requestHeader.setSpId(spId);
		requestHeader.setSpPassword(password);
		requestHeader.setServiceId(serviceId);
		requestHeader.setTimeStamp(created);
		requestHeader.setOA("841205332233");
		requestHeader.setFA("841205332233");
		requestHeaderE.setRequestSOAPHeader(requestHeader);
		return requestHeaderE;
	}

	public static StopSmsNotificationE createBody()
	{
		StopSmsNotificationE startSmsNotificationE = new StopSmsNotificationE();
		StopSmsNotification request = new StopSmsNotification();
		request.setCorrelator("12344");
		startSmsNotificationE.setStopSmsNotification(request);
		return startSmsNotificationE;
	}

	public static void StopSmsNotification(RequestSOAPHeaderE header,
			StopSmsNotificationE body)
	{
		try
		{
			SmsNotificationManagerServiceStub stub = new SmsNotificationManagerServiceStub("http://113.187.31.2:8080/SmsNotificationManagerService/services/SmsNotificationManager");
			stub._getServiceClient()
					.addHeader(header.getOMElement(RequestSOAPHeaderE.MY_QNAME, OMAbstractFactory
							.getSOAP11Factory()));
			StopSmsNotificationResponseE response = stub
					.stopSmsNotification(body);
			System.out
					.println(response
							.getOMElement(StartSmsNotificationResponseE.MY_QNAME, OMAbstractFactory
									.getSOAP11Factory()));
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
		catch (ADBException e)
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
	}

	public static void main(String[] args)
	{
		StopSmsNotification(createHeader(), createBody());
	}

}
