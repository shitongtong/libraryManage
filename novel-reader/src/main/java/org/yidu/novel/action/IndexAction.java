//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yidu.novel.action;

import org.yidu.novel.action.base.AbstractPublicBaseAction;

public class IndexAction extends AbstractPublicBaseAction {
    private static final long serialVersionUID = -5991997032217966675L;
    public static final String NAME = "index";
    public static final String URL = "/index";

    public IndexAction() {
        logger.debug("entry constructor method IndexAction()");
    }

    public String getTempName() {
        logger.debug("entry method getTempName()");
        return "index";
    }

    public void loadData() {
        logger.debug("entry method loadData()");
    }

    public int getPageType() {
        logger.debug("entry method getPageType()");
        return 1;
    }

}
