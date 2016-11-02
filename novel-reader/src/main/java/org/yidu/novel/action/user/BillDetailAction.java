//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yidu.novel.action.user;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.yidu.novel.action.base.AbstractUserBaseAction;

@Action("billdetail")
public class BillDetailAction extends AbstractUserBaseAction {
    private static final long serialVersionUID = -2984522801349519469L;
    private int articleno;

    public BillDetailAction() {
    }

    public int getArticleno() {
        return this.articleno;
    }

    public void setArticleno(int articleno) {
        this.articleno = articleno;
    }

    public int getPageType() {
        return 40;
    }

    @SkipValidation
    public String execute() {
        this.logger.debug("execute start.");
        if(this.articleno != 0) {
            this.articleService.updateVoteStatistic(this.articleno);
            this.logger.debug("execute normally start.");
            return "freemarker";
        } else {
            this.addActionError(this.getText("errors.not.exsits.article"));
            return "freemarker_error";
        }
    }

    protected void loadData() {
    }

    public String getTempName() {
        return "billdetail";
    }
}
