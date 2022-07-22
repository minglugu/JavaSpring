import com.beans.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

/**
 * @author Minglu Gu
 * @version 1.0
 * @since 2022/7/3
 * 1. Spring的启动类,方便测试
 * 2. 将bean对象存储到容器（Spring）中
 *      a)第一次添加，先在Spring项目中添加配置文件
 *      b)先创建bean对象，即普通的Java对象
 *      c)在配置文件中，将需要保持到Spring中的bean对象进行注册
 * 3. ApplicationContext VS BeanFactory
 *    https://www.baeldung.com/spring-beanfactory-vs-applicationcontext
 * 4. 注解：https://blog.csdn.net/u010648555/article/details/76299467
 */

public class App {
    public static void main(String[] args) {
//        1. 得到spring上下文的对象，已经加载bean了，eager load
        ApplicationContext context =
                new ClassPathXmlApplicationContext("spring-config.xml");

        // 2. 根据上下文对象，提供的方法获取到bean,bean name id in spring-config.xml
        // User user = (User) context.getBean("userInfo"); // 如果是null，cast会失败
        // User user = context.getBean(User.class); //同一个类型，会可能有两个id
        User user = context.getBean("userInfo", User.class); // 不需要强制类型转换
        // User user1 = new User(); 解耦，增加代码耦合性，如果调用类改变，就会造成问题

        // 3. 使用
        user.sayHi("LittleLamb");


        // 1. use bean factory，deprecated
       /* BeanFactory factory = new XmlBeanFactory(
                new ClassPathResource("spring-config.xml"));

        // 2. 获取bean，使用的时候，才加载bean，lazy load
        User user = (User) factory.getBean("userInfo");

        // 3. 使用bean
        user.sayHi("Jasper");*/
    }
}
