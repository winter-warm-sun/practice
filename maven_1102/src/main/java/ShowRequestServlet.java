import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

@WebServlet("/showRequest")
public class ShowRequestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //把API执行的结果，放到stringBuilder中
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("<h3>首行部分</h3>");
        stringBuilder.append(req.getProtocol());
        stringBuilder.append("<br>");
        stringBuilder.append(req.getMethod());
        stringBuilder.append("<br>");
        stringBuilder.append(req.getRequestURI());
        stringBuilder.append("<br>");
        stringBuilder.append(req.getContextPath());
        stringBuilder.append("<br>");
        stringBuilder.append(req.getQueryString());
        stringBuilder.append("<br>");
        stringBuilder.append("<h3>header部分</h3>");
        Enumeration<String> headerNames=req.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName= headerNames.nextElement();
            String headerValue=req.getHeader(headerName);
            stringBuilder.append(headerName+"："+headerValue+"<br>");
        }
        resp.setContentType("text/html;charset=utf8");
        resp.getWriter().write(stringBuilder.toString());
    }
}
