package com.stt.util2.cache;



/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/4/20.
 */
public class LoginUserCache {

    private String token;

    private String userUuid;

    private String userName;

    private String userType;

    private String phone;

    private String deviceType;

    private String deviceId;

    private String password;    //一次加密后的密码

    private boolean registerIMFlag;   //是否注册IM标记,false:否，true：是

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isRegisterIMFlag() {
        return registerIMFlag;
    }

    public void setRegisterIMFlag(boolean registerIMFlag) {
        this.registerIMFlag = registerIMFlag;
    }
}
