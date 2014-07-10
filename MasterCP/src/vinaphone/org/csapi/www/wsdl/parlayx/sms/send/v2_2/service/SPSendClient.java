/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vinaphone.org.csapi.www.wsdl.parlayx.sms.send.v2_2.service;

import java.util.Scanner;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.databinding.types.URI;
import vinaphone.org.csapi.www.schema.parlayx.common.v2_1.SimpleReference;
import vinaphone.org.csapi.www.schema.parlayx.sms.send.v2_2.local.SendSms;
import vinaphone.org.csapi.www.schema.parlayx.sms.send.v2_2.local.SendSmsE;
import vinaphone.org.csapi.www.schema.parlayx.sms.send.v2_2.local.SendSmsResponseE;
/**
 *
 * @author hungdt
 */
public class SPSendClient {
    /***
     * entry of SPClient
     * @param args
     */
    public static void main(String[] args)
    {
        try
        {
            /*
             * the sendSms service URI of SDP System
             */
//            String sendSmsURI = "http://127.0.0.1:8888/osg/services/SendSmsService";
            
            Scanner input= new Scanner(System.in);
            
            String sendSmsURI = "http://10.211.11.6:8080/osg/services/SendSmsService";
            
            System.out.println("INPUT URI");

            sendSmsURI = input.nextLine();
            /*
             * Generate SendSmsServiceStub for sending SMS
             */
            SendSmsServiceStub stub = new SendSmsServiceStub(sendSmsURI);

            /*
             * generate necessary SoapHeader
             */
            ServiceClient client = stub._getServiceClient();
            client.addHeader(SoapHeaderUtil.getAOMTSOAPHeaderBlock());

            /*
             * Create SoapBody
             */
            SendSmsE sendSmsE = new SendSmsE();
            SendSms sendSms = new SendSms();

            /*
             * Set SendSMS's Each field to SoapBody,
             * including Address,Message,SenderName,
             * SimpleReference and ChargingInformation.
             */
            /*
             * Set Address.
             * the destination addresses of message
             * it's element type is URI and schema must be tel.
             * Address format:country code + mobile phone number of a subscriber
             * Example: 8699907550001 (which can be prefixed by +, 0, or both)
             *
             * Address is an URI array,when SMS is sent to multi-users,
             * it will be multi-URIs.
             */
            URI[] addr = new URI[1];
             System.out.println("INPUT ISDN");
             String isdn = input.nextLine();
            addr[0] = new URI(isdn);
          //  addr[1] = new URI("84906130890");
            sendSms.setAddresses(addr);

            /*
             * Set Message.
             * the message content of SMS.
             * it¡¯s supported max length is 153 characters,
             * when message is longer than 153 characters,
             * it will be spit to several package,
             * the max number of packages is 255
             */
            
            String message = "ACOM Test";
            System.out.println("INPUT MSG");
             message = input.nextLine();
            sendSms.setMessage(message);

            /*
             * Set SenderName.
             * the Name of SP,it will display on User's terminal.
             * it may be sp's center accessCode,it is obtained when issuing service.
             */
            sendSms.setSenderName("8926");

            /*
             * Set SimpleReference.
             * When SP fill this filed, it means the mode to obtain status reports
             * is notify,otherwise,it means the mode is get.
             *
             */
            SimpleReference ref = new SimpleReference();
            //the identifier of SimpleReference
            ref.setCorrelator("123456");
            //endpoint,the webservice which SP used to receive SMS status.
            ref
                    .setEndpoint(new URI(
                            "http://183.91.14.218:8080/axis2/services/SmsNotificationService"));
            //SimpleReference's interfacename
            ref.setInterfaceName("SmsNotificationService");
            sendSms.setReceiptRequest(ref);


            sendSmsE.setSendSms(sendSms);

            /*
             * Invoke Stub's sendSms Operation for Sending SMS to SDP.
             */
            SendSmsResponseE rsp = stub.sendSms(sendSmsE);

            /*
             * Output the result of response.
             */
            System.out.println("requestIdentifier:"
                    + rsp.getSendSmsResponse().getResult());

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
