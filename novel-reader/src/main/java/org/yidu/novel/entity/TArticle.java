//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yidu.novel.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.yidu.novel.constant.YiDuConstants;

public class TArticle implements Serializable {
    private static final long serialVersionUID = -4668183129971625875L;
    private int articleno;
    private String articlename;
    private String initial;
    private String keywords;
    private Integer authorid;
    private String author;
    private Integer category;
    private Integer subcategory;
    private String intro;
    private Integer lastchapterno;
    private String lastchapter;
    private Integer chapters;
    private Integer size;
    private Boolean fullflag;
    private Integer imgflag;
    private Date postdate;
    private Date lastupdate;
    private Boolean firstflag;
    private Integer permission;
    private Boolean authorflag;
    private String agent;
    private Integer dayvisit;
    private Integer weekvisit;
    private Integer monthvisit;
    private Integer allvisit;
    private Integer dayvote;
    private Integer weekvote;
    private Integer monthvote;
    private Integer allvote;
    private Boolean deleteflag;
    private Integer publicflag;
    private Integer modifyuserno;
    private Date modifytime;

    public TArticle() {
    }

    public TArticle(int articleno) {
        this.articleno = articleno;
    }

    public int getArticleno() {
        return this.articleno;
    }

    public void setArticleno(int articleno) {
        this.articleno = articleno;
    }

    public String getArticlename() {
        return this.articlename;
    }

    public void setArticlename(String articlename) {
        this.articlename = articlename;
    }

    public String getInitial() {
        return this.initial;
    }

    public void setInitial(String initial) {
        this.initial = initial;
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

    public Integer getSubcategory() {
        return this.subcategory;
    }

    public void setSubcategory(Integer subcategory) {
        this.subcategory = subcategory;
    }

    public String getIntro() {
        return this.intro;
    }

    public String getIntroForHtml() {
        if(this.intro != null) {
            String html = StringEscapeUtils.escapeHtml4(this.intro);
            return html.replaceAll("\r\n", "<br/>").replaceAll("\n", "<br/>").replaceAll("\\s", "&nbsp;");
        } else {
            return null;
        }
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public Integer getLastchapterno() {
        return this.lastchapterno;
    }

    public void setLastchapterno(Integer lastchapterno) {
        this.lastchapterno = lastchapterno;
    }

    public String getLastchapter() {
        return this.lastchapter;
    }

    public void setLastchapter(String lastchapter) {
        this.lastchapter = lastchapter;
    }

    public Boolean getFirstflag() {
        return this.firstflag;
    }

    public void setFirstflag(Boolean firstflag) {
        this.firstflag = firstflag;
    }

    public void setPermission(Integer permission) {
        this.permission = permission;
    }

    public Integer getPermission() {
        return this.permission;
    }

    public Boolean getAuthorflag() {
        return this.authorflag;
    }

    public void setAuthorflag(Boolean authorflag) {
        this.authorflag = authorflag;
    }

    public Integer getChapters() {
        return this.chapters;
    }

    public void setChapters(Integer chapters) {
        this.chapters = chapters;
    }

    public Integer getSize() {
        return this.size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Boolean getFullflag() {
        return this.fullflag;
    }

    public void setFullflag(Boolean fullflag) {
        this.fullflag = fullflag;
    }

    public Integer getImgflag() {
        return this.imgflag;
    }

    public void setImgflag(Integer imgflag) {
        this.imgflag = imgflag;
    }

    public Date getPostdate() {
        return this.postdate;
    }

    public void setPostdate(Date postdate) {
        this.postdate = postdate;
    }

    public Date getLastupdate() {
        return this.lastupdate;
    }

    public String getAgent() {
        return this.agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public void setLastupdate(Date lastupdate) {
        this.lastupdate = lastupdate;
    }

    public Integer getDayvisit() {
        return this.dayvisit;
    }

    public void setDayvisit(Integer dayvisit) {
        this.dayvisit = dayvisit;
    }

    public Integer getWeekvisit() {
        return this.weekvisit;
    }

    public void setWeekvisit(Integer weekvisit) {
        this.weekvisit = weekvisit;
    }

    public Integer getMonthvisit() {
        return this.monthvisit;
    }

    public void setMonthvisit(Integer monthvisit) {
        this.monthvisit = monthvisit;
    }

    public Integer getAllvisit() {
        return this.allvisit;
    }

    public void setAllvisit(Integer allvisit) {
        this.allvisit = allvisit;
    }

    public Integer getDayvote() {
        return this.dayvote;
    }

    public void setDayvote(Integer dayvote) {
        this.dayvote = dayvote;
    }

    public Integer getWeekvote() {
        return this.weekvote;
    }

    public void setWeekvote(Integer weekvote) {
        this.weekvote = weekvote;
    }

    public Integer getMonthvote() {
        return this.monthvote;
    }

    public void setMonthvote(Integer monthvote) {
        this.monthvote = monthvote;
    }

    public Integer getAllvote() {
        return this.allvote;
    }

    public void setAllvote(Integer allvote) {
        this.allvote = allvote;
    }

    public Boolean getDeleteflag() {
        return this.deleteflag;
    }

    public void setDeleteflag(Boolean deleteflag) {
        this.deleteflag = deleteflag;
    }

    public Integer getPublicflag() {
        return this.publicflag;
    }

    public void setPublicflag(Integer publicflag) {
        this.publicflag = publicflag;
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

    public String getIntroOmit() {
        return this.getIntro() != null && this.getIntro().length() > 60?this.getIntro().substring(0, 60) + "...":this.getIntro();
    }

    public Integer getSubdir() {
        return Integer.valueOf(this.articleno / 1000);
    }

    public String getLastchapterOmit() {
        return this.getLastchapter() != null && this.getLastchapter().length() > 10?this.getLastchapter().substring(0, 10):this.getLastchapter();
    }

    public String getLastupdateMin() {
        SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm:ss");
        return sdf1.format(this.lastupdate);
    }

    public String getCategoryStr() {
        String[] categoryArr = new String[]{"玄幻魔法", "武侠修真", "都市言情", "历史军事", "侦探推理", "网游动漫", "科幻小说", "恐怖灵异", "散文诗词", "其他类型"};
        return categoryArr[this.category.intValue() - 1];
    }

    public String getImgUrl() {
        String fileName = "";
        if(this.imgflag == null) {
            fileName = "nocover.jpg";
        } else {
            switch(this.imgflag.intValue()) {
                case 1:
                    fileName = this.articleno + "s.jpg";
                    break;
                case 2:
                    fileName = this.articleno + "s.gif";
                    break;
                case 3:
                    fileName = this.articleno + "s.png";
                    break;
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                default:
                    fileName = "nocover.jpg";
                    break;
                case 10:
                    fileName = this.articleno + "l.jpg";
            }
        }

        String imgUrl = YiDuConstants.yiduConf.getString("relativeIamgePath") + "/";
        if(StringUtils.equals("nocover.jpg", fileName)) {
            imgUrl = imgUrl + fileName;
        } else {
            imgUrl = imgUrl + this.articleno / 1000 + "/" + this.articleno + "/" + fileName;
        }

        return imgUrl;
    }

    @Override
    public String toString() {
        return "TArticle{" +
                "articleno=" + articleno +
                ", articlename='" + articlename + '\'' +
                ", initial='" + initial + '\'' +
                ", keywords='" + keywords + '\'' +
                ", authorid=" + authorid +
                ", author='" + author + '\'' +
                ", category=" + category +
                ", subcategory=" + subcategory +
                ", intro='" + intro + '\'' +
                ", lastchapterno=" + lastchapterno +
                ", lastchapter='" + lastchapter + '\'' +
                ", chapters=" + chapters +
                ", size=" + size +
                ", fullflag=" + fullflag +
                ", imgflag=" + imgflag +
                ", postdate=" + postdate +
                ", lastupdate=" + lastupdate +
                ", firstflag=" + firstflag +
                ", permission=" + permission +
                ", authorflag=" + authorflag +
                ", agent='" + agent + '\'' +
                ", dayvisit=" + dayvisit +
                ", weekvisit=" + weekvisit +
                ", monthvisit=" + monthvisit +
                ", allvisit=" + allvisit +
                ", dayvote=" + dayvote +
                ", weekvote=" + weekvote +
                ", monthvote=" + monthvote +
                ", allvote=" + allvote +
                ", deleteflag=" + deleteflag +
                ", publicflag=" + publicflag +
                ", modifyuserno=" + modifyuserno +
                ", modifytime=" + modifytime +
                '}';
    }
}
