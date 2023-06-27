import com.beans.English;
import com.beans.Person;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Test {
    public static void main(String[] args) {
        ApplicationContext ac=new FileSystemXmlApplicationContext("spring-config.xml");
        Person person=null;
        person=(Person) ac.getBean("中国人");
        person.speak();
    }
}
