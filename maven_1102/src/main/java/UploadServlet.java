import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
@MultipartConfig
@WebServlet("/upload")
public class UploadServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取Part对象
        Part part=req.getPart("myfile");
        //输出文件信息
        //文件名
        System.out.println("文件名:"+part.getSubmittedFileName());
        //文件类型
        System.out.println("文件类型："+part.getContentType());
        //文件大小
        System.out.println("文件大小："+part.getSize());
        //将文件写入磁盘
        part.write("D:/Data/copy.jpg");
        //返回响应
        resp.setContentType("text/html;charset=utf8");
        resp.getWriter().write("上传成功！");
    }
}
