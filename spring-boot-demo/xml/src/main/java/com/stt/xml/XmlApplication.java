package com.stt.xml;

import com.stt.xml.service.HelloWorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by Administrator on 2017-02-15.
 *
 * @author shitongtong
 */
@SpringBootApplication
public class XmlApplication implements CommandLineRunner{

    @Autowired
    private HelloWorldService helloWorldService;

    @Override
    public void run(String... strings) throws Exception {
        System.out.println(this.helloWorldService.getHelloMessage());
    }

    public static void main(String[] args) {
        SpringApplication.run("classpath:/META-INF/application-context.xml",args);
    }
}
