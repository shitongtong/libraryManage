package com.stt.shiro.chapter8.web.filter;

import org.apache.shiro.web.filter.AccessControlFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 *
 * 自定义拦截器：扩展AccessControlFilter
 *
 * AccessControlFilter继承了PathMatchingFilter，并扩展了了两个方法：
 * isAccessAllowed：即是否允许访问，返回true表示允许；
 * onAccessDenied：表示访问拒绝时是否自己处理，如果返回true表示自己不处理且继续拦截器链执行，
 * 返回false表示自己已经处理了（比如重定向到另一个页面）。
 *
 * Created by Administrator on 2017-01-03.
 *
 * @author shitongtong
 */
public class MyAccessControlFilter extends AccessControlFilter{
    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        System.out.println("access allowed");
        return true;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        System.out.println("访问拒绝也不自己处理，继续拦截器链的执行");
        return true;
    }
}
