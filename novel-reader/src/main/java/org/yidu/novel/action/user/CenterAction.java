//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yidu.novel.action.user;

import org.apache.struts2.convention.annotation.Action;
import org.yidu.novel.action.base.AbstractUserBaseAction;
import org.yidu.novel.entity.TUser;
import org.yidu.novel.utils.LoginManager;

@Action("center")
public class CenterAction extends AbstractUserBaseAction {
    private static final long serialVersionUID = 822196809678036074L;
    private TUser user;

    public CenterAction() {
    }

    public TUser getUser() {
        return this.user;
    }

    protected void loadData() {
        this.user = LoginManager.getLoginUser();
    }

    public int getPageType() {
        return 25;
    }

    public String getTempName() {
        return "user/center";
    }
}
