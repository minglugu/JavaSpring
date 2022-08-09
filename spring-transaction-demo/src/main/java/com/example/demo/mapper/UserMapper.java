package com.example.demo.mapper;

import com.example.demo.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    //返回受影响的行数
    int add(UserInfo userInfo);
}
