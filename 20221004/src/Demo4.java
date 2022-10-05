import java.util.concurrent.CountDownLatch;

public class Demo4 {
    public static void main(String[] args) throws InterruptedException {
        //有10个选手参加比赛
        CountDownLatch countDownLatch=new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            Thread t=new Thread(()-> {
                //创建10个线程来执行一批任务
                System.out.println("选手出发！"+Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("选手到达！"+Thread.currentThread().getName());
                //撞线
                countDownLatch.countDown();
            });
            t.start();
        }

        //await是进行阻塞等待，会等待到所有的选手都撞线以后，才能解除阻塞
        countDownLatch.await();
        System.out.println("比赛结束！");
    }
}
