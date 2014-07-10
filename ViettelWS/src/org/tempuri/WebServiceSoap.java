/**
 * WebServiceSoap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public interface WebServiceSoap extends java.rmi.Remote {
    public java.lang.String insertMO(java.lang.String operator, int moID, java.lang.String srcNum, java.lang.String dstNum, java.lang.String cmdCode, java.lang.String msgContent, int isVirtual, int isCdr, java.lang.String userName, java.lang.String password) throws java.rmi.RemoteException;
    public java.lang.String insertMT(java.lang.String operator, int moID, java.lang.String srcNum, java.lang.String dstNum, java.lang.String cmdCode, java.lang.String msgTitle, int msgType, java.lang.String msgContent, int seqNum, int seqID, int isCdr, java.lang.String userName, java.lang.String password) throws java.rmi.RemoteException;
}
