package com.stt.shiro.chapter8.web.filter;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * 基于表单登录拦截器
 *
 * onPreHandle主要流程：

 1、首先判断是否已经登录过了，如果已经登录过了继续拦截器链即可；

 2、如果没有登录，看看是否是登录请求，如果是get方法的登录页面请求，则继续拦截器链（到请求页面），
 否则如果是get方法的其他页面请求则保存当前请求并重定向到登录页面；

 3、如果是post方法的登录页面表单提交请求，则收集用户名/密码登录即可，如果失败了保存错误消息到“shiroLoginFailure”并返回到登录页面；

 4、如果登录成功了，且之前有保存的请求，则重定向到之前的这个请求，否则到默认的成功页面。
 *
 * Created by Administrator on 2017-01-03.
 *
 * @author shitongtong
 */
public class FormLoginFilter extends PathMatchingFilter{

    private String loginUrl = "/login.jsp";

    private String successUrl = "/";

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {

        if (SecurityUtils.getSubject().isAuthenticated()){//已经登录过
            return true;
        }
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        //1、首先判断是否已经登录过了，如果已经登录过了继续拦截器链即可；
        if(isLoginRequest(req)){
            if ("post".equalsIgnoreCase(req.getMethod())){//form表单提交
                boolean loginSuccess  = login(req);//登录
                if (loginSuccess){
                    return redirectToSuccessUrl(req,resp);
                }
            }
            return true;//继续过滤器链
        }else{//保存当前地址并重定向到登录界面

        }

        return super.onPreHandle(request, response, mappedValue);
    }

    private boolean redirectToSuccessUrl(HttpServletRequest request,HttpServletResponse response) throws IOException {
        WebUtils.redirectToSavedRequest(request,response,successUrl);
        return false;
    }

    private boolean login(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            SecurityUtils.getSubject().login(new UsernamePasswordToken(username,password));
        } catch (AuthenticationException e) {
            e.printStackTrace();
            request.setAttribute("shiroLoginFailure",e.getClass());
            return false;
        }
        return true;
    }

    private void saveRequestAndRedirectToLogin(HttpServletRequest request,HttpServletResponse response) throws IOException {
        WebUtils.saveRequest(request);
        WebUtils.issueRedirect(request,response,loginUrl);
    }

    private boolean isLoginRequest(HttpServletRequest request){
        return pathsMatch(loginUrl, WebUtils.getPathWithinApplication(request));
    }

}
