//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yidu.novel.action.base;

import org.yidu.novel.action.base.AbstractUserBaseAction;
import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.utils.Pagination;

public abstract class AbstractUserListBaseAction extends AbstractUserBaseAction {
    private static final long serialVersionUID = 3736781138346525060L;
    protected Pagination pagination;

    public AbstractUserListBaseAction() {
        this.pagination = new Pagination(YiDuConstants.yiduConf.getInt("countPerPage"), 1);
    }

    public Pagination getPagination() {
        return this.pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }
}
