package com.crm.football.product.impl;

import com.crm.football.bean.FootballInfo;
import com.crm.football.sql.impl.FootballImpl;
import com.crm.kernel.message.Constants;
import com.crm.provisioning.message.CommandMessage;
import com.fss.util.AppException;

public class TeamInfoOrderRoutingImpl extends FootballOrderRoutingImpl
{
	@Override
	public FootballInfo validateKeyword(CommandMessage request) throws Exception
	{
		try
		{
			String code = request.getRequestValue("football.code", "");
			FootballInfo info = FootballImpl.validTeamName(code);
			if (info == null)
				throw new AppException(Constants.ERROR_INVALID_PARAMETER);
			return info;
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}
