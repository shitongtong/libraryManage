package com.stt.shiro.chapter10.web.listener;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

/**
 * 会话监听器用于监听会话创建、过期及停止事件：
 *
 * Created by Administrator on 2017-01-03.
 *
 * @author shitongtong
 */
public class MySessionListener1 implements SessionListener{
    @Override
    public void onStart(Session session) {//会话创建时触发
        System.out.println("会话创建：" + session.getId());
    }

    @Override
    public void onStop(Session session) {//会话过期时触发
        System.out.println("会话过期：" + session.getId());
    }

    @Override
    public void onExpiration(Session session) {//退出/会话过期时触发
        System.out.println("会话停止：" + session.getId());
    }
}
