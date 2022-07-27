package com.example.demo.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@ConfigurationProperties("dbtypes") // 读取配置文件中的集合
@Component
public class ReadList {
    private List<String> name; // 和yml文件里面，List集合里面的名字是一样的
}
