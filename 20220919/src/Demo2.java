class Counter {
    public int count=0;
    public synchronized void increase() {
        count++;
    }
    public void increase2() {
        synchronized (this) {
            count++;
        }
    }
    public void increase3() {
        synchronized (Counter.class) {
            count++;
        }
    }
    public void increase4() {
        Object locker=new Object();
        synchronized (locker) {
            count++;
        }
    }
}
public class Demo2 {
    private static Counter counter=new Counter();

    public static void main(String[] args) throws InterruptedException {
        Thread t1=new Thread(()->{
            for (int i = 0; i < 50000; i++) {
                counter.increase();
            }
        });
        Thread t2=new Thread(()->{
            for (int i = 0; i < 50000; i++) {
                counter.increase2();
            }
        });
        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("counter: " + counter.count);
    }
}
