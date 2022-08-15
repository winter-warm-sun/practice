class Test {
    public void func(){
        System.out.println("func()");
    }
}
public class Demo {
    public static void main(String[] args) {
        int a=100;
        new Test(){
            @Override
            public void func() {
                System.out.println("我是内部类，且重写了func这个方法！");
                System.out.println("捕获到变量a=="+a);
            }
        };
    }
}

