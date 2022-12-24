package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RestaurantDao {
    /**
     * 单元测试方法
     * @param args
     */
    public static void main(String[] args) {
        RestaurantDao restaurantDao=new RestaurantDao();
        try {
            restaurantDao.doOrder("123");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * 判断用户是否注册，并实现对文本中保存的数据的账号密码验证。
     * @param username
     * @param password
     * @return
     */
    public  boolean adminLogin(String username,String password) throws SQLException {
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
     * 查询参观信息
     * @param username
     * @return
     */
    public String selectUser(String username) throws SQLException {
        Restaurant restaurant=new Restaurant();
        Connection connection=DBUtil.getConnection();
        String sql="select * from restaurant where username=?";
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        try {
            statement=connection.prepareStatement(sql);
            statement.setString(1,username);
            resultSet=statement.executeQuery();
            if(resultSet.next()) {
                restaurant.setUserName(resultSet.getString("username"));
                restaurant.setPassword(resultSet.getString("password"));
                restaurant.setName(resultSet.getString("name"));
                restaurant.setAddress(resultSet.getString("address"));
                restaurant.setSpecialty(resultSet.getString("specialty"));
                restaurant.setDistance(resultSet.getString("distance"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,statement,resultSet);
        }
        return restaurant.toString();
    }

    /**
     * 修改餐馆信息
     * @param username
     */
    public void updateUser(String username,String password,String specialty) throws SQLException {
        Connection connection=DBUtil.getConnection();
        String sql="update restaurant set password=?,specialty=? where username=?";
        PreparedStatement statement=null;
        try {
            statement=connection.prepareStatement(sql);
            statement.setString(1,password);
            statement.setString(2,specialty);
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
     * 删除餐馆信息
     * @param username
     */
    public void deleteUser(String username) throws SQLException {
        Connection connection=DBUtil.getConnection();
        String sql="delete from restaurant where username=?";
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
     * 查询所有预定
     * @param username
     * @return
     */
    public List<Order> searchAllOrder(String username) throws SQLException {
        List<Order> list=new ArrayList<>();
        Connection connection=DBUtil.getConnection();
        String sql="select * from order_ where rid=(select rid from restaurant where username=?)";
        String sql2="select username from user where uid=?";
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
                order.setIsDone(resultSet.getInt("isDone"));

                String uid=resultSet.getString("uid");
                statement2=connection.prepareStatement(sql2);
                statement2.setString(1,uid);
                resultSet2=statement2.executeQuery();
                if(resultSet2.next()) {
                    order.setUser(resultSet2.getString("username"));
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
     * 查询某用户预定
     * @param username
     * @return
     */
    public List<Order> searchUserOrder(String username) throws SQLException {
        List<Order> list=new ArrayList<>();
        Connection connection=DBUtil.getConnection();
        String sql="select * from order_ where uid=(select uid from user where username=?)";
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        try {
            statement=connection.prepareStatement(sql);
            statement.setString(1,username);
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
     * 处理预定
     * @param username
     */
    public void doOrder(String username) throws SQLException {
        Connection connection=DBUtil.getConnection();
        String sql="update order_ set isDone=1 where uid=(select uid from user where username=?)";
        PreparedStatement statement=null;
        try {
            statement=connection.prepareStatement(sql);
            statement.setString(1,username);
            int res=statement.executeUpdate();
            if(res==1) {
                System.out.println("修改订单状态成功！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,statement,null);
        }
    }
}
