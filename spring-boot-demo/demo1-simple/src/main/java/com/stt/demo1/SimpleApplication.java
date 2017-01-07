package com.stt.demo1;

import com.stt.demo1.domain.ConnectionProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.boot.web.servlet.ErrorPageRegistrar;
import org.springframework.boot.web.servlet.ErrorPageRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017-01-04.
 *
 * @author shitongtong
 */
@RestController
//@Configuration
//@EnableAutoConfiguration
//@ComponentScan
@SpringBootApplication // same as @Configuration @EnableAutoConfiguration @ComponentScan
//@EnableConfigurationProperties(ConnectionProperties.class)
public class SimpleApplication {

    @Autowired
    private ConnectionProperties connectionProperties;

    @RequestMapping("/")
    @ResponseBody
    ConnectionProperties home() {
        return connectionProperties;
    }

    public static void main(String[] args) {
//        SpringApplication.run(SimpleApplication.class, args);
        SpringApplication application = new SpringApplication(SimpleApplication.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);

//        new SpringApplicationBuilder().bannerMode(Banner.Mode.OFF).parent(parent.class).child(child.class);
    }

    @Bean
    public ErrorPageRegistrar errorPageRegistrar(){
        return new MyErrorPageRegistrar();
    }

// ...

    private static class MyErrorPageRegistrar implements ErrorPageRegistrar {

        @Override
        public void registerErrorPages(ErrorPageRegistry registry) {
            registry.addErrorPages(new ErrorPage(HttpStatus.BAD_REQUEST, "/400"));
        }

    }
}
