package com.example._20221215.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.UUID;
@Slf4j
@RestController
public class UserController {
    @RequestMapping("/people/{id}/{name}")
    public String getPeoInfo(@PathVariable Integer id,@PathVariable String name) {
        return "ID:"+id+" | Name:"+name;
    }

    // 从配置文件中读取图片的保存路径
    @Value("${img.path}")
    private String imgPath;

    @RequestMapping("/upimg")
    public boolean umImg(Integer uid, @RequestPart("img") MultipartFile file) {
        boolean flag=false;
        String fileName=file.getOriginalFilename(); // 得到原图片的名称(xxx.png)
        fileName=fileName.substring(fileName.lastIndexOf(".")); // 得到图片后缀(.png)
        fileName= UUID.randomUUID().toString()+fileName; // 通过UUID拼接类型后缀生成随机文件名
        // 保存图片到本地目录
        try {
            file.transferTo(new File(imgPath+fileName));
            flag=true;
        } catch (IOException e) {
            log.error("上传图片失败："+e.getMessage());
        }
        return flag;
    }

    @RequestMapping("/cookie")
    public void getCookie(HttpServletRequest request) {
        // 得到全部的 Cookie
        Cookie[] cookies = request.getCookies();
        for (Cookie item : cookies) {
            log.info("Cookie Name：" + item.getName() +
                    " | Cookie Value：" + item.getValue());
        }
    }

    @RequestMapping("/cookie2")
    public String getCookie2(@CookieValue("CSDN")String cookie) {
        return "Cookie Value:" +cookie;
    }

    @RequestMapping("/getua")
    public String getHead(HttpServletRequest request) {
        return "Header: "+request.getHeader("User-Agent");
    }

    @RequestMapping("/getua2")
    public String getHead2(@RequestHeader("User-Agent") String userAgent) {
        return "Header: "+userAgent;
    }

    @RequestMapping("/setsess")
    public boolean setSession(HttpServletRequest request) {
        boolean flag=false;
        // 1.得到HttpSession
        HttpSession session=request.getSession(true);// true=>如果没有会话，那么会创建一个会话
        // 2.使用setAttribute设置值
        session.setAttribute("csdn","如风暖阳");
        flag=true;
        return flag;
    }

    @RequestMapping("/getsess")
    public String getSession(HttpServletRequest request) {
        String result=null;
        // 1.得到HttpSession对象
        HttpSession session = request.getSession(false);// false=>如果有会话就使用会话，没有会话也不会新创建会话
        // 2.getAttribute方法得到Session对象
        if(session!=null && session.getAttribute("csdn")!=null) {
            result=(String) session.getAttribute("csdn");
        }
        return result;
    }

    @RequestMapping("/getsess2")
    public String getSession2(@SessionAttribute(value = "csdn",
            required = false) String csdn) {
        return "会话："+ csdn;
    }
}
