package vms.org.csapi.www.wsdl.parlayx.wap_push.send.v1_0.service;

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
