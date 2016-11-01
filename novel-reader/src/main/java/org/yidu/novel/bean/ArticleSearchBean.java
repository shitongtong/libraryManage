//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yidu.novel.bean;

import java.util.Date;
import org.yidu.novel.bean.BaseSearchBean;

public class ArticleSearchBean extends BaseSearchBean {
    private int articleno;
    private Date lastupdate;
    private String articlename;
    private String keywords;
    private Integer authorid;
    private String author;
    private Integer category;
    private Boolean fullflag;
    private String key;
    private String articlenos;
    private int pageType;

    public ArticleSearchBean() {
    }

    public int getArticleno() {
        return this.articleno;
    }

    public void setArticleno(int articleno) {
        this.articleno = articleno;
    }

    public Date getLastupdate() {
        return this.lastupdate;
    }

    public void setLastupdate(Date lastupdate) {
        this.lastupdate = lastupdate;
    }

    public String getArticlename() {
        return this.articlename;
    }

    public void setArticlename(String articlename) {
        this.articlename = articlename;
    }

    public String getKeywords() {
        return this.keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public Integer getAuthorid() {
        return this.authorid;
    }

    public void setAuthorid(Integer authorid) {
        this.authorid = authorid;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getCategory() {
        return this.category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Boolean getFullflag() {
        return this.fullflag;
    }

    public void setFullflag(Boolean fullflag) {
        this.fullflag = fullflag;
    }

    public String getArticlenos() {
        return this.articlenos;
    }

    public void setArticlenos(String articlenos) {
        this.articlenos = articlenos;
    }

    public int getPageType() {
        return this.pageType;
    }

    public void setPageType(int pageType) {
        this.pageType = pageType;
    }

    public String toString() {
        return "ArticleSearchBean [articleno=" + this.articleno + ", lastupdate=" + this.lastupdate + ", articlename=" + this.articlename + ", keywords=" + this.keywords + ", authorid=" + this.authorid + ", author=" + this.author + ", category=" + this.category + ", fullflag=" + this.fullflag + ", key=" + this.key + ", articlenos=" + this.articlenos + ", pageType=" + this.pageType + this.getPagination() + "]";
    }

    public class PageType {
        public static final int publicPage = 1;
        public static final int authorPage = 2;
        public static final int adminPage = 3;

        public PageType() {
        }
    }
}
