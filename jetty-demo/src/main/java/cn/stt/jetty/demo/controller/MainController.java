package cn.stt.jetty.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/4/13.
 */
@Controller
public class MainController {
    @RequestMapping("/index")
    @ResponseBody
    public String index(){
        return "hello";
    }
}
