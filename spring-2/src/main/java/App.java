import com.beans.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context=
                new ClassPathXmlApplicationContext("spring-config.xml");
        BeanLifeComponent beanLifeComponent=
                context.getBean("beanLifeComponent",BeanLifeComponent.class);
        beanLifeComponent.use();
        context.destroy();
    }
}
