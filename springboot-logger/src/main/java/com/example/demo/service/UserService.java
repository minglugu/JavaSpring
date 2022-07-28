package com.example.demo.service;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import sun.rmi.runtime.Log;

@Service
public class UserService {
    // 另外一种获取logger的方法
    // 0. 准备工作：添加lombok到当前项目


    // 1. 得到日志对象
    private static final Logger log =
            LoggerFactory.getLogger(UserService.class);


}
