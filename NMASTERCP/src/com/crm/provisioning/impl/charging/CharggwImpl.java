/**
 * 
 */
package com.crm.provisioning.impl.charging;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.crm.kernel.message.Constants;
import com.crm.kernel.sql.Database;
import com.crm.product.cache.ProductEntry;
import com.crm.product.cache.ProductFactory;
import com.crm.provisioning.cache.ProvisioningCommand;
import com.crm.provisioning.impl.CommandImpl;
import com.crm.provisioning.message.CommandMessage;
import com.crm.provisioning.thread.CommandInstance;
import com.crm.util.DateUtil;

/**
 * @author hungdt
 * 
 */
public class CharggwImpl extends CommandImpl
{
	public void exportCDR(CommandInstance instance, ProvisioningCommand provisioningCommand, CommandMessage request) throws Exception
	{
		Connection connection = null;
		PreparedStatement stmtCDR = null;

		String sql = "Insert into cdr_export "
				+ "		(id, a_party, b_party, reqdate, description, cont_prov_id, cont_prov_name "
				+ "		, cont_code, cont_type, curency, amount) "
				+ " Values "
				+ " (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try
		{
			String AParty = "";
			String BParty = "";

			if (request.getCont_code() == Constants.CONTENT_CODE_MOBILE_TERMINATED_SMS
					|| request.getCont_code() == Constants.CONTENT_CODE_CALL_FOWARD)
			{
				AParty = request.getServiceAddress();
				BParty = request.getIsdn();
			}
			else
			{
				AParty = request.getIsdn();
				BParty = request.getServiceAddress();
			}

			connection = Database.getConnection();
			stmtCDR = connection.prepareStatement(sql);

			stmtCDR.setLong(1, request.getOrderId());
			stmtCDR.setString(2, AParty);
			stmtCDR.setString(3, BParty);
			stmtCDR.setTimestamp(4, DateUtil.getTimestampSQL(request.getOrderDate()));
			stmtCDR.setString(5, request.getDescription());
			stmtCDR.setLong(6, request.getCont_prov_id());
			stmtCDR.setString(7, request.getCont_prov_name());
			stmtCDR.setInt(8, request.getCont_code());
			stmtCDR.setInt(9, request.getCont_type());
			stmtCDR.setString(10, request.getCurrency());
			stmtCDR.setDouble(11, request.getAmount());

			stmtCDR.execute();
		}
		catch (Exception e)
		{
			processError(instance, provisioningCommand, request, e);
		}
		finally
		{
			Database.closeObject(stmtCDR);
			Database.closeObject(connection);
		}
	}
}
