package action.student;

import action.Action;

import javax.swing.*;
import java.awt.*;

public class StudentEntry extends Action {
    public StudentEntry() {
        super();
        init();
    }

    @Override
    public void init() {
        Box hbox1=Box.createHorizontalBox();
        JLabel jl1=new JLabel("基础分:");
        JTextField jf1=new JTextField(20);
        hbox1.add(jl1);
        hbox1.add(Box.createHorizontalStrut(20));
        hbox1.add(jf1);
        Box hbox2=Box.createHorizontalBox();
        JLabel jl2=new JLabel("考察分:");
        JTextField jf2=new JTextField(20);
        hbox2.add(jl2);
        hbox2.add(Box.createHorizontalStrut(20));
        hbox2.add(jf2);
        Box vbox=Box.createVerticalBox();
        vbox.add(Box.createVerticalStrut(200));
        vbox.add(hbox1);
        vbox.add(Box.createVerticalStrut(100));
        vbox.add(hbox2);
        add(vbox);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setFont(new Font("仿宋",Font.BOLD,60));
        g.setColor(Color.blue);
        g.drawString("请按照评分标准完成自评",100,100);
    }
}
