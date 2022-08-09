package com.example.demo.Service;

import com.example.demo.mapper.UserMapper;
import com.example.demo.model.UserInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    public int add(UserInfo userInfo) {
        return userMapper.add(userInfo);
    }
}
