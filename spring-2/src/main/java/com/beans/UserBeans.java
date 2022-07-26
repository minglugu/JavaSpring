package com.beans;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author Minglu Gu
 * @version 1.0
 * @description: 使用方法注解 @Bean， 不可以添加到class上
 */
// @Bean 不可以添加到class上，
// 直接在App里面，用方法名来去出bean，会比较confusing，因为方法名和bean名字的命名方法不一样。可以在Component（name = {"u1"}）
// 可以通过设置name属性给Bean对象，进行重命名操作。
@Component
public class UserBeans {

    // 只写@Bean，不会把user对象，存储到Spring中，需要5大类的注解@Component在Class上面
    // 允许多个名字(name = {"userinfo", "u1"}). 重新命名之后，就不可以使用方法名，来获取Bean对象了
    @Bean(name = "user1")
    public User getUser1() {
        User user = new User();
        user.setId(1);
        user.setName("James");
        return user;
    }

    @Bean(name = "user2")
    public User getUser2() {
        User user = new User();
        user.setId(2);
        user.setName("Bond");
        return user;
    }

    @Bean(name = "user3")
    // @Scope("prototype") // 每次请求，生产一个新对象
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public User getUser3() {
        User user = new User();
        user.setId(1);
        user.setName("Java");
        return user;
    }
}
