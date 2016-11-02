//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yidu.novel.action.base;

import org.yidu.novel.action.base.AbstractBaseAction;
import org.yidu.novel.bean.ResponseBean;

public abstract class JsonBaseAction extends AbstractBaseAction {
    private static final long serialVersionUID = 1L;
    protected ResponseBean<?> res;

    public JsonBaseAction() {
    }

    public String execute() {
        this.res = this.loadJsonData();
        return "json";
    }

    protected abstract ResponseBean<?> loadJsonData();

    public ResponseBean<?> getData() {
        return this.res;
    }
}
