import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
    public int[] smallestK(int[] arr, int k) {
        int[]ret=new int[k];
        if(k==0) return ret;
        PriorityQueue<Integer> q=new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });
        for (int i = 0; i < k; i++) {
            q.offer(arr[i]);
        }
        for (int i = k; i < arr.length; i++) {
            if(q.peek()>arr[i]) {
                q.poll();
                q.offer(arr[i]);
            }
        }
        for (int i = 0; i < k; i++) {
            ret[i]=q.poll();
        }
        return ret;
    }
}