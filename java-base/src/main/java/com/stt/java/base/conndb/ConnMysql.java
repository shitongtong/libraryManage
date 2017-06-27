package com.stt.java.base.conndb;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/6/26.
 */
public class ConnMysql {
    public static void main(String[] args) throws Exception {

    }

    @Test
    public void conn2() throws Exception {
        String type = "com.mysql.jdbc.Driver";
//        ClassLoader parent = Thread.currentThread().getContextClassLoader();
//        URLClassLoader ucl = new URLClassLoader(urls.toArray(new URL[urls
//                .size()]), parent);
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Class<?> clazz = Class.forName(type, true, classLoader);
        Driver driver = (Driver) clazz.newInstance();

        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "123456");

        String connectionURL = "jdbc:mysql://192.168.1.219:3306/onlyhitest";
        Connection conn = driver.connect(connectionURL, props);
        DatabaseMetaData databaseMetaData = conn.getMetaData();
        ResultSet rs = databaseMetaData.getColumns(null, null, "bank", "%");
        while (rs.next()) {
            String COLUMN_NAME = rs.getString("COLUMN_NAME");
            String column_name = rs.getString("column_name");
//            String comment = rs.getString("comment");
            System.out.println("COLUMN_NAME:" + COLUMN_NAME);
            System.out.println("column_name:" + column_name);
//            System.out.println("comment:" + comment);
            String remarks = rs.getString("REMARKS");
            System.out.println("remarks:" + remarks);
        }
        ResultSet schemas = databaseMetaData.getSchemas();
        ResultSet schemas1 = databaseMetaData.getSchemas(null, "%");
        ResultSet bank = databaseMetaData.getTables(null, null, "bank", null);
        while (bank.next()){
            String remark = bank.getString("REMARKS");
            System.out.println("===="+remark);
        }
        String sql = "show table status like 'bank'";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            String comment = resultSet.getString("comment");
            System.out.println("comment==="+comment);
        }
        System.out.println("");
    }

    @Test
    public void conn1() throws Exception {
        String driver = "com.mysql.jdbc.Driver";
        // URL指向要访问的数据库名scutcs
        String url = "jdbc:mysql://127.0.0.1:3306/scutcs";
        // MySQL配置时的用户名
        String user = "root";
        // Java连接MySQL配置时的密码
        String password = "root";
        // 加载驱动程序
        Class.forName(driver);
        // 连续数据库
        Connection conn = DriverManager.getConnection(url, user, password);
    }
}
