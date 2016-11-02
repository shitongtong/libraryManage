//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yidu.novel.action;

import org.yidu.novel.action.base.AbstractPublicBaseAction;
import org.yidu.novel.entity.TUser;
import org.yidu.novel.utils.LoginManager;

public class BookcaseAction extends AbstractPublicBaseAction {
    private static final long serialVersionUID = 366181195078436796L;

    public BookcaseAction() {
    }

    public String getTempName() {
        return "bookcase";
    }

    public int getPageType() {
        return 26;
    }

    public TUser getLoginUser() {
        return LoginManager.getLoginUser();
    }

    protected void loadData() {
    }
}
