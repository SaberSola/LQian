package com.zl.lqian.service;

import org.springframework.stereotype.Service;

import java.util.Map;

public interface LoginService {


    /**
     * 还好还好 这个网站的登录只需要账号密码啥的
     * @param account
     * @param password
     * @return
     */
    Map<String,String> login(String account, String password);
}
