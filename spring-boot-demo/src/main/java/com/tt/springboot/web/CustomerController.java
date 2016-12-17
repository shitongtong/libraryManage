package com.tt.springboot.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016-12-14.
 */
@RestController
public class CustomerController {

    @RequestMapping("/")
    public String home() {
        Map map = new HashMap<>();
        return "hello tt";
    }
}
