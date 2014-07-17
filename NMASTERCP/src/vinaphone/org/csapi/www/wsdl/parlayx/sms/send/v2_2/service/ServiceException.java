
/**
 * ServiceException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.4  Built on : Apr 26, 2008 (06:24:30 EDT)
 */

package vinaphone.org.csapi.www.wsdl.parlayx.sms.send.v2_2.service;

public class ServiceException extends java.lang.Exception{
    
    private vinaphone.org.csapi.www.schema.parlayx.common.v2_1.ServiceExceptionE faultMessage;
    
    public ServiceException() {
        super("ServiceException");
    }
           
    public ServiceException(java.lang.String s) {
       super(s);
    }
    
    public ServiceException(java.lang.String s, java.lang.Throwable ex) {
      super(s, ex);
    }
    
    public void setFaultMessage(vinaphone.org.csapi.www.schema.parlayx.common.v2_1.ServiceExceptionE msg){
       faultMessage = msg;
    }
    
    public vinaphone.org.csapi.www.schema.parlayx.common.v2_1.ServiceExceptionE getFaultMessage(){
       return faultMessage;
    }
}
    