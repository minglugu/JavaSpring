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
