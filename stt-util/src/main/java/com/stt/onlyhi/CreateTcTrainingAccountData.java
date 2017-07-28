package com.stt.onlyhi;

import com.stt.util2.DBPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/7/28.
 */
public class CreateTcTrainingAccountData {

    public static void main(String[] args) throws SQLException {
        Connection conn = DBPool.getInstance().getDruidConnection();
        String sql = "insert into tc_training_account(training_date) values('2017-07-27')";
        for (int i = 0; i < 48; i++) {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }
        conn.close();

    }
}
