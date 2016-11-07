package cn.stt.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class MysqlManager {
	private String dbDriver="com.mysql.jdbc.Driver";   
    private String dbUrl="jdbc:mysql://localhost:3306/sha?useUnicode=true&characterEncoding=utf-8";//根据实际情况变化  
    private String dbUser="root";  
    private String dbPass="root";  
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
            conn = (Connection) DriverManager.getConnection(dbUrl,dbUser,dbPass);//注意是三个参数  
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
    
    static MysqlManager getMysqlManager()
    {
    	if(pMysqlManager==null)
    	{
    		pMysqlManager = new MysqlManager();
    	}
    	
    	return pMysqlManager;
    }
}
