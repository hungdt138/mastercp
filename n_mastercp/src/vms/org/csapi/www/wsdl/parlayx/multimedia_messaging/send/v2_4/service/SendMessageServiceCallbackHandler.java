
/**
 * SendMessageServiceCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.4  Built on : Apr 26, 2008 (06:24:30 EDT)
 */

    package vms.org.csapi.www.wsdl.parlayx.multimedia_messaging.send.v2_4.service;

    /**
     *  SendMessageServiceCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class SendMessageServiceCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public SendMessageServiceCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public SendMessageServiceCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for sendMessage method
            * override this method for handling normal response from sendMessage operation
            */
           public void receiveResultsendMessage(
                    vms.org.csapi.www.wsdl.parlayx.multimedia_messaging.send.v2_4.service.SendMessageServiceStub.SendMessageResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from sendMessage operation
           */
            public void receiveErrorsendMessage(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getMessageDeliveryStatus method
            * override this method for handling normal response from getMessageDeliveryStatus operation
            */
           public void receiveResultgetMessageDeliveryStatus(
                    vms.org.csapi.www.wsdl.parlayx.multimedia_messaging.send.v2_4.service.SendMessageServiceStub.GetMessageDeliveryStatusResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getMessageDeliveryStatus operation
           */
            public void receiveErrorgetMessageDeliveryStatus(java.lang.Exception e) {
            }
                


    }
    