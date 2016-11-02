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
import org.yidu.novel.bean.MessageSearchBean;
import org.yidu.novel.entity.TMessage;
import org.yidu.novel.utils.LoginManager;

@Action("messageList")
public class MessageListAction extends AbstractAdminListBaseAction {
    private static final long serialVersionUID = -5969611068273330970L;
    public static final String NAME = "messageList";
    public static final String URL = "/admin/messageList";
    private int messageno;
    private List<TMessage> messageList = new ArrayList();

    public MessageListAction() {
    }

    public int getMessageno() {
        return this.messageno;
    }

    public void setMessageno(int messageno) {
        this.messageno = messageno;
    }

    public List<TMessage> getMessageList() {
        return this.messageList;
    }

    public void setMessageList(List<TMessage> messageList) {
        this.messageList = messageList;
    }

    protected void loadData() {
        this.initCollections(new String[]{"collectionProperties.message.isread"});
        if(StringUtils.isEmpty(this.pagination.getSortColumn())) {
            this.pagination.setSortColumn("postdate");
            this.pagination.setSortOrder("DESC");
        }

        MessageSearchBean searchBean = new MessageSearchBean();
        this.pagination.setPreperties(this.messageService.getCount(searchBean));
        searchBean.setPagination(this.pagination);
        this.messageList = this.messageService.find(searchBean);
        this.pagination.setPageRecords(this.messageList.size());
    }

    public String delete() throws Exception {
        TMessage message = this.messageService.getByNo(this.messageno);
        message.setDeleteflag(Boolean.valueOf(true));
        message.setModifyuserno(Integer.valueOf(LoginManager.getLoginUser().getUserno()));
        message.setModifytime(new Date());
        this.messageService.save(message);
        this.loadData();
        return "input";
    }
}
