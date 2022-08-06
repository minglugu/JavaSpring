package com.example.demo.model;

import lombok.Data;

@Data
public class UserInfo {
    private int uid;
    private String username;
    private String password;
    private String nickname;
    private String img;
    private String git;
    private int state;
    private int acount; // 当前用户发表的文章总数
//    public static void main(String[] args) {
//        String password = "bitekeji";
//        String md5 = SecureUtil.md5(password);
//        System.out.println(md5);
//    }
}
