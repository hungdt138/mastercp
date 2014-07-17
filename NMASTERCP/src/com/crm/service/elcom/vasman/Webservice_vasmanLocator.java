/**
 * Webservice_vasmanLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.crm.service.elcom.vasman;

public class Webservice_vasmanLocator extends org.apache.axis.client.Service implements com.crm.service.elcom.vasman.Webservice_vasman {

    public Webservice_vasmanLocator() {
    }


    public Webservice_vasmanLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public Webservice_vasmanLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for webservice_vasmanHttpPort
    private java.lang.String webservice_vasmanHttpPort_address = "http://10.8.13.32:8080/WSVasman/services/webservice_vasman";

    public java.lang.String getwebservice_vasmanHttpPortAddress() {
        return webservice_vasmanHttpPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String webservice_vasmanHttpPortWSDDServiceName = "webservice_vasmanHttpPort";

    public java.lang.String getwebservice_vasmanHttpPortWSDDServiceName() {
        return webservice_vasmanHttpPortWSDDServiceName;
    }

    public void setwebservice_vasmanHttpPortWSDDServiceName(java.lang.String name) {
        webservice_vasmanHttpPortWSDDServiceName = name;
    }

    public com.crm.service.elcom.vasman.Webservice_vasmanPortType getwebservice_vasmanHttpPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(webservice_vasmanHttpPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getwebservice_vasmanHttpPort(endpoint);
    }

    public com.crm.service.elcom.vasman.Webservice_vasmanPortType getwebservice_vasmanHttpPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.crm.service.elcom.vasman.Webservice_vasmanHttpBindingStub _stub = new com.crm.service.elcom.vasman.Webservice_vasmanHttpBindingStub(portAddress, this);
            _stub.setPortName(getwebservice_vasmanHttpPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setwebservice_vasmanHttpPortEndpointAddress(java.lang.String address) {
        webservice_vasmanHttpPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.crm.service.elcom.vasman.Webservice_vasmanPortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.crm.service.elcom.vasman.Webservice_vasmanHttpBindingStub _stub = new com.crm.service.elcom.vasman.Webservice_vasmanHttpBindingStub(new java.net.URL(webservice_vasmanHttpPort_address), this);
                _stub.setPortName(getwebservice_vasmanHttpPortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("webservice_vasmanHttpPort".equals(inputPortName)) {
            return getwebservice_vasmanHttpPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://vasman.elcom.com", "webservice_vasman");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://vasman.elcom.com", "webservice_vasmanHttpPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("webservice_vasmanHttpPort".equals(portName)) {
            setwebservice_vasmanHttpPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
