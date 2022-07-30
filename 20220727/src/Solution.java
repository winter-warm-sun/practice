import java.util.*;

class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        Map<String,Integer> map=new HashMap<>();
        for(String s:words) {
            if(map.get(s)==null) {
                map.put(s,1);
            }else {
                int count=map.get(s);
                map.put(s,count+1);
            }
        }
        PriorityQueue<Map.Entry<String,Integer>> minHeap=new PriorityQueue<>(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                if(o1.getValue().compareTo(o2.getValue())==0) {
                    return o2.getKey().compareTo(o1.getKey());
                }
                return o1.getValue()-o2.getValue();
            }
        });
        for (Map.Entry<String,Integer> m: map.entrySet()) {
            if(minHeap.size()<k) {
                minHeap.offer(m);
            }else {
                Map.Entry<String,Integer> top=minHeap.peek();
                if(top.getValue()==m.getValue()&&m.getKey().compareTo(top.getKey())<0) {
                    minHeap.poll();
                    minHeap.offer(m);
                }
                if(m.getValue()>top.getValue()) {
                    minHeap.poll();
                    minHeap.offer(m);
                }
            }
        }
        List<String> list=new ArrayList<>();
        for (int i = 0; i < k; i++) {
            list.add(minHeap.poll().getKey());
        }
        Collections.reverse(list);
        return list;
    }
}