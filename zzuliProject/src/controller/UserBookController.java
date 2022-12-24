package controller;

import model.Order;
import model.UserDao;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.dateTime;

public class UserBookController {
    static UserDao userDao=new UserDao();

    public static void findRes() {
        System.out.println("请输入您要查询的餐馆信息：");
        String name=inString();
        try {
            System.out.println(userDao.findRes(name));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchUserOrder(String username) {
        try {
            List<Order> list=userDao.searchUserOrder(username);
            for(Order order:list) {
                System.out.println(order.getRestaurant()+"   "+order.getTime());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchResOrder() {
        System.out.println("请输入要查询餐馆的名称：");
        String name=inString();
        try {
            List<Order> list=userDao.searchResOrder(name);
            for(Order order:list) {
                System.out.println(order.getTime()+"   "+order.getIsDone());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addOrder(String username) {
        System.out.println("请输入您要预定的餐厅：");
        String name=inString();
        Order order=new Order();
        order.setUser(username);
        order.setRestaurant(name);
        try {
            userDao.addOrder(order);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }






    /**
     * 在控制台输入一个数字，并将这个数字返回
     * @return
     */
    public static int inInt(){
        Scanner in = new Scanner(System.in);
        int choice = in.nextInt();
        return choice;
    }


    /**
     * 在控制台输入字符串，并将这个字符串返回
     * @return
     */
    public static String inString(){
        Scanner in = new Scanner(System.in);
        String choice = in.nextLine();
        return choice;
    }
}
