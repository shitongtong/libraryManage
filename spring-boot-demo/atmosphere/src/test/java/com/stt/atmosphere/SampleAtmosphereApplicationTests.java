package com.stt.atmosphere;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.PropertyPlaceholderAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Created by stt on 2017/2/11.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SampleAtmosphereApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext
public class SampleAtmosphereApplicationTests {

    private static Log logger = LogFactory.getLog(SampleAtmosphereApplicationTests.class);

    @LocalServerPort
    private int port = 1234;

    @Test
    public void chatEndpoint() {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(
          ClientConfiguration.class, PropertyPlaceholderAutoConfiguration.class)
                .properties("websocket.uri:ws://localhost:"+this.port+"/chat/websocket")
                .run("--spring.main.web_environment=false");
        long count = context.getBean(ClientConfiguration.class).latch.getCount();
        AtomicReference<String> messagePayloadReference = context.getBean(ClientConfiguration.class).messagePayload;
        context.close();
        System.out.println("messagePayloadReference.get()=="+messagePayloadReference.get());
        assertThat(count).isEqualTo(0L);
        assertThat(messagePayloadReference.get()).contains("{\"message\":\"test\",\"author\":\"test\",\"time\":");

    }

    @Configuration
    static class ClientConfiguration implements CommandLineRunner {

        @Value("${websocket.uri}")
        private String webSocketUri;

        private final CountDownLatch latch = new CountDownLatch(1);

        private final AtomicReference<String> messagePayload = new AtomicReference<>();

        @Override
        public void run(String... strings) throws Exception {
            logger.info("Waiting for response:latch=" + this.latch.getCount());
            if (this.latch.await(10, TimeUnit.SECONDS)) {
                logger.info("Got response:" + this.messagePayload.get());
            } else {
                logger.info("Response not received:latch=" + this.latch.getCount());
            }
        }

        @Bean
        public TextWebSocketHandler handler() {
            return new TextWebSocketHandler() {
                @Override
                public void afterConnectionEstablished(WebSocketSession session) throws Exception {
                    session.sendMessage(new TextMessage("{\"author\":\"test\",\"message\":\"test\"}"));
                }

                @Override
                protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
                    logger.info("Received:"+message+"("+ClientConfiguration.this.latch.getCount()+")");
                    session.close();
                    ClientConfiguration.this.messagePayload.set(message.getPayload());
                    ClientConfiguration.this.latch.countDown();
                }
            };
        }
    }
}
