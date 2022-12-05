public class NewCarExample {
    public static void main(String[] args) {
        Car car=new Car();
        car.init();
    }

    static class Car {
        public void init() {
            Framework framework=new Framework();
            framework.init();
        }
    }

    static class Framework {
        public void init() {
            Bottom bottom=new Bottom();
            bottom.init();
        }
    }

    static class Bottom {
        public void init() {
            Tire tire=new Tire();
            tire.init();
        }
    }

    static class Tire {
        private int size=30;

        public void init() {
            System.out.println("轮胎尺寸："+size);
        }
    }
}
