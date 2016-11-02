//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yidu.novel.action.user;

import java.io.File;
import org.apache.commons.lang.ArrayUtils;
import org.apache.struts2.convention.annotation.Action;
import org.yidu.novel.action.base.AbstractUserBaseAction;
import org.yidu.novel.constant.YiDuConstants;

@Action("regiauthor")
public class RegiAuthorAction extends AbstractUserBaseAction {
    private static final long serialVersionUID = 6332048762572310141L;
    public static final String NAME = "regiauthor";
    public static final String URL = "/user/regiauthor";
    private File sample;
    private String sampleContentType;
    private String sampleFileName;

    public RegiAuthorAction() {
    }

    public File getSample() {
        return this.sample;
    }

    public void setSample(File sample) {
        this.sample = sample;
    }

    public String getSampleContentType() {
        return this.sampleContentType;
    }

    public void setSampleContentType(String sampleContentType) {
        this.sampleContentType = sampleContentType;
    }

    public String getSampleFileName() {
        return this.sampleFileName;
    }

    public void setSampleFileName(String sampleFileName) {
        this.sampleFileName = sampleFileName;
    }

    public int getPageType() {
        return 24;
    }

    public String getTempName() {
        return "user/regiauthor";
    }

    protected void loadData() {
        this.logger.debug("loadData start.");
        this.logger.debug("loadData normally end.");
    }

    public String register() {
        this.logger.debug("register start.");
        if(ArrayUtils.contains(YiDuConstants.allowSampleTypes, this.getSampleContentType())) {
            this.addActionMessage(this.getText("messages.save.success"));
            this.logger.debug("register normally end.");
            return "freemarker";
        } else {
            this.addActionError(this.getText("errors.file.type"));
            return "freemarker";
        }
    }
}
