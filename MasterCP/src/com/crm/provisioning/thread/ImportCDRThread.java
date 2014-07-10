package com.crm.provisioning.thread;

import java.util.ArrayList;
import java.util.List;


public class ImportCDRThread extends ImportFileThread
{

	private List<CDR> cdrs	= new ArrayList<CDR>();

	public boolean fileDataProcessing(String[] fileData)
	{
		String fileBackupPostfix = "";
		
		boolean hasError = false;
		
		// TODO Auto-generated method stub
		for(int i = 0; i < fileData.length; i++)
		{
			try
			{
				CDR cdr = CDR.createCDRFromFileString(fileData[i]);
				
				cdrs.add(cdr);
				
				debugMonitor("Add records: " + cdr.toString());
				
			} 
			catch(Exception ex)
			{
				debugMonitor("Can not parse CDR: " + fileData[i]);
				debugMonitor(ex);
			}
		}
		
		
		debugMonitor("Added " + cdrs.size() + " records successfull");
		
		try
		{
			CDRImpl.insertCDR(cdrs.toArray(new CDR[] {}));
			cdrs.clear();
		}
		catch(Exception e)
		{
			debugMonitor(e);
			fileBackupPostfix = ".cdr";
			hasError = true;
		}
		
		setBackupFilePostfix(fileBackupPostfix);

		return !hasError;
	}
	
}
