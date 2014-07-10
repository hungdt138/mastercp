/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vinaphone.org.csapi.www.wsdl.parlayx.sms.send.v2_2.service;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axiom.soap.SOAPFactory;
import org.apache.axiom.soap.SOAPHeaderBlock;
/**
 *
 * @author hungdt
 */
public class SoapHeaderUtil {
      /*
     * SoapHeader'label Name.
     */
    /*
     * spId
     */
    protected static final String SOAPHEADER_SPID = "spId";

    /*
     * spPassword
     */
    protected static final String SOAPHEADER_SPPASSWORD = "spPassword";

    /*
     * serviceId
     */
    protected static final String SOAPHEADER_SERVICEID = "serviceId";

    /*
     * timeStamp
     */
    protected static final String SOAPHEADER_TIMESTAMP = "timeStamp";

    /*
     * OA
     */
    protected static final String SOAPHEADER_OA = "OA";

    /*
     * FA
     */
    protected static final String SOAPHEADER_FA = "FA";

    /*
     * Token
     */
    protected static final String SOAPHEADER_TOKEN = "token";

    /*
     * NameSpace for SOAPMessage
     */
    protected static final String nameSpace = "http://www.huawei.com/schema/osg/common/v2_1";

    /*
     * Request SOAPHeader for Sending Message to SDP
     */
    protected static final String SOAPHEADER_RequestSOAPHeader = "RequestSOAPHeader";

    /**
     * Generate SoapHeader which is necessary when sending Message to SDP
     * @return the generated SoapHeaderblock
     */
    public static SOAPHeaderBlock getAOMTSOAPHeaderBlock()
    {
        /*
         * SP's SOAPHeader Info,when sending message to SDP,
         * following SoapHeader fields may be taken to SDP.
         * SpID,spPassword,serviceID,timeStamp,OA,FA,token,
         * this can reference to API document.  
         * SP can get spID£¬ password, serviceID on SDP when issuing service on SDP.
         *  
         */
        /*
         * SpID
         */
        String spIDStr = "005556";    
        
        /*
         *  password,it is unencrypted,when sending to SDP,it should be encrypted by MD5 when needing authenticated by password.
         *  spPassword = MD5(spId + password + timeStamp)
         */
        String passwordStr = "Acom123123";  
        
        /*
         * serviceID
         */
        String serviceIDStr = "0055562000014434"; 
        
        /*
         * current timestamp
         */
        String timeStampStr = Long.toString(System.currentTimeMillis()); 
        
        /*
         * Service subscription address. Do not fill in it in case of group transmission.
         */
        String oaStr = "841242944164";   
        
        /*
         * Payment address. Optional
         */
        String faStr = "841242944164";
       
        /*
         * token is optional.
         * When there is token in SOAPHeader,it means that SP will be authenticated by token.
         * SP can get token using TokenManage interface when SP needs to authenticate by token.
         * 
         */
        String tokenStr = "";       
        

        // set SoapHeader
        OMFactory fac = OMAbstractFactory.getOMFactory();
        SOAPFactory sfac = OMAbstractFactory.getSOAP11Factory();
        
        // the namespace Object of request Header,when sending Message to SDP,it is mandatory 
        OMNamespace omNs = fac.createOMNamespace(nameSpace, "tns");

        // the SOAPHeaderBlock of request,when sending Message to SDP,it is mandatory and must be "RequestSOAPHeader"
        SOAPHeaderBlock soapHeadB = sfac.createSOAPHeaderBlock(
                SOAPHEADER_RequestSOAPHeader, omNs);

        // sp id
        SOAPHeaderBlock spID = sfac
                .createSOAPHeaderBlock(SOAPHEADER_SPID, omNs);
        spID.addChild(sfac.createOMText(spIDStr));

        /*
         * password,encrypted by md5
         * spPassword = MD5(spId + password + timeStamp)
         */ 
       
        String spPassword = md5(spIDStr + passwordStr +timeStampStr);
        SOAPHeaderBlock password = sfac.createSOAPHeaderBlock(
                SOAPHEADER_SPPASSWORD, omNs);
        password.addChild(sfac.createOMText(spPassword));

        // timestamp
        SOAPHeaderBlock timestamp = sfac.createSOAPHeaderBlock(
                SOAPHEADER_TIMESTAMP, omNs);
        timestamp.addChild(sfac.createOMText(timeStampStr));

        // service id
        SOAPHeaderBlock serviceID = sfac.createSOAPHeaderBlock(
                SOAPHEADER_SERVICEID, omNs);
        serviceID.addChild(sfac.createOMText(serviceIDStr));

        // oa
        SOAPHeaderBlock oa = sfac.createSOAPHeaderBlock(SOAPHEADER_OA, omNs);
        oa.addChild(sfac.createOMText(oaStr));

        // fa
        SOAPHeaderBlock fa = sfac.createSOAPHeaderBlock(SOAPHEADER_FA, omNs);
        fa.addChild(sfac.createOMText(faStr));

        // token
        SOAPHeaderBlock token = sfac.createSOAPHeaderBlock(SOAPHEADER_TOKEN,
                omNs);
        token.addChild(sfac.createOMText(tokenStr));

        soapHeadB.setMustUnderstand(false);

        /*
         * Add soapHeader block to RequestSoapHeader 
         */
        soapHeadB.addChild(spID);
        soapHeadB.addChild(password);
        soapHeadB.addChild(timestamp);
        soapHeadB.addChild(serviceID);
        soapHeadB.addChild(oa);
        soapHeadB.addChild(fa);
        soapHeadB.addChild(token);

        return soapHeadB;
    }
    
    /**
     * md5 arithmetic for encrypting String
     * @param s the param you want to encrypt
     * @return encrypted String
     */
    public static String md5(String s)
    {
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D',
                'E', 'F' };
        try
        {
            byte[] strTemp = s.getBytes();
            MessageDigest MD = MessageDigest.getInstance("MD5");            
            MD.update(strTemp);
            byte[] md = MD.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++)
            {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return String.valueOf(str);
        }
        catch (Exception e)
        {
            return null;
        }
    }
    
//    public static void main(String[] args)
//    {
//    	System.out.println(md5("130304Acom12345620130402105250"));
//    	//System.out.println(Long.toString(System.currentTimeMillis()));
//    	
//    	Date date = new Date();
//    	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
//    	String formattedDate = sdf.format(date);
//    	System.out.println(formattedDate);
//    }
}
