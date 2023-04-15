public class TrieTree {
    class Node {
        public int pass;
        public int end;
        public Node[] nexts;

        public Node() {
            pass=0;
            end=0;
            nexts=new Node[26];
        }
    }

    private Node root;

    public TrieTree() {
        root=new Node();
    }

    public void insert(String word) {
        if(word==null) {
            return;
        }
        char[] str=word.toCharArray();
        Node node=root;
        node.pass++;
        int path=0;
        for (int i = 0; i < str.length; i++) { // 从左往右遍历字符
            path=str[i]-'a'; // 由字符决定对应走哪条路
            if(node.nexts[path]==null) {
                node.nexts[path]=new Node();
            }
            node=node.nexts[path];
            node.pass++;
        }
        node.end++;
    }
    // word这个单词加入过几次
    public int search(String word) {
        if(word==null) {
            return 0;
        }
        char[] chs=word.toCharArray();
        Node node=root;
        int index=0;
        for (int i = 0; i < chs.length; i++) {
            index=chs[i]-'a';
            if(node.nexts[index]==null) {
                return 0;
            }
            node=node.nexts[index];
        }
        return node.end;
    }

    public void delete(String word) {
        if(search(word)!=0) {
            char[] chs=word.toCharArray();
            Node node=root;
            node.pass--;
            int path=0;
            for (int i = 0; i < chs.length; i++) {
                path=chs[i]-'a';
                if(--node.nexts[path].pass==0) {
                    node.nexts[path]=null;
                    return;
                }
                node=node.nexts[path];
            }
            node.end--;
        }
    }
    public int startWith(String prefix) {
        if(prefix==null) {
            return 0;
        }
        char[] chs=prefix.toCharArray();
        Node node=root;
        int index=0;
        for (int i = 0; i < chs.length; i++) {
            index=chs[i]-'a';
            if(node.nexts[index]==null) {
                return 0;
            }
            node=node.nexts[index];
        }
        return node.pass;
    }
}
