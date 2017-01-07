package com.stt.shiro.chapter8.web.filter;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * 自定义拦截器：任意角色授权拦截器
 *
 * Shiro提供roles拦截器，其验证用户拥有所有角色，没有提供验证用户拥有任意角色的拦截器
 *
 * 流程：
 1、首先判断用户有没有任意角色，如果没有返回false，将到onAccessDenied进行处理；
 2、如果用户没有角色，接着判断用户有没有登录，如果没有登录先重定向到登录；
 3、如果用户没有角色且设置了未授权页面（unauthorizedUrl），那么重定向到未授权页面；否则直接返回401未授权错误码。
 *
 * Created by Administrator on 2017-01-03.
 *
 * @author shitongtong
 */
public class AnyRolesFilter extends AccessControlFilter{

    private String unauthorizedUrl = "/unauthorized.jsp";
    private String loginUrl = "/login.jsp";

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {

        String[] roles = (String[]) o;
        if (roles == null) {
            return true;//如果没有设置角色参数，默认成功
        }
        for (String role : roles) {
            if (getSubject(servletRequest,servletResponse).hasRole(role)){
                return true;
            }
        }

        return false;//跳到onAccessDenied处理
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {

        Subject subject = getSubject(servletRequest, servletResponse);
        if (subject.getPrincipal() == null){//表示没有登录，重定向到登录页面
            saveRequest(servletRequest);
            WebUtils.issueRedirect(servletRequest,servletResponse,loginUrl);
        }else {
            if (StringUtils.hasText(unauthorizedUrl)){//如果有未授权页面跳转过去
                WebUtils.issueRedirect(servletRequest,servletResponse,unauthorizedUrl);
            }else{//否则返回401未授权状态码
                WebUtils.toHttp(servletResponse).sendError(HttpServletResponse.SC_UNAUTHORIZED);
            }
        }

        return false;
    }
}
