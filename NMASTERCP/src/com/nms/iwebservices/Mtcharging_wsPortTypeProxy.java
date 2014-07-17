package com.nms.iwebservices;

public class Mtcharging_wsPortTypeProxy implements com.nms.iwebservices.Mtcharging_wsPortType {
  private String _endpoint = null;
  private com.nms.iwebservices.Mtcharging_wsPortType mtcharging_wsPortType = null;
  
  public Mtcharging_wsPortTypeProxy() {
    _initMtcharging_wsPortTypeProxy();
  }
  
  public Mtcharging_wsPortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initMtcharging_wsPortTypeProxy();
  }
  
  private void _initMtcharging_wsPortTypeProxy() {
    try {
      mtcharging_wsPortType = (new com.nms.iwebservices.Mtcharging_wsLocator()).getmtcharging_wsHttpPort();
      if (mtcharging_wsPortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)mtcharging_wsPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)mtcharging_wsPortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (mtcharging_wsPortType != null)
      ((javax.xml.rpc.Stub)mtcharging_wsPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.nms.iwebservices.Mtcharging_wsPortType getMtcharging_wsPortType() {
    if (mtcharging_wsPortType == null)
      _initMtcharging_wsPortTypeProxy();
    return mtcharging_wsPortType;
  }
  
  public com.nms.iwebservice.ServiceStatus getStatus(com.nms.iwebservice.ServiceRequest in0) throws java.rmi.RemoteException{
    if (mtcharging_wsPortType == null)
      _initMtcharging_wsPortTypeProxy();
    return mtcharging_wsPortType.getStatus(in0);
  }
  
  public com.nms.iwebservice.SubscriptionResp subscription(com.nms.iwebservice.SubscriptionReq in0) throws java.rmi.RemoteException{
    if (mtcharging_wsPortType == null)
      _initMtcharging_wsPortTypeProxy();
    return mtcharging_wsPortType.subscription(in0);
  }
  
  public com.nms.iwebservice.SendSMSResponse sendSMS(com.nms.iwebservice.SendSMSRequest in0) throws java.rmi.RemoteException{
    if (mtcharging_wsPortType == null)
      _initMtcharging_wsPortTypeProxy();
    return mtcharging_wsPortType.sendSMS(in0);
  }
  
  public com.nms.iwebservice.SyncSubscriberResp syncSubscriber(com.nms.iwebservice.SyncSubscriberReq in0) throws java.rmi.RemoteException{
    if (mtcharging_wsPortType == null)
      _initMtcharging_wsPortTypeProxy();
    return mtcharging_wsPortType.syncSubscriber(in0);
  }
  
  public com.nms.iwebservice.SendWAPResponse sendWAP(com.nms.iwebservice.SendWAPRequest in0) throws java.rmi.RemoteException{
    if (mtcharging_wsPortType == null)
      _initMtcharging_wsPortTypeProxy();
    return mtcharging_wsPortType.sendWAP(in0);
  }
  
  
}