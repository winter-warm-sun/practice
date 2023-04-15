public class Main {
    static class Node {
        public int pass;
        public int end;
        public TrieTree.Node[] nexts;

        public Node() {
            pass=0;
            end=0;
            nexts=new TrieTree.Node[26];
        }
    }

    public static void main(String[] args) {
        Node root=new Node();
        System.out.println(root.nexts[0]);
    }
}
