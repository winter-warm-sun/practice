package com.example.demo.common;

import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.util.UUID;

/**
 * 加盐加密类
 */
public class SecurityUtil {
    /**
     * 加盐加密
     * @param password
     * @return
     */
    public static String encrypt(String password) {
        // 每次生成内容不同，但长度固定位32的盐值
        String salt= UUID.randomUUID().toString().replace("-","");
        // 加密密码=md5(盐值+原始密码)
        String encryptPassword= DigestUtils.md5DigestAsHex((salt+password).getBytes());
        return salt+encryptPassword;
    }

    /**
     * 密码验证
     * @param password  用户输入的待验证的密码
     * @param finalPassword  最终数据库中存储的密码
     * @return
     */
    public static boolean decrypt(String password,String finalPassword) {
        // 非空效验
        if(!StringUtils.hasLength(password)||!StringUtils.hasLength(finalPassword)) {
            return false;
        }
        if(finalPassword.length()!=64) { // 最终密码不正确
            return false;
        }
        // 数据库中存储密码的前32位为原密码的盐值
        String salt=finalPassword.substring(0,32);
        // 使用盐值+待确认的密码生成一个最终的密码
        String securityPassword=
                DigestUtils.md5DigestAsHex((salt+password).getBytes());
        // 使用盐值+最终密码和数据库的真实密码进行对比
        return (salt+securityPassword).equals(finalPassword);
    }
}
