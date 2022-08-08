package com.example.demo.controller;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/login")
    public boolean login(HttpServletRequest request, String username, String password) {
        boolean result = false;

        if(StringUtils.hasLength(username) && StringUtils.hasLength(password)) {
            if(username.equals("admin") && password.equals("admin")) {
                // 当前的登录用户放到session里面
                HttpSession session = request.getSession();
                session.setAttribute("userinfo", "userinfo"); // key value 与判断里面的key value一样
                return true;
            }
        }
        return result;
    }

/*    @RequestMapping("/index")
    public String index(HttpServletRequest request, String username, String password) {
        return "hello, index!";
    }*/
    @RequestMapping("/index")
    public String index() {
        int num = 10 / 0;
        return "hello, index.";
    }
}
