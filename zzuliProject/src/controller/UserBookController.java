package controller;

import model.Order;
import model.UserDao;

public class UserBookController {
    static UserDao userDao=new UserDao();

    public static void findRes(String name) {
        userDao.findRes(name);
    }

    public void searchUserOrder(String username) {
        userDao.searchUserOrder(username);
    }

    public void searchResOrder(String name) {
        userDao.searchResOrder(name);
    }

    public void addOrder(Order order) {
        userDao.addOrder(order);
    }
}
