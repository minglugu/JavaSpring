import com.beans.BeanLifeComponent;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Minglu Gu
 * @version 1.0
 * @description bean的初始化及销毁的示例
 */
public class App3 {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("spring-config.xml");

        BeanLifeComponent beanLifeComponent = context.getBean(BeanLifeComponent.class);
        beanLifeComponent.use();
        context.destroy();
    }
}
