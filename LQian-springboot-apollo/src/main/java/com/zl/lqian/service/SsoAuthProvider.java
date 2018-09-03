package com.zl.lqian.service;

import com.zl.lqian.common.SpringBeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 *
 * 实现AuthenticationProvider
 */
@Component
public class SsoAuthProvider implements AuthenticationProvider {


    private static final Logger log = LoggerFactory.getLogger(SsoAuthProvider.class);

    @Autowired
    SsoAuthService ssoAuthService;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        log.debug("自定义provider调用");
        Object details = authentication.getDetails();
        if (!(details instanceof LinkedHashMap) || !((LinkedHashMap) details).containsKey("grant_type") || !((LinkedHashMap) details).containsKey("client_id")) {
            List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            return new UsernamePasswordAuthenticationToken(authentication.getName(),authentication.getCredentials(), authorities);
        }else {
            //这里是客户端类型的登录
            String grant_type = ((LinkedHashMap) details).get("grant_type").toString();
            String client_id = ((LinkedHashMap) details).get("client_id").toString();
            //这里区分
            final Class clazz = ssoAuthService.factoryOf(client_id);
            SsoAuthHandler handler =
                    (SsoAuthHandler) SpringBeanUtils.getInstance().getBean(clazz);
            Authentication auth = handler.auth(authentication);
            return auth;
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
