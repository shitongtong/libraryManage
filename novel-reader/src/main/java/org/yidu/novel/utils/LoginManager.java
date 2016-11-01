//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yidu.novel.utils;

import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
//import org.yidu.novel.entity.TUser;

public class LoginManager {
    public LoginManager() {
    }

    private static final HttpSession getSession(boolean create) {
        return ServletActionContext.getRequest().getSession(create);
    }

//    public static final TUser getLoginUser() {
//        HttpSession session = getSession(false);
//        return session != null?(TUser)session.getAttribute("yidu.Novel.LoginUser"):null;
//    }
//
//    public static final boolean isLoginFlag() {
//        return getLoginUser() != null;
//    }
//
//    public static final void doLogin(TUser user) {
//        HttpSession session = getSession(true);
//        session.invalidate();
//        session = getSession(true);
//        session.setAttribute("yidu.Novel.LoginUser", user);
//    }

    public static final void doLogout() {
        HttpSession session = getSession(true);
        session.setAttribute("yidu.Novel.LoginUser", (Object)null);
    }
}
