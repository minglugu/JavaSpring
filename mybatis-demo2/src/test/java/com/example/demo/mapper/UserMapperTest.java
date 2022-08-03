package com.example.demo.mapper;

import com.example.demo.model.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // 表示当前单元测试运行在SpringBoot的环境中
@Slf4j
class UserMapperTest {

    // 属性注入(dependency injection),@Resource和@Autowired在社区版本的idea可以用，
    // 但是@Autowired在科学版的idea会报错。
    @Resource
    private UserMapper userMapper;

    @Test
    void getUserById() {
        UserInfo userInfo = userMapper.getUserById(1);
        // System.out.println(userInfo); // 这行只是打印，不是单元测试的表达式的执行
        // Assertions.assertNotNull(userInfo);
        log.info("用户信息：" + userInfo);
    }

    // 会改变数据库的值
/*    @Test
    void update() {
        int result = userMapper.update(2, "asan");
        Assertions.assertEquals(1, result);
    }*/

    @Test
    @Transactional // 此注解，表示在test方法执行玩，还原数据库里面的值
    void update() {
        int result = userMapper.update(2, "asan");
        Assertions.assertEquals(1, result);
    }

    @Test
    @Transactional
    void delete() {
        int result = userMapper.delete(2);
        System.out.println("受影响的行数：" + result);
        Assertions.assertEquals(1, result);
    }

    @Test
    @Transactional
    void add() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("wangwu");
        userInfo.setPassword("354");
        userInfo.setPhoto("default.png");
        int result = userMapper.add(userInfo);
        System.out.println("添加的结果：" + result);
        Assertions.assertEquals(1, result);
    }

    @Test
    @Transactional // 万一有错误，不确定的情况下，看是否测试通过
    void addGetId() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("zhaoliu");
        userInfo.setPassword("354d");
        userInfo.setPhoto("default1.png");
        // inner db id值不回滚
        System.out.println("添加之前的id：" + userInfo.getId());
        int result = userMapper.addGetId(userInfo);
        System.out.println("受影响的行数：" + result);
        System.out.println("添加之后的 user id：" + userInfo.getId());
        Assertions.assertEquals(1, result);
    }

    @Test
    void getUserByFullName() {
        UserInfo userInfo = userMapper.getUserByFullName("zhaoliu");
        log.info("用户信息：" + userInfo);
    }
}