package com.crm.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpRequest {
	public static String callURL(String url) throws IOException {
		String strURL = "";
		if (url.length() > 0 && !url.equals("")) {
			try {
				// Create a URL for the desired page
				URL _url = new URL(url);
				// Read all the text returned by the server
				BufferedReader in = new BufferedReader(new InputStreamReader(
						_url.openStream()));
				String str;
				// System.out.println(in.toString());
				while ((str = in.readLine()) != null) {
					strURL = strURL + str + "";
				}
				in.close();
			} catch (MalformedURLException e) {
				throw e;
			} catch (IOException e) {
				throw e;
			}
		}
		return strURL;
	}
	
}
