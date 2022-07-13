import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree {
    static class TreeNode {
        public char val;
        public TreeNode left;
        public TreeNode right;
        public TreeNode(char val) {
            this.val = val;
        }
    }
    public TreeNode createTree() {
        TreeNode A = new TreeNode('A');
        TreeNode B = new TreeNode('B');
        TreeNode C = new TreeNode('C');
        TreeNode D = new TreeNode('D');
        TreeNode E = new TreeNode('E');
        TreeNode F = new TreeNode('F');
        TreeNode G = new TreeNode('G');
        TreeNode H = new TreeNode('H');
        A.left=B;
        A.right=C;
        B.left=D;
        B.right=E;
        //E.right=H;
        C.left=F;
        C.right=G;
        return A;
    }
    //子问题思路
    int size(TreeNode root) {
        if(root==null) return 0;
        return size(root.left)+size(root.right)+1;
    }
    //遍历思路
    public static int nodeSize;
    void size2(TreeNode root) {
        if(root==null) return; ;
        nodeSize++;
        size2(root.left);
        size2(root.right);
    }
    // 获取叶子节点的个数
    int getLeafNodeCount(TreeNode root){
        if(root==null) return 0;
        if(root.left==null&&root.right==null) return 1;
        return getLeafNodeCount(root.left)+getLeafNodeCount(root.right);
    }
    //遍历思路
    public static int leafSize;
    void getLeafNodeCount2(TreeNode root) {
        if(root==null)return;
        if(root.left==null&&root.right==null)
            leafSize++;
        getLeafNodeCount2(root.left);
        getLeafNodeCount2(root.right);
    }
    // 获取第K层节点的个数
    int getKLevelNodeCount(TreeNode root,int k) {
        if(root==null)return 0;
        if(k==1) return 1;
        return getKLevelNodeCount(root.left,k-1)+getKLevelNodeCount(root.right,k-1);
    }
    // 获取二叉树的高度
    int getHeight(TreeNode root) {
        if(root==null)return 0;
        int leftHeight=getHeight(root.left);
        int rightHeight=getHeight(root.right);
        return (leftHeight>rightHeight?leftHeight+1:rightHeight+1);
    }
    //找关键字
    TreeNode find(TreeNode root, char val) {
        if(root==null)return null;
        if(root.val==val)return root;
        TreeNode ret1=find(root.left,val);
        if(ret1!=null)return ret1;
        TreeNode ret2=find(root.right,val);
        if(ret2!=null)return ret2;
        return null;
    }
    //层序遍历
    void levelOrder(TreeNode root) {
        if(root==null) return;
        Queue<TreeNode> queue=new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode cur=queue.poll();
            System.out.println(cur.val);
            if(cur.left!=null) {
                queue.offer(cur.left);
            }
            if(cur.right!=null) {
                queue.offer(cur.right);
            }
        }
    }
    //判断一棵树是不是完全二叉树
    boolean isCompleteTree(TreeNode root) {
        if(root==null) return true;
        Queue<TreeNode> queue=new LinkedList<>();
        queue.offer(root);
        TreeNode cur=null;
        while (!queue.isEmpty()) {
            cur=queue.poll();
            if(cur!=null) {
                queue.offer(cur.left);
                queue.offer(cur.right);
            } else {
                break;
            }
        }
        while (!queue.isEmpty()) {
            cur=queue.poll();
            if(cur!=null) {
                return false;
            }
        }
        return true;
    }
}
