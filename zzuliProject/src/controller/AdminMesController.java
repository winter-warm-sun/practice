package controller;

import model.RestaurantDao;

import java.sql.SQLException;
import java.util.Scanner;

public class AdminMesController {
    static RestaurantDao resDao=new RestaurantDao();

    public void selectUser(String username) {
        try {
            System.out.println(resDao.selectUser(username));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUser(String username) {
        System.out.println("请输入您想要修改后的密码：");
        String password=inString();
        System.out.println("请输入您想要修改后的特色菜：");
        String specialty=inString();
        try {
            resDao.updateUser(username,password,specialty);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(String username) {
        try {
            resDao.deleteUser(username);
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
