//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yidu.novel.action;

import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import java.util.Date;
import javax.servlet.http.Cookie;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.transaction.annotation.Transactional;
import org.yidu.novel.action.base.AbstractPublicBaseAction;
import org.yidu.novel.entity.TUser;
import org.yidu.novel.utils.CookieUtils;
import org.yidu.novel.utils.LoginManager;
import org.yidu.novel.utils.Utils;

public class LoginAction extends AbstractPublicBaseAction {
    private static final long serialVersionUID = 1L;
    public static final String NAME = "login";
    public static final String URL = "/login";
    private String loginid;
    private String password;
    private boolean useCookie;

    public LoginAction() {
    }

    public String getLoginid() {
        return this.loginid;
    }

    @Validations(
            requiredStrings = {            @RequiredStringValidator(
                    message = "${getText(\"errors.required.input\", {getText(\"label.user.loginid\")})}"
            )},
            stringLengthFields = {            @StringLengthFieldValidator(
                    maxLength = "32",
                    message = "${getText(\"errors.maxlength\", { {maxLength},getText(\"label.user.loginid\")})}"
            )}
    )
    public void setLoginid(String loginid) {
        this.loginid = loginid;
    }

    public String getPassword() {
        return this.password;
    }

    @Validations(
            requiredStrings = {            @RequiredStringValidator(
                    message = "${getText(\"errors.required.input\", {getText(\"label.user.password\")})}"
            )},
            stringLengthFields = {            @StringLengthFieldValidator(
                    maxLength = "32",
                    message = "${getText(\"errors.maxlength\", { {maxLength},getText(\"label.user.password\")})}"
            )}
    )
    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isUseCookie() {
        return this.useCookie;
    }

    public void setUseCookie(boolean useCookie) {
        this.useCookie = useCookie;
    }

    public String getTempName() {
        return "login";
    }

    @SkipValidation
    public String execute() {
        this.logger.info("LoginAction execute has been excuted.");
        this.initCollections(new String[]{"collectionProperties.article.category"});
        return LoginManager.isLoginFlag()?"GOTO_Top":"freemarker";
    }

    @Transactional
    public String login() {
        this.logger.info("LoginAction login has been excuted.");
        TUser user = this.userService.findByLoginInfo(this.loginid, Utils.convert2MD5(this.password));
        if(user != null && user.getDeleteflag() != null && !user.getDeleteflag().booleanValue()) {
            this.logger.info("user info is " + user.toString());
            LoginManager.doLogin(user);
            user.setLastlogin(new Date());
            this.userService.save(user);
            Cookie cookie = CookieUtils.addUserCookie(user);
            ServletActionContext.getResponse().addCookie(cookie);
            this.logger.debug("LoginAction login user is exist. normally end.");
            return "redirect";
        } else {
            this.addActionError(this.getText("errors.login.failed"));
            this.logger.debug("LoginAction login user is not exist. abnormally end.");
            return "freemarker";
        }
    }

    public int getPageType() {
        return 11;
    }

    protected void loadData() {
    }
}
