import java.util.ArrayList;
import java.util.List;

class Solution {
    public char firstUniqChar(String s) {
        char ch = ' ';
        if (s == "") {
            return ch;
        }
        List<Character> list = new ArrayList<>();
        list.add(s.charAt(0));
        int index = 1;
        while (index < s.length()) {
            if (list.contains(s.charAt(index))) {
                list.set(list.indexOf(s.charAt(index)),' ' );
            }else {
                list.add(s.charAt(index));
            }
            index++;
        }
        for (char c:list) {
            if(c!=' ') {
                ch=c;
                break;
            }
        }
        return ch;
    }
}