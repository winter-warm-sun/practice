import java.util.*;

public class MaxLoad {
    static class Container {
        public int weight;
        public int index;

        public Container(int weight, int index) {
            this.weight = weight;
            this.index = index;
        }
    }
    // w:存储每个物品的重量 下标为集装箱编号
    // C:轮船载重量
    public static List<Integer> load(int[] w, int C) {
        List<Integer> list=new ArrayList<>();// 用于记录返回集装箱编号的list
        List<Container> clist=new ArrayList<>();
        if(w==null||C<=0) {
            return list;
        }

        for (int i = 0; i < w.length; i++) {
            Container container=new Container(w[i],i);
            clist.add(container);
        }
        clist.sort(new Comparator<Container>() {
            @Override
            public int compare(Container o1, Container o2) {
                return o1.weight-o2.weight;
            }
        });
        int curLoad=0;
        for (int i = 0; i < clist.size(); i++) {
            Container container=clist.get(i);
            if(curLoad+ container.weight<=C) {
                curLoad+=container.weight;
                list.add(container.index);
            }else break;
        }
        return list;
    }
}
