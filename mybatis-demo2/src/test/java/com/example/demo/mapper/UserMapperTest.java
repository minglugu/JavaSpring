package com.example.demo.mapper;

import com.example.demo.model.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

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
        UserInfo userInfo = userMapper.getUserById(2);
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
        userInfo.setName("wangwu");
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
        userInfo.setName("zhaoliu");
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

    @Test
    void getOrderList() {
        List<UserInfo> list = userMapper.getOrderList("desc");
        log.info("列表：", list);
    }

    @Test
    void getAllBySort() {
        List<UserInfo> list = userMapper.getAllBySort("asc");
        log.info("列表为：" + list);
    }

    @Test
    void isLogin() {
        String username = "admin";
        // 在mySQL里面，会将密码转换成这样：select * from userinfo where username='admin' and password='' or 1='1'
        // mySQL里面 1='1'是true。可以在mySQL的数据库演示，输入select 1='1'; 得到的结果是 1.
        // 后果是虽然密码出错，但是用户的信息会显示出来。这样用户信息就被泄露
        String password = "' or 1='1"; // 比较奇怪的密码：' or 1='1，对数据进行了分割
        UserInfo userInfo = userMapper.isLogin(username, password);
        log.info("用户信息：" + userInfo);
    }

    @Test
    void getListByName() {
        String username = "a";
        List<UserInfo> list = userMapper.getListByName(username);
        log.info("用户列表：" + list);
    }

    @Test
    void getUserAndArticleByUid() {
        UserInfo userInfo = userMapper.getUserAndArticleByUid(2);
        log.info("用户文章：" + userInfo);
    }

    @Test
    void add2() {
        UserInfo userInfo = new UserInfo();
        userInfo.setName("lisi");
        userInfo.setPassword("654");
        int result = userMapper.add2(userInfo);
        log.info("添加用户的结果：" + result);
    }

    @Test
    void add3() {
        UserInfo userInfo = new UserInfo();
        userInfo.setName("wangwu");
        userInfo.setPassword("354");
        int result = userMapper.add3(userInfo);
        log.info("添加用户的结果：" + result);
    }

    @Test
    void update2() {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(2);
        userInfo.setName("admin");
        userInfo.setPassword("123");
        userInfo.setPhoto("default.png");
        int result = userMapper.update2(userInfo);
        log.info("update2 修改后的用户信息：" + result);
    }

    @Test
    void delIds() {
        List<Integer> list = new ArrayList<>();
        list.add(15);
        list.add(16);
        list.add(17);
        list.add(18);
        int result = userMapper.delIds(list);
        log.info("批量删除的结果：" + result);
    }
}