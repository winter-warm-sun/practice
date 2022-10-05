import java.util.concurrent.locks.ReentrantLock;

public class Demo1 {
    public static void main(String[] args) {
        ReentrantLock locker=new ReentrantLock();
        //加锁
        try {
            locker.lock();
        } finally {
            //解锁
            locker.unlock();
        }
    }
}
