package com.stt.websocket;


import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.Objects;

/**
 * 用tomcat启动服务即可创建服务端
 *
 * Created by Administrator on 2017-02-15.
 *
 * @author shitongtong
 */
@ServerEndpoint("/websocket")
public class WebSocketServer{

    @OnOpen
    public void init(Session session){
        //todo ：表示待办
        //fixme ：表示代码有问题需要进行修改
        Objects.equals("","");
        // 添加初始化操作
        System.out.println("===session==="+session+"======");
    }

    @OnMessage
    public void getMessage(Session session,String message){

        System.out.println("session==="+session);
        System.out.println("message==="+message);
        // 把客户端的消息解析为JSON对象
        Gson gson = new Gson();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("receive","你好！！");
        // 把消息发送给所有连接的会话
        for (Session openSession : session.getOpenSessions()) {
            // 添加本条消息是否为当前会话本身发的标志
            jsonObject.addProperty("isSelf", openSession.equals(session));
            // 发送JSON格式的消息
            openSession.getAsyncRemote().sendText(jsonObject.toString());
        }
    }

    @OnClose
    public void onClose(){//停止服务
        //关闭回话操作
        System.out.println("==========onclose========");
    }

    @OnError
    public void onError(Throwable t){//关闭页面，会话结束
        System.out.println("===Throwable==="+t+"======");
    }



}
