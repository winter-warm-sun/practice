import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Solution {
    public int[] getLeastNumbers(int[] arr, int k) {
        List<Integer> list=new ArrayList<>();
        for(int i=0;i<arr.length;i++) {
            list.add(arr[i]);
        }
        list.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1-o2;
            }
        });
        int[] res=new int[k];
        for(int i=0;i<k;i++) {
            res[i]=list.get(i);
        }
        return res;
    }
}