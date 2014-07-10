/**
 * ----------------------------------------------------------------- 
 * @ Copyright(c) 2013 Vietnamobile. JSC. All Rights Reserved.
 * ----------------------------------------------------------------- 
 * Date 	Author 		Version
 * ------------------------------------- 
 * Dec 18, 2013 hungdt  v1.0
 * -------------------------------------
 */
package com.mastercp.iwebservices;

/**
 * @author hungdt
 *
 */
public class ErrorCode
{
	public static final int	E_OK							= 0;	// Detail:Successfully.
	public static final int	E_UNKNOWN						= 1;	// : Lenh
																	// khong ho
																	// tro
	public static final int	E_FAILSE						= 2;	// : That
																	// bai
	public static final int	E_NOTCONNECT					= 3;	// Mat ket
																	// noi toi
																	// CGW
	public static final int	E_OVERLOADED					= 4;	// Detail:System
																	// overload
	public static final int	E_TIMEOUT						= 5;	// Detail:Debit
																	// Timeout.
	public static final int	E_INVALID_USERPASS				= 6;	// Can't
																	// authenticate
																	// this user

	public static final int	E_NOT_HAS_PERMISSION			= 7;	// Detail:Access
																	// denied.
	public static final int	E_WEBSERVICE_ERROR				= 8;
	public static final int	E_SUBCRIBER_NOT_FOUND			= 9;

	public static final int	E_MDN_IS_PROCESSING				= 11;
	public static final int	E_NOT_ENOUGH_MONEY				= 12;	// Result:12,Detail:Not
																	// enough
																	// money.
	public static final int	E_INVALID_STATE					= 13;
	public static final int	E_INVALID_AMOUNT				= 14;
	public static final int	E_INVALID_CURRENCY				= 15;
	public static final int	E_FAIL_TO_DEDUCT				= 16;	// Detail:Can
																	// not debit
																	// Idle
	public static final int	E_INVALID_CONDITION				= 17;
	public static final int	E_WEBSERVICE_NOT_INITIALIZED	= 18;
	public static final int	E_INVALID_SYNTAX				= 19;	// Result:19,Detail:Not
																	// found
																	// rule for
																	// debit.

	public static final int	E_INVALID_MSISDN_INVITE			= 20;	//
	public static final int	E_SYSTEM_BUSY					= 21;	// Detail:System
																	// busy.
	public static final int	E_INVALID_BALANCE				= 22;
	public static final int	E_INVALID_EXPIRATEDDATE			= 23;
	public static final int	E_CP_NOT_FOUND					= 24;
	public static final int	E_INVALID_MSISDN				= 25;	//

	public static final int	E_FAIL_DEBIT_S1					= 26;	// Suspended(S1)
	public static final int	E_FAIL_DEBIT_S2					= 27;	// Desabled(S2)
	public static final int	E_FAIL_DEBIT_S3					= 28;	// Retired(S3)

	public static final int	E_FRAUD_LOCKOUT					= 30;
	public static final int	E_UNKNOW_COMMAND				= 32;

	public String getErrorDetail(int errorcode)
	{
		switch (errorcode)
		{
			case E_OK:
				return "Successfully.";
			case E_UNKNOWN:
				return "unsupported";
			case E_FAILSE:
				return "Unsuccessfully";
			case E_NOTCONNECT:
				return "can-not-connect";
			case E_OVERLOADED:
				return "System overload";
			case E_TIMEOUT:
				return "Debit Timeout.";
			case E_INVALID_USERPASS:
				return "Can't authenticate this user";
			case E_NOT_HAS_PERMISSION:
				return "Access denied.";
			case E_WEBSERVICE_ERROR:
				return "webservice-error";
			case E_SUBCRIBER_NOT_FOUND:
				return "subscriber-not-found";
			case E_INVALID_SYNTAX:
				return "Not found rule for debit.";
			case E_MDN_IS_PROCESSING:
				return "mdn-is-processing";
			case E_NOT_ENOUGH_MONEY:
				return "Not enough money.";
			case E_INVALID_STATE:
				return "invalid-state";
			case E_INVALID_AMOUNT:
				return "invalid-amount";
			case E_INVALID_CURRENCY:
				return "invalid-currency";
			case E_FAIL_TO_DEDUCT:
				return "Can not debit idle.";
			case E_INVALID_CONDITION:
				return "Parameter invalid";
			case E_WEBSERVICE_NOT_INITIALIZED:
				return "webservice-not-initialized";
			case E_SYSTEM_BUSY:
				return "System busy.";
			case E_INVALID_BALANCE:
				return "invalid-balance";
			case E_INVALID_EXPIRATEDDATE:
				return "invalid-expiratedDate";
			case E_CP_NOT_FOUND:
				return "cp-not-found";
			case E_FAIL_DEBIT_S1:
				return "Can not debit S1.";
			case E_FAIL_DEBIT_S2:
				return "Can not debit S2.";
			case E_FAIL_DEBIT_S3:
				return "Can not debit S3.";
			case E_FRAUD_LOCKOUT:
				return "Fraud lockout";
			case E_UNKNOW_COMMAND:
				return "Unknow Command";
			default:
				return "unknown";
		}
	}
}
