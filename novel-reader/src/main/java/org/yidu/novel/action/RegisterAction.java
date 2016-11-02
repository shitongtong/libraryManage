//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yidu.novel.action;

import com.opensymphony.xwork2.validator.annotations.RegexFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;
import org.yidu.novel.action.base.AbstractPublicBaseAction;
import org.yidu.novel.bean.UserSearchBean;
import org.yidu.novel.entity.TUser;
import org.yidu.novel.utils.LoginManager;
import org.yidu.novel.utils.Utils;

public class RegisterAction extends AbstractPublicBaseAction {
    private static final long serialVersionUID = 1L;
    private String loginid;
    private String password;
    private String repassword;
    private String email;
    private String qq;

    public RegisterAction() {
    }

    public String getLoginid() {
        return this.loginid;
    }

    @RequiredStringValidator(
            message = "${getText(\"errors.required.input\", {getText(\"label.user.loginid\")})}"
    )
    @StringLengthFieldValidator(
            minLength = "5",
            maxLength = "32",
            message = "${getText(\"errors.lengthrange\", { {minLength}, {maxLength},getText(\"label.user.loginid\")})}"
    )
    public void setLoginid(String loginid) {
        this.loginid = loginid;
    }

    public String getPassword() {
        return this.password;
    }

    @RequiredStringValidator(
            message = "${getText(\"errors.required.input\", {getText(\"label.user.password\")})}"
    )
    @StringLengthFieldValidator(
            minLength = "6",
            maxLength = "32",
            message = "${getText(\"errors.lengthrange\", { {minLength},{maxLength},getText(\"label.user.password\")})}"
    )
    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepassword() {
        return this.repassword;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }

    public String getEmail() {
        return this.email;
    }

    @RequiredStringValidator(
            message = "${getText(\"errors.required.input\", {getText(\"label.user.email\")})}"
    )
    @StringLengthFieldValidator(
            maxLength = "60",
            message = "${getText(\"errors.maxlength\", { {maxLength},getText(\"label.user.email\")})}"
    )
    @RegexFieldValidator(
            regexExpression = "^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w+)+)$",
            message = "${getText(\"errors.format.email\", {getText(\'label.user.email\')})}"
    )
    public void setEmail(String email) {
        this.email = email;
    }

    public String getQq() {
        return this.qq;
    }

    @StringLengthFieldValidator(
            maxLength = "15",
            message = "${getText(\"errors.maxlength\", { {maxLength},getText(\"label.user.qq\")})}"
    )
    @RegexFieldValidator(
            regexExpression = "^\\d*$",
            message = "${getText(\"errors.format.number\", {getText(\'label.user.qq\')})}"
    )
    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getTempName() {
        return "register";
    }

    @SkipValidation
    public String execute() {
        this.logger.info("RegisterAction execute has been excuted.");
        return LoginManager.isLoginFlag()?"redirect":"freemarker";
    }

    @Transactional
    public String register() {
        this.logger.info("RegisterAction register started.");
        if(!StringUtils.equals(this.password, this.repassword)) {
            this.addActionError(this.getText("errors.repassword"));
            return "freemarker";
        } else {
            UserSearchBean searchBean = new UserSearchBean();
            searchBean.setLoginid(this.loginid);
            searchBean.setDeleteflag(Boolean.valueOf(false));
            List userList = this.userService.find(searchBean);
            if(userList != null && userList.size() > 0) {
                this.addActionError(this.getText("errors.duplicated", new String[]{this.getText("label.user.loginid")}));
                return "freemarker";
            } else {
                TUser user = new TUser();
                BeanUtils.copyProperties(this, user);
                user.setDeleteflag(Boolean.valueOf(false));
                user.setRegdate(new Date());
                user.setPassword(Utils.convert2MD5(this.password));
                user.setType(Short.valueOf((short)10));
                this.userService.save(user);
                LoginManager.doLogin(user);
                this.logger.debug("RegisterAction register normally end.");
                return "redirect";
            }
        }
    }

    public int getPageType() {
        return 12;
    }

    protected void loadData() {
    }
}
