import java.util.Comparator;
import java.util.Objects;
import java.util.PriorityQueue;

public class Card {
    public int rank;//数字
    public String suit;//花色

    public Card(int rank,String suit) {
        this.rank=rank;
        this.suit=suit;
    }

    static class RankComparator implements Comparator<Card> {

        @Override
        public int compare(Card o1, Card o2) {
            return o1.rank-o2.rank;
        }
    }
    static class SuitComparator implements Comparator<Card> {

        @Override
        public int compare(Card o1, Card o2) {
            return o1.suit.compareTo(o2.suit);
        }
    }
    public static void main(String[] args) {
        Card c1=new Card(1,"♠");
        Card c2=new Card(2,"♣");
        RankComparator rankComparator = new RankComparator();
        SuitComparator suitComparator = new SuitComparator();
        System.out.println(rankComparator.compare(c1,c2));
        System.out.println(suitComparator.compare(c1,c2));
    }
}
