import java.util.concurrent.Semaphore;

public class Demo2 {
    public static void main(String[] args) {
        Semaphore semaphore=new Semaphore(1);
        int count=0;
        Thread t1=new Thread(()-> {
            while (true) {

            }
        });

        Thread t2=new Thread(()-> {
            for (int i = 0; i < 1000; i++) {

            }
        });
    }
}
