
/**
 * SendSms.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.4  Built on : Apr 26, 2008 (06:25:17 EDT)
 */
            
                package vinaphone.org.csapi.www.schema.parlayx.sms.send.v2_2.local;
            

            /**
            *  SendSms bean class
            */
        
        public  class SendSms
        implements org.apache.axis2.databinding.ADBBean{
        /* This type was generated from the piece of schema that had
                name = sendSms
                Namespace URI = http://www.csapi.org/schema/parlayx/sms/send/v2_2/local
                Namespace Prefix = ns3
                */
            

        private static java.lang.String generatePrefix(java.lang.String namespace) {
            if(namespace.equals("http://www.csapi.org/schema/parlayx/sms/send/v2_2/local")){
                return "ns3";
            }
            return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }

        

                        /**
                        * field for Addresses
                        * This was an Array!
                        */

                        
                                    protected org.apache.axis2.databinding.types.URI[] localAddresses ;
                                

                           /**
                           * Auto generated getter method
                           * @return org.apache.axis2.databinding.types.URI[]
                           */
                           public  org.apache.axis2.databinding.types.URI[] getAddresses(){
                               return localAddresses;
                           }

                           
                        


                               
                              /**
                               * validate the array for Addresses
                               */
                              protected void validateAddresses(org.apache.axis2.databinding.types.URI[] param){
                             
                              if ((param != null) && (param.length < 1)){
                                throw new java.lang.RuntimeException();
                              }
                              
                              }


                             /**
                              * Auto generated setter method
                              * @param param Addresses
                              */
                              public void setAddresses(org.apache.axis2.databinding.types.URI[] param){
                              
                                   validateAddresses(param);

                               
                                      this.localAddresses=param;
                              }

                               
                             
                             /**
                             * Auto generated add method for the array for convenience
                             * @param param org.apache.axis2.databinding.types.URI
                             */
                             public void addAddresses(org.apache.axis2.databinding.types.URI param){
                                   if (localAddresses == null){
                                   localAddresses = new org.apache.axis2.databinding.types.URI[]{};
                                   }

                            

                               java.util.List list =
                            org.apache.axis2.databinding.utils.ConverterUtil.toList(localAddresses);
                               list.add(param);
                               this.localAddresses =
                             (org.apache.axis2.databinding.types.URI[])list.toArray(
                            new org.apache.axis2.databinding.types.URI[list.size()]);

                             }
                             

                        /**
                        * field for SenderName
                        */

                        
                                    protected java.lang.String localSenderName ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localSenderNameTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getSenderName(){
                               return localSenderName;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param SenderName
                               */
                               public void setSenderName(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localSenderNameTracker = true;
                                       } else {
                                          localSenderNameTracker = false;
                                              
                                       }
                                   
                                            this.localSenderName=param;
                                    

                               }
                            

                        /**
                        * field for Charging
                        */

                        
                                    protected vinaphone.org.csapi.www.schema.parlayx.common.v2_1.ChargingInformation localCharging ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localChargingTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return vinaphone.org.csapi.www.schema.parlayx.common.v2_1.ChargingInformation
                           */
                           public  vinaphone.org.csapi.www.schema.parlayx.common.v2_1.ChargingInformation getCharging(){
                               return localCharging;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Charging
                               */
                               public void setCharging(vinaphone.org.csapi.www.schema.parlayx.common.v2_1.ChargingInformation param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localChargingTracker = true;
                                       } else {
                                          localChargingTracker = false;
                                              
                                       }
                                   
                                            this.localCharging=param;
                                    

                               }
                            

                        /**
                        * field for Message
                        */

                        
                                    protected java.lang.String localMessage ;
                                

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getMessage(){
                               return localMessage;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Message
                               */
                               public void setMessage(java.lang.String param){
                            
                                            this.localMessage=param;
                                    

                               }
                            

                        /**
                        * field for ReceiptRequest
                        */

                        
                                    protected vinaphone.org.csapi.www.schema.parlayx.common.v2_1.SimpleReference localReceiptRequest ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localReceiptRequestTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return vinaphone.org.csapi.www.schema.parlayx.common.v2_1.SimpleReference
                           */
                           public  vinaphone.org.csapi.www.schema.parlayx.common.v2_1.SimpleReference getReceiptRequest(){
                               return localReceiptRequest;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ReceiptRequest
                               */
                               public void setReceiptRequest(vinaphone.org.csapi.www.schema.parlayx.common.v2_1.SimpleReference param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localReceiptRequestTracker = true;
                                       } else {
                                          localReceiptRequestTracker = false;
                                              
                                       }
                                   
                                            this.localReceiptRequest=param;
                                    

                               }
                            

                        /**
                        * field for Encode
                        */

                        
                                    protected java.lang.String localEncode ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localEncodeTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getEncode(){
                               return localEncode;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Encode
                               */
                               public void setEncode(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localEncodeTracker = true;
                                       } else {
                                          localEncodeTracker = false;
                                              
                                       }
                                   
                                            this.localEncode=param;
                                    

                               }
                            

                        /**
                        * field for Sourceport
                        */

                        
                                    protected int localSourceport ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localSourceportTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return int
                           */
                           public  int getSourceport(){
                               return localSourceport;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Sourceport
                               */
                               public void setSourceport(int param){
                            
                                       // setting primitive attribute tracker to true
                                       
                                               if (param==java.lang.Integer.MIN_VALUE) {
                                           localSourceportTracker = false;
                                              
                                       } else {
                                          localSourceportTracker = true;
                                       }
                                   
                                            this.localSourceport=param;
                                    

                               }
                            

                        /**
                        * field for Destinationport
                        */

                        
                                    protected int localDestinationport ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localDestinationportTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return int
                           */
                           public  int getDestinationport(){
                               return localDestinationport;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Destinationport
                               */
                               public void setDestinationport(int param){
                            
                                       // setting primitive attribute tracker to true
                                       
                                               if (param==java.lang.Integer.MIN_VALUE) {
                                           localDestinationportTracker = false;
                                              
                                       } else {
                                          localDestinationportTracker = true;
                                       }
                                   
                                            this.localDestinationport=param;
                                    

                               }
                            

                        /**
                        * field for Esm_class
                        */

                        
                                    protected int localEsm_class ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localEsm_classTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return int
                           */
                           public  int getEsm_class(){
                               return localEsm_class;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Esm_class
                               */
                               public void setEsm_class(int param){
                            
                                       // setting primitive attribute tracker to true
                                       
                                               if (param==java.lang.Integer.MIN_VALUE) {
                                           localEsm_classTracker = false;
                                              
                                       } else {
                                          localEsm_classTracker = true;
                                       }
                                   
                                            this.localEsm_class=param;
                                    

                               }
                            

                        /**
                        * field for Data_coding
                        */

                        
                                    protected int localData_coding ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localData_codingTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return int
                           */
                           public  int getData_coding(){
                               return localData_coding;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Data_coding
                               */
                               public void setData_coding(int param){
                            
                                       // setting primitive attribute tracker to true
                                       
                                               if (param==java.lang.Integer.MIN_VALUE) {
                                           localData_codingTracker = false;
                                              
                                       } else {
                                          localData_codingTracker = true;
                                       }
                                   
                                            this.localData_coding=param;
                                    

                               }
                            

     /**
     * isReaderMTOMAware
     * @return true if the reader supports MTOM
     */
   public static boolean isReaderMTOMAware(javax.xml.stream.XMLStreamReader reader) {
        boolean isReaderMTOMAware = false;
        
        try{
          isReaderMTOMAware = java.lang.Boolean.TRUE.equals(reader.getProperty(org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
        }catch(java.lang.IllegalArgumentException e){
          isReaderMTOMAware = false;
        }
        return isReaderMTOMAware;
   }
     
     
        /**
        *
        * @param parentQName
        * @param factory
        * @return org.apache.axiom.om.OMElement
        */
       public org.apache.axiom.om.OMElement getOMElement (
               final javax.xml.namespace.QName parentQName,
               final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException{


        
               org.apache.axiom.om.OMDataSource dataSource =
                       new org.apache.axis2.databinding.ADBDataSource(this,parentQName){

                 public void serialize(org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
                       SendSms.this.serialize(parentQName,factory,xmlWriter);
                 }
               };
               return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(
               parentQName,factory,dataSource);
            
       }

         public void serialize(final javax.xml.namespace.QName parentQName,
                                       final org.apache.axiom.om.OMFactory factory,
                                       org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
                                throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
                           serialize(parentQName,factory,xmlWriter,false);
         }

         public void serialize(final javax.xml.namespace.QName parentQName,
                               final org.apache.axiom.om.OMFactory factory,
                               org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter,
                               boolean serializeType)
            throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
            
                


                java.lang.String prefix = null;
                java.lang.String namespace = null;
                

                    prefix = parentQName.getPrefix();
                    namespace = parentQName.getNamespaceURI();

                    if ((namespace != null) && (namespace.trim().length() > 0)) {
                        java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
                        if (writerPrefix != null) {
                            xmlWriter.writeStartElement(namespace, parentQName.getLocalPart());
                        } else {
                            if (prefix == null) {
                                prefix = generatePrefix(namespace);
                            }

                            xmlWriter.writeStartElement(prefix, parentQName.getLocalPart(), namespace);
                            xmlWriter.writeNamespace(prefix, namespace);
                            xmlWriter.setPrefix(prefix, namespace);
                        }
                    } else {
                        xmlWriter.writeStartElement(parentQName.getLocalPart());
                    }
                
                  if (serializeType){
               

                   java.lang.String namespacePrefix = registerPrefix(xmlWriter,"http://www.csapi.org/schema/parlayx/sms/send/v2_2/local");
                   if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)){
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           namespacePrefix+":sendSms",
                           xmlWriter);
                   } else {
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           "sendSms",
                           xmlWriter);
                   }

               
                   }
               
                             if (localAddresses!=null) {
                                   namespace = "http://www.csapi.org/schema/parlayx/sms/send/v2_2/local";
                                   boolean emptyNamespace = namespace == null || namespace.length() == 0;
                                   prefix =  emptyNamespace ? null : xmlWriter.getPrefix(namespace);
                                   for (int i = 0;i < localAddresses.length;i++){
                                        
                                            if (localAddresses[i] != null){
                                        
                                                if (!emptyNamespace) {
                                                    if (prefix == null) {
                                                        java.lang.String prefix2 = generatePrefix(namespace);

                                                        xmlWriter.writeStartElement(prefix2,"addresses", namespace);
                                                        xmlWriter.writeNamespace(prefix2, namespace);
                                                        xmlWriter.setPrefix(prefix2, namespace);

                                                    } else {
                                                        xmlWriter.writeStartElement(namespace,"addresses");
                                                    }

                                                } else {
                                                    xmlWriter.writeStartElement("addresses");
                                                }

                                            
                                                        xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localAddresses[i]));
                                                    
                                                xmlWriter.writeEndElement();
                                              
                                                } else {
                                                   
                                                           throw new org.apache.axis2.databinding.ADBException("addresses cannot be null!!");
                                                       
                                                }

                                   }
                             } else {
                                 
                                         throw new org.apache.axis2.databinding.ADBException("addresses cannot be null!!");
                                    
                             }

                         if (localSenderNameTracker){
                                    namespace = "http://www.csapi.org/schema/parlayx/sms/send/v2_2/local";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"senderName", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"senderName");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("senderName");
                                    }
                                

                                          if (localSenderName==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("senderName cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localSenderName);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localChargingTracker){
                                            if (localCharging==null){
                                                 throw new org.apache.axis2.databinding.ADBException("charging cannot be null!!");
                                            }
                                           localCharging.serialize(new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/sms/send/v2_2/local","charging"),
                                               factory,xmlWriter);
                                        }
                                    namespace = "http://www.csapi.org/schema/parlayx/sms/send/v2_2/local";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"message", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"message");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("message");
                                    }
                                

                                          if (localMessage==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("message cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localMessage);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                              if (localReceiptRequestTracker){
                                            if (localReceiptRequest==null){
                                                 throw new org.apache.axis2.databinding.ADBException("receiptRequest cannot be null!!");
                                            }
                                           localReceiptRequest.serialize(new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/sms/send/v2_2/local","receiptRequest"),
                                               factory,xmlWriter);
                                        } if (localEncodeTracker){
                                    namespace = "http://www.csapi.org/schema/parlayx/sms/send/v2_2/local";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"encode", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"encode");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("encode");
                                    }
                                

                                          if (localEncode==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("encode cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localEncode);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localSourceportTracker){
                                    namespace = "http://www.csapi.org/schema/parlayx/sms/send/v2_2/local";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"sourceport", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"sourceport");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("sourceport");
                                    }
                                
                                               if (localSourceport==java.lang.Integer.MIN_VALUE) {
                                           
                                                         throw new org.apache.axis2.databinding.ADBException("sourceport cannot be null!!");
                                                      
                                               } else {
                                                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSourceport));
                                               }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localDestinationportTracker){
                                    namespace = "http://www.csapi.org/schema/parlayx/sms/send/v2_2/local";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"destinationport", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"destinationport");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("destinationport");
                                    }
                                
                                               if (localDestinationport==java.lang.Integer.MIN_VALUE) {
                                           
                                                         throw new org.apache.axis2.databinding.ADBException("destinationport cannot be null!!");
                                                      
                                               } else {
                                                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localDestinationport));
                                               }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localEsm_classTracker){
                                    namespace = "http://www.csapi.org/schema/parlayx/sms/send/v2_2/local";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"esm_class", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"esm_class");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("esm_class");
                                    }
                                
                                               if (localEsm_class==java.lang.Integer.MIN_VALUE) {
                                           
                                                         throw new org.apache.axis2.databinding.ADBException("esm_class cannot be null!!");
                                                      
                                               } else {
                                                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localEsm_class));
                                               }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localData_codingTracker){
                                    namespace = "http://www.csapi.org/schema/parlayx/sms/send/v2_2/local";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"data_coding", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"data_coding");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("data_coding");
                                    }
                                
                                               if (localData_coding==java.lang.Integer.MIN_VALUE) {
                                           
                                                         throw new org.apache.axis2.databinding.ADBException("data_coding cannot be null!!");
                                                      
                                               } else {
                                                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localData_coding));
                                               }
                                    
                                   xmlWriter.writeEndElement();
                             }
                    xmlWriter.writeEndElement();
               

        }

         /**
          * Util method to write an attribute with the ns prefix
          */
          private void writeAttribute(java.lang.String prefix,java.lang.String namespace,java.lang.String attName,
                                      java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
              if (xmlWriter.getPrefix(namespace) == null) {
                       xmlWriter.writeNamespace(prefix, namespace);
                       xmlWriter.setPrefix(prefix, namespace);

              }

              xmlWriter.writeAttribute(namespace,attName,attValue);

         }

        /**
          * Util method to write an attribute without the ns prefix
          */
          private void writeAttribute(java.lang.String namespace,java.lang.String attName,
                                      java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
                if (namespace.equals(""))
              {
                  xmlWriter.writeAttribute(attName,attValue);
              }
              else
              {
                  registerPrefix(xmlWriter, namespace);
                  xmlWriter.writeAttribute(namespace,attName,attValue);
              }
          }


           /**
             * Util method to write an attribute without the ns prefix
             */
            private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
                                             javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

                java.lang.String attributeNamespace = qname.getNamespaceURI();
                java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
                if (attributePrefix == null) {
                    attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
                }
                java.lang.String attributeValue;
                if (attributePrefix.trim().length() > 0) {
                    attributeValue = attributePrefix + ":" + qname.getLocalPart();
                } else {
                    attributeValue = qname.getLocalPart();
                }

                if (namespace.equals("")) {
                    xmlWriter.writeAttribute(attName, attributeValue);
                } else {
                    registerPrefix(xmlWriter, namespace);
                    xmlWriter.writeAttribute(namespace, attName, attributeValue);
                }
            }
        /**
         *  method to handle Qnames
         */

        private void writeQName(javax.xml.namespace.QName qname,
                                javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            java.lang.String namespaceURI = qname.getNamespaceURI();
            if (namespaceURI != null) {
                java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
                if (prefix == null) {
                    prefix = generatePrefix(namespaceURI);
                    xmlWriter.writeNamespace(prefix, namespaceURI);
                    xmlWriter.setPrefix(prefix,namespaceURI);
                }

                if (prefix.trim().length() > 0){
                    xmlWriter.writeCharacters(prefix + ":" + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                } else {
                    // i.e this is the default namespace
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                }

            } else {
                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
            }
        }

        private void writeQNames(javax.xml.namespace.QName[] qnames,
                                 javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

            if (qnames != null) {
                // we have to store this data until last moment since it is not possible to write any
                // namespace data after writing the charactor data
                java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
                java.lang.String namespaceURI = null;
                java.lang.String prefix = null;

                for (int i = 0; i < qnames.length; i++) {
                    if (i > 0) {
                        stringToWrite.append(" ");
                    }
                    namespaceURI = qnames[i].getNamespaceURI();
                    if (namespaceURI != null) {
                        prefix = xmlWriter.getPrefix(namespaceURI);
                        if ((prefix == null) || (prefix.length() == 0)) {
                            prefix = generatePrefix(namespaceURI);
                            xmlWriter.writeNamespace(prefix, namespaceURI);
                            xmlWriter.setPrefix(prefix,namespaceURI);
                        }

                        if (prefix.trim().length() > 0){
                            stringToWrite.append(prefix).append(":").append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        } else {
                            stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        }
                    } else {
                        stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                    }
                }
                xmlWriter.writeCharacters(stringToWrite.toString());
            }

        }


         /**
         * Register a namespace prefix
         */
         private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace) throws javax.xml.stream.XMLStreamException {
                java.lang.String prefix = xmlWriter.getPrefix(namespace);

                if (prefix == null) {
                    prefix = generatePrefix(namespace);

                    while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null) {
                        prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
                    }

                    xmlWriter.writeNamespace(prefix, namespace);
                    xmlWriter.setPrefix(prefix, namespace);
                }

                return prefix;
            }


  
        /**
        * databinding method to get an XML representation of this object
        *
        */
        public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
                    throws org.apache.axis2.databinding.ADBException{


        
                 java.util.ArrayList elementList = new java.util.ArrayList();
                 java.util.ArrayList attribList = new java.util.ArrayList();

                
                            if (localAddresses!=null){
                                  for (int i = 0;i < localAddresses.length;i++){
                                      
                                         if (localAddresses[i] != null){
                                          elementList.add(new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/sms/send/v2_2/local",
                                                                              "addresses"));
                                          elementList.add(
                                          org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localAddresses[i]));
                                          } else {
                                             
                                                    throw new org.apache.axis2.databinding.ADBException("addresses cannot be null!!");
                                                
                                          }
                                      

                                  }
                            } else {
                              
                                    throw new org.apache.axis2.databinding.ADBException("addresses cannot be null!!");
                                
                            }

                         if (localSenderNameTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/sms/send/v2_2/local",
                                                                      "senderName"));
                                 
                                        if (localSenderName != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSenderName));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("senderName cannot be null!!");
                                        }
                                    } if (localChargingTracker){
                            elementList.add(new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/sms/send/v2_2/local",
                                                                      "charging"));
                            
                            
                                    if (localCharging==null){
                                         throw new org.apache.axis2.databinding.ADBException("charging cannot be null!!");
                                    }
                                    elementList.add(localCharging);
                                }
                                      elementList.add(new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/sms/send/v2_2/local",
                                                                      "message"));
                                 
                                        if (localMessage != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localMessage));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("message cannot be null!!");
                                        }
                                     if (localReceiptRequestTracker){
                            elementList.add(new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/sms/send/v2_2/local",
                                                                      "receiptRequest"));
                            
                            
                                    if (localReceiptRequest==null){
                                         throw new org.apache.axis2.databinding.ADBException("receiptRequest cannot be null!!");
                                    }
                                    elementList.add(localReceiptRequest);
                                } if (localEncodeTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/sms/send/v2_2/local",
                                                                      "encode"));
                                 
                                        if (localEncode != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localEncode));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("encode cannot be null!!");
                                        }
                                    } if (localSourceportTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/sms/send/v2_2/local",
                                                                      "sourceport"));
                                 
                                elementList.add(
                                   org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSourceport));
                            } if (localDestinationportTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/sms/send/v2_2/local",
                                                                      "destinationport"));
                                 
                                elementList.add(
                                   org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localDestinationport));
                            } if (localEsm_classTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/sms/send/v2_2/local",
                                                                      "esm_class"));
                                 
                                elementList.add(
                                   org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localEsm_class));
                            } if (localData_codingTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/sms/send/v2_2/local",
                                                                      "data_coding"));
                                 
                                elementList.add(
                                   org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localData_coding));
                            }

                return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(), attribList.toArray());
            
            

        }

  

     /**
      *  Factory class that keeps the parse method
      */
    public static class Factory{

        
        

        /**
        * static method to create the object
        * Precondition:  If this object is an element, the current or next start element starts this object and any intervening reader events are ignorable
        *                If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
        * Postcondition: If this object is an element, the reader is positioned at its end element
        *                If this object is a complex type, the reader is positioned at the end element of its outer element
        */
        public static SendSms parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            SendSms object =
                new SendSms();

            int event;
            java.lang.String nillableValue = null;
            java.lang.String prefix ="";
            java.lang.String namespaceuri ="";
            try {
                
                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                
                if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","type")!=null){
                  java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                        "type");
                  if (fullTypeName!=null){
                    java.lang.String nsPrefix = null;
                    if (fullTypeName.indexOf(":") > -1){
                        nsPrefix = fullTypeName.substring(0,fullTypeName.indexOf(":"));
                    }
                    nsPrefix = nsPrefix==null?"":nsPrefix;

                    java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":")+1);
                    
                            if (!"sendSms".equals(type)){
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (SendSms)vinaphone.org.csapi.www.schema.parlayx.sms.send.v2_2.local.ExtensionMapper.getTypeObject(
                                     nsUri,type,reader);
                              }
                        

                  }
                

                }

                

                
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();
                

                 
                    
                    reader.next();
                
                        java.util.ArrayList list1 = new java.util.ArrayList();
                    
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/sms/send/v2_2/local","addresses").equals(reader.getName())){
                                
                                    
                                    
                                    // Process the array and step past its final element's end.
                                    list1.add(reader.getElementText());
                                            
                                            //loop until we find a start element that is not part of this array
                                            boolean loopDone1 = false;
                                            while(!loopDone1){
                                                // Ensure we are at the EndElement
                                                while (!reader.isEndElement()){
                                                    reader.next();
                                                }
                                                // Step out of this element
                                                reader.next();
                                                // Step to next element event.
                                                while (!reader.isStartElement() && !reader.isEndElement())
                                                    reader.next();
                                                if (reader.isEndElement()){
                                                    //two continuous end elements means we are exiting the xml structure
                                                    loopDone1 = true;
                                                } else {
                                                    if (new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/sms/send/v2_2/local","addresses").equals(reader.getName())){
                                                         list1.add(reader.getElementText());
                                                        
                                                    }else{
                                                        loopDone1 = true;
                                                    }
                                                }
                                            }
                                            // call the converter utility  to convert and set the array
                                            
                                            object.setAddresses((org.apache.axis2.databinding.types.URI[])
                                                org.apache.axis2.databinding.utils.ConverterUtil.convertToArray(
                                                            org.apache.axis2.databinding.types.URI.class,list1));
                                                
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/sms/send/v2_2/local","senderName").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setSenderName(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/sms/send/v2_2/local","charging").equals(reader.getName())){
                                
                                                object.setCharging(vinaphone.org.csapi.www.schema.parlayx.common.v2_1.ChargingInformation.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/sms/send/v2_2/local","message").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setMessage(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/sms/send/v2_2/local","receiptRequest").equals(reader.getName())){
                                
                                                object.setReceiptRequest(vinaphone.org.csapi.www.schema.parlayx.common.v2_1.SimpleReference.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/sms/send/v2_2/local","encode").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setEncode(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/sms/send/v2_2/local","sourceport").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setSourceport(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToInt(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                               object.setSourceport(java.lang.Integer.MIN_VALUE);
                                           
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/sms/send/v2_2/local","destinationport").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setDestinationport(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToInt(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                               object.setDestinationport(java.lang.Integer.MIN_VALUE);
                                           
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/sms/send/v2_2/local","esm_class").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setEsm_class(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToInt(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                               object.setEsm_class(java.lang.Integer.MIN_VALUE);
                                           
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/sms/send/v2_2/local","data_coding").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setData_coding(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToInt(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                               object.setData_coding(java.lang.Integer.MIN_VALUE);
                                           
                                    }
                                  
                            while (!reader.isStartElement() && !reader.isEndElement())
                                reader.next();
                            
                                if (reader.isStartElement())
                                // A start element we are not expecting indicates a trailing invalid property
                                throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                            



            } catch (javax.xml.stream.XMLStreamException e) {
                throw new java.lang.Exception(e);
            }

            return object;
        }

        }//end of factory class

        

        }
           
          