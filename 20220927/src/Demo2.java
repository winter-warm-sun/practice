import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Demo2 {
    public static void main(String[] args) {
        //用标准库中的线程池，创建出一个线程池中的实例
        ExecutorService pool= Executors.newCachedThreadPool();
        //给这个实例里面加入一些任务
        for (int i = 0; i < 5; i++) {
            pool.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println("hello");
                }
            });
        }
    }
}
