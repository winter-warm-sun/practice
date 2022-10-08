import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Demo {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor=
                new ThreadPoolExecutor(5,10,3, TimeUnit.SECONDS,
                        new LinkedBlockingQueue<>(100),
                        new ThreadPoolExecutor.DiscardPolicy());
        for (int i = 0; i < 1000; i++) {
            new Thread(()-> {
                System.out.println(Thread.currentThread().getName()+
                        "已执行.");
            },"thread-"+(i+1)).start();
        }
    }
}
