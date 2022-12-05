public class NewCarExample2 {
    public static void main(String[] args) {
        Car car=new Car();
        car.init(50,"猛男粉");
    }

    static class Car {
        public void init(int size,String color) {
            Framework framework=new Framework();
            framework.init(size,color);
        }
    }

    static class Framework {
        public void init(int size,String color) {
            Bottom bottom=new Bottom();
            bottom.init(size,color);
        }
    }

    static class Bottom {
        public void init(int size,String color) {
            Tire tire=new Tire();
            tire.init(size,color);
        }
    }

    static class Tire {
        //private int size=30;

        public void init(int size,String color) {
            System.out.println("轮胎尺寸："+size+"|颜色："+color);
        }
    }
}
