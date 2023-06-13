import java.util.HashMap;
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
        for(Map.Entry<Integer,Integer> entry: counts.entrySet()) {
            coins[index]=entry.getKey();
            pages[index++]=entry.getValue();
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

    public static int process(int[] coins,int[] pages,int index,int rest) {
        if(index==coins.length) {
            return rest==0?1:0;
        }
        int ways=0;
        for (int page = 0; page*coins[index]<=rest&&page<=pages[index]  ; page++) {
            ways+=process(coins, pages, index+1, rest-page*coins[index]);
        }
        return ways;
    }

    public static int dp(int[] arr,int aim) {
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
            for (int rest = 0; rest <= aim; rest++) {
                int ways=0;
                for (int page = 0; page*coins[index]<=rest&&page<=pages[index]  ; page++) {
                    ways+=dp[index+1][ rest-page*coins[index]];
                }
                dp[index][rest]= ways;
            }
        }
        return dp[0][aim];
    }
}
