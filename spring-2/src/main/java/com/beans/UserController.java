package com.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * @author Minglu Gu
 * @version 1.0
 *
 */
// 注解不能够省略
@Controller
public class UserController {
    /*public void sayHi() {
        System.out.println("Hello, User Controller.");
    }*/

    // 注入User对象，并打印
    // autowired先根据类型查找，Spring中，有没有user对象
    // 如果User不是唯一的，那么会出错。解决办法之一:
    //      1. 将User的名字改成确切的名字，e.g. user1
    //      2. 如果有很多次的user要改，使用@Resource，并命名，是Bean的名字，此处是UserBeans里面，某个user的名字。
/*    @Resource(name = "user2")
    private User user;*/

    // 在只能用Autowired的情况下，需要增加@Qualifier("beanName") 或者@Qualifier(value = "beanName")
    @Autowired
    @Qualifier(value = "user1")
    private User user;

    public void sayHi() {
        System.out.println(" User -> " + user);
    }

}
