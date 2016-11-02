//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yidu.novel.action;

import com.opensymphony.xwork2.ActionSupport;
import java.io.ByteArrayInputStream;
import java.util.Map;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.SessionAware;
import org.yidu.novel.utils.SecurityCode;
import org.yidu.novel.utils.SecurityImage;

@Results({    @Result(
        name = "success",
        type = "stream",
        params = {"contentType", "image/jpeg", "inputName", "imageStream", "bufferSize", "4096"}
)})
public class SecurityCodeImageAction extends ActionSupport implements SessionAware {
    private static final long serialVersionUID = 1496691731440581303L;
    private ByteArrayInputStream imageStream;
    private Map<String, Object> session;

    public SecurityCodeImageAction() {
    }

    public ByteArrayInputStream getImageStream() {
        return this.imageStream;
    }

    public void setImageStream(ByteArrayInputStream imageStream) {
        this.imageStream = imageStream;
    }

    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    @Action("imagecode")
    public String execute() throws Exception {
        String securityCode = SecurityCode.getSecurityCode();
        this.imageStream = SecurityImage.getImageAsInputStream(securityCode);
        this.session.put("securityCode", securityCode);
        return "success";
    }
}
