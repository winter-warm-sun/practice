import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class JDBCDelete {
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
        System.out.println("请输入要删除的学号：");
        int id=scanner.nextInt();
        String sql="delete from student where id=?";
        PreparedStatement statement=connection.prepareStatement(sql);
        statement.setInt(1,id);

        //4.执行SQL
        int n=statement.executeUpdate();
        System.out.println("n=:"+n);

        //5.释放资源
        statement.close();
        connection.close();
    }
}
