//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yidu.novel.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.interceptor.PreResultListener;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.yidu.novel.action.base.AbstractPublicBaseAction;
import org.yidu.novel.action.base.AbstractUserBaseAction;
import org.yidu.novel.constant.YiDuConstants;

public class MobileInterceptor extends AbstractInterceptor {
    private static final long serialVersionUID = -8192961773909614182L;
    private static final String RESULT_CODE_SUFFIX_MOBILE = "mobile_";
    private final Log logger = LogFactory.getLog(this.getClass());
    private static final String[] MOBILE_BROWSER_UAS = new String[]{"iPhone OS", "Android", "BlackBerry", "Windows Phone"};

    public MobileInterceptor() {
    }

    public String intercept(ActionInvocation invocation) throws Exception {
        this.logger.debug("AuthCheckInterceptor start.");
        invocation.addPreResultListener(new PreResultListener() {
            public void beforeResult(ActionInvocation invocation, String resultCode) {
                if((invocation.getAction() instanceof AbstractPublicBaseAction || invocation.getAction() instanceof AbstractUserBaseAction) && (StringUtils.equalsIgnoreCase(resultCode, "freemarker") || StringUtils.equals(resultCode, "freemarker_error"))) {
                    boolean showMobileVersion = false;
                    if(YiDuConstants.yiduConf.getBoolean("judgmobilesitebydomian", false)) {
                        if(StringUtils.equals(ServletActionContext.getRequest().getServerName(), YiDuConstants.yiduConf.getString("mobilesitedomian"))) {
                            showMobileVersion = true;
                        }
                    } else {
                        String userAgent = ServletActionContext.getRequest().getHeader("User-Agent");
                        String[] var8;
                        int var7 = (var8 = MobileInterceptor.MOBILE_BROWSER_UAS).length;

                        for(int var6 = 0; var6 < var7; ++var6) {
                            String ua = var8[var6];
                            if(userAgent.toLowerCase().matches(".*" + ua.toLowerCase() + ".*")) {
                                showMobileVersion = true;
                                break;
                            }
                        }
                    }

                    if(showMobileVersion) {
                        invocation.setResultCode("mobile_" + resultCode);
                    }
                }

            }
        });
        this.logger.debug("AuthCheckInterceptor normally end.");
        return invocation.invoke();
    }
}
