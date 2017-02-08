package com.stt.springboot.activemq;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;

import javax.jms.Queue;

/**
 * Created by Administrator on 2017-02-07.
 *
 * @author shitongtong
 */
@SpringBootApplication
@EnableJms
public class ActiveMQApplication {

    @Bean
    public Queue queue(){
        return new ActiveMQQueue("sample.queue");
    }

    public static void main(String[] args) {
        SpringApplication.run(ActiveMQApplication.class,args);
    }
}
