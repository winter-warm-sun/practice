public class CoverToLetterString {
    public static int number(String str) {
        if(str==null||str.length()==0) {
            return 0;
        }
        return process(str.toCharArray(),0);
    }

    public static int process(char[] str,int i) {
        if(i==str.length) {
            return 1;
        }
        if(str[i]=='0') {
            return 0;
        }
        int ways=process(str,i+1);
        if(i+1<str.length&&(str[i]-'a')*10+str[i+1]-'a'<27) {
            ways+=process(str,i+2);
        }
        return ways;
    }

    public static int dp(String s) {
        if(s==null||s.length()==0) {
            return 0;
        }
        char[] str=s.toCharArray();
        int N=str.length;
        int[] dp=new int[N+1];
        dp[N]=1;
        for (int i = N-1; i >=0 ; i--) {
            if(str[i]!='0') {
                int ways=dp[i+1];
                if(i+1<N&&(str[i]-'a')*10+str[i+1]-'a'<27) {
                    ways+=dp[i+2];
                }
                dp[i]=ways;
            }
        }
        return dp[0];
    }
}
