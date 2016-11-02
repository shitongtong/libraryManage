//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yidu.novel.batch;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.yidu.novel.batch.CreateSiteMapJob;
import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.utils.Utils;

public class CreateIndexJob extends QuartzJobBean {
    private Log logger = LogFactory.getLog(this.getClass());

    public CreateIndexJob() {
    }

    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        this.logger.debug("CreateIndexJob start.");
        boolean createindexFlag = YiDuConstants.yiduConf.getBoolean("createIndexPage", false);
        if(createindexFlag) {
            String uri = YiDuConstants.yiduConf.getString("uri");
            String responseBody = Utils.getContentFromUri(uri + "/index");

            try {
                String e = CreateSiteMapJob.class.getClassLoader().getResource("").getPath();
                String webRootPath = e.substring(0, e.length() - 16);
                if(StringUtils.isNotBlank(responseBody)) {
                    OutputStreamWriter outputStream = new OutputStreamWriter(new FileOutputStream(new File(webRootPath + "index.html")), "UTF-8");
                    outputStream.write(responseBody);
                    outputStream.flush();
                    outputStream.close();
                }

                this.logger.debug("CreateIndexJob normally end.");
            } catch (Exception var8) {
                this.logger.error(var8);
                this.logger.debug("CreateIndexJob abnormally end.");
            }
        }

    }
}
