import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

public class Demo5 {
    public static void main(String[] args) throws InterruptedException {
        Object locker1=new Object();
        Object locker2=new Object();

        Thread t1=new Thread(()-> {
            System.out.println("t1尝试获取locker1");
            synchronized (locker1) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("t1尝试获取locker2");
                synchronized (locker2) {
                    System.out.println("t1获取两把锁成功");
                }
            }
        });
        Thread t2=new Thread(()-> {
            System.out.println("t2尝试获取locker2");
            synchronized (locker2) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("t2尝试获取locker1");
                synchronized (locker1) {
                    System.out.println("t2获取两把锁成功");
                }
            }
        });
        t1.start();
        t2.start();
    }
}
