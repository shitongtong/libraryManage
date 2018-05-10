package com.stt.onlyhi;

import com.stt.util2.DBPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2018/5/2.
 */
public class RecoverCcCrTestData {
    public static void main(String[] args) throws SQLException {
        Connection conn = DBPool.getInstance().getDruidConnection();
        String sql1 = "select cc_uuid,cr_uuid,phone from leads where phone like '18900000%'";
        String sql2 = "select uuid from user where status=1 and uuid=?";
        String sql3 = "insert into user(uuid,login_name,user_name) values()";
        PreparedStatement ps1 = conn.prepareStatement(sql1);
        ResultSet rs = ps1.executeQuery();
        while (rs.next()) {
            String ccUuid = rs.getString(1);
            String crUuid = rs.getString(2);
            String phone = rs.getString(3);
            if (phone.startsWith("1890000000")) {
                PreparedStatement ps2 = conn.prepareStatement(sql2);
                ps2.setString(1, ccUuid);
                ResultSet resultSet = ps2.executeQuery();
                if (!resultSet.next()){

                }
            }
        }
    }
}
