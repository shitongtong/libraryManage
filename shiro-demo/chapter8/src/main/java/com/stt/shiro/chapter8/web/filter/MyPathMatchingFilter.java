package com.stt.shiro.chapter8.web.filter;

import org.apache.shiro.web.filter.PathMatchingFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.Arrays;

/**
 *
 * 自定义拦截器：扩展PathMatchingFilter
 *
 * PathMatchingFilter继承了AdviceFilter，提供了url模式过滤的功能
 *
 * Created by Administrator on 2017-01-03.
 *
 * @author shitongtong
 */
public class MyPathMatchingFilter extends PathMatchingFilter{
    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        System.out.println("url matches,config is " + Arrays.toString((String[])mappedValue));
        return true;
    }
}
