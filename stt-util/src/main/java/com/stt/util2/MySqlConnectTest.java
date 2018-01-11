package com.stt.util2;

import org.junit.Test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/11/7.
 */
public class MySqlConnectTest {
    public static void main(String[] args) throws SQLException {

    }

    @Test
    public void test1() throws SQLException {
        Connection conn = DBPool.getInstance().getDruidConnection();
        String sql = "insert into user_wechat(user_wechat_uuid,uid,name,openid) values(?,?,?,?) ";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, "edaadc31fdb9410f920ae476dd3727de");
        preparedStatement.setString(2, "oUEphxBcy_YGtK6-Uen-GEJdmkJs");
        preparedStatement.setString(3, "童?%@");
        preparedStatement.setString(4, "omViAvyuIkv1sthxiQHsG7lSfzIc");
        preparedStatement.executeUpdate();
        preparedStatement.close();
        preparedStatement.close();
        conn.close();
    }

    @Test
    public void test2() throws SQLException {
        Connection conn = DBPool.getInstance().getDruidConnection();
        String sql = "insert into class_room(class_room_uuid,course_uuid,enter_room_time,update_time) values(?,?,?,?) ";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, "aa");
        preparedStatement.setString(2, "ss");
        preparedStatement.setDate(3, new Date(new java.util.Date().getTime()));
        preparedStatement.setDate(4, new Date(new java.util.Date().getTime()));
        preparedStatement.executeUpdate();
        preparedStatement.close();
        preparedStatement.close();
        conn.close();
    }

    @Test
    public void test3() throws SQLException {
        Connection conn = DBPool.getInstance().getDruidConnection();
        String sql = "insert into date_test2(start_time) values(?) ";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        long l = System.currentTimeMillis();
        System.out.println(l);
        preparedStatement.setLong(1, new java.util.Date().getTime());
        preparedStatement.executeUpdate();
        preparedStatement.close();
        preparedStatement.close();
        conn.close();
    }
}
