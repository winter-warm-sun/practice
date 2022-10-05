package Test20220926;

import java.util.Timer;
import java.util.TimerTask;

public class Demo {
    public static void main(String[] args) {
        Timer timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("时间到，快起床！");
            }
        },3000);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("时间到2！");
            }
        },4000);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("时间到3！");
            }
        },5000);
        System.out.println("开始计时！");
    }
}
