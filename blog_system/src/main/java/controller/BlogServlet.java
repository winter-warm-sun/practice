package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Blog;
import model.BlogDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

//通过这个类来处理 /blog 路径对应的请求
@WebServlet("/blog")
public class BlogServlet extends HttpServlet {
    private ObjectMapper objectMapper=new ObjectMapper();

    //这个方法用来获取到数据库中的博客列表
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=utf8");
        BlogDao blogDao=new BlogDao();
        //先尝试获取到req中的blogId参数，如果该参数存在，说明是要求请求博客详情
        //如果该参数不存在，说明是要请求博客的列表
        String parm=req.getParameter("blogId");
        if(parm==null) {
            //不存在参数，获取博客列表
            List<Blog> blogs=blogDao.selectAll();
            //把blogs对象转成JSON格式
            String respJson=objectMapper.writeValueAsString(blogs);
            resp.getWriter().write(respJson);
        }else {
            //存在参数，获取博客详情
            int blogId=Integer.parseInt(parm);
            Blog blog=blogDao.selectOne(blogId);
            String respJson=objectMapper.writeValueAsString(blog);
            resp.getWriter().write(respJson);
        }
    }
}
