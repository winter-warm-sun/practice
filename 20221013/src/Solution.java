import java.util.Arrays;

public class Solution {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param numbers int整型一维数组
     * @return int整型
     */
    public static int MoreThanHalfNum_Solution (int[] numbers) {
        // write code here
        int[] count=new int[10000];
        int length=numbers.length;
        for(int i=0;i<length;i++) {
            count[numbers[i]]++;
        }
        int[] count2=Arrays.copyOf(count,count.length);
        Arrays.sort(count);
        int max=count[count.length-1];
        for (int i = 0; i < count2.length; i++) {
            if(count2[i]==max) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums=new int[]{1,2,3,2,2,2,5,4,2};
        MoreThanHalfNum_Solution(nums);
    }
}
