package com.stt.xml.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017-02-15.
 *
 * @author shitongtong
 */
@Component
public class HelloWorldService {

    @Value("${name:World}")
    private String name;

    public String getHelloMessage() {
        return "Hello " + this.name;
    }

}
