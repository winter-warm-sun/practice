import java.util.LinkedList;
import java.util.Queue;

public class IsCBT {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static boolean isCBT(Node head) {
        if(head==null) {
            return true; // 如果为空树默认是完全二叉树
        }
        Queue<Node> queue=new LinkedList<>();
        // 是否遇到过左右孩子不全的节点
        boolean leaf=false;
        Node left=null;
        Node right=null;
        queue.offer(head);
        while (!queue.isEmpty()) {
            head=queue.poll();
            left=head.left;
            right=head.right;
            if((leaf&&(left!=null||right!=null))||(left==null&&right!=null)) return false;
            if(left!=null) queue.offer(left);
            if(right!=null) queue.offer(right);
            if(left==null||right==null) leaf=true;
        }
        return true;
    }
}
