import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.*;

public class MysqlUtil {
    private static Connection connection;
    private static PreparedStatement statement;
    private static ResultSet rs;
    private static String url="jdbc:mysql://localhost:3306/homework?characterEncoding=utf8&useSSL=false";
    private static String user="root";
    private static String password="1234";

    public static Connection getConnection() throws Exception{
        DataSource dataSource=new MysqlDataSource();
        ((MysqlDataSource)dataSource).setURL(url);
        ((MysqlDataSource)dataSource).setUser(user);
        ((MysqlDataSource)dataSource).setPassword(password);
        Connection connection= dataSource.getConnection();
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