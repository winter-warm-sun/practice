package com.beans;

import org.springframework.stereotype.Controller;

@Controller
public class APIController {
    public void sayHi() {
        System.out.println("你好，APIController");
    }
}
