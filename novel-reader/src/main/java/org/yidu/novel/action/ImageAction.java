//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yidu.novel.action;

import java.io.File;
import java.io.FileInputStream;
import org.apache.struts2.convention.annotation.Result;
import org.yidu.novel.action.base.AbstractPublicListBaseAction;
import org.yidu.novel.constant.YiDuConstants;

@Result(
        name = "input",
        type = "imageResult"
)
public class ImageAction extends AbstractPublicListBaseAction {
    private static final long serialVersionUID = -4215796997609788238L;
    private int articleno;

    public ImageAction() {
    }

    public int getArticleno() {
        return this.articleno;
    }

    public void setArticleno(int articleno) {
        this.articleno = articleno;
    }

    protected void loadData() {
    }

    public int getPageType() {
        return 99;
    }

    public String getCustomContentType() {
        return "image/jpeg";
    }

    public String getCustomContentDisposition() {
        return this.articleno + ".jpg";
    }

    public byte[] getGraph() {
        this.logger.debug("getGraph(10) start.");
        byte[] binaryinfo = null;
        if(this.articleno != 0) {
            String picPath = YiDuConstants.yiduConf.getString("iamgePath");
            String jpgPath = picPath + "/" + this.articleno / 1000 + "/" + this.articleno + "/" + this.articleno + "s.jpg";
            String gifPath = picPath + "/" + this.articleno / 1000 + "/" + this.articleno + "/" + this.articleno + "s.gif";

            try {
                File e = new File(jpgPath);
                FileInputStream inputStream;
                if(e.exists()) {
                    binaryinfo = new byte[(int)e.length()];
                    inputStream = new FileInputStream(e);
                    inputStream.read(binaryinfo);
                    inputStream.close();
                } else {
                    e = new File(gifPath);
                    if(e.exists()) {
                        binaryinfo = new byte[(int)e.length()];
                        inputStream = new FileInputStream(e);
                        inputStream.read(binaryinfo);
                        inputStream.close();
                    }
                }
            } catch (Exception var7) {
                this.logger.error(var7);
            }

            this.logger.debug("getGraph(99) normally end.");
        } else {
            this.logger.debug("getGraph(30) measurePointId is null.");
        }

        return binaryinfo;
    }
}
