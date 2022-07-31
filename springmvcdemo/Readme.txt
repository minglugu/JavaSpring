Spring MVC 项目创建：
    基于Spring Boot框架添加一个 Spring Web（它使用的就是Spring MVC）依赖，此时项目就变成了 Spring MVC项目

Spring MVC 学习目标
    1. 实现用户和程序的映射(Mapping), 在浏览器输入URL地址之后，能够在程序中匹配到相应方法
    2. 服务器端要得到用户的请求参数
    3. 服务器要将结果返回给用户（前端）。

关于IDEA文件夹展开的设置：
    uncheck ”Compact Middle Packages“ in setting icon

1.  使用用户和程序的映射
    1.1 方法1：@RequestMapping("/xxxx")

	@RequestMapping 特征：
	1. 既可修饰类（optional），也能修饰方法
	2. 默认情况下，支持Post和get请求方法

    1.2 方法2：使用@PostMapping("/xxx")
    1.2 方法2：使用@PostMapping("/xxx")

路由映射：当用户访问一个url时，将用户的请求对应到程序中某个类的某个方法的过程。

2. 实现用户参数的获取，因为用户请求比较多，所以类型比较多。
    2.1 获取单个参数,见UserInfo getuserbyid(Integer id)
    2.2 获取多个参数，见login(String username, String password)
    2.3 如果参数非常多，比如说有14个。但是可维护性比较低。
        获取对象，参数里面只要写个对象就可以

3. 表单参数的传递/传递多个参数（非对象）

3.1 扩展功能，后端参数重命名（后端参数映射）使用@RequestParam的注解
    前端传递的参数key和我们后端接受的key可以不一致，比如前端传递了一个time给后端，而后端是由createtime字段来接受的。这样就会出现参数接受不到的
    情况。如果出现这种情况，我们就可以使用@RequestParam来重命名前后端的参数值

    @RequestParam 注意事项：
    如果在参数中添加 此注解，那么前端一定要传递此参数，否则就会报错。如果想要解决此问题，可以给@RequestParam里面
    添加 required = false

4. 接受JSON格式的数据
    使用Postman模拟发送JSON数据给服务器

    接受普通的请求方式（e.g. 表单格式）,不能接受JSON格式
    服务器端，实现JSON数据的接受，需要使用@RequestBody
    接受JSON对象，需要用到@RequestBody

5   获取URL中参数 @PathVariable
    （不是从URL地址中的参数部分获取参数）
    e.g. http(s)://xxxx?yyyy        参数经常会变
                   xxxx: url 地址
                   yyyy: 参数部分

    如果是写成这样的url地址，http://dota2.uuu9.com/66/影魔/    66是id，影魔名字
    为什么这么写？SEO优先级比较高。因为SEO（search engine optimization）的效果更好，把参数伪装成url地址了，url变动性比较少


6   使用postman来上传文件

7   扩充功能：不同运行平台的配置文件设置
    Reference URL: https://www.baeldung.com/spring-yaml
    1. 新建不同平台的配置文件。
       application-dev.yml & application-prod.yml 分别对应的是开发环境和生产环境的文件。
       命名规则：application-平台名称.yml （application-dev.yml  application-prod.yml  application-test.yml）
    2. 在主配置文件（application.yml）中,设置运行的配置文件

三种特殊的参数：
Cookie
Session(常见)，比如说，获取用户的浏览器信息，哪种浏览器用得比较多，主流浏览器是哪些。帮助优化。
Header
8   获取Cookie的方法
    1. Servlet获取Cookie的方法
    2. 使用@CookieValue

9   获取header（请求头）的方法
    1. 用Servlet
    2. @RequestHeader("key"), User-Agent

10  存储和获取session的方法，比cookie安全，因为无法模拟
    10.1 session先存（Servlet和Spring MVC操作方式是一样的）
    10.2 才能取
        10.2.1 Servlet模式

    备注：存了Session后，在Inspect的Application里面，会有JSESSIONID，value是服务器的talking 值
    存储session见 setSession()

@Controller 和@RequestMapping的情况下，返回的是静态页面的名称，而不是hello这个String
验证：在resources->static->hello.html

11. 返回一个非静态页面的数据
    a) 使用@ResponseBody这个注解来完成
        - 修饰类时，表示当前类中的所有方法，都会返回一个静态页面的数据
        - 修饰方法是，表示当前方法返回的是一个非静态页面的数据

    b) 更加简单的方法
        使用@RestController 来代替@Controller 和 @ResponseBody

12. CaclController的calc()中，所传的参数的num1和num2需要和calc.html前端页面的变量名是一致的

13. <!-- action 路径名/calc, calc.html文件名，以及在CalcController里面的RequestMapping("/calc")里面的路由的名字，都保持一致-->
    否则会出现mapping错误

14. 热部署 automatic deployment

15. 实现登录功能(login), frontend uses AJAX, backend will return JSON to the frontend

16. 典型的错误：存在jQuery这个文件，但访问的时候，却有 status 404。 因为target文件夹下面，没有生成js。将target文件夹删除，重新启动。
    用这种方法，清除缓存(cache)

17. 前端传递JSON给后端，后端使用JSON来接收，用@RequestBody，接收的是一个JSON字符串
    后端接受JSON数据的标准格式，见login3(@RequestBody 对象或参数)

18. forward vs redirect(请求转发 vs 请求重定向)
    return可以返回一个视图，还可以实现跳转，跳转的方式有两种：
    forward是请求转发
    redirect是请求重定向