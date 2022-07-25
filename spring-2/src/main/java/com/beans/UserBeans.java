package com.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author Minglu Gu
 * @version 1.0
 * @description: 使用方法注解 @Bean， 不可以添加到class上
 */
// @Bean 不可以添加到class上
@Component
public class UserBeans {

    // 只写@Bean，不会把user对象，存储到Spring中，需要5大类的注解
    @Bean
    public User user1() {
        User user = new User();
        user.setId(1);
        user.setName("James");
        return user;
    }
}
