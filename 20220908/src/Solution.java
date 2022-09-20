import java.util.HashMap;

 class TreeNode {
   int val;
   TreeNode left;
   TreeNode right;
   TreeNode() {}
   TreeNode(int val) { this.val = val; }
   TreeNode(int val, TreeNode left, TreeNode right) {
         this.val = val;
          this.left = left;
         this.right = right;
   }
 }
public class Solution {

    public static void main(String[] args) {
        int[] preorder=new int[]{3,9,20,15,7};
        int[] inorder=new int[]{9,3,15,20,7};
        Solution solution=new Solution();
        solution.buildTree(preorder,inorder);
    }
    HashMap<Integer,Integer> map=new HashMap<>();
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for(int i=0;i<inorder.length;i++) {
            map.put(inorder[i],i);
        }
        return build(preorder,0,preorder.length-1,inorder,0,inorder.length-1);
    }
    TreeNode build(int[] preorder,int preStart,int preEnd,int[] inorder,int inStart,int inEnd) {
        if(preStart>preEnd) {
            return null;
        }
        int rootVal=preorder[preStart];
        int index=map.get(rootVal);
        int leftSize=index-inStart;
        TreeNode root=new TreeNode(rootVal);
        root.left=build(preorder,preStart+1,preStart+leftSize,inorder,inStart,index-1);
        root.right=build(preorder,preStart+leftSize+1,preEnd,inorder,index+1,inEnd);
        return root;
    }
}