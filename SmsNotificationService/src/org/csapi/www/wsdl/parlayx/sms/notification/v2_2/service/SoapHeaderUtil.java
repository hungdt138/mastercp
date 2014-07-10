/**
 * 
 */
package org.csapi.www.wsdl.parlayx.sms.notification.v2_2.service;

import java.util.Iterator;

import org.apache.axiom.om.OMElement;
import org.apache.axiom.soap.SOAPHeader;
import org.apache.axiom.soap.SOAPHeaderBlock;
import org.apache.axis2.context.MessageContext;
/**
 * @author hungdt
 *
 */
public class SoapHeaderUtil {
	public static void printSoapHeader(MessageContext msgContext)
    {
        /*
         * get SOAPHeade from MessageContext
         */
        SOAPHeader soapHeader = msgContext.getEnvelope().getHeader();
        if (soapHeader != null)
        {
            Iterator iter = soapHeader.examineHeaderBlocks(null);

            // soapheader is the first block.
            SOAPHeaderBlock firstblock = null;
            if (iter.hasNext())
            {
                firstblock = (SOAPHeaderBlock) iter.next();
            }
            // get value from block
            Iterator attrs = null;
            if (null != firstblock)
                attrs = firstblock.getChildElements();

            if (null != attrs)
            {
                while (attrs.hasNext())
                {
                    OMElement element = (OMElement) attrs.next();
                    String tag = element.getLocalName();

                    // get soapheader info

                    if (tag.equals("spRevId"))
                    {
                        //reverse spID
                        System.out.println("spRevId      : " + element.getText());
                    }
                    if (tag.equals("spRevpassword"))
                    {
                        //reverse password
                        System.out.println("spRevpassword: " + element.getText());
                    }
                    if (tag.equals("spId"))
                    {
                        //spID
                        System.out.println("spId         : " + element.getText());
                    }
                    if (tag.equals("serviceId"))
                    {
                        //spID
                        System.out.println("serviceId    : " + element.getText());
                    }
                }
            }
        }
    }
}
