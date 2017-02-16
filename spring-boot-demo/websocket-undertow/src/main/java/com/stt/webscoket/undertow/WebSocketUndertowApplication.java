package com.stt.webscoket.undertow;

import com.stt.webscoket.undertow.echo.DefaultEchoService;
import com.stt.webscoket.undertow.echo.EchoService;
import com.stt.webscoket.undertow.echo.EchoWebSocketHandler;
import com.stt.webscoket.undertow.snake.SnakeWebSocketHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.handler.PerConnectionWebSocketHandler;

/**
 * Created by Administrator on 2017-02-16.
 *
 * @author shitongtong
 */
@Configuration
@EnableAutoConfiguration
@EnableWebSocket
public class WebSocketUndertowApplication extends SpringBootServletInitializer implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry.addHandler(echoWebSocketHandler(),"/echo").withSockJS();
        webSocketHandlerRegistry.addHandler(snackWebSocketHandler(),"/snake").withSockJS();
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(WebSocketUndertowApplication.class);
    }

    @Bean
    public WebSocketHandler echoWebSocketHandler(){
        return new EchoWebSocketHandler(echoService());
    }

    @Bean
    public EchoService echoService() {
        return new DefaultEchoService("Did you say \"%s\"?");
    }

    @Bean
    public WebSocketHandler snackWebSocketHandler(){
        return new PerConnectionWebSocketHandler(SnakeWebSocketHandler.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(WebSocketUndertowApplication.class,args);
    }
}
