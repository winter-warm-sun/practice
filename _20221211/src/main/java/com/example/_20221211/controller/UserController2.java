package com.example._20221211.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@Slf4j // 替代了之前需要通过LoggerFactory.getLogger操作
public class UserController2 {
    @RequestMapping("sayhi2")
    public void sayHi() {
        log.trace("我是trace");
        log.debug("我是debug");
        log.info("我是info");
        log.warn("我是warn");
        log.error("我是error");
    }
}
