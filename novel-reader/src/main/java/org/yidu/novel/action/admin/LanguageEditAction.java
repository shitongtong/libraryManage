//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yidu.novel.action.admin;

import java.io.File;
import java.io.FileOutputStream;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.yidu.novel.action.base.AbstractAdminEditBaseAction;

@Action("languageEdit")
public class LanguageEditAction extends AbstractAdminEditBaseAction {
    private static final long serialVersionUID = -6768164951656460867L;
    private String title;
    private String siteKeywords;
    private String siteDescription;
    private String name;
    private String url;
    private String copyright;
    private String beianNo;
    private String analyticscode;

    public LanguageEditAction() {
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSiteKeywords() {
        return this.siteKeywords;
    }

    public void setSiteKeywords(String siteKeywords) {
        this.siteKeywords = siteKeywords;
    }

    public String getSiteDescription() {
        return this.siteDescription;
    }

    public void setSiteDescription(String siteDescription) {
        this.siteDescription = siteDescription;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCopyright() {
        return this.copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getBeianNo() {
        return this.beianNo;
    }

    public void setBeianNo(String beianNo) {
        this.beianNo = beianNo;
    }

    public String getAnalyticscode() {
        return this.analyticscode;
    }

    public void setAnalyticscode(String analyticscode) {
        this.analyticscode = analyticscode;
    }

    protected void loadData() {
        try {
            PropertiesConfiguration e = new PropertiesConfiguration(Thread.currentThread().getContextClassLoader().getResource("language/package.properties"));
            this.title = e.getString("label.system.title");
            this.siteKeywords = e.getString("label.system.siteKeywords");
            this.siteDescription = e.getString("label.system.siteDescription");
            this.name = e.getString("label.system.name");
            this.url = e.getString("label.system.url");
            this.copyright = e.getString("label.system.copyright");
            this.beianNo = e.getString("label.system.beianNo");
            this.analyticscode = e.getString("label.system.analyticscode");
        } catch (ConfigurationException var2) {
            this.logger.error(var2);
        }

    }

    public String save() {
        try {
            PropertiesConfiguration e = new PropertiesConfiguration(Thread.currentThread().getContextClassLoader().getResource("language/package.properties"));
            e.setProperty("label.system.title", this.escape(this.title));
            e.setProperty("label.system.siteKeywords", this.escape(this.siteKeywords));
            e.setProperty("label.system.siteDescription", this.escape(this.siteDescription));
            e.setProperty("label.system.name", this.escape(this.name));
            e.setProperty("label.system.url", this.escape(this.url));
            e.setProperty("label.system.copyright", this.escape(this.copyright));
            e.setProperty("label.system.beianNo", this.escape(this.beianNo));
            e.setProperty("label.system.analyticscode", this.escape(this.analyticscode));
            File languageFile = new File(e.getPath());
            FileOutputStream out = new FileOutputStream(languageFile);
            e.save(out);
        } catch (Exception var4) {
            this.addActionError(var4.getMessage());
            this.logger.error(var4);
            return "adminerror";
        }

        this.loadData();
        this.addActionMessage(this.getText("messages.save.success"));
        return "input";
    }

    private String escape(String value) {
        return StringUtils.replace(value, ",", "\\,");
    }
}
