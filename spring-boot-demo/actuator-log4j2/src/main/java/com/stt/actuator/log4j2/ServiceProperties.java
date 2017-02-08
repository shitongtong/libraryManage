package com.stt.actuator.log4j2;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017-02-08.
 *
 * @author shitongtong
 */
@ConfigurationProperties(prefix = "service", ignoreUnknownFields = false)
@Component
public class ServiceProperties {
    private String name = "World";

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
