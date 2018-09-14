package com.zl.lqian.service;

import com.zl.lqian.dto.AuthInfoDTO;

public interface LoginService {

    /**
     * 删除token
     * @param accessToken
     */
    public void logout(String accessToken);

    public AuthInfoDTO findAuthInfo(String accessToken);
}
