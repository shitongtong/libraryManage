package com.stt.webscoket.undertow.reverse;

import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * Created by Administrator on 2017-02-16.
 *
 * @author shitongtong
 */
@ServerEndpoint("/reverse")
public class ReverseWebSocketEndpoint {

    @OnMessage
    public void handleMessage(Session session,String message) throws IOException {
        session.getBasicRemote().sendText("Reversed: " + new StringBuilder(message).reverse());
    }

}
