import java.util.ArrayList;
import java.util.List;

/**
 * Definition for singly-linked list.
 * */

class ListNode {
   int val;
   ListNode next;
   ListNode(int x) { val = x; }
}

class Solution {
    public int[] reversePrint(ListNode head) {
        List<Integer> list=new ArrayList<>();
        while(head!=null) {
            list.add(head.val);
            head=head.next;
        }
        int[] arr=new int[list.size()];
        int index=0;
        for (int i=list.size()-1;i>0;i--) {
            arr[index++]=list.get(i);
        }
        return arr;
    }
}