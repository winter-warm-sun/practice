class MyBlockingQueue {
    private int[] items=new int[1000];
    private volatile int head=0;
    private volatile int tail=0;
    private volatile int size=0;

    public void put(int elem) throws InterruptedException {
        synchronized (this) {
            while (size>=items.length) {
                this.wait();
            }
            items[tail]=elem;
            tail++;
            if(tail>=items.length) {
                tail=0;
            }
            size++;
            this.notify();
        }
    }

    public int take() throws InterruptedException {
        synchronized (this) {
            while (size==0) {
                this.wait();
            }
            int ret=items[head];
            head++;
            if(head>=items.length) {
                head=0;
            }
            size--;
            this.notify();
            return ret;
        }
    }
}
public class Demo {
    public static void main(String[] args) throws InterruptedException {
        MyBlockingQueue queue=new MyBlockingQueue();
        Thread producer=new Thread(()-> {
            for (int i = 0; i < 10000; i++) {
                System.out.println("生产元素："+i);
                try {
                    queue.put(i);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        producer.start();
        Thread consumer=new Thread(()-> {
           while (true) {
               try {
                   int elem= queue.take();
                   System.out.println("消费者元素:"+elem);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
        });
        consumer.start();

        producer.join();
        consumer.join();

    }
}
