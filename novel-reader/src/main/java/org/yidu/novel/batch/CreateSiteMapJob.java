//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yidu.novel.batch;

import java.io.FileWriter;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.utils.Utils;

public class CreateSiteMapJob extends QuartzJobBean {
    private Log logger = LogFactory.getLog(this.getClass());

    public CreateSiteMapJob() {
    }

    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        this.logger.debug("CreateSiteMapJob start.");
        boolean createSiteMapFlag = YiDuConstants.yiduConf.getBoolean("createSiteMap", false);
        if(createSiteMapFlag) {
            String uri = YiDuConstants.yiduConf.getString("uri");
            String responseBody = Utils.getContentFromUri(uri + "/siteMap");

            try {
                String e = CreateSiteMapJob.class.getClassLoader().getResource("").getPath();
                logger.debug("e::"+e);
                String webRootPath = e.substring(0, e.length() - 16);
                logger.debug("webRootPath::"+webRootPath);
                if(StringUtils.isNotBlank(responseBody)) {
                    FileWriter writer = new FileWriter(webRootPath + "/sitemap/index.html");
                    writer.write(responseBody);
                    writer.flush();
                    writer.close();
                }

                this.logger.debug("CreateSiteMapJob normally end.");
            } catch (Exception var8) {
                this.logger.error(var8);
                this.logger.debug("CreateSiteMapJob abnormally end.");
            }
        }

    }
}
