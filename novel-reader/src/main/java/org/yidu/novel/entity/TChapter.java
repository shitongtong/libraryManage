//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yidu.novel.entity;

import java.io.Serializable;
import java.util.Date;

public class TChapter implements Serializable {
    private static final long serialVersionUID = -1179430868094869232L;
    private int chapterno;
    private Integer articleno;
    private String articlename;
    private Short chaptertype;
    private String chaptername;
    private Integer size;
    private Boolean isvip;
    private Date postdate;
    private Date publishtime;
    private Boolean deleteflag;
    private Integer modifyuserno;
    private Date modifytime;

    public TChapter() {
    }

    public TChapter(int chapterno) {
        this.chapterno = chapterno;
    }

    public int getChapterno() {
        return this.chapterno;
    }

    public void setChapterno(int chapterno) {
        this.chapterno = chapterno;
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

    public Short getChaptertype() {
        return this.chaptertype;
    }

    public void setChaptertype(Short chaptertype) {
        this.chaptertype = chaptertype;
    }

    public String getChaptername() {
        return this.chaptername;
    }

    public void setChaptername(String chaptername) {
        this.chaptername = chaptername;
    }

    public Integer getSize() {
        return this.size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Boolean getIsvip() {
        return this.isvip;
    }

    public void setIsvip(Boolean isvip) {
        this.isvip = isvip;
    }

    public Date getPostdate() {
        return this.postdate;
    }

    public void setPostdate(Date postdate) {
        this.postdate = postdate;
    }

    public Date getPublishtime() {
        return this.publishtime;
    }

    public void setPublishtime(Date publishtime) {
        this.publishtime = publishtime;
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
