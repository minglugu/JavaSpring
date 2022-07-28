package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

// 日志门面（logging facade），日志实现是通过logback
// slf4j: Simple Logging Facade for Java. https://www.tutorialspoint.com/slf4j/slf4j_quick_guide.htm

@Controller
@ResponseBody
public class UserController {
    // 1. 先得到日志对象, there are many logger objects. Here, org.slf4j is used.
    // 设置当前的类型，e.g. UserController，方便日志打印
    private final static Logger log =
            LoggerFactory.getLogger(UserController.class);

    //一级链接, 日志
    @RequestMapping("/sayhi") //映射一下路由,process HTTP requests with specified URL patterns
    public void sayHi() {
        // 2. 写日志。使用日志对象提供的打印方法，进行日志打印。一下级别从低到高的顺序。输出的是大于等于的级别
        //    级别越高，接受的信息就越少。如果设置了warn，就只能收到warn，error和fatal级别的日志了
        //    可以在logging.level配置文件里面进行设置
        log.trace("This is trace."); // 打印最少的信息,微量的。少许日志（级别最低）
        log.debug("This is debug."); // 调试日志
        log.info("This is info."); // 默认级别，输出的是大于等于info的级别，才会打印。普通信息日志
        log.warn("This is warn."); // 警告日志
        log.error("This is error"); // 错误日志
        // fatal: 系统输出的日志，不能够自定义

    }
}
