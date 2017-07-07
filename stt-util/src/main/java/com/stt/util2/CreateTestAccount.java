package com.stt.util2;

import com.stt.util2.po.*;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/5/11.
 */
public class CreateTestAccount {

    public static void main(String[] args) throws Exception {
        Connection conn = DBPool.getInstance().getDruidConnection();

        //调用Class.forName()方法加载驱动程序
        /*Class.forName("com.mysql.jdbc.Driver");
        System.out.println("成功加载MySQL驱动！");

        String url="jdbc:mysql://rm-uf665r5id03a3i41o.mysql.rds.aliyuncs.com:3306/onlyhitest";    //JDBC的URL
        Connection conn;

        conn = DriverManager.getConnection(url,"onlyhiedu","Hkt2016!@#");*/

        //创建leads对象列表
        List<Leads> leadsList = createLeads();
        String sql1 = "insert into leads(uuid,stu_no,name,password,phone) values(?,?,?,?,?) ";
        for (Leads leads : leadsList) {
            PreparedStatement preparedStatement = conn.prepareStatement(sql1);
            preparedStatement.setString(1, leads.getUuid());
            preparedStatement.setString(2, leads.getStuNo());
            preparedStatement.setString(3, leads.getName());
            preparedStatement.setString(4, leads.getPassword());
            preparedStatement.setString(5, leads.getPhone());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }
        System.out.println("==================创建leads对象列表END=====================");

        //创建student对象列表
        List<Student> studentList = createStudent(leadsList);
        String sql2 = "insert into student(Lead_Uuid,stu_no,name,password,phone) values(?,?,?,?,?) ";
        for (Student student : studentList) {
            PreparedStatement preparedStatement = conn.prepareStatement(sql2);
            preparedStatement.setString(1, student.getLeadUuid());
            preparedStatement.setString(2, student.getStuNo());
            preparedStatement.setString(3, student.getName());
            preparedStatement.setString(4, student.getPassword());
            preparedStatement.setString(5, student.getPhone());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }
        System.out.println("==================创建student对象列表END=====================");

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
        }
        System.out.println("==================创建teacher对象列表END=====================");

        //创建订单列表
        List<CpCourseOrder> courseOrderList = createrCpCourseOrder(leadsList, teacherList);
        String sql4 = "insert into cp_course_order(uuid,leads_uuid,user_uuid,teacher_name,teacher_phone,type,length,subject) values(?,?,?,?,?,?,?,?)";
        for (CpCourseOrder courseOrder : courseOrderList) {
            PreparedStatement preparedStatement = conn.prepareStatement(sql4);
            preparedStatement.setString(1, courseOrder.getUuid());
            preparedStatement.setString(2, courseOrder.getLeadsUuid());
            preparedStatement.setString(3, courseOrder.getUserUuid());
            preparedStatement.setString(4, courseOrder.getTeacherName());
            preparedStatement.setString(5, courseOrder.getTeacherPhone());
            preparedStatement.setByte(6, courseOrder.getType());
            preparedStatement.setBigDecimal(7, courseOrder.getLength());
            preparedStatement.setString(8, courseOrder.getSubject());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }
        System.out.println("==================创建订单列表END=====================");

        //创建课程列表
        List<CpCourse> courseList = createrCpCourse(leadsList, teacherList, courseOrderList);
        String sql5 = "insert into cp_course(uuid,course_order_uuid,leads_uuid,course_type,length,course_date,start_time,end_time,teacher_uuid) values(?,?,?,?,?,?,?,?,?)";
        for (CpCourse course : courseList) {
            PreparedStatement preparedStatement = conn.prepareStatement(sql5);
            preparedStatement.setString(1, course.getUuid());
            preparedStatement.setString(2, course.getCourseOrderUuid());
            preparedStatement.setString(3, course.getLeadsUuid());
            preparedStatement.setBoolean(4, course.getCourseType());
            preparedStatement.setString(5, course.getLength());
            preparedStatement.setString(6, course.getCourseDate());
            preparedStatement.setString(7, course.getStartTime());
            preparedStatement.setString(8, course.getEndTime());
            preparedStatement.setString(9, course.getTeacherUuid());
            preparedStatement.executeUpdate();
            preparedStatement.close();

            System.out.print("'" + course.getUuid() + "',");
        }
        System.out.println();
        System.out.println("==================创建课程列表END=====================");


        conn.close();
    }

    //创建课程
    public static List<CpCourse> createrCpCourse(List<Leads> leadsList, List<TcTeacher> teacherList, List<CpCourseOrder> courseOrderList) {
        List<CpCourse> courseList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Leads leads = leadsList.get(i);
            TcTeacher teacher = teacherList.get(i);
            CpCourseOrder courseOrder = courseOrderList.get(i);

            CpCourse course = new CpCourse();
            course.setUuid(UUIDUtil.randomUUID());
            course.setCourseOrderUuid(courseOrder.getUuid());
            course.setLeadsUuid(leads.getUuid());
            course.setCourseType(false);
            course.setLength("1");
            course.setCourseDate("2017-06-27");
            course.setStartTime("00:30");
            course.setEndTime("23:30");
            course.setTeacherUuid(teacher.getUuid());

            courseList.add(course);
        }
        return courseList;
    }

    static String[] subjects = new String[]{"语文", "数学", "英语", "物理", "化学", "生物", "历史", "政治", "地理", "科学"};

    //创建订单列表
    public static List<CpCourseOrder> createrCpCourseOrder(List<Leads> leadsList, List<TcTeacher> teacherList) {
        List<CpCourseOrder> courseOrderList = new ArrayList<>();
        for (int i = 0; i < leadsList.size(); i++) {
            Leads leads = leadsList.get(i);
            TcTeacher teacher = teacherList.get(i);
            CpCourseOrder courseOrder = new CpCourseOrder();
            courseOrder.setUuid(UUIDUtil.randomUUID());
            courseOrder.setUserUuid("ADE4B65A-4476-45A2-81AA-5862154A0930");
            courseOrder.setLeadsUuid(leads.getUuid());
            courseOrder.setTeacherName(teacher.getTcName());
            courseOrder.setTeacherPhone(teacher.getPhone());
            courseOrder.setType(Byte.valueOf("0"));
            courseOrder.setLength(new BigDecimal(1));
            courseOrder.setSubject(subjects[new Random().nextInt(10)]);
            courseOrderList.add(courseOrder);
        }
        return courseOrderList;
    }

    //创建老师列表
    public static List<TcTeacher> createTeacher() {
        List<TcTeacher> teacherList = new ArrayList<>();
        String phoneTemp = "188000000";
        String phone = "";
        for (int i = 0; i < 50; i++) {
            if (i < 10) {
                phone = phoneTemp + "0" + i;
            } else {
                phone = phoneTemp + i;
            }

            TcTeacher teacher = new TcTeacher();
            teacher.setUuid(UUIDUtil.randomUUID());
            teacher.setTcName("老师测试账号" + (i + 1));
            String password = SecurityUtil.hashSha512Hex(phone + "&" + "123456" + ":onlyhi");
            teacher.setPassword(password);
            teacher.setPhone(phone);
            teacher.setTeacherStatus(5);

            teacherList.add(teacher);
        }
        return teacherList;
    }

    //创建student列表
    public static List<Student> createStudent(List<Leads> leadsList) {
        List<Student> studentList = new ArrayList<>();
        for (int i = 0; i < leadsList.size(); i++) {
            Leads leads = leadsList.get(i);

            Student student = new Student();
            student.setLeadUuid(leads.getUuid());
            student.setStuNo(leads.getStuNo());
            student.setName(leads.getName());
            student.setPassword(leads.getPassword());
            student.setPhone(leads.getPhone());

            studentList.add(student);
        }
        return studentList;
    }

    //创建leads列表
    public static List<Leads> createLeads() {
        List<Leads> leadsList = new ArrayList<>();
        String phoneTemp = "189000000";
        String phone = "";
        for (int i = 0; i < 50; i++) {
            if (i < 10) {
                phone = phoneTemp + "0" + i;
            } else {
                phone = phoneTemp + i;
            }

            Leads leads = new Leads();
            leads.setUuid(UUIDUtil.randomUUID());
            leads.setStuNo(phone);
            leads.setName("学生测试账号" + (i + 1));
            String password = SecurityUtil.hashSha512Hex(phone + "&" + "123456" + ":onlyhi");
            leads.setPassword(password);
            leads.setPhone(phone);

            leadsList.add(leads);
        }
        return leadsList;
    }
}
