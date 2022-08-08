package com.example.demo.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 自定义用户登录的拦截器
 * 返回true，表示拦截判断通过，可以访问后面的接口，如果返回false 表示拦截未通过，直接返回结果给前端
 * @throws Exception
 */
@Component
public class LoginIntercept implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1. 得到http session对象
        HttpSession session = request.getSession(false);
        if(session != null && session.getAttribute("userinfo") != null) {
            // 用户登录成功,那么拦截器就不会拦截了
            return true;
        }
        //执行到此行代码，表示未登录，就跳转到登录页面
        // response.sendRedirect("/login.html");
        return false;
    }
}
