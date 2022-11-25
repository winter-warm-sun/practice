class A {
    public A() {
        System.out.println("A的构造方法");
    }
    {
        System.out.println("A的构造代码块");
    }
    static {
        System.out.println("A的静态代码块");
    }
}

class B extends A {
    public B() {
        System.out.println("B的构造方法");
    }
    {
        System.out.println("B的构造代码块");
    }
    static  {
        System.out.println("B的静态代码块");
    }
}
public class Test {
    public static void main(String[] args) {
        new B();
        System.out.println("----------");
        new B();
    }
}
