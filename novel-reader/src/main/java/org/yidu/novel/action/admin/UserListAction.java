//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yidu.novel.action.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.yidu.novel.action.base.AbstractAdminListBaseAction;
import org.yidu.novel.bean.UserSearchBean;
import org.yidu.novel.entity.TUser;
import org.yidu.novel.utils.LoginManager;

@Action("userList")
public class UserListAction extends AbstractAdminListBaseAction {
    private static final long serialVersionUID = 8182483310788301445L;
    public static final String NAME = "userList";
    public static final String URL = "/admin/userList";
    private int userno;
    private List<TUser> userList = new ArrayList();

    public UserListAction() {
    }

    public int getUserno() {
        return this.userno;
    }

    public void setUserno(int userno) {
        this.userno = userno;
    }

    public List<TUser> getUserList() {
        return this.userList;
    }

    public void setUserList(List<TUser> userList) {
        this.userList = userList;
    }

    protected void loadData() {
        this.initCollections(new String[]{"collectionProperties.user.sex", "collectionProperties.user.type"});
        UserSearchBean searchBean = new UserSearchBean();
        if(StringUtils.isEmpty(this.pagination.getSortColumn())) {
            this.pagination.setSortColumn("userno");
        }

        searchBean.setPagination(this.pagination);
        this.pagination.setPreperties(this.userService.getCount(searchBean));
        this.userList = this.userService.find(searchBean);
        this.pagination.setPageRecords(this.userList.size());
    }

    public String delete() throws Exception {
        this.initCollections(new String[]{"collectionProperties.user.sex", "collectionProperties.user.type"});
        TUser user = this.userService.getByNo(this.userno);
        user.setDeleteflag(Boolean.valueOf(true));
        user.setModifyuserno(Integer.valueOf(LoginManager.getLoginUser().getUserno()));
        user.setModifytime(new Date());
        this.userService.save(user);
        this.loadData();
        return "input";
    }
}
