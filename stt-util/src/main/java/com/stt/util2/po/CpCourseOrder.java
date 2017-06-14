package com.stt.util2.po;

import java.math.BigDecimal;
import java.util.Date;

public class CpCourseOrder {
    private Integer id;

    private String uuid;

    private String leadsUuid;

    private String requestUuid;

    private String userUuid;

    private String payUuid;

    private String teacherUuid;

    private String teacherName;

    private String teacherPhone;

    private Byte type;

    private BigDecimal length;

    private Byte style;

    private String hz;

    private Byte level;

    private Byte teacherSex;

    private String teachRequest;

    private String grade;

    private String subject;

    private String subjectVersion;

    private String studyStatus;

    private String monthSocre;

    private String fullMonthScore;

    private String termScore;

    private String fullTeamScore;

    private String otherScore;

    private String fullOtherScore;

    private String monthPoint;

    private String termPoint;

    private String otherPoint;

    private Byte feedbackStaus;

    private String feedbackDes;

    private Boolean isPayRequest;

    private Boolean ispay;

    private Boolean isFirstCourse;

    private Boolean status;

    private Date createDate;

    private Date updateDate;

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

    public String getLeadsUuid() {
        return leadsUuid;
    }

    public void setLeadsUuid(String leadsUuid) {
        this.leadsUuid = leadsUuid == null ? null : leadsUuid.trim();
    }

    public String getRequestUuid() {
        return requestUuid;
    }

    public void setRequestUuid(String requestUuid) {
        this.requestUuid = requestUuid == null ? null : requestUuid.trim();
    }

    public String getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid == null ? null : userUuid.trim();
    }

    public String getPayUuid() {
        return payUuid;
    }

    public void setPayUuid(String payUuid) {
        this.payUuid = payUuid == null ? null : payUuid.trim();
    }

    public String getTeacherUuid() {
        return teacherUuid;
    }

    public void setTeacherUuid(String teacherUuid) {
        this.teacherUuid = teacherUuid == null ? null : teacherUuid.trim();
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName == null ? null : teacherName.trim();
    }

    public String getTeacherPhone() {
        return teacherPhone;
    }

    public void setTeacherPhone(String teacherPhone) {
        this.teacherPhone = teacherPhone == null ? null : teacherPhone.trim();
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public BigDecimal getLength() {
        return length;
    }

    public void setLength(BigDecimal length) {
        this.length = length;
    }

    public Byte getStyle() {
        return style;
    }

    public void setStyle(Byte style) {
        this.style = style;
    }

    public String getHz() {
        return hz;
    }

    public void setHz(String hz) {
        this.hz = hz == null ? null : hz.trim();
    }

    public Byte getLevel() {
        return level;
    }

    public void setLevel(Byte level) {
        this.level = level;
    }

    public Byte getTeacherSex() {
        return teacherSex;
    }

    public void setTeacherSex(Byte teacherSex) {
        this.teacherSex = teacherSex;
    }

    public String getTeachRequest() {
        return teachRequest;
    }

    public void setTeachRequest(String teachRequest) {
        this.teachRequest = teachRequest == null ? null : teachRequest.trim();
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

    public String getSubjectVersion() {
        return subjectVersion;
    }

    public void setSubjectVersion(String subjectVersion) {
        this.subjectVersion = subjectVersion == null ? null : subjectVersion.trim();
    }

    public String getStudyStatus() {
        return studyStatus;
    }

    public void setStudyStatus(String studyStatus) {
        this.studyStatus = studyStatus == null ? null : studyStatus.trim();
    }

    public String getMonthSocre() {
        return monthSocre;
    }

    public void setMonthSocre(String monthSocre) {
        this.monthSocre = monthSocre == null ? null : monthSocre.trim();
    }

    public String getFullMonthScore() {
        return fullMonthScore;
    }

    public void setFullMonthScore(String fullMonthScore) {
        this.fullMonthScore = fullMonthScore == null ? null : fullMonthScore.trim();
    }

    public String getTermScore() {
        return termScore;
    }

    public void setTermScore(String termScore) {
        this.termScore = termScore == null ? null : termScore.trim();
    }

    public String getFullTeamScore() {
        return fullTeamScore;
    }

    public void setFullTeamScore(String fullTeamScore) {
        this.fullTeamScore = fullTeamScore == null ? null : fullTeamScore.trim();
    }

    public String getOtherScore() {
        return otherScore;
    }

    public void setOtherScore(String otherScore) {
        this.otherScore = otherScore == null ? null : otherScore.trim();
    }

    public String getFullOtherScore() {
        return fullOtherScore;
    }

    public void setFullOtherScore(String fullOtherScore) {
        this.fullOtherScore = fullOtherScore == null ? null : fullOtherScore.trim();
    }

    public String getMonthPoint() {
        return monthPoint;
    }

    public void setMonthPoint(String monthPoint) {
        this.monthPoint = monthPoint == null ? null : monthPoint.trim();
    }

    public String getTermPoint() {
        return termPoint;
    }

    public void setTermPoint(String termPoint) {
        this.termPoint = termPoint == null ? null : termPoint.trim();
    }

    public String getOtherPoint() {
        return otherPoint;
    }

    public void setOtherPoint(String otherPoint) {
        this.otherPoint = otherPoint == null ? null : otherPoint.trim();
    }

    public Byte getFeedbackStaus() {
        return feedbackStaus;
    }

    public void setFeedbackStaus(Byte feedbackStaus) {
        this.feedbackStaus = feedbackStaus;
    }

    public String getFeedbackDes() {
        return feedbackDes;
    }

    public void setFeedbackDes(String feedbackDes) {
        this.feedbackDes = feedbackDes == null ? null : feedbackDes.trim();
    }

    public Boolean getIsPayRequest() {
        return isPayRequest;
    }

    public void setIsPayRequest(Boolean isPayRequest) {
        this.isPayRequest = isPayRequest;
    }

    public Boolean getIspay() {
        return ispay;
    }

    public void setIspay(Boolean ispay) {
        this.ispay = ispay;
    }

    public Boolean getIsFirstCourse() {
        return isFirstCourse;
    }

    public void setIsFirstCourse(Boolean isFirstCourse) {
        this.isFirstCourse = isFirstCourse;
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
}