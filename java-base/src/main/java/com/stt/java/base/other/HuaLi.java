package com.stt.java.base.other;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/9/14.
 */
public class HuaLi implements Serializable{
    private static final long serialVersionUID = 1L;
    private String realName;
    private Date birthday;
    private Integer gender;
    private String introduce;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }
}
