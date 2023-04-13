import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class MedianFinder {
    List<Integer> list;
    /** initialize your data structure here. */
    public MedianFinder() {
        list=new ArrayList<>();
    }

    public void addNum(int num) {
        list.add(num);
    }

    public double findMedian() {
        int size=list.size();
        list.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1-o2;
            }
        });
        if(size%2==1) {
            return list.get(size/2);
        }else {
            System.out.println(list.get(size/2-1));
            System.out.println(list.get(size/2));
            return (double) (list.get(size/2-1)+list.get(size/2))/2;
        }
    }

    public static void main(String[] args) {
        MedianFinder m=new MedianFinder();
        m.addNum(1);
        m.addNum(2);
        System.out.println(m.findMedian());
    }
}
