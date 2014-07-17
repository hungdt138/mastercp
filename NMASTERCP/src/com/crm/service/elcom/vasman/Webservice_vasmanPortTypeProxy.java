package com.crm.service.elcom.vasman;

public class Webservice_vasmanPortTypeProxy implements com.crm.service.elcom.vasman.Webservice_vasmanPortType {
  private String _endpoint = null;
  private com.crm.service.elcom.vasman.Webservice_vasmanPortType webservice_vasmanPortType = null;
  
  public Webservice_vasmanPortTypeProxy() {
    _initWebservice_vasmanPortTypeProxy();
  }
  
  public Webservice_vasmanPortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initWebservice_vasmanPortTypeProxy();
  }
  
  private void _initWebservice_vasmanPortTypeProxy() {
    try {
      webservice_vasmanPortType = (new com.crm.service.elcom.vasman.Webservice_vasmanLocator()).getwebservice_vasmanHttpPort();
      if (webservice_vasmanPortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)webservice_vasmanPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)webservice_vasmanPortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (webservice_vasmanPortType != null)
      ((javax.xml.rpc.Stub)webservice_vasmanPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.crm.service.elcom.vasman.Webservice_vasmanPortType getWebservice_vasmanPortType() {
    if (webservice_vasmanPortType == null)
      _initWebservice_vasmanPortTypeProxy();
    return webservice_vasmanPortType;
  }
  
  public com.crm.service.elcom.iwebservice.ProvisioningResponse provisioning(com.crm.service.elcom.iwebservice.ProvisioningRequest in0) throws java.rmi.RemoteException{
    if (webservice_vasmanPortType == null)
      _initWebservice_vasmanPortTypeProxy();
    return webservice_vasmanPortType.provisioning(in0);
  }
  
  public com.crm.service.elcom.iwebservice.CheckAllVasStatusResponse checkAllVasStatus(com.crm.service.elcom.iwebservice.CheckAllVasStatusRequest in0) throws java.rmi.RemoteException{
    if (webservice_vasmanPortType == null)
      _initWebservice_vasmanPortTypeProxy();
    return webservice_vasmanPortType.checkAllVasStatus(in0);
  }
  
  public com.crm.service.elcom.iwebservice.CheckVasStatusResponse checkVasStatus(com.crm.service.elcom.iwebservice.CheckVasStatusRequest in0) throws java.rmi.RemoteException{
    if (webservice_vasmanPortType == null)
      _initWebservice_vasmanPortTypeProxy();
    return webservice_vasmanPortType.checkVasStatus(in0);
  }
  
  
}