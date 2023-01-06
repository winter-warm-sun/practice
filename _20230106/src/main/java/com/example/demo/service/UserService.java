package com.example.demo.service;

import com.example.demo.mapper.UserMapper;
import com.example.demo.model.UserInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;

@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    public int add(String username,String password,String photo) {
        return userMapper.add(username,password,photo);
    }

    @Transactional(propagation = Propagation.NESTED)
    public int save(String username,String password,String photo) {
        try {
            int result = 10 / 0;
        } catch (Exception e) {
            System.out.println("ex:" + e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return userMapper.add(username, password, photo);
    }
}
