import java.util.*;

public class Solution {
    public static List<Integer> findAnagrams(String s, String p) {
        int len=p.length();
        char[] arr=p.toCharArray();
        Arrays.sort(arr);
        p=new String(arr);
        List<Integer> list=new ArrayList();
        for(int i=0;i<s.length();i++) {
            String q=new String();
            if(i+len<s.length()) {
                q=p.substring(i,i+len);
            }
            if(p.equals(q)) {
                list.add(i);
            }
        }
        return list;
    }

    public static void main(String[] args) {
        System.out.println(findAnagrams("cbaebabacd","abc"));
    }
}