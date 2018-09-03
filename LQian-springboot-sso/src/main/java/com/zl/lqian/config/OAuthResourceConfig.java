package com.zl.lqian.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@EnableGlobalMethodSecurity(prePostEnabled=true)
public class OAuthResourceConfig extends ResourceServerConfigurerAdapter {


   /* @Value("${security.oauth2.client.client-id}")
    private String clientId;
    @Value("${security.oauth2.client.client-secret}")
    private String clientSecret;
    @Value("${security.oauth2.resource.id}")
    private String resourceId;
    @Value("${security.oauth2.resource.token-info-uri}")
    private String checkTokenUri;*/
}
