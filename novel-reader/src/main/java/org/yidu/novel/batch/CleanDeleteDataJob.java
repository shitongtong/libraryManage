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
import org.yidu.novel.service.CleanDeleteDataService;

public class CleanDeleteDataJob extends QuartzJobBean {
    private Log logger = LogFactory.getLog(this.getClass());
    private CleanDeleteDataService cleanDeleteDataService;

    public CleanDeleteDataJob() {
    }

    public void setCleanDeleteDataService(CleanDeleteDataService cleanDeleteDataService) {
        this.cleanDeleteDataService = cleanDeleteDataService;
    }

    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        this.logger.debug("CleanDeleteDataJob start.");

        try {
            this.cleanDeleteDataService.cleanDeleteData();
            this.logger.debug("CleanDeleteDataJob normally end.");
        } catch (Exception var3) {
            this.logger.error(var3);
            this.logger.debug("CleanDeleteDataJob abnormally end.");
        }

    }
}
