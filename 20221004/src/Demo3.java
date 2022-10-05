import java.util.concurrent.Semaphore;

public class Demo3 {
    public static void main(String[] args) throws InterruptedException {
        //构造的时候需要指定初始值，计数的初始值表示有几个可用的资源
        Semaphore semaphore=new Semaphore(4);
        //P操作申请资源，计数器-1
        semaphore.acquire();
        System.out.println("P操作");
        semaphore.acquire();
        System.out.println("P操作");
        semaphore.acquire();
        System.out.println("P操作");
        semaphore.acquire();
        System.out.println("P操作");
        semaphore.acquire();
        System.out.println("P操作");
        //V操作释放资源，计数器+1
        semaphore.release();
    }
}
