package controller;

import model.RestaurantDao;

public class AdminMesController {
    static RestaurantDao resDao=new RestaurantDao();

    public void selectUser(String username) {
        resDao.selectUser(username);
    }

    public void updateUser(String username) {
        resDao.updateUser(username);
    }

    public void deleteUser(String username) {
        resDao.deleteUser(username);
    }
}
