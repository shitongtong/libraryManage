//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yidu.novel.action.base;

import org.apache.struts2.interceptor.validation.SkipValidation;
import org.yidu.novel.action.base.AbstractPublicAndUserBaseAction;
import org.yidu.novel.entity.TArticle;
import org.yidu.novel.entity.TUser;
import org.yidu.novel.utils.LoginManager;

public abstract class AbstractUserBaseAction extends AbstractPublicAndUserBaseAction {
    private static final long serialVersionUID = 4900892616460135567L;
    public static final String NAMESPACE = "/user";

    public AbstractUserBaseAction() {
    }

    @SkipValidation
    public String execute() {
        this.logger.debug("execute start.");
        this.loadData();
        if(this.hasErrors()) {
            this.logger.debug("execute abnormally end.");
            this.setHasError(true);
            return "freemarker_error";
        } else {
            this.logger.debug("execute normally end.");
            return "freemarker";
        }
    }

    public abstract int getPageType();

    public abstract String getTempName();

    public TUser getUser() {
        return LoginManager.getLoginUser();
    }

    protected boolean checkRight(TArticle article) {
        boolean hasRihgtFlag = false;
        TUser user = LoginManager.getLoginUser();
        if(user.getType().shortValue() == 20 && article.getAuthorid().intValue() == user.getUserno()) {
            hasRihgtFlag = true;
        }

        if(user.getType().shortValue() == 40 && article.getCategory().intValue() == user.getUserno()) {
            hasRihgtFlag = true;
        }

        return hasRihgtFlag;
    }
}
