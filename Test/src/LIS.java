import java.util.Arrays;
public class LIS {
    public static int lengthOfLIS(int[] nums) {
        if(nums==null||nums.length==0) {
            return 0;
        }
        int ans=0;
        for (int i = 0; i < nums.length; i++) {
            ans=Math.max(ans,process(nums,i));
        }
        return ans;
    }

    public static int process(int[] nums,int index) {
        if(index==nums.length) {
            return 0;
        }
        int max=0;
        for (int i = index+1; i < nums.length; i++) {
            if(nums[i]>nums[index]) {
                max=Math.max(max,process(nums,i));
            }
        }
        return max+1;
    }

    public static int dp(int[] nums) {
        int N=nums.length;
        int[] dp=new int[N+1];
        dp[N-1]=1;
        for (int index = N-2; index >=0 ; index--) {
            int max=0;
            for (int i = index+1; i < nums.length; i++) {
                if(nums[i]>nums[index]) {
                    max=Math.max(max,dp[i]);
                }
            }
            dp[index]= max+1;
        }
        Arrays.sort(dp);
        return dp[N];
    }
    public static void main(String[] args) {
        int[] nums=new int[]{10,9,2,5,3,7,101,18};
        System.out.println(lengthOfLIS(nums));
    }
}
