package controller;

import model.Order;
import model.RestaurantDao;

import java.util.ArrayList;
import java.util.List;


public class AdminBookController {
    static RestaurantDao resDao=new RestaurantDao();

    public void searchAllOrder(String name) {
        resDao.searchAllOrder(name);
    }

    public void searchUserOrder(String username) {
        resDao.searchUserOrder(username);
    }

    public void doOrder(int orderId) {
        List<Order> list=new ArrayList<>();
        resDao.doOrder(orderId,list);
    }
}
