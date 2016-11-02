//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yidu.novel.action.base;

import org.yidu.novel.action.base.AbstractAdminBaseAction;
import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.utils.Pagination;

public abstract class AbstractAdminListBaseAction extends AbstractAdminBaseAction {
    private static final long serialVersionUID = 5250455993870220163L;
    protected Pagination pagination;

    public AbstractAdminListBaseAction() {
        this.pagination = new Pagination(YiDuConstants.yiduConf.getInt("numberPerPage"), 1);
    }

    public Pagination getPagination() {
        return this.pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }
}
