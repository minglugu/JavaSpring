package com.example.demo.model;

import lombok.Data;

/**
 * model文件夹下面，是普通实体类，无需加5大类注解
 */
// object名字和表格的名字保持一致
@Data
public class UserInfo {
    // attributes和表格里面的fields是一样的
    private int id;
    private String username;
    private String password;
    private String photo;
    private String createtime;
    private String updatetime;
    private int state;
}
