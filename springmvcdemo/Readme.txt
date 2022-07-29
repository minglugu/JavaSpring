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

