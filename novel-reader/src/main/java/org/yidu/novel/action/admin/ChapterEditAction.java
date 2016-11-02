//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yidu.novel.action.admin;

import java.util.Date;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.BeanUtils;
import org.yidu.novel.action.base.AbstractAdminEditBaseAction;
import org.yidu.novel.entity.TArticle;
import org.yidu.novel.entity.TChapter;
import org.yidu.novel.utils.LoginManager;
import org.yidu.novel.utils.Utils;

@Action("chapterEdit")
@Result(
        name = "redirect",
        type = "redirect",
        location = "/admin/chapterList?articleno=${articleno}"
)
public class ChapterEditAction extends AbstractAdminEditBaseAction {
    private static final long serialVersionUID = -6064353669030314155L;
    private int chapterno;
    private Integer articleno;
    private String articlename;
    private String volumeid;
    private String chaptername;
    private String content;
    private Integer size;
    private Boolean isvip;
    private Date postdate;

    public ChapterEditAction() {
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

    public void setArticleno(String articleno) {
        this.articleno = Integer.valueOf(Integer.parseInt(articleno));
    }

    public String getArticlename() {
        return this.articlename;
    }

    public void setArticlename(String articlename) {
        this.articlename = articlename;
    }

    public String getVolumeid() {
        return this.volumeid;
    }

    public void setVolumeid(String volumeid) {
        this.volumeid = volumeid;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public int getVip() {
        return this.isvip.booleanValue()?2:1;
    }

    public void setVip(int vip) {
        this.isvip = Boolean.valueOf(vip == 2);
    }

    public Date getPostdate() {
        return this.postdate;
    }

    public void setPostdate(Date postdate) {
        this.postdate = postdate;
    }

    protected void loadData() {
        this.logger.debug("loadData start.");
        this.initCollections(new String[]{"collectionProperties.chapter.isvip"});
        if(this.chapterno == 0 && this.articleno.intValue() == 0) {
            this.addActionError(this.getText("errors.unknown"));
        } else {
            if(this.chapterno != 0) {
                TChapter article = this.chapterService.getByNo(this.chapterno);
                BeanUtils.copyProperties(article, this);
                this.content = Utils.getContext(article, false);
            } else {
                TArticle article1 = this.articleService.getByNo(this.articleno.intValue());
                BeanUtils.copyProperties(article1, this);
            }

            this.logger.debug("loadData normally end.");
        }
    }

    public String save() {
        this.logger.debug("save start.");
        this.initCollections(new String[]{"collectionProperties.chapter.isvip"});
        TChapter chapter = new TChapter();
        TArticle article = this.articleService.getByNo(this.articleno.intValue());
        if(this.chapterno != 0) {
            chapter = this.chapterService.getByNo(this.chapterno);
        } else {
            chapter.setArticleno(this.articleno);
            chapter.setArticlename(article.getArticlename());
            chapter.setChaptertype(Short.valueOf((short)0));
            chapter.setDeleteflag(Boolean.valueOf(false));
            chapter.setPostdate(new Date());
        }

        BeanUtils.copyProperties(this, chapter, new String[]{"articleno", "articlename"});
        chapter.setSize(Integer.valueOf(StringUtils.length(this.content)));
        chapter.setPostdate(new Date());
        chapter.setModifytime(new Date());
        chapter.setModifyuserno(Integer.valueOf(LoginManager.getLoginUser().getUserno()));
        this.chapterService.save(chapter);

        try {
            Utils.saveContext(this.articleno.intValue(), chapter.getChapterno(), this.content);
        } catch (Exception var4) {
            this.addActionError(var4.getMessage());
            return "input";
        }

        if(this.chapterno == 0) {
            article.setLastchapterno(Integer.valueOf(chapter.getChapterno()));
            article.setLastchapter(chapter.getChaptername());
            article.setLastupdate(new Date());
        }

        article.setSize(this.chapterService.getArticleSize(this.articleno.intValue()));
        article.setModifytime(new Date());
        article.setModifyuserno(Integer.valueOf(LoginManager.getLoginUser().getUserno()));
        this.articleService.save(article);
        this.logger.debug("save normally end.");
        return "redirect";
    }
}
