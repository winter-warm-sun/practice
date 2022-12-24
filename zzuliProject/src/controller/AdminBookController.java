package controller;

import model.Order;
import model.RestaurantDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class AdminBookController {
    static RestaurantDao resDao=new RestaurantDao();

    public void searchAllOrder(String name) {
        try {
            resDao.searchAllOrder(name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchUserOrder(String username) {
        resDao.searchUserOrder(username);
    }

    public void doOrder(int orderId) {
        List<Order> list=new ArrayList<>();
        resDao.doOrder(orderId,list);
    }
}
