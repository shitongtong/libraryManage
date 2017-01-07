package com.stt.shiro.chapter8.web.filter;

import org.apache.shiro.web.servlet.AdviceFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 *
 * 自定义拦截器：扩展AdviceFilter
 *
 * AdviceFilter提供了AOP的功能，其实现和SpringMVC中的Interceptor思想一样
 *
 * Created by Administrator on 2017-01-03.
 *
 * @author shitongtong
 */
public class MyAdviceFilter extends AdviceFilter{
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        System.out.println("====预处理/前置处理");
        return true;//返回false将中断后续拦截器链的执行
    }

    @Override
    protected void postHandle(ServletRequest request, ServletResponse response) throws Exception {
        System.out.println("====后处理/后置返回处理");
    }

    @Override
    public void afterCompletion(ServletRequest request, ServletResponse response, Exception exception) throws Exception {
        System.out.println("====完成处理/后置最终处理");
    }
}
