import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

//这个类表示一个任务
class MyTask implements Comparable<MyTask> {
    //要执行的任务
    private Runnable runnable;
    //什么时间来执行任务
    private long time;

    public MyTask(Runnable runnable,long delay) {
        this.runnable=runnable;
        this.time=System.currentTimeMillis()+delay;
    }

    public Runnable getRunnable() {
        return runnable;
    }

    public long getTime() {
        return time;
    }

    @Override
    public int compareTo(MyTask o) {
        return (int)(this.time-o.time);
    }
}
class MyTimer {
    private BlockingQueue<MyTask> queue=new PriorityBlockingQueue<>();
    //使用locker对象来解决忙等问题
    private Object locker=new Object();
    public void schedule(Runnable runnable,long after) throws InterruptedException {
        MyTask myTask=new MyTask(runnable,after);
        queue.put(myTask);
        //每次插入新的任务都要唤醒扫描线程，让扫描线程能够重新计算wait的时间，保证新的任务也不会错过
        synchronized (locker) {
            locker.notify();
        }
    }
    public MyTimer() {
        //创建一个扫描线程
        Thread t=new Thread(()-> {
            while (true) {
                //取出队首元素
                try {
                    synchronized (locker) {
                        //取出队首元素
                        MyTask task=queue.take();
                        long curTime=System.currentTimeMillis();
                        if(curTime>=task.getTime()) {
                            //到时间执行任务
                            task.getRunnable().run();
                        } else {
                            //没有到时间就再放回队列
                            queue.put(task);
                            //根据时间差进行等待
                            locker.wait(task.getTime()-curTime);
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }
}
public class Demo {
    public static void main(String[] args) throws InterruptedException {
        MyTimer timer=new MyTimer();
        timer.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("时间到1！");
            }
        },3000);
        timer.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("时间到2！");
            }
        },4000);
        timer.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("时间到3！");
            }
        },5000);
        System.out.println("开始计时！");
    }
}
