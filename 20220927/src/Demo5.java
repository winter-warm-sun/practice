public class Demo5 {
    private static Object locker1=new Object();
    private static Object locker2=new Object();
    private static Object locker3=new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread t1=new Thread(()-> {
            for (int i = 0; i < 10; i++) {
                synchronized (locker1) {
                    try {
                        locker1.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.print("A");
                synchronized (locker2) {
                    locker2.notify();
                }
            }
        });
        Thread t2=new Thread(()-> {
            for (int i = 0; i < 10; i++) {
                synchronized (locker2) {
                    try {
                        locker2.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.print("B");
                synchronized (locker3) {
                    locker3.notify();
                }
            }
        });
        Thread t3=new Thread(()-> {
            for (int i = 0; i < 10; i++) {
                synchronized (locker3) {
                    try {
                        locker3.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("C");
                synchronized (locker1) {
                    locker1.notify();
                }
            }
        });

        t1.start();
        t2.start();
        t3.start();
        Thread.sleep(1000);
        synchronized (locker1) {
            locker1.notify();
        }
    }
}
