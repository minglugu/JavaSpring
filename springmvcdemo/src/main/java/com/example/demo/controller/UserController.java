package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user") // 类上面的RequestingMapping 可以省略
public class UserController {

    // 不能省略@RequestMapping，网页访问路径为：http://localhost:8080/user/sayhi
    @RequestMapping("/sayhi")
    @ResponseBody // 没有这个，表面返回的是静态页面。加了这行，返回的是非静态页面的数据
    public String sayHi() {
        return "Hello, World!";
    }

    // 只支持post类型的访问方式
    @RequestMapping(method = RequestMethod.POST, value = "/sayhi2")
    public String sayHi2() {
        return "你好，世界";
    }

    // 只支持post类型的访问方式
    @PostMapping("/sayhi3")
    public String sayHi3() {
        return "Hello, w3";
    }

    // 只支持get类型的访问方式
    @GetMapping("/sayhi4")
    public String sayHi4() {
        return "Hello, w4";
    }
}
