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

    @RequestMapping("/detailbyid")
    public Object getDetailById(HttpServletRequest request,Integer aid) {
        if(aid!=null&&aid>0) {
            // 根据文章id查询文章详情
            ArticleInfo articleInfo=articleService.getDetail(aid);
            // 文章归属人验证
            UserInfo userInfo=SessionUtil.getLoginUser(request);
            //文章归属人正确
            if(userInfo!=null&&articleInfo!=null&&userInfo.getId()== articleInfo.getUid()) {
                return Result.success(articleInfo);
            }
        }
        return Result.fail(-1,"查询失败");
    }

    @RequestMapping("/update")
    public int update(HttpServletRequest request,Integer aid,String title,String content) {
        if(aid != null&& aid>0) {
            UserInfo userInfo=SessionUtil.getLoginUser(request);
            if(userInfo!=null&&userInfo.getId()>0) {
                return articleService.update(aid,userInfo.getId(),title,content);
            }
        }
        return 0;
    }

    @RequestMapping("/add")
    public int add(HttpServletRequest request,String title,String content) {
        UserInfo userInfo=SessionUtil.getLoginUser(request);
        if(userInfo!=null&&userInfo.getId()>0) {
            return articleService.add(userInfo.getId(),title,content);
        }else {
            return 0;
        }
    }

    @RequestMapping("/delete")
    public int delete(HttpServletRequest request,Integer aid) {
        if(aid!=null&&aid>0) {
            // 根据文章id查询文章详情
            ArticleInfo articleInfo=articleService.getDetail(aid);
            // 文章归属人验证
            UserInfo userInfo=SessionUtil.getLoginUser(request);
            //文章归属人正确
            if(userInfo!=null&&articleInfo!=null&&userInfo.getId()== articleInfo.getUid()) {
                return articleService.delete(aid);
            }
        }
        return 0;
    }
}
