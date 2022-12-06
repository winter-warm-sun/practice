//import com.beans.BeanScope1;
//import com.beans.BeanScope2;
//import com.beans.User;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//
//public class BeanScopeTest {
//    public static void main(String[] args) {
//        ApplicationContext context=
//                new ClassPathXmlApplicationContext("spring-config.xml");
//        BeanScope1 A=context.getBean(BeanScope1.class);
//        User user1=A.getUser();
//        System.out.println("BeanScope1:"+user1);
//
//        BeanScope2 B=context.getBean(BeanScope2.class);
//        User user2=B.getUser();
//        System.out.println("BeanScope2:"+user2);
//    }
//}
