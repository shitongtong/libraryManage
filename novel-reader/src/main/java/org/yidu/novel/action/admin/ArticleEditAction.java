//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yidu.novel.action.admin;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.BeanUtils;
import org.yidu.novel.action.base.AbstractAdminEditBaseAction;
import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.entity.TArticle;
import org.yidu.novel.utils.LoginManager;
import org.yidu.novel.utils.Utils;

@Action("articleEdit")
@Result(
        name = "redirect",
        type = "redirect",
        location = "/admin/articleList"
)
public class ArticleEditAction extends AbstractAdminEditBaseAction {
    private static final long serialVersionUID = 822196809678036074L;
    private int articleno;
    private String articlename;
    private String keywords;
    private Integer authorid;
    private String author;
    private Integer category;
    private String intro;
    private Boolean fullflag;
    private Date postdate;
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
    private File articlespic;
    private String articlespicContentType;
    private String articlespicFileName;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");

    public ArticleEditAction() {
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

    public String getIntro() {
        return this.intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public Boolean getFullflag() {
        return this.fullflag;
    }

    public void setFullflag(Boolean fullflag) {
        this.fullflag = fullflag;
    }

    public Date getPostdate() {
        return this.postdate;
    }

    public void setPostdate(Date postdate) {
        this.postdate = postdate;
    }

    public String getPostdateStr() {
        return this.sdf.format(this.postdate);
    }

    public Boolean getFirstflag() {
        return this.firstflag;
    }

    public void setFirstflag(Boolean firstflag) {
        this.firstflag = firstflag;
    }

    public Integer getPermission() {
        return this.permission;
    }

    public void setPermission(Integer permission) {
        this.permission = permission;
    }

    public Boolean getAuthorflag() {
        return this.authorflag;
    }

    public void setAuthorflag(Boolean authorflag) {
        this.authorflag = authorflag;
    }

    public String getAgent() {
        return this.agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
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

    public void setPostdateStr(String postdateStr) {
        try {
            this.postdate = this.sdf.parse(postdateStr);
        } catch (ParseException var3) {
            this.addFieldError(postdateStr, this.getText("errors.format.date"));
        }

    }

    public File getArticlespic() {
        return this.articlespic;
    }

    public void setArticlespic(File articlespic) {
        this.articlespic = articlespic;
    }

    public String getArticlespicContentType() {
        return this.articlespicContentType;
    }

    public void setArticlespicContentType(String articlespicContentType) {
        this.articlespicContentType = articlespicContentType;
    }

    public String getArticlespicFileName() {
        return this.articlespicFileName;
    }

    public void setArticlespicFileName(String articlespicFileName) {
        this.articlespicFileName = articlespicFileName;
    }

    protected void loadData() {
        this.logger.debug("loadData start.");
        this.initCollections(new String[]{"collectionProperties.article.category", "collectionProperties.article.fullflag", "collectionProperties.article.authorflag", "collectionProperties.article.permission", "collectionProperties.article.firstflag"});
        if(this.articleno != 0) {
            TArticle article = this.articleService.getByNo(this.articleno);
            BeanUtils.copyProperties(article, this);
        }

        this.logger.debug("loadData normally end.");
    }

    public String save() {
        this.logger.debug("save start.");
        this.initCollections(new String[]{"collectionProperties.article.category", "collectionProperties.article.fullflag", "collectionProperties.article.authorflag", "collectionProperties.article.permission", "collectionProperties.article.firstflag"});
        TArticle article = new TArticle();
        if(this.articleno != 0) {
            article = this.articleService.getByNo(this.articleno);
        } else {
            article.setDeleteflag(Boolean.valueOf(false));
        }

        BeanUtils.copyProperties(this, article);
        article.setModifytime(new Date());
        article.setModifyuserno(Integer.valueOf(LoginManager.getLoginUser().getUserno()));
        this.articleService.save(article);
        if(this.articlespic != null) {
            if(!ArrayUtils.contains(YiDuConstants.allowPicTypes, this.getArticlespicContentType())) {
                this.addActionError(this.getText("errors.file.type"));
                return "input";
            }

            try {
                Utils.saveArticlespic(article.getArticleno(), this.articlespic, this.articlespicFileName);
            } catch (Exception var3) {
                this.addActionError(this.getText("errors.file.save"));
                return "input";
            }

            if(StringUtils.equals(this.getArticlespicContentType(), "image/jpeg")) {
                article.setImgflag(Integer.valueOf(1));
            } else if(StringUtils.equals(this.getArticlespicContentType(), "image/gif")) {
                article.setImgflag(Integer.valueOf(2));
            } else if(StringUtils.equals(this.getArticlespicContentType(), "image/png")) {
                article.setImgflag(Integer.valueOf(3));
            }
        }

        this.logger.debug("save normally end.");
        return "redirect";
    }
}
