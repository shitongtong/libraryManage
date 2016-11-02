//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yidu.novel.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.yidu.novel.action.base.AbstractPublicBaseAction;

@Result(
        name = "download",
        type = "stream",
        params = {"inputName", "is", "contentType", "application/x-download; charset=UTF-8", "contentLength", "${length}", "contentDisposition", "attachment; filename =${downloadFileName}"}
)
@Action("saveShortcut")
public class SaveShortcutAction extends AbstractPublicBaseAction {
    private static final long serialVersionUID = 1982119718178098202L;
    public static final String NAME = "download";
    public static final String URL = "/download";
    public static final String RESULT_DOWNLOAD = "download";
    private long length;
    private InputStream is;

    public SaveShortcutAction() {
    }

    public String getDownloadFileName() {
        try {
            return URLEncoder.encode(this.getText("label.system.name") + ".url", "UTF-8");
        } catch (UnsupportedEncodingException var2) {
            this.logger.error(var2);
            return this.getText("label.system.url") + ".url";
        }
    }

    public long getLength() {
        return this.length;
    }

    public InputStream getIs() {
        return this.is;
    }

    @SkipValidation
    public String execute() {
        this.logger.debug("execute start.");
        String shortcutStr = "[InternetShortcut]\nURL=" + this.getText("label.system.url");
        this.logger.info(shortcutStr);
        this.is = new ByteArrayInputStream(shortcutStr.getBytes());
        this.length = (long)shortcutStr.length();
        this.logger.debug("execute normally end.");
        return "download";
    }

    protected void loadData() {
    }

    public int getPageType() {
        return 99;
    }
}
