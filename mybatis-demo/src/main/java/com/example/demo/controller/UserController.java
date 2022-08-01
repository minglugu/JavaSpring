package com.example.demo.controller;

import com.example.demo.model.UserInfo;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// controller里调用service
@RestController
@RequestMapping("/user")
public class UserController {

    // controller调用service，所以注入service,才可以return getUserById的id
    @Autowired
    private UserService userService;

    @RequestMapping("/getuserbyid")
    public UserInfo getUserById(Integer id) {
        if(id==null) return null;

        return userService.getUserById(id);

    }
}
