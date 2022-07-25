package com.beans;

import org.springframework.stereotype.Controller;

/**
 * @author Minglu Gu
 * @version 1.0
 *
 */
// 注解不能够省略
@Controller
public class UserController {

    public void sayHi() {
        System.out.println("Hello, User Controller.");
    }
}
