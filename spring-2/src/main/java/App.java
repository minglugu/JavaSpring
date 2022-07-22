import com.beans.UserController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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
        // 2. getBean, 命名规则：因为没有Bean id了，所以用小驼峰的class name
        UserController controller = context.getBean("userController",UserController.class);
        // 3. use bean
        controller.sayHi();
    }
}
