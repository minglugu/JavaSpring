package com.example.demo.mapper;

import com.example.demo.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

// 必须要加这个注解，从普通的interface。变成MyBatis的interface，用XML来实现。
@Mapper
public interface UserMapper {

    // or public UserInfo findUserById(), stick to one of it
    // get/add | find/save
    // search 根据id查询用户
    // @Param, 表明这个参数Integer id,在userMapper.xml,就叫id
    public UserInfo getUserById(@Param("id") Integer id);

    // update 修改方法{根据id修改名称}, @Param()里面的参数，是userMapper.xml里面的id
    public int update(@Param("id") Integer id,
                      @Param("username") String name);

    // 删除方法
    public int delete(@Param("id") Integer id);

    // 添加用户，返回受影响的行数
    public int add(UserInfo userInfo);

    // MyBatis添加用户，返回受影响的行数和自增id，
    // 需要在xml里，设置两个属性:useGeneratedKeys（主键） 和keyProperty
    public int addGetId(UserInfo userInfo);
}
