//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yidu.novel.result;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.Result;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.yidu.novel.action.ImageAction;

public class CustomImageBytesResult implements Result {
    private static final long serialVersionUID = -4829552280917099091L;
    private final Log logger = LogFactory.getLog(this.getClass());

    public CustomImageBytesResult() {
    }

    public void execute(ActionInvocation invocation) throws Exception {
        this.logger.debug("execute(10) start.");
        ImageAction action = (ImageAction)invocation.getAction();
        byte[] binaryinfo = action.getGraph();

        try {
            if(binaryinfo != null) {
                HttpServletResponse e = ServletActionContext.getResponse();
                e.setContentType(action.getCustomContentType());
                e.getOutputStream().write(binaryinfo);
                e.getOutputStream().flush();
                this.logger.debug("execute(99) normally end.");
            } else {
                this.logger.debug("execute(80) binaryinfo is null.");
                this.logger.debug("execute(89) abnormally end.");
            }
        } catch (Exception var5) {
            this.logger.debug("execute(79) abnormally end by ClientAbortException.");
        }

    }
}
