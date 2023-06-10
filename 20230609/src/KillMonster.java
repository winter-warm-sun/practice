public class KillMonster {
    public static double right(int N,int M,int K) {
        if(N<1 || M<1 || K<1) {
            return 0;
        }
        long all=(long) Math.pow(M+1,K);
        long kill=process(K,M,N);
        return (double) ((double)kill/(double) all);
    }
    // 怪兽还剩hp点血
    // 每次的伤害范围为0~M
    // 还有times可以砍
    // 返回砍死的情况数
    public static long process(int times,int M,int hp) {
        if (times == 0) {
            return hp <= 0 ? 1 : 0;
        }
        if(hp<=0) {
            return (long) Math.pow(M+1,times);
        }
        long ways=0;
        for (int i = 0; i <=M ; i++) {
            ways+=process(times-1,M,hp-i);
        }
        return ways;
    }

    public static double dp1(int N,int M,int K) {
        if(N<1 || M<1 || K<1) {
            return 0;
        }
        long all=(long) Math.pow(M+1,K);
        long[][] dp=new long[K+1][N+1];
        dp[0][0]=1;
        for (int times = 1; times <=K ; times++) {
            dp[times][0]=(long) Math.pow(M+1,times);
            for (int hp = 1; hp <=N ; hp++) {
                long ways=0;
                for (int i = 0; i <=M ; i++) {
                    if(hp-i>=0) {
                        ways+=dp[times-1][hp-i];
                    }else {  // 当血量为负的时候，情况数直接就是下边的公式
                        ways+=(long) Math.pow(M+1,times-1);
                    }
                }
                dp[times][hp]=ways;
            }
        }
        long kill=dp[K][N];
        return (double) ((double)kill/(double) all);
    }

    // 枚举优化
    public static double dp2(int N,int M,int K) {
        if(N<1 || M<1 ||K<1) {
            return 0;
        }
        long all=(long) Math.pow(M+1,K);
        long[][] dp=new long[K+1][N+1];
        dp[0][0]=1;
        for (int times = 1; times <=K ; times++) {
            dp[times][0]=(long) Math.pow(M+1,times);
            for (int hp = 1; hp <=N ; hp++) {
                dp[times][hp]=dp[times][hp-1]+dp[times-1][hp];
                if(hp-1-M>=0) {// 没有越界
                    dp[times][hp]-=dp[times-1][hp-1-M];
                }else {
                    dp[times][hp]-=Math.pow(M+1,times-1);
                }
            }
        }
        long kill=dp[K][N];
        return (double) ((double)kill/(double) all);
    }
}
