package com.example.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.rmi.runtime.Log;

// lombok原理

@Controller // 需要加controller，才能够访问路由
@ResponseBody
@Slf4j // 替代了之前需要通过LoggerFactory.getLogger的操作
public class UserService {
    // 另外一种获取logger的方法
    // 0. 准备工作：添加lombok到当前项目.
    @RequestMapping("/sayhi2")
    public void sayHi2() {
        // 加了Slf4j以后，会自动生成log对象
        log.trace("This is trace."); // 打印最少的信息,微量的。少许日志（级别最低）
        log.debug("This is debug."); // 调试日志
        log.info("This is info."); // 默认级别，输出的是大于等于info的级别，才会打印。普通信息日志
        log.warn("This is warn."); // 警告日志
        log.error("This is error");
    }

/*    // 1. 得到日志对象
    private static final Logger log =
            LoggerFactory.getLogger(UserService.class);*/


}
