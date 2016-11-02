//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yidu.novel.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.yidu.novel.action.base.AbstractAdminBaseAction;
import org.yidu.novel.action.base.AbstractBaseAction;

public class ErrorInterceptor extends AbstractInterceptor {
    private static final long serialVersionUID = 9085740143630189023L;
    private static final String UNKNOWN_ERROR_KEY = "errors.unknown";
    private final Log logger = LogFactory.getLog(this.getClass());

    public ErrorInterceptor() {
    }

    public String intercept(ActionInvocation invocation) throws Exception {
        try {
            String th = invocation.invoke();
            return th;
        } catch (Throwable var5) {
            AbstractBaseAction action = (AbstractBaseAction)invocation.getAction();
            this.logger.error(action, var5);
            String errorMsg = action.getText("errors.unknown");
            action.addActionError(errorMsg);
            return invocation.getAction() instanceof AbstractAdminBaseAction?"adminerror":"freemarker_error";
        }
    }
}
