package com.tt.springboot;


import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
@EnableAutoConfiguration
public class SampleController {

    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello World!";
    }

    public static void main(String[] args) throws Exception {
//        SpringApplication.run(SampleController.class, args);
        SpringApplication app = new SpringApplication(SampleController.class);
        app.setBannerMode(Banner.Mode.LOG);
        app.run(args);
    }
}
