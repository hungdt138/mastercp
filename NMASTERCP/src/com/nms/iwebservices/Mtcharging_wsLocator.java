/**
 * Mtcharging_wsLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.nms.iwebservices;

public class Mtcharging_wsLocator extends org.apache.axis.client.Service implements com.nms.iwebservices.Mtcharging_ws {

    public Mtcharging_wsLocator() {
    }


    public Mtcharging_wsLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public Mtcharging_wsLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for mtcharging_wsHttpPort
    private java.lang.String mtcharging_wsHttpPort_address = "http://203.128.246.91:8088/mtcharging/services/mtcharging_ws";

    public java.lang.String getmtcharging_wsHttpPortAddress() {
        return mtcharging_wsHttpPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String mtcharging_wsHttpPortWSDDServiceName = "mtcharging_wsHttpPort";

    public java.lang.String getmtcharging_wsHttpPortWSDDServiceName() {
        return mtcharging_wsHttpPortWSDDServiceName;
    }

    public void setmtcharging_wsHttpPortWSDDServiceName(java.lang.String name) {
        mtcharging_wsHttpPortWSDDServiceName = name;
    }

    public com.nms.iwebservices.Mtcharging_wsPortType getmtcharging_wsHttpPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(mtcharging_wsHttpPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getmtcharging_wsHttpPort(endpoint);
    }

    public com.nms.iwebservices.Mtcharging_wsPortType getmtcharging_wsHttpPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.nms.iwebservices.Mtcharging_wsHttpBindingStub _stub = new com.nms.iwebservices.Mtcharging_wsHttpBindingStub(portAddress, this);
            _stub.setPortName(getmtcharging_wsHttpPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setmtcharging_wsHttpPortEndpointAddress(java.lang.String address) {
        mtcharging_wsHttpPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.nms.iwebservices.Mtcharging_wsPortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.nms.iwebservices.Mtcharging_wsHttpBindingStub _stub = new com.nms.iwebservices.Mtcharging_wsHttpBindingStub(new java.net.URL(mtcharging_wsHttpPort_address), this);
                _stub.setPortName(getmtcharging_wsHttpPortWSDDServiceName());
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
        if ("mtcharging_wsHttpPort".equals(inputPortName)) {
            return getmtcharging_wsHttpPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://iwebservices.nms.com", "mtcharging_ws");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://iwebservices.nms.com", "mtcharging_wsHttpPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("mtcharging_wsHttpPort".equals(portName)) {
            setmtcharging_wsHttpPortEndpointAddress(address);
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
