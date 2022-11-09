import com.fasterxml.jackson.databind.ObjectMapper;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

class User {
    public int userId;
    public int classId;
}
@WebServlet("/postJson")
public class PostJsonServlet extends HttpServlet {
    //1.创建一个jackson的核心对象
    private ObjectMapper objectMapper=new ObjectMapper();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //2.读取body中的请求，然后使用ObjectMapper来解析成需要的对象
        //readValue就是把JSON格式的字符串，转成Java的对象
        //第一个参数，表示对哪个字符串进行转换（这个参数可以是一个String，也可以是
        // 一个InputStream对象，还可以是一个File对象）
        //第二个参数，表示要把这个JSON格式的字符串，转换成哪个Java对象
        User user=objectMapper.readValue(req.getInputStream(),User.class);
        resp.getWriter().write("userId:"+user.userId+",classId:"+user.classId);
    }
}
