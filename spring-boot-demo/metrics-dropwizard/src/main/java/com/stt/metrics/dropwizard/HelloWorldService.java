package com.stt.metrics.dropwizard;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017-02-14.
 *
 * @author shitongtong
 */
@Component
@ConfigurationProperties(prefix = "service",ignoreUnknownFields = false)
public class HelloWorldService {

    private String name = "World";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHelloMessage(){
        return "Hello " + this.name;
    }
}
