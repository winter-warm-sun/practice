import com.beans.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        //1.得到Spring上下文
        ApplicationContext context=
                new ClassPathXmlApplicationContext("spring-config.xml");

        UserController userController=context.getBean("userController",UserController.class);

        userController.sayHi();
    }
}
