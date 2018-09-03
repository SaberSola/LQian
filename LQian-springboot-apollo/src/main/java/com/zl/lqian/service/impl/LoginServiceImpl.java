package com.zl.lqian.service.impl;

import com.zl.lqian.dto.AuthInfoDTO;
import com.zl.lqian.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private TokenStore tokenStore;

    @Override
    public void logout(String accessToken) {

        //获取token信息
        OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(accessToken);
        if (oAuth2AccessToken == null){
            return;
        }
        //从刷新token里移除
        if (oAuth2AccessToken.getRefreshToken() != null){
            tokenStore.removeRefreshToken(oAuth2AccessToken.getRefreshToken());
        }
        tokenStore.removeAccessToken(oAuth2AccessToken);

    }

    /**
     *
     * 获取用户名和clientid
     * @param accessToken
     * @return
     */
    @Override
    public AuthInfoDTO findAuthInfo(String accessToken) {

        OAuth2Authentication oAuth2Authentication = tokenStore.readAuthentication(accessToken);
        if (oAuth2Authentication == null) {
            return null;
        }
        AuthInfoDTO authInfo = new AuthInfoDTO();
        authInfo.setUserName(oAuth2Authentication.getUserAuthentication().getName());
        Object detail = oAuth2Authentication.getUserAuthentication().getDetails();
        if (detail != null && detail instanceof LinkedHashMap){
            LinkedHashMap linkedHashMap = (LinkedHashMap) detail;
            if (linkedHashMap.containsKey("client_id")) {
                String clientId = linkedHashMap.get("client_id").toString();
                authInfo.setClient_id(clientId);
            }
        }
        return authInfo;
    }
}
