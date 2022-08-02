package com.example.demo.service;

import com.example.demo.mapper.UserMapper;
import com.example.demo.model.UserInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

// service来调用mapper，controller里调用service、
@Service
public class UserService {
    //属性注入，是mybatis的接口，所以可以被注入，因为有了@Mapper以后，运行的时候，会化身为一个对象
    @Resource
    private UserMapper userMapper;

    public UserInfo getUserById(Integer id) {
        // userMapper来调用，此时它作为一个对象
        return userMapper.getUserById(id);
    }

}
