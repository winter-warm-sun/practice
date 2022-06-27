import java.util.ArrayList;
import java.util.List;

class Food {
}
class Fruit extends Food {
}
class Apple extends Fruit {
}
class Banana extends Fruit {
}
class Message<T> {
    private T message;
    public T getMessage() {
        return message;
    }

    public void setMessage(T message) {
        this.message=message;
    }
}
public class Demo1 {
    public static void main(String[] args) {
        List<Integer> list=new ArrayList<>();
        list.remove(0);
    }
    public static void main1(String[] args) {
        Message<Fruit> message1=new Message<>();
        message1.setMessage(new Fruit());
        fun(message1);
        Message<Food> message2=new Message<>();
        message2.setMessage(new Food());
        fun(message2);
    }
    //这里只要是Fruit或者Fruit的父类即可
    public static void fun(Message<? super Fruit> temp) {
        //此时可以修改，添加的是Fruit或者Fruit的子类
        temp.setMessage(new Banana());
        temp.setMessage(new Apple());
        //Fruit fruit=temp.getMessage();不能接收，这里无法确定是哪个父类
        System.out.println(temp.getMessage());//只能直接输出
    }
}
