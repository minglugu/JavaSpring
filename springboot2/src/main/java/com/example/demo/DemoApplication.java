package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 与启动类同级的类或子类，会被扫描，看是否加了5大类注解，在启动spring boot的时候，已经初始化好了。
@SpringBootApplication 	// spring boot 启动类
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
