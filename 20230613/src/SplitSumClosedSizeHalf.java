public class SplitSumClosedSizeHalf {
    public static int right(int[] arr) {
        if(arr==null||arr.length<2) {
            return 0;
        }
        int sum=0;
        for (int num:arr) {
            sum+=num;
        }
        if(((arr.length&1)==0)) {
            return process(arr,0,arr.length/2,sum/2);
        }else {
            return Math.max(process(arr,0,arr.length/2,sum/2),process(arr,0,arr.length/2+1,sum/2));
        }
    }

    private static int process(int[] arr, int i, int picks, int rest) {
        if(i==arr.length) {
            return picks==0?0:-1;
        }else {
            int p1=process(arr,i+1,picks,rest);
            int p2=-1;
            int next=-1;
            if(arr[i]<=rest) {
                next+=process(arr, i+1, picks-1, rest-arr[i]);
            }
            if(next!=-1) {
                p2=arr[i]+next;
            }
            return Math.max(p1,p2);
        }
    }

    public static int dp(int[] arr) {
        if(arr==null||arr.length<2) {
            return 0;
        }
        int sum=0;
        for (int num:arr) {
            sum+=num;
        }
        sum/=2;
        int N=arr.length;
        int M=(N+1)/2;
        int[][][] dp=new int[N+1][M+1][sum+1];
        for (int i = 0; i <=N; i++) {
            for (int j = 0; j <=M ; j++) {
                for (int k = 0; k <=sum ; k++) {
                    dp[i][j][k]=-1;
                }
            }
        }
        for (int rest = 0; rest <=sum ; rest++) {
            dp[N][0][rest]=0;
        }
        for (int i = N-1; i >=0 ; i--) {
            for (int picks = 0; picks <=M ; picks++) {
                for (int rest = 0; rest <=sum ; rest++) {
                    int p1=dp[i+1][picks][rest];
                    int p2=-1;
                    int next=-1;
                    if(arr[i]<=rest) {
                        next+=dp[i+1][picks-1][rest-arr[i]];
                    }
                    if(next!=-1) {
                        p2=arr[i]+next;
                    }
                    dp[i][picks][rest]= Math.max(p1,p2);
                }
            }
        }
        if((arr.length&1)==0) {
            return dp[0][arr.length/2][sum];
        }else {
            return Math.max(dp[0][arr.length/2][sum],dp[0][arr.length/2+1][sum]);
        }
    }
}
