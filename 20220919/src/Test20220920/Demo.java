package Test20220920;

public class Demo {
    public static void main(String[] args) throws InterruptedException {
        //专门准备一个对象，保证等待和通知是一个对象
        Object locker = new Object();

        //第一个线程，进行wait操作
        Thread t1 = new Thread(() -> {
            while (true) {
                synchronized (locker) {
                    System.out.println("wait之前");
                    try {
                        locker.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //此处写的代码，一定是在notify之后执行的
                    System.out.println("wait之后");
                }
            }
        });
        t1.start();

        Thread.sleep(500);
        //第二个线程,进行notify
        Thread t2 = new Thread(() -> {
            while (true) {
                synchronized (locker) {
                    System.out.println("notify之前");
                    //此处写的代码，一定是在wait唤醒之前执行的

                    locker.notify();
                    System.out.println("notify之后");
                }
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t2.start();
    }
}
