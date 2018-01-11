package com.stt.util2;

import com.stt.util2.po.TcTeacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static com.stt.util2.CreateTestAccount.createTeacher;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/11/18.
 */
public class CreateTestAccount4 {
    public static void main(String[] args) throws SQLException {
        Connection conn = DBPool.getInstance().getDruidConnection();
        //创建teacher对象列表
        List<TcTeacher> teacherList = createTeacher();
        String sql3 = "insert into tc_teacher(uuid,tc_name,password,phone,teacher_status) values(?,?,?,?,?) ";
        for (TcTeacher teacher : teacherList) {
            PreparedStatement preparedStatement = conn.prepareStatement(sql3);
            preparedStatement.setString(1, teacher.getUuid());
            preparedStatement.setString(2, teacher.getTcName());
            preparedStatement.setString(3, teacher.getPassword());
            preparedStatement.setString(4, teacher.getPhone());
            preparedStatement.setInt(5, teacher.getTeacherStatus());
            preparedStatement.executeUpdate();
            preparedStatement.close();

            System.out.println(teacher.getUuid());
        }
        System.out.println("==================创建teacher对象列表END=====================");
    }
}
