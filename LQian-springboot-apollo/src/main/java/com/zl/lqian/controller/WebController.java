package com.zl.lqian.controller;

import com.alibaba.fastjson.JSONObject;
import com.zl.lqian.dto.ApolloResultDTO;
import com.zl.lqian.dto.AuthInfoDTO;
import com.zl.lqian.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class WebController {

    @Autowired
    LoginService loginService;

    @RequestMapping(value = "/removeToken", method = RequestMethod.POST)
    @ResponseBody
    public ApolloResultDTO logout(@RequestBody JSONObject jsonObject) {

        String accessToken = jsonObject.getString("accessToken");

        loginService.logout(accessToken);

        ApolloResultDTO resultDto = new ApolloResultDTO();

        resultDto.setResult(0);

        return resultDto;
    }

    @RequestMapping(value = "/v1/api/userAuthInfo/{accessToken}", method = RequestMethod.GET)
    @ResponseBody
    public ApolloResultDTO getUserAuthInfo(@PathVariable("accessToken") String accessToken) {
        ApolloResultDTO resultDTO = new ApolloResultDTO();

        AuthInfoDTO authInfoDTO = loginService.findAuthInfo(accessToken);

        if (authInfoDTO != null) {
            resultDTO.setResult(0);
            resultDTO.setData(authInfoDTO);
        } else {
            // 无效的Token
            resultDTO.setResult(99999);
        }

        return resultDTO;
    }
}
