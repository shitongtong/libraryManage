//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yidu.novel.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.yidu.novel.action.base.AbstractPublicBaseAction;
import org.yidu.novel.entity.TUser;
import org.yidu.novel.utils.LoginManager;

@Action("checklogin")
public class CheckLoginAction extends AbstractPublicBaseAction {
    private static final long serialVersionUID = -5991997032217966675L;
    public static final String NAME = "checklogin";
    public static final String URL = "/checklogin";

    public CheckLoginAction() {
    }

    @SkipValidation
    public String execute() {
        this.logger.debug("execute normally end.");
        return "json";
    }

    public TUser getData() {
        return LoginManager.isLoginFlag()?LoginManager.getLoginUser():null;
    }

    public void loadData() {
    }

    public int getPageType() {
        return 99;
    }
}
