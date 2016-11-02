//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yidu.novel.action.user;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.BeanUtils;
import org.yidu.novel.action.base.AbstractUserBaseAction;
import org.yidu.novel.entity.TArticle;
import org.yidu.novel.entity.TChapter;
import org.yidu.novel.utils.LoginManager;
import org.yidu.novel.utils.Utils;

@Action("chapterEdit")
@Result(
        name = "redirect",
        type = "redirect",
        location = "/user/chapterList?articleno=${articleno}"
)
public class ChapterEditAction extends AbstractUserBaseAction {
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
    private Date publishtime;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");

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

    public Date getPublishtime() {
        return this.publishtime;
    }

    public void setPublishtime(Date publishtime) {
        this.publishtime = publishtime;
    }

    public String getPublishtimeStr() {
        return this.publishtime != null?this.sdf.format(this.publishtime):"";
    }

    public void setPublishtimeStr(String publishtimeStr) {
        if(StringUtils.isNotEmpty(publishtimeStr)) {
            try {
                this.publishtime = this.sdf.parse(publishtimeStr);
            } catch (ParseException var3) {
                this.addFieldError(publishtimeStr, this.getText("errors.format.date"));
            }
        }

    }

    protected void loadData() {
        this.logger.debug("loadData start.");
        this.initCollections(new String[]{"collectionProperties.chapter.isvip"});
        if(this.chapterno != 0) {
            TChapter chapter = this.chapterService.getByNo(this.chapterno);
            if(chapter != null) {
                TArticle article = this.articleService.getByNo(chapter.getArticleno().intValue());
                if(article == null) {
                    this.addActionError(this.getText("errors.not.exsits.article"));
                    return;
                }

                if(!this.checkRight(article)) {
                    this.addActionError(this.getText("errors.right"));
                    return;
                }

                BeanUtils.copyProperties(chapter, this);
                this.content = Utils.getContext(chapter, false);
            } else {
                this.addActionError(this.getText("errors.not.exsits.chapter"));
            }
        }

        this.logger.debug("loadData normally end.");
    }

    public String save() {
        this.logger.debug("save start.");
        this.initCollections(new String[]{"collectionProperties.chapter.isvip"});
        TChapter chapter = new TChapter();
        TArticle article = this.articleService.getByNo(this.articleno.intValue());
        if(article == null) {
            this.addActionError(this.getText("errors.not.exsits.article"));
            return "freemarker_error";
        } else {
            if(this.chapterno != 0) {
                chapter = this.chapterService.getByNo(this.chapterno);
            } else {
                chapter.setArticleno(this.articleno);
                chapter.setArticlename(article.getArticlename());
                chapter.setChaptertype(Short.valueOf((short)0));
                chapter.setDeleteflag(Boolean.valueOf(false));
                chapter.setPostdate(new Date());
            }

            if(!this.checkRight(article)) {
                this.addActionError(this.getText("errors.right"));
                return "freemarker_error";
            } else {
                BeanUtils.copyProperties(this, chapter, new String[]{"articleno", "articlename", "postdate"});
                chapter.setSize(Integer.valueOf(StringUtils.length(this.content)));
                chapter.setModifytime(new Date());
                chapter.setModifyuserno(Integer.valueOf(LoginManager.getLoginUser().getUserno()));
                this.chapterService.save(chapter);

                try {
                    Utils.saveContext(this.articleno.intValue(), this.chapterno, this.content);
                } catch (Exception var4) {
                    this.addActionError(var4.getMessage());
                    return "freemarker_error";
                }

                this.logger.debug("save normally end.");
                return "redirect";
            }
        }
    }

    public int getPageType() {
        return 33;
    }

    public String getTempName() {
        return "user/chapterEdit";
    }
}
