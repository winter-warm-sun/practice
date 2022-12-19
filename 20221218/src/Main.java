import java.util.Scanner;
class Node {
    int data;
    Node left;
    Node right;
    public Node(int data) {
        this.data = data;
    }
}
public class Main {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        String s=scanner.nextLine();
        Node root=new Node(s.charAt(0));
        root.left=new Node(s.charAt(1));
        root.right=new Node(s.charAt(2));

    }
}
