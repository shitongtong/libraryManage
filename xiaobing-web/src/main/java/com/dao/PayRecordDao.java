package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.model.PayRecord;

public class PayRecordDao extends BaseDao{

	
	public List<PayRecord> QueryRayRecord(String uid,String pingtai,String order_statue,String pay_qudao)
	{
		String sql = "select order_id,pay_time,pingtai,name,body,money,fact_money,order_statue,pay_qudao from NMPayer_pay_record where uid="+uid;
		if(pingtai!=null)
		{
			sql += "and pingtai='";
			sql += pingtai;
			sql += "'";
		}
		if(order_statue!=null)
		{
			sql += "and order_statue='";
			sql += order_statue;
			sql += "'";
		}
		if(pay_qudao!=null)
		{
			sql += "and pay_qudao='";
			sql += pay_qudao;
			sql += "'";
		}
		
        Statement stmt;
        List<PayRecord> payRecordList = new ArrayList<PayRecord>();
		try {
			stmt = cnn.createStatement();
			 ResultSet rs = stmt.executeQuery(sql);  
			
		        while(rs.next())  
		        {  
		        	PayRecord payRecord = new PayRecord();
		        	payRecord.setOrder_id(rs.getString(1));
		        	payRecord.setPay_time(rs.getString(2));
		        	payRecord.setPingtai(rs.getString(3));
		        	payRecord.setName(rs.getString(4));
		        	payRecord.setBody(rs.getString(5));
		        	payRecord.setMoney(rs.getString(6));
		        	payRecord.setFact_money(rs.getString(7));
		        	payRecord.setOrder_statue(rs.getString(8));
		        	payRecord.setPay_qudao(rs.getString(9));
		        	payRecordList.add(payRecord);
		        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
       
    	
    	return payRecordList;
		
		
	}
	
}
