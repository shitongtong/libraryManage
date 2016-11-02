//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yidu.novel.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.yidu.novel.action.base.AbstractAdminBaseAction;

public class SetLanguageInterceptor extends AbstractInterceptor {
    private static final long serialVersionUID = 7671199059328089342L;
    private final Log logger = LogFactory.getLog(this.getClass());
    public static final String ADMIN_PAGE_LOCALE = "WW_TRANS_I18N_LOCALE";

    public SetLanguageInterceptor() {
    }

    public String intercept(ActionInvocation invocation) throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession(true);
        Locale locale = (Locale)session.getAttribute("WW_TRANS_I18N_LOCALE");
        if(locale == null) {
            if(!request.getLocale().equals(Locale.CHINA) && !request.getLocale().equals(Locale.CHINESE)) {
                if(!request.getLocale().equals(Locale.JAPAN) && !request.getLocale().equals(Locale.JAPANESE)) {
                    session.setAttribute("WW_TRANS_I18N_LOCALE", Locale.US);
                    this.logger.debug("Language is set to English.from IP <" + request.getRemoteAddr() + ">");
                } else {
                    session.setAttribute("WW_TRANS_I18N_LOCALE", Locale.JAPAN);
                    this.logger.debug("Language is set to Japanese. from IP <" + request.getRemoteAddr() + ">");
                }
            } else {
                session.setAttribute("WW_TRANS_I18N_LOCALE", Locale.CHINA);
                this.logger.debug("Language is set to Chinese. from IP <" + request.getRemoteAddr() + ">");
            }
        }

        if(invocation.getAction() instanceof AbstractAdminBaseAction && (locale == null || !locale.equals(Locale.CHINA))) {
            session.setAttribute("WW_TRANS_I18N_LOCALE", Locale.CHINA);
            this.logger.debug("because access page is admin page. Language is set to Chinese. from IP <" + request.getRemoteAddr() + ">");
        }

        return invocation.invoke();
    }
}
