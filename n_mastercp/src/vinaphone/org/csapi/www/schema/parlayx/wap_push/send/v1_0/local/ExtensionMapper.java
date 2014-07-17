
/**
 * ExtensionMapper.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.4  Built on : Apr 26, 2008 (06:25:17 EDT)
 */

            package vinaphone.org.csapi.www.schema.parlayx.wap_push.send.v1_0.local;
            /**
            *  ExtensionMapper class
            */
        
        public  class ExtensionMapper{

          public static java.lang.Object getTypeObject(java.lang.String namespaceURI,
                                                       java.lang.String typeName,
                                                       javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{

              
                  if (
                  "http://www.csapi.org/schema/parlayx/wap_push/v1_0".equals(namespaceURI) &&
                  "DeliveryStatus".equals(typeName)){
                   
                            return  vinaphone.org.csapi.www.schema.parlayx.wap_push.v1_0.DeliveryStatus.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://www.csapi.org/schema/parlayx/wap_push/send/v1_0/local".equals(namespaceURI) &&
                  "sendPushMessageResponse".equals(typeName)){
                   
                            return  vinaphone.org.csapi.www.schema.parlayx.wap_push.send.v1_0.local.SendPushMessageResponse.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://www.csapi.org/schema/parlayx/common/v2_1".equals(namespaceURI) &&
                  "SimpleReference".equals(typeName)){
                   
                            return  vinaphone.org.csapi.www.schema.parlayx.common.v2_1.SimpleReference.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://www.csapi.org/schema/parlayx/common/v2_1".equals(namespaceURI) &&
                  "ServiceException".equals(typeName)){
                   
                            return  vinaphone.org.csapi.www.schema.parlayx.common.v2_1.ServiceException.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://www.csapi.org/schema/parlayx/common/v2_1".equals(namespaceURI) &&
                  "TimeMetrics".equals(typeName)){
                   
                            return  vinaphone.org.csapi.www.schema.parlayx.common.v2_1.TimeMetrics.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://www.csapi.org/schema/parlayx/wap_push/v1_0".equals(namespaceURI) &&
                  "DeliveryInformation".equals(typeName)){
                   
                            return  vinaphone.org.csapi.www.schema.parlayx.wap_push.v1_0.DeliveryInformation.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://www.csapi.org/schema/parlayx/common/v2_1".equals(namespaceURI) &&
                  "PolicyException".equals(typeName)){
                   
                            return  vinaphone.org.csapi.www.schema.parlayx.common.v2_1.PolicyException.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://www.csapi.org/schema/parlayx/wap_push/v1_0".equals(namespaceURI) &&
                  "MessagePriority".equals(typeName)){
                   
                            return  vinaphone.org.csapi.www.schema.parlayx.wap_push.v1_0.MessagePriority.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://www.csapi.org/schema/parlayx/common/v2_1".equals(namespaceURI) &&
                  "ServiceError".equals(typeName)){
                   
                            return  vinaphone.org.csapi.www.schema.parlayx.common.v2_1.ServiceError.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://www.csapi.org/schema/parlayx/common/v2_1".equals(namespaceURI) &&
                  "ChargingInformation".equals(typeName)){
                   
                            return  vinaphone.org.csapi.www.schema.parlayx.common.v2_1.ChargingInformation.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://www.csapi.org/schema/parlayx/common/v2_1".equals(namespaceURI) &&
                  "TimeMetric".equals(typeName)){
                   
                            return  vinaphone.org.csapi.www.schema.parlayx.common.v2_1.TimeMetric.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://www.csapi.org/schema/parlayx/wap_push/send/v1_0/local".equals(namespaceURI) &&
                  "getPushMessageDeliveryStatusResponse".equals(typeName)){
                   
                            return  vinaphone.org.csapi.www.schema.parlayx.wap_push.send.v1_0.local.GetPushMessageDeliveryStatusResponse.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://www.csapi.org/schema/parlayx/wap_push/send/v1_0/local".equals(namespaceURI) &&
                  "getPushMessageDeliveryStatus".equals(typeName)){
                   
                            return  vinaphone.org.csapi.www.schema.parlayx.wap_push.send.v1_0.local.GetPushMessageDeliveryStatus.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://www.csapi.org/schema/parlayx/wap_push/send/v1_0/local".equals(namespaceURI) &&
                  "sendPushMessage".equals(typeName)){
                   
                            return  vinaphone.org.csapi.www.schema.parlayx.wap_push.send.v1_0.local.SendPushMessage.Factory.parse(reader);
                        

                  }

              
             throw new org.apache.axis2.databinding.ADBException("Unsupported type " + namespaceURI + " " + typeName);
          }

        }
    