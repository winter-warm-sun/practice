package model;

public class Order {
    private String user;
    private String restaurant;
    private String time;
    private Integer isDone;

    @Override
    public String toString() {
        return "Order{" +
                "user='" + user + '\'' +
                ", restaurant='" + restaurant + '\'' +
                ", time='" + time + '\'' +
                ", isDone=" + isDone +
                '}';
    }

    public Order() {
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getIsDone() {
        return isDone;
    }

    public void setIsDone(Integer isDone) {
        this.isDone = isDone;
    }
}
