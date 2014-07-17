/**
 * Mtcharging_wsPortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.nms.iwebservices;

public interface Mtcharging_wsPortType extends java.rmi.Remote {
    public com.nms.iwebservice.ServiceStatus getStatus(com.nms.iwebservice.ServiceRequest in0) throws java.rmi.RemoteException;
    public com.nms.iwebservice.SubscriptionResp subscription(com.nms.iwebservice.SubscriptionReq in0) throws java.rmi.RemoteException;
    public com.nms.iwebservice.SendSMSResponse sendSMS(com.nms.iwebservice.SendSMSRequest in0) throws java.rmi.RemoteException;
    public com.nms.iwebservice.SyncSubscriberResp syncSubscriber(com.nms.iwebservice.SyncSubscriberReq in0) throws java.rmi.RemoteException;
    public com.nms.iwebservice.SendWAPResponse sendWAP(com.nms.iwebservice.SendWAPRequest in0) throws java.rmi.RemoteException;
}
