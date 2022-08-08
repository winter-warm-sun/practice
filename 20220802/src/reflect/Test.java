package reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
 class Person {
    //私有属性name
    private String name = "张三";
    //公有属性age
    public int age;

    //不带参数的构造方法
    public Person() {
        System.out.println("Person()");
    }

    //带参数的私有构造方法
    private Person(String name, int age) {
        this.age = age;
        this.name = name;
        System.out.println("Person(String,name)");
    }

    private void eat() {
        System.out.println("I am eating");
    }

    public void sleep() {
        System.out.println("I am sleeping");
    }

    private void function(String str) {
        System.out.println(str);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
public class Test {
    public static void main(String[] args) {
        try {
            Class<?> c=Person.class;
            //通过构造方法创建对象
            Constructor<?> constructor= c.getDeclaredConstructor(String.class,int.class);
            constructor.setAccessible(true);
            Person person=(Person) constructor.newInstance("王五",18);
            System.out.println(person);
            System.out.println("=====================");
            //字段属性
            Field field=c.getDeclaredField("name");
            field.setAccessible(true);
            field.set(person,"李四");
            System.out.println(person);
            System.out.println("=====================");
            //方法属性
            Method method= c.getDeclaredMethod("function", String.class);
            method.setAccessible(true);
            method.invoke(person,"我是通过反射给你传参的");//执行方法
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
    public static void main3(String[] args) throws NoSuchFieldException, NoSuchMethodException {
        Class<?> c=Person.class;

        //获取name属性
        Field field=c.getDeclaredField("name");
        //获取自身和父类的公有属性（不包括私有的）
        Field[] fields= c.getFields();
        //获取自身的公有和私有属性
        Field[] declaredFields = c.getDeclaredFields();


        //获取function方法，参数为String
        Method method= c.getMethod("function", String.class);
        //获取自身和父类的公有方法（不包括私有的）
        Method[]methods= c.getMethods();
        //获取自身的公有和私有方法
        Method[] declaredMethods = c.getDeclaredMethods();

        //获取无参的构造方法
        Constructor<?> constructor=c.getConstructor();
        //获取参数为String和int的构造方法
        Constructor<?> constructor1=c.getDeclaredConstructor(String.class,int.class);
        //获取自身和父类的构造方法（不包括私有的）
        Constructor<?>[]constructors=c.getConstructors();
    }
    public static void main2(String[] args) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Class<?> c=Person.class;
        //1.通过Class对象的newInstance()方法
       //Person person1=(Person) c.newInstance();
        //2.通过 Constructor 对象的 newInstance() 方法
        Constructor<?> constructor= c.getConstructor(String.class,int.class);
        constructor.setAccessible(true);
        Person person=(Person) constructor.newInstance("王五",18);
        System.out.println(person);
    }
    public static void main1(String[] args) throws ClassNotFoundException {
        //1.使用 Class.forName() 静态方法,需要知道类的全路径名
        Class<?> c1=Class.forName("reflect.Person");
        //2.使用.class方法
        Class<?> c2=Person.class;
        //3.使用类对象的getClass()方法
        Person person=new Person();
        Class<?> c3=person.getClass();
        System.out.println(c1==c2);
        System.out.println(c1==c3);
        System.out.println(c2==c3);
    }
}
