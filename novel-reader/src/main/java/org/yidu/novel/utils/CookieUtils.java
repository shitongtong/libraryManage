//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yidu.novel.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
//import org.yidu.novel.entity.TUser;
//import org.yidu.novel.service.UserService;
import org.yidu.novel.utils.LoginManager;

public class CookieUtils {
    protected static Log logger = LogFactory.getLog(CookieUtils.class);
    public static final String USER_COOKIE = "user.cookie";
    public static final String READ_HISTORY_COOKIE = "read.history.cookie";

    public CookieUtils() {
    }

//    public static Cookie addUserCookie(TUser user) {
//        try {
//            Cookie e = new Cookie("user.cookie", URLEncoder.encode(user.getLoginid(), "UTF-8") + "," + user.getPassword());
//            e.setMaxAge(1209600);
//            return e;
//        } catch (UnsupportedEncodingException var2) {
//            logger.error(var2);
//            return null;
//        }
//    }
//
//    public static void getUserCookieAndLogoin(HttpServletRequest request, UserService userService) {
//        Cookie[] cookies = request.getCookies();
//        if(cookies != null) {
//            Cookie[] var6 = cookies;
//            int var5 = cookies.length;
//
//            for(int var4 = 0; var4 < var5; ++var4) {
//                Cookie cookie = var6[var4];
//                if("user.cookie".equals(cookie.getName())) {
//                    String value = cookie.getValue();
//                    if(StringUtils.isNotBlank(value)) {
//                        String[] split = value.split(",");
//                        if(split.length == 2) {
//                            String loginid = split[0];
//                            String password = split[1];
//                            TUser user = userService.findByLoginInfoByJDBC(loginid, password);
//                            if(user != null) {
//                                LoginManager.doLogin(user);
//                                userService.updateLastLoginDate(user.getUserno(), new Date());
//                            }
//                        }
//                    }
//                }
//            }
//        }
//
//    }

    public static Cookie delUserCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            Cookie[] var5 = cookies;
            int var4 = cookies.length;

            for(int var3 = 0; var3 < var4; ++var3) {
                Cookie cookie = var5[var3];
                if("user.cookie".equals(cookie.getName())) {
                    cookie.setValue("");
                    cookie.setMaxAge(0);
                    return cookie;
                }
            }
        }

        return null;
    }

    public static Cookie addHistoryCookie(String articlenos) {
        Cookie cookie = new Cookie("read.history.cookie", articlenos);
        cookie.setMaxAge(31536000);
        cookie.setPath("/");
        return cookie;
    }

    public static String getHistoryCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            Cookie[] var5 = cookies;
            int var4 = cookies.length;

            for(int var3 = 0; var3 < var4; ++var3) {
                Cookie cookie = var5[var3];
                if("read.history.cookie".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }

        return null;
    }
}
