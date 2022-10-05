import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Demo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //使用Callable定义一个任务
        Callable<Integer> callable=new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                int sum=0;
                for (int i = 0; i <= 1000; i++) {
                    sum+=i;
                }
                return sum;
            }
        };

        FutureTask<Integer> futureTask=new FutureTask<>(callable);

        //创建一个线程，来执行上述任务
        //Thread的构造方法,不能直接传callable,还需要一个中间的类
        Thread t=new Thread(futureTask);
        t.start();

        //获取线程的计算结果
        //get方法会阻塞，直到call方法计算完毕，get才会返回
        System.out.println(futureTask.get());
    }
}
