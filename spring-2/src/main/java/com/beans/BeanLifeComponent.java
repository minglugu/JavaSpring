package com.beans;

import org.springframework.beans.factory.BeanNameAware;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author Minglu Gu
 * @version 1.0
 */
//@Component 因为@Component和xml文件里同时注入了bean，所以把这行comment掉，就出现一次执行的结果了。
public class BeanLifeComponent implements BeanNameAware {

    public void setBeanName(String s) {
        System.out.println("执行了 Aware 通知");
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("执行 @PostConstruct");
    }

    public void init() {
        System.out.println("执行 init-method");
    }

    public void use() {
        System.out.println("使用bean");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("执行了 @PreDestroy");
    }

}