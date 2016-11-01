//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yidu.novel.entity;

import java.io.Serializable;
import java.util.Date;

public class TUser implements Serializable {
    private static final long serialVersionUID = 8319861322717026182L;
    private int userno;
    private String loginid;
    private String password;
    private String username;
    private String email;
    private Date regdate;
    private Short sex;
    private String qq;
    private Date lastlogin;
    private String lineno;
    private Short type;
    private Integer votecount;
    private Boolean deleteflag;
    private String realname;
    private String id;
    private String mobileno;
    private String branch;
    private String bankno;
    private String alipayacount;
    private Integer modifyuserno;
    private Date modifytime;

    public TUser() {
    }

    public TUser(int userno, String loginid, String username) {
        this.userno = userno;
        this.loginid = loginid;
        this.username = username;
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

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Date getRegdate() {
        return this.regdate;
    }

    public void setRegdate(Date regdate) {
        this.regdate = regdate;
    }

    public Short getSex() {
        return this.sex;
    }

    public void setSex(Short sex) {
        this.sex = sex;
    }

    public String getQq() {
        return this.qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public Date getLastlogin() {
        return this.lastlogin;
    }

    public void setLastlogin(Date lastlogin) {
        this.lastlogin = lastlogin;
    }

    public String getLineno() {
        return this.lineno;
    }

    public void setLineno(String lineno) {
        this.lineno = lineno;
    }

    public Short getType() {
        return this.type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public Integer getVotecount() {
        return this.votecount;
    }

    public void setVotecount(Integer votecount) {
        this.votecount = votecount;
    }

    public Boolean getDeleteflag() {
        return this.deleteflag;
    }

    public void setDeleteflag(Boolean deleteflag) {
        this.deleteflag = deleteflag;
    }

    public String getRealname() {
        return this.realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMobileno() {
        return this.mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String getBranch() {
        return this.branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getBankno() {
        return this.bankno;
    }

    public void setBankno(String bankno) {
        this.bankno = bankno;
    }

    public String getAlipayacount() {
        return this.alipayacount;
    }

    public void setAlipayacount(String alipayacount) {
        this.alipayacount = alipayacount;
    }

    public Integer getModifyuserno() {
        return this.modifyuserno;
    }

    public void setModifyuserno(Integer modifyuserno) {
        this.modifyuserno = modifyuserno;
    }

    public Date getModifytime() {
        return this.modifytime;
    }

    public void setModifytime(Date modifytime) {
        this.modifytime = modifytime;
    }
}
