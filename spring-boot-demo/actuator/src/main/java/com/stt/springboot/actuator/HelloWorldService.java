package com.stt.springboot.actuator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017-02-07.
 *
 * @author shitongtong
 */
@Service
public class HelloWorldService {

    @Autowired
    private ServiceProperties configuration;

    public String getHelloMessage(){
        return "Hello " + this.configuration.getName();
    }
}
