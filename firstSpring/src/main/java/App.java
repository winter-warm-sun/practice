import com.beans.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import java.security.cert.X509Certificate;

public class App {
    public static void main(String[] args) {
        //1.得到bean工厂
        BeanFactory factory=new XmlBeanFactory(
                new ClassPathResource("spring-config.xml")
        );
        //2.获取bean对象
        User user=(User) factory.getBean("user");
        //3.使用
        user.sayHello("李四");
    }
}
