package com.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @author Minglu Gu
 * @version 1.0
 * 注入：使用setter实现Bean的注入
 */

@Controller
public class UserController4 {
    private UserService userService;

    // @Resource
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void sayHi() {
        userService.sayHi();
    }

}
