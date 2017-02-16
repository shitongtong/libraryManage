package com.stt.websocket.jetty.echo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * Echo messages by implementing a Spring {@link WebSocketHandler} abstraction.
 *
 * Created by Administrator on 2017-02-16.
 *
 * @author shitongtong
 */
public class EchoWebSocketHandler extends TextWebSocketHandler {

    private static final Logger logger = LoggerFactory.getLogger(EchoWebSocketHandler.class);

    private final EchoService echoService;

    public EchoWebSocketHandler(EchoService echoService){
        this.echoService = echoService;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
//        super.afterConnectionEstablished(session);
        logger.debug("Opened new session in instance " + this);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
//        super.handleTextMessage(session, message);
        String echoMessage = this.echoService.getMessage(message.getPayload());
        if (logger.isDebugEnabled()){
            logger.debug(echoMessage);
        }
        session.sendMessage(new TextMessage(echoMessage));
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
//        super.handleTransportError(session, exception);
        session.close(CloseStatus.SERVER_ERROR);
    }
}
