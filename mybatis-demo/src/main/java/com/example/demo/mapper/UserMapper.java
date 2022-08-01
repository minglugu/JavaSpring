package com.example.demo.mapper;

import com.example.demo.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

// 必须要加这个注解，从普通的interface。变成MyBatis的interface，用XML来实现。
@Mapper
public interface UserMapper {

    // or public UserInfo findUserById(), stick to one of it
    // get/add | find/save
    // 根据id查询用户
    // @Param, 表明这个参数Integer id,在userMapper.xml,就叫id
    public UserInfo getUserById(@Param("id") Integer id);
}
