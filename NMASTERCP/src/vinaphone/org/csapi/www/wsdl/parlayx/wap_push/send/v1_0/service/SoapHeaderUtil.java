/**
 *-----------------------------------------------------------------------------
 * @ Copyright(c) 2006~2012  Huawei Technologies, Ltd. All Rights Reserved.
 *-----------------------------------------------------------------------------
 * FILE  NAME             : SoapHeaderUtil.java
 * DESCRIPTION            :
 * PRINCIPAL AUTHOR       : Huawei Technologies OSG Project Team
 * SYSTEM NAME            : OSG
 * MODULE NAME            : OSG 
 * LANGUAGE               : Java
 * DATE OF FIRST RELEASE  :
 *-----------------------------------------------------------------------------
 * @ Created on 2009-5-5
 * @ Release 1.0.0.0
 * @ Version 1.0
 * -----------------------------------------------------------------------------------
 * Date	            Author	      Version        Description
 * -----------------------------------------------------------------------------------
 * 2009-5-5    yaoxiaohu/00135078        1.0 	     Initial Create
 * -----------------------------------------------------------------------------------
 */
package vinaphone.org.csapi.www.wsdl.parlayx.wap_push.send.v1_0.service;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axiom.soap.SOAPFactory;
import org.apache.axiom.soap.SOAPHeaderBlock;

/**
 * SoapHeaderUtil is used for process SoapHeader. 
 * @author y00135078
 *
 */
public class SoapHeaderUtil
{

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
         * SP can get spID�� password, serviceID on SDP when issuing service on SDP.
         *  
         */
        String spIDStr = "005556";    //SpID.
        /*
         *  password,it is unencrypted,when sending to SDP,it should be encrypted by MD5 when needing authenticated by password.
         *  spPassword = MD5(spId + password + timeStamp)
         */
        String passwordStr = "Acom123123";  
        
        /*
         * serviceID
         */
        String serviceIDStr = "0055562000014433"; 
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
        String tokenStr = "";       //SP can get token using TokenManage interface when SP needs to authentication by token.
        

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
    
    public static void main(String[] args)
    {
    	
    	System.out.println(md5("130304Acom12345620130408025252"));
    	Date date = new Date();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
    	String formattedDate = sdf.format(date);
    	System.out.println(formattedDate);
    }

}
