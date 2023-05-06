public class NextNode {
    public static class Node {
        public int value;
        public Node left;
        public Node right;
        public Node parent;

        public Node(int value) {
            this.value = value;
        }
    }
    public static Node getNextNode(Node node) {
        if(node==null) {
            return node;
        }
        if(node.right!=null) return getLeftMost(node.right);
        else {  // 无右子树
            Node parent=node.parent;
            while (parent!=null&&parent.right==node) {  // 当前节点是其父亲节点的右孩子
                node=parent;
                parent=node.parent;
            }
            // 如果因为parent为null而跳出，说明没有后继节点，该节点即为最右节点。
            return parent;
        }
    }

    public static Node getLeftMost(Node node) {
        if(node==null) {
            return node;
        }
        while (node.left!=null) {
            node=node.left;
        }
        return node;
    }
}
