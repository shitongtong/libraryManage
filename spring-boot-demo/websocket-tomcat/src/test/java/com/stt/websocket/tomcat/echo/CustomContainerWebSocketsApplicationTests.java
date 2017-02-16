package com.stt.websocket.tomcat.echo;

import com.stt.websocket.tomcat.WebSocketTomcatApplication;
import com.stt.websocket.tomcat.client.GreetingService;
import com.stt.websocket.tomcat.client.SimpleClientWebSocketHandler;
import com.stt.websocket.tomcat.client.SimpleGreetingService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.PropertyPlaceholderAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.SocketUtils;
import org.springframework.web.socket.client.WebSocketConnectionManager;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Administrator on 2017-02-16.
 *
 * @author shitongtong
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { WebSocketTomcatApplication.class,
        CustomContainerWebSocketsApplicationTests.CustomContainerConfiguration.class }, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DirtiesContext
public class CustomContainerWebSocketsApplicationTests {

    private static Log logger = LogFactory
            .getLog(CustomContainerWebSocketsApplicationTests.class);

    private static int PORT = SocketUtils.findAvailableTcpPort();

    @Test
    public void echoEndpoint() throws Exception {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(
                ClientConfiguration.class, PropertyPlaceholderAutoConfiguration.class)
                .properties("websocket.uri:ws://localhost:" + PORT + "/ws/echo/websocket")
                .run("--spring.main.web_environment=false");
        long count = context.getBean(ClientConfiguration.class).latch.getCount();
        AtomicReference<String> messagePayloadReference = context
                .getBean(ClientConfiguration.class).messagePayload;
        context.close();
        assertThat(count).isEqualTo(0);
        assertThat(messagePayloadReference.get())
                .isEqualTo("Did you say \"Hello world!\"?");
    }

    @Test
    public void reverseEndpoint() throws Exception {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(
                ClientConfiguration.class, PropertyPlaceholderAutoConfiguration.class)
                .properties(
                        "websocket.uri:ws://localhost:" + PORT + "/ws/reverse")
                .run("--spring.main.web_environment=false");
        long count = context.getBean(ClientConfiguration.class).latch.getCount();
        AtomicReference<String> messagePayloadReference = context
                .getBean(ClientConfiguration.class).messagePayload;
        context.close();
        assertThat(count).isEqualTo(0);
        assertThat(messagePayloadReference.get()).isEqualTo("Reversed: !dlrow olleH");
    }

    @Configuration
    protected static class CustomContainerConfiguration {

        @Bean
        public EmbeddedServletContainerFactory embeddedServletContainerFactory() {
            return new TomcatEmbeddedServletContainerFactory("/ws", PORT);
        }

    }

    @Configuration
    static class ClientConfiguration implements CommandLineRunner {

        @Value("${websocket.uri}")
        private String webSocketUri;

        private final CountDownLatch latch = new CountDownLatch(1);

        private final AtomicReference<String> messagePayload = new AtomicReference<String>();

        @Override
        public void run(String... args) throws Exception {
            logger.info("Waiting for response: latch=" + this.latch.getCount());
            if (this.latch.await(10, TimeUnit.SECONDS)) {
                logger.info("Got response: " + this.messagePayload.get());
            }
            else {
                logger.info("Response not received: latch=" + this.latch.getCount());
            }
        }

        @Bean
        public WebSocketConnectionManager wsConnectionManager() {

            WebSocketConnectionManager manager = new WebSocketConnectionManager(client(),
                    handler(), this.webSocketUri);
            manager.setAutoStartup(true);

            return manager;
        }

        @Bean
        public StandardWebSocketClient client() {
            return new StandardWebSocketClient();
        }

        @Bean
        public SimpleClientWebSocketHandler handler() {
            return new SimpleClientWebSocketHandler(greetingService(), this.latch,
                    this.messagePayload);
        }

        @Bean
        public GreetingService greetingService() {
            return new SimpleGreetingService();
        }

    }
}
