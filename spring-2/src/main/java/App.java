import com.beans.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import sun.rmi.server.UnicastServerRef;

/**
 * @author Minglu Gu
 * @version 1.0
 * @since 7-3-2022
 * @spring annotation: https://www.geeksforgeeks.org/difference-between-component-repository-service-and-controller-annotations-in-spring/
 *
 */
public class App {
    public static void main(String[] args) {
        // 1. 先得到上下文对象
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
/*        // 2. getBean, 命名规则：因为没有Bean id了，所以用小驼峰的class name
        UserController controller = context.getBean("userController",UserController.class);
        // 3. use bean
        controller.sayHi();*/

/*        UserService userService = context.getBean(UserService.class);
        userService.sayHi();*/

/*        UserRepository userRepository = context.getBean(UserRepository.class);
        userRepository.sayHi();*/

/*        UserConfiguration userConfiguration = context.getBean(UserConfiguration.class);
        userConfiguration.sayHi();*/

/*        UserComponent userComponent = context.getBean(UserComponent.class);
        userComponent.sayHi();*/

        // 此处API，是以大写开头
/*        APIController apiController = context.getBean("APIController", APIController.class);
        apiController.sayHi();*/

        // 只使用@Bean注解，不能将对象存入Spring。需要增加@Component在class名上面。如何获取,默认情况下，id名字，就是方法名(此处为user1).
        /*  No bean named 'user1' available
        User user = context.getBean("user1", User.class);*/
       /* No qualifying bean of type 'com.beans.User' available
        User user = context.getBean(User.class);*/
        User user = context.getBean("user1", User.class);
        System.out.println(user);
    }
}
