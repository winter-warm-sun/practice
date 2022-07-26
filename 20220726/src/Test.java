import java.util.*;

public class Test {
    public static void main(String[] args) {
        int[] array=new int[10_0000];
        Random random=new Random();
        //随机生成十万个数字（给定范围5000，一定有重复的数字）
        for (int i = 0; i < array.length; i++) {
            array[i]=random.nextInt(5_000);
        }
        func1(array);
        System.out.println(func2(array));
        func3(array);
    }
    /**
     * 删除重复元素 直接用Set集合
     * @param array
     */
    public static void func1(int[] array) {
        Set<Integer> set=new HashSet<>();
        for (int i = 0; i < array.length; i++) {
            set.add(array[i]);
        }
        System.out.println(set);
    }

    /**
     * 找第一个重复出现的数值
     * @param array
     */
    public static int func2(int[] array) {
        Set<Integer> set=new HashSet<>();
        for (int i = 0; i < array.length; i++) {
            //第一次出现，就将其添加到集合中；若不是第一次出现，则返回该值
            if(!set.contains(array[i])) {
                set.add(array[i]);
            }else {
                return array[i];
            }
        }
        return -1;
    }

    /**
     * 统计每个数值出现的次数
     * @param array
     */
    public static void func3(int[] array) {
        Map<Integer,Integer> map=new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            int key=array[i];
            //第一次出现，Value值为1；再重复出现时，将Value值加1
            if(map.get(key)==null) {
                map.put(key,1);
            }else {
                int val=map.get(key);
                map.put(key,val+1);
            }
        }
        System.out.println(map);
    }
    public static void main2(String[] args) {
        Map<String,Integer> map=new HashMap<>();
        map.put("abc",10);
        map.put("hello",3);
        map.put("double",5);
        Set<Map.Entry<String,Integer>> entrySet=map.entrySet();
        for (Map.Entry<String,Integer> entry:entrySet) {
            System.out.println("key:"+entry.getKey()+"  val:"+entry.getValue());
        }
    }

    public static void main1(String[] args) {
        Map<String,Integer> map1=new TreeMap<>();
        Map<String,Integer> map2=new HashMap<>();
        Set<String> set1=new TreeSet<>();
        Set<String> set2=new HashSet<>();
    }
}
