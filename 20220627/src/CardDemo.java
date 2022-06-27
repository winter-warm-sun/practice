import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Card {
    public int rank;//数字
    public String suit;//花色

    public Card(int rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }

    //重写打印方法  数字+花色
    @Override
    public String toString() {
        return ""+suit+" "+rank;
    }
}
public class CardDemo {
    public static final String[] suits={"♥","♠","♣","♦"};

    /**
     * 买扑克方法
     * @return 扑克
     */
    public List<Card> buyDeskCard() {
        List<Card> cards=new ArrayList<>();
        //13个数字，每个数字四张不同花色
        for (int i = 1; i <=13 ; i++) {
            for (int j = 0; j < 4; j++) {
                Card card=new Card(i,suits[j]);
                cards.add(card);
            }
        }
        return cards;
    }

    /**
     * 洗牌方法
     */
    public void shuffle(List<Card> cards) {
        for (int i = cards.size()-1; i >0 ; i--) {
            Random random=new Random();
            int index=random.nextInt(i);
            swap(cards,index,i);
        }
    }

    /**
     * 交换两张牌
     * @param cards
     * @param i
     * @param j
     */
    private void swap(List<Card> cards,int i,int j) {
        Card tmp=cards.get(i);
        cards.set(i,cards.get(j));
        cards.set(j,tmp);
    }

    /**
     * 摸牌方法
     * @param cards 牌堆
     * @return  最后的摸牌清空
     */
    public List<List<Card>> test(List<Card> cards) {
        List<Card> hand1 = new ArrayList<>();
        List<Card> hand2 = new ArrayList<>();
        List<Card> hand3 = new ArrayList<>();
        List<List<Card>> hands = new ArrayList<>();
        hands.add(hand1);
        hands.add(hand2);
        hands.add(hand3);
        //3个人 每个人轮流抓5张牌
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 3; j++) {
                //remove方法返回值为牌堆的第一张牌，并且会把这张牌从牌堆中清除
                Card card = cards.remove(0);
                hands.get(j).add(card);
            }
        }
        return hands;
    }
}
