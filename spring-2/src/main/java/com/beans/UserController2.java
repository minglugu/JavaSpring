package com.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @author Minglu Gu
 * @version 1.0
 * 注入对象的三种方式: 此处为根据属性，注入对象。
 */
@Controller
public class UserController2 {
    // 1. 属性注入. 加载UserController2这个类的时候，先将UserService对象注入到userService这个属性里面
    // 前提是User Service已经注入到Spring里面
    @Autowired
    private UserService userService; // 不用new，注入这个属性，在19行调用 sayHi()

    public void sayHi() {
        userService.sayHi();
    }
}


