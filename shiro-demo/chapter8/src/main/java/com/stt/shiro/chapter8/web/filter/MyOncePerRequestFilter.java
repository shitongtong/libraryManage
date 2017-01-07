package com.stt.shiro.chapter8.web.filter;

import org.apache.shiro.web.servlet.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * 自定义拦截器：扩展OncePerRequestFilter
 *
 * OncePerRequestFilter保证一次请求只调用一次doFilterInternal，即如内部的forward不会再多执行一次
 *
 * Created by Administrator on 2017-01-03.
 *
 * @author shitongtong
 */
public class MyOncePerRequestFilter extends OncePerRequestFilter{
    @Override
    protected void doFilterInternal(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("=========once per request filter");
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
