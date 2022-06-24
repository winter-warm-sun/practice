package login;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class LogOnFrm {
    private DbUtil dbUtil=new DbUtil();

    JFrame jf=new JFrame("坦克大战登陆系统");

    final int WIDTH=500;
    final int HEIGHT=300;
    //组装试图
    public void init(){
        //位置
        jf.setBounds((ScreenUtils.gerScreenWidth()-WIDTH)/2,(ScreenUtils.gerScreenHeight()-HEIGHT)/2,WIDTH,HEIGHT);
        jf.setResizable(false);//不能从新设置大小

        //设置窗口的内容
        JPanel jp=new JPanel();
         Box vBox=Box.createVerticalBox();//垂直布局Box


        //水平布局Box
        Box ubox=Box.createHorizontalBox();
        JLabel uLabel=new JLabel("用户名：");
        JTextField uField=new JTextField(15);


        ubox.add(uLabel);
        ubox.add(Box.createHorizontalStrut(20));//设置间隔
        ubox.add(uField);

        //组装密码
        Box pbox=Box.createHorizontalBox();
        JLabel pLabel=new JLabel("密    码：");
        JTextField pField=new JTextField(15);

        pbox.add(pLabel);
        pbox.add(Box.createHorizontalStrut(20));//设置间隔
        pbox.add(pField);



        //组装按钮
        Box btnbox=Box.createHorizontalBox();
        JButton loginBtn=new JButton("登陆 / 注册");

        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //获取用户输入数据
                String username=uField.getText().trim();
                String password=pField.getText().trim();
                boolean flag ;
                Socket client = null;
                try {
                    client = new Socket("127.0.0.1",6688);
                    //用于向服务器端发送数据
                    ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
                    //用于接收服务器端反馈数据
                    DataInputStream dis = new DataInputStream(client.getInputStream());
                    //封装数据，准备向服务器端发送
                    User user = new User(username,password);
                    //发送
                    oos.writeObject(user);
                    flag = dis.readBoolean();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        btnbox.add(loginBtn);
        vBox.add(Box.createVerticalStrut(50));
        vBox.add(ubox);
        vBox.add(Box.createVerticalStrut(20));
        vBox.add(pbox);
        vBox.add(Box.createVerticalStrut(40));
        vBox.add(btnbox);

        jp.add(vBox);
        jf.add(jp);
        //窗口可见
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
        }

    public static void main(String[] args) {
        new LogOnFrm().init();
        }
    }