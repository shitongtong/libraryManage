package com.stt.actuator.ui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.Map;

/**
 * Created by Administrator on 2017-02-08.
 *
 * @author shitongtong
 */
@SpringBootApplication
@Controller
public class SampleActuatorUiApplication {
    @GetMapping("/")
    public String home(Map<String, Object> model) {
        model.put("message", "Hello World");
        model.put("title", "Hello Home");
        model.put("date", new Date());
        return "home";
    }

    @RequestMapping("/foo")
    public String foo() {
        throw new RuntimeException("Expected exception in controller");
    }

    @Bean
    public SecurityProperties securityProperties() {
        SecurityProperties security = new SecurityProperties();
        security.getBasic().setPath(""); // empty so home page is insecured
        return security;
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SampleActuatorUiApplication.class, args);
    }
}
