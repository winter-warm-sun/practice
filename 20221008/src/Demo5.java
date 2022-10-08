import java.util.concurrent.CountDownLatch;

public class Demo5 {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch=new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            Thread t=new Thread(()-> {
                System.out.println("选手出发！"+Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("选手到达！"+Thread.currentThread().getName());
                countDownLatch.countDown();
            });
            t.start();
        }
        countDownLatch.await();
        System.out.println("比赛结束！");
    }
}
