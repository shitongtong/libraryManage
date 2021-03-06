//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yidu.novel.entity;

import java.io.Serializable;
import java.util.Date;

public class TMessage implements Serializable {
    private static final long serialVersionUID = -8954182132250066583L;
    private int messageno;
    private Integer userno;
    private String loginid;
    private Integer touserno;
    private String tologinid;
    private String title;
    private String content;
    private Short category;
    private Boolean isread;
    private Date postdate;
    private Boolean deleteflag;
    private Integer modifyuserno;
    private Date modifytime;

    public TMessage() {
    }

    public int getMessageno() {
        return this.messageno;
    }

    public void setMessageno(int messageno) {
        this.messageno = messageno;
    }

    public Date getPostdate() {
        return this.postdate;
    }

    public void setPostdate(Date postdate) {
        this.postdate = postdate;
    }

    public Integer getUserno() {
        return this.userno;
    }

    public void setUserno(Integer userno) {
        this.userno = userno;
    }

    public String getLoginid() {
        return this.loginid;
    }

    public void setLoginid(String loginid) {
        this.loginid = loginid;
    }

    public Integer getTouserno() {
        return this.touserno;
    }

    public void setTouserno(Integer touserno) {
        this.touserno = touserno;
    }

    public String getTologinid() {
        return this.tologinid;
    }

    public void setTologinid(String tologinid) {
        this.tologinid = tologinid;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Short getCategory() {
        return this.category;
    }

    public void setCategory(Short category) {
        this.category = category;
    }

    public Boolean getIsread() {
        return this.isread;
    }

    public void setIsread(Boolean isread) {
        this.isread = isread;
    }

    public Boolean getDeleteflag() {
        return this.deleteflag;
    }

    public void setDeleteflag(Boolean deleteflag) {
        this.deleteflag = deleteflag;
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
