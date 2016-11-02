//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yidu.novel.action;

import org.apache.struts2.convention.annotation.Action;
import org.yidu.novel.action.base.AbstractPublicListBaseAction;

@Action("mobileSearch")
public class MobileSearchAction extends AbstractPublicListBaseAction {
    private static final long serialVersionUID = -4215796997609788238L;
    private String key;

    public MobileSearchAction() {
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTempName() {
        return "search";
    }

    protected void loadData() {
        this.logger.debug("loadData start.");
        this.logger.debug("normally end.");
    }

    public int getPageType() {
        return 5;
    }
}
