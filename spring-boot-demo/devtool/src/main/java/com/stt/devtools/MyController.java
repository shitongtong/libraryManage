package com.stt.devtools;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Created by Administrator on 2017-02-13.
 *
 * @author shitongtong
 */
@Controller
public class MyController {

    //被@PostConstruct修饰的方法会在服务器加载Servlet的时候运行，并且只会被服务器调用一次，类似于Serclet的inti()方法。
    // 被@PostConstruct修饰的方法会在构造函数之后，init()方法之前运行。
    @PostConstruct
    public void slowRestart() throws InterruptedException {
        Thread.sleep(5000);
    }

    @GetMapping("/")
    public ModelAndView get(HttpSession session){

        Object sessionVar = session.getAttribute("var");
        System.out.println("sessionVar==="+sessionVar);
        if (sessionVar == null){
            sessionVar = new Date();
            session.setAttribute("var",sessionVar);
        }
        ModelMap model = new ModelMap("message",Message.MESSAGE).addAttribute("sessionVar",sessionVar);

        return new ModelAndView("hello",model);
    }

}
