public class StickersToSpellWord {
    public static int minStickers(String[] stickers,String target) {
        int ans=process(stickers,target);
        return ans==Integer.MAX_VALUE?-1:ans;
    }

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
        for(char ch:str1) {
            count[ch-'a']++;
        }
        for (char ch:str2) {
            count[ch-'a']--;
        }
        StringBuilder sb=new StringBuilder();
        for (int i = 0; i < 26; i++) {
            if(count[i]>0) {
                for (int j = 0; j < count[i]; j++) {
                    sb.append((char) (i+'a'));
                }
            }
        }
        return sb.toString();
    }
}
