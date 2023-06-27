import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String[] words={"words","frog","sde"};
        List<String> in=new ArrayList<>();
        in.add(words[0]);
        char lastChar=words[0].charAt(words[0].length()-1);
        for(int i=1;i< words.length;i++) {
            boolean found=false;
            for (int j = 1; j < words.length; j++) {
                if(words[j].charAt(0)==lastChar) {
                    in.add(words[j]);
                    lastChar=words[j].charAt(words[j].length()-1);
                    found=true;
                    break;
                }
            }
            if(!found) {
                // 如果没有找到lastChar开头的单词，就顺延一个字母
                lastChar=(char) ((lastChar - 'a' + 1) % 26 + 'a');
                i--;
            }
        }
        for(String s:in) {
            System.out.println(s);
        }
    }
}
// #单词接龙：数组第一个单词的最后一个字母作为ch，查找数据中以ch开头的单词作为第二个，
// 然后再取改单词最后一个字母作为ch，依次处理；如果没有ch开头的单词，则顺延一个，比如
//#a顺延到b，z的下一个字母设定为a
//# 可以新建一个数组，每次操作在数组里新增一个单词，原数组删除一个单词；
//#[words,frog,sde]=>[words,sde,frog]
