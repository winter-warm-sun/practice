import java.util.Random;

public class Demo2 {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        int total = 1000_0000;
        int[] arr = new int[total];
        Random random = new Random();
        for (int i = 0; i < total; i++) {
            arr[i] = random.nextInt(100) + 1;
        }
        SumOperator sumOperator = new SumOperator();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < total; i += 2) {
                sumOperator.addEvenSum(arr[i]);
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 1; i < total; i += 2) {
                sumOperator.addOddSum(arr[i]);
            }
        });
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("结果为:");
        sumOperator.result();
        System.out.println("总时间为：" + (end - start) + "ms.");
    }
}

class SumOperator {
    long evenSum;
    long oddSum;

    public void addEvenSum(int a) {
        evenSum += a;
    }

    public void addOddSum(int a) {
        oddSum += a;
    }

    public void result() {
        System.out.println("偶数下标元素和为：" + evenSum);
        System.out.println("奇数下标元素和为：" + oddSum);
        System.out.println("总和为：" + (evenSum + oddSum));
    }
}
