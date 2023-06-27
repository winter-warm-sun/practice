public class SplitSumClosed {
    public static int right(int[] arr) {
        if(arr==null||arr.length<2) {
            return 0;
        }
        int sum=0;
        for(int num:arr) {
            sum+=num;
        }
        return process(arr,0,sum/2);
    }

    public static int process(int[] arr, int i, int rest) {
        if(i==arr.length) {
            return 0;
        }else {
            int p1=process(arr,i+1,rest);
            int p2=0;
            if(arr[i]<=rest) {
                p2=arr[i]+process(arr,i+1,rest-arr[i]);
            }
            return Math.max(p1,p2);
        }
    }

    public static int dp(int[] arr) {
        if(arr==null||arr.length<2) {
            return 0;
        }
        int sum=0;
        for(int num:arr) {
            sum+=num;
        }
        sum/=2;
        int N=arr.length;
        int[][] dp=new int[N+1][sum+1];
        for (int i = N-1; i >=0 ; i--) {
            for (int rest = 0; rest <=N ; rest++) {
                int p1=dp[i+1][rest];
                int p2=0;
                if(arr[i]<=rest) {
                    p2=arr[i]+dp[i+1][rest-arr[i]];
                }
                dp[i][rest]=Math.max(p1,p2);
            }
        }
        return dp[0][sum];
    }
}
