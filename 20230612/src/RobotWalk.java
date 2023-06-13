public class RobotWalk {
    public static int ways(int N,int start,int aim,int k) {
        return process(start,k,aim,N);
    }

    public static int process(int cur,int rest,int aim,int N) {
        if(rest==0) {
            return cur==aim?1:0;
        }
        if(cur==1) {
            return process(2,rest-1,aim,N);
        }
        if(cur==N) {
            return process(N-1,rest-1,aim,N);
        }
        return process(cur-1,rest-1,aim,N)+process(cur+1,rest-1,aim,N);
    }

    public static int ways2(int N,int start,int aim,int k) {
        int[][] dp=new int[N+1][k+1];
        for (int i = 0; i <=N ; i++) {
            for (int j = 0; j <=k ; j++) {
                dp[i][j]=-1;
            }
        }
        return process2(start,k,aim,N,dp);
    }

    public static int process2(int cur,int rest,int aim,int N,int[][] dp) {
        if(dp[cur][rest]!=-1) {
            return dp[cur][rest];
        }
        int ans=0;
        if(rest==0) {
            ans=cur==aim?1:0;
        }else if (cur==1) {
            ans=process2(2,rest-1,aim,N,dp);
        }else if (cur==N) {
            ans=process2(N-1,rest-1,aim,N,dp);
        }else {
            ans=process2(cur-1,rest-1,aim,N,dp)+process2(cur+1,rest-1,aim,N,dp);
        }
        dp[cur][rest]=ans;
        return ans;
    }

    public static int ways3(int N,int start,int aim,int k) {
        int[][] dp=new int[N+1][k+1];
        dp[aim][0]=1;
        for (int rest = 1; rest <=k; rest++) {
            dp[1][rest]=dp[2][rest-1];
            for (int cur = 2; cur <N ; cur++) {
                dp[cur][rest]=dp[cur-1][rest-1]+dp[cur+1][rest-1];
            }
            dp[N][rest]=dp[N-1][rest-1];
        }
        return dp[start][k];
    }
}
