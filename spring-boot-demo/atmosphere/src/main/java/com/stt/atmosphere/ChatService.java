package com.stt.atmosphere;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.atmosphere.config.managed.Decoder;
import org.atmosphere.config.managed.Encoder;
import org.atmosphere.config.service.Disconnect;
import org.atmosphere.config.service.ManagedService;
import org.atmosphere.config.service.Ready;
import org.atmosphere.cpr.AtmosphereResource;
import org.atmosphere.cpr.AtmosphereResourceEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by stt on 2017/2/11.
 */
@ManagedService(path = "/chat")
public class ChatService {

    private final Logger logger = LoggerFactory.getLogger(ChatService.class);

    @Ready
    public void onReady(final AtmosphereResource resource){
        this.logger.info("Completed",resource.uuid());
    }

    @Disconnect
    public void onDisConnect(AtmosphereResourceEvent event){
        this.logger.info("Client {} disconnect [{}]",event.getResource().uuid(),(event.isCancelled()?"cancelled":"closed"));
    }

    @org.atmosphere.config.service.Message(encoders = JacksonEncoderDeconder.class,decoders = JacksonEncoderDeconder.class)
    public Message onMessage(Message message){
        this.logger.info("Author {} send message {}",message.getAuthor(),message.getMessage());
        return message;
    }


    public static class JacksonEncoderDeconder implements Encoder<Message,String>,Decoder<String,Message>{

        private final ObjectMapper mapper = new ObjectMapper();

        @Override
        public Message decode(String s) {
            try {
                return this.mapper.readValue(s,Message.class);
            } catch (IOException e) {
                e.printStackTrace();
                throw new IllegalStateException(e);
            }
        }

        @Override
        public String encode(Message message) {

            try {
                return this.mapper.writeValueAsString(message);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                throw new IllegalStateException(e);
            }
        }
    }
}
