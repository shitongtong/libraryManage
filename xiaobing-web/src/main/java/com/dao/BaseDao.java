package com.dao;


import java.sql.Connection;

public class BaseDao {
	public Connection cnn;
	public BaseDao() {
		super();
		cnn = MysqlManager.getMysqlManager().getConn();
		
	}
	
}
