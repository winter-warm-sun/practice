package com.example.demo.controller;

import com.example.demo.model.Blog;
import com.example.demo.service.BlogService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/blog")
public class BlogController {
    @Resource
    private BlogService blogService;

    @RequestMapping("/select")
    public List<Blog> select(HttpServletRequest req) {
        String parm = req.getParameter("blogid");
        if (parm == null) {
            // 不存在参数，获取博客列表
            return blogService.selectAll();
        } else {
            // 存在参数，获取博客详情
            int blodid = Integer.parseInt(parm);
            List<Blog> list = new ArrayList<>();
            list.add(blogService.selectOne(blodid));
            return list;
        }
    }
}
