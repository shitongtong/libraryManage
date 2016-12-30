package com.dao;

import java.security.MessageDigest;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.model.User;

public class UserDao extends BaseDao{
	
	
	
	 public User querySyncUrl(String uid)
	  {
		  String sql = "select notify_url from NMPayer_user where uid='"+uid +"'"; 
	        Statement stmt;
	        User user = null;
			try {
				stmt = cnn.createStatement();
				 ResultSet rs = stmt.executeQuery(sql);  
			        if(rs.next())  
			        {  
			        	user = new User();
			            user.setNotify_url(rs.getString(1));
			        }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
	       
	    	
	    	return user;
	  }
	
	 public User queryAppInfo(String uid)
	  {
		  String sql = "select app_id from NMPayer_user where uid='"+uid +"'"; 
	        Statement stmt;
	        User user = null;
			try {
				stmt = cnn.createStatement();
				 ResultSet rs = stmt.executeQuery(sql);  
			        if(rs.next())  
			        {  
			        	user = new User();
			            user.setApp_id(rs.getString(1));
			        }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
	       
	    	
	    	return user;
	  }
 
	  public User queryQualification(String uid)
	  {
		  String sql = "select Qualificate,Qualificate_Name,Qualificate_Number,Qualificat_pic from NMPayer_user where uid='"+uid +"'"; 
	        Statement stmt;
	        User user = null;
			try {
				stmt = cnn.createStatement();
				 ResultSet rs = stmt.executeQuery(sql);  
			        if(rs.next())  
			        {  
			        	user = new User();
			            user.setQualificate(rs.getString(1));
			            user.setQualificate_Name(rs.getString(2));
			            user.setQualificate_Number(rs.getString(3));
			            user.setQualificat_pic(rs.getString(4));
			        }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
	       
	    	
	    	return user;
	  }
	  
	  public User queryCard(String uid)
	    {
	    	String sql = "select IDCard,name,bank,bank_location from NMPayer_user where uid='"+uid +"'"; 
	        Statement stmt;
	        User user = null;
			try {
				stmt = cnn.createStatement();
				 ResultSet rs = stmt.executeQuery(sql);  
			        
			        while(rs.next())  
			        {  
			        	user = new User();
			            user.setIDCard(rs.getString(1));
			            user.setName(rs.getString(2));
			            user.setBank(rs.getString(3));
			            user.setBank_location(rs.getString(4));
			        }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
	       
	    	
	    	return user;
	    }
	  
    public User login(String username,String password)
    {
    	String sql = "select uid,user_name,password from NMPayer_user where user_name='"+username +"'"; 
        Statement stmt;
        User user = null;
		try {
			stmt = cnn.createStatement();
			 ResultSet rs = stmt.executeQuery(sql);  
		        
		        if(rs.next())  
		        {  
		        	String md5password = rs.getString(3);
		        	String temp = MD5(password);
		        	if(md5password.compareTo(temp)==0)
		            {
		        		user = new User();
		            	user.setUid(rs.getString(1));
		            	user.setUser_name(rs.getString(2));
		            }
		        	
		        	
		        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
       
    	
    	return user;
    }
        
    public User regist(String username,String password)
    {
    	 User user = null;
    	 String sql = "select password,uid from NMPayer_user where user_name='"+username +"'";
    	 try  
	     {
    		 Statement stmt = cnn.createStatement();  
	         ResultSet rs = stmt.executeQuery(sql);  
	         int count = 0 ;
	         if(rs.next())  
	         {  
	        	 count++;
	         }
	         if(count>0)
	         {
	        	 //已经被注册了
	        	 
	         }
	         else
	         {
	        	 String tempPassword = MD5(password);
	        	 String insertsql;
			     insertsql = "insert into NMPayer_user(user_name,password) value('";
			     insertsql=insertsql +username + "','";
			     insertsql=insertsql + tempPassword + "')";
			     PreparedStatement statement = cnn.prepareStatement(insertsql);  
		         statement.executeUpdate();   
		         ResultSet rs1 = stmt.executeQuery(sql);  
		         if(rs1.next())  
		         {   
		        	 user = new User();
		             user.setUid(rs1.getString(1));
		             user.setUser_name(rs1.getString(2));
		         }  
	         }

	     }
    	 catch (SQLException e)  
	      {  
	            e.printStackTrace();  
	      } 
    
    	 return user;
    }
        
        
        public   String MD5(String s) {
            char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};       
            try {
                byte[] btInput = s.getBytes();
                // 获得MD5摘要算法的 MessageDigest 对象
                MessageDigest mdInst = MessageDigest.getInstance("MD5");
                // 使用指定的字节更新摘要
                mdInst.update(btInput);
                // 获得密文
                byte[] md = mdInst.digest();
                // 把密文转换成十六进制的字符串形式
                int j = md.length;
                char str[] = new char[j * 2];
                int k = 0;
                for (int i = 0; i < j; i++) {
                    byte byte0 = md[i];
                    str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                    str[k++] = hexDigits[byte0 & 0xf];
                }
                return new String(str);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
}
