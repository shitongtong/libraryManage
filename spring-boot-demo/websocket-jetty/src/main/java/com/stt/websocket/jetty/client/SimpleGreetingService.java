package com.stt.websocket.jetty.client;

/**
 * Created by Administrator on 2017-02-16.
 *
 * @author shitongtong
 */
public class SimpleGreetingService implements GreetingService {
    @Override
    public String getGreeting() {
        return "Hello world!";
    }
}
