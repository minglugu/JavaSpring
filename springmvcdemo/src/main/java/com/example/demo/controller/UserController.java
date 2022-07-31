package com.example.demo.controller;

import com.example.demo.model.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

@Slf4j
@Controller
@RequestMapping("/user") // 类上面的RequestingMapping 可以省略
@ResponseBody
public class UserController {
    // 从配置文件的读取图片（img file）的保存路径,见application-prod.yml
    @Value("${img.path}")
    // 赋值给一个变量
    private String imgPath;

    @RequestMapping("/setsession")
    public boolean setSession(HttpServletRequest request) {
        boolean result = false;
        // 1. 得到HttpSession
        HttpSession httpSession = request.getSession(true); // true:如果没有会话，那么创建一个。就不用判断null
                                                                // false：有的话，就会用，如果没有会话，不会创建。
        // 2. 使用setAtt，设置值

        httpSession.setAttribute("userInfo", "张三");
        result = true;
        return result;
    }

    //用Servlet来获取
    @RequestMapping("/getsession")
    public String getSession(HttpServletRequest request) {
        String result = null;
        // 1. 得到HttpSession对象
        HttpSession session = request.getSession(false); // false如果有会话，使用会话，没有，就不创建新的会话
        // 2. getAtt得到Session信息
        if(session!=null && session.getAttribute("userInfo") != null) {
            result = (String) session.getAttribute("userInfo");
        }
        return result;
    }

    // 用@SessionAttribute
    // @SessionAttribute中的boolean required() default true; 手动设置成false
    @RequestMapping("/getsession2")
    public String getSession2(@SessionAttribute(value = "userinfo",
            required = false) String userinfo) {
        return "Session是" + userinfo;
    }

    // Servlet获取header, ua is user agent
    @RequestMapping("/getua")
    public String getHeader(HttpServletRequest request) {
        return "Header: " + request.getHeader("User-Agent"); // User-Agent是GET 请求里面的header名字。
    }

    // 用@RequestHeader来获取值
    @RequestMapping("/getua2")
    public String getHeader2(@RequestHeader("User-Agent") String userAgent) {
        return "Header's User-Agent: " + userAgent;
    }

    // Servlet获取cookie
    @RequestMapping("/cookie")
    public void getCookie(HttpServletRequest request) {
        // 得到全部cookie
        Cookie[] cookies = request.getCookies();
        for (Cookie c:cookies) {
            log.info("Cookie Name: " + c.getName() +
                    " | Cookie Value: " + c.getValue());
        }
    }
    // 简洁地获取Cookie-用这个注解搞定@CookieValue("Cookie的value的名字") 放在bite这个变量里面
    // 拿多个值
    @RequestMapping("/cookie2")
    public String cookie(@CookieValue("bite") String bite,
                         @CookieValue("student") String student) {
        return "cookie: " + bite;
    }

    // 不能省略@RequestMapping，网页访问路径为：http://localhost:8080/user/sayhi
    @RequestMapping("/sayhi")
    @ResponseBody // 没有这个，表面返回的是静态页面。加了这行，返回的是非静态页面的数据
    public String sayHi() {
        return "Hello, World! " + imgPath;
    }

    @RequestMapping("/login2")
    public HashMap<String, Object> login2(String username, String password) {
        HashMap<String, Object> result = new HashMap<>();
        // Spring 的非空判断,haslength(), 判断非空和非null, 并且把username和password都固定成admin
        int state = 200;
        int data = -1; // 等于1，登录成功，否则登录失败
        String msg = "未知错误"; // 错误提示信息
        if(StringUtils.hasLength(username) && StringUtils.hasLength(password)) {
            if (username.equals("admin") && password.equals("admin")) {
                data = 1;
                msg = "";
            } else {
                msg = "用户名或密码失败！";
            }
        } else { // 参数为空
            msg = "非法参数";
        }
        result.put("state", state);
        result.put("data", data);
        result.put("msg", msg);
        return result;
    }

    /**
     * 获取前端的json数据
     * @param userInfo
     * @return
     */
    // 前端传递JSON给后端，后端使用JSON来接收，用@RequestBody，接收的是一个JSON字符串
    @RequestMapping("/login3")
    // 检查前后端的key值是否一直，此处是一致的
    public HashMap<String, Object> login3(@RequestBody UserInfo userInfo) {
        HashMap<String, Object> result = new HashMap<>();
        // Spring 的非空判断,haslength(), 判断非空和非null, 并且把username和password都固定成admin
        int state = 200;
        int data = -1; // 等于1，登录成功，否则登录失败
        String msg = "未知错误"; // 错误提示信息
        if(StringUtils.hasLength(userInfo.getUserName()) && StringUtils.hasLength(userInfo.getPassword())) {
            if (userInfo.getUserName().equals("admin") && userInfo.getPassword().equals("admin")) {
                data = 1;
                msg = "";
            } else {
                msg = "用户名或密码失败！";
            }
        } else { // 参数为空
            msg = "非法参数";
        }
        result.put("state", state);
        result.put("data", data);
        result.put("msg", msg);
        return result;
    }

    // 只支持post类型的访问方式
    @RequestMapping(method = RequestMethod.POST, value = "/sayhi2")
    public String sayHi2() {
        return "你好，世界";
    }

    // 只支持post类型的访问方式
    @PostMapping("/sayhi3")
    public String sayHi3() {
        return "Hello, w3";
    }

    // 只支持get类型的访问方式
    @GetMapping("/sayhi4")
    public String sayHi4() {
        return "Hello, w4";
    }

    // getUserById or findUserById, 在获取参数的时候，use Wrapper class Integer. 用int，会报错。阿里的规范
    // 参数的名字，要等于前端传递的参数名。否则不会获取参数的值
    // @RequestingMapping路由不区分大小写
    @RequestMapping("/getuserbyid")
    // @ResponseBody // 没有这一行代码，无法返回json 数据。会出现“no explicit mapping for /error,” 404的错误
    // 或者在这个class类上面，标注@ResponsBody，那么在每个method上面，就无需再标注，
    public UserInfo getUserById(Integer id) {
        // 不查数据库，伪代码，返回用户对象
        UserInfo userInfo = new UserInfo();
        userInfo.setId(id == null ? 0 : id); //需要做非空的校验
        userInfo.setUserName("James");
        userInfo.setAge(18);

        return userInfo;
    }

    // name 是前端传来的名称，重命名。当写了@RequestParam，必须要传name这个参数的值,因为默认的是required=true。
    // 如果不想传name这个参数，那么将required = false
    @RequestMapping("/login")
    public String login(@RequestParam(value = "name", required = false) String username, String password) {
/*        //默认登录失败.
        boolean result = false;
        // 伪代码：如果用户名和密码都是admin，那么就登录成功了*/
        return "用户名： " + username + " | 密码：" + password;
    }

    // 很多个参数，获取对象,这个业务类就无需调整参数。参数再UserInfo里面调整
    // 根据获取的参数的属性，来填写信息后输出。
    // 前端的话，在url里面，填写属性的内容就可以
    // 注册的业务
    @RequestMapping("/reg")
    public String reg(@RequestBody UserInfo userInfo) {
        return "用户信息：" + userInfo;
    }

    // 从URL路径里面获取参数@PathVariable，参数用{参数名}
    // 将参数写成url的格式，是用来增加SEO的优先级的
    @RequestMapping("/hero/{id}/{name}")
    public String getHeroInfo(@PathVariable Integer id, @PathVariable String name) {
        return "ID: " + id + " | Name: " + name;
    }

    // 上传文件和图片，数据流
    // 在方法中的某个参数上，加@RequestPart
    // file.transferTo(new File(filePath)), 接受的这个file，这个流会放到哪个文件下
    @RequestMapping("/upimg")
    public boolean upImg(Integer uid, @RequestPart("img") MultipartFile file) {
        boolean result = false;
        // 1. 目录 ： 见这一行代码“@Value("${img.path}")”
        // 2. 图片名称（图片名不能重复）[时间戳/UUID,时间戳不可取，并发的情况下，后面的会覆盖前面一个，使用UUID(universally unique identifier)]
        /*String fileName = file.getOriginalFilename(); // 得到原图片的名称(xxx.png)
        // fileName做截取
        fileName = fileName.substring(fileName.lastIndexOf(".")); // 得到图片后缀(png)
        fileName = UUID.randomUUID().toString() + fileName; // 此处得到一个独一无二的fileName*/
        String fileName = file.getOriginalFilename(); // 得到原图片的名称（xxx.png）
        fileName = fileName.substring(fileName.lastIndexOf(".")); // 得到图片后缀（png）
        fileName = UUID.randomUUID().toString() + fileName;

        // 3. 获取原上传图片的格式
        //    日志的好处：根据不同的环境，写不同的配置文件。放在不同目录里面，只需要改一个参数
        // 保存图片到本地目录（此处本地电脑既是客户端又是服务器）
        try {
            // 这么写，会把路径名和图片名写死了，后上传的图片，将前面上传的图片给覆盖掉了
             file.transferTo(new File("C:\\Users\\LuLu\\bit_github\\Java\\JavaSpring\\springmvcdemo\\img.png"));
            // file.transferTo(new File(imgPath + fileName)); // 相比于上面一行，更加灵活，路径名不会写死
            result = true;
        } catch (IOException e) {
            log.error("上传图片失败。" + e.getMessage());
        }
        return result;
    }

}