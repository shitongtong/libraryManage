//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yidu.novel.batch;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.yidu.novel.service.ArticleService;

public class CleanStatisticsDataJob extends QuartzJobBean {
    private Log logger = LogFactory.getLog(this.getClass());
    private ArticleService articleService;

    public CleanStatisticsDataJob() {
    }

    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }

    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        this.logger.debug("CleanStatisticsDataJob start.");

        try {
            this.articleService.cleanStatistics();
        } catch (Exception var3) {
            var3.printStackTrace();
        }

        this.logger.debug("CleanStatisticsDataJob normally end.");
    }
}
