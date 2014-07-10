/**
 * 
 */
package com.crm.provisioning.thread;

import javax.jms.Message;

import com.crm.kernel.message.Constants;
import com.crm.kernel.queue.QueueFactory;
import com.crm.provisioning.message.CommandMessage;

/**
 * @author hungdt
 *
 */
public class ChargingInstance extends CommandInstance {

	
	
	public ChargingInstance() throws Exception {
		super();
		//this.getDispatcher().logMonitor(getLogs());
	}
}
