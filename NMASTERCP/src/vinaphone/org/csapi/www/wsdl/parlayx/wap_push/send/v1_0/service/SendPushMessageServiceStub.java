
/**
 * SendPushMessageServiceStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.4  Built on : Apr 26, 2008 (06:24:30 EDT)
 */
        package vinaphone.org.csapi.www.wsdl.parlayx.wap_push.send.v1_0.service;

        

        /*
        *  SendPushMessageServiceStub java implementation
        */

        
        public class SendPushMessageServiceStub extends org.apache.axis2.client.Stub
        {
        protected org.apache.axis2.description.AxisOperation[] _operations;

        //hashmaps to keep the fault mapping
        private java.util.HashMap faultExceptionNameMap = new java.util.HashMap();
        private java.util.HashMap faultExceptionClassNameMap = new java.util.HashMap();
        private java.util.HashMap faultMessageMap = new java.util.HashMap();

        private static int counter = 0;

        private static synchronized String getUniqueSuffix(){
            // reset the counter if it is greater than 99999
            if (counter > 99999){
                counter = 0;
            }
            counter = counter + 1; 
            return Long.toString(System.currentTimeMillis()) + "_" + counter;
        }

    
    private void populateAxisService() throws org.apache.axis2.AxisFault {

     //creating the Service with a unique name
     _service = new org.apache.axis2.description.AxisService("SendPushMessageService" + getUniqueSuffix());
     addAnonymousOperations();

        //creating the operations
        org.apache.axis2.description.AxisOperation __operation;

        _operations = new org.apache.axis2.description.AxisOperation[2];
        
                   __operation = new org.apache.axis2.description.OutInAxisOperation();
                

            __operation.setName(new javax.xml.namespace.QName("http://www.csapi.org/wsdl/parlayx/wap_push/send/v1_0/interface", "getPushMessageDeliveryStatus"));
	    _service.addOperation(__operation);
	    

	    
	    
            _operations[0]=__operation;
            
        
                   __operation = new org.apache.axis2.description.OutInAxisOperation();
                

            __operation.setName(new javax.xml.namespace.QName("http://www.csapi.org/wsdl/parlayx/wap_push/send/v1_0/interface", "sendPushMessage"));
	    _service.addOperation(__operation);
	    

	    
	    
            _operations[1]=__operation;
            
        
        }

    //populates the faults
    private void populateFaults(){
         
              faultExceptionNameMap.put( new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/common/v2_1","PolicyException"),"vinaphone.org.csapi.www.wsdl.parlayx.wap_push.send.v1_0.service.PolicyException");
              faultExceptionClassNameMap.put(new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/common/v2_1","PolicyException"),"vinaphone.org.csapi.www.wsdl.parlayx.wap_push.send.v1_0.service.PolicyException");
              faultMessageMap.put( new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/common/v2_1","PolicyException"),"vinaphone.org.csapi.www.schema.parlayx.common.v2_1.PolicyExceptionE");
           
              faultExceptionNameMap.put( new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/common/v2_1","ServiceException"),"vinaphone.org.csapi.www.wsdl.parlayx.wap_push.send.v1_0.service.ServiceException");
              faultExceptionClassNameMap.put(new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/common/v2_1","ServiceException"),"vinaphone.org.csapi.www.wsdl.parlayx.wap_push.send.v1_0.service.ServiceException");
              faultMessageMap.put( new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/common/v2_1","ServiceException"),"vinaphone.org.csapi.www.schema.parlayx.common.v2_1.ServiceExceptionE");
           
              faultExceptionNameMap.put( new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/common/v2_1","PolicyException"),"vinaphone.org.csapi.www.wsdl.parlayx.wap_push.send.v1_0.service.PolicyException");
              faultExceptionClassNameMap.put(new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/common/v2_1","PolicyException"),"vinaphone.org.csapi.www.wsdl.parlayx.wap_push.send.v1_0.service.PolicyException");
              faultMessageMap.put( new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/common/v2_1","PolicyException"),"vinaphone.org.csapi.www.schema.parlayx.common.v2_1.PolicyExceptionE");
           
              faultExceptionNameMap.put( new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/common/v2_1","ServiceException"),"vinaphone.org.csapi.www.wsdl.parlayx.wap_push.send.v1_0.service.ServiceException");
              faultExceptionClassNameMap.put(new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/common/v2_1","ServiceException"),"vinaphone.org.csapi.www.wsdl.parlayx.wap_push.send.v1_0.service.ServiceException");
              faultMessageMap.put( new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/common/v2_1","ServiceException"),"vinaphone.org.csapi.www.schema.parlayx.common.v2_1.ServiceExceptionE");
           


    }

    /**
      *Constructor that takes in a configContext
      */

    public SendPushMessageServiceStub(org.apache.axis2.context.ConfigurationContext configurationContext,
       java.lang.String targetEndpoint)
       throws org.apache.axis2.AxisFault {
         this(configurationContext,targetEndpoint,false);
   }


   /**
     * Constructor that takes in a configContext  and useseperate listner
     */
   public SendPushMessageServiceStub(org.apache.axis2.context.ConfigurationContext configurationContext,
        java.lang.String targetEndpoint, boolean useSeparateListener)
        throws org.apache.axis2.AxisFault {
         //To populate AxisService
         populateAxisService();
         populateFaults();

        _serviceClient = new org.apache.axis2.client.ServiceClient(configurationContext,_service);
        
	
        configurationContext = _serviceClient.getServiceContext().getConfigurationContext();

        _serviceClient.getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(
                targetEndpoint));
        _serviceClient.getOptions().setUseSeparateListener(useSeparateListener);
        
    
    }

    /**
     * Default Constructor
     */
    public SendPushMessageServiceStub(org.apache.axis2.context.ConfigurationContext configurationContext) throws org.apache.axis2.AxisFault {
        
                    this(configurationContext,"http://localhost:9080/SendPushMessageService/services/SendPushMessage" );
                
    }

    /**
     * Default Constructor
     */
    public SendPushMessageServiceStub() throws org.apache.axis2.AxisFault {
        
                    this("http://localhost:9080/SendPushMessageService/services/SendPushMessage" );
                
    }

    /**
     * Constructor taking the target endpoint
     */
    public SendPushMessageServiceStub(java.lang.String targetEndpoint) throws org.apache.axis2.AxisFault {
        this(null,targetEndpoint);
    }



        
                    /**
                     * Auto generated method signature
                     * 
                     * @see vinaphone.org.csapi.www.wsdl.parlayx.wap_push.send.v1_0.service.SendPushMessageService#getPushMessageDeliveryStatus
                     * @param getPushMessageDeliveryStatus24
                    
                     * @throws vinaphone.org.csapi.www.wsdl.parlayx.wap_push.send.v1_0.service.PolicyException : 
                     * @throws vinaphone.org.csapi.www.wsdl.parlayx.wap_push.send.v1_0.service.ServiceException : 
                     */

                    

                            public  vinaphone.org.csapi.www.schema.parlayx.wap_push.send.v1_0.local.GetPushMessageDeliveryStatusResponseE getPushMessageDeliveryStatus(

                            vinaphone.org.csapi.www.schema.parlayx.wap_push.send.v1_0.local.GetPushMessageDeliveryStatusE getPushMessageDeliveryStatus24)
                        

                    throws java.rmi.RemoteException
                    
                    
                        ,vinaphone.org.csapi.www.wsdl.parlayx.wap_push.send.v1_0.service.PolicyException
                        ,vinaphone.org.csapi.www.wsdl.parlayx.wap_push.send.v1_0.service.ServiceException{
              org.apache.axis2.context.MessageContext _messageContext = null;
              try{
               org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[0].getName());
              _operationClient.getOptions().setAction("\"\"");
              _operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

              
              
                  addPropertyToOperationClient(_operationClient,org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,"&");
              

              // create a message context
              _messageContext = new org.apache.axis2.context.MessageContext();

              

              // create SOAP envelope with that payload
              org.apache.axiom.soap.SOAPEnvelope env = null;
                    
                                                    
                                                    env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()),
                                                    getPushMessageDeliveryStatus24,
                                                    optimizeContent(new javax.xml.namespace.QName("http://www.csapi.org/wsdl/parlayx/wap_push/send/v1_0/interface",
                                                    "getPushMessageDeliveryStatus")));
                                                
        //adding SOAP soap_headers
         _serviceClient.addHeadersToEnvelope(env);
        // set the message context with that soap envelope
        _messageContext.setEnvelope(env);

        // add the message contxt to the operation client
        _operationClient.addMessageContext(_messageContext);

        //execute the operation client
        _operationClient.execute(true);

         
               org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient.getMessageContext(
                                           org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
                org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();
                
                
                                java.lang.Object object = fromOM(
                                             _returnEnv.getBody().getFirstElement() ,
                                             vinaphone.org.csapi.www.schema.parlayx.wap_push.send.v1_0.local.GetPushMessageDeliveryStatusResponseE.class,
                                              getEnvelopeNamespaces(_returnEnv));

                               
                                        return (vinaphone.org.csapi.www.schema.parlayx.wap_push.send.v1_0.local.GetPushMessageDeliveryStatusResponseE)object;
                                   
         }catch(org.apache.axis2.AxisFault f){

            org.apache.axiom.om.OMElement faultElt = f.getDetail();
            if (faultElt!=null){
                if (faultExceptionNameMap.containsKey(faultElt.getQName())){
                    //make the fault by reflection
                    try{
                        java.lang.String exceptionClassName = (java.lang.String)faultExceptionClassNameMap.get(faultElt.getQName());
                        java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
                        java.lang.Exception ex=
                                (java.lang.Exception) exceptionClass.newInstance();
                        //message class
                        java.lang.String messageClassName = (java.lang.String)faultMessageMap.get(faultElt.getQName());
                        java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
                        java.lang.Object messageObject = fromOM(faultElt,messageClass,null);
                        java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
                                   new java.lang.Class[]{messageClass});
                        m.invoke(ex,new java.lang.Object[]{messageObject});
                        
                        if (ex instanceof vinaphone.org.csapi.www.wsdl.parlayx.wap_push.send.v1_0.service.PolicyException){
                          throw (vinaphone.org.csapi.www.wsdl.parlayx.wap_push.send.v1_0.service.PolicyException)ex;
                        }
                        
                        if (ex instanceof vinaphone.org.csapi.www.wsdl.parlayx.wap_push.send.v1_0.service.ServiceException){
                          throw (vinaphone.org.csapi.www.wsdl.parlayx.wap_push.send.v1_0.service.ServiceException)ex;
                        }
                        

                        throw new java.rmi.RemoteException(ex.getMessage(), ex);
                    }catch(java.lang.ClassCastException e){
                       // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    } catch (java.lang.ClassNotFoundException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    }catch (java.lang.NoSuchMethodException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    } catch (java.lang.reflect.InvocationTargetException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    }  catch (java.lang.IllegalAccessException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    }   catch (java.lang.InstantiationException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    }
                }else{
                    throw f;
                }
            }else{
                throw f;
            }
            } finally {
                _messageContext.getTransportOut().getSender().cleanup(_messageContext);
            }
        }
            
                /**
                * Auto generated method signature for Asynchronous Invocations
                * 
                * @see vinaphone.org.csapi.www.wsdl.parlayx.wap_push.send.v1_0.service.SendPushMessageService#startgetPushMessageDeliveryStatus
                    * @param getPushMessageDeliveryStatus24
                
                */
                public  void startgetPushMessageDeliveryStatus(

                 vinaphone.org.csapi.www.schema.parlayx.wap_push.send.v1_0.local.GetPushMessageDeliveryStatusE getPushMessageDeliveryStatus24,

                  final vinaphone.org.csapi.www.wsdl.parlayx.wap_push.send.v1_0.service.SendPushMessageServiceCallbackHandler callback)

                throws java.rmi.RemoteException{

              org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[0].getName());
             _operationClient.getOptions().setAction("\"\"");
             _operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

              
              
                  addPropertyToOperationClient(_operationClient,org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,"&");
              


              // create SOAP envelope with that payload
              org.apache.axiom.soap.SOAPEnvelope env=null;
              final org.apache.axis2.context.MessageContext _messageContext = new org.apache.axis2.context.MessageContext();

                    
                                    //Style is Doc.
                                    
                                                    
                                                    env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()),
                                                    getPushMessageDeliveryStatus24,
                                                    optimizeContent(new javax.xml.namespace.QName("http://www.csapi.org/wsdl/parlayx/wap_push/send/v1_0/interface",
                                                    "getPushMessageDeliveryStatus")));
                                                
        // adding SOAP soap_headers
         _serviceClient.addHeadersToEnvelope(env);
        // create message context with that soap envelope
        _messageContext.setEnvelope(env);

        // add the message context to the operation client
        _operationClient.addMessageContext(_messageContext);


                    
                        _operationClient.setCallback(new org.apache.axis2.client.async.AxisCallback() {
                            public void onMessage(org.apache.axis2.context.MessageContext resultContext) {
                            try {
                                org.apache.axiom.soap.SOAPEnvelope resultEnv = resultContext.getEnvelope();
                                
                                        java.lang.Object object = fromOM(resultEnv.getBody().getFirstElement(),
                                                                         vinaphone.org.csapi.www.schema.parlayx.wap_push.send.v1_0.local.GetPushMessageDeliveryStatusResponseE.class,
                                                                         getEnvelopeNamespaces(resultEnv));
                                        callback.receiveResultgetPushMessageDeliveryStatus(
                                        (vinaphone.org.csapi.www.schema.parlayx.wap_push.send.v1_0.local.GetPushMessageDeliveryStatusResponseE)object);
                                        
                            } catch (org.apache.axis2.AxisFault e) {
                                callback.receiveErrorgetPushMessageDeliveryStatus(e);
                            }
                            }

                            public void onError(java.lang.Exception error) {
								if (error instanceof org.apache.axis2.AxisFault) {
									org.apache.axis2.AxisFault f = (org.apache.axis2.AxisFault) error;
									org.apache.axiom.om.OMElement faultElt = f.getDetail();
									if (faultElt!=null){
										if (faultExceptionNameMap.containsKey(faultElt.getQName())){
											//make the fault by reflection
											try{
													java.lang.String exceptionClassName = (java.lang.String)faultExceptionClassNameMap.get(faultElt.getQName());
													java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
													java.lang.Exception ex=
														(java.lang.Exception) exceptionClass.newInstance();
													//message class
													java.lang.String messageClassName = (java.lang.String)faultMessageMap.get(faultElt.getQName());
														java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
													java.lang.Object messageObject = fromOM(faultElt,messageClass,null);
													java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
															new java.lang.Class[]{messageClass});
													m.invoke(ex,new java.lang.Object[]{messageObject});
													
													if (ex instanceof vinaphone.org.csapi.www.wsdl.parlayx.wap_push.send.v1_0.service.PolicyException){
														callback.receiveErrorgetPushMessageDeliveryStatus((vinaphone.org.csapi.www.wsdl.parlayx.wap_push.send.v1_0.service.PolicyException)ex);
											            return;
										            }
										            
													if (ex instanceof vinaphone.org.csapi.www.wsdl.parlayx.wap_push.send.v1_0.service.ServiceException){
														callback.receiveErrorgetPushMessageDeliveryStatus((vinaphone.org.csapi.www.wsdl.parlayx.wap_push.send.v1_0.service.ServiceException)ex);
											            return;
										            }
										            
					
										            callback.receiveErrorgetPushMessageDeliveryStatus(new java.rmi.RemoteException(ex.getMessage(), ex));
                                            } catch(java.lang.ClassCastException e){
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorgetPushMessageDeliveryStatus(f);
                                            } catch (java.lang.ClassNotFoundException e) {
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorgetPushMessageDeliveryStatus(f);
                                            } catch (java.lang.NoSuchMethodException e) {
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorgetPushMessageDeliveryStatus(f);
                                            } catch (java.lang.reflect.InvocationTargetException e) {
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorgetPushMessageDeliveryStatus(f);
                                            } catch (java.lang.IllegalAccessException e) {
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorgetPushMessageDeliveryStatus(f);
                                            } catch (java.lang.InstantiationException e) {
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorgetPushMessageDeliveryStatus(f);
                                            } catch (org.apache.axis2.AxisFault e) {
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorgetPushMessageDeliveryStatus(f);
                                            }
									    } else {
										    callback.receiveErrorgetPushMessageDeliveryStatus(f);
									    }
									} else {
									    callback.receiveErrorgetPushMessageDeliveryStatus(f);
									}
								} else {
								    callback.receiveErrorgetPushMessageDeliveryStatus(error);
								}
                            }

                            public void onFault(org.apache.axis2.context.MessageContext faultContext) {
                                org.apache.axis2.AxisFault fault = org.apache.axis2.util.Utils.getInboundFaultFromMessageContext(faultContext);
                                onError(fault);
                            }

                            public void onComplete() {
                                try {
                                    _messageContext.getTransportOut().getSender().cleanup(_messageContext);
                                } catch (org.apache.axis2.AxisFault axisFault) {
                                    callback.receiveErrorgetPushMessageDeliveryStatus(axisFault);
                                }
                            }
                });
                        

          org.apache.axis2.util.CallbackReceiver _callbackReceiver = null;
        if ( _operations[0].getMessageReceiver()==null &&  _operationClient.getOptions().isUseSeparateListener()) {
           _callbackReceiver = new org.apache.axis2.util.CallbackReceiver();
          _operations[0].setMessageReceiver(
                    _callbackReceiver);
        }

           //execute the operation client
           _operationClient.execute(false);

                    }
                
                    /**
                     * Auto generated method signature
                     * 
                     * @see vinaphone.org.csapi.www.wsdl.parlayx.wap_push.send.v1_0.service.SendPushMessageService#sendPushMessage
                     * @param sendPushMessage26
                    
                     * @throws vinaphone.org.csapi.www.wsdl.parlayx.wap_push.send.v1_0.service.PolicyException : 
                     * @throws vinaphone.org.csapi.www.wsdl.parlayx.wap_push.send.v1_0.service.ServiceException : 
                     */

                    

                            public  vinaphone.org.csapi.www.schema.parlayx.wap_push.send.v1_0.local.SendPushMessageResponseE sendPushMessage(

                            vinaphone.org.csapi.www.schema.parlayx.wap_push.send.v1_0.local.SendPushMessageE sendPushMessage26)
                        

                    throws java.rmi.RemoteException
                    
                    
                        ,vinaphone.org.csapi.www.wsdl.parlayx.wap_push.send.v1_0.service.PolicyException
                        ,vinaphone.org.csapi.www.wsdl.parlayx.wap_push.send.v1_0.service.ServiceException{
              org.apache.axis2.context.MessageContext _messageContext = null;
              try{
               org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[1].getName());
              _operationClient.getOptions().setAction("\"\"");
              _operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

              
              
                  addPropertyToOperationClient(_operationClient,org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,"&");
              

              // create a message context
              _messageContext = new org.apache.axis2.context.MessageContext();

              

              // create SOAP envelope with that payload
              org.apache.axiom.soap.SOAPEnvelope env = null;
                    
                                                    
                                                    env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()),
                                                    sendPushMessage26,
                                                    optimizeContent(new javax.xml.namespace.QName("http://www.csapi.org/wsdl/parlayx/wap_push/send/v1_0/interface",
                                                    "sendPushMessage")));
                                                
        //adding SOAP soap_headers
         _serviceClient.addHeadersToEnvelope(env);
        // set the message context with that soap envelope
        _messageContext.setEnvelope(env);

        // add the message contxt to the operation client
        _operationClient.addMessageContext(_messageContext);

        //execute the operation client
        _operationClient.execute(true);

         
               org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient.getMessageContext(
                                           org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
                org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();
                
                
                                java.lang.Object object = fromOM(
                                             _returnEnv.getBody().getFirstElement() ,
                                             vinaphone.org.csapi.www.schema.parlayx.wap_push.send.v1_0.local.SendPushMessageResponseE.class,
                                              getEnvelopeNamespaces(_returnEnv));

                               
                                        return (vinaphone.org.csapi.www.schema.parlayx.wap_push.send.v1_0.local.SendPushMessageResponseE)object;
                                   
         }catch(org.apache.axis2.AxisFault f){

            org.apache.axiom.om.OMElement faultElt = f.getDetail();
            if (faultElt!=null){
                if (faultExceptionNameMap.containsKey(faultElt.getQName())){
                    //make the fault by reflection
                    try{
                        java.lang.String exceptionClassName = (java.lang.String)faultExceptionClassNameMap.get(faultElt.getQName());
                        java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
                        java.lang.Exception ex=
                                (java.lang.Exception) exceptionClass.newInstance();
                        //message class
                        java.lang.String messageClassName = (java.lang.String)faultMessageMap.get(faultElt.getQName());
                        java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
                        java.lang.Object messageObject = fromOM(faultElt,messageClass,null);
                        java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
                                   new java.lang.Class[]{messageClass});
                        m.invoke(ex,new java.lang.Object[]{messageObject});
                        
                        if (ex instanceof vinaphone.org.csapi.www.wsdl.parlayx.wap_push.send.v1_0.service.PolicyException){
                          throw (vinaphone.org.csapi.www.wsdl.parlayx.wap_push.send.v1_0.service.PolicyException)ex;
                        }
                        
                        if (ex instanceof vinaphone.org.csapi.www.wsdl.parlayx.wap_push.send.v1_0.service.ServiceException){
                          throw (vinaphone.org.csapi.www.wsdl.parlayx.wap_push.send.v1_0.service.ServiceException)ex;
                        }
                        

                        throw new java.rmi.RemoteException(ex.getMessage(), ex);
                    }catch(java.lang.ClassCastException e){
                       // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    } catch (java.lang.ClassNotFoundException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    }catch (java.lang.NoSuchMethodException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    } catch (java.lang.reflect.InvocationTargetException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    }  catch (java.lang.IllegalAccessException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    }   catch (java.lang.InstantiationException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    }
                }else{
                    throw f;
                }
            }else{
                throw f;
            }
            } finally {
                _messageContext.getTransportOut().getSender().cleanup(_messageContext);
            }
        }
            
                /**
                * Auto generated method signature for Asynchronous Invocations
                * 
                * @see vinaphone.org.csapi.www.wsdl.parlayx.wap_push.send.v1_0.service.SendPushMessageService#startsendPushMessage
                    * @param sendPushMessage26
                
                */
                public  void startsendPushMessage(

                 vinaphone.org.csapi.www.schema.parlayx.wap_push.send.v1_0.local.SendPushMessageE sendPushMessage26,

                  final vinaphone.org.csapi.www.wsdl.parlayx.wap_push.send.v1_0.service.SendPushMessageServiceCallbackHandler callback)

                throws java.rmi.RemoteException{

              org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[1].getName());
             _operationClient.getOptions().setAction("\"\"");
             _operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

              
              
                  addPropertyToOperationClient(_operationClient,org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,"&");
              


              // create SOAP envelope with that payload
              org.apache.axiom.soap.SOAPEnvelope env=null;
              final org.apache.axis2.context.MessageContext _messageContext = new org.apache.axis2.context.MessageContext();

                    
                                    //Style is Doc.
                                    
                                                    
                                                    env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()),
                                                    sendPushMessage26,
                                                    optimizeContent(new javax.xml.namespace.QName("http://www.csapi.org/wsdl/parlayx/wap_push/send/v1_0/interface",
                                                    "sendPushMessage")));
                                                
        // adding SOAP soap_headers
         _serviceClient.addHeadersToEnvelope(env);
        // create message context with that soap envelope
        _messageContext.setEnvelope(env);

        // add the message context to the operation client
        _operationClient.addMessageContext(_messageContext);


                    
                        _operationClient.setCallback(new org.apache.axis2.client.async.AxisCallback() {
                            public void onMessage(org.apache.axis2.context.MessageContext resultContext) {
                            try {
                                org.apache.axiom.soap.SOAPEnvelope resultEnv = resultContext.getEnvelope();
                                
                                        java.lang.Object object = fromOM(resultEnv.getBody().getFirstElement(),
                                                                         vinaphone.org.csapi.www.schema.parlayx.wap_push.send.v1_0.local.SendPushMessageResponseE.class,
                                                                         getEnvelopeNamespaces(resultEnv));
                                        callback.receiveResultsendPushMessage(
                                        (vinaphone.org.csapi.www.schema.parlayx.wap_push.send.v1_0.local.SendPushMessageResponseE)object);
                                        
                            } catch (org.apache.axis2.AxisFault e) {
                                callback.receiveErrorsendPushMessage(e);
                            }
                            }

                            public void onError(java.lang.Exception error) {
								if (error instanceof org.apache.axis2.AxisFault) {
									org.apache.axis2.AxisFault f = (org.apache.axis2.AxisFault) error;
									org.apache.axiom.om.OMElement faultElt = f.getDetail();
									if (faultElt!=null){
										if (faultExceptionNameMap.containsKey(faultElt.getQName())){
											//make the fault by reflection
											try{
													java.lang.String exceptionClassName = (java.lang.String)faultExceptionClassNameMap.get(faultElt.getQName());
													java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
													java.lang.Exception ex=
														(java.lang.Exception) exceptionClass.newInstance();
													//message class
													java.lang.String messageClassName = (java.lang.String)faultMessageMap.get(faultElt.getQName());
														java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
													java.lang.Object messageObject = fromOM(faultElt,messageClass,null);
													java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
															new java.lang.Class[]{messageClass});
													m.invoke(ex,new java.lang.Object[]{messageObject});
													
													if (ex instanceof vinaphone.org.csapi.www.wsdl.parlayx.wap_push.send.v1_0.service.PolicyException){
														callback.receiveErrorsendPushMessage((vinaphone.org.csapi.www.wsdl.parlayx.wap_push.send.v1_0.service.PolicyException)ex);
											            return;
										            }
										            
													if (ex instanceof vinaphone.org.csapi.www.wsdl.parlayx.wap_push.send.v1_0.service.ServiceException){
														callback.receiveErrorsendPushMessage((vinaphone.org.csapi.www.wsdl.parlayx.wap_push.send.v1_0.service.ServiceException)ex);
											            return;
										            }
										            
					
										            callback.receiveErrorsendPushMessage(new java.rmi.RemoteException(ex.getMessage(), ex));
                                            } catch(java.lang.ClassCastException e){
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorsendPushMessage(f);
                                            } catch (java.lang.ClassNotFoundException e) {
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorsendPushMessage(f);
                                            } catch (java.lang.NoSuchMethodException e) {
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorsendPushMessage(f);
                                            } catch (java.lang.reflect.InvocationTargetException e) {
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorsendPushMessage(f);
                                            } catch (java.lang.IllegalAccessException e) {
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorsendPushMessage(f);
                                            } catch (java.lang.InstantiationException e) {
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorsendPushMessage(f);
                                            } catch (org.apache.axis2.AxisFault e) {
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorsendPushMessage(f);
                                            }
									    } else {
										    callback.receiveErrorsendPushMessage(f);
									    }
									} else {
									    callback.receiveErrorsendPushMessage(f);
									}
								} else {
								    callback.receiveErrorsendPushMessage(error);
								}
                            }

                            public void onFault(org.apache.axis2.context.MessageContext faultContext) {
                                org.apache.axis2.AxisFault fault = org.apache.axis2.util.Utils.getInboundFaultFromMessageContext(faultContext);
                                onError(fault);
                            }

                            public void onComplete() {
                                try {
                                    _messageContext.getTransportOut().getSender().cleanup(_messageContext);
                                } catch (org.apache.axis2.AxisFault axisFault) {
                                    callback.receiveErrorsendPushMessage(axisFault);
                                }
                            }
                });
                        

          org.apache.axis2.util.CallbackReceiver _callbackReceiver = null;
        if ( _operations[1].getMessageReceiver()==null &&  _operationClient.getOptions().isUseSeparateListener()) {
           _callbackReceiver = new org.apache.axis2.util.CallbackReceiver();
          _operations[1].setMessageReceiver(
                    _callbackReceiver);
        }

           //execute the operation client
           _operationClient.execute(false);

                    }
                


       /**
        *  A utility method that copies the namepaces from the SOAPEnvelope
        */
       private java.util.Map getEnvelopeNamespaces(org.apache.axiom.soap.SOAPEnvelope env){
        java.util.Map returnMap = new java.util.HashMap();
        java.util.Iterator namespaceIterator = env.getAllDeclaredNamespaces();
        while (namespaceIterator.hasNext()) {
            org.apache.axiom.om.OMNamespace ns = (org.apache.axiom.om.OMNamespace) namespaceIterator.next();
            returnMap.put(ns.getPrefix(),ns.getNamespaceURI());
        }
       return returnMap;
    }

    
    
    private javax.xml.namespace.QName[] opNameArray = null;
    private boolean optimizeContent(javax.xml.namespace.QName opName) {
        

        if (opNameArray == null) {
            return false;
        }
        for (int i = 0; i < opNameArray.length; i++) {
            if (opName.equals(opNameArray[i])) {
                return true;   
            }
        }
        return false;
    }
     //http://localhost:9080/SendPushMessageService/services/SendPushMessage
            private  org.apache.axiom.om.OMElement  toOM(vinaphone.org.csapi.www.schema.parlayx.wap_push.send.v1_0.local.GetPushMessageDeliveryStatusE param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(vinaphone.org.csapi.www.schema.parlayx.wap_push.send.v1_0.local.GetPushMessageDeliveryStatusE.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(vinaphone.org.csapi.www.schema.parlayx.wap_push.send.v1_0.local.GetPushMessageDeliveryStatusResponseE param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(vinaphone.org.csapi.www.schema.parlayx.wap_push.send.v1_0.local.GetPushMessageDeliveryStatusResponseE.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(vinaphone.org.csapi.www.schema.parlayx.common.v2_1.PolicyExceptionE param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(vinaphone.org.csapi.www.schema.parlayx.common.v2_1.PolicyExceptionE.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(vinaphone.org.csapi.www.schema.parlayx.common.v2_1.ServiceExceptionE param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(vinaphone.org.csapi.www.schema.parlayx.common.v2_1.ServiceExceptionE.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(vinaphone.org.csapi.www.schema.parlayx.wap_push.send.v1_0.local.SendPushMessageE param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(vinaphone.org.csapi.www.schema.parlayx.wap_push.send.v1_0.local.SendPushMessageE.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(vinaphone.org.csapi.www.schema.parlayx.wap_push.send.v1_0.local.SendPushMessageResponseE param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(vinaphone.org.csapi.www.schema.parlayx.wap_push.send.v1_0.local.SendPushMessageResponseE.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
                                    
                                        private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, vinaphone.org.csapi.www.schema.parlayx.wap_push.send.v1_0.local.SendPushMessageE param, boolean optimizeContent)
                                        throws org.apache.axis2.AxisFault{

                                             
                                                    try{

                                                            org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                                                            emptyEnvelope.getBody().addChild(param.getOMElement(vinaphone.org.csapi.www.schema.parlayx.wap_push.send.v1_0.local.SendPushMessageE.MY_QNAME,factory));
                                                            return emptyEnvelope;
                                                        } catch(org.apache.axis2.databinding.ADBException e){
                                                            throw org.apache.axis2.AxisFault.makeFault(e);
                                                        }
                                                

                                        }
                                
                             
                             /* methods to provide back word compatibility */

                             
                                    
                                        private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, vinaphone.org.csapi.www.schema.parlayx.wap_push.send.v1_0.local.GetPushMessageDeliveryStatusE param, boolean optimizeContent)
                                        throws org.apache.axis2.AxisFault{

                                             
                                                    try{

                                                            org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                                                            emptyEnvelope.getBody().addChild(param.getOMElement(vinaphone.org.csapi.www.schema.parlayx.wap_push.send.v1_0.local.GetPushMessageDeliveryStatusE.MY_QNAME,factory));
                                                            return emptyEnvelope;
                                                        } catch(org.apache.axis2.databinding.ADBException e){
                                                            throw org.apache.axis2.AxisFault.makeFault(e);
                                                        }
                                                

                                        }
                                
                             
                             /* methods to provide back word compatibility */

                             


        /**
        *  get the default envelope
        */
        private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory){
        return factory.getDefaultEnvelope();
        }


        private  java.lang.Object fromOM(
        org.apache.axiom.om.OMElement param,
        java.lang.Class type,
        java.util.Map extraNamespaces) throws org.apache.axis2.AxisFault{

        try {
        
                if (vinaphone.org.csapi.www.schema.parlayx.wap_push.send.v1_0.local.GetPushMessageDeliveryStatusE.class.equals(type)){
                
                           return vinaphone.org.csapi.www.schema.parlayx.wap_push.send.v1_0.local.GetPushMessageDeliveryStatusE.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (vinaphone.org.csapi.www.schema.parlayx.wap_push.send.v1_0.local.GetPushMessageDeliveryStatusResponseE.class.equals(type)){
                
                           return vinaphone.org.csapi.www.schema.parlayx.wap_push.send.v1_0.local.GetPushMessageDeliveryStatusResponseE.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (vinaphone.org.csapi.www.schema.parlayx.common.v2_1.PolicyExceptionE.class.equals(type)){
                
                           return vinaphone.org.csapi.www.schema.parlayx.common.v2_1.PolicyExceptionE.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (vinaphone.org.csapi.www.schema.parlayx.common.v2_1.ServiceExceptionE.class.equals(type)){
                
                           return vinaphone.org.csapi.www.schema.parlayx.common.v2_1.ServiceExceptionE.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (vinaphone.org.csapi.www.schema.parlayx.wap_push.send.v1_0.local.SendPushMessageE.class.equals(type)){
                
                           return vinaphone.org.csapi.www.schema.parlayx.wap_push.send.v1_0.local.SendPushMessageE.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (vinaphone.org.csapi.www.schema.parlayx.wap_push.send.v1_0.local.SendPushMessageResponseE.class.equals(type)){
                
                           return vinaphone.org.csapi.www.schema.parlayx.wap_push.send.v1_0.local.SendPushMessageResponseE.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (vinaphone.org.csapi.www.schema.parlayx.common.v2_1.PolicyExceptionE.class.equals(type)){
                
                           return vinaphone.org.csapi.www.schema.parlayx.common.v2_1.PolicyExceptionE.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (vinaphone.org.csapi.www.schema.parlayx.common.v2_1.ServiceExceptionE.class.equals(type)){
                
                           return vinaphone.org.csapi.www.schema.parlayx.common.v2_1.ServiceExceptionE.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
        } catch (java.lang.Exception e) {
        throw org.apache.axis2.AxisFault.makeFault(e);
        }
           return null;
        }



    
   }
   