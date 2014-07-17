
/**
 * PolicyException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.4  Built on : Apr 26, 2008 (06:24:30 EDT)
 */

package vms.org.csapi.www.wsdl.parlayx.sms.send.v2_2.service;

public class PolicyException extends java.lang.Exception{
    
    private vms.org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.SendSmsServiceStub.PolicyExceptionE faultMessage;
    
    public PolicyException() {
        super("PolicyException");
    }
           
    public PolicyException(java.lang.String s) {
       super(s);
    }
    
    public PolicyException(java.lang.String s, java.lang.Throwable ex) {
      super(s, ex);
    }
    
    public void setFaultMessage(vms.org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.SendSmsServiceStub.PolicyExceptionE msg){
       faultMessage = msg;
    }
    
    public vms.org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.SendSmsServiceStub.PolicyExceptionE getFaultMessage(){
       return faultMessage;
    }
}
    