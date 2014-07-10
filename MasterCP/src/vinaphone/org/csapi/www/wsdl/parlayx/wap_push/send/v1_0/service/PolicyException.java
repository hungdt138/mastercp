
/**
 * PolicyException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.4  Built on : Apr 26, 2008 (06:24:30 EDT)
 */

package vinaphone.org.csapi.www.wsdl.parlayx.wap_push.send.v1_0.service;

public class PolicyException extends java.lang.Exception{
    
    private vinaphone.org.csapi.www.schema.parlayx.common.v2_1.PolicyExceptionE faultMessage;
    
    public PolicyException() {
        super("PolicyException");
    }
           
    public PolicyException(java.lang.String s) {
       super(s);
    }
    
    public PolicyException(java.lang.String s, java.lang.Throwable ex) {
      super(s, ex);
    }
    
    public void setFaultMessage(vinaphone.org.csapi.www.schema.parlayx.common.v2_1.PolicyExceptionE msg){
       faultMessage = msg;
    }
    
    public vinaphone.org.csapi.www.schema.parlayx.common.v2_1.PolicyExceptionE getFaultMessage(){
       return faultMessage;
    }
}
    