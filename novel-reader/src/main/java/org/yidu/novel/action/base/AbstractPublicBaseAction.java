//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yidu.novel.action.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.beans.BeanUtils;
import org.yidu.novel.action.IndexAction;
import org.yidu.novel.action.base.AbstractPublicAndUserBaseAction;
//import org.yidu.novel.bean.ArticleSearchBean;
//import org.yidu.novel.bean.SystemBlockSearchBean;
import org.yidu.novel.bean.ArticleSearchBean;
import org.yidu.novel.bean.SystemBlockSearchBean;
import org.yidu.novel.cache.CacheManager;
import org.yidu.novel.entity.TArticle;
import org.yidu.novel.entity.TSystemBlock;
import org.yidu.novel.utils.Pagination;
//import org.yidu.novel.dto.ArticleDTO;
//import org.yidu.novel.entity.TArticle;
//import org.yidu.novel.entity.TSystemBlock;
//import org.yidu.novel.entity.TUser;
//import org.yidu.novel.utils.CookieUtils;
//import org.yidu.novel.utils.LoginManager;
//import org.yidu.novel.utils.Pagination;

public abstract class AbstractPublicBaseAction extends AbstractPublicAndUserBaseAction {
    private static final long serialVersionUID = 4900892616460135567L;
    public static final String NAMESPACE = "";
    protected static final String CACHE_KEY_INDEX_BLOCK = "CacheKey_indexBlock";
    protected static final String CACHE_KEY_ARTICEL_LIST_COUNT_PREFIX = "CacheKey_ARTICLE_LIST_COUNT";
    protected static final String CACHE_KEY_ARTICEL_LIST_PREFIX = "CacheKey_ARTICLE_LIST";
    protected static final String CACHE_KEY_ARTICEL_PREFIX = "CacheKey_ARTICLE";
    protected static final String CACHE_KEY_CHAPTER_LIST_PREFIX = "CacheKey_CHAPTER_LIST";
    protected static final String CACHE_KEY_CHAPTER_PREFIX = "CacheKey_CHAPTER";
    protected static final String CACHE_KEY_HISTORY_PREFIX = "CacheKey_HISTORY";
    private Map<String, Object> blocks = new HashMap();

    public AbstractPublicBaseAction() {
        logger.debug("entry constructor method: AbstractPublicBaseAction()");
    }

    public Map<String, Object> getBlocks() {
        logger.debug("entry method: getBlocks() || this.blocks::"+this.blocks);
        return this.blocks;
    }

    public void setBlocks(Map<String, Object> blocks) {
        logger.debug("entry method: setBlocks(Map<String, Object> blocks) || blocks::"+blocks);
        this.blocks = blocks;
    }

    @SkipValidation
    public String execute() {
        this.logger.debug("execute start.");
        this.loadBlock();
//        if(!LoginManager.isLoginFlag()) {
//            CookieUtils.getUserCookieAndLogoin(ServletActionContext.getRequest(), this.userService);
//        }

        this.loadData();
        if(this.hasErrors()) {
            this.logger.debug("execute abnormally end.");
            this.setHasError(true);
            return "freemarker_error";
        } else {
            this.logger.debug("execute normally end.");
            return "freemarker";
        }
    }

    protected void loadBlock() {
        this.logger.debug("loadBlock start.");
        if(this instanceof IndexAction) {
            this.logger.debug("this instanceof IndexAction.");
            this.blocks = (Map)CacheManager.getObject("CacheKey_indexBlock");
            if(this.blocks == null || this.blocks.size() == 0) {
                this.blocks = new HashMap();
                /*
                数据测试用
                List<TArticle> articleList = new ArrayList<>();
                TArticle article = new TArticle(1);
                articleList.add(article);
                this.blocks.put("index_yanqing_tuijian", articleList);
                */

                new ArrayList();
                SystemBlockSearchBean searchBean = new SystemBlockSearchBean();
                List blockList = this.systemBlockService.find(searchBean);
                Iterator var4 = blockList.iterator();

                while(var4.hasNext()) {
                    TSystemBlock tSystemBlock = (TSystemBlock)var4.next();
                    if(tSystemBlock.getTarget().shortValue() == 6) {
                        ArticleSearchBean articleSearchBean;
                        if(tSystemBlock.getType().shortValue() == 10) {
                            articleSearchBean = new ArticleSearchBean();
                            Pagination articleList = new Pagination(tSystemBlock.getLimitnum().intValue(), 1);
                            articleList.setSortColumn(tSystemBlock.getSortcol());
                            articleList.setSortOrder("true".equals(tSystemBlock.getIsasc())?"ASC":"DESC");
                            articleSearchBean.setPagination(articleList);
                            List articleList1 = this.articleService.find(articleSearchBean);
                            this.blocks.put(tSystemBlock.getBlockid(), articleList1);
                        } else if(tSystemBlock.getType().shortValue() == 20) {
                            articleSearchBean = new ArticleSearchBean();
                            articleSearchBean.setArticlenos(tSystemBlock.getContent());
                            List articleList2 = this.articleService.find(articleSearchBean);
                            this.blocks.put(tSystemBlock.getBlockid(), articleList2);
                        } else if(tSystemBlock.getType().shortValue() == 30) {
                            this.blocks.put(tSystemBlock.getBlockid(), tSystemBlock.getContent());
                        }

                        CacheManager.putObject("CacheKey_indexBlock", this.blocks);
                    }
                }
            }
        }

        this.logger.debug("loadBlock normally end.");
    }

//    protected ArticleDTO getArticleInfoByNo(int articleno) {
//        ArticleDTO article = (ArticleDTO)CacheManager.getObject("CacheKey_ARTICLE" + articleno);
//        if(article == null || article.getArticleno() == 0) {
//            article = new ArticleDTO();
//            TArticle tarticle = this.articleService.getByNo(articleno);
//            if(tarticle == null || tarticle.getArticleno() == 0) {
//                this.addActionError(this.getText("errors.not.exsits.article"));
//                return null;
//            }
//
//            BeanUtils.copyProperties(tarticle, article);
//            CacheManager.putObject("CacheKey_ARTICLE" + articleno, article);
//        }
//
//        return article;
//    }

    public abstract int getPageType();

//    public boolean getLoginFlag() {
//        return LoginManager.isLoginFlag();
//    }
//
//    public TUser getLoginUser() {
//        return LoginManager.getLoginUser();
//    }
}
