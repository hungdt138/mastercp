/**
 * ----------------------------------------------------------------- 
 * @ Copyright(c) 2013 Vietnamobile. JSC. All Rights Reserved.
 * ----------------------------------------------------------------- 
 * Date Author Version
 * ------------------------------------- 
 * Sep 18, 2013 HaiPV 1.0 
 * -------------------------------------
 */
package com.crm.provisioning.impl.mobifone.otp;

import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

/**
 * @author HaiPV
 */
public class OTPService {
	private static final int INVALID_REQUEST_ID_407 = 407;
	private static final int OK_200 = 200;
	private static final int BAD_400 = 400;
	private static final int UNAUTHORIZED_401 = 401;
	private static final int INVALID_402 = 402;
	private static final int FORBIDDEN_403 = 403;
	private static final int METHOD_NOT_ALLOWED_405 = 405;
	private static final int NOT_ACCEPTABLE_406 = 406;
	private static final int TIMEOUT_VALUE = 60000;

	private HttpURLConnection con = null;
	
	/**
	 * To send a OTPRequest to Violet, Partner need to send and HTTP GET request to Violet’s HTTP server.
	 * 
	 * @param otpLink
	 *            http://xx.xx.xx.xx/OTPRequest.
	 * @param msisdn
	 *            Mobile number who send the message.
	 * @param request_id
	 *            ID of Partner's request.
	 * @param product_code
	 *            Partner's product code.
	 * @param action
	 *            register or unregister.
	 * @param username
	 *            Partner's login name.
	 * @param salt
	 *            to create password MD5(username + request_id + action + msisdn + product_code + salt).
	 * @return Responding Respond for OPT request.
	 * @throws Exception.
	 */
	private String createOTPRequest(final String otpLink, final String msisdn, final String request_id,
			final String product_code, final String action, final String username, final String salt) throws Exception {

		String responseStatus = null;
		StringBuffer otpLinkTemp = new StringBuffer(otpLink);
		StringBuffer passwordBeforeMD5 = new StringBuffer(username);

		passwordBeforeMD5.append(request_id);
		passwordBeforeMD5.append(action);
		passwordBeforeMD5.append(msisdn);
		passwordBeforeMD5.append(product_code);
		passwordBeforeMD5.append(salt);

		otpLinkTemp.append("/?msisdn=" + msisdn);
		otpLinkTemp.append("&request_id=" + request_id);
		otpLinkTemp.append("&product_code=" + product_code);
		otpLinkTemp.append("&action=" + action);
		otpLinkTemp.append("&username=" + username);
		otpLinkTemp.append("&password=" + MD5HashingForPw(passwordBeforeMD5));
		 System.out.println(otpLinkTemp);
		try {

			URL obj = new URL(otpLinkTemp.toString());
			con = (HttpURLConnection) obj.openConnection();

			// optional default is GET
			con.setRequestMethod("GET");

			// add request header
			con.setRequestProperty("Partner-Violet", "OTP Request");

			// Set time out
			con.setConnectTimeout(TIMEOUT_VALUE);
			con.setReadTimeout(TIMEOUT_VALUE);

			// Responding
			int response = con.getResponseCode();
			if (response == OK_200) {
				responseStatus = "OK";
			} else if (response == BAD_400) {
				responseStatus = "Bad request";
			} else if (response == UNAUTHORIZED_401) {
				responseStatus = "Unauthorized";
			} else if (response == INVALID_402) {
				responseStatus = "Invalid MSISDN";
			} else if (response == FORBIDDEN_403) {
				responseStatus = "Forbidden";
			} else if (response == METHOD_NOT_ALLOWED_405) {
				responseStatus = "Method not allowed";
			} else if (response == NOT_ACCEPTABLE_406) {
				responseStatus = "Not Acceptable";
			}
		} catch (SocketTimeoutException se) {
			System.out.println("More than " + TIMEOUT_VALUE + "elapsed.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				con.disconnect();
			}
		}
		return responseStatus;
	}

	/**
	 * Executing use MD5 hashing algorithm to generate a hash value for a password.
	 * 
	 * @param password
	 *            String
	 * @throws NoSuchAlgorithmException
	 */
	private String MD5HashingForPw(final StringBuffer password) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(String.valueOf(password).getBytes());

		byte byteData[] = md.digest();

		// convert the byte to hex format.
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < byteData.length; i++) {
			String hex = Integer.toHexString(0xff & byteData[i]);
			if (hex.length() == 1)
				hexString.append('0');
			hexString.append(hex);
		}

		return hexString.toString();
	}

	/**
	 * To send a SUBRequest to Violet, Partner need to send and HTTP GET request to Violet’s HTTP server.
	 * 
	 * @param otpLink
	 *            http://xx.xx.xx.xx/SUBRequest.
	 * @param msisdn
	 *            Mobile number who send the message.
	 * @param request_id
	 *            ID of Partner's request.
	 * @param otp
	 *            OTP code.
	 * @param username
	 *            Partner's login name.
	 * @param salt
	 *            to create password MD5(username + request_id + action + msisdn + product_code + salt).
	 * @return Responding Respond for SUB request.
	 * @throws Exception
	 */
	private String createSUBRequest(final String subLink, final String msisdn, final String request_id, final String otp,
			final String username, final String salt) throws Exception {
		String responseStatus = null;
		StringBuffer subLinkTemp = new StringBuffer(subLink);
		StringBuffer passwordBeforeMD5 = new StringBuffer(username);

		passwordBeforeMD5.append(request_id);
		passwordBeforeMD5.append(msisdn);
		passwordBeforeMD5.append(otp);
		passwordBeforeMD5.append(salt);

		subLinkTemp.append("/?msisdn=" + msisdn);
		subLinkTemp.append("&request_id=" + request_id);
		subLinkTemp.append("&otp=" + otp);
		subLinkTemp.append("&username=" + username);
		subLinkTemp.append("&password=" + MD5HashingForPw(passwordBeforeMD5));
		System.out.println(subLinkTemp);
		try {

			URL obj = new URL(subLinkTemp.toString());
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			
			// optional default is GET
			con.setRequestMethod("GET");

			// Set time out
			con.setConnectTimeout(TIMEOUT_VALUE);
			con.setReadTimeout(TIMEOUT_VALUE);

			// add request header
			con.setRequestProperty("Partner-Violet", "SUB Request");

			// Responding
			int response = con.getResponseCode();
			if (response == OK_200) {
				responseStatus = "OK";
			} else if (response == BAD_400) {
				responseStatus = "Bad request";
			} else if (response == UNAUTHORIZED_401) {
				responseStatus = "Unauthorized";
			} else if (response == INVALID_402) {
				responseStatus = "Invalid MSISDN";
			} else if (response == FORBIDDEN_403) {
				responseStatus = "Forbidden";
			} else if (response == METHOD_NOT_ALLOWED_405) {
				responseStatus = "Method not allowed";
			} else if (response == NOT_ACCEPTABLE_406) {
				responseStatus = "Not Acceptable";
			} else if (response == INVALID_REQUEST_ID_407) {
				responseStatus = "Request ID is NOT exist";
			}
		} catch (SocketTimeoutException se) {
			System.out.println("More than " + TIMEOUT_VALUE + "elapsed.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				con.disconnect();
			}
		}
		return responseStatus;
	}
	
//	private static String  OTPLINK = "OTPLINK";
//	private static String  MSISDN = "MSISDN";
//	private static String  REQUEST_ID = "REQUEST_ID";
//	private static String  PRODUCT_CODE = "PRODUCT_CODE";
//	private static String  ACTION = "ACTION";
//	private static String  USERNAME = "acom_cp";
//	private static String  SALT = "8*(6^Uff";
//	
//	private static String SUBLINK = "SUBLINK";
//	private static String  OTP = "OTP";
//	
//	public static void main(String[] args) throws Exception {
//		OTPService otps = new OTPService();
//		Scanner sc = new Scanner(System.in);
//		System.out.print("Hello: "+ USERNAME + "(Salt:" + SALT +")" + "\nPress 1(2) to create OTP(SUB) request. Your select: ");
//		int choice = sc.nextInt();
//		switch (choice) {
//		case 1:
//			System.out.println("Create OTP request.");
//			OTPLINK = getDataInput(OTPLINK);
//			MSISDN = getDataInput(MSISDN);
//			REQUEST_ID = getDataInput(REQUEST_ID);
//			PRODUCT_CODE = getDataInput(PRODUCT_CODE);
//			ACTION = getDataInput(ACTION);
//			String moOutput = otps.createOTPRequest(OTPLINK, MSISDN, REQUEST_ID, PRODUCT_CODE, ACTION, USERNAME, SALT);
//			System.out.println("Response for OTP Request: " + moOutput);
//			break;
////		} else if (choice == 2) {
//		case 2:
//			System.out.println("Create SUB request.");
//			SUBLINK = getDataInput(SUBLINK);
//			MSISDN = getDataInput(MSISDN);
//			REQUEST_ID = getDataInput(REQUEST_ID);
//			OTP = getDataInput(OTP);
//			String subRequest = otps.createSUBRequest(SUBLINK, MSISDN, REQUEST_ID, OTP, USERNAME, SALT);
//			System.out.println("Response for SUB Request: " + subRequest);
//			break;
//		default:
//			System.out.println("Value invalid. Try again and press 1 or 2.");
//		}
//		
//	}
//	
//	private static String getDataInput(String options) {
//		Scanner sc = new Scanner (System.in);
//		System.out.print("Please enter ".concat(options).concat(":"));
//		String result = sc.nextLine();
//		return result;
//	}
	
}