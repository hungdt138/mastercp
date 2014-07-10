/**
 * 
 */
package org.tempuri;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Date;

import sun.security.x509.CertAndKeyGen;
import sun.security.x509.X500Name;
/**
 * @author Hung
 * 
 */
public class Test1
{
	private static final int	keysize				= 1024;
	private static final String	commonName			= "www.test.de";
	private static final String	organizationalUnit	= "IT";
	private static final String	organization		= "test";
	private static final String	city				= "test";
	private static final String	state				= "test";
	private static final String	country				= "DE";
	private static final long	validity			= 1096;					// 3
																				// years
	private static final String	alias				= "tomcat";
	private static final char[]	keyPass				= "changeit".toCharArray();

	/**
	 * @param args
	 * @throws KeyStoreException 
	 * @throws IOException 
	 * @throws CertificateException 
	 * @throws NoSuchAlgorithmException 
	 */
	public static void main(String[] args) throws Exception
	{
		KeyStore keyStore = KeyStore.getInstance("JKS");
        keyStore.load(null, null);

        CertAndKeyGen keypair = new CertAndKeyGen("RSA", "SHA1WithRSA", null);

        X500Name x500Name = new X500Name(commonName, organizationalUnit, organization, city, state, country);

        keypair.generate(keysize);
        PrivateKey privKey = keypair.getPrivateKey();

        X509Certificate[] chain = new X509Certificate[1];

        chain[0] = keypair.getSelfCertificate(x500Name, new Date(), (long) validity * 24 * 60 * 60);

        keyStore.setKeyEntry(alias, privKey, keyPass, chain);

        keyStore.store(new FileOutputStream(".keystore"), keyPass);
	}

}