import com.beans.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import sun.rmi.server.UnicastServerRef;

/**
 * @author Minglu Gu
 * @version 1.0
 * @since 7-3-2022
 * 1. spring annotation: https://www.geeksforgeeks.org/difference-between-component-repository-service-and-controller-annotations-in-spring/
 *
 * 2. attribute injection, constructor injection, and setter injection:
 * https://laurspilca.com/comparison-field-constructor-setter-injection/
 * @Resource vs @Autowired
 *
 * 3. Quick guide to Spring Bean Scopes: www.baeldung.com.spring-bean-scopes
 *
 * 4. Life cycle of Bean in Spring: https://springframework.guru/spring-bean-lifecycle/#:~:text=Spring%20Bean%20Lifecycle%201%20Spring%20Bean%20Lifecycle%20Overview.,Init%20and%20Destroy%20Method.%20...%206%20Summary.%20
 */
public class App {
    public static void main(String[] args) {
        // 1. 先得到上下文对象
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");

        // 1.   BeanScope1 and BeanScope2 classes, default is singleton: two objects referring to the same bean will
        //      share the same bean
        // 2.   If @Scope("prototype") is added to @Bean(user3) in UserBeans class,
        //      they return a different instance every time
        BeanScope1 beanScope1 = context.getBean(BeanScope1.class);
        User user1 = beanScope1.getUser();
        System.out.println("BeanScope1: " + user1); // name = 'Wu Kong'

        BeanScope2 beanScope2 = context.getBean(BeanScope2.class);
        User user2 = beanScope2.getUser();
        System.out.println("BeanScope2: " + user2); // name = 'Wu Kong'

        //构造方法的注入
/*        UserController3 userController3 = context.getBean(UserController3.class);
        userController3.sayHi();*/

        // 用setter来注入
/*        UserController4 userController4 = context.getBean(UserController4.class);
        userController4.sayHi();*/

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
/*        User user = context.getBean("userinfo", User.class);
        System.out.println(user);*/

        //调用userController
/*        UserController controller = context.getBean(UserController.class);
        controller.sayHi();*/



    }
}
