//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yidu.novel.entity;

import java.io.Serializable;
import java.util.Date;

public class TBookcase implements Serializable {
    private static final long serialVersionUID = 6985324103555781120L;
    private int bookcaseno;
    private Integer articleno;
    private String articlename;
    private Integer category;
    private Integer userno;
    private String username;
    private Integer chapterno;
    private String chaptername;
    private Date lastvisit;
    private Date createtime;
    private Boolean deleteflag;
    private Integer modifyuserno;
    private Date modifytime;

    public TBookcase() {
    }

    public TBookcase(int bookcaseno) {
        this.bookcaseno = bookcaseno;
    }

    public int getBookcaseno() {
        return this.bookcaseno;
    }

    public void setBookcaseno(int bookcaseno) {
        this.bookcaseno = bookcaseno;
    }

    public Integer getArticleno() {
        return this.articleno;
    }

    public void setArticleno(Integer articleno) {
        this.articleno = articleno;
    }

    public String getArticlename() {
        return this.articlename;
    }

    public void setArticlename(String articlename) {
        this.articlename = articlename;
    }

    public Integer getCategory() {
        return this.category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Integer getUserno() {
        return this.userno;
    }

    public void setUserno(Integer userno) {
        this.userno = userno;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getChapterno() {
        return this.chapterno;
    }

    public void setChapterno(Integer chapterno) {
        this.chapterno = chapterno;
    }

    public String getChaptername() {
        return this.chaptername;
    }

    public void setChaptername(String chaptername) {
        this.chaptername = chaptername;
    }

    public Date getLastvisit() {
        return this.lastvisit;
    }

    public void setLastvisit(Date lastvisit) {
        this.lastvisit = lastvisit;
    }

    public Date getCreatetime() {
        return this.createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
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

    public Integer getSubdir() {
        return Integer.valueOf(this.articleno.intValue() / 1000);
    }
}
