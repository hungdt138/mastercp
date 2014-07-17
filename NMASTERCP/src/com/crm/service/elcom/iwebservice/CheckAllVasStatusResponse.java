/**
 * CheckAllVasStatusResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.crm.service.elcom.iwebservice;

public class CheckAllVasStatusResponse  implements java.io.Serializable {
    private int[] NStatus;

    private int[] NVasID;

    private java.lang.String isdn;

    private java.lang.Integer nErrorCode;

    private java.lang.String sErrorDetail;

    public CheckAllVasStatusResponse() {
    }

    public CheckAllVasStatusResponse(
           int[] NStatus,
           int[] NVasID,
           java.lang.String isdn,
           java.lang.Integer nErrorCode,
           java.lang.String sErrorDetail) {
           this.NStatus = NStatus;
           this.NVasID = NVasID;
           this.isdn = isdn;
           this.nErrorCode = nErrorCode;
           this.sErrorDetail = sErrorDetail;
    }


    /**
     * Gets the NStatus value for this CheckAllVasStatusResponse.
     * 
     * @return NStatus
     */
    public int[] getNStatus() {
        return NStatus;
    }


    /**
     * Sets the NStatus value for this CheckAllVasStatusResponse.
     * 
     * @param NStatus
     */
    public void setNStatus(int[] NStatus) {
        this.NStatus = NStatus;
    }


    /**
     * Gets the NVasID value for this CheckAllVasStatusResponse.
     * 
     * @return NVasID
     */
    public int[] getNVasID() {
        return NVasID;
    }


    /**
     * Sets the NVasID value for this CheckAllVasStatusResponse.
     * 
     * @param NVasID
     */
    public void setNVasID(int[] NVasID) {
        this.NVasID = NVasID;
    }


    /**
     * Gets the isdn value for this CheckAllVasStatusResponse.
     * 
     * @return isdn
     */
    public java.lang.String getIsdn() {
        return isdn;
    }


    /**
     * Sets the isdn value for this CheckAllVasStatusResponse.
     * 
     * @param isdn
     */
    public void setIsdn(java.lang.String isdn) {
        this.isdn = isdn;
    }


    /**
     * Gets the nErrorCode value for this CheckAllVasStatusResponse.
     * 
     * @return nErrorCode
     */
    public java.lang.Integer getNErrorCode() {
        return nErrorCode;
    }


    /**
     * Sets the nErrorCode value for this CheckAllVasStatusResponse.
     * 
     * @param nErrorCode
     */
    public void setNErrorCode(java.lang.Integer nErrorCode) {
        this.nErrorCode = nErrorCode;
    }


    /**
     * Gets the sErrorDetail value for this CheckAllVasStatusResponse.
     * 
     * @return sErrorDetail
     */
    public java.lang.String getSErrorDetail() {
        return sErrorDetail;
    }


    /**
     * Sets the sErrorDetail value for this CheckAllVasStatusResponse.
     * 
     * @param sErrorDetail
     */
    public void setSErrorDetail(java.lang.String sErrorDetail) {
        this.sErrorDetail = sErrorDetail;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CheckAllVasStatusResponse)) return false;
        CheckAllVasStatusResponse other = (CheckAllVasStatusResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.NStatus==null && other.getNStatus()==null) || 
             (this.NStatus!=null &&
              java.util.Arrays.equals(this.NStatus, other.getNStatus()))) &&
            ((this.NVasID==null && other.getNVasID()==null) || 
             (this.NVasID!=null &&
              java.util.Arrays.equals(this.NVasID, other.getNVasID()))) &&
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
        if (getNStatus() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getNStatus());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getNStatus(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getNVasID() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getNVasID());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getNVasID(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
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
        new org.apache.axis.description.TypeDesc(CheckAllVasStatusResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://iwebservice.elcom.com", "CheckAllVasStatusResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("http://iwebservice.elcom.com", "NStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://vasman.elcom.com", "int"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NVasID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://iwebservice.elcom.com", "NVasID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://vasman.elcom.com", "int"));
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
