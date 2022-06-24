package login;


import Main.TankClient;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

//服务器端实现runnable接口的类
public class ServerRunnable implements Runnable {

    private Socket client;


    public ServerRunnable(Socket client){
        this.client = client;
    }
    @Override
    public void run() {
        try{
            boolean flag = false, type1 = true, type2 = true;
            //用于接收客户端发送的数据
            ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
            //用于向客户端发送反馈数据
            DataOutputStream dos = new DataOutputStream(client.getOutputStream());

            while(true){

                PreparedStatement ps = null;
                ResultSet rs = null;
                User user = (login.User) ois.readObject();
                DbUtil dbUtil = new DbUtil();
                Connection conn = dbUtil.getCon();
                String sql2 = "select username, password from userlist";
                ps = conn.prepareStatement(sql2);
                rs = ps.executeQuery();
                while (rs.next()){
                    if (rs.getString("username").equals(user.getUsername()) && rs.getString("password").equals(user.getPassword())){
                        System.out.println("登陆成功");
                        flag = true;
                        type1 = true;
                        type2 = true;

                        TankClient tc = new TankClient();
                        tc.setLocationRelativeTo(null);
                        tc.setVisible(true);
                        tc.setSize(800,600);
                        tc.launchFrame();
                        break;

                    }else if (rs.getString("username").equals(user.getUsername()) && !rs.getString("password").equals(user.getPassword())){
                        type2 = false;
                        type1 = true;
                    }else {
                        type1 = false;
                        type2 = true;
                    }


                }
                if (!type1){
                    System.out.println("用户不存在，以为您自动注册");
                    String sql = "insert into userlist (username, password) values(?, ?)";
                    ps = conn.prepareStatement(sql);
                    ps.setString(1, user.getUsername());
                    ps.setString(2, user.getPassword());
                    ps.executeUpdate();
                }

                if (!type2){
                    System.out.println("密码错误，请重新输入");
                }



                //给客户端反馈
                dos.writeBoolean(flag);
                if(flag){
                    break;//登录成功，则不需要再接收客户端数据了，结束循环
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}