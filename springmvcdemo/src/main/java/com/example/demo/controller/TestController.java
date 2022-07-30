package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/*@Controller
@ResponseBody // 如果要返回非静态页面的数据是，用这行注解*/
@RestController // Ctrl点进去，会发现它包含了@Controller和@ResponseBody
public class TestController {
    @RequestMapping("/sayhi")
    public String sayHi() {
        return "hello.html"; // 默认情况下，返回的不是数据，返回的是静态页面的名称
    }
}

