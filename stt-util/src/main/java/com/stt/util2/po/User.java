package com.stt.util2.po;

import java.util.Date;

public class User {
    private Long id;

    private String uuid;

    private String loginName;

    private String name;

    private String password;

    private String email;

    private String phone;

    private Boolean sex;

    private Integer age;

    private String organizationUuid;

    private String teamUuid;

    private String postsUuid;

    private String roleUuid;

    private Boolean userType;

    private Boolean status;

    private Date createDate;

    private Date updateDate;

    private Date passwordExpiration;

    private String seatsNo;

    private String seatsPassword;

    private String hotlinePhone;

    private Integer seatsCallStatus;

    private Integer seatsStatus;

    private Integer dayCallNum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getOrganizationUuid() {
        return organizationUuid;
    }

    public void setOrganizationUuid(String organizationUuid) {
        this.organizationUuid = organizationUuid == null ? null : organizationUuid.trim();
    }

    public String getTeamUuid() {
        return teamUuid;
    }

    public void setTeamUuid(String teamUuid) {
        this.teamUuid = teamUuid == null ? null : teamUuid.trim();
    }

    public String getPostsUuid() {
        return postsUuid;
    }

    public void setPostsUuid(String postsUuid) {
        this.postsUuid = postsUuid == null ? null : postsUuid.trim();
    }

    public String getRoleUuid() {
        return roleUuid;
    }

    public void setRoleUuid(String roleUuid) {
        this.roleUuid = roleUuid == null ? null : roleUuid.trim();
    }

    public Boolean getUserType() {
        return userType;
    }

    public void setUserType(Boolean userType) {
        this.userType = userType;
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

    public Date getPasswordExpiration() {
        return passwordExpiration;
    }

    public void setPasswordExpiration(Date passwordExpiration) {
        this.passwordExpiration = passwordExpiration;
    }

    public String getSeatsNo() {
        return seatsNo;
    }

    public void setSeatsNo(String seatsNo) {
        this.seatsNo = seatsNo == null ? null : seatsNo.trim();
    }

    public String getSeatsPassword() {
        return seatsPassword;
    }

    public void setSeatsPassword(String seatsPassword) {
        this.seatsPassword = seatsPassword == null ? null : seatsPassword.trim();
    }

    public String getHotlinePhone() {
        return hotlinePhone;
    }

    public void setHotlinePhone(String hotlinePhone) {
        this.hotlinePhone = hotlinePhone == null ? null : hotlinePhone.trim();
    }

    public Integer getSeatsCallStatus() {
        return seatsCallStatus;
    }

    public void setSeatsCallStatus(Integer seatsCallStatus) {
        this.seatsCallStatus = seatsCallStatus;
    }

    public Integer getSeatsStatus() {
        return seatsStatus;
    }

    public void setSeatsStatus(Integer seatsStatus) {
        this.seatsStatus = seatsStatus;
    }

    public Integer getDayCallNum() {
        return dayCallNum;
    }

    public void setDayCallNum(Integer dayCallNum) {
        this.dayCallNum = dayCallNum;
    }
}