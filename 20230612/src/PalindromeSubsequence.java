public class PalindromeSubsequence {
    public static int lpsl(String s) {
        if(s==null||s.length()==0) {
            return 0;
        }
        char[] str=s.toCharArray();
        return f(str,0,str.length-1);
    }
    // str[L...R] 最长回文子序列长度返回
    public static int f(char[] str,int L,int R) {
        if(L==R) {
            return 1;
        }
        if(L==R-1) {
            return str[L]==str[R]?2:1;
        }
        int p1=f(str,L+1,R-1);
        int p2=f(str,L,R-1);
        int p3=f(str,L+1,R);
        int p4=str[L]!=str[R]?0:(2+f(str,L+1,R-1));
        return Math.max(Math.max(p1,p2),Math.max(p3,p4));
    }

    public static int dp(String s) {
        if(s==null||s.length()==0) {
            return 0;
        }
        if(s.length()==1) {
            return 1;
        }
        char[] str=s.toCharArray();
        int N=str.length;
        int[][] dp=new int[N][N];
        dp[N-1][N-1]=1;
        for (int i = 0; i <N-1 ; i++) {
            dp[i][i]=1;
            dp[i][i+1]=str[i]==str[i+1]?2:1;
        }

        for (int i = N-3; i >=0 ; i--) {
            for (int j = N+2; j <N ; j++) {
                dp[i][j]=Math.max(dp[i][j-1],dp[i+1][j]);
                if(str[i]==str[j]) {
                    dp[i][j]=Math.max(dp[i][j],2+dp[i+1][j-1]);
                }
            }
        }
        return dp[0][N-1];
    }
}
