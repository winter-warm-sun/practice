package net;

import net.message.CommonMessage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientFrame extends JFrame implements Runnable {
    private CommonMessage message;
    private JPanel jPanel;
    public ClientFrame(CommonMessage message) throws HeadlessException {
        this.message=message;
        setTitle("正在进行会话");
        setSize(850,700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        init();
    }

    public void init() {
        jPanel=new JPanel();
        setContentPane(jPanel);
        jPanel.setLayout(null);
        //显示聊天记录的文本区
        JTextArea jt1=new JTextArea();
        jt1.setEditable(false);
        jt1.setBounds(10,10,800,500);
        jPanel.add(jt1);
        JTextArea jt2=new JTextArea();
        jt2.setBounds(10,530,700,130);
        jPanel.add(jt2);
        JButton jButton=new JButton("发送");
        jButton.setBounds(720,550,100,80);
        jPanel.add(jButton);
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
    }
    @Override
    public void run() {

    }

}
