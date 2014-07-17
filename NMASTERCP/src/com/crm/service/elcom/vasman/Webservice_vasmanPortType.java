/**
 * Webservice_vasmanPortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.crm.service.elcom.vasman;

public interface Webservice_vasmanPortType extends java.rmi.Remote {
    public com.crm.service.elcom.iwebservice.ProvisioningResponse provisioning(com.crm.service.elcom.iwebservice.ProvisioningRequest in0) throws java.rmi.RemoteException;
    public com.crm.service.elcom.iwebservice.CheckAllVasStatusResponse checkAllVasStatus(com.crm.service.elcom.iwebservice.CheckAllVasStatusRequest in0) throws java.rmi.RemoteException;
    public com.crm.service.elcom.iwebservice.CheckVasStatusResponse checkVasStatus(com.crm.service.elcom.iwebservice.CheckVasStatusRequest in0) throws java.rmi.RemoteException;
}
