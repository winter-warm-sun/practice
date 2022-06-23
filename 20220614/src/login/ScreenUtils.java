package login;

import java.awt.*;

public class ScreenUtils {
    /*
    当前电脑屏幕的宽度
     */
    public static int gerScreenWidth(){
        return Toolkit.getDefaultToolkit().getScreenSize().width;
    }
    /*
    获取当前电脑屏幕的高度
     */
    public static int gerScreenHeight(){
        return Toolkit.getDefaultToolkit().getScreenSize().height;
    }
}