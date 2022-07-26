package com.example.demo.Service;

import com.example.demo.mapper.LogInfoMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.LogInfo;
import com.example.demo.model.UserInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private LogInfoMapper logInfoMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    public int add(UserInfo userInfo) {
/*
        try {
            Thread.sleep(3*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        int result = userMapper.add(userInfo);
        return result;
    }
}
