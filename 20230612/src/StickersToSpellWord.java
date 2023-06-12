public class StickersToSpellWord {
    public static int minSticker1(String[] stickers,String target) {
        int ans=process(stickers,target);
        return ans==Integer.MAX_VALUE?-1:ans;
    }

    // 所有贴纸stickers,每一种贴纸都有无穷张
    // target
    // 最少张数
    public static int process(String[] stickers,String target) {
        if(target.length()==0) {
            return 0;
        }
        int min=Integer.MAX_VALUE;
        for (String first:stickers) {
            String rest=minus(target,first);
            if(rest.length()!=target.length()) {
                min=Math.min(min,process(stickers,rest));
            }
        }
        return min+(min==Integer.MAX_VALUE?0:1);
    }

    public static String minus(String s1,String s2) {
        char[] str1=s1.toCharArray();
        char[] str2=s2.toCharArray();
        int[] count=new int[26];
        for (char ch:str1) {
            count[ch-'a']++;
        }
        for (char ch:str2) {
            count[ch-'a']--;
        }
        StringBuilder builder=new StringBuilder();
        for (int i = 0; i < 26; i++) {
            if(count[i]>0) {
                for (int j = 0; j < count[i]; j++) {
                    builder.append((char) (i+'a'));
                }
            }
        }
        return builder.toString();
    }

    public static int minStickers2(String[] stickers,String target) {
        int N=stickers.length;
        // 关键优化（用词频表代替贴纸数组）
        int[][] counts=new int[N][26];
        for (int i = 0; i < N; i++) {
            char[] str=stickers[i].toCharArray();
            for (char ch:str) {
                counts[i][ch-'a']++;
            }
        }
        int ans=process2(counts,target);
        return ans==Integer.MAX_VALUE?-1:ans;
    }

    // stickers[i]数组
    // 每种贴纸都有无穷张
    // 返回搞定target的最少张数
    public static int process2(int[][] stickers,String t) {
        if(t.length()==0) {
            return 0;
        }
        // target做出词频统计
        char[] target=t.toCharArray();
        int[] tcounts=new int[26];
        for (char ch:target) {
            tcounts[ch-'a']++;
        }
        int N=stickers.length;
        int min=Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            // 尝试第一张贴纸是谁
            int[] sticker=stickers[i];
            // 剪枝优化
            if(sticker[target[0]-'a']>0) {  // 如果目标target中的字符在该贴纸中存在
                StringBuilder builder=new StringBuilder();
                for (int j = 0; j < 26; j++) {
                    if(tcounts[j]>0) {
                        int nums=tcounts[j]-sticker[j];
                        for (int k = 0; k < nums; k++) {
                            builder.append((char) j+'a');
                        }
                    }
                }
                String rest=builder.toString();
                min=Math.min(min,process2(stickers,rest));
            }
        }
        return min+(min==Integer.MAX_VALUE?0:1);
    }
}
