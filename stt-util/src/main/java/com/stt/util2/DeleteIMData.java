package com.stt.util2;

import com.alibaba.fastjson.JSON;
import com.stt.util.RedisUtil;
import com.stt.util2.cache.LoginUserCache;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/7/11.
 */
public class DeleteIMData {

    public static void main(String[] args) throws SQLException {
        Connection conn = DBPool.getInstance().getDruidConnection();
        deleteIMData(conn);
        deleteCacheIMData(conn);
        conn.close();
    }

    private static void deleteCacheIMData(Connection conn) throws SQLException {
        String sql = "select leads_uuid,phone from leads_ext";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
//        RedisUtil redisUtil = new RedisUtil("192.168.1.250", 6379, null);
        RedisUtil redisUtil = new RedisUtil("106.14.46.51", 6300, "onlyhi.cn");
        while (resultSet.next()) {
            String leadsUuid = resultSet.getString(1);
            String phone = resultSet.getString(2);
            String indexToken = phone + "STUDENT";
            String token = redisUtil.get(indexToken);
            if (token != null) {
                String jsonStr = redisUtil.get(token);
                LoginUserCache loginUserCache = JSON.parseObject(jsonStr, LoginUserCache.class);
                loginUserCache.setRegisterIMFlag(false);
                redisUtil.set(token, JSON.toJSONString(loginUserCache));
            }
        }
        resultSet.close();
        preparedStatement.close();
    }

    /**
     * 删除db中IM数据
     *
     * @param conn
     * @throws SQLException
     */
    private static void deleteIMData(Connection conn) throws SQLException {
        String updateSql = "update leads_ext set easemob_uuid='',easemob_username=''";
        PreparedStatement preparedStatement = conn.prepareStatement(updateSql);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }
}
