import java.util.HashMap;
import java.util.Map;

public class Main2 {
    public static void main(String[] args) {
        int[] nums=new int[]{2,5,7,3,1,9,6,0,5,1};
        for (int i = 0; i < nums.length-1; i++) {
            boolean flag=false;
            for (int j = 0; j < nums.length-1; j++) {
                if(nums[j]>nums[j+1]) {
                    swap(nums,j,j+1);
                    flag=true;
                }
            }
            if(!flag) {
                break;
            }
        }
        for (int num:nums) {
            System.out.print(num+" ");
        }
    }

    public static void swap(int[] nums,int i,int j) {
        int tmp=nums[i];
        nums[i]=nums[j];
        nums[j]=tmp;
    }
}
// 两数之和：给定一个整型数组 是否能找出其中的两个数使得其和为某个指定的值