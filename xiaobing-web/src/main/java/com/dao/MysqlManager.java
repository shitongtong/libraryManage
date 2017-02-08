package com.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class MysqlManager {
	private String dbDriver="com.mysql.jdbc.Driver";   
    private String dbUrl="jdbc:mysql://183.232.235.30:3306/NMPayer?useUnicode=true&characterEncoding=utf-8"; 
    private String dbUser="root";  
    private String dbPass="woaini";
    private Connection conn=null;
    static MysqlManager pMysqlManager=null;
    public Connection getConn()  
    {  
    	if(conn!=null)
    	{
    		return conn;
    	}

        try  
        {  
            Class.forName(dbDriver);    
            conn = (Connection) DriverManager.getConnection(dbUrl,dbUser,dbPass);
        }  
        catch (SQLException e)  
        {  
            e.printStackTrace();  
        }  
        catch (ClassNotFoundException e)  
        {  
            e.printStackTrace();  
        } 
        return conn;  
    }  
    
    public static MysqlManager getMysqlManager()
    {
    	if(pMysqlManager==null)
    	{
    		pMysqlManager = new MysqlManager();
    	}
    	
    	return pMysqlManager;
    }
}
