package com.zl.lqian.service.impl;

import com.zl.lqian.service.LoginService;
import com.zl.lqian.utils.HttpClientUtils;
import com.zl.lqian.utils.JsonUtils;
import com.zl.lqian.utils.MD5Utils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class LoginServiceImpl implements LoginService {

    @Override
    public Map<String, String> login(String account, String password) {
        Map headers = new HashMap<>();
        headers.put("Accept", "application/json, text/plain");
        headers.put("Accept-Encoding", "gzip, deflate");
        headers.put("Accept-Language", "zh-CN");
        headers.put("Connection", "keep-alive");
        headers.put("Content-Type", "application/json");
        headers.put("Host", "api.12306.com");
        headers.put("Origin", "http://www.12306.com");
        headers.put("Referer", "http://www.12306.com/");
        headers.put("User-Agent", HttpClientUtils.pcUserAgentArray[new Random().nextInt(HttpClientUtils.pcUserAgentArray.length)]);

        String json = HttpClientUtils.doPostJson("http://api.12306.com/oauth/token?client_id=client&client_secret=secret&grant_type=password&password=" + MD5Utils.md5(password) + "&username=" + account, "{}", headers);
        return JsonUtils.jsonToMap(json, String.class, String.class);
    }

    public static void main(String[] args){

        LoginServiceImpl loginService = new LoginServiceImpl();
        loginService.login("13162706810","19951215zl");
    }
}
