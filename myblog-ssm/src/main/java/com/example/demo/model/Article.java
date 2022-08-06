package com.example.demo.model;

import lombok.Data;

@Data
public class Article {
    private int aid;
    private String title;
    private String desc;
    private String context;
    private String createtime;
    private String updatetime;
    private int readcount;
    private int uid;
    private int state;
}
