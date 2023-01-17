package com.example.demo.controller;

import com.example.demo.common.Result;
import com.example.demo.common.SessionUtil;
import com.example.demo.model.ArticleInfo;
import com.example.demo.model.UserInfo;
import com.example.demo.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/art")
public class ArticleController {
    @Resource
    private ArticleService articleService;

    @RequestMapping("/mylist")
    public List<ArticleInfo> myList(HttpServletRequest request) {
        UserInfo userInfo= SessionUtil.getLoginUser(request);
        if(userInfo!=null) {
            return articleService.getMyList(userInfo.getId());
        }
        return null;
    }

    @RequestMapping("/detail")
    public Object getDetail(Integer aid) {
        if(aid!=null&&aid>0) {
            return Result.success(articleService.getDetail(aid));
        }
        return Result.fail(-1,"查询失败");
    }

    @RequestMapping("/detailby")
}
