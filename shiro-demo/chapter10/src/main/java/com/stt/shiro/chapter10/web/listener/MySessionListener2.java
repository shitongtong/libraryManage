package com.stt.shiro.chapter10.web.listener;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListenerAdapter;

/**
 * 如果只想监听某一个事件，可以继承SessionListenerAdapter实现：
 *
 * Created by Administrator on 2017-01-03.
 *
 * @author shitongtong
 */
public class MySessionListener2 extends SessionListenerAdapter{
    @Override
    public void onStart(Session session) {
        System.out.println("会话创建：" + session.getId());
    }
}
