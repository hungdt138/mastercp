/**
 *-----------------------------------------------------------------------------
 * @ Copyright(c) 2006~2012  Huawei Technologies, Ltd. All Rights Reserved.
 *-----------------------------------------------------------------------------
 * FILE  NAME             : SPSendClient.java
 * DESCRIPTION            :
 * PRINCIPAL AUTHOR       : Huawei Technologies OSG Project Team
 * SYSTEM NAME            : OSG
 * MODULE NAME            : OSG
 * LANGUAGE               : Java
 * DATE OF FIRST RELEASE  :
 *-----------------------------------------------------------------------------
 * @ Created on 2009-5-12
 * @ Release 1.0.0.0
 * @ Version 1.0
 * -----------------------------------------------------------------------------------
 * Date	            Author	      Version        Description
 * -----------------------------------------------------------------------------------
 * 2009-5-12    yaoxiaohu/00135078        1.0 	     Initial Create
 * -----------------------------------------------------------------------------------
 */
package vinaphone.org.csapi.www.wsdl.parlayx.wap_push.send.v1_0.service;

import java.math.BigDecimal;
import java.util.Scanner;

import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.databinding.types.URI;
import vinaphone.org.csapi.www.schema.parlayx.common.v2_1.ChargingInformation;
import vinaphone.org.csapi.www.schema.parlayx.common.v2_1.SimpleReference;
import vinaphone.org.csapi.www.schema.parlayx.wap_push.send.v1_0.local.SendPushMessage;
import vinaphone.org.csapi.www.schema.parlayx.wap_push.send.v1_0.local.SendPushMessageE;
import vinaphone.org.csapi.www.schema.parlayx.wap_push.send.v1_0.local.SendPushMessageResponseE;
import vinaphone.org.csapi.www.schema.parlayx.wap_push.v1_0.MessagePriority;

/**
 * SP's Client for sending WAP to SDP
 * @author y00135078
 *
 */
public class SPSendClient
{

    /***
     * entry of SPClient
     * @param args
     */
    public static void main(String[] args)
    {
        try
        {
            Scanner input= new Scanner(System.in);
            /*
             * the sendPushMessage service URI of SDP System
             */
            String sendPushMessageURL = "http://10.211.11.6:8080/osg/services/SendPushMessageService";

             System.out.println("INPUT URI");

            sendPushMessageURL = input.nextLine();
            
            /*
             * Generate SendPushMessageServiceStub for sending push message
             */
            SendPushMessageServiceStub stub = new SendPushMessageServiceStub(sendPushMessageURL);

            /*
             * generate necessery SoapHeader
             */
            ServiceClient client = stub._getServiceClient();
            client.addHeader(SoapHeaderUtil.getAOMTSOAPHeaderBlock());

            /*
             * Create SoapBody
             */
            SendPushMessageE sendPushMessageE = new SendPushMessageE();
            SendPushMessage sendPushMessage = new SendPushMessage();

            /*
             * Set SendPushMessage's Each field to SoapBody,
             * including Addresses,TargetURL,SenderAddress,
             * Subject,Priority,SimpleReference and ChargingInformation.
             */
            /*
             * Set Address.
             * the destination addresses of message
             * it's element type is URI and schema must be tel.
             * Address format:country code + mobile phone number of a subscriber
             * Example: 8613507550001 (which can be prefixed by +, 0, or both)
             *
             * Address is an URI array,when WAP is sent to multi-users,
             * it will be multi-URIs.
             */
            URI[] addr = new URI[1];
             System.out.println("INPUT ISDN");
             String isdn = input.nextLine();
            addr[0] = new URI(isdn);
            //addr[1] = new URI("tel:84906130890");
            sendPushMessage.setAddresses(addr);


            /*
             * Set TargetURL.
             * It indicates the URL connection of a push.
             */
            URI targetURL = new URI(
            "http://203.162.70.62:9000/FileDownload.aspx?id=4a1630d3-e833-4aa8-85da-775e2c9c36d1&t=4");
            sendPushMessage.setTargetURL(targetURL);



            /*
             * Set SenderAddress.the Name of SP.
             * It indicates the source address,it will display on User's terminal.
             * it may be sp's center accessCode.
             */
            sendPushMessage.setSenderAddress("8926");

            /*
             * Set Subject
             * It indicates the subject of a push message.
             */
            sendPushMessage.setSubject("Acom-test");

            /*
             * Set Priority
             * It indicates the priority of a push message.
             */
            sendPushMessage.setPriority(MessagePriority.Default);

            /*
             * Set SimpleReference.
             * When SP fill this filed, it means the mode to obtain status reports
             * is notify,otherwise,it means the mode is get.
             *
             */
            SimpleReference ref = new SimpleReference();
            //the identifier of SimpleReference
            ref.setCorrelator("123456");
            //endpoint,the webservice which SP used to receive WAP status.
            ref
                    .setEndpoint(new URI(
                            "http://183.91.14.218:8080/axis2/services/PushMessageNotificationService"));
            //SimpleReference's interfacename
            ref.setInterfaceName("PushMessageNotificationService");
            sendPushMessage.setReceiptRequest(ref);



            sendPushMessageE.setSendPushMessage(sendPushMessage);
            
            /*
             * Invoke Stub's sendPushMessage Operation for Sending Push Message to SDP.
             */
            SendPushMessageResponseE rsp = stub.sendPushMessage(sendPushMessageE);

            /*
             * Output the result of response.
             */
            System.out.println("requestIdentifier:"+rsp.getSendPushMessageResponse().getRequestIdentifier());


        }
        catch (PolicyException e)
        {
            /*
             * deal PolicyExcepion
             */
            System.out.println("PolicyException:\n"
                    + e.getFaultMessage().getPolicyException().getMessageId()
                    + ":" + e.getFaultMessage().getPolicyException().getText());

            e.printStackTrace();
        }
        catch (ServiceException e)
        {
            /*
             * deal PolicyExcepion
             */
            System.out
                    .println("ServiceException:\n"
                            + e.getFaultMessage().getServiceException()
                                    .getMessageId()
                            + ":"
                            + e.getFaultMessage().getServiceException()
                                    .getText());
            e.printStackTrace();
        }
        catch (Exception e)
        {
            /*
             * deal other Excepion
             */
            e.printStackTrace();
        }
    }
}