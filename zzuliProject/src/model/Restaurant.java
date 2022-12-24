package model;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String userName;
    private String password;
    private String name;
    private String address;
    private String specialty;
    private String distance;
    private List<Order> list;

    public Restaurant(String userName, String password, String name, String address, String specialty, String distance) {
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.address = address;
        this.specialty = specialty;
        this.distance = distance;
        this.list = new ArrayList<>();
    }

    public List<Order> getList() {
        return list;
    }

    public void setList(List<Order> list) {
        this.list = list;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
}
