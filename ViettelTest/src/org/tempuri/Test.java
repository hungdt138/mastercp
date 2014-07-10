/**
 * 
 */
package org.tempuri;

import org.tempuri.WebServiceStub.InsertMO;

/**
 * @author Hung
 *
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		try {
			WebServiceStub stub = new WebServiceStub();
			InsertMO iMO = new InsertMO();
			iMO.setCmdCode("TV");
			iMO.setDstNum("8026");
			iMO.setIsCdr(1);
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
