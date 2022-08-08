1. 登录、注册页面不拦截，其他页面都拦截。
2. 当登录成功写入session之后，拦截的页面可正常访问。

@Component
public class LoginIntercept implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1. 得到http session对象
        HttpSession session = request.getSession(false);
        if(session != null && session.getAttribute("userinfo") != null) {
            // 用户登录成功,那么拦截器就不会拦截了
            return true;
        }
        //执行到此行代码，表示未登录，就跳转到登录页面
        response.sendRedirect("/login.html");
        return false;
    }
}

统一异常处理使用的是@ControllAdvice + @ExceptionHandler来实现的。
@ControllerAdvice表示控制器通知类
@ExceptionHandler是异常处理器