package com.stt.metrics.dropwizard;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.Map;

/**
 * Created by Administrator on 2017-02-14.
 *
 * @author shitongtong
 */
@Controller
@Description("A controller for handling requests for hello messages")
public class SampleController {

    @Autowired
    private HelloWorldService helloWorldService;

    @Autowired
    private GaugeService gaugeService;

    @GetMapping("/")
    @ResponseBody
    public Map<String,String> hello(){
        this.gaugeService.submit("timer.test.value",Math.random()*1000+1000);
        return Collections.singletonMap("message",this.helloWorldService.getHelloMessage());
    }

    public static class Message{

        @NotBlank(message = "Message value cannot be empty")
        private String value;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

}
