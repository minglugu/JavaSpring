package com.example.demo.controller;

import com.example.demo.model.UserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user") // 类上面的RequestingMapping 可以省略
@ResponseBody
public class UserController {

    // 不能省略@RequestMapping，网页访问路径为：http://localhost:8080/user/sayhi
    @RequestMapping("/sayhi")
    @ResponseBody // 没有这个，表面返回的是静态页面。加了这行，返回的是非静态页面的数据
    public String sayHi() {
        return "Hello, World!";
    }

    // 只支持post类型的访问方式
    @RequestMapping(method = RequestMethod.POST, value = "/sayhi2")
    public String sayHi2() {
        return "你好，世界";
    }

    // 只支持post类型的访问方式
    @PostMapping("/sayhi3")
    public String sayHi3() {
        return "Hello, w3";
    }

    // 只支持get类型的访问方式
    @GetMapping("/sayhi4")
    public String sayHi4() {
        return "Hello, w4";
    }

    // getUserById or findUserById, 在获取参数的时候，use Wrapper class Integer. 用int，会报错。阿里的规范
    // 参数的名字，要等于前端传递的参数名。否则不会获取参数的值
    // @RequestingMapping路由不区分大小写
    @RequestMapping("/getuserbyid")
    // @ResponseBody // 没有这一行代码，无法返回json 数据。会出现“no explicit mapping for /error,” 404的错误
    // 或者在这个class类上面，标注@ResponsBody，那么在每个method上面，就无需再标注，
    public UserInfo getUserById(Integer id) {
        // 不查数据库，伪代码，返回用户对象
        UserInfo userInfo = new UserInfo();
        userInfo.setId(id == null ? 0 : id); //需要做非空的校验
        userInfo.setUserName("James");
        userInfo.setAge(18);

        return userInfo;
    }

    @RequestMapping("/login")
    public String login(String username, String password) {
/*        //默认登录失败.
        boolean result = false;
        // 伪代码：如果用户名和密码都是admin，那么就登录成功了*/
        return "用户名： " + username + " | 密码：" + password;
    }
}