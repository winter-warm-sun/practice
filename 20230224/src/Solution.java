class Solution {
    public static int search(int[] nums, int target) {
        int count=0;
        for(int i=0;i<nums.length;i++) {
            if(nums[i]==target) {
                while(i<nums.length&&nums[i]==target) {
                    count++;
                    i++;
                }
                return count;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        int[] arr=new int[]{1};
        System.out.println(search(arr, 1));
    }
}