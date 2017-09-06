package com.stt.onlyhi;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.stt.im.ClientSecretCredential;
import com.stt.im.Constants;
import com.stt.im.Credential;
import com.stt.im.EndPoints;
import com.stt.im.HTTPMethod;
import com.stt.im.JerseyUtils;
import com.stt.im.Roles;
import com.stt.util.RedisUtil;
import com.stt.util2.DBPool;
import org.apache.commons.lang.StringUtils;
import org.glassfish.jersey.client.JerseyWebTarget;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static com.stt.im.Constants.APPKEY;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/7/18.
 */
public class DeleteStudentRegisterInfo {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeleteStudentRegisterInfo.class);

    private static final String phone = "13052509803";

    public static void main(String[] args) {

        try {
            String leadsUuid = getLeadsUuid();
            if (StringUtils.isBlank(leadsUuid)){
                return;
            }
            LOGGER.info("获取用户uuid:{}",leadsUuid);
            deleteDBData();
            LOGGER.info("删除用户DB数据成功");
            deleteRedisData();
            LOGGER.info("删除用户redis数据成功");
            deleteIMData(leadsUuid);
            LOGGER.info("删除用户IM数据成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteIMData(){
        deleteIMData("591f9ad95a554a2197f8314f0abd4d4b");
    }

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

    private static void deleteRedisData(){
        RedisUtil redisUtil = new RedisUtil("106.14.46.51", 6300, "onlyhi.cn");
        String indexToken = phone + "STUDENT";
        String token = redisUtil.get(indexToken);
        redisUtil.del(token,indexToken);
    }

    private static void deleteDBData() throws Exception {
        Connection conn = DBPool.getInstance().getDruidConnection();
        String sql0 = "delete from student where phone=?";
        String sql1 = "delete from leads where phone=?";
        String sql2 = "delete from leads_ext where phone=?";
        String sql3 = "delete from user_qq where phone=?";
        String sql4 = "delete from user_wechat where phone=?";
        String sql5 = "delete from user_sinamicroblog where phone=?";
        List<String> sqlList = new ArrayList<>();
        sqlList.add(sql0);
        sqlList.add(sql1);
        sqlList.add(sql2);
        sqlList.add(sql3);
        sqlList.add(sql4);
        sqlList.add(sql5);
        PreparedStatement preparedStatement;
        for (int i = 0; i < sqlList.size(); i++) {
            String sql  = sqlList.get(i);
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,phone);
            preparedStatement.execute();
            preparedStatement.close();
        }
        conn.close();
        /*PreparedStatement preparedStatement = conn.prepareStatement(deleteStudentSql);
        PreparedStatement preparedStatement1 = conn.prepareStatement(deleteLeadsSql);
        preparedStatement.setString(1,phone);
        PreparedStatement preparedStatement2 = conn.prepareStatement(deleteLeadsExtSql);
        preparedStatement.setString(1,phone);
        PreparedStatement preparedStatement3 = conn.prepareStatement(deleteUserQqSql);
        preparedStatement.setString(1,phone);
        PreparedStatement preparedStatement4 = conn.prepareStatement(deleteUserWechatSql);
        preparedStatement.setString(1,phone);
        PreparedStatement preparedStatement5 = conn.prepareStatement(deleteUserSinamicroblogSql);
        preparedStatement.setString(1,phone);*/
    }

    private static String getLeadsUuid() throws Exception {
        Connection conn = DBPool.getInstance().getDruidConnection();
        String sql = "select uuid from leads where phone=?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1,phone);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            String leadsUuid = resultSet.getString(1);
            return leadsUuid;
        }
        return null;
    }
}
