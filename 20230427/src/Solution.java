import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Solution {
    List<String> res=new LinkedList<>();
    public String[] permutation(String s) {
        StringBuffer track=new StringBuffer();
        char[] nums=s.toCharArray();
        boolean[] used=new boolean[nums.length];
        Arrays.sort(nums);
        backtrack(nums,track,used);
        return res.toArray(new String[0]);
    }
    void backtrack(char[] nums,StringBuffer track,boolean[] used) {
        if(track.length()==nums.length) {
            res.add(track.toString());
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if(used[i]) {
                continue;
            }
            if(i>0&&nums[i]==nums[i-1]&&!used[i-1]) {
                continue;
            }
            track.append(nums[i]);
            used[i]=true;
            backtrack(nums,track,used);
            track.deleteCharAt(track.length()-1);
            used[i]=false;
        }
    }
}