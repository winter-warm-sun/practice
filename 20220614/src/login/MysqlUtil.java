package login;

import java.sql.*;

public class MysqlUtil {
    private static Connection connection;
    private static Statement statement;
    private static ResultSet rs;
    private static String url="jdbc:mysql://localhost:3306/homework";
    private static String user="root";
    private static String password="1234";

    public static Connection getConnection() throws Exception{
            Class.forName("com.mysql.jdbc.Driver");
            connection= DriverManager.getConnection(url,user,password);
            return connection;
    }

    public static void close() throws SQLException {
        if(rs!=null)
            rs.close();
        if(statement!=null)
            statement.close();
        if(connection!=null)
            connection.close();
    }
}