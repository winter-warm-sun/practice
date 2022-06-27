import java.util.List;

public class Test {
    public static void main(String[] args) {
        CardDemo cardDemo=new CardDemo();
        List<Card> ret=cardDemo.buyDeskCard();
        System.out.println(ret);
        System.out.println("洗牌：");
        cardDemo.shuffle(ret);
        System.out.println(ret);
        System.out.println("摸牌：");
        List<List<Card>> ret2=cardDemo.test(ret);
        for (int i = 0; i < ret2.size(); i++) {
            System.out.println("第"+(i+1)+"个人的牌："+ret2.get(i));
        }
        System.out.println("剩余的牌:");
        System.out.println(ret);
    }
}
