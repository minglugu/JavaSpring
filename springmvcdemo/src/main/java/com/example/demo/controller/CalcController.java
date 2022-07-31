package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalcController {

    //此处的num1和num2需要和calculator.html前端页面的变量名是一致的
    @RequestMapping("/calc")
    public String calc(Integer num1, Integer num2) {
        // 判断是否为空
        if(num1 == null || num2 == null) return "<h1>参数不能为空！</h1> " +
                "<a href='javascript:history.go(-1)'>return to the previous page</a>";
        // 加一个返回的链接,表示返回到上一层：<a href='javascript:history.go(-1)>return</a>'>
        return "<h1>结果：" + (num1 + num2) + "</h1><a href='javascript:history.go(-1)'>return to the previous page</a>";
    }
}
