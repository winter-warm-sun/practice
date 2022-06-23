package action.admin;

import action.Action;
import login.MysqlUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AdminMangeStudent extends Action {
    public AdminMangeStudent() {
        super();
        init();
    }

    @Override
    public void init() {
        JLabel jl1=new JLabel("班级：");
        JLabel jl2=new JLabel("姓名：");
        JLabel jl3=new JLabel("学号：");
        JLabel jl4=new JLabel("用户名：");
        JLabel jl5=new JLabel("密码：");
        JTextField jt1=new JTextField(20);
        JTextField jt2=new JTextField(20);
        JTextField jt3=new JTextField(20);
        JTextField jt4=new JTextField(20);
        JTextField jt5=new JTextField(20);
        JButton jButton=new JButton("添加");
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //
                try {
                    Connection connection= MysqlUtil.getConnection();
                    String sql1="insert into student values(null,?,?,?,?,?,null)";
                    String sql2="insert into loadinformation values(null,?,?,'学生')";

                    PreparedStatement statement1=connection.prepareStatement(sql1);
                    PreparedStatement statement2=connection.prepareStatement(sql2);
                    statement1.setInt(1,Integer.parseInt(jt1.getText()));
                    statement1.setString(2,jt2.getText());
                    statement1.setString(3,jt3.getText());
                    statement1.setString(4,jt4.getText());
                    statement1.setString(5,jt5.getText());
                    statement2.setString(1,jt4.getText());
                    statement2.setString(2,jt5.getText());
                    int resultSet= statement1.executeUpdate();
                    statement2.executeUpdate();
                    if (resultSet==1) {
                        JOptionPane.showMessageDialog(null,"添加成功！");
                        return;
                    }else {
                        JOptionPane.showMessageDialog(null,"添加失败！");
                        return;
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        Box hbox1=Box.createHorizontalBox();
        hbox1.add(jl1);
        hbox1.add(Box.createHorizontalStrut(20));
        hbox1.add(jt1);
        Box hbox2=Box.createHorizontalBox();
        hbox2.add(jl2);
        hbox2.add(Box.createHorizontalStrut(20));
        hbox2.add(jt2);
        Box hbox3=Box.createHorizontalBox();
        hbox3.add(jl3);
        hbox3.add(Box.createHorizontalStrut(20));
        hbox3.add(jt3);
        Box hbox4=Box.createHorizontalBox();
        hbox4.add(jl4);
        hbox4.add(Box.createHorizontalStrut(20));
        hbox4.add(jt4);
        Box hbox5=Box.createHorizontalBox();
        hbox5.add(jl5);
        hbox5.add(Box.createHorizontalStrut(20));
        hbox5.add(jt5);
        Box vbox=Box.createVerticalBox();
        vbox.add(Box.createVerticalStrut(200));
        vbox.add(hbox1);
        vbox.add(Box.createVerticalStrut(50));
        vbox.add(hbox2);
        vbox.add(Box.createVerticalStrut(50));
        vbox.add(hbox3);
        vbox.add(Box.createVerticalStrut(50));
        vbox.add(hbox4);
        vbox.add(Box.createVerticalStrut(50));
        vbox.add(hbox5);
        vbox.add(Box.createVerticalStrut(50));
        vbox.add(jButton);
        add(vbox);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setFont(new Font("仿宋",Font.BOLD,60));
        g.setColor(Color.blue);
        g.drawString("请输入要添加学生的信息",100,100);
    }
}
