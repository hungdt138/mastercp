package com.gateway.util;


import com.crm.provisioning.message.CommandMessage;
import com.gateway.model.DeliveryResponse;
import com.gateway.model.ServiceResponse;
import com.gateway.model.ServiceStatus;

public class WSResponseUtil
{
	

	public static ServiceResponse updateResponse(ServiceResponse response, CommandMessage result)
	{
		response.setStatus(WSExeption.getErrorByCause(result.getCause()));
		//response.setAmount(result.getAmount());
		response.setResult(result.getStatus());
		
		
		if (result.getCause().equals(""))
		{
			response.setStatus(WSExeption.SVC_ERROR);
			response.setResult(WSExeption.ERROR);
		}
		
		if (result.getCause().equals("error") || result.getCause().equals("timeout") || result.getStatus() == 4)
		{
			response.setResult(WSExeption.ERROR);
		}
		
		return response;
	}
	
	//0926669999
	
	public static DeliveryResponse updateResponse(DeliveryResponse response, CommandMessage result)
	{
		response.setStatus(WSExeption.getErrorByCause(result.getCause()));
		response.setAmount(result.getAmount());
	//	response.setNumOfSms(result.getNumSMS());
		response.setResult(result.getStatus());
	
		if (result.getCause().equals(""))
		{
			response.setStatus(WSExeption.SVC_ERROR);
			response.setResult(WSExeption.ERROR);
		}
		if (result.getCause().equals("error") || result.getCause().equals("timeout") || result.getStatus() == 4)
		{
			response.setResult(WSExeption.ERROR);
		}
		
		return response;
	}
	
	public static ServiceStatus updateResponse(ServiceStatus response, String cause)
	{
		response.setStatus(WSExeption.getErrorByCause(cause));
		
		if(cause.equals("error"))
		{
			response.setResult(WSExeption.ERROR);
		}
		else
		{
			response.setResult(WSExeption.SUCCESS);
		}
		
		
		return response;
	}
	
	public static DeliveryResponse updateResponse(DeliveryResponse response, String cause)
	{
		response.setStatus(WSExeption.getErrorByCause(cause));
		
		if(cause.equals("error") || cause.equals("timeout") || cause.equals("invalid-request"))
		{
			response.setResult(WSExeption.ERROR);
		}
		else
		{
			response.setResult(WSExeption.SUCCESS);
		}
		
		
		return response;
	}
	
	public static ServiceResponse updateResponse(ServiceResponse response, String cause)
	{
		response.setStatus(WSExeption.getErrorByCause(cause));
		
		if(cause.equals("error") || cause.equals("timeout") || cause.equals("invalid-request"))
		{
			response.setResult(WSExeption.ERROR);
		}
		else
		{
			response.setResult(WSExeption.SUCCESS);
		}
		
		return response;
	}
}
