//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yidu.novel.action.base;

import com.google.gson.Gson;
import org.apache.commons.collections.map.LinkedMap;
import org.apache.struts2.ServletActionContext;
import org.yidu.novel.action.base.AbstractBaseAction;
import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.template.EncodeURLMethod;
import org.yidu.novel.template.GetTextMethod;

public abstract class AbstractPublicAndUserBaseAction extends AbstractBaseAction {
    private static final long serialVersionUID = 6698799932081679448L;
    private boolean hasError = false;
    private String backUrl;

    public AbstractPublicAndUserBaseAction() {
        logger.debug("entry constructor method: AbstractPublicAndUserBaseAction()");
    }

    protected abstract void loadData();

    public void setHasError(boolean hasError) {
        logger.debug("entry method: setHasError(boolean hasError) || hasError::"+hasError);
        this.hasError = hasError;
    }

    public boolean getHasError() {
        logger.debug("entry method: getHasError() || this.hasError::"+this.hasError);
        return this.hasError;
    }

    public String getCategoryData() {
        logger.debug("entry method getCategoryData()");
        Gson gson = new Gson();
        LinkedMap pulldown = new LinkedMap();
        String value = this.getText("collectionProperties.article.category");
        String[] items = value.split(",");
        String[] var8 = items;
        int var7 = items.length;

        for(int var6 = 0; var6 < var7; ++var6) {
            String item = var8[var6];
            String[] property = item.split(":");
            if(property.length == 2) {
                pulldown.put(property[0], property[1]);
            }
        }
        String pulldownJson = gson.toJson(pulldown);
        logger.debug("pulldownJson::"+pulldownJson);
        return pulldownJson;
    }

    public boolean getAdEffective() {
        return YiDuConstants.yiduConf.getBoolean("adEffective", true);
    }

    public String getBackUrl() {
        return this.backUrl;
    }

    public void setBackUrl(String backUrl) {
        this.backUrl = backUrl;
    }

    public EncodeURLMethod getEncodeURL() {
        return new EncodeURLMethod(ServletActionContext.getResponse());
    }

    public GetTextMethod getGetText() {
        return new GetTextMethod(this);
    }

    public String getThemeName() {
        return YiDuConstants.yiduConf.getString("themeName", "default");
    }
}
