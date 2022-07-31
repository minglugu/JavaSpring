package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller // 跳转到页面，所以用这行代码
/*@Controller
@ResponseBody // 如果要返回非静态页面的数据是，用这行注解*/
// @RestController // Ctrl点进去，会发现它包含了@Controller和@ResponseBody
public class TestController {
    @RequestMapping("/sayhi")
    public String sayHi() {
        return "hello.html"; // 默认情况下，返回的不是数据，返回的是静态页面的名称
    }

    /**
     * forward，请求转发，是服务器端帮用户实现的
     * 是请求转发的实现方式1
     * @return hello.html页面
     */
    @RequestMapping("/fw")
    public String myForward() {
        return "forward:/hello.html"; //服务器端的行为
    }

    // 更加简单的写法
    @RequestMapping("/fw1")
    public String myForward1() {
        return "/hello.html";
    }

    /**
     * 请求转发的实现方式2
     * 先得到request和response对象
     * @return hello.html页面
     */
    @RequestMapping("/fw2")
    public void myForward2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 调度器，获取请求的调度器里面，输入需要跳转的地址
        request.getRequestDispatcher("/hello.html").forward(request, response);
    }

    //请求重定向,是客户端（浏览器端）帮用户实现的。服务器端，是不会对用户进行请求操作的。
    /**
     * 方法一
     * @return
     * URL的地址是：http://localhost:8080/hello.html
     * 302是临时重定向，表明放错地址了，去hello.html访问
     * 而且Fiddler里面，有两次请求，一次是/rd，另一次是跳转到/hello.html
     */
    @RequestMapping("/rd")
    public String myRedirect() {
        return "redirect:/hello.html";
    }

    // 方法二
    @RequestMapping("/rd2")
    public void myRedirect2(HttpServletResponse response) throws IOException {
        response.sendRedirect("/hello.html");
    }

}




