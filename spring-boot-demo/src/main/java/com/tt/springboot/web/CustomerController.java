package com.tt.springboot.web;

import com.tt.springboot.vo.PersonForm;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
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

    //非实体类参数可以直接使用注解
    @GetMapping("/check")
    @ResponseBody
    public String check(@Min(value = 10,message = "kpId必须大于等于10") @RequestParam int kpId,
                        @RequestParam int level) {
        System.out.println("kpid=="+kpId);
        System.out.println("level=="+level);

        return "ok";
    }
    //实体类注解校验使用@Valid
    @PostMapping("/checkPersonInfo")
    public String checkPersonInfo(@Valid PersonForm personForm, BindingResult bindingResult) {
        if (personForm!=null){
            String name = personForm.getName();
            Integer age = personForm.getAge();
            System.out.println(name);
            System.out.println(age);
        }
        if (bindingResult.hasErrors()) {
            return "form";
        }
        return "redirect:/results";
    }
}
