import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/index")
public class IndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取会话，参数需要是false
        HttpSession httpSession=req.getSession(false);
        //取出会话信息
        String username=(String)httpSession.getAttribute("username");
        Integer count=(Integer)httpSession.getAttribute("count");

        //访问次数加1
        count++;
        //写回到会话中
        httpSession.setAttribute("count",count);

        //构造页面
        resp.setContentType("text/html;charset=utf8");
        resp.getWriter().write("<h3>hello！"+username+"</h3> <h4>这个主页已经被访问了"+count+"次</h4>");
    }
}
