public class Demo1 {
    public static void main(String[] args) {
        Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(1);
            }
        });
        t1.start();
        System.out.println(2);
    }
}
