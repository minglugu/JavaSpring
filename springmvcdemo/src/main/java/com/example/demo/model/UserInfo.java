package com.example.demo.model;

import lombok.Data;

// https://javabydeveloper.com/lombok-data-annotation/
// @Data 组合注解，包含了 get set toString这三种方法
@Data
public class UserInfo {
    private int id;
    private String userName;
    private String password;
    private int age;
    // ...
}
