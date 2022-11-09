import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * 与json格式对应的类
 */
class Message {
    public String from;
    public String to;
    public String message;
}

@WebServlet("/message")
public class MessageServlet extends HttpServlet {
    //用于将类和json进行转换的类
    private ObjectMapper objectMapper=new ObjectMapper();
    //用于存放消息的顺序表
//    private List<Message> messages=new ArrayList<>();

    /**
     * 处理提交消息请求，对数据进行保存
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Message message=objectMapper.readValue(req.getInputStream(),Message.class);
//        messages.add(message);
        save(message);
        //指定响应的数据格式
        resp.setContentType("application/json;charset=utf8");
        resp.getWriter().write("{\"ok\":true}");
    }

    /**
     *获取消息列表，把消息列表中的内容返回给客户端
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //将Java对象转成JSON格式字符串
        List<Message> messages=load();
        String jsonString=objectMapper.writeValueAsString(messages);
        System.out.println("jsonString:"+jsonString);
        resp.setContentType("application/json;charset=utf8");
        resp.getWriter().write(jsonString);
    }

    /**
     * 把一条消息保存到数据库中
     * @param message
     */
    private void save(Message message) {
        Connection connection=null;
        PreparedStatement statement=null;
        try {
            //1.和数据库建立连接
            connection=DBUtil.getConnection();
            //2.构造SQL
            String sql="insert into message values(?,?,?)";
            statement=connection.prepareStatement(sql);
            statement.setString(1,message.from);
            statement.setString(2,message.to);
            statement.setString(3,message.message);
            //3.执行SQL
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,statement,null);
        }
    }

    /**
     * 从数据库中获取到所有的消息
     * @return
     */
    private List<Message> load() {
        List<Message> messages=new ArrayList<>();
        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        try {
            connection=DBUtil.getConnection();
            String sql="select * from message";
            statement=connection.prepareStatement(sql);
            resultSet=statement.executeQuery();
            while (resultSet.next()) {
                Message message=new Message();
                message.from=resultSet.getString("from");
                message.to=resultSet.getString("to");
                message.message=resultSet.getString("message");
                messages.add(message);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,statement,resultSet);
        }
        return messages;
    }
}
