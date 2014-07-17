/**
 * ProvisioningResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.crm.service.elcom.iwebservice;

public class ProvisioningResponse  implements java.io.Serializable {
    private java.lang.Integer NVasID;

    private java.lang.String isdn;

    private java.lang.Integer nErrorCode;

    private java.lang.String sErrorDetail;

    public ProvisioningResponse() {
    }

    public ProvisioningResponse(
           java.lang.Integer NVasID,
           java.lang.String isdn,
           java.lang.Integer nErrorCode,
           java.lang.String sErrorDetail) {
           this.NVasID = NVasID;
           this.isdn = isdn;
           this.nErrorCode = nErrorCode;
           this.sErrorDetail = sErrorDetail;
    }


    /**
     * Gets the NVasID value for this ProvisioningResponse.
     * 
     * @return NVasID
     */
    public java.lang.Integer getNVasID() {
        return NVasID;
    }


    /**
     * Sets the NVasID value for this ProvisioningResponse.
     * 
     * @param NVasID
     */
    public void setNVasID(java.lang.Integer NVasID) {
        this.NVasID = NVasID;
    }


    /**
     * Gets the isdn value for this ProvisioningResponse.
     * 
     * @return isdn
     */
    public java.lang.String getIsdn() {
        return isdn;
    }


    /**
     * Sets the isdn value for this ProvisioningResponse.
     * 
     * @param isdn
     */
    public void setIsdn(java.lang.String isdn) {
        this.isdn = isdn;
    }


    /**
     * Gets the nErrorCode value for this ProvisioningResponse.
     * 
     * @return nErrorCode
     */
    public java.lang.Integer getNErrorCode() {
        return nErrorCode;
    }


    /**
     * Sets the nErrorCode value for this ProvisioningResponse.
     * 
     * @param nErrorCode
     */
    public void setNErrorCode(java.lang.Integer nErrorCode) {
        this.nErrorCode = nErrorCode;
    }


    /**
     * Gets the sErrorDetail value for this ProvisioningResponse.
     * 
     * @return sErrorDetail
     */
    public java.lang.String getSErrorDetail() {
        return sErrorDetail;
    }


    /**
     * Sets the sErrorDetail value for this ProvisioningResponse.
     * 
     * @param sErrorDetail
     */
    public void setSErrorDetail(java.lang.String sErrorDetail) {
        this.sErrorDetail = sErrorDetail;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ProvisioningResponse)) return false;
        ProvisioningResponse other = (ProvisioningResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.NVasID==null && other.getNVasID()==null) || 
             (this.NVasID!=null &&
              this.NVasID.equals(other.getNVasID()))) &&
            ((this.isdn==null && other.getIsdn()==null) || 
             (this.isdn!=null &&
              this.isdn.equals(other.getIsdn()))) &&
            ((this.nErrorCode==null && other.getNErrorCode()==null) || 
             (this.nErrorCode!=null &&
              this.nErrorCode.equals(other.getNErrorCode()))) &&
            ((this.sErrorDetail==null && other.getSErrorDetail()==null) || 
             (this.sErrorDetail!=null &&
              this.sErrorDetail.equals(other.getSErrorDetail())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getNVasID() != null) {
            _hashCode += getNVasID().hashCode();
        }
        if (getIsdn() != null) {
            _hashCode += getIsdn().hashCode();
        }
        if (getNErrorCode() != null) {
            _hashCode += getNErrorCode().hashCode();
        }
        if (getSErrorDetail() != null) {
            _hashCode += getSErrorDetail().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ProvisioningResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://iwebservice.elcom.com", "ProvisioningResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NVasID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://iwebservice.elcom.com", "NVasID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isdn");
        elemField.setXmlName(new javax.xml.namespace.QName("http://iwebservice.elcom.com", "isdn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NErrorCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://iwebservice.elcom.com", "nErrorCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SErrorDetail");
        elemField.setXmlName(new javax.xml.namespace.QName("http://iwebservice.elcom.com", "sErrorDetail"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
