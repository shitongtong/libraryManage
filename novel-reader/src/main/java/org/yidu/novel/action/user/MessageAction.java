//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yidu.novel.action.user;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import org.yidu.novel.action.base.AbstractUserBaseAction;
import org.yidu.novel.bean.MessageSearchBean;
import org.yidu.novel.entity.TMessage;
import org.yidu.novel.utils.LoginManager;

public class MessageAction extends AbstractUserBaseAction {
    private static final long serialVersionUID = 6707140588808286899L;
    private int messageno;
    private List<TMessage> messageList;

    public MessageAction() {
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

    public String getTempName() {
        return "user/message";
    }

    public int getPageType() {
        return 22;
    }

    protected void loadData() {
        MessageSearchBean searchBean = new MessageSearchBean();
        int userno = LoginManager.getLoginUser().getUserno();
        searchBean.setUserno(Integer.valueOf(userno));
        searchBean.setTouserno(Integer.valueOf(userno));
        this.messageList = this.messageService.find(searchBean);
    }

    @Transactional
    public String add() {
        return "freemarker_message";
    }

    @Transactional
    public String delete() {
        if(this.messageno != 0) {
            TMessage message = this.messageService.getByNo(this.messageno);
            if(message.getUserno().intValue() != LoginManager.getLoginUser().getUserno()) {
                this.addActionError(this.getText("errors.unauthority.message"));
                return "freemarker_error";
            }

            this.messageService.delteByNo(this.messageno);
        }

        this.loadData();
        return "input";
    }
}
