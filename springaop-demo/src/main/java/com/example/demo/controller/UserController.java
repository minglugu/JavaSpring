package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/sayhi")
    public String sayHi() {
        System.out.println("sayhi方法：被执行了！");
        return "Hello world!";
    }

    @RequestMapping("/sayhello")
    public String sayHello() {
        System.out.println("say hello方法：被执行了！");
        return "你好，世界！";
    }
}
