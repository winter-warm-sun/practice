package controller;

import model.UserDao;

public class UserMesController {
    static UserDao userDao=new UserDao();

    public void selectUser(String username) {
        userDao.selectUser(username);
    }

    public void updateUser(String username) {
        userDao.updateUser(username);
    }

    public void deleteUser(String username) {
        userDao.deleteUser(username);
    }
}
