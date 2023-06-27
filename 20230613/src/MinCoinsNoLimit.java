public class MinCoinsNoLimit {
    public static int minCoins(int[] arr,int aim) {
        return process(arr,0,aim);
    }

    public static int process(int[] arr,int index,int rest) {
        if(index==arr.length) {
            return rest==0?0:Integer.MAX_VALUE;
        }else {
            int ans=Integer.MAX_VALUE;
            for (int pages = 0; pages*arr[index] <=rest ; pages++) {
                int next=process(arr,index+1,rest-pages*arr[index]);
                if(next!=Integer.MAX_VALUE) {
                    ans=Math.min(ans,pages+next);
                }
            }
            return ans;
        }
    }

    public static int dp1(int[] arr,int aim) {
        if(aim==0) {
            return 0;
        }
        int N=arr.length;
        int[][] dp=new int[N+1][aim+1];
        dp[N][0]=0;
        for (int j = 1; j <=aim ; j++) {
            dp[N][j]=Integer.MAX_VALUE;
        }
        for (int index = N-1; index >=0 ; index--) {
            for (int rest = 0; rest <=aim ; rest++) {
                int ans=Integer.MAX_VALUE;
                for (int pages = 0; pages*arr[index] <=rest ; pages++) {
                    int next=dp[index+1][rest-pages*arr[index]];
                    if(next!=Integer.MAX_VALUE) {
                        ans=Math.min(ans,pages+next);
                    }
                }
                dp[index][rest]= ans;
            }
        }
        return dp[0][aim];
    }

    public static int dp2(int[] arr,int aim) {
        if(aim==0) {
            return 0;
        }
        int N=arr.length;
        int[][] dp=new int[N+1][aim+1];
        dp[N][0]=0;
        for (int j = 1; j <= aim; j++) {
            dp[N][j]=Integer.MAX_VALUE;
        }
        for (int index = N-1; index >=0 ; index--) {
            for (int rest = 0; rest <=aim ; rest++) {
                dp[index][rest]=dp[index+1][rest];
                if(rest-arr[index]>=0&&
                dp[index][rest-arr[index]]!=Integer.MAX_VALUE) {
                    dp[index][rest]=Math.min(dp[index][rest-arr[index]]+1,dp[index][rest]);
                }
            }
        }
        return dp[0][aim];
    }
}
