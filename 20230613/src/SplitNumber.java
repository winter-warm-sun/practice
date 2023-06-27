public class SplitNumber {
    public static int ways(int n) {
        if(n<0) {
            return 0;
        }
        if(n==1) {
            return 1;
        }
        return process(1,n);
    }

    public static int process(int pre, int rest) {
        if(rest==0) {
            return 1;
        }
        if(pre>rest) {
            return 0;
        }
        int ways=0;
        for (int first = pre; first <=rest ; first++) {
            ways+=process(first,rest-first);
        }
        return ways;
    }

    public static int dp1(int n) {
        if(n<0) {
            return 0;
        }
        if(n==1) {
            return 1;
        }
        int[][] dp=new int[n+1][n+1];
        for (int pre = 1; pre <=n ; pre++) {
            dp[pre][0]=1;
            dp[pre][pre]=1;
        }
        for (int pre = n-1; pre >=0 ; pre--) {
            for (int rest = pre+1; rest <=n ; rest++) {
                int ways=0;
                for (int first = pre; first <=rest ; first++) {
                    ways+=dp[first][rest-first];
                }
                dp[pre][rest]=ways;
            }
        }
        return dp[1][n];
    }

}
