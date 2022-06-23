package action.student;

import action.Action;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentSee extends Action {
    static Image[] images=new Image[3];
    static int i=0;
    static {
        images[0]=Toolkit.getDefaultToolkit().getImage("images/0.jpg");
        images[1]=Toolkit.getDefaultToolkit().getImage("images/1.jpg");
    }
    public StudentSee() {
        super();
        init();
    }
    @Override
    public void init() {
        JButton jb1=new JButton("上一张");
        JButton jb2=new JButton("下一张");
        jb1.setVisible(true);
        jb2.setVisible(true);
        jb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                i=0;
                repaint();
            }
        });
        jb2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                i=1;
                repaint();
            }
        });
        Box box=Box.createHorizontalBox();
        box.add(Box.createVerticalStrut(1520));
        box.add(Box.createHorizontalStrut(630));
        box.add(jb1);
        box.add(Box.createHorizontalStrut(20));
        box.add(jb2);
        add(box);
    }
    @Override
    public void paint(Graphics g) {
        g.drawImage(images[i],0,0,null);
    }
}
