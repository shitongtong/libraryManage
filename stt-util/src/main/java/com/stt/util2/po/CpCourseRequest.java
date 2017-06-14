package com.stt.util2.po;

import java.util.Date;

public class CpCourseRequest {
    private Integer id;

    private String uuid;

    private String courseOrderUuid;

    private String userUuid;

    private String dates;

    private String startTime;

    private String endTime;

    private String fileAddress;

    private String teacherName;

    private String originalFileName;

    private String serverFileName;

    private String other;

    private Byte requesetStatus;

    private Boolean status;

    private String giveupCause;

    private Date createDate;

    private Date updateDate;

    private String leadsUuid;

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

    public String getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid == null ? null : userUuid.trim();
    }

    public String getDates() {
        return dates;
    }

    public void setDates(String dates) {
        this.dates = dates == null ? null : dates.trim();
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

    public String getFileAddress() {
        return fileAddress;
    }

    public void setFileAddress(String fileAddress) {
        this.fileAddress = fileAddress == null ? null : fileAddress.trim();
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName == null ? null : teacherName.trim();
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName == null ? null : originalFileName.trim();
    }

    public String getServerFileName() {
        return serverFileName;
    }

    public void setServerFileName(String serverFileName) {
        this.serverFileName = serverFileName == null ? null : serverFileName.trim();
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other == null ? null : other.trim();
    }

    public Byte getRequesetStatus() {
        return requesetStatus;
    }

    public void setRequesetStatus(Byte requesetStatus) {
        this.requesetStatus = requesetStatus;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getGiveupCause() {
        return giveupCause;
    }

    public void setGiveupCause(String giveupCause) {
        this.giveupCause = giveupCause == null ? null : giveupCause.trim();
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

    public String getLeadsUuid() {
        return leadsUuid;
    }

    public void setLeadsUuid(String leadsUuid) {
        this.leadsUuid = leadsUuid == null ? null : leadsUuid.trim();
    }
}