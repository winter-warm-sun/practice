import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Solution {
    public static int[] answerQueries(int[] nums, int[] queries) {
        int[] ret=new int[queries.length];
        for(int i=0;i<queries.length;i++) {
            List<Integer> list=new ArrayList<>();
            for(int j=0;j<nums.length;j++) {
                if(nums[j]<queries[i]) {
                    list.add(nums[j]);
                }
            }
            list.sort(Comparator.comparing(Integer::intValue));
            int sum=0;
            int count=0;
            for (int j = 0; j < list.size(); j++) {
                sum+=list.get(j);
                if(sum<=queries[i]) {
                    count++;
                }
            }
            ret[i]=count;
            if()
            list.clear();
        }
        return ret;
    }

    public static void main(String[] args) {
        int[] nums=new int[]{4,5,2,1};
        int[] querise=new int[]{3,10,21};
        int[]ret=answerQueries(nums,querise);
        System.out.println(Arrays.toString(ret));
    }
}