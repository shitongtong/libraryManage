//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yidu.novel.action.base;

import org.apache.struts2.interceptor.validation.SkipValidation;
import org.yidu.novel.action.base.AbstractBaseAction;

public abstract class AbstractAdminBaseAction extends AbstractBaseAction {
    private static final long serialVersionUID = 4900892616460135567L;
    public static final String NAMESPACE = "/admin";

    public AbstractAdminBaseAction() {
    }

    protected abstract void loadData();

    @SkipValidation
    public String execute() {
        this.logger.debug("execute start.");
        this.initCollections(new String[]{"collectionProperties.article.category"});
        this.loadData();
        if(this.hasErrors()) {
            this.logger.debug("execute abnormally end.");
            return "adminerror";
        } else {
            this.logger.debug("execute normally end.");
            return "input";
        }
    }
}
