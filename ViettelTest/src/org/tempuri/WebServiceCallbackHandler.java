
/**
 * WebServiceCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.4  Built on : Apr 26, 2008 (06:24:30 EDT)
 */

    package org.tempuri;

    /**
     *  WebServiceCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class WebServiceCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public WebServiceCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public WebServiceCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for InsertMO method
            * override this method for handling normal response from InsertMO operation
            */
           public void receiveResultInsertMO(
                    org.tempuri.WebServiceStub.InsertMOResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from InsertMO operation
           */
            public void receiveErrorInsertMO(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for InsertMT method
            * override this method for handling normal response from InsertMT operation
            */
           public void receiveResultInsertMT(
                    org.tempuri.WebServiceStub.InsertMTResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from InsertMT operation
           */
            public void receiveErrorInsertMT(java.lang.Exception e) {
            }
                


    }
    