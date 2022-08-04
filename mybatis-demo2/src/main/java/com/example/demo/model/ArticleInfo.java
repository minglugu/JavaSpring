package com.example.demo.model;

import com.example.demo.model.UserInfo;
import lombok.Data;

@Data
public class ArticleInfo {
    private int id;
    private String title;
    private String content;
    private String createtime;
    private String updatetime;
    private int uid;
    private int rcount;
    private int state;
    // 一对一的关系，一篇文章一个发布人
    private UserInfo userInfo;
}
