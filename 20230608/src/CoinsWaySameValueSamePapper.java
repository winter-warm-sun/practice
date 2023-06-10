import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class CoinsWaySameValueSamePapper {
    public static class Info {
        public int[] coins;
        public int[] pages;

        public Info(int[] coins, int[] pages) {
            this.coins = coins;
            this.pages = pages;
        }
    }

    public static Info getInfo(int[] arr) {
        HashMap<Integer,Integer> counts=new HashMap<>();
        for (int value:arr) {
            if(!counts.containsKey(value)) {
                counts.put(value,1);
            }else {
                counts.put(value,counts.get(value)+1);
            }
        }
        int N=counts.size();
        int[] coins=new int[N];
        int[] pages=new int[N];
        int index=0;
        for (Map.Entry<Integer,Integer> entry: counts.entrySet()) {
            coins[index]=entry.getKey();
            pages[index++]= entry.getValue();
        }
        return new Info(coins,pages);
    }

    public static int coinsWay(int[] arr,int aim) {
        if(arr==null||arr.length==0||aim<0) {
            return 0;
        }
        Info info=getInfo(arr);
        return process(info.coins,info.pages,0,aim);
    }

    // coins 面值数组，正数且去重
    // pages 每种面值对应的张数
    public static int process(int[] coins,int[] pages,int index,int rest) {
        if(index==coins.length) {
            return rest==0?1:0;
        }
        int ways=0;
        for (int page=0;page*coins[index]<=rest&&page<=pages[index];page++) {
            ways+=process(coins,pages,index+1,rest-(page*coins[index]));
        }
        return ways;
    }

    public static int dp1(int[] arr,int aim) {
        if(arr==null||arr.length==0||aim<0) {
            return 0;
        }
        Info info=getInfo(arr);
        int[] coins= info.coins;
        int[] pages= info.pages;
        int N=coins.length;
        int[][] dp=new int[N+1][aim+1];
        dp[N][0]=1;
        for (int index = N-1; index >=0 ; index--) {
            for (int rest = 0; rest <=aim ; rest++) {
                int ways=0;
                for (int page = 0; page*coins[index]<=rest&&page<=pages[index] ; page++) {
                    ways+=dp[index+1][rest-page*coins[index]];
                }
                dp[index][rest]=ways;
            }
        }
        return dp[0][aim];
    }

    public static int dp2(int[] arr,int aim) {
        if(arr==null||arr.length==0||aim<0) {
            return 0;
        }
        Info info=getInfo(arr);
        int[] coins= info.coins;
        int[] pages=info.pages;
        int N=coins.length;
        int[][] dp=new int[N+1][aim+1];
        dp[N][0]=1;
        for (int index = N-1; index >=0 ; index--) {
            for (int rest = 0; rest <=aim ; rest++) {
                dp[index][rest]=dp[index+1][rest];
                if(rest-coins[index]>=0) {
                    dp[index][rest]+=dp[index][rest-coins[index]];
                }
                // 如果记忆化搜索可能会多算，再把多算的减掉即可
                if(rest-coins[index]*(pages[index]+1)>=0) {
                    dp[index][rest]-=dp[index+1][rest-coins[index]*(pages[index]+1)];
                }
            }
        }
        return dp[0][aim];
    }
}
