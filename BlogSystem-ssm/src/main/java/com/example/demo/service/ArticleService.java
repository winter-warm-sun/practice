package com.example.demo.service;

import com.example.demo.mapper.ArticleMapper;
import com.example.demo.model.ArticleInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ArticleService {
    @Resource
    private ArticleMapper articleMapper;

    public List<ArticleInfo> getMyList(Integer uid) {
        return articleMapper.getMyList(uid);
    }

    public ArticleInfo getDetail(Integer aid) {
        return articleMapper.getDetail(aid);
    }

    public int update(Integer aid, Integer uid, String title, String content) {
        return articleMapper.update(aid,uid,title,content);
    }

    public int add(Integer uid, String title, String content) {
        return articleMapper.add(uid,title,content);
    }

    public int delete(Integer aid) {
        return articleMapper.delete(aid);
    }

    public List<ArticleInfo> getList(Integer psize, Integer offset) {
        return articleMapper.getList(psize,offset);
    }

    public int getTotalCount() {
        return articleMapper.getTotalCount();
    }
}
