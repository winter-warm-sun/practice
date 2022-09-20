import java.util.*;
public class Solution {
    public static int StrToInt(String str) {
        double ret=0;
        for(int i=0;i<str.length();i++) {
            char ch=str.charAt(i);
            if(ch>'0'&&ch<'9') {
                ret+=(int)(ch-'0');
                ret*=10;
            }else if(ch=='+'||ch=='-'||ch=='/') {
                continue;
            }else {
                return 0;
            }
        }
        return (int)ret;
    }

    public static void main(String[] args) {
        System.out.println(StrToInt("+2147483647"));
    }
}