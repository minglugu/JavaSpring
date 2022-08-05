package com.example.demo.mapper;

import com.example.demo.model.ArticleInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper // 要用数据库的话，需要加这个notation，否则连不上***Mapper.xml
public interface ArticleMapper {
    //根据文章的id，来获取文章的详情
    public ArticleInfo getArticleById(@Param("id") Integer id);
}
