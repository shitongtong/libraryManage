package cn.stt.test2.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/6/7.
 */
@RestController
@RequestMapping("test2")
public class TestController {

    @RequestMapping("test")
    public ResponseEntity<String> test(){
        return ResponseEntity.ok("test2");
    }
}
