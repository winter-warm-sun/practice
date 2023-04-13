class Solution {
    public static int[] exchange(int[] nums) {
        int i=0,j=nums.length-1;
        while(i<j) {
            while(nums[i]%2==1&&i<j) {
                i++;
            }
            while(nums[j]%2==0&&i<j) {
                j--;
            }
            swap(nums,i,j);
            i++;
            j--;
        }
        return nums;
    }

    static void swap(int[] nums,int i,int j) {
        int tmp=nums[i];
        nums[i]=nums[j];
        nums[j]=tmp;
    }

    public static void main(String[] args) {
        int[] nums=new int[]{1,2,3,4};
        exchange(nums);
        for(int num:nums) {
            System.out.print(num+" ");
        }
    }
}