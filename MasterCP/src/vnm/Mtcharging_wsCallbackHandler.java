/**
 * Mtcharging_wsCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.4  Built on : Apr 26, 2008 (06:24:30 EDT)
 */

package vnm;

/**
 * Mtcharging_wsCallbackHandler Callback class, Users can extend this class and
 * implement their own receiveResult and receiveError methods.
 */
public abstract class Mtcharging_wsCallbackHandler
{

	protected Object	clientData;

	/**
	 * User can pass in any object that needs to be accessed once the
	 * NonBlocking Web service call is finished and appropriate method of this
	 * CallBack is called.
	 * 
	 * @param clientData
	 *            Object mechanism by which the user can pass in user data that
	 *            will be avilable at the time this callback is called.
	 */
	public Mtcharging_wsCallbackHandler(Object clientData)
	{
		this.clientData = clientData;
	}

	/**
	 * Please use this constructor if you don't want to set any clientData
	 */
	public Mtcharging_wsCallbackHandler()
	{
		this.clientData = null;
	}

	/**
	 * Get the client data
	 */

	public Object getClientData()
	{
		return clientData;
	}

	/**
	 * auto generated Axis2 call back method for syncSubscriber method override
	 * this method for handling normal response from syncSubscriber operation
	 */
	public void receiveResultsyncSubscriber(
			vnm.Mtcharging_wsStub.SyncSubscriberResponse result
			)
	{
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from syncSubscriber operation
	 */
	public void receiveErrorsyncSubscriber(java.lang.Exception e)
	{
	}

	/**
	 * auto generated Axis2 call back method for sendSMS method override this
	 * method for handling normal response from sendSMS operation
	 */
	public void receiveResultsendSMS(
			vnm.Mtcharging_wsStub.SendSMSResponseE result
			)
	{
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from sendSMS operation
	 */
	public void receiveErrorsendSMS(java.lang.Exception e)
	{
	}

	/**
	 * auto generated Axis2 call back method for subscription method override
	 * this method for handling normal response from subscription operation
	 */
	public void receiveResultsubscription(
			vnm.Mtcharging_wsStub.SubscriptionResponse result
			)
	{
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from subscription operation
	 */
	public void receiveErrorsubscription(java.lang.Exception e)
	{
	}

	/**
	 * auto generated Axis2 call back method for sendWAP method override this
	 * method for handling normal response from sendWAP operation
	 */
	public void receiveResultsendWAP(
			vnm.Mtcharging_wsStub.SendWAPResponseE result
			)
	{
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from sendWAP operation
	 */
	public void receiveErrorsendWAP(java.lang.Exception e)
	{
	}

	/**
	 * auto generated Axis2 call back method for getStatus method override this
	 * method for handling normal response from getStatus operation
	 */
	public void receiveResultgetStatus(
			vnm.Mtcharging_wsStub.GetStatusResponse result
			)
	{
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from getStatus operation
	 */
	public void receiveErrorgetStatus(java.lang.Exception e)
	{
	}

}
