package com.stt.util2.po;

import java.util.Date;

public class CpCourse {
    private Integer id;

    private String uuid;

    private String courseOrderUuid;

    private String courseRequestUuid;

    private String payUuid;

    private String userUuid;

    private String leadsUuid;

    private String teacherUuid;

    private Boolean courseType;

    private String length;

    private String courseDate;

    private String startTime;

    private String endTime;

    private String remark;

    private Integer isSuccess;

    private Boolean status;

    private Date createDate;

    private Date updateDate;

    private String realStartTime;

    private String realEndTime;

    private Integer classStatus;

    private String saleUserUuid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public String getCourseOrderUuid() {
        return courseOrderUuid;
    }

    public void setCourseOrderUuid(String courseOrderUuid) {
        this.courseOrderUuid = courseOrderUuid == null ? null : courseOrderUuid.trim();
    }

    public String getCourseRequestUuid() {
        return courseRequestUuid;
    }

    public void setCourseRequestUuid(String courseRequestUuid) {
        this.courseRequestUuid = courseRequestUuid == null ? null : courseRequestUuid.trim();
    }

    public String getPayUuid() {
        return payUuid;
    }

    public void setPayUuid(String payUuid) {
        this.payUuid = payUuid == null ? null : payUuid.trim();
    }

    public String getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid == null ? null : userUuid.trim();
    }

    public String getLeadsUuid() {
        return leadsUuid;
    }

    public void setLeadsUuid(String leadsUuid) {
        this.leadsUuid = leadsUuid == null ? null : leadsUuid.trim();
    }

    public String getTeacherUuid() {
        return teacherUuid;
    }

    public void setTeacherUuid(String teacherUuid) {
        this.teacherUuid = teacherUuid == null ? null : teacherUuid.trim();
    }

    public Boolean getCourseType() {
        return courseType;
    }

    public void setCourseType(Boolean courseType) {
        this.courseType = courseType;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length == null ? null : length.trim();
    }

    public String getCourseDate() {
        return courseDate;
    }

    public void setCourseDate(String courseDate) {
        this.courseDate = courseDate == null ? null : courseDate.trim();
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime == null ? null : startTime.trim();
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime == null ? null : endTime.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Integer isSuccess) {
        this.isSuccess = isSuccess;
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

    public String getRealStartTime() {
        return realStartTime;
    }

    public void setRealStartTime(String realStartTime) {
        this.realStartTime = realStartTime == null ? null : realStartTime.trim();
    }

    public String getRealEndTime() {
        return realEndTime;
    }

    public void setRealEndTime(String realEndTime) {
        this.realEndTime = realEndTime == null ? null : realEndTime.trim();
    }

    public Integer getClassStatus() {
        return classStatus;
    }

    public void setClassStatus(Integer classStatus) {
        this.classStatus = classStatus;
    }

    public String getSaleUserUuid() {
        return saleUserUuid;
    }

    public void setSaleUserUuid(String saleUserUuid) {
        this.saleUserUuid = saleUserUuid == null ? null : saleUserUuid.trim();
    }
}