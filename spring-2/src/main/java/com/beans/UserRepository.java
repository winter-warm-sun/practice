package com.beans;

import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
    public void sayHi() {
        System.out.println("你好，Repository");
    }
}
