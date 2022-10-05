import java.util.concurrent.atomic.AtomicInteger;

public class Demo2 {
    public static void main(String[] args) throws InterruptedException {
        AtomicInteger count=new AtomicInteger(0);

        Thread t1=new Thread(()-> {
            for (int i = 0; i < 50000; i++) {
                count.getAndIncrement();
            }
        });

        Thread t2=new Thread(()-> {
            for (int i = 0; i < 50000; i++) {
                count.getAndIncrement();
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        //get获取到内部的值
        System.out.println(count.get());
    }
}
