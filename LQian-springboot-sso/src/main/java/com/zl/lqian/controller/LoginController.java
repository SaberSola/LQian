package com.zl.lqian.controller;


import com.alibaba.fastjson.JSONObject;
import com.zl.lqian.entity.ResponseDTO;
import com.zl.lqian.service.client.ApolloOAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController()
public class LoginController {


    @Value("${security.oauth2.client.client-id}")
    private String clientId;
    @Value("${security.oauth2.client.client-secret}")
    private String clientSecret;
    @Value("${security.oauth2.client.grant-type}")
    private String grantType;

    @Autowired
    ApolloOAuthService apolloOAuthService;

    @RequestMapping(value = "/loginSystem", method = RequestMethod.POST)
    public ResponseDTO login(@RequestBody JSONObject jsonObject) {
        String userName = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        ResponseDTO resultDto = new ResponseDTO();
        //    logger.info("-------------->登录系统操作开始");
        try {

            JSONObject responseJson = apolloOAuthService.getOAuthToken(grantType, userName, password, clientId, clientSecret, "");

            String accessToken = responseJson.getString("access_token");

            if (accessToken != null && accessToken.length() > 0) {
                resultDto.setResult(0);
                resultDto.setMessage("登录成功");
                resultDto.setData(accessToken);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultDto;
    }
}
