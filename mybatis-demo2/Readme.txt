MyBatis是一个ORM框架，ORM(Object Relational Mapping), 即对象关系映射。在面向对象编程语言中，
将关系型数据库中的数据与对象建立去映射关系，进而自动的完成数据与对象的相互转换：
1.  将输入数据（即传入对象）+ SQL映射成原生SQL
2. 将结果集映射为返回对象，即输出对象

ORM把数据库映射对对象：
-  数据库表(table) -->  类(class)
- 记录(record, 行数据) --> 对象(object)
- 字段(field) --> 对象的属性(attribute)

一般的ORM框架，会将数据库模型的每张表都映射成一个Java类。
也就是说使用MyBatis可以像操作对象一样，来操作数据库中的表，可以实现对象和数据库表之间的转换。

In this project, we need to use MyBatis to read all the users in the User table. 我们使用个人博客的数据库和数据包，具体SQL如下。

创建用户表（create table userinfo)和创建文章表(create table articleinfo)的时候，字段名(attributes)是一样的。这样会造成bug。
创建视频表名录。
以下是MyBatis-测试数据库的sql 语言

-- 创建数据库
drop database if exists mycnblog;
create database mycnblog DEFAULT CHARACTER SET utf8mb4;

-- 使用数据数据
use mycnblog;

-- 创建表[用户表]
drop table if exists  userinfo;
create table userinfo(
    id int primary key auto_increment,
    username varchar(100) not null,
    password varchar(32) not null,
    photo varchar(500) default '',
    createtime datetime default now(),
    updatetime datetime default now(),
    `state` int default 1
) default charset 'utf8mb4';

-- 创建文章表
drop table if exists  articleinfo;
create table articleinfo(
    id int primary key auto_increment,
    title varchar(100) not null,
    content text not null,
    createtime datetime default now(),
    updatetime datetime default now(),
    uid int not null,
    rcount int not null default 1,
    `state` int default 1
)default charset 'utf8mb4';

-- 创建视频表
drop table if exists videoinfo;
create table videoinfo(
  	vid int primary key,
  	`title` varchar(250),
  	`url` varchar(1000),
		createtime datetime default now(),
		updatetime datetime default now(),
  	uid int
)default charset 'utf8mb4';

-- 添加一个用户信息
INSERT INTO `mycnblog`.`userinfo` (`id`, `username`, `password`, `photo`, `createtime`, `updatetime`, `state`) VALUES 
(1, 'admin', 'admin', '', '2021-12-06 17:10:48', '2021-12-06 17:10:48', 1);

-- 文章添加测试数据
insert into articleinfo(title,content,uid)
    values('Java','Java正文',1);
    
-- 添加视频
insert into videoinfo(vid,title,url,uid) values(1,'java title','http://www.baidu.com',1);


1.  添加MyBatis的相关依赖(Dependency): MySQL Driver and MyBatis Framework
    以及yml文件

2.  配置数据连接字符串和MyBatis(保存的XML的目录)
    配置MyBatis的XML保存路径：resources->新建mybatis文件夹, 写在application.yml里

    使用MyBatis的操作模式 操作数据库。模式：Interface(接口，用来定义方法声明，放在com.example.demo.mapper里面) + XXX.yml(实现接口中的方法)，生成数据库可以执行的SQL，并且执行SQL
    将结果映射到程序的对象中。
    1. 定义接口

MyBatis在整个框架中的定位，框架交互流程图如下：
             |----------------------------------------------------------|
             |              后端                                         |
             |                                                          |
前端--Ajax JSON--> 控制器-------调用--> 服务层                              |
     <--返回----- Controller<--返回-- Service                            |
             |    (验证参数)          (组合接口)                           |
             |                          |  返回                          |
             |                      Mapper                              |
             |    |-----------------------------------------------------|
             |    |            |-->Interface---------|                  |
             |    |  持久层     |                     |-->生成SQL----操作----> Database(数据库)
             |    |  Mapper  --|                     |  调用JDBC <---返回---
             |    |  (调用数据库)|-->.xml(实现接口)------|                  |
             |    |-----------------------------------------------------|
             |                                                          |
             |----------------------------------------------------------|

开启mybatis，打印logging日志，mybatis是基于JDBC的，最终生成mysql
先运行，再刷新网页localhost/8080/user/getuserbyid?id=1，会在terminal里出现这个SQL的结果：
Creating a new SqlSession
SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@382c72a8] was not registered for synchronization because synchronization is not active
2022-08-01 19:50:49.489  INFO 1036 --- [nio-8080-exec-1] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Starting...
2022-08-01 19:50:49.709  INFO 1036 --- [nio-8080-exec-1] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Start completed.
JDBC Connection [HikariProxyConnection@1735013940 wrapping com.mysql.cj.jdbc.ConnectionImpl@3b4f50f3] will not be managed by Spring
==>  Preparing: select * from userinfo where id=1
==> Parameters:
<==    Columns: id, username, password, photo, createtime, updatetime, state
<==        Row: 1, admin, admin, , 2021-12-06 17:10:48, 2021-12-06 17:10:48, 1
<==      Total: 1
Closing non transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@382c72a8]

3. 写完Mapper，需要让service，controller以及前端三方一起运行，才能够测试mapper，所以会比较繁琐。
    那么能否在写完Mapper直接测试呢？可以使用Spring Boot的单元测试。见SpringBoot单元测试.pdf文件

4. 单元测试(JUnit) 优点
    1. 可以简单，直观，快速地i测试某一个功能是否正确
    2. 使用单元测试可以帮我们在打包的时候，发现一些问题，因为在打包之前，所有的单元测试必须通过，否则不能打包成功
    3. 使用单元测试，在测试功能的时候，可以不污染连接的数据库，也就是可以不对数据库进行任何改变的情况下，来测试功能

5. 配置生成JUnit的过程：
    1. 生成test，在需要测试的方法界面，点击右键，选generate--->test,再选中需要测试的方法。
    2. 配置单元测试的类，添加@SpringBootTest annotation
    3. 再写测试的代码，断言(assert)

    科学版的idea中用@Mapper，用@Autowire做Dependency Injection,会报错，但是@Resource就不会。因为@Mapper来自于
    ibatis这个package。@Autowire来自于Spring，@Mapper来自MyBatis, @Resource来自于jdk，来自两家公司，所以有可能
    出现不兼容。解决方案是使用JDK提供的@Resource，来注入Mapper。

6. Bird logo实现快速跳转接口和XML文件，通过点击小鸟的logo

7. insert, update, delete(增，删，改的操作) 见UserMapper.xml

8. MyBatis修改(update)的实现

    8.1 Interface 里面添加修改方法的声明,建议写@Param明确名字，是和xml里面的参数是匹配的。否则会出现找不到参数的报错信息
    public int update(@Param("id") Integer id,
                     @Param("username") String name);
    8.2 在xml中添加接口的实现标签和具体的执行sql

    事务隔离级别：juejin.cn/post/7114494161155260429
    如果想要在测试的时候，不更改数据库里面的数据，那么在@Test上面加@Transational
    Read Uncommitted
    Read Committed
    Repeatable Read(幻读)
    Serializable

9. MyBatis删除的操作(delete)
    9.1 在mapper(interface)里面添加删除的代码声明
    9.2 在xml中添加<delete> 标签和删除的sql编写
        <delete id="delete">
            delete from userinfo where id=#{id}
        </delete>
    9.3 unit test

10. MyBatis添加数据
    字段是表格里面的，属性是对象里面的
    10.1.1  添加用户，返回受影响的行数
            public int add(UserInfo userInfo);
    10.1.2  在xml中添加<insert> 标签和添加的sql编写
            <insert id="add">
                insert into userinfo(username, password, photo)
                values(#{username},#{password},#{photo})
            </insert>

    10.2.1  在UserMapper的interface里添加方法声明
            public int addGetId(UserInfo userinfo);
    10.2.2  xml实现方法
            <insert id="addGetId" useGeneratedKeys="true" keyProperty="id" keyColumn="id">
                insert into userinfo(username, password, photo)
                values(#{username},#{password},#{photo})
            </insert>


    id自增，用 useGeneratedKeys，而且不用返回int，用void就可以。
    Set the useGeneratedKeys parameter in the setting element.
    For databases that support automatic generation of primary keys,
    such as mysql, sql server, set useGeneratedKeys to true at this time.
    After inserting records, the primary key ID automatically generated by the database can be read.
