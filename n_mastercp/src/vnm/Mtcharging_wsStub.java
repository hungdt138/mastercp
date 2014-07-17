/**
 * Mtcharging_wsStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.4  Built on : Apr 26, 2008 (06:24:30 EDT)
 */
package vnm;

/*
 *  Mtcharging_wsStub java implementation
 */

public class Mtcharging_wsStub extends org.apache.axis2.client.Stub
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
		_service = new org.apache.axis2.description.AxisService("Mtcharging_ws" + getUniqueSuffix());
		addAnonymousOperations();

		// creating the operations
		org.apache.axis2.description.AxisOperation __operation;

		_operations = new org.apache.axis2.description.AxisOperation[5];

		__operation = new org.apache.axis2.description.OutInAxisOperation();

		__operation.setName(new javax.xml.namespace.QName("http://iwebservices.nms.com", "syncSubscriber"));
		_service.addOperation(__operation);

		_operations[0] = __operation;

		__operation = new org.apache.axis2.description.OutInAxisOperation();

		__operation.setName(new javax.xml.namespace.QName("http://iwebservices.nms.com", "sendSMS"));
		_service.addOperation(__operation);

		_operations[1] = __operation;

		__operation = new org.apache.axis2.description.OutInAxisOperation();

		__operation.setName(new javax.xml.namespace.QName("http://iwebservices.nms.com", "subscription"));
		_service.addOperation(__operation);

		_operations[2] = __operation;

		__operation = new org.apache.axis2.description.OutInAxisOperation();

		__operation.setName(new javax.xml.namespace.QName("http://iwebservices.nms.com", "sendWAP"));
		_service.addOperation(__operation);

		_operations[3] = __operation;

		__operation = new org.apache.axis2.description.OutInAxisOperation();

		__operation.setName(new javax.xml.namespace.QName("http://iwebservices.nms.com", "getStatus"));
		_service.addOperation(__operation);

		_operations[4] = __operation;

	}

	// populates the faults
	private void populateFaults()
	{

	}

	/**
	 * Constructor that takes in a configContext
	 */

	public Mtcharging_wsStub(org.apache.axis2.context.ConfigurationContext configurationContext,
			java.lang.String targetEndpoint)
			throws org.apache.axis2.AxisFault
	{
		this(configurationContext, targetEndpoint, false);
	}

	/**
	 * Constructor that takes in a configContext and useseperate listner
	 */
	public Mtcharging_wsStub(org.apache.axis2.context.ConfigurationContext configurationContext,
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
	public Mtcharging_wsStub(org.apache.axis2.context.ConfigurationContext configurationContext) throws org.apache.axis2.AxisFault
	{

		this(configurationContext, "http://203.128.246.91:8088/mtcharging/services/mtcharging_ws");

	}

	/**
	 * Default Constructor
	 */
	public Mtcharging_wsStub() throws org.apache.axis2.AxisFault
	{

		this("http://203.128.246.91:8088/mtcharging/services/mtcharging_ws");

	}

	/**
	 * Constructor taking the target endpoint
	 */
	public Mtcharging_wsStub(java.lang.String targetEndpoint) throws org.apache.axis2.AxisFault
	{
		this(null, targetEndpoint);
	}

	/**
	 * Auto generated method signature
	 * 
	 * @see vnm.Mtcharging_ws#syncSubscriber
	 * @param syncSubscriber0
	 */

	public vnm.Mtcharging_wsStub.SyncSubscriberResponse syncSubscriber(

			vnm.Mtcharging_wsStub.SyncSubscriber syncSubscriber0)

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
					syncSubscriber0,
					optimizeContent(new javax.xml.namespace.QName("http://iwebservices.nms.com",
							"syncSubscriber")));

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
					vnm.Mtcharging_wsStub.SyncSubscriberResponse.class,
					getEnvelopeNamespaces(_returnEnv));

			return (vnm.Mtcharging_wsStub.SyncSubscriberResponse) object;

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
	 * @see vnm.Mtcharging_ws#startsyncSubscriber
	 * @param syncSubscriber0
	 */
	public void startsyncSubscriber(

			vnm.Mtcharging_wsStub.SyncSubscriber syncSubscriber0,

			final vnm.Mtcharging_wsCallbackHandler callback)

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
				syncSubscriber0,
				optimizeContent(new javax.xml.namespace.QName("http://iwebservices.nms.com",
						"syncSubscriber")));

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
							vnm.Mtcharging_wsStub.SyncSubscriberResponse.class,
							getEnvelopeNamespaces(resultEnv));
					callback.receiveResultsyncSubscriber(
							(vnm.Mtcharging_wsStub.SyncSubscriberResponse) object);

				}
				catch (org.apache.axis2.AxisFault e)
				{
					callback.receiveErrorsyncSubscriber(e);
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

								callback.receiveErrorsyncSubscriber(new java.rmi.RemoteException(ex.getMessage(), ex));
							}
							catch (java.lang.ClassCastException e)
							{
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorsyncSubscriber(f);
							}
							catch (java.lang.ClassNotFoundException e)
							{
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorsyncSubscriber(f);
							}
							catch (java.lang.NoSuchMethodException e)
							{
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorsyncSubscriber(f);
							}
							catch (java.lang.reflect.InvocationTargetException e)
							{
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorsyncSubscriber(f);
							}
							catch (java.lang.IllegalAccessException e)
							{
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorsyncSubscriber(f);
							}
							catch (java.lang.InstantiationException e)
							{
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorsyncSubscriber(f);
							}
							catch (org.apache.axis2.AxisFault e)
							{
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorsyncSubscriber(f);
							}
						}
						else
						{
							callback.receiveErrorsyncSubscriber(f);
						}
					}
					else
					{
						callback.receiveErrorsyncSubscriber(f);
					}
				}
				else
				{
					callback.receiveErrorsyncSubscriber(error);
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
					callback.receiveErrorsyncSubscriber(axisFault);
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
	 * @see vnm.Mtcharging_ws#sendSMS
	 * @param sendSMS2
	 */

	public vnm.Mtcharging_wsStub.SendSMSResponseE sendSMS(

			vnm.Mtcharging_wsStub.SendSMS sendSMS2)

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
					sendSMS2,
					optimizeContent(new javax.xml.namespace.QName("http://iwebservices.nms.com",
							"sendSMS")));

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
					vnm.Mtcharging_wsStub.SendSMSResponseE.class,
					getEnvelopeNamespaces(_returnEnv));

			return (vnm.Mtcharging_wsStub.SendSMSResponseE) object;

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
	 * @see vnm.Mtcharging_ws#startsendSMS
	 * @param sendSMS2
	 */
	public void startsendSMS(

			vnm.Mtcharging_wsStub.SendSMS sendSMS2,

			final vnm.Mtcharging_wsCallbackHandler callback)

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
				sendSMS2,
				optimizeContent(new javax.xml.namespace.QName("http://iwebservices.nms.com",
						"sendSMS")));

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
							vnm.Mtcharging_wsStub.SendSMSResponseE.class,
							getEnvelopeNamespaces(resultEnv));
					callback.receiveResultsendSMS(
							(vnm.Mtcharging_wsStub.SendSMSResponseE) object);

				}
				catch (org.apache.axis2.AxisFault e)
				{
					callback.receiveErrorsendSMS(e);
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

								callback.receiveErrorsendSMS(new java.rmi.RemoteException(ex.getMessage(), ex));
							}
							catch (java.lang.ClassCastException e)
							{
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorsendSMS(f);
							}
							catch (java.lang.ClassNotFoundException e)
							{
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorsendSMS(f);
							}
							catch (java.lang.NoSuchMethodException e)
							{
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorsendSMS(f);
							}
							catch (java.lang.reflect.InvocationTargetException e)
							{
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorsendSMS(f);
							}
							catch (java.lang.IllegalAccessException e)
							{
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorsendSMS(f);
							}
							catch (java.lang.InstantiationException e)
							{
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorsendSMS(f);
							}
							catch (org.apache.axis2.AxisFault e)
							{
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorsendSMS(f);
							}
						}
						else
						{
							callback.receiveErrorsendSMS(f);
						}
					}
					else
					{
						callback.receiveErrorsendSMS(f);
					}
				}
				else
				{
					callback.receiveErrorsendSMS(error);
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
					callback.receiveErrorsendSMS(axisFault);
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
	 * @see vnm.Mtcharging_ws#subscription
	 * @param subscription4
	 */

	public vnm.Mtcharging_wsStub.SubscriptionResponse subscription(

			vnm.Mtcharging_wsStub.Subscription subscription4)

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
					subscription4,
					optimizeContent(new javax.xml.namespace.QName("http://iwebservices.nms.com",
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
					vnm.Mtcharging_wsStub.SubscriptionResponse.class,
					getEnvelopeNamespaces(_returnEnv));

			return (vnm.Mtcharging_wsStub.SubscriptionResponse) object;

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
	 * @see vnm.Mtcharging_ws#startsubscription
	 * @param subscription4
	 */
	public void startsubscription(

			vnm.Mtcharging_wsStub.Subscription subscription4,

			final vnm.Mtcharging_wsCallbackHandler callback)

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
				subscription4,
				optimizeContent(new javax.xml.namespace.QName("http://iwebservices.nms.com",
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
							vnm.Mtcharging_wsStub.SubscriptionResponse.class,
							getEnvelopeNamespaces(resultEnv));
					callback.receiveResultsubscription(
							(vnm.Mtcharging_wsStub.SubscriptionResponse) object);

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
	 * Auto generated method signature
	 * 
	 * @see vnm.Mtcharging_ws#sendWAP
	 * @param sendWAP6
	 */

	public vnm.Mtcharging_wsStub.SendWAPResponseE sendWAP(

			vnm.Mtcharging_wsStub.SendWAP sendWAP6)

			throws java.rmi.RemoteException

	{
		org.apache.axis2.context.MessageContext _messageContext = null;
		try
		{
			org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[3].getName());
			_operationClient.getOptions().setAction("\"\"");
			_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

			addPropertyToOperationClient(_operationClient, org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR, "&");

			// create a message context
			_messageContext = new org.apache.axis2.context.MessageContext();

			// create SOAP envelope with that payload
			org.apache.axiom.soap.SOAPEnvelope env = null;

			env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()),
					sendWAP6,
					optimizeContent(new javax.xml.namespace.QName("http://iwebservices.nms.com",
							"sendWAP")));

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
					vnm.Mtcharging_wsStub.SendWAPResponseE.class,
					getEnvelopeNamespaces(_returnEnv));

			return (vnm.Mtcharging_wsStub.SendWAPResponseE) object;

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
	 * @see vnm.Mtcharging_ws#startsendWAP
	 * @param sendWAP6
	 */
	public void startsendWAP(

			vnm.Mtcharging_wsStub.SendWAP sendWAP6,

			final vnm.Mtcharging_wsCallbackHandler callback)

			throws java.rmi.RemoteException
	{

		org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[3].getName());
		_operationClient.getOptions().setAction("\"\"");
		_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

		addPropertyToOperationClient(_operationClient, org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR, "&");

		// create SOAP envelope with that payload
		org.apache.axiom.soap.SOAPEnvelope env = null;
		final org.apache.axis2.context.MessageContext _messageContext = new org.apache.axis2.context.MessageContext();

		// Style is Doc.

		env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()),
				sendWAP6,
				optimizeContent(new javax.xml.namespace.QName("http://iwebservices.nms.com",
						"sendWAP")));

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
							vnm.Mtcharging_wsStub.SendWAPResponseE.class,
							getEnvelopeNamespaces(resultEnv));
					callback.receiveResultsendWAP(
							(vnm.Mtcharging_wsStub.SendWAPResponseE) object);

				}
				catch (org.apache.axis2.AxisFault e)
				{
					callback.receiveErrorsendWAP(e);
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

								callback.receiveErrorsendWAP(new java.rmi.RemoteException(ex.getMessage(), ex));
							}
							catch (java.lang.ClassCastException e)
							{
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorsendWAP(f);
							}
							catch (java.lang.ClassNotFoundException e)
							{
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorsendWAP(f);
							}
							catch (java.lang.NoSuchMethodException e)
							{
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorsendWAP(f);
							}
							catch (java.lang.reflect.InvocationTargetException e)
							{
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorsendWAP(f);
							}
							catch (java.lang.IllegalAccessException e)
							{
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorsendWAP(f);
							}
							catch (java.lang.InstantiationException e)
							{
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorsendWAP(f);
							}
							catch (org.apache.axis2.AxisFault e)
							{
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorsendWAP(f);
							}
						}
						else
						{
							callback.receiveErrorsendWAP(f);
						}
					}
					else
					{
						callback.receiveErrorsendWAP(f);
					}
				}
				else
				{
					callback.receiveErrorsendWAP(error);
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
					callback.receiveErrorsendWAP(axisFault);
				}
			}
		});

		org.apache.axis2.util.CallbackReceiver _callbackReceiver = null;
		if (_operations[3].getMessageReceiver() == null && _operationClient.getOptions().isUseSeparateListener())
		{
			_callbackReceiver = new org.apache.axis2.util.CallbackReceiver();
			_operations[3].setMessageReceiver(
					_callbackReceiver);
		}

		// execute the operation client
		_operationClient.execute(false);

	}

	/**
	 * Auto generated method signature
	 * 
	 * @see vnm.Mtcharging_ws#getStatus
	 * @param getStatus8
	 */

	public vnm.Mtcharging_wsStub.GetStatusResponse getStatus(

			vnm.Mtcharging_wsStub.GetStatus getStatus8)

			throws java.rmi.RemoteException

	{
		org.apache.axis2.context.MessageContext _messageContext = null;
		try
		{
			org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[4].getName());
			_operationClient.getOptions().setAction("\"\"");
			_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

			addPropertyToOperationClient(_operationClient, org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR, "&");

			// create a message context
			_messageContext = new org.apache.axis2.context.MessageContext();

			// create SOAP envelope with that payload
			org.apache.axiom.soap.SOAPEnvelope env = null;

			env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()),
					getStatus8,
					optimizeContent(new javax.xml.namespace.QName("http://iwebservices.nms.com",
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
					vnm.Mtcharging_wsStub.GetStatusResponse.class,
					getEnvelopeNamespaces(_returnEnv));

			return (vnm.Mtcharging_wsStub.GetStatusResponse) object;

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
	 * @see vnm.Mtcharging_ws#startgetStatus
	 * @param getStatus8
	 */
	public void startgetStatus(

			vnm.Mtcharging_wsStub.GetStatus getStatus8,

			final vnm.Mtcharging_wsCallbackHandler callback)

			throws java.rmi.RemoteException
	{

		org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[4].getName());
		_operationClient.getOptions().setAction("\"\"");
		_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

		addPropertyToOperationClient(_operationClient, org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR, "&");

		// create SOAP envelope with that payload
		org.apache.axiom.soap.SOAPEnvelope env = null;
		final org.apache.axis2.context.MessageContext _messageContext = new org.apache.axis2.context.MessageContext();

		// Style is Doc.

		env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()),
				getStatus8,
				optimizeContent(new javax.xml.namespace.QName("http://iwebservices.nms.com",
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
							vnm.Mtcharging_wsStub.GetStatusResponse.class,
							getEnvelopeNamespaces(resultEnv));
					callback.receiveResultgetStatus(
							(vnm.Mtcharging_wsStub.GetStatusResponse) object);

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
		if (_operations[4].getMessageReceiver() == null && _operationClient.getOptions().isUseSeparateListener())
		{
			_callbackReceiver = new org.apache.axis2.util.CallbackReceiver();
			_operations[4].setMessageReceiver(
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

	// http://203.128.246.91:8088/mtcharging/services/mtcharging_ws
	public static class SendWAP
			implements org.apache.axis2.databinding.ADBBean
	{

		public static final javax.xml.namespace.QName	MY_QNAME	= new javax.xml.namespace.QName(
																			"http://iwebservices.nms.com",
																			"sendWAP",
																			"ns2");

		private static java.lang.String generatePrefix(java.lang.String namespace)
		{
			if (namespace.equals("http://iwebservices.nms.com"))
			{
				return "ns2";
			}
			return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
		}

		/**
		 * field for In0
		 */

		protected SendWAPRequest	localIn0;

		/**
		 * Auto generated getter method
		 * 
		 * @return SendWAPRequest
		 */
		public SendWAPRequest getIn0()
		{
			return localIn0;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            In0
		 */
		public void setIn0(SendWAPRequest param)
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
							SendWAP.this.serialize(MY_QNAME, factory, xmlWriter);
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

				java.lang.String namespacePrefix = registerPrefix(xmlWriter, "http://iwebservices.nms.com");
				if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0))
				{
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type",
							namespacePrefix + ":sendWAP",
							xmlWriter);
				}
				else
				{
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type",
							"sendWAP",
							xmlWriter);
				}

			}

			if (localIn0 == null)
			{

				java.lang.String namespace2 = "http://iwebservices.nms.com";

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
				localIn0.serialize(new javax.xml.namespace.QName("http://iwebservices.nms.com", "in0"),
						factory, xmlWriter);
			}

			xmlWriter.writeEndElement();

		}

		/**
		 * Util method to write an attribute with the ns prefix
		 */
		private void writeAttribute(java.lang.String prefix, java.lang.String namespace, java.lang.String attName,
				java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException
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
				java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException
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
				javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException
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

			elementList.add(new javax.xml.namespace.QName("http://iwebservices.nms.com",
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
			public static SendWAP parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception
			{
				SendWAP object =
						new SendWAP();

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

							if (!"sendWAP".equals(type))
							{
								// find namespace for the prefix
								java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
								return (SendWAP) ExtensionMapper.getTypeObject(
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

					if (reader.isStartElement() && new javax.xml.namespace.QName("http://iwebservices.nms.com", "in0").equals(reader.getName()))
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

							object.setIn0(SendWAPRequest.Factory.parse(reader));

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

	public static class SendWAPResponse
			implements org.apache.axis2.databinding.ADBBean
	{
		/*
		 * This type was generated from the piece of schema that had name =
		 * SendWAPResponse Namespace URI = http://iwebservice.nms.com Namespace
		 * Prefix = ns1
		 */

		private static java.lang.String generatePrefix(java.lang.String namespace)
		{
			if (namespace.equals("http://iwebservice.nms.com"))
			{
				return "ns1";
			}
			return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
		}

		/**
		 * field for Result
		 */

		protected java.lang.String	localResult;

		/*
		 * This tracker boolean wil be used to detect whether the user called
		 * the set method for this attribute. It will be used to determine
		 * whether to include this field in the serialized XML
		 */
		protected boolean			localResultTracker	= false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getResult()
		{
			return localResult;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            Result
		 */
		public void setResult(java.lang.String param)
		{

			if (param != null)
			{
				// update the setting tracker
				localResultTracker = true;
			}
			else
			{
				localResultTracker = true;

			}

			this.localResult = param;

		}

		/**
		 * field for ResultDescription
		 */

		protected java.lang.String	localResultDescription;

		/*
		 * This tracker boolean wil be used to detect whether the user called
		 * the set method for this attribute. It will be used to determine
		 * whether to include this field in the serialized XML
		 */
		protected boolean			localResultDescriptionTracker	= false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getResultDescription()
		{
			return localResultDescription;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            ResultDescription
		 */
		public void setResultDescription(java.lang.String param)
		{

			if (param != null)
			{
				// update the setting tracker
				localResultDescriptionTracker = true;
			}
			else
			{
				localResultDescriptionTracker = true;

			}

			this.localResultDescription = param;

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
							SendWAPResponse.this.serialize(parentQName, factory, xmlWriter);
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

				java.lang.String namespacePrefix = registerPrefix(xmlWriter, "http://iwebservice.nms.com");
				if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0))
				{
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type",
							namespacePrefix + ":SendWAPResponse",
							xmlWriter);
				}
				else
				{
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type",
							"SendWAPResponse",
							xmlWriter);
				}

			}
			if (localResultTracker)
			{
				namespace = "http://iwebservice.nms.com";
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

				if (localResult == null)
				{
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				}
				else
				{

					xmlWriter.writeCharacters(localResult);

				}

				xmlWriter.writeEndElement();
			}
			if (localResultDescriptionTracker)
			{
				namespace = "http://iwebservice.nms.com";
				if (!namespace.equals(""))
				{
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null)
					{
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "resultDescription", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					}
					else
					{
						xmlWriter.writeStartElement(namespace, "resultDescription");
					}

				}
				else
				{
					xmlWriter.writeStartElement("resultDescription");
				}

				if (localResultDescription == null)
				{
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				}
				else
				{

					xmlWriter.writeCharacters(localResultDescription);

				}

				xmlWriter.writeEndElement();
			}
			xmlWriter.writeEndElement();

		}

		/**
		 * Util method to write an attribute with the ns prefix
		 */
		private void writeAttribute(java.lang.String prefix, java.lang.String namespace, java.lang.String attName,
				java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException
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
				java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException
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
				javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException
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

			if (localResultTracker)
			{
				elementList.add(new javax.xml.namespace.QName("http://iwebservice.nms.com",
						"result"));

				elementList.add(localResult == null ? null :
						org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localResult));
			}
			if (localResultDescriptionTracker)
			{
				elementList.add(new javax.xml.namespace.QName("http://iwebservice.nms.com",
						"resultDescription"));

				elementList.add(localResultDescription == null ? null :
						org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localResultDescription));
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
			public static SendWAPResponse parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception
			{
				SendWAPResponse object =
						new SendWAPResponse();

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

							if (!"SendWAPResponse".equals(type))
							{
								// find namespace for the prefix
								java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
								return (SendWAPResponse) ExtensionMapper.getTypeObject(
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

					if (reader.isStartElement() && new javax.xml.namespace.QName("http://iwebservice.nms.com", "result").equals(reader.getName()))
					{

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue))
						{

							java.lang.String content = reader.getElementText();

							object.setResult(
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
							&& new javax.xml.namespace.QName("http://iwebservice.nms.com", "resultDescription").equals(reader.getName()))
					{

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue))
						{

							java.lang.String content = reader.getElementText();

							object.setResultDescription(
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

	public static class SyncSubscriber
			implements org.apache.axis2.databinding.ADBBean
	{

		public static final javax.xml.namespace.QName	MY_QNAME	= new javax.xml.namespace.QName(
																			"http://iwebservices.nms.com",
																			"syncSubscriber",
																			"ns2");

		private static java.lang.String generatePrefix(java.lang.String namespace)
		{
			if (namespace.equals("http://iwebservices.nms.com"))
			{
				return "ns2";
			}
			return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
		}

		/**
		 * field for In0
		 */

		protected SyncSubscriberReq	localIn0;

		/**
		 * Auto generated getter method
		 * 
		 * @return SyncSubscriberReq
		 */
		public SyncSubscriberReq getIn0()
		{
			return localIn0;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            In0
		 */
		public void setIn0(SyncSubscriberReq param)
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
							SyncSubscriber.this.serialize(MY_QNAME, factory, xmlWriter);
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

				java.lang.String namespacePrefix = registerPrefix(xmlWriter, "http://iwebservices.nms.com");
				if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0))
				{
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type",
							namespacePrefix + ":syncSubscriber",
							xmlWriter);
				}
				else
				{
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type",
							"syncSubscriber",
							xmlWriter);
				}

			}

			if (localIn0 == null)
			{

				java.lang.String namespace2 = "http://iwebservices.nms.com";

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
				localIn0.serialize(new javax.xml.namespace.QName("http://iwebservices.nms.com", "in0"),
						factory, xmlWriter);
			}

			xmlWriter.writeEndElement();

		}

		/**
		 * Util method to write an attribute with the ns prefix
		 */
		private void writeAttribute(java.lang.String prefix, java.lang.String namespace, java.lang.String attName,
				java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException
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
				java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException
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
				javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException
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

			elementList.add(new javax.xml.namespace.QName("http://iwebservices.nms.com",
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
			public static SyncSubscriber parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception
			{
				SyncSubscriber object =
						new SyncSubscriber();

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

							if (!"syncSubscriber".equals(type))
							{
								// find namespace for the prefix
								java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
								return (SyncSubscriber) ExtensionMapper.getTypeObject(
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

					if (reader.isStartElement() && new javax.xml.namespace.QName("http://iwebservices.nms.com", "in0").equals(reader.getName()))
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

							object.setIn0(SyncSubscriberReq.Factory.parse(reader));

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

	public static class SendSMS
			implements org.apache.axis2.databinding.ADBBean
	{

		public static final javax.xml.namespace.QName	MY_QNAME	= new javax.xml.namespace.QName(
																			"http://iwebservices.nms.com",
																			"sendSMS",
																			"ns2");

		private static java.lang.String generatePrefix(java.lang.String namespace)
		{
			if (namespace.equals("http://iwebservices.nms.com"))
			{
				return "ns2";
			}
			return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
		}

		/**
		 * field for In0
		 */

		protected SendSMSRequest	localIn0;

		/**
		 * Auto generated getter method
		 * 
		 * @return SendSMSRequest
		 */
		public SendSMSRequest getIn0()
		{
			return localIn0;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            In0
		 */
		public void setIn0(SendSMSRequest param)
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
							SendSMS.this.serialize(MY_QNAME, factory, xmlWriter);
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

				java.lang.String namespacePrefix = registerPrefix(xmlWriter, "http://iwebservices.nms.com");
				if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0))
				{
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type",
							namespacePrefix + ":sendSMS",
							xmlWriter);
				}
				else
				{
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type",
							"sendSMS",
							xmlWriter);
				}

			}

			if (localIn0 == null)
			{

				java.lang.String namespace2 = "http://iwebservices.nms.com";

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
				localIn0.serialize(new javax.xml.namespace.QName("http://iwebservices.nms.com", "in0"),
						factory, xmlWriter);
			}

			xmlWriter.writeEndElement();

		}

		/**
		 * Util method to write an attribute with the ns prefix
		 */
		private void writeAttribute(java.lang.String prefix, java.lang.String namespace, java.lang.String attName,
				java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException
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
				java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException
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
				javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException
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

			elementList.add(new javax.xml.namespace.QName("http://iwebservices.nms.com",
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
			public static SendSMS parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception
			{
				SendSMS object =
						new SendSMS();

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

							if (!"sendSMS".equals(type))
							{
								// find namespace for the prefix
								java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
								return (SendSMS) ExtensionMapper.getTypeObject(
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

					if (reader.isStartElement() && new javax.xml.namespace.QName("http://iwebservices.nms.com", "in0").equals(reader.getName()))
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

							object.setIn0(SendSMSRequest.Factory.parse(reader));

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
																			"http://iwebservices.nms.com",
																			"getStatusResponse",
																			"ns2");

		private static java.lang.String generatePrefix(java.lang.String namespace)
		{
			if (namespace.equals("http://iwebservices.nms.com"))
			{
				return "ns2";
			}
			return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
		}

		/**
		 * field for Out
		 */

		protected ServiceStatus	localOut;

		/**
		 * Auto generated getter method
		 * 
		 * @return ServiceStatus
		 */
		public ServiceStatus getOut()
		{
			return localOut;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            Out
		 */
		public void setOut(ServiceStatus param)
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

				java.lang.String namespacePrefix = registerPrefix(xmlWriter, "http://iwebservices.nms.com");
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

				java.lang.String namespace2 = "http://iwebservices.nms.com";

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
				localOut.serialize(new javax.xml.namespace.QName("http://iwebservices.nms.com", "out"),
						factory, xmlWriter);
			}

			xmlWriter.writeEndElement();

		}

		/**
		 * Util method to write an attribute with the ns prefix
		 */
		private void writeAttribute(java.lang.String prefix, java.lang.String namespace, java.lang.String attName,
				java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException
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
				java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException
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
				javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException
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

			elementList.add(new javax.xml.namespace.QName("http://iwebservices.nms.com",
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

					if (reader.isStartElement() && new javax.xml.namespace.QName("http://iwebservices.nms.com", "out").equals(reader.getName()))
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

							object.setOut(ServiceStatus.Factory.parse(reader));

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

	public static class SyncSubscriberResponse
			implements org.apache.axis2.databinding.ADBBean
	{

		public static final javax.xml.namespace.QName	MY_QNAME	= new javax.xml.namespace.QName(
																			"http://iwebservices.nms.com",
																			"syncSubscriberResponse",
																			"ns2");

		private static java.lang.String generatePrefix(java.lang.String namespace)
		{
			if (namespace.equals("http://iwebservices.nms.com"))
			{
				return "ns2";
			}
			return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
		}

		/**
		 * field for Out
		 */

		protected SyncSubscriberResp	localOut;

		/**
		 * Auto generated getter method
		 * 
		 * @return SyncSubscriberResp
		 */
		public SyncSubscriberResp getOut()
		{
			return localOut;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            Out
		 */
		public void setOut(SyncSubscriberResp param)
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
							SyncSubscriberResponse.this.serialize(MY_QNAME, factory, xmlWriter);
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

				java.lang.String namespacePrefix = registerPrefix(xmlWriter, "http://iwebservices.nms.com");
				if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0))
				{
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type",
							namespacePrefix + ":syncSubscriberResponse",
							xmlWriter);
				}
				else
				{
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type",
							"syncSubscriberResponse",
							xmlWriter);
				}

			}

			if (localOut == null)
			{

				java.lang.String namespace2 = "http://iwebservices.nms.com";

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
				localOut.serialize(new javax.xml.namespace.QName("http://iwebservices.nms.com", "out"),
						factory, xmlWriter);
			}

			xmlWriter.writeEndElement();

		}

		/**
		 * Util method to write an attribute with the ns prefix
		 */
		private void writeAttribute(java.lang.String prefix, java.lang.String namespace, java.lang.String attName,
				java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException
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
				java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException
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
				javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException
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

			elementList.add(new javax.xml.namespace.QName("http://iwebservices.nms.com",
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
			public static SyncSubscriberResponse parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception
			{
				SyncSubscriberResponse object =
						new SyncSubscriberResponse();

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

							if (!"syncSubscriberResponse".equals(type))
							{
								// find namespace for the prefix
								java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
								return (SyncSubscriberResponse) ExtensionMapper.getTypeObject(
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

					if (reader.isStartElement() && new javax.xml.namespace.QName("http://iwebservices.nms.com", "out").equals(reader.getName()))
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

							object.setOut(SyncSubscriberResp.Factory.parse(reader));

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

	public static class SyncSubscriberReq
			implements org.apache.axis2.databinding.ADBBean
	{
		/*
		 * This type was generated from the piece of schema that had name =
		 * SyncSubscriberReq Namespace URI = http://iwebservice.nms.com
		 * Namespace Prefix = ns1
		 */

		private static java.lang.String generatePrefix(java.lang.String namespace)
		{
			if (namespace.equals("http://iwebservice.nms.com"))
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
							SyncSubscriberReq.this.serialize(parentQName, factory, xmlWriter);
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

				java.lang.String namespacePrefix = registerPrefix(xmlWriter, "http://iwebservice.nms.com");
				if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0))
				{
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type",
							namespacePrefix + ":SyncSubscriberReq",
							xmlWriter);
				}
				else
				{
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type",
							"SyncSubscriberReq",
							xmlWriter);
				}

			}
			if (localAgentIdTracker)
			{
				namespace = "http://iwebservice.nms.com";
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
				namespace = "http://iwebservice.nms.com";
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
			if (localDescriptionTracker)
			{
				namespace = "http://iwebservice.nms.com";
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
				namespace = "http://iwebservice.nms.com";
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
			if (localPasswordTracker)
			{
				namespace = "http://iwebservice.nms.com";
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
				namespace = "http://iwebservice.nms.com";
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
				namespace = "http://iwebservice.nms.com";
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
				namespace = "http://iwebservice.nms.com";
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
			xmlWriter.writeEndElement();

		}

		/**
		 * Util method to write an attribute with the ns prefix
		 */
		private void writeAttribute(java.lang.String prefix, java.lang.String namespace, java.lang.String attName,
				java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException
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
				java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException
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
				javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException
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
				elementList.add(new javax.xml.namespace.QName("http://iwebservice.nms.com",
						"agentId"));

				elementList.add(
						org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localAgentId));
			}
			if (localCpIdTracker)
			{
				elementList.add(new javax.xml.namespace.QName("http://iwebservice.nms.com",
						"cpId"));

				elementList.add(
						org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCpId));
			}
			if (localDescriptionTracker)
			{
				elementList.add(new javax.xml.namespace.QName("http://iwebservice.nms.com",
						"description"));

				elementList.add(localDescription == null ? null :
						org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localDescription));
			}
			if (localIsdnTracker)
			{
				elementList.add(new javax.xml.namespace.QName("http://iwebservice.nms.com",
						"isdn"));

				elementList.add(localIsdn == null ? null :
						org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localIsdn));
			}
			if (localPasswordTracker)
			{
				elementList.add(new javax.xml.namespace.QName("http://iwebservice.nms.com",
						"password"));

				elementList.add(localPassword == null ? null :
						org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localPassword));
			}
			if (localProductTracker)
			{
				elementList.add(new javax.xml.namespace.QName("http://iwebservice.nms.com",
						"product"));

				elementList.add(localProduct == null ? null :
						org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localProduct));
			}
			if (localRequestDateTracker)
			{
				elementList.add(new javax.xml.namespace.QName("http://iwebservice.nms.com",
						"requestDate"));

				elementList.add(localRequestDate == null ? null :
						org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localRequestDate));
			}
			if (localRequestIdTracker)
			{
				elementList.add(new javax.xml.namespace.QName("http://iwebservice.nms.com",
						"requestId"));

				elementList.add(
						org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localRequestId));
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
			public static SyncSubscriberReq parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception
			{
				SyncSubscriberReq object =
						new SyncSubscriberReq();

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

							if (!"SyncSubscriberReq".equals(type))
							{
								// find namespace for the prefix
								java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
								return (SyncSubscriberReq) ExtensionMapper.getTypeObject(
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

					if (reader.isStartElement() && new javax.xml.namespace.QName("http://iwebservice.nms.com", "agentId").equals(reader.getName()))
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

					if (reader.isStartElement() && new javax.xml.namespace.QName("http://iwebservice.nms.com", "cpId").equals(reader.getName()))
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
							&& new javax.xml.namespace.QName("http://iwebservice.nms.com", "description").equals(reader.getName()))
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

					if (reader.isStartElement() && new javax.xml.namespace.QName("http://iwebservice.nms.com", "isdn").equals(reader.getName()))
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

					if (reader.isStartElement() && new javax.xml.namespace.QName("http://iwebservice.nms.com", "password").equals(reader.getName()))
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

					if (reader.isStartElement() && new javax.xml.namespace.QName("http://iwebservice.nms.com", "product").equals(reader.getName()))
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

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("http://iwebservice.nms.com", "requestDate").equals(reader.getName()))
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

					if (reader.isStartElement() && new javax.xml.namespace.QName("http://iwebservice.nms.com", "requestId").equals(reader.getName()))
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

	public static class Subscription
			implements org.apache.axis2.databinding.ADBBean
	{

		public static final javax.xml.namespace.QName	MY_QNAME	= new javax.xml.namespace.QName(
																			"http://iwebservices.nms.com",
																			"subscription",
																			"ns2");

		private static java.lang.String generatePrefix(java.lang.String namespace)
		{
			if (namespace.equals("http://iwebservices.nms.com"))
			{
				return "ns2";
			}
			return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
		}

		/**
		 * field for In0
		 */

		protected SubscriptionReq	localIn0;

		/**
		 * Auto generated getter method
		 * 
		 * @return SubscriptionReq
		 */
		public SubscriptionReq getIn0()
		{
			return localIn0;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            In0
		 */
		public void setIn0(SubscriptionReq param)
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

				java.lang.String namespacePrefix = registerPrefix(xmlWriter, "http://iwebservices.nms.com");
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

				java.lang.String namespace2 = "http://iwebservices.nms.com";

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
				localIn0.serialize(new javax.xml.namespace.QName("http://iwebservices.nms.com", "in0"),
						factory, xmlWriter);
			}

			xmlWriter.writeEndElement();

		}

		/**
		 * Util method to write an attribute with the ns prefix
		 */
		private void writeAttribute(java.lang.String prefix, java.lang.String namespace, java.lang.String attName,
				java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException
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
				java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException
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
				javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException
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

			elementList.add(new javax.xml.namespace.QName("http://iwebservices.nms.com",
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

					if (reader.isStartElement() && new javax.xml.namespace.QName("http://iwebservices.nms.com", "in0").equals(reader.getName()))
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

							object.setIn0(SubscriptionReq.Factory.parse(reader));

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

	public static class ServiceStatus
			implements org.apache.axis2.databinding.ADBBean
	{
		/*
		 * This type was generated from the piece of schema that had name =
		 * ServiceStatus Namespace URI = http://iwebservice.nms.com Namespace
		 * Prefix = ns1
		 */

		private static java.lang.String generatePrefix(java.lang.String namespace)
		{
			if (namespace.equals("http://iwebservice.nms.com"))
			{
				return "ns1";
			}
			return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
		}

		/**
		 * field for ExpirationDate
		 */

		protected java.util.Calendar	localExpirationDate;

		/*
		 * This tracker boolean wil be used to detect whether the user called
		 * the set method for this attribute. It will be used to determine
		 * whether to include this field in the serialized XML
		 */
		protected boolean				localExpirationDateTracker	= false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.util.Calendar
		 */
		public java.util.Calendar getExpirationDate()
		{
			return localExpirationDate;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            ExpirationDate
		 */
		public void setExpirationDate(java.util.Calendar param)
		{

			if (param != null)
			{
				// update the setting tracker
				localExpirationDateTracker = true;
			}
			else
			{
				localExpirationDateTracker = false;

			}

			this.localExpirationDate = param;

		}

		/**
		 * field for GraceDate
		 */

		protected java.util.Calendar	localGraceDate;

		/*
		 * This tracker boolean wil be used to detect whether the user called
		 * the set method for this attribute. It will be used to determine
		 * whether to include this field in the serialized XML
		 */
		protected boolean				localGraceDateTracker	= false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.util.Calendar
		 */
		public java.util.Calendar getGraceDate()
		{
			return localGraceDate;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            GraceDate
		 */
		public void setGraceDate(java.util.Calendar param)
		{

			if (param != null)
			{
				// update the setting tracker
				localGraceDateTracker = true;
			}
			else
			{
				localGraceDateTracker = false;

			}

			this.localGraceDate = param;

		}

		/**
		 * field for RegisterDate
		 */

		protected java.util.Calendar	localRegisterDate;

		/*
		 * This tracker boolean wil be used to detect whether the user called
		 * the set method for this attribute. It will be used to determine
		 * whether to include this field in the serialized XML
		 */
		protected boolean				localRegisterDateTracker	= false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.util.Calendar
		 */
		public java.util.Calendar getRegisterDate()
		{
			return localRegisterDate;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            RegisterDate
		 */
		public void setRegisterDate(java.util.Calendar param)
		{

			if (param != null)
			{
				// update the setting tracker
				localRegisterDateTracker = true;
			}
			else
			{
				localRegisterDateTracker = false;

			}

			this.localRegisterDate = param;

		}

		/**
		 * field for Result
		 */

		protected java.lang.String	localResult;

		/*
		 * This tracker boolean wil be used to detect whether the user called
		 * the set method for this attribute. It will be used to determine
		 * whether to include this field in the serialized XML
		 */
		protected boolean			localResultTracker	= false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getResult()
		{
			return localResult;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            Result
		 */
		public void setResult(java.lang.String param)
		{

			if (param != null)
			{
				// update the setting tracker
				localResultTracker = true;
			}
			else
			{
				localResultTracker = true;

			}

			this.localResult = param;

		}

		/**
		 * field for ResultDescription
		 */

		protected java.lang.String	localResultDescription;

		/*
		 * This tracker boolean wil be used to detect whether the user called
		 * the set method for this attribute. It will be used to determine
		 * whether to include this field in the serialized XML
		 */
		protected boolean			localResultDescriptionTracker	= false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getResultDescription()
		{
			return localResultDescription;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            ResultDescription
		 */
		public void setResultDescription(java.lang.String param)
		{

			if (param != null)
			{
				// update the setting tracker
				localResultDescriptionTracker = true;
			}
			else
			{
				localResultDescriptionTracker = true;

			}

			this.localResultDescription = param;

		}

		/**
		 * field for Status
		 */

		protected int		localStatus;

		/*
		 * This tracker boolean wil be used to detect whether the user called
		 * the set method for this attribute. It will be used to determine
		 * whether to include this field in the serialized XML
		 */
		protected boolean	localStatusTracker	= false;

		/**
		 * Auto generated getter method
		 * 
		 * @return int
		 */
		public int getStatus()
		{
			return localStatus;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            Status
		 */
		public void setStatus(int param)
		{

			// setting primitive attribute tracker to true

			if (param == java.lang.Integer.MIN_VALUE)
			{
				localStatusTracker = false;

			}
			else
			{
				localStatusTracker = true;
			}

			this.localStatus = param;

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
							ServiceStatus.this.serialize(parentQName, factory, xmlWriter);
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

				java.lang.String namespacePrefix = registerPrefix(xmlWriter, "http://iwebservice.nms.com");
				if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0))
				{
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type",
							namespacePrefix + ":ServiceStatus",
							xmlWriter);
				}
				else
				{
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type",
							"ServiceStatus",
							xmlWriter);
				}

			}
			if (localExpirationDateTracker)
			{
				namespace = "http://iwebservice.nms.com";
				if (!namespace.equals(""))
				{
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null)
					{
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "expirationDate", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					}
					else
					{
						xmlWriter.writeStartElement(namespace, "expirationDate");
					}

				}
				else
				{
					xmlWriter.writeStartElement("expirationDate");
				}

				if (localExpirationDate == null)
				{
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("expirationDate cannot be null!!");

				}
				else
				{

					xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localExpirationDate));

				}

				xmlWriter.writeEndElement();
			}
			if (localGraceDateTracker)
			{
				namespace = "http://iwebservice.nms.com";
				if (!namespace.equals(""))
				{
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null)
					{
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "graceDate", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					}
					else
					{
						xmlWriter.writeStartElement(namespace, "graceDate");
					}

				}
				else
				{
					xmlWriter.writeStartElement("graceDate");
				}

				if (localGraceDate == null)
				{
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("graceDate cannot be null!!");

				}
				else
				{

					xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localGraceDate));

				}

				xmlWriter.writeEndElement();
			}
			if (localRegisterDateTracker)
			{
				namespace = "http://iwebservice.nms.com";
				if (!namespace.equals(""))
				{
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null)
					{
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "registerDate", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					}
					else
					{
						xmlWriter.writeStartElement(namespace, "registerDate");
					}

				}
				else
				{
					xmlWriter.writeStartElement("registerDate");
				}

				if (localRegisterDate == null)
				{
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("registerDate cannot be null!!");

				}
				else
				{

					xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localRegisterDate));

				}

				xmlWriter.writeEndElement();
			}
			if (localResultTracker)
			{
				namespace = "http://iwebservice.nms.com";
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

				if (localResult == null)
				{
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				}
				else
				{

					xmlWriter.writeCharacters(localResult);

				}

				xmlWriter.writeEndElement();
			}
			if (localResultDescriptionTracker)
			{
				namespace = "http://iwebservice.nms.com";
				if (!namespace.equals(""))
				{
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null)
					{
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "resultDescription", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					}
					else
					{
						xmlWriter.writeStartElement(namespace, "resultDescription");
					}

				}
				else
				{
					xmlWriter.writeStartElement("resultDescription");
				}

				if (localResultDescription == null)
				{
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				}
				else
				{

					xmlWriter.writeCharacters(localResultDescription);

				}

				xmlWriter.writeEndElement();
			}
			if (localStatusTracker)
			{
				namespace = "http://iwebservice.nms.com";
				if (!namespace.equals(""))
				{
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null)
					{
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "status", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					}
					else
					{
						xmlWriter.writeStartElement(namespace, "status");
					}

				}
				else
				{
					xmlWriter.writeStartElement("status");
				}

				if (localStatus == java.lang.Integer.MIN_VALUE)
				{

					throw new org.apache.axis2.databinding.ADBException("status cannot be null!!");

				}
				else
				{
					xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localStatus));
				}

				xmlWriter.writeEndElement();
			}
			xmlWriter.writeEndElement();

		}

		/**
		 * Util method to write an attribute with the ns prefix
		 */
		private void writeAttribute(java.lang.String prefix, java.lang.String namespace, java.lang.String attName,
				java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException
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
				java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException
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
				javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException
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

			if (localExpirationDateTracker)
			{
				elementList.add(new javax.xml.namespace.QName("http://iwebservice.nms.com",
						"expirationDate"));

				if (localExpirationDate != null)
				{
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localExpirationDate));
				}
				else
				{
					throw new org.apache.axis2.databinding.ADBException("expirationDate cannot be null!!");
				}
			}
			if (localGraceDateTracker)
			{
				elementList.add(new javax.xml.namespace.QName("http://iwebservice.nms.com",
						"graceDate"));

				if (localGraceDate != null)
				{
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localGraceDate));
				}
				else
				{
					throw new org.apache.axis2.databinding.ADBException("graceDate cannot be null!!");
				}
			}
			if (localRegisterDateTracker)
			{
				elementList.add(new javax.xml.namespace.QName("http://iwebservice.nms.com",
						"registerDate"));

				if (localRegisterDate != null)
				{
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localRegisterDate));
				}
				else
				{
					throw new org.apache.axis2.databinding.ADBException("registerDate cannot be null!!");
				}
			}
			if (localResultTracker)
			{
				elementList.add(new javax.xml.namespace.QName("http://iwebservice.nms.com",
						"result"));

				elementList.add(localResult == null ? null :
						org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localResult));
			}
			if (localResultDescriptionTracker)
			{
				elementList.add(new javax.xml.namespace.QName("http://iwebservice.nms.com",
						"resultDescription"));

				elementList.add(localResultDescription == null ? null :
						org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localResultDescription));
			}
			if (localStatusTracker)
			{
				elementList.add(new javax.xml.namespace.QName("http://iwebservice.nms.com",
						"status"));

				elementList.add(
						org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localStatus));
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
			public static ServiceStatus parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception
			{
				ServiceStatus object =
						new ServiceStatus();

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

							if (!"ServiceStatus".equals(type))
							{
								// find namespace for the prefix
								java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
								return (ServiceStatus) ExtensionMapper.getTypeObject(
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

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("http://iwebservice.nms.com", "expirationDate").equals(reader.getName()))
					{

						java.lang.String content = reader.getElementText();

						object.setExpirationDate(
								org.apache.axis2.databinding.utils.ConverterUtil.convertToDateTime(content));

						reader.next();

					} // End of if for expected property start element

					else
					{

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("http://iwebservice.nms.com", "graceDate").equals(reader.getName()))
					{

						java.lang.String content = reader.getElementText();

						object.setGraceDate(
								org.apache.axis2.databinding.utils.ConverterUtil.convertToDateTime(content));

						reader.next();

					} // End of if for expected property start element

					else
					{

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("http://iwebservice.nms.com", "registerDate").equals(reader.getName()))
					{

						java.lang.String content = reader.getElementText();

						object.setRegisterDate(
								org.apache.axis2.databinding.utils.ConverterUtil.convertToDateTime(content));

						reader.next();

					} // End of if for expected property start element

					else
					{

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("http://iwebservice.nms.com", "result").equals(reader.getName()))
					{

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue))
						{

							java.lang.String content = reader.getElementText();

							object.setResult(
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
							&& new javax.xml.namespace.QName("http://iwebservice.nms.com", "resultDescription").equals(reader.getName()))
					{

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue))
						{

							java.lang.String content = reader.getElementText();

							object.setResultDescription(
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

					if (reader.isStartElement() && new javax.xml.namespace.QName("http://iwebservice.nms.com", "status").equals(reader.getName()))
					{

						java.lang.String content = reader.getElementText();

						object.setStatus(
								org.apache.axis2.databinding.utils.ConverterUtil.convertToInt(content));

						reader.next();

					} // End of if for expected property start element

					else
					{

						object.setStatus(java.lang.Integer.MIN_VALUE);

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

	public static class ServiceRequest
			implements org.apache.axis2.databinding.ADBBean
	{
		/*
		 * This type was generated from the piece of schema that had name =
		 * ServiceRequest Namespace URI = http://iwebservice.nms.com Namespace
		 * Prefix = ns1
		 */

		private static java.lang.String generatePrefix(java.lang.String namespace)
		{
			if (namespace.equals("http://iwebservice.nms.com"))
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

				java.lang.String namespacePrefix = registerPrefix(xmlWriter, "http://iwebservice.nms.com");
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
				namespace = "http://iwebservice.nms.com";
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
				namespace = "http://iwebservice.nms.com";
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
			if (localDescriptionTracker)
			{
				namespace = "http://iwebservice.nms.com";
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
				namespace = "http://iwebservice.nms.com";
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
			if (localPasswordTracker)
			{
				namespace = "http://iwebservice.nms.com";
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
				namespace = "http://iwebservice.nms.com";
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
				namespace = "http://iwebservice.nms.com";
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
				namespace = "http://iwebservice.nms.com";
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
			xmlWriter.writeEndElement();

		}

		/**
		 * Util method to write an attribute with the ns prefix
		 */
		private void writeAttribute(java.lang.String prefix, java.lang.String namespace, java.lang.String attName,
				java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException
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
				java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException
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
				javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException
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
				elementList.add(new javax.xml.namespace.QName("http://iwebservice.nms.com",
						"agentId"));

				elementList.add(
						org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localAgentId));
			}
			if (localCpIdTracker)
			{
				elementList.add(new javax.xml.namespace.QName("http://iwebservice.nms.com",
						"cpId"));

				elementList.add(
						org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCpId));
			}
			if (localDescriptionTracker)
			{
				elementList.add(new javax.xml.namespace.QName("http://iwebservice.nms.com",
						"description"));

				elementList.add(localDescription == null ? null :
						org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localDescription));
			}
			if (localIsdnTracker)
			{
				elementList.add(new javax.xml.namespace.QName("http://iwebservice.nms.com",
						"isdn"));

				elementList.add(localIsdn == null ? null :
						org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localIsdn));
			}
			if (localPasswordTracker)
			{
				elementList.add(new javax.xml.namespace.QName("http://iwebservice.nms.com",
						"password"));

				elementList.add(localPassword == null ? null :
						org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localPassword));
			}
			if (localProductTracker)
			{
				elementList.add(new javax.xml.namespace.QName("http://iwebservice.nms.com",
						"product"));

				elementList.add(localProduct == null ? null :
						org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localProduct));
			}
			if (localRequestDateTracker)
			{
				elementList.add(new javax.xml.namespace.QName("http://iwebservice.nms.com",
						"requestDate"));

				elementList.add(localRequestDate == null ? null :
						org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localRequestDate));
			}
			if (localRequestIdTracker)
			{
				elementList.add(new javax.xml.namespace.QName("http://iwebservice.nms.com",
						"requestId"));

				elementList.add(
						org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localRequestId));
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

					if (reader.isStartElement() && new javax.xml.namespace.QName("http://iwebservice.nms.com", "agentId").equals(reader.getName()))
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

					if (reader.isStartElement() && new javax.xml.namespace.QName("http://iwebservice.nms.com", "cpId").equals(reader.getName()))
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
							&& new javax.xml.namespace.QName("http://iwebservice.nms.com", "description").equals(reader.getName()))
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

					if (reader.isStartElement() && new javax.xml.namespace.QName("http://iwebservice.nms.com", "isdn").equals(reader.getName()))
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

					if (reader.isStartElement() && new javax.xml.namespace.QName("http://iwebservice.nms.com", "password").equals(reader.getName()))
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

					if (reader.isStartElement() && new javax.xml.namespace.QName("http://iwebservice.nms.com", "product").equals(reader.getName()))
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

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("http://iwebservice.nms.com", "requestDate").equals(reader.getName()))
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

					if (reader.isStartElement() && new javax.xml.namespace.QName("http://iwebservice.nms.com", "requestId").equals(reader.getName()))
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

	public static class SubscriptionReq
			implements org.apache.axis2.databinding.ADBBean
	{
		/*
		 * This type was generated from the piece of schema that had name =
		 * SubscriptionReq Namespace URI = http://iwebservice.nms.com Namespace
		 * Prefix = ns1
		 */

		private static java.lang.String generatePrefix(java.lang.String namespace)
		{
			if (namespace.equals("http://iwebservice.nms.com"))
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
							SubscriptionReq.this.serialize(parentQName, factory, xmlWriter);
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

				java.lang.String namespacePrefix = registerPrefix(xmlWriter, "http://iwebservice.nms.com");
				if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0))
				{
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type",
							namespacePrefix + ":SubscriptionReq",
							xmlWriter);
				}
				else
				{
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type",
							"SubscriptionReq",
							xmlWriter);
				}

			}
			if (localAgentIdTracker)
			{
				namespace = "http://iwebservice.nms.com";
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
				namespace = "http://iwebservice.nms.com";
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
			if (localDescriptionTracker)
			{
				namespace = "http://iwebservice.nms.com";
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
				namespace = "http://iwebservice.nms.com";
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
			if (localPasswordTracker)
			{
				namespace = "http://iwebservice.nms.com";
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
				namespace = "http://iwebservice.nms.com";
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
				namespace = "http://iwebservice.nms.com";
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
				namespace = "http://iwebservice.nms.com";
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
			xmlWriter.writeEndElement();

		}

		/**
		 * Util method to write an attribute with the ns prefix
		 */
		private void writeAttribute(java.lang.String prefix, java.lang.String namespace, java.lang.String attName,
				java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException
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
				java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException
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
				javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException
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
				elementList.add(new javax.xml.namespace.QName("http://iwebservice.nms.com",
						"agentId"));

				elementList.add(
						org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localAgentId));
			}
			if (localCpIdTracker)
			{
				elementList.add(new javax.xml.namespace.QName("http://iwebservice.nms.com",
						"cpId"));

				elementList.add(
						org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCpId));
			}
			if (localDescriptionTracker)
			{
				elementList.add(new javax.xml.namespace.QName("http://iwebservice.nms.com",
						"description"));

				elementList.add(localDescription == null ? null :
						org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localDescription));
			}
			if (localIsdnTracker)
			{
				elementList.add(new javax.xml.namespace.QName("http://iwebservice.nms.com",
						"isdn"));

				elementList.add(localIsdn == null ? null :
						org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localIsdn));
			}
			if (localPasswordTracker)
			{
				elementList.add(new javax.xml.namespace.QName("http://iwebservice.nms.com",
						"password"));

				elementList.add(localPassword == null ? null :
						org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localPassword));
			}
			if (localProductTracker)
			{
				elementList.add(new javax.xml.namespace.QName("http://iwebservice.nms.com",
						"product"));

				elementList.add(localProduct == null ? null :
						org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localProduct));
			}
			if (localRequestDateTracker)
			{
				elementList.add(new javax.xml.namespace.QName("http://iwebservice.nms.com",
						"requestDate"));

				elementList.add(localRequestDate == null ? null :
						org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localRequestDate));
			}
			if (localRequestIdTracker)
			{
				elementList.add(new javax.xml.namespace.QName("http://iwebservice.nms.com",
						"requestId"));

				elementList.add(
						org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localRequestId));
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
			public static SubscriptionReq parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception
			{
				SubscriptionReq object =
						new SubscriptionReq();

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

							if (!"SubscriptionReq".equals(type))
							{
								// find namespace for the prefix
								java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
								return (SubscriptionReq) ExtensionMapper.getTypeObject(
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

					if (reader.isStartElement() && new javax.xml.namespace.QName("http://iwebservice.nms.com", "agentId").equals(reader.getName()))
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

					if (reader.isStartElement() && new javax.xml.namespace.QName("http://iwebservice.nms.com", "cpId").equals(reader.getName()))
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
							&& new javax.xml.namespace.QName("http://iwebservice.nms.com", "description").equals(reader.getName()))
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

					if (reader.isStartElement() && new javax.xml.namespace.QName("http://iwebservice.nms.com", "isdn").equals(reader.getName()))
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

					if (reader.isStartElement() && new javax.xml.namespace.QName("http://iwebservice.nms.com", "password").equals(reader.getName()))
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

					if (reader.isStartElement() && new javax.xml.namespace.QName("http://iwebservice.nms.com", "product").equals(reader.getName()))
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

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("http://iwebservice.nms.com", "requestDate").equals(reader.getName()))
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

					if (reader.isStartElement() && new javax.xml.namespace.QName("http://iwebservice.nms.com", "requestId").equals(reader.getName()))
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

	public static class SyncSubscriberResp
			implements org.apache.axis2.databinding.ADBBean
	{
		/*
		 * This type was generated from the piece of schema that had name =
		 * SyncSubscriberResp Namespace URI = http://iwebservice.nms.com
		 * Namespace Prefix = ns1
		 */

		private static java.lang.String generatePrefix(java.lang.String namespace)
		{
			if (namespace.equals("http://iwebservice.nms.com"))
			{
				return "ns1";
			}
			return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
		}

		/**
		 * field for Action
		 */

		protected java.lang.String	localAction;

		/*
		 * This tracker boolean wil be used to detect whether the user called
		 * the set method for this attribute. It will be used to determine
		 * whether to include this field in the serialized XML
		 */
		protected boolean			localActionTracker	= false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getAction()
		{
			return localAction;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            Action
		 */
		public void setAction(java.lang.String param)
		{

			if (param != null)
			{
				// update the setting tracker
				localActionTracker = true;
			}
			else
			{
				localActionTracker = true;

			}

			this.localAction = param;

		}

		/**
		 * field for Result
		 */

		protected java.lang.String	localResult;

		/*
		 * This tracker boolean wil be used to detect whether the user called
		 * the set method for this attribute. It will be used to determine
		 * whether to include this field in the serialized XML
		 */
		protected boolean			localResultTracker	= false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getResult()
		{
			return localResult;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            Result
		 */
		public void setResult(java.lang.String param)
		{

			if (param != null)
			{
				// update the setting tracker
				localResultTracker = true;
			}
			else
			{
				localResultTracker = true;

			}

			this.localResult = param;

		}

		/**
		 * field for ResultDescription
		 */

		protected java.lang.String	localResultDescription;

		/*
		 * This tracker boolean wil be used to detect whether the user called
		 * the set method for this attribute. It will be used to determine
		 * whether to include this field in the serialized XML
		 */
		protected boolean			localResultDescriptionTracker	= false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getResultDescription()
		{
			return localResultDescription;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            ResultDescription
		 */
		public void setResultDescription(java.lang.String param)
		{

			if (param != null)
			{
				// update the setting tracker
				localResultDescriptionTracker = true;
			}
			else
			{
				localResultDescriptionTracker = true;

			}

			this.localResultDescription = param;

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
							SyncSubscriberResp.this.serialize(parentQName, factory, xmlWriter);
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

				java.lang.String namespacePrefix = registerPrefix(xmlWriter, "http://iwebservice.nms.com");
				if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0))
				{
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type",
							namespacePrefix + ":SyncSubscriberResp",
							xmlWriter);
				}
				else
				{
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type",
							"SyncSubscriberResp",
							xmlWriter);
				}

			}
			if (localActionTracker)
			{
				namespace = "http://iwebservice.nms.com";
				if (!namespace.equals(""))
				{
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null)
					{
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "action", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					}
					else
					{
						xmlWriter.writeStartElement(namespace, "action");
					}

				}
				else
				{
					xmlWriter.writeStartElement("action");
				}

				if (localAction == null)
				{
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				}
				else
				{

					xmlWriter.writeCharacters(localAction);

				}

				xmlWriter.writeEndElement();
			}
			if (localResultTracker)
			{
				namespace = "http://iwebservice.nms.com";
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

				if (localResult == null)
				{
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				}
				else
				{

					xmlWriter.writeCharacters(localResult);

				}

				xmlWriter.writeEndElement();
			}
			if (localResultDescriptionTracker)
			{
				namespace = "http://iwebservice.nms.com";
				if (!namespace.equals(""))
				{
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null)
					{
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "resultDescription", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					}
					else
					{
						xmlWriter.writeStartElement(namespace, "resultDescription");
					}

				}
				else
				{
					xmlWriter.writeStartElement("resultDescription");
				}

				if (localResultDescription == null)
				{
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				}
				else
				{

					xmlWriter.writeCharacters(localResultDescription);

				}

				xmlWriter.writeEndElement();
			}
			xmlWriter.writeEndElement();

		}

		/**
		 * Util method to write an attribute with the ns prefix
		 */
		private void writeAttribute(java.lang.String prefix, java.lang.String namespace, java.lang.String attName,
				java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException
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
				java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException
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
				javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException
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

			if (localActionTracker)
			{
				elementList.add(new javax.xml.namespace.QName("http://iwebservice.nms.com",
						"action"));

				elementList.add(localAction == null ? null :
						org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localAction));
			}
			if (localResultTracker)
			{
				elementList.add(new javax.xml.namespace.QName("http://iwebservice.nms.com",
						"result"));

				elementList.add(localResult == null ? null :
						org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localResult));
			}
			if (localResultDescriptionTracker)
			{
				elementList.add(new javax.xml.namespace.QName("http://iwebservice.nms.com",
						"resultDescription"));

				elementList.add(localResultDescription == null ? null :
						org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localResultDescription));
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
			public static SyncSubscriberResp parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception
			{
				SyncSubscriberResp object =
						new SyncSubscriberResp();

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

							if (!"SyncSubscriberResp".equals(type))
							{
								// find namespace for the prefix
								java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
								return (SyncSubscriberResp) ExtensionMapper.getTypeObject(
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

					if (reader.isStartElement() && new javax.xml.namespace.QName("http://iwebservice.nms.com", "action").equals(reader.getName()))
					{

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue))
						{

							java.lang.String content = reader.getElementText();

							object.setAction(
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

					if (reader.isStartElement() && new javax.xml.namespace.QName("http://iwebservice.nms.com", "result").equals(reader.getName()))
					{

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue))
						{

							java.lang.String content = reader.getElementText();

							object.setResult(
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
							&& new javax.xml.namespace.QName("http://iwebservice.nms.com", "resultDescription").equals(reader.getName()))
					{

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue))
						{

							java.lang.String content = reader.getElementText();

							object.setResultDescription(
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

	public static class SendWAPRequest
			implements org.apache.axis2.databinding.ADBBean
	{
		/*
		 * This type was generated from the piece of schema that had name =
		 * SendWAPRequest Namespace URI = http://iwebservice.nms.com Namespace
		 * Prefix = ns1
		 */

		private static java.lang.String generatePrefix(java.lang.String namespace)
		{
			if (namespace.equals("http://iwebservice.nms.com"))
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
		 * field for ShortCode
		 */

		protected java.lang.String	localShortCode;

		/*
		 * This tracker boolean wil be used to detect whether the user called
		 * the set method for this attribute. It will be used to determine
		 * whether to include this field in the serialized XML
		 */
		protected boolean			localShortCodeTracker	= false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getShortCode()
		{
			return localShortCode;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            ShortCode
		 */
		public void setShortCode(java.lang.String param)
		{

			if (param != null)
			{
				// update the setting tracker
				localShortCodeTracker = true;
			}
			else
			{
				localShortCodeTracker = true;

			}

			this.localShortCode = param;

		}

		/**
		 * field for Subject
		 */

		protected java.lang.String	localSubject;

		/*
		 * This tracker boolean wil be used to detect whether the user called
		 * the set method for this attribute. It will be used to determine
		 * whether to include this field in the serialized XML
		 */
		protected boolean			localSubjectTracker	= false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getSubject()
		{
			return localSubject;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            Subject
		 */
		public void setSubject(java.lang.String param)
		{

			if (param != null)
			{
				// update the setting tracker
				localSubjectTracker = true;
			}
			else
			{
				localSubjectTracker = true;

			}

			this.localSubject = param;

		}

		/**
		 * field for TargetURL
		 */

		protected java.lang.String	localTargetURL;

		/*
		 * This tracker boolean wil be used to detect whether the user called
		 * the set method for this attribute. It will be used to determine
		 * whether to include this field in the serialized XML
		 */
		protected boolean			localTargetURLTracker	= false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getTargetURL()
		{
			return localTargetURL;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            TargetURL
		 */
		public void setTargetURL(java.lang.String param)
		{

			if (param != null)
			{
				// update the setting tracker
				localTargetURLTracker = true;
			}
			else
			{
				localTargetURLTracker = true;

			}

			this.localTargetURL = param;

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
							SendWAPRequest.this.serialize(parentQName, factory, xmlWriter);
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

				java.lang.String namespacePrefix = registerPrefix(xmlWriter, "http://iwebservice.nms.com");
				if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0))
				{
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type",
							namespacePrefix + ":SendWAPRequest",
							xmlWriter);
				}
				else
				{
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type",
							"SendWAPRequest",
							xmlWriter);
				}

			}
			if (localAgentIdTracker)
			{
				namespace = "http://iwebservice.nms.com";
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
				namespace = "http://iwebservice.nms.com";
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
			if (localDescriptionTracker)
			{
				namespace = "http://iwebservice.nms.com";
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
				namespace = "http://iwebservice.nms.com";
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
			if (localPasswordTracker)
			{
				namespace = "http://iwebservice.nms.com";
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
				namespace = "http://iwebservice.nms.com";
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
				namespace = "http://iwebservice.nms.com";
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
				namespace = "http://iwebservice.nms.com";
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
			if (localShortCodeTracker)
			{
				namespace = "http://iwebservice.nms.com";
				if (!namespace.equals(""))
				{
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null)
					{
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "shortCode", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					}
					else
					{
						xmlWriter.writeStartElement(namespace, "shortCode");
					}

				}
				else
				{
					xmlWriter.writeStartElement("shortCode");
				}

				if (localShortCode == null)
				{
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				}
				else
				{

					xmlWriter.writeCharacters(localShortCode);

				}

				xmlWriter.writeEndElement();
			}
			if (localSubjectTracker)
			{
				namespace = "http://iwebservice.nms.com";
				if (!namespace.equals(""))
				{
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null)
					{
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "subject", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					}
					else
					{
						xmlWriter.writeStartElement(namespace, "subject");
					}

				}
				else
				{
					xmlWriter.writeStartElement("subject");
				}

				if (localSubject == null)
				{
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				}
				else
				{

					xmlWriter.writeCharacters(localSubject);

				}

				xmlWriter.writeEndElement();
			}
			if (localTargetURLTracker)
			{
				namespace = "http://iwebservice.nms.com";
				if (!namespace.equals(""))
				{
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null)
					{
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "targetURL", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					}
					else
					{
						xmlWriter.writeStartElement(namespace, "targetURL");
					}

				}
				else
				{
					xmlWriter.writeStartElement("targetURL");
				}

				if (localTargetURL == null)
				{
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				}
				else
				{

					xmlWriter.writeCharacters(localTargetURL);

				}

				xmlWriter.writeEndElement();
			}
			xmlWriter.writeEndElement();

		}

		/**
		 * Util method to write an attribute with the ns prefix
		 */
		private void writeAttribute(java.lang.String prefix, java.lang.String namespace, java.lang.String attName,
				java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException
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
				java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException
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
				javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException
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
				elementList.add(new javax.xml.namespace.QName("http://iwebservice.nms.com",
						"agentId"));

				elementList.add(
						org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localAgentId));
			}
			if (localCpIdTracker)
			{
				elementList.add(new javax.xml.namespace.QName("http://iwebservice.nms.com",
						"cpId"));

				elementList.add(
						org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCpId));
			}
			if (localDescriptionTracker)
			{
				elementList.add(new javax.xml.namespace.QName("http://iwebservice.nms.com",
						"description"));

				elementList.add(localDescription == null ? null :
						org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localDescription));
			}
			if (localIsdnTracker)
			{
				elementList.add(new javax.xml.namespace.QName("http://iwebservice.nms.com",
						"isdn"));

				elementList.add(localIsdn == null ? null :
						org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localIsdn));
			}
			if (localPasswordTracker)
			{
				elementList.add(new javax.xml.namespace.QName("http://iwebservice.nms.com",
						"password"));

				elementList.add(localPassword == null ? null :
						org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localPassword));
			}
			if (localProductTracker)
			{
				elementList.add(new javax.xml.namespace.QName("http://iwebservice.nms.com",
						"product"));

				elementList.add(localProduct == null ? null :
						org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localProduct));
			}
			if (localRequestDateTracker)
			{
				elementList.add(new javax.xml.namespace.QName("http://iwebservice.nms.com",
						"requestDate"));

				elementList.add(localRequestDate == null ? null :
						org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localRequestDate));
			}
			if (localRequestIdTracker)
			{
				elementList.add(new javax.xml.namespace.QName("http://iwebservice.nms.com",
						"requestId"));

				elementList.add(
						org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localRequestId));
			}
			if (localShortCodeTracker)
			{
				elementList.add(new javax.xml.namespace.QName("http://iwebservice.nms.com",
						"shortCode"));

				elementList.add(localShortCode == null ? null :
						org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localShortCode));
			}
			if (localSubjectTracker)
			{
				elementList.add(new javax.xml.namespace.QName("http://iwebservice.nms.com",
						"subject"));

				elementList.add(localSubject == null ? null :
						org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSubject));
			}
			if (localTargetURLTracker)
			{
				elementList.add(new javax.xml.namespace.QName("http://iwebservice.nms.com",
						"targetURL"));

				elementList.add(localTargetURL == null ? null :
						org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localTargetURL));
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
			public static SendWAPRequest parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception
			{
				SendWAPRequest object =
						new SendWAPRequest();

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

							if (!"SendWAPRequest".equals(type))
							{
								// find namespace for the prefix
								java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
								return (SendWAPRequest) ExtensionMapper.getTypeObject(
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

					if (reader.isStartElement() && new javax.xml.namespace.QName("http://iwebservice.nms.com", "agentId").equals(reader.getName()))
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

					if (reader.isStartElement() && new javax.xml.namespace.QName("http://iwebservice.nms.com", "cpId").equals(reader.getName()))
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
							&& new javax.xml.namespace.QName("http://iwebservice.nms.com", "description").equals(reader.getName()))
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

					if (reader.isStartElement() && new javax.xml.namespace.QName("http://iwebservice.nms.com", "isdn").equals(reader.getName()))
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

					if (reader.isStartElement() && new javax.xml.namespace.QName("http://iwebservice.nms.com", "password").equals(reader.getName()))
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

					if (reader.isStartElement() && new javax.xml.namespace.QName("http://iwebservice.nms.com", "product").equals(reader.getName()))
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

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("http://iwebservice.nms.com", "requestDate").equals(reader.getName()))
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

					if (reader.isStartElement() && new javax.xml.namespace.QName("http://iwebservice.nms.com", "requestId").equals(reader.getName()))
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

					if (reader.isStartElement() && new javax.xml.namespace.QName("http://iwebservice.nms.com", "shortCode").equals(reader.getName()))
					{

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue))
						{

							java.lang.String content = reader.getElementText();

							object.setShortCode(
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

					if (reader.isStartElement() && new javax.xml.namespace.QName("http://iwebservice.nms.com", "subject").equals(reader.getName()))
					{

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue))
						{

							java.lang.String content = reader.getElementText();

							object.setSubject(
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

					if (reader.isStartElement() && new javax.xml.namespace.QName("http://iwebservice.nms.com", "targetURL").equals(reader.getName()))
					{

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue))
						{

							java.lang.String content = reader.getElementText();

							object.setTargetURL(
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
																			"http://iwebservices.nms.com",
																			"getStatus",
																			"ns2");

		private static java.lang.String generatePrefix(java.lang.String namespace)
		{
			if (namespace.equals("http://iwebservices.nms.com"))
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

				java.lang.String namespacePrefix = registerPrefix(xmlWriter, "http://iwebservices.nms.com");
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

				java.lang.String namespace2 = "http://iwebservices.nms.com";

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
				localIn0.serialize(new javax.xml.namespace.QName("http://iwebservices.nms.com", "in0"),
						factory, xmlWriter);
			}

			xmlWriter.writeEndElement();

		}

		/**
		 * Util method to write an attribute with the ns prefix
		 */
		private void writeAttribute(java.lang.String prefix, java.lang.String namespace, java.lang.String attName,
				java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException
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
				java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException
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
				javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException
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

			elementList.add(new javax.xml.namespace.QName("http://iwebservices.nms.com",
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

					if (reader.isStartElement() && new javax.xml.namespace.QName("http://iwebservices.nms.com", "in0").equals(reader.getName()))
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

	public static class ExtensionMapper
	{

		public static java.lang.Object getTypeObject(java.lang.String namespaceURI,
				java.lang.String typeName,
				javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception
		{

			if ("http://iwebservice.nms.com".equals(namespaceURI) &&
					"SubscriptionReq".equals(typeName))
			{

				return SubscriptionReq.Factory.parse(reader);

			}

			if ("http://iwebservice.nms.com".equals(namespaceURI) &&
					"SendWAPResponse".equals(typeName))
			{

				return SendWAPResponse.Factory.parse(reader);

			}

			if ("http://iwebservice.nms.com".equals(namespaceURI) &&
					"SyncSubscriberResp".equals(typeName))
			{

				return SyncSubscriberResp.Factory.parse(reader);

			}

			if ("http://iwebservice.nms.com".equals(namespaceURI) &&
					"SendWAPRequest".equals(typeName))
			{

				return SendWAPRequest.Factory.parse(reader);

			}

			if ("http://iwebservice.nms.com".equals(namespaceURI) &&
					"SyncSubscriberReq".equals(typeName))
			{

				return SyncSubscriberReq.Factory.parse(reader);

			}

			if ("http://iwebservice.nms.com".equals(namespaceURI) &&
					"SendSMSResponse".equals(typeName))
			{

				return SendSMSResponse.Factory.parse(reader);

			}

			if ("http://iwebservice.nms.com".equals(namespaceURI) &&
					"SendSMSRequest".equals(typeName))
			{

				return SendSMSRequest.Factory.parse(reader);

			}

			if ("http://iwebservice.nms.com".equals(namespaceURI) &&
					"ServiceRequest".equals(typeName))
			{

				return ServiceRequest.Factory.parse(reader);

			}

			if ("http://iwebservice.nms.com".equals(namespaceURI) &&
					"ServiceStatus".equals(typeName))
			{

				return ServiceStatus.Factory.parse(reader);

			}

			if ("http://iwebservice.nms.com".equals(namespaceURI) &&
					"SubscriptionResp".equals(typeName))
			{

				return SubscriptionResp.Factory.parse(reader);

			}

			throw new org.apache.axis2.databinding.ADBException("Unsupported type " + namespaceURI + " " + typeName);
		}

	}

	public static class SendSMSResponse
			implements org.apache.axis2.databinding.ADBBean
	{
		/*
		 * This type was generated from the piece of schema that had name =
		 * SendSMSResponse Namespace URI = http://iwebservice.nms.com Namespace
		 * Prefix = ns1
		 */

		private static java.lang.String generatePrefix(java.lang.String namespace)
		{
			if (namespace.equals("http://iwebservice.nms.com"))
			{
				return "ns1";
			}
			return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
		}

		/**
		 * field for Result
		 */

		protected java.lang.String	localResult;

		/*
		 * This tracker boolean wil be used to detect whether the user called
		 * the set method for this attribute. It will be used to determine
		 * whether to include this field in the serialized XML
		 */
		protected boolean			localResultTracker	= false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getResult()
		{
			return localResult;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            Result
		 */
		public void setResult(java.lang.String param)
		{

			if (param != null)
			{
				// update the setting tracker
				localResultTracker = true;
			}
			else
			{
				localResultTracker = true;

			}

			this.localResult = param;

		}

		/**
		 * field for ResultDescription
		 */

		protected java.lang.String	localResultDescription;

		/*
		 * This tracker boolean wil be used to detect whether the user called
		 * the set method for this attribute. It will be used to determine
		 * whether to include this field in the serialized XML
		 */
		protected boolean			localResultDescriptionTracker	= false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getResultDescription()
		{
			return localResultDescription;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            ResultDescription
		 */
		public void setResultDescription(java.lang.String param)
		{

			if (param != null)
			{
				// update the setting tracker
				localResultDescriptionTracker = true;
			}
			else
			{
				localResultDescriptionTracker = true;

			}

			this.localResultDescription = param;

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
							SendSMSResponse.this.serialize(parentQName, factory, xmlWriter);
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

				java.lang.String namespacePrefix = registerPrefix(xmlWriter, "http://iwebservice.nms.com");
				if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0))
				{
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type",
							namespacePrefix + ":SendSMSResponse",
							xmlWriter);
				}
				else
				{
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type",
							"SendSMSResponse",
							xmlWriter);
				}

			}
			if (localResultTracker)
			{
				namespace = "http://iwebservice.nms.com";
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

				if (localResult == null)
				{
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				}
				else
				{

					xmlWriter.writeCharacters(localResult);

				}

				xmlWriter.writeEndElement();
			}
			if (localResultDescriptionTracker)
			{
				namespace = "http://iwebservice.nms.com";
				if (!namespace.equals(""))
				{
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null)
					{
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "resultDescription", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					}
					else
					{
						xmlWriter.writeStartElement(namespace, "resultDescription");
					}

				}
				else
				{
					xmlWriter.writeStartElement("resultDescription");
				}

				if (localResultDescription == null)
				{
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				}
				else
				{

					xmlWriter.writeCharacters(localResultDescription);

				}

				xmlWriter.writeEndElement();
			}
			xmlWriter.writeEndElement();

		}

		/**
		 * Util method to write an attribute with the ns prefix
		 */
		private void writeAttribute(java.lang.String prefix, java.lang.String namespace, java.lang.String attName,
				java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException
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
				java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException
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
				javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException
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

			if (localResultTracker)
			{
				elementList.add(new javax.xml.namespace.QName("http://iwebservice.nms.com",
						"result"));

				elementList.add(localResult == null ? null :
						org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localResult));
			}
			if (localResultDescriptionTracker)
			{
				elementList.add(new javax.xml.namespace.QName("http://iwebservice.nms.com",
						"resultDescription"));

				elementList.add(localResultDescription == null ? null :
						org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localResultDescription));
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
			public static SendSMSResponse parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception
			{
				SendSMSResponse object =
						new SendSMSResponse();

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

							if (!"SendSMSResponse".equals(type))
							{
								// find namespace for the prefix
								java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
								return (SendSMSResponse) ExtensionMapper.getTypeObject(
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

					if (reader.isStartElement() && new javax.xml.namespace.QName("http://iwebservice.nms.com", "result").equals(reader.getName()))
					{

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue))
						{

							java.lang.String content = reader.getElementText();

							object.setResult(
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
							&& new javax.xml.namespace.QName("http://iwebservice.nms.com", "resultDescription").equals(reader.getName()))
					{

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue))
						{

							java.lang.String content = reader.getElementText();

							object.setResultDescription(
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

	public static class SendSMSRequest
			implements org.apache.axis2.databinding.ADBBean
	{
		/*
		 * This type was generated from the piece of schema that had name =
		 * SendSMSRequest Namespace URI = http://iwebservice.nms.com Namespace
		 * Prefix = ns1
		 */

		private static java.lang.String generatePrefix(java.lang.String namespace)
		{
			if (namespace.equals("http://iwebservice.nms.com"))
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
		 * field for Message
		 */

		protected java.lang.String	localMessage;

		/*
		 * This tracker boolean wil be used to detect whether the user called
		 * the set method for this attribute. It will be used to determine
		 * whether to include this field in the serialized XML
		 */
		protected boolean			localMessageTracker	= false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getMessage()
		{
			return localMessage;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            Message
		 */
		public void setMessage(java.lang.String param)
		{

			if (param != null)
			{
				// update the setting tracker
				localMessageTracker = true;
			}
			else
			{
				localMessageTracker = true;

			}

			this.localMessage = param;

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
		 * field for ShortCode
		 */

		protected java.lang.String	localShortCode;

		/*
		 * This tracker boolean wil be used to detect whether the user called
		 * the set method for this attribute. It will be used to determine
		 * whether to include this field in the serialized XML
		 */
		protected boolean			localShortCodeTracker	= false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getShortCode()
		{
			return localShortCode;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            ShortCode
		 */
		public void setShortCode(java.lang.String param)
		{

			if (param != null)
			{
				// update the setting tracker
				localShortCodeTracker = true;
			}
			else
			{
				localShortCodeTracker = true;

			}

			this.localShortCode = param;

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
							SendSMSRequest.this.serialize(parentQName, factory, xmlWriter);
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

				java.lang.String namespacePrefix = registerPrefix(xmlWriter, "http://iwebservice.nms.com");
				if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0))
				{
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type",
							namespacePrefix + ":SendSMSRequest",
							xmlWriter);
				}
				else
				{
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type",
							"SendSMSRequest",
							xmlWriter);
				}

			}
			if (localAgentIdTracker)
			{
				namespace = "http://iwebservice.nms.com";
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
				namespace = "http://iwebservice.nms.com";
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
			if (localDescriptionTracker)
			{
				namespace = "http://iwebservice.nms.com";
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
				namespace = "http://iwebservice.nms.com";
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
			if (localMessageTracker)
			{
				namespace = "http://iwebservice.nms.com";
				if (!namespace.equals(""))
				{
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null)
					{
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "message", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					}
					else
					{
						xmlWriter.writeStartElement(namespace, "message");
					}

				}
				else
				{
					xmlWriter.writeStartElement("message");
				}

				if (localMessage == null)
				{
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				}
				else
				{

					xmlWriter.writeCharacters(localMessage);

				}

				xmlWriter.writeEndElement();
			}
			if (localPasswordTracker)
			{
				namespace = "http://iwebservice.nms.com";
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
				namespace = "http://iwebservice.nms.com";
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
				namespace = "http://iwebservice.nms.com";
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
				namespace = "http://iwebservice.nms.com";
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
			if (localShortCodeTracker)
			{
				namespace = "http://iwebservice.nms.com";
				if (!namespace.equals(""))
				{
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null)
					{
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "shortCode", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					}
					else
					{
						xmlWriter.writeStartElement(namespace, "shortCode");
					}

				}
				else
				{
					xmlWriter.writeStartElement("shortCode");
				}

				if (localShortCode == null)
				{
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				}
				else
				{

					xmlWriter.writeCharacters(localShortCode);

				}

				xmlWriter.writeEndElement();
			}
			xmlWriter.writeEndElement();

		}

		/**
		 * Util method to write an attribute with the ns prefix
		 */
		private void writeAttribute(java.lang.String prefix, java.lang.String namespace, java.lang.String attName,
				java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException
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
				java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException
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
				javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException
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
				elementList.add(new javax.xml.namespace.QName("http://iwebservice.nms.com",
						"agentId"));

				elementList.add(
						org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localAgentId));
			}
			if (localCpIdTracker)
			{
				elementList.add(new javax.xml.namespace.QName("http://iwebservice.nms.com",
						"cpId"));

				elementList.add(
						org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCpId));
			}
			if (localDescriptionTracker)
			{
				elementList.add(new javax.xml.namespace.QName("http://iwebservice.nms.com",
						"description"));

				elementList.add(localDescription == null ? null :
						org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localDescription));
			}
			if (localIsdnTracker)
			{
				elementList.add(new javax.xml.namespace.QName("http://iwebservice.nms.com",
						"isdn"));

				elementList.add(localIsdn == null ? null :
						org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localIsdn));
			}
			if (localMessageTracker)
			{
				elementList.add(new javax.xml.namespace.QName("http://iwebservice.nms.com",
						"message"));

				elementList.add(localMessage == null ? null :
						org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localMessage));
			}
			if (localPasswordTracker)
			{
				elementList.add(new javax.xml.namespace.QName("http://iwebservice.nms.com",
						"password"));

				elementList.add(localPassword == null ? null :
						org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localPassword));
			}
			if (localProductTracker)
			{
				elementList.add(new javax.xml.namespace.QName("http://iwebservice.nms.com",
						"product"));

				elementList.add(localProduct == null ? null :
						org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localProduct));
			}
			if (localRequestDateTracker)
			{
				elementList.add(new javax.xml.namespace.QName("http://iwebservice.nms.com",
						"requestDate"));

				elementList.add(localRequestDate == null ? null :
						org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localRequestDate));
			}
			if (localRequestIdTracker)
			{
				elementList.add(new javax.xml.namespace.QName("http://iwebservice.nms.com",
						"requestId"));

				elementList.add(
						org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localRequestId));
			}
			if (localShortCodeTracker)
			{
				elementList.add(new javax.xml.namespace.QName("http://iwebservice.nms.com",
						"shortCode"));

				elementList.add(localShortCode == null ? null :
						org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localShortCode));
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
			public static SendSMSRequest parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception
			{
				SendSMSRequest object =
						new SendSMSRequest();

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

							if (!"SendSMSRequest".equals(type))
							{
								// find namespace for the prefix
								java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
								return (SendSMSRequest) ExtensionMapper.getTypeObject(
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

					if (reader.isStartElement() && new javax.xml.namespace.QName("http://iwebservice.nms.com", "agentId").equals(reader.getName()))
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

					if (reader.isStartElement() && new javax.xml.namespace.QName("http://iwebservice.nms.com", "cpId").equals(reader.getName()))
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
							&& new javax.xml.namespace.QName("http://iwebservice.nms.com", "description").equals(reader.getName()))
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

					if (reader.isStartElement() && new javax.xml.namespace.QName("http://iwebservice.nms.com", "isdn").equals(reader.getName()))
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

					if (reader.isStartElement() && new javax.xml.namespace.QName("http://iwebservice.nms.com", "message").equals(reader.getName()))
					{

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue))
						{

							java.lang.String content = reader.getElementText();

							object.setMessage(
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

					if (reader.isStartElement() && new javax.xml.namespace.QName("http://iwebservice.nms.com", "password").equals(reader.getName()))
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

					if (reader.isStartElement() && new javax.xml.namespace.QName("http://iwebservice.nms.com", "product").equals(reader.getName()))
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

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("http://iwebservice.nms.com", "requestDate").equals(reader.getName()))
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

					if (reader.isStartElement() && new javax.xml.namespace.QName("http://iwebservice.nms.com", "requestId").equals(reader.getName()))
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

					if (reader.isStartElement() && new javax.xml.namespace.QName("http://iwebservice.nms.com", "shortCode").equals(reader.getName()))
					{

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue))
						{

							java.lang.String content = reader.getElementText();

							object.setShortCode(
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

	public static class SendWAPResponseE
			implements org.apache.axis2.databinding.ADBBean
	{

		public static final javax.xml.namespace.QName	MY_QNAME	= new javax.xml.namespace.QName(
																			"http://iwebservices.nms.com",
																			"sendWAPResponse",
																			"ns2");

		private static java.lang.String generatePrefix(java.lang.String namespace)
		{
			if (namespace.equals("http://iwebservices.nms.com"))
			{
				return "ns2";
			}
			return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
		}

		/**
		 * field for Out
		 */

		protected SendWAPResponse	localOut;

		/**
		 * Auto generated getter method
		 * 
		 * @return SendWAPResponse
		 */
		public SendWAPResponse getOut()
		{
			return localOut;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            Out
		 */
		public void setOut(SendWAPResponse param)
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
							SendWAPResponseE.this.serialize(MY_QNAME, factory, xmlWriter);
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

				java.lang.String namespacePrefix = registerPrefix(xmlWriter, "http://iwebservices.nms.com");
				if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0))
				{
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type",
							namespacePrefix + ":sendWAPResponse",
							xmlWriter);
				}
				else
				{
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type",
							"sendWAPResponse",
							xmlWriter);
				}

			}

			if (localOut == null)
			{

				java.lang.String namespace2 = "http://iwebservices.nms.com";

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
				localOut.serialize(new javax.xml.namespace.QName("http://iwebservices.nms.com", "out"),
						factory, xmlWriter);
			}

			xmlWriter.writeEndElement();

		}

		/**
		 * Util method to write an attribute with the ns prefix
		 */
		private void writeAttribute(java.lang.String prefix, java.lang.String namespace, java.lang.String attName,
				java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException
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
				java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException
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
				javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException
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

			elementList.add(new javax.xml.namespace.QName("http://iwebservices.nms.com",
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
			public static SendWAPResponseE parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception
			{
				SendWAPResponseE object =
						new SendWAPResponseE();

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

							if (!"sendWAPResponse".equals(type))
							{
								// find namespace for the prefix
								java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
								return (SendWAPResponseE) ExtensionMapper.getTypeObject(
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

					if (reader.isStartElement() && new javax.xml.namespace.QName("http://iwebservices.nms.com", "out").equals(reader.getName()))
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

							object.setOut(SendWAPResponse.Factory.parse(reader));

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
																			"http://iwebservices.nms.com",
																			"subscriptionResponse",
																			"ns2");

		private static java.lang.String generatePrefix(java.lang.String namespace)
		{
			if (namespace.equals("http://iwebservices.nms.com"))
			{
				return "ns2";
			}
			return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
		}

		/**
		 * field for Out
		 */

		protected SubscriptionResp	localOut;

		/**
		 * Auto generated getter method
		 * 
		 * @return SubscriptionResp
		 */
		public SubscriptionResp getOut()
		{
			return localOut;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            Out
		 */
		public void setOut(SubscriptionResp param)
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

				java.lang.String namespacePrefix = registerPrefix(xmlWriter, "http://iwebservices.nms.com");
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

				java.lang.String namespace2 = "http://iwebservices.nms.com";

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
				localOut.serialize(new javax.xml.namespace.QName("http://iwebservices.nms.com", "out"),
						factory, xmlWriter);
			}

			xmlWriter.writeEndElement();

		}

		/**
		 * Util method to write an attribute with the ns prefix
		 */
		private void writeAttribute(java.lang.String prefix, java.lang.String namespace, java.lang.String attName,
				java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException
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
				java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException
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
				javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException
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

			elementList.add(new javax.xml.namespace.QName("http://iwebservices.nms.com",
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

					if (reader.isStartElement() && new javax.xml.namespace.QName("http://iwebservices.nms.com", "out").equals(reader.getName()))
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

							object.setOut(SubscriptionResp.Factory.parse(reader));

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

	public static class SendSMSResponseE
			implements org.apache.axis2.databinding.ADBBean
	{

		public static final javax.xml.namespace.QName	MY_QNAME	= new javax.xml.namespace.QName(
																			"http://iwebservices.nms.com",
																			"sendSMSResponse",
																			"ns2");

		private static java.lang.String generatePrefix(java.lang.String namespace)
		{
			if (namespace.equals("http://iwebservices.nms.com"))
			{
				return "ns2";
			}
			return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
		}

		/**
		 * field for Out
		 */

		protected SendSMSResponse	localOut;

		/**
		 * Auto generated getter method
		 * 
		 * @return SendSMSResponse
		 */
		public SendSMSResponse getOut()
		{
			return localOut;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            Out
		 */
		public void setOut(SendSMSResponse param)
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
							SendSMSResponseE.this.serialize(MY_QNAME, factory, xmlWriter);
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

				java.lang.String namespacePrefix = registerPrefix(xmlWriter, "http://iwebservices.nms.com");
				if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0))
				{
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type",
							namespacePrefix + ":sendSMSResponse",
							xmlWriter);
				}
				else
				{
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type",
							"sendSMSResponse",
							xmlWriter);
				}

			}

			if (localOut == null)
			{

				java.lang.String namespace2 = "http://iwebservices.nms.com";

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
				localOut.serialize(new javax.xml.namespace.QName("http://iwebservices.nms.com", "out"),
						factory, xmlWriter);
			}

			xmlWriter.writeEndElement();

		}

		/**
		 * Util method to write an attribute with the ns prefix
		 */
		private void writeAttribute(java.lang.String prefix, java.lang.String namespace, java.lang.String attName,
				java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException
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
				java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException
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
				javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException
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

			elementList.add(new javax.xml.namespace.QName("http://iwebservices.nms.com",
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
			public static SendSMSResponseE parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception
			{
				SendSMSResponseE object =
						new SendSMSResponseE();

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

							if (!"sendSMSResponse".equals(type))
							{
								// find namespace for the prefix
								java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
								return (SendSMSResponseE) ExtensionMapper.getTypeObject(
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

					if (reader.isStartElement() && new javax.xml.namespace.QName("http://iwebservices.nms.com", "out").equals(reader.getName()))
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

							object.setOut(SendSMSResponse.Factory.parse(reader));

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

	public static class SubscriptionResp
			implements org.apache.axis2.databinding.ADBBean
	{
		/*
		 * This type was generated from the piece of schema that had name =
		 * SubscriptionResp Namespace URI = http://iwebservice.nms.com Namespace
		 * Prefix = ns1
		 */

		private static java.lang.String generatePrefix(java.lang.String namespace)
		{
			if (namespace.equals("http://iwebservice.nms.com"))
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
		 * field for Result
		 */

		protected java.lang.String	localResult;

		/*
		 * This tracker boolean wil be used to detect whether the user called
		 * the set method for this attribute. It will be used to determine
		 * whether to include this field in the serialized XML
		 */
		protected boolean			localResultTracker	= false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getResult()
		{
			return localResult;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            Result
		 */
		public void setResult(java.lang.String param)
		{

			if (param != null)
			{
				// update the setting tracker
				localResultTracker = true;
			}
			else
			{
				localResultTracker = true;

			}

			this.localResult = param;

		}

		/**
		 * field for ResultDescription
		 */

		protected java.lang.String	localResultDescription;

		/*
		 * This tracker boolean wil be used to detect whether the user called
		 * the set method for this attribute. It will be used to determine
		 * whether to include this field in the serialized XML
		 */
		protected boolean			localResultDescriptionTracker	= false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getResultDescription()
		{
			return localResultDescription;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            ResultDescription
		 */
		public void setResultDescription(java.lang.String param)
		{

			if (param != null)
			{
				// update the setting tracker
				localResultDescriptionTracker = true;
			}
			else
			{
				localResultDescriptionTracker = true;

			}

			this.localResultDescription = param;

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
							SubscriptionResp.this.serialize(parentQName, factory, xmlWriter);
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

				java.lang.String namespacePrefix = registerPrefix(xmlWriter, "http://iwebservice.nms.com");
				if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0))
				{
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type",
							namespacePrefix + ":SubscriptionResp",
							xmlWriter);
				}
				else
				{
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type",
							"SubscriptionResp",
							xmlWriter);
				}

			}
			if (localAmountTracker)
			{
				namespace = "http://iwebservice.nms.com";
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
			if (localResultTracker)
			{
				namespace = "http://iwebservice.nms.com";
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

				if (localResult == null)
				{
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				}
				else
				{

					xmlWriter.writeCharacters(localResult);

				}

				xmlWriter.writeEndElement();
			}
			if (localResultDescriptionTracker)
			{
				namespace = "http://iwebservice.nms.com";
				if (!namespace.equals(""))
				{
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null)
					{
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "resultDescription", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					}
					else
					{
						xmlWriter.writeStartElement(namespace, "resultDescription");
					}

				}
				else
				{
					xmlWriter.writeStartElement("resultDescription");
				}

				if (localResultDescription == null)
				{
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				}
				else
				{

					xmlWriter.writeCharacters(localResultDescription);

				}

				xmlWriter.writeEndElement();
			}
			xmlWriter.writeEndElement();

		}

		/**
		 * Util method to write an attribute with the ns prefix
		 */
		private void writeAttribute(java.lang.String prefix, java.lang.String namespace, java.lang.String attName,
				java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException
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
				java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException
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
				javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException
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
				elementList.add(new javax.xml.namespace.QName("http://iwebservice.nms.com",
						"amount"));

				elementList.add(
						org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localAmount));
			}
			if (localResultTracker)
			{
				elementList.add(new javax.xml.namespace.QName("http://iwebservice.nms.com",
						"result"));

				elementList.add(localResult == null ? null :
						org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localResult));
			}
			if (localResultDescriptionTracker)
			{
				elementList.add(new javax.xml.namespace.QName("http://iwebservice.nms.com",
						"resultDescription"));

				elementList.add(localResultDescription == null ? null :
						org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localResultDescription));
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
			public static SubscriptionResp parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception
			{
				SubscriptionResp object =
						new SubscriptionResp();

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

							if (!"SubscriptionResp".equals(type))
							{
								// find namespace for the prefix
								java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
								return (SubscriptionResp) ExtensionMapper.getTypeObject(
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

					if (reader.isStartElement() && new javax.xml.namespace.QName("http://iwebservice.nms.com", "amount").equals(reader.getName()))
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

					if (reader.isStartElement() && new javax.xml.namespace.QName("http://iwebservice.nms.com", "result").equals(reader.getName()))
					{

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue))
						{

							java.lang.String content = reader.getElementText();

							object.setResult(
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
							&& new javax.xml.namespace.QName("http://iwebservice.nms.com", "resultDescription").equals(reader.getName()))
					{

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue))
						{

							java.lang.String content = reader.getElementText();

							object.setResultDescription(
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

	private org.apache.axiom.om.OMElement toOM(vnm.Mtcharging_wsStub.SyncSubscriber param, boolean optimizeContent)
			throws org.apache.axis2.AxisFault
	{

		try
		{
			return param.getOMElement(vnm.Mtcharging_wsStub.SyncSubscriber.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		}
		catch (org.apache.axis2.databinding.ADBException e)
		{
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(vnm.Mtcharging_wsStub.SyncSubscriberResponse param, boolean optimizeContent)
			throws org.apache.axis2.AxisFault
	{

		try
		{
			return param.getOMElement(vnm.Mtcharging_wsStub.SyncSubscriberResponse.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		}
		catch (org.apache.axis2.databinding.ADBException e)
		{
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(vnm.Mtcharging_wsStub.SendSMS param, boolean optimizeContent)
			throws org.apache.axis2.AxisFault
	{

		try
		{
			return param.getOMElement(vnm.Mtcharging_wsStub.SendSMS.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		}
		catch (org.apache.axis2.databinding.ADBException e)
		{
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(vnm.Mtcharging_wsStub.SendSMSResponseE param, boolean optimizeContent)
			throws org.apache.axis2.AxisFault
	{

		try
		{
			return param.getOMElement(vnm.Mtcharging_wsStub.SendSMSResponseE.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		}
		catch (org.apache.axis2.databinding.ADBException e)
		{
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(vnm.Mtcharging_wsStub.Subscription param, boolean optimizeContent)
			throws org.apache.axis2.AxisFault
	{

		try
		{
			return param.getOMElement(vnm.Mtcharging_wsStub.Subscription.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		}
		catch (org.apache.axis2.databinding.ADBException e)
		{
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(vnm.Mtcharging_wsStub.SubscriptionResponse param, boolean optimizeContent)
			throws org.apache.axis2.AxisFault
	{

		try
		{
			return param.getOMElement(vnm.Mtcharging_wsStub.SubscriptionResponse.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		}
		catch (org.apache.axis2.databinding.ADBException e)
		{
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(vnm.Mtcharging_wsStub.SendWAP param, boolean optimizeContent)
			throws org.apache.axis2.AxisFault
	{

		try
		{
			return param.getOMElement(vnm.Mtcharging_wsStub.SendWAP.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		}
		catch (org.apache.axis2.databinding.ADBException e)
		{
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(vnm.Mtcharging_wsStub.SendWAPResponseE param, boolean optimizeContent)
			throws org.apache.axis2.AxisFault
	{

		try
		{
			return param.getOMElement(vnm.Mtcharging_wsStub.SendWAPResponseE.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		}
		catch (org.apache.axis2.databinding.ADBException e)
		{
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(vnm.Mtcharging_wsStub.GetStatus param, boolean optimizeContent)
			throws org.apache.axis2.AxisFault
	{

		try
		{
			return param.getOMElement(vnm.Mtcharging_wsStub.GetStatus.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		}
		catch (org.apache.axis2.databinding.ADBException e)
		{
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(vnm.Mtcharging_wsStub.GetStatusResponse param, boolean optimizeContent)
			throws org.apache.axis2.AxisFault
	{

		try
		{
			return param.getOMElement(vnm.Mtcharging_wsStub.GetStatusResponse.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		}
		catch (org.apache.axis2.databinding.ADBException e)
		{
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, vnm.Mtcharging_wsStub.SyncSubscriber param,
			boolean optimizeContent)
			throws org.apache.axis2.AxisFault
	{

		try
		{

			org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
			emptyEnvelope.getBody().addChild(param.getOMElement(vnm.Mtcharging_wsStub.SyncSubscriber.MY_QNAME, factory));
			return emptyEnvelope;
		}
		catch (org.apache.axis2.databinding.ADBException e)
		{
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	/* methods to provide back word compatibility */

	private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, vnm.Mtcharging_wsStub.SendSMS param,
			boolean optimizeContent)
			throws org.apache.axis2.AxisFault
	{

		try
		{

			org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
			emptyEnvelope.getBody().addChild(param.getOMElement(vnm.Mtcharging_wsStub.SendSMS.MY_QNAME, factory));
			return emptyEnvelope;
		}
		catch (org.apache.axis2.databinding.ADBException e)
		{
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	/* methods to provide back word compatibility */

	private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, vnm.Mtcharging_wsStub.Subscription param,
			boolean optimizeContent)
			throws org.apache.axis2.AxisFault
	{

		try
		{

			org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
			emptyEnvelope.getBody().addChild(param.getOMElement(vnm.Mtcharging_wsStub.Subscription.MY_QNAME, factory));
			return emptyEnvelope;
		}
		catch (org.apache.axis2.databinding.ADBException e)
		{
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	/* methods to provide back word compatibility */

	private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, vnm.Mtcharging_wsStub.SendWAP param,
			boolean optimizeContent)
			throws org.apache.axis2.AxisFault
	{

		try
		{

			org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
			emptyEnvelope.getBody().addChild(param.getOMElement(vnm.Mtcharging_wsStub.SendWAP.MY_QNAME, factory));
			return emptyEnvelope;
		}
		catch (org.apache.axis2.databinding.ADBException e)
		{
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	/* methods to provide back word compatibility */

	private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, vnm.Mtcharging_wsStub.GetStatus param,
			boolean optimizeContent)
			throws org.apache.axis2.AxisFault
	{

		try
		{

			org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
			emptyEnvelope.getBody().addChild(param.getOMElement(vnm.Mtcharging_wsStub.GetStatus.MY_QNAME, factory));
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

			if (vnm.Mtcharging_wsStub.SyncSubscriber.class.equals(type))
			{

				return vnm.Mtcharging_wsStub.SyncSubscriber.Factory.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (vnm.Mtcharging_wsStub.SyncSubscriberResponse.class.equals(type))
			{

				return vnm.Mtcharging_wsStub.SyncSubscriberResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (vnm.Mtcharging_wsStub.SendSMS.class.equals(type))
			{

				return vnm.Mtcharging_wsStub.SendSMS.Factory.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (vnm.Mtcharging_wsStub.SendSMSResponseE.class.equals(type))
			{

				return vnm.Mtcharging_wsStub.SendSMSResponseE.Factory.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (vnm.Mtcharging_wsStub.Subscription.class.equals(type))
			{

				return vnm.Mtcharging_wsStub.Subscription.Factory.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (vnm.Mtcharging_wsStub.SubscriptionResponse.class.equals(type))
			{

				return vnm.Mtcharging_wsStub.SubscriptionResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (vnm.Mtcharging_wsStub.SendWAP.class.equals(type))
			{

				return vnm.Mtcharging_wsStub.SendWAP.Factory.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (vnm.Mtcharging_wsStub.SendWAPResponseE.class.equals(type))
			{

				return vnm.Mtcharging_wsStub.SendWAPResponseE.Factory.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (vnm.Mtcharging_wsStub.GetStatus.class.equals(type))
			{

				return vnm.Mtcharging_wsStub.GetStatus.Factory.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (vnm.Mtcharging_wsStub.GetStatusResponse.class.equals(type))
			{

				return vnm.Mtcharging_wsStub.GetStatusResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());

			}

		}
		catch (java.lang.Exception e)
		{
			throw org.apache.axis2.AxisFault.makeFault(e);
		}
		return null;
	}

}
