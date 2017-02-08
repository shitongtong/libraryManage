package com.stt.springboot.activemq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Queue;

/**
 * Created by Administrator on 2017-02-07.
 *
 * @author shitongtong
 */
@Component
public class Producer implements CommandLineRunner {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Queue queue;

    @Override
    public void run(String... strings) throws Exception {
        send("Sample message");
        System.out.println("Message was send to the Queue");
    }

    public void send(String msg){
        this.jmsMessagingTemplate.convertAndSend(this.queue,msg);
    }
}
