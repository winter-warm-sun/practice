import com.beans.Reception;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Test {
    public static void main(String[] args) {
        ApplicationContext ac=new FileSystemXmlApplicationContext("spring-config.xml");
        Reception reception=(Reception) ac.getBean("reception"); //生成代理对象
        reception.serveCustomer("小强");  //接待人员接到客户，提供服务
    }
}
