package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.model.BalanceRecord;

public class BalanceRecordDao extends BaseDao{

	
	public List<BalanceRecord> QueryBalanceRecord(String uid)
	{
		String sql = "select order_id,time,tikuan_number,tikuan_state from NMPayer_balance_record where uid='"+uid +"'"; 
        Statement stmt;
        List<BalanceRecord> balanceRecordList = new ArrayList<BalanceRecord>();
		try {
			stmt = cnn.createStatement();
			 ResultSet rs = stmt.executeQuery(sql);  
		        
		        while(rs.next())  
		        {  
		        	BalanceRecord balanceRecord = new BalanceRecord();
		        	balanceRecord.setOrder_id(rs.getString(1));
		        	balanceRecord.setTikuan_number(rs.getString(2));
		        	balanceRecord.setTime(rs.getString(3));
		        	balanceRecord.setTikuan_state(rs.getString(4));
		        	balanceRecordList.add(balanceRecord);
		        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
       
    	
    	return balanceRecordList;
	}
}
