package login;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbUtil {
    private final String dbUtil="jdbc:mysql://localhost:3306/bjpowernode";//数据库连接地址
    private final String dbUserName="root";//用户名
    private final String dbPassword="1234";//密码
    private final String jdbcName="com.mysql.jdbc.Driver";

    public Connection getCon() throws Exception{
        Class.forName(jdbcName);
        Connection conn = DriverManager.getConnection(dbUtil,dbUserName,dbPassword);
        return conn;
    }//获取数据库连接
}