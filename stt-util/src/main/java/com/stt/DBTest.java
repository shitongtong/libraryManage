package com.stt;

import com.stt.util2.DBPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by shitt7 on 2018/6/25.
 */
public class DBTest {
    public static void main(String[] args) throws SQLException {
        Connection conn = DBPool.getInstance().getDruidConnection();
//        String userName = "admin' or 1='1";
        String userName = "admin";
//        String sql = "select login_name,name from sys_user where name like '%"+userName+"%' ";
        String sql = "select login_name,name from sys_user where name like ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,"%"+userName+"%");
        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next()){
            System.out.println(resultSet.getString("name"));
        }

        conn.close();
    }
}
