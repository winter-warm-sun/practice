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
class Solution {
    public String tree2str(TreeNode root) {

    }
    public String preOrder(TreeNode root) {
        if(root==null) return "()";
        StringBuffer sb=new StringBuffer();
        sb.append("("+root.val+")");
        sb.append(preOrder(root.left));
        sb.append(preOrder(root.right));
        return sb.toString();
    }
}