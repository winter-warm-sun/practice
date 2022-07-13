public class Test {
    public static void main(String[] args) {
        BinaryTree binaryTree=new BinaryTree();
        BinaryTree.TreeNode root=binaryTree.createTree();
        System.out.println(binaryTree.getLeafNodeCount(root));
        binaryTree.getLeafNodeCount2(root);
        System.out.println(BinaryTree.leafSize);
        System.out.println(binaryTree.getHeight(root));
        if(binaryTree.find(root,'E')!=null)
            System.out.println("true");
        binaryTree.levelOrder(root);
        System.out.println(binaryTree.isCompleteTree(root));
    }
}
