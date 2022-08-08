package reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Demo {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> c=Person.class;
        Constructor<?> constructor= c.getDeclaredConstructor(String.class,int.class);
        constructor.setAccessible(true);
        Person person=(Person) constructor.newInstance("王五",18);
        System.out.println(person);
    }
}
