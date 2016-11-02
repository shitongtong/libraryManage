//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yidu.novel.action.admin;

import java.io.File;
import java.io.FileOutputStream;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.struts2.convention.annotation.Action;
import org.yidu.novel.action.base.AbstractAdminEditBaseAction;
import org.yidu.novel.constant.YiDuConstants;

@Action("configEdit")
public class ConfigEditAction extends AbstractAdminEditBaseAction {
    private static final long serialVersionUID = -6768164951656460867L;
    private String filePath;
    private String relativeIamgePath;
    private String themeName;
    private boolean skipAuthCheck;
    private boolean cacheEffective;
    private boolean cleanUrl;
    private boolean gzipEffective;
    private boolean adEffective;
    private int countPerPage;
    private int maxBookcase;
    private boolean createIndexPage;
    private boolean createSiteMap;
    private String txtEncoding;
    private String dburl;
    private String username;
    private String password;

    public ConfigEditAction() {
    }

    public String getFilePath() {
        return this.filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getRelativeIamgePath() {
        return this.relativeIamgePath;
    }

    public void setRelativeIamgePath(String relativeIamgePath) {
        this.relativeIamgePath = relativeIamgePath;
    }

    public String getThemeName() {
        return this.themeName;
    }

    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }

    public boolean isSkipAuthCheck() {
        return this.skipAuthCheck;
    }

    public void setSkipAuthCheck(boolean skipAuthCheck) {
        this.skipAuthCheck = skipAuthCheck;
    }

    public boolean isCacheEffective() {
        return this.cacheEffective;
    }

    public void setCacheEffective(boolean cacheEffective) {
        this.cacheEffective = cacheEffective;
    }

    public boolean isCleanUrl() {
        return this.cleanUrl;
    }

    public void setCleanUrl(boolean cleanUrl) {
        this.cleanUrl = cleanUrl;
    }

    public boolean isGzipEffective() {
        return this.gzipEffective;
    }

    public void setGzipEffective(boolean gzipEffective) {
        this.gzipEffective = gzipEffective;
    }

    public boolean isAdEffective() {
        return this.adEffective;
    }

    public void setAdEffective(boolean adEffective) {
        this.adEffective = adEffective;
    }

    public int getCountPerPage() {
        return this.countPerPage;
    }

    public void setCountPerPage(int countPerPage) {
        this.countPerPage = countPerPage;
    }

    public int getMaxBookcase() {
        return this.maxBookcase;
    }

    public void setMaxBookcase(int maxBookcase) {
        this.maxBookcase = maxBookcase;
    }

    public boolean isCreateIndexPage() {
        return this.createIndexPage;
    }

    public void setCreateIndexPage(boolean createIndexPage) {
        this.createIndexPage = createIndexPage;
    }

    public boolean isCreateSiteMap() {
        return this.createSiteMap;
    }

    public void setCreateSiteMap(boolean createSiteMap) {
        this.createSiteMap = createSiteMap;
    }

    public String getTxtEncoding() {
        return this.txtEncoding;
    }

    public void setTxtEncoding(String txtEncoding) {
        this.txtEncoding = txtEncoding;
    }

    public String getDburl() {
        return this.dburl;
    }

    public void setDburl(String dburl) {
        this.dburl = dburl;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    protected void loadData() {
        this.initCollections(new String[]{"collectionProperties.boolean"});
        this.filePath = YiDuConstants.yiduConf.getString("filePath");
        this.relativeIamgePath = YiDuConstants.yiduConf.getString("relativeIamgePath");
        this.skipAuthCheck = YiDuConstants.yiduConf.getBoolean("skipAuthCheck", false);
        this.cacheEffective = YiDuConstants.yiduConf.getBoolean("cacheEffective", true);
        this.cleanUrl = YiDuConstants.yiduConf.getBoolean("cleanUrl", true);
        this.gzipEffective = YiDuConstants.yiduConf.getBoolean("gzipEffective", true);
        this.adEffective = YiDuConstants.yiduConf.getBoolean("adEffective", true);
        this.countPerPage = YiDuConstants.yiduConf.getInt("countPerPage", 15);
        this.maxBookcase = YiDuConstants.yiduConf.getInt("maxBookcase", 15);
        this.themeName = YiDuConstants.yiduConf.getString("themeName");
        this.createIndexPage = YiDuConstants.yiduConf.getBoolean("createIndexPage", true);
        this.createSiteMap = YiDuConstants.yiduConf.getBoolean("createSiteMap", true);
        this.txtEncoding = YiDuConstants.yiduConf.getString("txtEncoding");

        try {
            PropertiesConfiguration e = new PropertiesConfiguration("jdbc.properties");
            this.dburl = e.getString("jdbc.url");
            this.username = e.getString("jdbc.username");
            this.password = e.getString("jdbc.password");
        } catch (ConfigurationException var2) {
            this.logger.error(var2);
        }

    }

    public String save() {
        YiDuConstants.yiduConf.setProperty("filePath", this.filePath);
        YiDuConstants.yiduConf.setProperty("relativeIamgePath", this.relativeIamgePath);
        YiDuConstants.yiduConf.setProperty("skipAuthCheck", Boolean.valueOf(this.skipAuthCheck));
        YiDuConstants.yiduConf.setProperty("cacheEffective", Boolean.valueOf(this.cacheEffective));
        YiDuConstants.yiduConf.setProperty("cleanUrl", Boolean.valueOf(this.cleanUrl));
        YiDuConstants.yiduConf.setProperty("gzipEffective", Boolean.valueOf(this.gzipEffective));
        YiDuConstants.yiduConf.setProperty("adEffective", Boolean.valueOf(this.adEffective));
        YiDuConstants.yiduConf.setProperty("countPerPage", Integer.valueOf(this.countPerPage));
        YiDuConstants.yiduConf.setProperty("maxBookcase", Integer.valueOf(this.maxBookcase));
        YiDuConstants.yiduConf.setProperty("themeName", this.themeName);
        YiDuConstants.yiduConf.setProperty("createIndexPage", Boolean.valueOf(this.createIndexPage));
        YiDuConstants.yiduConf.setProperty("createSiteMap", Boolean.valueOf(this.createSiteMap));
        YiDuConstants.yiduConf.setProperty("txtEncoding", this.txtEncoding);

        try {
            File e = new File(YiDuConstants.yiduConf.getPath());
            FileOutputStream out = new FileOutputStream(e);
            YiDuConstants.yiduConf.save(out);
            PropertiesConfiguration jdbcConf = new PropertiesConfiguration(Thread.currentThread().getContextClassLoader().getResource("jdbc.properties"));
            jdbcConf.getString("jdbc.url", this.dburl);
            jdbcConf.getString("jdbc.username", this.username);
            jdbcConf.getString("jdbc.password", this.password);
            File jdbcFile = new File(jdbcConf.getPath());
            out = new FileOutputStream(jdbcFile);
            jdbcConf.save(out);
        } catch (Exception var5) {
            this.addActionError(var5.getMessage());
            this.logger.error(var5);
            return "adminerror";
        }

        this.loadData();
        this.addActionMessage(this.getText("messages.save.success"));
        return "input";
    }
}
