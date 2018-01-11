package com.stt.util2;

import com.stt.util2.po.Leads;
import com.stt.util2.po.User;
import com.stt.util2.po.UserRole;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/12/6.
 */
public class CreateTestAccount5 {
    public static String[] subjects = new String[]{"语文", "数学", "英语", "物理", "化学", "生物", "历史", "政治", "地理", "科学"};
    public static String grade = "高一";

    public static void main(String[] args) throws SQLException {
        Connection conn = DBPool.getInstance().getDruidConnection();
        //创建cc
//        List<User> ccList = createCC(conn);
        //创建cr
//        List<User> crList = createCR(conn);
        //创建教学监课
        List<User> tsList = createTS(conn);
        //创建leads
//        List<Leads> leadsList = createLeads(conn, ccList, crList);
        //创建teacher
        //创建cp_course_order
        //创建cp_course

        conn.close();
    }

    /**
     * 创建教学监课账号
     *
     * @param conn
     * @return
     * @throws Exception
     */
    public static List<User> createTS(Connection conn) throws SQLException {
        List<User> tsList = new ArrayList<>();
        String sql = "select uuid from role where status=1 and alias='ts' and `name`='教学-监课'";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        String roleUuid = resultSet.getString(1);

        for (int i = 0; i < 10; i++) {
            User user = new User();
            String uuid = UUIDUtil.randomUUID2();
            String loginName = "tstest" + i;
            String name = "ts测试账号" + i;
            String password = SecurityUtil.hashSha512Hex(loginName + "&" + "1234567" + ":onlyhi");
            String postsUuid = "7B64D910-D1EE-411F-B200-809F53267FEF";

            user.setUuid(uuid);
            user.setLoginName(loginName);
            user.setName(name);
            user.setPassword(password);
            user.setPostsUuid(postsUuid);
            user.setRoleUuid(roleUuid);
            tsList.add(user);

            sql = "insert into user(uuid,login_name,name,password,posts_uuid,role_uuid) values(?,?,?,?,?,?)";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, uuid);
            preparedStatement.setString(2, loginName);
            preparedStatement.setString(3, name);
            preparedStatement.setString(4, password);
            preparedStatement.setString(5, postsUuid);
            preparedStatement.setString(6, roleUuid);
            preparedStatement.executeUpdate();

            UserRole userRole = new UserRole();
            userRole.setUserUuid(uuid);
            userRole.setRoleUuid(roleUuid);
            sql = "insert into user_role(user_uuid,role_uuid) values(?,?)";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, uuid);
            preparedStatement.setString(2, roleUuid);
            preparedStatement.executeUpdate();
        }
        preparedStatement.close();
        return tsList;
    }

    /**
     * 创建leads
     *
     * @param conn
     * @param ccList
     * @param crList
     * @return
     * @throws SQLException
     */
    public static List<Leads> createLeads(Connection conn, List<User> ccList, List<User> crList) throws SQLException {
        List<Leads> leadsList = new ArrayList<>();
        String phoneTemp = "189000000";
        String phone = "";
        String sql = "insert into leads(uuid,cc_uuid,cr_uuid,name,password,phone,grade,subject) values(?,?,?,?,?,?,?,?) ";
        for (int i = 0; i < 100; i++) {
            if (i < 10) {
                phone = phoneTemp + "0" + i;
            } else {
                phone = phoneTemp + i;
            }
            int j = i % 10;
            String ccUuid = ccList.get(j).getUuid();
            String crUuid = crList.get(j).getUuid();
            String uuid = UUIDUtil.randomUUID2();
            String name = "学生测试账号" + i;
            String password = SecurityUtil.hashSha512Hex(phone + "&" + "123456" + ":onlyhi");

            Leads leads = new Leads();
            leads.setUuid(uuid);
            leads.setName(name);
            leads.setPassword(password);
            leads.setPhone(phone);
            leads.setCcUuid(ccUuid);
            leads.setCrUuid(crUuid);
            String subject = subjects[new Random().nextInt(10)];
            leads.setSubject(subject);
            leads.setGrade(grade);
            leadsList.add(leads);

            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, uuid);
            preparedStatement.setString(2, ccUuid);
            preparedStatement.setString(3, crUuid);
            preparedStatement.setString(4, name);
            preparedStatement.setString(5, password);
            preparedStatement.setString(6, phone);
            preparedStatement.setString(7, grade);
            preparedStatement.setString(8, subject);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }
        return leadsList;
    }

    /**
     * 创建CR
     *
     * @param conn
     * @return
     * @throws SQLException
     */
    public static List<User> createCR(Connection conn) throws SQLException {
        List<User> userList = new ArrayList<>();
        String sql = "select uuid from role where status=1 and alias='cr' and `name`='班主任';";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        String roleUuid = resultSet.getString(1);

        for (int i = 0; i < 10; i++) {
            User user = new User();
            String uuid = UUIDUtil.randomUUID2();
            String loginName = "crtest" + i;
            String name = "cr测试账号" + i;
            String password = SecurityUtil.hashSha512Hex(loginName + "&" + "1234567" + ":onlyhi");
            String postsUuid = "7B64D910-D1EE-411F-B200-809F53267FEF";

            user.setUuid(uuid);
            user.setLoginName(loginName);
            user.setName(name);
            user.setPassword(password);
            user.setPostsUuid(postsUuid);
            user.setRoleUuid(roleUuid);
            userList.add(user);

            sql = "insert into user(uuid,login_name,name,password,posts_uuid,role_uuid) values(?,?,?,?,?,?)";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, uuid);
            preparedStatement.setString(2, loginName);
            preparedStatement.setString(3, name);
            preparedStatement.setString(4, password);
            preparedStatement.setString(5, postsUuid);
            preparedStatement.setString(6, roleUuid);
            preparedStatement.executeUpdate();

            UserRole userRole = new UserRole();
            userRole.setUserUuid(uuid);
            userRole.setRoleUuid(roleUuid);
            sql = "insert into user_role(user_uuid,role_uuid) values(?,?)";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, uuid);
            preparedStatement.setString(2, roleUuid);
            preparedStatement.executeUpdate();
        }
        preparedStatement.close();
        return userList;
    }

    /**
     * 创建CC
     *
     * @param conn
     * @return
     * @throws SQLException
     */
    public static List<User> createCC(Connection conn) throws SQLException {
        List<User> ccList = new ArrayList<>();
        String sql = "select uuid from role where status=1 and alias='cc' and `name`='课程顾问'";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        String roleUuid = resultSet.getString(1);

        for (int i = 0; i < 10; i++) {
            User user = new User();
            String uuid = UUIDUtil.randomUUID2();
            String loginName = "cctest" + i;
            String name = "cc测试账号" + i;
            String password = SecurityUtil.hashSha512Hex(loginName + "&" + "1234567" + ":onlyhi");
            String postsUuid = "7B64D910-D1EE-411F-B200-809F53267FEF";

            user.setUuid(uuid);
            user.setLoginName(loginName);
            user.setName(name);
            user.setPassword(password);
            user.setPostsUuid(postsUuid);
            user.setRoleUuid(roleUuid);
            ccList.add(user);

            sql = "insert into user(uuid,login_name,name,password,posts_uuid,role_uuid) values(?,?,?,?,?,?)";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, uuid);
            preparedStatement.setString(2, loginName);
            preparedStatement.setString(3, name);
            preparedStatement.setString(4, password);
            preparedStatement.setString(5, postsUuid);
            preparedStatement.setString(6, roleUuid);
            preparedStatement.executeUpdate();

            UserRole userRole = new UserRole();
            userRole.setUserUuid(uuid);
            userRole.setRoleUuid(roleUuid);
            sql = "insert into user_role(user_uuid,role_uuid) values(?,?)";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, uuid);
            preparedStatement.setString(2, roleUuid);
            preparedStatement.executeUpdate();
        }
        preparedStatement.close();
        return ccList;
    }
}
