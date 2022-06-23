package login;

import frame.AdmFrm;
import frame.GroFrm;
import frame.StuFrm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LogFrm extends JFrame {
    public LogFrm() {
        super("软件学院德育学分评分系统");
    }
    private MysqlUtil mysqlUtil=new MysqlUtil();
    private final int WIDTH=500;
    private final int HEIGHT=300;
    //组装试图
    public void init(){
        //位置
        setBounds((ScreenUtils.gerScreenWidth()-WIDTH)/2,(ScreenUtils.gerScreenHeight()-HEIGHT)/2,WIDTH,HEIGHT);
        setResizable(false);//不能从新设置大小

        //设置窗口的内容
        JPanel jp=new JPanel();
        Box vBox=Box.createVerticalBox();//垂直布局Box


        //水平布局Box
        Box ubox=Box.createHorizontalBox();

        //组装用户名
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


        //用户类型
        Box jbox=Box.createHorizontalBox();
        JLabel jLabel=new JLabel("用户类型:");
        JComboBox jcbox=new JComboBox();
        jcbox.addItem("--请选择你的身份--");
        jcbox.addItem("学生");
        jcbox.addItem("评议组");
        jcbox.addItem("管理员");
        jbox.add(jLabel);
        jbox.add(Box.createHorizontalStrut(20));
        jbox.add(jcbox);

        //组装按钮
        Box btnbox=Box.createHorizontalBox();
        JButton loginBtn=new JButton("登陆");

        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //获取用户输入数据
                String userName=uField.getText().trim();
                String password=pField.getText().trim();
                String identity=(String) jcbox.getSelectedItem();
                if(StringUtil.isEmpty(userName)){
                    JOptionPane.showMessageDialog(null,"用户名不能为空！");
                    return;
                }
                if(StringUtil.isEmpty(password)){
                    JOptionPane.showMessageDialog(null,"密码不能为空！");
                    return;
                }
                if(identity.equals("--请选择你的身份--")) {
                    JOptionPane.showMessageDialog(null,"请选择你的身份！");
                    return;
                }

                try {
                    Connection connection= MysqlUtil.getConnection();
                    Statement statement=connection.createStatement();
                    ResultSet rs=statement.executeQuery("select *from information");
                    int flag=0;
                    Frame frame=null;
                    while (rs.next()) {
                        if (rs.getString(2).equals(userName)&&rs.getString(3).equals(password)&&rs.getString(4).equals(identity)) {
                            flag=1;
                            if (identity.equals("学生"))
                                frame=new StuFrm();
                            if(identity.equals("评议组"))
                                frame=new GroFrm();
                            if(identity.equals("管理员"))
                                frame=new AdmFrm();
                        }
                    }
                    if(flag==0) {
                        JOptionPane.showMessageDialog(null,"用户名或密码或身份错误！");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    dispose();
                    try {
                        MysqlUtil.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });


        btnbox.add(loginBtn);

        vBox.add(Box.createVerticalStrut(50));
        vBox.add(ubox);
        vBox.add(Box.createVerticalStrut(20));
        vBox.add(pbox);
        vBox.add(Box.createVerticalStrut(20));
        vBox.add(jbox);
        vBox.add(Box.createVerticalStrut(40));
        vBox.add(btnbox);

        jp.add(vBox);
        this.add(jp);
        //窗口可见
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new LogFrm().init();
    }
}
