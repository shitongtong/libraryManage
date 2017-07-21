package com.stt.util2;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.stt.im.ClientSecretCredential;
import com.stt.im.Constants;
import com.stt.im.Credential;
import com.stt.im.EndPoints;
import com.stt.im.HTTPMethod;
import com.stt.im.JerseyUtils;
import com.stt.im.Roles;
import com.stt.onlyhi.DeleteStudentRegisterInfo;
import com.stt.util.RedisUtil;
import com.stt.util2.cache.LoginUserCache;
import org.apache.commons.lang.StringUtils;
import org.glassfish.jersey.client.JerseyWebTarget;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.stt.im.Constants.APPKEY;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/7/11.
 */
public class DeleteIMData {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeleteStudentRegisterInfo.class);

    private static final String phone = "";

    public static void main(String[] args) throws SQLException {

        Connection conn = DBPool.getInstance().getDruidConnection();
        deleteIMData(conn);
        deleteCacheIMData(conn);
        conn.close();

    }

    /**
     * 删除IMData
     * @param leadsUuid
     */
    private static void deleteIMData(String leadsUuid){
        leadsUuid = leadsUuid.replace("-","");

        Credential credential = new ClientSecretCredential(Constants.APP_CLIENT_ID,
                Constants.APP_CLIENT_SECRET, Roles.USER_ROLE_APPADMIN);

        JerseyWebTarget webTarget = EndPoints.USERS_TARGET
                .resolveTemplate("org_name", APPKEY.split("#")[0])
                .resolveTemplate("app_name", APPKEY.split("#")[1])
                .path(leadsUuid);
        ObjectNode objectNode = JerseyUtils.sendRequest(webTarget, null, credential,
                HTTPMethod.METHOD_DELETE, null);
        LOGGER.info("删除用户:{}",objectNode);

    }

    private static String getLeadsUuid() throws SQLException {
        Connection conn = DBPool.getInstance().getDruidConnection();
        String sql = "select uuid from leads where phone=?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1,phone);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            String leadsUuid = resultSet.getString(1);
            preparedStatement.close();
            conn.close();
            return leadsUuid;
        }
        return null;
    }


    /**
     * 将缓存中用户注册IM标志改为false
     * @param conn
     * @throws SQLException
     */
    private static void deleteCacheIMData(Connection conn) throws SQLException {
        String sql = "select leads_uuid,phone from leads_ext ";
        if (StringUtils.isNotBlank(phone)) {
            sql += "WHERE phone='" + phone + "'";
        }
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
        String updateSql = "update leads_ext set easemob_uuid='',easemob_username='' ";
        if (StringUtils.isNotBlank(phone)) {
            updateSql += "WHERE phone='" + phone + "'";
        }
        PreparedStatement preparedStatement = conn.prepareStatement(updateSql);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }
}
