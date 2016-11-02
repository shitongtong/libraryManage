//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yidu.novel.action;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.yidu.novel.action.base.AbstractPublicBaseAction;
import org.yidu.novel.bean.ChapterSearchBean;
import org.yidu.novel.entity.TChapter;
import org.yidu.novel.utils.CookieUtils;

@Action("readhistory")
public class ReadHistoryAction extends AbstractPublicBaseAction {
    private static final long serialVersionUID = -1839809309350704624L;
    private List<TChapter> historyList = new ArrayList();

    public ReadHistoryAction() {
    }

    @SkipValidation
    public String execute() {
        this.logger.debug("execute start.");
        this.loadData();
        this.logger.debug("execute normally end.");
        return "json";
    }

    public List<TChapter> getData() {
        return this.historyList;
    }

    public int getPageType() {
        return 99;
    }

    protected void loadData() {
        this.logger.debug("loadData start.");
        String historys = CookieUtils.getHistoryCookie(ServletActionContext.getRequest());
        if(StringUtils.isNotEmpty(historys)) {
            String[] acnos = StringUtils.split(historys, ",");
            ArrayList chapternoList = new ArrayList();
            String[] var7 = acnos;
            int var6 = acnos.length;

            for(int var5 = 0; var5 < var6; ++var5) {
                String searchBean = var7[var5];
                String[] acnoArr = StringUtils.split(searchBean, "|");
                if(acnoArr.length == 2) {
                    chapternoList.add(acnoArr[1]);
                }
            }

            if(chapternoList.size() > 0) {
                ChapterSearchBean var9 = new ChapterSearchBean();
                var9.setChapternos(StringUtils.join(chapternoList, ","));
                this.historyList = this.chapterService.find(var9);
            }
        }

        this.logger.debug("loadData normally end.");
    }
}
