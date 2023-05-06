package LRU;

public class Node {
    public int val;
    public int key;
    public Node next,prev;

    public Node(int val, int key) {
        this.val = val;
        this.key = key;
    }
}
