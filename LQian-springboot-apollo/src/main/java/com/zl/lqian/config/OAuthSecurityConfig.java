package com.zl.lqian.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.client.ClientCredentialsTokenGranter;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeTokenGranter;
import org.springframework.security.oauth2.provider.implicit.ImplicitTokenGranter;
import org.springframework.security.oauth2.provider.password.ResourceOwnerPasswordTokenGranter;
import org.springframework.security.oauth2.provider.refresh.RefreshTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * 配置client信息，
 * 以及源码中自带路径（如/oauth/check_token,默认是关闭的）的设置
 */
@Configuration
public class OAuthSecurityConfig extends AuthorizationServerConfigurerAdapter {


    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private AuthenticationManager authenticationManager;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private DataSource dataSource;

    @Bean
    public TokenStore tokenStore(){
        //TODO 这里可以选择基于JDBC 还是 JwtTokenStore
        return new JdbcTokenStore(dataSource);
    }

    /**
     * 配置AuthorizationServerEndpointsConfigurer 中的众多相关类
     * 包括配置身份认证器，
     * 配置认证方式，TokenStore，
     * TokenGranter，OAuth2RequestFactory
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

        //配置身份管理者
        endpoints.authenticationManager(authenticationManager);
        //设置tokenStore基于jdbc
        endpoints.tokenStore(tokenStore());
        //配置tokenservice
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(endpoints.getTokenStore());
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setClientDetailsService(endpoints.getClientDetailsService());
        tokenServices.setTokenEnhancer(endpoints.getTokenEnhancer());
        tokenServices.setAccessTokenValiditySeconds(Integer.MAX_VALUE);
        endpoints.tokenServices(tokenServices);
        //配置token生成
        endpoints.tokenGranter(tokenGranter(endpoints));
    }

    public TokenGranter tokenGranter(AuthorizationServerEndpointsConfigurer endpoints){

        TokenGranter tokenGranter = new TokenGranter() {
            private CompositeTokenGranter delegate;

            @Override
            public OAuth2AccessToken grant(String grantType, TokenRequest tokenRequest) {
                if (delegate != null){
                    delegate = new CompositeTokenGranter(getDefaultTokenGranters(endpoints));
                }
                return delegate.grant(grantType, tokenRequest);
            }
        };
        return tokenGranter;
    }

    private List<TokenGranter> getDefaultTokenGranters(AuthorizationServerEndpointsConfigurer endpoints){

        List<TokenGranter> tokenGranters = new ArrayList<>();
        //配置客户端详细的服务
        ClientDetailsService clientDetails = endpoints.getClientDetailsService();
        AuthorizationServerTokenServices tokenServices = endpoints.getTokenServices();
        AuthorizationCodeServices  authorizationCodeServices = endpoints.getAuthorizationCodeServices();
        OAuth2RequestFactory requestFactory = endpoints.getOAuth2RequestFactory();
        // authorization_code授权码模式
        tokenGranters.add(new AuthorizationCodeTokenGranter(tokenServices, authorizationCodeServices, clientDetails,
                requestFactory));
        //刷新token 专用
        tokenGranters.add(new RefreshTokenGranter(tokenServices, clientDetails, requestFactory));
        //添加简化模式
        ImplicitTokenGranter implicit = new ImplicitTokenGranter(tokenServices, clientDetails, requestFactory);
        tokenGranters.add(implicit);
        //添加客户端模式
        ClientCredentialsTokenGranter clientCredentialsTokenGranter = new ClientCredentialsTokenGranter(tokenServices, clientDetails, requestFactory);
        tokenGranters.add(clientCredentialsTokenGranter);
        if (authenticationManager != null){
            DefaultTokenServices passwordTokenService = new DefaultTokenServices();
            passwordTokenService.setTokenStore(endpoints.getTokenStore());
            passwordTokenService.setRefreshTokenValiditySeconds(30 * 24 * 60 * 60);// 30天
            passwordTokenService.setClientDetailsService(endpoints.getClientDetailsService());
            passwordTokenService.setTokenEnhancer(endpoints.getTokenEnhancer());
            //passwordTokenService.setAccessTokenValiditySeconds(10); // 7天
            passwordTokenService.setAccessTokenValiditySeconds(7 * 24 * 60 * 60);
            //这里添加密码password模式
            tokenGranters.add(new ResourceOwnerPasswordTokenGranter(authenticationManager, passwordTokenService,
                    clientDetails, requestFactory));
        }
        return tokenGranters;
    }
    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("permitAll()");
        oauthServer.allowFormAuthenticationForClients();
    }

    @Bean
    public ClientDetailsService clientDetails() {
        return new JdbcClientDetailsService(dataSource);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetails());
    }


}
