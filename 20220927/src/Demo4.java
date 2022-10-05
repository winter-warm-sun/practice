public class Demo4 {
    public static void main(String[] args) {
        Object locker=new Object();
        for (int i = 0; i < 10; i++) {
            Thread t1=new Thread(()-> {
                synchronized (locker) {
                    System.out.print("A");
                }
            });
            t1.start();
            Thread t2=new Thread(()-> {
                try {
                    t1.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (locker) {
                    System.out.print("B");
                }
            });
            t2.start();
            Thread t3=new Thread(()-> {
                try {
                    t2.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (locker) {
                    System.out.println("C");
                }
            });
            t3.start();
        }
    }
}
