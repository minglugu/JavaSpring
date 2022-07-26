package com.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @author Minglu Gu
 * @version 1.0
 * 注入：使用构造方法实现Bean的注入
 */

@Controller
public class UserController3 {
    private UserService userService;

    // 官方推荐的写法,使用构造方法，但Autowired不可以替换成@Resource
    @Autowired
    public UserController3(UserService userService) {
        // 传统方法，会用new
        // userService = new UserService();
        this.userService = userService;
    }

    public void sayHi() {
        userService.sayHi();
    }

}
