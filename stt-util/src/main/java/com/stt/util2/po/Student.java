package com.stt.util2.po;

import java.util.Date;

public class Student {
    private Long id;

    private String stuNo;

    private String leadUuid;

    private String teacherUuid;

    private String firstCourseUuid;

    private String ccUuid;

    private String crUuid;

    private String name;

    private String password;

    private String phone;

    private String grade;

    private String subject;

    private Boolean isHandout;

    private Boolean isIntroduced;

    private String introducer;

    private Boolean isFirstCourse;

    private String handoutDes;

    private Boolean isDistribution;

    private Boolean isBound;

    private Boolean status;

    private Date createDate;

    private Date updateDate;

    private Integer sex;

    private Integer age;

    private String examArea;

    private Boolean isDimissionType;

    private Byte keyPoint;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStuNo() {
        return stuNo;
    }

    public void setStuNo(String stuNo) {
        this.stuNo = stuNo == null ? null : stuNo.trim();
    }

    public String getLeadUuid() {
        return leadUuid;
    }

    public void setLeadUuid(String leadUuid) {
        this.leadUuid = leadUuid == null ? null : leadUuid.trim();
    }

    public String getTeacherUuid() {
        return teacherUuid;
    }

    public void setTeacherUuid(String teacherUuid) {
        this.teacherUuid = teacherUuid == null ? null : teacherUuid.trim();
    }

    public String getFirstCourseUuid() {
        return firstCourseUuid;
    }

    public void setFirstCourseUuid(String firstCourseUuid) {
        this.firstCourseUuid = firstCourseUuid == null ? null : firstCourseUuid.trim();
    }

    public String getCcUuid() {
        return ccUuid;
    }

    public void setCcUuid(String ccUuid) {
        this.ccUuid = ccUuid == null ? null : ccUuid.trim();
    }

    public String getCrUuid() {
        return crUuid;
    }

    public void setCrUuid(String crUuid) {
        this.crUuid = crUuid == null ? null : crUuid.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade == null ? null : grade.trim();
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject == null ? null : subject.trim();
    }

    public Boolean getIsHandout() {
        return isHandout;
    }

    public void setIsHandout(Boolean isHandout) {
        this.isHandout = isHandout;
    }

    public Boolean getIsIntroduced() {
        return isIntroduced;
    }

    public void setIsIntroduced(Boolean isIntroduced) {
        this.isIntroduced = isIntroduced;
    }

    public String getIntroducer() {
        return introducer;
    }

    public void setIntroducer(String introducer) {
        this.introducer = introducer == null ? null : introducer.trim();
    }

    public Boolean getIsFirstCourse() {
        return isFirstCourse;
    }

    public void setIsFirstCourse(Boolean isFirstCourse) {
        this.isFirstCourse = isFirstCourse;
    }

    public String getHandoutDes() {
        return handoutDes;
    }

    public void setHandoutDes(String handoutDes) {
        this.handoutDes = handoutDes == null ? null : handoutDes.trim();
    }

    public Boolean getIsDistribution() {
        return isDistribution;
    }

    public void setIsDistribution(Boolean isDistribution) {
        this.isDistribution = isDistribution;
    }

    public Boolean getIsBound() {
        return isBound;
    }

    public void setIsBound(Boolean isBound) {
        this.isBound = isBound;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getExamArea() {
        return examArea;
    }

    public void setExamArea(String examArea) {
        this.examArea = examArea == null ? null : examArea.trim();
    }

    public Boolean getIsDimissionType() {
        return isDimissionType;
    }

    public void setIsDimissionType(Boolean isDimissionType) {
        this.isDimissionType = isDimissionType;
    }

    public Byte getKeyPoint() {
        return keyPoint;
    }

    public void setKeyPoint(Byte keyPoint) {
        this.keyPoint = keyPoint;
    }
}