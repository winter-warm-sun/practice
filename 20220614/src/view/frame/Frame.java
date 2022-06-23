package frame;

import javax.swing.*;

public abstract class Frame extends JFrame {
    private int width=980;
    private int height=900;
    JTabbedPane tab=new JTabbedPane();
    public Frame(){
        setSize(width,height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(3);
        setResizable(false);
        setVisible(true);
    }
}
