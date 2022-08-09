package com.example.demo.controller;

import com.example.demo.Service.UserService;
import com.example.demo.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private DataSourceTransactionManager transactionManager;

    @Autowired
    private TransactionDefinition transactionDefinition;

    //在此方法中，使用编程式的事务（Programmatic transaction management）
    @RequestMapping("/add")
    public int add(UserInfo userInfo) {
        // todo: 非空效验[验证用户名和密码 不为空]
        if (userInfo == null || !StringUtils.hasLength(userInfo.getUsername())
        || !StringUtils.hasLength(userInfo.getPassword())) return 0;
        // 开启事务,获取事务
        TransactionStatus status = transactionManager.getTransaction(transactionDefinition);
        int result = userService.add(userInfo);
        System.out.println("add 受影响的行数：" + result);
        // 提交事务还是回滚事务起作用。logging里面，会看到update一个user，但是数据库中mycnblog表里面，并没有增加
        // 这条数据，那么说明rollback起作用了。
        // transactionManager.rollback(status); // 回滚事务

        // 手动式/编程式的实现，提交事务，此时在数据库中mycnblog表里面，会有增加一条数据
        //  id | username | password | photo       | createtime          | updatetime          | state |
        //  20 | java     | java     |             | 2022-08-09 12:42:59 | 2022-08-09 12:42:59 |     1
        transactionManager.commit(status);
        return result;
    }

    //在此方法中，使用声明式的事务（Declarative transaction management）
    // 既可以修饰类也可以修饰方法。在进入方法之前，自动开启事务，在方法执行完之后，自动提交事务，如果出现异常，则自动进行回滚操作
    @Transactional // (timeout = 1)set 3s sleep in UserService, it will get TransactionTimedOutException.
    @RequestMapping("/add2")
    public int add2(UserInfo userInfo) {
        // todo: 非空效验[验证用户名和密码 不为空]
        if (userInfo == null || !StringUtils.hasLength(userInfo.getUsername())
                || !StringUtils.hasLength(userInfo.getPassword())) return 0;

        int result = userService.add(userInfo);
        System.out.println("add 受影响的行数：" + result);

        // int num = 10/0; 此行代码是验证错误的

        return result;
    }

    // 使用声明式事务
    @Transactional // (timeout = 1)set 3s sleep in UserService, it will get TransactionTimedOutException.
    @RequestMapping("/add3")
    public int add3(UserInfo userInfo) {
        // todo: 非空效验[验证用户名和密码 不为空]
        if (userInfo == null || !StringUtils.hasLength(userInfo.getUsername())
                || !StringUtils.hasLength(userInfo.getPassword())) return 0;

        int result = userService.add(userInfo);
        System.out.println("add 受影响的行数：" + result);

        try {
            int num = 10 / 0;
        }catch (Exception e) {
            // throw e; // 方法1
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }

        return result;
    }
}
