//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yidu.novel.action.base;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.ValidationWorkflowAware;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.collections.map.LinkedMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.yidu.novel.service.ArticleService;
import org.yidu.novel.service.SystemBlockService;

import javax.servlet.ServletContext;
//import org.yidu.novel.service.ArticleService;
//import org.yidu.novel.service.BookcaseService;
//import org.yidu.novel.service.ChapterService;
//import org.yidu.novel.service.MessageService;
//import org.yidu.novel.service.SystemBlockService;
//import org.yidu.novel.service.SystemConfigService;
//import org.yidu.novel.service.UserService;

@Results({    @Result(
        name = "freemarker_error",
        location = "/themes/${themeName}/error.ftl",
        type = "freemarker"
),     @Result(
        name = "freemarker_message",
        type = "freemarker",
        location = "/themes/${themeName}/message.ftl"
),     @Result(
        name = "freemarker",
        type = "freemarker",
        location = "/themes/${themeName}/${tempName}.ftl"
),     @Result(
        name = "mobile_freemarker_error",
        location = "/themes/${themeName}/mobile/error.ftl",
        type = "freemarker"
),     @Result(
        name = "mobile_freemarker_message",
        type = "freemarker",
        location = "/themes/${themeName}/mobile/message.ftl"
),     @Result(
        name = "mobile_freemarker",
        type = "freemarker",
        location = "/themes/${themeName}/mobile/${tempName}.ftl"
),     @Result(
        name = "adminerror",
        location = "/WEB-INF/error.jsp",
        type = "dispatcher"
),     @Result(
        name = "json",
        type = "json"
),     @Result(
        name = "GOTO_Top",
        location = "/index",
        type = "redirect"
),     @Result(
        name = "GOTO_Login",
        location = "/login",
        type = "redirect"
),     @Result(
        name = "redirect",
        location = "${backUrl}",
        type = "redirect"
)})
public abstract class AbstractBaseAction extends ActionSupport implements ValidationWorkflowAware {
    private static final long serialVersionUID = 1L;
    protected static final String JSON_RESULT = "json";
    public static final String GO_TOP = "GOTO_Top";
    public static final String GOTO_LOGIN = "GOTO_Login";
    public static final String REDIRECT = "redirect";
    public static final String MESSAGE = "message";
    public static final String ADMIN_ERROR = "adminerror";
    public static final String FREEMARKER = "freemarker";
    public static final String FREEMARKER_ERROR = "freemarker_error";
    public static final String FREEMARKER_MESSAGE = "freemarker_message";
    public static final String MOBILE_FREEMARKER = "mobile_freemarker";
    public static final String MOBILE_FREEMARKER_ERROR = "mobile_freemarker_error";
    public static final String MOBILE_FREEMARKER_MESSAGE = "mobile_freemarker_message";
    protected Log logger = LogFactory.getLog(this.getClass());
//    protected UserService userService;
    protected ArticleService articleService;
//    protected ChapterService chapterService;
//    protected BookcaseService bookcaseService;
    protected SystemBlockService systemBlockService;
//    protected SystemConfigService systemConfigService;
//    protected MessageService messageService;
    protected Map<String, LinkedMap> collections = new HashMap();

    public AbstractBaseAction() {
        logger.debug("entry constructor method: AbstractBaseAction()");
    }

    @Autowired
    public void setSystemBlockService(SystemBlockService systemBlockService) {
        this.systemBlockService = systemBlockService;
    }
//
//    @Autowired
//    public void setSystemConfigService(SystemConfigService systemConfigService) {
//        this.systemConfigService = systemConfigService;
//    }
//
//    @Autowired
//    public void setUserService(UserService userService) {
//        this.userService = userService;
//    }
//
    @Autowired
    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }
//
//    @Autowired
//    public void setChapterService(ChapterService chapterService) {
//        this.chapterService = chapterService;
//    }
//
//    @Autowired
//    public void setBookcaseService(BookcaseService bookcaseService) {
//        this.bookcaseService = bookcaseService;
//    }
//
//    @Autowired
//    public void setMessageService(MessageService messageService) {
//        this.messageService = messageService;
//    }

    public String getInputResultName() {
        logger.debug("entry method: getInputResultName()");
        return "input";
    }

    public String getContextPath() {
        logger.debug("entry method: getContextPath()");
        /*ServletContext servletContext = ServletActionContext.getServletContext();
        try {
            URL resource = servletContext.getResource("/");
            String path = resource.getPath();
            System.out.println("path::"+path);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        String s = ServletActionContext.getRequest().getRequestURL().toString();
        System.out.println("s::"+s);
        String realPath = servletContext.getRealPath("/");
        System.out.println("realPath::"+realPath);
        servletContext.getContext("/").getServerInfo();
        return ServletActionContext.getServletContext().getContextPath();*/

        String contextPath = ServletActionContext.getRequest().getContextPath();
//        System.out.println("contextPath::"+contextPath);
        logger.debug("contextPath :: "+contextPath);
        return contextPath;
    }

    public String getRequestUrl() {
        logger.debug("entry method: getRequestUrl()");
        String requestURL = ServletActionContext.getRequest().getRequestURL().toString();
        logger.debug("requestURL::"+requestURL);
        return requestURL;
    }

    @SkipValidation
    public String back() throws Exception {
        logger.debug("entry method: back()");
        return "redirect";
    }

    public Map<String, LinkedMap> getCollections() {
        logger.debug("entry method: getCollections()");
        logger.debug("this.collections::"+this.collections);
        return this.collections;
    }

    public void initCollections(String[] keys) {
        logger.debug("entry method: initCollections(String[] keys) keys:"+keys);
        String[] var5 = keys;
        int var4 = keys.length;

        for(int var3 = 0; var3 < var4; ++var3) {
            String key = var5[var3];
            LinkedMap pulldown = new LinkedMap();
            String value = this.getText(key);
            String[] items = value.split(",");
            String[] var12 = items;
            int var11 = items.length;

            for(int var10 = 0; var10 < var11; ++var10) {
                String item = var12[var10];
                String[] property = item.split(":");
                if(property.length == 2) {
                    pulldown.put(property[0], property[1]);
                }
            }

            this.collections.put(key, pulldown);
        }

    }
}
