import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class JDBC {
    public static void main(String[] args) throws SQLException {
        DataSource dataSource = new MysqlDataSource();

        //再将MysqlDataSource向上转型，完成URL、User、Password的设置
        ((MysqlDataSource) dataSource).setUrl("jdbc:mysql://localhost:3306/test?characterEncoding=utf8&useSSL=false");//test处可替换  此处为所需要连接的数据库名
        ((MysqlDataSource) dataSource).setUser("root");//本地mysql客户端用户名
        ((MysqlDataSource) dataSource).setPassword("1234");//本地mysql客户端密码

//完成连接
        Connection connection = dataSource.getConnection();
        // [用户输入] 通过用户输入的数据, 来确定插入的值.
        Scanner scanner=new Scanner(System.in);
        System.out.println("请输入要插入的学号：");
        int id=scanner.nextInt();
        System.out.println("请输入要插入的姓名：");
        String name=scanner.nextLine();
        //构造要执行的SQL语句;使用？作为占位符，后边会完成替换
        String sql="insert into student values(?,?)";
        PreparedStatement statement=connection.prepareStatement(sql);
        statement.setInt(1,id);
        statement.setString(2,name);
        int n=statement.executeUpdate();
        System.out.println("n="+n);
        statement.close();
        connection.close();

    }
}
