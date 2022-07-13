import java.util.Scanner;

public class Main {
    class TreeNode {
        char val;
        TreeNode left;
        TreeNode right;

        public TreeNode(char val) {
            this.val=val;
        }
    }
    int i=0;
    TreeNode createTree(String s) {
        TreeNode root=null;
        if(s.charAt(i)!='#') {
            root=new TreeNode(s.charAt(i));
            i++;
            root.left=createTree(s);
            root.right=createTree(s);
        }else {
            i++;
        }
        return root;
    }
    void inOrder(TreeNode root) {
        if (root==null) return;
        inOrder(root.left);
        System.out.println(root.val+" ");
        inOrder(root.right);
    }
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while (scanner.hasNext()) {
            String s=scanner.nextLine();
            Main m=new Main();
            TreeNode root=m.createTree(s);
            m.inOrder(root);
        }
    }
}
