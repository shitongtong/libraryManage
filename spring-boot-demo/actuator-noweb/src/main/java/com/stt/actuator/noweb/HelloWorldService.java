package com.stt.actuator.noweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017-02-08.
 *
 * @author shitongtong
 */
@Component
public class HelloWorldService {
    @Autowired
    private ServiceProperties configuration;

    public String getHelloMessage() {
        return "Hello " + this.configuration.getName();
    }
}
