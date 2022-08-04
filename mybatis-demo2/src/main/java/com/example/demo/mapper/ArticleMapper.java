package com.example.demo.mapper;

import com.example.demo.model.ArticleInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper // 要用数据库的话，需要加这个notation，否则连不上***Mapper.xml
public interface ArticleMapper {
    //文章的id
    public ArticleInfo getArticleById(Integer id);
}
