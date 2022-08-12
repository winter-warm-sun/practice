import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class JDBCUpdate {
    public static void main(String[] args) throws SQLException {
        //1.创建数据源
        DataSource dataSource = new MysqlDataSource();
        ((MysqlDataSource)dataSource).setURL("jdbc:mysql://127.0.0.1:3306/test?characterEncoding=utf8&useSSL=false");
        ((MysqlDataSource)dataSource).setUser("root");
        ((MysqlDataSource)dataSource).setPassword("1234");

        //2.和数据库建立连接
        Connection connection=dataSource.getConnection();

        //3.构造SQL语句
        Scanner scanner=new Scanner(System.in);
        System.out.println("请输入要修改的同学学号：");
        int id=scanner.nextInt();
        System.out.println("请输入要修改成的姓名: ");
        String name = scanner.next();

        String sql = "update student set name = ? where id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, name);
        statement.setInt(2, id);

        // 4. 执行 SQL
        int n = statement.executeUpdate();
        System.out.println("n = " + n);

        // 5. 关闭释放资源
        statement.close();
        connection.close();
    }
}
