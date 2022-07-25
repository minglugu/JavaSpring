package com.beans;

import org.springframework.stereotype.Component;

/**
 * @author Minglu Gu
 * @version 1.0
 * @since yyyy-mm-dd
 */
/*
* @Controller, @Service, @Repository, @Configuration都是基于 @Component实现的，所以@Component 可以认为是其他4类注解的父类
* Spring使用5大类注解，生成bean*/
@Component
public class UserComponent {
    public void sayHi() {
        System.out.println("Hello, component!");
    }
}
