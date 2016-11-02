//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yidu.novel.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import javax.servlet.http.Cookie;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;
import org.yidu.novel.action.base.AbstractPublicBaseAction;
import org.yidu.novel.cache.CacheManager;
import org.yidu.novel.dto.ChapterDTO;
import org.yidu.novel.entity.TChapter;
import org.yidu.novel.utils.CookieUtils;
import org.yidu.novel.utils.Utils;

public class ReaderAction extends AbstractPublicBaseAction {
    private static final long serialVersionUID = 1L;
    private int articleno;
    private int chapterno;
    private ChapterDTO chapter;

    public ReaderAction() {
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

    public ChapterDTO getChapter() {
        return this.chapter;
    }

    public void setChapter(ChapterDTO chapter) {
        this.chapter = chapter;
    }

    public String getTempName() {
        return "reader";
    }

    protected void loadData() {
        this.logger.debug("loadData start.");
        ChapterDTO chapterDto = (ChapterDTO)CacheManager.getObject("CacheKey_CHAPTER" + this.chapterno);
        if(chapterDto == null || chapterDto.getChapterno() == 0) {
            TChapter articlenos = this.chapterService.getByNo(this.chapterno);
            if(articlenos != null) {
                TChapter cookie = this.chapterService.getNextChapter(articlenos.getArticleno().intValue(), this.chapterno, true);
                TChapter chapterList = this.chapterService.getNextChapter(articlenos.getArticleno().intValue(), this.chapterno, false);
                chapterDto = new ChapterDTO();
                BeanUtils.copyProperties(articlenos, chapterDto);
                if(cookie != null) {
                    chapterDto.setNextChapterno(cookie.getChapterno());
                }

                if(chapterList != null) {
                    chapterDto.setPreChapterno(chapterList.getChapterno());
                }

                CacheManager.putObject("CacheKey_CHAPTER" + this.chapterno, chapterDto);
            } else {
                this.addActionError(this.getText("errors.not.exsits.chapter"));
            }
        }

        this.chapter = chapterDto;
        if(this.chapter != null && this.chapter.getChapterno() != 0) {
            this.chapter.setContent(Utils.getContext(this.chapter, true));
        }

        if(this.articleno != 0) {
            this.articleService.updateVisitStatistic(this.articleno);
        }

        String var9 = CookieUtils.getHistoryCookie(ServletActionContext.getRequest());
        if(StringUtils.isNotEmpty(var9)) {
            String[] var10 = StringUtils.split(var9, ",");
            List var12 = Arrays.asList(var10);
            boolean existflag = false;
            int index = 0;

            for(int tempList = 0; tempList < var12.size(); ++tempList) {
                if(StringUtils.startsWith((String)var12.get(tempList), this.articleno + "|")) {
                    existflag = true;
                    index = tempList;
                    break;
                }
            }

            Object var13 = new ArrayList();
            ((List)var13).add(this.articleno + "|" + this.chapterno);
            if(existflag) {
                for(int i = 0; i < var12.size(); ++i) {
                    if(i != index) {
                        ((List)var13).add((String)var12.get(i));
                    }
                }
            } else {
                ((List)var13).addAll(var12);
            }

            if(((List)var13).size() > 10) {
                var13 = ((List)var13).subList(0, 9);
            }

            var9 = StringUtils.join((Collection)var13, ",");
        } else {
            var9 = this.articleno + "|" + this.chapterno;
        }

        Cookie var11 = CookieUtils.addHistoryCookie(var9);
        ServletActionContext.getResponse().addCookie(var11);
        this.logger.debug("loadData normally end.");
    }

    public int getPageType() {
        return 4;
    }
}
