//package cn.stt.jetty.demo.controller;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @Author shitongtong
// * <p>
// * Created by shitongtong on 2017/4/13.
// */
//@Controller
//@RequestMapping(value = "/user")
//public class UserController {
//    @RequestMapping("/h1")
//    public String h1(HttpServletRequest request) {
//        request.setAttribute("userName", "萧");
//        request.setAttribute("age", 123);
//        return "user";
//    }
//
//    @RequestMapping(value = "/h2")
//    public void h2(HttpServletResponse response) {
//        String result = "{\"userName\":\" " + "萧" + " \",\"age\":\" " + 13
//                + " \"}";
//        PrintWriter out = null;
//        response.setContentType("application/json");
//
//        try {
//            out = response.getWriter();
//            out.write(result);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @RequestMapping("/h3")
//    public ModelAndView h3(HttpServletRequest request) {
//        Map<String, String> model = new HashMap<>();
//        model.put("userName", "萧");
//        model.put("age", "123");
//        return new ModelAndView("user", model);
//    }
//
//    @RequestMapping("/h4")
//    public String h4(Model model) {
//        model.addAttribute("userName", "萧");
//        model.addAttribute("age", "123");
//        return "user";
//    }
//}
