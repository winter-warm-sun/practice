import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/postParameter")
public class PostGetParameterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置请求与响应中的编码格式
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html; charset=utf8");
        //获取post请求中的参数
        String userId=req.getParameter("userId");
        String classId=req.getParameter("classId");
        resp.getWriter().write("userId="+userId+",classId="+classId);
    }
}
