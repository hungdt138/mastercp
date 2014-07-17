package vms.org.csapi.www.wsdl.parlayx.sms.send.v2_2.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;




public class Security {
	
	public static byte[] encryptSHA1(String source) {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
			messageDigest.update(source.getBytes());
			return messageDigest.digest();
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}
	
//	public static String encryptBase64(byte[] source) {
//		BASE64Encoder encoder = new BASE64Encoder();
//		return encoder.encode(source);
//	}
	
//	public static String encryptS1B(String srcPwd) {
//		return encryptBase64(encryptSHA1(srcPwd));
//	}
//	
	

}
