/**
 * MT_Charging_webserviceStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.4  Built on : Apr 26, 2008 (06:24:30 EDT)
 */
package vnm_old;

/*
 *  MT_Charging_webserviceStub java implementation
 */

public class MT_Charging_webserviceStub extends org.apache.axis2.client.Stub
{
	protected org.apache.axis2.description.AxisOperation[]	_operations;

	// hashmaps to keep the fault mapping
	private java.util.HashMap								faultExceptionNameMap		= new java.util.HashMap();
	private java.util.HashMap								faultExceptionClassNameMap	= new java.util.HashMap();
	private java.util.HashMap								faultMessageMap				= new java.util.HashMap();

	private static int										counter						= 0;

	private static synchronized String getUniqueSuffix()
	{
		// reset the counter if it is greater than 99999
		if (counter > 99999)
		{
			counter = 0;
		}
		counter = counter + 1;
		return Long.toString(System.currentTimeMillis()) + "_" + counter;
	}

	private void populateAxisService() throws org.apache.axis2.AxisFault
	{

		// creating the Service with a unique name
		_service = new org.apache.axis2.description.AxisService("MT_Charging_webservice" + getUniqueSuffix());
		addAnonymousOperations();

		// creating the operations
		org.apache.axis2.description.AxisOperation __operation;

		_operations = new org.apache.axis2.description.AxisOperation[3];

		__operation = new org.apache.axis2.description.OutInAxisOperation();

		__operation.setName(new javax.xml.namespace.QName("http://service.crm.com", "subscription"));
		_service.addOperation(__operation);

		_operations[0] = __operation;

		__operation = new org.apache.axis2.description.OutInAxisOperation();

		__operation.setName(new javax.xml.namespace.QName("http://service.crm.com", "getStatus"));
		_service.addOperation(__operation);

		_operations[1] = __operation;

		__operation = new org.apache.axis2.description.OutInAxisOperation();

		__operation.setName(new javax.xml.namespace.QName("http://service.crm.com", "delivery"));
		_service.addOperation(__operation);

		_operations[2] = __operation;

	}

	// populates the faults
	private void populateFaults()
	{

	}

	/**
	 * Constructor that takes in a configContext
	 */

	public MT_Charging_webserviceStub(org.apache.axis2.context.ConfigurationContext configurationContext,
			java.lang.String targetEndpoint)
			throws org.apache.axis2.AxisFault
	{
		this(configurationContext, targetEndpoint, false);
	}

	/**
	 * Constructor that takes in a configContext and useseperate listner
	 */
	public MT_Charging_webserviceStub(org.apache.axis2.context.ConfigurationContext configurationContext,
			java.lang.String targetEndpoint, boolean useSeparateListener)
			throws org.apache.axis2.AxisFault
	{
		// To populate AxisService
		populateAxisService();
		populateFaults();

		_serviceClient = new org.apache.axis2.client.ServiceClient(configurationContext, _service);

		configurationContext = _serviceClient.getServiceContext().getConfigurationContext();

		_serviceClient.getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(
				targetEndpoint));
		_serviceClient.getOptions().setUseSeparateListener(useSeparateListener);

	}

	/**
	 * Default Constructor
	 */
	public MT_Charging_webserviceStub(org.apache.axis2.context.ConfigurationContext configurationContext) throws org.apache.axis2.AxisFault
	{

		this(configurationContext, "http://203.128.246.91:8088/charging-gw/services/MT_Charging_webservice");

	}

	/**
	 * Default Constructor
	 */
	public MT_Charging_webserviceStub() throws org.apache.axis2.AxisFault
	{

		this("http://203.128.246.91:8088/charging-gw/services/MT_Charging_webservice");

	}

	/**
	 * Constructor taking the target endpoint
	 */
	public MT_Charging_webserviceStub(java.lang.String targetEndpoint) throws org.apache.axis2.AxisFault
	{
		this(null, targetEndpoint);
	}

	/**
	 * Auto generated method signature
	 * 
	 * @see vnm.MT_Charging_webservice#subscription
	 * @param subscription0
	 */

	public vnm_old.MT_Charging_webserviceStub.SubscriptionResponse subscription(

	vnm_old.MT_Charging_webserviceStub.Subscription subscription0)

	throws java.rmi.RemoteException

	{
		org.apache.axis2.context.MessageContext _messageContext = null;
		try
		{
			org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[0].getName());
			_operationClient.getOptions().setAction("\"\"");
			_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

			addPropertyToOperationClient(_operationClient, org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR, "&");

			// create a message context
			_messageContext = new org.apache.axis2.context.MessageContext();

			// create SOAP envelope with that payload
			org.apache.axiom.soap.SOAPEnvelope env = null;

			env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()),
													subscription0,
													optimizeContent(new javax.xml.namespace.QName("http://service.crm.com",
															"subscription")));

			// adding SOAP soap_headers
			_serviceClient.addHeadersToEnvelope(env);
			// set the message context with that soap envelope
			_messageContext.setEnvelope(env);

			// add the message contxt to the operation client
			_operationClient.addMessageContext(_messageContext);

			// execute the operation client
			_operationClient.execute(true);

			org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient.getMessageContext(
											org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
			org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();

			java.lang.Object object = fromOM(
												_returnEnv.getBody().getFirstElement(),
												vnm_old.MT_Charging_webserviceStub.SubscriptionResponse.class,
												getEnvelopeNamespaces(_returnEnv));

			return (vnm_old.MT_Charging_webserviceStub.SubscriptionResponse) object;

		}
		catch (org.apache.axis2.AxisFault f)
		{

			org.apache.axiom.om.OMElement faultElt = f.getDetail();
			if (faultElt != null)
			{
				if (faultExceptionNameMap.containsKey(faultElt.getQName()))
				{
					// make the fault by reflection
					try
					{
						java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap.get(faultElt.getQName());
						java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
						java.lang.Exception ex =
								(java.lang.Exception) exceptionClass.newInstance();
						// message class
						java.lang.String messageClassName = (java.lang.String) faultMessageMap.get(faultElt.getQName());
						java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
						java.lang.Object messageObject = fromOM(faultElt, messageClass, null);
						java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
									new java.lang.Class[] { messageClass });
						m.invoke(ex, new java.lang.Object[] { messageObject });

						throw new java.rmi.RemoteException(ex.getMessage(), ex);
					}
					catch (java.lang.ClassCastException e)
					{
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					}
					catch (java.lang.ClassNotFoundException e)
					{
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					}
					catch (java.lang.NoSuchMethodException e)
					{
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					}
					catch (java.lang.reflect.InvocationTargetException e)
					{
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					}
					catch (java.lang.IllegalAccessException e)
					{
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					}
					catch (java.lang.InstantiationException e)
					{
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					}
				}
				else
				{
					throw f;
				}
			}
			else
			{
				throw f;
			}
		}
		finally
		{
			_messageContext.getTransportOut().getSender().cleanup(_messageContext);
		}
	}

	/**
	 * Auto generated method signature for Asynchronous Invocations
	 * 
	 * @see vnm.MT_Charging_webservice#startsubscription
	 * @param subscription0
	 */
	public void startsubscription(

	vnm_old.MT_Charging_webserviceStub.Subscription subscription0,

	final vnm_old.MT_Charging_webserviceCallbackHandler callback)

	throws java.rmi.RemoteException
	{

		org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[0].getName());
		_operationClient.getOptions().setAction("\"\"");
		_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

		addPropertyToOperationClient(_operationClient, org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR, "&");

		// create SOAP envelope with that payload
		org.apache.axiom.soap.SOAPEnvelope env = null;
		final org.apache.axis2.context.MessageContext _messageContext = new org.apache.axis2.context.MessageContext();

		// Style is Doc.

		env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()),
													subscription0,
													optimizeContent(new javax.xml.namespace.QName("http://service.crm.com",
															"subscription")));

		// adding SOAP soap_headers
		_serviceClient.addHeadersToEnvelope(env);
		// create message context with that soap envelope
		_messageContext.setEnvelope(env);

		// add the message context to the operation client
		_operationClient.addMessageContext(_messageContext);

		_operationClient.setCallback(new org.apache.axis2.client.async.AxisCallback()
		{
			public void onMessage(org.apache.axis2.context.MessageContext resultContext)
			{
				try
				{
					org.apache.axiom.soap.SOAPEnvelope resultEnv = resultContext.getEnvelope();

					java.lang.Object object = fromOM(resultEnv.getBody().getFirstElement(),
																			vnm_old.MT_Charging_webserviceStub.SubscriptionResponse.class,
																			getEnvelopeNamespaces(resultEnv));
					callback.receiveResultsubscription(
										(vnm_old.MT_Charging_webserviceStub.SubscriptionResponse) object);

				}
				catch (org.apache.axis2.AxisFault e)
				{
					callback.receiveErrorsubscription(e);
				}
			}

			public void onError(java.lang.Exception error)
			{
				if (error instanceof org.apache.axis2.AxisFault)
				{
					org.apache.axis2.AxisFault f = (org.apache.axis2.AxisFault) error;
					org.apache.axiom.om.OMElement faultElt = f.getDetail();
					if (faultElt != null)
					{
						if (faultExceptionNameMap.containsKey(faultElt.getQName()))
						{
							// make the fault by reflection
							try
							{
								java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap.get(faultElt.getQName());
								java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
								java.lang.Exception ex =
														(java.lang.Exception) exceptionClass.newInstance();
								// message class
								java.lang.String messageClassName = (java.lang.String) faultMessageMap.get(faultElt.getQName());
								java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
								java.lang.Object messageObject = fromOM(faultElt, messageClass, null);
								java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
															new java.lang.Class[] { messageClass });
								m.invoke(ex, new java.lang.Object[] { messageObject });

								callback.receiveErrorsubscription(new java.rmi.RemoteException(ex.getMessage(), ex));
							}
							catch (java.lang.ClassCastException e)
							{
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorsubscription(f);
							}
							catch (java.lang.ClassNotFoundException e)
							{
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorsubscription(f);
							}
							catch (java.lang.NoSuchMethodException e)
							{
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorsubscription(f);
							}
							catch (java.lang.reflect.InvocationTargetException e)
							{
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorsubscription(f);
							}
							catch (java.lang.IllegalAccessException e)
							{
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorsubscription(f);
							}
							catch (java.lang.InstantiationException e)
							{
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorsubscription(f);
							}
							catch (org.apache.axis2.AxisFault e)
							{
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorsubscription(f);
							}
						}
						else
						{
							callback.receiveErrorsubscription(f);
						}
					}
					else
					{
						callback.receiveErrorsubscription(f);
					}
				}
				else
				{
					callback.receiveErrorsubscription(error);
				}
			}

			public void onFault(org.apache.axis2.context.MessageContext faultContext)
			{
				org.apache.axis2.AxisFault fault = org.apache.axis2.util.Utils.getInboundFaultFromMessageContext(faultContext);
				onError(fault);
			}

			public void onComplete()
			{
				try
				{
					_messageContext.getTransportOut().getSender().cleanup(_messageContext);
				}
				catch (org.apache.axis2.AxisFault axisFault)
				{
					callback.receiveErrorsubscription(axisFault);
				}
			}
		});

		org.apache.axis2.util.CallbackReceiver _callbackReceiver = null;
		if (_operations[0].getMessageReceiver() == null && _operationClient.getOptions().isUseSeparateListener())
		{
			_callbackReceiver = new org.apache.axis2.util.CallbackReceiver();
			_operations[0].setMessageReceiver(
					_callbackReceiver);
		}

		// execute the operation client
		_operationClient.execute(false);

	}

	/**
	 * Auto generated method signature
	 * 
	 * @see vnm.MT_Charging_webservice#getStatus
	 * @param getStatus2
	 */

	public vnm_old.MT_Charging_webserviceStub.GetStatusResponse getStatus(

	vnm_old.MT_Charging_webserviceStub.GetStatus getStatus2)

	throws java.rmi.RemoteException

	{
		org.apache.axis2.context.MessageContext _messageContext = null;
		try
		{
			org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[1].getName());
			_operationClient.getOptions().setAction("\"\"");
			_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

			addPropertyToOperationClient(_operationClient, org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR, "&");

			// create a message context
			_messageContext = new org.apache.axis2.context.MessageContext();

			// create SOAP envelope with that payload
			org.apache.axiom.soap.SOAPEnvelope env = null;

			env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()),
													getStatus2,
													optimizeContent(new javax.xml.namespace.QName("http://service.crm.com",
															"getStatus")));

			// adding SOAP soap_headers
			_serviceClient.addHeadersToEnvelope(env);
			// set the message context with that soap envelope
			_messageContext.setEnvelope(env);

			// add the message contxt to the operation client
			_operationClient.addMessageContext(_messageContext);

			// execute the operation client
			_operationClient.execute(true);

			org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient.getMessageContext(
											org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
			org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();

			java.lang.Object object = fromOM(
												_returnEnv.getBody().getFirstElement(),
												vnm_old.MT_Charging_webserviceStub.GetStatusResponse.class,
												getEnvelopeNamespaces(_returnEnv));

			return (vnm_old.MT_Charging_webserviceStub.GetStatusResponse) object;

		}
		catch (org.apache.axis2.AxisFault f)
		{

			org.apache.axiom.om.OMElement faultElt = f.getDetail();
			if (faultElt != null)
			{
				if (faultExceptionNameMap.containsKey(faultElt.getQName()))
				{
					// make the fault by reflection
					try
					{
						java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap.get(faultElt.getQName());
						java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
						java.lang.Exception ex =
								(java.lang.Exception) exceptionClass.newInstance();
						// message class
						java.lang.String messageClassName = (java.lang.String) faultMessageMap.get(faultElt.getQName());
						java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
						java.lang.Object messageObject = fromOM(faultElt, messageClass, null);
						java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
									new java.lang.Class[] { messageClass });
						m.invoke(ex, new java.lang.Object[] { messageObject });

						throw new java.rmi.RemoteException(ex.getMessage(), ex);
					}
					catch (java.lang.ClassCastException e)
					{
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					}
					catch (java.lang.ClassNotFoundException e)
					{
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					}
					catch (java.lang.NoSuchMethodException e)
					{
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					}
					catch (java.lang.reflect.InvocationTargetException e)
					{
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					}
					catch (java.lang.IllegalAccessException e)
					{
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					}
					catch (java.lang.InstantiationException e)
					{
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					}
				}
				else
				{
					throw f;
				}
			}
			else
			{
				throw f;
			}
		}
		finally
		{
			_messageContext.getTransportOut().getSender().cleanup(_messageContext);
		}
	}

	/**
	 * Auto generated method signature for Asynchronous Invocations
	 * 
	 * @see vnm.MT_Charging_webservice#startgetStatus
	 * @param getStatus2
	 */
	public void startgetStatus(

	vnm_old.MT_Charging_webserviceStub.GetStatus getStatus2,

	final vnm_old.MT_Charging_webserviceCallbackHandler callback)

	throws java.rmi.RemoteException
	{

		org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[1].getName());
		_operationClient.getOptions().setAction("\"\"");
		_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

		addPropertyToOperationClient(_operationClient, org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR, "&");

		// create SOAP envelope with that payload
		org.apache.axiom.soap.SOAPEnvelope env = null;
		final org.apache.axis2.context.MessageContext _messageContext = new org.apache.axis2.context.MessageContext();

		// Style is Doc.

		env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()),
													getStatus2,
													optimizeContent(new javax.xml.namespace.QName("http://service.crm.com",
															"getStatus")));

		// adding SOAP soap_headers
		_serviceClient.addHeadersToEnvelope(env);
		// create message context with that soap envelope
		_messageContext.setEnvelope(env);

		// add the message context to the operation client
		_operationClient.addMessageContext(_messageContext);

		_operationClient.setCallback(new org.apache.axis2.client.async.AxisCallback()
		{
			public void onMessage(org.apache.axis2.context.MessageContext resultContext)
			{
				try
				{
					org.apache.axiom.soap.SOAPEnvelope resultEnv = resultContext.getEnvelope();

					java.lang.Object object = fromOM(resultEnv.getBody().getFirstElement(),
																			vnm_old.MT_Charging_webserviceStub.GetStatusResponse.class,
																			getEnvelopeNamespaces(resultEnv));
					callback.receiveResultgetStatus(
										(vnm_old.MT_Charging_webserviceStub.GetStatusResponse) object);

				}
				catch (org.apache.axis2.AxisFault e)
				{
					callback.receiveErrorgetStatus(e);
				}
			}

			public void onError(java.lang.Exception error)
			{
				if (error instanceof org.apache.axis2.AxisFault)
				{
					org.apache.axis2.AxisFault f = (org.apache.axis2.AxisFault) error;
					org.apache.axiom.om.OMElement faultElt = f.getDetail();
					if (faultElt != null)
					{
						if (faultExceptionNameMap.containsKey(faultElt.getQName()))
						{
							// make the fault by reflection
							try
							{
								java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap.get(faultElt.getQName());
								java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
								java.lang.Exception ex =
														(java.lang.Exception) exceptionClass.newInstance();
								// message class
								java.lang.String messageClassName = (java.lang.String) faultMessageMap.get(faultElt.getQName());
								java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
								java.lang.Object messageObject = fromOM(faultElt, messageClass, null);
								java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
															new java.lang.Class[] { messageClass });
								m.invoke(ex, new java.lang.Object[] { messageObject });

								callback.receiveErrorgetStatus(new java.rmi.RemoteException(ex.getMessage(), ex));
							}
							catch (java.lang.ClassCastException e)
							{
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetStatus(f);
							}
							catch (java.lang.ClassNotFoundException e)
							{
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetStatus(f);
							}
							catch (java.lang.NoSuchMethodException e)
							{
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetStatus(f);
							}
							catch (java.lang.reflect.InvocationTargetException e)
							{
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetStatus(f);
							}
							catch (java.lang.IllegalAccessException e)
							{
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetStatus(f);
							}
							catch (java.lang.InstantiationException e)
							{
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetStatus(f);
							}
							catch (org.apache.axis2.AxisFault e)
							{
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetStatus(f);
							}
						}
						else
						{
							callback.receiveErrorgetStatus(f);
						}
					}
					else
					{
						callback.receiveErrorgetStatus(f);
					}
				}
				else
				{
					callback.receiveErrorgetStatus(error);
				}
			}

			public void onFault(org.apache.axis2.context.MessageContext faultContext)
			{
				org.apache.axis2.AxisFault fault = org.apache.axis2.util.Utils.getInboundFaultFromMessageContext(faultContext);
				onError(fault);
			}

			public void onComplete()
			{
				try
				{
					_messageContext.getTransportOut().getSender().cleanup(_messageContext);
				}
				catch (org.apache.axis2.AxisFault axisFault)
				{
					callback.receiveErrorgetStatus(axisFault);
				}
			}
		});

		org.apache.axis2.util.CallbackReceiver _callbackReceiver = null;
		if (_operations[1].getMessageReceiver() == null && _operationClient.getOptions().isUseSeparateListener())
		{
			_callbackReceiver = new org.apache.axis2.util.CallbackReceiver();
			_operations[1].setMessageReceiver(
					_callbackReceiver);
		}

		// execute the operation client
		_operationClient.execute(false);

	}

	/**
	 * Auto generated method signature
	 * 
	 * @see vnm.MT_Charging_webservice#delivery
	 * @param delivery4
	 */

	public vnm_old.MT_Charging_webserviceStub.DeliveryResponseE delivery(

	vnm_old.MT_Charging_webserviceStub.Delivery delivery4)

	throws java.rmi.RemoteException

	{
		org.apache.axis2.context.MessageContext _messageContext = null;
		try
		{
			org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[2].getName());
			_operationClient.getOptions().setAction("\"\"");
			_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

			addPropertyToOperationClient(_operationClient, org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR, "&");

			// create a message context
			_messageContext = new org.apache.axis2.context.MessageContext();

			// create SOAP envelope with that payload
			org.apache.axiom.soap.SOAPEnvelope env = null;

			env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()),
													delivery4,
													optimizeContent(new javax.xml.namespace.QName("http://service.crm.com",
															"delivery")));

			// adding SOAP soap_headers
			_serviceClient.addHeadersToEnvelope(env);
			// set the message context with that soap envelope
			_messageContext.setEnvelope(env);

			// add the message contxt to the operation client
			_operationClient.addMessageContext(_messageContext);

			// execute the operation client
			_operationClient.execute(true);

			org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient.getMessageContext(
											org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
			org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();

			java.lang.Object object = fromOM(
												_returnEnv.getBody().getFirstElement(),
												vnm_old.MT_Charging_webserviceStub.DeliveryResponseE.class,
												getEnvelopeNamespaces(_returnEnv));

			return (vnm_old.MT_Charging_webserviceStub.DeliveryResponseE) object;

		}
		catch (org.apache.axis2.AxisFault f)
		{

			org.apache.axiom.om.OMElement faultElt = f.getDetail();
			if (faultElt != null)
			{
				if (faultExceptionNameMap.containsKey(faultElt.getQName()))
				{
					// make the fault by reflection
					try
					{
						java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap.get(faultElt.getQName());
						java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
						java.lang.Exception ex =
								(java.lang.Exception) exceptionClass.newInstance();
						// message class
						java.lang.String messageClassName = (java.lang.String) faultMessageMap.get(faultElt.getQName());
						java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
						java.lang.Object messageObject = fromOM(faultElt, messageClass, null);
						java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
									new java.lang.Class[] { messageClass });
						m.invoke(ex, new java.lang.Object[] { messageObject });

						throw new java.rmi.RemoteException(ex.getMessage(), ex);
					}
					catch (java.lang.ClassCastException e)
					{
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					}
					catch (java.lang.ClassNotFoundException e)
					{
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					}
					catch (java.lang.NoSuchMethodException e)
					{
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					}
					catch (java.lang.reflect.InvocationTargetException e)
					{
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					}
					catch (java.lang.IllegalAccessException e)
					{
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					}
					catch (java.lang.InstantiationException e)
					{
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					}
				}
				else
				{
					throw f;
				}
			}
			else
			{
				throw f;
			}
		}
		finally
		{
			_messageContext.getTransportOut().getSender().cleanup(_messageContext);
		}
	}

	/**
	 * Auto generated method signature for Asynchronous Invocations
	 * 
	 * @see vnm.MT_Charging_webservice#startdelivery
	 * @param delivery4
	 */
	public void startdelivery(

	vnm_old.MT_Charging_webserviceStub.Delivery delivery4,

	final vnm_old.MT_Charging_webserviceCallbackHandler callback)

	throws java.rmi.RemoteException
	{

		org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[2].getName());
		_operationClient.getOptions().setAction("\"\"");
		_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

		addPropertyToOperationClient(_operationClient, org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR, "&");

		// create SOAP envelope with that payload
		org.apache.axiom.soap.SOAPEnvelope env = null;
		final org.apache.axis2.context.MessageContext _messageContext = new org.apache.axis2.context.MessageContext();

		// Style is Doc.

		env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()),
													delivery4,
													optimizeContent(new javax.xml.namespace.QName("http://service.crm.com",
															"delivery")));

		// adding SOAP soap_headers
		_serviceClient.addHeadersToEnvelope(env);
		// create message context with that soap envelope
		_messageContext.setEnvelope(env);

		// add the message context to the operation client
		_operationClient.addMessageContext(_messageContext);

		_operationClient.setCallback(new org.apache.axis2.client.async.AxisCallback()
		{
			public void onMessage(org.apache.axis2.context.MessageContext resultContext)
			{
				try
				{
					org.apache.axiom.soap.SOAPEnvelope resultEnv = resultContext.getEnvelope();

					java.lang.Object object = fromOM(resultEnv.getBody().getFirstElement(),
																			vnm_old.MT_Charging_webserviceStub.DeliveryResponseE.class,
																			getEnvelopeNamespaces(resultEnv));
					callback.receiveResultdelivery(
										(vnm_old.MT_Charging_webserviceStub.DeliveryResponseE) object);

				}
				catch (org.apache.axis2.AxisFault e)
				{
					callback.receiveErrordelivery(e);
				}
			}

			public void onError(java.lang.Exception error)
			{
				if (error instanceof org.apache.axis2.AxisFault)
				{
					org.apache.axis2.AxisFault f = (org.apache.axis2.AxisFault) error;
					org.apache.axiom.om.OMElement faultElt = f.getDetail();
					if (faultElt != null)
					{
						if (faultExceptionNameMap.containsKey(faultElt.getQName()))
						{
							// make the fault by reflection
							try
							{
								java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap.get(faultElt.getQName());
								java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
								java.lang.Exception ex =
														(java.lang.Exception) exceptionClass.newInstance();
								// message class
								java.lang.String messageClassName = (java.lang.String) faultMessageMap.get(faultElt.getQName());
								java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
								java.lang.Object messageObject = fromOM(faultElt, messageClass, null);
								java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
															new java.lang.Class[] { messageClass });
								m.invoke(ex, new java.lang.Object[] { messageObject });

								callback.receiveErrordelivery(new java.rmi.RemoteException(ex.getMessage(), ex));
							}
							catch (java.lang.ClassCastException e)
							{
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrordelivery(f);
							}
							catch (java.lang.ClassNotFoundException e)
							{
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrordelivery(f);
							}
							catch (java.lang.NoSuchMethodException e)
							{
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrordelivery(f);
							}
							catch (java.lang.reflect.InvocationTargetException e)
							{
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrordelivery(f);
							}
							catch (java.lang.IllegalAccessException e)
							{
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrordelivery(f);
							}
							catch (java.lang.InstantiationException e)
							{
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrordelivery(f);
							}
							catch (org.apache.axis2.AxisFault e)
							{
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrordelivery(f);
							}
						}
						else
						{
							callback.receiveErrordelivery(f);
						}
					}
					else
					{
						callback.receiveErrordelivery(f);
					}
				}
				else
				{
					callback.receiveErrordelivery(error);
				}
			}

			public void onFault(org.apache.axis2.context.MessageContext faultContext)
			{
				org.apache.axis2.AxisFault fault = org.apache.axis2.util.Utils.getInboundFaultFromMessageContext(faultContext);
				onError(fault);
			}

			public void onComplete()
			{
				try
				{
					_messageContext.getTransportOut().getSender().cleanup(_messageContext);
				}
				catch (org.apache.axis2.AxisFault axisFault)
				{
					callback.receiveErrordelivery(axisFault);
				}
			}
		});

		org.apache.axis2.util.CallbackReceiver _callbackReceiver = null;
		if (_operations[2].getMessageReceiver() == null && _operationClient.getOptions().isUseSeparateListener())
		{
			_callbackReceiver = new org.apache.axis2.util.CallbackReceiver();
			_operations[2].setMessageReceiver(
					_callbackReceiver);
		}

		// execute the operation client
		_operationClient.execute(false);

	}

	/**
	 * A utility method that copies the namepaces from the SOAPEnvelope
	 */
	private java.util.Map getEnvelopeNamespaces(org.apache.axiom.soap.SOAPEnvelope env)
	{
		java.util.Map returnMap = new java.util.HashMap();
		java.util.Iterator namespaceIterator = env.getAllDeclaredNamespaces();
		while (namespaceIterator.hasNext())
		{
			org.apache.axiom.om.OMNamespace ns = (org.apache.axiom.om.OMNamespace) namespaceIterator.next();
			returnMap.put(ns.getPrefix(), ns.getNamespaceURI());
		}
		return returnMap;
	}

	private javax.xml.namespace.QName[]	opNameArray	= null;

	private boolean optimizeContent(javax.xml.namespace.QName opName)
	{

		if (opNameArray == null)
		{
			return false;
		}
		for (int i = 0; i < opNameArray.length; i++)
		{
			if (opName.equals(opNameArray[i]))
			{
				return true;
			}
		}
		return false;
	}

	// http://203.128.246.91:8088/charging-gw/services/MT_Charging_webservice
	public static class Subscription
			implements org.apache.axis2.databinding.ADBBean
	{

		public static final javax.xml.namespace.QName	MY_QNAME	= new javax.xml.namespace.QName(
																			"http://service.crm.com",
																			"subscription",
																			"ns2");

		private static java.lang.String generatePrefix(java.lang.String namespace)
		{
			if (namespace.equals("http://service.crm.com"))
			{
				return "ns2";
			}
			return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
		}

		/**
		 * field for In0
		 */

		protected ServiceRequest	localIn0;

		/**
		 * Auto generated getter method
		 * 
		 * @return ServiceRequest
		 */
		public ServiceRequest getIn0()
		{
			return localIn0;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            In0
		 */
		public void setIn0(ServiceRequest param)
		{

			this.localIn0 = param;

		}

		/**
		 * isReaderMTOMAware
		 * 
		 * @return true if the reader supports MTOM
		 */
		public static boolean isReaderMTOMAware(javax.xml.stream.XMLStreamReader reader)
		{
			boolean isReaderMTOMAware = false;

			try
			{
				isReaderMTOMAware = java.lang.Boolean.TRUE.equals(reader.getProperty(org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
			}
			catch (java.lang.IllegalArgumentException e)
			{
				isReaderMTOMAware = false;
			}
			return isReaderMTOMAware;
		}

		/**
		 * 
		 * @param parentQName
		 * @param factory
		 * @return org.apache.axiom.om.OMElement
		 */
		public org.apache.axiom.om.OMElement getOMElement(
				final javax.xml.namespace.QName parentQName,
				final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException
		{

			org.apache.axiom.om.OMDataSource dataSource =
						new org.apache.axis2.databinding.ADBDataSource(this, MY_QNAME)
						{

							public void serialize(org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
									throws javax.xml.stream.XMLStreamException
							{
								Subscription.this.serialize(MY_QNAME, factory, xmlWriter);
							}
						};
			return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(
					MY_QNAME, factory, dataSource);

		}

		public void serialize(final javax.xml.namespace.QName parentQName,
										final org.apache.axiom.om.OMFactory factory,
										org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
								throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException
		{
			serialize(parentQName, factory, xmlWriter, false);
		}

		public void serialize(final javax.xml.namespace.QName parentQName,
								final org.apache.axiom.om.OMFactory factory,
								org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter,
								boolean serializeType)
				throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException
		{

			java.lang.String prefix = null;
			java.lang.String namespace = null;

			prefix = parentQName.getPrefix();
			namespace = parentQName.getNamespaceURI();

			if ((namespace != null) && (namespace.trim().length() > 0))
			{
				java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
				if (writerPrefix != null)
				{
					xmlWriter.writeStartElement(namespace, parentQName.getLocalPart());
				}
				else
				{
					if (prefix == null)
					{
						prefix = generatePrefix(namespace);
					}

					xmlWriter.writeStartElement(prefix, parentQName.getLocalPart(), namespace);
					xmlWriter.writeNamespace(prefix, namespace);
					xmlWriter.setPrefix(prefix, namespace);
				}
			}
			else
			{
				xmlWriter.writeStartElement(parentQName.getLocalPart());
			}

			if (serializeType)
			{

				java.lang.String namespacePrefix = registerPrefix(xmlWriter, "http://service.crm.com");
				if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0))
				{
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type",
							namespacePrefix + ":subscription",
							xmlWriter);
				}
				else
				{
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type",
							"subscription",
							xmlWriter);
				}

			}

			if (localIn0 == null)
			{

				java.lang.String namespace2 = "http://service.crm.com";

				if (!namespace2.equals(""))
				{
					java.lang.String prefix2 = xmlWriter.getPrefix(namespace2);

					if (prefix2 == null)
					{
						prefix2 = generatePrefix(namespace2);

						xmlWriter.writeStartElement(prefix2, "in0", namespace2);
						xmlWriter.writeNamespace(prefix2, namespace2);
						xmlWriter.setPrefix(prefix2, namespace2);

					}
					else
					{
						xmlWriter.writeStartElement(namespace2, "in0");
					}

				}
				else
				{
					xmlWriter.writeStartElement("in0");
				}

				// write the nil attribute
				writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);
				xmlWriter.writeEndElement();
			}
			else
			{
				localIn0.serialize(new javax.xml.namespace.QName("http://service.crm.com", "in0"),
										factory, xmlWriter);
			}

			xmlWriter.writeEndElement();

		}

		/**
		 * Util method to write an attribute with the ns prefix
		 */
		private void writeAttribute(java.lang.String prefix, java.lang.String namespace, java.lang.String attName,
										java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException
		{
			if (xmlWriter.getPrefix(namespace) == null)
			{
				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);

			}

			xmlWriter.writeAttribute(namespace, attName, attValue);

		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeAttribute(java.lang.String namespace, java.lang.String attName,
										java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException
		{
			if (namespace.equals(""))
			{
				xmlWriter.writeAttribute(attName, attValue);
			}
			else
			{
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attValue);
			}
		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
												javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException
		{

			java.lang.String attributeNamespace = qname.getNamespaceURI();
			java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
			if (attributePrefix == null)
			{
				attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
			}
			java.lang.String attributeValue;
			if (attributePrefix.trim().length() > 0)
			{
				attributeValue = attributePrefix + ":" + qname.getLocalPart();
			}
			else
			{
				attributeValue = qname.getLocalPart();
			}

			if (namespace.equals(""))
			{
				xmlWriter.writeAttribute(attName, attributeValue);
			}
			else
			{
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attributeValue);
			}
		}

		/**
		 * method to handle Qnames
		 */

		private void writeQName(javax.xml.namespace.QName qname,
								javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException
		{
			java.lang.String namespaceURI = qname.getNamespaceURI();
			if (namespaceURI != null)
			{
				java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
				if (prefix == null)
				{
					prefix = generatePrefix(namespaceURI);
					xmlWriter.writeNamespace(prefix, namespaceURI);
					xmlWriter.setPrefix(prefix, namespaceURI);
				}

				if (prefix.trim().length() > 0)
				{
					xmlWriter.writeCharacters(prefix + ":" + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				}
				else
				{
					// i.e this is the default namespace
					xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				}

			}
			else
			{
				xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
			}
		}

		private void writeQNames(javax.xml.namespace.QName[] qnames,
									javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException
		{

			if (qnames != null)
			{
				// we have to store this data until last moment since it is not
				// possible to write any
				// namespace data after writing the charactor data
				java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
				java.lang.String namespaceURI = null;
				java.lang.String prefix = null;

				for (int i = 0; i < qnames.length; i++)
				{
					if (i > 0)
					{
						stringToWrite.append(" ");
					}
					namespaceURI = qnames[i].getNamespaceURI();
					if (namespaceURI != null)
					{
						prefix = xmlWriter.getPrefix(namespaceURI);
						if ((prefix == null) || (prefix.length() == 0))
						{
							prefix = generatePrefix(namespaceURI);
							xmlWriter.writeNamespace(prefix, namespaceURI);
							xmlWriter.setPrefix(prefix, namespaceURI);
						}

						if (prefix.trim().length() > 0)
						{
							stringToWrite.append(prefix).append(":")
									.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
						}
						else
						{
							stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
						}
					}
					else
					{
						stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
					}
				}
				xmlWriter.writeCharacters(stringToWrite.toString());
			}

		}

		/**
		 * Register a namespace prefix
		 */
		private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace)
				throws javax.xml.stream.XMLStreamException
		{
			java.lang.String prefix = xmlWriter.getPrefix(namespace);

			if (prefix == null)
			{
				prefix = generatePrefix(namespace);

				while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null)
				{
					prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
				}

				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);
			}

			return prefix;
		}

		/**
		 * databinding method to get an XML representation of this object
		 * 
		 */
		public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
					throws org.apache.axis2.databinding.ADBException
		{

			java.util.ArrayList elementList = new java.util.ArrayList();
			java.util.ArrayList attribList = new java.util.ArrayList();

			elementList.add(new javax.xml.namespace.QName("http://service.crm.com",
																		"in0"));

			elementList.add(localIn0 == null ? null :
									localIn0);

			return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(), attribList.toArray());

		}

		/**
		 * Factory class that keeps the parse method
		 */
		public static class Factory
		{

			/**
			 * static method to create the object Precondition: If this object
			 * is an element, the current or next start element starts this
			 * object and any intervening reader events are ignorable If this
			 * object is not an element, it is a complex type and the reader is
			 * at the event just after the outer start element Postcondition: If
			 * this object is an element, the reader is positioned at its end
			 * element If this object is a complex type, the reader is
			 * positioned at the end element of its outer element
			 */
			public static Subscription parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception
			{
				Subscription object =
						new Subscription();

				int event;
				java.lang.String nillableValue = null;
				java.lang.String prefix = "";
				java.lang.String namespaceuri = "";
				try
				{

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type") != null)
					{
						java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
								"type");
						if (fullTypeName != null)
						{
							java.lang.String nsPrefix = null;
							if (fullTypeName.indexOf(":") > -1)
							{
								nsPrefix = fullTypeName.substring(0, fullTypeName.indexOf(":"));
							}
							nsPrefix = nsPrefix == null ? "" : nsPrefix;

							java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":") + 1);

							if (!"subscription".equals(type))
							{
								// find namespace for the prefix
								java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
								return (Subscription) ExtensionMapper.getTypeObject(
										nsUri, type, reader);
							}

						}

					}

					// Note all attributes that were handled. Used to differ
					// normal attributes
					// from anyAttributes.
					java.util.Vector handledAttributes = new java.util.Vector();

					reader.next();

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("http://service.crm.com", "in0").equals(reader.getName()))
					{

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if ("true".equals(nillableValue) || "1".equals(nillableValue))
						{
							object.setIn0(null);
							reader.next();

							reader.next();

						}
						else
						{

							object.setIn0(ServiceRequest.Factory.parse(reader));

							reader.next();
						}
					} // End of if for expected property start element

					else
					{
						// A start element we are not expecting indicates an
						// invalid parameter was passed
						throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement())
						// A start element we are not expecting indicates a
						// trailing invalid property
						throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());

				}
				catch (javax.xml.stream.XMLStreamException e)
				{
					throw new java.lang.Exception(e);
				}

				return object;
			}

		}// end of factory class

	}

	public static class SubscriptionResponse
			implements org.apache.axis2.databinding.ADBBean
	{

		public static final javax.xml.namespace.QName	MY_QNAME	= new javax.xml.namespace.QName(
																			"http://service.crm.com",
																			"subscriptionResponse",
																			"ns2");

		private static java.lang.String generatePrefix(java.lang.String namespace)
		{
			if (namespace.equals("http://service.crm.com"))
			{
				return "ns2";
			}
			return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
		}

		/**
		 * field for Out
		 */

		protected ServiceResponse	localOut;

		/**
		 * Auto generated getter method
		 * 
		 * @return ServiceResponse
		 */
		public ServiceResponse getOut()
		{
			return localOut;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            Out
		 */
		public void setOut(ServiceResponse param)
		{

			this.localOut = param;

		}

		/**
		 * isReaderMTOMAware
		 * 
		 * @return true if the reader supports MTOM
		 */
		public static boolean isReaderMTOMAware(javax.xml.stream.XMLStreamReader reader)
		{
			boolean isReaderMTOMAware = false;

			try
			{
				isReaderMTOMAware = java.lang.Boolean.TRUE.equals(reader.getProperty(org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
			}
			catch (java.lang.IllegalArgumentException e)
			{
				isReaderMTOMAware = false;
			}
			return isReaderMTOMAware;
		}

		/**
		 * 
		 * @param parentQName
		 * @param factory
		 * @return org.apache.axiom.om.OMElement
		 */
		public org.apache.axiom.om.OMElement getOMElement(
				final javax.xml.namespace.QName parentQName,
				final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException
		{

			org.apache.axiom.om.OMDataSource dataSource =
						new org.apache.axis2.databinding.ADBDataSource(this, MY_QNAME)
						{

							public void serialize(org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
									throws javax.xml.stream.XMLStreamException
							{
								SubscriptionResponse.this.serialize(MY_QNAME, factory, xmlWriter);
							}
						};
			return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(
					MY_QNAME, factory, dataSource);

		}

		public void serialize(final javax.xml.namespace.QName parentQName,
										final org.apache.axiom.om.OMFactory factory,
										org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
								throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException
		{
			serialize(parentQName, factory, xmlWriter, false);
		}

		public void serialize(final javax.xml.namespace.QName parentQName,
								final org.apache.axiom.om.OMFactory factory,
								org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter,
								boolean serializeType)
				throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException
		{

			java.lang.String prefix = null;
			java.lang.String namespace = null;

			prefix = parentQName.getPrefix();
			namespace = parentQName.getNamespaceURI();

			if ((namespace != null) && (namespace.trim().length() > 0))
			{
				java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
				if (writerPrefix != null)
				{
					xmlWriter.writeStartElement(namespace, parentQName.getLocalPart());
				}
				else
				{
					if (prefix == null)
					{
						prefix = generatePrefix(namespace);
					}

					xmlWriter.writeStartElement(prefix, parentQName.getLocalPart(), namespace);
					xmlWriter.writeNamespace(prefix, namespace);
					xmlWriter.setPrefix(prefix, namespace);
				}
			}
			else
			{
				xmlWriter.writeStartElement(parentQName.getLocalPart());
			}

			if (serializeType)
			{

				java.lang.String namespacePrefix = registerPrefix(xmlWriter, "http://service.crm.com");
				if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0))
				{
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type",
							namespacePrefix + ":subscriptionResponse",
							xmlWriter);
				}
				else
				{
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type",
							"subscriptionResponse",
							xmlWriter);
				}

			}

			if (localOut == null)
			{

				java.lang.String namespace2 = "http://service.crm.com";

				if (!namespace2.equals(""))
				{
					java.lang.String prefix2 = xmlWriter.getPrefix(namespace2);

					if (prefix2 == null)
					{
						prefix2 = generatePrefix(namespace2);

						xmlWriter.writeStartElement(prefix2, "out", namespace2);
						xmlWriter.writeNamespace(prefix2, namespace2);
						xmlWriter.setPrefix(prefix2, namespace2);

					}
					else
					{
						xmlWriter.writeStartElement(namespace2, "out");
					}

				}
				else
				{
					xmlWriter.writeStartElement("out");
				}

				// write the nil attribute
				writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);
				xmlWriter.writeEndElement();
			}
			else
			{
				localOut.serialize(new javax.xml.namespace.QName("http://service.crm.com", "out"),
										factory, xmlWriter);
			}

			xmlWriter.writeEndElement();

		}

		/**
		 * Util method to write an attribute with the ns prefix
		 */
		private void writeAttribute(java.lang.String prefix, java.lang.String namespace, java.lang.String attName,
										java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException
		{
			if (xmlWriter.getPrefix(namespace) == null)
			{
				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);

			}

			xmlWriter.writeAttribute(namespace, attName, attValue);

		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeAttribute(java.lang.String namespace, java.lang.String attName,
										java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException
		{
			if (namespace.equals(""))
			{
				xmlWriter.writeAttribute(attName, attValue);
			}
			else
			{
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attValue);
			}
		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
												javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException
		{

			java.lang.String attributeNamespace = qname.getNamespaceURI();
			java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
			if (attributePrefix == null)
			{
				attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
			}
			java.lang.String attributeValue;
			if (attributePrefix.trim().length() > 0)
			{
				attributeValue = attributePrefix + ":" + qname.getLocalPart();
			}
			else
			{
				attributeValue = qname.getLocalPart();
			}

			if (namespace.equals(""))
			{
				xmlWriter.writeAttribute(attName, attributeValue);
			}
			else
			{
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attributeValue);
			}
		}

		/**
		 * method to handle Qnames
		 */

		private void writeQName(javax.xml.namespace.QName qname,
								javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException
		{
			java.lang.String namespaceURI = qname.getNamespaceURI();
			if (namespaceURI != null)
			{
				java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
				if (prefix == null)
				{
					prefix = generatePrefix(namespaceURI);
					xmlWriter.writeNamespace(prefix, namespaceURI);
					xmlWriter.setPrefix(prefix, namespaceURI);
				}

				if (prefix.trim().length() > 0)
				{
					xmlWriter.writeCharacters(prefix + ":" + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				}
				else
				{
					// i.e this is the default namespace
					xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				}

			}
			else
			{
				xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
			}
		}

		private void writeQNames(javax.xml.namespace.QName[] qnames,
									javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException
		{

			if (qnames != null)
			{
				// we have to store this data until last moment since it is not
				// possible to write any
				// namespace data after writing the charactor data
				java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
				java.lang.String namespaceURI = null;
				java.lang.String prefix = null;

				for (int i = 0; i < qnames.length; i++)
				{
					if (i > 0)
					{
						stringToWrite.append(" ");
					}
					namespaceURI = qnames[i].getNamespaceURI();
					if (namespaceURI != null)
					{
						prefix = xmlWriter.getPrefix(namespaceURI);
						if ((prefix == null) || (prefix.length() == 0))
						{
							prefix = generatePrefix(namespaceURI);
							xmlWriter.writeNamespace(prefix, namespaceURI);
							xmlWriter.setPrefix(prefix, namespaceURI);
						}

						if (prefix.trim().length() > 0)
						{
							stringToWrite.append(prefix).append(":")
									.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
						}
						else
						{
							stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
						}
					}
					else
					{
						stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
					}
				}
				xmlWriter.writeCharacters(stringToWrite.toString());
			}

		}

		/**
		 * Register a namespace prefix
		 */
		private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace)
				throws javax.xml.stream.XMLStreamException
		{
			java.lang.String prefix = xmlWriter.getPrefix(namespace);

			if (prefix == null)
			{
				prefix = generatePrefix(namespace);

				while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null)
				{
					prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
				}

				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);
			}

			return prefix;
		}

		/**
		 * databinding method to get an XML representation of this object
		 * 
		 */
		public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
					throws org.apache.axis2.databinding.ADBException
		{

			java.util.ArrayList elementList = new java.util.ArrayList();
			java.util.ArrayList attribList = new java.util.ArrayList();

			elementList.add(new javax.xml.namespace.QName("http://service.crm.com",
																		"out"));

			elementList.add(localOut == null ? null :
									localOut);

			return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(), attribList.toArray());

		}

		/**
		 * Factory class that keeps the parse method
		 */
		public static class Factory
		{

			/**
			 * static method to create the object Precondition: If this object
			 * is an element, the current or next start element starts this
			 * object and any intervening reader events are ignorable If this
			 * object is not an element, it is a complex type and the reader is
			 * at the event just after the outer start element Postcondition: If
			 * this object is an element, the reader is positioned at its end
			 * element If this object is a complex type, the reader is
			 * positioned at the end element of its outer element
			 */
			public static SubscriptionResponse parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception
			{
				SubscriptionResponse object =
						new SubscriptionResponse();

				int event;
				java.lang.String nillableValue = null;
				java.lang.String prefix = "";
				java.lang.String namespaceuri = "";
				try
				{

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type") != null)
					{
						java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
								"type");
						if (fullTypeName != null)
						{
							java.lang.String nsPrefix = null;
							if (fullTypeName.indexOf(":") > -1)
							{
								nsPrefix = fullTypeName.substring(0, fullTypeName.indexOf(":"));
							}
							nsPrefix = nsPrefix == null ? "" : nsPrefix;

							java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":") + 1);

							if (!"subscriptionResponse".equals(type))
							{
								// find namespace for the prefix
								java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
								return (SubscriptionResponse) ExtensionMapper.getTypeObject(
										nsUri, type, reader);
							}

						}

					}

					// Note all attributes that were handled. Used to differ
					// normal attributes
					// from anyAttributes.
					java.util.Vector handledAttributes = new java.util.Vector();

					reader.next();

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("http://service.crm.com", "out").equals(reader.getName()))
					{

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if ("true".equals(nillableValue) || "1".equals(nillableValue))
						{
							object.setOut(null);
							reader.next();

							reader.next();

						}
						else
						{

							object.setOut(ServiceResponse.Factory.parse(reader));

							reader.next();
						}
					} // End of if for expected property start element

					else
					{
						// A start element we are not expecting indicates an
						// invalid parameter was passed
						throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement())
						// A start element we are not expecting indicates a
						// trailing invalid property
						throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());

				}
				catch (javax.xml.stream.XMLStreamException e)
				{
					throw new java.lang.Exception(e);
				}

				return object;
			}

		}// end of factory class

	}

	public static class Delivery
			implements org.apache.axis2.databinding.ADBBean
	{

		public static final javax.xml.namespace.QName	MY_QNAME	= new javax.xml.namespace.QName(
																			"http://service.crm.com",
																			"delivery",
																			"ns2");

		private static java.lang.String generatePrefix(java.lang.String namespace)
		{
			if (namespace.equals("http://service.crm.com"))
			{
				return "ns2";
			}
			return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
		}

		/**
		 * field for In0
		 */

		protected DeliveryRequest	localIn0;

		/**
		 * Auto generated getter method
		 * 
		 * @return DeliveryRequest
		 */
		public DeliveryRequest getIn0()
		{
			return localIn0;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            In0
		 */
		public void setIn0(DeliveryRequest param)
		{

			this.localIn0 = param;

		}

		/**
		 * isReaderMTOMAware
		 * 
		 * @return true if the reader supports MTOM
		 */
		public static boolean isReaderMTOMAware(javax.xml.stream.XMLStreamReader reader)
		{
			boolean isReaderMTOMAware = false;

			try
			{
				isReaderMTOMAware = java.lang.Boolean.TRUE.equals(reader.getProperty(org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
			}
			catch (java.lang.IllegalArgumentException e)
			{
				isReaderMTOMAware = false;
			}
			return isReaderMTOMAware;
		}

		/**
		 * 
		 * @param parentQName
		 * @param factory
		 * @return org.apache.axiom.om.OMElement
		 */
		public org.apache.axiom.om.OMElement getOMElement(
				final javax.xml.namespace.QName parentQName,
				final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException
		{

			org.apache.axiom.om.OMDataSource dataSource =
						new org.apache.axis2.databinding.ADBDataSource(this, MY_QNAME)
						{

							public void serialize(org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
									throws javax.xml.stream.XMLStreamException
							{
								Delivery.this.serialize(MY_QNAME, factory, xmlWriter);
							}
						};
			return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(
					MY_QNAME, factory, dataSource);

		}

		public void serialize(final javax.xml.namespace.QName parentQName,
										final org.apache.axiom.om.OMFactory factory,
										org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
								throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException
		{
			serialize(parentQName, factory, xmlWriter, false);
		}

		public void serialize(final javax.xml.namespace.QName parentQName,
								final org.apache.axiom.om.OMFactory factory,
								org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter,
								boolean serializeType)
				throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException
		{

			java.lang.String prefix = null;
			java.lang.String namespace = null;

			prefix = parentQName.getPrefix();
			namespace = parentQName.getNamespaceURI();

			if ((namespace != null) && (namespace.trim().length() > 0))
			{
				java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
				if (writerPrefix != null)
				{
					xmlWriter.writeStartElement(namespace, parentQName.getLocalPart());
				}
				else
				{
					if (prefix == null)
					{
						prefix = generatePrefix(namespace);
					}

					xmlWriter.writeStartElement(prefix, parentQName.getLocalPart(), namespace);
					xmlWriter.writeNamespace(prefix, namespace);
					xmlWriter.setPrefix(prefix, namespace);
				}
			}
			else
			{
				xmlWriter.writeStartElement(parentQName.getLocalPart());
			}

			if (serializeType)
			{

				java.lang.String namespacePrefix = registerPrefix(xmlWriter, "http://service.crm.com");
				if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0))
				{
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type",
							namespacePrefix + ":delivery",
							xmlWriter);
				}
				else
				{
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type",
							"delivery",
							xmlWriter);
				}

			}

			if (localIn0 == null)
			{

				java.lang.String namespace2 = "http://service.crm.com";

				if (!namespace2.equals(""))
				{
					java.lang.String prefix2 = xmlWriter.getPrefix(namespace2);

					if (prefix2 == null)
					{
						prefix2 = generatePrefix(namespace2);

						xmlWriter.writeStartElement(prefix2, "in0", namespace2);
						xmlWriter.writeNamespace(prefix2, namespace2);
						xmlWriter.setPrefix(prefix2, namespace2);

					}
					else
					{
						xmlWriter.writeStartElement(namespace2, "in0");
					}

				}
				else
				{
					xmlWriter.writeStartElement("in0");
				}

				// write the nil attribute
				writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);
				xmlWriter.writeEndElement();
			}
			else
			{
				localIn0.serialize(new javax.xml.namespace.QName("http://service.crm.com", "in0"),
										factory, xmlWriter);
			}

			xmlWriter.writeEndElement();

		}

		/**
		 * Util method to write an attribute with the ns prefix
		 */
		private void writeAttribute(java.lang.String prefix, java.lang.String namespace, java.lang.String attName,
										java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException
		{
			if (xmlWriter.getPrefix(namespace) == null)
			{
				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);

			}

			xmlWriter.writeAttribute(namespace, attName, attValue);

		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeAttribute(java.lang.String namespace, java.lang.String attName,
										java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException
		{
			if (namespace.equals(""))
			{
				xmlWriter.writeAttribute(attName, attValue);
			}
			else
			{
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attValue);
			}
		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
												javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException
		{

			java.lang.String attributeNamespace = qname.getNamespaceURI();
			java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
			if (attributePrefix == null)
			{
				attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
			}
			java.lang.String attributeValue;
			if (attributePrefix.trim().length() > 0)
			{
				attributeValue = attributePrefix + ":" + qname.getLocalPart();
			}
			else
			{
				attributeValue = qname.getLocalPart();
			}

			if (namespace.equals(""))
			{
				xmlWriter.writeAttribute(attName, attributeValue);
			}
			else
			{
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attributeValue);
			}
		}

		/**
		 * method to handle Qnames
		 */

		private void writeQName(javax.xml.namespace.QName qname,
								javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException
		{
			java.lang.String namespaceURI = qname.getNamespaceURI();
			if (namespaceURI != null)
			{
				java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
				if (prefix == null)
				{
					prefix = generatePrefix(namespaceURI);
					xmlWriter.writeNamespace(prefix, namespaceURI);
					xmlWriter.setPrefix(prefix, namespaceURI);
				}

				if (prefix.trim().length() > 0)
				{
					xmlWriter.writeCharacters(prefix + ":" + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				}
				else
				{
					// i.e this is the default namespace
					xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				}

			}
			else
			{
				xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
			}
		}

		private void writeQNames(javax.xml.namespace.QName[] qnames,
									javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException
		{

			if (qnames != null)
			{
				// we have to store this data until last moment since it is not
				// possible to write any
				// namespace data after writing the charactor data
				java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
				java.lang.String namespaceURI = null;
				java.lang.String prefix = null;

				for (int i = 0; i < qnames.length; i++)
				{
					if (i > 0)
					{
						stringToWrite.append(" ");
					}
					namespaceURI = qnames[i].getNamespaceURI();
					if (namespaceURI != null)
					{
						prefix = xmlWriter.getPrefix(namespaceURI);
						if ((prefix == null) || (prefix.length() == 0))
						{
							prefix = generatePrefix(namespaceURI);
							xmlWriter.writeNamespace(prefix, namespaceURI);
							xmlWriter.setPrefix(prefix, namespaceURI);
						}

						if (prefix.trim().length() > 0)
						{
							stringToWrite.append(prefix).append(":")
									.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
						}
						else
						{
							stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
						}
					}
					else
					{
						stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
					}
				}
				xmlWriter.writeCharacters(stringToWrite.toString());
			}

		}

		/**
		 * Register a namespace prefix
		 */
		private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace)
				throws javax.xml.stream.XMLStreamException
		{
			java.lang.String prefix = xmlWriter.getPrefix(namespace);

			if (prefix == null)
			{
				prefix = generatePrefix(namespace);

				while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null)
				{
					prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
				}

				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);
			}

			return prefix;
		}

		/**
		 * databinding method to get an XML representation of this object
		 * 
		 */
		public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
					throws org.apache.axis2.databinding.ADBException
		{

			java.util.ArrayList elementList = new java.util.ArrayList();
			java.util.ArrayList attribList = new java.util.ArrayList();

			elementList.add(new javax.xml.namespace.QName("http://service.crm.com",
																		"in0"));

			elementList.add(localIn0 == null ? null :
									localIn0);

			return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(), attribList.toArray());

		}

		/**
		 * Factory class that keeps the parse method
		 */
		public static class Factory
		{

			/**
			 * static method to create the object Precondition: If this object
			 * is an element, the current or next start element starts this
			 * object and any intervening reader events are ignorable If this
			 * object is not an element, it is a complex type and the reader is
			 * at the event just after the outer start element Postcondition: If
			 * this object is an element, the reader is positioned at its end
			 * element If this object is a complex type, the reader is
			 * positioned at the end element of its outer element
			 */
			public static Delivery parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception
			{
				Delivery object =
						new Delivery();

				int event;
				java.lang.String nillableValue = null;
				java.lang.String prefix = "";
				java.lang.String namespaceuri = "";
				try
				{

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type") != null)
					{
						java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
								"type");
						if (fullTypeName != null)
						{
							java.lang.String nsPrefix = null;
							if (fullTypeName.indexOf(":") > -1)
							{
								nsPrefix = fullTypeName.substring(0, fullTypeName.indexOf(":"));
							}
							nsPrefix = nsPrefix == null ? "" : nsPrefix;

							java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":") + 1);

							if (!"delivery".equals(type))
							{
								// find namespace for the prefix
								java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
								return (Delivery) ExtensionMapper.getTypeObject(
										nsUri, type, reader);
							}

						}

					}

					// Note all attributes that were handled. Used to differ
					// normal attributes
					// from anyAttributes.
					java.util.Vector handledAttributes = new java.util.Vector();

					reader.next();

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("http://service.crm.com", "in0").equals(reader.getName()))
					{

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if ("true".equals(nillableValue) || "1".equals(nillableValue))
						{
							object.setIn0(null);
							reader.next();

							reader.next();

						}
						else
						{

							object.setIn0(DeliveryRequest.Factory.parse(reader));

							reader.next();
						}
					} // End of if for expected property start element

					else
					{
						// A start element we are not expecting indicates an
						// invalid parameter was passed
						throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement())
						// A start element we are not expecting indicates a
						// trailing invalid property
						throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());

				}
				catch (javax.xml.stream.XMLStreamException e)
				{
					throw new java.lang.Exception(e);
				}

				return object;
			}

		}// end of factory class

	}

	public static class ExtensionMapper
	{

		public static java.lang.Object getTypeObject(java.lang.String namespaceURI,
														java.lang.String typeName,
														javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception
		{

			if ("http://model.gateway.com".equals(namespaceURI) &&
					"ServiceRequest".equals(typeName))
			{

				return ServiceRequest.Factory.parse(reader);

			}

			if ("http://model.gateway.com".equals(namespaceURI) &&
					"ServiceResponse".equals(typeName))
			{

				return ServiceResponse.Factory.parse(reader);

			}

			if ("http://model.gateway.com".equals(namespaceURI) &&
					"DeliveryRequest".equals(typeName))
			{

				return DeliveryRequest.Factory.parse(reader);

			}

			if ("http://model.gateway.com".equals(namespaceURI) &&
					"DeliveryResponse".equals(typeName))
			{

				return DeliveryResponse.Factory.parse(reader);

			}

			throw new org.apache.axis2.databinding.ADBException("Unsupported type " + namespaceURI + " " + typeName);
		}

	}

	public static class ServiceRequest
			implements org.apache.axis2.databinding.ADBBean
	{
		/*
		 * This type was generated from the piece of schema that had name =
		 * ServiceRequest Namespace URI = http://model.gateway.com Namespace
		 * Prefix = ns1
		 */

		private static java.lang.String generatePrefix(java.lang.String namespace)
		{
			if (namespace.equals("http://model.gateway.com"))
			{
				return "ns1";
			}
			return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
		}

		/**
		 * field for AgentId
		 */

		protected long		localAgentId;

		/*
		 * This tracker boolean wil be used to detect whether the user called
		 * the set method for this attribute. It will be used to determine
		 * whether to include this field in the serialized XML
		 */
		protected boolean	localAgentIdTracker	= false;

		/**
		 * Auto generated getter method
		 * 
		 * @return long
		 */
		public long getAgentId()
		{
			return localAgentId;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            AgentId
		 */
		public void setAgentId(long param)
		{

			// setting primitive attribute tracker to true

			if (param == java.lang.Long.MIN_VALUE)
			{
				localAgentIdTracker = false;

			}
			else
			{
				localAgentIdTracker = true;
			}

			this.localAgentId = param;

		}

		/**
		 * field for CpId
		 */

		protected long		localCpId;

		/*
		 * This tracker boolean wil be used to detect whether the user called
		 * the set method for this attribute. It will be used to determine
		 * whether to include this field in the serialized XML
		 */
		protected boolean	localCpIdTracker	= false;

		/**
		 * Auto generated getter method
		 * 
		 * @return long
		 */
		public long getCpId()
		{
			return localCpId;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            CpId
		 */
		public void setCpId(long param)
		{

			// setting primitive attribute tracker to true

			if (param == java.lang.Long.MIN_VALUE)
			{
				localCpIdTracker = false;

			}
			else
			{
				localCpIdTracker = true;
			}

			this.localCpId = param;

		}

		/**
		 * field for Isdn
		 */

		protected java.lang.String	localIsdn;

		/*
		 * This tracker boolean wil be used to detect whether the user called
		 * the set method for this attribute. It will be used to determine
		 * whether to include this field in the serialized XML
		 */
		protected boolean			localIsdnTracker	= false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getIsdn()
		{
			return localIsdn;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            Isdn
		 */
		public void setIsdn(java.lang.String param)
		{

			if (param != null)
			{
				// update the setting tracker
				localIsdnTracker = true;
			}
			else
			{
				localIsdnTracker = true;

			}

			this.localIsdn = param;

		}

		/**
		 * field for OrderNo
		 */

		protected java.lang.String	localOrderNo;

		/*
		 * This tracker boolean wil be used to detect whether the user called
		 * the set method for this attribute. It will be used to determine
		 * whether to include this field in the serialized XML
		 */
		protected boolean			localOrderNoTracker	= false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getOrderNo()
		{
			return localOrderNo;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            OrderNo
		 */
		public void setOrderNo(java.lang.String param)
		{

			if (param != null)
			{
				// update the setting tracker
				localOrderNoTracker = true;
			}
			else
			{
				localOrderNoTracker = true;

			}

			this.localOrderNo = param;

		}

		/**
		 * field for Password
		 */

		protected java.lang.String	localPassword;

		/*
		 * This tracker boolean wil be used to detect whether the user called
		 * the set method for this attribute. It will be used to determine
		 * whether to include this field in the serialized XML
		 */
		protected boolean			localPasswordTracker	= false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getPassword()
		{
			return localPassword;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            Password
		 */
		public void setPassword(java.lang.String param)
		{

			if (param != null)
			{
				// update the setting tracker
				localPasswordTracker = true;
			}
			else
			{
				localPasswordTracker = true;

			}

			this.localPassword = param;

		}

		/**
		 * field for Product
		 */

		protected java.lang.String	localProduct;

		/*
		 * This tracker boolean wil be used to detect whether the user called
		 * the set method for this attribute. It will be used to determine
		 * whether to include this field in the serialized XML
		 */
		protected boolean			localProductTracker	= false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getProduct()
		{
			return localProduct;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            Product
		 */
		public void setProduct(java.lang.String param)
		{

			if (param != null)
			{
				// update the setting tracker
				localProductTracker = true;
			}
			else
			{
				localProductTracker = true;

			}

			this.localProduct = param;

		}

		/**
		 * field for RequestDate
		 */

		protected java.lang.String	localRequestDate;

		/*
		 * This tracker boolean wil be used to detect whether the user called
		 * the set method for this attribute. It will be used to determine
		 * whether to include this field in the serialized XML
		 */
		protected boolean			localRequestDateTracker	= false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getRequestDate()
		{
			return localRequestDate;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            RequestDate
		 */
		public void setRequestDate(java.lang.String param)
		{

			if (param != null)
			{
				// update the setting tracker
				localRequestDateTracker = true;
			}
			else
			{
				localRequestDateTracker = true;

			}

			this.localRequestDate = param;

		}

		/**
		 * field for RequestId
		 */

		protected long		localRequestId;

		/*
		 * This tracker boolean wil be used to detect whether the user called
		 * the set method for this attribute. It will be used to determine
		 * whether to include this field in the serialized XML
		 */
		protected boolean	localRequestIdTracker	= false;

		/**
		 * Auto generated getter method
		 * 
		 * @return long
		 */
		public long getRequestId()
		{
			return localRequestId;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            RequestId
		 */
		public void setRequestId(long param)
		{

			// setting primitive attribute tracker to true

			if (param == java.lang.Long.MIN_VALUE)
			{
				localRequestIdTracker = false;

			}
			else
			{
				localRequestIdTracker = true;
			}

			this.localRequestId = param;

		}

		/**
		 * field for SessionId
		 */

		protected java.lang.String	localSessionId;

		/*
		 * This tracker boolean wil be used to detect whether the user called
		 * the set method for this attribute. It will be used to determine
		 * whether to include this field in the serialized XML
		 */
		protected boolean			localSessionIdTracker	= false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getSessionId()
		{
			return localSessionId;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            SessionId
		 */
		public void setSessionId(java.lang.String param)
		{

			if (param != null)
			{
				// update the setting tracker
				localSessionIdTracker = true;
			}
			else
			{
				localSessionIdTracker = true;

			}

			this.localSessionId = param;

		}

		/**
		 * field for Username
		 */

		protected java.lang.String	localUsername;

		/*
		 * This tracker boolean wil be used to detect whether the user called
		 * the set method for this attribute. It will be used to determine
		 * whether to include this field in the serialized XML
		 */
		protected boolean			localUsernameTracker	= false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getUsername()
		{
			return localUsername;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            Username
		 */
		public void setUsername(java.lang.String param)
		{

			if (param != null)
			{
				// update the setting tracker
				localUsernameTracker = true;
			}
			else
			{
				localUsernameTracker = true;

			}

			this.localUsername = param;

		}

		/**
		 * isReaderMTOMAware
		 * 
		 * @return true if the reader supports MTOM
		 */
		public static boolean isReaderMTOMAware(javax.xml.stream.XMLStreamReader reader)
		{
			boolean isReaderMTOMAware = false;

			try
			{
				isReaderMTOMAware = java.lang.Boolean.TRUE.equals(reader.getProperty(org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
			}
			catch (java.lang.IllegalArgumentException e)
			{
				isReaderMTOMAware = false;
			}
			return isReaderMTOMAware;
		}

		/**
		 * 
		 * @param parentQName
		 * @param factory
		 * @return org.apache.axiom.om.OMElement
		 */
		public org.apache.axiom.om.OMElement getOMElement(
				final javax.xml.namespace.QName parentQName,
				final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException
		{

			org.apache.axiom.om.OMDataSource dataSource =
						new org.apache.axis2.databinding.ADBDataSource(this, parentQName)
						{

							public void serialize(org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
									throws javax.xml.stream.XMLStreamException
							{
								ServiceRequest.this.serialize(parentQName, factory, xmlWriter);
							}
						};
			return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(
					parentQName, factory, dataSource);

		}

		public void serialize(final javax.xml.namespace.QName parentQName,
										final org.apache.axiom.om.OMFactory factory,
										org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
								throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException
		{
			serialize(parentQName, factory, xmlWriter, false);
		}

		public void serialize(final javax.xml.namespace.QName parentQName,
								final org.apache.axiom.om.OMFactory factory,
								org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter,
								boolean serializeType)
				throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException
		{

			java.lang.String prefix = null;
			java.lang.String namespace = null;

			prefix = parentQName.getPrefix();
			namespace = parentQName.getNamespaceURI();

			if ((namespace != null) && (namespace.trim().length() > 0))
			{
				java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
				if (writerPrefix != null)
				{
					xmlWriter.writeStartElement(namespace, parentQName.getLocalPart());
				}
				else
				{
					if (prefix == null)
					{
						prefix = generatePrefix(namespace);
					}

					xmlWriter.writeStartElement(prefix, parentQName.getLocalPart(), namespace);
					xmlWriter.writeNamespace(prefix, namespace);
					xmlWriter.setPrefix(prefix, namespace);
				}
			}
			else
			{
				xmlWriter.writeStartElement(parentQName.getLocalPart());
			}

			if (serializeType)
			{

				java.lang.String namespacePrefix = registerPrefix(xmlWriter, "http://model.gateway.com");
				if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0))
				{
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type",
							namespacePrefix + ":ServiceRequest",
							xmlWriter);
				}
				else
				{
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type",
							"ServiceRequest",
							xmlWriter);
				}

			}
			if (localAgentIdTracker)
			{
				namespace = "http://model.gateway.com";
				if (!namespace.equals(""))
				{
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null)
					{
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "agentId", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					}
					else
					{
						xmlWriter.writeStartElement(namespace, "agentId");
					}

				}
				else
				{
					xmlWriter.writeStartElement("agentId");
				}

				if (localAgentId == java.lang.Long.MIN_VALUE)
				{

					throw new org.apache.axis2.databinding.ADBException("agentId cannot be null!!");

				}
				else
				{
					xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localAgentId));
				}

				xmlWriter.writeEndElement();
			}
			if (localCpIdTracker)
			{
				namespace = "http://model.gateway.com";
				if (!namespace.equals(""))
				{
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null)
					{
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "cpId", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					}
					else
					{
						xmlWriter.writeStartElement(namespace, "cpId");
					}

				}
				else
				{
					xmlWriter.writeStartElement("cpId");
				}

				if (localCpId == java.lang.Long.MIN_VALUE)
				{

					throw new org.apache.axis2.databinding.ADBException("cpId cannot be null!!");

				}
				else
				{
					xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCpId));
				}

				xmlWriter.writeEndElement();
			}
			if (localIsdnTracker)
			{
				namespace = "http://model.gateway.com";
				if (!namespace.equals(""))
				{
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null)
					{
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "isdn", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					}
					else
					{
						xmlWriter.writeStartElement(namespace, "isdn");
					}

				}
				else
				{
					xmlWriter.writeStartElement("isdn");
				}

				if (localIsdn == null)
				{
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				}
				else
				{

					xmlWriter.writeCharacters(localIsdn);

				}

				xmlWriter.writeEndElement();
			}
			if (localOrderNoTracker)
			{
				namespace = "http://model.gateway.com";
				if (!namespace.equals(""))
				{
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null)
					{
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "orderNo", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					}
					else
					{
						xmlWriter.writeStartElement(namespace, "orderNo");
					}

				}
				else
				{
					xmlWriter.writeStartElement("orderNo");
				}

				if (localOrderNo == null)
				{
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				}
				else
				{

					xmlWriter.writeCharacters(localOrderNo);

				}

				xmlWriter.writeEndElement();
			}
			if (localPasswordTracker)
			{
				namespace = "http://model.gateway.com";
				if (!namespace.equals(""))
				{
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null)
					{
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "password", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					}
					else
					{
						xmlWriter.writeStartElement(namespace, "password");
					}

				}
				else
				{
					xmlWriter.writeStartElement("password");
				}

				if (localPassword == null)
				{
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				}
				else
				{

					xmlWriter.writeCharacters(localPassword);

				}

				xmlWriter.writeEndElement();
			}
			if (localProductTracker)
			{
				namespace = "http://model.gateway.com";
				if (!namespace.equals(""))
				{
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null)
					{
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "product", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					}
					else
					{
						xmlWriter.writeStartElement(namespace, "product");
					}

				}
				else
				{
					xmlWriter.writeStartElement("product");
				}

				if (localProduct == null)
				{
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				}
				else
				{

					xmlWriter.writeCharacters(localProduct);

				}

				xmlWriter.writeEndElement();
			}
			if (localRequestDateTracker)
			{
				namespace = "http://model.gateway.com";
				if (!namespace.equals(""))
				{
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null)
					{
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "requestDate", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					}
					else
					{
						xmlWriter.writeStartElement(namespace, "requestDate");
					}

				}
				else
				{
					xmlWriter.writeStartElement("requestDate");
				}

				if (localRequestDate == null)
				{
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				}
				else
				{

					xmlWriter.writeCharacters(localRequestDate);

				}

				xmlWriter.writeEndElement();
			}
			if (localRequestIdTracker)
			{
				namespace = "http://model.gateway.com";
				if (!namespace.equals(""))
				{
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null)
					{
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "requestId", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					}
					else
					{
						xmlWriter.writeStartElement(namespace, "requestId");
					}

				}
				else
				{
					xmlWriter.writeStartElement("requestId");
				}

				if (localRequestId == java.lang.Long.MIN_VALUE)
				{

					throw new org.apache.axis2.databinding.ADBException("requestId cannot be null!!");

				}
				else
				{
					xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localRequestId));
				}

				xmlWriter.writeEndElement();
			}
			if (localSessionIdTracker)
			{
				namespace = "http://model.gateway.com";
				if (!namespace.equals(""))
				{
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null)
					{
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "sessionId", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					}
					else
					{
						xmlWriter.writeStartElement(namespace, "sessionId");
					}

				}
				else
				{
					xmlWriter.writeStartElement("sessionId");
				}

				if (localSessionId == null)
				{
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				}
				else
				{

					xmlWriter.writeCharacters(localSessionId);

				}

				xmlWriter.writeEndElement();
			}
			if (localUsernameTracker)
			{
				namespace = "http://model.gateway.com";
				if (!namespace.equals(""))
				{
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null)
					{
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "username", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					}
					else
					{
						xmlWriter.writeStartElement(namespace, "username");
					}

				}
				else
				{
					xmlWriter.writeStartElement("username");
				}

				if (localUsername == null)
				{
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				}
				else
				{

					xmlWriter.writeCharacters(localUsername);

				}

				xmlWriter.writeEndElement();
			}
			xmlWriter.writeEndElement();

		}

		/**
		 * Util method to write an attribute with the ns prefix
		 */
		private void writeAttribute(java.lang.String prefix, java.lang.String namespace, java.lang.String attName,
										java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException
		{
			if (xmlWriter.getPrefix(namespace) == null)
			{
				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);

			}

			xmlWriter.writeAttribute(namespace, attName, attValue);

		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeAttribute(java.lang.String namespace, java.lang.String attName,
										java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException
		{
			if (namespace.equals(""))
			{
				xmlWriter.writeAttribute(attName, attValue);
			}
			else
			{
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attValue);
			}
		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
												javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException
		{

			java.lang.String attributeNamespace = qname.getNamespaceURI();
			java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
			if (attributePrefix == null)
			{
				attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
			}
			java.lang.String attributeValue;
			if (attributePrefix.trim().length() > 0)
			{
				attributeValue = attributePrefix + ":" + qname.getLocalPart();
			}
			else
			{
				attributeValue = qname.getLocalPart();
			}

			if (namespace.equals(""))
			{
				xmlWriter.writeAttribute(attName, attributeValue);
			}
			else
			{
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attributeValue);
			}
		}

		/**
		 * method to handle Qnames
		 */

		private void writeQName(javax.xml.namespace.QName qname,
								javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException
		{
			java.lang.String namespaceURI = qname.getNamespaceURI();
			if (namespaceURI != null)
			{
				java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
				if (prefix == null)
				{
					prefix = generatePrefix(namespaceURI);
					xmlWriter.writeNamespace(prefix, namespaceURI);
					xmlWriter.setPrefix(prefix, namespaceURI);
				}

				if (prefix.trim().length() > 0)
				{
					xmlWriter.writeCharacters(prefix + ":" + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				}
				else
				{
					// i.e this is the default namespace
					xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				}

			}
			else
			{
				xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
			}
		}

		private void writeQNames(javax.xml.namespace.QName[] qnames,
									javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException
		{

			if (qnames != null)
			{
				// we have to store this data until last moment since it is not
				// possible to write any
				// namespace data after writing the charactor data
				java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
				java.lang.String namespaceURI = null;
				java.lang.String prefix = null;

				for (int i = 0; i < qnames.length; i++)
				{
					if (i > 0)
					{
						stringToWrite.append(" ");
					}
					namespaceURI = qnames[i].getNamespaceURI();
					if (namespaceURI != null)
					{
						prefix = xmlWriter.getPrefix(namespaceURI);
						if ((prefix == null) || (prefix.length() == 0))
						{
							prefix = generatePrefix(namespaceURI);
							xmlWriter.writeNamespace(prefix, namespaceURI);
							xmlWriter.setPrefix(prefix, namespaceURI);
						}

						if (prefix.trim().length() > 0)
						{
							stringToWrite.append(prefix).append(":")
									.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
						}
						else
						{
							stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
						}
					}
					else
					{
						stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
					}
				}
				xmlWriter.writeCharacters(stringToWrite.toString());
			}

		}

		/**
		 * Register a namespace prefix
		 */
		private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace)
				throws javax.xml.stream.XMLStreamException
		{
			java.lang.String prefix = xmlWriter.getPrefix(namespace);

			if (prefix == null)
			{
				prefix = generatePrefix(namespace);

				while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null)
				{
					prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
				}

				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);
			}

			return prefix;
		}

		/**
		 * databinding method to get an XML representation of this object
		 * 
		 */
		public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
					throws org.apache.axis2.databinding.ADBException
		{

			java.util.ArrayList elementList = new java.util.ArrayList();
			java.util.ArrayList attribList = new java.util.ArrayList();

			if (localAgentIdTracker)
			{
				elementList.add(new javax.xml.namespace.QName("http://model.gateway.com",
																		"agentId"));

				elementList.add(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localAgentId));
			}
			if (localCpIdTracker)
			{
				elementList.add(new javax.xml.namespace.QName("http://model.gateway.com",
																		"cpId"));

				elementList.add(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCpId));
			}
			if (localIsdnTracker)
			{
				elementList.add(new javax.xml.namespace.QName("http://model.gateway.com",
																		"isdn"));

				elementList.add(localIsdn == null ? null :
											org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localIsdn));
			}
			if (localOrderNoTracker)
			{
				elementList.add(new javax.xml.namespace.QName("http://model.gateway.com",
																		"orderNo"));

				elementList.add(localOrderNo == null ? null :
											org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localOrderNo));
			}
			if (localPasswordTracker)
			{
				elementList.add(new javax.xml.namespace.QName("http://model.gateway.com",
																		"password"));

				elementList.add(localPassword == null ? null :
											org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localPassword));
			}
			if (localProductTracker)
			{
				elementList.add(new javax.xml.namespace.QName("http://model.gateway.com",
																		"product"));

				elementList.add(localProduct == null ? null :
											org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localProduct));
			}
			if (localRequestDateTracker)
			{
				elementList.add(new javax.xml.namespace.QName("http://model.gateway.com",
																		"requestDate"));

				elementList.add(localRequestDate == null ? null :
											org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localRequestDate));
			}
			if (localRequestIdTracker)
			{
				elementList.add(new javax.xml.namespace.QName("http://model.gateway.com",
																		"requestId"));

				elementList.add(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localRequestId));
			}
			if (localSessionIdTracker)
			{
				elementList.add(new javax.xml.namespace.QName("http://model.gateway.com",
																		"sessionId"));

				elementList.add(localSessionId == null ? null :
											org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSessionId));
			}
			if (localUsernameTracker)
			{
				elementList.add(new javax.xml.namespace.QName("http://model.gateway.com",
																		"username"));

				elementList.add(localUsername == null ? null :
											org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localUsername));
			}

			return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(), attribList.toArray());

		}

		/**
		 * Factory class that keeps the parse method
		 */
		public static class Factory
		{

			/**
			 * static method to create the object Precondition: If this object
			 * is an element, the current or next start element starts this
			 * object and any intervening reader events are ignorable If this
			 * object is not an element, it is a complex type and the reader is
			 * at the event just after the outer start element Postcondition: If
			 * this object is an element, the reader is positioned at its end
			 * element If this object is a complex type, the reader is
			 * positioned at the end element of its outer element
			 */
			public static ServiceRequest parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception
			{
				ServiceRequest object =
						new ServiceRequest();

				int event;
				java.lang.String nillableValue = null;
				java.lang.String prefix = "";
				java.lang.String namespaceuri = "";
				try
				{

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type") != null)
					{
						java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
								"type");
						if (fullTypeName != null)
						{
							java.lang.String nsPrefix = null;
							if (fullTypeName.indexOf(":") > -1)
							{
								nsPrefix = fullTypeName.substring(0, fullTypeName.indexOf(":"));
							}
							nsPrefix = nsPrefix == null ? "" : nsPrefix;

							java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":") + 1);

							if (!"ServiceRequest".equals(type))
							{
								// find namespace for the prefix
								java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
								return (ServiceRequest) ExtensionMapper.getTypeObject(
										nsUri, type, reader);
							}

						}

					}

					// Note all attributes that were handled. Used to differ
					// normal attributes
					// from anyAttributes.
					java.util.Vector handledAttributes = new java.util.Vector();

					reader.next();

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("http://model.gateway.com", "agentId").equals(reader.getName()))
					{

						java.lang.String content = reader.getElementText();

						object.setAgentId(
													org.apache.axis2.databinding.utils.ConverterUtil.convertToLong(content));

						reader.next();

					} // End of if for expected property start element

					else
					{

						object.setAgentId(java.lang.Long.MIN_VALUE);

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("http://model.gateway.com", "cpId").equals(reader.getName()))
					{

						java.lang.String content = reader.getElementText();

						object.setCpId(
													org.apache.axis2.databinding.utils.ConverterUtil.convertToLong(content));

						reader.next();

					} // End of if for expected property start element

					else
					{

						object.setCpId(java.lang.Long.MIN_VALUE);

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("http://model.gateway.com", "isdn").equals(reader.getName()))
					{

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue))
						{

							java.lang.String content = reader.getElementText();

							object.setIsdn(
													org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						}
						else
						{

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else
					{

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("http://model.gateway.com", "orderNo").equals(reader.getName()))
					{

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue))
						{

							java.lang.String content = reader.getElementText();

							object.setOrderNo(
													org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						}
						else
						{

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else
					{

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("http://model.gateway.com", "password").equals(reader.getName()))
					{

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue))
						{

							java.lang.String content = reader.getElementText();

							object.setPassword(
													org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						}
						else
						{

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else
					{

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("http://model.gateway.com", "product").equals(reader.getName()))
					{

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue))
						{

							java.lang.String content = reader.getElementText();

							object.setProduct(
													org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						}
						else
						{

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else
					{

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("http://model.gateway.com", "requestDate").equals(reader.getName()))
					{

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue))
						{

							java.lang.String content = reader.getElementText();

							object.setRequestDate(
													org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						}
						else
						{

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else
					{

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("http://model.gateway.com", "requestId").equals(reader.getName()))
					{

						java.lang.String content = reader.getElementText();

						object.setRequestId(
													org.apache.axis2.databinding.utils.ConverterUtil.convertToLong(content));

						reader.next();

					} // End of if for expected property start element

					else
					{

						object.setRequestId(java.lang.Long.MIN_VALUE);

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("http://model.gateway.com", "sessionId").equals(reader.getName()))
					{

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue))
						{

							java.lang.String content = reader.getElementText();

							object.setSessionId(
													org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						}
						else
						{

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else
					{

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("http://model.gateway.com", "username").equals(reader.getName()))
					{

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue))
						{

							java.lang.String content = reader.getElementText();

							object.setUsername(
													org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						}
						else
						{

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else
					{

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement())
						// A start element we are not expecting indicates a
						// trailing invalid property
						throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());

				}
				catch (javax.xml.stream.XMLStreamException e)
				{
					throw new java.lang.Exception(e);
				}

				return object;
			}

		}// end of factory class

	}

	public static class DeliveryResponseE
			implements org.apache.axis2.databinding.ADBBean
	{

		public static final javax.xml.namespace.QName	MY_QNAME	= new javax.xml.namespace.QName(
																			"http://service.crm.com",
																			"deliveryResponse",
																			"ns2");

		private static java.lang.String generatePrefix(java.lang.String namespace)
		{
			if (namespace.equals("http://service.crm.com"))
			{
				return "ns2";
			}
			return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
		}

		/**
		 * field for Out
		 */

		protected DeliveryResponse	localOut;

		/**
		 * Auto generated getter method
		 * 
		 * @return DeliveryResponse
		 */
		public DeliveryResponse getOut()
		{
			return localOut;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            Out
		 */
		public void setOut(DeliveryResponse param)
		{

			this.localOut = param;

		}

		/**
		 * isReaderMTOMAware
		 * 
		 * @return true if the reader supports MTOM
		 */
		public static boolean isReaderMTOMAware(javax.xml.stream.XMLStreamReader reader)
		{
			boolean isReaderMTOMAware = false;

			try
			{
				isReaderMTOMAware = java.lang.Boolean.TRUE.equals(reader.getProperty(org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
			}
			catch (java.lang.IllegalArgumentException e)
			{
				isReaderMTOMAware = false;
			}
			return isReaderMTOMAware;
		}

		/**
		 * 
		 * @param parentQName
		 * @param factory
		 * @return org.apache.axiom.om.OMElement
		 */
		public org.apache.axiom.om.OMElement getOMElement(
				final javax.xml.namespace.QName parentQName,
				final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException
		{

			org.apache.axiom.om.OMDataSource dataSource =
						new org.apache.axis2.databinding.ADBDataSource(this, MY_QNAME)
						{

							public void serialize(org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
									throws javax.xml.stream.XMLStreamException
							{
								DeliveryResponseE.this.serialize(MY_QNAME, factory, xmlWriter);
							}
						};
			return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(
					MY_QNAME, factory, dataSource);

		}

		public void serialize(final javax.xml.namespace.QName parentQName,
										final org.apache.axiom.om.OMFactory factory,
										org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
								throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException
		{
			serialize(parentQName, factory, xmlWriter, false);
		}

		public void serialize(final javax.xml.namespace.QName parentQName,
								final org.apache.axiom.om.OMFactory factory,
								org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter,
								boolean serializeType)
				throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException
		{

			java.lang.String prefix = null;
			java.lang.String namespace = null;

			prefix = parentQName.getPrefix();
			namespace = parentQName.getNamespaceURI();

			if ((namespace != null) && (namespace.trim().length() > 0))
			{
				java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
				if (writerPrefix != null)
				{
					xmlWriter.writeStartElement(namespace, parentQName.getLocalPart());
				}
				else
				{
					if (prefix == null)
					{
						prefix = generatePrefix(namespace);
					}

					xmlWriter.writeStartElement(prefix, parentQName.getLocalPart(), namespace);
					xmlWriter.writeNamespace(prefix, namespace);
					xmlWriter.setPrefix(prefix, namespace);
				}
			}
			else
			{
				xmlWriter.writeStartElement(parentQName.getLocalPart());
			}

			if (serializeType)
			{

				java.lang.String namespacePrefix = registerPrefix(xmlWriter, "http://service.crm.com");
				if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0))
				{
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type",
							namespacePrefix + ":deliveryResponse",
							xmlWriter);
				}
				else
				{
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type",
							"deliveryResponse",
							xmlWriter);
				}

			}

			if (localOut == null)
			{

				java.lang.String namespace2 = "http://service.crm.com";

				if (!namespace2.equals(""))
				{
					java.lang.String prefix2 = xmlWriter.getPrefix(namespace2);

					if (prefix2 == null)
					{
						prefix2 = generatePrefix(namespace2);

						xmlWriter.writeStartElement(prefix2, "out", namespace2);
						xmlWriter.writeNamespace(prefix2, namespace2);
						xmlWriter.setPrefix(prefix2, namespace2);

					}
					else
					{
						xmlWriter.writeStartElement(namespace2, "out");
					}

				}
				else
				{
					xmlWriter.writeStartElement("out");
				}

				// write the nil attribute
				writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);
				xmlWriter.writeEndElement();
			}
			else
			{
				localOut.serialize(new javax.xml.namespace.QName("http://service.crm.com", "out"),
										factory, xmlWriter);
			}

			xmlWriter.writeEndElement();

		}

		/**
		 * Util method to write an attribute with the ns prefix
		 */
		private void writeAttribute(java.lang.String prefix, java.lang.String namespace, java.lang.String attName,
										java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException
		{
			if (xmlWriter.getPrefix(namespace) == null)
			{
				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);

			}

			xmlWriter.writeAttribute(namespace, attName, attValue);

		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeAttribute(java.lang.String namespace, java.lang.String attName,
										java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException
		{
			if (namespace.equals(""))
			{
				xmlWriter.writeAttribute(attName, attValue);
			}
			else
			{
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attValue);
			}
		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
												javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException
		{

			java.lang.String attributeNamespace = qname.getNamespaceURI();
			java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
			if (attributePrefix == null)
			{
				attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
			}
			java.lang.String attributeValue;
			if (attributePrefix.trim().length() > 0)
			{
				attributeValue = attributePrefix + ":" + qname.getLocalPart();
			}
			else
			{
				attributeValue = qname.getLocalPart();
			}

			if (namespace.equals(""))
			{
				xmlWriter.writeAttribute(attName, attributeValue);
			}
			else
			{
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attributeValue);
			}
		}

		/**
		 * method to handle Qnames
		 */

		private void writeQName(javax.xml.namespace.QName qname,
								javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException
		{
			java.lang.String namespaceURI = qname.getNamespaceURI();
			if (namespaceURI != null)
			{
				java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
				if (prefix == null)
				{
					prefix = generatePrefix(namespaceURI);
					xmlWriter.writeNamespace(prefix, namespaceURI);
					xmlWriter.setPrefix(prefix, namespaceURI);
				}

				if (prefix.trim().length() > 0)
				{
					xmlWriter.writeCharacters(prefix + ":" + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				}
				else
				{
					// i.e this is the default namespace
					xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				}

			}
			else
			{
				xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
			}
		}

		private void writeQNames(javax.xml.namespace.QName[] qnames,
									javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException
		{

			if (qnames != null)
			{
				// we have to store this data until last moment since it is not
				// possible to write any
				// namespace data after writing the charactor data
				java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
				java.lang.String namespaceURI = null;
				java.lang.String prefix = null;

				for (int i = 0; i < qnames.length; i++)
				{
					if (i > 0)
					{
						stringToWrite.append(" ");
					}
					namespaceURI = qnames[i].getNamespaceURI();
					if (namespaceURI != null)
					{
						prefix = xmlWriter.getPrefix(namespaceURI);
						if ((prefix == null) || (prefix.length() == 0))
						{
							prefix = generatePrefix(namespaceURI);
							xmlWriter.writeNamespace(prefix, namespaceURI);
							xmlWriter.setPrefix(prefix, namespaceURI);
						}

						if (prefix.trim().length() > 0)
						{
							stringToWrite.append(prefix).append(":")
									.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
						}
						else
						{
							stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
						}
					}
					else
					{
						stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
					}
				}
				xmlWriter.writeCharacters(stringToWrite.toString());
			}

		}

		/**
		 * Register a namespace prefix
		 */
		private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace)
				throws javax.xml.stream.XMLStreamException
		{
			java.lang.String prefix = xmlWriter.getPrefix(namespace);

			if (prefix == null)
			{
				prefix = generatePrefix(namespace);

				while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null)
				{
					prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
				}

				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);
			}

			return prefix;
		}

		/**
		 * databinding method to get an XML representation of this object
		 * 
		 */
		public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
					throws org.apache.axis2.databinding.ADBException
		{

			java.util.ArrayList elementList = new java.util.ArrayList();
			java.util.ArrayList attribList = new java.util.ArrayList();

			elementList.add(new javax.xml.namespace.QName("http://service.crm.com",
																		"out"));

			elementList.add(localOut == null ? null :
									localOut);

			return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(), attribList.toArray());

		}

		/**
		 * Factory class that keeps the parse method
		 */
		public static class Factory
		{

			/**
			 * static method to create the object Precondition: If this object
			 * is an element, the current or next start element starts this
			 * object and any intervening reader events are ignorable If this
			 * object is not an element, it is a complex type and the reader is
			 * at the event just after the outer start element Postcondition: If
			 * this object is an element, the reader is positioned at its end
			 * element If this object is a complex type, the reader is
			 * positioned at the end element of its outer element
			 */
			public static DeliveryResponseE parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception
			{
				DeliveryResponseE object =
						new DeliveryResponseE();

				int event;
				java.lang.String nillableValue = null;
				java.lang.String prefix = "";
				java.lang.String namespaceuri = "";
				try
				{

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type") != null)
					{
						java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
								"type");
						if (fullTypeName != null)
						{
							java.lang.String nsPrefix = null;
							if (fullTypeName.indexOf(":") > -1)
							{
								nsPrefix = fullTypeName.substring(0, fullTypeName.indexOf(":"));
							}
							nsPrefix = nsPrefix == null ? "" : nsPrefix;

							java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":") + 1);

							if (!"deliveryResponse".equals(type))
							{
								// find namespace for the prefix
								java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
								return (DeliveryResponseE) ExtensionMapper.getTypeObject(
										nsUri, type, reader);
							}

						}

					}

					// Note all attributes that were handled. Used to differ
					// normal attributes
					// from anyAttributes.
					java.util.Vector handledAttributes = new java.util.Vector();

					reader.next();

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("http://service.crm.com", "out").equals(reader.getName()))
					{

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if ("true".equals(nillableValue) || "1".equals(nillableValue))
						{
							object.setOut(null);
							reader.next();

							reader.next();

						}
						else
						{

							object.setOut(DeliveryResponse.Factory.parse(reader));

							reader.next();
						}
					} // End of if for expected property start element

					else
					{
						// A start element we are not expecting indicates an
						// invalid parameter was passed
						throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement())
						// A start element we are not expecting indicates a
						// trailing invalid property
						throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());

				}
				catch (javax.xml.stream.XMLStreamException e)
				{
					throw new java.lang.Exception(e);
				}

				return object;
			}

		}// end of factory class

	}

	public static class ServiceResponse
			implements org.apache.axis2.databinding.ADBBean
	{
		/*
		 * This type was generated from the piece of schema that had name =
		 * ServiceResponse Namespace URI = http://model.gateway.com Namespace
		 * Prefix = ns1
		 */

		private static java.lang.String generatePrefix(java.lang.String namespace)
		{
			if (namespace.equals("http://model.gateway.com"))
			{
				return "ns1";
			}
			return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
		}

		/**
		 * field for Amount
		 */

		protected double	localAmount;

		/*
		 * This tracker boolean wil be used to detect whether the user called
		 * the set method for this attribute. It will be used to determine
		 * whether to include this field in the serialized XML
		 */
		protected boolean	localAmountTracker	= false;

		/**
		 * Auto generated getter method
		 * 
		 * @return double
		 */
		public double getAmount()
		{
			return localAmount;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            Amount
		 */
		public void setAmount(double param)
		{

			// setting primitive attribute tracker to true

			if (java.lang.Double.isNaN(param))
			{
				localAmountTracker = false;

			}
			else
			{
				localAmountTracker = true;
			}

			this.localAmount = param;

		}

		/**
		 * field for Cause
		 */

		protected java.lang.String	localCause;

		/*
		 * This tracker boolean wil be used to detect whether the user called
		 * the set method for this attribute. It will be used to determine
		 * whether to include this field in the serialized XML
		 */
		protected boolean			localCauseTracker	= false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getCause()
		{
			return localCause;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            Cause
		 */
		public void setCause(java.lang.String param)
		{

			if (param != null)
			{
				// update the setting tracker
				localCauseTracker = true;
			}
			else
			{
				localCauseTracker = true;

			}

			this.localCause = param;

		}

		/**
		 * field for Description
		 */

		protected java.lang.String	localDescription;

		/*
		 * This tracker boolean wil be used to detect whether the user called
		 * the set method for this attribute. It will be used to determine
		 * whether to include this field in the serialized XML
		 */
		protected boolean			localDescriptionTracker	= false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getDescription()
		{
			return localDescription;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            Description
		 */
		public void setDescription(java.lang.String param)
		{

			if (param != null)
			{
				// update the setting tracker
				localDescriptionTracker = true;
			}
			else
			{
				localDescriptionTracker = true;

			}

			this.localDescription = param;

		}

		/**
		 * field for Isdn
		 */

		protected java.lang.String	localIsdn;

		/*
		 * This tracker boolean wil be used to detect whether the user called
		 * the set method for this attribute. It will be used to determine
		 * whether to include this field in the serialized XML
		 */
		protected boolean			localIsdnTracker	= false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getIsdn()
		{
			return localIsdn;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            Isdn
		 */
		public void setIsdn(java.lang.String param)
		{

			if (param != null)
			{
				// update the setting tracker
				localIsdnTracker = true;
			}
			else
			{
				localIsdnTracker = true;

			}

			this.localIsdn = param;

		}

		/**
		 * field for OrderDate
		 */

		protected java.util.Calendar	localOrderDate;

		/*
		 * This tracker boolean wil be used to detect whether the user called
		 * the set method for this attribute. It will be used to determine
		 * whether to include this field in the serialized XML
		 */
		protected boolean				localOrderDateTracker	= false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.util.Calendar
		 */
		public java.util.Calendar getOrderDate()
		{
			return localOrderDate;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            OrderDate
		 */
		public void setOrderDate(java.util.Calendar param)
		{

			if (param != null)
			{
				// update the setting tracker
				localOrderDateTracker = true;
			}
			else
			{
				localOrderDateTracker = false;

			}

			this.localOrderDate = param;

		}

		/**
		 * field for OrderId
		 */

		protected long		localOrderId;

		/*
		 * This tracker boolean wil be used to detect whether the user called
		 * the set method for this attribute. It will be used to determine
		 * whether to include this field in the serialized XML
		 */
		protected boolean	localOrderIdTracker	= false;

		/**
		 * Auto generated getter method
		 * 
		 * @return long
		 */
		public long getOrderId()
		{
			return localOrderId;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            OrderId
		 */
		public void setOrderId(long param)
		{

			// setting primitive attribute tracker to true

			if (param == java.lang.Long.MIN_VALUE)
			{
				localOrderIdTracker = false;

			}
			else
			{
				localOrderIdTracker = true;
			}

			this.localOrderId = param;

		}

		/**
		 * field for OrderNo
		 */

		protected java.lang.String	localOrderNo;

		/*
		 * This tracker boolean wil be used to detect whether the user called
		 * the set method for this attribute. It will be used to determine
		 * whether to include this field in the serialized XML
		 */
		protected boolean			localOrderNoTracker	= false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getOrderNo()
		{
			return localOrderNo;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            OrderNo
		 */
		public void setOrderNo(java.lang.String param)
		{

			if (param != null)
			{
				// update the setting tracker
				localOrderNoTracker = true;
			}
			else
			{
				localOrderNoTracker = true;

			}

			this.localOrderNo = param;

		}

		/**
		 * field for Result
		 */

		protected int		localResult;

		/*
		 * This tracker boolean wil be used to detect whether the user called
		 * the set method for this attribute. It will be used to determine
		 * whether to include this field in the serialized XML
		 */
		protected boolean	localResultTracker	= false;

		/**
		 * Auto generated getter method
		 * 
		 * @return int
		 */
		public int getResult()
		{
			return localResult;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            Result
		 */
		public void setResult(int param)
		{

			// setting primitive attribute tracker to true

			if (param == java.lang.Integer.MIN_VALUE)
			{
				localResultTracker = false;

			}
			else
			{
				localResultTracker = true;
			}

			this.localResult = param;

		}

		/**
		 * isReaderMTOMAware
		 * 
		 * @return true if the reader supports MTOM
		 */
		public static boolean isReaderMTOMAware(javax.xml.stream.XMLStreamReader reader)
		{
			boolean isReaderMTOMAware = false;

			try
			{
				isReaderMTOMAware = java.lang.Boolean.TRUE.equals(reader.getProperty(org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
			}
			catch (java.lang.IllegalArgumentException e)
			{
				isReaderMTOMAware = false;
			}
			return isReaderMTOMAware;
		}

		/**
		 * 
		 * @param parentQName
		 * @param factory
		 * @return org.apache.axiom.om.OMElement
		 */
		public org.apache.axiom.om.OMElement getOMElement(
				final javax.xml.namespace.QName parentQName,
				final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException
		{

			org.apache.axiom.om.OMDataSource dataSource =
						new org.apache.axis2.databinding.ADBDataSource(this, parentQName)
						{

							public void serialize(org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
									throws javax.xml.stream.XMLStreamException
							{
								ServiceResponse.this.serialize(parentQName, factory, xmlWriter);
							}
						};
			return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(
					parentQName, factory, dataSource);

		}

		public void serialize(final javax.xml.namespace.QName parentQName,
										final org.apache.axiom.om.OMFactory factory,
										org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
								throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException
		{
			serialize(parentQName, factory, xmlWriter, false);
		}

		public void serialize(final javax.xml.namespace.QName parentQName,
								final org.apache.axiom.om.OMFactory factory,
								org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter,
								boolean serializeType)
				throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException
		{

			java.lang.String prefix = null;
			java.lang.String namespace = null;

			prefix = parentQName.getPrefix();
			namespace = parentQName.getNamespaceURI();

			if ((namespace != null) && (namespace.trim().length() > 0))
			{
				java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
				if (writerPrefix != null)
				{
					xmlWriter.writeStartElement(namespace, parentQName.getLocalPart());
				}
				else
				{
					if (prefix == null)
					{
						prefix = generatePrefix(namespace);
					}

					xmlWriter.writeStartElement(prefix, parentQName.getLocalPart(), namespace);
					xmlWriter.writeNamespace(prefix, namespace);
					xmlWriter.setPrefix(prefix, namespace);
				}
			}
			else
			{
				xmlWriter.writeStartElement(parentQName.getLocalPart());
			}

			if (serializeType)
			{

				java.lang.String namespacePrefix = registerPrefix(xmlWriter, "http://model.gateway.com");
				if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0))
				{
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type",
							namespacePrefix + ":ServiceResponse",
							xmlWriter);
				}
				else
				{
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type",
							"ServiceResponse",
							xmlWriter);
				}

			}
			if (localAmountTracker)
			{
				namespace = "http://model.gateway.com";
				if (!namespace.equals(""))
				{
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null)
					{
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "amount", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					}
					else
					{
						xmlWriter.writeStartElement(namespace, "amount");
					}

				}
				else
				{
					xmlWriter.writeStartElement("amount");
				}

				if (java.lang.Double.isNaN(localAmount))
				{

					throw new org.apache.axis2.databinding.ADBException("amount cannot be null!!");

				}
				else
				{
					xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localAmount));
				}

				xmlWriter.writeEndElement();
			}
			if (localCauseTracker)
			{
				namespace = "http://model.gateway.com";
				if (!namespace.equals(""))
				{
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null)
					{
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "cause", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					}
					else
					{
						xmlWriter.writeStartElement(namespace, "cause");
					}

				}
				else
				{
					xmlWriter.writeStartElement("cause");
				}

				if (localCause == null)
				{
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				}
				else
				{

					xmlWriter.writeCharacters(localCause);

				}

				xmlWriter.writeEndElement();
			}
			if (localDescriptionTracker)
			{
				namespace = "http://model.gateway.com";
				if (!namespace.equals(""))
				{
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null)
					{
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "description", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					}
					else
					{
						xmlWriter.writeStartElement(namespace, "description");
					}

				}
				else
				{
					xmlWriter.writeStartElement("description");
				}

				if (localDescription == null)
				{
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				}
				else
				{

					xmlWriter.writeCharacters(localDescription);

				}

				xmlWriter.writeEndElement();
			}
			if (localIsdnTracker)
			{
				namespace = "http://model.gateway.com";
				if (!namespace.equals(""))
				{
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null)
					{
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "isdn", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					}
					else
					{
						xmlWriter.writeStartElement(namespace, "isdn");
					}

				}
				else
				{
					xmlWriter.writeStartElement("isdn");
				}

				if (localIsdn == null)
				{
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				}
				else
				{

					xmlWriter.writeCharacters(localIsdn);

				}

				xmlWriter.writeEndElement();
			}
			if (localOrderDateTracker)
			{
				namespace = "http://model.gateway.com";
				if (!namespace.equals(""))
				{
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null)
					{
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "orderDate", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					}
					else
					{
						xmlWriter.writeStartElement(namespace, "orderDate");
					}

				}
				else
				{
					xmlWriter.writeStartElement("orderDate");
				}

				if (localOrderDate == null)
				{
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("orderDate cannot be null!!");

				}
				else
				{

					xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localOrderDate));

				}

				xmlWriter.writeEndElement();
			}
			if (localOrderIdTracker)
			{
				namespace = "http://model.gateway.com";
				if (!namespace.equals(""))
				{
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null)
					{
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "orderId", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					}
					else
					{
						xmlWriter.writeStartElement(namespace, "orderId");
					}

				}
				else
				{
					xmlWriter.writeStartElement("orderId");
				}

				if (localOrderId == java.lang.Long.MIN_VALUE)
				{

					throw new org.apache.axis2.databinding.ADBException("orderId cannot be null!!");

				}
				else
				{
					xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localOrderId));
				}

				xmlWriter.writeEndElement();
			}
			if (localOrderNoTracker)
			{
				namespace = "http://model.gateway.com";
				if (!namespace.equals(""))
				{
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null)
					{
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "orderNo", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					}
					else
					{
						xmlWriter.writeStartElement(namespace, "orderNo");
					}

				}
				else
				{
					xmlWriter.writeStartElement("orderNo");
				}

				if (localOrderNo == null)
				{
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				}
				else
				{

					xmlWriter.writeCharacters(localOrderNo);

				}

				xmlWriter.writeEndElement();
			}
			if (localResultTracker)
			{
				namespace = "http://model.gateway.com";
				if (!namespace.equals(""))
				{
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null)
					{
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "result", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					}
					else
					{
						xmlWriter.writeStartElement(namespace, "result");
					}

				}
				else
				{
					xmlWriter.writeStartElement("result");
				}

				if (localResult == java.lang.Integer.MIN_VALUE)
				{

					throw new org.apache.axis2.databinding.ADBException("result cannot be null!!");

				}
				else
				{
					xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localResult));
				}

				xmlWriter.writeEndElement();
			}
			xmlWriter.writeEndElement();

		}

		/**
		 * Util method to write an attribute with the ns prefix
		 */
		private void writeAttribute(java.lang.String prefix, java.lang.String namespace, java.lang.String attName,
										java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException
		{
			if (xmlWriter.getPrefix(namespace) == null)
			{
				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);

			}

			xmlWriter.writeAttribute(namespace, attName, attValue);

		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeAttribute(java.lang.String namespace, java.lang.String attName,
										java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException
		{
			if (namespace.equals(""))
			{
				xmlWriter.writeAttribute(attName, attValue);
			}
			else
			{
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attValue);
			}
		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
												javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException
		{

			java.lang.String attributeNamespace = qname.getNamespaceURI();
			java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
			if (attributePrefix == null)
			{
				attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
			}
			java.lang.String attributeValue;
			if (attributePrefix.trim().length() > 0)
			{
				attributeValue = attributePrefix + ":" + qname.getLocalPart();
			}
			else
			{
				attributeValue = qname.getLocalPart();
			}

			if (namespace.equals(""))
			{
				xmlWriter.writeAttribute(attName, attributeValue);
			}
			else
			{
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attributeValue);
			}
		}

		/**
		 * method to handle Qnames
		 */

		private void writeQName(javax.xml.namespace.QName qname,
								javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException
		{
			java.lang.String namespaceURI = qname.getNamespaceURI();
			if (namespaceURI != null)
			{
				java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
				if (prefix == null)
				{
					prefix = generatePrefix(namespaceURI);
					xmlWriter.writeNamespace(prefix, namespaceURI);
					xmlWriter.setPrefix(prefix, namespaceURI);
				}

				if (prefix.trim().length() > 0)
				{
					xmlWriter.writeCharacters(prefix + ":" + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				}
				else
				{
					// i.e this is the default namespace
					xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				}

			}
			else
			{
				xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
			}
		}

		private void writeQNames(javax.xml.namespace.QName[] qnames,
									javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException
		{

			if (qnames != null)
			{
				// we have to store this data until last moment since it is not
				// possible to write any
				// namespace data after writing the charactor data
				java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
				java.lang.String namespaceURI = null;
				java.lang.String prefix = null;

				for (int i = 0; i < qnames.length; i++)
				{
					if (i > 0)
					{
						stringToWrite.append(" ");
					}
					namespaceURI = qnames[i].getNamespaceURI();
					if (namespaceURI != null)
					{
						prefix = xmlWriter.getPrefix(namespaceURI);
						if ((prefix == null) || (prefix.length() == 0))
						{
							prefix = generatePrefix(namespaceURI);
							xmlWriter.writeNamespace(prefix, namespaceURI);
							xmlWriter.setPrefix(prefix, namespaceURI);
						}

						if (prefix.trim().length() > 0)
						{
							stringToWrite.append(prefix).append(":")
									.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
						}
						else
						{
							stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
						}
					}
					else
					{
						stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
					}
				}
				xmlWriter.writeCharacters(stringToWrite.toString());
			}

		}

		/**
		 * Register a namespace prefix
		 */
		private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace)
				throws javax.xml.stream.XMLStreamException
		{
			java.lang.String prefix = xmlWriter.getPrefix(namespace);

			if (prefix == null)
			{
				prefix = generatePrefix(namespace);

				while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null)
				{
					prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
				}

				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);
			}

			return prefix;
		}

		/**
		 * databinding method to get an XML representation of this object
		 * 
		 */
		public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
					throws org.apache.axis2.databinding.ADBException
		{

			java.util.ArrayList elementList = new java.util.ArrayList();
			java.util.ArrayList attribList = new java.util.ArrayList();

			if (localAmountTracker)
			{
				elementList.add(new javax.xml.namespace.QName("http://model.gateway.com",
																		"amount"));

				elementList.add(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localAmount));
			}
			if (localCauseTracker)
			{
				elementList.add(new javax.xml.namespace.QName("http://model.gateway.com",
																		"cause"));

				elementList.add(localCause == null ? null :
											org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCause));
			}
			if (localDescriptionTracker)
			{
				elementList.add(new javax.xml.namespace.QName("http://model.gateway.com",
																		"description"));

				elementList.add(localDescription == null ? null :
											org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localDescription));
			}
			if (localIsdnTracker)
			{
				elementList.add(new javax.xml.namespace.QName("http://model.gateway.com",
																		"isdn"));

				elementList.add(localIsdn == null ? null :
											org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localIsdn));
			}
			if (localOrderDateTracker)
			{
				elementList.add(new javax.xml.namespace.QName("http://model.gateway.com",
																		"orderDate"));

				if (localOrderDate != null)
				{
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localOrderDate));
				}
				else
				{
					throw new org.apache.axis2.databinding.ADBException("orderDate cannot be null!!");
				}
			}
			if (localOrderIdTracker)
			{
				elementList.add(new javax.xml.namespace.QName("http://model.gateway.com",
																		"orderId"));

				elementList.add(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localOrderId));
			}
			if (localOrderNoTracker)
			{
				elementList.add(new javax.xml.namespace.QName("http://model.gateway.com",
																		"orderNo"));

				elementList.add(localOrderNo == null ? null :
											org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localOrderNo));
			}
			if (localResultTracker)
			{
				elementList.add(new javax.xml.namespace.QName("http://model.gateway.com",
																		"result"));

				elementList.add(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localResult));
			}

			return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(), attribList.toArray());

		}

		/**
		 * Factory class that keeps the parse method
		 */
		public static class Factory
		{

			/**
			 * static method to create the object Precondition: If this object
			 * is an element, the current or next start element starts this
			 * object and any intervening reader events are ignorable If this
			 * object is not an element, it is a complex type and the reader is
			 * at the event just after the outer start element Postcondition: If
			 * this object is an element, the reader is positioned at its end
			 * element If this object is a complex type, the reader is
			 * positioned at the end element of its outer element
			 */
			public static ServiceResponse parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception
			{
				ServiceResponse object =
						new ServiceResponse();

				int event;
				java.lang.String nillableValue = null;
				java.lang.String prefix = "";
				java.lang.String namespaceuri = "";
				try
				{

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type") != null)
					{
						java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
								"type");
						if (fullTypeName != null)
						{
							java.lang.String nsPrefix = null;
							if (fullTypeName.indexOf(":") > -1)
							{
								nsPrefix = fullTypeName.substring(0, fullTypeName.indexOf(":"));
							}
							nsPrefix = nsPrefix == null ? "" : nsPrefix;

							java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":") + 1);

							if (!"ServiceResponse".equals(type))
							{
								// find namespace for the prefix
								java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
								return (ServiceResponse) ExtensionMapper.getTypeObject(
										nsUri, type, reader);
							}

						}

					}

					// Note all attributes that were handled. Used to differ
					// normal attributes
					// from anyAttributes.
					java.util.Vector handledAttributes = new java.util.Vector();

					reader.next();

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("http://model.gateway.com", "amount").equals(reader.getName()))
					{

						java.lang.String content = reader.getElementText();

						object.setAmount(
													org.apache.axis2.databinding.utils.ConverterUtil.convertToDouble(content));

						reader.next();

					} // End of if for expected property start element

					else
					{

						object.setAmount(java.lang.Double.NaN);

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("http://model.gateway.com", "cause").equals(reader.getName()))
					{

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue))
						{

							java.lang.String content = reader.getElementText();

							object.setCause(
													org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						}
						else
						{

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else
					{

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("http://model.gateway.com", "description").equals(reader.getName()))
					{

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue))
						{

							java.lang.String content = reader.getElementText();

							object.setDescription(
													org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						}
						else
						{

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else
					{

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("http://model.gateway.com", "isdn").equals(reader.getName()))
					{

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue))
						{

							java.lang.String content = reader.getElementText();

							object.setIsdn(
													org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						}
						else
						{

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else
					{

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("http://model.gateway.com", "orderDate").equals(reader.getName()))
					{

						java.lang.String content = reader.getElementText();

						object.setOrderDate(
													org.apache.axis2.databinding.utils.ConverterUtil.convertToDateTime(content));

						reader.next();

					} // End of if for expected property start element

					else
					{

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("http://model.gateway.com", "orderId").equals(reader.getName()))
					{

						java.lang.String content = reader.getElementText();

						object.setOrderId(
													org.apache.axis2.databinding.utils.ConverterUtil.convertToLong(content));

						reader.next();

					} // End of if for expected property start element

					else
					{

						object.setOrderId(java.lang.Long.MIN_VALUE);

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("http://model.gateway.com", "orderNo").equals(reader.getName()))
					{

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue))
						{

							java.lang.String content = reader.getElementText();

							object.setOrderNo(
													org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						}
						else
						{

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else
					{

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("http://model.gateway.com", "result").equals(reader.getName()))
					{

						java.lang.String content = reader.getElementText();

						object.setResult(
													org.apache.axis2.databinding.utils.ConverterUtil.convertToInt(content));

						reader.next();

					} // End of if for expected property start element

					else
					{

						object.setResult(java.lang.Integer.MIN_VALUE);

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement())
						// A start element we are not expecting indicates a
						// trailing invalid property
						throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());

				}
				catch (javax.xml.stream.XMLStreamException e)
				{
					throw new java.lang.Exception(e);
				}

				return object;
			}

		}// end of factory class

	}

	public static class DeliveryRequest
			implements org.apache.axis2.databinding.ADBBean
	{
		/*
		 * This type was generated from the piece of schema that had name =
		 * DeliveryRequest Namespace URI = http://model.gateway.com Namespace
		 * Prefix = ns1
		 */

		private static java.lang.String generatePrefix(java.lang.String namespace)
		{
			if (namespace.equals("http://model.gateway.com"))
			{
				return "ns1";
			}
			return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
		}

		/**
		 * field for AgentId
		 */

		protected long		localAgentId;

		/*
		 * This tracker boolean wil be used to detect whether the user called
		 * the set method for this attribute. It will be used to determine
		 * whether to include this field in the serialized XML
		 */
		protected boolean	localAgentIdTracker	= false;

		/**
		 * Auto generated getter method
		 * 
		 * @return long
		 */
		public long getAgentId()
		{
			return localAgentId;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            AgentId
		 */
		public void setAgentId(long param)
		{

			// setting primitive attribute tracker to true

			if (param == java.lang.Long.MIN_VALUE)
			{
				localAgentIdTracker = false;

			}
			else
			{
				localAgentIdTracker = true;
			}

			this.localAgentId = param;

		}

		/**
		 * field for ChargeMode
		 */

		protected int		localChargeMode;

		/*
		 * This tracker boolean wil be used to detect whether the user called
		 * the set method for this attribute. It will be used to determine
		 * whether to include this field in the serialized XML
		 */
		protected boolean	localChargeModeTracker	= false;

		/**
		 * Auto generated getter method
		 * 
		 * @return int
		 */
		public int getChargeMode()
		{
			return localChargeMode;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            ChargeMode
		 */
		public void setChargeMode(int param)
		{

			// setting primitive attribute tracker to true

			if (param == java.lang.Integer.MIN_VALUE)
			{
				localChargeModeTracker = false;

			}
			else
			{
				localChargeModeTracker = true;
			}

			this.localChargeMode = param;

		}

		/**
		 * field for CpId
		 */

		protected long		localCpId;

		/*
		 * This tracker boolean wil be used to detect whether the user called
		 * the set method for this attribute. It will be used to determine
		 * whether to include this field in the serialized XML
		 */
		protected boolean	localCpIdTracker	= false;

		/**
		 * Auto generated getter method
		 * 
		 * @return long
		 */
		public long getCpId()
		{
			return localCpId;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            CpId
		 */
		public void setCpId(long param)
		{

			// setting primitive attribute tracker to true

			if (param == java.lang.Long.MIN_VALUE)
			{
				localCpIdTracker = false;

			}
			else
			{
				localCpIdTracker = true;
			}

			this.localCpId = param;

		}

		/**
		 * field for DeliveryContent
		 */

		protected java.lang.String	localDeliveryContent;

		/*
		 * This tracker boolean wil be used to detect whether the user called
		 * the set method for this attribute. It will be used to determine
		 * whether to include this field in the serialized XML
		 */
		protected boolean			localDeliveryContentTracker	= false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getDeliveryContent()
		{
			return localDeliveryContent;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            DeliveryContent
		 */
		public void setDeliveryContent(java.lang.String param)
		{

			if (param != null)
			{
				// update the setting tracker
				localDeliveryContentTracker = true;
			}
			else
			{
				localDeliveryContentTracker = true;

			}

			this.localDeliveryContent = param;

		}

		/**
		 * field for DeliveryMode
		 */

		protected int		localDeliveryMode;

		/*
		 * This tracker boolean wil be used to detect whether the user called
		 * the set method for this attribute. It will be used to determine
		 * whether to include this field in the serialized XML
		 */
		protected boolean	localDeliveryModeTracker	= false;

		/**
		 * Auto generated getter method
		 * 
		 * @return int
		 */
		public int getDeliveryMode()
		{
			return localDeliveryMode;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            DeliveryMode
		 */
		public void setDeliveryMode(int param)
		{

			// setting primitive attribute tracker to true

			if (param == java.lang.Integer.MIN_VALUE)
			{
				localDeliveryModeTracker = false;

			}
			else
			{
				localDeliveryModeTracker = true;
			}

			this.localDeliveryMode = param;

		}

		/**
		 * field for DeliveryWapHref
		 */

		protected java.lang.String	localDeliveryWapHref;

		/*
		 * This tracker boolean wil be used to detect whether the user called
		 * the set method for this attribute. It will be used to determine
		 * whether to include this field in the serialized XML
		 */
		protected boolean			localDeliveryWapHrefTracker	= false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getDeliveryWapHref()
		{
			return localDeliveryWapHref;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            DeliveryWapHref
		 */
		public void setDeliveryWapHref(java.lang.String param)
		{

			if (param != null)
			{
				// update the setting tracker
				localDeliveryWapHrefTracker = true;
			}
			else
			{
				localDeliveryWapHrefTracker = true;

			}

			this.localDeliveryWapHref = param;

		}

		/**
		 * field for DeliveryWapTitle
		 */

		protected java.lang.String	localDeliveryWapTitle;

		/*
		 * This tracker boolean wil be used to detect whether the user called
		 * the set method for this attribute. It will be used to determine
		 * whether to include this field in the serialized XML
		 */
		protected boolean			localDeliveryWapTitleTracker	= false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getDeliveryWapTitle()
		{
			return localDeliveryWapTitle;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            DeliveryWapTitle
		 */
		public void setDeliveryWapTitle(java.lang.String param)
		{

			if (param != null)
			{
				// update the setting tracker
				localDeliveryWapTitleTracker = true;
			}
			else
			{
				localDeliveryWapTitleTracker = true;

			}

			this.localDeliveryWapTitle = param;

		}

		/**
		 * field for Isdn
		 */

		protected java.lang.String	localIsdn;

		/*
		 * This tracker boolean wil be used to detect whether the user called
		 * the set method for this attribute. It will be used to determine
		 * whether to include this field in the serialized XML
		 */
		protected boolean			localIsdnTracker	= false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getIsdn()
		{
			return localIsdn;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            Isdn
		 */
		public void setIsdn(java.lang.String param)
		{

			if (param != null)
			{
				// update the setting tracker
				localIsdnTracker = true;
			}
			else
			{
				localIsdnTracker = true;

			}

			this.localIsdn = param;

		}

		/**
		 * field for OrderNo
		 */

		protected java.lang.String	localOrderNo;

		/*
		 * This tracker boolean wil be used to detect whether the user called
		 * the set method for this attribute. It will be used to determine
		 * whether to include this field in the serialized XML
		 */
		protected boolean			localOrderNoTracker	= false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getOrderNo()
		{
			return localOrderNo;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            OrderNo
		 */
		public void setOrderNo(java.lang.String param)
		{

			if (param != null)
			{
				// update the setting tracker
				localOrderNoTracker = true;
			}
			else
			{
				localOrderNoTracker = true;

			}

			this.localOrderNo = param;

		}

		/**
		 * field for Password
		 */

		protected java.lang.String	localPassword;

		/*
		 * This tracker boolean wil be used to detect whether the user called
		 * the set method for this attribute. It will be used to determine
		 * whether to include this field in the serialized XML
		 */
		protected boolean			localPasswordTracker	= false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getPassword()
		{
			return localPassword;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            Password
		 */
		public void setPassword(java.lang.String param)
		{

			if (param != null)
			{
				// update the setting tracker
				localPasswordTracker = true;
			}
			else
			{
				localPasswordTracker = true;

			}

			this.localPassword = param;

		}

		/**
		 * field for Product
		 */

		protected java.lang.String	localProduct;

		/*
		 * This tracker boolean wil be used to detect whether the user called
		 * the set method for this attribute. It will be used to determine
		 * whether to include this field in the serialized XML
		 */
		protected boolean			localProductTracker	= false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getProduct()
		{
			return localProduct;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            Product
		 */
		public void setProduct(java.lang.String param)
		{

			if (param != null)
			{
				// update the setting tracker
				localProductTracker = true;
			}
			else
			{
				localProductTracker = true;

			}

			this.localProduct = param;

		}

		/**
		 * field for RequestDate
		 */

		protected java.lang.String	localRequestDate;

		/*
		 * This tracker boolean wil be used to detect whether the user called
		 * the set method for this attribute. It will be used to determine
		 * whether to include this field in the serialized XML
		 */
		protected boolean			localRequestDateTracker	= false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getRequestDate()
		{
			return localRequestDate;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            RequestDate
		 */
		public void setRequestDate(java.lang.String param)
		{

			if (param != null)
			{
				// update the setting tracker
				localRequestDateTracker = true;
			}
			else
			{
				localRequestDateTracker = true;

			}

			this.localRequestDate = param;

		}

		/**
		 * field for RequestId
		 */

		protected long		localRequestId;

		/*
		 * This tracker boolean wil be used to detect whether the user called
		 * the set method for this attribute. It will be used to determine
		 * whether to include this field in the serialized XML
		 */
		protected boolean	localRequestIdTracker	= false;

		/**
		 * Auto generated getter method
		 * 
		 * @return long
		 */
		public long getRequestId()
		{
			return localRequestId;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            RequestId
		 */
		public void setRequestId(long param)
		{

			// setting primitive attribute tracker to true

			if (param == java.lang.Long.MIN_VALUE)
			{
				localRequestIdTracker = false;

			}
			else
			{
				localRequestIdTracker = true;
			}

			this.localRequestId = param;

		}

		/**
		 * field for SessionId
		 */

		protected java.lang.String	localSessionId;

		/*
		 * This tracker boolean wil be used to detect whether the user called
		 * the set method for this attribute. It will be used to determine
		 * whether to include this field in the serialized XML
		 */
		protected boolean			localSessionIdTracker	= false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getSessionId()
		{
			return localSessionId;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            SessionId
		 */
		public void setSessionId(java.lang.String param)
		{

			if (param != null)
			{
				// update the setting tracker
				localSessionIdTracker = true;
			}
			else
			{
				localSessionIdTracker = true;

			}

			this.localSessionId = param;

		}

		/**
		 * field for Username
		 */

		protected java.lang.String	localUsername;

		/*
		 * This tracker boolean wil be used to detect whether the user called
		 * the set method for this attribute. It will be used to determine
		 * whether to include this field in the serialized XML
		 */
		protected boolean			localUsernameTracker	= false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getUsername()
		{
			return localUsername;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            Username
		 */
		public void setUsername(java.lang.String param)
		{

			if (param != null)
			{
				// update the setting tracker
				localUsernameTracker = true;
			}
			else
			{
				localUsernameTracker = true;

			}

			this.localUsername = param;

		}

		/**
		 * isReaderMTOMAware
		 * 
		 * @return true if the reader supports MTOM
		 */
		public static boolean isReaderMTOMAware(javax.xml.stream.XMLStreamReader reader)
		{
			boolean isReaderMTOMAware = false;

			try
			{
				isReaderMTOMAware = java.lang.Boolean.TRUE.equals(reader.getProperty(org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
			}
			catch (java.lang.IllegalArgumentException e)
			{
				isReaderMTOMAware = false;
			}
			return isReaderMTOMAware;
		}

		/**
		 * 
		 * @param parentQName
		 * @param factory
		 * @return org.apache.axiom.om.OMElement
		 */
		public org.apache.axiom.om.OMElement getOMElement(
				final javax.xml.namespace.QName parentQName,
				final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException
		{

			org.apache.axiom.om.OMDataSource dataSource =
						new org.apache.axis2.databinding.ADBDataSource(this, parentQName)
						{

							public void serialize(org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
									throws javax.xml.stream.XMLStreamException
							{
								DeliveryRequest.this.serialize(parentQName, factory, xmlWriter);
							}
						};
			return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(
					parentQName, factory, dataSource);

		}

		public void serialize(final javax.xml.namespace.QName parentQName,
										final org.apache.axiom.om.OMFactory factory,
										org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
								throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException
		{
			serialize(parentQName, factory, xmlWriter, false);
		}

		public void serialize(final javax.xml.namespace.QName parentQName,
								final org.apache.axiom.om.OMFactory factory,
								org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter,
								boolean serializeType)
				throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException
		{

			java.lang.String prefix = null;
			java.lang.String namespace = null;

			prefix = parentQName.getPrefix();
			namespace = parentQName.getNamespaceURI();

			if ((namespace != null) && (namespace.trim().length() > 0))
			{
				java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
				if (writerPrefix != null)
				{
					xmlWriter.writeStartElement(namespace, parentQName.getLocalPart());
				}
				else
				{
					if (prefix == null)
					{
						prefix = generatePrefix(namespace);
					}

					xmlWriter.writeStartElement(prefix, parentQName.getLocalPart(), namespace);
					xmlWriter.writeNamespace(prefix, namespace);
					xmlWriter.setPrefix(prefix, namespace);
				}
			}
			else
			{
				xmlWriter.writeStartElement(parentQName.getLocalPart());
			}

			if (serializeType)
			{

				java.lang.String namespacePrefix = registerPrefix(xmlWriter, "http://model.gateway.com");
				if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0))
				{
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type",
							namespacePrefix + ":DeliveryRequest",
							xmlWriter);
				}
				else
				{
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type",
							"DeliveryRequest",
							xmlWriter);
				}

			}
			if (localAgentIdTracker)
			{
				namespace = "http://model.gateway.com";
				if (!namespace.equals(""))
				{
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null)
					{
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "agentId", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					}
					else
					{
						xmlWriter.writeStartElement(namespace, "agentId");
					}

				}
				else
				{
					xmlWriter.writeStartElement("agentId");
				}

				if (localAgentId == java.lang.Long.MIN_VALUE)
				{

					throw new org.apache.axis2.databinding.ADBException("agentId cannot be null!!");

				}
				else
				{
					xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localAgentId));
				}

				xmlWriter.writeEndElement();
			}
			if (localChargeModeTracker)
			{
				namespace = "http://model.gateway.com";
				if (!namespace.equals(""))
				{
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null)
					{
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "chargeMode", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					}
					else
					{
						xmlWriter.writeStartElement(namespace, "chargeMode");
					}

				}
				else
				{
					xmlWriter.writeStartElement("chargeMode");
				}

				if (localChargeMode == java.lang.Integer.MIN_VALUE)
				{

					throw new org.apache.axis2.databinding.ADBException("chargeMode cannot be null!!");

				}
				else
				{
					xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localChargeMode));
				}

				xmlWriter.writeEndElement();
			}
			if (localCpIdTracker)
			{
				namespace = "http://model.gateway.com";
				if (!namespace.equals(""))
				{
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null)
					{
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "cpId", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					}
					else
					{
						xmlWriter.writeStartElement(namespace, "cpId");
					}

				}
				else
				{
					xmlWriter.writeStartElement("cpId");
				}

				if (localCpId == java.lang.Long.MIN_VALUE)
				{

					throw new org.apache.axis2.databinding.ADBException("cpId cannot be null!!");

				}
				else
				{
					xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCpId));
				}

				xmlWriter.writeEndElement();
			}
			if (localDeliveryContentTracker)
			{
				namespace = "http://model.gateway.com";
				if (!namespace.equals(""))
				{
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null)
					{
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "deliveryContent", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					}
					else
					{
						xmlWriter.writeStartElement(namespace, "deliveryContent");
					}

				}
				else
				{
					xmlWriter.writeStartElement("deliveryContent");
				}

				if (localDeliveryContent == null)
				{
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				}
				else
				{

					xmlWriter.writeCharacters(localDeliveryContent);

				}

				xmlWriter.writeEndElement();
			}
			if (localDeliveryModeTracker)
			{
				namespace = "http://model.gateway.com";
				if (!namespace.equals(""))
				{
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null)
					{
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "deliveryMode", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					}
					else
					{
						xmlWriter.writeStartElement(namespace, "deliveryMode");
					}

				}
				else
				{
					xmlWriter.writeStartElement("deliveryMode");
				}

				if (localDeliveryMode == java.lang.Integer.MIN_VALUE)
				{

					throw new org.apache.axis2.databinding.ADBException("deliveryMode cannot be null!!");

				}
				else
				{
					xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localDeliveryMode));
				}

				xmlWriter.writeEndElement();
			}
			if (localDeliveryWapHrefTracker)
			{
				namespace = "http://model.gateway.com";
				if (!namespace.equals(""))
				{
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null)
					{
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "deliveryWapHref", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					}
					else
					{
						xmlWriter.writeStartElement(namespace, "deliveryWapHref");
					}

				}
				else
				{
					xmlWriter.writeStartElement("deliveryWapHref");
				}

				if (localDeliveryWapHref == null)
				{
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				}
				else
				{

					xmlWriter.writeCharacters(localDeliveryWapHref);

				}

				xmlWriter.writeEndElement();
			}
			if (localDeliveryWapTitleTracker)
			{
				namespace = "http://model.gateway.com";
				if (!namespace.equals(""))
				{
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null)
					{
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "deliveryWapTitle", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					}
					else
					{
						xmlWriter.writeStartElement(namespace, "deliveryWapTitle");
					}

				}
				else
				{
					xmlWriter.writeStartElement("deliveryWapTitle");
				}

				if (localDeliveryWapTitle == null)
				{
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				}
				else
				{

					xmlWriter.writeCharacters(localDeliveryWapTitle);

				}

				xmlWriter.writeEndElement();
			}
			if (localIsdnTracker)
			{
				namespace = "http://model.gateway.com";
				if (!namespace.equals(""))
				{
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null)
					{
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "isdn", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					}
					else
					{
						xmlWriter.writeStartElement(namespace, "isdn");
					}

				}
				else
				{
					xmlWriter.writeStartElement("isdn");
				}

				if (localIsdn == null)
				{
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				}
				else
				{

					xmlWriter.writeCharacters(localIsdn);

				}

				xmlWriter.writeEndElement();
			}
			if (localOrderNoTracker)
			{
				namespace = "http://model.gateway.com";
				if (!namespace.equals(""))
				{
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null)
					{
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "orderNo", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					}
					else
					{
						xmlWriter.writeStartElement(namespace, "orderNo");
					}

				}
				else
				{
					xmlWriter.writeStartElement("orderNo");
				}

				if (localOrderNo == null)
				{
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				}
				else
				{

					xmlWriter.writeCharacters(localOrderNo);

				}

				xmlWriter.writeEndElement();
			}
			if (localPasswordTracker)
			{
				namespace = "http://model.gateway.com";
				if (!namespace.equals(""))
				{
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null)
					{
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "password", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					}
					else
					{
						xmlWriter.writeStartElement(namespace, "password");
					}

				}
				else
				{
					xmlWriter.writeStartElement("password");
				}

				if (localPassword == null)
				{
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				}
				else
				{

					xmlWriter.writeCharacters(localPassword);

				}

				xmlWriter.writeEndElement();
			}
			if (localProductTracker)
			{
				namespace = "http://model.gateway.com";
				if (!namespace.equals(""))
				{
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null)
					{
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "product", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					}
					else
					{
						xmlWriter.writeStartElement(namespace, "product");
					}

				}
				else
				{
					xmlWriter.writeStartElement("product");
				}

				if (localProduct == null)
				{
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				}
				else
				{

					xmlWriter.writeCharacters(localProduct);

				}

				xmlWriter.writeEndElement();
			}
			if (localRequestDateTracker)
			{
				namespace = "http://model.gateway.com";
				if (!namespace.equals(""))
				{
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null)
					{
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "requestDate", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					}
					else
					{
						xmlWriter.writeStartElement(namespace, "requestDate");
					}

				}
				else
				{
					xmlWriter.writeStartElement("requestDate");
				}

				if (localRequestDate == null)
				{
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				}
				else
				{

					xmlWriter.writeCharacters(localRequestDate);

				}

				xmlWriter.writeEndElement();
			}
			if (localRequestIdTracker)
			{
				namespace = "http://model.gateway.com";
				if (!namespace.equals(""))
				{
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null)
					{
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "requestId", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					}
					else
					{
						xmlWriter.writeStartElement(namespace, "requestId");
					}

				}
				else
				{
					xmlWriter.writeStartElement("requestId");
				}

				if (localRequestId == java.lang.Long.MIN_VALUE)
				{

					throw new org.apache.axis2.databinding.ADBException("requestId cannot be null!!");

				}
				else
				{
					xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localRequestId));
				}

				xmlWriter.writeEndElement();
			}
			if (localSessionIdTracker)
			{
				namespace = "http://model.gateway.com";
				if (!namespace.equals(""))
				{
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null)
					{
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "sessionId", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					}
					else
					{
						xmlWriter.writeStartElement(namespace, "sessionId");
					}

				}
				else
				{
					xmlWriter.writeStartElement("sessionId");
				}

				if (localSessionId == null)
				{
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				}
				else
				{

					xmlWriter.writeCharacters(localSessionId);

				}

				xmlWriter.writeEndElement();
			}
			if (localUsernameTracker)
			{
				namespace = "http://model.gateway.com";
				if (!namespace.equals(""))
				{
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null)
					{
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "username", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					}
					else
					{
						xmlWriter.writeStartElement(namespace, "username");
					}

				}
				else
				{
					xmlWriter.writeStartElement("username");
				}

				if (localUsername == null)
				{
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				}
				else
				{

					xmlWriter.writeCharacters(localUsername);

				}

				xmlWriter.writeEndElement();
			}
			xmlWriter.writeEndElement();

		}

		/**
		 * Util method to write an attribute with the ns prefix
		 */
		private void writeAttribute(java.lang.String prefix, java.lang.String namespace, java.lang.String attName,
										java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException
		{
			if (xmlWriter.getPrefix(namespace) == null)
			{
				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);

			}

			xmlWriter.writeAttribute(namespace, attName, attValue);

		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeAttribute(java.lang.String namespace, java.lang.String attName,
										java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException
		{
			if (namespace.equals(""))
			{
				xmlWriter.writeAttribute(attName, attValue);
			}
			else
			{
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attValue);
			}
		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
												javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException
		{

			java.lang.String attributeNamespace = qname.getNamespaceURI();
			java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
			if (attributePrefix == null)
			{
				attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
			}
			java.lang.String attributeValue;
			if (attributePrefix.trim().length() > 0)
			{
				attributeValue = attributePrefix + ":" + qname.getLocalPart();
			}
			else
			{
				attributeValue = qname.getLocalPart();
			}

			if (namespace.equals(""))
			{
				xmlWriter.writeAttribute(attName, attributeValue);
			}
			else
			{
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attributeValue);
			}
		}

		/**
		 * method to handle Qnames
		 */

		private void writeQName(javax.xml.namespace.QName qname,
								javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException
		{
			java.lang.String namespaceURI = qname.getNamespaceURI();
			if (namespaceURI != null)
			{
				java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
				if (prefix == null)
				{
					prefix = generatePrefix(namespaceURI);
					xmlWriter.writeNamespace(prefix, namespaceURI);
					xmlWriter.setPrefix(prefix, namespaceURI);
				}

				if (prefix.trim().length() > 0)
				{
					xmlWriter.writeCharacters(prefix + ":" + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				}
				else
				{
					// i.e this is the default namespace
					xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				}

			}
			else
			{
				xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
			}
		}

		private void writeQNames(javax.xml.namespace.QName[] qnames,
									javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException
		{

			if (qnames != null)
			{
				// we have to store this data until last moment since it is not
				// possible to write any
				// namespace data after writing the charactor data
				java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
				java.lang.String namespaceURI = null;
				java.lang.String prefix = null;

				for (int i = 0; i < qnames.length; i++)
				{
					if (i > 0)
					{
						stringToWrite.append(" ");
					}
					namespaceURI = qnames[i].getNamespaceURI();
					if (namespaceURI != null)
					{
						prefix = xmlWriter.getPrefix(namespaceURI);
						if ((prefix == null) || (prefix.length() == 0))
						{
							prefix = generatePrefix(namespaceURI);
							xmlWriter.writeNamespace(prefix, namespaceURI);
							xmlWriter.setPrefix(prefix, namespaceURI);
						}

						if (prefix.trim().length() > 0)
						{
							stringToWrite.append(prefix).append(":")
									.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
						}
						else
						{
							stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
						}
					}
					else
					{
						stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
					}
				}
				xmlWriter.writeCharacters(stringToWrite.toString());
			}

		}

		/**
		 * Register a namespace prefix
		 */
		private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace)
				throws javax.xml.stream.XMLStreamException
		{
			java.lang.String prefix = xmlWriter.getPrefix(namespace);

			if (prefix == null)
			{
				prefix = generatePrefix(namespace);

				while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null)
				{
					prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
				}

				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);
			}

			return prefix;
		}

		/**
		 * databinding method to get an XML representation of this object
		 * 
		 */
		public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
					throws org.apache.axis2.databinding.ADBException
		{

			java.util.ArrayList elementList = new java.util.ArrayList();
			java.util.ArrayList attribList = new java.util.ArrayList();

			if (localAgentIdTracker)
			{
				elementList.add(new javax.xml.namespace.QName("http://model.gateway.com",
																		"agentId"));

				elementList.add(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localAgentId));
			}
			if (localChargeModeTracker)
			{
				elementList.add(new javax.xml.namespace.QName("http://model.gateway.com",
																		"chargeMode"));

				elementList.add(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localChargeMode));
			}
			if (localCpIdTracker)
			{
				elementList.add(new javax.xml.namespace.QName("http://model.gateway.com",
																		"cpId"));

				elementList.add(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCpId));
			}
			if (localDeliveryContentTracker)
			{
				elementList.add(new javax.xml.namespace.QName("http://model.gateway.com",
																		"deliveryContent"));

				elementList.add(localDeliveryContent == null ? null :
											org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localDeliveryContent));
			}
			if (localDeliveryModeTracker)
			{
				elementList.add(new javax.xml.namespace.QName("http://model.gateway.com",
																		"deliveryMode"));

				elementList.add(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localDeliveryMode));
			}
			if (localDeliveryWapHrefTracker)
			{
				elementList.add(new javax.xml.namespace.QName("http://model.gateway.com",
																		"deliveryWapHref"));

				elementList.add(localDeliveryWapHref == null ? null :
											org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localDeliveryWapHref));
			}
			if (localDeliveryWapTitleTracker)
			{
				elementList.add(new javax.xml.namespace.QName("http://model.gateway.com",
																		"deliveryWapTitle"));

				elementList.add(localDeliveryWapTitle == null ? null :
											org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localDeliveryWapTitle));
			}
			if (localIsdnTracker)
			{
				elementList.add(new javax.xml.namespace.QName("http://model.gateway.com",
																		"isdn"));

				elementList.add(localIsdn == null ? null :
											org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localIsdn));
			}
			if (localOrderNoTracker)
			{
				elementList.add(new javax.xml.namespace.QName("http://model.gateway.com",
																		"orderNo"));

				elementList.add(localOrderNo == null ? null :
											org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localOrderNo));
			}
			if (localPasswordTracker)
			{
				elementList.add(new javax.xml.namespace.QName("http://model.gateway.com",
																		"password"));

				elementList.add(localPassword == null ? null :
											org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localPassword));
			}
			if (localProductTracker)
			{
				elementList.add(new javax.xml.namespace.QName("http://model.gateway.com",
																		"product"));

				elementList.add(localProduct == null ? null :
											org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localProduct));
			}
			if (localRequestDateTracker)
			{
				elementList.add(new javax.xml.namespace.QName("http://model.gateway.com",
																		"requestDate"));

				elementList.add(localRequestDate == null ? null :
											org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localRequestDate));
			}
			if (localRequestIdTracker)
			{
				elementList.add(new javax.xml.namespace.QName("http://model.gateway.com",
																		"requestId"));

				elementList.add(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localRequestId));
			}
			if (localSessionIdTracker)
			{
				elementList.add(new javax.xml.namespace.QName("http://model.gateway.com",
																		"sessionId"));

				elementList.add(localSessionId == null ? null :
											org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSessionId));
			}
			if (localUsernameTracker)
			{
				elementList.add(new javax.xml.namespace.QName("http://model.gateway.com",
																		"username"));

				elementList.add(localUsername == null ? null :
											org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localUsername));
			}

			return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(), attribList.toArray());

		}

		/**
		 * Factory class that keeps the parse method
		 */
		public static class Factory
		{

			/**
			 * static method to create the object Precondition: If this object
			 * is an element, the current or next start element starts this
			 * object and any intervening reader events are ignorable If this
			 * object is not an element, it is a complex type and the reader is
			 * at the event just after the outer start element Postcondition: If
			 * this object is an element, the reader is positioned at its end
			 * element If this object is a complex type, the reader is
			 * positioned at the end element of its outer element
			 */
			public static DeliveryRequest parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception
			{
				DeliveryRequest object =
						new DeliveryRequest();

				int event;
				java.lang.String nillableValue = null;
				java.lang.String prefix = "";
				java.lang.String namespaceuri = "";
				try
				{

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type") != null)
					{
						java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
								"type");
						if (fullTypeName != null)
						{
							java.lang.String nsPrefix = null;
							if (fullTypeName.indexOf(":") > -1)
							{
								nsPrefix = fullTypeName.substring(0, fullTypeName.indexOf(":"));
							}
							nsPrefix = nsPrefix == null ? "" : nsPrefix;

							java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":") + 1);

							if (!"DeliveryRequest".equals(type))
							{
								// find namespace for the prefix
								java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
								return (DeliveryRequest) ExtensionMapper.getTypeObject(
										nsUri, type, reader);
							}

						}

					}

					// Note all attributes that were handled. Used to differ
					// normal attributes
					// from anyAttributes.
					java.util.Vector handledAttributes = new java.util.Vector();

					reader.next();

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("http://model.gateway.com", "agentId").equals(reader.getName()))
					{

						java.lang.String content = reader.getElementText();

						object.setAgentId(
													org.apache.axis2.databinding.utils.ConverterUtil.convertToLong(content));

						reader.next();

					} // End of if for expected property start element

					else
					{

						object.setAgentId(java.lang.Long.MIN_VALUE);

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("http://model.gateway.com", "chargeMode").equals(reader.getName()))
					{

						java.lang.String content = reader.getElementText();

						object.setChargeMode(
													org.apache.axis2.databinding.utils.ConverterUtil.convertToInt(content));

						reader.next();

					} // End of if for expected property start element

					else
					{

						object.setChargeMode(java.lang.Integer.MIN_VALUE);

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("http://model.gateway.com", "cpId").equals(reader.getName()))
					{

						java.lang.String content = reader.getElementText();

						object.setCpId(
													org.apache.axis2.databinding.utils.ConverterUtil.convertToLong(content));

						reader.next();

					} // End of if for expected property start element

					else
					{

						object.setCpId(java.lang.Long.MIN_VALUE);

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("http://model.gateway.com", "deliveryContent").equals(reader.getName()))
					{

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue))
						{

							java.lang.String content = reader.getElementText();

							object.setDeliveryContent(
													org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						}
						else
						{

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else
					{

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("http://model.gateway.com", "deliveryMode").equals(reader.getName()))
					{

						java.lang.String content = reader.getElementText();

						object.setDeliveryMode(
													org.apache.axis2.databinding.utils.ConverterUtil.convertToInt(content));

						reader.next();

					} // End of if for expected property start element

					else
					{

						object.setDeliveryMode(java.lang.Integer.MIN_VALUE);

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("http://model.gateway.com", "deliveryWapHref").equals(reader.getName()))
					{

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue))
						{

							java.lang.String content = reader.getElementText();

							object.setDeliveryWapHref(
													org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						}
						else
						{

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else
					{

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("http://model.gateway.com", "deliveryWapTitle").equals(reader.getName()))
					{

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue))
						{

							java.lang.String content = reader.getElementText();

							object.setDeliveryWapTitle(
													org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						}
						else
						{

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else
					{

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("http://model.gateway.com", "isdn").equals(reader.getName()))
					{

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue))
						{

							java.lang.String content = reader.getElementText();

							object.setIsdn(
													org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						}
						else
						{

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else
					{

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("http://model.gateway.com", "orderNo").equals(reader.getName()))
					{

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue))
						{

							java.lang.String content = reader.getElementText();

							object.setOrderNo(
													org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						}
						else
						{

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else
					{

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("http://model.gateway.com", "password").equals(reader.getName()))
					{

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue))
						{

							java.lang.String content = reader.getElementText();

							object.setPassword(
													org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						}
						else
						{

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else
					{

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("http://model.gateway.com", "product").equals(reader.getName()))
					{

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue))
						{

							java.lang.String content = reader.getElementText();

							object.setProduct(
													org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						}
						else
						{

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else
					{

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("http://model.gateway.com", "requestDate").equals(reader.getName()))
					{

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue))
						{

							java.lang.String content = reader.getElementText();

							object.setRequestDate(
													org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						}
						else
						{

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else
					{

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("http://model.gateway.com", "requestId").equals(reader.getName()))
					{

						java.lang.String content = reader.getElementText();

						object.setRequestId(
													org.apache.axis2.databinding.utils.ConverterUtil.convertToLong(content));

						reader.next();

					} // End of if for expected property start element

					else
					{

						object.setRequestId(java.lang.Long.MIN_VALUE);

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("http://model.gateway.com", "sessionId").equals(reader.getName()))
					{

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue))
						{

							java.lang.String content = reader.getElementText();

							object.setSessionId(
													org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						}
						else
						{

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else
					{

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("http://model.gateway.com", "username").equals(reader.getName()))
					{

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue))
						{

							java.lang.String content = reader.getElementText();

							object.setUsername(
													org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						}
						else
						{

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else
					{

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement())
						// A start element we are not expecting indicates a
						// trailing invalid property
						throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());

				}
				catch (javax.xml.stream.XMLStreamException e)
				{
					throw new java.lang.Exception(e);
				}

				return object;
			}

		}// end of factory class

	}

	public static class GetStatus
			implements org.apache.axis2.databinding.ADBBean
	{

		public static final javax.xml.namespace.QName	MY_QNAME	= new javax.xml.namespace.QName(
																			"http://service.crm.com",
																			"getStatus",
																			"ns2");

		private static java.lang.String generatePrefix(java.lang.String namespace)
		{
			if (namespace.equals("http://service.crm.com"))
			{
				return "ns2";
			}
			return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
		}

		/**
		 * field for In0
		 */

		protected ServiceRequest	localIn0;

		/**
		 * Auto generated getter method
		 * 
		 * @return ServiceRequest
		 */
		public ServiceRequest getIn0()
		{
			return localIn0;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            In0
		 */
		public void setIn0(ServiceRequest param)
		{

			this.localIn0 = param;

		}

		/**
		 * isReaderMTOMAware
		 * 
		 * @return true if the reader supports MTOM
		 */
		public static boolean isReaderMTOMAware(javax.xml.stream.XMLStreamReader reader)
		{
			boolean isReaderMTOMAware = false;

			try
			{
				isReaderMTOMAware = java.lang.Boolean.TRUE.equals(reader.getProperty(org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
			}
			catch (java.lang.IllegalArgumentException e)
			{
				isReaderMTOMAware = false;
			}
			return isReaderMTOMAware;
		}

		/**
		 * 
		 * @param parentQName
		 * @param factory
		 * @return org.apache.axiom.om.OMElement
		 */
		public org.apache.axiom.om.OMElement getOMElement(
				final javax.xml.namespace.QName parentQName,
				final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException
		{

			org.apache.axiom.om.OMDataSource dataSource =
						new org.apache.axis2.databinding.ADBDataSource(this, MY_QNAME)
						{

							public void serialize(org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
									throws javax.xml.stream.XMLStreamException
							{
								GetStatus.this.serialize(MY_QNAME, factory, xmlWriter);
							}
						};
			return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(
					MY_QNAME, factory, dataSource);

		}

		public void serialize(final javax.xml.namespace.QName parentQName,
										final org.apache.axiom.om.OMFactory factory,
										org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
								throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException
		{
			serialize(parentQName, factory, xmlWriter, false);
		}

		public void serialize(final javax.xml.namespace.QName parentQName,
								final org.apache.axiom.om.OMFactory factory,
								org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter,
								boolean serializeType)
				throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException
		{

			java.lang.String prefix = null;
			java.lang.String namespace = null;

			prefix = parentQName.getPrefix();
			namespace = parentQName.getNamespaceURI();

			if ((namespace != null) && (namespace.trim().length() > 0))
			{
				java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
				if (writerPrefix != null)
				{
					xmlWriter.writeStartElement(namespace, parentQName.getLocalPart());
				}
				else
				{
					if (prefix == null)
					{
						prefix = generatePrefix(namespace);
					}

					xmlWriter.writeStartElement(prefix, parentQName.getLocalPart(), namespace);
					xmlWriter.writeNamespace(prefix, namespace);
					xmlWriter.setPrefix(prefix, namespace);
				}
			}
			else
			{
				xmlWriter.writeStartElement(parentQName.getLocalPart());
			}

			if (serializeType)
			{

				java.lang.String namespacePrefix = registerPrefix(xmlWriter, "http://service.crm.com");
				if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0))
				{
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type",
							namespacePrefix + ":getStatus",
							xmlWriter);
				}
				else
				{
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type",
							"getStatus",
							xmlWriter);
				}

			}

			if (localIn0 == null)
			{

				java.lang.String namespace2 = "http://service.crm.com";

				if (!namespace2.equals(""))
				{
					java.lang.String prefix2 = xmlWriter.getPrefix(namespace2);

					if (prefix2 == null)
					{
						prefix2 = generatePrefix(namespace2);

						xmlWriter.writeStartElement(prefix2, "in0", namespace2);
						xmlWriter.writeNamespace(prefix2, namespace2);
						xmlWriter.setPrefix(prefix2, namespace2);

					}
					else
					{
						xmlWriter.writeStartElement(namespace2, "in0");
					}

				}
				else
				{
					xmlWriter.writeStartElement("in0");
				}

				// write the nil attribute
				writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);
				xmlWriter.writeEndElement();
			}
			else
			{
				localIn0.serialize(new javax.xml.namespace.QName("http://service.crm.com", "in0"),
										factory, xmlWriter);
			}

			xmlWriter.writeEndElement();

		}

		/**
		 * Util method to write an attribute with the ns prefix
		 */
		private void writeAttribute(java.lang.String prefix, java.lang.String namespace, java.lang.String attName,
										java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException
		{
			if (xmlWriter.getPrefix(namespace) == null)
			{
				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);

			}

			xmlWriter.writeAttribute(namespace, attName, attValue);

		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeAttribute(java.lang.String namespace, java.lang.String attName,
										java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException
		{
			if (namespace.equals(""))
			{
				xmlWriter.writeAttribute(attName, attValue);
			}
			else
			{
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attValue);
			}
		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
												javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException
		{

			java.lang.String attributeNamespace = qname.getNamespaceURI();
			java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
			if (attributePrefix == null)
			{
				attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
			}
			java.lang.String attributeValue;
			if (attributePrefix.trim().length() > 0)
			{
				attributeValue = attributePrefix + ":" + qname.getLocalPart();
			}
			else
			{
				attributeValue = qname.getLocalPart();
			}

			if (namespace.equals(""))
			{
				xmlWriter.writeAttribute(attName, attributeValue);
			}
			else
			{
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attributeValue);
			}
		}

		/**
		 * method to handle Qnames
		 */

		private void writeQName(javax.xml.namespace.QName qname,
								javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException
		{
			java.lang.String namespaceURI = qname.getNamespaceURI();
			if (namespaceURI != null)
			{
				java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
				if (prefix == null)
				{
					prefix = generatePrefix(namespaceURI);
					xmlWriter.writeNamespace(prefix, namespaceURI);
					xmlWriter.setPrefix(prefix, namespaceURI);
				}

				if (prefix.trim().length() > 0)
				{
					xmlWriter.writeCharacters(prefix + ":" + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				}
				else
				{
					// i.e this is the default namespace
					xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				}

			}
			else
			{
				xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
			}
		}

		private void writeQNames(javax.xml.namespace.QName[] qnames,
									javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException
		{

			if (qnames != null)
			{
				// we have to store this data until last moment since it is not
				// possible to write any
				// namespace data after writing the charactor data
				java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
				java.lang.String namespaceURI = null;
				java.lang.String prefix = null;

				for (int i = 0; i < qnames.length; i++)
				{
					if (i > 0)
					{
						stringToWrite.append(" ");
					}
					namespaceURI = qnames[i].getNamespaceURI();
					if (namespaceURI != null)
					{
						prefix = xmlWriter.getPrefix(namespaceURI);
						if ((prefix == null) || (prefix.length() == 0))
						{
							prefix = generatePrefix(namespaceURI);
							xmlWriter.writeNamespace(prefix, namespaceURI);
							xmlWriter.setPrefix(prefix, namespaceURI);
						}

						if (prefix.trim().length() > 0)
						{
							stringToWrite.append(prefix).append(":")
									.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
						}
						else
						{
							stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
						}
					}
					else
					{
						stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
					}
				}
				xmlWriter.writeCharacters(stringToWrite.toString());
			}

		}

		/**
		 * Register a namespace prefix
		 */
		private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace)
				throws javax.xml.stream.XMLStreamException
		{
			java.lang.String prefix = xmlWriter.getPrefix(namespace);

			if (prefix == null)
			{
				prefix = generatePrefix(namespace);

				while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null)
				{
					prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
				}

				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);
			}

			return prefix;
		}

		/**
		 * databinding method to get an XML representation of this object
		 * 
		 */
		public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
					throws org.apache.axis2.databinding.ADBException
		{

			java.util.ArrayList elementList = new java.util.ArrayList();
			java.util.ArrayList attribList = new java.util.ArrayList();

			elementList.add(new javax.xml.namespace.QName("http://service.crm.com",
																		"in0"));

			elementList.add(localIn0 == null ? null :
									localIn0);

			return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(), attribList.toArray());

		}

		/**
		 * Factory class that keeps the parse method
		 */
		public static class Factory
		{

			/**
			 * static method to create the object Precondition: If this object
			 * is an element, the current or next start element starts this
			 * object and any intervening reader events are ignorable If this
			 * object is not an element, it is a complex type and the reader is
			 * at the event just after the outer start element Postcondition: If
			 * this object is an element, the reader is positioned at its end
			 * element If this object is a complex type, the reader is
			 * positioned at the end element of its outer element
			 */
			public static GetStatus parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception
			{
				GetStatus object =
						new GetStatus();

				int event;
				java.lang.String nillableValue = null;
				java.lang.String prefix = "";
				java.lang.String namespaceuri = "";
				try
				{

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type") != null)
					{
						java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
								"type");
						if (fullTypeName != null)
						{
							java.lang.String nsPrefix = null;
							if (fullTypeName.indexOf(":") > -1)
							{
								nsPrefix = fullTypeName.substring(0, fullTypeName.indexOf(":"));
							}
							nsPrefix = nsPrefix == null ? "" : nsPrefix;

							java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":") + 1);

							if (!"getStatus".equals(type))
							{
								// find namespace for the prefix
								java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
								return (GetStatus) ExtensionMapper.getTypeObject(
										nsUri, type, reader);
							}

						}

					}

					// Note all attributes that were handled. Used to differ
					// normal attributes
					// from anyAttributes.
					java.util.Vector handledAttributes = new java.util.Vector();

					reader.next();

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("http://service.crm.com", "in0").equals(reader.getName()))
					{

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if ("true".equals(nillableValue) || "1".equals(nillableValue))
						{
							object.setIn0(null);
							reader.next();

							reader.next();

						}
						else
						{

							object.setIn0(ServiceRequest.Factory.parse(reader));

							reader.next();
						}
					} // End of if for expected property start element

					else
					{
						// A start element we are not expecting indicates an
						// invalid parameter was passed
						throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement())
						// A start element we are not expecting indicates a
						// trailing invalid property
						throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());

				}
				catch (javax.xml.stream.XMLStreamException e)
				{
					throw new java.lang.Exception(e);
				}

				return object;
			}

		}// end of factory class

	}

	public static class GetStatusResponse
			implements org.apache.axis2.databinding.ADBBean
	{

		public static final javax.xml.namespace.QName	MY_QNAME	= new javax.xml.namespace.QName(
																			"http://service.crm.com",
																			"getStatusResponse",
																			"ns2");

		private static java.lang.String generatePrefix(java.lang.String namespace)
		{
			if (namespace.equals("http://service.crm.com"))
			{
				return "ns2";
			}
			return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
		}

		/**
		 * field for Out
		 */

		protected ServiceResponse	localOut;

		/**
		 * Auto generated getter method
		 * 
		 * @return ServiceResponse
		 */
		public ServiceResponse getOut()
		{
			return localOut;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            Out
		 */
		public void setOut(ServiceResponse param)
		{

			this.localOut = param;

		}

		/**
		 * isReaderMTOMAware
		 * 
		 * @return true if the reader supports MTOM
		 */
		public static boolean isReaderMTOMAware(javax.xml.stream.XMLStreamReader reader)
		{
			boolean isReaderMTOMAware = false;

			try
			{
				isReaderMTOMAware = java.lang.Boolean.TRUE.equals(reader.getProperty(org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
			}
			catch (java.lang.IllegalArgumentException e)
			{
				isReaderMTOMAware = false;
			}
			return isReaderMTOMAware;
		}

		/**
		 * 
		 * @param parentQName
		 * @param factory
		 * @return org.apache.axiom.om.OMElement
		 */
		public org.apache.axiom.om.OMElement getOMElement(
				final javax.xml.namespace.QName parentQName,
				final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException
		{

			org.apache.axiom.om.OMDataSource dataSource =
						new org.apache.axis2.databinding.ADBDataSource(this, MY_QNAME)
						{

							public void serialize(org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
									throws javax.xml.stream.XMLStreamException
							{
								GetStatusResponse.this.serialize(MY_QNAME, factory, xmlWriter);
							}
						};
			return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(
					MY_QNAME, factory, dataSource);

		}

		public void serialize(final javax.xml.namespace.QName parentQName,
										final org.apache.axiom.om.OMFactory factory,
										org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
								throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException
		{
			serialize(parentQName, factory, xmlWriter, false);
		}

		public void serialize(final javax.xml.namespace.QName parentQName,
								final org.apache.axiom.om.OMFactory factory,
								org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter,
								boolean serializeType)
				throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException
		{

			java.lang.String prefix = null;
			java.lang.String namespace = null;

			prefix = parentQName.getPrefix();
			namespace = parentQName.getNamespaceURI();

			if ((namespace != null) && (namespace.trim().length() > 0))
			{
				java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
				if (writerPrefix != null)
				{
					xmlWriter.writeStartElement(namespace, parentQName.getLocalPart());
				}
				else
				{
					if (prefix == null)
					{
						prefix = generatePrefix(namespace);
					}

					xmlWriter.writeStartElement(prefix, parentQName.getLocalPart(), namespace);
					xmlWriter.writeNamespace(prefix, namespace);
					xmlWriter.setPrefix(prefix, namespace);
				}
			}
			else
			{
				xmlWriter.writeStartElement(parentQName.getLocalPart());
			}

			if (serializeType)
			{

				java.lang.String namespacePrefix = registerPrefix(xmlWriter, "http://service.crm.com");
				if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0))
				{
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type",
							namespacePrefix + ":getStatusResponse",
							xmlWriter);
				}
				else
				{
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type",
							"getStatusResponse",
							xmlWriter);
				}

			}

			if (localOut == null)
			{

				java.lang.String namespace2 = "http://service.crm.com";

				if (!namespace2.equals(""))
				{
					java.lang.String prefix2 = xmlWriter.getPrefix(namespace2);

					if (prefix2 == null)
					{
						prefix2 = generatePrefix(namespace2);

						xmlWriter.writeStartElement(prefix2, "out", namespace2);
						xmlWriter.writeNamespace(prefix2, namespace2);
						xmlWriter.setPrefix(prefix2, namespace2);

					}
					else
					{
						xmlWriter.writeStartElement(namespace2, "out");
					}

				}
				else
				{
					xmlWriter.writeStartElement("out");
				}

				// write the nil attribute
				writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);
				xmlWriter.writeEndElement();
			}
			else
			{
				localOut.serialize(new javax.xml.namespace.QName("http://service.crm.com", "out"),
										factory, xmlWriter);
			}

			xmlWriter.writeEndElement();

		}

		/**
		 * Util method to write an attribute with the ns prefix
		 */
		private void writeAttribute(java.lang.String prefix, java.lang.String namespace, java.lang.String attName,
										java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException
		{
			if (xmlWriter.getPrefix(namespace) == null)
			{
				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);

			}

			xmlWriter.writeAttribute(namespace, attName, attValue);

		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeAttribute(java.lang.String namespace, java.lang.String attName,
										java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException
		{
			if (namespace.equals(""))
			{
				xmlWriter.writeAttribute(attName, attValue);
			}
			else
			{
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attValue);
			}
		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
												javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException
		{

			java.lang.String attributeNamespace = qname.getNamespaceURI();
			java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
			if (attributePrefix == null)
			{
				attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
			}
			java.lang.String attributeValue;
			if (attributePrefix.trim().length() > 0)
			{
				attributeValue = attributePrefix + ":" + qname.getLocalPart();
			}
			else
			{
				attributeValue = qname.getLocalPart();
			}

			if (namespace.equals(""))
			{
				xmlWriter.writeAttribute(attName, attributeValue);
			}
			else
			{
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attributeValue);
			}
		}

		/**
		 * method to handle Qnames
		 */

		private void writeQName(javax.xml.namespace.QName qname,
								javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException
		{
			java.lang.String namespaceURI = qname.getNamespaceURI();
			if (namespaceURI != null)
			{
				java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
				if (prefix == null)
				{
					prefix = generatePrefix(namespaceURI);
					xmlWriter.writeNamespace(prefix, namespaceURI);
					xmlWriter.setPrefix(prefix, namespaceURI);
				}

				if (prefix.trim().length() > 0)
				{
					xmlWriter.writeCharacters(prefix + ":" + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				}
				else
				{
					// i.e this is the default namespace
					xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				}

			}
			else
			{
				xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
			}
		}

		private void writeQNames(javax.xml.namespace.QName[] qnames,
									javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException
		{

			if (qnames != null)
			{
				// we have to store this data until last moment since it is not
				// possible to write any
				// namespace data after writing the charactor data
				java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
				java.lang.String namespaceURI = null;
				java.lang.String prefix = null;

				for (int i = 0; i < qnames.length; i++)
				{
					if (i > 0)
					{
						stringToWrite.append(" ");
					}
					namespaceURI = qnames[i].getNamespaceURI();
					if (namespaceURI != null)
					{
						prefix = xmlWriter.getPrefix(namespaceURI);
						if ((prefix == null) || (prefix.length() == 0))
						{
							prefix = generatePrefix(namespaceURI);
							xmlWriter.writeNamespace(prefix, namespaceURI);
							xmlWriter.setPrefix(prefix, namespaceURI);
						}

						if (prefix.trim().length() > 0)
						{
							stringToWrite.append(prefix).append(":")
									.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
						}
						else
						{
							stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
						}
					}
					else
					{
						stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
					}
				}
				xmlWriter.writeCharacters(stringToWrite.toString());
			}

		}

		/**
		 * Register a namespace prefix
		 */
		private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace)
				throws javax.xml.stream.XMLStreamException
		{
			java.lang.String prefix = xmlWriter.getPrefix(namespace);

			if (prefix == null)
			{
				prefix = generatePrefix(namespace);

				while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null)
				{
					prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
				}

				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);
			}

			return prefix;
		}

		/**
		 * databinding method to get an XML representation of this object
		 * 
		 */
		public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
					throws org.apache.axis2.databinding.ADBException
		{

			java.util.ArrayList elementList = new java.util.ArrayList();
			java.util.ArrayList attribList = new java.util.ArrayList();

			elementList.add(new javax.xml.namespace.QName("http://service.crm.com",
																		"out"));

			elementList.add(localOut == null ? null :
									localOut);

			return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(), attribList.toArray());

		}

		/**
		 * Factory class that keeps the parse method
		 */
		public static class Factory
		{

			/**
			 * static method to create the object Precondition: If this object
			 * is an element, the current or next start element starts this
			 * object and any intervening reader events are ignorable If this
			 * object is not an element, it is a complex type and the reader is
			 * at the event just after the outer start element Postcondition: If
			 * this object is an element, the reader is positioned at its end
			 * element If this object is a complex type, the reader is
			 * positioned at the end element of its outer element
			 */
			public static GetStatusResponse parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception
			{
				GetStatusResponse object =
						new GetStatusResponse();

				int event;
				java.lang.String nillableValue = null;
				java.lang.String prefix = "";
				java.lang.String namespaceuri = "";
				try
				{

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type") != null)
					{
						java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
								"type");
						if (fullTypeName != null)
						{
							java.lang.String nsPrefix = null;
							if (fullTypeName.indexOf(":") > -1)
							{
								nsPrefix = fullTypeName.substring(0, fullTypeName.indexOf(":"));
							}
							nsPrefix = nsPrefix == null ? "" : nsPrefix;

							java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":") + 1);

							if (!"getStatusResponse".equals(type))
							{
								// find namespace for the prefix
								java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
								return (GetStatusResponse) ExtensionMapper.getTypeObject(
										nsUri, type, reader);
							}

						}

					}

					// Note all attributes that were handled. Used to differ
					// normal attributes
					// from anyAttributes.
					java.util.Vector handledAttributes = new java.util.Vector();

					reader.next();

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("http://service.crm.com", "out").equals(reader.getName()))
					{

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if ("true".equals(nillableValue) || "1".equals(nillableValue))
						{
							object.setOut(null);
							reader.next();

							reader.next();

						}
						else
						{

							object.setOut(ServiceResponse.Factory.parse(reader));

							reader.next();
						}
					} // End of if for expected property start element

					else
					{
						// A start element we are not expecting indicates an
						// invalid parameter was passed
						throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement())
						// A start element we are not expecting indicates a
						// trailing invalid property
						throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());

				}
				catch (javax.xml.stream.XMLStreamException e)
				{
					throw new java.lang.Exception(e);
				}

				return object;
			}

		}// end of factory class

	}

	public static class DeliveryResponse
			implements org.apache.axis2.databinding.ADBBean
	{
		/*
		 * This type was generated from the piece of schema that had name =
		 * DeliveryResponse Namespace URI = http://model.gateway.com Namespace
		 * Prefix = ns1
		 */

		private static java.lang.String generatePrefix(java.lang.String namespace)
		{
			if (namespace.equals("http://model.gateway.com"))
			{
				return "ns1";
			}
			return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
		}

		/**
		 * field for Amount
		 */

		protected double	localAmount;

		/*
		 * This tracker boolean wil be used to detect whether the user called
		 * the set method for this attribute. It will be used to determine
		 * whether to include this field in the serialized XML
		 */
		protected boolean	localAmountTracker	= false;

		/**
		 * Auto generated getter method
		 * 
		 * @return double
		 */
		public double getAmount()
		{
			return localAmount;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            Amount
		 */
		public void setAmount(double param)
		{

			// setting primitive attribute tracker to true

			if (java.lang.Double.isNaN(param))
			{
				localAmountTracker = false;

			}
			else
			{
				localAmountTracker = true;
			}

			this.localAmount = param;

		}

		/**
		 * field for Cause
		 */

		protected java.lang.String	localCause;

		/*
		 * This tracker boolean wil be used to detect whether the user called
		 * the set method for this attribute. It will be used to determine
		 * whether to include this field in the serialized XML
		 */
		protected boolean			localCauseTracker	= false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getCause()
		{
			return localCause;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            Cause
		 */
		public void setCause(java.lang.String param)
		{

			if (param != null)
			{
				// update the setting tracker
				localCauseTracker = true;
			}
			else
			{
				localCauseTracker = true;

			}

			this.localCause = param;

		}

		/**
		 * field for Description
		 */

		protected java.lang.String	localDescription;

		/*
		 * This tracker boolean wil be used to detect whether the user called
		 * the set method for this attribute. It will be used to determine
		 * whether to include this field in the serialized XML
		 */
		protected boolean			localDescriptionTracker	= false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getDescription()
		{
			return localDescription;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            Description
		 */
		public void setDescription(java.lang.String param)
		{

			if (param != null)
			{
				// update the setting tracker
				localDescriptionTracker = true;
			}
			else
			{
				localDescriptionTracker = true;

			}

			this.localDescription = param;

		}

		/**
		 * field for Isdn
		 */

		protected java.lang.String	localIsdn;

		/*
		 * This tracker boolean wil be used to detect whether the user called
		 * the set method for this attribute. It will be used to determine
		 * whether to include this field in the serialized XML
		 */
		protected boolean			localIsdnTracker	= false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getIsdn()
		{
			return localIsdn;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            Isdn
		 */
		public void setIsdn(java.lang.String param)
		{

			if (param != null)
			{
				// update the setting tracker
				localIsdnTracker = true;
			}
			else
			{
				localIsdnTracker = true;

			}

			this.localIsdn = param;

		}

		/**
		 * field for NumOfSms
		 */

		protected int		localNumOfSms;

		/*
		 * This tracker boolean wil be used to detect whether the user called
		 * the set method for this attribute. It will be used to determine
		 * whether to include this field in the serialized XML
		 */
		protected boolean	localNumOfSmsTracker	= false;

		/**
		 * Auto generated getter method
		 * 
		 * @return int
		 */
		public int getNumOfSms()
		{
			return localNumOfSms;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            NumOfSms
		 */
		public void setNumOfSms(int param)
		{

			// setting primitive attribute tracker to true

			if (param == java.lang.Integer.MIN_VALUE)
			{
				localNumOfSmsTracker = false;

			}
			else
			{
				localNumOfSmsTracker = true;
			}

			this.localNumOfSms = param;

		}

		/**
		 * field for OrderDate
		 */

		protected java.util.Calendar	localOrderDate;

		/*
		 * This tracker boolean wil be used to detect whether the user called
		 * the set method for this attribute. It will be used to determine
		 * whether to include this field in the serialized XML
		 */
		protected boolean				localOrderDateTracker	= false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.util.Calendar
		 */
		public java.util.Calendar getOrderDate()
		{
			return localOrderDate;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            OrderDate
		 */
		public void setOrderDate(java.util.Calendar param)
		{

			if (param != null)
			{
				// update the setting tracker
				localOrderDateTracker = true;
			}
			else
			{
				localOrderDateTracker = false;

			}

			this.localOrderDate = param;

		}

		/**
		 * field for OrderId
		 */

		protected long		localOrderId;

		/*
		 * This tracker boolean wil be used to detect whether the user called
		 * the set method for this attribute. It will be used to determine
		 * whether to include this field in the serialized XML
		 */
		protected boolean	localOrderIdTracker	= false;

		/**
		 * Auto generated getter method
		 * 
		 * @return long
		 */
		public long getOrderId()
		{
			return localOrderId;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            OrderId
		 */
		public void setOrderId(long param)
		{

			// setting primitive attribute tracker to true

			if (param == java.lang.Long.MIN_VALUE)
			{
				localOrderIdTracker = false;

			}
			else
			{
				localOrderIdTracker = true;
			}

			this.localOrderId = param;

		}

		/**
		 * field for OrderNo
		 */

		protected java.lang.String	localOrderNo;

		/*
		 * This tracker boolean wil be used to detect whether the user called
		 * the set method for this attribute. It will be used to determine
		 * whether to include this field in the serialized XML
		 */
		protected boolean			localOrderNoTracker	= false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getOrderNo()
		{
			return localOrderNo;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            OrderNo
		 */
		public void setOrderNo(java.lang.String param)
		{

			if (param != null)
			{
				// update the setting tracker
				localOrderNoTracker = true;
			}
			else
			{
				localOrderNoTracker = true;

			}

			this.localOrderNo = param;

		}

		/**
		 * field for Result
		 */

		protected int		localResult;

		/*
		 * This tracker boolean wil be used to detect whether the user called
		 * the set method for this attribute. It will be used to determine
		 * whether to include this field in the serialized XML
		 */
		protected boolean	localResultTracker	= false;

		/**
		 * Auto generated getter method
		 * 
		 * @return int
		 */
		public int getResult()
		{
			return localResult;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            Result
		 */
		public void setResult(int param)
		{

			// setting primitive attribute tracker to true

			if (param == java.lang.Integer.MIN_VALUE)
			{
				localResultTracker = false;

			}
			else
			{
				localResultTracker = true;
			}

			this.localResult = param;

		}

		/**
		 * isReaderMTOMAware
		 * 
		 * @return true if the reader supports MTOM
		 */
		public static boolean isReaderMTOMAware(javax.xml.stream.XMLStreamReader reader)
		{
			boolean isReaderMTOMAware = false;

			try
			{
				isReaderMTOMAware = java.lang.Boolean.TRUE.equals(reader.getProperty(org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
			}
			catch (java.lang.IllegalArgumentException e)
			{
				isReaderMTOMAware = false;
			}
			return isReaderMTOMAware;
		}

		/**
		 * 
		 * @param parentQName
		 * @param factory
		 * @return org.apache.axiom.om.OMElement
		 */
		public org.apache.axiom.om.OMElement getOMElement(
				final javax.xml.namespace.QName parentQName,
				final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException
		{

			org.apache.axiom.om.OMDataSource dataSource =
						new org.apache.axis2.databinding.ADBDataSource(this, parentQName)
						{

							public void serialize(org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
									throws javax.xml.stream.XMLStreamException
							{
								DeliveryResponse.this.serialize(parentQName, factory, xmlWriter);
							}
						};
			return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(
					parentQName, factory, dataSource);

		}

		public void serialize(final javax.xml.namespace.QName parentQName,
										final org.apache.axiom.om.OMFactory factory,
										org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
								throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException
		{
			serialize(parentQName, factory, xmlWriter, false);
		}

		public void serialize(final javax.xml.namespace.QName parentQName,
								final org.apache.axiom.om.OMFactory factory,
								org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter,
								boolean serializeType)
				throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException
		{

			java.lang.String prefix = null;
			java.lang.String namespace = null;

			prefix = parentQName.getPrefix();
			namespace = parentQName.getNamespaceURI();

			if ((namespace != null) && (namespace.trim().length() > 0))
			{
				java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
				if (writerPrefix != null)
				{
					xmlWriter.writeStartElement(namespace, parentQName.getLocalPart());
				}
				else
				{
					if (prefix == null)
					{
						prefix = generatePrefix(namespace);
					}

					xmlWriter.writeStartElement(prefix, parentQName.getLocalPart(), namespace);
					xmlWriter.writeNamespace(prefix, namespace);
					xmlWriter.setPrefix(prefix, namespace);
				}
			}
			else
			{
				xmlWriter.writeStartElement(parentQName.getLocalPart());
			}

			if (serializeType)
			{

				java.lang.String namespacePrefix = registerPrefix(xmlWriter, "http://model.gateway.com");
				if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0))
				{
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type",
							namespacePrefix + ":DeliveryResponse",
							xmlWriter);
				}
				else
				{
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type",
							"DeliveryResponse",
							xmlWriter);
				}

			}
			if (localAmountTracker)
			{
				namespace = "http://model.gateway.com";
				if (!namespace.equals(""))
				{
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null)
					{
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "amount", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					}
					else
					{
						xmlWriter.writeStartElement(namespace, "amount");
					}

				}
				else
				{
					xmlWriter.writeStartElement("amount");
				}

				if (java.lang.Double.isNaN(localAmount))
				{

					throw new org.apache.axis2.databinding.ADBException("amount cannot be null!!");

				}
				else
				{
					xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localAmount));
				}

				xmlWriter.writeEndElement();
			}
			if (localCauseTracker)
			{
				namespace = "http://model.gateway.com";
				if (!namespace.equals(""))
				{
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null)
					{
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "cause", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					}
					else
					{
						xmlWriter.writeStartElement(namespace, "cause");
					}

				}
				else
				{
					xmlWriter.writeStartElement("cause");
				}

				if (localCause == null)
				{
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				}
				else
				{

					xmlWriter.writeCharacters(localCause);

				}

				xmlWriter.writeEndElement();
			}
			if (localDescriptionTracker)
			{
				namespace = "http://model.gateway.com";
				if (!namespace.equals(""))
				{
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null)
					{
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "description", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					}
					else
					{
						xmlWriter.writeStartElement(namespace, "description");
					}

				}
				else
				{
					xmlWriter.writeStartElement("description");
				}

				if (localDescription == null)
				{
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				}
				else
				{

					xmlWriter.writeCharacters(localDescription);

				}

				xmlWriter.writeEndElement();
			}
			if (localIsdnTracker)
			{
				namespace = "http://model.gateway.com";
				if (!namespace.equals(""))
				{
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null)
					{
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "isdn", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					}
					else
					{
						xmlWriter.writeStartElement(namespace, "isdn");
					}

				}
				else
				{
					xmlWriter.writeStartElement("isdn");
				}

				if (localIsdn == null)
				{
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				}
				else
				{

					xmlWriter.writeCharacters(localIsdn);

				}

				xmlWriter.writeEndElement();
			}
			if (localNumOfSmsTracker)
			{
				namespace = "http://model.gateway.com";
				if (!namespace.equals(""))
				{
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null)
					{
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "numOfSms", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					}
					else
					{
						xmlWriter.writeStartElement(namespace, "numOfSms");
					}

				}
				else
				{
					xmlWriter.writeStartElement("numOfSms");
				}

				if (localNumOfSms == java.lang.Integer.MIN_VALUE)
				{

					throw new org.apache.axis2.databinding.ADBException("numOfSms cannot be null!!");

				}
				else
				{
					xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localNumOfSms));
				}

				xmlWriter.writeEndElement();
			}
			if (localOrderDateTracker)
			{
				namespace = "http://model.gateway.com";
				if (!namespace.equals(""))
				{
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null)
					{
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "orderDate", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					}
					else
					{
						xmlWriter.writeStartElement(namespace, "orderDate");
					}

				}
				else
				{
					xmlWriter.writeStartElement("orderDate");
				}

				if (localOrderDate == null)
				{
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("orderDate cannot be null!!");

				}
				else
				{

					xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localOrderDate));

				}

				xmlWriter.writeEndElement();
			}
			if (localOrderIdTracker)
			{
				namespace = "http://model.gateway.com";
				if (!namespace.equals(""))
				{
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null)
					{
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "orderId", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					}
					else
					{
						xmlWriter.writeStartElement(namespace, "orderId");
					}

				}
				else
				{
					xmlWriter.writeStartElement("orderId");
				}

				if (localOrderId == java.lang.Long.MIN_VALUE)
				{

					throw new org.apache.axis2.databinding.ADBException("orderId cannot be null!!");

				}
				else
				{
					xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localOrderId));
				}

				xmlWriter.writeEndElement();
			}
			if (localOrderNoTracker)
			{
				namespace = "http://model.gateway.com";
				if (!namespace.equals(""))
				{
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null)
					{
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "orderNo", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					}
					else
					{
						xmlWriter.writeStartElement(namespace, "orderNo");
					}

				}
				else
				{
					xmlWriter.writeStartElement("orderNo");
				}

				if (localOrderNo == null)
				{
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				}
				else
				{

					xmlWriter.writeCharacters(localOrderNo);

				}

				xmlWriter.writeEndElement();
			}
			if (localResultTracker)
			{
				namespace = "http://model.gateway.com";
				if (!namespace.equals(""))
				{
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null)
					{
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "result", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					}
					else
					{
						xmlWriter.writeStartElement(namespace, "result");
					}

				}
				else
				{
					xmlWriter.writeStartElement("result");
				}

				if (localResult == java.lang.Integer.MIN_VALUE)
				{

					throw new org.apache.axis2.databinding.ADBException("result cannot be null!!");

				}
				else
				{
					xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localResult));
				}

				xmlWriter.writeEndElement();
			}
			xmlWriter.writeEndElement();

		}

		/**
		 * Util method to write an attribute with the ns prefix
		 */
		private void writeAttribute(java.lang.String prefix, java.lang.String namespace, java.lang.String attName,
										java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException
		{
			if (xmlWriter.getPrefix(namespace) == null)
			{
				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);

			}

			xmlWriter.writeAttribute(namespace, attName, attValue);

		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeAttribute(java.lang.String namespace, java.lang.String attName,
										java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException
		{
			if (namespace.equals(""))
			{
				xmlWriter.writeAttribute(attName, attValue);
			}
			else
			{
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attValue);
			}
		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
												javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException
		{

			java.lang.String attributeNamespace = qname.getNamespaceURI();
			java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
			if (attributePrefix == null)
			{
				attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
			}
			java.lang.String attributeValue;
			if (attributePrefix.trim().length() > 0)
			{
				attributeValue = attributePrefix + ":" + qname.getLocalPart();
			}
			else
			{
				attributeValue = qname.getLocalPart();
			}

			if (namespace.equals(""))
			{
				xmlWriter.writeAttribute(attName, attributeValue);
			}
			else
			{
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attributeValue);
			}
		}

		/**
		 * method to handle Qnames
		 */

		private void writeQName(javax.xml.namespace.QName qname,
								javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException
		{
			java.lang.String namespaceURI = qname.getNamespaceURI();
			if (namespaceURI != null)
			{
				java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
				if (prefix == null)
				{
					prefix = generatePrefix(namespaceURI);
					xmlWriter.writeNamespace(prefix, namespaceURI);
					xmlWriter.setPrefix(prefix, namespaceURI);
				}

				if (prefix.trim().length() > 0)
				{
					xmlWriter.writeCharacters(prefix + ":" + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				}
				else
				{
					// i.e this is the default namespace
					xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				}

			}
			else
			{
				xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
			}
		}

		private void writeQNames(javax.xml.namespace.QName[] qnames,
									javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException
		{

			if (qnames != null)
			{
				// we have to store this data until last moment since it is not
				// possible to write any
				// namespace data after writing the charactor data
				java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
				java.lang.String namespaceURI = null;
				java.lang.String prefix = null;

				for (int i = 0; i < qnames.length; i++)
				{
					if (i > 0)
					{
						stringToWrite.append(" ");
					}
					namespaceURI = qnames[i].getNamespaceURI();
					if (namespaceURI != null)
					{
						prefix = xmlWriter.getPrefix(namespaceURI);
						if ((prefix == null) || (prefix.length() == 0))
						{
							prefix = generatePrefix(namespaceURI);
							xmlWriter.writeNamespace(prefix, namespaceURI);
							xmlWriter.setPrefix(prefix, namespaceURI);
						}

						if (prefix.trim().length() > 0)
						{
							stringToWrite.append(prefix).append(":")
									.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
						}
						else
						{
							stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
						}
					}
					else
					{
						stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
					}
				}
				xmlWriter.writeCharacters(stringToWrite.toString());
			}

		}

		/**
		 * Register a namespace prefix
		 */
		private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace)
				throws javax.xml.stream.XMLStreamException
		{
			java.lang.String prefix = xmlWriter.getPrefix(namespace);

			if (prefix == null)
			{
				prefix = generatePrefix(namespace);

				while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null)
				{
					prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
				}

				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);
			}

			return prefix;
		}

		/**
		 * databinding method to get an XML representation of this object
		 * 
		 */
		public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
					throws org.apache.axis2.databinding.ADBException
		{

			java.util.ArrayList elementList = new java.util.ArrayList();
			java.util.ArrayList attribList = new java.util.ArrayList();

			if (localAmountTracker)
			{
				elementList.add(new javax.xml.namespace.QName("http://model.gateway.com",
																		"amount"));

				elementList.add(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localAmount));
			}
			if (localCauseTracker)
			{
				elementList.add(new javax.xml.namespace.QName("http://model.gateway.com",
																		"cause"));

				elementList.add(localCause == null ? null :
											org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCause));
			}
			if (localDescriptionTracker)
			{
				elementList.add(new javax.xml.namespace.QName("http://model.gateway.com",
																		"description"));

				elementList.add(localDescription == null ? null :
											org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localDescription));
			}
			if (localIsdnTracker)
			{
				elementList.add(new javax.xml.namespace.QName("http://model.gateway.com",
																		"isdn"));

				elementList.add(localIsdn == null ? null :
											org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localIsdn));
			}
			if (localNumOfSmsTracker)
			{
				elementList.add(new javax.xml.namespace.QName("http://model.gateway.com",
																		"numOfSms"));

				elementList.add(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localNumOfSms));
			}
			if (localOrderDateTracker)
			{
				elementList.add(new javax.xml.namespace.QName("http://model.gateway.com",
																		"orderDate"));

				if (localOrderDate != null)
				{
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localOrderDate));
				}
				else
				{
					throw new org.apache.axis2.databinding.ADBException("orderDate cannot be null!!");
				}
			}
			if (localOrderIdTracker)
			{
				elementList.add(new javax.xml.namespace.QName("http://model.gateway.com",
																		"orderId"));

				elementList.add(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localOrderId));
			}
			if (localOrderNoTracker)
			{
				elementList.add(new javax.xml.namespace.QName("http://model.gateway.com",
																		"orderNo"));

				elementList.add(localOrderNo == null ? null :
											org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localOrderNo));
			}
			if (localResultTracker)
			{
				elementList.add(new javax.xml.namespace.QName("http://model.gateway.com",
																		"result"));

				elementList.add(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localResult));
			}

			return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(), attribList.toArray());

		}

		/**
		 * Factory class that keeps the parse method
		 */
		public static class Factory
		{

			/**
			 * static method to create the object Precondition: If this object
			 * is an element, the current or next start element starts this
			 * object and any intervening reader events are ignorable If this
			 * object is not an element, it is a complex type and the reader is
			 * at the event just after the outer start element Postcondition: If
			 * this object is an element, the reader is positioned at its end
			 * element If this object is a complex type, the reader is
			 * positioned at the end element of its outer element
			 */
			public static DeliveryResponse parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception
			{
				DeliveryResponse object =
						new DeliveryResponse();

				int event;
				java.lang.String nillableValue = null;
				java.lang.String prefix = "";
				java.lang.String namespaceuri = "";
				try
				{

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type") != null)
					{
						java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
								"type");
						if (fullTypeName != null)
						{
							java.lang.String nsPrefix = null;
							if (fullTypeName.indexOf(":") > -1)
							{
								nsPrefix = fullTypeName.substring(0, fullTypeName.indexOf(":"));
							}
							nsPrefix = nsPrefix == null ? "" : nsPrefix;

							java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":") + 1);

							if (!"DeliveryResponse".equals(type))
							{
								// find namespace for the prefix
								java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
								return (DeliveryResponse) ExtensionMapper.getTypeObject(
										nsUri, type, reader);
							}

						}

					}

					// Note all attributes that were handled. Used to differ
					// normal attributes
					// from anyAttributes.
					java.util.Vector handledAttributes = new java.util.Vector();

					reader.next();

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("http://model.gateway.com", "amount").equals(reader.getName()))
					{

						java.lang.String content = reader.getElementText();

						object.setAmount(
													org.apache.axis2.databinding.utils.ConverterUtil.convertToDouble(content));

						reader.next();

					} // End of if for expected property start element

					else
					{

						object.setAmount(java.lang.Double.NaN);

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("http://model.gateway.com", "cause").equals(reader.getName()))
					{

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue))
						{

							java.lang.String content = reader.getElementText();

							object.setCause(
													org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						}
						else
						{

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else
					{

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("http://model.gateway.com", "description").equals(reader.getName()))
					{

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue))
						{

							java.lang.String content = reader.getElementText();

							object.setDescription(
													org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						}
						else
						{

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else
					{

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("http://model.gateway.com", "isdn").equals(reader.getName()))
					{

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue))
						{

							java.lang.String content = reader.getElementText();

							object.setIsdn(
													org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						}
						else
						{

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else
					{

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("http://model.gateway.com", "numOfSms").equals(reader.getName()))
					{

						java.lang.String content = reader.getElementText();

						object.setNumOfSms(
													org.apache.axis2.databinding.utils.ConverterUtil.convertToInt(content));

						reader.next();

					} // End of if for expected property start element

					else
					{

						object.setNumOfSms(java.lang.Integer.MIN_VALUE);

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("http://model.gateway.com", "orderDate").equals(reader.getName()))
					{

						java.lang.String content = reader.getElementText();

						object.setOrderDate(
													org.apache.axis2.databinding.utils.ConverterUtil.convertToDateTime(content));

						reader.next();

					} // End of if for expected property start element

					else
					{

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("http://model.gateway.com", "orderId").equals(reader.getName()))
					{

						java.lang.String content = reader.getElementText();

						object.setOrderId(
													org.apache.axis2.databinding.utils.ConverterUtil.convertToLong(content));

						reader.next();

					} // End of if for expected property start element

					else
					{

						object.setOrderId(java.lang.Long.MIN_VALUE);

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("http://model.gateway.com", "orderNo").equals(reader.getName()))
					{

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue))
						{

							java.lang.String content = reader.getElementText();

							object.setOrderNo(
													org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						}
						else
						{

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else
					{

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("http://model.gateway.com", "result").equals(reader.getName()))
					{

						java.lang.String content = reader.getElementText();

						object.setResult(
													org.apache.axis2.databinding.utils.ConverterUtil.convertToInt(content));

						reader.next();

					} // End of if for expected property start element

					else
					{

						object.setResult(java.lang.Integer.MIN_VALUE);

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement())
						// A start element we are not expecting indicates a
						// trailing invalid property
						throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());

				}
				catch (javax.xml.stream.XMLStreamException e)
				{
					throw new java.lang.Exception(e);
				}

				return object;
			}

		}// end of factory class

	}

	private org.apache.axiom.om.OMElement toOM(vnm_old.MT_Charging_webserviceStub.Subscription param, boolean optimizeContent)
			throws org.apache.axis2.AxisFault
	{

		try
		{
			return param.getOMElement(vnm_old.MT_Charging_webserviceStub.Subscription.MY_QNAME,
											org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		}
		catch (org.apache.axis2.databinding.ADBException e)
		{
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(vnm_old.MT_Charging_webserviceStub.SubscriptionResponse param, boolean optimizeContent)
			throws org.apache.axis2.AxisFault
	{

		try
		{
			return param.getOMElement(vnm_old.MT_Charging_webserviceStub.SubscriptionResponse.MY_QNAME,
											org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		}
		catch (org.apache.axis2.databinding.ADBException e)
		{
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(vnm_old.MT_Charging_webserviceStub.GetStatus param, boolean optimizeContent)
			throws org.apache.axis2.AxisFault
	{

		try
		{
			return param.getOMElement(vnm_old.MT_Charging_webserviceStub.GetStatus.MY_QNAME,
											org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		}
		catch (org.apache.axis2.databinding.ADBException e)
		{
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(vnm_old.MT_Charging_webserviceStub.GetStatusResponse param, boolean optimizeContent)
			throws org.apache.axis2.AxisFault
	{

		try
		{
			return param.getOMElement(vnm_old.MT_Charging_webserviceStub.GetStatusResponse.MY_QNAME,
											org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		}
		catch (org.apache.axis2.databinding.ADBException e)
		{
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(vnm_old.MT_Charging_webserviceStub.Delivery param, boolean optimizeContent)
			throws org.apache.axis2.AxisFault
	{

		try
		{
			return param.getOMElement(vnm_old.MT_Charging_webserviceStub.Delivery.MY_QNAME,
											org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		}
		catch (org.apache.axis2.databinding.ADBException e)
		{
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(vnm_old.MT_Charging_webserviceStub.DeliveryResponseE param, boolean optimizeContent)
			throws org.apache.axis2.AxisFault
	{

		try
		{
			return param.getOMElement(vnm_old.MT_Charging_webserviceStub.DeliveryResponseE.MY_QNAME,
											org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		}
		catch (org.apache.axis2.databinding.ADBException e)
		{
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory,
			vnm_old.MT_Charging_webserviceStub.Subscription param, boolean optimizeContent)
										throws org.apache.axis2.AxisFault
	{

		try
		{

			org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
			emptyEnvelope.getBody().addChild(param.getOMElement(vnm_old.MT_Charging_webserviceStub.Subscription.MY_QNAME, factory));
			return emptyEnvelope;
		}
		catch (org.apache.axis2.databinding.ADBException e)
		{
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	/* methods to provide back word compatibility */

	private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, vnm_old.MT_Charging_webserviceStub.GetStatus param,
			boolean optimizeContent)
										throws org.apache.axis2.AxisFault
	{

		try
		{

			org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
			emptyEnvelope.getBody().addChild(param.getOMElement(vnm_old.MT_Charging_webserviceStub.GetStatus.MY_QNAME, factory));
			return emptyEnvelope;
		}
		catch (org.apache.axis2.databinding.ADBException e)
		{
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	/* methods to provide back word compatibility */

	private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, vnm_old.MT_Charging_webserviceStub.Delivery param,
			boolean optimizeContent)
										throws org.apache.axis2.AxisFault
	{

		try
		{

			org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
			emptyEnvelope.getBody().addChild(param.getOMElement(vnm_old.MT_Charging_webserviceStub.Delivery.MY_QNAME, factory));
			return emptyEnvelope;
		}
		catch (org.apache.axis2.databinding.ADBException e)
		{
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	/* methods to provide back word compatibility */

	/**
	 * get the default envelope
	 */
	private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory)
	{
		return factory.getDefaultEnvelope();
	}

	private java.lang.Object fromOM(
			org.apache.axiom.om.OMElement param,
			java.lang.Class type,
			java.util.Map extraNamespaces) throws org.apache.axis2.AxisFault
	{

		try
		{

			if (vnm_old.MT_Charging_webserviceStub.Subscription.class.equals(type))
			{

				return vnm_old.MT_Charging_webserviceStub.Subscription.Factory.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (vnm_old.MT_Charging_webserviceStub.SubscriptionResponse.class.equals(type))
			{

				return vnm_old.MT_Charging_webserviceStub.SubscriptionResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (vnm_old.MT_Charging_webserviceStub.GetStatus.class.equals(type))
			{

				return vnm_old.MT_Charging_webserviceStub.GetStatus.Factory.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (vnm_old.MT_Charging_webserviceStub.GetStatusResponse.class.equals(type))
			{

				return vnm_old.MT_Charging_webserviceStub.GetStatusResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (vnm_old.MT_Charging_webserviceStub.Delivery.class.equals(type))
			{

				return vnm_old.MT_Charging_webserviceStub.Delivery.Factory.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (vnm_old.MT_Charging_webserviceStub.DeliveryResponseE.class.equals(type))
			{

				return vnm_old.MT_Charging_webserviceStub.DeliveryResponseE.Factory.parse(param.getXMLStreamReaderWithoutCaching());

			}

		}
		catch (java.lang.Exception e)
		{
			throw org.apache.axis2.AxisFault.makeFault(e);
		}
		return null;
	}

}
