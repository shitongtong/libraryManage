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
public class updateTeacherPassword {

    public static void main(String[] args) throws SQLException {
        Connection conn = DBPool.getInstance().getDruidConnection();
        String sql = "select uuid, phone from tc_teacher";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        String sql1 = "update tc_teacher set password=? where uuid=?";

        String uuid;
        String phone;
        String password;
        PreparedStatement statement;
        while (resultSet.next()) {
            uuid = resultSet.getString(1);
            phone = resultSet.getString(2);
            password = SecurityUtil.hashSha512Hex(phone + "&" + "123456" + ":onlyhi");
            statement = conn.prepareStatement(sql1);
            statement.setString(1,password);
            statement.setString(2,uuid);
            statement.executeUpdate();
            statement.close();
        }
        resultSet.close();
        preparedStatement.close();
        conn.close();
    }
}
