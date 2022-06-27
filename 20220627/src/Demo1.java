import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Demo1 {
    public static void main(String[] args) {
        ArrayList<Integer> list=new ArrayList<>();//顺序表大小是0
        list.add(1);//大小变为了10
        list.add(2);
        list.add(3);
        //。。。。。。。。当十个放慢以后，需要扩容，扩容采取的是1.5倍的扩容方式
    }
    public static void main2(String[] args) {
        List<Integer> list=new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        //for循环遍历
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i)+" ");
        }
        System.out.println();
        for(Integer integer:list) {
            System.out.println(integer+" ");
        }
        System.out.println();
        //迭代器遍历
        Iterator<Integer> it=list.listIterator();
        while (it.hasNext()) {
            System.out.println(it.next()+" ");
        }
        System.out.println();
    }
    public static void main1(String[] args) {
        // ArrayList创建，推荐写法
        // 构造一个空的列表
        List<Integer> list1=new ArrayList<>();
        List<Integer> list2=new ArrayList<>(10);
        list2.add(1);
        list2.add(2);
        list2.add(3);
        // list3构造好之后，与list2中的元素一致
        ArrayList<Integer> list3=new ArrayList<>();
        list3.add(1);
    }
}
