package com.zl.lqian.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.web.cors.CorsUtils;

@EnableGlobalMethodSecurity(prePostEnabled=true)
public class OAuthResourceConfig extends ResourceServerConfigurerAdapter {

    @Value("${security.oauth2.client.client-id}")
    private String clientId;
    @Value("${security.oauth2.client.client-secret}")
    private String clientSecret;
    @Value("${security.oauth2.resource.id}")
    private String resourceId;
    @Value("${security.oauth2.resource.token-info-uri}")
    private String checkTokenUri;

    @Bean
    public RemoteTokenServices remoteTokenServices() {
        RemoteTokenServices remoteTokenServices = new RemoteTokenServices();
        remoteTokenServices.setClientId(clientId);
        remoteTokenServices.setClientSecret(clientSecret);
        remoteTokenServices.setCheckTokenEndpointUrl(checkTokenUri);
        return remoteTokenServices;
    }

    /**
     * 这里过滤url的路径
     * scope 代表域，这里代指系统
     * @param http
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                .antMatchers("/api/**").authenticated()//允许认证过后用户访问
                .antMatchers("/loginSystem").anonymous()//允许匿名用户,也就是完全暴漏的api 很危险
                //只允许包含scope域的api访问，access()参数是一个el表达式 类似于oauth2.hasScope 等
                .antMatchers("/**").access("#oauth2.hasScope('app')");
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(resourceId);
        resources.tokenServices(remoteTokenServices());
    }
}
