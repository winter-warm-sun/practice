package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    /**
     * 单元测试方法
     * @param args
     */
    public static void main(String[] args) throws SQLException {
        UserDao userDao=new UserDao();
        Order order=new Order();
        order.setUser("123");
        order.setRestaurant("俏江南");
        userDao.addOrder(order);
    }
    /**
     * 判断用户是否注册，并实现对文本中保存的数据的账号密码验证。
     * @param username
     * @param password
     * @return
     */
    public  boolean userLogin(String username,String password) throws SQLException {
        Connection connection=DBUtil.getConnection();
        String sql="select * from user where username=?";
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        try {
            statement=connection.prepareStatement(sql);
            statement.setString(1,username);
            resultSet=statement.executeQuery();
            String s="";
            if(resultSet.next()) {
                s=resultSet.getString("password");
            }
            if(s.equals(password)) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,statement,resultSet);
        }
        return false;
    }

    /**
     * 注册用户
     * @param username
     * @param password
     * @param phone
     */
    public void addUser(String username,String password,String phone) throws SQLException {
        Connection connection=DBUtil.getConnection();
        String sql="insert into user values (null,?,?,?)";
        PreparedStatement statement=null;
        try {
            statement=connection.prepareStatement(sql);
            statement.setString(1,username);
            statement.setString(2,password);
            statement.setString(3,phone);
            int res=statement.executeUpdate();
            if(res==1) {
                System.out.println("注册成功！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,statement,null);
        }
    }

    /**
     * 查看用户信息
     * @param username
     * @return
     */
    public String selectUser(String username) throws SQLException {
        User user=new User();
        Connection connection=DBUtil.getConnection();
        String sql="select * from user where username=?";
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        try {
            statement=connection.prepareStatement(sql);
            statement.setString(1,username);
            resultSet=statement.executeQuery();
            if(resultSet.next()) {
                user.setUserName(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setPhone(resultSet.getString("phone"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,statement,resultSet);
        }
        return user.toString();
    }

    /**
     * 修改用户信息
     * @param username
     */
    public void updateUser(String username,String password,String phone) throws SQLException {
        Connection connection=DBUtil.getConnection();
        String sql="update user set password=?,phone=? where username=?";
        PreparedStatement statement=null;
        try {
            statement=connection.prepareStatement(sql);
            statement.setString(1,password);
            statement.setString(2,phone);
            statement.setString(3,username);
            int res=statement.executeUpdate();
            if(res==1) {
                System.out.println("修改成功！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,statement,null);
        }
    }

    /**
     * 删除用户信息
     * @param username
     */
    public void deleteUser(String username) throws SQLException {
        Connection connection=DBUtil.getConnection();
        String sql="delete from user where username=?";
        PreparedStatement statement=null;
        try {
            statement=connection.prepareStatement(sql);
            statement.setString(1,username);
            int res=statement.executeUpdate();
            if(res==1) {
                System.out.println("注销成功！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,statement,null);
        }
    }

    /**
     * 餐馆查询
     * @param name
     * @return
     */
    public String findRes(String name) throws SQLException {
        Restaurant restaurant=new Restaurant();
        Connection connection=DBUtil.getConnection();
        String sql="select * from restaurant where name=?";
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        try {
            statement=connection.prepareStatement(sql);
            statement.setString(1,name);
            resultSet=statement.executeQuery();
            if(resultSet.next()) {
                restaurant.setName(resultSet.getString("name"));
                restaurant.setAddress(resultSet.getString("address"));
                restaurant.setDistance(resultSet.getString("distance"));
                restaurant.setSpecialty(resultSet.getString("specialty"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,statement,resultSet);
        }
        return restaurant.toString();
    }

    /**
     * 查询本人所有预定信息
     * @param username
     * @return
     */
    public List<Order> searchUserOrder(String username) throws SQLException {
        List<Order> list=new ArrayList<>();
        Connection connection=DBUtil.getConnection();
        String sql="select * from order_ where uid=(select uid from user where username=?)";
        String sql2="select name from restaurant where rid=?";
        PreparedStatement statement=null;
        PreparedStatement statement2=null;
        ResultSet resultSet=null;
        ResultSet resultSet2=null;
        try {
            statement=connection.prepareStatement(sql);
            statement.setString(1,username);
            resultSet=statement.executeQuery();
            while (resultSet.next()) {
                Order order=new Order();
                order.setTime(resultSet.getString("time"));

                String rid=resultSet.getString("rid");
                statement2=connection.prepareStatement(sql2);
                statement2.setString(1,rid);
                resultSet2=statement2.executeQuery();
                if(resultSet2.next()) {
                    order.setRestaurant(resultSet2.getString("name"));
                }
                list.add(order);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,statement,resultSet);
        }
        return list;
    }

    /**
     * 查询某间餐馆所有预定信息
     * @param name
     * @return
     */
    public List<Order> searchResOrder(String name) throws SQLException {
        List<Order> list=new ArrayList<>();
        Connection connection=DBUtil.getConnection();
        String sql="select * from order_ where rid=(select rid from restaurant where name=?)";
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        try {
            statement=connection.prepareStatement(sql);
            statement.setString(1,name);
            resultSet=statement.executeQuery();
            while (resultSet.next()) {
                Order order=new Order();
                order.setTime(resultSet.getString("time"));
                order.setIsDone(resultSet.getInt("isDone"));
                list.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,statement,resultSet);
        }
        return list;
    }

    /**
     * 添加预定信息
     * @param order
     */
    public void addOrder(Order order) throws SQLException {
        Connection connection=DBUtil.getConnection();
        String sql="insert into order_ values (null,?,?,now(),0)";
        String sql1="select uid from user where username=?";
        String sql2="select rid from restaurant where name=?";
        PreparedStatement statement=null;
        PreparedStatement statement1=null;
        PreparedStatement statement2=null;
        ResultSet resultSet1=null;
        ResultSet resultSet2=null;
        try {
            String uid="";
            statement1=connection.prepareStatement(sql1);
            statement1.setString(1,order.getUser());
            resultSet1=statement1.executeQuery();
            if(resultSet1.next()) {
                uid= resultSet1.getString("uid");
            }

            String rid="";
            statement2=connection.prepareStatement(sql2);
            statement2.setString(1,order.getRestaurant());
            resultSet2=statement2.executeQuery();
            if (resultSet2.next()) {
                rid=resultSet2.getString("rid");
            }

            statement=connection.prepareStatement(sql);
            statement.setString(1,uid);
            statement.setString(2,rid);
            int res=statement.executeUpdate();

            if(res==1) {
                System.out.println("预定成功！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,statement,null);
        }
    }
}
