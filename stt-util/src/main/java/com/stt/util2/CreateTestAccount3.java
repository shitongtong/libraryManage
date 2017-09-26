package com.stt.util2;

import com.stt.util2.po.CourseLeads;
import com.stt.util2.po.CpCourse;
import com.stt.util2.po.CpCourseOrder;
import com.stt.util2.po.Leads;
import com.stt.util2.po.Student;
import com.stt.util2.po.TcTeacher;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

/**
 * 创建一个一对多测试账号
 *
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/9/26.
 */
public class CreateTestAccount3 {
    public static void main(String[] args) throws Exception {
        Connection conn = DBPool.getInstance().getDruidConnection();

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

        //创建teacher对象
        TcTeacher teacher = createTeacher();
        String sql3 = "insert into tc_teacher(uuid,tc_name,password,phone,teacher_status) values(?,?,?,?,?) ";
        PreparedStatement preparedStatement1 = conn.prepareStatement(sql3);
        preparedStatement1.setString(1, teacher.getUuid());
        preparedStatement1.setString(2, teacher.getTcName());
        preparedStatement1.setString(3, teacher.getPassword());
        preparedStatement1.setString(4, teacher.getPhone());
        preparedStatement1.setInt(5, teacher.getTeacherStatus());
        preparedStatement1.executeUpdate();
        preparedStatement1.close();
        System.out.println("==================创建teacher对象END=====================");

        //创建订单列表
        List<CpCourseOrder> courseOrderList = createrCpCourseOrder(leadsList, teacher);
        String sql4 = "insert into cp_course_order(uuid,leads_uuid,user_uuid,teacher_name,teacher_phone,type,length,subject,grade) values(?,?,?,?,?,?,?,?,?)";
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
            preparedStatement.setString(9, courseOrder.getGrade());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }
        System.out.println("==================创建订单列表END=====================");

        //创建课程
        CpCourse course = createrCpCourse(teacher);
        String sql5 = "insert into cp_course(uuid,course_type,length,course_date,start_time,end_time,teacher_uuid) values(?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement2 = conn.prepareStatement(sql5);
        preparedStatement2.setString(1, course.getUuid());
        preparedStatement2.setBoolean(2, course.getCourseType());
        preparedStatement2.setString(3, course.getLength());
        preparedStatement2.setString(4, course.getCourseDate());
        preparedStatement2.setString(5, course.getStartTime());
        preparedStatement2.setString(6, course.getEndTime());
        preparedStatement2.setString(7, course.getTeacherUuid());
        preparedStatement2.executeUpdate();
        preparedStatement2.close();
        System.out.println("课程uuid="+course.getUuid());
        System.out.println("==================创建课程列表END=====================");

        //创建课程学生数据
        List<CourseLeads> courseLeadsList = createrCourseLeads(leadsList, courseOrderList, course);
        String sql6 = "insert into course_leads(course_leads_uuid,course_uuid,leads_uuid,course_order_uuid) values(?,?,?,?)";
        for (CourseLeads courseLeads : courseLeadsList) {
            PreparedStatement preparedStatement = conn.prepareStatement(sql6);
            preparedStatement.setString(1, courseLeads.getCourseLeadsUuid());
            preparedStatement.setString(2, courseLeads.getCourseUuid());
            preparedStatement.setString(3, courseLeads.getLeadsUuid());
            preparedStatement.setString(4, courseLeads.getCourseOrderUuid());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }
        System.out.println("==================创建课程学生数据END=====================");

        conn.close();
    }

    //创建课程学生数据
    public static List<CourseLeads> createrCourseLeads(List<Leads> leadsList,List<CpCourseOrder> courseOrderList,CpCourse cpCourse){
        List<CourseLeads> courseLeadsList = new ArrayList<>();
        CourseLeads courseLeads = new CourseLeads();
        for (int i = 0; i < leadsList.size(); i++) {
            Leads leads = leadsList.get(i);
            CpCourseOrder courseOrder = courseOrderList.get(i);

            courseLeads = new CourseLeads();
            courseLeads.setCourseLeadsUuid(UUIDUtil.randomUUID2());
            courseLeads.setCourseUuid(cpCourse.getUuid());
            courseLeads.setLeadsUuid(leads.getUuid());
            courseLeads.setCourseOrderUuid(courseOrder.getUuid());

            courseLeadsList.add(courseLeads);
        }
        return courseLeadsList;
    }

    //创建课程
    public static CpCourse createrCpCourse(TcTeacher teacher) {
        CpCourse course = new CpCourse();
        course.setUuid(UUIDUtil.randomUUID2());
        course.setCourseType(false);
        course.setLength("1");
        course.setCourseDate("2017-09-26");
        course.setStartTime("01:30");
        course.setEndTime("23:30");
        course.setTeacherUuid(teacher.getUuid());
        return course;
    }

    //创建订单列表
    public static List<CpCourseOrder> createrCpCourseOrder(List<Leads> leadsList, TcTeacher teacher) {
        String teacherName = teacher.getTcName();
        String teacherPhone = teacher.getPhone();
        List<CpCourseOrder> courseOrderList = new ArrayList<>();
        for (int i = 0; i < leadsList.size(); i++) {
            Leads leads = leadsList.get(i);
            CpCourseOrder courseOrder = new CpCourseOrder();
            courseOrder.setUuid(UUIDUtil.randomUUID2());
            courseOrder.setUserUuid("ADE4B65A-4476-45A2-81AA-5862154A0930");
            courseOrder.setLeadsUuid(leads.getUuid());
            courseOrder.setTeacherName(teacherName);
            courseOrder.setTeacherPhone(teacherPhone);
            courseOrder.setType(Byte.valueOf("0"));
            courseOrder.setLength(new BigDecimal(1));
            courseOrder.setSubject("英语");
            courseOrder.setGrade("小三");
            courseOrderList.add(courseOrder);
        }
        return courseOrderList;
    }

    //创建老师
    public static TcTeacher createTeacher() {
        String phone = "18840000010";

        TcTeacher teacher = new TcTeacher();
        teacher.setUuid(UUIDUtil.randomUUID2());
        teacher.setTcName("老师一对多测试账号");
        String password = SecurityUtil.hashSha512Hex(phone + "&" + "123456" + ":onlyhi");
        teacher.setPassword(password);
        teacher.setPhone(phone);
        teacher.setTeacherStatus(5);

        return teacher;
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
        String phoneTemp = "1894000001";
        String phone = "";
        for (int i = 0; i < 3; i++) {
            /*if (i < 10) {
                phone = phoneTemp + "0" + i;
            } else {
                phone = phoneTemp + i;
            }*/
//            phone = "13916593205";
            phone = phoneTemp + i;
            Leads leads = new Leads();
            leads.setUuid(UUIDUtil.randomUUID2());
            leads.setStuNo(phone);
            leads.setName("学生一对多测试账号" + i);
            String password = SecurityUtil.hashSha512Hex(phone + "&" + "123456" + ":onlyhi");
            leads.setPassword(password);
            leads.setPhone(phone);

            leadsList.add(leads);
        }
        return leadsList;
    }
}
