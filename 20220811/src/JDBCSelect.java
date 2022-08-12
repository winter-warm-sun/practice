import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCSelect {
    public static void main(String[] args) throws SQLException {
        // 1. 创建数据源
        DataSource dataSource = new MysqlDataSource();
        ((MysqlDataSource)dataSource).setURL("jdbc:mysql://127.0.0.1:3306/test?characterEncoding=utf8&useSSL=false");
        ((MysqlDataSource)dataSource).setUser("root");
        ((MysqlDataSource)dataSource).setPassword("1234");

        // 2. 建立连接
        Connection connection = dataSource.getConnection();

        // 3. 构造 SQL
        String sql = "select * from student where id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, 1);

        // 4. 执行 SQL
        ResultSet resultSet = statement.executeQuery();

        // 5. 遍历结果集合
        while (resultSet.next()) {
            // 每次循环, 就能够获取到 resultSet 中的一行. 进一步的就可以拿到每一列!!
            // getXXX 也是有一系列方法的. 会根据要取的数据的类型, 来灵活决策.
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            System.out.println("id = " + id + ", name =" + name);
        }

        // 6. 释放资源
        resultSet.close();
        statement.close();
        connection.close();
    }
}
