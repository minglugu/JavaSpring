package com.example.demo.mapper;

import com.example.demo.model.LogInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LogInfoMapper {
    int add(LogInfo logInfo);
}
