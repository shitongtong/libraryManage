package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.model.Message;


public class MessageDao extends BaseDao{
   
	    public List<Message> QueryMessage(String uid)
	    {
	    	String sql = "select message,url,time from NMPayer_message where uid='"+uid +"' or uid=1 order by id desc limit 0,15"; 
	        Statement stmt;
	        List<Message> messageList = new ArrayList<Message>();
			try {
				stmt = cnn.createStatement();
				 ResultSet rs = stmt.executeQuery(sql);  
			        
			        while(rs.next())  
			        {  
			        	Message message = new Message();
			        	message.setMessage(rs.getString(1));
			        	message.setUrl(rs.getString(2));
			        	message.setTime(rs.getString(3));
			        	messageList.add(message);
			        }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
	       
	    	
	    	return messageList;
	    }
}
