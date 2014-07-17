package com.crm.provisioning.impl.charging;

public class ChargingConstants
{
	/**
	 * SEPARATE_CHARS: end of each request
	 */
	public static final String	SEPARATE_CHARS								= "\r\n";				// CRLF
	public static final String	SEPARATOR_CMD								= " ";
	public static final String	SEPARATOR_FIELD								= "&";
	public static final String	SEPARATOR_REQUEST_CMD_FIELD					= "?";
	public static final String	SEPARATOR_RESPONSE_CMD_FIELD				= ":";

	public static final String	REQUEST										= "REQ";
	public static final String	RESPONSE									= "RESP";
	public static final String	INFO										= "INFO";

	public static final String	CMD_LOGON									= "Logon";
	public static final String	CMD_CREATE_SESSION							= "CreateSession";
	public static final String	CMD_HEART_BEAT								= "HeartBeat";
	public static final String	CMD_DEBIT									= "Debit";
	public static final String	CMD_DETROY_SESSION							= "DestroySession";

	public static final String	FIELD_SESSION_ID							= "SessionId=";
	public static final String	FIELD_MSG									= "Msg=";
	public static final String	FIELD_USER									= "UserName=";
	public static final String	FIELD_PASSWORD								= "Password=";
	public static final String	FIELD_ERROR_CODE							= "ErrCode=";
	public static final String	FIELD_ERROR_DETAIL							= "ErrDetail=";
	public static final String	FIELD_TRANSACTION_ID						= "TransId=";
	public static final String	FIELD_TRANSACTION_DATETIME					= "TransDateTime=";
	public static final String	FIELD_CP_ID									= "CpId=";
	public static final String	FIELD_CP_NAME								= "CpName=";
	public static final String	FIELD_NUMBER_A								= "ANumber=";
	public static final String	FIELD_NUMBER_B								= "BNumber=";
	public static final String	FIELD_SUBMIT_TIME							= "SubmitTime=";
	public static final String	FIELD_SENT_TIME								= "SentTime=";
	public static final String	FIELD_SERVICE_STATE							= "ServiceState=";
	public static final String	FIELD_CONTENT_CODE							= "ContCode=";
	public static final String	FIELD_CONTENT_TYPE							= "ContType=";
	public static final String	FIELD_DESCRIPTION							= "Description=";

	public static final String	VALUE_SERVICE_STATE_SUCCESS					= "D";
	public static final String	VALUE_SERVICE_STATE_FAILED					= "U";
	public static final String	VALUE_CONTENT_CODE_VOICE_MO					= "100";
	public static final String	VALUE_CONTENT_CODE_SMS_MO					= "102";
	public static final String	VALUE_CONTENT_CODE_SMS_MT					= "103";
	public static final String	VALUE_CONTENT_CODE_CALL_FORWARD_OR_DIVERT	= "104";
	public static final String	VALUE_CONTENT_CODE_MMS_MO					= "120";
	public static final String	VALUE_CONTENT_CODE_MMS_MT					= "121";
	public static final String	VALUE_CONTENT_CODE_MMS_FORWARDED			= "125";
	public static final String	VALUE_CONTENT_CODE_DATA						= "122";
	public static final String	VALUE_CONTENT_TYPE_DEFAULT					= "1";

	public static String		COS_CURRENT									= "cos.current";
	public static String		COS_NEW										= "cos.new";
	public static String		SERVICE_BALANCE								= "service.balance";
	public static String		SERVICE_AMOUNT								= "service.amount";
	public static String		SERVICE_PRICE								= "service.price";
	public static String		SERVICE_DAYS								= "service.days";
	public static String		SERVICE_ACTIVE_DAYS							= "service.activeDays";
	public static String		SERVICE_START_DATE							= "service.startDate";
	public static String		SERVICE_EXPIRE_DATE							= "service.expireDate";
	public static String		SERVICE_ACTIVE_DATE							= "service.activeDate";
	public static String		LEADER										= "leader";
	public static String		REFERAL										= "referal";
	public static String		PHONEBOOK									= "phonebook";
	public static String		FRIEND_OLD									= "friend.old";
	public static String		FRIEND_NEW									= "friend.new";
	public static String		MEMBER										= "member";
	public static String		MEMBER_FREE									= "member.free";

	/**
	 * add static fields.
	 * 
	 * Edited by NamTA
	 */
	public static String		ACCOUNT_STATE								= "account.state";
	public static String		BALANCES									= "balances";
	public static String		TIMEOUT										= "timeout";
	public static String		SESSION_ID									= "sessionId";
	public static String		VALUE										= "Value";

	public static String		AMOUNT_UNBILL								= "unbillAmount";
	public static String		AMOUNT_OUTSTANDING							= "outstandingAmount";
	public static String		AMOUNT_LAST_PAYMENT							= "lastPaymentAmount";

	public static String		VAS											= "VAS";
	public static String		SMS_HREF									= "smsHref";
	public static String		SMS_TEXT									= "smsText";
	public static String		SMS_TYPE									= "smsType";
	public static String		SMS_CMD_CHECK								= "smsCmdCheck";

	public static String getFieldValue(String name, String source)
	{
		String value = "";

		int fieldIndex = source.indexOf(name);

		if (fieldIndex >= 0)
		{
			value = source.substring(fieldIndex + name.length());

			int endIndex = value.indexOf(SEPARATOR_FIELD);

			if (endIndex >= 0)
			{
				value = value.substring(0, endIndex);
			}
		}

		return value;
	}
}
