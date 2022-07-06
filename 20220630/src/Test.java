import java.util.LinkedList;
import java.util.ListIterator;

public class Test {
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1); // add(elem): 表示尾插
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        System.out.println(list.size());
        // foreach遍历
        for (int e:list) {
            System.out.print(e + " ");
        }
        System.out.println();
        // 使用迭代器遍历---正向遍历
        ListIterator<Integer> it = list.listIterator();
        while(it.hasNext()){
            System.out.print(it.next()+ " ");
        }
        System.out.println();
        // 使用反向迭代器---反向遍历
        ListIterator<Integer> rit = list.listIterator(list.size());
        while (rit.hasPrevious()){
            System.out.print(rit.previous() +" ");
        }
        System.out.println();
    }
}
