package com.zl.lqian.service.client;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value="${client.feign.apollo}")
public interface ApolloOAuthService {

    @RequestMapping(value = "/oauth/token", method = RequestMethod.POST)
    public JSONObject getOAuthToken(@RequestParam(value = "grant_type") String grant_type,
                                    @RequestParam(value = "username") String username,
                                    @RequestParam(value = "password") String password,
                                    @RequestParam(value = "client_id") String client_id,
                                    @RequestParam(value = "client_secret") String client_secret,
                                    @RequestParam(value = "source")String source);

    @RequestMapping(value = "/removeToken", method = RequestMethod.POST)
    public JSONObject removeToken(@RequestBody JSONObject jsonObject);

    @RequestMapping(value = "/oauth/token", method = RequestMethod.POST)
    public JSONObject refreshToken(@RequestParam(value = "grant_type") String grant_type,
                                   @RequestParam(value = "client_id") String client_id,
                                   @RequestParam(value = "client_secret") String client_secret,
                                   @RequestParam(value = "refresh_token") String refresh_token);

}
