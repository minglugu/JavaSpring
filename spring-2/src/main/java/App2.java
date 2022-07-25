
import java.beans.Introspector;

/**
 * @author Minglu Gu
 * @version 1.0
 * @description: beanName小驼峰，但是也有例外，e.g. APIController。
 *              此处的App2用来验证命名的大小写
 *              ps:bean名字的大小写，是交给JDK来决定的，Introspector.decapitalize
 */
public class App2 {
    public static void main(String[] args) {
        String className = "UserController";
        String className2 = "APIController";

        System.out.println(Introspector.decapitalize(className));
        System.out.println(Introspector.decapitalize(className2));
    }
}
