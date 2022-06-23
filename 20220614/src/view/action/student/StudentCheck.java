package action.student;
import action.Action;
import login.MysqlUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentCheck extends Action {
    public StudentCheck() {
        super();
        init();
    }

    @Override
    public void init() {
        JLabel jl1=new JLabel("用户名：");
        JLabel jl2=new JLabel("班级：");
        JLabel jl3=new JLabel("姓名：");
        JLabel jl4=new JLabel("学号：");
        JLabel jl5=new JLabel("最终分数：");
        JTextField jt1=new JTextField(20);
        JTextField jt2=new JTextField(20);
        JTextField jt3=new JTextField(20);
        JTextField jt4=new JTextField(20);
        JTextField jt5=new JTextField(20);
        //设置这三个栏目不可更改，用户名栏在完成查询后也不可改
        jt2.setEditable(false);
        jt3.setEditable(false);
        jt4.setEditable(false);
        JButton jButton=new JButton("查询");
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection connection= MysqlUtil.getConnection();
                    String sql="select *from student where username=?";
                    PreparedStatement statement=connection.prepareStatement(sql);
                    statement.setString(1,jt1.getText());
                    ResultSet resultSet= statement.executeQuery();
                    while (resultSet.next()) {
                        jt2.setText(resultSet.getString("class"));
                        jt3.setText(resultSet.getString("name"));
                        jt4.setText(resultSet.getString("num"));
                        jt1.setEditable(false);
                        if(resultSet.getString("score")==null) {
                            JOptionPane.showMessageDialog(null,"您的评定结果尚未完成，请继续等待！");
                        }else {
                            jt5.setText(resultSet.getString("score"));
                            jt5.setEditable(false);
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    try {
                        MysqlUtil.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
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
        g.setFont(new Font("仿宋",Font.BOLD,50));
        g.setColor(Color.blue);
        g.drawString("请再次输入用户名查询您的最终认定结果",5,100);
    }
}
