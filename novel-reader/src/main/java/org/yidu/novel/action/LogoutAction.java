//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yidu.novel.action;

import javax.servlet.http.Cookie;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.yidu.novel.action.base.AbstractPublicBaseAction;
import org.yidu.novel.utils.CookieUtils;
import org.yidu.novel.utils.LoginManager;

public class LogoutAction extends AbstractPublicBaseAction {
    private static final long serialVersionUID = -5189599136868434255L;

    public LogoutAction() {
    }

    @SkipValidation
    public String execute() {
        this.logger.info("LoginOutAction execute has been excuted.");
        if(LoginManager.isLoginFlag()) {
            Cookie cookie = CookieUtils.delUserCookie(ServletActionContext.getRequest());
            if(cookie != null) {
                ServletActionContext.getResponse().addCookie(cookie);
            }

            LoginManager.doLogout();
        }

        return "GOTO_Top";
    }

    public int getPageType() {
        return 99;
    }

    protected void loadData() {
    }
}
