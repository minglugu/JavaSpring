package com.example.demo.controller;

import com.example.demo.model.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Slf4j
@Controller
@RequestMapping("/user") // 类上面的RequestingMapping 可以省略
@ResponseBody
public class UserController {

    // 不能省略@RequestMapping，网页访问路径为：http://localhost:8080/user/sayhi
    @RequestMapping("/sayhi")
    @ResponseBody // 没有这个，表面返回的是静态页面。加了这行，返回的是非静态页面的数据
    public String sayHi() {
        return "Hello, World!";
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
        // 保存图片到本地目录（此处本地电脑既是客户端又是服务器）
        try {
            file.transferTo(new File("C:\\Users\\LuLu\\bit_github\\Java\\JavaSpring\\springmvcdemo\\img.png"));
            result = true;
        } catch (IOException e) {
            log.error("上传图片失败。" + e.getMessage());
        }
        return result;
    }

}