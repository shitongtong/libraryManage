package com.stt.demo1.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.net.InetAddress;

/**
 * Created by Administrator on 2017-01-04.
 *
 * @author shitongtong
 */
@Component
@ConfigurationProperties(prefix = "connection")
public class ConnectionProperties {
//    @Value("${connection.username}")
    private String username;
//    @Value("${connection.remoteAddress}")
    private InetAddress remoteAddress;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public InetAddress getRemoteAddress() {
        return remoteAddress;
    }

    public void setRemoteAddress(InetAddress remoteAddress) {
        this.remoteAddress = remoteAddress;
    }

}
