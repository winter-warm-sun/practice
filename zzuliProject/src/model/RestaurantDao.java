package model;

import java.util.List;

public class RestaurantDao {
    /**
     * 判断用户是否注册，并实现对文本中保存的数据的账号密码验证。
     * @param userName
     * @param password
     * @return
     */
    public  boolean adminLogin(String userName,String password){
        return false;
    }

    /**
     * 查询参观信息
     * @param username
     * @return
     */
    public String selectUser(String username) {
        return null;
    }

    /**
     * 修改餐馆信息
     * @param username
     */
    public void updateUser(String username) {

    }

    /**
     * 删除餐馆信息
     * @param username
     */
    public void deleteUser(String username) {

    }

    /**
     * 查询所有预定
     * @param name
     * @return
     */
    public List<Order> searchAllOrder(String name) {
        return null;
    }

    /**
     * 查询某用户预定
     * @param username
     * @return
     */
    public List<Order> searchUserOrder(String username) {
        return null;
    }

    /**
     * 处理预定
     * @param orderId
     * @param list
     */
    public void doOrder(int orderId,List<Order> list) {

    }
}
