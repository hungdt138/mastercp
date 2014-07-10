/**
 * ----------------------------------------------------------------- 
 * @ Copyright(c) 2013 Vietnamobile. JSC. All Rights Reserved.
 * ----------------------------------------------------------------- 
 * Date 	Author 		Version
 * ------------------------------------- 
 * Oct 6, 2013 hungdt  v1.0
 * -------------------------------------
 */
package com.crm.provisioning.impl.vinaphone.otp;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;

import com.crm.kernel.message.Constants;
import com.crm.provisioning.cache.ProvisioningConnection;
import com.crm.util.AppProperties;

/**
 * @author hungdt
 * 
 */
public class OTPConnection extends ProvisioningConnection
{

	public static Logger			log				= Logger.getLogger(OTPConnection.class);
	
	private HttpURLConnection	con	= null;

	public OTPConnection() {
		super();
	}

	public boolean openConnection() throws Exception
	{
		return super.openConnection();
	}

	/**
	 * To send a OTPRequest to Violet, Partner need to send and HTTP GET request
	 * to Violet�s HTTP server.
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
	 *            to create password MD5(username + request_id + action + msisdn
	 *            + product_code + salt).
	 * @return Responding Respond for OPT request.
	 * @throws Exception.
	 */
	@SuppressWarnings("unused")
	public String createOTPRequest(final String msisdn, final long request_id,
			final String product_code, final String action) throws Exception
	{

		String responseStatus = null;
		StringBuffer otpLinkTemp = new StringBuffer(getHost());
		StringBuffer passwordBeforeMD5 = new StringBuffer(getUserName());
		String response = "";
		passwordBeforeMD5.append(request_id);
		passwordBeforeMD5.append(action);
		passwordBeforeMD5.append(msisdn);
		passwordBeforeMD5.append(product_code);
		passwordBeforeMD5.append(getPassword());

		otpLinkTemp.append("?msisdn=" + msisdn);
		otpLinkTemp.append("&request_id=" + request_id);
		otpLinkTemp.append("&product_code=" + product_code);
		otpLinkTemp.append("&action=" + action);
		otpLinkTemp.append("&username=" + getUserName());
		otpLinkTemp.append("&password=" + MD5HashingForPw(passwordBeforeMD5));
		
		debugMonitor(otpLinkTemp);
		
		try
		{

			URL obj = new URL(otpLinkTemp.toString());
			con = (HttpURLConnection) obj.openConnection();

			// optional default is GET
			con.setRequestMethod("GET");

			// add request header
			con.setRequestProperty("Acom-Vinaphone", "OTP Request");

			// Set time out
			con.setConnectTimeout((int) getTimeout());
			con.setReadTimeout((int) getTimeout());
			
			//Vinaphone return content
			InputStream in = con.getInputStream();
			InputStreamReader rd = new InputStreamReader(in);
			
			BufferedReader buff = new BufferedReader(rd);
			String str;
			while ((str = buff.readLine()) != null)
			{
				response = response + str + "";
			}

		}
		catch (SocketTimeoutException se)
		{
			// System.out.println("More than " + (int) getTimeout() +
			// "elapsed.");
			throw se;
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			if (con != null)
			{
				con.disconnect();
			}
		}
		return response;
	}

	/**
	 * Executing use MD5 hashing algorithm to generate a hash value for a
	 * password.
	 * 
	 * @param password
	 *            String
	 * @throws NoSuchAlgorithmException
	 */
	private String MD5HashingForPw(final StringBuffer password)
			throws NoSuchAlgorithmException
	{
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(String.valueOf(password).getBytes());

		byte byteData[] = md.digest();

		// convert the byte to hex format.
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < byteData.length; i++)
		{
			String hex = Integer.toHexString(0xff & byteData[i]);
			if (hex.length() == 1)
				hexString.append('0');
			hexString.append(hex);
		}

		return hexString.toString();
	}

	/**
	 * To send a SUBRequest to Violet, Partner need to send and HTTP GET request
	 * to Violet�s HTTP server.
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
	 *            to create password MD5(username + request_id + action + msisdn
	 *            + product_code + salt).
	 * @return Responding Respond for SUB request.
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	public String createSUBRequest(final String msisdn, final long request_id,
			final String otp) throws Exception
	{
		String responseStatus = null;
		StringBuffer subLinkTemp = new StringBuffer(getHost());
		StringBuffer passwordBeforeMD5 = new StringBuffer(getUserName());
		String response = "";
		passwordBeforeMD5.append(request_id);
		passwordBeforeMD5.append(msisdn);
		passwordBeforeMD5.append(otp);
		passwordBeforeMD5.append(getPassword());

		subLinkTemp.append("?msisdn=" + msisdn);
		subLinkTemp.append("&request_id=" + request_id);
		subLinkTemp.append("&otp=" + otp);
		subLinkTemp.append("&username=" + getUserName());
		subLinkTemp.append("&password=" + MD5HashingForPw(passwordBeforeMD5));
		debugMonitor(subLinkTemp);
		try
		{

			URL obj = new URL(subLinkTemp.toString());
			con = (HttpURLConnection) obj.openConnection();

			// optional default is GET
			con.setRequestMethod("GET");

			// Set time out
			con.setConnectTimeout((int) getTimeout());
			con.setReadTimeout((int) getTimeout());

			// add request header
			con.setRequestProperty("Acom-Vinaphone", "SUB Request");
			
			//Vinaphone return content
			InputStream in = con.getInputStream();
			InputStreamReader rd = new InputStreamReader(in);
			
			BufferedReader buff = new BufferedReader(rd);
			String str;
			while ((str = buff.readLine()) != null)
			{
				response = response + str + "";
			}
			
		}
		catch (SocketTimeoutException se)
		{
			//System.out.println("More than " + getTimeout() + "elapsed.");
			debugMonitor(se.toString());
			throw se;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (con != null)
			{
				con.disconnect();
			}
		}
		return response;
	}

}
