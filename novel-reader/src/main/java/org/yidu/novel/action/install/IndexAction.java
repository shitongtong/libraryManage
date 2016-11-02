//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yidu.novel.action.install;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.yidu.novel.action.base.AbstractInstallBaseAction;
import org.yidu.novel.utils.Utils;

public class IndexAction extends AbstractInstallBaseAction {
    private static final long serialVersionUID = -5991997032217966675L;
    public static final String NAMESPACE = "install";
    public static final String NAME = "index";
    public static final String URL = "install/index";
    private String prefixjdbc = "jdbc:postgresql://";
    private static String LOCK_FILE = ServletActionContext.getServletContext().getRealPath("/") + "/install.lock";
    private String title;
    private String siteKeywords;
    private String siteDescription;
    private String name;
    private String url;
    private String copyright;
    private String beianNo;
    private String analyticscode;
    private String dbhost;
    private String dbport;
    private String dbname;
    private String dbusername;
    private String dbpassword;
    private String username;
    private String password;

    public IndexAction() {
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

    public String getDbhost() {
        return this.dbhost;
    }

    public void setDbhost(String dbhost) {
        this.dbhost = dbhost;
    }

    public String getDbport() {
        return this.dbport;
    }

    public void setDbport(String dbport) {
        this.dbport = dbport;
    }

    public String getDbname() {
        return this.dbname;
    }

    public void setDbname(String dbname) {
        this.dbname = dbname;
    }

    public String getDbusername() {
        return this.dbusername;
    }

    public void setDbusername(String dbusername) {
        this.dbusername = dbusername;
    }

    public String getDbpassword() {
        return this.dbpassword;
    }

    public void setDbpassword(String dbpassword) {
        this.dbpassword = dbpassword;
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

    @SkipValidation
    public String execute() {
        this.logger.debug("execute start.");
        File lockFile = new File(LOCK_FILE);
        if(lockFile.exists()) {
            this.addActionError(this.getText("errors.install.file.exist", new String[]{LOCK_FILE}));
            return "error";
        } else {
            try {
                PropertiesConfiguration e = new PropertiesConfiguration("jdbc.properties");
                String dburl = e.getString("jdbc.url").substring(this.prefixjdbc.length());
                String[] dbinfo = StringUtils.split(dburl, ":");
                this.dbhost = dbinfo[0];
                dbinfo = StringUtils.split(dbinfo[1], "/");
                this.dbport = dbinfo[0];
                this.dbname = "yidu";
                this.dbusername = e.getString("jdbc.username");
                this.dbpassword = e.getString("jdbc.password");
                PropertiesConfiguration languageConf = new PropertiesConfiguration(Thread.currentThread().getContextClassLoader().getResource("language/package.properties"));
                this.title = languageConf.getString("label.system.title");
                this.siteKeywords = languageConf.getString("label.system.siteKeywords");
                this.siteDescription = languageConf.getString("label.system.siteDescription");
                this.name = languageConf.getString("label.system.name");
                this.url = languageConf.getString("label.system.url");
                this.copyright = languageConf.getString("label.system.copyright");
                this.beianNo = languageConf.getString("label.system.beianNo");
                this.analyticscode = languageConf.getString("label.system.analyticscode");
            } catch (ConfigurationException var6) {
                this.logger.error(var6);
            }

            this.logger.debug("execute normally end.");
            return "input";
        }
    }

    public String save() {
        File lockFile = new File(LOCK_FILE);
        if(lockFile.exists()) {
            this.addActionError(this.getText("errors.install.file.exist", new String[]{LOCK_FILE}));
            return "error";
        } else {
            Connection conn = null;
            Statement stmt = null;
            ResultSet rs = null;

            label95: {
                try {
                    Class.forName("org.postgresql.Driver");
                    conn = DriverManager.getConnection(this.prefixjdbc + this.dbhost + ":" + this.dbport + "/postgres", this.dbusername, this.dbpassword);
                    stmt = conn.createStatement();
                    rs = null;
                    String e = "SELECT 1 FROM pg_database WHERE datname = \'" + this.dbname + "\'";
                    rs = stmt.executeQuery(e);

                    boolean dbExist;
                    for(dbExist = false; rs.next(); dbExist = true) {
                        ;
                    }

                    if(dbExist) {
                        stmt.execute("drop database " + this.dbname);
                    }

                    this.excuteSqlFromFile(conn, "01_createdb.sql", new Object[]{this.dbname});
                    conn = DriverManager.getConnection(this.prefixjdbc + this.dbhost + ":" + this.dbport + "/" + this.dbname, this.dbusername, this.dbpassword);
                    this.excuteSqlFromFile(conn, "02_createtable.sql", new Object[0]);
                    this.excuteSqlFromFile(conn, "03_createindex.sql", new Object[0]);
                    this.excuteSqlFromFile(conn, "04_master_data.sql", new Object[]{this.username, Utils.convert2MD5(this.password)});
                    PropertiesConfiguration jdbcConf = new PropertiesConfiguration(Thread.currentThread().getContextClassLoader().getResource("jdbc.properties"));
                    jdbcConf.setProperty("jdbc.url", this.prefixjdbc + this.dbhost + ":" + this.dbport + "/" + this.dbname);
                    jdbcConf.setProperty("jdbc.username", this.dbusername);
                    jdbcConf.setProperty("jdbc.password", this.dbpassword);
                    File jdbcFile = new File(jdbcConf.getPath());
                    FileOutputStream out = new FileOutputStream(jdbcFile);
                    jdbcConf.save(out);
                    PropertiesConfiguration languageConf = new PropertiesConfiguration(Thread.currentThread().getContextClassLoader().getResource("language/package.properties"));
                    languageConf.setProperty("label.system.title", this.title);
                    languageConf.setProperty("label.system.siteKeywords", this.siteKeywords);
                    languageConf.setProperty("label.system.siteDescription", this.siteDescription);
                    languageConf.setProperty("label.system.name", this.name);
                    languageConf.setProperty("label.system.url", this.url);
                    languageConf.setProperty("label.system.copyright", this.copyright);
                    languageConf.setProperty("label.system.beianNo", this.beianNo);
                    languageConf.setProperty("label.system.analyticscode", this.analyticscode);
                    File languageFile = new File(languageConf.getPath());
                    out = new FileOutputStream(languageFile);
                    languageConf.save(out);
                    lockFile.createNewFile();
                    break label95;
                } catch (Exception var20) {
                    this.addActionError(var20.getMessage());
                    this.logger.error(var20.getMessage(), var20);
                } finally {
                    try {
                        rs.close();
                        stmt.close();
                        conn.close();
                    } catch (SQLException var19) {
                        this.logger.error(var19);
                    }

                }

                return "error";
            }

            this.addActionMessage(this.getText("messages.install.success"));
            return "success";
        }
    }

    private void excuteSqlFromFile(Connection conn, String fileName, Object... params) throws FileNotFoundException, IOException, SQLException {
        URL url = this.getClass().getResource(fileName);
        String realPath = url.getPath();
        File file = new File(realPath);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String sql = new String();
        new String();

        String line;
        while((line = br.readLine()) != null) {
            sql = sql + line + "\r\n";
        }

        br.close();
        Statement stmt = conn.createStatement();
        sql = MessageFormat.format(sql, params);
        stmt.execute(sql);
    }

    protected void loadData() {
    }
}
