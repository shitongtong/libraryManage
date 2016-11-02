//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yidu.novel.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.Cookie;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.BeanUtils;
import org.yidu.novel.action.base.AbstractPublicBaseAction;
import org.yidu.novel.bean.ArticleSearchBean;
import org.yidu.novel.bean.BookcaseSearchBean;
import org.yidu.novel.bean.ChapterSearchBean;
import org.yidu.novel.bean.UserSearchBean;
import org.yidu.novel.cache.CacheManager;
import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.dto.JsonInfoDTO;
import org.yidu.novel.entity.TArticle;
import org.yidu.novel.entity.TBookcase;
import org.yidu.novel.entity.TUser;
import org.yidu.novel.utils.CookieUtils;
import org.yidu.novel.utils.LoginManager;
import org.yidu.novel.utils.Pagination;
import org.yidu.novel.utils.Utils;

@Action("ajaxService")
public class AjaxServiceAction extends AbstractPublicBaseAction {
    private static final long serialVersionUID = -5966399886620363535L;
    private String action;
    private String loginid;
    private String password;
    private int category;
    private int articleno;
    private int index;
    private int sort;
    private int size;
    private int type;
    private int userno;
    private String bookcasenos;
    private String key;
    private int length;
    private int status;
    private JsonInfoDTO dto = new JsonInfoDTO();

    public AjaxServiceAction() {
    }

    public String getAction() {
        return this.action;
    }

    public void setAction(String action) {
        this.action = action;
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

    public int getCategory() {
        return this.category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getArticleno() {
        return this.articleno;
    }

    public void setArticleno(int articleno) {
        this.articleno = articleno;
    }

    public int getIndex() {
        return this.index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getSort() {
        return this.sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getSize() {
        return this.size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getUserno() {
        return this.userno;
    }

    public void setUserno(int userno) {
        this.userno = userno;
    }

    public String getBookcasenos() {
        return this.bookcasenos;
    }

    public void setBookcasenos(String bookcasenos) {
        this.bookcasenos = bookcasenos;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getLength() {
        return this.length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public JsonInfoDTO getData() {
        return this.dto;
    }

    public String execute() {
        this.logger.debug("execute started. ");
        if(StringUtils.equals(this.action, "categorylist")) {
            this.getCategoryList();
        } else if(StringUtils.equals(this.action, "changepassword")) {
            this.dochangepass();
        } else if(StringUtils.equals(this.action, "chapterlist")) {
            this.getChapterList();
        } else if(StringUtils.equals(this.action, "login")) {
            this.dologin();
        } else if(StringUtils.equals(this.action, "toplist")) {
            this.getTopList();
        } else if(StringUtils.equals(this.action, "bookcase")) {
            this.getBookcase();
        } else if(StringUtils.equals(this.action, "deletebookcase")) {
            this.deleteBookcase();
        } else if(StringUtils.equals(this.action, "history")) {
            this.getHistory();
        } else if(StringUtils.equals(this.action, "bookcaseisexists")) {
            this.checkBookCaseExists();
        } else if(StringUtils.equals(this.action, "addbookcase")) {
            this.addBookcase();
        } else if(StringUtils.equals(this.action, "deletebookcasebyarticle")) {
            this.deleteBookcaseByArticle();
        } else if(StringUtils.equals(this.action, "search")) {
            this.doSearch();
        } else if(StringUtils.equals(this.action, "register")) {
            this.register();
        } else {
            this.dto.setCode(1);
            this.dto.setErr(this.getText("errors.unknown"));
        }

        this.logger.debug("execute normally end. ");
        return "json";
    }

    private void register() {
        this.logger.info("regedit started.");
        if(StringUtils.trim(this.loginid).length() >= 5 && StringUtils.trim(this.loginid).length() <= 32) {
            if(StringUtils.trim(this.password).length() >= 5 && StringUtils.trim(this.password).length() <= 32) {
                UserSearchBean searchBean = new UserSearchBean();
                searchBean.setLoginid(this.loginid);
                searchBean.setDeleteflag(Boolean.valueOf(false));
                List userList = this.userService.find(searchBean);
                if(userList != null && userList.size() > 0) {
                    this.dto.setCode(1);
                    this.dto.setErr(this.getText("errors.duplicated", new String[]{this.getText("label.user.loginid")}));
                } else {
                    TUser user = new TUser();
                    user.setLoginid(this.loginid);
                    user.setDeleteflag(Boolean.valueOf(false));
                    user.setRegdate(new Date());
                    user.setLastlogin(new Date());
                    user.setPassword(Utils.convert2MD5(this.password));
                    user.setType(Short.valueOf((short)10));
                    this.userService.save(user);
                    LoginManager.doLogin(user);
                    this.dto.setCode(0);
                    this.dto.setResult("注册成功！");
                    this.logger.debug("regedit normally end.");
                }
            } else {
                this.dto.setCode(1);
                this.dto.setErr(this.getText("errors.lengthrange", new String[]{"5", "32", this.getText("label.user.password")}));
            }
        } else {
            this.dto.setCode(1);
            this.dto.setErr(this.getText("errors.lengthrange", new String[]{"5", "32", this.getText("label.user.loginid")}));
        }
    }

    private void doSearch() {
        this.logger.debug("doSearch start.");
        ArticleSearchBean searchBean = new ArticleSearchBean();
        BeanUtils.copyProperties(this, searchBean);
        if(this.status == 1) {
            searchBean.setFullflag(Boolean.valueOf(true));
        }

        int count = this.articleService.getCount(searchBean).intValue();
        this.dto.setTotal(count);
        int pages = count / this.size;
        if(count % this.size > 0) {
            ++pages;
        }

        this.dto.setPages(pages);
        this.dto.setSize(this.size);
        this.dto.setIndex(this.index);
        Pagination pagination = new Pagination(this.size, this.index + 1);
        switch(this.sort) {
            case 1:
                pagination.setSortColumn("lastupdate");
                break;
            case 2:
                pagination.setSortColumn("allvisit");
                break;
            case 3:
                pagination.setSortColumn("allvote");
                break;
            case 4:
                pagination.setSortColumn("size");
                break;
            default:
                pagination.setSortColumn("allvisit");
        }

        pagination.setSortOrder("DESC");
        searchBean.setPagination(pagination);
        List articleList = (List)CacheManager.getObject("CacheKey_ARTICLE_LIST" + searchBean.toString());
        if(articleList == null || articleList.size() == 0) {
            articleList = this.articleService.find(searchBean);
            CacheManager.putObject("CacheKey_ARTICLE_LIST" + searchBean.toString(), articleList);
        }

        this.dto.setItems(articleList);
        this.logger.debug("doSearch normally end.");
    }

    private void deleteBookcaseByArticle() {
        this.logger.debug("deleteBookcaseByArticle start.");
        if(this.articleno == 0) {
            this.dto.setCode(1);
        } else {
            this.bookcaseService.getByArticleno(LoginManager.getLoginUser().getUserno(), this.articleno);
            this.dto.setCode(0);
            this.logger.debug("deleteBookcaseByArticle normally end.");
        }
    }

    private void addBookcase() {
        this.logger.debug("addBookcase start.");
        if(this.articleno == 0) {
            this.dto.setCode(1);
        } else {
            BookcaseSearchBean searchBean = new BookcaseSearchBean();
            searchBean.setUserno(Integer.valueOf(LoginManager.getLoginUser().getUserno()));
            int bookcaseCount = this.bookcaseService.getCount(searchBean).intValue();
            if(bookcaseCount > YiDuConstants.yiduConf.getInt("maxBookcase")) {
                this.dto.setCode(1);
            } else {
                TBookcase bookcase = this.bookcaseService.getByArticleno(LoginManager.getLoginUser().getUserno(), this.articleno);
                if(bookcase == null) {
                    bookcase = new TBookcase();
                }

                TArticle article = this.articleService.getByNo(this.articleno);
                if(article != null && article.getArticleno() != 0) {
                    BeanUtils.copyProperties(article, bookcase);
                    bookcase.setCreatetime(new Date());
                    bookcase.setUserno(Integer.valueOf(LoginManager.getLoginUser().getUserno()));
                    this.bookcaseService.save(bookcase);
                    this.dto.setCode(0);
                    this.logger.debug("addBookcase normally end.");
                } else {
                    this.dto.setCode(1);
                }
            }
        }
    }

    private void checkBookCaseExists() {
        this.logger.debug("checkBookCaseExists start.");
        if(this.articleno == 0) {
            this.dto.setCode(1);
        } else {
            TBookcase bookcase = this.bookcaseService.getByArticleno(LoginManager.getLoginUser().getUserno(), this.articleno);
            if(bookcase != null) {
                this.dto.setResult("1");
            }

            this.dto.setCode(0);
            this.logger.debug("checkBookCaseExists normally end.");
        }
    }

    private void getHistory() {
        this.logger.debug("getHistory start.");
        String historys = CookieUtils.getHistoryCookie(ServletActionContext.getRequest());
        if(StringUtils.isNotEmpty(historys)) {
            String[] acnos = StringUtils.split(historys, ",");
            ArrayList articlenoList = new ArrayList();
            String[] var7 = acnos;
            int var6 = acnos.length;

            for(int var5 = 0; var5 < var6; ++var5) {
                String searchBean = var7[var5];
                String[] acnoArr = StringUtils.split(searchBean, "|");
                if(acnoArr.length > 0) {
                    articlenoList.add(acnoArr[0]);
                }
            }

            if(articlenoList.size() > 0) {
                ArticleSearchBean var9 = new ArticleSearchBean();
                var9.setArticlenos(StringUtils.join(articlenoList, ","));
                this.dto.setItems(this.articleService.find(var9));
            }

            this.dto.setCode(0);
        }

        this.logger.debug("getHistory normally end.");
    }

    private void deleteBookcase() {
        this.logger.debug("deleteBookcase start.");
        if(StringUtils.isEmpty(StringUtils.trim(this.bookcasenos))) {
            this.dto.setCode(1);
        } else {
            this.bookcaseService.batchdeleteByNo(this.bookcasenos, LoginManager.getLoginUser().getUserno());
            this.dto.setCode(0);
            this.logger.debug("deleteBookcase normally end.");
        }
    }

    private void getBookcase() {
        this.logger.debug("getBookcase start.");
        BookcaseSearchBean searchBean = new BookcaseSearchBean();
        searchBean.setUserno(Integer.valueOf(LoginManager.getLoginUser().getUserno()));
        Pagination pagination = new Pagination(this.size, this.index + 1);
        switch(this.sort) {
            case 1:
                pagination.setSortColumn("tb.createtime");
                pagination.setSortOrder("DESC");
                break;
            case 2:
                pagination.setSortColumn("ta.lastupdate");
                pagination.setSortOrder("DESC");
                break;
            case 3:
                pagination.setSortColumn("ta.articlename");
                pagination.setSortOrder("ASC");
                break;
            default:
                pagination.setSortColumn("lastupdate");
                pagination.setSortOrder("DESC");
        }

        searchBean.setPagination(pagination);
        this.dto.setItems(this.bookcaseService.findWithArticleInfo(searchBean));
        this.dto.setCode(0);
        this.logger.debug("getBookcase normally end.");
    }

    private void getTopList() {
        this.logger.debug("getTopList start.");
        ArticleSearchBean searchBean = new ArticleSearchBean();
        BeanUtils.copyProperties(this, searchBean);
        Object countInfo = CacheManager.getObject("CacheKey_ARTICLE_LIST_COUNT" + searchBean.toString());
        boolean count = false;
        int var7;
        if(countInfo == null) {
            var7 = this.articleService.getCount(searchBean).intValue();
            CacheManager.putObject("CacheKey_ARTICLE_LIST_COUNT" + searchBean.toString(), Integer.valueOf(var7));
        } else {
            var7 = Integer.parseInt(countInfo.toString());
        }

        this.dto.setTotal(var7);
        int pages = var7 / this.size;
        if(var7 % this.size > 0) {
            ++pages;
        }

        this.dto.setPages(pages);
        this.dto.setSize(this.size);
        this.dto.setIndex(this.index);
        Pagination pagination = new Pagination(this.size, this.index + 1);
        switch(this.type) {
            case 1:
                pagination.setSortColumn("lastupdate");
                break;
            case 2:
                pagination.setSortColumn("allvisit");
                break;
            case 3:
                pagination.setSortColumn("allvote");
                break;
            case 4:
                pagination.setSortColumn("size");
                break;
            default:
                pagination.setSortColumn("lastupdate");
        }

        pagination.setSortOrder("DESC");
        searchBean.setPagination(pagination);
        List articleList = (List)CacheManager.getObject("CacheKey_ARTICLE_LIST" + searchBean.toString());
        if(articleList == null || articleList.size() == 0) {
            articleList = this.articleService.find(searchBean);
            CacheManager.putObject("CacheKey_ARTICLE_LIST" + searchBean.toString(), articleList);
        }

        this.dto.setItems(articleList);
        this.logger.debug("getTopList normally end.");
    }

    private void getCategoryList() {
        this.logger.debug("getCategoryList start.");
        ArticleSearchBean searchBean = new ArticleSearchBean();
        BeanUtils.copyProperties(this, searchBean);
        Object countInfo = CacheManager.getObject("CacheKey_ARTICLE_LIST_COUNT" + searchBean.toString());
        boolean count = false;
        int var7;
        if(countInfo == null) {
            var7 = this.articleService.getCount(searchBean).intValue();
            CacheManager.putObject("CacheKey_ARTICLE_LIST_COUNT" + searchBean.toString(), Integer.valueOf(var7));
        } else {
            var7 = Integer.parseInt(countInfo.toString());
        }

        this.dto.setTotal(var7);
        int pages = var7 / this.size;
        if(var7 % this.size > 0) {
            ++pages;
        }

        this.dto.setPages(pages);
        this.dto.setSize(this.size);
        this.dto.setIndex(this.index);
        Pagination pagination = new Pagination(this.size, this.index + 1);
        pagination.setSortColumn("lastupdate");
        pagination.setSortOrder("DESC");
        searchBean.setPagination(pagination);
        List articleList = (List)CacheManager.getObject("CacheKey_ARTICLE_LIST" + searchBean.toString());
        if(articleList == null || articleList.size() == 0) {
            articleList = this.articleService.find(searchBean);
            CacheManager.putObject("CacheKey_ARTICLE_LIST" + searchBean.toString(), articleList);
        }

        this.dto.setItems(articleList);
        this.logger.debug("getCategoryList normally end.");
    }

    private void getChapterList() {
        this.logger.debug("getChapterList start.");
        ChapterSearchBean searchBean = new ChapterSearchBean();
        BeanUtils.copyProperties(this, searchBean);
        int count = this.chapterService.getCount(searchBean).intValue();
        this.dto.setTotal(count);
        int pages = count / this.size;
        if(count % this.size > 0) {
            ++pages;
        }

        this.dto.setPages(pages);
        Pagination pagination = new Pagination(this.size, this.index + 1);
        pagination.setSortColumn("chapterno");
        if(this.sort == 0) {
            pagination.setSortOrder("DESC");
        } else {
            pagination.setSortOrder("ASC");
        }

        searchBean.setPagination(pagination);
        List chapterList = (List)CacheManager.getObject("CacheKey_CHAPTER_LIST" + searchBean.toString());
        if(chapterList == null || chapterList.size() == 0) {
            chapterList = this.chapterService.find(searchBean);
            if(chapterList != null && chapterList.size() != 0) {
                CacheManager.putObject("CacheKey_CHAPTER_LIST" + searchBean.toString(), chapterList);
            }
        }

        this.dto.setItems(chapterList);
        if(this.articleno != 0) {
            this.articleService.updateVisitStatistic(this.articleno);
        }

        this.logger.debug("getChapterList normally end.");
    }

    private void dochangepass() {
        TUser user = this.userService.getByNo(this.userno);
        if(StringUtils.equals(user.getPassword(), Utils.convert2MD5(this.password))) {
            this.dto.setCode(1);
        } else {
            if(StringUtils.isNotEmpty(this.password)) {
                user.setPassword(Utils.convert2MD5(this.password));
            }

            this.userService.save(user);
            this.dto.setCode(0);
        }
    }

    private void dologin() {
        this.logger.info("dologin start.");
        TUser user = this.userService.findByLoginInfo(this.loginid, Utils.convert2MD5(this.password));
        if(user != null && user.getDeleteflag() != null && !user.getDeleteflag().booleanValue()) {
            LoginManager.doLogin(user);
            user.setLastlogin(new Date());
            this.userService.save(user);
            Cookie cookie = CookieUtils.addUserCookie(user);
            ServletActionContext.getResponse().addCookie(cookie);
            this.dto.setCode(0);
            this.logger.debug("dologin normally end.");
        } else {
            this.dto.setCode(1);
            this.dto.setErr(this.getText("errors.login.failed"));
            this.logger.debug("dologin abnormally end.");
        }

    }

    public int getPageType() {
        return 99;
    }

    protected void loadData() {
    }

    class ActionType {
        public static final String TOP_LIST = "toplist";
        public static final String CATEGORY_LIST = "categorylist";
        public static final String CHAPTER_LIST = "chapterlist";
        public static final String LOGIN = "login";
        public static final String CHANGE_PASSWORD = "changepassword";
        public static final String BOOKCASEIS_EXISTS = "bookcaseisexists";
        public static final String BOOKCASE = "bookcase";
        public static final String DELETE_BOOKCASE = "deletebookcase";
        public static final String ADD_BOOKCASE = "addbookcase";
        public static final String DELETE_BOOKCASE_BY_ARTICLE = "deletebookcasebyarticle";
        public static final String HISTORY = "history";
        public static final String SEARCH = "search";
        public static final String REGISTER = "register";

        ActionType() {
        }
    }
}
