import javafx.scene.layout.Priority;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Test {
    public static void main(String[] args) {
        //创建一个空的优先级队列，底层默认容量为11
        PriorityQueue<Integer> q1=new PriorityQueue<>();
        //创建一个空的优先级队列，底层容量为initialCapacity
        PriorityQueue<Integer> q2=new PriorityQueue<>(100);

        ArrayList<Integer> list=new ArrayList<>();
        list.add(4);
        list.add(3);
        list.add(2);
        list.add(1);
        //用ArrayList对象来构造一个优先级队列的对象
        PriorityQueue<Integer> q3=new PriorityQueue<>(list);
    }
}
