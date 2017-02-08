package com.stt.actuator.log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.Map;

/**
 * Created by Administrator on 2017-02-08.
 *
 * @author shitongtong
 */
@Controller
public class SampleController {
    @Autowired
    private HelloWorldService helloWorldService;

    @GetMapping("/")
    @ResponseBody
    public Map<String, String> helloWorld() {
        return Collections.singletonMap("message",
                this.helloWorldService.getHelloMessage());
    }

    @RequestMapping("/foo")
    @ResponseBody
    public String foo() {
        throw new IllegalArgumentException("Server error");
    }
}
