package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // 不是Configurable
public class AppConfig implements WebMvcConfigurer {

    @Autowired
    private LoginIntercept loginIntercept;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // registry.addInterceptor(new LoginIntercept()); // 设置拦截器
        registry.addInterceptor(loginIntercept).addPathPatterns("/**").     // 拦截所有的url
                excludePathPatterns("/user/login").                         // 不拦截登录接口
                excludePathPatterns("/user/reg").                           //  不拦截注册接口
                excludePathPatterns("/login.html").                        //  登录页面不拦
                excludePathPatterns("/reg.html").                           //  注册页面不拦
                excludePathPatterns("/**/*.js").                            //所有的静态资源也不拦
                excludePathPatterns("/**/*.css").
                excludePathPatterns("/**/*.jpg").
                excludePathPatterns("/**/*.png");
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        // 给所有的地址添加api的前缀
        configurer.addPathPrefix("api", c->true); // 给所有的controller都去加前缀 api
    }
}
