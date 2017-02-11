package com.stt.aop;

import com.stt.aop.service.HelloWorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by Administrator on 2017-02-11.
 *
 * @author shitongtong
 */
@SpringBootApplication
public class SampleAopApplication implements CommandLineRunner {

    // Simple example shows how an application can spy on itself with AOP

    @Autowired
    private HelloWorldService helloWorldService;

    @Override
    public void run(String... strings) throws Exception {
        System.out.println(this.helloWorldService.getHelloMessage());
    }

    public static void main(String[] args) {
        SpringApplication.run(SampleAopApplication.class, args);
    }
}
