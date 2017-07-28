package com.stt.onlyhi;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.stt.im.ClientSecretCredential;
import com.stt.im.Constants;
import com.stt.im.Credential;
import com.stt.im.EndPoints;
import com.stt.im.HTTPMethod;
import com.stt.im.JerseyUtils;
import com.stt.im.Roles;
import com.stt.util2.DBPool;
import org.glassfish.jersey.client.JerseyWebTarget;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static com.stt.im.Constants.APPKEY;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/7/21.
 */
public class SettingIMNickName {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeleteStudentRegisterInfo.class);

    public static void main(String[] args) throws SQLException {
        int count = 1;
        Map<String, String> userMap = getIMResgisterUser();
        Set<Map.Entry<String, String>> entrySet = userMap.entrySet();
        for (Map.Entry<String, String> entry : entrySet){
            String imUserName = entry.getKey();
            String userName = entry.getValue();
            ObjectNode objectNode = updateNickName(imUserName, userName);
            LOGGER.info("修改用户推送显示昵称: " + objectNode);
            LOGGER.info("修改昵称成功: " + count++);
        }
    }

    /**
     * 修改用户推送显示昵称
     * @param userName
     * @param nickName
     * @return
     */
    private static ObjectNode updateNickName(String userName, String nickName) {
        JsonNodeFactory factory = new JsonNodeFactory(false);
        ObjectNode objectNode = factory.objectNode();
        String appKey = APPKEY;
        // check appKey format
        if (!JerseyUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", appKey)) {
            LOGGER.error("Bad format of Appkey: " + appKey);
            objectNode.put("message", "Bad format of Appkey");
            return objectNode;
        }
        JerseyWebTarget webTarget = EndPoints.UPDATE_NICKNAME_TARGET
                .resolveTemplate("org_name", appKey.split("#")[0])
                .resolveTemplate("app_name", appKey.split("#")[1])
                .resolveTemplate("username", userName);
        ObjectNode datanode = JsonNodeFactory.instance.objectNode();
        datanode.put("nickname",nickName);
        Credential credential = new ClientSecretCredential(Constants.APP_CLIENT_ID,
                Constants.APP_CLIENT_SECRET, Roles.USER_ROLE_APPADMIN);
        objectNode = JerseyUtils.sendRequest(webTarget, datanode, credential, HTTPMethod.METHOD_PUT, null);

        return objectNode;
    }

    /**
     * 获取已注册IM用户信息（IMUserName和userName）
     * @return
     * @throws SQLException
     */
    private static Map<String,String> getIMResgisterUser() throws SQLException {
        Map<String,String> userMap = new HashMap<>();
        Connection conn = DBPool.getInstance().getDruidConnection();
        String sql = "SELECT le.easemob_username,l.`name` FROM leads_ext le LEFT JOIN leads l ON l.uuid = le.leads_uuid WHERE le.easemob_username != '' AND le.easemob_username IS NOT NULL";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            String imUserName = resultSet.getString(1);
            String userName = resultSet.getString(2);
            userMap.put(imUserName,userName);
        }
        preparedStatement.close();
        conn.close();
        return userMap;
    }
}
