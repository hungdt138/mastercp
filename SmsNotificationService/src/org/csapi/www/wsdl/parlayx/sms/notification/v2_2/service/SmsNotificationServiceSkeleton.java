/**
 * SmsNotificationServiceSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.4  Built on : Apr 26, 2008 (06:24:30 EDT)
 */
package org.csapi.www.wsdl.parlayx.sms.notification.v2_2.service;

import org.apache.axis2.context.MessageContext;
import org.csapi.www.schema.parlayx.sms.notification.v2_2.local.NotifySmsDeliveryReceipt;
import org.csapi.www.schema.parlayx.sms.notification.v2_2.local.NotifySmsDeliveryReceiptResponse;
import org.csapi.www.schema.parlayx.sms.notification.v2_2.local.NotifySmsDeliveryReceiptResponseE;
import org.csapi.www.schema.parlayx.sms.notification.v2_2.local.NotifySmsReception;
import org.csapi.www.schema.parlayx.sms.notification.v2_2.local.NotifySmsReceptionResponse;
import org.csapi.www.schema.parlayx.sms.notification.v2_2.local.NotifySmsReceptionResponseE;

/**
 * SmsNotificationServiceSkeleton java skeleton for the axisService
 */
public class SmsNotificationServiceSkeleton
{

	/**
	 * Auto generated method signature
	 * 
	 * @param notifySmsDeliveryReceipt
	 */

	public org.csapi.www.schema.parlayx.sms.notification.v2_2.local.NotifySmsDeliveryReceiptResponseE notifySmsDeliveryReceipt
			(
					org.csapi.www.schema.parlayx.sms.notification.v2_2.local.NotifySmsDeliveryReceiptE notifySmsDeliveryReceipt
			)
	{
		MessageContext msgContext = MessageContext.getCurrentMessageContext();
		/*
		 * print soapheader
		 */
		System.out.println("=============SoapHeader begin====================");
		SoapHeaderUtil.printSoapHeader(msgContext);
		System.out.println("=============SoapHeader end====================");

		NotifySmsDeliveryReceipt receipt = notifySmsDeliveryReceipt
				.getNotifySmsDeliveryReceipt();

		// print reqest soap body
		System.out.println("=============Soapbody begin====================");
		System.out.println("correlator    : " + receipt.getCorrelator());
		if (null != receipt.getDeliveryStatus())
		{
			System.out.println("Address       : "
					+ receipt.getDeliveryStatus().getAddress());

			System.out.println("DeliveryStatus: "
					+ receipt.getDeliveryStatus().getDeliveryStatus()
							.getValue());
		}
		System.out.println("=============Soapbody end====================");
		// generate reponse
		NotifySmsDeliveryReceiptResponseE reponseE = new NotifySmsDeliveryReceiptResponseE();
		NotifySmsDeliveryReceiptResponse response = new NotifySmsDeliveryReceiptResponse();
		reponseE.setNotifySmsDeliveryReceiptResponse(response);
		return reponseE;
	}

	/**
	 * Auto generated method signature
	 * 
	 * @param notifySmsReception
	 */

	public org.csapi.www.schema.parlayx.sms.notification.v2_2.local.NotifySmsReceptionResponseE notifySmsReception
			(
					org.csapi.www.schema.parlayx.sms.notification.v2_2.local.NotifySmsReceptionE notifySmsReception
			)
	{
		MessageContext msgContext = MessageContext.getCurrentMessageContext();
		/*
		 * print soapheader
		 */
		System.out.println("=============SoapHeader begin====================");
		SoapHeaderUtil.printSoapHeader(msgContext);
		System.out.println("=============SoapHeader end====================");

		// print reqest soap body
		NotifySmsReception receipt = notifySmsReception.getNotifySmsReception();
		System.out.println("=============Soapbody begin====================");
		System.out.println("correlator                : "
				+ receipt.getCorrelator());
		if (null != receipt.getMessage())
		{
			System.out.println("Message                   : "
					+ receipt.getMessage().getMessage());
			System.out.println("DateTime                  : "
					+ receipt.getMessage().getDateTime().getTimeInMillis());
			System.out.println("SenderAddress             : "
					+ receipt.getMessage().getSenderAddress());
			System.out.println("SmsServiceActivationNumber: "
					+ receipt.getMessage().getSmsServiceActivationNumber());
		}
		System.out.println("=============Soapbody end====================");
		// generate reponse
		NotifySmsReceptionResponseE reponseE = new NotifySmsReceptionResponseE();
		NotifySmsReceptionResponse response = new NotifySmsReceptionResponse();
		reponseE.setNotifySmsReceptionResponse(response);
		return reponseE;
	}

}
