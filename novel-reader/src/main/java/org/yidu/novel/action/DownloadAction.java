//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yidu.novel.action;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.yidu.novel.action.base.AbstractPublicBaseAction;
import org.yidu.novel.bean.ChapterSearchBean;
import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.entity.TChapter;

@Results({    @Result(
        name = "download",
        type = "stream",
        params = {"inputName", "is", "contentType", "application/octet-stream; charset=UTF-8", "contentLength", "${length}", "contentDisposition", "attachment; filename =${downloadFileName}"}
),     @Result(
        name = "error",
        type = "httpheader",
        params = {"status", "404"}
)})
public class DownloadAction extends AbstractPublicBaseAction {
    private static final long serialVersionUID = -6265156751627551469L;
    public static final String NAME = "download";
    public static final String URL = "/download";
    public static final String RESULT_DOWNLOAD = "download";
    private String downloadFileName;
    private int articleno;
    private int chapterno;
    private long length;
    private InputStream is;

    public DownloadAction() {
    }

    public String getDownloadFileName() {
        return this.downloadFileName;
    }

    public void setDownloadFileName(String downloadFileName) {
        this.downloadFileName = downloadFileName;
    }

    public long getLength() {
        return this.length;
    }

    public InputStream getIs() {
        return this.is;
    }

    public int getArticleno() {
        return this.articleno;
    }

    public void setArticleno(int articleno) {
        this.articleno = articleno;
    }

    public int getChapterno() {
        return this.chapterno;
    }

    public void setChapterno(int chapterno) {
        this.chapterno = chapterno;
    }

    @SkipValidation
    public String execute() {
        this.logger.debug("execute start.");
        this.setDownloadFileName(this.articleno + ".txt");
        long size = 0L;

        try {
            Vector e = new Vector();
            ChapterSearchBean searchBean = new ChapterSearchBean();
            searchBean.setArticleno(this.articleno);
            List chapterList = this.chapterService.find(searchBean);
            File dir = new File(YiDuConstants.yiduConf.getString("filePath") + "/" + this.articleno / 1000 + "/" + this.articleno);
            if(dir.isDirectory()) {
                File[] enumeration = dir.listFiles();
                HashMap fileMap = new HashMap();
                File[] file = enumeration;
                int chapterno = enumeration.length;

                for(int var10 = 0; var10 < chapterno; ++var10) {
                    File chapter = file[var10];
                    fileMap.put(chapter.getName(), chapter);
                }

                Iterator var18 = chapterList.iterator();

                while(var18.hasNext()) {
                    TChapter var17 = (TChapter)var18.next();
                    chapterno = var17.getChapterno();
                    File var19 = (File)fileMap.get(chapterno + ".txt");
                    if(var19 != null) {
                        String chaptername = var17.getChaptername() + "\n";
                        size += (long)chaptername.length();
                        size += var19.length();
                        ByteArrayInputStream chapternameis = new ByteArrayInputStream(chaptername.getBytes("GBK"));
                        e.addElement(chapternameis);
                        e.addElement(new FileInputStream(var19));
                    }
                }
            }

            Enumeration var16 = e.elements();
            this.is = new SequenceInputStream(var16);
            this.length = size;
        } catch (Exception var15) {
            this.logger.error(this.getText("YIDU99999"), var15);
            this.addActionError(this.getText("YIDU99999"));
            return "freemarker_error";
        }

        this.logger.debug("execute normally end.");
        return "download";
    }

    protected void loadData() {
    }

    public int getPageType() {
        return 99;
    }
}
