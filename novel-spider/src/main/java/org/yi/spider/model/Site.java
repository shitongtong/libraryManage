//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yi.spider.model;

import java.util.List;
import org.yi.spider.enums.ProgramEnum;
import org.yi.spider.model.Template;

public class Site {
    private String siteUrl;
    private String siteName;
    private ProgramEnum program;
    private String charset;
    private String txtFile;
    private String htmlFile;
    private String coverDir;
    private String basePath;
    private Template template;
    private String copyright;
    private String description;
    private String keywords;
    private List<String> chapterList;
    private Integer usePinyin;

    public Site() {
    }

    public String getSiteUrl() {
        return this.siteUrl;
    }

    public void setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl;
    }

    public String getSiteName() {
        return this.siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public ProgramEnum getProgram() {
        return this.program;
    }

    public void setProgram(ProgramEnum program) {
        this.program = program;
    }

    public String getCharset() {
        return this.charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getCoverDir() {
        return this.coverDir;
    }

    public void setCoverDir(String coverDir) {
        this.coverDir = coverDir;
    }

    public List<String> getChapterList() {
        return this.chapterList;
    }

    public void setChapterList(List<String> chapterList) {
        this.chapterList = chapterList;
    }

    public String getBasePath() {
        return this.basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public Template getTemplate() {
        return this.template;
    }

    public void setTemplate(Template template) {
        this.template = template;
    }

    public String getCopyright() {
        return this.copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getKeywords() {
        return this.keywords;
    }

    public String getTxtFile() {
        return this.txtFile;
    }

    public void setTxtFile(String txtFile) {
        this.txtFile = txtFile;
    }

    public String getHtmlFile() {
        return this.htmlFile;
    }

    public void setHtmlFile(String htmlFile) {
        this.htmlFile = htmlFile;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public Integer getUsePinyin() {
        return this.usePinyin;
    }

    public void setUsePinyin(Integer usePinyin) {
        this.usePinyin = usePinyin;
    }
}
