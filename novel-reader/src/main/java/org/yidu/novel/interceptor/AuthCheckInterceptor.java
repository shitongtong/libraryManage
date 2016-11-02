//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yidu.novel.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.yidu.novel.action.base.AbstractAdminBaseAction;
import org.yidu.novel.action.base.AbstractBaseAction;
import org.yidu.novel.action.base.AbstractInstallBaseAction;
import org.yidu.novel.action.base.AbstractPublicBaseAction;
import org.yidu.novel.action.base.AbstractUserBaseAction;
import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.service.UserService;
import org.yidu.novel.utils.CookieUtils;
import org.yidu.novel.utils.LoginManager;

public class AuthCheckInterceptor extends AbstractInterceptor {
    private static final long serialVersionUID = 6232895416881210405L;
    private static final String AUTH_ERROR_KEY = "errors.unauthority";
    private static final String UNKNOWN_ERROR_KEY = "errors.unknown";
    private final Log logger = LogFactory.getLog(this.getClass());
    protected UserService userService;

    public AuthCheckInterceptor() {
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public String intercept(ActionInvocation invocation) throws Exception {
        this.logger.debug("AuthCheckInterceptor start.");
        if(invocation.getAction() instanceof AbstractInstallBaseAction) {
            return invocation.invoke();
        } else {
            boolean skipAuthCheck = YiDuConstants.yiduConf.getBoolean("skipAuthCheck", false);
            if(skipAuthCheck) {
                return invocation.invoke();
            } else {
                if(!LoginManager.isLoginFlag()) {
                    CookieUtils.getUserCookieAndLogoin(ServletActionContext.getRequest(), this.userService);
                }

                if(invocation.getAction() instanceof AbstractUserBaseAction) {
                    return LoginManager.isLoginFlag()?invocation.invoke():"GOTO_Login";
                } else if(invocation.getAction() instanceof AbstractAdminBaseAction) {
                    AbstractAdminBaseAction action1 = (AbstractAdminBaseAction)invocation.getAction();
                    if(LoginManager.isLoginFlag()) {
                        if(LoginManager.getLoginUser().getType().shortValue() == 30) {
                            return invocation.invoke();
                        } else {
                            action1.addActionError(action1.getText("errors.unauthority"));
                            return "adminerror";
                        }
                    } else {
                        return "GOTO_Login";
                    }
                } else if(invocation.getAction() instanceof AbstractPublicBaseAction) {
                    return invocation.invoke();
                } else {
                    AbstractBaseAction action = (AbstractBaseAction)invocation.getAction();
                    String errorMsg = action.getText("errors.unknown");
                    action.addActionError(errorMsg);
                    return "freemarker_error";
                }
            }
        }
    }
}
