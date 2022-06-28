import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<Integer> list=new ArrayList<>();
        list.add(1);
        List<List<Integer>> ret=new ArrayList<>();
        ret.add(list);
        for(int i=1;i<numRows;i++) {
            List<Integer> list2=new ArrayList<>();
            list2.add(1);
            for(int j=1;j<i;j++) {
                int num=ret.get(i-1).get(j)+ret.get(i-1).get(j-1);
                list2.add(num);
            }
            list2.add(1);
            ret.add(list2);
        }
        Iterator<Integer> iterator;
        Collection
        return ret;
    }
}