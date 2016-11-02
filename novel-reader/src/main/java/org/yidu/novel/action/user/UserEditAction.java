//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yidu.novel.action.user;

import com.opensymphony.xwork2.validator.annotations.RegexFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import java.util.ArrayList;
import java.util.Date;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.BeanUtils;
import org.yidu.novel.action.base.AbstractUserBaseAction;
import org.yidu.novel.entity.TUser;
import org.yidu.novel.utils.LoginManager;
import org.yidu.novel.utils.Utils;

@Action("useredit")
public class UserEditAction extends AbstractUserBaseAction {
    private static final long serialVersionUID = 8182483310788301445L;
    public static final String NAME = "useredit";
    public static final String URL = "/user/useredit";
    private String loginid;
    private String password;
    private String repassword;
    private String email;
    private String qq;
    private String username;
    private String realname;
    private Short sex;
    private String id;
    private String mobileno;
    private String branch;
    private String bankno;
    private String alipayacount;

    public UserEditAction() {
    }

    public String getLoginid() {
        return this.loginid;
    }

    public void setLoginid(String loginid) {
        this.loginid = loginid;
    }

    public String getPassword() {
        return this.password;
    }

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

    public Short getSex() {
        return this.sex;
    }

    public void setSex(Short sex) {
        this.sex = sex;
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

    public String getUsername() {
        return this.username;
    }

    @StringLengthFieldValidator(
            maxLength = "50",
            message = "${getText(\"errors.maxlength\", { {maxLength},getText(\"label.user.username\")})}"
    )
    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealname() {
        return this.realname;
    }

    @StringLengthFieldValidator(
            maxLength = "10",
            message = "${getText(\"errors.maxlength\", { {maxLength},getText(\"label.user.realname\")})}"
    )
    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getId() {
        return this.id;
    }

    @StringLengthFieldValidator(
            maxLength = "18",
            message = "${getText(\"errors.maxlength\", { {maxLength},getText(\"label.user.id\")})}"
    )
    public void setId(String id) {
        this.id = id;
    }

    public String getMobileno() {
        return this.mobileno;
    }

    @StringLengthFieldValidator(
            maxLength = "11",
            message = "${getText(\"errors.maxlength\", { {maxLength},getText(\"label.user.mobileno\")})}"
    )
    @RegexFieldValidator(
            regexExpression = "^\\d*$",
            message = "${getText(\"errors.format.number\", {getText(\'label.user.mobileno\')})}"
    )
    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String getBranch() {
        return this.branch;
    }

    @StringLengthFieldValidator(
            maxLength = "50",
            message = "${getText(\"errors.maxlength\", { {maxLength},getText(\"label.user.branch\")})}"
    )
    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getBankno() {
        return this.bankno;
    }

    @StringLengthFieldValidator(
            maxLength = "20",
            message = "${getText(\"errors.maxlength\", { {maxLength},getText(\"label.user.bankno\")})}"
    )
    @RegexFieldValidator(
            regexExpression = "^\\d*$",
            message = "${getText(\"errors.format.number\", {getText(\'label.user.bankno\')})}"
    )
    public void setBankno(String bankno) {
        this.bankno = bankno;
    }

    public String getAlipayacount() {
        return this.alipayacount;
    }

    @StringLengthFieldValidator(
            maxLength = "50",
            message = "${getText(\"errors.maxlength\", { {maxLength},getText(\"label.user.alipayacount\")})}"
    )
    public void setAlipayacount(String alipayacount) {
        this.alipayacount = alipayacount;
    }

    public int getPageType() {
        return 23;
    }

    public String getTempName() {
        return "user/useredit";
    }

    protected void loadData() {
        this.logger.debug("loadData start.");
        int userno = LoginManager.getLoginUser().getUserno();
        this.initCollections(new String[]{"collectionProperties.user.sex", "collectionProperties.user.type"});
        TUser user = this.userService.getByNo(userno);
        BeanUtils.copyProperties(user, this);
        this.logger.debug("loadData normally end.");
    }

    public String save() {
        this.logger.debug("save start.");
        if(!StringUtils.equals(this.password, this.repassword)) {
            this.addActionError(this.getText("errors.repassword"));
            return "freemarker";
        } else {
            new TUser();
            int userno = LoginManager.getLoginUser().getUserno();
            TUser user = this.userService.getByNo(userno);
            ArrayList ignoreProperties = new ArrayList();
            ignoreProperties.add("loginid");
            ignoreProperties.add("regdate");
            ignoreProperties.add("lastlogin");
            if(StringUtils.isEmpty(this.password)) {
                ignoreProperties.add("password");
            }

            if(StringUtils.isNotEmpty(user.getRealname())) {
                ignoreProperties.add("realname");
            }

            if(StringUtils.isNotEmpty(user.getUsername())) {
                ignoreProperties.add("username");
            }

            if(user.getSex() != null && user.getSex().shortValue() != 0) {
                ignoreProperties.add("sex");
            }

            if(StringUtils.isNotEmpty(user.getId())) {
                ignoreProperties.add("id");
            }

            if(StringUtils.isNotEmpty(user.getBranch())) {
                ignoreProperties.add("branch");
            }

            if(StringUtils.isNotEmpty(user.getBankno())) {
                ignoreProperties.add("bankno");
            }

            if(StringUtils.isNotEmpty(user.getAlipayacount())) {
                ignoreProperties.add("alipayacount");
            }

            BeanUtils.copyProperties(this, user, (String[])ignoreProperties.toArray(new String[0]));
            if(StringUtils.isNotEmpty(this.password)) {
                user.setPassword(Utils.convert2MD5(this.password));
            }

            user.setModifytime(new Date());
            user.setModifyuserno(Integer.valueOf(LoginManager.getLoginUser().getUserno()));
            this.userService.save(user);
            this.addActionMessage(this.getText("messages.save.success"));
            this.loadData();
            this.logger.debug("save normally end.");
            return "freemarker";
        }
    }
}
