import reflect.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public enum TestEnum {
    RED("red", 2), BLACK("black", 3),
    GREEN("green", 4), WHITE("white", 5);
    public String color;
    public int ordinal;

    TestEnum(String color, int ordinal) {
        this.color = color;
        this.ordinal = ordinal;
    }

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> c = TestEnum.class;
        Constructor<?> constructor = c.getDeclaredConstructor(String.class, int.class,String.class, int.class);
        constructor.setAccessible(true);
        TestEnum testEnum = (TestEnum) constructor.newInstance("海雾蓝", 9);
        System.out.println(testEnum);
    }

    public static void main2(String[] args) {
        //values()方法
        TestEnum[] testEnums = TestEnum.values();
        for (int i = 0; i < testEnums.length; i++) {
            //ordinal()方法
            System.out.println(testEnums[i] + " 序号： " + testEnums[i].ordinal());
        }
        System.out.println("==============");
        //valueOf()方法
        TestEnum testEnum = TestEnum.valueOf("GREEN");
        System.out.println(testEnum);
        System.out.println("==============");
        TestEnum testEnum1 = TestEnum.BLACK;
        TestEnum testEnum2 = TestEnum.RED;
        //compareTo()方法
        System.out.println(testEnum1.compareTo(testEnum2));
        System.out.println(BLACK.compareTo(RED));
        System.out.println(RED.compareTo(BLACK));
    }

    public static void main1(String[] args) {
        TestEnum testEnum = TestEnum.BLACK;
        switch (testEnum) {
            case RED:
                System.out.println("red");
                break;
            case BLACK:
                System.out.println("black");
                break;
            case WHITE:
                System.out.println("WHITE");
                break;
            case GREEN:
                System.out.println("black");
                break;
            default:
                break;
        }
    }
}
