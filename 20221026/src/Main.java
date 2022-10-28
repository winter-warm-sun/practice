import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        String s=in.nextLine();
        String[] ss=s.split("-");
        String player1=ss[0];
        String player2=ss[1];
        String[] s1=player1.split(" ");
        String[] s2=player2.split(" ");
        String table="34567891JQKA2";
        if(ss[0].equals("joker JOKER")||ss[1].equals("joker JOKER")) {
            System.out.print("joker JOKER");
            return;
        }
        if(s1.length==s2.length) {
            String one=player1.substring(0,1);
            String two=player2.substring(0,1);
            String end=table.indexOf(one)>table.indexOf(two)?player1:player2;
            System.out.print(end);
        }else if(s1.length==4){
            System.out.print(player1);
        }else if(s2.length==4) {
            System.out.print(player2);
        }else {
            System.out.print("ERROR");
        }
    }
}