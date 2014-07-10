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
import org.apache.axis2.databinding.types.URI;
import org.apache.axis2.databinding.types.URI.MalformedURIException;
import org.csapi.www.wsdl.parlayx.sms.notification_manager.v2_3.service.PolicyException;
import org.csapi.www.wsdl.parlayx.sms.notification_manager.v2_3.service.ServiceException;
import org.csapi.www.wsdl.parlayx.sms.notification_manager.v2_3.service.SmsNotificationManagerServiceStub;
import org.csapi.www.wsdl.parlayx.sms.notification_manager.v2_3.service.SmsNotificationManagerServiceStub.SimpleReference;
import org.csapi.www.wsdl.parlayx.sms.notification_manager.v2_3.service.SmsNotificationManagerServiceStub.StartSmsNotification;
import org.csapi.www.wsdl.parlayx.sms.notification_manager.v2_3.service.SmsNotificationManagerServiceStub.StartSmsNotificationE;
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
public class SoapStartSmsNotificationClient
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

	public static StartSmsNotificationE createBody()
	{
		try
		{
			URI number = new URI("841205332233");
			URI endpoint = new URI("http://183.91.14.218:8080/axis2/services/SmsNotificationService");
			StartSmsNotificationE startSmsNotificationE = new StartSmsNotificationE();
			StartSmsNotification request = new StartSmsNotification();
			request.setCriteria("1234242");
			SimpleReference sim = new SimpleReference();
			sim.setCorrelator("123456");
			sim.setEndpoint(endpoint);
			sim.setInterfaceName("SmsNotification");
			request.setReference(sim);
			request.setCriteria("123");
			request.setSmsServiceActivationNumber(number);
			startSmsNotificationE.setStartSmsNotification(request);
			return startSmsNotificationE;
		}
		catch (MalformedURIException e)
		{
			return null;
		}
	}

    public static StopSmsNotificationE createBodystop()
    {
        StopSmsNotificationE startSmsNotificationE = new StopSmsNotificationE();
        StopSmsNotification request = new StopSmsNotification();
        request.setCorrelator("123456");
        startSmsNotificationE.setStopSmsNotification(request);
        return startSmsNotificationE;
}

	
	public static void StartSmsNotification(RequestSOAPHeaderE header,
			StartSmsNotificationE body)
	{
		try
		{
			SmsNotificationManagerServiceStub stub = new SmsNotificationManagerServiceStub("http://113.187.31.2:8080/SmsNotificationManagerService/services/SmsNotificationManager");
			stub._getServiceClient()
					.addHeader(header.getOMElement(RequestSOAPHeaderE.MY_QNAME, OMAbstractFactory
							.getSOAP11Factory()));
			StartSmsNotificationResponseE response = stub
					.startSmsNotification(body);
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
		StartSmsNotification(createHeader(), createBody());
	}

}
