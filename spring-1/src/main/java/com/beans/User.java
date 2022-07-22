package com.beans;

/**
 * 普通的bean对象,然后注册到spring-config.xml文件内
 */
public class User {
    public User() {
        System.out.println("加载了User！");
    }

    public void sayHi(String name) {
        System.out.println("hello " + name);
    }
}
