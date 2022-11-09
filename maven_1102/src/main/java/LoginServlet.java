import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf8");
        resp.setCharacterEncoding("utf8");
        //获取用户用户名
        String username=req.getParameter("username");
        String password=req.getParameter("password");
        //验证用户
        if("lisi".equals(username)&&"123".equals(password)) {
            //登录成功
            //创建会话，为后续登录的页面做准备
            HttpSession httpSession= req.getSession(true);
            httpSession.setAttribute("username",username);
            //初始情况下设置登录次数
            httpSession.setAttribute("count",0);
            resp.sendRedirect("index");
        } else {
            resp.getWriter().write("登录失败！");
        }
    }
}
