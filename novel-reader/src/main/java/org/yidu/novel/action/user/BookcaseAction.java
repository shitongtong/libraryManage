//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yidu.novel.action.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;
import org.yidu.novel.action.base.AbstractUserBaseAction;
import org.yidu.novel.bean.BookcaseSearchBean;
import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.dto.BookcaseDTO;
import org.yidu.novel.entity.TArticle;
import org.yidu.novel.entity.TBookcase;
import org.yidu.novel.entity.TChapter;
import org.yidu.novel.utils.LoginManager;

public class BookcaseAction extends AbstractUserBaseAction {
    private static final long serialVersionUID = 366181195078436796L;
    private int bookcaseno;
    private int articleno;
    private int chapterno;
    private List<BookcaseDTO> bookcaseList = new ArrayList();

    public BookcaseAction() {
    }

    public int getBookcaseno() {
        return this.bookcaseno;
    }

    public void setBookcaseno(int bookcaseno) {
        this.bookcaseno = bookcaseno;
    }

    public int getArticleno() {
        return this.articleno;
    }

    public void setArticleno(int articleno) {
        this.articleno = articleno;
    }

    public int getChapterno() {
        return this.chapterno;
    }

    public void setChapterno(int chapterno) {
        this.chapterno = chapterno;
    }

    public List<BookcaseDTO> getBookcaseList() {
        return this.bookcaseList;
    }

    public void setBookcaseList(List<BookcaseDTO> bookcaseList) {
        this.bookcaseList = bookcaseList;
    }

    public int getMaxBookcaseNum() {
        return YiDuConstants.yiduConf.getInt("maxBookcase");
    }

    public int getBookcaseNum() {
        return this.bookcaseList.size();
    }

    public String getTempName() {
        return "user/bookcase";
    }

    public int getPageType() {
        return 21;
    }

    protected void loadData() {
        BookcaseSearchBean searchBean = new BookcaseSearchBean();
        searchBean.setUserno(Integer.valueOf(LoginManager.getLoginUser().getUserno()));
        this.bookcaseList = this.bookcaseService.findWithArticleInfo(searchBean);
    }

    @Transactional
    public String add() {
        if(this.articleno == 0) {
            this.addActionError(this.getText("errors.not.exsits.article"));
            return "freemarker_error";
        } else {
            BookcaseSearchBean searchBean = new BookcaseSearchBean();
            searchBean.setUserno(Integer.valueOf(LoginManager.getLoginUser().getUserno()));
            int bookcaseCount = this.bookcaseService.getCount(searchBean).intValue();
            if(bookcaseCount > YiDuConstants.yiduConf.getInt("maxBookcase")) {
                this.addActionError(this.getText("errors.max.bookcase"));
                return "freemarker_error";
            } else {
                TBookcase bookcase = this.bookcaseService.getByArticleno(LoginManager.getLoginUser().getUserno(), this.articleno);
                if(bookcase == null) {
                    bookcase = new TBookcase();
                }

                if(this.chapterno != 0) {
                    TChapter article1 = this.chapterService.getByNo(this.chapterno);
                    if(article1 == null || article1.getChapterno() == 0) {
                        this.addActionError(this.getText("errors.not.exsits.chapter"));
                        return "freemarker_error";
                    }

                    BeanUtils.copyProperties(article1, bookcase);
                } else if(this.articleno != 0) {
                    TArticle article = this.articleService.getByNo(this.articleno);
                    if(article == null || article.getArticleno() == 0) {
                        this.addActionError(this.getText("errors.not.exsits.article"));
                        return "freemarker_error";
                    }

                    BeanUtils.copyProperties(article, bookcase);
                }

                bookcase.setCreatetime(new Date());
                bookcase.setUserno(Integer.valueOf(LoginManager.getLoginUser().getUserno()));
                this.bookcaseService.save(bookcase);
                this.loadData();
                return "freemarker_message";
            }
        }
    }

    @Transactional
    public String delete() {
        if(this.bookcaseno != 0) {
            TBookcase bookcase = this.bookcaseService.getByNo(this.bookcaseno);
            if(bookcase.getUserno().intValue() != LoginManager.getLoginUser().getUserno()) {
                this.addActionError(this.getText("errors.unauthority.bookcase"));
                return "freemarker_error";
            }

            this.bookcaseService.delteByNo(this.bookcaseno);
        }

        this.loadData();
        return "freemarker";
    }
}
