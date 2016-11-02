//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yidu.novel.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.yidu.novel.action.base.AbstractPublicBaseAction;

@Action("notFound")
public class NotFoundAction extends AbstractPublicBaseAction {
    private static final long serialVersionUID = -7303163605597104098L;
    public static final String NAME = "notFound";
    public static final String URL = "/notFound";

    public NotFoundAction() {
    }

    @SkipValidation
    public String execute() {
        this.logger.debug("execute start.");
        this.addActionError(this.getText("errors.not.exsits.page"));
        return "freemarker_error";
    }

    protected void loadData() {
    }

    public int getPageType() {
        return 99;
    }
}
