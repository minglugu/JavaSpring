package com.example.demo;

import com.example.demo.model.ReadList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import com.example.demo.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class UserController {
    // 日志 logging: https://www.baeldung.com/spring-boot-logging



    // 读取配置项，server port的value
    @Value("${myyml.string}")
    private String ymlstr;

    @Value("${server.port}")
    private String port;

    @Value("${mystring}")
    private String mystring;

    @Value("${mystring2}")
    private String mystring2;

    @Value("${mystring3}")
    private String mystring3;

/*  error
    @Value("${student}")
    private Student student;*/
    // 见Student类里面，加入的这些annotation
    /*@Data
    @ConfigurationProperties(prefix = "student") // 读取配置文件的对象
    @Component // 不能省略*/
    @Autowired
    private Student student;

    @Resource
    private ReadList readList;

    // 设置路由sayHi()的地址,可以加类上，可以加方法上(使用一级目录，就可以加载上)
    // 路由的名字，都是小写
    // https://www.baeldung.com/spring-requestmapping
    @ResponseBody // 返回一个非静态页面的数据
    @RequestMapping("/sayhi") // 设置路由地址
    public String sayHi() {

        //return "student: " + student;
        return "readList: " + readList.getName();
    }
}
