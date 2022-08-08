public class BinarySearchTree {
    static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public TreeNode root;

    public TreeNode search(int key) {
        TreeNode cur = root;
        while (cur != null) {
            if (cur.val < key) {
                cur = cur.right;
            } else if (cur.val > key) {
                cur = cur.left;
            } else {
                return cur;
            }
        }
        //走到这里说明没有找到关键字
        return null;
    }

    public boolean insert(int key) {
        TreeNode node = new TreeNode(key);
        //空树直接插入
        if (root == null) {
            root = node;
            return true;
        }
        TreeNode cur = root;
        TreeNode parent = null;//用于存储上一个节点的引用
        while (cur != null) {
            if (cur.val < key) {
                parent = cur;
                cur = cur.right;
            } else if (cur.val > key) {
                parent = cur;
                cur = cur.left;
            } else {
                //存在相同的元素 则不能插入成功
                return false;
            }
        }
        //代码走到这里，cur==null，根据与上一个节点parent的val的比较，确定插入该结点的左还是右
        if (parent.val < key) {
            parent.right = node;
        } else {
            parent.left = node;
        }
        return true;
    }

    /**
     * 删除关键字为key的节点
     *
     * @param key
     */
    public void remove(int key) {
        //先找到该节点
        TreeNode cur = root;
        TreeNode parent = null;
        while (cur != null) {
            if (cur.val < key) {
                parent = cur;
                cur = cur.right;
            } else if (cur.val > key) {
                parent = cur;
                cur = cur.left;
            } else {
                //代码走到此处说明找到了要删除的节点
                removeNode(cur, parent);
            }
        }
    }

    /**
     * 进行删除
     *
     * @param cur    要删除的节点
     * @param parent 删除节点的父结点
     */
    private void removeNode(TreeNode cur, TreeNode parent) {
        if (cur.left == null) {
            if (cur == root) {
                root = root.right;
            } else if (cur == parent.left) {
                parent.left = cur.right;
            } else {
                parent.right = cur.right;
            }
        } else if (cur.right == null) {
            if (cur == root) {
                root = root.left;
            } else if (cur == parent.left) {
                parent.left = cur.left;
            } else {
                parent.right = cur.left;
            }
        } else {
            TreeNode targetParent = cur;//记录用于替换的节点的上一个节点
            TreeNode target = cur.right;//记录用于替换的节点
            //在右子树中寻找最小值，就是一直往左走，走到尽头的节点就是最小值
            while (target.left != null) {
                targetParent = target;
                target = target.left;
            }
            //找到用于替换的节点，完成值的替换，再去处理删除这个替换节点
            cur.val = target.val;
            //此时该节点一定没有左子树，与之前没有左子树的删除方法相同
            if (targetParent.left == target) {
                targetParent.left = target.right;
            } else {
                targetParent.right = target.right;
            }
        }
    }
}
