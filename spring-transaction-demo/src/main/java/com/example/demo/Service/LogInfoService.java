package com.example.demo.Service;

import com.example.demo.mapper.LogInfoMapper;
import com.example.demo.model.LogInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class LogInfoService {

    @Resource
    private LogInfoMapper logInfoMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    public int add(LogInfo logInfo){
        int number = 10/0; // 此处让日志添加失败
        return logInfoMapper.add(logInfo);
    }
}
