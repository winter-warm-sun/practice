public class CoverToLetterString {
    // str只含有数字字符0~9
    // 返回多少种转化方案
    public static int number(String str) {
        if(str==null||str.length()==0) {
            return 0;
        }
        return process(str.toCharArray(),0);
    }
    // str[0..i-1] 转化无需过问
    // str[i...]去转化，返回有多少种转化方法
    public static int process(char[] str, int i) {
        if(i==str.length) {  // 如果到最后，就是一种转化方法
            return 1;
        }
        // i没到最后，说明有字符
        if(str[i]=='0') {  // 之前的决定有问题
            return 0;
        }
        // str[i]!='0'
        // 可能性1，i单独转化
        int ways=process(str,i+1);
        // 可能性2，i和i+1一同转化
        if(i+1<str.length&&(str[i]-'0')*10+str[i+1]-'0'<27) {
            ways+=process(str,i+2);
        }
        return ways;
    }

    public static int dp(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str=s.toCharArray();
        int N= str.length;
        int[] dp=new int[N+1];
        dp[N]=1;
        for (int i = N-1; i >=0 ; i--) {
            if(str[i]!='0') {
                int ways=dp[i+1];
                if(i+1<str.length&&(str[i]-'0')*10+str[i+1]-'0'<27) {
                    ways+=dp[i+2];
                }
                dp[i]=ways;
            }
        }
        return dp[0];
    }

    public static void main(String[] args) {
        System.out.println(number("23451203"));
        System.out.println(dp("23451203"));
    }
}
