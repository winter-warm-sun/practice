package model;

import java.util.List;

public class UserDao {
    /**
     * 判断用户是否注册，并实现对文本中保存的数据的账号密码验证。
     * @param username
     * @param password
     * @return
     */
    public  boolean userLogin(String username,String password){
        return false;
    }

    /**
     * 查看用户信息
     * @param username
     * @return
     */
    public String selectUser(String username) {
        return null;
    }

    /**
     * 修改用户信息
     * @param username
     */
    public void updateUser(String username) {

    }

    /**
     * 删除用户信息
     * @param username
     */
    public void deleteUser(String username) {

    }

    /**
     * 餐馆查询
     * @param name
     * @return
     */
    public String findRes(String name) {
        return null;
    }

    /**
     * 查询本人所有预定信息
     * @param username
     * @return
     */
    public List<Order> searchUserOrder(String username) {
        return null;
    }

    /**
     * 查询某间餐馆所有预定信息
     * @param name
     * @return
     */
    public List<Order> searchResOrder(String name) {
        return null;
    }

    /**
     * 添加预定信息
     * @param order
     */
    public void addOrder(Order order) {

    }
}
