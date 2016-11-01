//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yidu.novel.bean;

import org.yidu.novel.bean.BaseSearchBean;

public class UserSearchBean extends BaseSearchBean {
    private int userno;
    private String loginid;
    private String username;
    private String email;
    private int sex;
    private Boolean deleteflag;

    public UserSearchBean() {
    }

    public int getUserno() {
        return this.userno;
    }

    public void setUserno(int userno) {
        this.userno = userno;
    }

    public String getLoginid() {
        return this.loginid;
    }

    public void setLoginid(String loginid) {
        this.loginid = loginid;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getSex() {
        return this.sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public Boolean getDeleteflag() {
        return this.deleteflag;
    }

    public void setDeleteflag(Boolean deleteflag) {
        this.deleteflag = deleteflag;
    }
}
