package org.tempuri;

public class WebServiceSoapProxy implements org.tempuri.WebServiceSoap {
  private String _endpoint = null;
  private org.tempuri.WebServiceSoap webServiceSoap = null;
  
  public WebServiceSoapProxy() {
    _initWebServiceSoapProxy();
  }
  
  public WebServiceSoapProxy(String endpoint) {
    _endpoint = endpoint;
    _initWebServiceSoapProxy();
  }
  
  private void _initWebServiceSoapProxy() {
    try {
      webServiceSoap = (new org.tempuri.WebServiceLocator()).getWebServiceSoap();
      if (webServiceSoap != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)webServiceSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)webServiceSoap)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (webServiceSoap != null)
      ((javax.xml.rpc.Stub)webServiceSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public org.tempuri.WebServiceSoap getWebServiceSoap() {
    if (webServiceSoap == null)
      _initWebServiceSoapProxy();
    return webServiceSoap;
  }
  
  public java.lang.String insertMO(java.lang.String operator, int moID, java.lang.String srcNum, java.lang.String dstNum, java.lang.String cmdCode, java.lang.String msgContent, int isVirtual, int isCdr, java.lang.String userName, java.lang.String password) throws java.rmi.RemoteException{
    if (webServiceSoap == null)
      _initWebServiceSoapProxy();
    return webServiceSoap.insertMO(operator, moID, srcNum, dstNum, cmdCode, msgContent, isVirtual, isCdr, userName, password);
  }
  
  public java.lang.String insertMT(java.lang.String operator, int moID, java.lang.String srcNum, java.lang.String dstNum, java.lang.String cmdCode, java.lang.String msgTitle, int msgType, java.lang.String msgContent, int seqNum, int seqID, int isCdr, java.lang.String userName, java.lang.String password) throws java.rmi.RemoteException{
    if (webServiceSoap == null)
      _initWebServiceSoapProxy();
    return webServiceSoap.insertMT(operator, moID, srcNum, dstNum, cmdCode, msgTitle, msgType, msgContent, seqNum, seqID, isCdr, userName, password);
  }
  
  
}