package com.stt.websocket.tomcat.client;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by Administrator on 2017-02-16.
 *
 * @author shitongtong
 */
public class SimpleClientWebSocketHandler extends TextWebSocketHandler {

    protected Log logger = LogFactory.getLog(SimpleClientWebSocketHandler.class);

    private final GreetingService greetingService;

    private final CountDownLatch countDownLatch;

    private final AtomicReference<String> messagePayload;

    public SimpleClientWebSocketHandler(GreetingService greetingService, CountDownLatch countDownLatch, AtomicReference<String> messagePayload) {
        this.greetingService = greetingService;
        this.countDownLatch = countDownLatch;
        this.messagePayload = messagePayload;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        TextMessage message = new TextMessage(this.greetingService.getGreeting());
        session.sendMessage(message);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        this.logger.info("Received: " + message + " (" + this.countDownLatch.getCount() + ")");
        session.close();
        this.messagePayload.set(message.getPayload());
        this.countDownLatch.countDown();
    }
}
