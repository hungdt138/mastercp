package com.crm.provisioning.cache;

import javax.jms.QueueConnection;
import org.apache.log4j.Logger;

import com.crm.kernel.queue.QueueFactory;

public class MQConnection implements PoolableObject {

    private QueueConnection queueConnection = null;

    private boolean hasError = false;
    private Logger log = Logger.getLogger(MQConnection.class.getName());

    public MQConnection() throws Exception {
        queueConnection = QueueFactory.createQueueConnection();
    }

    public void activate() {
        // TODO Auto-generated method stub
    }

    public void destroy() {
        try {
            QueueFactory.closeQueue(queueConnection);
        } catch (Exception e) {
            log.error(e);
        } finally {
            queueConnection = null;
        }
    }

    public void passivate() {
        // TODO Auto-generated method stub
    }

    public boolean validate() {
        if (!hasError) {
            return true;
        }
        return false;
    }

    public void markError() {
        hasError = true;
    }

    public QueueConnection getQueueConnection() {
        return queueConnection;
    }

    public void setQueueConnection(QueueConnection queueConnection) {
        this.queueConnection = queueConnection;
    }
}
