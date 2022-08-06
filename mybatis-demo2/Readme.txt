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

常用的sql: use 数据库名字
          show tables
          desc tablename

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

11. 项目名称如果有关键字（比如说mybatis），需要在后面加后缀名，比如说-demo，或者-example

12. 参数占位符(parameter notation in MyBatis) #{}和${}的区别
    the parameter notation # {id,jdbcType=INTEGER} tells MyBatis to take the id property of a parameter object
    and use it as a JDBC prepared statement parameter.
    #{}: 预编译处理: 会将SQL中的#{}替换为？号，使用PreparedStatement的set方法来赋值。
    ${}: 字符直接替换: MyBatis直接把${}替换成变量的值。

    如何查看区别：
    在userMapper.xml里面，用#{},在UserMapperTest的getUserById()里，用log.info打印用户信息，在terminal里面，
    使用#{}得到的JDBC的代码如下：(筛选过滤限定，所以更加安全)
    ==>  Preparing: select * from userinfo where id=?
    ==> Parameters: 1(Integer)
    <==    Columns: id, username, password, photo, createtime, updatetime, state
    <==        Row: 1, admin, admin, , 2021-12-06 17:10:48, 2021-12-06 17:10:48, 1
    <==      Total: 1

    在userMapper.xml里面，用${}, 在UserMapperTest的getUserById()里，用log.info打印用户信息，在terminal里面，
    使用${}(争对int类型的)得到的JDBC的代码如下：
    ==>  Preparing: select * from userinfo where id=1
    ==> Parameters:
    <==    Columns: id, username, password, photo, createtime, updatetime, state
    <==        Row: 1, admin, admin, , 2021-12-06 17:10:48, 2021-12-06 17:10:48, 1
    <==      Total: 1

    ${}针对String类型的，getUserByFullName()的test里面，结果如下，no"" on zhaoliu, sql就报错了，以为是field：
    ==>  Preparing: select * from userinfo where username=zhaoliu
    ==> Parameters:

    URL reference: https://mybatis.org/mybatis-dynamic-sql/docs/howItWorks.html#:~:text=This%20is%20standard%20SQL%20with%20a%20MyBatis%20twist,suppose%20we%20have%20two%20Java%20classes%20like%20this%3A

    总结：#{} vs ${}区别：
    1. 定义不同：#{} 预处理，而${} 是直接替换；
    2. 使用不同：#{} 适用于所有类型的参数匹配，但 ${} 只适用于数值类型；
    3. 安全性不同：#{} 性能高，没有安全问题，但${} 存在sql 注入(injection)的安全问题。

    ${} 使用场景，getOrderList()排序
    <select id="getOrderList" resultType="com.example.demo.model.UserInfo">
            select * from userinfo order by createtime #{order}
    </select>

    ==>  Preparing: select * from userinfo order by createtime desc
    ==> Parameters:

    #{} 传递SQL关键字(desc/asc)时，test有误(You have an error in your SQL syntax),因为不是个普通的value：
    ==>  Preparing: select * from userinfo order by createtime ?
    ==> Parameters: desc(String)

    小结：当传递的是一个SQL关键字（SQL指令，e.g. desc/asc）的时候，只能使用${},此时如果使用#{}, 就会认为传递的为一个普通的值，而非SQL命令，
    所以执行会报错。
    当使用${}时，注意事项：
    当必须使用时，那么一定要在业务代码中，对传递的值进行安全效验。

13. SQL注入问题演示：${}
    见@Test isLogin()
    password='' or 1='1'
    如果改成#{}，那么就返回null，不存在这个安全问题。

14. 特殊的like查询
    like 使用#{}会报错，但也不能直接使用${},可以考虑使用mysql的内置函数concat()来处理。
    举例：在userMapper.xml文件里,输入 select * from userinfo where username like '%#{username}%';
    在@Test getListByName()执行后，sql为Preparing: select * from userinfo where username like '%?%'
    ?是String类型的，预处理时，会加一个''，最后在@Test里面，SQL为select * from userinfo where username like '%'a'%'
    错误提示为：nested exception is org.apache.ibatis.type.TypeException: Could not set parameters for mapping: ParameterMapping{property='username', mode=IN, javaType=class java.lang.Object, jdbcType=null, numericScale=null, resultMapId='null', jdbcTypeName='null', expression='null'}.
    另外，在MySQL里面执行的结果为You have an error in your SQL syntax。

    使用#{}会报错，但可以用${},在业务层的值，又不能穷举。无法对参数进行校验。
    在MySQL里面，用concat SQL来验证：select concat('zhang','san');
    会返回zhangsan
    select concat('zhang','san') as username;
    得到zhangsan为username。

    select * from userinfo where username like concat('%',#{username},'%')
    ==>  Preparing: select * from userinfo where username like concat('%',?,'%')
    ==> Parameters: a(String)
    <==    Columns: id, username, password, photo, createtime, updatetime, state
    <==        Row: 1, admin, admin, , 2021-12-06 17:10:48, 2021-12-06 17:10:48, 1
    <==      Total: 1

15. resultType vs resultMap
    返回类型：resultType
    绝大多数查询场景可以使用resultType进行返回，代码如下：
    <select id="getListByName" resultType="com.example.demo.model.UserInfo">
        select * from userinfo where username like concat('%',#{username},'%')
    </select>
    有点是使用方便，直接定义到某个实体类就可以。

    返回字典映射：resultMap
    使用场景：
    1. 字段(field)名称和程序中的属性名(attribute)不同的情况下,可以使用resultMap配置映射
    2. 一对一和一对多关系，可以使用resultMap映射并查询数据。
    使用desc userinfo;
    就可以看到
    +------------+--------------+------+-----+-------------------+-------------------+
    | Field      | Type         | Null | Key | Default           | Extra             |
    +------------+--------------+------+-----+-------------------+-------------------+
    | id         | int(11)      | NO   | PRI | NULL              | auto_increment    |
    | username   | varchar(100) | NO   |     | NULL              |                   |
    | password   | varchar(32)  | NO   |     | NULL              |                   |
    | photo      | varchar(500) | YES  |     |                   |                   |
    | createtime | datetime     | YES  |     | CURRENT_TIMESTAMP | DEFAULT_GENERATED |
    | updatetime | datetime     | YES  |     | CURRENT_TIMESTAMP | DEFAULT_GENERATED |
    | state      | int(11)      | YES  |     | 1                 |                   |
    +------------+--------------+------+-----+-------------------+-------------------+

    然而在UserInfo的Object中，attribute的名字和field名字不一致。
    在测试@Test void getUserById()时，name=null,因为数据库是username，object里面是name，所以找不到，是null。结果如下：
    用户信息：UserInfo(id=1, name=null, password=admin, photo=, createtime=2021-12-06 17:10:48, updatetime=2021-12-06 17:10:48, state=1)

    怎么解决？用resultMap
    reference URL: https://www.tutorialspoint.com/ibatis/ibatis_result_maps.htm
    1. 先定义resultMap。其中id=”BaseMap“，用Upper Case Camel取名，此名字用于”2.“中的resultMap的名字
    <resultMap id="BaseMap" type="com.example.demo.model.UserInfo">
        <!--主键映射，column这里，表示数据库里面，字段的名字。property表示程序中对象的属性-->
        <id column="id" property="id"></id>
        <!--普通属性映射-->
        <result column="username" property="name"></result>
    </resultMap>

    2. 然后在userMapper.xml里面，getUserById
    <!--将resultType改成resultMap-->
        <select id="getUserById" resultMap="BaseMap">
    <!-- 具体的sql query，id=${id} 或者 id=#{id} -->
            select * from userinfo where id=#{id}
        </select>

    2. 在@Test void getUserById()中，得到的结果为，注意返回值里面，
    用户信息：UserInfo(id=1, name=admin, password=admin, photo=, createtime=2021-12-06 17:10:48, updatetime=2021-12-06 17:10:48, state=1)

16. 上面是one to one的表的mapping，下面来将one to many的关系（多表查询）
    one to one: 个人博客的文章，对应了一个作者
    one to many: 一个用户可以发表多篇文章
    - 表名都小写
    MyBatis一对一查询，<resultMap>,<association property="此处是attribute，对应的是另外一个表里面的对象">

    多表的一对一查询，步骤如下：
    1. ArticleInfo class里，建ArticleInfo的对象+属性，包含UserInfo这个属性
    2. ArticleMapper.xml文件里，要设置resultMap，包含<association>tag,用来实现一对一的关联查询。
       查询的是2张表：select a.*,u.* from ..
    3. 在<association>里面，配置resultMap=...UserMapper.BaseMap的信息，所以在UserMapper里面，也要有resultMap。

    更详细的说明：如果<select..>中sql query这么写：select * from userinfo where id=#{id},userinfo出来的结果是不对的。
    因为userinfo和articleinfo里面的字段重名，userinfo里面，字段显示的信息，来自于articleinfo表中。
    如何验证？
    将userinfo的id改成2， articleinfo的uid=2。（update userinfo set id=2;）
    那么在test结果里面，会出现这样的结果
    userInfo=UserInfo(id=1, name=admin, password=admin, photo=, createtime=2022-08-01 16:14:02, updatetime=2022-08-01 16:14:02, state=1))
    id=1.
    原因：如果两个表，里面有相同字段，那么后一个表的相同字段，被前面表里的相同字段值覆盖。
    怎么解决？设置前缀<columnPrefix="u_"> 随便命名个前缀.用来解决多表中，相同字段数据覆盖的问题



17. 如果在ArticleMapper.xml文件里，写select，如下
    <select id="getArticleById" resultType="com.example.demo.model.ArticleInfo">
            select * from articleinfo where id=#{id}
    </select>
    那么在ArticleMapperTest里，虽然test能够pass，但是也会出现userInfo为null。
    文章的详情：ArticleInfo(id=1, title=Java, content=Java??, createtime=2022-08-01 16:14:02, updatetime=2022-08-01 16:14:02, uid=1, rcount=1, state=1, userInfo=null)
    为什么是null？
    一对一的多表联查/field和property不一样的名字，不能用resultType

    sql：select a.*, u.*... (alias, 给表取的别名)

18. 一对多实现：
    一个用户发多篇文章
    <collection>标签
    <collection property="artlist"          // property：对象中的属性名
                resultMap="com.example.demo.mapper.ArticleMapper.BaseMap"   // 映射对象所对应的字典
                columnPrefix="a_">          // columbPrefix，不要省略，解决了多张表中，相同字段查询数据会被覆盖的问题

19. MyBatis的动态SQL
    if
    choose (when, otherwise)
    trim (where, set)
    foreach

    *** <if> 标签
    非必传参数（比如说注册用户是，用户性别无需填写）
    注册分为两种字段：必填字段和非必填字段，那如果在添加用户的时候，有不确定的字段传入，程序应该如何实现呢？
    这个时候，就需要使用动态标签<if>来判断了，比如添加的时候，性别gender为非必填字段。
    <if>标签用来动态判断，参数是否有值，如果没有值，那么隐藏if里面的数据，进行隐藏。sql里面就没有这个字段名。
    非常重要的语法
    语法：test的表达式，来进行判断
        <if test=”username != null“>        表达式：username的参数是否为空，
            username=#{username}                  如果结果为true，那么拼接的SQL就会加上username=#{username}，
        </if>                                     如果结果为false，那么if标签中的sql就会忽略。

    *** <trim>标签：配合<if>一起使用，去除sql语句前后多余的某个字符（任何）的。
    如果所有字段都是非必填项，就考虑使用<trim>标签结合<if>标签，对多个字段都采取动态生成的方式。
    <trim> 标签中有如下属性：
    - prefix：表示整个语句块，以prefix的值作为前缀
    - suffix：表示整个语句块，以suffix的值作为前缀
    - prefixOverrides：表示整个语句块，要去除掉的前缀
    - suffixOverrides：表示整个语句块，要去除掉的后缀
    完整的语法：在trim之前，加(,结束后加),去除掉前面的"," , 去除掉后面的","
    <trim prefix="(" suffix=")" prefixOverrides="," suffixOverrides=",">
        <if test="xxx != null">
            ...
        </if>
            ...
    </trim>

    *** <where>标签的特点及使用
    主要作用：1. 实现查询中的where sql替换的，它可以实现如果没有任何的查询条件，那么它可以因此查询中的where sql，但如果存在查询条件，那么会生成
    where的sql查询；2. 并且使用where标签可以自动的去除最后一个and字符，格式为 and id=#{}。
    <where> 等于 <trim prefix="where" prefixOverrides="and">，所以<where>可以用后面的<trim>来替换