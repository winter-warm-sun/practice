package controller;

import model.Order;
import model.RestaurantDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class AdminBookController {
    static RestaurantDao resDao=new RestaurantDao();

    public void searchAllOrder(String username) {
        try {
            List<Order> list=resDao.searchAllOrder(username);
            for(Order order:list) {
                System.out.println(order.getUser()+"  "+order.getTime()+"  "+order.getIsDone());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchUserOrder() {
        System.out.println("请输入要查看订单用户的用户名：");
        String username=inString();
        try {
            List<Order> list=resDao.searchUserOrder(username);
            for(Order order:list) {
                System.out.println(order.getTime()+"  "+order.getIsDone());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void doOrder(String username) {
        searchAllOrder(username);
        System.out.println("请输入已完成用户订单的用户名:");
        String name=inString();
        try {
            resDao.doOrder(name);
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
