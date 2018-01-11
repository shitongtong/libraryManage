package com.stt.util2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/6/29.
 */
public class UpdateTeacherPassword {

    public static void main(String[] args) throws SQLException {
        Connection conn = DBPool.getInstance().getDruidConnection();
        String sql = "select uuid, phone from tc_teacher where phone like '1880000009%'";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        String sql1 = "update tc_teacher set password=? where uuid=?";

        String uuid;
        String phone;
        String password;
        PreparedStatement statement;
        int total = 0;
        while (resultSet.next()) {
            total++;
            uuid = resultSet.getString(1);
            phone = resultSet.getString(2);
            password = SecurityUtil.hashSha512Hex(phone + "&" + "123456" + ":onlyhi");
            statement = conn.prepareStatement(sql1);
            statement.setString(1,password);
            statement.setString(2,uuid);
            statement.executeUpdate();
            statement.close();
        }
        System.out.println("total="+total);
        resultSet.close();
        preparedStatement.close();
        conn.close();
    }
}
