//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yidu.novel.action.admin;

import java.util.Date;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.BeanUtils;
import org.yidu.novel.action.base.AbstractAdminEditBaseAction;
import org.yidu.novel.entity.TUser;
import org.yidu.novel.utils.LoginManager;
import org.yidu.novel.utils.Utils;

@Action("userEdit")
@Result(
        name = "redirect",
        type = "redirect",
        location = "/admin/userList"
)
public class UserEditAction extends AbstractAdminEditBaseAction {
    private static final long serialVersionUID = 8182483310788301445L;
    public static final String NAMESPACE = "/admin/user";
    public static final String NAME = "edit";
    public static final String URL = "/admin/user/edit";
    private int userno;
    private String loginid;
    private String password;
    private String username;
    private String email;
    private Date regdate;
    private Short sex;
    private String qq;
    private Short type;
    private Date lastlogin;
    private String realname;
    private String id;
    private String mobileno;
    private String branch;
    private String bankno;
    private String alipayacount;

    public UserEditAction() {
    }

    public int getUserno() {
        return this.userno;
    }

    public void setUserno(int userno) {
        this.userno = userno;
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

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getRegdate() {
        return this.regdate;
    }

    public void setRegdate(Date regdate) {
        this.regdate = regdate;
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

    public void setQq(String qq) {
        this.qq = qq;
    }

    public Short getType() {
        return this.type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public Date getLastlogin() {
        return this.lastlogin;
    }

    public void setLastlogin(Date lastlogin) {
        this.lastlogin = lastlogin;
    }

    public String getRealname() {
        return this.realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMobileno() {
        return this.mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String getBranch() {
        return this.branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getBankno() {
        return this.bankno;
    }

    public void setBankno(String bankno) {
        this.bankno = bankno;
    }

    public String getAlipayacount() {
        return this.alipayacount;
    }

    public void setAlipayacount(String alipayacount) {
        this.alipayacount = alipayacount;
    }

    protected void loadData() {
        this.logger.debug("loadData start.");
        this.initCollections(new String[]{"collectionProperties.user.sex", "collectionProperties.user.type"});
        if(this.userno != 0) {
            TUser user = this.userService.getByNo(this.userno);
            BeanUtils.copyProperties(user, this);
        }

        this.logger.debug("loadData normally end.");
    }

    public String save() {
        this.logger.debug("save start.");
        TUser user = new TUser();
        if(this.userno != 0) {
            user = this.userService.getByNo(this.userno);
        } else {
            user.setRegdate(new Date());
            user.setDeleteflag(Boolean.valueOf(false));
        }

        BeanUtils.copyProperties(this, user, new String[]{"regdate", "lastlogin", "password"});
        if(StringUtils.isNotEmpty(this.password)) {
            user.setPassword(Utils.convert2MD5(this.password));
        }

        user.setModifytime(new Date());
        user.setModifyuserno(Integer.valueOf(LoginManager.getLoginUser().getUserno()));
        this.userService.save(user);
        this.logger.debug("save normally end.");
        return "redirect";
    }
}
