package com.example.demo.controller;

import com.example.demo.model.UserInfo;
import com.example.demo.service.UserService;
import com.example.demo.util.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/reg")
    public ResultData reg(UserInfo userInfo){
        // 1. 非空校验
        if (userInfo == null || !StringUtils.hasLength(userInfo.getUsername()) ||
                !StringUtils.hasLength(userInfo.getPassword()) ||
                !StringUtils.hasLength(userInfo.getNickname())) {
            return ResultData.fail(-1, null, "非法参数");
        }
        // 2. 调用业务代码，执行注册功能. result为受影响的行数
        int result = userService.reg(userInfo);

        return ResultData.ok(result,"");
    }
}
