//User类：
package login;

import java.io.Serializable;

public class User implements Serializable{

    //serialVersionUID序列化ID------易错点
    private static final long serialVersionUID = 1L;
    private String username;
    private String password;

    public User() {
        super();
    }

    public User(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}